package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.service.SubRuteService;

@RestController
@RequestMapping(path = "/sub-rute")
public class SubRuteRestController {

	@Autowired
	private SubRuteService subRuteService;
	
    @GetMapping(path = "/browse")
	public List<SearchFromHierarkiBrowseDto> getBrowse() {
		return subRuteService.getBrowse();
	}
}
