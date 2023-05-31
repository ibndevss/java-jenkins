package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.OutletChainRepository;
import myor.matrix.master.service.OutletChainService;
@Service
public class OutletChainServiceImpl implements OutletChainService {

	@Autowired
	private OutletChainRepository outletChainRepository;
	
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return outletChainRepository.getBrowse();
	}

}
