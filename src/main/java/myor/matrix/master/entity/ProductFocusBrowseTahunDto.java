package myor.matrix.master.entity;

public class ProductFocusBrowseTahunDto {
	private String tahun;
	private String periode;
	
	public ProductFocusBrowseTahunDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductFocusBrowseTahunDto(String tahun, String periode) {
		super();
		this.tahun = tahun;
		this.periode = periode;
	}

	public String getTahun() {
		return tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}
	
	
}
