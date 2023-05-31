package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SubbrandBrowseDto;

public interface SubBrandProductRepository {
	public List<SelectItem<String>> getListSubBrand();
	public List<SubbrandBrowseDto> getBrowseSubBrand(String umbrellaFrom, String umbrellaTo, String brandFrom, String brandTo);
}
