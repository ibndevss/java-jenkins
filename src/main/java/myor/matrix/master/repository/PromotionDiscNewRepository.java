package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscApHirDto;
import myor.matrix.master.entity.DiscPromoDto;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.entity.DiskonBertingkatDto;

public interface PromotionDiscNewRepository {
	
	DiscViewDto getPromoDiscNew(String ap);
	
	List<DiscApBrowseDto> getPromoDiscApBrowse();
	
	DiscApHirDto getApHir(String ap);
	
	List<DiskonBertingkatDto> getDiskonBertingkat(String ap);
	
	List<ApExcludeDto> getApExclude(String ap);
	
	List<DiscPromoDto> getDataPromo(String ap);
	
	void updateDataPromo(String kdAp, double seqNo, String pc);

}
