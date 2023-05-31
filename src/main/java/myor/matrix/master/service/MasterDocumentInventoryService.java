package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.ListDocumentDto;

public interface MasterDocumentInventoryService {

	public List<ListDocumentDto> getListDocumentPenurunanStockVan(String type);
	
	public List<ListDocumentDto> getListDocumentBookingPenambahanBarangVan();
	public List<ListDocumentDto> getListDocumentPenambahanBarangVan();
}
