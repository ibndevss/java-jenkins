package myor.matrix.master.entity;

public class EdiParamDto {
	private String var;
	private String label;
	private String placeholder;
	private int maxLength;

	public EdiParamDto() {
		// TODO Auto-generated constructor stub
	}

	public EdiParamDto(String var, String label, String placeholder, int maxLength) {
		super();
		this.var = var;
		this.label = label;
		this.placeholder = placeholder;
		this.maxLength = maxLength;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

}
