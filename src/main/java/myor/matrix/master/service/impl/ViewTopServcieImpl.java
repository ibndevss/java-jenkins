package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ViewTopDto;
import myor.matrix.master.repository.ViewTopRepository;
import myor.matrix.master.service.ViewTopService;

@Service
public class ViewTopServcieImpl implements ViewTopService {

	@Autowired
	private ViewTopRepository repository;

	@Override
	public List<ViewTopDto> getListTop() {
		// TODO Auto-generated method stub
		return repository.getListTop();
	}

	@Override
	public List<ViewTopDto> getListTopByTgl() {
		// TODO Auto-generated method stub
		return repository.getListTopOrderByTgl();
	}

	@Override
	public List<ViewTopDto> getListTopByTipe() {
		// TODO Auto-generated method stub
		return repository.getListTopOrderByTipe();
	}
	
}
