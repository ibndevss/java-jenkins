package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ProsesNooBrowseDto;
import myor.matrix.master.entity.ProsesNooDataProsesDto;
import myor.matrix.master.entity.ProsesNooDetailDto;
import myor.matrix.master.entity.ProsesNooEditDto;
import myor.matrix.master.entity.ProsesNooInputDto;
import myor.matrix.master.entity.ProsesNooRekapDto;
import myor.matrix.master.entity.ProsesNooFppDto;

public interface ProsesNooRepository {

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
	
	public List<ProsesNooBrowseDto> getBrowseDocument();
	
	public List<ProsesNooBrowseDto> getBrowseDataDocument();
	
	public List<ProsesNooDetailDto> getDetailNoo(String docNo);
	
	public String getQueryCekData(ProsesNooInputDto p);
	
	public List<ProsesNooFppDto> getDataPrintFpp(String docNo, String path);
	
	public List<ProsesNooRekapDto> getDataPrintRekap(String docNo, String docDate, String reqKey);
	
	public List<ProsesNooDataProsesDto> getDataProcess(String docNo);
	
	public String cekCustNo(String groupDivisi);
	
	public ProsesNooEditDto getDataFtable141(String docNo);
}
