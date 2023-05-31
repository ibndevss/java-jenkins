package myor.matrix.master.entity;

public class ProductFocusDetailStockDto {
	private String kodeSalesforce;
	private String salesforce;
	private String kodeTeam;
	private String team;
	private String kodeChannel;
	private String channel;
	private String pcode;
	private String pcodeName;
	private String tglMulai;
	private String tglAkhir;
	
	public ProductFocusDetailStockDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductFocusDetailStockDto(String kodeSalesforce, String salesforce, String kodeTeam, String team,
			String kodeChannel, String channel, String pcode, String pcodeName, String tglMulai, String tglAkhir) {
		super();
		this.kodeSalesforce = kodeSalesforce;
		this.salesforce = salesforce;
		this.kodeTeam = kodeTeam;
		this.team = team;
		this.kodeChannel = kodeChannel;
		this.channel = channel;
		this.pcode = pcode;
		this.pcodeName = pcodeName;
		this.tglMulai = tglMulai;
		this.tglAkhir = tglAkhir;
	}

	public String getKodeSalesforce() {
		return kodeSalesforce;
	}

	public void setKodeSalesforce(String kodeSalesforce) {
		this.kodeSalesforce = kodeSalesforce;
	}

	public String getSalesforce() {
		return salesforce;
	}

	public void setSalesforce(String salesforce) {
		this.salesforce = salesforce;
	}

	public String getKodeTeam() {
		return kodeTeam;
	}

	public void setKodeTeam(String kodeTeam) {
		this.kodeTeam = kodeTeam;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getKodeChannel() {
		return kodeChannel;
	}

	public void setKodeChannel(String kodeChannel) {
		this.kodeChannel = kodeChannel;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPcodeName() {
		return pcodeName;
	}

	public void setPcodeName(String pcodeName) {
		this.pcodeName = pcodeName;
	}

	public String getTglMulai() {
		return tglMulai;
	}

	public void setTglMulai(String tglMulai) {
		this.tglMulai = tglMulai;
	}

	public String getTglAkhir() {
		return tglAkhir;
	}

	public void setTglAkhir(String tglAkhir) {
		this.tglAkhir = tglAkhir;
	}
}
