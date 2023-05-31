package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SalesmanChoosenDto;
import myor.matrix.master.entity.SelectItem;

public interface SalesmanRepository {
	
	public List<SelectItem<String>> getListSalesmanSelectItem(String oprType);
	
	public SalesmanChoosenDto getSalesmanChoosen(String slsId);

	public List<SelectItem<String>> getDataSalesCanvas();
	
	public List<SelectItem<String>> getListSalesforce();
	
	public List<SalesmanBrowseDto> getBrowseSalesman(String oprType);

	public List<SalesmanChoosenDto> getListSalesmanViewKpl();
	
	public List<SalesmanChoosenDto> getListSalesmanChoosenBy(String salesBy);
	
	public String getSalesforce(String slsNo);
	
	public List<SalesmanBrowseDto> getBrowseInPengajuanRetur(String WHTRANSISI);
	public List<SalesmanBrowseDto> getSalesmanCover(String custno);
	
	public List<SelectItem<String>> getListSalesmanSalesByTeam(String team); 

	public List<SalesmanBrowseDto> getSalesmanLaporanPiutangDagang();
	
	public List<SelectItem<String>> getListSalesman(); 
	
	public List<SalesmanBrowseDto> getSalesmanValidasiKTP();
}
