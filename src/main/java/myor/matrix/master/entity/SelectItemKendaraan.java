package myor.matrix.master.entity;

import java.math.BigDecimal;

public class SelectItemKendaraan<T> {
	private T label;
	private BigDecimal value1;
	private BigDecimal value2;
	private BigDecimal value3;
	
	
	public SelectItemKendaraan() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SelectItemKendaraan(T label, BigDecimal value1, BigDecimal value2, BigDecimal value3) {
		super();
		this.label = label;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}


	public T getLabel() {
		return label;
	}


	public void setLabel(T label) {
		this.label = label;
	}


	public BigDecimal getValue() {
		return value1;
	}


	public void setValue(BigDecimal value) {
		this.value1 = value;
	}


	public BigDecimal getValue2() {
		return value2;
	}


	public void setValue2(BigDecimal value2) {
		this.value2 = value2;
	}


	public BigDecimal getValue1() {
		return value1;
	}


	public void setValue1(BigDecimal value1) {
		this.value1 = value1;
	}


	public BigDecimal getValue3() {
		return value3;
	}


	public void setValue3(BigDecimal value3) {
		this.value3 = value3;
	}
	
	


	

	
	
	
}
