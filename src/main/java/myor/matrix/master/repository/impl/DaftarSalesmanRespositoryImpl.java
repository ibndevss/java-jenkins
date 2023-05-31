package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.FormatExtractDaftarSalesmanDto;
import myor.matrix.master.entity.ListAttributeDaftarSalesmanDto;
import myor.matrix.master.repository.DaftarSalesmanRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class DaftarSalesmanRespositoryImpl implements DaftarSalesmanRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ListAttributeDaftarSalesmanDto> getListAttribute() {
		// TODO Auto-generated method stub
		List<ListAttributeDaftarSalesmanDto> result = new ArrayList<>();
		String sql = " SELECT NO, NO||' - '||ATTRIBUT AS ATTRIBUT_, CHECKLIST "
				+ " FROM "+t.getTenantId()+".tmpmtx_daftar_salesman "
				+ " ORDER BY NO ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			ListAttributeDaftarSalesmanDto data = new ListAttributeDaftarSalesmanDto(
					(String) obj[0],
					(String) obj[1],
					((BigDecimal) obj[2]).intValue(),
					false);
			result.add(data);
		}
		return result;
	}

	@Override
	public int cekDataAttribute() {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(NO)id "
				+ "FROM "+t.getTenantId()+".TMPMTX_DAFTAR_SALESMAN";
		Query query = entityManager.createNativeQuery(sql);
		try {
			return Integer.parseInt(query.getSingleResult().toString());
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public void insertAttribute() {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".tmpmtx_daftar_salesman (NO, ATTRIBUT, COLUMNNAME, CHECKLIST)  "
				+ "	SELECT '01','No Urut','NO', 0 FROM DUAL  "
				+ "UNION ALL SELECT '02','Kode Sales', 'SLSNO',0 FROM DUAL "
				+ "UNION ALL SELECT '03','Nama Salesman','SLSNAME',0 FROM DUAL "
				+ "UNION ALL SELECT '04','Alamat1', 'SLSADD1',0 FROM DUAL "
				+ "UNION ALL SELECT '05','Alamat2', 'SLSADD2',0 FROM DUAL "
				+ "UNION ALL SELECT '06','Kota', 'SLSCITY',0 FROM DUAL "
				+ "UNION ALL SELECT '07','Pendidikan','PDDK',0 FROM DUAL "
				+ "UNION ALL SELECT '08','Tanggal Lahir','LAHIR',0 FROM DUAL "
				+ "UNION ALL SELECT '09','Tanggal Masuk Kerja','WORKDATE',0 FROM DUAL "
				+ "UNION ALL SELECT '10','Tipe Operasi','OPRTYPE',0 FROM DUAL "
				+ "UNION ALL SELECT '11','Team','TEAM',0 FROM DUAL "
				+ "UNION ALL SELECT '12','Nama Team','TEAMNAME',0 FROM DUAL "
				+ "UNION ALL SELECT '13','Tanggal Transaksi','TRANSDATE',0 FROM DUAL "
				+ "UNION ALL SELECT '14','Kode Gudang','KG',0 FROM DUAL "
				+ "UNION ALL SELECT '15','Nama Gudang','KGNAME',0 FROM DUAL "
				+ "UNION ALL SELECT '16','Kode Pejabat','KPEJ',0 FROM DUAL "
				+ "UNION ALL SELECT '17','Nama Pejabat','PEJNAME',0 FROM DUAL "
				+ "UNION ALL SELECT '18','No Telpon','DATA01',0 FROM DUAL "
				+ "UNION ALL SELECT '19','No Hp','DATA02',0 FROM DUAL "
				+ "UNION ALL SELECT '20','Email','DATA03',0 FROM DUAL "
				+ "UNION ALL SELECT '21','Kode Salesforce','DATA04',0 FROM DUAL "
				+ "UNION ALL SELECT '22','Nama Salesforce','sf.DATA2',0 FROM DUAL "
				+ "UNION ALL SELECT '23','Kode Rayon','DATA05',0 FROM DUAL "
				+ "UNION ALL SELECT '24','Nama Rayon','ra.DATA2',0 FROM DUAL "
				+ "UNION ALL SELECT '25','Blok','DATA06',0 FROM DUAL "
				+ "UNION ALL SELECT '26','Tempat Lahir','DATA08',0 FROM DUAL "
				+ "UNION ALL SELECT '27','Jenis Kelamin','DATA09',0 FROM DUAL "
				+ "UNION ALL SELECT '28','Status','DATA10',0 FROM DUAL "
				+ "UNION ALL SELECT '29','Akunting dan Nama Bank','DATA11',0 FROM DUAL "
				+ "UNION ALL SELECT '30','Agama','DATA12',0 FROM DUAL "
				+ "UNION ALL SELECT '31','Proses Pembuatan KPL','DATA13',0 FROM DUAL "
				+ "UNION ALL SELECT '32','Sales Divisi','KDMAPPING',0 FROM DUAL "
				+ "UNION ALL SELECT '33','Nama Sales Divisi','SBRA2NAME',0 FROM DUAL "
				+ "UNION ALL SELECT '34','Diskon Reguler','f3.KODE',0 FROM DUAL "
				+ "UNION ALL SELECT '35','Know As','KNOWNAS',0 FROM DUAL "
				+ "UNION ALL SELECT '36','Sales Employee','SLSEMPLOYEE',0 FROM DUAL ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<FormatExtractDaftarSalesmanDto> getDefaultFormat(List<String> list) {
		// TODO Auto-generated method stub
		List<FormatExtractDaftarSalesmanDto> result = new ArrayList<>();
		String sql = " SELECT NO, 'DAFTAR MASTER SALESMAN'||'|'||NO||'|' ||ATTRIBUT AS Format "
				+ " FROM "+t.getTenantId()+".TMPMTX_DAFTAR_SALESMAN "
				+ " WHERE NO in (:list) "
				+ " ORDER BY NO ";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("list", list);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj: resultList) {
			FormatExtractDaftarSalesmanDto data = new FormatExtractDaftarSalesmanDto(
					(String) obj[0],
					(String) obj[1]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<String> getColumnField(List<String> list) {
		// TODO Auto-generated method stub
		String sql = " SELECT COLUMNNAME "
				+ " FROM "+t.getTenantId()+".TMPMTX_DAFTAR_SALESMAN "
				+ " WHERE NO in (:list) "
				+ " ORDER BY NO ";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("list", list);
		return query.getResultList();
	}

	@Override
	public List<FormatExtractDaftarSalesmanDto> getSelectByColumn(List<String> datas, List<String> datasHeader, String salesFrom, String salesTo, String sforceFrom, String sforceTo, 
			String teamFrom, String teamTo, String rayonFrom, String rayonTo, String pilihData) {
		// TODO Auto-generated method stub
		List<FormatExtractDaftarSalesmanDto> result = new ArrayList<>();
		String dt = "";
		String dh = "";
		
		String diskonReg = "";
		String dtAll = "";
		
		for(String selected : datas) {
			if(selected.equals("f3.KODE")) {
				diskonReg = "f3.KODE || '|' ||";
			} else { 
				dt += selected;
			}
		}
		
		for(String selectedHeader : datasHeader) {
			dh += selectedHeader;
		}
		
		dtAll += dt + diskonReg;
		String sql = "SELECT "
				+ "'0' AS NO_, "
				+ "TO_CHAR(a.test)AS test "
				+ "FROM	  "
				+ "(SELECT  "
				+ "	"+dh+" '' AS TEST "
				+ "FROM DUAL)a "
				+ "UNION ALL "
				+ "SELECT "
				+ "a.NO_, "
				+ "TO_CHAR(a.test)AS test   "
				+ "FROM  "
				+ "(SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY s.SLSNO)) AS NO_, "
				+ " "+dtAll+" '' AS TEST "
				+ "	FROM "+t.getTenantId()+".FSLS s " 
				+ " LEFT JOIN "+t.getTenantId()+".FTEAM f ON s.TEAM = f.TEAM  "
				+ " LEFT JOIN "+t.getTenantId()+".FLOKGD g ON g.KG = s.KG "
				+ " LEFT JOIN "+t.getTenantId()+".FPEJ pj ON pj.PEJKODE = s.KPEJ "
				+ " LEFT JOIN "+t.getTenantId()+".FTABEL10 sf ON sf.DATA1 = s.DATA04 "
				+ " LEFT JOIN "+t.getTenantId()+".FTABEL11 ra ON ra.DATA1 = s.DATA05"
				+ " LEFT JOIN  "
				+ "   (SELECT f279.TEAM, f279.KDMAPPING, fsb.SBRA2NAME "
				+ "		   FROM "+t.getTenantId()+".FTABLE279 f279 "
				+ "		   LEFT JOIN "+t.getTenantId()+".FTSBRAND2 fsb ON fsb.SBRA2 = f279.KDMAPPING "
				+ "		   	WHERE TIPE = '3') f2 "
				+ "   		ON f2.TEAM = s.TEAM  ";
		if(diskonReg == "f3.KODE || '|' ||") {
			sql += "LEFT JOIN "
				+ " (SELECT DISTINCT SLSNO, KODE FROM "+t.getTenantId()+".FTABEL12)f3 "
				+ "		ON s.SLSNO = f3.SLSNO "
				+ " WHERE s.SLSNO IS NOT NULL ";
		} else {
			 sql += " WHERE s.SLSNO IS NOT NULL ";
		}
		if(!salesFrom.equalsIgnoreCase("")) {
			sql += " and s.SLSNO >= '"+salesFrom+"' ";
		}
		if(!salesTo.equalsIgnoreCase("")) {
			sql += " and s.SLSNO <= '"+salesTo+"' ";
		} 

		if(!sforceFrom.equalsIgnoreCase("")) {
			sql += "and s.DATA04 >= '"+sforceFrom+"' ";
		} 
		if(!sforceTo.equalsIgnoreCase("")) {
			sql += "and s.DATA04 <= '"+sforceTo+"' ";
		} 
		
		if(!teamFrom.equalsIgnoreCase("")) {
			sql += "and s.TEAM >= '"+teamFrom+"' ";
		} 
		if(!teamTo.equalsIgnoreCase("")) {
			sql += "and s.TEAM <= '"+teamTo+"' ";
		} 

		if(!rayonFrom.equalsIgnoreCase("")) {
			sql += "and s.DATA05 >= '"+rayonFrom+"' ";
		} 
		
		if(!rayonTo.equalsIgnoreCase("")) {
			sql += "and s.DATA05 <= '"+rayonTo+"' ";
		} 
		
		if(pilihData.equalsIgnoreCase("S")) {
			sql += "";
		}
		if(pilihData.equalsIgnoreCase("Y")) {
			sql += "and s.DATA06 = '"+pilihData+"' ";
		}
		if(pilihData.equalsIgnoreCase("T")) {
			sql += "and s.DATA06 = '"+pilihData+"' ";
		}
		
		sql += " )a ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj: resultList) {
			FormatExtractDaftarSalesmanDto data = new FormatExtractDaftarSalesmanDto(
					(String) obj[0],
					(String) obj[1]);
			result.add(data);
		}
		return result;
	}



}
