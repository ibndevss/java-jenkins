package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ListDocumentDto;
import myor.matrix.master.repository.MasterDocumentInventoryRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MasterDocumentInventoryRepositoryImpl implements MasterDocumentInventoryRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ListDocumentDto> getListDocumentPenurunanStockVan(String type) {
		// TODO Auto-generated method stub
		List<ListDocumentDto> result = new ArrayList<>();
		String sql = " SELECT DISTINCT DOCNO, "
				+ "	TO_CHAR(DOCDATE, 'DD MON YYYY') DOCDATES, "
				+ "	SLSNO "
				+ "FROM "+t.getTenantId()+".FMUTASIVAN_H "
				+ "WHERE "
				+ "	TRANSTYPE = '"+type+"' "
				+ "ORDER BY DOCNO ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) {
			ListDocumentDto data = new ListDocumentDto((String) obj[0], (String) obj[1], (String) obj[2]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ListDocumentDto> getListDocumentBookingPenambahanBarangVan() {
		// TODO Auto-generated method stub
		List<ListDocumentDto> result = new ArrayList<>();
		String sql = "SELECT DOCNO, TO_CHAR(DOCDATE, 'DD MON YYYY') DOCDATE, SLSNO FROM "+t.getTenantId()+".FBOOKV_H ORDER BY DOCNO ASC  ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) {
			ListDocumentDto data = new ListDocumentDto((String) obj[0], (String) obj[1], (String) obj[2]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ListDocumentDto> getListDocumentPenambahanBarangVan() {
		// TODO Auto-generated method stub
		List<ListDocumentDto> result = new ArrayList<>();
		String sql = "SELECT DISTINCT REFNO, TO_CHAR(DOCDATE, 'DD MON YYYY') DOCDATE, SLSNO FROM "+t.getTenantId()+".FBOOKV_H WHERE FLAG='*' ORDER BY REFNO ASC  ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) {
			ListDocumentDto data = new ListDocumentDto((String) obj[0], (String) obj[1], (String) obj[2]);
			result.add(data);
		}
		return result;
	}
}
