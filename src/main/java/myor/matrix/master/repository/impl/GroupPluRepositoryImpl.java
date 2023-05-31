package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.GroupPluRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class GroupPluRepositoryImpl implements GroupPluRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		List<SearchBrowseDto> result = new ArrayList<>();
		String sql = " select distinct gplu as Kode,ket as Keterangan from "+t.getTenantId()+".fgplu order by gplu ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SearchBrowseDto data = new SearchBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		
		return result;
	}

}
