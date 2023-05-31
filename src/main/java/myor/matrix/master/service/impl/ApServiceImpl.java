package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ApBrowseDto;
import myor.matrix.master.repository.ApRepository;
import myor.matrix.master.repository.UtilRepository;
import myor.matrix.master.service.ApService;

@Service
public class ApServiceImpl implements ApService {
	
	@Autowired
	private ApRepository apRepository;
	
	@Autowired
	private UtilRepository utilRepository;
	
	@Override
	public List<ApBrowseDto> getBrowseAp() {
		// TODO Auto-generated method stub
		String whDate = utilRepository.getDateNowByWarehouse();
		return apRepository.getBrowseApByDate(whDate);
	}

}
