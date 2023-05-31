package myor.matrix.master.restcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifExcelDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifHeaderDto;
import myor.matrix.master.service.UploadOutletAktifNonAktifService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path = "/upload-outlet-aktif-non-aktif")
public class UploadOutletAktifNonAktifRestController {

	@Autowired
	private UploadOutletAktifNonAktifService serviceOutlet;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		try {
			List<String> result = new ArrayList<String>();
			result.add("26 MEI 2023");
			result.addAll(serviceOutlet.loadForm());
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
	
	@GetMapping(path = "/browse-doc")
	public List<UploadOutletAktifNonAktifHeaderDto> getBrowseDocument() {
		return serviceOutlet.getBrowseDocument();
	}
	
	@GetMapping(path = "/get-datas", params = { "docno" })
	public UploadOutletAktifNonAktifDto getDatas(@RequestParam String docno) {
		return serviceOutlet.getDatas(docno);
	}
	
	@GetMapping(path = "/delete-doc", params = { "docno" })
	public MessageDto deleteDocument(@RequestParam String docno) {
		return serviceOutlet.deleteDocument(docno);
	}
	
	@GetMapping(path = "/process-doc", params = { "docno", "xact", "pass", "ras", "xuser" })
	public MessageDto processDocument(@RequestParam String docno, @RequestParam String xact, @RequestParam String pass, @RequestParam String ras, @RequestParam String xuser) {
		return serviceOutlet.processDocument(docno, xact, pass, ras, xuser);
	}
	
	@GetMapping(path = "/validasi-outlet", params = { "custno", "docdate", "action" })
	public MessageDto deleteDocument(@RequestParam String custno, @RequestParam String docdate, @RequestParam String action) {
		return serviceOutlet.validasiOutlet(custno, docdate, action);
	}
	
	@PutMapping(path = "/print",params = { "action" })
	public MessageDto onPrint(@RequestBody UploadOutletAktifNonAktifDto p, @RequestParam String action, HttpServletResponse response) throws FileNotFoundException, JRException, IOException  {
		return serviceOutlet.print(p, action, response);
	}
	
	@PutMapping(path = "/excel",params = { "action", "docdate" })
	public UploadOutletAktifNonAktifDto cekExcel(@RequestBody List<UploadOutletAktifNonAktifExcelDto> p, @RequestParam String action, @RequestParam String docdate)  {
		return serviceOutlet.cekExcel(p, action, docdate);
	}
	
}
