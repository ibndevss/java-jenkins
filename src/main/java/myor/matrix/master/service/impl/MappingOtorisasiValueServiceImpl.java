package myor.matrix.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.MappingOtorisasiValueDTO;
import myor.matrix.master.repository.MappingOtorisasiValueRepository;
import myor.matrix.master.service.MappingOtorisasiValueService;

@Service
public class MappingOtorisasiValueServiceImpl implements MappingOtorisasiValueService{
	
	@Autowired
	private MappingOtorisasiValueRepository mappingOtorisasiValueRepository;
	
	@Override
	public List<MappingOtorisasiValueDTO> inisialisasiMapping() {
		// TODO Auto-generated method stub
		List<MappingOtorisasiValueDTO> result = new ArrayList();
		
		//Cek Table Mapping Otorisasi
		Boolean cekTable = true;
		try {
			cekTable = mappingOtorisasiValueRepository.cekTableMapping();
		} catch (Exception e) {
			// TODO: handle exception
			cekTable = false;
		}
		
		//Create or Select Mapping Otorisasi
		if(cekTable == false) {
			try {
				mappingOtorisasiValueRepository.createTableMapping();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			result = mappingOtorisasiValueRepository.getDataMapping();
		}
		
		return result;
	}
	
	@Transactional
	@Override
	public List<MappingOtorisasiValueDTO> deleteMapping(String pejabatId, List<String> teams) {
		// TODO Auto-generated method stub
		mappingOtorisasiValueRepository.deleteMapping(pejabatId, teams);
		List<MappingOtorisasiValueDTO> result = mappingOtorisasiValueRepository.getDataMapping();
		return result;
	}
	
	@Transactional
	@Override
	public List<MappingOtorisasiValueDTO> insertMapping(Integer nilaiFrom, Integer nilaiTo, List<String> teams,
			String pejabatId, String pejabatName) {
		// TODO Auto-generated method stub
		for (String team : teams) {
			mappingOtorisasiValueRepository.insertDataMapping(nilaiFrom, nilaiTo, team, pejabatId, pejabatName);
		}
		
		List<MappingOtorisasiValueDTO> result = mappingOtorisasiValueRepository.getDataMapping();
		return result;
	}
	
}
