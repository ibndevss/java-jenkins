package myor.matrix.master.entity;

import java.util.List;

public class MasterOutletDto {

	private List<MasterOutletDataProfileDto> dataProfile;
	private List<MasterOutletDataAttributeDto> dataAttribute;
	private List<MasterOutletDataPajakDto> dataPajak;
	private List<MasterOutletDataPemerintahanDto> dataPemerintahan;
	private List<MasterOutletDataCoverDto> dataCover;
	private List<MasterOutletDataDivisiDto> dataDivisi;
	public MasterOutletDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MasterOutletDto(List<MasterOutletDataProfileDto> dataProfile,
			List<MasterOutletDataAttributeDto> dataAttribute, List<MasterOutletDataPajakDto> dataPajak,
			List<MasterOutletDataPemerintahanDto> dataPemerintahan, List<MasterOutletDataCoverDto> dataCover,
			List<MasterOutletDataDivisiDto> dataDivisi) {
		super();
		this.dataProfile = dataProfile;
		this.dataAttribute = dataAttribute;
		this.dataPajak = dataPajak;
		this.dataPemerintahan = dataPemerintahan;
		this.dataCover = dataCover;
		this.dataDivisi = dataDivisi;
	}
	public List<MasterOutletDataProfileDto> getDataProfile() {
		return dataProfile;
	}
	public void setDataProfile(List<MasterOutletDataProfileDto> dataProfile) {
		this.dataProfile = dataProfile;
	}
	public List<MasterOutletDataAttributeDto> getDataAttribute() {
		return dataAttribute;
	}
	public void setDataAttribute(List<MasterOutletDataAttributeDto> dataAttribute) {
		this.dataAttribute = dataAttribute;
	}
	public List<MasterOutletDataPajakDto> getDataPajak() {
		return dataPajak;
	}
	public void setDataPajak(List<MasterOutletDataPajakDto> dataPajak) {
		this.dataPajak = dataPajak;
	}
	public List<MasterOutletDataPemerintahanDto> getDataPemerintahan() {
		return dataPemerintahan;
	}
	public void setDataPemerintahan(List<MasterOutletDataPemerintahanDto> dataPemerintahan) {
		this.dataPemerintahan = dataPemerintahan;
	}
	public List<MasterOutletDataCoverDto> getDataCover() {
		return dataCover;
	}
	public void setDataCover(List<MasterOutletDataCoverDto> dataCover) {
		this.dataCover = dataCover;
	}
	public List<MasterOutletDataDivisiDto> getDataDivisi() {
		return dataDivisi;
	}
	public void setDataDivisi(List<MasterOutletDataDivisiDto> dataDivisi) {
		this.dataDivisi = dataDivisi;
	}
}
