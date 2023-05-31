package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.CNDNDto;
import myor.matrix.master.entity.SelectItem;

public interface CNDNRepository {

	
	public List<SelectItem<String>> getListCNDN();
	
	public List<CNDNDto> browseCNDN();
}
