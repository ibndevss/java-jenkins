package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.DriverBrowseDto;
import myor.matrix.master.entity.HelperBrowseDto;
import myor.matrix.master.entity.PejabatBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.EmployeeService;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(path = "/browse-pej")
	public List<PejabatBrowseDto> getBrowsePejabat(){
		return employeeService.getBrowsePejabat();
	}
	
	@GetMapping(path = "/browse-driver")
	public List<DriverBrowseDto> getBrowseDriver(){
		return employeeService.getBrowseDriver();
	}
	
	@GetMapping(path = "/browse-helper")
	public List<HelperBrowseDto> getBrowseHelper(){
		return employeeService.getBrowseHelper();
	}
	
	@GetMapping(path = "/select-item-col")
	public List<SelectItem<String>> getListKolektor(){
		return employeeService.getListKolektor();
	}
}
