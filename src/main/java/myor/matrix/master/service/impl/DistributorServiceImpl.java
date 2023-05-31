package myor.matrix.master.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.DistributorInfoDTO;
import myor.matrix.master.repository.DistributorRepository;
import myor.matrix.master.service.DistributorService;

@Service
public class DistributorServiceImpl implements DistributorService {
	
	@Autowired
	private DistributorRepository distributorRepository;
	
	@Override
	public DistributorInfoDTO getDistributorInfo() {
		// TODO Auto-generated method stub
		return distributorRepository.getDistInfo();
	}

}
