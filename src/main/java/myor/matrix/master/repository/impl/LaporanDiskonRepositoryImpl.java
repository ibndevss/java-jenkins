package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.LaporanDiskonBrowseAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryByAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryPromoByAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryPromoDto;
import myor.matrix.master.entity.LaporanDiskonReportDetailDto;
import myor.matrix.master.entity.LaporanDiskonReportDetailPromoDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryByAPDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryPromoByAPDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryPromoDto;
import myor.matrix.master.repository.LaporanDiskonRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class LaporanDiskonRepositoryImpl implements LaporanDiskonRepository {

	@Autowired
	private TenantContext t;	

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<LaporanDiskonBrowseAPDto> getBrowseAP(String flagHierarki, String tipeDiskon) {
		// TODO Auto-generated method stub
		List<LaporanDiskonBrowseAPDto> result = new ArrayList<>();
		
		String sql = "";
		
		if (flagHierarki.equalsIgnoreCase("0")) {
			sql = " SELECT   kodeap, keterangan, TO_CHAR (tglawal, 'DD MON YYYY') tglawal, "
				 +"          TO_CHAR (tglakhir, 'DD MON YYYY') tglakhir "
				 +"     FROM "+t.getTenantId()+".ftable155 "
				 +"    WHERE tipe = '"+tipeDiskon+"' "
				 +" ORDER BY kodeap ASC ";
		} else {
			sql = " SELECT DISTINCT kodeap, keterangan, TO_CHAR (tglawal, 'DD MON YYYY') tglawal, "
				 +"                 TO_CHAR (tglakhir, 'DD MON YYYY') tglakhir "
				 +"            FROM "+t.getTenantId()+".ftable155_hir "
				 +"           WHERE tipe = '"+tipeDiskon+"' "
				 +"        ORDER BY kodeap ASC ";
		}		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> hasil = query.getResultList();
	
		for (Object[] obj : hasil) {
			LaporanDiskonBrowseAPDto data = new LaporanDiskonBrowseAPDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), Objects.toString((String) obj[2], ""),
					Objects.toString((String) obj[3], "")); 
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public void deleteTemp(String tableName, String userId) {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM "+t.getTenantId()+"."+tableName+" WHERE userId = '"+userId+"' ";
		Query query = entityManager.createNativeQuery(sql);
		
		query.executeUpdate();
	}
	
	@Override
	public List<LaporanDiskonReportSummaryPromoDto> getReportSummaryPromo(String pilihanReport, String tipeDiskon, String flagKlaim,
			String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo,
			String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonReportSummaryPromoDto> result = new ArrayList<>();
		
		String header = "";
		if (pilihanReport.equalsIgnoreCase("2")) {
			header = "MONITORING PROMOSI";
		} else {
			header = "LAPORAN TPR (PROMOSI)";
		}
		
		String filterAP = "";
		if (kodeAPFrom.equalsIgnoreCase("")) {
			filterAP = "ALL";
		} else {
			filterAP = kodeAPFrom + " - " + kodeAPTo;
		}
		
		String filterKlaim = "";
		if (flagKlaim.equalsIgnoreCase("1")) {
			filterKlaim = "Klaim";
		} else {
			filterKlaim = "Tidak Klaim";
		}
		
		String filterTanggal = "";
		if (invDateFrom.equalsIgnoreCase("")) {
			filterTanggal = "ALL";
		} else {
			filterTanggal = invDateFrom + " - " + invDateTo;
		}		
		
		String sql = " SELECT   '"+header+"' || ' - ' || " 
					+"          (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') "
					+"          || ' ' ||  "
					+"          CASE "
					+"             WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END header, "
					+"          (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') || ' - ' || TO_CHAR (SYSDATE, 'HH24:MI:SS') tglsistem FROM DUAL) tglsistem, "
					+"          (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang, "
					+"          (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release,  "
					+"          'PROMOSI BARANG' tipediskon, "
					+"          '"+filterAP+"' filterap,  "
					+"          '"+filterKlaim+"' flagklaim, "
					+"          '"+filterTanggal+"' filtertanggal, "
					+"          'Rekapitulasi' pilihandiskon,  "
					+"          a.kodeap, a.invno, a.invdate, a.slsno, a.slsname, a.custno, a.custname, a.pctpr, a.pctprname, "
//					+" 			a.besar, a.tengah, a.kecil, a.tpramount, "
					+"          CASE "
					+"             WHEN a.transtype = 'F' "
					+"                THEN a.besar "
					+"             ELSE a.besar * -1 "
					+"          END AS besar, "
					+"          CASE "
					+"             WHEN a.transtype = 'F' "
					+"                THEN a.tengah "
					+"             ELSE a.tengah * -1 "
					+"          END AS tengah, "
					+"          CASE "
					+"             WHEN a.transtype = 'F' "
					+"                THEN a.kecil "
					+"             ELSE a.kecil * -1 "
					+"          END AS kecil, a.tpramount, "
					+"          CASE "
					+"             WHEN a.transtype = 'F' "
					+"                THEN b.amount "
					+"             ELSE b.amount * -1 "
					+"          END AS amount "
					+"     FROM ((SELECT ROWNUM AS ID, a.tprkode AS kodeap, h.invno, "
					+"                   TO_CHAR (h.invdate, 'DD MON YYYY') invdate, h.slsno, "
					+"                   s.slsname, h.custno, b.custname, a.pctpr, m.pcodename AS pctprname, "
					+"                   FLOOR (a.qty / m.convunit3) AS besar, "
					+"                   FLOOR (MOD (a.qty, m.convunit3) / m.convunit2) AS tengah, "
					+"                   FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)) AS kecil, "
					+"                   CASE "
					+"                      WHEN h.transtype = 'F' "
					+"                         THEN ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                               + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                               + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) "
					+"                      ELSE   ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                              + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                              + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) * -1 "
					+"                   END AS tpramount, h.transtype "
					+"              FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype "
					+"                   JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
					+"                   JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode "
					+"                   JOIN "+t.getTenantId()+".fjual_d2 d2 "
					+"                   ON a.orderno = d2.orderno "
					+"                 AND a.slsno = d2.slsno "
					+"                 AND a.transtype = d2.transtype "
					+"                 AND a.pctpr = d2.pcode "
					+"                   JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap "
					+"                   JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
					+"             WHERE c.tipe = '"+tipeDiskon+"' "
					+"               AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}
					
		sql += "               AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') a "
			+"          LEFT JOIN "
			+"          (SELECT   d.tprkode, h.invno, SUM (d1.amount) amount "
			+"               FROM "+t.getTenantId()+".fhasiltpr_d d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype "
			+"                    JOIN "+t.getTenantId()+".fjual_d1 d1 ON d.orderno = d1.orderno AND d.slsno = d1.slsno AND d.transtype = d1.transtype AND d.pcsec = d1.pcode "
			+"                    JOIN "+t.getTenantId()+".fmaster m1 ON d1.pcode = m1.pcode "
			+"                    JOIN "+t.getTenantId()+".ftable155 c ON d.tprkode = c.kodeap "
			+"              WHERE c.tipe = '"+tipeDiskon+"' "
			+"                AND c.klaim = '"+flagKlaim+"' ";
					
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}			
					
		sql += "                AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"' "
			+"           GROUP BY d.tprkode, h.invno) b ON a.kodeap = b.tprkode AND a.invno = b.invno) "
			+" ORDER BY a.kodeap, a.invno, a.pctpr ASC ";
		Query query = entityManager.createNativeQuery(sql);
				
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			LaporanDiskonReportSummaryPromoDto data = new LaporanDiskonReportSummaryPromoDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""), Objects.toString(obj[5], ""),
					Objects.toString(obj[6], ""), Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""), Objects.toString(obj[11], ""),
					Objects.toString(obj[12], ""), Objects.toString(obj[13], ""), Objects.toString(obj[14], ""),
					Objects.toString(obj[15], ""), Objects.toString(obj[16], ""), Objects.toString(obj[17], ""),
					(BigDecimal) obj[18], (BigDecimal) obj[19], (BigDecimal) obj[20], (BigDecimal) obj[21],
					(BigDecimal) obj[22]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonReportSummaryPromoDto> getReportSummaryPromoHierarki(String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonReportSummaryPromoDto> result = new ArrayList<>();
		
		String header = "";
		if (pilihanReport.equalsIgnoreCase("2")) {
			header = "MONITORING PROMOSI";
		} else {
			header = "LAPORAN TPR (PROMOSI)";
		}
		
		String filterAP = "";
		if (kodeAPFrom.equalsIgnoreCase("")) {
			filterAP = "ALL";
		} else {
			filterAP = kodeAPFrom + " - " + kodeAPTo;
		}
		
		String filterKlaim = "";
		if (flagKlaim.equalsIgnoreCase("1")) {
			filterKlaim = "Klaim";
		} else {
			filterKlaim = "Tidak Klaim";
		}
		
		String filterTanggal = "";
		if (invDateFrom.equalsIgnoreCase("")) {
			filterTanggal = "ALL";
		} else {
			filterTanggal = invDateFrom + " - " + invDateTo;
		}		
		
		String sql = " SELECT   '"+header+"' || ' - ' || "
					+"          (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') || ' ' || " 
					+"          CASE "
					+"             WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END header, "
					+"          (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') || ' - ' || TO_CHAR (SYSDATE, 'HH24:MI:SS') tglsistem FROM DUAL) tglsistem, "
					+"          (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang, "
					+"          (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release,  "
					+"          'PROMOSI BARANG' tipediskon, "
					+"          '"+filterAP+"' filterap,  "
					+"          '"+filterKlaim+"' flagklaim, "
					+"          '"+filterTanggal+"' filtertanggal, "
					+"          'Rekapitulasi' pilihandiskon, " 
					+"          a.kodeap, a.invno, a.invdate, a.slsno, a.slsname, a.custno, a.custname, a.pctpr, a.pctprname, a.besar, a.tengah, a.kecil, a.tpramount, "
					+"          CASE "
					+"             WHEN a.transtype = 'F' "
					+"                THEN b.amount "
					+"             ELSE b.amount * -1 "
					+"          END AS amount "
					+"     FROM ((SELECT a.tprkode AS kodeap, h.invno, "
					+"                   TO_CHAR (h.invdate, 'DD MON YYYY') invdate, h.slsno, "
					+"                   s.slsname, h.custno, b.custname, a.pctpr, m.pcodename AS pctprname, "
					+"                   FLOOR (a.qty / m.convunit3) AS besar, "
					+"                   FLOOR (MOD (a.qty, m.convunit3) / m.convunit2) AS tengah, "
					+"                   FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)) AS kecil, "
					+"                   CASE "
					+"                      WHEN h.transtype = 'F' "
					+"                         THEN d2.amount "
					+"                      ELSE d2.amount * -1 "
					+"                   END AS tpramount, h.transtype "
					+"              FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype "
					+"                   JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
					+"                   JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode "
					+"                   JOIN "+t.getTenantId()+".fjual_d2 d2 "
					+"                   ON a.orderno = d2.orderno "
					+"                 AND a.slsno = d2.slsno "
					+"                 AND a.transtype = d2.transtype "
					+"                 AND a.pctpr = d2.pcode " 
					+"                   JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki "
					+"                   JOIN "+t.getTenantId()+".fsls s ON a.slsno = s.slsno "
					+"             WHERE c.tipe = '"+tipeDiskon+"' "
					+"               AND c.klaim = '"+flagKlaim+"' ";		
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}
		
					
		sql += "               AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') a "
			+"          LEFT JOIN "
			+"          (SELECT   d.tprkode, h.invno, SUM (d1.amount) amount "
			+"               FROM "+t.getTenantId()+".fhasiltpr_d_hir d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype "
			+"                    JOIN "+t.getTenantId()+".fjual_d1 d1 ON d.orderno = d1.orderno AND d.slsno = d1.slsno AND d.transtype = d1.transtype AND d.pcsec = d1.pcode "
			+"                    JOIN "+t.getTenantId()+".fmaster m1 ON d1.pcode = m1.pcode "
			+"                    JOIN "+t.getTenantId()+".ftable155_hir c ON d.tprkode = c.kodeap AND d.tglawal = c.tglawal AND d.berlakuhierarki = c.berlakuhierarki AND d.detailhierarki = c.detailhierarki "
			+"              WHERE c.tipe = '"+tipeDiskon+"' "
			+"                AND c.klaim = '"+flagKlaim+"' ";
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}
			
		sql += "                AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"' "
			+"           GROUP BY d.tprkode, h.invno) b ON a.kodeap = b.tprkode AND a.invno = b.invno) "
			+" ORDER BY a.kodeap, a.invno, a.pctpr ASC ";
		Query query = entityManager.createNativeQuery(sql);
				
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			LaporanDiskonReportSummaryPromoDto data = new LaporanDiskonReportSummaryPromoDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""), Objects.toString(obj[5], ""),
					Objects.toString(obj[6], ""), Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""), Objects.toString(obj[11], ""),
					Objects.toString(obj[12], ""), Objects.toString(obj[13], ""), Objects.toString(obj[14], ""),
					Objects.toString(obj[15], ""), Objects.toString(obj[16], ""), Objects.toString(obj[17], ""),
					(BigDecimal) obj[18], (BigDecimal) obj[19], (BigDecimal) obj[20], (BigDecimal) obj[21],
					(BigDecimal) obj[22]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonReportSummaryPromoByAPDto> getReportSummaryPromoByAP(String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonReportSummaryPromoByAPDto> result = new ArrayList<>();
		
		String header = "";
		if (pilihanReport.equalsIgnoreCase("2")) {
			header = "Summary Claim MONITORING PROMOSI";
		} else {
			header = "Summary Claim LAPORAN TPR (PROMOSI)";
		}
		
		String filterAP = "";
		if (kodeAPFrom.equalsIgnoreCase("")) {
			filterAP = "ALL";
		} else {
			filterAP = kodeAPFrom + " - " + kodeAPTo;
		}
		
		String filterKlaim = "";
		if (flagKlaim.equalsIgnoreCase("1")) {
			filterKlaim = "Klaim";
		} else {
			filterKlaim = "Tidak Klaim";
		}
		
		String filterTanggal = "";
		if (invDateFrom.equalsIgnoreCase("")) {
			filterTanggal = "ALL";
		} else {
			filterTanggal = invDateFrom + " - " + invDateTo;
		}		
		
		String sql = "	SELECT   '"+header+"' || ' - ' || 	"
					+"	         (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST')	"
					+"	         || ' ' || 	"
					+"	         CASE	"
					+"	            WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL	"
					+"	               THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME')	"
					+"	            ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH')	"
					+"	         END header,	"
					+"	         (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') || ' - ' || TO_CHAR (SYSDATE, 'HH24:MI:SS') tglsistem FROM DUAL) tglsistem,	"
					+"	         (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang,	"
					+"	         (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release,	"
					+"	         'PROMOSI BARANG' tipediskon,	"
					+"	         '"+filterAP+"' filterap, 	"
					+"	         '"+filterKlaim+"' flagklaim,	"
					+"	         '"+filterTanggal+"' filtertanggal,	"
					+"	         'Rekapitulasi By AP' pilihandiskon,  	"
					+"  		 kodeap, ket, awal, akhir, slsno, slsname, pctpr, pctprname, qty, tpramount, amount, besar, tengah, kecil, tpramount2, amount2 FROM ( "		
		
		
					+"	SELECT '1' baris, a.kodeap, a.ket, a.awal, a.akhir, a.slsno, TO_CHAR (a.slsname) slsname, a.pctpr,	"
					+"	        TO_CHAR (a.pctprname) pctprname, a.qty, a.tpramount, a.amount,	"
					+"	        TO_CHAR (FLOOR (qty / convunit3)) AS besar, TO_CHAR (FLOOR ((qty - (FLOOR (qty / convunit3) * convunit3)) / convunit2)) AS tengah,	"
					+"	        TO_CHAR (qty - (FLOOR (qty / convunit3) * convunit3 + (FLOOR (  (qty - (FLOOR (qty / convunit3) * convunit3)) / convunit2)) * convunit2)) AS kecil,	"
					+" 			a.tpramount tpramount2, a.amount amount2 "
					+"	    FROM (SELECT   a.kodeap, b.keterangan ket,	"
					+"	                   TO_CHAR (b.tglawal, 'DD MON YYYY') awal,	"
					+"	                   TO_CHAR (b.tglakhir, 'DD MON YYYY') akhir, a.slsno,	"
					+"	                   a.slsname, a.pctpr, pctprname,	"
					+"	                   (SUM (besar) * m.convunit3) + (SUM (tengah) * m.convunit2) + SUM (kecil) qty,	"
					+"	                   SUM (tpramount) tpramount, SUM (amount) amount, m.convunit2, m.convunit3	"
					+"	              FROM (SELECT   a.kodeap, a.invno, a.invdate, a.slsno, a.slsname,	"
					+"	                             a.custno, a.custname, a.pctpr, a.pctprname,	"
					+"	                             a.besar, a.tengah, a.kecil, a.tpramount,	"
					+"	                             CASE	"
					+"	                                WHEN a.transtype = 'F'	"
					+"	                                   THEN b.amount	"
					+"	                                ELSE b.amount * -1	"
					+"	                             END AS amount	"
					+"	                        FROM ((SELECT a.tprkode AS kodeap, h.invno, TO_CHAR (h.invdate, 'DD MON YYYY') invdate,	"
					+"	                                      h.slsno, s.slsname, h.custno, b.custname, a.pctpr, m.pcodename AS pctprname,	"
					+"	                                      FLOOR (a.qty / m.convunit3) AS besar,	"
					+"	                                      FLOOR (MOD (a.qty, m.convunit3) / m.convunit2) AS tengah,	"
					+"	                                      FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)) AS kecil,	"
					+"	                                      CASE	"
					+"	                                         WHEN h.transtype = 'F'	"
					+"	                                            THEN ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3)	"
					+"	                                                  + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2)	"
					+"	                                                  + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1))	"
					+"	                                         ELSE   ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3)	"
					+"	                                                 + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2)	"
					+"	                                                 + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) * -1	"
					+"	                                      END AS tpramount, h.transtype	"
					+"	                                 FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype	"
					+"	                                      JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno	"
					+"	                                      JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode	"
					+"	                                      JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode	"
					+"	                                      JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap	"
					+"	                                      JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno	"
					+"	                                WHERE c.tipe = '"+tipeDiskon+"'	"
					+"	                                  AND c.klaim = '"+flagKlaim+"'	";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}		
					
		sql += "	                                  AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') a "
			+"	                             LEFT JOIN	"
			+"	                             (SELECT   d.tprkode, h.invno, SUM (d1.amount) amount "
			+"	                                  FROM "+t.getTenantId()+".fhasiltpr_d d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype	"
			+"	                                       JOIN "+t.getTenantId()+".fjual_d1 d1 ON d.orderno = d1.orderno AND d.slsno = d1.slsno AND d.transtype = d1.transtype AND d.pcsec = d1.pcode	"
			+"	                                       JOIN "+t.getTenantId()+".fmaster m1 ON d1.pcode = m1.pcode	"
			+"	                                       JOIN "+t.getTenantId()+".ftable155 c ON d.tprkode = c.kodeap	"
			+"	                                 WHERE c.tipe = '"+tipeDiskon+"'	"
			+"	                                   AND c.klaim = '"+flagKlaim+"'	";
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}
		
			
		sql += "	                                   AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"'	"
			+"	                              GROUP BY d.tprkode, h.invno) b	"
			+"	                             ON a.kodeap = b.tprkode AND a.invno = b.invno)) a	"
			+"	                   LEFT JOIN "+t.getTenantId()+".ftable155 b ON a.kodeap = b.kodeap	"
			+"	                   LEFT JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode	"
			+"	             WHERE a.kodeap IS NOT NULL	"
			+"	          GROUP BY a.kodeap, b.keterangan, b.tglawal, b.tglakhir, slsno, slsname, a.pctpr, a.pctprname, convunit2, convunit3) a	"
			
			+"  UNION ALL "
	        +"  SELECT '2' baris, tprkode, '' ket, '' awal, '' akhir, '' slsno, "
	        +"         'Rekapitulasi ' || tprkode AS slsname, '' pctpr, "
	        +"         TO_CHAR (pctprname) pctprname, 0 qty, "
	        +"         tpramount, CASE "
	        +"            WHEN urut > 1 "
	        +"               THEN 0 "
	        +"            ELSE amount "
	        +"         END amount, TO_CHAR (besar) besar, TO_CHAR (tengah) tengah, "
	        +"         TO_CHAR (kecil) kecil, 0 tpramount2, 0 amount2 "
	        +"    FROM (SELECT ROW_NUMBER () OVER (PARTITION BY tprkode ORDER BY pctpr) AS urut, "
	        +"                 tprkode, pctpr, pctprname, besar, tengah, kecil, tpramount, amount "
	        +"            FROM (SELECT   a.tprkode, a.pctpr, "
	        +"                           CASE "
	        +"                              WHEN a.pctpr IS NOT NULL "
	        +"                                 THEN TO_CHAR(a.pctpr || ' -  ' || a.pcodename) "
	        +"                              ELSE '' "
	        +"                           END AS pctprname, "
	        +"                           a.besar, a.tengah, a.kecil, "
	        +"                           a.tpramountnew AS tpramount, b.amount "
	        +"                      FROM (SELECT tprkode, pctpr, pcodename, "
	        +"                                   FLOOR (qty / convunit3) AS besar, "
	        +"                                   FLOOR (MOD (qty, convunit3) / convunit2) AS tengah, "
	        +"                                   FLOOR (MOD (MOD (qty, convunit3), convunit2)) AS kecil, "
	        +"                                   amount AS tpramount, "
	        +"                                   (NVL (FLOOR (qty / convunit3), 0) * sellprice3) "
	        +"                                   + (NVL (FLOOR (MOD (qty, convunit3) / convunit2), 0) * sellprice2) "
	        +"                                   + (NVL (FLOOR (MOD (MOD (qty, convunit3), convunit2)), 0) * sellprice1) AS tpramountnew "
	        +"                              FROM (SELECT   tprkode, pctpr, pcodename, convunit2, convunit3, sellprice1, "
	        +"                                             sellprice2, sellprice3, SUM (qty) qty, SUM (amount) amount "
	        +"                                        FROM (SELECT a.tprkode, a.pctpr, m.pcodename, m.convunit2, m.convunit3, m.sellprice1, m.sellprice2, m.sellprice3, "
	        +"                                                     CASE "
	        +"                                                        WHEN a.transtype = 'F' "
	        +"                                                           THEN a.qty "
	        +"                                                        ELSE a.qty * -1 "
	        +"                                                     END qty, "
	        +"                                                     CASE "
	        +"                                                        WHEN a.transtype = 'F' "
	        +"                                                           THEN d2.amount "
	        +"                                                        ELSE d2.amount * -1 "
	        +"                                                     END amount "
	        +"                                                FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype "
	        +"                                                     JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode "
	        +"                                                     JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode "
	        +"                                                     JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap "
	        +"                                                     JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
	        +"                                               WHERE c.tipe = '"+tipeDiskon+"' "
	        +"                                                 AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND a.tprkode BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND a.tprkode = '"+kodeAPFrom+"' ";
		}	
		
	        
	    sql += "                                                 AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
	        +"                                    GROUP BY tprkode, pctpr, pcodename, convunit2, convunit3, sellprice1, sellprice2, sellprice3)) a "
	        +"                           LEFT JOIN "
	        +"                           (SELECT tprkode, amount "
	        +"                              FROM (SELECT tprkode, SUM (amount) amount "
	        +"                                        FROM (SELECT tprkode, pcode, pcodename, SUM (amount) amount "
	        +"                                                  FROM (SELECT a.tprkode, d1.pcode, m.pcodename, "
	        +"                                                               CASE "
	        +"                                                                  WHEN d1.transtype = 'F' "
	        +"                                                                     THEN d1.amount "
	        +"                                                                  ELSE d1.amount * -1 "
	        +"                                                               END amount "
	        +"                                                          FROM "+t.getTenantId()+".fhasiltpr_d a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype "
	        +"                                                               JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.orderno = d1.orderno AND a.slsno = d1.slsno AND a.transtype = d1.transtype AND a.pcsec = d1.pcode "
	        +"                                                               JOIN "+t.getTenantId()+".fmaster m ON d1.pcode = m.pcode "
	        +"                                                               JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap "
	        +"                                                         WHERE c.tipe = '"+tipeDiskon+"' "
	        +"                                                           AND c.klaim = '"+flagKlaim+"' ";
	    
	    if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}	    
	        
	    sql += "                                                           AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
	        +"                                              GROUP BY tprkode, pcode, pcodename) "
	        +"                                    GROUP BY tprkode)) b ON a.tprkode = b.tprkode)) "
	        +"  UNION ALL "
	        +"  SELECT '3' baris, a.tprkode, '' ket, '' awal, '' akhir, '' slsno, 'Total ' || a.tprkode AS slsname, '' pctpr, '' pctprname, "
	        +"         0 qty, a.tpramountnew AS tpramount, b.amount, '' besar, '' tengah, '' kecil, 0 tpramount2, 0 amount2 "
	        +"    FROM (SELECT tprkode, (NVL (FLOOR (qty / convunit3), 0) * sellprice3) "
	        +"                 + (NVL (FLOOR (MOD (qty, convunit3) / convunit2),0) * sellprice2) "
	        +"                 + (NVL (FLOOR (MOD (MOD (qty, convunit3), convunit2)), 0) * sellprice1) AS tpramountnew "
	        +"            FROM (SELECT   tprkode, pctpr, pcodename, convunit2, convunit3, sellprice1, sellprice2, "
	        +"                           sellprice3, SUM (qty) qty, SUM (amount) amount "
	        +"                      FROM (SELECT a.tprkode, a.pctpr, m.pcodename, m.convunit2, m.convunit3, m.sellprice1, m.sellprice2, m.sellprice3, "
	        +"                                   CASE "
	        +"                                      WHEN a.transtype = 'F' "
	        +"                                         THEN a.qty "
	        +"                                      ELSE a.qty * -1 "
	        +"                                   END qty, "
	        +"                                   CASE "
	        +"                                      WHEN a.transtype = 'F' "
	        +"                                         THEN d2.amount "
	        +"                                      ELSE d2.amount * -1 "
	        +"                                   END amount "
	        +"                              FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype "
	        +"                                   JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode "
	        +"                                   JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode "
	        +"                                   JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap "
	        +"                                   JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
	        +"                             WHERE c.tipe = '"+tipeDiskon+"' "
	        +"                               AND c.klaim = '"+flagKlaim+"' ";
	    
	    if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND a.tprkode BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND a.tprkode = '"+kodeAPFrom+"' ";
		}	
	    
	        
	    sql += "                               AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
	        +"                  GROUP BY tprkode, pctpr, pcodename, convunit2, convunit3, sellprice1, sellprice2, sellprice3)) a "
	        +"         LEFT JOIN "
	        +"         (SELECT tprkode, amount "
	        +"            FROM (SELECT   tprkode, SUM (amount) amount "
	        +"                      FROM (SELECT   tprkode, pcode, pcodename, SUM (amount) amount "
	        +"                                FROM (SELECT a.tprkode, d1.pcode, m.pcodename, "
	        +"                                             CASE "
	        +"                                                WHEN d1.transtype = 'F' "
	        +"                                                   THEN d1.amount "
	        +"                                                ELSE d1.amount * -1 "
	        +"                                             END amount "
	        +"                                        FROM "+t.getTenantId()+".fhasiltpr_d a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype "
	        +"                                             JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.orderno = d1.orderno AND a.slsno = d1.slsno AND a.transtype = d1.transtype AND a.pcsec = d1.pcode "
	        +"                                             JOIN "+t.getTenantId()+".fmaster m ON d1.pcode = m.pcode "
	        +"                                             JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap "
	        +"                                       WHERE c.tipe = '"+tipeDiskon+"' "
	        +"                                         AND c.klaim = '"+flagKlaim+"' ";
	    
	    if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}
	    
	        
	    sql += "                                         AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
	        +"                            GROUP BY tprkode, pcode, pcodename) "
	        +"                  GROUP BY tprkode)) b ON a.tprkode = b.tprkode) "
	        +" ORDER BY kodeap, baris, pctpr ASC  ";
			
			
//			+"	ORDER BY kodeap, a.slsno, a.pctpr	";
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			LaporanDiskonReportSummaryPromoByAPDto data = new LaporanDiskonReportSummaryPromoByAPDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""), Objects.toString(obj[5], ""),
					Objects.toString(obj[6], ""), Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""), Objects.toString(obj[11], ""),
					Objects.toString(obj[12], ""), Objects.toString(obj[13], ""), Objects.toString(obj[14], ""),
					Objects.toString(obj[15], ""), Objects.toString(obj[16], ""), (BigDecimal) obj[17],
					(BigDecimal) obj[18], (BigDecimal) obj[19], Objects.toString(obj[20], ""),
					Objects.toString(obj[21], ""), Objects.toString(obj[22], ""), (BigDecimal) obj[23],
					(BigDecimal) obj[24]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonReportSummaryPromoByAPDto> getReportSummaryPromoByAPHierarki(String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonReportSummaryPromoByAPDto> result = new ArrayList<>();
		
		String header = "";
		if (pilihanReport.equalsIgnoreCase("2")) {
			header = "Summary Claim MONITORING PROMOSI";
		} else {
			header = "Summary Claim LAPORAN TPR (PROMOSI)";
		}
		
		String filterAP = "";
		if (kodeAPFrom.equalsIgnoreCase("")) {
			filterAP = "ALL";
		} else {
			filterAP = kodeAPFrom + " - " + kodeAPTo;
		}
		
		String filterKlaim = "";
		if (flagKlaim.equalsIgnoreCase("1")) {
			filterKlaim = "Klaim";
		} else {
			filterKlaim = "Tidak Klaim";
		}
		
		String filterTanggal = "";
		if (invDateFrom.equalsIgnoreCase("")) {
			filterTanggal = "ALL";
		} else {
			filterTanggal = invDateFrom + " - " + invDateTo;
		}		
		
		String sql = "	SELECT   '"+header+"' || ' - ' || 	"
					+"	         (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST')	"
					+"	         || ' ' || 	"
					+"	         CASE	"
					+"	            WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL	"
					+"	               THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME')	"
					+"	            ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH')	"
					+"	         END header,	"
					+"	         (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') || ' - ' || TO_CHAR (SYSDATE, 'HH24:MI:SS') tglsistem FROM DUAL) tglsistem,	"
					+"	         (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang,	"
					+"	         (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release,	"
					+"	         'PROMOSI BARANG' tipediskon,	"
					+"	         '"+filterAP+"' filterap, 	"
					+"	         '"+filterKlaim+"' flagklaim,	"
					+"	         '"+filterTanggal+"' filtertanggal,	"
					+"	         'Rekapitulasi By AP' pilihandiskon,  	"
					+" 			 kodeap, ket, awal, akhir, slsno, slsname, pctpr, pctprname, qty, tpramount, " 
					+" 			 amount, besar, tengah, kecil, tpramount2, amount2	FROM ( "
					+"	  SELECT '1' baris, a.kodeap, a.ket, a.awal, a.akhir, a.slsno, TO_CHAR (a.slsname) slsname, a.pctpr,	"
					+"	         TO_CHAR (a.pctprname) pctprname, a.qty, a.tpramount, a.amount,	"
					+"	         TO_CHAR (FLOOR (qty / convunit3)) AS besar, TO_CHAR (FLOOR ((qty - (FLOOR (qty / convunit3) * convunit3)) / convunit2)) AS tengah,	"
					+"	         TO_CHAR (qty - (FLOOR (qty / convunit3) * convunit3 + (FLOOR (  (qty - (FLOOR (qty / convunit3) * convunit3)) / convunit2)) * convunit2)) AS kecil,	"
					+" 			 a.tpramount tpramount2, a.amount amount2 "
					+"	    FROM (SELECT   a.kodeap, b.keterangan ket,	"
					+"	                   TO_CHAR (b.tglawal, 'DD MON YYYY') awal,	"
					+"	                   TO_CHAR (b.tglakhir, 'DD MON YYYY') akhir, a.slsno,	"
					+"	                   a.slsname, a.pctpr, pctprname,	"
					+"	                   (SUM (besar) * m.convunit3) + (SUM (tengah) * m.convunit2) + SUM (kecil) qty,	"
					+"	                   SUM (tpramount) tpramount, SUM (amount) amount, m.convunit2, m.convunit3	"
					+"	              FROM (SELECT   a.kodeap, a.invno, a.invdate, a.slsno, a.slsname,	"
					+"	                             a.custno, a.custname, a.pctpr, a.pctprname,	"
					+"	                             a.besar, a.tengah, a.kecil, a.tpramount,	"
					+"	                             CASE	"
					+"	                                WHEN a.transtype = 'F'	"
					+"	                                   THEN b.amount	"
					+"	                                ELSE b.amount * -1	"
					+"	                             END AS amount	"
					+"	                        FROM ((SELECT a.tprkode AS kodeap, h.invno, TO_CHAR (h.invdate, 'DD MON YYYY') invdate,	"
					+"	                                      h.slsno, s.slsname, h.custno, b.custname, a.pctpr, m.pcodename AS pctprname,	"
					+"	                                      FLOOR (a.qty / m.convunit3) AS besar,	"
					+"	                                      FLOOR (MOD (a.qty, m.convunit3) / m.convunit2) AS tengah,	"
					+"	                                      FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)) AS kecil,	"
					+"	                                      CASE "
					+"	                                         WHEN h.transtype = 'F' "
					+"	                                            THEN d2.amount "
					+"	                                         ELSE d2.amount * -1 "
					+"	                                      END AS tpramount, h.transtype	"
					+"	                                 FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype	"
					+"	                                      JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno	"
					+"	                                      JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode	"
					+"	                                      JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode	"
					+"	                                      JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki	"
					+"	                                      JOIN "+t.getTenantId()+".fsls s ON a.slsno = s.slsno	"
					+"	                                WHERE c.tipe = '"+tipeDiskon+"'	"
					+"	                                  AND c.klaim = '"+flagKlaim+"'	";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}		
					
		sql += "	                                  AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') a "
			+"	                             LEFT JOIN	"
			+"	                             (SELECT   d.tprkode, h.invno, SUM (d1.amount) amount "
			+"	                                  FROM "+t.getTenantId()+".fhasiltpr_d_hir d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype	"
			+"	                                       JOIN "+t.getTenantId()+".fjual_d1 d1 ON d.orderno = d1.orderno AND d.slsno = d1.slsno AND d.transtype = d1.transtype AND d.pcsec = d1.pcode	"
			+"	                                       JOIN "+t.getTenantId()+".fmaster m1 ON d1.pcode = m1.pcode	"
			+"	                                       JOIN "+t.getTenantId()+".ftable155_hir c ON d.tprkode = c.kodeap AND d.tglawal = c.tglawal AND d.berlakuhierarki = c.berlakuhierarki AND d.detailhierarki = c.detailhierarki	"
			+"	                                 WHERE c.tipe = '"+tipeDiskon+"'	"
			+"	                                   AND c.klaim = '"+flagKlaim+"'	";
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}
		
			
		sql += "	                                   AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"'	"
			+"	                              GROUP BY d.tprkode, h.invno) b	"
			+"	                             ON a.kodeap = b.tprkode AND a.invno = b.invno)) a	"
			+"	                   LEFT JOIN "+t.getTenantId()+".ftable155_hir b ON a.kodeap = b.kodeap	"
			+"	                   LEFT JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode	"
			+"	             WHERE a.kodeap IS NOT NULL	"
			+"	          GROUP BY a.kodeap, b.keterangan, b.tglawal, b.tglakhir, slsno, slsname, a.pctpr, a.pctprname, convunit2, convunit3) a	"
			
			+"	          UNION ALL	"
			+"	          SELECT '2' baris, tprkode, '' ket, '' awal, '' akhir, '' slsno,	"
			+"	                 'Rekapitulasi ' || tprkode AS slsname, '' pctpr,	"
			+"	                 TO_CHAR (pctprname) pctprname, 0 qty, tpramount, 	"
			+"	                 CASE	"
			+"	                    WHEN urut > 1	"
			+"	                       THEN 0	"
			+"	                    ELSE amount	"
			+"	                 END amount, TO_CHAR (besar) besar, TO_CHAR (tengah) tengah,	"
			+"	                 TO_CHAR (kecil) kecil, 0 tpramount2, 0 amount2	"
			+"	            FROM (SELECT ROW_NUMBER () OVER (PARTITION BY tprkode ORDER BY pctpr) AS urut,	"
			+"	                         tprkode, pctpr, pctprname, besar, tengah, kecil, tpramount, amount	"
			+"	                    FROM (SELECT a.tprkode, a.pctpr,	"
			+"	                                 CASE	"
			+"	                                    WHEN a.pctpr IS NOT NULL	"
			+"	                                       THEN TO_CHAR (a.pctpr || ' -  ' || a.pcodename)	"
			+"	                                    ELSE ''	"
			+"	                                 END AS pctprname,	"
			+"	                                 a.besar, a.tengah, a.kecil, a.tpramountnew AS tpramount, b.amount	"
			+"	                            FROM (SELECT tprkode, pctpr, pcodename, FLOOR (qty / convunit3) AS besar,	"
			+"	                                         FLOOR (MOD (qty, convunit3) / convunit2) AS tengah,	"
			+"	                                         FLOOR (MOD (MOD (qty, convunit3), convunit2)) AS kecil,	"
			+"	                                         amount AS tpramount,	"
			+"	                                         (NVL (FLOOR (qty / convunit3), 0) * sellprice3)	"
			+"	                                         + (NVL (FLOOR (MOD (qty, convunit3) / convunit2), 0) * sellprice2)	"
			+"	                                         + (NVL (FLOOR (MOD (MOD (qty, convunit3), convunit2)), 0) * sellprice1) AS tpramountnew	"
			+"	                                    FROM (SELECT   tprkode, pctpr, pcodename, convunit2, convunit3,	"
			+"	                                                   sellprice1, sellprice2, sellprice3, SUM (qty) qty, SUM (amount) amount	"
			+"	                                              FROM (SELECT a.tprkode, a.pctpr, m.pcodename, m.convunit2, m.convunit3, m.sellprice1, m.sellprice2, m.sellprice3,	"
			+"	                                                           CASE	"
			+"	                                                              WHEN a.transtype = 'F'	"
			+"	                                                                 THEN a.qty	"
			+"	                                                              ELSE a.qty * -1	"
			+"	                                                           END qty,	"
			+"	                                                           CASE	"
			+"	                                                              WHEN a.transtype = 'F'	"
			+"	                                                                 THEN d2.amount	"
			+"	                                                              ELSE d2.amount * -1	"
			+"	                                                           END amount	"
			+"	                                                      FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype	"
			+"	                                                           JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode	"
			+"	                                                           JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode	"
			+"	                                                           JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki	"
			+"	                                                           JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno	"
			+"	                                                     WHERE c.tipe = '"+tipeDiskon+"'	"
			+"	                                                       AND c.klaim = '"+flagKlaim+"'	";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND a.tprkode BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND a.tprkode = '"+kodeAPFrom+"' ";
		}
		
		
		sql += "	                                                       AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"')	"
			+"	                                          GROUP BY tprkode, pctpr, pcodename, convunit2, convunit3, sellprice1, sellprice2, sellprice3)) a	"
			+"	                                 LEFT JOIN	"
			+"	                                 (SELECT tprkode, amount	"
			+"	                                    FROM (SELECT tprkode, SUM (amount) amount	"
			+"	                                              FROM (SELECT tprkode, pcode, pcodename, SUM (amount) amount	"
			+"	                                                        FROM (SELECT a.tprkode, d1.pcode, m.pcodename,	"
			+"	                                                                     CASE	"
			+"	                                                                        WHEN d1.transtype = 'F'	"
			+"	                                                                           THEN d1.amount	"
			+"	                                                                        ELSE d1.amount * -1	"
			+"	                                                                     END amount	"
			+"	                                                                FROM "+t.getTenantId()+".fhasiltpr_d_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype	"
			+"	                                                                     JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.orderno = d1.orderno AND a.slsno = d1.slsno AND a.transtype = d1.transtype AND a.pcsec = d1.pcode	"
			+"	                                                                     JOIN "+t.getTenantId()+".fmaster m ON d1.pcode = m.pcode	"
			+"	                                                                     JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki	"
			+"	                                                               WHERE c.tipe = '"+tipeDiskon+"'	"
			+"	                                                                 AND c.klaim = '"+flagKlaim+"'	";
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}		
			
		sql += "	                                                                 AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"')	"
			+"	                                                    GROUP BY tprkode, pcode, pcodename)	"
			+"	                                          GROUP BY tprkode)) b	"
			+"	                                 ON a.tprkode = b.tprkode))	"
			+"	          UNION ALL	"
			+"	          SELECT '3' baris, a.tprkode, '' ket, '' awal, '' akhir, '' slsno, 'Total ' || a.tprkode AS slsname, '' pctpr, '' pctprname,	"
			+"	                 0 qty, a.tpramountnew AS tpramount, b.amount, '' besar, '' tengah, '' kecil, 0 tpramount2, 0 amount2	"
			+"	            FROM (SELECT tprkode, (NVL (FLOOR (qty / convunit3), 0) * sellprice3)	"
			+"	                         + (NVL (FLOOR (MOD (qty, convunit3) / convunit2), 0) * sellprice2)	"
			+"	                         + (NVL (FLOOR (MOD (MOD (qty, convunit3), convunit2)), 0) * sellprice1) AS tpramountnew	"
			+"	                    FROM (SELECT   tprkode, pctpr, pcodename, convunit2, convunit3, sellprice1, sellprice2,	"
			+"	                                   sellprice3, SUM (qty) qty, SUM (amount) amount	"
			+"	                              FROM (SELECT a.tprkode, a.pctpr, m.pcodename, m.convunit2, m.convunit3, m.sellprice1, m.sellprice2, m.sellprice3,	"
			+"	                                           CASE	"
			+"	                                              WHEN a.transtype = 'F'	"
			+"	                                                 THEN a.qty	"
			+"	                                              ELSE a.qty * -1	"
			+"	                                           END qty,	"
			+"	                                           CASE	"
			+"	                                              WHEN a.transtype = 'F'	"
			+"	                                                 THEN d2.amount	"
			+"	                                              ELSE d2.amount * -1	"
			+"	                                           END amount	"
			+"	                                      FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype	"
			+"	                                           JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode	"
			+"	                                           JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode	"
			+"	                                           JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki	"
			+"	                                           JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno	"
			+"	                                     WHERE c.tipe = '"+tipeDiskon+"'	"
			+"	                                       AND c.klaim = '"+flagKlaim+"'	";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND a.tprkode BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND a.tprkode = '"+kodeAPFrom+"' ";
		}	
			
		sql += "	                                       AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"')	"
			+"	                          GROUP BY tprkode, pctpr, pcodename, convunit2, convunit3, sellprice1, sellprice2, sellprice3)) a	"
			+"	                 LEFT JOIN	"
			+"	                 (SELECT tprkode, amount	"
			+"	                    FROM (SELECT tprkode, SUM (amount) amount	"
			+"	                              FROM (SELECT tprkode, pcode, pcodename, SUM (amount) amount	"
			+"	                                        FROM (SELECT a.tprkode, d1.pcode, m.pcodename,	"
			+"	                                                     CASE	"
			+"	                                                        WHEN d1.transtype = 'F'	"
			+"	                                                           THEN d1.amount	"
			+"	                                                        ELSE d1.amount * -1	"
			+"	                                                     END amount	"
			+"	                                                FROM "+t.getTenantId()+".fhasiltpr_d_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype	"
			+"	                                                     JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.orderno = d1.orderno AND a.slsno = d1.slsno AND a.transtype = d1.transtype AND a.pcsec = d1.pcode	"
			+"	                                                     JOIN "+t.getTenantId()+".fmaster m ON d1.pcode = m.pcode	"
			+"	                                                     JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki	"
			+"	                                               WHERE c.tipe = '"+tipeDiskon+"'	"
			+"	                                                 AND c.klaim = '"+flagKlaim+"'	";
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}
		
		sql += "	                                                 AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"')	"
			+"	                                    GROUP BY tprkode, pcode, pcodename)	"
			+"	                          GROUP BY tprkode)) b ON a.tprkode = b.tprkode)	"
			+"	ORDER BY kodeap, baris, pctpr ASC	";

			
			
			
//			+"	ORDER BY kodeap, a.slsno, a.pctpr	";
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			LaporanDiskonReportSummaryPromoByAPDto data = new LaporanDiskonReportSummaryPromoByAPDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""), Objects.toString(obj[5], ""),
					Objects.toString(obj[6], ""), Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""), Objects.toString(obj[11], ""),
					Objects.toString(obj[12], ""), Objects.toString(obj[13], ""), Objects.toString(obj[14], ""),
					Objects.toString(obj[15], ""), Objects.toString(obj[16], ""), (BigDecimal) obj[17],
					(BigDecimal) obj[18], (BigDecimal) obj[19], Objects.toString(obj[20], ""),
					Objects.toString(obj[21], ""), Objects.toString(obj[22], ""), (BigDecimal) obj[23],
					(BigDecimal) obj[24]);
			
			result.add(data);
		}
		
		return result;
	}
	
	@Override
	public List<Object[]> getListAPPromo(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom,
			String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo,
			String invDateFrom, String invDateTo) {
		// TODO Auto-generated method stub
		String sql = " SELECT DISTINCT a.tprkode, a.tprkode kodeap "
					+"            FROM "+t.getTenantId()+".fjual_h h ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".fhasiltpr_h a ON h.orderno = a.orderno AND h.slsno = a.slsno AND h.transtype = a.transtype "
				 + " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".fhasiltpr_h_hir a ON h.orderno = a.orderno AND h.slsno = a.slsno AND h.transtype = a.transtype "
				 + " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}		
					
		sql += "           WHERE h.invno IS NOT NULL "
			+"             AND c.tipe = '"+tipeDiskon+"' "
			+"             AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}			
		
		sql += "             AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"' "
			  +"        ORDER BY a.tprkode ASC ";
		Query query = entityManager.createNativeQuery(sql);		
		
		List<Object[]> result = query.getResultList(); 
	
		return result;
	}

	@Override
	public List<Object[]> getListAPInvnoPromo(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom,
			String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String kodeAP,
			String invDateFrom, String invDateTo) {
		// TODO Auto-generated method stub
		String sql = " SELECT DISTINCT a.tprkode, h.invno "
					+"            FROM "+t.getTenantId()+".fjual_h h ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".fhasiltpr_h a ON h.orderno = a.orderno AND h.slsno = a.slsno AND h.transtype = a.transtype "
				 + " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".fhasiltpr_h_hir a ON h.orderno = a.orderno AND h.slsno = a.slsno AND h.transtype = a.transtype "
				 + " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}			
		
		sql += "           WHERE h.invno IS NOT NULL "
			+"             AND c.tipe = '"+tipeDiskon+"' "
			+"             AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		}
		
		sql += "             AND c.kodeap = '"+kodeAP+"' "
			  +"             AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"' "
			  +"        ORDER BY a.tprkode, h.invno ASC ";
		Query query = entityManager.createNativeQuery(sql);		
		
		List<Object[]> result = query.getResultList(); 

		return result;
	}

	@Override
	public List<Object[]> getListDetailPromoKiri(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk,
			String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String invno,
			String kodeAP) {
		// TODO Auto-generated method stub
		String sql = " SELECT ROWNUM AS ID, a.tprkode AS kodeap, h.invno, h.custno, b.custname, "
					+"        a.pctpr, m.pcodename AS pctprname, FLOOR (a.qty / m.convunit3) AS besar, "
					+"        TO_CHAR (h.invdate, 'DD MON YYYY') invdate, h.slsno, s.slsname, "
					+"        FLOOR (MOD (a.qty, m.convunit3) / m.convunit2) AS tengah, "
					+"        FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)) AS kecil, "
					+"        CASE "
					+"           WHEN h.transtype = 'F' "
					+"              THEN ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                    + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                    + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) "
					+"           ELSE   ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                   + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                   + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) * -1 "
					+"        END AS tpramount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		}
					
		sql += "        JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
			+"        JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode "
			+"        JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}	
			
		sql += "        JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"  WHERE c.tipe = '"+tipeDiskon+"' "
			+"    AND c.klaim = '"+flagKlaim+"' "   
			+"    AND h.invno = '"+invno+"' "
			+"    AND a.tprkode = '"+kodeAP+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		}	
		
		Query query = entityManager.createNativeQuery(sql);		

		List<Object[]> result = query.getResultList(); 

		return result;
	}

	@Override
	public List<Object[]> getListDetailPromoKanan(String flagHierarki, String tipeDiskon, String flagKlaim,
			String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo,
			String invno, String kodeAP) {
		// TODO Auto-generated method stub
		String sql = " SELECT ROWNUM AS ID, d1.pcode, m1.pcodename, "
					+"        CASE "
					+"           WHEN h.transtype = 'F' "
					+"              THEN d1.qty / m1.convunit3 "
					+"           ELSE d1.qty / m1.convunit3 * -1 "
					+"        END AS qty, "
					+"        CASE "
					+"           WHEN h.transtype = 'F' "
					+"              THEN d1.amount "
					+"           ELSE d1.amount * -1 "
					+"        END AS amount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d_hir d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype ";
		}		
					
		sql += "        JOIN "+t.getTenantId()+".fjual_d1 d1 ON d.orderno = d1.orderno AND d.slsno = d1.slsno AND d.transtype = d1.transtype AND d.pcsec = d1.pcode "
			+"        JOIN "+t.getTenantId()+".fmaster m1 ON d1.pcode = m1.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON d.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON d.tprkode = c.kodeap AND d.tglawal = c.tglawal AND d.berlakuhierarki = c.berlakuhierarki AND d.detailhierarki = c.detailhierarki ";
		}		
								
		sql += "  WHERE c.tipe = '"+tipeDiskon+"' "
			+"    AND c.klaim = '"+flagKlaim+"' "   
			+"    AND h.invno = '"+invno+"' "
			+"    AND c.kodeap = '"+kodeAP+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		}	
		
		Query query = entityManager.createNativeQuery(sql);		
	
		List<Object[]> result = query.getResultList(); 
	
		return result;
	}

	@Override
	public List<Object[]> getListDetailPromo(String flagHierarki, String tipeDiskon, String flagKlaim,
			String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo,
			String invno, String kodeAP, String join) {
		// TODO Auto-generated method stub
		String sql = " SELECT a.kodeap, a.invno, a.invdate, a.slsno, a.slsname, a.custno, a.custname, "
					+"        a.pctpr, a.pctprname, a.besar, a.tengah, a.kecil, a.tpramount, b.pcode, "
					+"        b.pcodename, ROUND (b.qty, 2) qty, b.amount "
					+"   FROM ((SELECT ROWNUM AS ID, a.tprkode AS kodeap, h.invno, h.custno, "
					+"                 b.custname, a.pctpr, m.pcodename AS pctprname, "                
					+"                 TO_CHAR (h.invdate, 'DD MON YYYY') invdate, h.slsno, s.slsname, "
					+"                 FLOOR (a.qty / m.convunit3) AS besar, "
					+"                 FLOOR (MOD (a.qty, m.convunit3) / m.convunit2) AS tengah, "
					+"                 FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)) AS kecil, "
					+"                 CASE "
					+"                    WHEN h.transtype = 'F' "
					+"                       THEN ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                             + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                             + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) "
					+"                    ELSE   ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                            + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                            + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) * -1 "
					+"                 END AS tpramount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		}
		
					
		sql += "                 JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
			+"                 JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode "
			+"                 JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}
		
		sql += "                 JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"           WHERE c.tipe = '"+tipeDiskon+"' "
			+"             AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		}			
					
		sql += "           AND h.invno = '"+invno+"' "
			+"             AND a.tprkode = '"+kodeAP+"') a "
			+"        "+join+"  "
			+"        (SELECT ROWNUM AS ID, d1.pcode, m1.pcodename, "
			+"                CASE "
			+"                   WHEN h.transtype = 'F' "
			+"                      THEN d1.qty / m1.convunit3 "
			+"                   ELSE d1.qty / m1.convunit3 * -1  "
			+"                END AS qty, "
			+"                CASE "
			+"                   WHEN h.transtype = 'F' "
			+"                      THEN d1.amount "
			+"                   ELSE d1.amount * -1 "
			+"                END AS amount ";		
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d_hir d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype ";
		}		
			
		sql += "                JOIN "+t.getTenantId()+".fjual_d1 d1 ON d.orderno = d1.orderno AND d.slsno = d1.slsno AND d.transtype = d1.transtype AND d.pcsec = d1.pcode "
			+"                JOIN "+t.getTenantId()+".fmaster m1 ON d1.pcode = m1.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON d.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON d.tprkode = c.kodeap AND d.tglawal = c.tglawal AND d.berlakuhierarki = c.berlakuhierarki AND d.detailhierarki = c.detailhierarki ";
		}		
			
		sql += "          WHERE c.tipe = '"+tipeDiskon+"' "
			+"            AND c.klaim = '"+flagKlaim+"' ";
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		}	
		
		sql	+= "          AND h.invno = '"+invno+"' "
			+"            AND d.tprkode = '"+kodeAP+"') b "
			+"        ON a.ID = b.ID) ";
		Query query = entityManager.createNativeQuery(sql);		
		
		List<Object[]> result = query.getResultList(); 
	
		return result;
	}

	@Override
	public List<Object[]> getTotalPromoKiri(String flagHierarki, String tipeDiskon, String flagKlaim,
			String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo,
			String kodeAP, String invDateFrom, String invDateTo) {
		// TODO Auto-generated method stub
		String sql = " SELECT pctpr, pcodename, FLOOR (qty / convunit3) AS besar, "
					+"        FLOOR (MOD (qty, convunit3) / convunit2) AS tengah, "
					+"        FLOOR (MOD (MOD (qty, convunit3), convunit2)) AS kecil, "
					+"        amount AS tpramount "
					+"   FROM (SELECT   pctpr, pcodename, convunit2, convunit3, SUM (qty) qty, SUM (amount) amount "
					+"             FROM (SELECT a.pctpr, m.pcodename, m.convunit2, m.convunit3, "
					+"                          CASE "
					+"                             WHEN a.transtype = 'F' "
					+"                                THEN a.qty "
					+"                             ELSE a.qty * -1 "
					+"                          END qty, "
					+"                          CASE "
					+"                             WHEN h.transtype = 'F' "
					+"                                THEN ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                                      + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                                      + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) "
					+"                             ELSE   ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                                     + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                                     + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) * -1 "
					+"                          END AS amount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		}		
					
		sql += "                          JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode "
			+"                          JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}		
			
		sql += "                          JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"                    WHERE c.tipe = '"+tipeDiskon+"' "
			+"                      AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		}		
		
		sql += "                      AND a.tprkode = '"+kodeAP+"' "
			+"                      AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
			+"         GROUP BY pctpr, pcodename, convunit2, convunit3) ";
		Query query = entityManager.createNativeQuery(sql);		
		
		List<Object[]> result = query.getResultList(); 
	
		return result;
	}

	@Override
	public List<Object[]> getTotalPromoKanan(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom,
			String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String kodeAP, String invDateFrom,
			String invDateTo) {
		// TODO Auto-generated method stub
		String sql = " SELECT pcode, pcodename, ROUND (qty / convunit3, 2) qty, amount "
					+"  	  FROM (SELECT   pcode, pcodename, convunit3, SUM (qty) qty, "
					+"  	                 SUM (amount) amount "
					+"  	            FROM (SELECT d1.pcode, m.pcodename, m.convunit3, "
					+"  	                         CASE "
					+"  	                            WHEN d1.transtype = 'F' "
					+"  	                               THEN d1.qty "
					+"  	                            ELSE d1.qty * -1 "
					+"  	                         END qty, "
					+"  	                         CASE "
					+"  	                            WHEN d1.transtype = 'F' "
					+"  	                               THEN d1.amount "
					+"  	                            ELSE d1.amount * -1 "
					+"  	                         END amount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		}
		
					
		sql += "  	                         JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.orderno = d1.orderno AND a.slsno = d1.slsno AND a.transtype = d1.transtype AND a.pcsec = d1.pcode "
			+"  	                         JOIN "+t.getTenantId()+".fmaster m ON d1.pcode = m.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}		
								
		sql += "                    	   WHERE c.tipe = '"+tipeDiskon+"' "
			+"                           AND c.klaim = '"+flagKlaim+"' ";
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		}		
		
		sql += "  	                     AND a.tprkode = '"+kodeAP+"' "
			+"  	                     AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
			+"  	        GROUP BY pcode, pcodename, convunit3 "
			+"  	        ORDER BY pcode) ";
		Query query = entityManager.createNativeQuery(sql);		
		
		List<Object[]> result = query.getResultList(); 
	
		return result;
	}

	@Override
	public List<Object[]> getTotalPromo(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom,
			String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String kodeAP, String invDateFrom,
			String invDateTo, String join) {
		// TODO Auto-generated method stub
		String sql = " SELECT CASE "
					+"           WHEN a.pctpr IS NOT NULL "
					+"              THEN TO_CHAR(a.pctpr || ' - ' || a.pcodename) "
					+"           ELSE '' "
					+"        END AS pctprname, "
					+"        a.besar, a.tengah, a.kecil, a.tpramountnew AS tpramount, "
					+"        b.pcode || ' - ' || b.pcodename AS pcodename, b.qty, b.amount "
					+"   FROM (SELECT ROWNUM AS ID, pctpr, pcodename, "
					+"                FLOOR (qty / convunit3) AS besar, "
					+"                FLOOR (MOD (qty, convunit3) / convunit2) AS tengah, "
					+"                FLOOR (MOD (MOD (qty, convunit3), convunit2)) AS kecil, "
					+"                amount AS tpramount, "
					+"                (NVL (FLOOR (qty / convunit3), 0) * sellprice3) "
					+"                + (NVL (FLOOR (MOD (qty, convunit3) / convunit2), 0) * sellprice2) "
					+"                + (NVL (FLOOR (MOD (MOD (qty, convunit3), convunit2)), 0) * sellprice1) AS tpramountnew "
					+"           FROM (SELECT   pctpr, pcodename, convunit2, convunit3, sellprice1, "
					+"                          sellprice2, sellprice3, SUM (qty) qty, SUM (amount) amount "
					+"                     FROM (SELECT a.pctpr, m.pcodename, m.convunit2, m.convunit3, m.sellprice1, m.sellprice2, m.sellprice3, "
					+"                                  CASE WHEN a.transtype = 'F' THEN a.qty ELSE a.qty * -1 END qty, "
					+"                                  CASE WHEN a.transtype = 'F' THEN d2.amount ELSE d2.amount * -1 END amount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		}		
					
		sql += "                                  JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode "
			+"                                  JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}			
			
		sql += "                                  JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"                            WHERE c.tipe = '"+tipeDiskon+"' "
			+"                              AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		}	
		
		
		sql += "                              AND a.tprkode = '"+kodeAP+"' "
			+"                              AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
			+"                 GROUP BY pctpr, pcodename, convunit2, convunit3, sellprice1, sellprice2, sellprice3)) a "
			+"        "+join+" "
			+"        (SELECT ROWNUM AS ID, pcode, pcodename, ROUND (qty / convunit3, 2) qty, amount "
			+"           FROM (SELECT   pcode, pcodename, convunit3, SUM (qty) qty, SUM (amount) amount "
			+"                     FROM (SELECT d1.pcode, m.pcodename, m.convunit3, "
			+"                                  CASE WHEN d1.transtype = 'F' THEN d1.qty ELSE d1.qty * -1 END qty, "
			+"                                  CASE WHEN d1.transtype = 'F' THEN d1.amount ELSE d1.amount * -1 END amount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		}			
			
		sql += "                                  JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.orderno = d1.orderno AND a.slsno = d1.slsno AND a.transtype = d1.transtype AND a.pcsec = d1.pcode "
			+"                                  JOIN "+t.getTenantId()+".fmaster m ON d1.pcode = m.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}
		
		sql += "                            WHERE c.tipe = '"+tipeDiskon+"' "
			+"                              AND c.klaim = '"+flagKlaim+"' ";
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		}
		
		sql += "                              AND a.tprkode = '"+kodeAP+"' "
			+"                              AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
			+"                 GROUP BY pcode, pcodename, convunit3 "
			+"                 ORDER BY pcode)) b ON a.ID = b.ID ";
		Query query = entityManager.createNativeQuery(sql);		
		
		List<Object[]> result = query.getResultList(); 
	
		return result;
		
	}

	@Override
	public void insertTempPromo(String flag, String userId, Integer urut, String kodeAP, String invno, String invDate, String slsno,
			String slsName, String custno, String custName, String pctpr, String pctprName, String besar, String tengah,
			String kecil, String tprAmount, String pcode, String pcodeName, String qty, String amount) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".tmpmtx_lapdiskonpromo (userid, urut, kodeap, invno, invdate, slsno, slsname, custno, custname, pctpr, pctprname, besar, tengah, kecil, tpramount, pcode, pcodename, qty, amount ";
		
		if (flag.equalsIgnoreCase("0")) { //DETAIL
			sql += " ,tpramount2, qty2, amount2 ";
		}		
					
		sql += "	) VALUES ( "
			+" '"+userId+"' "
			+","+urut+" "
			+",'"+kodeAP+"' "
			+",'"+invno+"' "
			+",'"+invDate+"' "
			+",'"+slsno+"' "
			+",'"+slsName+"' "
			+",'"+custno+"' "
			+",'"+custName+"' "
			+",'"+pctpr+"' "
			+",'"+pctprName+"' "
			+",'"+besar+"' "
			+",'"+tengah+"' "
			+",'"+kecil+"' "
			+",'"+tprAmount+"' "
			+",'"+pcode+"' "
			+",'"+pcodeName+"' "
			+",'"+qty+"' "
			+",'"+amount+"' ";
		
		if (flag.equalsIgnoreCase("0")) { //DETAIL
			sql += ","+tprAmount+" "
				+","+qty+" "
				+","+amount+" ";
		}	
			
		sql += " ) ";
		Query query = entityManager.createNativeQuery(sql);
		
		query.executeUpdate();
	}

	@Override
	public List<LaporanDiskonReportDetailPromoDto> getReportDetailPromo(String pilihanReport, String tipeDiskon,
			String flagKlaim, String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo, String userId) {
		// TODO Auto-generated method stub
		List<LaporanDiskonReportDetailPromoDto> result = new ArrayList<>();
		
		String header = "";
		if (pilihanReport.equalsIgnoreCase("2")) {
			header = "MONITORING PROMOSI";
		} else {
			header = "LAPORAN TPR (PROMOSI)";
		}
		
		String filterAP = "";
		if (kodeAPFrom.equalsIgnoreCase("")) {
			filterAP = "ALL";
		} else {
			filterAP = kodeAPFrom + " - " + kodeAPTo;
		}
		
		String filterKlaim = "";
		if (flagKlaim.equalsIgnoreCase("1")) {
			filterKlaim = "Klaim";
		} else {
			filterKlaim = "Tidak Klaim";
		}
		
		String filterTanggal = "";
		if (invDateFrom.equalsIgnoreCase("")) {
			filterTanggal = "ALL";
		} else {
			filterTanggal = invDateFrom + " - " + invDateTo;
		}	
		
		String sql = " SELECT   '"+header+"' || ' - ' || " 
					+"          (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') "
					+"          || ' ' ||  "
					+"          CASE "
					+"             WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END header, "
					+"          (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') || ' - ' || TO_CHAR (SYSDATE, 'HH24:MI:SS') tglsistem FROM DUAL) tglsistem, "
					+"          (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang, "
					+"          (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release,  "
					+"          'PROMOSI BARANG' tipediskon, "
					+"          '"+filterAP+"' filterap, "
					+"          '"+filterKlaim+"' flagklaim, '"+filterTanggal+"' filtertanggal, "
					+"          'Detail' pilihandiskon, a.kodeap, a.invno, a.invdate, "
					+"          CASE "
					+"             WHEN a.slsno IS NOT NULL "
					+"                THEN a.slsno || ' - ' || a.slsname "
					+"             ELSE '' "
					+"          END salesman, "
					+"          CASE "
					+"             WHEN a.custno IS NOT NULL "
					+"                THEN a.custno || ' - ' || a.custname "
					+"             ELSE a.custname "
					+"          END outlet, "
					+"          CASE "
					+"             WHEN a.pctpr IS NOT NULL "
					+"                THEN a.pctpr || ' - ' || a.pctprname "
					+"             ELSE a.pctprname "
					+"          END bonus, "
					+"          a.besar, a.tengah, a.kecil, a.tpramount, "
					+"          CASE "
					+"             WHEN a.pcode IS NOT NULL "
					+"                THEN a.pcode || ' - ' || a.pcodename "
					+"             ELSE a.pcodename "
					+"          END beli, "
					+"          a.qty, a.amount, a.tpramount2, a.qty2, a.amount2  "
					+"     FROM "+t.getTenantId()+".tmpmtx_lapdiskonpromo a "
					+"    WHERE a.userid = '"+userId+"' "
					+" ORDER BY a.urut ASC ";
		Query query = entityManager.createNativeQuery(sql);
				
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			LaporanDiskonReportDetailPromoDto data = new LaporanDiskonReportDetailPromoDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""), Objects.toString(obj[9], ""),
					Objects.toString(obj[10], ""), Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					Objects.toString(obj[13], ""), Objects.toString(obj[14], ""), (BigDecimal) obj[15],
					(BigDecimal) obj[16], (BigDecimal) obj[17], (BigDecimal) obj[18], Objects.toString(obj[19], ""),
					(BigDecimal) obj[20], (BigDecimal) obj[21], (BigDecimal) obj[22], (BigDecimal) obj[23],
					(BigDecimal) obj[24]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonReportDetailDto> getReportDetail(String flagHierarki, String pilihanReport, String tipeDiskon, String flagKlaim,
			String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo,
			String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonReportDetailDto> result = new ArrayList<>();
		
		String header = "";
		String filterTipeDiskon = "";
		
		if (tipeDiskon.equalsIgnoreCase("1")) {
			filterTipeDiskon = "EKSTRA DISKON 1";
			if (pilihanReport.equalsIgnoreCase("2")) {
				header = "MONITORING EKSTRA DISKON 1";
			} else {
				header = "LAPORAN TPR (EKSTRA DISKON 1)";
			}
		} else if (tipeDiskon.equalsIgnoreCase("2")) {
			filterTipeDiskon = "EKSTRA DISKON 2";
			if (pilihanReport.equalsIgnoreCase("2")) {
				header = "MONITORING EKSTRA DISKON 2";
			} else {
				header = "LAPORAN TPR (EKSTRA DISKON 2)";
			}
		} else if (tipeDiskon.equalsIgnoreCase("4")) {
			filterTipeDiskon = "REGULAR DISKON";
			if (pilihanReport.equalsIgnoreCase("2")) {
				header = "MONITORING REGULAR DISKON";
			} else {
				header = "LAPORAN TPR (REGULAR DISKON)";
			}
		}
		
		String filterAP = "";
		if (kodeAPFrom.equalsIgnoreCase("")) {
			filterAP = "ALL";
		} else {
			filterAP = kodeAPFrom + " - " + kodeAPTo;
		}
		
		String filterKlaim = "";
		if (flagKlaim.equalsIgnoreCase("1")) {
			filterKlaim = "Klaim";
		} else {
			filterKlaim = "Tidak Klaim";
		}
		
		String filterTanggal = "";
		if (invDateFrom.equalsIgnoreCase("")) {
			filterTanggal = "ALL";
		} else {
			filterTanggal = invDateFrom + " - " + invDateTo;
		}		
		
		String sql = " SELECT   '"+header+"' || ' - ' || " 
					+"          (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') || ' ' || " 
					+"          CASE "
					+"             WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END header, "
					+"          (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') || ' - ' || TO_CHAR (SYSDATE, 'HH24:MI:SS') tglsistem FROM DUAL) tglsistem, "
					+"          (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang, "
					+"          (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release, "
					+"          '"+filterTipeDiskon+"' tipediskon, "
					+"          '"+filterAP+"' filterap, " 
					+"          '"+filterKlaim+"' flagklaim, "
					+"          '"+filterTanggal+"' filtertanggal, " 
					+"          'Detail' pilihandiskon, "
					+"          a.data4 AS kodeap, h.invno, TO_CHAR (h.invdate, 'DD MON YYYY') invdate, " 
					+"          h.slsno, s.slsname, h.custno, b.custname, "
					+"          CASE "
					+"             WHEN h.transtype = 'F' "
					+"                THEN ROUND (a.data6, 0) "
					+"             ELSE ROUND (a.data6 * -1, 0) "
					+"          END AS disc, "
					+"          a.data5 AS pcode, m.pcodename, "
					+"          CASE "
					+"             WHEN h.transtype = 'F' "
					+"                THEN ROUND (d1.qty / m.convunit3, 2) "
					+"             ELSE ROUND (d1.qty / m.convunit3 * -1, 2) "
					+"          END AS qty, "
					+"          CASE "
					+"             WHEN h.transtype = 'F' "
					+"                THEN d1.amount "
					+"             ELSE d1.amount * -1 "
					+"          END AS amount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".ftabel55 a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".ftabel55_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		}		
		
		sql += "          JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
			+"          JOIN "+t.getTenantId()+".fmaster m ON a.data5 = m.pcode "
			+"          JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.data1 = d1.orderno AND a.data2 = d1.slsno AND a.data5 = d1.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.data4 = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.data4 = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}
			
		sql += "          JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"    WHERE h.invno IS NOT NULL "
			+"      AND a.tipediskon = '"+tipeDiskon+"' "
			+"      AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d1.pcode IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}		
					
		sql += "      AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"' "
			  +" ORDER BY a.data4, h.invno, h.transtype ASC ";
		Query query = entityManager.createNativeQuery(sql);
				
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			
			LaporanDiskonReportDetailDto data = new LaporanDiskonReportDetailDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""), Objects.toString(obj[9], ""),
					Objects.toString(obj[10], ""), Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					Objects.toString(obj[13], ""), Objects.toString(obj[14], ""), Objects.toString(obj[15], ""),
					(BigDecimal) obj[16], Objects.toString(obj[17], ""), Objects.toString(obj[18], ""),
					(BigDecimal) obj[19], (BigDecimal) obj[20]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonReportSummaryDto> getReportSummary(String flagHierarki, String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonReportSummaryDto> result = new ArrayList<>();
		
		String header = "";
		String filterTipeDiskon = "";
		
		if (tipeDiskon.equalsIgnoreCase("1")) {
			filterTipeDiskon = "EKSTRA DISKON 1";
			if (pilihanReport.equalsIgnoreCase("2")) {
				header = "MONITORING EKSTRA DISKON 1";
			} else {
				header = "LAPORAN TPR (EKSTRA DISKON 1)";
			}
		} else if (tipeDiskon.equalsIgnoreCase("2")) {
			filterTipeDiskon = "EKSTRA DISKON 2";
			if (pilihanReport.equalsIgnoreCase("2")) {
				header = "MONITORING EKSTRA DISKON 2";
			} else {
				header = "LAPORAN TPR (EKSTRA DISKON 2)";
			}
		} else if (tipeDiskon.equalsIgnoreCase("4")) {
			filterTipeDiskon = "REGULAR DISKON";
			if (pilihanReport.equalsIgnoreCase("2")) {
				header = "MONITORING REGULAR DISKON";
			} else {
				header = "LAPORAN TPR (REGULAR DISKON)";
			}
		}
		
		String filterAP = "";
		if (kodeAPFrom.equalsIgnoreCase("")) {
			filterAP = "ALL";
		} else {
			filterAP = kodeAPFrom + " - " + kodeAPTo;
		}
		
		String filterKlaim = "";
		if (flagKlaim.equalsIgnoreCase("1")) {
			filterKlaim = "Klaim";
		} else {
			filterKlaim = "Tidak Klaim";
		}
		
		String filterTanggal = "";
		if (invDateFrom.equalsIgnoreCase("")) {
			filterTanggal = "ALL";
		} else {
			filterTanggal = invDateFrom + " - " + invDateTo;
		}		
		
		String sql = " SELECT   '"+header+"' || ' - ' || " 
					+"          (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') || ' ' || " 
					+"          CASE "
					+"             WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END header, "
					+"          (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') || ' - ' || TO_CHAR (SYSDATE, 'HH24:MI:SS') tglsistem FROM DUAL) tglsistem, "
					+"          (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang, "
					+"          (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release, "
					+"          '"+filterTipeDiskon+"' tipediskon, "
					+"          '"+filterAP+"' filterap, " 
					+"          '"+filterKlaim+"' flagklaim, "
					+"          '"+filterTanggal+"' filtertanggal, " 
					+"          'Rekapitulasi' pilihandiskon, "
					+"          kodeap, invno, invdate, slsno, slsname, custno, custname, "
					+"          ROUND (SUM (disc), 0) AS disc, COUNT (pcode) AS pcode, "
					+"          SUM (qty) AS qty, SUM (amount) AS amount, transtype "
					+"     FROM (SELECT a.data4 AS kodeap, h.invno, h.custno, b.custname, "
					+"                  TO_CHAR (h.invdate, 'DD MON YYYY') invdate, h.slsno, "
					+"                  s.slsname, "
					+"                  CASE "
					+"                     WHEN h.transtype = 'F' "
					+"                        THEN a.data6 "
					+"                     ELSE a.data6 * -1 "
					+"                  END AS disc, a.data5 AS pcode, "
					+"                  CASE "
					+"                     WHEN h.transtype = 'F' "
					+"                        THEN ROUND (d1.qty / m.convunit3, 2) "
					+"                     ELSE ROUND (d1.qty / m.convunit3 * -1, 2) "
					+"                  END AS qty, "
					+"                  CASE "
					+"                     WHEN h.transtype = 'F' "
					+"                        THEN d1.amount "
					+"                     ELSE d1.amount * -1 "
					+"                  END AS amount, "
					+"                  h.transtype ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".ftabel55 a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".ftabel55_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		}		
					
		sql += "                  JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
			+"                  JOIN "+t.getTenantId()+".fmaster m ON a.data5 = m.pcode "
			+"                  JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.data1 = d1.orderno AND a.data2 = d1.slsno AND a.data5 = d1.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.data4 = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.data4 = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}		
			
		sql += "                  JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"            WHERE h.invno IS NOT NULL "
			+"              AND a.tipediskon = '"+tipeDiskon+"' "
			+"              AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d1.pcode IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}		
					
		sql += "              AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
			+" GROUP BY kodeap, invno, invdate, slsno, slsname, custno, custname, transtype "
			+" ORDER BY kodeap, invno, transtype ASC ";
		Query query = entityManager.createNativeQuery(sql);
				
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			
			LaporanDiskonReportSummaryDto data = new LaporanDiskonReportSummaryDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""), Objects.toString(obj[9], ""),
					Objects.toString(obj[10], ""), Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					Objects.toString(obj[13], ""), Objects.toString(obj[14], ""), Objects.toString(obj[15], ""),
					(BigDecimal) obj[16], (BigDecimal) obj[17], (BigDecimal) obj[18], (BigDecimal) obj[19],
					Objects.toString(obj[20], ""));
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonReportSummaryByAPDto> getReportSummaryByAP(String flagHierarki, String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonReportSummaryByAPDto> result = new ArrayList<>();
		
		String header = "";
		String filterTipeDiskon = "";
		
		if (tipeDiskon.equalsIgnoreCase("1")) {
			filterTipeDiskon = "EKSTRA DISKON 1";
			if (pilihanReport.equalsIgnoreCase("2")) {
				header = "MONITORING EKSTRA DISKON 1";
			} else {
				header = "LAPORAN TPR (EKSTRA DISKON 1)";
			}
		} else if (tipeDiskon.equalsIgnoreCase("2")) {
			filterTipeDiskon = "EKSTRA DISKON 2";
			if (pilihanReport.equalsIgnoreCase("2")) {
				header = "MONITORING EKSTRA DISKON 2";
			} else {
				header = "LAPORAN TPR (EKSTRA DISKON 2)";
			}
		} else if (tipeDiskon.equalsIgnoreCase("4")) {
			filterTipeDiskon = "REGULAR DISKON";
			if (pilihanReport.equalsIgnoreCase("2")) {
				header = "MONITORING REGULAR DISKON";
			} else {
				header = "LAPORAN TPR (REGULAR DISKON)";
			}
		}
		
		String filterAP = "";
		if (kodeAPFrom.equalsIgnoreCase("")) {
			filterAP = "ALL";
		} else {
			filterAP = kodeAPFrom + " - " + kodeAPTo;
		}
		
		String filterKlaim = "";
		if (flagKlaim.equalsIgnoreCase("1")) {
			filterKlaim = "Klaim";
		} else {
			filterKlaim = "Tidak Klaim";
		}
		
		String filterTanggal = "";
		if (invDateFrom.equalsIgnoreCase("")) {
			filterTanggal = "ALL";
		} else {
			filterTanggal = invDateFrom + " - " + invDateTo;
		}		
		
		String sql = " SELECT   '"+header+"' || ' - ' || " 
					+"          (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') || ' ' || " 
					+"          CASE "
					+"             WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END header, "
					+"          (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') || ' - ' || TO_CHAR (SYSDATE, 'HH24:MI:SS') tglsistem FROM DUAL) tglsistem, "
					+"          (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang, "
					+"          (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release, "
					+"          '"+filterTipeDiskon+"' tipediskon, "
					+"          '"+filterAP+"' filterap, " 
					+"          '"+filterKlaim+"' flagklaim, "
					+"          '"+filterTanggal+"' filtertanggal, " 
					+"          'Rekapitulasi' pilihandiskon, "					
					+"          a.kodeap, b.keterangan, TO_CHAR (b.tglawal, 'DD MON YYYY') awal, " 
					+"          TO_CHAR (b.tglakhir, 'DD MON YYYY') akhir, slsno, slsname, SUM (a.pcode) pcode, "
					+"          SUM (disc) disc, SUM (qty) qty, SUM (amount) amount "
					+"     FROM ((SELECT   kodeap, invno, invdate, slsno, slsname, custno, custname, "
					+"                     ROUND (SUM (disc), 0) AS disc, COUNT (pcode) AS pcode, "
					+"                     SUM (qty) AS qty, SUM (amount) AS amount, transtype "
					+"                FROM (SELECT a.data4 AS kodeap, h.invno, h.custno, b.custname, "
					+"                             TO_CHAR (h.invdate, 'DD MON YYYY') invdate, h.slsno, s.slsname, "
					+"                             CASE "
					+"                                WHEN h.transtype = 'F' "
					+"                                   THEN a.data6 "
					+"                                ELSE a.data6 * -1 "
					+"                             END AS disc, "
					+"                             a.data5 AS pcode, "
					+"                             CASE "
					+"                                WHEN h.transtype = 'F' "
					+"                                   THEN ROUND (d1.qty / m.convunit3, 2) "
					+"                                ELSE ROUND (d1.qty / m.convunit3 * -1, 2) "
					+"                             END AS qty, "
					+"                             CASE "
					+"                                WHEN h.transtype = 'F' "
					+"                                   THEN d1.amount "
					+"                                ELSE d1.amount * -1 "
					+"                             END AS amount, "
					+"                             h.transtype ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".ftabel55 a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".ftabel55_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		}		
					
		sql += "                             JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
			+"                             JOIN "+t.getTenantId()+".fmaster m ON a.data5 = m.pcode "
			+"                             JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.data1 = d1.orderno AND a.data2 = d1.slsno AND a.data5 = d1.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.data4 = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.data4 = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}
			
		sql += "                             JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"                       WHERE h.invno IS NOT NULL "
			+"                         AND a.tipediskon = '"+tipeDiskon+"' "
			+"                         AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d1.pcode IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}		
					
		sql += "                         AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
			+"            GROUP BY kodeap, invno, invdate, slsno, slsname, custno, custname, transtype)) a "
			+"          LEFT JOIN "+t.getTenantId()+".ftable155 b ON a.kodeap = b.kodeap "
			+"    WHERE a.kodeap IS NOT NULL "
			+" GROUP BY a.kodeap, b.keterangan, b.tglawal, b.tglakhir, slsno, slsname "
			+" ORDER BY a.kodeap, b.tglawal, b.tglakhir, slsno ASC ";
		Query query = entityManager.createNativeQuery(sql);
				
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			
			LaporanDiskonReportSummaryByAPDto data = new LaporanDiskonReportSummaryByAPDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""), Objects.toString(obj[9], ""),
					Objects.toString(obj[10], ""), Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					Objects.toString(obj[13], ""), Objects.toString(obj[14], ""), (BigDecimal) obj[15],
					(BigDecimal) obj[16], (BigDecimal) obj[17], (BigDecimal) obj[18]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonExtractSummaryPromoDto> extractSummaryPromo(String flagHierarki, String tipeDiskon,
			String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom,
			String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonExtractSummaryPromoDto> result = new ArrayList<>();			
		
		String sql = " SELECT   (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') kodedist, "
					+"          CASE "
					+"             WHEN (SELECT DISTINCT memonama || '-' || memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT DISTINCT memonama || '-' || memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END namadist, "
					+"          a.kodeap, a.invno, a.invdate, a.slsno, a.slsname, a.custno, a.custname, a.pctpr, a.pctprname, a.besar, a.tengah, a.kecil, a.tpramount, "
					+"          CASE "
					+"             WHEN a.transtype = 'F' "
					+"                THEN b.amount "
					+"             ELSE b.amount * -1 "
					+"          END AS amount "
					+"     FROM ((SELECT ROWNUM AS ID, a.tprkode AS kodeap, h.invno, "
					+"                   TO_CHAR (h.invdate, 'DD MON YYYY') invdate, h.slsno, "
					+"                   s.slsname, h.custno, b.custname, a.pctpr, m.pcodename AS pctprname, "
					+"                   FLOOR (a.qty / m.convunit3) AS besar, "
					+"                   FLOOR (MOD (a.qty, m.convunit3) / m.convunit2) AS tengah, "
					+"                   FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)) AS kecil, "
					+"                   CASE "
					+"                      WHEN h.transtype = 'F' "
					+"                         THEN ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                               + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                               + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) "
					+"                      ELSE   ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3) "
					+"                              + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2) "
					+"                              + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) * -1 "
					+"                   END AS tpramount, h.transtype ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		}		
					
		sql += "                   JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
			+"                   JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode "
			+"                   JOIN "+t.getTenantId()+".fjual_d2 d2 "
			+"                   ON a.orderno = d2.orderno "
			+"                 AND a.slsno = d2.slsno "
			+"                 AND a.transtype = d2.transtype "
			+"                 AND a.pctpr = d2.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}	
		
			
		sql += "                   JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"             WHERE c.tipe = '"+tipeDiskon+"' "
			+"               AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}
					
		sql += "               AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') a "
			+"          LEFT JOIN "
			+"          (SELECT   d.tprkode, h.invno, SUM (d1.amount) amount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d_hir d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype ";
		}		
			
		sql += "                    JOIN "+t.getTenantId()+".fjual_d1 d1 ON d.orderno = d1.orderno AND d.slsno = d1.slsno AND d.transtype = d1.transtype AND d.pcsec = d1.pcode "
			+"                    JOIN "+t.getTenantId()+".fmaster m1 ON d1.pcode = m1.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON d.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON d.tprkode = c.kodeap AND d.tglawal = c.tglawal AND d.berlakuhierarki = c.berlakuhierarki AND d.detailhierarki = c.detailhierarki ";
		}		
			
		sql += "              WHERE c.tipe = '"+tipeDiskon+"' "
			+"                AND c.klaim = '"+flagKlaim+"' ";
					
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}			
					
		sql += "                AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"' "
			+"           GROUP BY d.tprkode, h.invno) b ON a.kodeap = b.tprkode AND a.invno = b.invno) "
			+" ORDER BY a.kodeap, a.invno, a.pctpr ASC ";
		Query query = entityManager.createNativeQuery(sql);
				
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			LaporanDiskonExtractSummaryPromoDto data = new LaporanDiskonExtractSummaryPromoDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""), Objects.toString(obj[5], ""),
					Objects.toString(obj[6], ""), Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""), (BigDecimal) obj[11],
					(BigDecimal) obj[12], (BigDecimal) obj[13], (BigDecimal) obj[14], (BigDecimal) obj[15]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonExtractSummaryPromoByAPDto> extractSummaryPromoByAP(String flagHierarki, String tipeDiskon,
			String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom,
			String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonExtractSummaryPromoByAPDto> result = new ArrayList<>();		
		
		String sql = " SELECT   (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') kodedist, "
					+"          CASE "
					+"             WHEN (SELECT DISTINCT memonama || '-' || memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT DISTINCT memonama || '-' || memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END namadist, "
					+"	         a.kodeap, a.ket, a.awal, a.akhir, a.slsno, a.slsname, a.pctpr,	"
					+"	         a.pctprname, a.qty, a.tpramount, a.amount,	a.convunit2, a.convunit3, "
					+"	         FLOOR (qty / convunit3) AS besar, FLOOR ((qty - (FLOOR (qty / convunit3) * convunit3)) / convunit2) AS tengah,	"
					+"	         qty - (FLOOR (qty / convunit3) * convunit3 + (FLOOR (  (qty - (FLOOR (qty / convunit3) * convunit3)) / convunit2)) * convunit2) AS kecil	"
					+"	    FROM (SELECT   a.kodeap, b.keterangan ket,	"
					+"	                   TO_CHAR (b.tglawal, 'DD MON YYYY') awal,	"
					+"	                   TO_CHAR (b.tglakhir, 'DD MON YYYY') akhir, a.slsno,	"
					+"	                   a.slsname, a.pctpr, pctprname,	"
					+"	                   (SUM (besar) * m.convunit3) + (SUM (tengah) * m.convunit2) + SUM (kecil) qty,	"
					+"	                   SUM (tpramount) tpramount, SUM (amount) amount, m.convunit2, m.convunit3	"
					+"	              FROM (SELECT   a.kodeap, a.invno, a.invdate, a.slsno, a.slsname,	"
					+"	                             a.custno, a.custname, a.pctpr, a.pctprname,	"
					+"	                             a.besar, a.tengah, a.kecil, a.tpramount,	"
					+"	                             CASE	"
					+"	                                WHEN a.transtype = 'F'	"
					+"	                                   THEN b.amount	"
					+"	                                ELSE b.amount * -1	"
					+"	                             END AS amount	"
					+"	                        FROM ((SELECT a.tprkode AS kodeap, h.invno, TO_CHAR (h.invdate, 'DD MON YYYY') invdate,	"
					+"	                                      h.slsno, s.slsname, h.custno, b.custname, a.pctpr, m.pcodename AS pctprname,	"
					+"	                                      FLOOR (a.qty / m.convunit3) AS besar,	"
					+"	                                      FLOOR (MOD (a.qty, m.convunit3) / m.convunit2) AS tengah,	"
					+"	                                      FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)) AS kecil,	"
					+"	                                      CASE	"
					+"	                                         WHEN h.transtype = 'F'	"
					+"	                                            THEN ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3)	"
					+"	                                                  + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2)	"
					+"	                                                  + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1))	"
					+"	                                         ELSE   ((NVL (FLOOR (a.qty / m.convunit3), 0) * m.sellprice3)	"
					+"	                                                 + (NVL (FLOOR (MOD (a.qty, m.convunit3) / m.convunit2), 0) * m.sellprice2)	"
					+"	                                                 + (NVL (FLOOR (MOD (MOD (a.qty, m.convunit3), m.convunit2)), 0) * m.sellprice1)) * -1	"
					+"	                                      END AS tpramount, h.transtype	";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_h_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.orderno = h.orderno AND a.slsno = h.slsno AND a.transtype = h.transtype ";
		}
					
		sql += "	                                      JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno	"
			+"	                                      JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode	"
			+"	                                      JOIN "+t.getTenantId()+".fjual_d2 d2 ON a.orderno = d2.orderno AND a.slsno = d2.slsno AND a.transtype = d2.transtype AND a.pctpr = d2.pcode	";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.tprkode = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}
		
			
		sql += "	                                      JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno	"
			+"	                                WHERE c.tipe = '"+tipeDiskon+"'	"
			+"	                                  AND c.klaim = '"+flagKlaim+"'	";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND a.pctpr = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d2.pctpr IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}		
					
		sql += "	                                  AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') a "
			+"	                             LEFT JOIN	"
			+"	                             (SELECT   d.tprkode, h.invno, SUM (d1.amount) amount ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".fhasiltpr_d_hir d JOIN "+t.getTenantId()+".fjual_h h ON d.orderno = h.orderno AND d.slsno = h.slsno AND d.transtype = h.transtype ";
		}
			
		sql += "	                                       JOIN "+t.getTenantId()+".fjual_d1 d1 ON d.orderno = d1.orderno AND d.slsno = d1.slsno AND d.transtype = d1.transtype AND d.pcsec = d1.pcode	"
			+"	                                       JOIN "+t.getTenantId()+".fmaster m1 ON d1.pcode = m1.pcode	";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON d.tprkode = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON d.tprkode = c.kodeap AND d.tglawal = c.tglawal AND d.berlakuhierarki = c.berlakuhierarki AND d.detailhierarki = c.detailhierarki ";
		}
			
		sql += "	                                 WHERE c.tipe = '"+tipeDiskon+"'	"
			+"	                                   AND c.klaim = '"+flagKlaim+"'	";
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}
		
			
		sql += "	                                   AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"'	"
			+"	                              GROUP BY d.tprkode, h.invno) b	"
			+"	                             ON a.kodeap = b.tprkode AND a.invno = b.invno)) a	";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " LEFT JOIN "+t.getTenantId()+".ftable155 b ON a.kodeap = b.kodeap ";
		} else {
			sql += " LEFT JOIN "+t.getTenantId()+".ftable155_hir b ON a.kodeap = b.kodeap ";
		}
			
		sql += "	                   LEFT JOIN "+t.getTenantId()+".fmaster m ON a.pctpr = m.pcode	"
			+"	             WHERE a.kodeap IS NOT NULL	"
			+"	          GROUP BY a.kodeap, b.keterangan, b.tglawal, b.tglakhir, slsno, slsname, a.pctpr, a.pctprname, convunit2, convunit3) a	"
			+"	ORDER BY kodeap, a.slsno, a.pctpr	";
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			LaporanDiskonExtractSummaryPromoByAPDto data = new LaporanDiskonExtractSummaryPromoByAPDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""), Objects.toString(obj[5], ""),
					Objects.toString(obj[6], ""), Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), (BigDecimal) obj[10], (BigDecimal) obj[11], (BigDecimal) obj[12],
					(BigDecimal) obj[13], (BigDecimal) obj[14], (BigDecimal) obj[15], (BigDecimal) obj[16],
					(BigDecimal) obj[17]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonExtractSummaryDto> extractSummary(String flagHierarki, String tipeDiskon, String flagKlaim,
			String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo,
			String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonExtractSummaryDto> result = new ArrayList<>(); 
		
		String sql = " SELECT   (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') kodedist, "
					+"          CASE "
					+"             WHEN (SELECT DISTINCT memonama || '-' || memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT DISTINCT memonama || '-' || memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END namadist, "
					+"          kodeap, invno, invdate, slsno, slsname, custno, custname, npwp, "
					+"          custadd1, ROUND (SUM (disc), 0) AS disc, COUNT (pcode) AS pcode, "
					+"          SUM (qty) AS qty, SUM (amount) AS amount, transtype "
					+"     FROM (SELECT a.data4 AS kodeap, h.invno, h.custno, b.custname, b.npwp, "
					+"                  b.custadd1, TO_CHAR (h.invdate, 'DD MON YYYY') invdate, h.slsno, "
					+"                  s.slsname, "
					+"                  CASE "
					+"                     WHEN h.transtype = 'F' "
					+"                        THEN a.data6 "
					+"                     ELSE a.data6 * -1 "
					+"                  END AS disc, a.data5 AS pcode, "
					+"                  CASE "
					+"                     WHEN h.transtype = 'F' "
					+"                        THEN ROUND (d1.qty / m.convunit3, 2) "
					+"                     ELSE ROUND (d1.qty / m.convunit3 * -1, 2) "
					+"                  END AS qty, "
					+"                  CASE "
					+"                     WHEN h.transtype = 'F' "
					+"                        THEN d1.amount "
					+"                     ELSE d1.amount * -1 "
					+"                  END AS amount, "
					+"                  h.transtype ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".ftabel55 a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".ftabel55_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		}		
					
		sql += "                  JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
			+"                  JOIN "+t.getTenantId()+".fmaster m ON a.data5 = m.pcode "
			+"                  JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.data1 = d1.orderno AND a.data2 = d1.slsno AND a.data5 = d1.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.data4 = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.data4 = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}		
			
		sql += "                  JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"            WHERE h.invno IS NOT NULL "
			+"              AND a.tipediskon = '"+tipeDiskon+"' "
			+"              AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d1.pcode IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}		
					
		sql += "              AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
			+" GROUP BY kodeap, invno, invdate, slsno, slsname, custno, custname, npwp, custadd1, transtype "
			+" ORDER BY kodeap, invno, transtype ASC ";
		Query query = entityManager.createNativeQuery(sql);
				
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			
			LaporanDiskonExtractSummaryDto data = new LaporanDiskonExtractSummaryDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""), Objects.toString(obj[9], ""),
					Objects.toString(obj[10], ""), (BigDecimal) obj[11], (BigDecimal) obj[12], (BigDecimal) obj[13],
					(BigDecimal) obj[14]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<LaporanDiskonExtractSummaryByAPDto> extractSummaryByAP(String flagHierarki, String tipeDiskon,
			String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom,
			String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo) {
		// TODO Auto-generated method stub
		List<LaporanDiskonExtractSummaryByAPDto> result = new ArrayList<>();		
		
		String sql = " SELECT   (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') kodedist, "
					+"          CASE "
					+"             WHEN (SELECT DISTINCT memonama || '-' || memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+"                THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+"             ELSE (SELECT DISTINCT memonama || '-' || memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"          END namadist, "				
					+"          a.kodeap, b.keterangan, TO_CHAR (b.tglawal, 'DD MON YYYY') awal, " 
					+"          TO_CHAR (b.tglakhir, 'DD MON YYYY') akhir, slsno, slsname, "
					+"          SUM (disc) disc, SUM (a.pcode) pcode, SUM (qty) qty, SUM (amount) amount "
					+"     FROM ((SELECT   kodeap, invno, invdate, slsno, slsname, custno, custname, "
					+"                     ROUND (SUM (disc), 0) AS disc, COUNT (pcode) AS pcode, "
					+"                     SUM (qty) AS qty, SUM (amount) AS amount, transtype "
					+"                FROM (SELECT a.data4 AS kodeap, h.invno, h.custno, b.custname, "
					+"                             TO_CHAR (h.invdate, 'DD MON YYYY') invdate, h.slsno, s.slsname, "
					+"                             CASE "
					+"                                WHEN h.transtype = 'F' "
					+"                                   THEN a.data6 "
					+"                                ELSE a.data6 * -1 "
					+"                             END AS disc, "
					+"                             a.data5 AS pcode, "
					+"                             CASE "
					+"                                WHEN h.transtype = 'F' "
					+"                                   THEN ROUND (d1.qty / m.convunit3, 2) "
					+"                                ELSE ROUND (d1.qty / m.convunit3 * -1, 2) "
					+"                             END AS qty, "
					+"                             CASE "
					+"                                WHEN h.transtype = 'F' "
					+"                                   THEN d1.amount "
					+"                                ELSE d1.amount * -1 "
					+"                             END AS amount, "
					+"                             h.transtype ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " FROM "+t.getTenantId()+".ftabel55 a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		} else {
			sql += " FROM "+t.getTenantId()+".ftabel55_hir a JOIN "+t.getTenantId()+".fjual_h h ON a.data1 = h.orderno AND a.data2 = h.slsno AND a.data3 = h.transtype ";
		}		
					
		sql += "                             JOIN "+t.getTenantId()+".fcustmst b ON h.custno = b.custno "
			+"                             JOIN "+t.getTenantId()+".fmaster m ON a.data5 = m.pcode "
			+"                             JOIN "+t.getTenantId()+".fjual_d1 d1 ON a.data1 = d1.orderno AND a.data2 = d1.slsno AND a.data5 = d1.pcode ";
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			sql += " JOIN "+t.getTenantId()+".ftable155 c ON a.data4 = c.kodeap ";
		} else {
			sql += " JOIN "+t.getTenantId()+".ftable155_hir c ON a.data4 = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki ";
		}
			
		sql += "                             JOIN "+t.getTenantId()+".fsls s ON h.slsno = s.slsno "
			+"                       WHERE h.invno IS NOT NULL "
			+"                         AND a.tipediskon = '"+tipeDiskon+"' "
			+"                         AND c.klaim = '"+flagKlaim+"' ";
		
		if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (!pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode BETWEEN '"+pcodeFrom+"' AND '"+pcodeTo+"' ";
		} else if ((filterProduk.equalsIgnoreCase("1")) && (!pcodeFrom.equalsIgnoreCase("")) && (pcodeTo.equalsIgnoreCase(""))) {
			sql += " AND d1.pcode = '"+pcodeFrom+"' ";
		} else if ( (filterProduk.equalsIgnoreCase("2")) && (!pcodeIn.equalsIgnoreCase("")) ) {
			sql += " AND d1.pcode IN ("+pcodeIn+") ";
		}
		
		if ((!custnoFrom.equalsIgnoreCase("")) && (!custnoTo.equalsIgnoreCase(""))) {
			sql += " AND h.custno BETWEEN '"+custnoFrom+"' AND '"+custnoTo+"' ";
		} else if ( (!custnoFrom.equalsIgnoreCase("")) && (custnoTo.equalsIgnoreCase("")) ) {
			sql += " AND h.custno = '"+custnoFrom+"' ";
		} 
			
		if ((!kodeAPFrom.equalsIgnoreCase("")) && (!kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap BETWEEN '"+kodeAPFrom+"' AND '"+kodeAPTo+"' ";
		} else if ((!kodeAPFrom.equalsIgnoreCase("")) && (kodeAPTo.equalsIgnoreCase(""))) {
			sql += " AND c.kodeap = '"+kodeAPFrom+"' ";
		}		
					
		sql += "                         AND h.invdate BETWEEN '"+invDateFrom+"' AND '"+invDateTo+"') "
			+"            GROUP BY kodeap, invno, invdate, slsno, slsname, custno, custname, transtype)) a "
			+"          LEFT JOIN "+t.getTenantId()+".ftable155 b ON a.kodeap = b.kodeap "
			+"    WHERE a.kodeap IS NOT NULL "
			+" GROUP BY a.kodeap, b.keterangan, b.tglawal, b.tglakhir, slsno, slsname "
			+" ORDER BY a.kodeap, b.tglawal, b.tglakhir, slsno ASC ";
		Query query = entityManager.createNativeQuery(sql);
				
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			
			LaporanDiskonExtractSummaryByAPDto data = new LaporanDiskonExtractSummaryByAPDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""), Objects.toString(obj[5], ""),
					Objects.toString(obj[6], ""), Objects.toString(obj[7], ""), (BigDecimal) obj[8],
					(BigDecimal) obj[9], (BigDecimal) obj[10], (BigDecimal) obj[11]);
			
			result.add(data);
		}
		
		return result;
	}
	

}
