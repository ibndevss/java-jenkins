package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.tenant.TenantContext;
import myor.matrix.master.entity.AlasanBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.MasterAlasanRepository;

@Repository
public class MasterAlasanRepositoryImpl implements MasterAlasanRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SelectItem<String>> getAlasan(String tipe) {
		// TODO Auto-generated method stub		
		String sql = " SELECT kdalasan, alasan FROM "+t.getTenantId()+".ftable01 WHERE tipealasan = '"+tipe+"' ORDER BY kdalasan ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SelectItem<String>> getAlasanNotEc() {
		// TODO Auto-generated method stub
		String sql = " SELECT data1 as kode, data2 as keterangan "
				+"	FROM "+t.getTenantId()+".FTABEL44 "
				+"	ORDER BY data1 ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<AlasanBrowseDto> browseAlasan(String tipeAlasan) {
		// TODO Auto-generated method stub
		List<AlasanBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT kdalasan, alasan FROM "+t.getTenantId()+".ftable01 WHERE tipealasan = '"+tipeAlasan+"' ORDER BY kdalasan ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			AlasanBrowseDto data = new AlasanBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<SelectItem<String>> getAlasanRetur(boolean reshuffle) {
		// TODO Auto-generated method stub
		String sql = "	SELECT kdrtr, kdrtr||'-'||kdrtrname AS alasan "
					+"	 FROM "+t.getTenantId()+".ftretur WHERE kdrtr IS NOT NULL ";
				if (reshuffle == true) {
					sql +="  AND KDRTR = 'R' "; 
				} else {
					sql +="	 AND LENGTH(KDRTR) = 1 AND kdrtr BETWEEN 'A' AND 'Z' ";
				}
					
				sql	+="	ORDER BY kdrtr ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

}
