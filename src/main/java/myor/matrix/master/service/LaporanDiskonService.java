package myor.matrix.master.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import myor.matrix.master.entity.LaporanDiskonBrowseAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractDto;
import myor.matrix.master.entity.LaporanDiskonFilterDto;
import myor.matrix.master.entity.LaporanDiskonReportDto;
import net.sf.jasperreports.engine.JRException;

public interface LaporanDiskonService {
	
	public List<LaporanDiskonBrowseAPDto> getBrowseAP(LaporanDiskonFilterDto fs);
	
	public LaporanDiskonReportDto getReport(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException;
	
	public LaporanDiskonReportDto reportSummaryPromo(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException;
	
	public LaporanDiskonReportDto reportSummaryPromoByAP(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException;
	
	public LaporanDiskonReportDto reportDetailPromo(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException;
	
	public LaporanDiskonReportDto reportDetail(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException;
	
	public LaporanDiskonReportDto reportSummary(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException;
	
	public LaporanDiskonReportDto reportSummaryByAP(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException;
	
	public LaporanDiskonExtractDto extractData(LaporanDiskonFilterDto fs);
}
