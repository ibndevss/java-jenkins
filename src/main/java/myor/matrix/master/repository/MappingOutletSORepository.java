package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.MappingOutletSOBrowseDto;
import myor.matrix.master.entity.MappingOutletSODto;

public interface MappingOutletSORepository {

	List<MappingOutletSOBrowseDto> getBrowseSOSAP();

	List<MappingOutletSODto> getDataMapping();

	List<MappingOutletSODto> getDataMappingByCustSOSAP(String custNo);

	void deleteMappingOutlet(String custNoSO);

	void insertMappingOutlet(String custNoSO, String custNameSO, String addressSO, String custNo, String tglUpd);

	void updateMappingOutlet(String custNoSO, String custNameSO, String addressSO, String custNo, String tglUpd);

}
