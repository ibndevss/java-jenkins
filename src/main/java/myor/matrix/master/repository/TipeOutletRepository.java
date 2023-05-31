package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.TypeOutletBrowseDto;

public interface TipeOutletRepository {

	public List<SearchBrowseDto> getBrowse();
	
	public List<TypeOutletBrowseDto> browseTypeOutlet();
}
