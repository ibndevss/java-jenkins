package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.AlasanBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.MasterAlasanService;

@RestController
@RequestMapping(path = "/alasan")
public class MasterAlasanRestController {

	@Autowired
	private MasterAlasanService service;
	
	@GetMapping(path = "/select-item/{tipe}")	
	public List<SelectItem<String>> getAlasan(@PathVariable String tipe){
		return service.getAlasan(tipe);
	}
	
	@GetMapping(path = "/browse-notec")	
	public List<SelectItem<String>> getAlasanNotEc(){
		return service.getAlasanNotEc();
	}
	
	@GetMapping(path = "/browse/{tipeAlasan}")
	public List<AlasanBrowseDto> browseAlasan(@PathVariable String tipeAlasan) {
		return service.browseAlasan(tipeAlasan);
	}
	
	@GetMapping(path="/select-item/retur",params= {"reshuffle"})
	public List<SelectItem<String>> getAlasanRetur(boolean reshuffle) {		
		try {
			return service.getAlasanRetur(reshuffle);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getCause() != null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getCause().getLocalizedMessage());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
	}
}
