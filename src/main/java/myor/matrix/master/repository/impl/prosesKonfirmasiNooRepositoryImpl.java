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

import myor.matrix.master.entity.ProsesKonfirmasiNooDto;
import myor.matrix.master.repository.ProsesKonfirmasiNooRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class prosesKonfirmasiNooRepositoryImpl implements ProsesKonfirmasiNooRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String getXkeyString(String table, String column, String where) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+where+"  ";
			Query query = entityManager.createNativeQuery(sql);
			result = Objects.toString(query.getSingleResult(), "");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}
	
	
	@Override
	public String getXkeyStringJoin(String table, String column, String join, String where) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+join+" "+where+"  ";
			Query query = entityManager.createNativeQuery(sql);
			result = Objects.toString(query.getSingleResult(), "");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}
	
	@Override
	public String getFromDualString( String table, String column, String where) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " select "+column+" from "+table+" "+where+"  ";
			Query query = entityManager.createNativeQuery(sql);
			result = Objects.toString(query.getSingleResult(), "");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}

	@Override
	public List<Object[]> getXkeyListObject(String table, String column, String where) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public List<Object[]> getXkeyListObjectJoin(String table, String column, String join, String where) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+join+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public List<Object[]> getListObjectQuery(String query1) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = query1;
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public void deleteData(String table, String where) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM "+t.getTenantId()+"."+table+" "+where+" ";	
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateData(String table, String values, String where) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+"."+table+" "+values+" "+where+" ";	
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void insertData(String table, String column, String value) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+"."+table+" ("+column+") values ("+value+") ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public void insertDataSelect(String table, String select, String where) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+"."+table+" "+select+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}


	@Override
	public List<ProsesKonfirmasiNooDto> getListData(String tglRegister) {
		// TODO Auto-generated method stub
		List<ProsesKonfirmasiNooDto> result = new ArrayList<>();
		
		String sql =" SELECT "
				+ "    a.custno, "
				+ "    a.custname, "
				+ "    a.climit, "
				+ "    a.cterm, "
				+ "    a.typeout | | '-' | | c.typename channel "
				+ "FROM "
				+ "    "+t.getTenantId()+".fcustmst a "
				+ "    LEFT JOIN "+t.getTenantId()+".ftable48 b ON a.custno = b.custno "
				+ "    LEFT JOIN "+t.getTenantId()+".ftypeout c ON a.typeout = c.TYPE "
				+ "WHERE "
				+ "    b.flagprocess IS NULL "
				+ "    AND a.regdate = '"+tglRegister+"' "
				+ "ORDER BY "
				+ "    custno ASC  ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProsesKonfirmasiNooDto data = new ProsesKonfirmasiNooDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""),
					new BigDecimal(Objects.toString(obj[2], "0")),
					new BigDecimal(Objects.toString(obj[3], "0")),
					Objects.toString(obj[4], ""));
			result.add(data);
		}
		return result;
	}
	
}
