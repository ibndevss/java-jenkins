package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.RuteRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class RuteRepositoryImpl implements RuteRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
        List<SearchFromHierarkiBrowseDto> result = new ArrayList<>();
		String sql = " select DISTINCT BEAT AS kode, BEATNAME AS keterangan, distrik from "+t.getTenantId()+".fbeatnew ORDER BY BEAT ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SearchFromHierarkiBrowseDto data = new SearchFromHierarkiBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], null, null, null, null);
			result.add(data);
		}
		
		return result;
	}

}
