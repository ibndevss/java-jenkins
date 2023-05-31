package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.ConvUnitPcodeDto;
import myor.matrix.master.entity.DetailDate;

public interface UtilRepository {

	public String getDateNowBySystem();

	public String getDateNowByWarehouse();

	public String cekParam(String xkey);

	public List<ConvUnitPcodeDto> getConvUnit(String pcode);

	public int cekTableFrgAct(String noSeq);

	public void insertTableFrgAct(String noSeq, String noSeq2, String userId);

	public int cekMemoInt(String xkey);

	public int cekFieldByTable(String field, String tableName, int cekMemoInt);

	public void updateFmemo(String xkey);

	public void delete(String noSeq);

	public String getNoDoc(String xkey);

	public int cekPenggunaan(String tableName, String noSeq);
	
	public boolean cekPenggunaanOtherUser(String tableName, String noSeq, String userName);

	public void insertPenggunaan(String tableName, String noSeq, String userId);

	public void deletePenggunaan(String tableName, String noSeq, String userId);

	public List<SelectItem<String>> getListFtable13ByXkey(String xkey);
	
	public String getXqty(String pcode, String qty);

	public DetailDate getDateDetail(String date);
	
	public String getMemostringFtable13(String xkey);
	
	public String getTimeNow();
	
	public long getNextVal(String sequenceName);
	
	public long getCurrtVal(String sequenceName);
	
	public boolean isDataExist(String fieldName, String tableName, long sequence);
	
	public boolean cekNpwpOutlet(String custNo);
	
	public boolean cekKtpOutlet(String custNo);
	
	public String getMemonamaFtable13(String xkey);
	
	public Double roundSql(Double value, String decPoint);

	String getDateNowByWarehouseFormatYYYMMDD();

	List<String> cekPenggunaanDetail(String tableName, String noSeq, String user);
	
	public String getTableString(String table, String column, String where);
	
	public String getTableStringJoin(String table, String column, String join, String where);
	
	public String getDualString(String table, String column, String where);

	public List<Object[]> getTableListObject(String table, String column, String where);
	
	public List<Object[]> getTableListObjectJoin(String table, String column, String join, String where);
	
	public List<Object[]> getTableListObjectQuery(String query);
	
	public void deleteData(String table, String where);

	public void updateData(String table, String values, String where);

	public void insertData(String table, String column, String values);
	
	public void insertDataSelect(String table, String select, String where);
}
