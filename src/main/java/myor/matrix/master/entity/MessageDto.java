package myor.matrix.master.entity;

public class MessageDto {
	private String severity;
	private String summary;
	private String detail;
	
	public MessageDto() {
		// TODO Auto-generated constructor stub
	}

	public MessageDto(String severity, String summary, String detail) {
		super();
		this.severity = severity;
		this.summary = summary;
		this.detail = detail;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
