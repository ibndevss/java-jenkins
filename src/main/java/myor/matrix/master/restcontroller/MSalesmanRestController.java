package myor.matrix.master.restcontroller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.DataSalesmanDto;
import myor.matrix.master.entity.MSalesmanAdd;
import myor.matrix.master.entity.MSalesmanChoosenDto;
import myor.matrix.master.entity.MSalesmanDto;
import myor.matrix.master.entity.MSalesmanKolektor;
import myor.matrix.master.entity.MSalesmanProductChoosen;
import myor.matrix.master.entity.SalesforceBrowseDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.MSalesmanService;

@RestController
@RequestMapping(path = "/master-Msalesman")
public class MSalesmanRestController {

	@Autowired
	private MSalesmanService mSalesmanService;

	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		List<String> result = new ArrayList<String>();
		result.add("01 AUG 2022");
		return result;
	}

	@GetMapping(path = "/get-ftable13/{param}")
	public List<Object[]> getFtable13(@PathVariable String param) {
		return mSalesmanService.getMasterFtable13(param);
	}

	@GetMapping(path = "/team-product/{team}")
	public List<MSalesmanProductChoosen> getProductTeam(@PathVariable String team) {
		return mSalesmanService.getTeamProduct(team);
	}

	@PostMapping(path = "/util/insert-penggunaan/{tableName}/{noSeq}/{userId}")
	public void insertPenggunaan(@PathVariable String tableName, @PathVariable String noSeq,
			@PathVariable String userId) {
		mSalesmanService.insertPenggunaan(tableName, noSeq, userId);
	}

	@GetMapping(path = "/get-saran-slsno")
	public BigDecimal getSaran() {
		return mSalesmanService.getSaran();
	}

	@GetMapping(path = "/browse-salesman")
	public List<SalesmanBrowseDto> getListSalesman() {
		return mSalesmanService.getListSelectItemSalesman();
	}

	@GetMapping(path = "/select-team")
	public List<SelectItem<String>> getListTeam() {
		return mSalesmanService.getListTeam();
	}

	@GetMapping(path = "/select-rayon")
	public List<SelectItem<String>> getListRayon() {
		return mSalesmanService.getListRayon();
	}

	@GetMapping(path = "/select-salesforce")
	public List<SalesforceBrowseDto> getListSalesforce() {
		return mSalesmanService.getListSalesforce();
	}

	@GetMapping(path = "/select-pejabat")
	public List<Object[]> getListPejabat() {
		return mSalesmanService.getListPejabat();
	}

	@GetMapping(path = "/select-gudang")
	public List<SelectItem<String>> getListGudang() {
		return mSalesmanService.getListGudang();
	}

	@GetMapping(path = "/select-kolektor")
	public List<SelectItem<String>> getListKolektor() {
		return mSalesmanService.getListKolektor();
	}

	@GetMapping(path = "/select-customer")
	public List<SelectItem<String>> getListCustomer() {
		return mSalesmanService.getListCustomer();
	}

	@GetMapping(path = "/select-salesemp")
	public List<SelectItem<String>> getListSalesEmp() {
		return mSalesmanService.getListSalesEmp();
	}

	@GetMapping(value = "/{slsno}")
	public MSalesmanDto getSalesmanDto(@PathVariable(name = "slsno") String slsno) {
		return mSalesmanService.getSalesmanDto(slsno);
	}

	@GetMapping(value = "/kolektor/{empno}")
	public MSalesmanKolektor getKolektorChoosen(@PathVariable(name = "empno") String empno) {
		return mSalesmanService.getKolektorChoosen(empno);
	};

	@GetMapping(value = "/blok/{slsno}")
	public MSalesmanDto blokSalesman(@PathVariable(name = "slsno") String slsno) {
		return mSalesmanService.blokSalesman(slsno);
	}

	@PostMapping(path = "/add")
	public MSalesmanDto addSalesman(@RequestBody MSalesmanAdd dataAddSalesman) {

		try {
			return mSalesmanService.addSalesman(dataAddSalesman);
		} catch (Exception e) {
			// TODO: handle exception
			if (e.getCause() != null) {				
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Repository! " + e.getCause().getCause().getLocalizedMessage());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Service! " + e.getMessage());
			}
		}
	}

	@PutMapping(path = "/update")
	public MSalesmanDto updateSalesman(@RequestBody MSalesmanAdd dataUpdateSalesman) {

		return mSalesmanService.updateSalesman(dataUpdateSalesman);
	}
	
	@PostMapping(path = "/add-salesman")
    public String[] addDataSalesman(@RequestBody DataSalesmanDto dataSalesman) {
		return mSalesmanService.addDataSalesman(dataSalesman);
	}
	
	@PostMapping(path = "/edit-salesman")
    public String[] updateDataSalesman(@RequestBody DataSalesmanDto dataSalesman) {
		return mSalesmanService.updateDataSalesman(dataSalesman);
	}
	
	@GetMapping(path = "/cek-add-salesman")
    public String[] cekAddSalesman() {
		return mSalesmanService.cekAddSalesman();
	}
	
	@PutMapping(path = "/cek-blok-salesman")
    public String[] cekBlokSalesman(@RequestBody DataSalesmanDto dataSalesman) {
		return mSalesmanService.cekBlokSalesman(dataSalesman);
	}
	
	@PutMapping(path = "/process-blok-salesman")
    public String[] processBlokSalesman(@RequestBody DataSalesmanDto dataSalesman) {
		return mSalesmanService.processBlokSalesman(dataSalesman);
	}
	
	@PutMapping(path = "/cek-edit-salesman")
    public String[] cekEditSalesman(@RequestBody DataSalesmanDto dataSalesman) {
		return mSalesmanService.cekEditSalesman(dataSalesman);
	}
	
	@PutMapping(path = "/delete-antrian")
    public void deleteAntrian(@RequestBody DataSalesmanDto dataSalesman) {
		mSalesmanService.deleteAntrian(dataSalesman);
	}
	
	@GetMapping(path = "/cek-slsno/{slsNo}")
    public String[] cekCekSlsNo(@PathVariable(name = "slsNo") String slsNo) {
		return mSalesmanService.getCekNewNoSlsNo(slsNo);
	}
	
	@GetMapping(path = "/get-new-slsno")
    public String[] getSuggestNewNoSalesman() {
		return mSalesmanService.getSuggestNewNoSalesman();
	}
	
	@GetMapping(path = "/summary")
	public List<MSalesmanChoosenDto> getDataSalesmanSummary(){
		return mSalesmanService.getDataSalesmanSummary();
	}

}
