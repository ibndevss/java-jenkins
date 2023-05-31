package myor.matrix.master.entity;

public class HelperBrowseDto {
	private String helperCode;
	private String helperName;
	
	public HelperBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public HelperBrowseDto(String helperCode, String helperName) {
		super();
		this.helperCode = helperCode;
		this.helperName = helperName;
	}

	public String getHelperCode() {
		return helperCode;
	}

	public void setHelperCode(String helperCode) {
		this.helperCode = helperCode;
	}

	public String getHelperName() {
		return helperName;
	}

	public void setHelperName(String helperName) {
		this.helperName = helperName;
	}
	
	
}
