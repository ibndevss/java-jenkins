package myor.matrix.master.entity;

public class ProductBrowserHargaSpesifikDto {
	private String pcode;
	private String pcodeName;
	private String kode;
	private String kodeName;
	private String startDate;
	private int toleransi;
	private String type;
	private Double buyprice1;
	private Double buyprice2;
	private Double buyprice3;
	private String endDate;
	private String groupName;
	private String sForceName;
	private String typeName;
	private String groupHargaName;
	private String ketTipe;
	private String custName;

	public ProductBrowserHargaSpesifikDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductBrowserHargaSpesifikDto(String pcode, String pcodeName, String kode, String kodeName,
			String startDate, int toleransi, String type, Double buyprice1, Double buyprice2, Double buyprice3,
			String endDate, String groupName, String sForceName, String typeName, String groupHargaName, String ketTipe,
			String custName) {
		super();
		this.pcode = pcode;
		this.pcodeName = pcodeName;
		this.kode = kode;
		this.kodeName = kodeName;
		this.startDate = startDate;
		this.toleransi = toleransi;
		this.type = type;
		this.buyprice1 = buyprice1;
		this.buyprice2 = buyprice2;
		this.buyprice3 = buyprice3;
		this.endDate = endDate;
		this.groupName = groupName;
		this.sForceName = sForceName;
		this.typeName = typeName;
		this.groupHargaName = groupHargaName;
		this.ketTipe = ketTipe;
		this.custName = custName;
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

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getKodeName() {
		return kodeName;
	}

	public void setKodeName(String kodeName) {
		this.kodeName = kodeName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getToleransi() {
		return toleransi;
	}

	public void setToleransi(int toleransi) {
		this.toleransi = toleransi;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBuyprice1() {
		return buyprice1;
	}

	public void setBuyprice1(Double buyprice1) {
		this.buyprice1 = buyprice1;
	}

	public Double getBuyprice2() {
		return buyprice2;
	}

	public void setBuyprice2(Double buyprice2) {
		this.buyprice2 = buyprice2;
	}

	public Double getBuyprice3() {
		return buyprice3;
	}

	public void setBuyprice3(Double buyprice3) {
		this.buyprice3 = buyprice3;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getsForceName() {
		return sForceName;
	}

	public void setsForceName(String sForceName) {
		this.sForceName = sForceName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getGroupHargaName() {
		return groupHargaName;
	}

	public void setGroupHargaName(String groupHargaName) {
		this.groupHargaName = groupHargaName;
	}

	public String getKetTipe() {
		return ketTipe;
	}

	public void setKetTipe(String ketTipe) {
		this.ketTipe = ketTipe;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
}
