package myor.matrix.master.entity;

public class DiscPromoDto {
	
	private String kodeAp;
	private double seqNo;
	private String pCode;
	private String keterangan;
	
	public DiscPromoDto(String kodeAp, double seqNo, String pCode, String keterangan) {
		super();
		this.kodeAp = kodeAp;
		this.seqNo = seqNo;
		this.pCode = pCode;
		this.keterangan = keterangan;
	}

	public String getKodeAp() {
		return kodeAp;
	}

	public void setKodeAp(String kodeAp) {
		this.kodeAp = kodeAp;
	}

	public double getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(double seqNo) {
		this.seqNo = seqNo;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	

}
