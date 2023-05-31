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

import myor.matrix.master.entity.MasterBankHODto;
import myor.matrix.master.service.MasterBankHOService;

@RestController
@RequestMapping(path = "/master-bank-ho")
public class MasterBankHORestController {

	@Autowired
	private MasterBankHOService masterBankHOService;
	
	@GetMapping()
	public List<MasterBankHODto> getDataBankHO() {
		return masterBankHOService.getDataBankHO();
	}

	@PostMapping(path = "/insert")
	public void insertMasterBankHO(@RequestParam String kdBank, @RequestParam String ketBank) {
		masterBankHOService.insertMasterBankHO(kdBank, ketBank);
	}

	@PutMapping(path = "/update")
	public void updateMasterBankHO(@RequestParam String kdBank, @RequestParam String ketBank) {
		masterBankHOService.updateMasterBankHO(kdBank, ketBank);
	}	
	
	@GetMapping(path = "/delete/{kdBank}")
	public String deleteMasterBankHO(@PathVariable String kdBank) {
		return masterBankHOService.deleteMasterBankHO(kdBank);
	}	

}
