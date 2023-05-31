package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ProsesKonfirmasiNooDto;

public interface ProsesKonfirmasiNooRepository {

	public String getXkeyString(String table, String column, String where);
	
	public String getXkeyStringJoin(String table, String column, String join, String where);
	
	public String getFromDualString(String table, String column, String where);

	public List<Object[]> getXkeyListObject(String table, String column, String where);
	
	public List<Object[]> getXkeyListObjectJoin(String table, String column, String join, String where);
	
	public List<Object[]> getListObjectQuery(String query);
	
	public void deleteData(String table, String where);

	public void updateData(String table, String values, String where);

	public void insertData(String table, String column, String values);
	
	public void insertDataSelect(String table, String select, String where);
	
	public List<ProsesKonfirmasiNooDto> getListData(String tglRegister);
	
}
