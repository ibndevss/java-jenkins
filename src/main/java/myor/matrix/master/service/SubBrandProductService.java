package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SubbrandBrowseDto;

public interface SubBrandProductService {
	public List<SelectItem<String>> getListSubBrand();
	public List<SubbrandBrowseDto> getBrowseSubBrand(String umbrellaFrom, String umbrellaTo, String brandFrom, String brandTo);
}
