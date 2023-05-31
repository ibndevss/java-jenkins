package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.tenant.TenantContext;
import myor.matrix.master.entity.DaftarTOPProdukDto;
import myor.matrix.master.repository.DaftarTOPProdukRepository;

@Repository
public class DaftarTOPProdukRepositoryImpl implements DaftarTOPProdukRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<DaftarTOPProdukDto> getReport(String pilihanData, String pilihanData2, String filterFrom,
			String filterTo) {
		// TODO Auto-generated method stub
		List<DaftarTOPProdukDto> result = new ArrayList<>();
		
		String sql = " SELECT   'Daftar Tabel TOP' "
					+"          || ' - ' || (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') "
					+"          || ' - ' || (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') header, "
					+"          (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') tglsistem FROM DUAL) tglsistem, "
					+"          (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang, "
					+"          (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release, a.data1 tipe, "
					+"          DECODE (a.data1, '100', 'PCODE', '200', 'DIVISI') berlaku, a.data2 pcdiv, "
					+"          DECODE (a.data1, "
					+"                  '100', (SELECT pcodename FROM "+t.getTenantId()+".fmaster WHERE pcode = a.data2), "
					+"                  '200', (SELECT sbra2name FROM "+t.getTenantId()+".ftsbrand2 WHERE sbra2 = a.data2), "
					+"                  NULL) nmberlaku, "
					+"          a.data3 tipetop, "
					+"          DECODE (a.data3, "
					+"                  '100', 'CHANEL', "
					+"                  '200', 'OUTLET', "
					+"                  '300', 'GR.OUT', "
					+"                  '400', 'SFORCE', "
					+"                  NULL) nmtipe, "
					+"          a.data4 DATA, "
					+"          DECODE (a.data3, "
					+"                  '100', (SELECT typename FROM "+t.getTenantId()+".ftypeout WHERE TYPE = a.data4), "
					+"                  '200', (SELECT custname FROM "+t.getTenantId()+".fcustmst WHERE custno = a.data4), "
					+"                  '300', (SELECT grupname FROM "+t.getTenantId()+".fgrupout WHERE grupout = a.data4), "
					+"                  '400', (SELECT data2 FROM "+t.getTenantId()+".ftabel10 WHERE data1 = a.data4), "
					+"                  NULL) nama, "
					+"          TO_CHAR (a.data5, 'DD-MM-YYYY') tglmulai, "
					+"          TO_CHAR (a.data9, 'DD-MM-YYYY') tglakhir, a.data6, TO_CHAR (a.data7) topday, "
					+"          TO_CHAR (a.data8, 'DD-MM-YYYY') fixdate "
					+"     FROM "+t.getTenantId()+".ftabel22 a "
					+"    WHERE a.data1 IS NOT NULL ";
		
		if (pilihanData2.equalsIgnoreCase("2")) {
			sql += " AND a.data3 = '100' "; //CHANNEL
		} else if (pilihanData2.equalsIgnoreCase("3")) {
			sql += " AND a.data3 = '200' "; //OUTLET
		} else if (pilihanData2.equalsIgnoreCase("4")) {
			sql += " AND a.data3 = '300' "; //GROUPOUTLET
		} else if (pilihanData2.equalsIgnoreCase("5")) {
			sql += " AND a.data3 = '400' "; //SALESFORCE
		}
		
		if (!filterFrom.equalsIgnoreCase("")) {
			sql += " AND a.data2 >= '"+filterFrom+"' ";
		}
		
		if (!filterTo.equalsIgnoreCase("")) {
			sql += " AND a.data2 <= '"+filterTo+"' ";
		}			
		sql += " ORDER BY a.data1 ASC, a.data2 ASC, a.data3 ASC, TO_DATE (a.data5) ASC, a.data4 ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			DaftarTOPProdukDto data = new DaftarTOPProdukDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), Objects.toString((String) obj[2], ""),
					Objects.toString((String) obj[3], ""), Objects.toString((String) obj[4], ""),
					Objects.toString((String) obj[5], ""), Objects.toString((String) obj[6], ""),
					Objects.toString((String) obj[7], ""), Objects.toString((String) obj[8], ""),
					Objects.toString((String) obj[9], ""), Objects.toString((String) obj[10], ""),
					Objects.toString((String) obj[11], ""), Objects.toString((String) obj[12], ""),
					Objects.toString((String) obj[13], ""), Objects.toString((String) obj[14], ""),
					Objects.toString((String) obj[15], ""), Objects.toString((String) obj[16], ""));
			result.add(data);
		}
		
		return result;
	}

}
