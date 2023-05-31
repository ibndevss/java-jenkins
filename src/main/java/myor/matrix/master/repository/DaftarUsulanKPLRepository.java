package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.DaftarUsulanKPLDto;

public interface DaftarUsulanKPLRepository {

	public List<DaftarUsulanKPLDto> getReport(String slsnoFrom, String slsnoTo, String tglFrom, String tglTo, String urutanData);
}
