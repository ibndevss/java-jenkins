package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;

import myor.matrix.master.service.ExtractMstOutletPengcoveranService;

@RestController
@RequestMapping(path = "/extract-outlet-pengcoveran")
public class ExtractMstOutletPengcoveranRestController {
	
	@Autowired
	private ExtractMstOutletPengcoveranService extractMstOutletPengcoveranService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		List<String> result = new ArrayList<String>();
		result.add("12 AUG 2022");
		return result;
	}
	
	@GetMapping(path ="/pengcoveran", params = {"username","optSls","optOutletStatus","listSalesman"})
	public ResponseEntity<Resource> extractFilePengcoveran(@RequestParam String username, @RequestParam String optSls,  
			@RequestParam String optOutletStatus, @RequestParam List<String> listSalesman) {
		Resource file = extractMstOutletPengcoveranService.ekstrakPengcoveran(username, optSls, optOutletStatus, listSalesman);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping(path ="/outlet", params = {"username","optSls","optOutletStatus","listSalesman"})
	public ResponseEntity<Resource> extractFileOutlet(@RequestParam String username, @RequestParam String optSls,  
			@RequestParam String optOutletStatus, @RequestParam List<String> listSalesman) {
		Resource file = extractMstOutletPengcoveranService.ekstrakOutlet(username, optSls, optOutletStatus, listSalesman);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
