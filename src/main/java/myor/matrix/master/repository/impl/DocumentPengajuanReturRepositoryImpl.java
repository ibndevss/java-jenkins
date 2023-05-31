package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.DocumentPengajuanReturBrowseDto;
import myor.matrix.master.entity.UmbrellaBrowseDto;
import myor.matrix.master.repository.DocumentPengajuanReturRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class DocumentPengajuanReturRepositoryImpl implements DocumentPengajuanReturRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<DocumentPengajuanReturBrowseDto> getBrowseDocumentPengajuanRetur() {
		// TODO Auto-generated method stub
		List<DocumentPengajuanReturBrowseDto> result = new ArrayList<>();
		
		String sql = "SELECT DOCNO, TO_CHAR(DOCDATE, 'DD MON YYYY') docDat  FROM "+t.getTenantId()+".FREQRETUR_H ORDER BY DOCNO  ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			DocumentPengajuanReturBrowseDto data = new DocumentPengajuanReturBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		
		return result;
	}

}
