package myor.matrix.master.entity;

public class ProductBrowseDto {

	private String pcode;
	private String pcodeName;
	private Double buyprice1;
	private Double buyprice2;
	private Double buyprice3;
	private String data09;
	private String data10;
	private Double convUnit3;
	private Double convUnit2;

	public ProductBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductBrowseDto(String pcode, String pcodeName, Double buyprice1, Double buyprice2, Double buyprice3,
			String data09, String data10, Double convUnit3, Double convUnit2) {
		super();
		this.pcode = pcode;
		this.pcodeName = pcodeName;
		this.buyprice1 = buyprice1;
		this.buyprice2 = buyprice2;
		this.buyprice3 = buyprice3;
		this.data09 = data09;
		this.data10 = data10;
		this.convUnit3 = convUnit3;
		this.convUnit2 = convUnit2;
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

	public Double getBuyprice1() {
		return buyprice1;
	}

	public void setBuyprice1(Double buyprice1) {
		this.buyprice1 = buyprice1;
	}

	public Double getBuyprice2() {
		return buyprice2;
	}

	public void setBuyprice2(Double buyprice2) {
		this.buyprice2 = buyprice2;
	}

	public Double getBuyprice3() {
		return buyprice3;
	}

	public void setBuyprice3(Double buyprice3) {
		this.buyprice3 = buyprice3;
	}

	public String getData09() {
		return data09;
	}

	public void setData09(String data09) {
		this.data09 = data09;
	}

	public String getData10() {
		return data10;
	}

	public void setData10(String data10) {
		this.data10 = data10;
	}

	public Double getConvUnit3() {
		return convUnit3;
	}

	public void setConvUnit3(Double convUnit3) {
		this.convUnit3 = convUnit3;
	}

	public Double getConvUnit2() {
		return convUnit2;
	}

	public void setConvUnit2(Double convUnit2) {
		this.convUnit2 = convUnit2;
	}

}
