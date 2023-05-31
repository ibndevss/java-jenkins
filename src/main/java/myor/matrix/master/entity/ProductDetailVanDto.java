package myor.matrix.master.entity;

public class ProductDetailVanDto {
	private String pcode;
	private String pcodename;
	private String buyprice1;
	private String buyprice2;
	private String buyprice3;
	
	public ProductDetailVanDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductDetailVanDto(String pcode, String pcodename, String buyprice1, String buyprice2, String buyprice3) {
		super();
		this.pcode = pcode;
		this.pcodename = pcodename;
		this.buyprice1 = buyprice1;
		this.buyprice2 = buyprice2;
		this.buyprice3 = buyprice3;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPcodename() {
		return pcodename;
	}

	public void setPcodename(String pcodename) {
		this.pcodename = pcodename;
	}

	public String getBuyprice1() {
		return buyprice1;
	}

	public void setBuyprice1(String buyprice1) {
		this.buyprice1 = buyprice1;
	}

	public String getBuyprice2() {
		return buyprice2;
	}

	public void setBuyprice2(String buyprice2) {
		this.buyprice2 = buyprice2;
	}

	public String getBuyprice3() {
		return buyprice3;
	}

	public void setBuyprice3(String buyprice3) {
		this.buyprice3 = buyprice3;
	}
	
	
	
}
