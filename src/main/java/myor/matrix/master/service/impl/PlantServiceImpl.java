package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.PlantDto;
import myor.matrix.master.repository.PlantRepository;
import myor.matrix.master.service.PlantService;

@Service
public class PlantServiceImpl implements PlantService {

	@Autowired
	PlantRepository repository;
	
	@Override
	public List<PlantDto> getListPlant() {
		// TODO Auto-generated method stub
		return repository.getListPlant();
	}

}
