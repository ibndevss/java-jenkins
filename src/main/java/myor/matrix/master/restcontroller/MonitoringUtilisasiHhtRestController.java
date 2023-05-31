package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.MonitoringUtilisasiHhtFristLoadDto;
import myor.matrix.master.service.MonitoringUtilisasiHhtService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path = "/monitoring-utilisasi-hht")
public class MonitoringUtilisasiHhtRestController {
	
	@Autowired
	MonitoringUtilisasiHhtService monitoringService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("10 APR 2023");
		return result;
	}
	
	@GetMapping(path = "/get-first-data")
	public MonitoringUtilisasiHhtFristLoadDto getFirstLoadData(){
		return monitoringService.getFirstLoadData();
	}
	
	@GetMapping(path = "/get-preview-data", params = {"tglAwal","tglAkhir","salesman","tglGd","periode","pekan","plhData",
			"sls","altis","flag","calll","tpScan","plhAllKpl","plhRekapDetail"})
	public String getReports(@RequestParam String tglAwal, String tglAkhir, String salesman, String tglGd, String periode,
			String pekan, String plhData, String sls, String altis, String flag, String calll, String tpScan,
			String plhAllKpl, String plhRekapDetail, HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
		return monitoringService.getReport(tglAwal, tglAkhir, salesman, tglGd, periode,
				pekan, plhData, sls, altis, flag, calll, tpScan, plhAllKpl, plhRekapDetail, response);
	}

}
