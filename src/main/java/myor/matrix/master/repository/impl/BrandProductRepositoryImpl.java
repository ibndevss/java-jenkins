package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.BrandBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.BrandProductRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class BrandProductRepositoryImpl implements BrandProductRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SelectItem<String>> getListBrand() {
		// TODO Auto-generated method stub		
		String sql = " SELECT prlin || brand AS brand, prlin || brand || ' ' || brandname brandname FROM "+t.getTenantId()+".ftbrand ORDER BY prlin, brand ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());		
	}

	@Override
	public List<BrandBrowseDto> getBrowseBrand(String umbrellaFrom, String umbrellaTo) {
		// TODO Auto-generated method stub
		List<BrandBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT DISTINCT (s.prlin) AS umbrella, (s.brand) AS brand, b.brandname AS brandname "
					+"            FROM "+t.getTenantId()+".ftbrand b INNER JOIN "+t.getTenantId()+".ftsbrand1 s ON s.prlin = b.prlin AND s.brand = b.brand "
					+"           WHERE s.brand IS NOT NULL ";
		
		if (!umbrellaFrom.equalsIgnoreCase("")) {
			sql += " AND s.prlin >= '"+umbrellaFrom+"' ";
		}
		
		if (!umbrellaTo.equalsIgnoreCase("")) {
			sql += " AND s.prlin <= '"+umbrellaTo+"' ";
		}
		
		sql += " ORDER BY umbrella, brand ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			BrandBrowseDto data = new BrandBrowseDto((String) obj[0], (String) obj[1], (String) obj[2]);
			result.add(data);
		}
		
		return result;
	}

}
