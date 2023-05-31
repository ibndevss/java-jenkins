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
import myor.matrix.master.entity.MasterBankTransferDto;
import myor.matrix.master.service.MasterBankService;
import myor.matrix.master.service.MasterBankTransferService;

@RestController
@RequestMapping(path = "/master-bank-transfer")
public class MasterBankTransferRestController {
	
	@Autowired
	private MasterBankTransferService masterBankTransferService;
	
	@GetMapping()
	public List<MasterBankTransferDto> getDataBank(){
		return masterBankTransferService.getDataBankTransfer();
	}
	
	@PostMapping(path = "/insert")
	public void insertMasterBank(@RequestParam String kdBank, @RequestParam String ketBank) {
		masterBankTransferService.insertMasterBankTransfer(kdBank, ketBank);
	}
	
	@PutMapping(path = "/update")
	public void updateMasterBank(@RequestParam String kdBank, @RequestParam String ketBank) {
		masterBankTransferService.updateMasterBankTransfer(kdBank, ketBank);
	}	
	
	
	@GetMapping(path = "/delete/{kdBank}")
	public String deleteMasterBank(@PathVariable String kdBank) {
		return masterBankTransferService.deleteMasterBankTransfer(kdBank);
	}	

}
