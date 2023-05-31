package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.MasterBankDto;
import myor.matrix.master.entity.MasterBankTransferDto;

public interface MasterBankTransferService {
	public List<MasterBankTransferDto> getDataBankTransfer();

	public void insertMasterBankTransfer(String kdBankTransfer, String namaBankTransfer);

	public void updateMasterBankTransfer(String kdBankTransfer, String namaBankTransfer);

	public String deleteMasterBankTransfer(String kdBankTransfer);
}
