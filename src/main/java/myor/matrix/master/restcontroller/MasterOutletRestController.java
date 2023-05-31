package myor.matrix.master.restcontroller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.GroupPayerBrowseDto;
import myor.matrix.master.entity.MasterOutletBrowseDto;
import myor.matrix.master.entity.MasterOutletDataCoverDto;
import myor.matrix.master.entity.MasterOutletDto;
import myor.matrix.master.entity.MasterOutletFormShowDto;
import myor.matrix.master.entity.MasterOutletSaveDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.service.MasterOutletService;

@RestController
@RequestMapping(path = "/outlet")
public class MasterOutletRestController {

	@Autowired
	private MasterOutletService mOService;

	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		try {
			List<String> result = new ArrayList<String>();
			result.add("02 SEPTEMBER 2022");
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

	@GetMapping(path = "/select-divisi/{groupDivisi}")
	public String[] getSelectDivisi(@PathVariable String groupDivisi) {
		return mOService.getSelectDivisi(groupDivisi);
	}

	@GetMapping(path = "/form-show")
	public MasterOutletFormShowDto getFormShow() {
		return mOService.getFormShow();
	}

	@GetMapping(path = "/get-outlet", params = { "custno", "group", "value" })
	public MasterOutletDto getOutlet(@RequestParam String custno, @RequestParam String group, @RequestParam String value) {
		return mOService.getMasterOutlet(custno, group, value);
	}
	
	@GetMapping(path = "/browse-outlet", params = { "divisi", "taskforce" })
	public List<MasterOutletBrowseDto> getBrowseMasterOutlet(@RequestParam String divisi,@RequestParam String taskforce) {
		return mOService.getBrowserMasterOutlet(divisi, taskforce);
	}
	
	@GetMapping(path = "/suggest-no-outlet", params = { "divisi" })
	public String[] getSuggestNoOutlet(@RequestParam String divisi) {
		return mOService.getSuggestNoOutlet(divisi);
	}
	
	@GetMapping(path = "/cek-edit-customer", params = { "custNo" })
	public String[] cekEditCustomer(@RequestParam String custNo) {
		return mOService.cekEditCustomer(custNo);
	}

	@PutMapping(path = "/save-outlet", params = { "divisi", "taskforce" })
	public String[] updateSalesman(@RequestBody MasterOutletSaveDto datas, @RequestParam String divisi, @RequestParam String taskforce) {
		return mOService.saveOutlet(datas, divisi, taskforce);
	}

	@PostMapping(path = "/delete-cover", params = { "custNo", "slsNo", "tglGudang" })
	public void deleteOutletCOver(@RequestParam String custNo, @RequestParam String slsNo, @RequestParam String tglGudang) {
		mOService.deleteCoverOutlet(custNo, slsNo, tglGudang);
	}
	
	@GetMapping(path = "/cek-edit-cover", params = { "custNo", "slsNo", "divisi" })
	public String[] cekEditCover(@RequestParam String custNo, @RequestParam String slsNo, @RequestParam String divisi) {
		return mOService.cekEditCover(custNo, slsNo, divisi);
	}
	
	@PostMapping(path = "/edit-cover", params = { "tglGudang" })
	public void editCover(@RequestBody MasterOutletDataCoverDto p, @RequestParam String tglGudang) {
		mOService.updateCover(p, tglGudang);
	}

	@PostMapping(path = "/add-cover", params = { "tglGudang" })
	public String[] addCover(@RequestBody MasterOutletDataCoverDto p, @RequestParam String tglGudang) {
		return mOService.addCover(p, tglGudang);
	}

	@GetMapping(path = "/browse-salesman", params = { "divisi", "taskforce" })
	public List<SalesmanBrowseDto> getBrowseSalesman(@RequestParam String divisi,@RequestParam String taskforce) {
		return mOService.getBrowseSalesman(divisi, taskforce);
	}

	@GetMapping(path = "/browse-group-payer", params = { "taskforce" })
	public List<GroupPayerBrowseDto> getBrowseGroupPayer(@RequestParam String taskforce) {
		return mOService.getBrowseGroupPayer(taskforce);
	}

	@GetMapping(path = "/browse-chiller")
	public List<SearchBrowseDto> getBrowseChiller() {
		return mOService.getBrowseChiller();
	}

}
