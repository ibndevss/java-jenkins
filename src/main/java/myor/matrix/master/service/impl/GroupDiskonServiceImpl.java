package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.GroupDiskonRepository;
import myor.matrix.master.service.GroupDiskonService;
@Service
public class GroupDiskonServiceImpl implements GroupDiskonService {

	@Autowired
	private GroupDiskonRepository groupDiskonRepository;
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return groupDiskonRepository.getBrowse();
	}

}
