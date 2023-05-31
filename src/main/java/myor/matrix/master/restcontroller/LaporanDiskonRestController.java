package myor.matrix.master.restcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.LaporanDiskonBrowseAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractDto;
import myor.matrix.master.entity.LaporanDiskonFilterDto;
import myor.matrix.master.entity.LaporanDiskonReportDto;
import myor.matrix.master.service.LaporanDiskonService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path = "/laporan-diskon")
public class LaporanDiskonRestController {
	
	@Autowired
	private LaporanDiskonService service;
	
	@PostMapping(path="/browse-ap")
	public List<LaporanDiskonBrowseAPDto> getBrowseAP(@RequestBody LaporanDiskonFilterDto fs){		
		try {
			return service.getBrowseAP(fs);
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
	
	@PostMapping(path = "/preview")
	public LaporanDiskonReportDto getReport(@RequestBody LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		try {
			return service.getReport(fs, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getCause() != null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						e.getCause().getCause().getLocalizedMessage());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}

	}
	
	@PostMapping(path = "/extract")
	public LaporanDiskonExtractDto extractData(@RequestBody LaporanDiskonFilterDto fs) {
		try {
			return service.extractData(fs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getCause() != null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						e.getCause().getCause().getLocalizedMessage());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
	}	
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("11 Nov 2022");
		return result;
	}
}
