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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.ProsesNooBrowseDto;
import myor.matrix.master.entity.ProsesNooDto;
import myor.matrix.master.entity.ProsesNooInputDto;
import myor.matrix.master.entity.ProsesNooMessageDto;
import myor.matrix.master.service.ProsesNooService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path = "/proses-noo")
public class ProsesNooRestController {

	@Autowired
	private ProsesNooService serviceNoo;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		try {
			List<String> result = new ArrayList<String>();
			result.add("03 APRIL 2023");
			result.addAll(serviceNoo.loadForm());
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
	public List<ProsesNooBrowseDto> getBrowseDocument() {
		return serviceNoo.getBrowseDocument();
	}
	
	@GetMapping(path = "/browse-data-doc")
	public List<ProsesNooBrowseDto> getBrowseDataDocument() {
		return serviceNoo.getBrowseDataDocument();
	}
	
	@GetMapping(path = "/load-doc")
	public ProsesNooDto loadDocument(String docNo) {
		return serviceNoo.getLoadData(docNo);
	}
	
	@PutMapping(path = "/add-doc")
	public MessageDto addDocument(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.addDocument(p);
	}
	
	@PutMapping(path = "/add-data")
	public MessageDto addData(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.addData(p);
	}
	
	@PutMapping(path = "/cancel-doc")
	public MessageDto cancelDocument(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.cancelDocument(p);
	}
	
	@PutMapping(path = "/delete-doc")
	public MessageDto deleteDocument(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.deleteDocument(p);
	}
	
	@PutMapping(path = "/save-doc")
	public MessageDto saveDocument(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.saveDocument(p);
	}
	
	@PutMapping(path = "/bersih-doc")
	public MessageDto bersihDocument(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.bersihDocument(p);
	}
	
	@PutMapping(path = "/load-data-doc")
	public MessageDto loadDataDocument(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.loadDataDocument(p);
	}
	
	@PutMapping(path = "/delete-data-doc")
	public MessageDto deleteDataDocument(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.deleteDataDocument(p);
	}
	
	@PutMapping(path = "/key-request")
	public MessageDto keyRequest(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.keyRequest(p);
	}
	
	@PutMapping(path = "/print-fpp")
	public MessageDto onPrintFPP(@RequestBody ProsesNooInputDto p, HttpServletResponse response) throws FileNotFoundException, JRException, IOException  {
		return serviceNoo.onPrintFPP(p, response);
	}
	
	@PutMapping(path = "/print-rekap")
	public MessageDto onPrintRekap(@RequestBody ProsesNooInputDto p, HttpServletResponse response) throws FileNotFoundException, JRException, IOException  {
		return serviceNoo.onPrintRekap(p, response);
	}
	
	@PutMapping(path = "/cek-edit")
	public ProsesNooMessageDto onEdit(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.onCekEdit(p);
	}
	
	@PutMapping(path = "/action-edit")
	public MessageDto actionEdit(@RequestBody ProsesNooInputDto p) {
		return serviceNoo.onActionEdit(p);
	}
	
	@PutMapping(path = "/process-doc")
	public ProsesNooMessageDto onProcessDocument(@RequestBody ProsesNooInputDto p){
		return serviceNoo.onProcessDocument(p);
	}
}
