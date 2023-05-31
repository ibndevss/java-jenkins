package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.PropinsiRepository;
import myor.matrix.master.service.PropinsiService;
@Service
public class PropinsiServiceImpl implements PropinsiService {

	@Autowired
	private PropinsiRepository propinsiRepository;
	
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return propinsiRepository.getBrowse();
	}

}
