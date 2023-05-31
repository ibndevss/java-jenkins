package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ViewValidasiOutletDto;
import myor.matrix.master.service.ViewValidasiOutletService;

@RestController
@RequestMapping(path="/validasi-outlet")
public class ViewValidasiOutletRestController {
	@Autowired
	ViewValidasiOutletService viewValidasiOutletService;
	
	@GetMapping(path = "/view", params= {"dateFrom","dateTo"})
	public List<ViewValidasiOutletDto> getViewValidasiOutlet(@RequestParam String dateFrom, @RequestParam String dateTo) {
		return viewValidasiOutletService.viewValidasiOutlet(dateFrom, dateTo);
	}
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("31 MAR 2023");
		return result;
	}
}
