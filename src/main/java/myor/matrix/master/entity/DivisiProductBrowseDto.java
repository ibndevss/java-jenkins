package myor.matrix.master.entity;

public class DivisiProductBrowseDto {
	private String divisi;
	private String divisiName;
	
	public DivisiProductBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public DivisiProductBrowseDto(String divisi, String divisiName) {
		super();
		this.divisi = divisi;
		this.divisiName = divisiName;
	}

	public String getDivisi() {
		return divisi;
	}

	public void setDivisi(String divisi) {
		this.divisi = divisi;
	}

	public String getDivisiName() {
		return divisiName;
	}

	public void setDivisiName(String divisiName) {
		this.divisiName = divisiName;
	}
	
	
}
