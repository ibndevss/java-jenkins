package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.CNDNDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.CNDNRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class CNDNRepositoryImpl implements CNDNRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SelectItem<String>> getListCNDN() {
		// TODO Auto-generated method stub
		String sql = "   SELECT   kode, kode||' - '||ket as keterangan  "
			        +"      FROM "+t.getTenantId()+".fartipe " 
			        +"  ORDER BY kode ASC ";
          Query query = entityManager.createNativeQuery(sql);
  		List<Object[]> resultList = query.getResultList();

  		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
  				.collect(Collectors.toList());		
	}

	@Override
	public List<CNDNDto> browseCNDN() {
		// TODO Auto-generated method stub
		List<CNDNDto> result = new ArrayList<>();
		
		String sql = "   SELECT   kode, kode||' - '||ket as keterangan  "
		        +"      FROM "+t.getTenantId()+".fartipe " 
		        +"  ORDER BY kode ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			CNDNDto data = new CNDNDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		
		return result;
	}

}
