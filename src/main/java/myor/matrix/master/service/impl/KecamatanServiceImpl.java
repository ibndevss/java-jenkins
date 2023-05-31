package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.KecamatanRepository;
import myor.matrix.master.service.KecamatanService;
@Service
public class KecamatanServiceImpl implements KecamatanService {

	@Autowired
	private KecamatanRepository kecamatanRepository;
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return kecamatanRepository.getBrowse();
	}

}
