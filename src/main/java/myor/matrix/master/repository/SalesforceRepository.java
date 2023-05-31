package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SearchBrowseDto;

public interface SalesforceRepository {

	public List<SearchBrowseDto> getBrowseSalesforce();
}
