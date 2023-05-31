package myor.matrix.master.entity;

import java.util.List;

public class EdiDto {
	private String ediId;
	private String ediNm;
	private String query;
	private List<EdiParamDto> params;
	private List<String> schema;

	public EdiDto() {
		// TODO Auto-generated constructor stub
	}

	public EdiDto(String ediId, String ediNm, String query, List<EdiParamDto> params, List<String> schema) {
		super();
		this.ediId = ediId;
		this.ediNm = ediNm;
		this.query = query;
		this.params = params;
		this.schema = schema;
	}

	public String getEdiId() {
		return ediId;
	}

	public void setEdiId(String ediId) {
		this.ediId = ediId;
	}

	public String getEdiNm() {
		return ediNm;
	}

	public void setEdiNm(String ediNm) {
		this.ediNm = ediNm;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<EdiParamDto> getParams() {
		return params;
	}

	public void setParams(List<EdiParamDto> params) {
		this.params = params;
	}

	public List<String> getSchema() {
		return schema;
	}

	public void setSchema(List<String> schema) {
		this.schema = schema;
	}
	
}
