package myor.matrix.master.service;

import java.math.BigDecimal;
import java.util.List;

import myor.matrix.master.entity.ConvUnitPcodeDto;
import myor.matrix.master.entity.DetailDate;
import myor.matrix.master.entity.SelectItem;

public interface UtilService {

	public String getDateNowBySystem();

	public String getDateNowByWarehouse();

	public String cekParam(String xkey);

	public List<ConvUnitPcodeDto> getConvUnit(String pcode);

	public String getAntrianNoSeq(String tableName, String field, String noSeq, String xkey, String userId, boolean withDelete);

	public void insertPenggunaan(String tableName, String noSeq, String userId);
	
	public List<SelectItem<String>> getPilihanOtorisasiTop();

	public String getXqty(String pcode, String qty);

	public DetailDate getDateDetail(String date);
	
	public String getAntrianRO(String userId);
	
	public String getAntrianStockOutlet(String userId);
	
	public String getTimeNow();
	
	public String getSequence(String sequenceName, String fieldName, String tableName);
	
	public String getXkey (int x, int y, String xjam);
	
	public List<String> getXkeyAndAuth (int x, int y, String xjam);
	
	public String cekKey (int x, int y, String kodeKey, String kodeAuth);
	
	public List<String> cekValAndKey38 (int x, int y, String kodeKey, String kodeAuth);
	
	public Boolean dmCheckPola(String pola_k, String edtWeek);

	String getDateNowByWarehouseFormatYYYMMDD();

	void deleteFprgactByNamaTableKey(String namaTable, String key);

	int cekPenggunaan(String tableName, String noSeq);

	List<String> cekPenggunaanDetail(String tableName, String noSeq, String user);

	void deleteFprgactByNamaTableAndUser(String namaTable, String user);
	
	String generateBarcode(String xstr);

}
