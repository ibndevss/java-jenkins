package myor.matrix.master.entity;

import java.util.List;

public class CreateKplInputDto {

	private String slsNo;
	private String tglProcess;
	private Boolean datePlusOne;
	private String keyRequest;
	private String keyProcess;
	private List<CreateKplListOutletDto> dataOutlet;
	private List<CreateKplListPcodeDto> dataPcode;
	private String xUser;
	public CreateKplInputDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateKplInputDto(String slsNo, String tglProcess, Boolean datePlusOne, String keyRequest, String keyProcess,
			List<CreateKplListOutletDto> dataOutlet, List<CreateKplListPcodeDto> dataPcode, String xUser) {
		super();
		this.slsNo = slsNo;
		this.tglProcess = tglProcess;
		this.datePlusOne = datePlusOne;
		this.keyRequest = keyRequest;
		this.keyProcess = keyProcess;
		this.dataOutlet = dataOutlet;
		this.dataPcode = dataPcode;
		this.xUser = xUser;
	}
	public String getSlsNo() {
		return slsNo;
	}
	public void setSlsNo(String slsNo) {
		this.slsNo = slsNo;
	}
	public String getTglProcess() {
		return tglProcess;
	}
	public void setTglProcess(String tglProcess) {
		this.tglProcess = tglProcess;
	}
	public Boolean getDatePlusOne() {
		return datePlusOne;
	}
	public void setDatePlusOne(Boolean datePlusOne) {
		this.datePlusOne = datePlusOne;
	}
	public String getKeyRequest() {
		return keyRequest;
	}
	public void setKeyRequest(String keyRequest) {
		this.keyRequest = keyRequest;
	}
	public String getKeyProcess() {
		return keyProcess;
	}
	public void setKeyProcess(String keyProcess) {
		this.keyProcess = keyProcess;
	}
	public List<CreateKplListOutletDto> getDataOutlet() {
		return dataOutlet;
	}
	public void setDataOutlet(List<CreateKplListOutletDto> dataOutlet) {
		this.dataOutlet = dataOutlet;
	}
	public List<CreateKplListPcodeDto> getDataPcode() {
		return dataPcode;
	}
	public void setDataPcode(List<CreateKplListPcodeDto> dataPcode) {
		this.dataPcode = dataPcode;
	}
	public String getxUser() {
		return xUser;
	}
	public void setxUser(String xUser) {
		this.xUser = xUser;
	}
}
