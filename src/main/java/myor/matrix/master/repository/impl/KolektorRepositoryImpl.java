package myor.matrix.master.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.KolektorRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class KolektorRepositoryImpl implements KolektorRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SelectItem<String>> getList() {
		// TODO Auto-generated method stub
		String sql = " Select EMPNO AS value, EMPNO || ' - '|| EMPNAME AS label from "+t.getTenantId()+".femployee WHERE EMPOPR = 'K' order by empno ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());	
	}

}
