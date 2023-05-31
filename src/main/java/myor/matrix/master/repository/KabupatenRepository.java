package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SearchFromHierarkiBrowseDto;

public interface KabupatenRepository {

	public List<SearchFromHierarkiBrowseDto> getBrowse();
}
