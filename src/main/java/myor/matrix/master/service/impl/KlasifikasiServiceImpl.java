package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.KlasifikasiRepository;
import myor.matrix.master.service.KlasifikasiService;

@Service
public class KlasifikasiServiceImpl implements KlasifikasiService {

	@Autowired
	private KlasifikasiRepository repoKlasifikasi;
	
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowseKlasifikasi() {
		// TODO Auto-generated method stub
		return repoKlasifikasi.getBrowseKlasifikasi();
	}

}
