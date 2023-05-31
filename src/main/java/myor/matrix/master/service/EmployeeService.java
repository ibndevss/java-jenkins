package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.DriverBrowseDto;
import myor.matrix.master.entity.HelperBrowseDto;
import myor.matrix.master.entity.PejabatBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface EmployeeService {
	
	public List<PejabatBrowseDto> getBrowsePejabat();

	public List<DriverBrowseDto> getBrowseDriver();

	public List<HelperBrowseDto> getBrowseHelper();
	
	public List<SelectItem<String>> getListKolektor();

}
