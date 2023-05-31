package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;
import myor.matrix.master.service.SubLokasiService;

@RestController
@RequestMapping(path = "/sub-lokasi")
public class SubLokasiRestController {

	 @Autowired
	 private SubLokasiService servSubLokasi;
	
    @GetMapping(path = "/browse")
    public List<SearchFromHierarkiBrowseDto> getBrowseSubLokasi() {
        return servSubLokasi.getBrowseSubLokasi();
    }
}
