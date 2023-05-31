package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.GudangService;

@RestController
@RequestMapping(path = "/gudang")
public class GudangRestController {

	@Autowired
	private GudangService gudangService;

	@GetMapping(path = "/select-item")
	public List<SelectItem<String>> getListGudang() {
		return gudangService.getListGudang();
	}
	
	@GetMapping(path="/select-item/all")
	public List<SelectItem<String>> getListAllGudang() {
		return gudangService.getListAllGudang();
	}
	
	@GetMapping(path="/select-item/mutasi",params={"kdGudang","flagWhTransisi"})
	public List<SelectItem<String>> getListMutasiGudang(@RequestParam String kdGudang, @RequestParam String flagWhTransisi) {
		return gudangService.getListMutasiGudang(kdGudang, flagWhTransisi);
	}
}
