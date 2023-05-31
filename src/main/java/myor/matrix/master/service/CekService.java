package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.CekAllDto;
import myor.matrix.master.entity.CekBankChoosen;
import myor.matrix.master.entity.CekChoosen;
import myor.matrix.master.entity.CekCustChoosen;
import myor.matrix.master.entity.CekCustDto;
import myor.matrix.master.entity.CekDto;
import myor.matrix.master.entity.CekNoCheck;
import myor.matrix.master.entity.SelectItem;

public interface CekService {
	
	public List<CekDto> getDataCek();
	
	public CekChoosen getCekChoosen(String checkNo);
	
	public List<SelectItem<String>> getDataBank();
	
	public List<CekCustDto> getDataCust();
	
	public List<SelectItem<String>> getDataBankCollection();
	
	public List<CekAllDto> insertDataCek(String checkNo, String checkDate, String checkDuedT, String bank,
			String accNo, String checkAmount, String custNo, String flag, String bankCair, String userId);
	
	public List<CekAllDto> insertDataTransfer(String checkNo, String checkDate, String bank, String accNo, String checkAmount,
			String custNo, String flag, String bankTransfer);
	
	public List<CekAllDto> getDataAllCek();
	
	public List<CekBankChoosen> getCekBank(String bankNo);
	
	public List<CekBankChoosen> getCekBankCollection(String bankNo);
	
	public List<CekCustChoosen> getCekCust(String custNo);
	
	public void deleteDataCek(String checkNo);
	
	public CekChoosen updateDataCek(CekChoosen dataCek);
	
	String getTransferNo (String checkNo);
	
	String getCheckNo (String checkNo);
	
	List<SelectItem<String>> getDateCek(String checkNo);
	
	void insertPenggunaan(String tableName, String noSeq, String userId);
	
	public String getMemointPatamTf();
	
	public int getKeyAuth();
	
	public String getKeyOk(String key, String auth);
	
	String getDateNowBySystem();
	

}
