package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.BrandBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.BrandProductRepository;
import myor.matrix.master.service.BrandProductService;

@Service
public class BrandProductServiceImpl implements BrandProductService {

	@Autowired
	private BrandProductRepository brandRepository;
	
	@Override
	public List<SelectItem<String>> getListBrand() {
		// TODO Auto-generated method stub
		return brandRepository.getListBrand();
	}

	@Override
	public List<BrandBrowseDto> getBrowseBrand(String umbrellaFrom, String umbrellaTo) {
		// TODO Auto-generated method stub
		return brandRepository.getBrowseBrand(umbrellaFrom, umbrellaTo);
	}

}
