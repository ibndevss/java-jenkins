package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.MappingOtorisasiValueAdd;
import myor.matrix.master.entity.MappingOtorisasiValueDTO;

public interface MappingOtorisasiValueService {

	public List<MappingOtorisasiValueDTO> inisialisasiMapping();

	public List<MappingOtorisasiValueDTO> deleteMapping(String pejabatId, List<String> teams);

	public List<MappingOtorisasiValueDTO> insertMapping(Integer nilaiFrom, Integer nilaiTo, List<String> teams,
			String pejabatId, String pejabatName);

}
