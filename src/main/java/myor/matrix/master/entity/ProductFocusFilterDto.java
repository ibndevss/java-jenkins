package myor.matrix.master.entity;

public class ProductFocusFilterDto {
	private String tahun;
	private String periode;
	private String pcode;
	private String salesforce;
	private String team;
	private String channel;
	private String tglAwal;
	private String tglAkhir;
	private String flagAuto;
	
	public ProductFocusFilterDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductFocusFilterDto(String tahun, String periode, String pcode, String salesforce, String team,
			String channel, String tglAwal, String tglAkhir, String flagAuto) {
		super();
		this.tahun = tahun;
		this.periode = periode;
		this.pcode = pcode;
		this.salesforce = salesforce;
		this.team = team;
		this.channel = channel;
		this.tglAwal = tglAwal;
		this.tglAkhir = tglAkhir;
		this.flagAuto = flagAuto;
	}

	public String getTahun() {
		return tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getSalesforce() {
		return salesforce;
	}

	public void setSalesforce(String salesforce) {
		this.salesforce = salesforce;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public String getFlagAuto() {
		return flagAuto;
	}

	public void setFlagAuto(String flagAuto) {
		this.flagAuto = flagAuto;
	}
}
