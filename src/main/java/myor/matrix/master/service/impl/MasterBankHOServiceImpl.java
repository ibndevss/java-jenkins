package myor.matrix.master.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.MasterBankHODto;
import myor.matrix.master.repository.MasterBankHORepository;
import myor.matrix.master.service.MasterBankHOService;

@Service
public class MasterBankHOServiceImpl implements MasterBankHOService {

	@Autowired
	private MasterBankHORepository masterBankHORepository;

	@Override
	public List<MasterBankHODto> getDataBankHO() {
		// TODO Auto-generated method stub
		return masterBankHORepository.getDataBankHO();
	}

	@Transactional
	@Override
	public void insertMasterBankHO(String kdBank, String namaBank) {
		// TODO Auto-generated method stub
		masterBankHORepository.insertMasterBankHO(kdBank, namaBank);
	}

	@Transactional
	@Override
	public void updateMasterBankHO(String kdBank, String namaBank) {
		// TODO Auto-generated method stub
		masterBankHORepository.updateMasterBankHO(kdBank, namaBank);
	}

	@Transactional
	@Override
	public String deleteMasterBankHO(String kdBank) {
		// TODO Auto-generated method stub	
		
		boolean val = masterBankHORepository.valDelBank(kdBank);
		String message = "";
		
		if (val == true) {
			message = "X";
		} else {						
			masterBankHORepository.deleteMasterBankHO(kdBank);
			message = "Y";
		}
		
		return message;
	}

}
