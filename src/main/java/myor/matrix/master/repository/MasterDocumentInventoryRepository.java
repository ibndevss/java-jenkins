package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ListDocumentDto;

public interface MasterDocumentInventoryRepository {

	public List<ListDocumentDto> getListDocumentPenurunanStockVan(String type);
	
	public List<ListDocumentDto> getListDocumentBookingPenambahanBarangVan();
	public List<ListDocumentDto> getListDocumentPenambahanBarangVan();
}
