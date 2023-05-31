package myor.matrix.master.entity;

public class DistributorInfoDTO {

	private String distId;
	private String distName;
	private String branchId;
	private String branchName;
	private String city;
	private String today;
	private String whdate;
	private String week;
	private String period;
	private String year;
	private String versionMtxPro;

	public DistributorInfoDTO() {
		// TODO Auto-generated constructor stub
	}

	public DistributorInfoDTO(String distId, String distName, String branchId, String branchName, String city,
			String today, String whdate, String week, String period, String year, String versionMtxPro) {
		super();
		this.distId = distId;
		this.distName = distName;
		this.branchId = branchId;
		this.branchName = branchName;
		this.city = city;
		this.today = today;
		this.whdate = whdate;
		this.week = week;
		this.period = period;
		this.year = year;
		this.versionMtxPro = versionMtxPro;
	}

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getWhdate() {
		return whdate;
	}

	public void setWhdate(String whdate) {
		this.whdate = whdate;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getVersionMtxPro() {
		return versionMtxPro;
	}

	public void setVersionMtxPro(String versionMtxPro) {
		this.versionMtxPro = versionMtxPro;
	}

}
