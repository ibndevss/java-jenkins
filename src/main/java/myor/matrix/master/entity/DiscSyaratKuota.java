package myor.matrix.master.entity;

public class DiscSyaratKuota {
	private String distributorId;
	private String outlet;
	private String keterangan;
	private double nilai;
	public DiscSyaratKuota() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiscSyaratKuota(String distributorId, String outlet, String keterangan, double nilai) {
		super();
		this.distributorId = distributorId;
		this.outlet = outlet;
		this.keterangan = keterangan;
		this.nilai = nilai;
	}
	public String getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
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
	public double getNilai() {
		return nilai;
	}
	public void setNilai(double nilai) {
		this.nilai = nilai;
	}
	
}	
