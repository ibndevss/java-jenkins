package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.DaftarNewHargaSpesifikDto;

public interface DaftarNewHargaSpesifikRepository {
	public List<DaftarNewHargaSpesifikDto> getReport(String umbrellaFrom, String umbrellaTo, String brandFrom,
			String brandTo, String subBrandFrom, String subBrandTo, String pcodeFrom, String pcodeTo,
			String tglBerlakuFrom, String tglBerlakuTo, String pilihanData, String sortingData);
}
