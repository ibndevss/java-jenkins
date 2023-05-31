package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ProductBrowserHargaSpesifikDto;
import myor.matrix.master.entity.ViewHargaSpesifikDto;

public interface ViewHargaSpesifikRepository {

	List<ProductBrowserHargaSpesifikDto> getListHargaSpesifik();

	List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTgl();

	List<ProductBrowserHargaSpesifikDto> getListHargaSpesifikByTipe();

}
