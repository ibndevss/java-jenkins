package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.CustomerBrowseDto;
import myor.matrix.master.entity.GroupOutletBrowseDto;
import myor.matrix.master.entity.MasterOutletDataAttributeDto;

public interface CustomerRepository {
	public List<CustomerBrowseDto> getBrowseCustomer();
	
	public List<GroupOutletBrowseDto> getBrowseGroupOutlet();
	
	public List<CustomerBrowseDto> getBrowseCustomerBySalesman(String slsNo, String xmasterData);

	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutleSO();

	List<CustomerBrowseDto> getCustomerByCustNo(String custNo);

	List<CustomerBrowseDto> getCustomerNotMappingOutleSOByCustNo(String custNo);

	List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipalByCustNo(String custNo);

	List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipal();
	
	MasterOutletDataAttributeDto getCustomerAttribute(String custNo);
	
	List<CustomerBrowseDto> getBrowseCustomerInPengajuanRetur(String slsNo);
	
	List<CustomerBrowseDto> getBrowseCustomerLaporanUmurPiutang();
	
	List<CustomerBrowseDto> getBrowseCustomerRekalkulasiLimitKredt(String XLIMITKREDIT);
	
	List<CustomerBrowseDto> getBrowseCustomerLaporanPiutangDagang();
	
	List<CustomerBrowseDto> getBrowseCustomerViewPhotoAndKoordOutlet();
	
}
