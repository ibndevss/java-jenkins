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

import myor.matrix.master.entity.MasterBankDto;
import myor.matrix.master.service.MasterBankService;

@RestController
@RequestMapping(path = "/master-bank")
public class MasterBankRestController {
	
	@Autowired
	private MasterBankService masterBankService;

	@GetMapping()
	public List<MasterBankDto> getDataBank() {
		return masterBankService.getDataBank();
	}

	@PostMapping(path = "/insert")
	public void insertMasterBank(@RequestParam String kdBank, @RequestParam String ketBank) {
		masterBankService.insertMasterBank(kdBank, ketBank);
	}

	@PutMapping(path = "/update")
	public void updateMasterBank(@RequestParam String kdBank, @RequestParam String ketBank) {
		masterBankService.updateMasterBank(kdBank, ketBank);
	}	
	
	@GetMapping(path = "/delete/{kdBank}")
	public String deleteMasterBank(@PathVariable String kdBank) {
		return masterBankService.deleteMasterBank(kdBank);
	}
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("18 Feb 2022");
		return result;
	}

}
