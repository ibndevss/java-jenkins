package myor.matrix.master.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import myor.matrix.master.entity.ReportOutletRuteDto;
import net.sf.jasperreports.engine.JRException;

public interface OutletRuteService {
	public void insertTemp(String custFrom, String custTo, String teamFrom, String teamTo,
			String slsFrom, String slsTo, String pil, String hari, String urut, String userId);
	public List<ReportOutletRuteDto> getReport(String custFrom, String custTo, String teamFrom, String teamTo,
			String slsFrom, String slsTo, String pil, String hari, String urut, String userId);
	public String getReportOutletRute(String custFrom, String custTo, String teamFrom, String teamTo,
			String slsFrom, String slsTo, String pil, String hari, String urut, String userId, HttpServletResponse response) throws FileNotFoundException, JRException, IOException;
}
