package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.UploadMasterOutletCoverDto;
import myor.matrix.master.entity.UploadMasterOutletOutletDto;

public interface UploadMasterOutletRepository {
	
	public List<Object[]> cekNoOutlet(String xkey, String custNo);
	
	public void insertFucstmst(UploadMasterOutletOutletDto p, String xbarcode, String Xflagsubdist);
	
	public void insertFcustsls(UploadMasterOutletCoverDto p);
	
}
