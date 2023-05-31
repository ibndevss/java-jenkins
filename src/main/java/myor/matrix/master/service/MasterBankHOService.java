package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.MasterBankHODto;

public interface MasterBankHOService {
	public List<MasterBankHODto> getDataBankHO();

	public void insertMasterBankHO(String kdBank, String namaBank);

	public void updateMasterBankHO(String kdBank, String namaBank);

	public String deleteMasterBankHO(String kdBank);

}
