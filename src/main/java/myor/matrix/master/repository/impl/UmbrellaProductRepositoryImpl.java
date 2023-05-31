package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.UmbrellaBrowseDto;
import myor.matrix.master.repository.UmbrellaProductRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class UmbrellaProductRepositoryImpl implements UmbrellaProductRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SelectItem<String>> getListUmbrella() {
		// TODO Auto-generated method stub
		String sql = " SELECT prlin AS umbrella, prlin || ' ' || prliname AS umbrellaname FROM "+t.getTenantId()+".ftproline ORDER BY prlin ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
	
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());		
	}

	@Override
	public List<UmbrellaBrowseDto> getBrowseUmbrella() {
		// TODO Auto-generated method stub
		List<UmbrellaBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT DISTINCT prlin, prliname FROM "+t.getTenantId()+".ftproline ORDER BY prlin ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			UmbrellaBrowseDto data = new UmbrellaBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		
		return result;
	}

}
