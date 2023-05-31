package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.service.DistrikService;

@RestController
@RequestMapping(path = "/distrik")
public class DistrikRestController {

	@Autowired
	private DistrikService distrikService;
	
    @GetMapping(path = "/browse")
	public List<SearchBrowseDto> getBrowse() {
		return distrikService.getBrowse();
	}
}
