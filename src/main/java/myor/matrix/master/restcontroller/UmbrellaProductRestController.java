package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.CustomerBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.UmbrellaBrowseDto;
import myor.matrix.master.service.UmbrellaProductService;

@RestController
@RequestMapping(path = "/umbrella")
public class UmbrellaProductRestController {
	
	@Autowired
	private UmbrellaProductService umbrellaService;
	
	@GetMapping(path = "/select-item")
	public List<SelectItem<String>> getListUmbrella() {
		return umbrellaService.getListUmbrella();
	}
	
	@GetMapping(path = "/browse")
	public List<UmbrellaBrowseDto> getBrowseUmbrella(){
		return umbrellaService.getBrowseUmbrella();
	}
}
