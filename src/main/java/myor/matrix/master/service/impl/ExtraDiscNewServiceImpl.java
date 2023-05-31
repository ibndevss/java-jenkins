package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiskonBertingkatDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscApHirDto;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.repository.ExtraDiscNewRepository;
import myor.matrix.master.service.ExtraDiscNewService;

@Service
public class ExtraDiscNewServiceImpl implements ExtraDiscNewService {
	@Autowired
	ExtraDiscNewRepository extraDiscNewRepo;
	
	@Override
	public DiscViewDto getExtraDiscNew(String ap) {
		// TODO Auto-generated method stub
		return extraDiscNewRepo.getExtraDiscNew(ap);
	}

	@Override
	public List<DiscApBrowseDto> getExtraDiscApBrowse() {
		// TODO Auto-generated method stub
		return extraDiscNewRepo.getExtraDiscApBrowse();
	}

	@Override
	public DiscApHirDto getApHir(String ap) {
		// TODO Auto-generated method stub
		return extraDiscNewRepo.getApHir(ap);
	}

	@Override
	public List<DiskonBertingkatDto> getDiskonBertingkat(String ap) {
		// TODO Auto-generated method stub
		return extraDiscNewRepo.getDiskonBertingkat(ap);
	}

	@Override
	public List<ApExcludeDto> getApExclude(String ap) {
		// TODO Auto-generated method stub
		return extraDiscNewRepo.getApExclude(ap);
	}

}
