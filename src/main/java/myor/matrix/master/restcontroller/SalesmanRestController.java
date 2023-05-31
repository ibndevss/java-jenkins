package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SalesmanChoosenDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.SalesmanService;

@RestController
@RequestMapping(path = "/salesman")
public class SalesmanRestController {
	
	@Autowired
	private SalesmanService salesmanService;
	
	@GetMapping(path = "/select-item",params = {"oprType"})
	public List<SelectItem<String>> getListSalesman(@RequestParam String oprType){
		return salesmanService.getListSelectItemSalesman(oprType);
	}
	
	@GetMapping(value = "/{sales-id}/choosen")
	public SalesmanChoosenDto getSalesmanChoosen(@PathVariable(name = "sales-id") String slsId){
		return salesmanService.getSalesmanChoosen(slsId);
	}
	
	@GetMapping(value = "/choosen")
	public List<SalesmanChoosenDto> getListSalesmanChoosen(){
		return salesmanService.getListSalesmanChoosen();
	}
	
	@GetMapping(value = "/choosen", params = {"salesBy"})
	public List<SalesmanChoosenDto> getListSalesmanChoosenBy(@RequestParam String salesBy){
		return salesmanService.getListSalesmanChoosenBy(salesBy);
	}
	
	@GetMapping(path = "/canvas")
	public List<SelectItem<String>> getDataSalesCanvas(){
		return salesmanService.getDataSalesCanvas();

	}
	
	@GetMapping(path = "/salesforce")
	public List<SelectItem<String>> getListSalesforce() {
		return salesmanService.getListSalesforce();
	}
	
	@GetMapping(path = "/browse",params = {"oprType"})
	public List<SalesmanBrowseDto> getBrowseSalesman(@RequestParam String oprType) {
		return salesmanService.getBrowseSalesman(oprType);
	}
	
	//Choose Salesman Module View KPL	
	@GetMapping(path="/select-items-view-kpl")
	public List<SalesmanChoosenDto> getListSalesmanViewKpl(){
		return salesmanService.getListSalesmanViewKpl();
	}
	
	@GetMapping(path = "/browse/pengajuan-retur",params = {"whtransisi"})
	public List<SalesmanBrowseDto> getBrowseSalesmanInPengajuanRetur(@RequestParam String whtransisi) {
		return salesmanService.getBrowseInPengajuanRetur(whtransisi);

	}
	
	@GetMapping(path="/cover",params= {"custno"})
	public List<SalesmanBrowseDto> getSalesmanCover(@RequestParam String custno) {
		return salesmanService.getSalesmanCover(custno);
	}
	
	@GetMapping(path = "/select-item",params = {"team"})
	public List<SelectItem<String>> getListSalesmanSalesByTeam(@RequestParam String team) {
		return salesmanService.getListSalesmanSalesByTeam(team);
	}

	@GetMapping(value = "/laporan-piutang-dagang")
	public List<SalesmanBrowseDto> getListSalesman(){
		return salesmanService.getSalesmanLaporanPiutangDagang();
	}
	
	@GetMapping(value = "/filter")
	public List<SelectItem<String>> getSalesman(){
		return salesmanService.getListSalesman();
	}
	
	@GetMapping(value = "/validasi-KTP")
	public List<SalesmanBrowseDto> getSalesmanValidasiKTP(){
		return salesmanService.getSalesmanValidasiKTP();
	}
		
}
