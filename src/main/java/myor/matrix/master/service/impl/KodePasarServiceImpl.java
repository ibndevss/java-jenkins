package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.KodePasarRepository;
import myor.matrix.master.service.KodePasarService;

@Service
public class KodePasarServiceImpl implements KodePasarService {

	@Autowired
	private KodePasarRepository repoKodePasar;
	
	@Override
	public List<SearchBrowseDto> getBrowseKodePasar() {
		// TODO Auto-generated method stub
		return repoKodePasar.getBrowseKodePasar();
	}

}
