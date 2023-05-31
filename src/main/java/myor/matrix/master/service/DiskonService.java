package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.SelectItem;

public interface DiskonService {
	
	public List<SelectItem<String>> getSelectItemMasterTactical();
	
	public List<SelectItem<String>> getDataMasterCod();
	
}
