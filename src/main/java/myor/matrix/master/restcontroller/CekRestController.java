package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import myor.matrix.master.entity.CekAllDto;
import myor.matrix.master.entity.CekBankChoosen;
import myor.matrix.master.entity.CekChoosen;
import myor.matrix.master.entity.CekCustChoosen;
import myor.matrix.master.entity.CekCustDto;
import myor.matrix.master.entity.CekDto;
import myor.matrix.master.entity.CekNoCheck;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.CekService;

@RestController
@RequestMapping(path = "/cek")
public class CekRestController {

	@Autowired
	private CekService cekService;
	
	@GetMapping(path = "/date-now-system")
	public String getDateNowBySystem() {
		List<String> result = new ArrayList<>();
		result.add(cekService.getDateNowBySystem());
	return cekService.getDateNowBySystem();
	}
	
	@GetMapping(path = "/getdatacek")
	public List<CekDto> getDataCek(){
		return cekService.getDataCek();
	}
	
	@GetMapping(path = "/getdataallcek")
	public List<CekAllDto> getDataAllCek(){
		return cekService.getDataAllCek();
	}
	
	@GetMapping(path = "/{checkNo}")
	public CekChoosen getCekChoosen(@PathVariable(name = "checkNo") String checkNo) {
		return cekService.getCekChoosen(checkNo);
	}
	
	@GetMapping(path = "/getdatabank")
	public List<SelectItem<String>> getDataBank(){
		return cekService.getDataBank();
	}
	
	@GetMapping(value = "/selectbank/{bankNo}")
	public List<CekBankChoosen> getCekBank(@PathVariable String bankNo){
		return cekService.getCekBank(bankNo);
	}
	
	@GetMapping(path = "/getdatacust")
	public List<CekCustDto> getDataCust(){
		return cekService.getDataCust();
	}
	
	@GetMapping(value = "/selectcust/{custNo}")
	public List<CekCustChoosen> getCekCust(@PathVariable String custNo){
		return cekService.getCekCust(custNo);
	}
	
	@GetMapping(path = "/getdatabankcollection")
	public List<SelectItem<String>> getDataBankCollection(){
		return cekService.getDataBankCollection();
	}
	
	@GetMapping(value = "/selectbankcollection/{bankNo}")
	public List<CekBankChoosen> getCekBankCollection(@PathVariable String bankNo){
		return cekService.getCekBankCollection(bankNo);
	}
	
	@PostMapping(path = "/insertdatacek/{checkNo}/{checkDate}/{checkDuedT}/{bank}/{accNo}/{checkAmount}/"
			+ "{custNo}/{flag}/{bankCair}/{userId}")
	public void insertDataCek(@PathVariable String checkNo, @PathVariable String checkDate,@PathVariable String checkDuedT,
			@PathVariable String bank,@PathVariable String accNo,@PathVariable String checkAmount,@PathVariable String custNo,
			@PathVariable String flag,@PathVariable String bankCair, @PathVariable String userId) {
		cekService.insertDataCek(checkNo, checkDate, checkDuedT, bank, accNo, checkAmount, custNo, flag, bankCair, userId);
	
	}
	
	@PostMapping(path = "/insertdatatransfer/{checkNo}/{checkDate}/{bank}/{accNo}/{checkAmount}/"
			+ "{custNo}/{flag}/{bankTransfer}/")
	public void insertDataTransfer(@PathVariable String checkNo, @PathVariable String checkDate,
			@PathVariable String bank,@PathVariable String accNo,@PathVariable String checkAmount,@PathVariable String custNo,
			@PathVariable String flag,@PathVariable String bankTransfer) {
		cekService.insertDataTransfer(checkNo, checkDate, bank, accNo, checkAmount, custNo, flag, bankTransfer);
	
	}
	
	@PutMapping(path = "/updatedatacek")
	public CekChoosen updateDataCek(@RequestBody CekChoosen dataCek){
		return cekService.updateDataCek(dataCek);
	}
	
	@GetMapping(path ="/trsno/{checkNo}")
	public String getTransferNo (@PathVariable String checkNo) {
		return cekService.getTransferNo (checkNo);
	}
	
	@GetMapping(path ="/cekno/{checkNo}")
	public String getCheckNo (@PathVariable String checkNo) {
		return cekService.getCheckNo (checkNo);
	}
	
	@GetMapping(value ="/cekdate/{checkNo}")
	public List<SelectItem<String>> getDateCek (@PathVariable String checkNo) {
		return cekService.getDateCek(checkNo);
	}
	
	@PostMapping(path = "/insert-penggunaan/{tableName}/{noSeq}/{userId}")
	public void insertPenggunaan(@PathVariable String tableName, @PathVariable String noSeq, @PathVariable String userId) {
		cekService.insertPenggunaan(tableName,noSeq,userId);
	}
	
	@DeleteMapping(path = "/deletedatacek/{checkNo}")
	public void deleteDataCek(@PathVariable String checkNo) {
		cekService.deleteDataCek(checkNo);
	}
	
	@GetMapping(path ="/getmemointtf")
	public String getMemointPatamTf() {
		return cekService.getMemointPatamTf();
	}
	
	@GetMapping(path ="/getkey")
	public int getKey() {
		return cekService.getKeyAuth();
	}
	
	@GetMapping(path ="/keyok/{key}/{auth}")
	public String getKeyOk(@PathVariable String key, @PathVariable String auth) {
		return cekService.getKeyOk(key, auth);
	}
		
	
}
