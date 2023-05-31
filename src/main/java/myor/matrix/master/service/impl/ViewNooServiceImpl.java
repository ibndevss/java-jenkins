package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.DocumentBrowseDto;
import myor.matrix.master.entity.ViewNooDto;
import myor.matrix.master.repository.ViewNooRepository;
import myor.matrix.master.service.ViewNooService;
@Service
public class ViewNooServiceImpl implements ViewNooService {

	@Autowired
	private ViewNooRepository nooRepo;
	
	@Override
	public List<DocumentBrowseDto> getListDocument(String status) {
		// TODO Auto-generated method stub
		return nooRepo.getListDocument(status);
	}

	@Override
	public ViewNooDto getDataViewNoo(String docNo,String status) {
		// TODO Auto-generated method stub
		
		String noDoc = docNo;
		
		if (noDoc.equalsIgnoreCase("null")) {
			noDoc = nooRepo.getMaxDocNo(status);
		}
		
		return nooRepo.getDataViewNoo(noDoc);
	}

}
