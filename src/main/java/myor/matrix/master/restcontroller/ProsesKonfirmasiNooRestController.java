package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.ProsesKonfirmasiNooDto;
import myor.matrix.master.service.ProsesKonfirmasiNooService;

@RestController
@RequestMapping(path = "/proses-konfirmasi-noo")
public class ProsesKonfirmasiNooRestController {

	@Autowired
	private ProsesKonfirmasiNooService serviceKonfirmasiNoo;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		try {
			List<String> result = new ArrayList<String>();
			result.add("14 APRIL 2023");
			result.addAll(serviceKonfirmasiNoo.loadForm());
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
	
	@GetMapping(value = "/list-data", params={"tglReg"})
	public List<ProsesKonfirmasiNooDto> getListData(@RequestParam String tglReg) {
		return serviceKonfirmasiNoo.getListData(tglReg);
	}
	
	
}
