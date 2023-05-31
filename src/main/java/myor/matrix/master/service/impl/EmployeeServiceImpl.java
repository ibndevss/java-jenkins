package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.DriverBrowseDto;
import myor.matrix.master.entity.HelperBrowseDto;
import myor.matrix.master.entity.PejabatBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.EmployeeRepository;
import myor.matrix.master.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<PejabatBrowseDto> getBrowsePejabat() {
		// TODO Auto-generated method stub
		return employeeRepository.getBrowsePejabat();
	}

	@Override
	public List<DriverBrowseDto> getBrowseDriver() {
		// TODO Auto-generated method stub
		return employeeRepository.getBrowseDriver();
	}

	@Override
	public List<HelperBrowseDto> getBrowseHelper() {
		// TODO Auto-generated method stub
		return employeeRepository.getBrowseHelper();
	}

	@Override
	public List<SelectItem<String>> getListKolektor() {
		// TODO Auto-generated method stub
		return employeeRepository.getListKolektorSelectItem();
	}

}
