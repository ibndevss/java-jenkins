package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.service.GroupOutlet2Service;

@RestController
@RequestMapping(path = "/group-outlet2")
public class GroupOutlet2RestController {

	@Autowired
	private GroupOutlet2Service groupOutlet2Service;
	
    @GetMapping(path = "/browse")
	public List<SearchBrowseDto> getBrowse() {
		return groupOutlet2Service.getBrowse();
	}
}
