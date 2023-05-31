package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.MappingOtorisasiValueDTO;

public interface MappingOtorisasiValueRepository {
	
	public Boolean cekTableMapping();
	
	public void createTableMapping();
	
	public List<MappingOtorisasiValueDTO> getDataMapping();
	
	public void deleteMapping(String pejabatId, List<String> teams);
	
	public void insertDataMapping(Integer nilaiFrom, Integer nilaiTo, String team, String pejabatId, String pejabatName);
	
}
