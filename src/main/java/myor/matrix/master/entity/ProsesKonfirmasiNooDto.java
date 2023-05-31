package myor.matrix.master.entity;

import java.math.BigDecimal;

public class ProsesKonfirmasiNooDto {

	private String custno;
	private String custname;
	private BigDecimal climit;
	private BigDecimal cterm;
	private String channel;
	public ProsesKonfirmasiNooDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProsesKonfirmasiNooDto(String custno, String custname, BigDecimal climit, BigDecimal cterm, String channel) {
		super();
		this.custno = custno;
		this.custname = custname;
		this.climit = climit;
		this.cterm = cterm;
		this.channel = channel;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public BigDecimal getClimit() {
		return climit;
	}
	public void setClimit(BigDecimal climit) {
		this.climit = climit;
	}
	public BigDecimal getCterm() {
		return cterm;
	}
	public void setCterm(BigDecimal cterm) {
		this.cterm = cterm;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
