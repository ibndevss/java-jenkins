package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ViewPhotoAndKoordOutletDto;
import myor.matrix.master.service.ImageStorageService;
import myor.matrix.master.service.ViewPhotoAndKoordOutletService;

@RestController
@RequestMapping(path = "/view-validasi-outlet")
public class ViewPhotoAndKoordOutletRestController {
	@Autowired
	private ViewPhotoAndKoordOutletService photoService;
	
	@Autowired
	private ImageStorageService storageService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("29 MAR 2023");
		return result;
	}
	
	@GetMapping(path = "/view-data", params={"outlet"})
	public List<ViewPhotoAndKoordOutletDto> getData(@RequestParam String outlet) {
		return photoService.getViewPhotoAndKoordOutlet(outlet);
	}
	
	@GetMapping(path = "/cek-path-photo")
	public boolean cekPathPhoto() {
		return photoService.cekPathPhoto();
	}
	
	@PostMapping(path = "/insert-custno")
	public void insertCustno() {
		photoService.insertCustnoToFtable275();
	}
	
	@GetMapping(path="/images", params= {"custno"})
	public ResponseEntity<Resource> getFile(@RequestParam String custno) {
		Resource file = storageService.load(custno);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
}
