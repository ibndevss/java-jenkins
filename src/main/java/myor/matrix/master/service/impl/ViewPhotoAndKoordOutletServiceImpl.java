package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ViewPhotoAndKoordOutletDto;
import myor.matrix.master.repository.ViewPhotoAndKoordOutletRepository;
import myor.matrix.master.service.ViewPhotoAndKoordOutletService;

@Service
public class ViewPhotoAndKoordOutletServiceImpl implements ViewPhotoAndKoordOutletService {
	@Autowired
	ViewPhotoAndKoordOutletRepository viewPhotoRepository;

	@Override
	public List<ViewPhotoAndKoordOutletDto> getViewPhotoAndKoordOutlet(String custno) {
		// TODO Auto-generated method stub
		return viewPhotoRepository.getViewPhotoAndKoordOutlet(custno);
	}

	@Override
	public void insertCustnoToFtable275() {
		// TODO Auto-generated method stub
		viewPhotoRepository.insertCustnoToFtable275();
	}

	@Override
	public boolean cekPathPhoto() {
		// TODO Auto-generated method stub
		return viewPhotoRepository.cekPathPhoto();
	}

}
