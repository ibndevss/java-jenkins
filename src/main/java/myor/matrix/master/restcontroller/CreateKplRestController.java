package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.CreateKplInputDto;
import myor.matrix.master.entity.CreateKplListOutletDto;
import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.service.CreateKplService;

@RestController
@RequestMapping(path = "/create-kpl")
public class CreateKplRestController {
	
	@Autowired
	private CreateKplService createKplService;

	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		try {
			List<String> result = new ArrayList<String>();
			result.add("03 OKTOBER 2022");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			if (e.getCause() != null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Error Repository! " + e.getCause().getCause().getLocalizedMessage());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Service! " + e.getMessage());
			}
		}
	}
	
	@PostMapping(path = "/daftar-outlet")
	public List<CreateKplListOutletDto> getDaftarOutlet(@RequestBody CreateKplInputDto p) {
		return createKplService.getDaftarOutlet(p);
	}
	
	@GetMapping(path = "/req-key")
	public String getRequestKey() {
		return createKplService.getRequestKey();
	}
	
	@PostMapping(path = "/process-kpl")
	public MessageDto processKpl(@RequestBody CreateKplInputDto p) {
		return createKplService.processCreateKpl(p);
	}
}
