package myor.matrix.master.entity;

public class CNDNDto {

	private String kode;
	private String keterangan;
	
	public CNDNDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CNDNDto(String kode, String keterangan) {
		super();
		this.kode = kode;
		this.keterangan = keterangan;
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
	
	
	
}
