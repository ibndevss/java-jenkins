package myor.matrix.master.entity;

public class SalesmanChoosenDto {

	private String kdSalesman;
	private String nmSalesman;
	private String salesTipe;
	private String salesTipeName;
	private String transDate;
	private String team;
	private String gudang;
	private String gudangName;
	private String typeOpr;
	private String keterangan;
	private String usernameUsulan;
	private String usernameOtomatis;
	private boolean slsIsTovas;

	public SalesmanChoosenDto() {
		// TODO Auto-generated constructor stub
	}

	public SalesmanChoosenDto(String kdSalesman, String nmSalesman, String transDate, String typeOpr, String keterangan,
			String usernameUsulan, String usernameOtomatis) {
		super();
		this.kdSalesman = kdSalesman;
		this.nmSalesman = nmSalesman;
		this.transDate = transDate;
		this.typeOpr = typeOpr;
		this.keterangan = keterangan;
		this.usernameUsulan = usernameUsulan;
		this.usernameOtomatis = usernameOtomatis;
	}

	public SalesmanChoosenDto(String kdSalesman, String nmSalesman, String salesTipe, String salesTipeName,
			String transDate, String team, String gudang, String gudangName) {
		super();
		this.kdSalesman = kdSalesman;
		this.nmSalesman = nmSalesman;
		this.salesTipe = salesTipe;
		this.salesTipeName = salesTipeName;
		this.transDate = transDate;
		this.team = team;
		this.gudang = gudang;
		this.gudangName = gudangName;
	}

	public SalesmanChoosenDto(String kdSalesman, String nmSalesman, String salesTipe, String salesTipeName,
			String transDate, String team, String gudang, String gudangName, boolean slsIsTovas) {
		super();
		this.kdSalesman = kdSalesman;
		this.nmSalesman = nmSalesman;
		this.salesTipe = salesTipe;
		this.salesTipeName = salesTipeName;
		this.transDate = transDate;
		this.team = team;
		this.gudang = gudang;
		this.gudangName = gudangName;
		this.slsIsTovas = slsIsTovas;
	}

	public String getKdSalesman() {
		return kdSalesman;
	}

	public void setKdSalesman(String kdSalesman) {
		this.kdSalesman = kdSalesman;
	}

	public String getNmSalesman() {
		return nmSalesman;
	}

	public void setNmSalesman(String nmSalesman) {
		this.nmSalesman = nmSalesman;
	}

	public String getSalesTipe() {
		return salesTipe;
	}

	public void setSalesTipe(String salesTipe) {
		this.salesTipe = salesTipe;
	}

	public String getSalesTipeName() {
		return salesTipeName;
	}

	public void setSalesTipeName(String salesTipeName) {
		this.salesTipeName = salesTipeName;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getGudang() {
		return gudang;
	}

	public void setGudang(String gudang) {
		this.gudang = gudang;
	}

	public String getGudangName() {
		return gudangName;
	}

	public void setGudangName(String gudangName) {
		this.gudangName = gudangName;
	}

	public String getTypeOpr() {
		return typeOpr;
	}

	public void setTypeOpr(String typeOpr) {
		this.typeOpr = typeOpr;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getUsernameUsulan() {
		return usernameUsulan;
	}

	public void setUsernameUsulan(String usernameUsulan) {
		this.usernameUsulan = usernameUsulan;
	}

	public String getUsernameOtomatis() {
		return usernameOtomatis;
	}

	public void setUsernameOtomatis(String usernameOtomatis) {
		this.usernameOtomatis = usernameOtomatis;
	}

	public boolean isSlsIsTovas() {
		return slsIsTovas;
	}

	public void setSlsIsTovas(boolean slsIsTovas) {
		this.slsIsTovas = slsIsTovas;
	}

}
