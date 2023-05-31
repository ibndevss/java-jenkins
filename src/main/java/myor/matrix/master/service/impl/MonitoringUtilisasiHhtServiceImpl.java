package myor.matrix.master.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Year;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.MonitoringUtilisasiHhtDetail;
import myor.matrix.master.entity.MonitoringUtilisasiHhtFristLoadDto;
import myor.matrix.master.entity.MonitoringUtilisasiHhtRekap;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.MonitoringUtilisasiHhtRepository;
import myor.matrix.master.service.MonitoringUtilisasiHhtService;
import myor.matrix.master.util.Util;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class MonitoringUtilisasiHhtServiceImpl implements MonitoringUtilisasiHhtService{

	@Autowired
	MonitoringUtilisasiHhtRepository monitoringRepo;
	
	@Autowired
	Util util;

	@Override
	public MonitoringUtilisasiHhtFristLoadDto getFirstLoadData() {
		// TODO Auto-generated method stub
		String tglGd = util.getTglGudang();
		MonitoringUtilisasiHhtFristLoadDto datas = new MonitoringUtilisasiHhtFristLoadDto();
		List<MonitoringUtilisasiHhtFristLoadDto> dataDate = new ArrayList<>();
		List<SelectItem<String>> dataSales = new ArrayList<>();
		List<SelectItem<String>> dataAlasan = new ArrayList<>();
		dataSales = monitoringRepo.getDataSalesman();
		dataAlasan = monitoringRepo.getDataAlasan();
		dataDate = monitoringRepo.getDates(tglGd);
		datas.setDataSales(dataSales);
		datas.setDataAlasan(dataAlasan);
		for (MonitoringUtilisasiHhtFristLoadDto x : dataDate) {
			datas.setPeriode(x.getPeriode());
			datas.setYear(x.getYear());
			datas.setWeekFrom(x.getWeekFrom());
			datas.setWeekTo(x.getWeekTo());
			datas.setDateFrom(x.getDateFrom());
			datas.setDateTo(x.getDateTo());
		}
		
		return datas;
	}

	@Transactional
	@Override
	public String getReport(String tglAwal, String tglAkhir, String salesman, String tglGd, String periode,
			String pekan, String plhData, String sls, String altis, String flag, String calll, String tpScan,
			String plhAllKpl, String plhRekapDetail, HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String rslt = "";
		BigDecimal prd = null;
		BigDecimal yr = null;
		
		String tglGdng = util.getTglGudang();
		List<MonitoringUtilisasiHhtFristLoadDto> dataDate = new ArrayList<>();
		dataDate = monitoringRepo.getDates(tglGdng);
		for (MonitoringUtilisasiHhtFristLoadDto x : dataDate) {
			prd = x.getPeriode();
			yr = x.getYear();
		}
		
		System.out.println(prd.toString());
		System.out.println(yr.toString());
		
		String cekFcycle2 = monitoringRepo.cekFcycle2(yr.toString(), prd.toString());
		if(cekFcycle2.equalsIgnoreCase("Aman")) {
		
			if (plhRekapDetail.equalsIgnoreCase("rekap")) {
				List<MonitoringUtilisasiHhtRekap> data = monitoringRepo.getDataRekap(tglAwal, tglAkhir, 
						salesman, tglGd, periode, pekan, plhData, sls, altis, plhAllKpl);
				if (data.size() == 0) {
					rslt = "NODATA";
				}else {
					Resource rsc = new ClassPathResource("");
					rsc = new ClassPathResource("MonitoringHhtRekap.jrxml");
					InputStream inStream = rsc.getInputStream();
					JasperReport jasper =  JasperCompileManager.compileReport(inStream);
					
					JRBeanCollectionDataSource ds =  new JRBeanCollectionDataSource(data);
					Map<String, Object> param = new HashMap<>();
					param.put(null, null);
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, param, ds);
					try {
						byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
						rslt =  Base64.getEncoder().encodeToString(pdf);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
				
			}else if (plhRekapDetail.equalsIgnoreCase("detail")) {
				List<MonitoringUtilisasiHhtDetail> data = monitoringRepo.getDataDetail(tglAwal, tglAkhir, salesman, tglGd,
						periode, pekan, plhData, sls, flag, calll, tpScan, plhAllKpl);
				if (data.size() == 0) {
					rslt = "NODATA";
				}else {
					Resource rsc = new ClassPathResource("");
					rsc = new ClassPathResource("MonitoringHhtDetail.jrxml");
					InputStream inStream = rsc.getInputStream();
					JasperReport jasper =  JasperCompileManager.compileReport(inStream);
					
					JRBeanCollectionDataSource ds =  new JRBeanCollectionDataSource(data);
					Map<String, Object> param = new HashMap<>();
					param.put(null, null);
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, param, ds);
					try {
						byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
						rslt =  Base64.getEncoder().encodeToString(pdf);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				
			}else {
				rslt = "failed";
			}	
			
		}else if (cekFcycle2.equalsIgnoreCase("Gagal")) {
			rslt = "NotCalendar";
		}else {
			rslt = "failed";
		}
		
		return rslt;
	}
	
	
}
