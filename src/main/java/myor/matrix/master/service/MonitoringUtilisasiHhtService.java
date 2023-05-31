package myor.matrix.master.service;

import javax.servlet.http.HttpServletResponse;
import myor.matrix.master.entity.MonitoringUtilisasiHhtFristLoadDto;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MonitoringUtilisasiHhtService {
	
	public MonitoringUtilisasiHhtFristLoadDto getFirstLoadData();
	
	public String getReport(String tglAwal, String tglAkhir, String salesman, 
			String tglGd, String periode, String pekan, String plhData, String sls, 
			String altis, String flag, String calll, String tpScan, String plhAllKpl, String plhRekapDetail,
			HttpServletResponse response) throws FileNotFoundException, JRException, IOException;

}
