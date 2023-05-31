package myor.matrix.master.entity;

public class ViewKplDto {
	private String kdOutlet;
	private String nmOutlet;
	private String pcode;
	private String pcodeName;
	private int lastOrder;
	private int lastCall;
	private int avg4Call;
	private int ord1;
	private int ord2;
	private int ord3;
	private int ord4;
	private int lToko;
	private int jCall;
	private int jOrder;
	private int sls1;
	private int sls2;
	private int sls3;
	private int sls4;
	private String keterangan;
	
	public ViewKplDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewKplDto(String kdOutlet, String nmOutlet, String pcode, String pcodeName, int lastOrder, int lastCall,
			int avg4Call, int ord1, int ord2, int ord3, int ord4, int lToko, int jCall, int jOrder, int sls1, int sls2,
			int sls3, int sls4, String keterangan) {
		super();
		this.kdOutlet = kdOutlet;
		this.nmOutlet = nmOutlet;
		this.pcode = pcode;
		this.pcodeName = pcodeName;
		this.lastOrder = lastOrder;
		this.lastCall = lastCall;
		this.avg4Call = avg4Call;
		this.ord1 = ord1;
		this.ord2 = ord2;
		this.ord3 = ord3;
		this.ord4 = ord4;
		this.lToko = lToko;
		this.jCall = jCall;
		this.jOrder = jOrder;
		this.sls1 = sls1;
		this.sls2 = sls2;
		this.sls3 = sls3;
		this.sls4 = sls4;
		this.keterangan = keterangan;
	}

	public String getKdOutlet() {
		return kdOutlet;
	}

	public void setKdOutlet(String kdOutlet) {
		this.kdOutlet = kdOutlet;
	}

	public String getNmOutlet() {
		return nmOutlet;
	}

	public void setNmOutlet(String nmOutlet) {
		this.nmOutlet = nmOutlet;
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

	public int getLastOrder() {
		return lastOrder;
	}

	public void setLastOrder(int lastOrder) {
		this.lastOrder = lastOrder;
	}

	public int getLastCall() {
		return lastCall;
	}

	public void setLastCall(int lastCall) {
		this.lastCall = lastCall;
	}

	public int getAvg4Call() {
		return avg4Call;
	}

	public void setAvg4Call(int avg4Call) {
		this.avg4Call = avg4Call;
	}

	public int getOrd1() {
		return ord1;
	}

	public void setOrd1(int ord1) {
		this.ord1 = ord1;
	}

	public int getOrd2() {
		return ord2;
	}

	public void setOrd2(int ord2) {
		this.ord2 = ord2;
	}

	public int getOrd3() {
		return ord3;
	}

	public void setOrd3(int ord3) {
		this.ord3 = ord3;
	}

	public int getOrd4() {
		return ord4;
	}

	public void setOrd4(int ord4) {
		this.ord4 = ord4;
	}

	public int getlToko() {
		return lToko;
	}

	public void setlToko(int lToko) {
		this.lToko = lToko;
	}

	public int getjCall() {
		return jCall;
	}

	public void setjCall(int jCall) {
		this.jCall = jCall;
	}

	public int getjOrder() {
		return jOrder;
	}

	public void setjOrder(int jOrder) {
		this.jOrder = jOrder;
	}

	public int getSls1() {
		return sls1;
	}

	public void setSls1(int sls1) {
		this.sls1 = sls1;
	}

	public int getSls2() {
		return sls2;
	}

	public void setSls2(int sls2) {
		this.sls2 = sls2;
	}

	public int getSls3() {
		return sls3;
	}

	public void setSls3(int sls3) {
		this.sls3 = sls3;
	}

	public int getSls4() {
		return sls4;
	}

	public void setSls4(int sls4) {
		this.sls4 = sls4;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
		
}
