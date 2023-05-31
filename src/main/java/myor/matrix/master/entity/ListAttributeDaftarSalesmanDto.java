package myor.matrix.master.entity;

public class ListAttributeDaftarSalesmanDto {
	private String nomer;
	private String attribute;
	private int checkList;
	private boolean checked;

	public ListAttributeDaftarSalesmanDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListAttributeDaftarSalesmanDto(String nomer, String attribute, int checkList, boolean checked) {
		super();
		this.nomer = nomer;
		this.attribute = attribute;
		this.checkList = checkList;
		this.checked = checked;
	}

	public String getNomer() {
		return nomer;
	}

	public void setNomer(String nomer) {
		this.nomer = nomer;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public int getCheckList() {
		return checkList;
	}

	public void setCheckList(int checkList) {
		this.checkList = checkList;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
