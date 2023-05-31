package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.UmbrellaBrowseDto;
import myor.matrix.master.repository.UmbrellaProductRepository;
import myor.matrix.master.service.UmbrellaProductService;

@Service
public class UmbrellaProductServiceImpl implements UmbrellaProductService {

	@Autowired
	private UmbrellaProductRepository umbrellaRepository;
	
	@Override
	public List<SelectItem<String>> getListUmbrella() {
		// TODO Auto-generated method stub
		return umbrellaRepository.getListUmbrella();
	}

	@Override
	public List<UmbrellaBrowseDto> getBrowseUmbrella() {
		// TODO Auto-generated method stub
		return umbrellaRepository.getBrowseUmbrella();
	}

}
