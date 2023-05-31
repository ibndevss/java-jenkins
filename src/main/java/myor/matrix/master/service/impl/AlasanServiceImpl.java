package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.AlasanBrowseDto;
import myor.matrix.master.repository.AlasanRepository;
import myor.matrix.master.service.AlasanService;

@Service
public class AlasanServiceImpl implements AlasanService {

	@Autowired
	private AlasanRepository repoAlasan;
	
	@Override
	public List<AlasanBrowseDto> getBrowse() {
		// TODO Auto-generated method stub
		return repoAlasan.getBrowse();
	}

}
