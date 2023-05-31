package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.KompetitorService;

@RestController
@RequestMapping(path = "/kompetitor")
public class KompetitorRestController {
	
	@Autowired
	private KompetitorService kompetitorService;
	
	@GetMapping(path = "/browse")
	public List<SelectItem<String>> getBrowseKompetitor() {
		return kompetitorService.getBrowseKompetitor();
	}
	
}
