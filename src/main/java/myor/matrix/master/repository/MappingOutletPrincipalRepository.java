package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.MappingOutletPrincipalDto;

public interface MappingOutletPrincipalRepository {

	List<MappingOutletPrincipalDto> getDataMapping();

	List<MappingOutletPrincipalDto> getDataMappingByCustNo(String custNo);

	void insertMappingOutletPrincipal(String custNo, String custName, String address, String tglUpd);

	void deleteMappingOutletPrincipal(String custNo);

}
