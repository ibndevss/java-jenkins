package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.service.GroupDiskonService;

@RestController
@RequestMapping(path = "/group-diskon")
public class GroupDiskonRestController {

	@Autowired
	private GroupDiskonService groupDiskonService;
	
    @GetMapping(path = "/browse")
	public List<SearchBrowseDto> getBrowse() {
		return groupDiskonService.getBrowse();
	}
}
