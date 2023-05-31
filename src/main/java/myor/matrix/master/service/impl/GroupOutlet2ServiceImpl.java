package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.GroupOutlet2Repository;
import myor.matrix.master.service.GroupOutlet2Service;
@Service
public class GroupOutlet2ServiceImpl implements GroupOutlet2Service {

	@Autowired
	private GroupOutlet2Repository groupOutlet2Repository;
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return groupOutlet2Repository.getBrowse();
	}

}
