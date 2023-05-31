package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.TypeOutletBrowseDto;
import myor.matrix.master.service.TipeOutletService;

@RestController
@RequestMapping(path = "/tipe-outlet")
public class TipeOutletRestController {

	@Autowired
	private TipeOutletService tipeOutletService;
	
    @GetMapping(path = "/browse")
	public List<SearchBrowseDto> getBrowse() {
		return tipeOutletService.getBrowse();
	}
    
    @GetMapping(path= "/browse-type")
    public List<TypeOutletBrowseDto> browseTypeOutlet() {
    	return tipeOutletService.browseTypeOutlet();
    }
}
