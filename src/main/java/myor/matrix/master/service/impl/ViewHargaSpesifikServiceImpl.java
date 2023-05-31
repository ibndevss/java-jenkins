package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ProductBrowserHargaSpesifikDto;
import myor.matrix.master.entity.ViewHargaSpesifikDto;
import myor.matrix.master.repository.ViewHargaSpesifikRepository;
import myor.matrix.master.service.ViewHargaSpesifikService;

@Service
public class ViewHargaSpesifikServiceImpl implements ViewHargaSpesifikService {
	
	@Autowired
	private ViewHargaSpesifikRepository repository;

	@Override
	public List<ProductBrowserHargaSpesifikDto> getListHargaSpesifik() {
		// TODO Auto-generated method stub
		return repository.getListHargaSpesifik();
	}

	@Override
	public List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTgl() {
		// TODO Auto-generated method stub
		return repository.getListHargaSpesifikByTgl();
	}

	@Override
	public List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTipe() {
		// TODO Auto-generated method stub
		return repository.getListHargaSpesifikByTipe();
	}
	
	
}
