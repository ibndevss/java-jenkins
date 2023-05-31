package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.FilterStandard;
import myor.matrix.master.entity.FormatExtractDaftarSalesmanDto;
import myor.matrix.master.entity.ListAttributeDaftarSalesmanDto;
import myor.matrix.master.service.DaftarSalesmanService;

@RestController
@RequestMapping(path = "/daftar-salesman")
public class DaftarSalesmanRestController {

	@Autowired
	private DaftarSalesmanService service;

	@GetMapping(path = "/list-attribut")
	public List<ListAttributeDaftarSalesmanDto> getListAttribute(){
		return service.getListAttribute();
	}

	@GetMapping(path="/cek-attribute")
	public int cekDataAttribute() {
		return service.cekDataAttribute();
	}

	@PostMapping(path="/attribute")
	public void insertAttribute() {
		service.insertAttribute();
	}

	@GetMapping(path="/get-default/{tmp}")
	public List<FormatExtractDaftarSalesmanDto> getDefaultFormat(
			@PathVariable (name="tmp") List<String> list){
		return service.getDefaultFormat(list);
	}

	@PostMapping(path="/preview/{tmp}")
	public String getPreview(
			@RequestBody FilterStandard fs,
			@PathVariable (name="tmp") List<String> list){
		return service.getPreview(fs, list);
	}

	@PostMapping(path="/extract/{tmp}")
	List<FormatExtractDaftarSalesmanDto> getExtractFormat(
			@RequestBody FilterStandard fs,
			@PathVariable (name="tmp") List<String> list){
		return service.getExtractFormat(fs,list);
	}
}
