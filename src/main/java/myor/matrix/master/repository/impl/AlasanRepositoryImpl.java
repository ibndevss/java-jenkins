package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.AlasanBrowseDto;
import myor.matrix.master.repository.AlasanRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class AlasanRepositoryImpl implements AlasanRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<AlasanBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		List<AlasanBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT KDALASAN, ALASAN  FROM "+t.getTenantId()+".FTABLE01 WHERE TIPEALASAN = '16' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			AlasanBrowseDto data = new AlasanBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		return result;
	}

	
}
