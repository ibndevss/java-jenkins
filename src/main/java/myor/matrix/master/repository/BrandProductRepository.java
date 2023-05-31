package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.BrandBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface BrandProductRepository {
	public List<SelectItem<String>> getListBrand();
	public List<BrandBrowseDto> getBrowseBrand(String umbrellaFrom, String umbrellaTo);
}
