package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SubbrandBrowseDto;
import myor.matrix.master.repository.SubBrandProductRepository;
import myor.matrix.master.service.SubBrandProductService;

@Service
public class SubBrandProductServiceImpl implements SubBrandProductService {

	@Autowired
	private SubBrandProductRepository subBrandRepository;
	
	@Override
	public List<SelectItem<String>> getListSubBrand() {
		// TODO Auto-generated method stub
		return subBrandRepository.getListSubBrand();
	}

	@Override
	public List<SubbrandBrowseDto> getBrowseSubBrand(String umbrellaFrom, String umbrellaTo, String brandFrom,
			String brandTo) {
		// TODO Auto-generated method stub
		return subBrandRepository.getBrowseSubBrand(umbrellaFrom, umbrellaTo, brandFrom, brandTo);
	}

}
