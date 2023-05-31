package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.service.LokasiService;

@RestController
@RequestMapping(path = "/lokasi")
public class LokasiRestController {

    @Autowired
    private LokasiService lokasiService;

    @GetMapping(path = "/browse")
	public List<SearchBrowseDto> getBrowse() {
		return lokasiService.getBrowseLokasi();
	}
}
