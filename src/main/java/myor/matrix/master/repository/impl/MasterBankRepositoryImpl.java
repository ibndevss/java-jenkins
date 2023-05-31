package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.MasterBankDto;
import myor.matrix.master.repository.MasterBankRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MasterBankRepositoryImpl implements MasterBankRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<MasterBankDto> getDataBank() {
		List<MasterBankDto> result = new ArrayList<>();
		
		// TODO Auto-generated method stub
		String sql = "SELECT bank_no, bank_name FROM "+t.getTenantId()+".ftbank ORDER BY bank_no ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resulList = query.getResultList();
		for (Object[] obj : resulList) {
			MasterBankDto data = new MasterBankDto((String) obj[0], (String) obj[1]);
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public void insertMasterBank(String kdBank, String namaBank) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+".ftbank (bank_no,bank_name) VALUES ('"+kdBank+"','"+namaBank+"')";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();		
	}

	@Override
	public void updateMasterBank(String kdBank, String namaBank) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+".ftbank SET bank_name = '"+namaBank+"' WHERE bank_no = '"+kdBank+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteMasterBank(String kdBank) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM "+t.getTenantId()+".ftbank WHERE bank_no = '"+kdBank+"'";
		
//		if (!kdBank.isEmpty()) {
//			sql += "AND bank_no = '"+kdBank+"'";
//		}
		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public boolean valDelBank(String kdBank) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		String sql = " SELECT * FROM "+t.getTenantId()+".fpcheq_h WHERE bankcair = '"+kdBank+"' ";
		Query query = entityManager.createNativeQuery(sql);	
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			result = true;
		}
		
		return result;
	}

}
