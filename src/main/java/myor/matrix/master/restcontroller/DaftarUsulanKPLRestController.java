package myor.matrix.master.restcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.FilterStandardDto;
import myor.matrix.master.service.DaftarUsulanKPLService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path = "/daftar-usulan-kpl")
public class DaftarUsulanKPLRestController {

	@Autowired
	DaftarUsulanKPLService service;
	
	@PutMapping(path="/preview")
	public String getReport(@RequestBody FilterStandardDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		return service.getReport(fs, response); 
	}
}
