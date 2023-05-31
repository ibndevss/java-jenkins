package myor.matrix.master.restcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.ValidasiKTPDto;
import myor.matrix.master.service.ImageStorageService;
import myor.matrix.master.service.ValidasiKTPService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path = "/validasi-ktp")
public class ValidasiKTPRestController {
	@Autowired
	ValidasiKTPService validasiKtpService;
	
	@Autowired
	ImageStorageService storageService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("17 APR 2023");
		return result;
	}
	
	@GetMapping(path = "/select-item", params={"statusValidasi","slsOpt","salesmans","slsFrom","slsTo","custFrom","custTo","valDateFrom","valDateTo","kplDateFrom","kplDateTo"})
	public List<SelectItem<String>> getCustnoByFilter(@RequestParam String statusValidasi, @RequestParam String slsOpt, @RequestParam List<String> salesmans, @RequestParam String slsFrom, @RequestParam String slsTo, 
			@RequestParam String custFrom, @RequestParam String custTo, @RequestParam String valDateFrom, @RequestParam String valDateTo, @RequestParam String kplDateFrom, @RequestParam String kplDateTo){
		return validasiKtpService.getCustnoByFilter(statusValidasi, slsOpt, salesmans, slsFrom, slsTo, custFrom, custTo, valDateFrom, valDateTo, kplDateFrom, kplDateTo);
	}
	
	@GetMapping(path = "/count-custno", params={"statusValidasi","slsOpt","salesmans","slsFrom","slsTo","custFrom","custTo","valDateFrom","valDateTo","kplDateFrom","kplDateTo"})
	public int getCountCustnoByFilter(@RequestParam String statusValidasi, @RequestParam String slsOpt, @RequestParam List<String> salesmans, @RequestParam String slsFrom, @RequestParam String slsTo, 
			@RequestParam String custFrom, @RequestParam String custTo, @RequestParam String valDateFrom, @RequestParam String valDateTo, @RequestParam String kplDateFrom, @RequestParam String kplDateTo){
		return validasiKtpService.getCountCustnoByFilter(statusValidasi, slsOpt, salesmans, slsFrom, slsTo, custFrom, custTo, valDateFrom, valDateTo, kplDateFrom, kplDateTo);
	}
	
	@GetMapping(path = "/save-val-ktp", params={"pemilik","telpon","ktp","ktpName","alamat1","alamat2","npwp","npwpName","custno"})
	public List<String> saveKtp(@RequestParam String pemilik,@RequestParam String telpon, @RequestParam String ktp, @RequestParam String ktpName, @RequestParam String alamat1,
			@RequestParam String alamat2, @RequestParam String npwp, @RequestParam String npwpName, @RequestParam String custno){
		return validasiKtpService.saveValidasiKTP(pemilik, telpon, ktp, ktpName, alamat1, alamat2, npwp, npwpName, custno);
	}
	
	@GetMapping(path = "/cek-path-photo-ktp")
	public boolean cekPathPhotoKtp(){
		return validasiKtpService.cekPathPhotoKTP();
	}
	
	@GetMapping(path="/get-validasi-ktp", params= {"custno"})
	public List<ValidasiKTPDto> getDataValidasiKTP(@RequestParam String custno){
		return validasiKtpService.getValidasiKTP(custno);
	}
	

	@GetMapping(path="/images/ktp", params= {"custno"})
	public ResponseEntity<Resource> getFileKtp(@RequestParam String custno) {
		Resource file = validasiKtpService.loadImageKTP(custno);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@GetMapping(path="/images/npwp", params= {"custno"})
	public ResponseEntity<Resource> getFileNpwp(@RequestParam String custno) {
		Resource file = validasiKtpService.loadImageNPWP(custno);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@GetMapping(path = "/isExistOutletApproveByGP", params= {"custno"})
	public boolean cekIsExistOutletApproveByGP(@RequestParam String custno){
		return validasiKtpService.cekIsExistOutletApproveByGroupPayer(custno);
	}
	
	@PostMapping(path = "/reject", params= {"custno","userLogin"})
	public void rejectValidasiKTP(@RequestParam String custno, @RequestParam String userLogin){
		validasiKtpService.rejectValidasiKTP(userLogin, custno);
	}
	
	@GetMapping(path = "/isExistPass")
	public boolean cekIsExistPassword(){
		return validasiKtpService.cekIsExistPassword();
	}
	
	@GetMapping(path = "/isPass", params= {"pass"})
	public boolean cekIsPassword(@RequestParam String pass){
		return validasiKtpService.cekIsPassword(pass);
	}
	
	@PostMapping(path="/revalidate", params= {"userLogin","custno"})
	public void revalidate(@RequestParam String userLogin, @RequestParam String custno) {
		validasiKtpService.revalidate(userLogin, custno);
	}
	
	@GetMapping(path="/info-approve-outlets", params= {"outlet"})
	List<String> getInfoApproveMoreThanOneOutletInGroupPayer(@RequestParam List<String> outlet){
		return validasiKtpService.infoApproveMoreThanOneOutletInGroupPayer(outlet);
	}
	
	@GetMapping(path="/approve", params= {"user","custno"})
	List<String> approveOutlet(@RequestParam String user, @RequestParam List<String> custno){
		return validasiKtpService.approve(user, custno);
	}
	
	@GetMapping(path = "/select-item-approve", params={"statusValidasi","slsOpt","salesmans","slsFrom","slsTo","custFrom","custTo","valDateFrom","valDateTo","kplDateFrom","kplDateTo"})
	public List<SelectItem<String>> getCustnoApproveByFilter(@RequestParam String statusValidasi, @RequestParam String slsOpt, @RequestParam List<String> salesmans, @RequestParam String slsFrom, @RequestParam String slsTo, 
			@RequestParam String custFrom, @RequestParam String custTo, @RequestParam String valDateFrom, @RequestParam String valDateTo, @RequestParam String kplDateFrom, @RequestParam String kplDateTo){
		return validasiKtpService.getSelectItemApprove(slsOpt, salesmans, slsFrom, slsTo, custFrom, custTo, valDateFrom, valDateTo, kplDateFrom, kplDateTo);
	}
	
	@PutMapping(path = "/preview-report",  params= {"statusValidasi","slsOpt","salesmans","slsFrom","slsTo","custFrom","custTo","valDateFrom","valDateTo"})
	public String getReportAll(@RequestParam String statusValidasi, @RequestParam String slsOpt, @RequestParam List<String> salesmans, @RequestParam String slsFrom, @RequestParam String slsTo, 
			@RequestParam String custFrom, @RequestParam String custTo, @RequestParam String valDateFrom, @RequestParam String valDateTo, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {		
		return validasiKtpService.getReportOutlet("", statusValidasi, slsOpt, salesmans, slsFrom, slsTo, custFrom, custTo, valDateFrom, valDateTo, "", response); 
	}
	
	@PutMapping(path = "/print-report-outlet",  params= {"statusValidasi","slsOpt","salesmans","slsFrom","slsTo","custFrom","custTo","valDateFrom","valDateTo","custId"})
	public String getReportOutlet(@RequestParam String statusValidasi, @RequestParam String slsOpt, @RequestParam List<String> salesmans, @RequestParam String slsFrom, @RequestParam String slsTo, 
			@RequestParam String custFrom, @RequestParam String custTo, @RequestParam String valDateFrom, @RequestParam String valDateTo, @RequestParam String custId, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {		
		return validasiKtpService.getReportOutlet("outlet", statusValidasi, slsOpt, salesmans, slsFrom, slsTo, custFrom, custTo, valDateFrom, valDateTo, custId, response); 
	}
	
	@GetMapping(path="/info-grouppayer-has-been-approved", params= {"outlet"})
	List<String> getInfoApproveGroupPayer(@RequestParam List<String> outlet){
		return validasiKtpService.validasiGroupPayerHasBeenApproved(outlet);
	}
}
