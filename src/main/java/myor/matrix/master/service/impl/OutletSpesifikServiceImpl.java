package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.OutletSpesifikRepository;
import myor.matrix.master.service.OutletSpesifikService;
@Service
public class OutletSpesifikServiceImpl implements OutletSpesifikService {

	@Autowired
	private OutletSpesifikRepository outletSpesifikRepository;
	
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return outletSpesifikRepository.getBrowse();
	}

}
