package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ViewKplDto;
import myor.matrix.master.service.ViewKplService;

@RestController
@RequestMapping(path = "/view-kpl")
public class ViewKplRestController {
	
	@Autowired
	private ViewKplService service;
	
	@GetMapping(path = "/get-detail/{sls-no}/{date}/{choose-data}")
	public List<ViewKplDto> getListDetail(
			@PathVariable (name="sls-no") String slsNo,
			@PathVariable (name="date") String tgl,
			@PathVariable (name="choose-data") String chooseData){
		return service.getDataDetail(slsNo,tgl,chooseData);
	}
}
