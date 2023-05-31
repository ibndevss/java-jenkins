package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiskonBertingkatDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscApHirDto;
import myor.matrix.master.entity.DiscViewDto;

public interface ExtraDiscNewService {
	DiscViewDto getExtraDiscNew(String ap);
	List<DiscApBrowseDto> getExtraDiscApBrowse();
	DiscApHirDto getApHir(String ap);
	List<DiskonBertingkatDto> getDiskonBertingkat(String ap);
	List<ApExcludeDto> getApExclude(String ap);
}
