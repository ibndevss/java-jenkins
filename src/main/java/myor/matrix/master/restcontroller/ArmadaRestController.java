package myor.matrix.master.restcontroller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ArmadaAllDto;
import myor.matrix.master.entity.ArmadaChoosenDto;
import myor.matrix.master.entity.EmployeeDto;
//import myor.matrix.master.entity.JenisKendaraanDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SelectItemKendaraan;
import myor.matrix.master.service.ArmadaService;

@RestController
@RequestMapping(path = "/armada")
@CrossOrigin(origins = "http://localhost:4200")
public class ArmadaRestController {

	@Autowired
	private ArmadaService armadaService;
	
	@GetMapping(path = "/getdataarmada")
	public List<ArmadaAllDto> getDataArmada(){
		return armadaService.getDataArmada();
	}
	
	@GetMapping(path = "/getdataarmada-temp")
	public List<ArmadaAllDto> getDataArmadaTemp(){
		return armadaService.getDataArmadaTemp();
	}
	
	@GetMapping(path = "/getdatakendaraan")
	public List<SelectItemKendaraan<String>> getListJenisKendaraan(){
		return armadaService.getListJenisKendaraan();
	}
	
	@GetMapping(path = "/getstatuskendaraan")
	public List<SelectItem<String>> getStatusKendaraan(){
		return armadaService.getStatusKendaraan();
	}
	
	@GetMapping(path = "/selectdrivers")
	public List<SelectItem<String>> getListDriver(){
		return armadaService.getListDriver();
	}
	
	@GetMapping(value = "selectemp/{empno}")
	public List<EmployeeDto> getNameEmp(@PathVariable String empno){
		return armadaService.getNameEmp(empno);
	}
	
	@GetMapping(path = "/selecthelpers")
	public List<SelectItem<String>> getListHelper(){
		return armadaService.getListHelper();
	}
	
	@PostMapping(path = "/insertdataarmada/{noKend}/{jnsKend}/{jenisBbm}/{capacity}/{kubikase}/{kmAkhir}/"
			+ "{statusAktif}/{tonase}/{jenisMuat}/{driverCode}/{helperCode}/{statusKend}/{stdIsi}/{maxRit}/{dedicated}/{tglE}/{tglA}")
	public void insertDataArmada(@PathVariable String noKend,@PathVariable String jnsKend,@PathVariable String jenisBbm,@PathVariable String capacity,
			@PathVariable String kubikase,@PathVariable String kmAkhir,@PathVariable String statusAktif,@PathVariable String tonase,@PathVariable String jenisMuat,
			@PathVariable String driverCode,@PathVariable String helperCode,@PathVariable String statusKend,@PathVariable String stdIsi,
			@PathVariable String maxRit,@PathVariable String dedicated, @PathVariable String tglE, @PathVariable String tglA) {
		armadaService.insertDataArmada(noKend, jnsKend, jenisBbm, capacity, kubikase, kmAkhir, statusAktif, tonase, jenisMuat, driverCode, 
				helperCode, statusKend, stdIsi, maxRit, dedicated, tglE, tglA);
	}
	
	@DeleteMapping(path = "/deletedataarmada/{noKend}")
	public void deleteDataArmada(@PathVariable String noKend) {
		armadaService.deleteDataArmada(noKend);
	}
	
	@DeleteMapping(path = "/deletedataarmada-temp/{noKend}")
	public String deleteDataArmadaTemp(@PathVariable String noKend) {
		return armadaService.deleteDataArmadaTemp(noKend);
	}
	
	@PutMapping(path = "/updatedataarmada")
	public List<ArmadaAllDto> updateDataArmada(@RequestBody List<ArmadaAllDto> dataArmada){
		return armadaService.updateDataArmada(dataArmada);
	}
	
	@GetMapping(path = "/cekkendrekap", params = {"carNo"})
	public List<Object[]> cekKendRekap(@RequestParam String carNo) {
		return armadaService.cekKendRekap(carNo);
	}
	
	@GetMapping(path = "/cekkenddelivery", params = {"noKend"})
	public List<Object[]> cekKendDelivery(@RequestParam String noKend) {
		return armadaService.cekKendDelivery(noKend);
	}
	
	@GetMapping(path = "/cekkendar", params = {"noKend"})
	public String cekKendar(@RequestParam String noKend) {	
		return armadaService.cekKendar(noKend);
	}
	
	@GetMapping(path ="/getxkey")
	public String getXkey() {
		return armadaService.getXkey();
	}
	
	@PostMapping(path = "/insertdataarmada-temp")
	public List<ArmadaAllDto> insertDataArmadaTemp(@RequestBody List<ArmadaAllDto> dataArmadaTemp){
		return armadaService.insertDataArmadaTemp(dataArmadaTemp);
	}
	
	@GetMapping(path ="genkey-auth",params= {"xKey","xAuth"})
	public String cekKeyAuth(@RequestParam String xKey, @RequestParam String xAuth) {
		return armadaService.cekKeyAuth(xKey, xAuth);
	}
	
	@GetMapping(path ="cek-antrian",params= {"xUser"})
	public String cekAntrian(@RequestParam String xUser) {
		 boolean cek = armadaService.cekAntian("FTABLE03", xUser);
		 String cekk = String.valueOf(cek);
		 return cekk;
	}
	
	@PostMapping(path = "/insertantrian", params = {"xUser"})
	public void insertAntrian(@RequestParam String xUser){
		armadaService.insertAntiran("FTABLE03", xUser);
	}
	
	@DeleteMapping(path = "/deleteantrian", params = {"xUser"})
	public void deleteAntrian(@RequestParam String xUser){
		armadaService.deleteAntrian("FTABLE03", xUser);
	}
	
	@PutMapping(path = "/deletedataarmada-temp-final")
	public List<ArmadaAllDto> deleteDataArmadaTempFinal(@RequestBody List<ArmadaAllDto> dataArmadaTemp){
		return armadaService.deleteDataArmadaTempFinal(dataArmadaTemp);
	}
	
	
	
//	@GetMapping(path = "/cek", params = {"carNo"})
//	public List<SelectItem<String>> cek(@RequestParam String carNo) {
//		return armadaService.cek(carNo);
//	}
	
}
