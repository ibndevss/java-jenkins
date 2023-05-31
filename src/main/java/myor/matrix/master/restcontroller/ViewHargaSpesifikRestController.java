package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ProductBrowserHargaSpesifikDto;
import myor.matrix.master.entity.ViewHargaSpesifikDto;
import myor.matrix.master.service.ViewHargaSpesifikService;

@RestController
@RequestMapping(path="/view-harga-spesifik")
public class ViewHargaSpesifikRestController {
	
	@Autowired
	private ViewHargaSpesifikService service;
	
	@GetMapping()
	public List<ProductBrowserHargaSpesifikDto> getListHargaSpesifik(){
		return service.getListHargaSpesifik();
	}
	
	@GetMapping(path="/by-tgl")
	public List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTgl(){
		return service.getListHargaSpesifikByTgl();
	}
	
	@GetMapping(path="/by-tipe")
	public List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTipe(){
		return service.getListHargaSpesifikByTipe();
	}
	
}
