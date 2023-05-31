package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.GudangRepository;
import myor.matrix.master.service.GudangService;

@Service
public class GudangServiceImpl implements GudangService {

	@Autowired
	private GudangRepository gudangRepository;
	
	@Override
	public List<SelectItem<String>> getListGudang() {
		// TODO Auto-generated method stub
		return gudangRepository.getListGudang();
	}

	@Override
	public List<SelectItem<String>> getListAllGudang() {
		// TODO Auto-generated method stub
		return gudangRepository.getListAllGudang();
	}

	@Override
	public List<SelectItem<String>> getListMutasiGudang(String kdGudang, String flagWhTransisi) {
		// TODO Auto-generated method stub	 				
		return gudangRepository.getListMutasiGudang(kdGudang, flagWhTransisi);
	}

}
