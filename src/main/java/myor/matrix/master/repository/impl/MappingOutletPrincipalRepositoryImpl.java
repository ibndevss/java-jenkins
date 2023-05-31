package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.MappingOutletPrincipalDto;
import myor.matrix.master.entity.MappingOutletSODto;
import myor.matrix.master.repository.MappingOutletPrincipalRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MappingOutletPrincipalRepositoryImpl implements MappingOutletPrincipalRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private TenantContext t;
	
	@Override
	public List<MappingOutletPrincipalDto> getDataMapping() {
		List<MappingOutletPrincipalDto> result = new ArrayList<>();
		String sql = "	select A.CUSTNOAP, A.CUSTNAMEAP, A.CUSTADDAP, B.CUSTNO, B.CUSTNAME, B.CUSTADD1	"
					+"	,TO_CHAR(UPDDATE,'DD MON YYYY') TGLUPDATE 	"
					+" 	from "+t.getTenantId()+".FCUSTAP a "
					+"	left join "+t.getTenantId()+".FCUSTMST b on a.custnoap = b.custno	"
					+"	ORDER BY CUSTNOAP ASC	";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			MappingOutletPrincipalDto data = new MappingOutletPrincipalDto((String) obj[0], (String) obj[1], (String) obj[2], 
					(String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6]);
			result.add(data);
		}
		return result;
	}	
	
	@Override
	public List<MappingOutletPrincipalDto> getDataMappingByCustNo(String custNo) {
		List<MappingOutletPrincipalDto> result = new ArrayList<>();
		String sql = "	select A.CUSTNOAP, A.CUSTNAMEAP, A.CUSTADDAP, B.CUSTNO, B.CUSTNAME, B.CUSTADD1	"
					+"	,TO_CHAR(UPDDATE,'DD MON YYYY') TGLUPDATE 	"
					+" 	from "+t.getTenantId()+".FCUSTAP a "
					+"	left join "+t.getTenantId()+".FCUSTMST b on a.custnoap = b.custno"
					+"	where a.custnoap = '"+custNo+"'	"
					+"	ORDER BY CUSTNOAP ASC	";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			MappingOutletPrincipalDto data = new MappingOutletPrincipalDto((String) obj[0], (String) obj[1], (String) obj[2], 
					(String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6]);
			result.add(data);
		}
		return result;
	}	
	
	@Override
	public void insertMappingOutletPrincipal(String custNo, String custName, String address, String tglUpd) {
		String sql = "	insert into "+t.getTenantId()+".FCUSTAP (CUSTNOAP, CUSTNAMEAP, CUSTADDAP, UPDDATE ) "
					+"	values ('"+custNo+"', '"+custName+"', '"+address+"', '"+tglUpd+"')	";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	
	@Override
	public void deleteMappingOutletPrincipal(String custNo) {
		String sql = "	delete from "+t.getTenantId()+".FCUSTAP where custnoap = '"+custNo+"'	";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

}
