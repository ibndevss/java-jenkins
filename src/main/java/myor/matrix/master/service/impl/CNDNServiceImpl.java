package myor.matrix.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.CNDNDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.CNDNRepository;
import myor.matrix.master.service.CNDNService;

@Service
public class CNDNServiceImpl implements CNDNService {

	@Autowired
	CNDNRepository repository;
	
	@Override
	public List<SelectItem<String>> getListCnDn() {
		// TODO Auto-generated method stub
		List<SelectItem<String>> data = new ArrayList();
		SelectItem<String> choose = new SelectItem<String>("Choose", null);
		data.add(choose);
		data.addAll(repository.getListCNDN());
				 
		return  data;
	}

	@Override
	public List<CNDNDto> browseCNDN() {
		// TODO Auto-generated method stub
		return repository.browseCNDN();
	}

}
