package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.DivisiProductBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.DivisiProductRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class DivisiProductRepositoryImpl implements DivisiProductRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SelectItem<String>> getListDivisi() {
		// TODO Auto-generated method stub
		String sql = " SELECT sbra2 AS divisi, sbra2 || ' ' || sbra2name AS divisiname FROM "+t.getTenantId()+".ftsbrand2 ORDER BY sbra2 ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());		
	}

	@Override
	public List<DivisiProductBrowseDto> getBrowseDivisi() {
		// TODO Auto-generated method stub
		List<DivisiProductBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT sbra2, sbra2name FROM "+t.getTenantId()+".ftsbrand2 ORDER BY sbra2 ASC ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			DivisiProductBrowseDto data = new DivisiProductBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		
		return result;
	}

}
