package myor.matrix.master.entity;

public class DiscSyaratGProdDto {
	private String kodeap;
	private String kode;
	private String keterangan;
	private String flag;
	private String syaratQtyIn;
	private double nilai;
	public DiscSyaratGProdDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiscSyaratGProdDto(String kodeap, String kode, String keterangan, String flag, String syaratQtyIn,
			double nilai) {
		super();
		this.kodeap = kodeap;
		this.kode = kode;
		this.keterangan = keterangan;
		this.flag = flag;
		this.syaratQtyIn = syaratQtyIn;
		this.nilai = nilai;
	}
	public String getKodeap() {
		return kodeap;
	}
	public void setKodeap(String kodeap) {
		this.kodeap = kodeap;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getSyaratQtyIn() {
		return syaratQtyIn;
	}
	public void setSyaratQtyIn(String syaratQtyIn) {
		this.syaratQtyIn = syaratQtyIn;
	}
	public double getNilai() {
		return nilai;
	}
	public void setNilai(double nilai) {
		this.nilai = nilai;
	}
	
}
