package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.MappingOtorisasiValueDTO;
import myor.matrix.master.repository.MappingOtorisasiValueRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MappingOtorisasiValueRepositoryImpl implements MappingOtorisasiValueRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Boolean cekTableMapping() {
		// TODO Auto-generated method stub
		Boolean result = true;
		
		try {
			String sql = "SELECT * FROM "+t.getTenantId()+".fmapping_otorisasi_value ";
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> resultList = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		
		return result;
	}
	
	@Transactional
	@Override
	public void createTableMapping() {
		// TODO Auto-generated method stub
		String sql = " CREATE TABLE "+t.getTenantId()+".fmapping_otorisasi_value "
			+" ( "
			+"   pejabat_id   NUMBER, "
			+"   pejabat_name   VARCHAR2(20), "
			+"   team   VARCHAR2(10), "
			+"   nilai_from  NUMBER, "
			+"   nilai_to  NUMBER, "
			+"   CONSTRAINT fmapping_otorisasi_value_PK PRIMARY KEY (pejabat_id, team) "
			+" ) ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<MappingOtorisasiValueDTO> getDataMapping() {
		// TODO Auto-generated method stub
		List<MappingOtorisasiValueDTO> result = new ArrayList<>();
		String sql = "SELECT nilai_from, nilai_to, " 
			+"		LTRIM(MAX(SYS_CONNECT_BY_PATH(team, ', ' )), ', ') as team, "
			+"		TO_CHAR(pejabat_id) pejabat_id, pejabat_name "
			+"	FROM ( "
			+"		SELECT nilai_from, nilai_to, team, pejabat_id, pejabat_name, "
			+"			ROW_NUMBER() OVER (PARTITION BY pejabat_id, nilai_from, nilai_to ORDER BY team) rn "
			+"		FROM "+t.getTenantId()+".fmapping_otorisasi_value "
			+"	) "
			+"	START WITH rn = 1 "
			+"	CONNECT BY PRIOR rn = rn-1 "
			+"		AND PRIOR pejabat_name = pejabat_name "
			+"		AND PRIOR nilai_from = nilai_from "
			+"		AND PRIOR nilai_to = nilai_to "
			+"	GROUP BY pejabat_name, pejabat_id, nilai_from, nilai_to "
			+"	ORDER BY pejabat_id, team ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			MappingOtorisasiValueDTO data = new MappingOtorisasiValueDTO( ((BigDecimal) obj[0]).doubleValue(), 
					((BigDecimal) obj[1]).doubleValue(), (String) obj[2], (String) obj[3], (String) obj[4]);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public void deleteMapping(String pejabatId, List<String> teams) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM "+t.getTenantId()+".fmapping_otorisasi_value "
				+"	WHERE pejabat_id = '"+pejabatId+"' "
				+"		AND team IN (:teams) ";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("teams", teams);
		query.executeUpdate();
	}

	@Override
	public void insertDataMapping(Integer nilaiFrom, Integer nilaiTo, String team, String pejabatId, String pejabatName) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+".fmapping_otorisasi_value(pejabat_id, pejabat_name, team, "
				+"		nilai_from, nilai_to) "
				+"	VALUES( "
				+"		'"+pejabatId+"', "
				+"		'"+pejabatName+"', "
				+"		'"+team+"', "
				+"		"+nilaiFrom+", "
				+"		"+nilaiTo+" "
				+"	)";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

}
