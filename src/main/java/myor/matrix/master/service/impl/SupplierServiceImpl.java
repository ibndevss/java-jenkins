package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SupplierDto;
import myor.matrix.master.repository.SupplierRepository;
import myor.matrix.master.service.SupplierService;
@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierRepository supplier;

	@Override
	public List<SelectItem<String>> getListSupplier() {
		// TODO Auto-generated method stub
		return supplier.getListSupplier();
	}
	
	@Override
	public List<SupplierDto> getListSupplierAll() {
		return supplier.getListSupplierAll();
	}

}
