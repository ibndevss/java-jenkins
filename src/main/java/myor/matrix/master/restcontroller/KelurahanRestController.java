package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.service.KelurahanService;

@RestController
@RequestMapping(path = "/kelurahan")
public class KelurahanRestController {

	@Autowired
	 private KelurahanService kelurahanService;
	
  @GetMapping(path = "/browse")
  public List<SearchFromHierarkiBrowseDto> getBrowse() {
      return kelurahanService.getBrowse();
  }
}
