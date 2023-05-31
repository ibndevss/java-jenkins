package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ViewPhotoAndKoordOutletDto;

public interface ViewPhotoAndKoordOutletRepository {
	List<ViewPhotoAndKoordOutletDto> getViewPhotoAndKoordOutlet(String custno);
	void insertCustnoToFtable275();
	boolean cekPathPhoto();
}
