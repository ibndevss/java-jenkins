package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.GroupOutletRepository;
import myor.matrix.master.service.GroupOutletService;
@Service
public class GroupOutletServiceImpl implements GroupOutletService {

	@Autowired
	private GroupOutletRepository groupOutletRepository;
	
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return groupOutletRepository.getBrowse();
	}

}
