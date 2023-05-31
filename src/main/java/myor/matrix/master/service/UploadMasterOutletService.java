package myor.matrix.master.service;

import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.UploadMasterOutletDto;

public interface UploadMasterOutletService {

	public UploadMasterOutletDto cekExcel(UploadMasterOutletDto p);
	
	public MessageDto process(UploadMasterOutletDto p);
}
