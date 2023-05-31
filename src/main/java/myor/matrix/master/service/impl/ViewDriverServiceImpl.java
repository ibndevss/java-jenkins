package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.ViewDriverDto;
import myor.matrix.master.repository.ViewDriverRepository;
import myor.matrix.master.service.ViewDriverService;

@Service
public class ViewDriverServiceImpl implements ViewDriverService {
	
	@Autowired
	private ViewDriverRepository repository;

	@Override
	public List<ViewDriverDto> getListDriver() {
		// TODO Auto-generated method stub
		return repository.getListDriver();
	}
	
	
}
