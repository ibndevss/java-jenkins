package myor.matrix.master.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.DistributorInfoDTO;
import myor.matrix.master.service.DistributorService;

@RestController
@RequestMapping(path = "/distributor")
public class DistributorRestController {
	
	@Autowired
	private DistributorService distributorService;
	
	@GetMapping(path = "/info")
	public DistributorInfoDTO getDistributorInfo() {
		return distributorService.getDistributorInfo();
	}
	
}
