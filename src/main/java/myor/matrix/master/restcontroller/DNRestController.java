package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.DNBrowseDto;
import myor.matrix.master.service.DNService;

@RestController
@RequestMapping(path="/dn")
public class DNRestController {

	@Autowired
	DNService service;
	
	@GetMapping(path="/list",params={"tglDN"})
	public List<DNBrowseDto> getListDN(@RequestParam String tglDN) {
		try {
			return service.getListDN(tglDN);
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
