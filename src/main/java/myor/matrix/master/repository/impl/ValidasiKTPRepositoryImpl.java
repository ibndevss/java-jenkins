package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ReportValidasiKTPDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.ValidasiKTPDto;
import myor.matrix.master.entity.ValidasiKTPOutletApproveDto;
import myor.matrix.master.entity.ValidasiKtpOutletGroupPayerDto;
import myor.matrix.master.repository.ValidasiKTPRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ValidasiKTPRepositoryImpl implements ValidasiKTPRepository{
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean cekPathPhotoKTP() {
		String sql = "select * from "+t.getTenantId()+".ftable13 where xkey='XPHOTOKTP'";
		Query query = entityManager.createNativeQuery(sql);
		if(query.getResultList().size() == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<ValidasiKTPDto> getValidasiKTP(String custno) {
		List<ValidasiKTPDto> result = new ArrayList<>();
		String sql =  " SELECT c.custno, c.outlet, c.grouppayer || ' - ' || d.custname AS grouppayer, "
				+ "       c.grouppayer AS grouppayerid, d.custadd1 || d.custadd2 AS alamat, "
				+ "       cud.h11 || ' - ' || cud.propinsi AS provinsi, "
				+ "       cud.h12 || ' - ' || cud.kabupaten AS kabupaten, "
				+ "       cud.h13 || ' - ' || cud.kecamatan AS kecamatan, "
				+ "       cud.h14 || ' - ' || cud.kelurahan AS kelurahan, "
				+ "       TO_CHAR (c.docdate, 'DD MON YYYY') tglval, "
				+ "       c.slsno || ' - ' || s.slsname AS salesman, "
				+ "       CASE "
				+ "          WHEN c.flag_ktp = 0 OR c.flag_ktp IS NULL "
				+ "             THEN 'OPEN' "
				+ "          WHEN c.flag_ktp = 1 "
				+ "             THEN 'APPROVE' "
				+ "          WHEN c.flag_ktp = 2 "
				+ "             THEN 'REJECT' "
				+ "       END AS status, "
				+ "       c.nama_pemilik, c.tlp, c.nik, c.nama_ktp, c.alamat_ktp, c.alamat_ktp2, "
				+ "       c.npwp, c.nama_npwp, c.alamat_npwp, c.alamat_npwp2, c.foto_ktp, "
				+ "       c.foto_npwp, (select memostring from "+t.getTenantId()+".ftable13 where xkey='XPHOTOKTP') path "
				+ "  FROM (SELECT a.custno, a.custno || ' - ' || b.custname AS outlet, "
				+ "               grouppayer, konf.docdate, slsno, flag_ktp, nama_pemilik, tlp, "
				+ "               nik, nama_ktp, alamat_ktp, alamat_ktp2, a.npwp, a.nama_npwp, "
				+ "               alamat_npwp, alamat_npwp2, foto_ktp, foto_npwp "
				+ "          FROM (SELECT   custno, MAX (docdate) AS docdate "
				+ "                    FROM "+t.getTenantId()+".fkonfirmasi_ktp "
				+ "                   WHERE custno = '"+custno+"' "
				+ "                GROUP BY custno) konf "
				+ "               LEFT JOIN "
				+ "               "+t.getTenantId()+".fkonfirmasi_ktp a "
				+ "               ON konf.custno = a.custno AND konf.docdate = a.docdate "
				+ "               LEFT JOIN "+t.getTenantId()+".fcustmst b ON a.custno = b.custno "
				+ "         WHERE a.custno = '"+custno+"') c "
				+ "       LEFT JOIN "
				+ "       "+t.getTenantId()+".fcustmst d ON c.grouppayer = d.custno "
				+ "       LEFT JOIN "
				+ "       (SELECT a.custno, a.h11, b.ket AS propinsi, h12, c.ket AS kabupaten, "
				+ "               h13, d.ket AS kecamatan, h14, e.ket AS kelurahan "
				+ "          FROM "+t.getTenantId()+".fcustud a LEFT JOIN "+t.getTenantId()+".fcshir11 b "
				+ "               ON (a.h11 = b.t11) "
				+ "               LEFT JOIN "+t.getTenantId()+".fcshir12 c "
				+ "               ON (a.h11 || a.h12 = c.t11 || c.t12) "
				+ "               LEFT JOIN "+t.getTenantId()+".fcshir13 d "
				+ "               ON (a.h11 || a.h12 || a.h13 = d.t11 || d.t12 || d.t13) "
				+ "               LEFT JOIN "+t.getTenantId()+".fcshir14 e "
				+ "               ON (a.h11 || a.h12 || a.h13 || a.h14 = "
				+ "                                              e.t11 || e.t12 || e.t13 || e.t14 "
				+ "                  ) "
				+ "         WHERE a.custno = '"+custno+"') cud ON c.custno = cud.custno "
				+ "       LEFT JOIN "+t.getTenantId()+".fsls s ON c.slsno = s.slsno";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for(Object[]o: obj) {
			ValidasiKTPDto data = new ValidasiKTPDto((String)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5], 
					(String)o[6], (String)o[7], (String)o[8], (String)o[9], (String)o[10], (String)o[11], (String)o[12], (String)o[13], 
					(String)o[14], (String)o[15], (String)o[16], (String)o[17], (String)o[18], (String)o[19], (String)o[20], (String)o[21], (String)o[22], (String)o[23]);
			data.setPath((String)o[24]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ValidasiKtpOutletGroupPayerDto> getDetilGroupPayer(String custno) {
		List<ValidasiKtpOutletGroupPayerDto> result = new ArrayList<>();
		String sql = "SELECT   a.custno, b.custname, a.grouppayer, "
				+ "         c.slsno || ' - ' || s.slsname AS salesman, flag "
				+ "    FROM (SELECT DISTINCT custno, grouppayer, "
				+ "                          CASE "
				+ "                             WHEN custno = grouppayer "
				+ "                                THEN 1 "
				+ "                             ELSE 0 "
				+ "                          END AS flag "
				+ "                     FROM "+t.getTenantId()+".ftable48 "
				+ "                    WHERE grouppayer IN (SELECT grouppayer "
				+ "                                           FROM "+t.getTenantId()+".ftable48 "
				+ "                                           WHERE custno = '"+custno+"' "
				+ "                                          )) a "
				+ "         LEFT JOIN "
				+ "         "+t.getTenantId()+".fcustmst b ON a.custno = b.custno "
				+ "         LEFT JOIN "+t.getTenantId()+".fcustsls c ON a.custno = c.custno "
				+ "         LEFT JOIN "+t.getTenantId()+".fsls s ON c.slsno = s.slsno "
				+ " ORDER BY flag DESC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for(Object[]o: obj) {
			ValidasiKtpOutletGroupPayerDto data = new ValidasiKtpOutletGroupPayerDto((String)o[0], (String)o[1], (String)o[3]);
			result.add(data);
		}
		return result;
	}

	@Transactional
	@Override
	public void updateValidasiKTP(String pemilik, String telpon, String ktp, String ktpName, String alamat1,
			String alamat2, String npwp, String npwpName, String custno) {
		String sql = "UPDATE "+t.getTenantId()+".FKONFIRMASI_KTP    SET NAMA_PEMILIK = '"+pemilik+"',       TLP = '"+telpon+"', NIK = '"+ktp+"', "
				+ "    NAMA_KTP = '"+ktpName+"', ALAMAT_KTP = '"+alamat1+"', ALAMAT_KTP2 = '"+alamat2+"', NPWP = '"+npwp+"',"
				+ "NAMA_NPWP = '"+npwpName+"'  WHERE custno = '"+custno+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public boolean cekNpwp() {
		String sql = "select * from "+t.getTenantId()+".ftable13 where xkey='XNPWPBLANK'";
		Query query = entityManager.createNativeQuery(sql);
		if(query.getResultList().size()==0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean cekIsExistOutletApproveByGroupPayer(String custno) {
		String sql = " SELECT DISTINCT GROUPPAYER  "
				+ "          FROM "+t.getTenantId()+".FTABLE48  "
				+ "          WHERE GROUPPAYER IN (SELECT GROUPPAYER FROM "+t.getTenantId()+".FTABLE48 WHERE CUSTNO = '"+custno+"')  "
				+ "          AND FLAG_KTP = '1' ";
		Query query = entityManager.createNativeQuery(sql);
		if(query.getResultList().size()>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	@Transactional
	public void rejectValidasiKTP(String userLogin, String custno) {
		String sql = " UPDATE "+t.getTenantId()+".FKONFIRMASI_KTP SET FLAG_KTP = '2',  "
				+ "                  USER_REJECT = '"+userLogin+"',  "
				+ "                  DATE_REJECT = (SELECT TO_CHAR(memodate,'DD MON YYYY') as tglgudang FROM "+t.getTenantId()+".FMEMO WHERE MEMONAMA = 'CADATE'),  "
				+ "                  DATESYS_REJECT = (select TO_CHAR(sysdate,'DD MON YYYY') as tglsystem from dual ),  "
				+ "                  TIMESYS_REJECT = (select TO_CHAR(sysdate,'HH24:MI:SS') as jamsystem from dual)  "
				+ "                  WHERE CUSTNO IN (SELECT CUSTNO FROM "+t.getTenantId()+".FTABLE48  "
				+ "                                  WHERE GROUPPAYER IN (SELECT GROUPPAYER  "
				+ "                                       FROM "+t.getTenantId()+".FTABLE48 WHERE CUSTNO = '"+custno+"'))";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
		String sql2 = " UPDATE "+t.getTenantId()+".FTABLE48 SET FLAG_KTP = '2'  "
				+ "                   WHERE CUSTNO IN (SELECT CUSTNO FROM "+t.getTenantId()+".FTABLE48  "
				+ "                                   WHERE GROUPPAYER IN (SELECT GROUPPAYER  "
				+ "                                        FROM "+t.getTenantId()+".FTABLE48 WHERE CUSTNO = '"+custno+"') )";
		Query query2 = entityManager.createNativeQuery(sql2);
		query2.executeUpdate();
	}

	@Override
	@Transactional
	public void revalidate(String userLogin, String custno) {
		String sql = " UPDATE "+t.getTenantId()+".FKONFIRMASI_KTP SET FLAG_KTP = '0'  "
				+ "                  WHERE CUSTNO IN (SELECT CUSTNO FROM "+t.getTenantId()+".FTABLE48  "
				+ "                                  WHERE GROUPPAYER IN (SELECT GROUPPAYER  "
				+ "                                       FROM "+t.getTenantId()+".FTABLE48 WHERE CUSTNO = '"+custno+"'))";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
		String sql2 = " UPDATE "+t.getTenantId()+".FTABLE48 SET FLAG_KTP = '0'  "
				+ "                   WHERE CUSTNO IN (SELECT CUSTNO FROM "+t.getTenantId()+".FTABLE48  "
				+ "                                   WHERE GROUPPAYER IN (SELECT GROUPPAYER  "
				+ "                                        FROM "+t.getTenantId()+".FTABLE48 WHERE CUSTNO = '"+custno+"') )";
		Query query2 = entityManager.createNativeQuery(sql2);
		query2.executeUpdate();
	
	}

	@Override
	public boolean cekIsExistPassword() {
		String sql = "SELECT ID, nama FROM "+t.getTenantId()+".fpassword    WHERE ID = 'RKTP'";
		Query query = entityManager.createNativeQuery(sql);
		if(query.getResultList().size()>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean cekIsPassword(String pass) {
		String sql = "SELECT ID, nama FROM "+t.getTenantId()+".fpassword  WHERE ID = 'RKTP' AND nama = '"+pass+"' ";
		Query query = entityManager.createNativeQuery(sql);
		if(query.getResultList().size()>0) {
			return true;
		}else {
			return false;
		}

	}

	@Override
	public List<String> infoApproveMoreThanOneOutletInGroupPayer(List<String> outlet) {
		List<String> result = new ArrayList<>();
		String sql = "SELECT a.custno, a.grouppayer, a.ID      "
				+ "               FROM (SELECT custno, grouppayer,    "
				+ "                            ROW_NUMBER () OVER (PARTITION BY grouppayer ORDER BY grouppayer) AS ID   "
				+ "                       FROM "+t.getTenantId()+".ftable48   "
				+ "                      WHERE custno IN (:outlet)) a  "
				+ "             WHERE a.ID > 1 ";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("outlet", outlet);
		List<Object[]> obj = query.getResultList();
		for(Object[]o:obj) {
			result.add("Dalam 1 group payer "+ (String)o[1] + " ada lebih dari 1 outlet yang akan di approve \n");
		}
		return result;
	}

	@Override
	public String getDocno() {
		String sql = "select to_char((TO_NUMBER(NVL(memostring,0))+1)) docno from "+t.getTenantId()+".ftable13 where xkey='NO_ATTOUT_3'";
		Query query = entityManager.createNativeQuery(sql);
		if(query.getResultList().size()>0) {
			return ((String)query.getResultList().get(0));
		}else {
			return "0";
		}
	}

	@Override
	public double getToleransiAtt() {
		String sql = " SELECT MEMOINT FROM "+t.getTenantId()+".FTABLE13 WHERE XKEY = 'XTOLERANSIATTRIBUTE3' ";
		Query query = entityManager.createNativeQuery(sql);
		if(query.getResultList().size()==0) {
			return 0;
		}else {
			return ((BigDecimal)query.getResultList().get(0)).doubleValue();
		}
	}

	@Override
	public boolean cekIsExistDocno(String docno) {
		String sql = "SELECT * FROM "+t.getTenantId()+".FTABLE118_H WHERE DOCNO = '"+docno+"'";
		Query query = entityManager.createNativeQuery(sql);
		if(query.getResultList().size()==0) {
			return false;
		}else {
			return true;
		}
	}

	@Transactional
	@Override
	public void approveHeader(String docno, String tglGudang, String jamSys, String tglSys, String user) {
		String sql = " UPDATE "+t.getTenantId()+".FTABLE13 SET MEMOSTRING='"+docno+"' "
				+ "         WHERE XKEY='NO_ATTOUT_3' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
		String sql2=" INSERT INTO "+t.getTenantId()+".FTABLE118_H (DOCNO,DATE_CREATED,USER_CREATED,DATE_PROCESS_MAX)  "
				+ "    VALUES ( "
				+ "    '"+docno+"', "
				+ "    '"+tglGudang+"', "
				+ "    '"+user+"', "
				+ "    (select TO_CHAR(min(cdate),'DD MON YYYY') as tglGudangAtt from "+t.getTenantId()+".fcycle3 where workflag = 'Y' "
				+ "    and cdate > (select memodate + (SELECT MEMOINT FROM "+t.getTenantId()+".FTABLE13 WHERE XKEY = 'XTOLERANSIATTRIBUTE3') "
				+ "	   from "+t.getTenantId()+".fmemo where memonama = 'CADATE') ))";
		Query query2 = entityManager.createNativeQuery(sql2);
		query2.executeUpdate();
	}

	@Override
	public ValidasiKTPOutletApproveDto getDetailOutletApprove(String custno) {
		ValidasiKTPOutletApproveDto result = new ValidasiKTPOutletApproveDto();
		String sql = "SELECT distinct a.custno, b.custname, a.nama_pemilik, a.tlp, a.nik, a.nama_ktp, "
				+ "       a.alamat_ktp, a.alamat_ktp2, a.npwp, a.nama_npwp, a.alamat_npwp, "
				+ "       a.alamat_npwp2, b.custadd1, b.custadd2, b.taxname, b.npwp AS npwplast, "
				+ "       b.taxadd1, b.taxadd2, b.cphone1, c.noktp AS ktplast, "
				+ "       b.ccontact AS nama_pemiliklast "
				+ "  FROM "+t.getTenantId()+".fkonfirmasi_ktp a LEFT JOIN "+t.getTenantId()+".fcustmst b ON a.custno = b.custno "
				+ "       LEFT JOIN "+t.getTenantId()+".ftable48 c ON a.custno = c.custno "
				+ " WHERE a.custno = '"+custno+"'";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for(Object[]o: obj) {
			result = new ValidasiKTPOutletApproveDto((String)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],
					(String)o[6], (String)o[7], (String)o[8], (String)o[9], (String)o[10], (String)o[11], (String)o[12], (String)o[13], (String)o[14], (String)o[15],
					(String)o[16], (String)o[17], (String)o[18], (String)o[19], (String)o[20]);
		}
		return result;
	}

	@Transactional
	@Override
	public void approveDetail(ValidasiKTPOutletApproveDto d, String docno,  String user, String tglGudang, String jamSys, String tglSys) {
//		for(ValidasiKTPOutletApproveDto d: data) {
			String sql = " INSERT INTO "+t.getTenantId()+".FTABLE118_D (DOCNO,CUSTNO,CUSTNAME_NEW,CUSTADD_NEW,NPWP_NEW,NPWPNAME_NEW,NPWPADD1_NEW,NPWPADD2_NEW, "
					+ " PEMILIK_NEW,TELP_NEW, CUSTNAME_LAST,CUSTADD_LAST,NPWP_LAST,NPWPNAME_LAST,NPWPADD1_LAST,NPWPADD2_LAST,PEMILIK_LAST,TELP_LAST, "
					+ " NOKTP_LAST,NOKTP_NEW,NAMAKTP_NEW,ALAMATKTP1_NEW,ALAMATKTP2_NEW,NAMAPEMILIK_NEW)  "
					+ " VALUES ( '"+docno+"','"+d.getCustno()+"','"+d.getCustname()+"', "
					+ "'"+d.getCustAdd1()+"','"+d.getNpwp()+"','"+d.getNamaNpwp()+"','"+d.getAlamatNpwp()+"','"+d.getAlamatNpwp2()+"','"+d.getNamaPemilikLast()+"',"
					+ "'"+d.getTlp()+"','"+d.getCustname()+"', "
					+ "'"+d.getCustAdd1()+"','"+d.getNpwpLast()+"','"+d.getTaxName()+"','"+d.getTaxAdd1()+"','"+d.getTaxAdd2()+"','"+d.getNamaPemilikLast()+"','"+d.getcPhone1()+"', "
					+ "'"+d.getKtpLast()+"','"+d.getNik()+"','"+d.getNamaKtp()+"','"+d.getAlamatKtp()+"','"+d.getAlamatKtp2()+"','"+d.getNamaPemilik()+"')";
			Query query = entityManager.createNativeQuery(sql);
			query.executeUpdate();
			
			String sql2 = " UPDATE "+t.getTenantId()+".FTABLE48 SET FLAG_KTP = '1',     "
					+ " NAMA_KTP = '"+d.getNamaKtp()+"',     "
					+ " ALAMAT_KTP = '"+d.getAlamatKtp()+"',     "
					+ " ALAMAT_KTP2 = '"+d.getAlamatKtp2()+"',     "
					+ " NAMA_PEMILIK = '"+d.getNamaPemilik()+"'  "
					+ " WHERE CUSTNO IN (SELECT CUSTNO FROM "+t.getTenantId()+".FTABLE48                  "
					+ " WHERE GROUPPAYER IN (SELECT GROUPPAYER FROM "+t.getTenantId()+".FTABLE48 WHERE CUSTNO = '"+d.getCustno()+"'))";
			Query query2 = entityManager.createNativeQuery(sql2);
			query2.executeUpdate();
			
			String sql3 = " UPDATE "+t.getTenantId()+".FKONFIRMASI_KTP SET FLAG_KTP = '1',  USER_APPROVE = '"+user+"',  DATE_APPROVE = '"+tglGudang+"',  DATESYS_APPROVE = "
					+ "'"+tglSys+"',  TIMESYS_APPROVE = '"+jamSys+"'  WHERE CUSTNO = '"+d.getCustno()+"'";
			Query query3 = entityManager.createNativeQuery(sql3);
			query3.executeUpdate();
//		}
	}

	@Override
	public List<SelectItem<String>> getCustnoByFilter(String statusValidasi, String slsOpt, List<String> salesmans,
			String slsFrom, String slsTo, String custFrom, String custTo, String valDateFrom, String valDateTo,
			String kplDateFrom, String kplDateTo) {
		List<SelectItem<String>> result = new ArrayList<>();
		String sql = "SELECT   a.custno, a.custno||' - '||custname "
				+ "    FROM (SELECT   custno, MAX (docdate) AS docdate "
				+ "              FROM "+t.getTenantId()+".fkonfirmasi_ktp "
				+ "          GROUP BY custno) a "
				+ "         INNER JOIN "
				+ "         "+t.getTenantId()+".fkonfirmasi_ktp b "
				+ "         ON a.custno = b.custno AND a.docdate = b.docdate "
				+ " 		left join "+t.getTenantId()+".fcustmst c on a.custno=c.custno "
				+ "   WHERE a.custno IS NOT NULL ";
		if(statusValidasi.equalsIgnoreCase("open")) {
			sql += " AND b.flag_ktp IS NULL OR b.flag_ktp = '0'";
		}else if(statusValidasi.equalsIgnoreCase("approve")) {
			sql += " AND b.flag_ktp ='1'";
		}else if(statusValidasi.equalsIgnoreCase("reject")) {
			sql += " AND b.flag_ktp ='2'";
		}
		if(slsOpt.equalsIgnoreCase("selected")&& (salesmans.size()>0)) {
			sql += " AND b.SLSNO IN (:salesmans)";
		}else if(slsOpt.equalsIgnoreCase("range")) {
			if (!slsFrom.equalsIgnoreCase("")) {
				sql +=" AND b.SLSNO >= '"+slsFrom+"' ";
			}
			if (!slsTo.equalsIgnoreCase("")) {
				sql +=" AND b.SLSNO <= '"+slsTo+"' ";
			}
		}
		if (!custFrom.equalsIgnoreCase("")) {
			sql +=" AND b.CUSTNO >= '"+custFrom+"' ";
		}
		if (!custTo.equalsIgnoreCase("")) {
			sql +=" AND b.CUSTNO <= '"+custTo+"' ";
		}
		if (!valDateFrom.equalsIgnoreCase("null")) {
			sql +=" AND b.DOCDATE >= '"+valDateFrom+"' ";
		}
		if (!valDateTo.equalsIgnoreCase("null")) {
			sql +=" AND b.DOCDATE <= '"+valDateTo+"' ";
		}
		if(!kplDateFrom.equalsIgnoreCase("null") || !kplDateTo.equalsIgnoreCase("null")) {
			sql += "  AND a.custno in (SELECT data3 FROM "+t.getTenantId()+".ftabel46 "
					+ "              WHERE DATA1 IS NOT NULL";
			if(!kplDateFrom.equalsIgnoreCase("null")) {
				sql+= " and data1 >= '"+kplDateFrom+"'";
			}
			if(!kplDateTo.equalsIgnoreCase("null")) {
				sql+= " and data1 <= '"+kplDateTo+"'";
			}
			sql+=")";
		}
		sql	+= " ORDER BY a.custno ASC ";
		Query query = entityManager.createNativeQuery(sql);
		if(slsOpt.equalsIgnoreCase("selected")&& (salesmans.size()>0)) {
			query.setParameter("salesmans", salesmans);
		}
		List <Object[]> obj = query.getResultList();
		for(Object o[] : obj){
			SelectItem<String> data = new SelectItem<String>((String)o[1], (String)o[0]);
			result.add(data);
		}
		return result;
	}

	@Override
	public int getCountCustnoByFilter(String statusValidasi, String slsOpt, List<String> salesmans, String slsFrom,
			String slsTo, String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom,
			String kplDateTo) {
		int result = 0;
		String sql = "SELECT   count(*) as jmlOutlet "
				+ "    FROM (SELECT   custno, MAX (docdate) AS docdate "
				+ "              FROM "+t.getTenantId()+".fkonfirmasi_ktp "
				+ "          GROUP BY custno) a "
				+ "         INNER JOIN "
				+ "         "+t.getTenantId()+".fkonfirmasi_ktp b "
				+ "         ON a.custno = b.custno AND a.docdate = b.docdate "
				+ "   WHERE a.custno IS NOT NULL ";
		if(statusValidasi.equalsIgnoreCase("open")) {
			sql += " AND b.flag_ktp IS NULL OR b.flag_ktp = '0'";
		}else if(statusValidasi.equalsIgnoreCase("approve")) {
			sql += " AND b.flag_ktp ='1'";
		}else if(statusValidasi.equalsIgnoreCase("reject")) {
			sql += " AND b.flag_ktp ='2'";
		}
		if(slsOpt.equalsIgnoreCase("selected")&& salesmans.size()>0) {
			sql += " AND b.SLSNO IN (:salesmans)";
		}else if(slsOpt.equalsIgnoreCase("range")) {
			if (!slsFrom.equalsIgnoreCase("")) {
				sql +=" AND b.SLSNO >= '"+slsFrom+"' ";
			}
			if (!slsTo.equalsIgnoreCase("")) {
				sql +=" AND b.SLSNO <= '"+slsTo+"' ";
			}
		}
		if (!custFrom.equalsIgnoreCase("")) {
			sql +=" AND b.CUSTNO >= '"+custFrom+"' ";
		}
		if (!custTo.equalsIgnoreCase("")) {
			sql +=" AND b.CUSTNO <= '"+custTo+"' ";
		}
		if (!valDateFrom.equalsIgnoreCase("null")) {
			sql +=" AND b.DOCDATE >= '"+valDateFrom+"' ";
		}
		if (!valDateTo.equalsIgnoreCase("null")) {
			sql +=" AND b.DOCDATE <= '"+valDateTo+"' ";
		}
		if(!kplDateFrom.equalsIgnoreCase("null") || !kplDateTo.equalsIgnoreCase("null")) {
			sql += "  AND a.custno in (SELECT data3 FROM "+t.getTenantId()+".ftabel46 "
					+ "              WHERE DATA1 IS NOT NULL";
			if(!kplDateFrom.equalsIgnoreCase("null")) {
				sql+= " and data1 >= '"+kplDateFrom+"'";
			}
			if(!kplDateTo.equalsIgnoreCase("null")) {
				sql+= " and data1 <= '"+kplDateTo+"'";
			}
			sql+=")";
		}
		sql	+= " ORDER BY a.custno ASC ";
		Query query = entityManager.createNativeQuery(sql);
		if(slsOpt.equalsIgnoreCase("selected")&& (salesmans.size()>0)) {
			query.setParameter("salesmans", salesmans);
		}
		List <Object> obj = query.getResultList();
		for(Object o : obj){
			result = ((BigDecimal) o).intValue();
		}
		return result;
	}

	@Override
	public List<SelectItem<String>> getSelectItemApprove(String slsOpt, List<String> salesmans, String slsFrom,
			String slsTo, String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom,
			String kplDateTo) {
		List<SelectItem<String>> result = new ArrayList<>();
		String sql = "SELECT DISTINCT a.custno, a.custno||' - '||b.custname custname "
				+ "           FROM "+t.getTenantId()+".fkonfirmasi_ktp a LEFT JOIN "+t.getTenantId()+".fcustmst b "
				+ "                ON a.custno = b.custno ";
		if(!kplDateFrom.equalsIgnoreCase("")|| !kplDateTo.equalsIgnoreCase("")) {
			sql+= "			  LEFT JOIN "+t.getTenantId()+".ftabel46 c "
				+ "           ON a.custno = c.data3 AND a.slsno = c.data2 ";
		}
			sql	+= "          WHERE a.custno IS NOT NULL AND flag_ktp IS NULL OR flag_ktp = '0' ";
		if(slsOpt.equalsIgnoreCase("selected")) {
			sql += " AND SLSNO IN (:salesmans)";
		}else if(slsOpt.equalsIgnoreCase("range")) {
			if (!slsFrom.equalsIgnoreCase("")) {
				sql +=" AND SLSNO >= '"+slsFrom+"' ";
			}
			if (!slsTo.equalsIgnoreCase("")) {
				sql +=" AND SLSNO <= '"+slsTo+"' ";
			}
		}
		if (!custFrom.equalsIgnoreCase("")) {
			sql +=" AND a.CUSTNO >= '"+custFrom+"' ";
		}
		if (!custTo.equalsIgnoreCase("")) {
			sql +=" AND a.CUSTNO <= '"+custTo+"' ";
		}
		if (!valDateFrom.equalsIgnoreCase("null")) {
			sql +=" AND DOCDATE >= '"+valDateFrom+"' ";
		}
		if (!valDateTo.equalsIgnoreCase("null")) {
			sql +=" AND DOCDATE <= '"+valDateTo+"' ";
		}
		if (!kplDateFrom.equalsIgnoreCase("null")) {
			sql += " and c.data1 >= '"+kplDateFrom+"'";
		}
	      
	    if (!kplDateTo.equalsIgnoreCase("null")) {
	    	sql += " and c.data1 <= '"+kplDateTo+"'";
	    }
		sql	+= "  ORDER BY a.custno ASC";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for(Object o[] : obj){
			SelectItem<String> data = new SelectItem<String>((String)o[1], (String)o[0]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ReportValidasiKTPDto> viewReport(String flagReport, String statusValidasi, String slsOpt,
			List<String> salesmans, String slsFrom, String slsTo, String custFrom, String custTo, String valDateFrom,
			String valDateTo, String custId) {
		List<ReportValidasiKTPDto> result = new ArrayList<>();
		String sql = "SELECT a.grouppayer "
				+ "    ,a.custname AS grouppayername "
				+ "    ,a.slsno "
				+ "    ,a.alamat "
				+ "    ,a.alamat2 "
				+ "    ,a.prov "
				+ "    ,a.kab "
				+ "    ,a.kec "
				+ "    ,a.kel "
				+ "    ,a.nama_pemilik "
				+ "    ,a.tlp "
				+ "    ,a.nik "
				+ "    ,a.nama_ktp "
				+ "    ,a.alamat_ktp "
				+ "    ,a.alamat_ktp2 "
				+ "    ,a.npwp "
				+ "    ,a.nama_npwp "
				+ "    ,a.foto_ktp "
				+ "    ,a.foto_npwp "
				+ "    ,a.namapemiliklast "
				+ "    ,a.tlplast "
				+ "    ,a.ktplast "
				+ "    ,a.namaktplast "
				+ "    ,a.alamatktplast1 "
				+ "    ,a.alamatktplast2 "
				+ "    ,a.npwplast "
				+ "    ,a.npwpnamelast "
				+ "    ,a.STATUS "
				+ "    ,b.custno || ' - ' || b.custname AS listoutlet "
				+ "    ,b.slsno || ' - ' || b.slsname AS salesman "
				+ "    ,CASE  "
				+ "        WHEN a.grouppayer = b.custno "
				+ "            THEN 1 "
				+ "        ELSE 0 "
				+ "        END AS flag "
				+ "    ,path_ktp "
				+ "    ,path_npwp "
				+ "    ,( "
				+ "        SELECT kd || ' - ' || comp || ' - ' || kota || ' (' || branch || ')' AS header "
				+ "        FROM ( "
				+ "            SELECT memostring AS kd "
				+ "                ,( "
				+ "                    SELECT memostring "
				+ "                    FROM "+t.getTenantId()+".fmemo "
				+ "                    WHERE memonama = 'COMPANYNAME' "
				+ "                    ) AS comp "
				+ "                ,( "
				+ "                    SELECT memostring "
				+ "                    FROM "+t.getTenantId()+".fmemo "
				+ "                    WHERE memonama = 'KOTA' "
				+ "                    ) AS kota "
				+ "                ,( "
				+ "                    SELECT memonama "
				+ "                    FROM "+t.getTenantId()+".ftable13 "
				+ "                    WHERE xkey = 'XBRANCH' "
				+ "                    ) AS branch "
				+ "            FROM "+t.getTenantId()+".fmemo "
				+ "            WHERE memonama = 'KODEDIST' "
				+ "            ) a "
				+ "        ) header, 'FORM KONFIRMASI KELENGKAPAN DATA PELANGGAN (FKKDP)' judul "
				+ "FROM ( "
				+ "    SELECT DISTINCT a.grouppayer "
				+ "        ,b.custname "
				+ "        ,a.slsno || ' - ' || s.slsname AS slsno "
				+ "        ,b.custadd1 alamat "
				+ "        ,b.custadd2 alamat2 "
				+ "        ,c.h11 || ' - ' || prov.ket prov "
				+ "        ,c.h12 || ' - ' || kab.ket kab "
				+ "        ,c.h13 || ' - ' || kec.ket kec "
				+ "        ,c.h14 || ' - ' || kel.ket kel "
				+ "        ,a.nama_pemilik "
				+ "        ,a.tlp AS tlp "
				+ "        ,a.nik "
				+ "        ,a.nama_ktp "
				+ "        ,a.alamat_ktp "
				+ "        ,a.alamat_ktp2 "
				+ "        ,a.npwp "
				+ "        ,a.nama_npwp "
				+ "        ,a.foto_ktp "
				+ "        ,a.foto_npwp "
				+ "        ,CASE  "
				+ "            WHEN a.flag_ktp = '1' "
				+ "                THEN att.pemilik_last "
				+ "            ELSE LAST.nama_pemiliklast "
				+ "            END AS namapemiliklast "
				+ "        ,CASE  "
				+ "            WHEN a.flag_ktp = '1' "
				+ "                THEN att.telp_last "
				+ "            ELSE LAST.cphone1 "
				+ "            END AS tlplast "
				+ "        ,CASE  "
				+ "            WHEN a.flag_ktp = '1' "
				+ "                THEN att.noktp_last "
				+ "            ELSE LAST.ktplast "
				+ "            END AS ktplast "
				+ "        ,CASE  "
				+ "            WHEN a.flag_ktp = '1' "
				+ "                THEN TO_NCHAR(att.npwpname_last) "
				+ "            ELSE TO_NCHAR(LAST.taxname) "
				+ "            END AS namaktplast "
				+ "        ,CASE  "
				+ "            WHEN a.flag_ktp = '1' "
				+ "                THEN att.npwpadd1_last "
				+ "            ELSE LAST.taxadd1 "
				+ "            END AS alamatktplast1 "
				+ "        ,CASE  "
				+ "            WHEN a.flag_ktp = '1' "
				+ "                THEN att.npwpadd2_last "
				+ "            ELSE LAST.taxadd2 "
				+ "            END AS alamatktplast2 "
				+ "        ,CASE  "
				+ "            WHEN a.flag_ktp = '1' "
				+ "                THEN att.npwp_last "
				+ "            ELSE LAST.npwplast "
				+ "            END AS npwplast "
				+ "        ,CASE  "
				+ "            WHEN a.flag_ktp = '1' "
				+ "                THEN TO_NCHAR(att.npwpname_last) "
				+ "            ELSE LAST.taxname "
				+ "            END AS npwpnamelast "
				+ "        ,CASE  "
				+ "            WHEN a.flag_ktp = '0' "
				+ "                OR a.flag_ktp IS NULL "
				+ "                THEN 'OPEN' "
				+ "            WHEN a.flag_ktp = '1' "
				+ "                THEN 'APPROVE' "
				+ "            WHEN a.flag_ktp = '2' "
				+ "                THEN 'REJECT' "
				+ "            END AS STATUS "
				+ "        ,( "
				+ "            SELECT memostring "
				+ "            FROM "+t.getTenantId()+".ftable13 "
				+ "            WHERE xkey = 'XPHOTOKTP' "
				+ "            ) || '\\' || a.foto_ktp AS path_ktp "
				+ "        ,( "
				+ "            SELECT memostring "
				+ "            FROM "+t.getTenantId()+".ftable13 "
				+ "            WHERE xkey = 'XPHOTOKTP' "
				+ "            ) || '\\' || a.foto_npwp AS path_npwp "
				+ "    FROM ( "
				+ "        SELECT a.custno "
				+ "            ,b.grouppayer "
				+ "            ,MAX(docdate) AS docdate "
				+ "        FROM "+t.getTenantId()+".fkonfirmasi_ktp a "
				+ "        LEFT JOIN "+t.getTenantId()+".ftable48 b ON a.custno = b.custno ";
		if(flagReport.equalsIgnoreCase("outlet")) {
			 sql+= "        WHERE a.custno = '"+custId+"' ";
		}
			 sql+= "        GROUP BY a.custno "
				+ "            ,b.grouppayer "
				+ "        ) konf "
				+ "    LEFT JOIN "+t.getTenantId()+".fkonfirmasi_ktp a ON ( "
				+ "            konf.custno = a.custno "
				+ "            AND konf.docdate = a.docdate "
				+ "            ) "
				+ "    LEFT JOIN "+t.getTenantId()+".fcustmst b ON a.grouppayer = b.custno "
				+ "    LEFT JOIN "+t.getTenantId()+".fsls s ON a.slsno = s.slsno "
				+ "    LEFT JOIN "+t.getTenantId()+".fcustud c ON a.grouppayer = c.custno "
				+ "    LEFT JOIN "+t.getTenantId()+".fcshir11 prov ON (c.h11 = prov.t11) "
				+ "    LEFT JOIN "+t.getTenantId()+".fcshir12 kab ON (c.h11 || c.h12 = kab.t11 || kab.t12) "
				+ "    LEFT JOIN "+t.getTenantId()+".fcshir13 kec ON (c.h11 || c.h12 || c.h13 = kec.t11 || kec.t12 || kec.t13) "
				+ "    LEFT JOIN "+t.getTenantId()+".fcshir14 kel ON (c.h11 || c.h12 || c.h13 || c.h14 = kel.t11 || kel.t12 || kel.t13 || kel.t14) "
				+ "    LEFT JOIN ( "
				+ "        SELECT b.custno "
				+ "            ,b.custname "
				+ "            ,b.custadd1 "
				+ "            ,b.custadd2 "
				+ "            ,b.taxname "
				+ "            ,b.npwp AS npwplast "
				+ "            ,b.taxadd1 "
				+ "            ,b.taxadd2 "
				+ "            ,b.cphone1 "
				+ "            ,c.noktp AS ktplast "
				+ "            ,c.nama_pemilik AS nama_pemiliklast "
				+ "        FROM "+t.getTenantId()+".fcustmst b "
				+ "        LEFT JOIN "+t.getTenantId()+".ftable48 c ON b.custno = c.custno "
				+ "        ) LAST ON a.grouppayer = LAST.custno "
				+ "    LEFT JOIN ( "
				+ "        SELECT a.custno "
				+ "            ,custname_last "
				+ "            ,custadd_last "
				+ "            ,npwp_last "
				+ "            ,npwpname_last "
				+ "            ,TO_NCHAR(npwpadd1_last) AS npwpadd1_last "
				+ "            ,TO_NCHAR(npwpadd2_last) AS npwpadd2_last "
				+ "            ,pemilik_last "
				+ "            ,telp_last "
				+ "            ,noktp_last "
				+ "        FROM "+t.getTenantId()+".ftable118_d a "
				+ "        INNER JOIN ( "
				+ "            SELECT MAX(docno) docno "
				+ "                ,custno "
				+ "            FROM "+t.getTenantId()+".ftable118_d "
				+ "            GROUP BY custno "
				+ "            ) b ON a.docno = b.docno "
				+ "            AND a.custno = b.custno "
				+ "        ) att ON a.grouppayer = att.custno "
				+ "    WHERE a.grouppayer IS NOT NULL ";
			 if(flagReport.equalsIgnoreCase("outlet")) {
				 sql+="   AND a.custno = '"+custId+"' ";
			 }else {
				 if(statusValidasi.equalsIgnoreCase("open")) {
					 sql += " AND a.flag_ktp IS NULL OR a.flag_ktp = '0' ";
				 }else if(statusValidasi.equalsIgnoreCase("approve")) {
					 sql += " AND a.flag_ktp = '1' ";
				 }else if(statusValidasi.equalsIgnoreCase("reject")) {
					 sql += " AND a.flag_ktp = '2' ";
				 }
				 if(slsOpt.equalsIgnoreCase("selected") && (salesmans.size()>0)) {
					 sql += " AND a.SLSNO IN (:salesmans) ";
				 }else if(slsOpt.equalsIgnoreCase("range")) {
					 if (!slsFrom.equalsIgnoreCase("")) {
			            sql+=" AND a.SLSNO >= '"+slsFrom+"' ";
					 }
			         if (!slsTo.equalsIgnoreCase("")) {
			            sql+=" AND a.SLSNO <= '"+slsTo+"' ";
			         }
				 }
				 if(!custFrom.equalsIgnoreCase("")) {
					 sql+=" AND a.CUSTNO >= '"+custFrom+"' ";
				 }
				 if(!custTo.equalsIgnoreCase("")) {
					 sql+=" AND a.CUSTNO <= '"+custTo+"' ";
				 }
				 if(!valDateFrom.equalsIgnoreCase("null")) {
					 sql+=" AND a.DOCDATE >= '"+valDateFrom+"' ";
				 }
				 if(!valDateTo.equalsIgnoreCase("null")) {
					 sql+=" AND a.DOCDATE <= '"+valDateTo+"' ";
				 }
			 }
			sql+= "    ORDER BY grouppayer "
				+ "    ) a "
				+ "LEFT JOIN ( "
				+ "    SELECT DISTINCT grouppayer "
				+ "        ,tmp.custno "
				+ "        ,tmp.custname "
				+ "        ,csls.slsno "
				+ "        ,sls.slsname "
				+ "    FROM ( "
				+ "        SELECT a.grouppayer "
				+ "            ,a.custno "
				+ "            ,b.custname "
				+ "        FROM ( "
				+ "            SELECT custno "
				+ "                ,grouppayer "
				+ "                ,CASE  "
				+ "                    WHEN custno = grouppayer "
				+ "                        THEN 1 "
				+ "                    ELSE 0 "
				+ "                    END AS flag "
				+ "            FROM "+t.getTenantId()+".ftable48 "
//				+ "            WHERE grouppayer IN ('7200817') "
				+ "            ) a "
				+ "        LEFT JOIN "+t.getTenantId()+".fcustmst b ON a.custno = b.custno "
				+ "        ORDER BY flag DESC "
				+ "        ) tmp "
				+ "    LEFT JOIN "+t.getTenantId()+".fcustsls csls ON tmp.custno = csls.custno "
				+ "    LEFT JOIN "+t.getTenantId()+".fsls sls ON csls.slsno = sls.slsno "
				+ "    ) b ON a.grouppayer = b.grouppayer "
				+ "ORDER BY a.grouppayer "
				+ "    ,flag DESC ";
			Query query = entityManager.createNativeQuery(sql);
			 if(slsOpt.equalsIgnoreCase("selected") && (salesmans.size()>0)) {
				 query.setParameter("salesmans", salesmans);
			 }
			List<Object[]> obj = query.getResultList();
			for(Object[] o: obj) {
				ReportValidasiKTPDto data = new ReportValidasiKTPDto(Objects.toString(o[0], ""), Objects.toString(o[1], ""), Objects.toString(o[2], ""),
						Objects.toString(o[3], ""), Objects.toString(o[4], ""), Objects.toString(o[5], ""), Objects.toString(o[6], ""),
						Objects.toString(o[7], ""), Objects.toString(o[8], ""), Objects.toString(o[9], ""), Objects.toString(o[10], ""),
						Objects.toString(o[11], ""), Objects.toString(o[12], ""), Objects.toString(o[13], ""), Objects.toString(o[14], ""),
						Objects.toString(o[15], ""), Objects.toString(o[16], ""), Objects.toString(o[17], ""), Objects.toString(o[18], ""),
						Objects.toString(o[19], ""), Objects.toString(o[20], ""), Objects.toString(o[21], ""), Objects.toString(o[22], ""),
						Objects.toString(o[23], ""), Objects.toString(o[24], ""), Objects.toString(o[25], ""), Objects.toString(o[26], ""),
						Objects.toString(o[27], ""), Objects.toString(o[28], ""), Objects.toString(o[29], ""), new BigDecimal(Objects.toString(o[30], "0.0")),
						Objects.toString(o[31], ""), Objects.toString(o[32], ""), Objects.toString(o[33], ""), Objects.toString(o[34], ""));
				result.add(data);
			}
		return result;
	}

	@Override
	public List<String> validasiGroupPayerHasBeenApproved(List<String> outlet) {
		List<String> result = new ArrayList<>();
		for(String o: outlet) {
			String sql = "SELECT DISTINCT GROUPPAYER FROM "+t.getTenantId()+".FTABLE48    WHERE GROUPPAYER IN                                                   \r\n"
					+ "(SELECT GROUPPAYER FROM "+t.getTenantId()+".FTABLE48 WHERE CUSTNO = '"+o+"')    AND FLAG_KTP = '1' ";
			Query query = entityManager.createNativeQuery(sql);
//			query.setParameter("outlet", outlet);
			List<String> obj = query.getResultList();
			for(String ob:obj) {
				result.add("Group Payer "+ ob + " atas outlet "+o+" sudah di approve \n");
			}
		}
		if(result.size()>0) {
			result.add("\n Apakah akan lanjut proses data?");
		}
		return result;
	}

}
