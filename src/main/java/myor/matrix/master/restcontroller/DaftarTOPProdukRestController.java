package myor.matrix.master.restcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.DaftarTOPProdukFilterDto;
import myor.matrix.master.service.DaftarTOPProdukService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path = "/daftar-top-produk")
public class DaftarTOPProdukRestController {
	
	@Autowired
	private DaftarTOPProdukService service;
	
	@PostMapping(path = "/preview")
	public String getReport(@RequestBody DaftarTOPProdukFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {

		return service.getReport(fs, response);
	}
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("28 Sep 2022");
		return result;
	}
}
