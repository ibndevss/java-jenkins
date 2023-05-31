package myor.matrix.master.restcontroller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.CNDNDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.CNDNService;

@RestController
@RequestMapping(path="/cndn")
public class CNDNRestController {

	@Autowired
	CNDNService service;
	
	@GetMapping(path = "/select-items")
	public List<SelectItem<String>> getListCnDn() {
		
		List<SelectItem<String>> data = null; 
		
		try {
			data = service.getListCnDn(); 
		} catch (Exception e) {
			// TODO: handle exception
			if (e.getCause() != null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getCause().getLocalizedMessage());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}			
		}
		
		return data;
	}
	
	@GetMapping(path="/browse")
	public List<CNDNDto> browseCNDN() {
		try {
			return service.browseCNDN();
		} catch (Exception e) {
			// TODO: handle exception
			if (e.getCause() != null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getCause().getLocalizedMessage());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
	}
}
