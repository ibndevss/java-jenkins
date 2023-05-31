package myor.matrix.master.entity;

import java.math.BigDecimal;

public class MSalesmanSalesforce<T> {
	
	private String label;
	private T value;
	private String data3;
	private BigDecimal data4;
	public MSalesmanSalesforce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MSalesmanSalesforce(String label, T value, String data3, BigDecimal data4) {
		super();
		this.label = label;
		this.value = value;
		this.data3 = data3;
		this.data4 = data4;
	}
	public String getlabel() {
		return label;
	}
	public void setlabel(String label) {
		this.label = label;
	}
	public T getvalue() {
		return value;
	}
	public void setvalue(T value) {
		this.value = value;
	}
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}
	public BigDecimal getData4() {
		return data4;
	}
	public void setData4(BigDecimal data4) {
		this.data4 = data4;
	}
	
}
