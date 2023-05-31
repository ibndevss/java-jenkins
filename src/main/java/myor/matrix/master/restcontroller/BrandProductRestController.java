package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.BrandBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.UmbrellaBrowseDto;
import myor.matrix.master.service.BrandProductService;

@RestController
@RequestMapping(path = "/brand")
public class BrandProductRestController {
	
	@Autowired
	private BrandProductService brandService;
	
	@GetMapping(path = "/select-item")
	public List<SelectItem<String>> getListBrand() {
		return brandService.getListBrand();
	}
	
	@GetMapping(path = "/browse", params = {"umbrellaFrom","umbrellaTo"})
	public List<BrandBrowseDto> getBrowseBrand(@RequestParam String umbrellaFrom,@RequestParam String umbrellaTo){
		return brandService.getBrowseBrand(umbrellaFrom,umbrellaTo);
	}
}
