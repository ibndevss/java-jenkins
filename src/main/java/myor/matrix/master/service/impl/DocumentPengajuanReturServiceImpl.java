package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.DocumentPengajuanReturBrowseDto;
import myor.matrix.master.repository.DocumentPengajuanReturRepository;
import myor.matrix.master.service.DocumentPengajuanReturService;
@Service
public class DocumentPengajuanReturServiceImpl implements DocumentPengajuanReturService {

	@Autowired
	private DocumentPengajuanReturRepository pengajuanRepo;
	@Override
	public List<DocumentPengajuanReturBrowseDto> getBrowseDocumentPengajuanRetur() {
		// TODO Auto-generated method stub
		return pengajuanRepo.getBrowseDocumentPengajuanRetur();
	}

}
