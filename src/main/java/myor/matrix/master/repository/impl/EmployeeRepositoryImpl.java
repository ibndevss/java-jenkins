package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.DriverBrowseDto;
import myor.matrix.master.entity.HelperBrowseDto;
import myor.matrix.master.entity.PejabatBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.EmployeeRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<PejabatBrowseDto> getBrowsePejabat() {
		// TODO Auto-generated method stub
		List<PejabatBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT pejkode kdpejabat, pejname nmpejabat FROM "+t.getTenantId()+".fpej ORDER BY pejkode ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			PejabatBrowseDto data = new PejabatBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<DriverBrowseDto> getBrowseDriver() {
		// TODO Auto-generated method stub
		List<DriverBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT empno, empname FROM "+t.getTenantId()+".femployee WHERE empopr = 'D' ORDER BY empno ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			DriverBrowseDto data = new DriverBrowseDto(Objects.toString((String) obj[0], ""), Objects.toString((String) obj[1], ""));
			result.add(data);
		}
		return result;
	}

	@Override
	public List<HelperBrowseDto> getBrowseHelper() {
		// TODO Auto-generated method stub
		List<HelperBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT empno, empname FROM "+t.getTenantId()+".femployee WHERE empopr = 'H' ORDER BY empno ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			HelperBrowseDto data = new HelperBrowseDto(Objects.toString((String) obj[0], ""), Objects.toString((String) obj[1], ""));
			result.add(data);
		}
		return result;
	}

	@Override
	public List<SelectItem<String>> getListKolektorSelectItem() {
		// TODO Auto-generated method stub
		String sql = " SELECT DISTINCT empno, empno || ' - ' || empname kolektor FROM "+t.getTenantId()+".femployee WHERE empopr = 'K' ORDER BY empno ASC ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

}
