package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.KolektorRepository;
import myor.matrix.master.service.KolektorService;
@Service
public class KolektorServiceImpl implements KolektorService {

	@Autowired
	private KolektorRepository kolektorRepository;
	@Override
	public List<SelectItem<String>> getList() {
		// TODO Auto-generated method stub
		return kolektorRepository.getList();
	}

}
