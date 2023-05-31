package myor.matrix.master.restcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.FilterStandardDto;
import myor.matrix.master.service.DaftarHargaGeneralService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path = "/daftar-harga-general")
public class DaftarHargaGeneralRestController {
	
	@Autowired
	private DaftarHargaGeneralService hargaGeneralService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("28 OCT 2022");
		return result;
	}
	
	@PutMapping(path = "/preview")
	public String getReport(@RequestBody FilterStandardDto p, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {		
		return hargaGeneralService.getReport(p, response);
	}

}
