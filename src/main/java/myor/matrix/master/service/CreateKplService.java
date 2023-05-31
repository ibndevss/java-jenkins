package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.CreateKplInputDto;
import myor.matrix.master.entity.CreateKplListOutletDto;
import myor.matrix.master.entity.MessageDto;

public interface CreateKplService {

	public List<CreateKplListOutletDto> getDaftarOutlet(CreateKplInputDto p);
	
	public String getRequestKey();
	
	public MessageDto processCreateKpl(CreateKplInputDto p);
}
