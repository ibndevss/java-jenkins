package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.TypeOutletBrowseDto;
import myor.matrix.master.repository.TipeOutletRepository;
import myor.matrix.master.service.TipeOutletService;
@Service
public class TipeOutletServiceImpl implements TipeOutletService {

	@Autowired
	private TipeOutletRepository tipeOutletRepository;
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return tipeOutletRepository.getBrowse();
	}
	
	@Override
	public List<TypeOutletBrowseDto> browseTypeOutlet() {
		// TODO Auto-generated method stub
		return tipeOutletRepository.browseTypeOutlet();
	}

}
