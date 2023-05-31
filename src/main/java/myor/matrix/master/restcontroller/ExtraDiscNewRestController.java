package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiskonBertingkatDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.service.ExtraDiscNewService;

@RestController
@RequestMapping(path = "/extra-disc-new")
public class ExtraDiscNewRestController {
	@Autowired
	ExtraDiscNewService xtraDiscNewService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		List<String> result = new ArrayList<String>();
		result.add("13 SEP 2022");
		return result;
	}
	
	@GetMapping(path = "/browse")
	public List<DiscApBrowseDto> getListProduct() {
		return xtraDiscNewService.getExtraDiscApBrowse();
	}

	@GetMapping(value = "/view", params={"ap"})
	public DiscViewDto viewAp(@RequestParam(name = "ap") String ap) {
		return xtraDiscNewService.getExtraDiscNew(ap);
	}
	
	@GetMapping(value = "/exclude", params={"ap"})
	public List<ApExcludeDto> viewApExclude(@RequestParam(name = "ap") String ap) {
		return xtraDiscNewService.getApExclude(ap);
	}
	
	@GetMapping(value = "/bertingkat", params={"ap"})
	public List<DiskonBertingkatDto> viewApBertingkat(@RequestParam(name = "ap") String ap) {
		return xtraDiscNewService.getDiskonBertingkat(ap);
	}

	
}
