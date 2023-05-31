package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.SubRuteRepository;
import myor.matrix.master.service.SubRuteService;
@Service
public class SubRuteServiceImpl implements SubRuteService {

	@Autowired
	private SubRuteRepository subRuteRepository;
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return subRuteRepository.getBrowse();
	}

}
