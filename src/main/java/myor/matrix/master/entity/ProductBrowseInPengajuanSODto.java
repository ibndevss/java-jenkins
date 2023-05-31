package myor.matrix.master.entity;

import java.math.BigDecimal;

public class ProductBrowseInPengajuanSODto {

	private String pcode;
	private String pcodeName;
	private BigDecimal buyPrice1;
	private BigDecimal buyPrice2;
	private BigDecimal buyPrice3;
	private BigDecimal data03;
	private BigDecimal data04;
	private BigDecimal data05;
	private BigDecimal data06;
	private BigDecimal conv3;
	private BigDecimal conv2;
	public ProductBrowseInPengajuanSODto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductBrowseInPengajuanSODto(String pcode, String pcodeName, BigDecimal buyPrice1, BigDecimal buyPrice2,
			BigDecimal buyPrice3, BigDecimal data03, BigDecimal data04, BigDecimal data05, BigDecimal data06,
			BigDecimal conv3, BigDecimal conv2) {
		super();
		this.pcode = pcode;
		this.pcodeName = pcodeName;
		this.buyPrice1 = buyPrice1;
		this.buyPrice2 = buyPrice2;
		this.buyPrice3 = buyPrice3;
		this.data03 = data03;
		this.data04 = data04;
		this.data05 = data05;
		this.data06 = data06;
		this.conv3 = conv3;
		this.conv2 = conv2;
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
	public BigDecimal getBuyPrice1() {
		return buyPrice1;
	}
	public void setBuyPrice1(BigDecimal buyPrice1) {
		this.buyPrice1 = buyPrice1;
	}
	public BigDecimal getBuyPrice2() {
		return buyPrice2;
	}
	public void setBuyPrice2(BigDecimal buyPrice2) {
		this.buyPrice2 = buyPrice2;
	}
	public BigDecimal getBuyPrice3() {
		return buyPrice3;
	}
	public void setBuyPrice3(BigDecimal buyPrice3) {
		this.buyPrice3 = buyPrice3;
	}
	public BigDecimal getData03() {
		return data03;
	}
	public void setData03(BigDecimal data03) {
		this.data03 = data03;
	}
	public BigDecimal getData04() {
		return data04;
	}
	public void setData04(BigDecimal data04) {
		this.data04 = data04;
	}
	public BigDecimal getData05() {
		return data05;
	}
	public void setData05(BigDecimal data05) {
		this.data05 = data05;
	}
	public BigDecimal getData06() {
		return data06;
	}
	public void setData06(BigDecimal data06) {
		this.data06 = data06;
	}
	public BigDecimal getConv3() {
		return conv3;
	}
	public void setConv3(BigDecimal conv3) {
		this.conv3 = conv3;
	}
	public BigDecimal getConv2() {
		return conv2;
	}
	public void setConv2(BigDecimal conv2) {
		this.conv2 = conv2;
	}
}
