package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SupplierDto;
import myor.matrix.master.service.SupplierService;

@RestController
@RequestMapping(path = "/supplier")
public class SupplierRestController {

	@Autowired
	private SupplierService supplierService;
	
	@GetMapping(path = "/select-item")
	public List<SelectItem<String>> getListSupplier(){
		return supplierService.getListSupplier();
	}
	
	@GetMapping(path = "/browse")
	public List<SupplierDto> getListSupplierAll() {
		return supplierService.getListSupplierAll();
	}
}
