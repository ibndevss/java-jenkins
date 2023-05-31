package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.DistrikRepository;
import myor.matrix.master.service.DistrikService;
@Service
public class DistrikServiceImpl implements DistrikService {

	@Autowired
	private DistrikRepository distrikRepository;
	
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return distrikRepository.getBrowse();
	}

}
