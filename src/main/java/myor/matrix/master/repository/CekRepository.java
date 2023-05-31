package myor.matrix.master.repository;

import java.math.BigDecimal;
import java.util.List;

import myor.matrix.master.entity.CekAllDto;
import myor.matrix.master.entity.CekBankChoosen;
import myor.matrix.master.entity.CekChoosen;
import myor.matrix.master.entity.CekCustChoosen;
import myor.matrix.master.entity.CekCustDto;
import myor.matrix.master.entity.CekDto;
import myor.matrix.master.entity.CekNoCheck;
import myor.matrix.master.entity.SelectItem;

public interface CekRepository {

	public List<CekDto> getDataCek();
	
	public CekChoosen getCekChoosen(String checkNo);
	
	public List<CekBankChoosen> getCekBank(String bankNo);
	
	public List<CekBankChoosen> getCekBankCollection(String bankNo);
	
	public List<SelectItem<String>> getDataBank();
	
	public List<CekCustChoosen> getCekCust(String custNo);
	
	public List<CekCustDto> getDataCust();
	
	public List<SelectItem<String>> getDataBankCollection();
	
	public void insertDataCek(String checkNo, String checkDate, String checkDuedT, String bank,
			String accNo, String checkAmount, String custNo, String flag, String bankCair, String userId);
	
	public void insertDataTransfer(String checkNo, String checkDate, String bank,
			String accNo, String checkAmount, String custNo, String flag, String bankTransfer);
	
	public int getMemoint();
	
	public void updateMemoint(String T);
	
	public String getDateNowBySystem();
	
	public String getDateNowByWarehouse();
	
	public String getMemointTransfer();
	
	public String getTimeNowBySystem();
	
	public String getUser();
	
	public List<CekAllDto> getDataAllCek();
	
	public void deleteDataCek(String checkNo);
	
	public void updateDataCek (String checkNo, String checkDate, String checkDuedT, String bank,
			String accNo, String checkAmount, String custNo, String flag, String bankCair, String bankTransfer);
	
	String getTransferNo (String checkNo);
	
	String getCheckNo (String checkNo);
	
	List<SelectItem<String>> getDateCek (String checkNo);
	
	int cekPenggunaan(String tableName, String noSeq);
	
	void insertPenggunaan(String tableName, String noSeq, String userId);
	
	void deletePenggunaan(String tableName, String noSeq, String userId);
	
	public String getKeyAuth();
	
	public String getKeyOk(String key, String auth);

}
