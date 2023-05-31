package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.DivisiProductBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface DivisiProductService {
	public List<SelectItem<String>> getListDivisi();
	
	public List<DivisiProductBrowseDto> getBrowseDivisi();
}
