package myor.matrix.master.entity;

import java.math.BigDecimal;
import java.util.List;

public class MonitoringUtilisasiHhtFristLoadDto {

	private List<SelectItem<String>> dataSales;
	private BigDecimal periode;
	private BigDecimal year;
	private BigDecimal weekFrom;
	private BigDecimal weekTo;
	private String dateFrom;
	private String dateTo;
	private List<SelectItem<String>> dataAlasan;
	
	public MonitoringUtilisasiHhtFristLoadDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MonitoringUtilisasiHhtFristLoadDto(BigDecimal periode, BigDecimal year, BigDecimal weekFrom, BigDecimal weekTo,
			String dateFrom, String dateTo) {
		super();
		this.periode = periode;
		this.year = year;
		this.weekFrom = weekFrom;
		this.weekTo = weekTo;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public List<SelectItem<String>> getDataSales() {
		return dataSales;
	}

	public void setDataSales(List<SelectItem<String>> dataSales) {
		this.dataSales = dataSales;
	}

	public BigDecimal getPeriode() {
		return periode;
	}

	public void setPeriode(BigDecimal periode) {
		this.periode = periode;
	}

	public BigDecimal getYear() {
		return year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	public BigDecimal getWeekFrom() {
		return weekFrom;
	}

	public void setWeekFrom(BigDecimal weekFrom) {
		this.weekFrom = weekFrom;
	}

	public BigDecimal getWeekTo() {
		return weekTo;
	}

	public void setWeekTo(BigDecimal weekTo) {
		this.weekTo = weekTo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public List<SelectItem<String>> getDataAlasan() {
		return dataAlasan;
	}

	public void setDataAlasan(List<SelectItem<String>> dataAlasan) {
		this.dataAlasan = dataAlasan;
	}
	
	
	
}
