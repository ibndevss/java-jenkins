package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.TypeOutletBrowseDto;
import myor.matrix.master.repository.TipeOutletRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class TipeOutletRepositoryImpl implements TipeOutletRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		List<SearchBrowseDto> result = new ArrayList<>();
		String sql = " select distinct outtype as Kode,description as Keterangan from "+t.getTenantId()+".ftable49 ORDER BY kode ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SearchBrowseDto data = new SearchBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<TypeOutletBrowseDto> browseTypeOutlet() {
		// TODO Auto-generated method stub
		List<TypeOutletBrowseDto> result = new ArrayList<>();
		
		String sql  = "select TYPE AS CHANNEL, TYPENAME AS CHANNEL_NAME from  "+t.getTenantId()+".ftypeout  order by type asc ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			TypeOutletBrowseDto data = new TypeOutletBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		
		return result;
	}

}
