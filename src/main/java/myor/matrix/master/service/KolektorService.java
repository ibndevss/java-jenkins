package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.SelectItem;

public interface KolektorService {
	public List<SelectItem<String>> getList();
}
