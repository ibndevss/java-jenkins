package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SearchBrowseDto;

public interface GroupOutletRepository {

	public List<SearchBrowseDto> getBrowse();
}
