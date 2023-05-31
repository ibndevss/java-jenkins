package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.MappingOtorisasiValueAdd;
import myor.matrix.master.entity.MappingOtorisasiValueDTO;
import myor.matrix.master.service.MappingOtorisasiValueService;

@RestController
@RequestMapping(path = "/map_otor_val")
public class MappingOtorisasiValueRestController {

	@Autowired
	private MappingOtorisasiValueService mappingOtorisasiValueService;

	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		List<String> result = new ArrayList<String>();
		result.add("27 MEI 2022");
		return result;
	}

	@GetMapping(path = "/inisialisasi")
	public List<MappingOtorisasiValueDTO> inisialisasiMapping() {
		return mappingOtorisasiValueService.inisialisasiMapping();
	}

	@PostMapping(path = "/delete/{pejabat-id}")
	public List<MappingOtorisasiValueDTO> deleteMappingByPejabat(@RequestBody MappingOtorisasiValueAdd data) {
		return mappingOtorisasiValueService.deleteMapping(data.getPejabatId(), data.getTeams());
	}

	@PostMapping(path = "/insert")
	public List<MappingOtorisasiValueDTO> insertMapping(@RequestBody MappingOtorisasiValueAdd data) {
		return mappingOtorisasiValueService.insertMapping(data.getNilaiFrom(), data.getNilaiTo(), data.getTeams(),
				data.getPejabatId(), data.getPejabatName());
	}

}
