package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.DiskonRepository;
import myor.matrix.master.service.DiskonService;

@Service
public class DiskonServiceImpl implements DiskonService {
	
	@Autowired
	private DiskonRepository diskonRepository;
	
	@Override
	public List<SelectItem<String>> getSelectItemMasterTactical() {
		// TODO Auto-generated method stub
		return diskonRepository.getSelectItemMasterTactical();
	}

	@Override
	public List<SelectItem<String>> getDataMasterCod() {
		// TODO Auto-generated method stub
		return diskonRepository.getSelectItemMasterCod();
	}

}
