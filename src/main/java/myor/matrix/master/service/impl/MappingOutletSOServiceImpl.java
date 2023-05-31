package myor.matrix.master.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.MappingOutletSOBrowseDto;
import myor.matrix.master.entity.MappingOutletSODto;
import myor.matrix.master.repository.MappingOutletSORepository;
import myor.matrix.master.service.MappingOutletSOService;
import myor.matrix.master.service.UtilService;

@Service
public class MappingOutletSOServiceImpl implements MappingOutletSOService {
	
	@Autowired
	private MappingOutletSORepository mappingOutletSORepository;
	
	@Autowired
	private UtilService utilService;
	
	@Override
	public List<MappingOutletSOBrowseDto> getBrowseSOSAP() {
		return mappingOutletSORepository.getBrowseSOSAP();
	}
	
	@Override
	public List<MappingOutletSODto> getDataMapping() {
		return mappingOutletSORepository.getDataMapping();
	}
	
	@Override
	public List<MappingOutletSODto> getDataMappingByCustSOSAP(String custNo) {
		return mappingOutletSORepository.getDataMappingByCustSOSAP(custNo);
	}
	
	@Transactional
	@Override
	public void deleteMappingOutlet(String custNoSO) {
		mappingOutletSORepository.deleteMappingOutlet(custNoSO);
	}
	
	@Transactional
	@Override
	public void insertMappingOutlet(String custNoSO, String custNameSO, String addressSO, String custNo) {
		String dateWarehouse = utilService.getDateNowByWarehouse();		
		mappingOutletSORepository.insertMappingOutlet(custNoSO, custNameSO, addressSO, custNo, dateWarehouse);
	}
	
	@Transactional
	@Override
	public void updateMappingOutlet(String custNoSO, String custNameSO, String addressSO, String custNo) {
		String dateWarehouse = utilService.getDateNowByWarehouse();	
		mappingOutletSORepository.updateMappingOutlet(custNoSO, custNameSO, addressSO, custNo, dateWarehouse);
	}

}
