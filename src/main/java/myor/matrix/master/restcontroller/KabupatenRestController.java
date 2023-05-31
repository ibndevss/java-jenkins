package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.service.KabupatenService;

@RestController
@RequestMapping(path = "/kabupaten")
public class KabupatenRestController {

	@Autowired
	 private KabupatenService kabupatenService;
	
  @GetMapping(path = "/browse")
  public List<SearchFromHierarkiBrowseDto> getBrowse() {
      return kabupatenService.getBrowse();
  }
  
}
