package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscApHirDto;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.entity.DiskonBertingkatDto;
import myor.matrix.master.repository.RegularDiscNewRepository;
import myor.matrix.master.service.RegularDiscNewService;

@Service
public class RegularDiscNewServiceImpl implements RegularDiscNewService {
	
	@Autowired
	RegularDiscNewRepository regularDiscNewRepository;

	@Override
	public DiscViewDto getRegularDiscNew(String ap) {
		// TODO Auto-generated method stub
		return regularDiscNewRepository.getRegularDiscNew(ap);
	}

	@Override
	public List<DiscApBrowseDto> getRegularDiscApBrowse() {
		// TODO Auto-generated method stub
		return regularDiscNewRepository.getRegularDiscApBrowse();
	}

	@Override
	public DiscApHirDto getApHir(String ap) {
		// TODO Auto-generated method stub
		return regularDiscNewRepository.getApHir(ap);
	}

	@Override
	public List<DiskonBertingkatDto> getDiskonBertingkat(String ap) {
		// TODO Auto-generated method stub
		return regularDiscNewRepository.getDiskonBertingkat(ap);
	}

	@Override
	public List<ApExcludeDto> getApExclude(String ap) {
		// TODO Auto-generated method stub
		return regularDiscNewRepository.getApExclude(ap);
	}
	

}
