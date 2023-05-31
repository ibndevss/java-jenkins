package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.service.KlasifikasiService;

@RestController
@RequestMapping(path = "/klasifikasi")
public class KlasifikasiRestController {

	@Autowired
	 private KlasifikasiService servKlasifikasi;
	
    @GetMapping(path = "/browse")
    public List<SearchFromHierarkiBrowseDto> getBrowse() {
        return servKlasifikasi.getBrowseKlasifikasi();
    }
}
