package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.KolektorService;

@RestController
@RequestMapping(path = "/kolektor")
public class KolektorRestController {

	@Autowired
	private KolektorService kolektorService;
	
	@GetMapping(path = "/select-item")
	public List<SelectItem<String>> getList() {
		return kolektorService.getList();
	}
}
