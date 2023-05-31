package myor.matrix.master.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscApHirDto;
import myor.matrix.master.entity.DiscPromoDto;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.entity.DiskonBertingkatDto;
import myor.matrix.master.repository.PromotionDiscNewRepository;
import myor.matrix.master.service.PromotionDiscNewService;

@Service
public class PromotionDiscNewServiceImpl implements PromotionDiscNewService{
	
	@Autowired
	PromotionDiscNewRepository promotionDiscNewRepository;

	@Override
	public DiscViewDto getPromoDiscNew(String ap) {
		// TODO Auto-generated method stub
		return promotionDiscNewRepository.getPromoDiscNew(ap);
	}

	@Override
	public List<DiscApBrowseDto> getPromoDiscApBrowse() {
		// TODO Auto-generated method stub
		return promotionDiscNewRepository.getPromoDiscApBrowse();
	}

	@Override
	public DiscApHirDto getApHir(String ap) {
		// TODO Auto-generated method stub
		return promotionDiscNewRepository.getApHir(ap);
	}

	@Override
	public List<DiskonBertingkatDto> getDiskonBertingkat(String ap) {
		// TODO Auto-generated method stub
		return promotionDiscNewRepository.getDiskonBertingkat(ap);
	}

	@Override
	public List<ApExcludeDto> getApExclude(String ap) {
		// TODO Auto-generated method stub
		return promotionDiscNewRepository.getApExclude(ap);
	}

	@Override
	public List<DiscPromoDto> getDataPromo(String ap) {
		// TODO Auto-generated method stub
		return promotionDiscNewRepository.getDataPromo(ap);
	}

	@Transactional
	@Override
	public List<DiscPromoDto> updateDataPromo(List<DiscPromoDto> dataPromo) {
		// TODO Auto-generated method stub
		for (DiscPromoDto dp : dataPromo) {
			promotionDiscNewRepository.updateDataPromo(dp.getKodeAp(), dp.getSeqNo(), dp.getpCode());
		}
		return null;
	}
	
	
}
