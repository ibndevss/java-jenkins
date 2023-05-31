package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SalesmanChoosenDto;
import myor.matrix.master.entity.SelectItem;

public interface SalesmanService {
	
	public List<SelectItem<String>> getListSelectItemSalesman(String oprType);
	
	public SalesmanChoosenDto getSalesmanChoosen(String slsId);

	public List<SelectItem<String>> getDataSalesCanvas();
	
	public List<SelectItem<String>> getListSalesforce();
	
	public List<SalesmanBrowseDto> getBrowseSalesman(String oprType);
	
	public List<SalesmanChoosenDto> getListSalesmanChoosen();
	
	public List<SalesmanChoosenDto> getListSalesmanChoosenBy(String salesBy);

	public List<SalesmanChoosenDto> getListSalesmanViewKpl();
	
	public List<SalesmanBrowseDto> getBrowseInPengajuanRetur(String WHTRANSISI);
	public List<SalesmanBrowseDto> getSalesmanCover(String custno);
	
	public List<SelectItem<String>> getListSalesmanSalesByTeam(String team);

    public List<SalesmanBrowseDto> getSalesmanLaporanPiutangDagang();
    
    public List<SelectItem<String>> getListSalesman();
    
    public List<SalesmanBrowseDto> getSalesmanValidasiKTP();
}
