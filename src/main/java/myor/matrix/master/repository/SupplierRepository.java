package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SupplierDto;

public interface SupplierRepository {
	public List<SelectItem<String>> getListSupplier();

	List<SupplierDto> getListSupplierAll();
}
