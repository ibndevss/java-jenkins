package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.DocumentBrowseDto;
import myor.matrix.master.entity.ViewNooDto;
import myor.matrix.master.service.ViewNooService;

@RestController
@RequestMapping(path = "/view-noo")
public class ViewNooRestController {
	@Autowired
	private ViewNooService nooService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("04 MAR 2022");
		return result;
	}
	
	@GetMapping(path = "/{docNo}/{status}")
	public ViewNooDto getDataViewNoo(@PathVariable String docNo, @PathVariable String status) {
		return nooService.getDataViewNoo(docNo, status);
	}
	
	@GetMapping(path = "browse-document/{status}")
	public List<DocumentBrowseDto> getListDocument(@PathVariable String status) {
		return nooService.getListDocument(status);
	}
	
	
}
