package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.DocumentPengajuanReturBrowseDto;
import myor.matrix.master.service.DocumentPengajuanReturService;

@RestController
@RequestMapping(path = "/dokumen-pengajuan-retur")
public class DocumentPengajuanReturRestController {
	@Autowired
	private DocumentPengajuanReturService pengajuanRepo;
	@GetMapping(path = "/browse")
	public List<DocumentPengajuanReturBrowseDto> getBrowseDocumentPengajuanRetur(){
		return pengajuanRepo.getBrowseDocumentPengajuanRetur();
	}
}
