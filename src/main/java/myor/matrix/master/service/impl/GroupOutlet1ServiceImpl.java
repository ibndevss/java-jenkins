package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.GroupOutlet1Repository;
import myor.matrix.master.service.GroupOutlet1Service;
@Service
public class GroupOutlet1ServiceImpl implements GroupOutlet1Service {

	@Autowired
	private GroupOutlet1Repository groupOutlet1Repository; 
	@Override
	public List<SearchBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return groupOutlet1Repository.getBrowse();
	}

}
