package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.AlasanBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.MasterAlasanRepository;
import myor.matrix.master.service.MasterAlasanService;

@Service
public class MasterAlasanServiceImpl implements MasterAlasanService {

	@Autowired
	private MasterAlasanRepository repository;
	
	@Override
	public List<SelectItem<String>> getAlasan(String tipe) {
		// TODO Auto-generated method stub
		return repository.getAlasan(tipe);
	}

	@Override
	public List<SelectItem<String>> getAlasanNotEc() {
		// TODO Auto-generated method stub
		return repository.getAlasanNotEc();
	}

	@Override
	public List<AlasanBrowseDto> browseAlasan(String tipeAlasan) {
		// TODO Auto-generated method stub
		return repository.browseAlasan(tipeAlasan);
	}

	@Override
	public List<SelectItem<String>> getAlasanRetur(boolean reshuffle) {
		// TODO Auto-generated method stub
		return repository.getAlasanRetur(reshuffle);
	}

}
