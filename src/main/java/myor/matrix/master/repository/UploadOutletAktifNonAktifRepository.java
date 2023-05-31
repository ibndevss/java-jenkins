package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.UploadOutletAktifNonAktifCoverDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifDetailDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifHeaderDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifPrintDto;

public interface UploadOutletAktifNonAktifRepository {
	
	public List<UploadOutletAktifNonAktifHeaderDto> getBrowseDocument();

	public UploadOutletAktifNonAktifHeaderDto getHeader(String docNo);
	
	public List<UploadOutletAktifNonAktifDetailDto> getDetail(String docNo);
	
	public List<UploadOutletAktifNonAktifCoverDto> getCover(String docNo);
	
	public List<Object[]> valOutletOutstanding(String custNo);
	
	public List<Object[]> valOutletTransaksi(String custNo, String docDate, String periode, String tahun);
	
	public List<UploadOutletAktifNonAktifPrintDto> dataCetak(String docNo);
}
