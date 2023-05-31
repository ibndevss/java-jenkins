package myor.matrix.master.service;

import java.math.BigDecimal;
import java.util.List;

import myor.matrix.master.entity.DataSalesmanDto;
import myor.matrix.master.entity.MSalesmanAdd;
import myor.matrix.master.entity.MSalesmanChoosenDto;
import myor.matrix.master.entity.MSalesmanDto;
import myor.matrix.master.entity.MSalesmanKolektor;
import myor.matrix.master.entity.MSalesmanProductChoosen;
import myor.matrix.master.entity.SalesforceBrowseDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface MSalesmanService {
	
	String getDateNowBySystem();

	String getDateNowByWarehouse();
	
	String getRange();
	
	BigDecimal getSaran();
	
	void insertPenggunaan(String tableName, String noSeq, String userId);
	
	public List<SalesmanBrowseDto> getListSelectItemSalesman();
	
	public List<SelectItem<String>> getListTeam();
	public List<SelectItem<String>> getListRayon();
	public List<SalesforceBrowseDto> getListSalesforce();
	public List<Object[]> getListPejabat();
	public List<SelectItem<String>> getListGudang();
	public List<SelectItem<String>> getListKolektor();
	public List<SelectItem<String>> getListCustomer();
	public List<SelectItem<String>> getListSalesEmp();
	
	public MSalesmanDto getSalesmanDto(String slsno);
	public MSalesmanKolektor getKolektorChoosen(String empno); 
		
	public MSalesmanDto addSalesman(MSalesmanAdd mAddDataSales);
		
	public MSalesmanDto updateSalesman(MSalesmanAdd mUpdateSales);
	
	public MSalesmanDto blokSalesman(String slsno);
	
	public List<Object[]> getMasterFtable13(String param);
	
	public List<MSalesmanProductChoosen> getTeamProduct(String team);
	
	public String[] cekAddSalesman();
	public String[] addDataSalesman(DataSalesmanDto dataSalesman);
	public String[] updateDataSalesman(DataSalesmanDto d);
	
	public String[] cekEditSalesman(DataSalesmanDto p);
	
	public void deleteAntrian(DataSalesmanDto p);
	
	public String[] getCekNewNoSlsNo(String slsNo);
	public String[] getSuggestNewNoSalesman();
	
	public String[] cekBlokSalesman(DataSalesmanDto p);
	public String[] processBlokSalesman(DataSalesmanDto p);

	List<MSalesmanChoosenDto> getDataSalesmanSummary();
}
