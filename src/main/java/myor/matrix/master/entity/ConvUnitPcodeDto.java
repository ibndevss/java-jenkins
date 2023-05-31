package myor.matrix.master.entity;

public class ConvUnitPcodeDto {
	private double convUnit3;
	private double convUnit2;
	
	public ConvUnitPcodeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ConvUnitPcodeDto(double convUnit3, double convUnit2) {
		super();
		this.convUnit3 = convUnit3;
		this.convUnit2 = convUnit2;
	}
	public double getConvUnit3() {
		return convUnit3;
	}
	public void setConvUnit3(double convUnit3) {
		this.convUnit3 = convUnit3;
	}
	public double getConvUnit2() {
		return convUnit2;
	}
	public void setConvUnit2(double convUnit2) {
		this.convUnit2 = convUnit2;
	}
	
}
