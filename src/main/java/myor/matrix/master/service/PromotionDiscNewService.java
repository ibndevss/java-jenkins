package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscApHirDto;
import myor.matrix.master.entity.DiscPromoDto;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.entity.DiskonBertingkatDto;

public interface PromotionDiscNewService {
	
	DiscViewDto getPromoDiscNew(String ap);
	
	List<DiscApBrowseDto> getPromoDiscApBrowse();
	
	DiscApHirDto getApHir(String ap);
	
	List<DiskonBertingkatDto> getDiskonBertingkat(String ap);
	
	List<ApExcludeDto> getApExclude(String ap);
	
	List<DiscPromoDto> getDataPromo(String ap);
	
	List<DiscPromoDto> updateDataPromo(List<DiscPromoDto> dataPromo);

}
