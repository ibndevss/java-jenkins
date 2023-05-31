package myor.matrix.master.entity;

public class ProductVanBrowseDto {
	private String pcode;
	private String pcodeName;
	
	public ProductVanBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductVanBrowseDto(String pcode, String pcodeName) {
		super();
		this.pcode = pcode;
		this.pcodeName = pcodeName;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPcodeName() {
		return pcodeName;
	}

	public void setPcodeName(String pcodeName) {
		this.pcodeName = pcodeName;
	}
	
	
}


