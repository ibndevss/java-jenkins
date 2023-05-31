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
import myor.matrix.master.entity.SubbrandBrowseDto;
import myor.matrix.master.repository.SubBrandProductRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class SubBrandProductRepositoryImpl implements SubBrandProductRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SelectItem<String>> getListSubBrand() {
		// TODO Auto-generated method stub		
		String sql = " SELECT prlin || brand || sbra1 AS sbrand, prlin || brand || sbra1 || ' ' || sbra1name AS subbrandname "
					+" FROM "+t.getTenantId()+".ftsbrand1 ORDER BY prlin, brand, sbra1 ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());		
	}

	@Override
	public List<SubbrandBrowseDto> getBrowseSubBrand(String umbrellaFrom, String umbrellaTo, String brandFrom,
			String brandTo) {
		// TODO Auto-generated method stub
		List<SubbrandBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT DISTINCT s.prlin AS umbrella, b.brand, s.sbra1 AS subbrand, s.sbra1name AS subbrandname "
					+"            FROM "+t.getTenantId()+".ftproline p INNER JOIN "+t.getTenantId()+".ftsbrand1 s ON p.prlin = s.prlin "
					+"                 INNER JOIN "+t.getTenantId()+".ftbrand b ON s.prlin = b.prlin AND s.brand = b.brand "
					+"           WHERE s.sbra1 IS NOT NULL ";
		
		if (!umbrellaFrom.equalsIgnoreCase("")) {
			sql += " AND s.prlin >= '"+umbrellaFrom+"' ";
		}
		
		if (!umbrellaTo.equalsIgnoreCase("")) {
			sql += " AND s.prlin <= '"+umbrellaTo+"' ";
		}
		
		if (!brandFrom.equalsIgnoreCase("")) {
			sql += " AND b.brand >= '"+brandFrom+"' ";
		}
		
		if (!brandTo.equalsIgnoreCase("")) {
			sql += " AND b.brand <= '"+brandTo+"' ";
		}
		
		sql += " ORDER BY umbrella, brand, subbrand ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SubbrandBrowseDto data = new SubbrandBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3]);
			
			result.add(data);
		}
		
		return result;
	}

}
