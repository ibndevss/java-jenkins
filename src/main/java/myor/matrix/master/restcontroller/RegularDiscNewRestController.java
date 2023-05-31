package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.entity.DiskonBertingkatDto;
import myor.matrix.master.service.RegularDiscNewService;

@RestController
@RequestMapping(path = "/regular-disc-new")
public class RegularDiscNewRestController {

	@Autowired
	RegularDiscNewService regularNewService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		List<String> result = new ArrayList<String>();
		result.add("23 SEP 2022");
		return result;
	}
	
	@GetMapping(path = "/browse")
	public List<DiscApBrowseDto> getListProduct() {
		return regularNewService.getRegularDiscApBrowse();
	}

	@GetMapping(value = "/view", params={"ap"})
	public DiscViewDto viewAp(@RequestParam(name = "ap") String ap) {
		return regularNewService.getRegularDiscNew(ap);
	}
	
	@GetMapping(value = "/exclude", params={"ap"})
	public List<ApExcludeDto> viewApExclude(@RequestParam(name = "ap") String ap) {
		return regularNewService.getApExclude(ap);
	}
	
	@GetMapping(value = "/bertingkat", params={"ap"})
	public List<DiskonBertingkatDto> viewApBertingkat(@RequestParam(name = "ap") String ap) {
		return regularNewService.getDiskonBertingkat(ap);
	}
}
