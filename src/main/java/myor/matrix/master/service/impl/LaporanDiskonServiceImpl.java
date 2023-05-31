package myor.matrix.master.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.LaporanDiskonBrowseAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryByAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryPromoByAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryPromoDto;
import myor.matrix.master.entity.LaporanDiskonFilterDto;
import myor.matrix.master.entity.LaporanDiskonReportDetailDto;
import myor.matrix.master.entity.LaporanDiskonReportDetailPromoDto;
import myor.matrix.master.entity.LaporanDiskonReportDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryByAPDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryPromoByAPDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryPromoDto;
import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.repository.LaporanDiskonRepository;
import myor.matrix.master.service.LaporanDiskonService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class LaporanDiskonServiceImpl implements LaporanDiskonService {
	
	@Autowired
	private LaporanDiskonRepository repository;

	@Override
	public List<LaporanDiskonBrowseAPDto> getBrowseAP(LaporanDiskonFilterDto fs) {
		// TODO Auto-generated method stub
		String flagHierarki = fs.getFlagHierarki();
		String tipeDiskon = fs.getTipeDiskon();
		
		return repository.getBrowseAP(flagHierarki, tipeDiskon);
	}

	@Transactional
	@Override
	public LaporanDiskonReportDto getReport(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String report = "";		
		List<MessageDto> message = new ArrayList<>();
		
		LaporanDiskonReportDto result = new LaporanDiskonReportDto(report, message);
		
		if (fs.getTipeDiskon().equalsIgnoreCase("3")) {
			if (fs.getPilihanDiskon().equalsIgnoreCase("1")) { //SUMMARY				
				result = reportSummaryPromo(fs, response);				
			} else if (fs.getPilihanDiskon().equalsIgnoreCase("2")) { //SUMMARY BY AP
				result = reportSummaryPromoByAP(fs, response);
			} else if (fs.getPilihanDiskon().equalsIgnoreCase("3")) { //DETAIL
				result = reportDetailPromo(fs, response);
			}
		} else {
			if (fs.getPilihanDiskon().equalsIgnoreCase("1")) { //SUMMARY				
				result = reportSummary(fs, response);				
			} else if (fs.getPilihanDiskon().equalsIgnoreCase("2")) { //SUMMARY BY AP
				result = reportSummaryByAP(fs, response);
			} else if (fs.getPilihanDiskon().equalsIgnoreCase("3")) { //DETAIL
				result = reportDetail(fs, response);
			}
		}
		
		
		return result;
	}

	@Override
	public LaporanDiskonReportDto reportSummaryPromo(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String report = "";
		List<MessageDto> message = new ArrayList<>();
		
		LaporanDiskonReportDto result = new LaporanDiskonReportDto(report, message);
		
		String pilihanReport = fs.getPilihanReport();
		String tipeDiskon = fs.getTipeDiskon();
		String flagKlaim = fs.getFlagKlaim();
		String flagHierarki = fs.getFlagHierarki();
		String filterProduk = fs.getFilterProduk();
		String pcodeFrom = fs.getPcodeFrom();
		String pcodeTo = fs.getPcodeTo();
		String pcodeIn = fs.getPcodeIn();
		String custnoFrom = fs.getCustnoFrom();
		String custnoTo = fs.getCustnoTo();
		String kodeAPFrom = fs.getKodeAPFrom();
		String kodeAPTo = fs.getKodeAPTo();
		String invDateFrom = fs.getInvDateFrom();
		String invDateTo = fs.getInvDateTo();
		
		List<LaporanDiskonReportSummaryPromoDto> data = new ArrayList<>();
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			data = repository.getReportSummaryPromo(pilihanReport, tipeDiskon, flagKlaim, filterProduk, pcodeFrom,
					pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo);
		} else { //HIERARKI
			data = repository.getReportSummaryPromoHierarki(pilihanReport, tipeDiskon, flagKlaim, filterProduk,
					pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo);
		}
		
		if (data.size() > 0) {
			Resource resource = new ClassPathResource("LaporanDiskonSummaryPromo.jrxml");

			InputStream inStream = resource.getInputStream();

			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);

			try {
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				report = Base64.getEncoder().encodeToString(pdf);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			message.add(new MessageDto("success", "Success", "Proses Berhasil"));
		} else {
			message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
		}
		
		result = new LaporanDiskonReportDto(report, message);		
		
		return result;
	}

	@Override
	public LaporanDiskonReportDto reportSummaryPromoByAP(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String report = "";
		List<MessageDto> message = new ArrayList<>();
		
		LaporanDiskonReportDto result = new LaporanDiskonReportDto(report, message);
		
		String pilihanReport = fs.getPilihanReport();
		String tipeDiskon = fs.getTipeDiskon();
		String flagKlaim = fs.getFlagKlaim();
		String flagHierarki = fs.getFlagHierarki();
		String filterProduk = fs.getFilterProduk();
		String pcodeFrom = fs.getPcodeFrom();
		String pcodeTo = fs.getPcodeTo();
		String pcodeIn = fs.getPcodeIn();
		String custnoFrom = fs.getCustnoFrom();
		String custnoTo = fs.getCustnoTo();
		String kodeAPFrom = fs.getKodeAPFrom();
		String kodeAPTo = fs.getKodeAPTo();
		String invDateFrom = fs.getInvDateFrom();
		String invDateTo = fs.getInvDateTo();
		
		List<LaporanDiskonReportSummaryPromoByAPDto> data = new ArrayList<>();
		
		if (flagHierarki.equalsIgnoreCase("0")) { //NON HIERARKI
			data = repository.getReportSummaryPromoByAP(pilihanReport, tipeDiskon, flagKlaim, filterProduk, pcodeFrom,
					pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo);
		} else { //HIERARKI
			data = repository.getReportSummaryPromoByAPHierarki(pilihanReport, tipeDiskon, flagKlaim, filterProduk,
					pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo);
		}
		
		if (data.size() > 0) {
			Resource resource = new ClassPathResource("LaporanDiskonSummaryPromoByAP.jrxml");

			InputStream inStream = resource.getInputStream();

			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);

			try {
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				report = Base64.getEncoder().encodeToString(pdf);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			message.add(new MessageDto("success", "Success", "Proses Berhasil"));
		} else {
			message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
		}
		
		result = new LaporanDiskonReportDto(report, message);		
		
		return result;
	}

	@Transactional
	@Override
	public LaporanDiskonReportDto reportDetailPromo(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String report = "";
		List<MessageDto> message = new ArrayList<>();
		
		LaporanDiskonReportDto result = new LaporanDiskonReportDto(report, message);
		
		String pilihanReport = fs.getPilihanReport();
		String tipeDiskon = fs.getTipeDiskon();
		String flagKlaim = fs.getFlagKlaim();	
		String flagHierarki = fs.getFlagHierarki();
		String filterProduk = fs.getFilterProduk();
		String pcodeFrom = fs.getPcodeFrom();
		String pcodeTo = fs.getPcodeTo();
		String pcodeIn = fs.getPcodeIn();
		String custnoFrom = fs.getCustnoFrom();
		String custnoTo = fs.getCustnoTo();
		String kodeAPFrom = fs.getKodeAPFrom();
		String kodeAPTo = fs.getKodeAPTo();
		String invDateFrom = fs.getInvDateFrom();
		String invDateTo = fs.getInvDateTo();
		String userId = fs.getUserId();
		
		List<Object[]> list = repository.getListAPPromo(flagHierarki, tipeDiskon, flagKlaim, filterProduk, pcodeFrom, pcodeTo,
				pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo);
		
		if (list.size() > 0) {			
			
			String kodeAP = "";
			repository.deleteTemp("tmpmtx_lapdiskonpromo", userId);
			Integer urut = 0;
			
			for (Object[] ls : list) {
				kodeAP = Objects.toString(ls[0], "");
				
				List<Object[]> data = repository.getListAPInvnoPromo(flagHierarki, tipeDiskon, flagKlaim, filterProduk, pcodeFrom,
						pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAP, invDateFrom, invDateTo);
				
				String invno = "";
				String join = "";				
				
				for (Object[] dt : data) {
					invno = Objects.toString(dt[1], "");
					
					List<Object[]> detailKiri = repository.getListDetailPromoKiri(flagHierarki, tipeDiskon, flagKlaim, filterProduk,
							pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, invno, kodeAP);
					
					List<Object[]> detailKanan = repository.getListDetailPromoKanan(flagHierarki, tipeDiskon, flagKlaim, filterProduk,
							pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, invno, kodeAP);
					
					if (detailKiri.size() >= detailKanan.size()) {
						join = "LEFT JOIN";
					} else {
						join = "RIGHT JOIN";
					}
					
					//-----DETAIL DATA
					List<Object[]> detail = repository.getListDetailPromo(flagHierarki, tipeDiskon, flagKlaim, filterProduk, pcodeFrom,
							pcodeTo, pcodeIn, custnoFrom, custnoTo, invno, kodeAP, join);
					
					for (Object[] det : detail) {
						urut = urut + 1;
						String invDate = Objects.toString(det[2], "");
						String slsno = Objects.toString(det[3], "");
						String slsName = Objects.toString(det[4], "");
						String custno = Objects.toString(det[5], "");
						String custName = Objects.toString(det[6], "");					
						String pctpr = Objects.toString(det[7], "");
						String pctprName = Objects.toString(det[8], "");
						String besar = Objects.toString((BigDecimal) det[9], "");
						String tengah = Objects.toString((BigDecimal) det[10], "");
						String kecil = Objects.toString((BigDecimal) det[11], "");
						String tprAmount = Objects.toString((BigDecimal) det[12], "");
						String pcode = Objects.toString(det[13], "");
						String pcodeName = Objects.toString(det[14], "");
						String qty = Objects.toString((BigDecimal) det[15], "");
						String amount = Objects.toString((BigDecimal) det[16], "");
						
						repository.insertTempPromo("0", userId, urut, kodeAP, invno, invDate, slsno, slsName, custno, custName,
								pctpr, pctprName, besar, tengah, kecil, tprAmount, pcode, pcodeName, qty, amount);
					}
				}
				//-----DETAIL DATA
				
				//-----TOTAL PER AP
				List<Object[]> totalKiri = repository.getTotalPromoKiri(flagHierarki, tipeDiskon, flagKlaim, filterProduk,
						pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAP, invDateFrom, invDateTo);
				
				List<Object[]> totalKanan = repository.getTotalPromoKanan(flagHierarki, tipeDiskon, flagKlaim, filterProduk,
						pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAP, invDateFrom, invDateTo);
				
				if (totalKiri.size() >= totalKanan.size()) {
					join = "LEFT JOIN";
				} else {
					join = "RIGHT JOIN";
				}
				
				List<Object[]> total = repository.getTotalPromo(flagHierarki, tipeDiskon, flagKlaim, filterProduk, pcodeFrom,
						pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAP, invDateFrom, invDateTo, join);
				
				Integer i = 0;
				for (Object[] tot : total) {
					urut = urut + 1;
					
					String custName = "";
					if (i == 0) {
						custName = "Rekapitulasi " + kodeAP;
					}
					
					String pctprName = Objects.toString(tot[0], "");
					String besar = Objects.toString((BigDecimal) tot[1], "");
					String tengah = Objects.toString((BigDecimal) tot[2], "");
					String kecil = Objects.toString((BigDecimal) tot[3], "");
					String tprAmount = Objects.toString((BigDecimal) tot[4], "");					
					String pcodeName = Objects.toString(tot[5], "");
					String qty = Objects.toString((BigDecimal) tot[6], "");
					String amount = Objects.toString((BigDecimal) tot[7], "");
					
					repository.insertTempPromo("1", userId, urut, "", "", "", "", "", "", custName,
							"", pctprName, besar, tengah, kecil, tprAmount, "", pcodeName, qty, amount);
					
					i = i + 1; 
				}
				//-----TOTAL PER AP
				
			}
			
			List<LaporanDiskonReportDetailPromoDto> data = repository.getReportDetailPromo(pilihanReport, tipeDiskon,
					flagKlaim, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo, userId);
			
			
			if (data.size() > 0) {
				Resource resource = new ClassPathResource("LaporanDiskonDetailPromo.jrxml");

				InputStream inStream = resource.getInputStream();

				JasperReport jasper = JasperCompileManager.compileReport(inStream);

				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
				Map<String, Object> parameters = new HashMap<>();
				parameters.put(null, null);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);

				try {
					byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
					report = Base64.getEncoder().encodeToString(pdf);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}			
			repository.deleteTemp("tmpmtx_lapdiskonpromo", userId);
			
			message.add(new MessageDto("success", "Success", "Proses Berhasil"));
			
		} else {
			message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
		}	

		result = new LaporanDiskonReportDto(report, message);
		
		return result;
	}

	@Override
	public LaporanDiskonReportDto reportDetail(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String report = "";
		List<MessageDto> message = new ArrayList<>();
		
		LaporanDiskonReportDto result = new LaporanDiskonReportDto(report, message);
		
		String pilihanReport = fs.getPilihanReport();
		String tipeDiskon = fs.getTipeDiskon();
		String flagKlaim = fs.getFlagKlaim();
		String flagHierarki = fs.getFlagHierarki();
		String filterProduk = fs.getFilterProduk();
		String pcodeFrom = fs.getPcodeFrom();
		String pcodeTo = fs.getPcodeTo();
		String pcodeIn = fs.getPcodeIn();
		String custnoFrom = fs.getCustnoFrom();
		String custnoTo = fs.getCustnoTo();
		String kodeAPFrom = fs.getKodeAPFrom();
		String kodeAPTo = fs.getKodeAPTo();
		String invDateFrom = fs.getInvDateFrom();
		String invDateTo = fs.getInvDateTo();
		
		List<LaporanDiskonReportDetailDto> data = new ArrayList<>();
		
		data = repository.getReportDetail(flagHierarki, pilihanReport, tipeDiskon, flagKlaim, filterProduk, pcodeFrom, pcodeTo,
				pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo);
		
		if (data.size() > 0) {
			Resource resource = new ClassPathResource("LaporanDiskonDetail.jrxml");

			InputStream inStream = resource.getInputStream();

			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);

			try {
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				report = Base64.getEncoder().encodeToString(pdf);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			message.add(new MessageDto("success", "Success", "Proses Berhasil"));
		} else {
			message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
		}
		
		result = new LaporanDiskonReportDto(report, message);		
		
		return result;
	}

	@Override
	public LaporanDiskonReportDto reportSummary(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String report = "";
		List<MessageDto> message = new ArrayList<>();
		
		LaporanDiskonReportDto result = new LaporanDiskonReportDto(report, message);
		
		String pilihanReport = fs.getPilihanReport();
		String tipeDiskon = fs.getTipeDiskon();
		String flagKlaim = fs.getFlagKlaim();
		String flagHierarki = fs.getFlagHierarki();
		String filterProduk = fs.getFilterProduk();
		String pcodeFrom = fs.getPcodeFrom();
		String pcodeTo = fs.getPcodeTo();
		String pcodeIn = fs.getPcodeIn();
		String custnoFrom = fs.getCustnoFrom();
		String custnoTo = fs.getCustnoTo();
		String kodeAPFrom = fs.getKodeAPFrom();
		String kodeAPTo = fs.getKodeAPTo();
		String invDateFrom = fs.getInvDateFrom();
		String invDateTo = fs.getInvDateTo();
		
		List<LaporanDiskonReportSummaryDto> data = new ArrayList<>();
		
		data = repository.getReportSummary(flagHierarki, pilihanReport, tipeDiskon, flagKlaim, filterProduk, pcodeFrom,
				pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo);
		
		if (data.size() > 0) {
			Resource resource = new ClassPathResource("LaporanDiskonSummary.jrxml");

			InputStream inStream = resource.getInputStream();

			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);

			try {
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				report = Base64.getEncoder().encodeToString(pdf);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			message.add(new MessageDto("success", "Success", "Proses Berhasil"));
		} else {
			message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
		}
		
		result = new LaporanDiskonReportDto(report, message);		
		
		return result;
	}

	@Override
	public LaporanDiskonReportDto reportSummaryByAP(LaporanDiskonFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String report = "";
		List<MessageDto> message = new ArrayList<>();
		
		LaporanDiskonReportDto result = new LaporanDiskonReportDto(report, message);
		
		String pilihanReport = fs.getPilihanReport();
		String tipeDiskon = fs.getTipeDiskon();
		String flagKlaim = fs.getFlagKlaim();
		String flagHierarki = fs.getFlagHierarki();
		String filterProduk = fs.getFilterProduk();
		String pcodeFrom = fs.getPcodeFrom();
		String pcodeTo = fs.getPcodeTo();
		String pcodeIn = fs.getPcodeIn();
		String custnoFrom = fs.getCustnoFrom();
		String custnoTo = fs.getCustnoTo();
		String kodeAPFrom = fs.getKodeAPFrom();
		String kodeAPTo = fs.getKodeAPTo();
		String invDateFrom = fs.getInvDateFrom();
		String invDateTo = fs.getInvDateTo();
		
		List<LaporanDiskonReportSummaryByAPDto> data = new ArrayList<>();
		
		data = repository.getReportSummaryByAP(flagHierarki, pilihanReport, tipeDiskon, flagKlaim, filterProduk,
				pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo);
		
		if (data.size() > 0) {
			Resource resource = new ClassPathResource("LaporanDiskonSummaryByAP.jrxml");

			InputStream inStream = resource.getInputStream();

			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);

			try {
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				report = Base64.getEncoder().encodeToString(pdf);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			message.add(new MessageDto("success", "Success", "Proses Berhasil"));
		} else {
			message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
		}
		
		result = new LaporanDiskonReportDto(report, message);		
		
		return result;
	}

	@Override
	public LaporanDiskonExtractDto extractData(LaporanDiskonFilterDto fs) {
		// TODO Auto-generated method stub		
		List<LaporanDiskonExtractSummaryPromoDto> summaryPromo = new ArrayList<>();
		List<LaporanDiskonExtractSummaryPromoByAPDto> summaryPromoByAP = new ArrayList<>();
		List<LaporanDiskonExtractSummaryDto> summary = new ArrayList<>();
		List<LaporanDiskonExtractSummaryByAPDto> summaryByAP = new ArrayList<>();
		List<MessageDto> message = new ArrayList<>();
		
		LaporanDiskonExtractDto result = new LaporanDiskonExtractDto(summaryPromo, summaryPromoByAP, summary, summaryByAP, message);		
		
		String tipeDiskon = fs.getTipeDiskon();
		String flagKlaim = fs.getFlagKlaim();
		String flagHierarki = fs.getFlagHierarki();
		String filterProduk = fs.getFilterProduk();
		String pcodeFrom = fs.getPcodeFrom();
		String pcodeTo = fs.getPcodeTo();
		String pcodeIn = fs.getPcodeIn();
		String custnoFrom = fs.getCustnoFrom();
		String custnoTo = fs.getCustnoTo();
		String kodeAPFrom = fs.getKodeAPFrom();
		String kodeAPTo = fs.getKodeAPTo();
		String invDateFrom = fs.getInvDateFrom();
		String invDateTo = fs.getInvDateTo();
		
		if (fs.getTipeDiskon().equalsIgnoreCase("3")) {
			if (fs.getPilihanDiskon().equalsIgnoreCase("1")) { //SUMMARY				
				summaryPromo = repository.extractSummaryPromo(flagHierarki, tipeDiskon, flagKlaim, filterProduk,
						pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom,
						invDateTo);
				
				if (summaryPromo.size() > 0) {
					message.add(new MessageDto("success", "Success", "Proses Berhasil"));
				} else {				
					message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
				}
			} else if (fs.getPilihanDiskon().equalsIgnoreCase("2")) { //SUMMARY BY AP
				summaryPromoByAP = repository.extractSummaryPromoByAP(flagHierarki, tipeDiskon, flagKlaim, filterProduk,
						pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom,
						invDateTo);
				
				if (summaryPromoByAP.size() > 0) {
					message.add(new MessageDto("success", "Success", "Proses Berhasil"));
				} else {				
					message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
				}
			} else if (fs.getPilihanDiskon().equalsIgnoreCase("3")) { //DETAIL
				message.add(new MessageDto("warn", "Warning", "Extract belum tersedia !"));
			}
		} else {
			if (fs.getPilihanDiskon().equalsIgnoreCase("1")) { //SUMMARY				
				summary = repository.extractSummary(flagHierarki, tipeDiskon, flagKlaim, filterProduk, pcodeFrom,
						pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom, invDateTo);
				
				if (summary.size() > 0) {
					message.add(new MessageDto("success", "Success", "Proses Berhasil"));
				} else {				
					message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
				}
			} else if (fs.getPilihanDiskon().equalsIgnoreCase("2")) { //SUMMARY BY AP
				summaryByAP = repository.extractSummaryByAP(flagHierarki, tipeDiskon, flagKlaim, filterProduk,
						pcodeFrom, pcodeTo, pcodeIn, custnoFrom, custnoTo, kodeAPFrom, kodeAPTo, invDateFrom,
						invDateTo);
				
				if (summaryByAP.size() > 0) {
					message.add(new MessageDto("success", "Success", "Proses Berhasil"));
				} else {				
					message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
				}				
			} else if (fs.getPilihanDiskon().equalsIgnoreCase("3")) { //DETAIL
				message.add(new MessageDto("warn", "Warning", "Extract belum tersedia !"));
			}
		}
		
		result = new LaporanDiskonExtractDto(summaryPromo, summaryPromoByAP, summary, summaryByAP, message);
		
		return result;
	}


}
