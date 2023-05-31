package myor.matrix.master.entity;

public class DiscOutletDto {
	private String kodeAp;
	private String subdistId;
	private String subdistName;
	private String outlet;
	private String keterangan;
	public DiscOutletDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiscOutletDto(String kodeAp, String subdistId, String subdistName, String outlet, String keterangan) {
		super();
		this.kodeAp = kodeAp;
		this.subdistId = subdistId;
		this.subdistName = subdistName;
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
	public String getSubdistName() {
		return subdistName;
	}
	public void setSubdistName(String subdistName) {
		this.subdistName = subdistName;
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
