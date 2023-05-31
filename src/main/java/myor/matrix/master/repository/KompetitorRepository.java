package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SelectItem;

public interface KompetitorRepository {
	
	public List<SelectItem<String>> getBrowseKompetitor();
	
}
