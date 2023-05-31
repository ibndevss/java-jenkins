package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ApBrowseDto;
import myor.matrix.master.service.ApService;

@RestController
@RequestMapping(path = "/ap")
public class ApRestController {
	
	@Autowired
	private ApService apService;
	
	@GetMapping(path = "/browse")
	public List<ApBrowseDto> getBrowseAp(){
		return apService.getBrowseAp();
	}
	
}
