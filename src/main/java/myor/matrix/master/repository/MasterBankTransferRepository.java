package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.MasterBankDto;
import myor.matrix.master.entity.MasterBankTransferDto;

public interface MasterBankTransferRepository {
	public List<MasterBankTransferDto> getDataBankTransfer();	
	public void insertMasterBankTransfer (String kdBank,String namaBank);
	public void updateMasterBankTransfer (String kdBank,String namaBank);
	public void deleteMasterBankTransfer (String kdBank);
	public boolean valDelBank(String kdBank);
}
