package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.MasterBankDto;
import myor.matrix.master.entity.MasterBankHODto;
import myor.matrix.master.repository.MasterBankHORepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MasterBankHORepositoryImpl implements MasterBankHORepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<MasterBankHODto> getDataBankHO() {
		List<MasterBankHODto> result = new ArrayList<>();

		// TODO Auto-generated method stub
		String sql = "SELECT bank_no, bank_name FROM " + t.getTenantId() + ".ftbank_ho ORDER BY bank_no ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resulList = query.getResultList();
		for (Object[] obj : resulList) {
			MasterBankHODto data = new MasterBankHODto((String) obj[0], (String) obj[1]);

			result.add(data);
		}

		return result;
	}

	@Override
	public void insertMasterBankHO(String kdBank, String namaBank) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO " + t.getTenantId() + ".ftbank_ho (bank_no,bank_name) VALUES ('" + kdBank + "','"
				+ namaBank + "')";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateMasterBankHO(String kdBank, String namaBank) {
		// TODO Auto-generated method stub
		String sql = "UPDATE " + t.getTenantId() + ".ftbank_ho SET bank_name = '" + namaBank + "' WHERE bank_no = '"
				+ kdBank + "'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteMasterBankHO(String kdBank) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM " + t.getTenantId() + ".ftbank_ho WHERE bank_no = '" + kdBank + "'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public boolean valDelBank(String kdBank) {
		// TODO Auto-generated method stub
		boolean result = false;		
		
		String sql = " SELECT * FROM "+t.getTenantId()+".fpay_ho WHERE bank = '"+kdBank+"' ";
		Query query = entityManager.createNativeQuery(sql);	
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			result = true;
		}
		
		return result;
	}

}
