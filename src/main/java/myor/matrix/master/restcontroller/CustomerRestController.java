package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.CustomerBrowseDto;
import myor.matrix.master.entity.GroupOutletBrowseDto;
import myor.matrix.master.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(path = "/browse")
	public List<CustomerBrowseDto> getBrowseCustomer(){
		return customerService.getBrowseCustomer();
	}
	
	@GetMapping(path = "/browse-groupout")
	public List<GroupOutletBrowseDto> getBrowseGroupOutlet(){
		return customerService.getBrowseGroupOutlet();
	}
	
	@GetMapping(path = "/browse", params = {"slsNo"})
	public List<CustomerBrowseDto> getBrowseCustomerBySalesman(@RequestParam String slsNo){
		return customerService.getBrowseCustomerBySalesman(slsNo);
	}
	
	@GetMapping(path = "/browse-not-mapping-outlet-so")
	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutleSO() {
		return customerService.getBrowseCustomerNotMappingOutleSO();
	}
	
	@GetMapping( params = {"custNo"})
	public List<CustomerBrowseDto> getCustomerByCustNo(@RequestParam String custNo) {
		return customerService.getCustomerByCustNo(custNo);
	}
	
	@GetMapping(path = "/not-mapping-outlet-so", params = {"custNo"})
	public List<CustomerBrowseDto> getCustomerNotMappingOutleSOByCustNo(@RequestParam String custNo) {
		return customerService.getCustomerNotMappingOutleSOByCustNo(custNo);
	}
	
	@GetMapping(path = "/browse-not-mapping-outlet-principal")
	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipal() {
		return customerService.getBrowseCustomerNotMappingOutlePrincipal();
	}
	
	@GetMapping(path = "/not-mapping-outlet-principal", params = {"custNo"})
	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipalByCustNo(@RequestParam String custNo) {
		return customerService.getBrowseCustomerNotMappingOutlePrincipalByCustNo(custNo);
	}
	
	@GetMapping(path = "/browse/pengajuan-retur", params = {"slsNo"})
	public List<CustomerBrowseDto> getBrowseCustomerInPengajuanRetur(@RequestParam String slsNo) {
		return customerService.getBrowseCustomerInPengajuanRetur(slsNo);
	}
	
	@GetMapping(path = "/browse/laporan-umur-piutang")
	public List<CustomerBrowseDto> getBrowseCustomerLaporanUmurPiutang() {
		return customerService.getBrowseCustomerLaporanUmurPiutang();
	}
	
	@GetMapping(path="/browse/rekalkulasi-limit-kredit")
	public List<CustomerBrowseDto> getBrowseCustomerRekalkulasiLimitKredit() {
		return customerService.getBrowseCustomerRekalkulasiLimitKredit();
	}
	
	@GetMapping(path="/browse/laporan-piutang-dagang")
	public List<CustomerBrowseDto> getBrowseCustomerLaporanPiutangDagang() {
		return customerService.getBrowseCustomerLaporanPiutangDagang();
	}
	
	@GetMapping(path="/browse/view-photo-koord-outlet")
	public List<CustomerBrowseDto> getViewPhotoAndKoordOutlet() {
		return customerService.getBrowseCustomerViewPhotoAndKoordOutlet();
	}

}
