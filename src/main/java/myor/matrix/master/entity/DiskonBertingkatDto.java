package myor.matrix.master.entity;

public class DiskonBertingkatDto {
	double noRange;
	String kode;
	double nilai;
	public DiskonBertingkatDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiskonBertingkatDto(double noRange, String kode, double nilai) {
		super();
		this.noRange = noRange;
		this.kode = kode;
		this.nilai = nilai;
	}
	public double getNoRange() {
		return noRange;
	}
	public void setNoRange(double noRange) {
		this.noRange = noRange;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public double getNilai() {
		return nilai;
	}
	public void setNilai(double nilai) {
		this.nilai = nilai;
	}
	
}
