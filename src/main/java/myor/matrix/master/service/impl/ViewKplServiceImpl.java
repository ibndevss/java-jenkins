package myor.matrix.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ViewKplDto;
import myor.matrix.master.repository.ViewKplRepository;
import myor.matrix.master.service.ViewKplService;

@Service
public class ViewKplServiceImpl implements ViewKplService {
	
	@Autowired
	private ViewKplRepository repository;

	@Override
	public List<ViewKplDto> getDataDetail(String slsNo, String tgl, String chooseData) {
		// TODO Auto-generated method stub
		List<ViewKplDto> result = new ArrayList<>();
		if(chooseData.equalsIgnoreCase("0")) {
			result = repository.getDataDetailAll(slsNo,tgl);
		} else {
			result = repository.getDataDetailPcodeKpl(slsNo,tgl);
		}
		return result;
	}


	
}
