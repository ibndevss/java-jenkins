package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.CreateKplListOutletDto;

public interface CreateKplRepository {

	public String getXkeyString(String column, String table, String where);
	
	public String getFromDualString(String column, String table, String where);

	public List<Object[]> getXkeyListObject(String column, String table, String where);
	
	public List<Object[]> getFCycle(String edtTglGudang);
	
	public String getDay(String DateTglGudang);
	
	public List<CreateKplListOutletDto> getDaftarOutlet(String edtSlsNo, String edtHari);
	
	public void deleteData(String table, String where);

	public void updateData(String table, String values, String where);

	public void insertData(String table, String column, String value, String where);
	
	public List<Object[]> cekPengcoveran(String edtTglGudangplus1, String edtSlsNo);
	
	public List<Object[]> cekPengcoveranbyMonthly(String edtSlsNo);
	
	public void deleteFtabel46(String edtTglGudangplus1, String edtSlsNo);
	
	
	
	
}
