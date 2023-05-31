package myor.matrix.master.entity;

public class SalesmanBrowseDto {
	private String slsNo;
	private String slsName;
	private String oprType;
	private String team;
	private String slsForce;
	private String transDate;
	private String slsBlock;
	private String slsKg;
	public SalesmanBrowseDto() {
		// TODO Auto-generated constructor stub
	}
	public SalesmanBrowseDto(String slsNo, String slsName, String oprType, String team, String slsForce,
			String transDate, String slsBlock, String slsKg) {
		super();
		this.slsNo = slsNo;
		this.slsName = slsName;
		this.oprType = oprType;
		this.team = team;
		this.slsForce = slsForce;
		this.transDate = transDate;
		this.slsBlock = slsBlock;
		this.slsKg = slsKg;
	}
	public String getSlsNo() {
		return slsNo;
	}
	public void setSlsNo(String slsNo) {
		this.slsNo = slsNo;
	}
	public String getSlsName() {
		return slsName;
	}
	public void setSlsName(String slsName) {
		this.slsName = slsName;
	}
	public String getOprType() {
		return oprType;
	}
	public void setOprType(String oprType) {
		this.oprType = oprType;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getSlsForce() {
		return slsForce;
	}
	public void setSlsForce(String slsForce) {
		this.slsForce = slsForce;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getSlsBlock() {
		return slsBlock;
	}
	public void setSlsBlock(String slsBlock) {
		this.slsBlock = slsBlock;
	}
	public String getSlsKg() {
		return slsKg;
	}
	public void setSlsKg(String slsKg) {
		this.slsKg = slsKg;
	}

}
