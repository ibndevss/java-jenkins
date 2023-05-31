package myor.matrix.master.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.MasterBankTransferDto;
import myor.matrix.master.repository.MasterBankTransferRepository;
import myor.matrix.master.service.MasterBankTransferService;

@Service
public class MasterBankTransferServiceImpl implements MasterBankTransferService {
	
	@Autowired
	private MasterBankTransferRepository masterBankTransferRepository;

	@Override
	public List<MasterBankTransferDto> getDataBankTransfer() {
		// TODO Auto-generated method stub
		return masterBankTransferRepository.getDataBankTransfer();
	}
	
	@Transactional
	@Override
	public void insertMasterBankTransfer(String kdBankTransfer, String namaBankTransfer) {
		masterBankTransferRepository.insertMasterBankTransfer(kdBankTransfer, namaBankTransfer);

	}
	
	@Transactional
	@Override
	public void updateMasterBankTransfer(String kdBankTransfer, String namaBankTransfer) {
		// TODO Auto-generated method stub
		masterBankTransferRepository.updateMasterBankTransfer(kdBankTransfer, namaBankTransfer);
	}

	@Transactional
	@Override
	public String deleteMasterBankTransfer(String kdBankTransfer) {
		// TODO Auto-generated method stub		
		
		boolean val = masterBankTransferRepository.valDelBank(kdBankTransfer);
		String message = "";
		
		if (val == true) {
			message = "X";
		} else {						
			masterBankTransferRepository.deleteMasterBankTransfer(kdBankTransfer);
			message = "Y";
		}
		
		return message;
	}

}
