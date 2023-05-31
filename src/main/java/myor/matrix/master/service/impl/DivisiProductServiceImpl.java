package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.DivisiProductBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.DivisiProductRepository;
import myor.matrix.master.service.DivisiProductService;

@Service
public class DivisiProductServiceImpl implements DivisiProductService {

	@Autowired
	private DivisiProductRepository divisiRepository;
	
	@Override
	public List<SelectItem<String>> getListDivisi() {
		// TODO Auto-generated method stub
		return divisiRepository.getListDivisi();
	}

	@Override
	public List<DivisiProductBrowseDto> getBrowseDivisi() {
		// TODO Auto-generated method stub
		return divisiRepository.getBrowseDivisi();
	}

}
