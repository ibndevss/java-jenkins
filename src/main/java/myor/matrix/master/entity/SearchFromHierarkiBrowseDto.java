package myor.matrix.master.entity;

public class SearchFromHierarkiBrowseDto {

	private String kode;
	private String keterangan;
	private String h1;
	private String h2;
	private String h3;
	private String h4;
	private String h5;
	public SearchFromHierarkiBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchFromHierarkiBrowseDto(String kode, String keterangan, String h1, String h2, String h3, String h4,
			String h5) {
		super();
		this.kode = kode;
		this.keterangan = keterangan;
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
		this.h5 = h5;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public String getH1() {
		return h1;
	}
	public void setH1(String h1) {
		this.h1 = h1;
	}
	public String getH2() {
		return h2;
	}
	public void setH2(String h2) {
		this.h2 = h2;
	}
	public String getH3() {
		return h3;
	}
	public void setH3(String h3) {
		this.h3 = h3;
	}
	public String getH4() {
		return h4;
	}
	public void setH4(String h4) {
		this.h4 = h4;
	}
	public String getH5() {
		return h5;
	}
	public void setH5(String h5) {
		this.h5 = h5;
	}
}
