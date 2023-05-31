package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SupplierDto;

public interface SupplierService {
	public List<SelectItem<String>> getListSupplier();

	List<SupplierDto> getListSupplierAll();
}
