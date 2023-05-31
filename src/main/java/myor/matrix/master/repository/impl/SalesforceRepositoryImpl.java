package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.SalesforceRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class SalesforceRepositoryImpl implements SalesforceRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SearchBrowseDto> getBrowseSalesforce() {
		// TODO Auto-generated method stub
		List<SearchBrowseDto> result = new ArrayList<>();		
		
		String sql = " SELECT data1, data2 FROM "+t.getTenantId()+".ftabel10 ORDER BY data1 ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SearchBrowseDto data = new SearchBrowseDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""));
			result.add(data);
		}
		
		return result;
	}
	
}
