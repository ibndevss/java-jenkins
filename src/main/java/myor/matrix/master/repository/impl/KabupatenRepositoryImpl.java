package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.KabupatenRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class KabupatenRepositoryImpl implements KabupatenRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
        List<SearchFromHierarkiBrowseDto> result = new ArrayList<>();
		String sql = " select distinct T12 as Kode,ket as Keterangan, T11, T12 from "+t.getTenantId()+".FCSHIR12 order by t12 ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SearchFromHierarkiBrowseDto data = new SearchFromHierarkiBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], null, null, null);
			result.add(data);
		}
		
		return result;
	}

}
