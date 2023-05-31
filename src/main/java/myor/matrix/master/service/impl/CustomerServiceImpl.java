package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.CustomerBrowseDto;
import myor.matrix.master.entity.GroupOutletBrowseDto;
import myor.matrix.master.repository.CustomerRepository;
import myor.matrix.master.repository.UtilRepository;
import myor.matrix.master.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UtilRepository utilRepository;
	
	@Override
	public List<CustomerBrowseDto> getBrowseCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.getBrowseCustomer();
	}

	@Override
	public List<GroupOutletBrowseDto> getBrowseGroupOutlet() {
		// TODO Auto-generated method stub
		return customerRepository.getBrowseGroupOutlet();
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerBySalesman(String slsNo) {
		// TODO Auto-generated method stub
		String xmasterData = utilRepository.getMemostringFtable13("XMASTERDATA");
		return customerRepository.getBrowseCustomerBySalesman(slsNo, xmasterData);
	}
	
	@Override
	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutleSO() {
		return customerRepository.getBrowseCustomerNotMappingOutleSO();
	}
	
	@Override
	public List<CustomerBrowseDto> getCustomerByCustNo(String custNo) {
		return customerRepository.getCustomerByCustNo(custNo);
	}
	
	@Override
	public List<CustomerBrowseDto> getCustomerNotMappingOutleSOByCustNo(String custNo) {
		return customerRepository.getCustomerNotMappingOutleSOByCustNo(custNo);
	}
	
	@Override
	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipal() {
		return customerRepository.getBrowseCustomerNotMappingOutlePrincipal();
	}
	
	@Override
	public List<CustomerBrowseDto> getBrowseCustomerNotMappingOutlePrincipalByCustNo(String custNo) {
		return customerRepository.getBrowseCustomerNotMappingOutlePrincipalByCustNo(custNo);
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerInPengajuanRetur(String slsNo) {
		// TODO Auto-generated method stub
		return customerRepository.getBrowseCustomerInPengajuanRetur(slsNo);
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerLaporanUmurPiutang() {
		// TODO Auto-generated method stub
		return customerRepository.getBrowseCustomerLaporanUmurPiutang();
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerRekalkulasiLimitKredit() {
		// TODO Auto-generated method stub
		
		String XLIMITKREDIT = utilRepository.getMemostringFtable13("LIMITKREDITGP");
		
		
		return customerRepository.getBrowseCustomerRekalkulasiLimitKredt(XLIMITKREDIT);
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerLaporanPiutangDagang() {
		// TODO Auto-generated method stub
		return customerRepository.getBrowseCustomerLaporanPiutangDagang();
	}

	@Override
	public List<CustomerBrowseDto> getBrowseCustomerViewPhotoAndKoordOutlet() {
		// TODO Auto-generated method stub
		return customerRepository.getBrowseCustomerViewPhotoAndKoordOutlet();
	}

}
