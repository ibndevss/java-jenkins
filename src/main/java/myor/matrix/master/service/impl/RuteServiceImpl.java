package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.RuteRepository;
import myor.matrix.master.service.RuteService;
@Service
class RuteServiceImpl implements RuteService {

	@Autowired
	private RuteRepository ruteRepository;
	
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return ruteRepository.getBrowse();
	}

}
