package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface KolektorRepository {

	public List<SelectItem<String>> getList();
}
