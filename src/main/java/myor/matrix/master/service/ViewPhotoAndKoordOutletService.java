package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.ViewPhotoAndKoordOutletDto;

public interface ViewPhotoAndKoordOutletService {
	List<ViewPhotoAndKoordOutletDto> getViewPhotoAndKoordOutlet(String custno);
	void insertCustnoToFtable275();
	boolean cekPathPhoto();
}
