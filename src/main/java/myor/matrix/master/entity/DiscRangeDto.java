package myor.matrix.master.entity;

public class DiscRangeDto {
	private String kodeAp;
	private double noRange;
	private double minRange;
	private double maxRange;
	private double nilai;
	private String groupKel;
	public DiscRangeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiscRangeDto(String kodeAp, double noRange, double minRange, double maxRange, double nilai,
			String groupKel) {
		super();
		this.kodeAp = kodeAp;
		this.noRange = noRange;
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.nilai = nilai;
		this.groupKel = groupKel;
	}

	public String getKodeAp() {
		return kodeAp;
	}

	public void setKodeAp(String kodeAp) {
		this.kodeAp = kodeAp;
	}

	public double getNoRange() {
		return noRange;
	}

	public void setNoRange(double noRange) {
		this.noRange = noRange;
	}

	public double getMinRange() {
		return minRange;
	}

	public void setMinRange(double minRange) {
		this.minRange = minRange;
	}

	public double getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(double maxRange) {
		this.maxRange = maxRange;
	}

	public double getNilai() {
		return nilai;
	}

	public void setNilai(double nilai) {
		this.nilai = nilai;
	}

	public String getGroupKel() {
		return groupKel;
	}

	public void setGroupKel(String groupKel) {
		this.groupKel = groupKel;
	}
	
}
