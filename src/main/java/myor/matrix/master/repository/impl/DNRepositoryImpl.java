package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.DNBrowseDto;
import myor.matrix.master.repository.DNRepository;
import myor.matrix.master.tenant.TenantContext;
import myor.matrix.master.util.Util;

@Repository
public class DNRepositoryImpl implements DNRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	Util util;
	
	@Override
	public List<DNBrowseDto> listDN(String tglDN) {
		// TODO Auto-generated method stub
		List<DNBrowseDto> result = new ArrayList<>();
				
		String tanggal = "";
		if (tglDN.equalsIgnoreCase("") || tglDN == null) {
			tanggal = util.getTglGudang();
		} else {
			tanggal = tglDN;
		}
		
		String sql = "	SELECT   delino as noDocument, docno, to_char(docdate, 'DD MON YYYY') as tglDocument, total, remark " 
					+"		FROM "+t.getTenantId()+".fmutasigd_h "   
					+"	WHERE transid = 'RCV' AND transtype = '11' AND docdate = '"+tanggal+"' "
					+"	ORDER BY delino ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			DNBrowseDto data = new DNBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (BigDecimal) obj[3], (String) obj[4]);
			result.add(data);
		}
		return result;
	}

}
