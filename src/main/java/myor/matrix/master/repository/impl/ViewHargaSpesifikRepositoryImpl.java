package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ProductBrowserHargaSpesifikDto;
import myor.matrix.master.entity.ViewHargaSpesifikDto;
import myor.matrix.master.repository.ViewHargaSpesifikRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ViewHargaSpesifikRepositoryImpl implements ViewHargaSpesifikRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ProductBrowserHargaSpesifikDto> getListHargaSpesifik() {
		// TODO Auto-generated method stub
		List<ProductBrowserHargaSpesifikDto> result = new ArrayList<>();
		String sql = "SELECT "
				+ "	t14.data1 pcode, "
				+ "	m.pcodename AS pcodename, "
				+ "	t14.data3 code, "
				+ "	DECODE (t14.data2,'100', "
				+ "		c.custname,'200', "
				+ "		g.grupname,'300', "
				+ "		t10.data2,'400', "
				+ "		t.typename,'500', "
				+ "		h.ket,'ALL' ) kodename, "
				+ "	to_char(t14.data4, 'dd MON yyyy') start_date, "
				+ "	COALESCE(t14.data5,0) toleransi, "
				+ "	t14.data2 TYPE, "
				+ "	t14.data6 sell1, "
				+ "	t14.data7 sell2, "
				+ "	t14.data8 sell3, "
				+ "	to_char(t14.data9, 'dd MON yyyy') end_date, "
				+ "	g.grupname, "
				+ "	t10.data2 sforname, "
				+ "	t.typename , "
				+ "	CASE WHEN h.ket IS NULL THEN '-'  "
				+ "	ELSE h.ket END AS gruphrgname, "
				+ "	CASE WHEN t14.data2 IS NULL THEN '-' "
				+ "	ELSE  "
				+ "	DECODE (t14.data2, "
				+ "	'100', "
				+ "	'100.OUTLET', "
				+ "	'200', "
				+ "	'200.GRUPOUTLET', "
				+ "	'300', "
				+ "	'300.SFORCE', "
				+ "	'400', "
				+ "	'400.CHANNEL', "
				+ "	'500', "
				+ "	'500.GRUPHARGA', "
				+ "	'ALL' ) END AS kettipe, "
				+ "	 c.custname "
				+ "FROM "
				+ "	"+t.getTenantId()+".ftabel14 t14 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fmaster m ON "
				+ "	t14.data1 = m.pcode "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fcustmst c ON "
				+ "	t14.data3 = c.custno "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftypeout t ON "
				+ "	t14.data3 = t.TYPE "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fgrupout g ON "
				+ "	t14.data3 = g.grupout "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftabel10 t10 ON "
				+ "	t14.data3 = t10.data1 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fgharga h ON "
				+ "	t14.data3 = h.gharga "
				+ "ORDER BY "
				+ "	T14.DATA4, "
				+ "	T14.DATA2";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) { 
			ProductBrowserHargaSpesifikDto data = new ProductBrowserHargaSpesifikDto(
					(String)obj[0],
					(String)obj[1],
					(String)obj[2],
					(String)obj[3],
					(String)obj[4],
					((BigDecimal)obj[5]).intValue(),
					(String)obj[6],
					((BigDecimal) obj[7]).doubleValue(),
					((BigDecimal) obj[8]).doubleValue(),
					((BigDecimal) obj[9]).doubleValue(),
					(String)obj[10],
					(String)obj[11],
					(String)obj[12],
					(String)obj[13],
					(String)obj[14],
					(String)obj[15],
					(String)obj[16]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTgl() {
		// TODO Auto-generated method stub
		List<ProductBrowserHargaSpesifikDto> result = new ArrayList<>();
		String sql = "SELECT "
				+ "	t14.data1 pcode, "
				+ "	m.pcodename AS pcodename, "
				+ "	t14.data3 code, "
				+ "	DECODE (t14.data2,'100', "
				+ "		c.custname,'200', "
				+ "		g.grupname,'300', "
				+ "		t10.data2,'400', "
				+ "		t.typename,'500', "
				+ "		h.ket,'ALL' ) kodename, "
				+ "	to_char(t14.data4, 'dd MON yyyy') start_date, "
				+ "	COALESCE(t14.data5,0) toleransi, "
				+ "	t14.data2 TYPE, "
				+ "	t14.data6 sell1, "
				+ "	t14.data7 sell2, "
				+ "	t14.data8 sell3, "
				+ "	to_char(t14.data9, 'dd MON yyyy') end_date, "
				+ "	g.grupname, "
				+ "	t10.data2 sforname, "
				+ "	t.typename , "
				+ "	CASE WHEN h.ket IS NULL THEN '-'  "
				+ "	ELSE h.ket END AS gruphrgname, "
				+ "	CASE WHEN t14.data2 IS NULL THEN '-' "
				+ "	ELSE  "
				+ "	DECODE (t14.data2, "
				+ "	'100', "
				+ "	'100.OUTLET', "
				+ "	'200', "
				+ "	'200.GRUPOUTLET', "
				+ "	'300', "
				+ "	'300.SFORCE', "
				+ "	'400', "
				+ "	'400.CHANNEL', "
				+ "	'500', "
				+ "	'500.GRUPHARGA', "
				+ "	'ALL' ) END AS kettipe, "
				+ "	 c.custname "
				+ "FROM "
				+ "	"+t.getTenantId()+".ftabel14 t14 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fmaster m ON "
				+ "	t14.data1 = m.pcode "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fcustmst c ON "
				+ "	t14.data3 = c.custno "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftypeout t ON "
				+ "	t14.data3 = t.TYPE "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fgrupout g ON "
				+ "	t14.data3 = g.grupout "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftabel10 t10 ON "
				+ "	t14.data3 = t10.data1 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fgharga h ON "
				+ "	t14.data3 = h.gharga "
				+ "ORDER BY "
				+ "	T14.DATA4, "
				+ "	T14.DATA2";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) { 
			ProductBrowserHargaSpesifikDto data = new ProductBrowserHargaSpesifikDto(
					(String)obj[0],
					(String)obj[1],
					(String)obj[2],
					(String)obj[3],
					(String)obj[4],
					((BigDecimal)obj[5]).intValue(),
					(String)obj[6],
					((BigDecimal) obj[7]).doubleValue(),
					((BigDecimal) obj[8]).doubleValue(),
					((BigDecimal) obj[9]).doubleValue(),
					(String)obj[10],
					(String)obj[11],
					(String)obj[12],
					(String)obj[13],
					(String)obj[14],
					(String)obj[15],
					(String)obj[16]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTipe() {
		// TODO Auto-generated method stub
		List<ProductBrowserHargaSpesifikDto> result = new ArrayList<>();
		String sql = "SELECT "
				+ "	t14.data1 pcode, "
				+ "	m.pcodename AS pcodename, "
				+ "	t14.data3 code, "
				+ "	DECODE (t14.data2,'100', "
				+ "		c.custname,'200', "
				+ "		g.grupname,'300', "
				+ "		t10.data2,'400', "
				+ "		t.typename,'500', "
				+ "		h.ket,'ALL' ) kodename, "
				+ "	to_char(t14.data4, 'dd MON yyyy') start_date, "
				+ "	COALESCE(t14.data5,0) toleransi, "
				+ "	t14.data2 TYPE, "
				+ "	t14.data6 sell1, "
				+ "	t14.data7 sell2, "
				+ "	t14.data8 sell3, "
				+ "	to_char(t14.data9, 'dd MON yyyy') end_date, "
				+ "	g.grupname, "
				+ "	t10.data2 sforname, "
				+ "	t.typename , "
				+ "	CASE WHEN h.ket IS NULL THEN '-'  "
				+ "	ELSE h.ket END AS gruphrgname, "
				+ "	CASE WHEN t14.data2 IS NULL THEN '-' "
				+ "	ELSE  "
				+ "	DECODE (t14.data2, "
				+ "	'100', "
				+ "	'100.OUTLET', "
				+ "	'200', "
				+ "	'200.GRUPOUTLET', "
				+ "	'300', "
				+ "	'300.SFORCE', "
				+ "	'400', "
				+ "	'400.CHANNEL', "
				+ "	'500', "
				+ "	'500.GRUPHARGA', "
				+ "	'ALL' ) END AS kettipe, "
				+ "	 c.custname "
				+ "FROM "
				+ "	"+t.getTenantId()+".ftabel14 t14 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fmaster m ON "
				+ "	t14.data1 = m.pcode "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fcustmst c ON "
				+ "	t14.data3 = c.custno "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftypeout t ON "
				+ "	t14.data3 = t.TYPE "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fgrupout g ON "
				+ "	t14.data3 = g.grupout "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftabel10 t10 ON "
				+ "	t14.data3 = t10.data1 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fgharga h ON "
				+ "	t14.data3 = h.gharga "
				+ "ORDER BY "
				+ "	T14.DATA2, "
				+ "	T14.DATA4";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) { 
			ProductBrowserHargaSpesifikDto data = new ProductBrowserHargaSpesifikDto(
					(String)obj[0],
					(String)obj[1],
					(String)obj[2],
					(String)obj[3],
					(String)obj[4],
					((BigDecimal)obj[5]).intValue(),
					(String)obj[6],
					((BigDecimal) obj[7]).doubleValue(),
					((BigDecimal) obj[8]).doubleValue(),
					((BigDecimal) obj[9]).doubleValue(),
					(String)obj[10],
					(String)obj[11],
					(String)obj[12],
					(String)obj[13],
					(String)obj[14],
					(String)obj[15],
					(String)obj[16]);
			result.add(data);
		}
		return result;
	}

}
