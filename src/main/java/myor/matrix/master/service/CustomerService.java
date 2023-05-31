package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.CustomerBrowseDto;
import myor.matrix.master.entity.GroupOutletBrowseDto;

public interface CustomerService {
	public List<CustomerBrowseDto> getBrowseCustomer();
	
	public List<GroupOutletBrowseDto> getBrowseGroupOutlet();
	
	public List<CustomerBrowseDto> getBrowseCustomerBySalesman(String slsNo);

	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutleSO();

	List<CustomerBrowseDto> getCustomerByCustNo(String custNo);

	List<CustomerBrowseDto> getCustomerNotMappingOutleSOByCustNo(String custNo);

	List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipal();

	List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipalByCustNo(String custNo);
	
	List<CustomerBrowseDto> getBrowseCustomerInPengajuanRetur(String slsNo);
	
	List<CustomerBrowseDto> getBrowseCustomerLaporanUmurPiutang();
	
	List<CustomerBrowseDto> getBrowseCustomerRekalkulasiLimitKredit();
	
	List<CustomerBrowseDto> getBrowseCustomerLaporanPiutangDagang();
	
	List<CustomerBrowseDto> getBrowseCustomerViewPhotoAndKoordOutlet();
}
