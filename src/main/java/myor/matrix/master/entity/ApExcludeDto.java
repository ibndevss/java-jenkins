package myor.matrix.master.entity;

public class ApExcludeDto {
	String kodeExclude;
//	String kodeAp;
	public ApExcludeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApExcludeDto(String kodeExclude) {
		super();
		this.kodeExclude = kodeExclude;
//		this.kodeAp = kodeAp;
	}
	public String getKodeExclude() {
		return kodeExclude;
	}
	public void setKodeExclude(String kodeExclude) {
		this.kodeExclude = kodeExclude;
	}
//	public String getKodeAp() {
//		return kodeAp;
//	}
//	public void setKodeAp(String kodeAp) {
//		this.kodeAp = kodeAp;
//	}
	
}
