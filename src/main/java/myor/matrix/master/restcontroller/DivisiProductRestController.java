package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.DivisiProductBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.DivisiProductService;

@RestController
@RequestMapping(path = "/divisi")
public class DivisiProductRestController {

	@Autowired
	private DivisiProductService divisiService;
	
	@GetMapping(path = "/select-item")
	public List<SelectItem<String>> getListDivisi() {
		return divisiService.getListDivisi();
	}
	
	@GetMapping(path = "/browse")
	public List<DivisiProductBrowseDto> getBrowseDivisi() {
		return divisiService.getBrowseDivisi();
	}
}
