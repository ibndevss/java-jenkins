package myor.matrix.master.entity;

public class ViewDriverDto {
	private String kode;
	private String name;
	
	public ViewDriverDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewDriverDto(String kode, String name) {
		super();
		this.kode = kode;
		this.name = name;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
