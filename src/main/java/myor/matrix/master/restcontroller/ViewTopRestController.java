package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ViewTopDto;
import myor.matrix.master.service.ViewTopService;

@RestController
@RequestMapping(path="/view-top")
public class ViewTopRestController {
	
	@Autowired
	private ViewTopService service;
	
	@GetMapping()
	public List<ViewTopDto> getListTop(){
		return service.getListTop();
	}
	
	@GetMapping(path="/by-tgl")
	public List<ViewTopDto> getListTopByTgl(){
		return service.getListTopByTgl();
	}
	
	@GetMapping(path="/by-tipe")
	public List<ViewTopDto> getListTopByTipe(){
		return service.getListTopByTipe();
	}
}
