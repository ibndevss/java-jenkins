package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.MappingOutletSOBrowseDto;
import myor.matrix.master.entity.MappingOutletSODto;
import myor.matrix.master.repository.MappingOutletSORepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MappingOutletSORepositoryImpl implements MappingOutletSORepository {
	
	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<MappingOutletSOBrowseDto> getBrowseSOSAP(){
		List<MappingOutletSOBrowseDto> result = new ArrayList<MappingOutletSOBrowseDto>();
		String sql = "	select CUSTNOSO AS CUSTNO,CUSTNONAMESO AS CUSTNAME,ADDRESS_SO AS ADDRESS "
					+"	from "+t.getTenantId()+".FTABLE76 ORDER BY CUSTNOSO ASC	";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] a : resultList) {
			MappingOutletSOBrowseDto data = new MappingOutletSOBrowseDto((String) a[0], (String) a[1], (String) a[2]);
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<MappingOutletSODto> getDataMapping() {
		List<MappingOutletSODto> result = new ArrayList<>();
		String sql = "	select A.CUSTNOSO, A.CUSTNONAMESO, A.ADDRESS_SO, A.CUSTNO,B.CUSTNAME,B.CUSTADD1	"
					+"	,TO_CHAR(TGLUPDATE,'DD MON YYYY') TGLUPDATE 	"
					+" 	from "+t.getTenantId()+".FTABLE76 a "
					+"	left join "+t.getTenantId()+".FCUSTMST b on a.custno = b.custno	"
					+"	ORDER BY CUSTNOSO ASC	";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			MappingOutletSODto data = new MappingOutletSODto((String) obj[0], (String) obj[1], (String) obj[2], 
					(String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6]);
			result.add(data);
		}
		return result;
	}	
	
	@Override
	public List<MappingOutletSODto> getDataMappingByCustSOSAP(String custNo) {
		List<MappingOutletSODto> result = new ArrayList<>();
		String sql = "	select A.CUSTNOSO, A.CUSTNONAMESO, A.ADDRESS_SO, A.CUSTNO,B.CUSTNAME,B.CUSTADD1	"
					+"	,TO_CHAR(TGLUPDATE,'DD MON YYYY') TGLUPDATE 	"
					+" 	from "+t.getTenantId()+".FTABLE76 a "
					+"	left join "+t.getTenantId()+".FCUSTMST b on a.custno = b.custno	"
					+"	where a.custnoso = '"+custNo+"'	"
					+"	ORDER BY CUSTNOSO ASC	";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			MappingOutletSODto data = new MappingOutletSODto((String) obj[0], (String) obj[1], (String) obj[2], 
					(String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6]);
			result.add(data);
		}
		return result;
	}
	
	@Override
	public void deleteMappingOutlet(String custNoSO) {
		String sql = "	delete from "+t.getTenantId()+".FTABLE76 where custnoso = '"+custNoSO+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public void insertMappingOutlet(String custNoSO, String custNameSO, String addressSO, String custNo, String tglUpd) {
		String sql = "	insert into "+t.getTenantId()+".FTABLE76 (custnoso, custnonameso, address_so, custno, tglupdate ) "
					+"	values ('"+custNoSO+"', '"+custNameSO+"', '"+addressSO+"', '"+custNo+"' , '"+tglUpd+"')	";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public void updateMappingOutlet(String custNoSO, String custNameSO, String addressSO, String custNo, String tglUpd) {
		String sql = "	update "+t.getTenantId()+".FTABLE76 "
					+"	set custnonameso = '"+custNameSO+"', address_so = '"+addressSO+"', custno = '"+custNo+"',"
					+"		tglupdate =  '"+tglUpd+"' "
					+"	where custnoso = '"+custNoSO+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
}
