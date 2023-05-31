package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.UmbrellaBrowseDto;

public interface UmbrellaProductService {
	public List<SelectItem<String>> getListUmbrella();
	public List<UmbrellaBrowseDto> getBrowseUmbrella();
}
