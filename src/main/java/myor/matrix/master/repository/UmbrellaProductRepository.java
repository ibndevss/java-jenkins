package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.UmbrellaBrowseDto;

public interface UmbrellaProductRepository {
	public List<SelectItem<String>> getListUmbrella();
	public List<UmbrellaBrowseDto> getBrowseUmbrella();
}
