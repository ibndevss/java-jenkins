package myor.matrix.master.entity;

public class MSalesmanKolektor {
	
	private String empno;
	private String empname;
	public MSalesmanKolektor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MSalesmanKolektor(String empno, String empname) {
		super();
		this.empno = empno;
		this.empname = empname;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	

}
