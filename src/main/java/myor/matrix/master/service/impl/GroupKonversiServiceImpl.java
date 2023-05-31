package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.GroupKonversiRepository;
import myor.matrix.master.service.GroupKonversiService;
@Service
public class GroupKonversiServiceImpl implements GroupKonversiService {

	@Autowired
	private GroupKonversiRepository groupKonversiRepository;
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return groupKonversiRepository.getBrowse();
	}

}
