package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.MappingOutletPrincipalDto;

public interface MappingOutletPrincipalService {

	List<MappingOutletPrincipalDto> getDataMapping();

	List<MappingOutletPrincipalDto> getDataMappingByCustNo(String custNo);

	void insertMappingOutletPrincipal(String custNo, String custName, String address);

	void deleteMappingOutletPrincipal(String custNo);

}
