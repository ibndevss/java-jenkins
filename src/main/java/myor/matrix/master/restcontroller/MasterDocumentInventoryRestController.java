package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ListDocumentDto;
import myor.matrix.master.service.MasterDocumentInventoryService;

@RestController
@RequestMapping(path = "/inventory")
public class MasterDocumentInventoryRestController {
	@Autowired
	private MasterDocumentInventoryService mDocInvenService;
	
	@GetMapping(path = "/document-penurunan/select-item",params = {"type"})
	public List<ListDocumentDto> getListDocumentPenurunanStockVan(@RequestParam String type){
		return mDocInvenService.getListDocumentPenurunanStockVan(type);
	}
	
	@GetMapping(path = "/document-booking/select-item")
	public List<ListDocumentDto> getListDocumentBookingPenambahanBarangVan(){
		return mDocInvenService.getListDocumentBookingPenambahanBarangVan();
	}
	
	@GetMapping(path = "/document-penambahan/select-item")
	public List<ListDocumentDto> getListDocumentPenambahanBarangVan(){
		return mDocInvenService.getListDocumentPenambahanBarangVan();
	}
}
