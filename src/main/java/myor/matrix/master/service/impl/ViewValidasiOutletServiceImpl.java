package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ViewValidasiOutletDto;
import myor.matrix.master.repository.ViewValidasiOutletRepository;
import myor.matrix.master.service.ViewValidasiOutletService;

@Service
public class ViewValidasiOutletServiceImpl implements ViewValidasiOutletService{
	@Autowired
	ViewValidasiOutletRepository viewValidasiOutletRepository;

	@Override
	public List<ViewValidasiOutletDto> viewValidasiOutlet(String dateSubmitFrom, String dateSubmitTo) {
		// TODO Auto-generated method stub
		return viewValidasiOutletRepository.viewValidasiOutlet(dateSubmitFrom, dateSubmitTo);
	}
	

}
