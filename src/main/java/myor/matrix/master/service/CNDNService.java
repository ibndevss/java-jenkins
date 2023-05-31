package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.CNDNDto;
import myor.matrix.master.entity.SelectItem;

public interface CNDNService {
	
	public List<SelectItem<String>> getListCnDn();
	
	public List<CNDNDto> browseCNDN();
}
