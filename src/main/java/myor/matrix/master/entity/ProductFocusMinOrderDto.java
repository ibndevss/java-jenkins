package myor.matrix.master.entity;

public class ProductFocusMinOrderDto {
	private String pcode;
	private String pcodeName;
	private String tglAwal;
	private String tglAkhir;
	private Integer minOrder;
	
	public ProductFocusMinOrderDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductFocusMinOrderDto(String pcode, String pcodeName, String tglAwal, String tglAkhir, Integer minOrder) {
		super();
		this.pcode = pcode;
		this.pcodeName = pcodeName;
		this.tglAwal = tglAwal;
		this.tglAkhir = tglAkhir;
		this.minOrder = minOrder;
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

	public String getTglAwal() {
		return tglAwal;
	}

	public void setTglAwal(String tglAwal) {
		this.tglAwal = tglAwal;
	}

	public String getTglAkhir() {
		return tglAkhir;
	}

	public void setTglAkhir(String tglAkhir) {
		this.tglAkhir = tglAkhir;
	}

	public Integer getMinOrder() {
		return minOrder;
	}

	public void setMinOrder(Integer minOrder) {
		this.minOrder = minOrder;
	}

	
	
}
