package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscApHirDto;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.entity.DiskonBertingkatDto;

public interface RegularDiscNewRepository {
	
	DiscViewDto getRegularDiscNew(String ap);
	
	List<DiscApBrowseDto> getRegularDiscApBrowse();
	
	DiscApHirDto getApHir(String ap);
	
	List<DiskonBertingkatDto> getDiskonBertingkat(String ap);
	
	List<ApExcludeDto> getApExclude(String ap);

}
