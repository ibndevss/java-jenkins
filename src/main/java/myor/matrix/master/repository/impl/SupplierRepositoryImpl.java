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
import myor.matrix.master.entity.SupplierDto;
import myor.matrix.master.repository.SupplierRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class SupplierRepositoryImpl implements SupplierRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SelectItem<String>> getListSupplier() {
		// TODO Auto-generated method stub
		String sql = "SELECT SUPPNO supplier, suppno||'-'||SUPPNAME suppliername FROM "+t.getTenantId()+".FSUPPLIER ORDER BY SUPPNO ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());	
	}
	
	@Override
	public List<SupplierDto> getListSupplierAll() {
		List<SupplierDto> result = new ArrayList<SupplierDto>();
		String sql = "SELECT SUPPNO supplier,  SUPPNAME suppliername FROM "+t.getTenantId()+".FSUPPLIER ORDER BY SUPPNO ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		 for (Object[] o : resultList) {
			SupplierDto data = new SupplierDto((String) o[0], (String) o[1]);
			result.add(data);
		}
		return result;	
	}
	
	
	
	
}
