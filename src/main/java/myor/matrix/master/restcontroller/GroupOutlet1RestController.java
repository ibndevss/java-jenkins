package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.service.GroupOutlet1Service;

@RestController
@RequestMapping(path = "/group-outlet1")
public class GroupOutlet1RestController {

	@Autowired
	private GroupOutlet1Service groupOutlet1Service;
	
    @GetMapping(path = "/browse")
	public List<SearchBrowseDto> getBrowse() {
		return groupOutlet1Service.getBrowse();
	}
}
