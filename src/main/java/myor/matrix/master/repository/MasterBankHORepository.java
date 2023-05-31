package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.MasterBankHODto;

public interface MasterBankHORepository {
	public List<MasterBankHODto> getDataBankHO();

	public void insertMasterBankHO(String kdBank, String namaBank);

	public void updateMasterBankHO(String kdBank, String namaBank);

	public void deleteMasterBankHO(String kdBank);
	
	public boolean valDelBank(String kdBank);

}
