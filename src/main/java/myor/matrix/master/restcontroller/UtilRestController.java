package myor.matrix.master.restcontroller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ConvUnitPcodeDto;
import myor.matrix.master.entity.DetailDate;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.UtilService;

@RestController
@RequestMapping(path = "/util")
public class UtilRestController {

	@Autowired
	private UtilService utilService;

	@GetMapping(path = "/date-now-system")
	public List<String> getDateNowBySystem() {
		List <String> result = new ArrayList<>();
		result.add(utilService.getDateNowBySystem());
		return result;
	}

	@GetMapping(path = "/date-now-warehouse")
	public List<String> getDateNowByWarehouse() {
		List<String> result = new ArrayList<>();
		result.add(utilService.getDateNowByWarehouse());
		return result;
	}
	
	@GetMapping(path = "/date-now-warehouse-yyyy-mm-dd")
	public List<String> getDateNowByWarehouseFormatYYYMMDD(){
		List<String> result = new ArrayList<>();
		result.add(utilService.getDateNowByWarehouseFormatYYYMMDD());
		return result;
	}

	@GetMapping(path = "/cek-params/{xkey}")
	public String cekParam(@PathVariable String xkey) {
		return utilService.cekParam(xkey);
	}

	@GetMapping(path = "/conv-unit-pcode/{pcode}")
	public List<ConvUnitPcodeDto> getConvUnit(@PathVariable String pcode) {
		return utilService.getConvUnit(pcode);
	}

	@GetMapping(path = "/get-antrian/{tableName}/{field}/{noSeq}/{xkey}/{userId}")
	public String getAntrian(@PathVariable String tableName, @PathVariable String field, @PathVariable String noSeq,
			@PathVariable String xkey, @PathVariable String userId) {
		return utilService.getAntrianNoSeq(tableName, field, noSeq, xkey, userId, true);
	}

	@PostMapping(path = "/insert-penggunaan/{tableName}/{noSeq}/{userId}")
	public void insertPenggunaan(@PathVariable String tableName, @PathVariable String noSeq,
			@PathVariable String userId) {
		utilService.insertPenggunaan(tableName, noSeq, userId);
	}

	@GetMapping(path = "/list-otorisasi-top")
	public List<SelectItem<String>> getPilihanOtorisasiTop() {
		return utilService.getPilihanOtorisasiTop();
	}
	
	@GetMapping(path = "/get-xqty/{pcode}/{qty}")
	public String getXqty(@PathVariable String pcode,@PathVariable String qty){
		return utilService.getXqty(pcode, qty);
	}
	
  	@GetMapping(path = "/get-date-detail", params = {"date"})
	public DetailDate getDateDetail(@RequestParam String date) {
		return utilService.getDateDetail(date);
	}
  	
  	@GetMapping(path = "/get-antrian/ro/{userId}")
	public String getAntrianRO(@PathVariable String userId) {
//		return utilService.getAntrianRO(userId);
		return utilService.getSequence("seq_realorder", "ORDERNO", "FORDER_H");
	}
  	
  	@GetMapping(path = "/get-antrian/stock-outlet/{userId}")
	public String getAntrianStockOutlet(@PathVariable String userId) {
		return utilService.getAntrianStockOutlet(userId);
	}
  	
  	@GetMapping(path = "/time-now")
	public List<String> getTimeNow() {
		List<String> result = new ArrayList<>();
		result.add(utilService.getTimeNow());
		return result;
	}
  	
  	@DeleteMapping(path = "/del-user-modul", params = {"namaTable", "key"})
  	public void deleteFprgactByNamaTableKey(@RequestParam String namaTable, @RequestParam String key) {
  		utilService.deleteFprgactByNamaTableKey(namaTable, key);
  	}
  	
  	@GetMapping(path = "/cek-penggunaan", params = {"tableName", "noSeq"})
  	public int cekPenggunaan(@RequestParam String tableName, @RequestParam String noSeq) {
  		return utilService.cekPenggunaan(tableName, noSeq);
  	}
  	
  	@GetMapping(path = "/cek-penggunaan-detail", params = {"tableName", "noSeq", "user"})
	public List<String> cekPenggunaanDetail(@RequestParam String tableName, @RequestParam String noSeq, @RequestParam String user) {
  		return utilService.cekPenggunaanDetail(tableName, noSeq, user);
  	}
  	
  	@DeleteMapping(path = "/del-user-modul-by-name", params = {"namaTable", "user"})
  	public void deleteFprgactByNamaTableAndUser(@RequestParam String namaTable, @RequestParam String user) {
  		utilService.deleteFprgactByNamaTableAndUser(namaTable, user);
  	}

}
