package myor.matrix.master.entity;

public class DiscApHirDto {
	private int id;
	private String kodeAp;
	private String tglAwal;
	private String berlakuHierarki;
	private int flagHierarki;
	private int seqno;
	public DiscApHirDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiscApHirDto(int id, String kodeAp, String tglAwal, String berlakuHierarki, int flagHierarki,
			int seqno) {
		super();
		this.id = id;
		this.kodeAp = kodeAp;
		this.tglAwal = tglAwal;
		this.berlakuHierarki = berlakuHierarki;
		this.flagHierarki = flagHierarki;
		this.seqno = seqno;
	}
	public double getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKodeAp() {
		return kodeAp;
	}
	public void setKodeAp(String kodeAp) {
		this.kodeAp = kodeAp;
	}
	public String getTglAwal() {
		return tglAwal;
	}
	public void setTglAwal(String tglAwal) {
		this.tglAwal = tglAwal;
	}
	public String getBerlakuHierarki() {
		return berlakuHierarki;
	}
	public void setBerlakuHierarki(String berlakuHierarki) {
		this.berlakuHierarki = berlakuHierarki;
	}
	public int getFlagHierarki() {
		return flagHierarki;
	}
	public void setFlagHierarki(int flagHierarki) {
		this.flagHierarki = flagHierarki;
	}
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	
	
}
