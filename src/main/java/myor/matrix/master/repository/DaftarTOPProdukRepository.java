package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.DaftarTOPProdukDto;

public interface DaftarTOPProdukRepository {
	public List<DaftarTOPProdukDto> getReport(String pilihanData, String pilihanData2, String filterFrom, String filterTo);
}
