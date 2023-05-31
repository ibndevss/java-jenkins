package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.repository.KabupatenRepository;
import myor.matrix.master.service.KabupatenService;
@Service
public class KabupatenServiceImpl implements KabupatenService, KabupatenRepository {

	@Autowired
	private KabupatenRepository kabupatenRepository;
	@Override
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return kabupatenRepository.getBrowse();
	}

}
