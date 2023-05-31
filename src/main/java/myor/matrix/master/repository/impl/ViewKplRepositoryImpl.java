package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ViewKplDto;
import myor.matrix.master.repository.ViewKplRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ViewKplRepositoryImpl implements ViewKplRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ViewKplDto> getDataDetailAll(String slsNo, String tgl) {
		// TODO Auto-generated method stub
		List<ViewKplDto> result = new ArrayList<>();
		String sql = "SELECT "
				+ "	t47.data3 AS Outlet, "
				+ "	c.custname AS NamaOutlet, "
				+ "	t47.data4 AS PCode, "
				+ "	m.pcodename NamaBarang , "
				+ "	t47.data5 AS LastOrder, "
				+ "	t47.data6 AS LastCall, "
				+ "	t47.data7 AS AVG4Call, "
				+ "	COALESCE(t48.data11,0)ord1, "
				+ "	COALESCE(t48.data12,0)ord2, "
				+ "	COALESCE(t48.data13,0)ord3, "
				+ "	COALESCE(t48.data14,0)ord4, "
				+ "	COALESCE(t48.ltoko,0)ltoko, "
				+ "	COALESCE(t48.jcall,0)jcall, "
				+ "	COALESCE(t48.jorder,0)jorder, "
				+ "	COALESCE(t48.data15,0)sls1, "
				+ "	COALESCE(t48.data16,0)sls2, "
				+ "	COALESCE(t48.data17,0)sls3, "
				+ "	COALESCE(t48.data18,0)sls4,"
				+ "	DECODE (t47.flag, "
				+ "	'K', "
				+ "	'KPL', "
				+ "	'NONKPL') ket "
				+ "FROM "
				+ "	"+t.getTenantId()+".ftabel47 t47 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftabel48 t48 ON "
				+ "	(t47.data3 = t48.data1) "
				+ "	AND  "
				+ "(t47.data4 = t48.data2) "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fcustmst c ON "
				+ "	t47.data3 = c.custno "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fmaster m ON "
				+ "	t47.data4 = m.pcode "
				+ "WHERE "
				+ "	t47.data2 = '"+slsNo+"' "
				+ "	AND t47.data1 = '"+tgl+"' "
				+ "ORDER BY "
				+ "	T47.DATA4, "
				+ "	T47.DATA2";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) { 
			ViewKplDto data = new ViewKplDto(
					(String) obj[0],
					(String) obj[1],
					(String) obj[2],
					(String) obj[3],
					((BigDecimal) obj[4]).intValue(),
					((BigDecimal) obj[5]).intValue(),
					((BigDecimal) obj[6]).intValue(),
					((BigDecimal) obj[7]).intValue(),
					((BigDecimal) obj[8]).intValue(),
					((BigDecimal) obj[9]).intValue(),
					((BigDecimal) obj[10]).intValue(),
					((BigDecimal) obj[11]).intValue(),
					((BigDecimal) obj[12]).intValue(),
					((BigDecimal) obj[13]).intValue(),
					((BigDecimal) obj[14]).intValue(),
					((BigDecimal) obj[15]).intValue(),
					((BigDecimal) obj[16]).intValue(),
					((BigDecimal) obj[17]).intValue(),
					(String) obj[18]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ViewKplDto> getDataDetailPcodeKpl(String slsNo, String tgl) {
		// TODO Auto-generated method stub
		List<ViewKplDto> result = new ArrayList<>();
		String sql = "SELECT "
				+ "	t47.data3 AS Outlet, "
				+ "	c.custname AS NamaOutlet, "
				+ "	t47.data4 AS PCode, "
				+ "	m.pcodename NamaBarang , "
				+ "	t47.data5 AS LastOrder, "
				+ "	t47.data6 AS LastCall, "
				+ "	t47.data7 AS AVG4Call, "
				+ "	COALESCE(t48.data11,0)ord1, "
				+ "	COALESCE(t48.data12,0)ord2, "
				+ "	COALESCE(t48.data13,0)ord3, "
				+ "	COALESCE(t48.data14,0)ord4, "
				+ "	COALESCE(t48.ltoko,0)ltoko, "
				+ "	COALESCE(t48.jcall,0)jcall, "
				+ "	COALESCE(t48.jorder,0)jorder, "
				+ "	COALESCE(t48.data15,0)sls1, "
				+ "	COALESCE(t48.data16,0)sls2, "
				+ "	COALESCE(t48.data17,0)sls3, "
				+ "	COALESCE(t48.data18,0)sls4, "
				+ "	DECODE (t47.flag, "
				+ "	'K', "
				+ "	'KPL', "
				+ "	'NONKPL') ket "
				+ "FROM "
				+ "	"+t.getTenantId()+".ftabel47 t47 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftabel48 t48 ON "
				+ "	(t47.data3 = t48.data1) "
				+ "	AND  "
				+ "(t47.data4 = t48.data2) "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fcustmst c ON "
				+ "	t47.data3 = c.custno "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fmaster m ON "
				+ "	t47.data4 = m.pcode "
				+ "WHERE "
				+ "	t47.data2 = '"+slsNo+"' "
				+ "	AND t47.data1 = '"+tgl+"' "
				+ "	AND T47.FLAG = 'K' "
				+ "ORDER BY "
				+ "	T47.DATA1, "
				+ "	T47.DATA2, "
				+ "	T47.DATA3";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) { 
			ViewKplDto data = new ViewKplDto(
					(String) obj[0],
					(String) obj[1],
					(String) obj[2],
					(String) obj[3],
					((BigDecimal) obj[4]).intValue(),
					((BigDecimal) obj[5]).intValue(),
					((BigDecimal) obj[6]).intValue(),
					((BigDecimal) obj[7]).intValue(),
					((BigDecimal) obj[8]).intValue(),
					((BigDecimal) obj[9]).intValue(),
					((BigDecimal) obj[10]).intValue(),
					((BigDecimal) obj[11]).intValue(),
					((BigDecimal) obj[12]).intValue(),
					((BigDecimal) obj[13]).intValue(),
					((BigDecimal) obj[14]).intValue(),
					((BigDecimal) obj[15]).intValue(),
					((BigDecimal) obj[16]).intValue(),
					((BigDecimal) obj[17]).intValue(),
					(String) obj[18]);
			result.add(data);
		}
		return result;
	}

	
	
}
