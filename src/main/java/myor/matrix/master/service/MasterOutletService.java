package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.GroupPayerBrowseDto;
import myor.matrix.master.entity.MasterOutletBrowseDto;
import myor.matrix.master.entity.MasterOutletDataCoverDto;
import myor.matrix.master.entity.MasterOutletDto;
import myor.matrix.master.entity.MasterOutletFormShowDto;
import myor.matrix.master.entity.MasterOutletSaveDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SearchBrowseDto;

public interface MasterOutletService {

	public String[] getSelectDivisi(String groupDivisi);

	public MasterOutletFormShowDto getFormShow();

	public MasterOutletDto getMasterOutlet(String custNo, String group, String value);

	
	public List<MasterOutletBrowseDto> getBrowserMasterOutlet(String groupDivisi, String valTaskforce);
	
	public String[] getSuggestNoOutlet(String divisi);
	
	public String[] cekEditCustomer(String custNo);

	public String[] saveOutlet(MasterOutletSaveDto p, String groupDivisi, String valTaskforce);

	public void deleteCoverOutlet(String custNo, String slsNo, String tglGudang);
	
	public String[] cekEditCover(String custNo, String slsNo, String groupDivisi);
	
	public void updateCover(MasterOutletDataCoverDto p, String tglGudang);

	public String[] addCover(MasterOutletDataCoverDto p, String tglGudang);

	public List<SalesmanBrowseDto> getBrowseSalesman(String groupDivisi, String taskforce);

	public List<GroupPayerBrowseDto> getBrowseGroupPayer(String taskforce);

	public List<SearchBrowseDto> getBrowseChiller();
}
