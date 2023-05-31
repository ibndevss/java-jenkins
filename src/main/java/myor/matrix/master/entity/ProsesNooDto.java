package myor.matrix.master.entity;

import java.util.List;

public class ProsesNooDto {

	private String param = "";
	private Boolean paramDelete;
	List<ProsesNooDetailDto> dataDetail;
	public ProsesNooDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProsesNooDto(String param, Boolean paramDelete, List<ProsesNooDetailDto> dataDetail) {
		super();
		this.param = param;
		this.paramDelete = paramDelete;
		this.dataDetail = dataDetail;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public Boolean getParamDelete() {
		return paramDelete;
	}
	public void setParamDelete(Boolean paramDelete) {
		this.paramDelete = paramDelete;
	}
	public List<ProsesNooDetailDto> getDataDetail() {
		return dataDetail;
	}
	public void setDataDetail(List<ProsesNooDetailDto> dataDetail) {
		this.dataDetail = dataDetail;
	}
}
