package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.MasterBankDto;

public interface MasterBankService {
	public List<MasterBankDto> getDataBank();

	public void insertMasterBank(String kdBank, String namaBank);

	public void updateMasterBank(String kdBank, String namaBank);

	public String deleteMasterBank(String kdBank);
}
