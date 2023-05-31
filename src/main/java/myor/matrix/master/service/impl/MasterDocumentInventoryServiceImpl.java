package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ListDocumentDto;
import myor.matrix.master.repository.MasterDocumentInventoryRepository;
import myor.matrix.master.service.MasterDocumentInventoryService;

@Service
public class MasterDocumentInventoryServiceImpl implements MasterDocumentInventoryService {
	
	@Autowired
	private MasterDocumentInventoryRepository mDocInvenRepo;

	@Override
	public List<ListDocumentDto> getListDocumentPenurunanStockVan(String type) {
		// TODO Auto-generated method stub
		return mDocInvenRepo.getListDocumentPenurunanStockVan(type);
	}

	@Override
	public List<ListDocumentDto> getListDocumentBookingPenambahanBarangVan() {
		// TODO Auto-generated method stub
		return mDocInvenRepo.getListDocumentBookingPenambahanBarangVan();
	}

	@Override
	public List<ListDocumentDto> getListDocumentPenambahanBarangVan() {
		// TODO Auto-generated method stub
		return mDocInvenRepo.getListDocumentPenambahanBarangVan();
	}

	
}
