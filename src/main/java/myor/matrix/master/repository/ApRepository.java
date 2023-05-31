package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ApBrowseDto;

public interface ApRepository {
	
	public List<ApBrowseDto> getBrowseApByDate(String tanggal);
	
}
