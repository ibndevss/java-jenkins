package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.MappingOutletSOBrowseDto;
import myor.matrix.master.entity.MappingOutletSODto;

public interface MappingOutletSOService {

	List<MappingOutletSOBrowseDto> getBrowseSOSAP();

	List<MappingOutletSODto> getDataMapping();

	List<MappingOutletSODto> getDataMappingByCustSOSAP(String custNo);

	void deleteMappingOutlet(String custNoSO);

	void insertMappingOutlet(String custNoSO, String custNameSO, String addressSO, String custNo);

	void updateMappingOutlet(String custNoSO, String custNameSO, String addressSO, String custNo);

}
