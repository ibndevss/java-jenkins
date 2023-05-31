package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.MappingOutletSOBrowseDto;
import myor.matrix.master.entity.MappingOutletSODto;
import myor.matrix.master.service.MappingOutletSOService;

@RestController
@RequestMapping(path = "/mapping-outlet-so")
public class MappingOutletSORestController {
	
	@Autowired
	private MappingOutletSOService mappingOutletSOService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		List<String> result = new ArrayList<String>();
		result.add("04 AUG 2022");
		return result;
	}
	
	@GetMapping(path = "/browse")
	public List<MappingOutletSOBrowseDto> getBrowseOutletSOSAP() {
		return mappingOutletSOService.getBrowseSOSAP();
	}
	
	@GetMapping()
	public List<MappingOutletSODto> getDataMapping() {
		return mappingOutletSOService.getDataMapping();
	}
	
	@GetMapping(path = "/{custNo}")
	public List<MappingOutletSODto> getDataMappingByCustSOSAP(@PathVariable String custNo) {
		return mappingOutletSOService.getDataMappingByCustSOSAP(custNo);
	}
	
	@DeleteMapping(path = "/{custNoSO}")
	public void deleteMappingOutlet(@PathVariable String custNoSO) {
		mappingOutletSOService.deleteMappingOutlet(custNoSO);
	}
	
	@PostMapping(params = {"custNoSO", "custNameSO", "addressSO", "custNo"} )
	public void insertMappingOutlet(@RequestParam String custNoSO, @RequestParam String custNameSO, @RequestParam String addressSO,
			@RequestParam String custNo) {
		mappingOutletSOService.insertMappingOutlet(custNoSO, custNameSO, addressSO, custNo);
	}
	
	@PutMapping(params = {"custNoSO", "custNameSO", "addressSO", "custNo"} )
	public void updateMappingOutlet(@RequestParam String custNoSO, @RequestParam  String custNameSO, @RequestParam String addressSO, 
			@RequestParam String custNo) {
		mappingOutletSOService.updateMappingOutlet(custNoSO, custNameSO, addressSO, custNo);
	}
	
	


}
