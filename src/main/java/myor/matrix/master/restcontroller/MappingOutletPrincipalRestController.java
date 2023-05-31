package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.MappingOutletPrincipalDto;
import myor.matrix.master.service.MappingOutletPrincipalService;

@RestController
@RequestMapping(path = "/mapping-outlet-principal")
public class MappingOutletPrincipalRestController {
	
	@Autowired
	private MappingOutletPrincipalService mappingOutletPrincipalService;

	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		List<String> result = new ArrayList<String>();
		result.add("08 AUG 2022");
		return result;
	}
	
	@GetMapping()
	public List<MappingOutletPrincipalDto> getDataMapping() {
		return mappingOutletPrincipalService.getDataMapping();
	}
	
	@GetMapping(path="/{custNo}")
	public List<MappingOutletPrincipalDto> getDataMappingByCustNo(@PathVariable String custNo) {
		return mappingOutletPrincipalService.getDataMappingByCustNo(custNo);
	}
	
	@PostMapping(params = {"custNo", "custName", "address"})
	public void insertMappingOutletPrincipal(@RequestParam String custNo, @RequestParam String custName, @RequestParam String address) {	
		mappingOutletPrincipalService.insertMappingOutletPrincipal(custNo, custName, address);
	}
	
	@DeleteMapping(path = "/{custNo}")
	public void deleteMappingOutletPrincipal(@PathVariable String custNo) {
		mappingOutletPrincipalService.deleteMappingOutletPrincipal(custNo);
	}

}
