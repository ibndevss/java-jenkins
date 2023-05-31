package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.DNBrowseDto;
import myor.matrix.master.repository.DNRepository;
import myor.matrix.master.service.DNService;

@Service
public class DNServiceImpl implements DNService {
	
	@Autowired
	DNRepository repository;
	
	@Override
	public List<DNBrowseDto> getListDN(String tglDN) {
		// TODO Auto-generated method stub
		return repository.listDN(tglDN);
	}

}
