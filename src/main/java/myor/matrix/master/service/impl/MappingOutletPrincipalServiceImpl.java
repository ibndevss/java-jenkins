package myor.matrix.master.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.MappingOutletPrincipalDto;
import myor.matrix.master.repository.MappingOutletPrincipalRepository;
import myor.matrix.master.service.MappingOutletPrincipalService;
import myor.matrix.master.service.UtilService;

@Service
public class MappingOutletPrincipalServiceImpl implements MappingOutletPrincipalService {
	
	@Autowired
	private MappingOutletPrincipalRepository mappingOutletPrincipalRepository;
	
	@Autowired
	private UtilService utilService;
	
	@Override
	public List<MappingOutletPrincipalDto> getDataMapping() {
		return mappingOutletPrincipalRepository.getDataMapping();
	}
	
	@Override
	public List<MappingOutletPrincipalDto> getDataMappingByCustNo(String custNo) {
		return mappingOutletPrincipalRepository.getDataMappingByCustNo(custNo);
	}
	
	@Transactional
	@Override
	public void insertMappingOutletPrincipal(String custNo, String custName, String address) {	
		String dateWarehouse = utilService.getDateNowByWarehouse();
		mappingOutletPrincipalRepository.insertMappingOutletPrincipal(custNo, custName, address, dateWarehouse);
	}
	
	@Transactional
	@Override
	public void deleteMappingOutletPrincipal(String custNo) {
		mappingOutletPrincipalRepository.deleteMappingOutletPrincipal(custNo);
	}
}
