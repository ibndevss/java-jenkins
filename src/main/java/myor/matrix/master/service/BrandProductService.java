package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.BrandBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface BrandProductService {
	public List<SelectItem<String>> getListBrand();
	public List<BrandBrowseDto> getBrowseBrand(String umbrellaFrom, String umbrellaTo);
}
