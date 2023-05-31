package myor.matrix.master.entity;

import java.util.List;

public class UploadMasterOutletDto {

	List<UploadMasterOutletOutletDto> dataOutlet;
	List<UploadMasterOutletCoverDto> dataCover;
	List<UploadMasterOutletExcelOutletDto> dataOutletExcel;
	List<UploadMasterOutletExcelCoverDto> dataCoverExcel;
	List<String> logs;
	String message;
	String user;
	public UploadMasterOutletDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadMasterOutletDto(List<UploadMasterOutletOutletDto> dataOutlet,
			List<UploadMasterOutletCoverDto> dataCover, List<UploadMasterOutletExcelOutletDto> dataOutletExcel,
			List<UploadMasterOutletExcelCoverDto> dataCoverExcel, List<String> logs, String message, String user) {
		super();
		this.dataOutlet = dataOutlet;
		this.dataCover = dataCover;
		this.dataOutletExcel = dataOutletExcel;
		this.dataCoverExcel = dataCoverExcel;
		this.logs = logs;
		this.message = message;
		this.user = user;
	}
	public List<UploadMasterOutletOutletDto> getDataOutlet() {
		return dataOutlet;
	}
	public void setDataOutlet(List<UploadMasterOutletOutletDto> dataOutlet) {
		this.dataOutlet = dataOutlet;
	}
	public List<UploadMasterOutletCoverDto> getDataCover() {
		return dataCover;
	}
	public void setDataCover(List<UploadMasterOutletCoverDto> dataCover) {
		this.dataCover = dataCover;
	}
	public List<UploadMasterOutletExcelOutletDto> getDataOutletExcel() {
		return dataOutletExcel;
	}
	public void setDataOutletExcel(List<UploadMasterOutletExcelOutletDto> dataOutletExcel) {
		this.dataOutletExcel = dataOutletExcel;
	}
	public List<UploadMasterOutletExcelCoverDto> getDataCoverExcel() {
		return dataCoverExcel;
	}
	public void setDataCoverExcel(List<UploadMasterOutletExcelCoverDto> dataCoverExcel) {
		this.dataCoverExcel = dataCoverExcel;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
