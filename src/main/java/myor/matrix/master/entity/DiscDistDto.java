package myor.matrix.master.entity;

public class DiscDistDto {
	private String kodeAp;
	private String subdistId;
	private String subdistNm;
	private String outlet;
	private String keterangan;
	public DiscDistDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiscDistDto(String kodeAp, String subdistId, String subdistNm, String outlet, String keterangan) {
		super();
		this.kodeAp = kodeAp;
		this.subdistId = subdistId;
		this.subdistNm = subdistNm;
		this.outlet = outlet;
		this.keterangan = keterangan;
	}
	public String getKodeAp() {
		return kodeAp;
	}
	public void setKodeAp(String kodeAp) {
		this.kodeAp = kodeAp;
	}
	public String getSubdistId() {
		return subdistId;
	}
	public void setSubdistId(String subdistId) {
		this.subdistId = subdistId;
	}
	public String getSubdistNm() {
		return subdistNm;
	}
	public void setSubdistNm(String subdistNm) {
		this.subdistNm = subdistNm;
	}
	public String getOutlet() {
		return outlet;
	}
	public void setOutlet(String outlet) {
		this.outlet = outlet;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	
}
