package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.SalesforceRepository;
import myor.matrix.master.service.SalesforceService;

@Service
public class SalesforceServiceImpl implements SalesforceService {

	@Autowired
	SalesforceRepository repository;
	
	@Override
	public List<SearchBrowseDto> getBrowseSalesforce() {
		// TODO Auto-generated method stub
		return repository.getBrowseSalesforce();
	}

}
