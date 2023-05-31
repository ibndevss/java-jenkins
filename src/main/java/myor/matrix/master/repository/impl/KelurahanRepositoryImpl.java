package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.KelurahanRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class KelurahanRepositoryImpl implements KelurahanRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
        List<SearchFromHierarkiBrowseDto> result = new ArrayList<>();
		String sql = " select distinct T14 as Kode,(T11||'-'||T12||'-'||T13||'-'||T14||'-'||ket) as Keterangan,T11, T12, T13, T14 from  "+t.getTenantId()+".FCSHIR14 order by t14 ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SearchFromHierarkiBrowseDto data = new SearchFromHierarkiBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], null);
			result.add(data);
		}
		
		return result;
	}

}
