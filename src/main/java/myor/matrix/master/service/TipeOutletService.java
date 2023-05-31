package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.TypeOutletBrowseDto;

public interface TipeOutletService {

	public List<SearchBrowseDto> getBrowse();
	
	public List<TypeOutletBrowseDto> browseTypeOutlet();
}
