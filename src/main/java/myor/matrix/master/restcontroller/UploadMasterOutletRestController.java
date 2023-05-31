package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.ProsesNooInputDto;
import myor.matrix.master.entity.UploadMasterOutletDto;
import myor.matrix.master.service.UploadMasterOutletService;

@RestController
@RequestMapping(path = "/upload-master-outlet")
public class UploadMasterOutletRestController {
	
	@Autowired
	private UploadMasterOutletService uploadMasterOutletService;

	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		try {
			List<String> result = new ArrayList<String>();
			result.add("11 MEI 2023");
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
	
	@PutMapping(path = "/cek")
	public UploadMasterOutletDto cekExcel(@RequestBody UploadMasterOutletDto p) {
		return uploadMasterOutletService.cekExcel(p);
	}
	
	@PutMapping(path = "/process")
	public MessageDto process(@RequestBody UploadMasterOutletDto p) {
		return uploadMasterOutletService.process(p);
	}
	
}
