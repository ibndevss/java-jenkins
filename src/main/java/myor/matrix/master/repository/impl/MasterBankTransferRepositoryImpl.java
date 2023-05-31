package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.MasterBankTransferDto;
import myor.matrix.master.repository.MasterBankTransferRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MasterBankTransferRepositoryImpl implements MasterBankTransferRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<MasterBankTransferDto> getDataBankTransfer() {
		List<MasterBankTransferDto> result = new ArrayList<>();

		// TODO Auto-generated method stub
		String sql = "SELECT bank_no, bank_name FROM " + t.getTenantId() + ".ftbank_transfer ORDER BY no ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resulList = query.getResultList();
		for (Object[] obj : resulList) {
			MasterBankTransferDto data = new MasterBankTransferDto ((String) obj[0], (String) obj[1]);

			result.add(data);
		}

		return result;
	}

	@Override
	public void insertMasterBankTransfer(String kdBankTransfer, String namaBankTransfer) {		
		String sql = " INSERT INTO "+t.getTenantId()+".ftbank_transfer (no, bank_no, bank_name) " + 
					 " SELECT NVL (MAX (NO), 0) + 1 NO, '"+kdBankTransfer+"' bank_no, '"+namaBankTransfer+"' bank_name FROM "+t.getTenantId()+".ftbank_transfer ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();

	}

	@Override
	public void updateMasterBankTransfer(String kdBank, String namaBank) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+".ftbank_transfer SET bank_name = '"+namaBank+"' WHERE bank_no = '"+kdBank+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteMasterBankTransfer(String kdBank) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM "+t.getTenantId()+".ftbank_transfer WHERE bank_no = '"+kdBank+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public boolean valDelBank(String kdBank) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		String sql = " SELECT * FROM "+t.getTenantId()+".fpcheq_h WHERE banktransfer = '"+kdBank+"' ";
		Query query = entityManager.createNativeQuery(sql);	
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			result = true;
		}
		
		return result;
	}

}
