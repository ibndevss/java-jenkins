package myor.matrix.master.entity;

public class ApBrowseDto {

	private String apCode;
	private String keterangan;
	private String approveDate;
	private String tglAwal;
	private String tglAkhir;

	public ApBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public ApBrowseDto(String apCode, String keterangan, String approveDate, String tglAwal, String tglAkhir) {
		super();
		this.apCode = apCode;
		this.keterangan = keterangan;
		this.approveDate = approveDate;
		this.tglAwal = tglAwal;
		this.tglAkhir = tglAkhir;
	}

	public String getApCode() {
		return apCode;
	}

	public void setApCode(String apCode) {
		this.apCode = apCode;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}

	public String getTglAwal() {
		return tglAwal;
	}

	public void setTglAwal(String tglAwal) {
		this.tglAwal = tglAwal;
	}

	public String getTglAkhir() {
		return tglAkhir;
	}

	public void setTglAkhir(String tglAkhir) {
		this.tglAkhir = tglAkhir;
	}

}
