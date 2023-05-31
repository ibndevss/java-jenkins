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
import myor.matrix.master.entity.ViewDriverDto;
import myor.matrix.master.repository.ViewDriverRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ViewDriverRepositoryImpl implements ViewDriverRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ViewDriverDto> getListDriver() {
		// TODO Auto-generated method stub
		List<ViewDriverDto> result = new ArrayList<>();
		String sql = "SELECT EMPNO, EMPNAME  "
				+ "FROM "+t.getTenantId()+".FEMPLOYEE T "
				+ "WHERE EMPOPR = 'D'";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		return resultList.stream().map(obj -> new ViewDriverDto(
				(String)obj[0], 
				(String)obj[1]))
				.collect(Collectors.toList());
	}
}
