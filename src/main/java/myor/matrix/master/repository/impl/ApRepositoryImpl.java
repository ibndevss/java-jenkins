package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ApBrowseDto;
import myor.matrix.master.repository.ApRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ApRepositoryImpl implements ApRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ApBrowseDto> getBrowseApByDate(String tanggal) {
		// TODO Auto-generated method stub
		List<ApBrowseDto> result = new ArrayList<ApBrowseDto>();
		
		String sql = "SELECT   AP_CODE, KETERANGAN, TO_CHAR(approve_date, 'DD MON YYYY') approve_date, "
			+"			TO_CHAR(TGL_AWAL, 'DD MON YYYY') TGL_AWAL, TO_CHAR(TGL_AKHIR, 'DD MON YYYY') TGL_AKHIR "
			+"       FROM "+t.getTenantId()+".FCLAIMAP "
			+"		WHERE tgl_awal <= '"+tanggal+"'  "
			+"         and tgl_akhir >=  '"+tanggal+"' "
			+"   ORDER BY AP_CODE  ASC ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			ApBrowseDto data = new ApBrowseDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""),
					Objects.toString(obj[2], ""), Objects.toString(obj[3], ""), Objects.toString(obj[4], ""));
			result.add(data);
		}
		
		return result;
	}

}
