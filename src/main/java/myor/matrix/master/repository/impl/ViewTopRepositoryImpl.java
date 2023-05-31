package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ViewTopDto;
import myor.matrix.master.repository.ViewTopRepository;
import myor.matrix.master.tenant.TenantContext;


@Repository
public class ViewTopRepositoryImpl implements ViewTopRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ViewTopDto> getListTop() {
		// TODO Auto-generated method stub
		List<ViewTopDto> result = new ArrayList<>();
		String sql = "SELECT "
				+ "	a.data1, "
				+ "	DECODE (a.data1, "
				+ "	'100', "
				+ "	'100.PCODE', "
				+ "	'200', "
				+ "	'200.DIVISI') berlaku, "
				+ "	a.data2 AS product , "
				+ "	DECODE (a.data1, "
				+ "	'100', "
				+ "	(SELECT pcodename "
				+ "		FROM "+t.getTenantId()+".fmaster "
				+ "		WHERE pcode = a.data2), "
				+ "		'200', "
				+ "	(SELECT sbra2name "
				+ "		FROM "+t.getTenantId()+".ftsbrand2 "
				+ "		WHERE sbra2 = a.data2), "
				+ "		NULL ) nmberlaku, "
				+ "	a.data3, "
				+ "	DECODE  "
				+ "	(a.data3, "
				+ "	'100','100.CHANEL', "
				+ "	'200','200.OUTLET', "
				+ "	'300','300.GRUP OUTLET', "
				+ "	'400','400.SALESFORCE', "
				+ "	NULL ) tipe, "
				+ "	a.data4 AS kode, "
				+ "	DECODE (a.data3, "
				+ "	'100', "
				+ "	( SELECT typename "
				+ "	FROM "+t.getTenantId()+".ftypeout "
				+ "	WHERE TYPE = a.data4),'200', "
				+ "	( SELECT custname "
				+ "		FROM "+t.getTenantId()+".fcustmst "
				+ "		WHERE custno = a.data4), '300', "
				+ "	( SELECT grupname "
				+ "		FROM "+t.getTenantId()+".fgrupout "
				+ "		WHERE grupout = a.data4), "
				+ "	'400', "
				+ "	( SELECT data2 "
				+ "		FROM "+t.getTenantId()+".ftabel10 "
				+ "	WHERE data1 = a.data4), "
				+ "	NULL ) nama, "
				+ "	to_char(a.data5, 'dd MON yyyy') AS  "
				+ " tglberlaku, "
				+ "	to_char(a.data9, 'dd MON yyyy') AS tglberakhir, "
				+ "	DECODE (a.data6, "
				+ "	'1', 'DAY', "
				+ "	'2', 'FIXDATE', ' ')   "
				+ "tipeberlaku, "
				+ "	COALESCE(a.data7,0) AS totalday, "
				+ "	TO_CHAR(a.data8,'dd MON yyyy') AS fixdate  "
				+ "FROM "+t.getTenantId()+".ftabel22 a "
				+ "WHERE "
				+ "	a.data1 IS NOT NULL "
				+ "ORDER BY "
				+ "	a.data5, "
				+ "	a.data1";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) { 
			ViewTopDto data = new ViewTopDto(
					(String)obj[0], 
					(String)obj[1],
					(String)obj[2], 
					(String)obj[3],
					(String)obj[4], 
					(String)obj[5],
					(String)obj[6], 
					(String)obj[7],
					(String)obj[8], 
					(String)obj[9],
					(String)obj[10], 
					((BigDecimal)obj[11]).intValue(),
					(String)obj[12]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ViewTopDto> getListTopOrderByTgl() {
		// TODO Auto-generated method stub
		List<ViewTopDto> result = new ArrayList<>();
		String sql = "SELECT  "
				+ "	a.data1, "
				+ "	DECODE (a.data1, "
				+ "	'100', "
				+ "	'100.PCODE', "
				+ "	'200', "
				+ "	'200.DIVISI') berlaku, "
				+ "	a.data2 AS product , "
				+ "	DECODE (a.data1, "
				+ "	'100', "
				+ "	( "
				+ "	SELECT "
				+ "		pcodename "
				+ "	FROM "
				+ "		"+t.getTenantId()+".fmaster "
				+ "	WHERE "
				+ "		pcode = a.data2), "
				+ "	'200', "
				+ "	( "
				+ "	SELECT "
				+ "		sbra2name "
				+ "	FROM "
				+ "		"+t.getTenantId()+".ftsbrand2 "
				+ "	WHERE "
				+ "		sbra2 = a.data2), "
				+ "	NULL ) nmberlaku, "
				+ "	a.data3, "
				+ "	DECODE  "
				+ "	(a.data3, "
				+ "	'100', "
				+ "	'100.CHANEL', "
				+ "	'200', "
				+ "	'200.OUTLET', "
				+ "	'300', "
				+ "	'300.GRUP OUTLET', "
				+ "	'400', "
				+ "	'400.SALESFORCE', "
				+ "	NULL ) tipe, "
				+ "	a.data4 AS kode, "
				+ "	DECODE (a.data3, "
				+ "	'100', "
				+ "	( "
				+ "	SELECT "
				+ "		typename "
				+ "	FROM "
				+ "		"+t.getTenantId()+".ftypeout "
				+ "	WHERE "
				+ "		TYPE  "
				+ "= a.data4), "
				+ "	'200', "
				+ "	( "
				+ "	SELECT "
				+ "		custname "
				+ "	FROM "
				+ "		"+t.getTenantId()+".fcustmst "
				+ "	WHERE "
				+ "		custno = a.data4), "
				+ "	'300', "
				+ "	( "
				+ "	SELECT "
				+ "		grupname "
				+ "	FROM "
				+ "		"+t.getTenantId()+".fgrupout "
				+ "	WHERE "
				+ "		grupout = a.data4), "
				+ "	'400', "
				+ "	( "
				+ "	SELECT "
				+ "		data2 "
				+ "	FROM "
				+ "		"+t.getTenantId()+".ftabel10 "
				+ "	WHERE "
				+ "		data1 = a.data4), "
				+ "	NULL ) nama, "
				+ "	to_char(a.data5, 'dd MON yyyy') AS  "
				+ "tglberlaku, "
				+ "	to_char(a.data9, 'dd MON yyyy') AS tglberakhir, "
				+ "	DECODE (a.data6, "
				+ "	'1', "
				+ "	'DAY', "
				+ "	'2', "
				+ "	'FIXDATE', "
				+ "	' ')  tipeberlaku, "
				+ "	COALESCE(a.data7,0)AS totalday, "
				+ "	TO_CHAR(a.data8,'dd MON yyyy') AS fixdate "
				+ "FROM "
				+ "	"+t.getTenantId()+".ftabel22 a  "
				+ "ORDER BY  "
				+ "	a.data3, a.data5";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) { 
			ViewTopDto data = new ViewTopDto(
					(String)obj[0], 
					(String)obj[1],
					(String)obj[2], 
					(String)obj[3],
					(String)obj[4], 
					(String)obj[5],
					(String)obj[6], 
					(String)obj[7],
					(String)obj[8], 
					(String)obj[9],
					(String)obj[10], 
					((BigDecimal)obj[11]).intValue(),
					(String)obj[12]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ViewTopDto> getListTopOrderByTipe() {
		// TODO Auto-generated method stub
		List<ViewTopDto> result = new ArrayList<>();
		String sql = "SELECT  "
				+ "	a.data1, "
				+ "	DECODE (a.data1, "
				+ "	'100', "
				+ "	'100.PCODE', "
				+ "	'200', "
				+ "	'200.DIVISI') berlaku, "
				+ "	a.data2 AS product , "
				+ "	DECODE (a.data1, "
				+ "	'100', "
				+ "	( "
				+ "	SELECT "
				+ "		pcodename "
				+ "	FROM "
				+ "		"+t.getTenantId()+".fmaster "
				+ "	WHERE "
				+ "		pcode = a.data2), "
				+ "	'200', "
				+ "	( "
				+ "	SELECT "
				+ "		sbra2name "
				+ "	FROM "
				+ "		"+t.getTenantId()+".ftsbrand2 "
				+ "	WHERE "
				+ "		sbra2 = a.data2), "
				+ "	NULL ) nmberlaku, "
				+ "	a.data3, "
				+ "	DECODE  "
				+ "	(a.data3, "
				+ "	'100', "
				+ "	'100.CHANEL', "
				+ "	'200', "
				+ "	'200.OUTLET', "
				+ "	'300', "
				+ "	'300.GRUP OUTLET', "
				+ "	'400', "
				+ "	'400.SALESFORCE', "
				+ "	NULL ) tipe, "
				+ "	a.data4 AS kode, "
				+ "	DECODE (a.data3, "
				+ "	'100', "
				+ "	( "
				+ "	SELECT "
				+ "		typename "
				+ "	FROM "
				+ "		"+t.getTenantId()+".ftypeout "
				+ "	WHERE "
				+ "		TYPE  "
				+ "= a.data4), "
				+ "	'200', "
				+ "	( "
				+ "	SELECT "
				+ "		custname "
				+ "	FROM "
				+ "		"+t.getTenantId()+".fcustmst "
				+ "	WHERE "
				+ "		custno = a.data4), "
				+ "	'300', "
				+ "	( "
				+ "	SELECT "
				+ "		grupname "
				+ "	FROM "
				+ "		"+t.getTenantId()+".fgrupout "
				+ "	WHERE "
				+ "		grupout = a.data4), "
				+ "	'400', "
				+ "	( "
				+ "	SELECT "
				+ "		data2 "
				+ "	FROM "
				+ "		"+t.getTenantId()+".ftabel10 "
				+ "	WHERE "
				+ "		data1 = a.data4), "
				+ "	NULL ) nama, "
				+ "	to_char(a.data5, 'dd MON yyyy') AS  "
				+ "tglberlaku, "
				+ "	to_char(a.data9, 'dd MON yyyy') AS tglberakhir, "
				+ "	DECODE (a.data6, "
				+ "	'1', "
				+ "	'DAY', "
				+ "	'2', "
				+ "	'FIXDATE', "
				+ "	' ')   "
				+ "tipeberlaku, "
				+ "	COALESCE(a.data7,0)AS totalday, "
				+ "	TO_CHAR(a.data8,'dd MON yyyy') AS fixdate "
				+ "FROM "
				+ "	"+t.getTenantId()+".ftabel22 a  "
				+ "ORDER BY  "
				+ "	a.data5,a.data3 ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) { 
			ViewTopDto data = new ViewTopDto(
					(String)obj[0], 
					(String)obj[1],
					(String)obj[2], 
					(String)obj[3],
					(String)obj[4], 
					(String)obj[5],
					(String)obj[6], 
					(String)obj[7],
					(String)obj[8], 
					(String)obj[9],
					(String)obj[10], 
					((BigDecimal)obj[11]).intValue(),
					(String)obj[12]);
			result.add(data);
		}
		return result;
	}

	
}
