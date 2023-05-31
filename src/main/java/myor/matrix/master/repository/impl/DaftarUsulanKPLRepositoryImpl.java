package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.DaftarUsulanKPLDto;
import myor.matrix.master.repository.DaftarUsulanKPLRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class DaftarUsulanKPLRepositoryImpl implements DaftarUsulanKPLRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<DaftarUsulanKPLDto> getReport(String slsnoFrom, String slsnoTo, String tglFrom, String tglTo,
			String urutanData) {
		// TODO Auto-generated method stub
		
		List<DaftarUsulanKPLDto> result = new ArrayList<>();
		
		if (slsnoFrom.equalsIgnoreCase("") || slsnoFrom == null) {
			slsnoFrom = "";
		} 
		
		if (slsnoTo.equalsIgnoreCase("") || slsnoTo == null) {
			slsnoTo= "";
		} 
		
		if (tglFrom.equalsIgnoreCase("") || tglFrom == null) {
			tglFrom = "";
		} 
		
		if (tglTo.equalsIgnoreCase("") || tglTo == null) {
			tglTo = "";
		} 
		
		
		String sql = "SELECT "
					+"		TO_CHAR(H.DATA1,'DD MON YYYY') tgl,H.DATA2 slsno,SUBSTR(s.slsname, 1, 16) slsname, "
					+"		H.DATA3 keterangan,H.DATA4 userUsulan,H.DATA5 userOtomatis,d.DATA3 custno, "
					+"		decode(d.DATA4, 'U', 'USULAN', 'O', 'OTOMATIS')tipe,c.custname,cv.route, "		
					+ "	'Daftar Usulan KPL' || ' - ' || (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') || ' ' ||  "
					+ "   CASE WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
					+ "   THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
					+ "   ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') END header , "
					+"     (SELECT TO_CHAR (memodate, 'DD MON YYYY') AS tglgudang "
					+"        FROM "+t.getTenantId()+".fmemo "
					+"       WHERE memonama = 'CADATE') AS tglgudang, "
					+"     (SELECT 'Release :' || ' ' || xno AS relase "
					+"        FROM "+t.getTenantId()+".ftable13 "
					+"       WHERE xkey = 'ARWANA_UPDATE') AS release, "
					+"     (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') "
					+"        FROM DUAL) AS tglsystem, "
					+"        '"+slsnoFrom+"' AS slsFrom, '"+slsnoTo+"' AS slsTo, '"+tglFrom+"' AS tglFrom, '"+tglTo+"' AS tglTo "
					+" FROM "
					+"	"+t.getTenantId()+".FTABEL45 h "
					+" INNER JOIN "+t.getTenantId()+".FTABEL46 d  "
					+"	ON (H.DATA1 = d.DATA1 AND h.DATA2 = d.DATA2) "
					+" INNER JOIN "+t.getTenantId()+".FSLS s  "
					+"	ON (h.DATA2 = s.SLSNO) "
					+" INNER JOIN "+t.getTenantId()+".FCUSTMST c  "
					+"	ON (d.DATA3 = c.CUSTNO) "
					+" LEFT JOIN "+t.getTenantId()+".FCUSTSLS cv  "
					+"	ON (h.DATA2 = cv.slsno AND d.DATA3 = cv.custno) "
					+" WHERE "
					+"		H.DATA1 IS NOT NULL ";
		if (!slsnoFrom.equalsIgnoreCase("") || slsnoFrom != null) {
			sql +="	AND H.DATA2 >= '"+slsnoFrom+"' ";
		} 
		
		if (!slsnoTo.equalsIgnoreCase("") || slsnoTo != null) {
			sql +="	AND H.DATA2 <= '"+slsnoTo+"' ";
		} 
		
		if (!tglFrom.equalsIgnoreCase("") || tglFrom != null) {
			sql +="	AND H.DATA1 >= '"+tglFrom+"' ";
		} 
		
		if (!tglTo.equalsIgnoreCase("") || tglTo != null) {
			sql +="	AND H.DATA1 <= '"+tglTo+"' ";
		} 
					
		
		if (urutanData.equalsIgnoreCase("'0'")) {
			sql +=" ORDER BY h.data2, h.data1, cv.route, custno ";
		} else {
			sql +=" ORDER BY  h.data1, h.data2, cv.route, custno ";
		}
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();		
		for (Object[] obj : hasil) {
			DaftarUsulanKPLDto data = new DaftarUsulanKPLDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3],
					(String) obj[4], (String) obj[5], (String) obj[6], (String) obj[7], (String) obj[8], (BigDecimal) obj[9],
					(String) obj[10], (String) obj[11], (String) obj[12], (String) obj[13], (String) obj[14], (String) obj[15], (String) obj[16], (String) obj[17]);
			result.add(data);
		}
		
		return result;
	}

}
