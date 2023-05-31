package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ProductFocusBrowseTahunDto;
import myor.matrix.master.entity.ProductFocusDetailDto;
import myor.matrix.master.entity.ProductFocusDetailStockDto;
import myor.matrix.master.entity.ProductFocusHeaderDto;
import myor.matrix.master.entity.ProductFocusMinOrderDto;
import myor.matrix.master.repository.ProductFocusRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ProductFocusRepositoryImpl implements ProductFocusRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ProductFocusBrowseTahunDto> getBrowseFocusSFA() {
		// TODO Auto-generated method stub
		List<ProductFocusBrowseTahunDto> result = new ArrayList<>();
		
//		String sql = " SELECT DISTINCT tahun, LPAD (TO_NUMBER (periode), 2, '0') periode FROM "+t.getTenantId()+".ftable20 ORDER BY tahun DESC, periode DESC ";
		String sql = " SELECT DISTINCT tahun, TO_NUMBER (periode) periode FROM "+t.getTenantId()+".ftable20 ORDER BY tahun DESC, periode DESC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProductFocusBrowseTahunDto data = new ProductFocusBrowseTahunDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""));
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<ProductFocusBrowseTahunDto> getBrowseFocusStock() {
		// TODO Auto-generated method stub		
		List<ProductFocusBrowseTahunDto> result = new ArrayList<>();
		
//		String sql = " SELECT DISTINCT tahun, LPAD (TO_NUMBER (periode), 2, '0') periode FROM "+t.getTenantId()+".ftable209 ORDER BY tahun DESC, periode DESC ";
		String sql = " SELECT DISTINCT tahun, TO_NUMBER (periode) periode FROM "+t.getTenantId()+".ftable209 ORDER BY tahun DESC, periode DESC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProductFocusBrowseTahunDto data = new ProductFocusBrowseTahunDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""));
			
			result.add(data);
		}
		
		return result;		
	}

	@Override
	public ProductFocusHeaderDto getMaxFocusSFA() {
		// TODO Auto-generated method stub
		ProductFocusHeaderDto result = new ProductFocusHeaderDto();
		
		String sql = " SELECT SUBSTR (DATA, 1, 4) tahun, SUBSTR (DATA, 5, 2) periode "
					+"   FROM (SELECT MAX (tahun || TO_NUMBER (periode)) DATA FROM "+t.getTenantId()+".ftable20) ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			ProductFocusHeaderDto data = new ProductFocusHeaderDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""));
			
			result = data;
		}

		return result;
	}

	@Override
	public ProductFocusHeaderDto getMaxFocusStock() {
		// TODO Auto-generated method stub
		ProductFocusHeaderDto result = new ProductFocusHeaderDto();
		
		String sql = " SELECT SUBSTR (DATA, 1, 4) tahun, SUBSTR (DATA, 5, 2) periode "
					+"   FROM (SELECT MAX (tahun || TO_NUMBER (periode)) DATA FROM "+t.getTenantId()+".ftable209) ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			ProductFocusHeaderDto data = new ProductFocusHeaderDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""));
			
			result = data;
		}

		return result;
	}

	@Override
	public List<ProductFocusDetailDto> getDetailFocusSFA(String tahun, String periode) {
		// TODO Auto-generated method stub
		List<ProductFocusDetailDto> result = new ArrayList<>();
		
		String sql = " SELECT   a.pcode, b.pcodename "
					+"     FROM "+t.getTenantId()+".ftable20 a INNER JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode "
					+"    WHERE tahun = '"+tahun+"' AND TO_NUMBER (periode) = '"+periode+"' "
					+" ORDER BY a.pcode ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProductFocusDetailDto data = new ProductFocusDetailDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""));
			result.add(data);
		}

		return result;
	}

	@Override
	public List<ProductFocusDetailStockDto> getDetailFocusStock(String tahun, String periode) {
		// TODO Auto-generated method stub
		List<ProductFocusDetailStockDto> result = new ArrayList<>();
		
		String sql = " SELECT DISTINCT a.kd_slsfc kodesalesforce, a.kd_slsfc || ' - ' || c.data2 salesforce, "
					+"                 a.kd_team kodeteam, a.kd_team || ' - ' || d.teamname team, "
					+"                 a.kd_channel kodechannel, a.kd_channel || ' - ' || e.typename channel, "
					+" 				   a.pcode, b.pcodename, TO_CHAR (tglawal, 'DD MON YYYY') tglawal, "
					+"                 TO_CHAR (tglakhir, 'DD MON YYYY') tglakhir "
					+"            FROM "+t.getTenantId()+".ftable209 a LEFT JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode "
					+"                 LEFT JOIN "+t.getTenantId()+".ftabel10 c ON a.kd_slsfc = c.data1 "
					+"                 LEFT JOIN "+t.getTenantId()+".fteam d ON a.kd_team = d.team "
					+"                 LEFT JOIN "+t.getTenantId()+".ftypeout e ON a.kd_channel = e.TYPE "
					+"           WHERE a.tahun = '"+tahun+"' AND a.periode = '"+periode+"' "
					+"        ORDER BY salesforce, team, channel, a.pcode ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProductFocusDetailStockDto data = new ProductFocusDetailStockDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""), Objects.toString(obj[9], ""));		
			
			result.add(data);
		}

		return result;
	}

	@Override
	public List<ProductFocusMinOrderDto> getDataMinOrder() {
		// TODO Auto-generated method stub
		List<ProductFocusMinOrderDto> result = new ArrayList<>();
		
		String sql = " SELECT   a.pcode, b.pcodename, TO_CHAR (a.tglawal, 'DD MON YYYY') tglawal, "
					+"          TO_CHAR (a.tglakhir, 'DD MON YYYY') tglakhir, "
					+"          NVL (a.minorder, 0) minorder "
					+"     FROM "+t.getTenantId()+".fmaster_npl a LEFT JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode "
					+" ORDER BY a.pcode ASC ";
		Query query = entityManager.createNativeQuery(sql);
	
		List<Object[]> hasil = query.getResultList();
	
		for (Object[] obj : hasil) {
			ProductFocusMinOrderDto data = new ProductFocusMinOrderDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Integer.parseInt(Objects.toString(obj[4], "")));
			
			result.add(data);
		}
	
		return result;
	}

	@Override
	public boolean isFocusSFAExist(String tahun, String periode, String pcode) {
		// TODO Auto-generated method stub
		boolean result = false;	
		
		String sql = " SELECT * FROM "+t.getTenantId()+".ftable20 "  
					+"  WHERE pcode = '"+pcode+"' AND tahun = '"+tahun+"' AND TO_NUMBER (periode) = '"+periode+"' ";
		Query query = entityManager.createNativeQuery(sql);	
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			result = true;
		}
		
		return result;
	}

	@Override
	public void insertFocusSFA(String tahun, String periode, String tipe, String data, String pcode) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".ftable20 (tahun, periode, tipe, data, pcode) VALUES ('"+tahun+"','"+periode+"','"+tipe+"','"+data+"','"+pcode+"') ";
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}

	@Override
	public boolean isFocusStockExist(String tahun, String periode, String pcode, String salesforce, String team,
			String channel, String tglAwal, String tglAkhir) {
		// TODO Auto-generated method stub
		boolean result = false;	
		
		String sql = " SELECT * FROM "+t.getTenantId()+".ftable209 "  
					+"  WHERE pcode = '"+pcode+"' "
					+" 	  AND tahun = '"+tahun+"' "
					+" 	  AND TO_NUMBER (periode) = '"+periode+"' "
					+"	  AND kd_slsfc = '"+salesforce+"' "
					+"    AND kd_team = '"+team+"' "
					+"    AND kd_channel = '"+channel+"' "
					+"    AND tglawal = '"+tglAwal+"' "
					+" 	  AND tglakhir = '"+tglAkhir+"' ";
		Query query = entityManager.createNativeQuery(sql);	
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			result = true;
		}
		
		return result;
	}

	@Override
	public void insertFocusStock(String tahun, String periode, String tipe, String data, String pcode,
			String salesforce, String team, String channel, String tglAwal, String tglAkhir, String flag) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".ftable209 (tahun, periode, tipe, data, pcode, kd_slsfc, kd_team, kd_channel, tglawal, tglakhir, flag) "
					+" VALUES ( "
					+" '"+tahun+"' "
					+",'"+periode+"' "
					+",'"+tipe+"' "
					+",'"+data+"' "
					+",'"+pcode+"' "
					+",'"+salesforce+"' "
					+",'"+team+"' "
					+",'"+channel+"' "
					+",'"+tglAwal+"' "
					+",'"+tglAkhir+"' "
					+",'"+flag+"' "
					+" ) ";
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}

	@Override
	public void deleteFocusSFA(String tahun, String periode, String pcode) {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM "+t.getTenantId()+".ftable20 WHERE tahun = '"+tahun+"' AND TO_NUMBER (periode) = '"+periode+"' AND pcode = '"+pcode+"' ";
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}

	@Override
	public void deleteFocusStock(String tahun, String periode, String pcode, String salesforce, String team,
			String channel, String tglAwal, String tglAkhir) {
		// TODO Auto-generated method stub
//		String sql = " DELETE FROM "+t.getTenantId()+".ftable209 WHERE tahun = '"+tahun+"' AND TO_NUMBER (periode) = '"+periode+"' AND pcode = '"+pcode+"' ";
		String sql = " DELETE FROM "+t.getTenantId()+".ftable209 "
					+"  WHERE tahun = '"+tahun+"' "
					+"    AND periode = '"+periode+"' "
					+"    AND pcode = '"+pcode+"' "
					+"    AND kd_slsfc = '"+salesforce+"' "
					+"    AND kd_team = '"+team+"' "
					+"    AND kd_channel = '"+channel+"' "
					+"    AND tglawal = '"+tglAwal+"' "
					+"    AND tglakhir = '"+tglAkhir+"' "; 
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}

	@Override
	public boolean isMinOrderExist(String pcode) {
		// TODO Auto-generated method stub
		boolean result = false;	
		
		String sql = " SELECT * FROM "+t.getTenantId()+".fmaster_npl WHERE pcode = '"+pcode+"' ";
		Query query = entityManager.createNativeQuery(sql);	
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			result = true;
		}
		
		return result;
	}

	@Override
	public void insertMinOrder(String pcode, String tglAwal, String tglAkhir, String minOrder, String userId) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".fmaster_npl (pcode, tglawal, tglakhir, minorder, upddate, userupdate) "
					+"    SELECT '"+pcode+"' pcode, '"+tglAwal+"' tglawal, '"+tglAkhir+"' tglakhir, "+minOrder+" minorder, " 
					+"           (SELECT TO_CHAR (memodate, 'DD MON YYYY') tgl FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') upddate, '"+userId+"' userid "
					+"      FROM DUAL ";
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}

	@Override
	public void updateMinOrder(String pcode, String tglAwal, String tglAkhir, String minOrder, String userId) {
		// TODO Auto-generated method stub
		String sql = " UPDATE "+t.getTenantId()+".fmaster_npl "
					+"    SET  tglawal = '"+tglAwal+"' "
					+"        ,tglakhir = '"+tglAkhir+"' "
					+"        ,minorder = "+minOrder+" "
					+"        ,upddate = (SELECT TO_CHAR (memodate, 'DD MON YYYY') tgl FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') "
					+"        ,userupdate = '"+userId+"' "
					+"  WHERE pcode = '"+pcode+"' ";
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}

	@Override
	public void deleteMinOrder(String pcode) {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM "+t.getTenantId()+".fmaster_npl WHERE pcode = '"+pcode+"' ";
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}
	
	@Override
	public void deleteTempFocusSFA(String userId) {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM "+t.getTenantId()+".tmpmtx_focus_sfa WHERE userid = '"+userId+"' ";
		Query query = entityManager.createNativeQuery(sql);
		
		query.executeUpdate();
	}

	@Override
	public void insertTempFocusSFA(String userId, Integer noBaris, String tahun, String periode, String pcode) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".tmpmtx_focus_sfa (userid, nobaris, tahun, periode, pcode) VALUES "
					+" ( "
					+" '"+userId+"' "
					+" ,"+noBaris+" "
					+" ,'"+tahun+"' "
					+" ,'"+periode+"' "
					+" ,'"+pcode+"' "
					+" )";
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}

	@Override
	public List<String> validasiUploadFocusSFA(String userId) {
		// TODO Auto-generated method stub
		String sql = " SELECT message "
					+"     FROM (SELECT nobaris, 'Baris ' || nobaris || ' : Ada data yang kosong' message "
					+"             FROM "+t.getTenantId()+".tmpmtx_focus_sfa "
					+"            WHERE userid = '"+userId+"' "
					+"              AND (tahun IS NULL OR tahun = '' OR periode IS NULL OR periode = '' OR pcode IS NULL OR pcode = '') "
					+"           UNION ALL "
					+"           SELECT a.nobaris, 'Baris ' || a.nobaris || ' : Kode Produk tidak ada di Master' message "
					+"             FROM "+t.getTenantId()+".tmpmtx_focus_sfa a LEFT JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode "
					+"            WHERE a.userid = '"+userId+"' AND a.pcode IS NOT NULL AND b.pcode IS NULL "
					+"           UNION ALL "
					+"           SELECT a.nobaris, 'Baris ' || a.nobaris || ' : Kode Produk sudah ada' message "
					+"             FROM "+t.getTenantId()+".tmpmtx_focus_sfa a INNER JOIN "+t.getTenantId()+".ftable20 b "
					+"               ON a.tahun = b.tahun AND TO_NUMBER(a.periode) = TO_NUMBER(b.periode) AND a.pcode = b.pcode "
					+"            WHERE a.userid = '"+userId+"') "
					+" ORDER BY nobaris ASC ";
		Query query = entityManager.createNativeQuery(sql);		
		
		List<String> result = query.getResultList(); 

		return result;
	}

	@Override
	public void insertFocusSFAUpload(String userId) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".ftable20 (tahun, periode, tipe, DATA, pcode) "
					+"    SELECT tahun, TO_NUMBER (periode) periode, 'Semua' tipe, 'DBEDATA' DATA, pcode " 
					+"      FROM "+t.getTenantId()+".tmpmtx_focus_sfa "
					+"     WHERE userid = '"+userId+"' ";
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}

	@Override
	public String getStartWeek() {
		// TODO Auto-generated method stub
		String startWeek = "";
		
		String sql = " SELECT TO_CHAR (tglawal_15, 'DD MON YYYY') tglawalweek "
				+"   FROM (SELECT MIN (startweek) tglawal_15 "
				+"           FROM (SELECT ROWNUM AS nomer, a.* "
				+"                   FROM (SELECT   a.* "
				+"                             FROM (SELECT a.* "
				+"                                     FROM "+t.getTenantId()+".fcycle2 a "
				+"                                          INNER JOIN "
				+"                                          (SELECT ROWNUM AS nomer, a.* "
				+"                                             FROM (SELECT a.* FROM "+t.getTenantId()+".fcycle3 a "
				+"                                                      WHERE cdate < "
				+"                                                               (SELECT MIN (a.cdate) tglawal "
				+"                                                                  FROM "+t.getTenantId()+".fcycle3 a "
				+"                                                                       INNER JOIN "
				+"                                                                       (SELECT * FROM "+t.getTenantId()+".fcycle3 "
				+"                                                                         WHERE cdate = (SELECT memodate FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE')) b "
				+"                                                                       ON a.tahun = b.tahun AND b.prdno = a.prdno AND a.weekno = b.weekno) "
				+"                                                   ORDER BY cdate DESC) a) b "
				+"                                          ON a.tahun = b.tahun AND b.prdno = a.prdno AND a.weekno = b.weekno AND b.cdate = a.endweek) a "
				+"                         ORDER BY startweek DESC) a) a "
				+"          WHERE nomer <= '13') ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object> result = query.getResultList();		
		if(result.size() > 0) {
			startWeek = Objects.toString(result.get(0), "");
		}
		
		return startWeek;
	}

	@Override
	public String getEndWeek() {
		// TODO Auto-generated method stub
		String endWeek = "";
		
		String sql = " SELECT TO_CHAR (startweek - 1, 'DD MON YYYY') memodate "
				+"   FROM "+t.getTenantId()+".fcycle2 a JOIN "+t.getTenantId()+".fcycle3 b ON a.tahun = b.tahun AND a.prdno = b.prdno AND a.weekno = b.weekno "
				+"  WHERE b.cdate = (SELECT memodate FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') ";		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object> result = query.getResultList();		
		if(result.size() > 0) {
			endWeek = Objects.toString(result.get(0), "");
		}
		
		return endWeek;
	}

	@Override
	public void insertFocusStockAuto(String tahun, String periode, String tipe, String data, String salesforce,
			String team, String channel, String tglAwal, String tglAkhir, String flag, String startWeek,
			String endWeek) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".ftable209 (tahun, periode, tipe, DATA, pcode, kd_slsfc, kd_team, kd_channel, tglawal, tglakhir, flag) "
					+"    SELECT DISTINCT '"+tahun+"' tahun, '"+periode+"' periode, '"+tipe+"' tipe, '"+data+"' DATA, pcode, '"+salesforce+"' salesforce, '"+team+"' team, '"+channel+"' channel, "
					+"                    '"+tglAwal+"' awal, '"+tglAkhir+"' akhir, '"+flag+"' flag "
					+"               FROM (SELECT a.custno, c.pcode, b.invdate, d.pcodename, c.qty, c.amount, d.unit1, d.unit2, d.unit3, d.convunit2, "
					+"                            d.convunit3, fc.typeout, fs.team, fs.data04 "
					+"                       FROM "+t.getTenantId()+".fcustsls a INNER JOIN "+t.getTenantId()+".fjual_h b ON a.custno = b.custno "
					+"                            INNER JOIN "+t.getTenantId()+".fjual_d1 c ON b.orderno = c.orderno AND b.slsno = c.slsno AND b.transtype = c.transtype "
					+"                            INNER JOIN "+t.getTenantId()+".fmaster d ON c.pcode = d.pcode "
					+"                            LEFT JOIN "+t.getTenantId()+".fcustmst fc ON b.custno = fc.custno "
					+"                            LEFT JOIN "+t.getTenantId()+".fsls fs ON b.slsno = fs.slsno "
					+"                      WHERE b.transtype = 'F' "
					+"                        AND b.invno IS NOT NULL "
					+"                        AND b.invdate >= '"+startWeek+"' "
					+"                        AND b.invdate <= '"+endWeek+"' "
					+"                        AND fc.typeout = '"+channel+"' "
					+"                        AND fs.team = '"+team+"' "
					+"                        AND fs.data04 = '"+salesforce+"') "
					+"              WHERE pcode NOT IN ( "
					+"                       SELECT pcode FROM "+t.getTenantId()+".ftable209 "
					+"                        WHERE tahun = '"+tahun+"' "
					+"                          AND periode = '"+periode+"' "					
					+"                          AND kd_slsfc = '"+salesforce+"' "
					+"                          AND kd_team = '"+team+"' "
					+"                          AND kd_channel = '"+channel+"' "
					+"                          AND tglawal = '"+tglAwal+"' "
					+"                          AND tglakhir = '"+tglAkhir+"') ";
		Query query = entityManager.createNativeQuery(sql);		
		
		query.executeUpdate();
	}

	@Override
	public boolean cekInsertStockAuto(String tahun, String periode, String tipe, String data, String salesforce,
			String team, String channel, String tglAwal, String tglAkhir, String flag, String startWeek,
			String endWeek) {
		// TODO Auto-generated method stub
		boolean result = false;	
		
		String sql = "    SELECT DISTINCT '"+tahun+"' tahun, '"+periode+"' periode, '"+tipe+"' tipe, '"+data+"' DATA, pcode, '"+salesforce+"' salesforce, '"+team+"' team, '"+channel+"' channel, "
					+"                    '"+tglAwal+"' awal, '"+tglAkhir+"' akhir, '"+flag+"' flag "
					+"               FROM (SELECT a.custno, c.pcode, b.invdate, d.pcodename, c.qty, c.amount, d.unit1, d.unit2, d.unit3, d.convunit2, "
					+"                            d.convunit3, fc.typeout, fs.team, fs.data04 "
					+"                       FROM "+t.getTenantId()+".fcustsls a INNER JOIN "+t.getTenantId()+".fjual_h b ON a.custno = b.custno "
					+"                            INNER JOIN "+t.getTenantId()+".fjual_d1 c ON b.orderno = c.orderno AND b.slsno = c.slsno AND b.transtype = c.transtype "
					+"                            INNER JOIN "+t.getTenantId()+".fmaster d ON c.pcode = d.pcode "
					+"                            LEFT JOIN "+t.getTenantId()+".fcustmst fc ON b.custno = fc.custno "
					+"                            LEFT JOIN "+t.getTenantId()+".fsls fs ON b.slsno = fs.slsno "
					+"                      WHERE b.transtype = 'F' "
					+"                        AND b.invno IS NOT NULL "
					+"                        AND b.invdate >= '"+startWeek+"' "
					+"                        AND b.invdate <= '"+endWeek+"' "
					+"                        AND fc.typeout = '"+channel+"' "
					+"                        AND fs.team = '"+team+"' "
					+"                        AND fs.data04 = '"+salesforce+"') "
					+"              WHERE pcode NOT IN ( "
					+"                       SELECT pcode FROM "+t.getTenantId()+".ftable209 "
					+"                        WHERE tahun = '"+tahun+"' "
					+"                          AND periode = '"+periode+"' "					
					+"                          AND kd_slsfc = '"+salesforce+"' "
					+"                          AND kd_team = '"+team+"' "
					+"                          AND kd_channel = '"+channel+"' "
					+"                          AND tglawal = '"+tglAwal+"' "
					+"                          AND tglakhir = '"+tglAkhir+"') ";
		Query query = entityManager.createNativeQuery(sql);	
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			result = true;
		}
		
		return result;
	}

	
}
