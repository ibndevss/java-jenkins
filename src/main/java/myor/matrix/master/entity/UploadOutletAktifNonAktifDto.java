package myor.matrix.master.entity;

import java.util.ArrayList;
import java.util.List;

public class UploadOutletAktifNonAktifDto {

	UploadOutletAktifNonAktifHeaderDto dataHeader;
	List<UploadOutletAktifNonAktifDetailDto> dataDetail;
	List<UploadOutletAktifNonAktifCoverDto> dataCover;
	List<String> logs = new ArrayList<>();
	public UploadOutletAktifNonAktifDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadOutletAktifNonAktifDto(UploadOutletAktifNonAktifHeaderDto dataHeader,
			List<UploadOutletAktifNonAktifDetailDto> dataDetail, List<UploadOutletAktifNonAktifCoverDto> dataCover) {
		super();
		this.dataHeader = dataHeader;
		this.dataDetail = dataDetail;
		this.dataCover = dataCover;
	}
	
	public UploadOutletAktifNonAktifDto(UploadOutletAktifNonAktifHeaderDto dataHeader,
			List<UploadOutletAktifNonAktifDetailDto> dataDetail, List<UploadOutletAktifNonAktifCoverDto> dataCover,
			List<String> logs) {
		super();
		this.dataHeader = dataHeader;
		this.dataDetail = dataDetail;
		this.dataCover = dataCover;
		this.logs = logs;
	}
	
	public UploadOutletAktifNonAktifHeaderDto getDataHeader() {
		return dataHeader;
	}
	public void setDataHeader(UploadOutletAktifNonAktifHeaderDto dataHeader) {
		this.dataHeader = dataHeader;
	}
	public List<UploadOutletAktifNonAktifDetailDto> getDataDetail() {
		return dataDetail;
	}
	public void setDataDetail(List<UploadOutletAktifNonAktifDetailDto> dataDetail) {
		this.dataDetail = dataDetail;
	}
	public List<UploadOutletAktifNonAktifCoverDto> getDataCover() {
		return dataCover;
	}
	public void setDataCover(List<UploadOutletAktifNonAktifCoverDto> dataCover) {
		this.dataCover = dataCover;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	
}
