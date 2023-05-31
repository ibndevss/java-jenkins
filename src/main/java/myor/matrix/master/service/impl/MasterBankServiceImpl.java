package myor.matrix.master.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.MasterBankDto;
import myor.matrix.master.repository.MasterBankRepository;
import myor.matrix.master.service.MasterBankService;

@Service
public class MasterBankServiceImpl implements MasterBankService {
	
	@Autowired
	private MasterBankRepository masterBankRepository;

	@Override
	public List<MasterBankDto> getDataBank() {
		// TODO Auto-generated method stub
		return masterBankRepository.getDataBank();
	}

	@Transactional
	@Override
	public void insertMasterBank(String kdBank, String namaBank) {
		// TODO Auto-generated method stub		
		masterBankRepository.insertMasterBank(kdBank, namaBank);
	}

	@Transactional
	@Override
	public void updateMasterBank(String kdBank, String namaBank) {
		// TODO Auto-generated method stub
		masterBankRepository.updateMasterBank(kdBank, namaBank);
	}

	@Transactional
	@Override
	public String deleteMasterBank(String kdBank) {
		// TODO Auto-generated method stub
		boolean val = masterBankRepository.valDelBank(kdBank);
		String message = "";
		
		if (val == true) {
			message = "X";
		} else {						
			masterBankRepository.deleteMasterBank(kdBank);
			message = "Y";
		}
		
		return message;
	}

}
