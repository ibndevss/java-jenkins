package myor.matrix.master.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.DiskonRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class DiskonRepositryImpl implements DiskonRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SelectItem<String>> getSelectItemMasterTactical() {
		// TODO Auto-generated method stub
		String sql = "SELECT gdisc, gdisc||' '||gdiscname  FROM "+t.getTenantId()+".fgdisc "
			+ "ORDER BY gdisc ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SelectItem<String>> getSelectItemMasterCod() {
		// TODO Auto-generated method stub
		String sql = "SELECT data1, data1||' '||data2  FROM "+t.getTenantId()+".ftabel06 "
			+ "ORDER BY data1 ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

}
