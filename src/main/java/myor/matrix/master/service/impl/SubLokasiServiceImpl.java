package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.SubLokasiRepository;
import myor.matrix.master.service.SubLokasiService;

@Service
public class SubLokasiServiceImpl implements SubLokasiService {

    @Autowired
	private SubLokasiRepository repoSubLokasi;
	
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowseSubLokasi() {
		// TODO Auto-generated method stub
		return repoSubLokasi.getBrowseSubLokasi();
	}

}
