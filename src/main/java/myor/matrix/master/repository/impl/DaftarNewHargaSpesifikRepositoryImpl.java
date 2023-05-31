package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.DaftarNewHargaSpesifikDto;
import myor.matrix.master.repository.DaftarNewHargaSpesifikRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class DaftarNewHargaSpesifikRepositoryImpl implements DaftarNewHargaSpesifikRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<DaftarNewHargaSpesifikDto> getReport(String umbrellaFrom, String umbrellaTo, String brandFrom,
			String brandTo, String subBrandFrom, String subBrandTo, String pcodeFrom, String pcodeTo,
			String tglBerlakuFrom, String tglBerlakuTo, String pilihanData, String sortingData) {
		// TODO Auto-generated method stub
		List<DaftarNewHargaSpesifikDto> result = new ArrayList<>();
		
		String sql = " SELECT   'Daftar Harga Spesifik Baru' "
					+"          || ' - ' || (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') "
					+"          || ' - ' || (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') header, "
					+"          (SELECT TO_CHAR (SYSDATE, 'DD MON YYYY') tglsistem FROM DUAL) tglsistem, "
					+"          (SELECT TO_CHAR (memodate, 'DD MON YYYY') tglgudang FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') tglgudang, "
					+"          (SELECT TO_CHAR (xno) release FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') release, ";
		
		if (tglBerlakuFrom.equalsIgnoreCase("") && tglBerlakuTo.equalsIgnoreCase("")) {
			sql += " 'Semua' tglberlaku, ";
		} else {
			sql += " '" + tglBerlakuFrom + " s/d " + tglBerlakuTo + "' tglberlaku, ";
		}
		
		if (umbrellaFrom.equalsIgnoreCase("") && umbrellaTo.equalsIgnoreCase("")) {
			sql += " 'Semua' umbrella, ";
		} else {
			sql += " '" + umbrellaFrom + " s/d " + umbrellaTo + "' umbrella, ";
		}
		
		if (brandFrom.equalsIgnoreCase("") && brandTo.equalsIgnoreCase("")) {
			sql += " 'Semua' brand, ";
		} else {
			sql += " '" + brandFrom + " s/d " + brandTo + "' brand, ";
		}
		
		if (subBrandFrom.equalsIgnoreCase("") && subBrandTo.equalsIgnoreCase("")) {
			sql += " 'Semua' subbrand, ";
		} else {
			sql += " '" + subBrandFrom + " s/d " + subBrandTo + "' subbrand, ";
		}
		
		if (pcodeFrom.equalsIgnoreCase("") && pcodeTo.equalsIgnoreCase("")) {
			sql += " 'Semua' produk, ";
		} else {
			sql += " '" + pcodeFrom + " s/d " + pcodeTo + "' produk, ";
		}					
					 
					
		sql += "          a.data1 pcode, m.pcodename, a.data2 tipe, "
		  	  +"          DECODE (a.data2, "
		  	  +"                  '100', 'CSTOMER', "
		  	  +"                  '200', 'GR.CUST', "
		  	  +"                  '300', 'SLFORCE', "
		  	  +"                  '400', 'CHANNEL', "
		  	  +"                  '500', 'GRP.PRC', "
		  	  +"                  '600', 'ALL', "
		  	  +"                  NULL) nmtipe, "
		  	  +"          DECODE (a.data3, '*', 'ALL', a.data3) data, "
		  	  +"          DECODE (a.data2, "
		  	  +"                  '100', SUBSTR ((SELECT custname FROM "+t.getTenantId()+".fcustmst WHERE custno = a.data3), 0, 16), "
		  	  +"                  '200', SUBSTR ((SELECT grupname FROM "+t.getTenantId()+".fgrupout WHERE grupout = a.data3), 0, 16), "
		  	  +"                  '300', SUBSTR ((SELECT data2 FROM "+t.getTenantId()+".ftabel10 WHERE data1 = a.data3), 0, 16), "
		  	  +"                  '400', SUBSTR ((SELECT typename FROM "+t.getTenantId()+".ftypeout WHERE TYPE = a.data3), 0, 16), "
		  	  +"                  '500', SUBSTR ((SELECT ket FROM "+t.getTenantId()+".fgharga WHERE gharga = a.data3), 0, 16), "
		  	  +"                  '600', 'SEMUA', "
		  	  +"                  NULL) nama, "
		  	  +"          TO_CHAR (a.data4, 'DD-MM-YYYY') tglmulai, "
		  	  +"          TO_CHAR (a.data9, 'DD-MM-YYYY') tglakhir, a.data5 toleransi, "
		  	  +"          a.data6 hrgkcl, a.data7 hrgtgh, a.data8 hrgbsr "
		  	  +"     FROM "+t.getTenantId()+".ftabel14 a INNER JOIN "+t.getTenantId()+".fmaster m ON a.data1 = m.pcode "
		  	  +"    WHERE m.pcode IS NOT NULL ";
		
		if (pilihanData.equalsIgnoreCase("outlet")) {
			sql += " and a.DATA2 = '100' ";
		}
		
		if (pilihanData.equalsIgnoreCase("groupoutlet")) {
			sql += " and a.DATA2 = '200' ";
		}
		
		if (pilihanData.equalsIgnoreCase("salesfoce")) {
			sql += " and a.DATA2 = '300' ";
		}
		
		if (pilihanData.equalsIgnoreCase("channel")) {
			sql += " and a.DATA2 = '400' ";
		}
		
		if (pilihanData.equalsIgnoreCase("groupharga")) {
			sql += " and a.DATA2 = '500' ";
		}
		
		if (pilihanData.equalsIgnoreCase("allpcode")) {
			sql += " and a.DATA2 = '600' ";
		}
		
		if (!umbrellaFrom.equalsIgnoreCase("")) {
			sql += " AND (M.PRLIN >= '" + umbrellaFrom + "') ";
		}
		
		if (!umbrellaTo.equalsIgnoreCase("")) {
			sql += " AND (M.PRLIN <= '" + umbrellaTo + "') ";
		}
		
		if (!brandFrom.equalsIgnoreCase("")) {
			sql += " AND (M.BRAND >= '" + brandFrom + "') ";
		}
		
		if (!brandTo.equalsIgnoreCase("")) {
			sql += " AND (M.BRAND <= '" + brandTo + "') ";
		}
		
		if (!subBrandFrom.equalsIgnoreCase("")) {
			sql += " AND (M.SBRA1 >= '" + subBrandFrom + "') ";
		}
		
		if (!subBrandTo.equalsIgnoreCase("")) {
			sql += " AND (M.SBRA1 <= '" + subBrandTo + "') ";
		}
		
		if (!pcodeFrom.equalsIgnoreCase("")) {
			sql += " AND (a.data1 >= '" + pcodeFrom + "') ";
		}
		
		if (!pcodeTo.equalsIgnoreCase("")) {
			sql += " AND (a.data1 <= '" + pcodeTo + "') ";
		}
		
		if (!tglBerlakuFrom.equalsIgnoreCase("")) {
			sql += " AND (a.data4 >= '" + tglBerlakuFrom + "') ";
		}
		
		if (!tglBerlakuTo.equalsIgnoreCase("")) {
			sql += " AND (a.data9 <= '" + tglBerlakuTo + "') ";
		}
		
		if (sortingData.equalsIgnoreCase("1")) {
			sql += " ORDER BY a.data4, a.data2, a.data1 ASC ";
		} else {
			sql += " ORDER BY a.data2, a.data4, a.data1 ASC ";
		}
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			DaftarNewHargaSpesifikDto data = new DaftarNewHargaSpesifikDto((String) obj[0], (String) obj[1],
					(String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6],
					(String) obj[7], (String) obj[8], (String) obj[9], (String) obj[10], (String) obj[11],
					(String) obj[12], (String) obj[13], (String) obj[14], (String) obj[15], (String) obj[16],
					(BigDecimal) obj[17], (BigDecimal) obj[18], (BigDecimal) obj[19], (BigDecimal) obj[20]);
			
			result.add(data);
		}
		
		return result;
	}

}
