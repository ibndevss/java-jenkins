package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.MonitoringUtilisasiHhtDetail;
import myor.matrix.master.entity.MonitoringUtilisasiHhtFristLoadDto;
import myor.matrix.master.entity.MonitoringUtilisasiHhtRekap;
import myor.matrix.master.entity.SelectItem;

public interface MonitoringUtilisasiHhtRepository {

	public List<MonitoringUtilisasiHhtFristLoadDto> getDates(String tglGudang);
	
	public List<SelectItem<String>> getDataSalesman();
	
	public List<SelectItem<String>> getDataAlasan();
	
	public String cekFcycle2(String year, String period);
	
	public List<MonitoringUtilisasiHhtRekap> getDataRekap(String tglAwal, String tglAkhir, String salesman, 
			String tglGd, String periode, String pekan, String plhData, String sls, String altis, String plhAllKpl);
	
	public List<MonitoringUtilisasiHhtDetail> getDataDetail(String tglAwal, String tglAkhir, String salesman, String tglGd,
			String periode, String pekan, String plhData, String sls, String flag, String calll, String tpScan, String plhAllKpl);
	
}
