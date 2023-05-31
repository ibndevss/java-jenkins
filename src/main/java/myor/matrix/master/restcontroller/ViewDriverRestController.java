package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ViewDriverDto;
import myor.matrix.master.service.ViewDriverService;

@RestController
@RequestMapping(path = "/view-driver")
public class ViewDriverRestController {
	
	@Autowired
	private ViewDriverService service;
	
	@GetMapping()
	public List<ViewDriverDto> getListDriver(){
		return service.getListDriver();
	}
	
}
