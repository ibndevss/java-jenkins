package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.PlantDto;
import myor.matrix.master.repository.PlantRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class PlantRepositoryImpl implements PlantRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<PlantDto> getListPlant() {
		// TODO Auto-generated method stub
		List<PlantDto> result = new ArrayList<>();
		
		String sql = " SELECT plant_id, plant_name, address, no_telpon " 
					+"    FROM "+t.getTenantId()+".ftable281 "
					+"   WHERE plant_id NOT IN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') "
					+"   ORDER BY plant_id ASC "; 
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			PlantDto data = new PlantDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3]);
			result.add(data);
		}
		return result;
	}

}
