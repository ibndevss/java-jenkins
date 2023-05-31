package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.GroupPluRepository;
import myor.matrix.master.service.GroupPluService;
@Service
public class GroupPluServiceImpl implements GroupPluService {

	@Autowired
	private GroupPluRepository groupPluRepository;
	
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return groupPluRepository.getBrowse();
	}

}
