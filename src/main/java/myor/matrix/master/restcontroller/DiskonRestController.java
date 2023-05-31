package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.DiskonService;

@RestController
@RequestMapping(path = "/diskon")
public class DiskonRestController {
	
	@Autowired
	private DiskonService diskonService;
	
	@GetMapping(path = "/tactical")
	public List<SelectItem<String>> getSelectItemMasterTactical() {
		return diskonService.getSelectItemMasterTactical();
	}
	
	@GetMapping(path = "/cod")
	public List<SelectItem<String>> getDataMasterCod() {
		return diskonService.getDataMasterCod();
	}
	
}
