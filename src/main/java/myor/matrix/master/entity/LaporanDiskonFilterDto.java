package myor.matrix.master.entity;

public class LaporanDiskonFilterDto {
	private String pilihanReport;
	private String tipeDiskon;
	private String flagHierarki;
	private String flagKlaim;
	private String invDateFrom;
	private String invDateTo;
	private String custnoFrom;
	private String custnoTo;
	private String filterProduk;
	private String pcodeFrom;
	private String pcodeTo;
	private String pcodeIn;
	private String pilihanDiskon;
	private String kodeAPFrom;
	private String kodeAPTo;
	private String userId;
	
	public LaporanDiskonFilterDto() {
		// TODO Auto-generated constructor stub
	}

	public LaporanDiskonFilterDto(String pilihanReport, String tipeDiskon, String flagHierarki, String flagKlaim,
			String invDateFrom, String invDateTo, String custnoFrom, String custnoTo, String filterProduk,
			String pcodeFrom, String pcodeTo, String pcodeIn, String pilihanDiskon, String kodeAPFrom, String kodeAPTo,
			String userId) {
		super();
		this.pilihanReport = pilihanReport;
		this.tipeDiskon = tipeDiskon;
		this.flagHierarki = flagHierarki;
		this.flagKlaim = flagKlaim;
		this.invDateFrom = invDateFrom;
		this.invDateTo = invDateTo;
		this.custnoFrom = custnoFrom;
		this.custnoTo = custnoTo;
		this.filterProduk = filterProduk;
		this.pcodeFrom = pcodeFrom;
		this.pcodeTo = pcodeTo;
		this.pcodeIn = pcodeIn;
		this.pilihanDiskon = pilihanDiskon;
		this.kodeAPFrom = kodeAPFrom;
		this.kodeAPTo = kodeAPTo;
		this.userId = userId;
	}

	public String getPilihanReport() {
		return pilihanReport;
	}

	public void setPilihanReport(String pilihanReport) {
		this.pilihanReport = pilihanReport;
	}

	public String getTipeDiskon() {
		return tipeDiskon;
	}

	public void setTipeDiskon(String tipeDiskon) {
		this.tipeDiskon = tipeDiskon;
	}

	public String getFlagHierarki() {
		return flagHierarki;
	}

	public void setFlagHierarki(String flagHierarki) {
		this.flagHierarki = flagHierarki;
	}

	public String getFlagKlaim() {
		return flagKlaim;
	}

	public void setFlagKlaim(String flagKlaim) {
		this.flagKlaim = flagKlaim;
	}

	public String getInvDateFrom() {
		return invDateFrom;
	}

	public void setInvDateFrom(String invDateFrom) {
		this.invDateFrom = invDateFrom;
	}

	public String getInvDateTo() {
		return invDateTo;
	}

	public void setInvDateTo(String invDateTo) {
		this.invDateTo = invDateTo;
	}

	public String getCustnoFrom() {
		return custnoFrom;
	}

	public void setCustnoFrom(String custnoFrom) {
		this.custnoFrom = custnoFrom;
	}

	public String getCustnoTo() {
		return custnoTo;
	}

	public void setCustnoTo(String custnoTo) {
		this.custnoTo = custnoTo;
	}

	public String getFilterProduk() {
		return filterProduk;
	}

	public void setFilterProduk(String filterProduk) {
		this.filterProduk = filterProduk;
	}

	public String getPcodeFrom() {
		return pcodeFrom;
	}

	public void setPcodeFrom(String pcodeFrom) {
		this.pcodeFrom = pcodeFrom;
	}

	public String getPcodeTo() {
		return pcodeTo;
	}

	public void setPcodeTo(String pcodeTo) {
		this.pcodeTo = pcodeTo;
	}

	public String getPcodeIn() {
		return pcodeIn;
	}

	public void setPcodeIn(String pcodeIn) {
		this.pcodeIn = pcodeIn;
	}

	public String getPilihanDiskon() {
		return pilihanDiskon;
	}

	public void setPilihanDiskon(String pilihanDiskon) {
		this.pilihanDiskon = pilihanDiskon;
	}

	public String getKodeAPFrom() {
		return kodeAPFrom;
	}

	public void setKodeAPFrom(String kodeAPFrom) {
		this.kodeAPFrom = kodeAPFrom;
	}

	public String getKodeAPTo() {
		return kodeAPTo;
	}

	public void setKodeAPTo(String kodeAPTo) {
		this.kodeAPTo = kodeAPTo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
