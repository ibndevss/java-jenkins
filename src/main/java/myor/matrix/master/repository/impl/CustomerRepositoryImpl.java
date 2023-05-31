package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.CustomerBrowseDto;
import myor.matrix.master.entity.GroupOutletBrowseDto;
import myor.matrix.master.entity.MasterOutletDataAttributeDto;
import myor.matrix.master.repository.CustomerRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<CustomerBrowseDto> getBrowseCustomer() {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT   custno, custname, custadd1, typeout, flagout FROM "+t.getTenantId()+".fcustmst "
					+" ORDER BY custno ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<GroupOutletBrowseDto> getBrowseGroupOutlet() {
		// TODO Auto-generated method stub
		List<GroupOutletBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT grupout, grupname FROM "+t.getTenantId()+".fgrupout ORDER BY grupout ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			GroupOutletBrowseDto data = new GroupOutletBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerBySalesman(String slsNo, String xmasterData) {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = "SELECT   c.custno, c.custname, c.custadd1, c.typeout, c.flagout, c.flagpay, "
			+"		CASE WHEN sh.ship_to IS NOT NULL THEN 'true' ELSE 'false' END AS isShipTo, " 
			+"		CASE WHEN ap.custnoap IS NOT NULL THEN 'true' ELSE 'false' END AS isAp "
			+"	FROM "+t.getTenantId()+".fcustmst c  "
			+"	LEFT JOIN "+t.getTenantId()+".fcustsls s ON (c.custno = s.custno) "
			+"	LEFT JOIN "+t.getTenantId()+".ftable48 b ON (c.custno = b.custno) "
			+"	LEFT JOIN "+t.getTenantId()+".ftable293 sh ON (c.custno = sh.kode_outlet) "
			+"	LEFT JOIN "+t.getTenantId()+".fcustap ap ON (c.custno = ap.custnoap) "
			+"	WHERE s.slsno = '"+slsNo+"' ";
		
		if(xmasterData != null && xmasterData.equals("") == false) {
			sql+= " AND b.processdate IS NOT NULL ";
		}
			
		sql+="	ORDER BY custno ASC ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto((String) obj[0], (String) obj[1], (String) obj[2],
					(String) obj[3], (String) obj[4], (String) obj[5], Boolean.parseBoolean(Objects.toString(obj[6])),
					Boolean.parseBoolean(Objects.toString(obj[7])));
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<CustomerBrowseDto> getCustomerByCustNo(String custNo) {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT   custno, custname, custadd1 FROM "+t.getTenantId()+".fcustmst "
					+" Where custno = '"+custNo+"' ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], "");
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutleSO() {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = "	select a.CUSTNO, a.CUSTNAME, a.CUSTADD1, coalesce(b.CUSTNO,'') as CUSTNO_SO "
					+"	from "+t.getTenantId()+".fcustmst a "
                  	+" 	left join "+t.getTenantId()+".ftable76 b on a.custno = b.custno "
                  	+" 	where b.custno is null "
                  	+"	order by a.CUSTNO ASC	";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], "");
			data.setCustNoSO((String) obj[3]);
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<CustomerBrowseDto> getCustomerNotMappingOutleSOByCustNo(String custNo) {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = "	select a.CUSTNO, a.CUSTNAME, a.CUSTADD1, coalesce(b.CUSTNO,'') as CUSTNO_SO "
					+"	from "+t.getTenantId()+".fcustmst a "
                  	+" 	left join "+t.getTenantId()+".ftable76 b on a.custno = b.custno "
                  	+" 	where b.custno is null and a.custno = '"+custNo+"' "
                  	+"	order by a.CUSTNO ASC	";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], "");
			data.setCustNoSO((String) obj[3]);
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipal() {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = "	select a.CUSTNO, a.CUSTNAME, a.CUSTADD1, coalesce(b.CUSTNOAP,'') as CUSTNOAP "
					+"	from "+t.getTenantId()+".fcustmst a "
                  	+" 	left join "+t.getTenantId()+".fcustap b on a.custno = b.custnoap "
                  	+" 	where b.custnoap is null "
                  	+"	order by a.CUSTNO ASC	";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], "");
			data.setCustNoAP((String) obj[3]);
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipalByCustNo(String custNo) {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = "	select a.CUSTNO, a.CUSTNAME, a.CUSTADD1, coalesce(b.CUSTNOAP,'') as CUSTNOAP "
					+"	from "+t.getTenantId()+".fcustmst a "
                  	+" 	left join "+t.getTenantId()+".fcustap b on a.custno = b.custnoap "
                  	+" 	where b.custnoap is null and a.custno = '"+custNo+"' "
                  	+"	order by a.CUSTNO ASC	";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], "");
			data.setCustNoAP((String) obj[3]);
			result.add(data);
		}
		return result;
	}

	@Override
	public MasterOutletDataAttributeDto getCustomerAttribute(String custNo) {
		// TODO Auto-generated method stub
		String sql = "SELECT GRUPOUT, TYPEOUT, GHARGA  FROM "+t.getTenantId()+".FCUSTMST "
				+ "	WHERE CUSTNO='"+custNo+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		MasterOutletDataAttributeDto result = new MasterOutletDataAttributeDto();
		for (Object[] obj : hasil) {
			result.setKdgroupout(Objects.toString(obj[0], ""));
			result.setOuttype(Objects.toString(obj[1], ""));
			result.setGharga(Objects.toString(obj[2], ""));
		}
		
		return result;
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerInPengajuanRetur(String slsNo) {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT a.CUSTNO, a.custname  FROM "+t.getTenantId()+".fcustmst a  left join "+t.getTenantId()+".fcustsls s on a.custno = s.custno where s.slsno = '"+slsNo+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto((String) obj[0], (String) obj[1], "", "");
			result.add(data);
		}
		return result;
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerLaporanUmurPiutang() {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = "SELECT DISTINCT m.custno , m.custname , m.custadd1||' '||m.custadd2 address, "
				+"	m.ccity city, m.ccontact contactperson, "
                +" 	m.cphone1 phone, m.cfaxno fax "
                +" FROM "+t.getTenantId()+".fcustmst m "
                +" WHERE m.custno IS NOT NULL "
                +" ORDER BY m.custno ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""),
					Objects.toString(obj[2], ""), Objects.toString(obj[3], ""), Objects.toString(obj[4], ""),
					Objects.toString(obj[5], ""), Objects.toString(obj[6], ""));
			result.add(data);
		}
		return result;
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerRekalkulasiLimitKredt(String XLIMITKREDIT) {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		String sql = "";
		if (XLIMITKREDIT.equalsIgnoreCase("2")) {
			sql =" SELECT a.grouppayer, b.custname, b.custadd1, b.typeout "
				+"	  FROM (SELECT DISTINCT grouppayer "
				+"	                   FROM "+t.getTenantId()+".ftable48 "
				+"	                  WHERE grouppayer IS NOT NULL) a "
				+"	       INNER JOIN "
				+"	       "+t.getTenantId()+".fcustmst b ON b.custno = a.grouppayer ";
		} else {
			sql =" SELECT   custno, custname, custadd1, typeout FROM "+t.getTenantId()+".fcustmst "
				+" ORDER BY custno ASC ";
		}
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerLaporanPiutangDagang() {
		// TODO Auto-generated method stub
		List<CustomerBrowseDto> result = new ArrayList<>();
		
		String sql = "SELECT DISTINCT m.custno , m.custname , m.custadd1, "
				+"	m.ccity city, m.ccontact contactperson, "
                +" 	m.cphone1 phone, m.cfaxno fax, m.custadd2 "
                +" FROM "+t.getTenantId()+".fcustmst m "
                +" WHERE m.custno IS NOT NULL "
                +" ORDER BY m.custno ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""),
					Objects.toString(obj[2], ""), Objects.toString(obj[3], ""), Objects.toString(obj[4], ""),
					Objects.toString(obj[5], ""), Objects.toString(obj[6], ""));
			data.setAddress2(Objects.toString(obj[7],""));
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<CustomerBrowseDto> getBrowseCustomerViewPhotoAndKoordOutlet() {
		List<CustomerBrowseDto> result = new ArrayList<>();
		String sql = "SELECT a.custno, b.custname "
				+ "  FROM (SELECT DISTINCT a.custno "
				+ "                   FROM "+t.getTenantId()+".ftable275 a INNER JOIN "+t.getTenantId()+".ftable274 b "
				+ "                        ON a.custno = b.custno "
				+ "                  WHERE b.custno IS NOT NULL) a "
				+ "       INNER JOIN "
				+ "       "+t.getTenantId()+".fcustmst b ON a.custno = b.custno";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			CustomerBrowseDto data = new CustomerBrowseDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""),
					"", "", "", "", "");
			result.add(data);
		}
		return result;
	}

}
