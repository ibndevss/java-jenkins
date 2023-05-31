package myor.matrix.master.entity;

public class SalesforceBrowseDto {
	
	private String value;
	private String label;
	private String flag;
	private String seq;
	public SalesforceBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalesforceBrowseDto(String value, String label, String flag, String seq) {
		super();
		this.value = value;
		this.label = label;
		this.flag = flag;
		this.seq = seq;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
}
