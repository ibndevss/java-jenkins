package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.DivisiProductBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface DivisiProductRepository {
	public List<SelectItem<String>> getListDivisi();
	public List<DivisiProductBrowseDto> getBrowseDivisi();
}
