package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.KompetitorRepository;
import myor.matrix.master.service.KompetitorService;

@Service
public class KompetitorServiceImpl implements KompetitorService {
	
	@Autowired
	private KompetitorRepository kompetitorRepository;
	
	@Override
	public List<SelectItem<String>> getBrowseKompetitor() {
		// TODO Auto-generated method stub
		return kompetitorRepository.getBrowseKompetitor();
	}

}
