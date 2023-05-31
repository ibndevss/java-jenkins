package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.MasterBankDto;

public interface MasterBankRepository {
	public List<MasterBankDto> getDataBank();

	public void insertMasterBank(String kdBank, String namaBank);

	public void updateMasterBank(String kdBank, String namaBank);

	public void deleteMasterBank(String kdBank);
	
	public boolean valDelBank(String kdBank);
	
}
