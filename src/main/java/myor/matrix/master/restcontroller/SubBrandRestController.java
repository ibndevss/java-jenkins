package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.BrandBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SubbrandBrowseDto;
import myor.matrix.master.service.SubBrandProductService;

@RestController
@RequestMapping(path = "/subbrand")
public class SubBrandRestController {
	
	@Autowired
	private SubBrandProductService subbrandService;
	
	@GetMapping(path = "/select-item")
	public List<SelectItem<String>> getListSubbrand() {
		return subbrandService.getListSubBrand();
	}
	
	@GetMapping(path = "/browse", params = { "umbrellaFrom", "umbrellaTo", "brandFrom", "brandTo" })
	public List<SubbrandBrowseDto> getBrowseSubBrand(@RequestParam String umbrellaFrom, @RequestParam String umbrellaTo,
			@RequestParam String brandFrom, @RequestParam String brandTo) {
		return subbrandService.getBrowseSubBrand(umbrellaFrom, umbrellaTo, brandFrom, brandTo);
	}
}
