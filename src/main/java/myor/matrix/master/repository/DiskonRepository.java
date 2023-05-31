package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SelectItem;

public interface DiskonRepository {
	
	public List<SelectItem<String>> getSelectItemMasterTactical();
	
	public List<SelectItem<String>> getSelectItemMasterCod();
	
}
