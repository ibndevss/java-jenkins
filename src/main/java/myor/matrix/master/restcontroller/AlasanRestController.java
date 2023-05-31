package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.AlasanBrowseDto;
import myor.matrix.master.service.AlasanService;

@RestController
@RequestMapping(path = "/alasan")
public class AlasanRestController {

	@Autowired
	private AlasanService serviceAlasan;
	
	@GetMapping(path = "/browse")
	public List<AlasanBrowseDto> getBrowseAlasan(){
		return serviceAlasan.getBrowse();
	}
}
