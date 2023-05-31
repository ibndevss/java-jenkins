package myor.matrix.master.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.DistributorInfoDTO;
import myor.matrix.master.repository.DistributorRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class DistributorRepositoryImpl implements DistributorRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public DistributorInfoDTO getDistInfo() {
		// TODO Auto-generated method stub
		DistributorInfoDTO result = new DistributorInfoDTO();
		
		String sql = "SELECT "
			+"		(SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') distid, "
			+"		(SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') distname, "
			+"		(SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') branchid, "
			+"		(SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') branchname, "
			+"		(SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KOTA') city, "
			+"		TRIM(TO_CHAR(sysdate, 'Day'))||', '||TO_CHAR(sysdate, 'DD/MM/YYYY') as today, "
			+"		(SELECT TRIM(TO_CHAR(memodate, 'Day'))||', '||TO_CHAR(memodate, 'DD/MM/YYYY') FROM "+t.getTenantId()+".fmemo WHERE memonama = 'CADATE') as whdate, "
			+"		(SELECT TO_CHAR(memoint) FROM "+t.getTenantId()+".fmemo WHERE memonama = 'PEKAN') as week_, "
			+"		(SELECT TO_CHAR(memoint) FROM "+t.getTenantId()+".fmemo WHERE memonama = 'PERIODE') as period_, "
			+"		(SELECT TO_CHAR(memoint) FROM "+t.getTenantId()+".fmemo WHERE memonama = 'TAHUN') as year_, "
			+"		(SELECT SUBSTR(xno, 0,2)||'.'||SUBSTR(xno, 3,2)||'.'||SUBSTR(xno, 5,2) FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') as version_pro "
			+"	FROM dual ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			result = new DistributorInfoDTO((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3],
					(String) obj[4], (String) obj[5], (String) obj[6], (String) obj[7], (String) obj[8],
					(String) obj[9], (String) obj[10]);
		}
		
		return result;
	}

}
