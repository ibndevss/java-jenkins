package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.GroupHargaRepository;
import myor.matrix.master.service.GroupHargaService;
@Service
public class GroupHargaServiceImpl implements GroupHargaService {

	@Autowired
	private GroupHargaRepository groupHargaRepository;
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return groupHargaRepository.getBrowse();
	}

}
