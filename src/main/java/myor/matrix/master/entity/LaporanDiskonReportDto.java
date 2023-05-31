package myor.matrix.master.entity;

import java.util.List;

public class LaporanDiskonReportDto {
	private String report;
	private List<MessageDto> message;	
	
	public LaporanDiskonReportDto() {
		// TODO Auto-generated constructor stub
	}
	
	public LaporanDiskonReportDto(String report, List<MessageDto> message) {
		super();
		this.report = report;
		this.message = message;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public List<MessageDto> getMessage() {
		return message;
	}

	public void setMessage(List<MessageDto> message) {
		this.message = message;
	}
	
	
}
