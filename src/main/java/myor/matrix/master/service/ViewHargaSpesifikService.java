package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.ProductBrowserHargaSpesifikDto;
import myor.matrix.master.entity.ViewHargaSpesifikDto;

public interface ViewHargaSpesifikService {

	List<ProductBrowserHargaSpesifikDto> getListHargaSpesifik();

	List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTgl();

	List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTipe();

}
