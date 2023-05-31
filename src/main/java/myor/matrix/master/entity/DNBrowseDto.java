package myor.matrix.master.entity;

import java.math.BigDecimal;

public class DNBrowseDto {

	private String noDN;
	private String noDocument;
	private String tglDocument;
	private BigDecimal total;
	private String remark;
	
	public DNBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DNBrowseDto(String noDN, String noDocument, String tglDocument, BigDecimal total, String remark) {
		super();
		this.noDN = noDN;
		this.noDocument = noDocument;
		this.tglDocument = tglDocument;
		this.total = total;
		this.remark = remark;
	}

	public String getNoDN() {
		return noDN;
	}

	public void setNoDN(String noDN) {
		this.noDN = noDN;
	}

	public String getNoDocument() {
		return noDocument;
	}

	public void setNoDocument(String noDocument) {
		this.noDocument = noDocument;
	}

	public String getTglDocument() {
		return tglDocument;
	}

	public void setTglDocument(String tglDocument) {
		this.tglDocument = tglDocument;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
