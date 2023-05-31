package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.KelurahanRepository;
import myor.matrix.master.service.KelurahanService;
@Service
public class KelurahanServiceImpl implements KelurahanService {

	@Autowired
	private KelurahanRepository kelurahanRepository;
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return kelurahanRepository.getBrowse();
	}

}
