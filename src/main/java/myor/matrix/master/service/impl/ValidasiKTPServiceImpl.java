package myor.matrix.master.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ReportValidasiKTPDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.ValidasiKTPDto;
import myor.matrix.master.entity.ValidasiKTPOutletApproveDto;
import myor.matrix.master.repository.ValidasiKTPRepository;
import myor.matrix.master.service.ImageStorageService;
import myor.matrix.master.service.ValidasiKTPService;
import myor.matrix.master.util.Util;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ValidasiKTPServiceImpl implements ValidasiKTPService {
	@Autowired
	ValidasiKTPRepository validasiKTPRepository;
	
	@Autowired
	ImageStorageService imageStorageService;
	
	@Autowired
	Util util;

	@Override
	public List<String> saveValidasiKTP(String pemilik, String telpon, String ktp, String ktpName, String alamat1,
			String alamat2, String npwp, String npwpName, String custno) {
		List<String> result = new ArrayList<>();
		if(validasiKTPRepository.cekNpwp() == true) {
			if(ktp.equalsIgnoreCase("")) {
				result.add("No KTP harus diisi");
				return result;
			}
			npwp = "";
		}else {
			String flagPajak = "";
			String npwpVal = "";
			if(npwp.equalsIgnoreCase("")) {
				if(ktp.equalsIgnoreCase("")) {
					result.add("NPWP atau No KTP harus diisi");
					return result;
				}
				npwp = "00.000.000.0-000.000";
			}else {
				npwpVal = npwp;
				if((npwpVal.contains("1"))||(npwpVal.contains("2"))||(npwpVal.contains("3"))||(npwpVal.contains("4"))||(npwpVal.contains("5"))
						||(npwpVal.contains("6"))||(npwpVal.contains("7"))||(npwpVal.contains("8"))||(npwpVal.contains("9"))) {
					flagPajak = "1";
					if(npwp.length() < 15) {
						result.add("NPWP kurang dari 15 digit");
						return result;
					}else if (npwp.length() == 15){
						npwpVal = npwp.substring(0,2)+ "." + npwp.substring(2, 5)+"." + npwp.substring(5,8)+"."+ npwp.substring(8,9)+"-"+npwp.substring(9,12)+"."+npwp.substring(12,15);
						npwp = npwpVal;
					}
				}else {
					flagPajak="0";
				}
				
				if(flagPajak.equalsIgnoreCase("0")) {
					if(ktp.equalsIgnoreCase("")) {
						result.add("NPWP tidak boleh 0 atau KTP harus diisi");
						return result;
					}
					npwp = "00.000.000.0-000.000";
				}
			}
		}
		
		if(!ktp.equalsIgnoreCase("")) {
			if(ktp.length()<16) {
				result.add("No KTP kurang dari 16 digit");
				return result;
			}
		}
		
		validasiKTPRepository.updateValidasiKTP(pemilik, telpon, ktp, ktpName, alamat1, alamat2, npwp, npwpName, custno);
		result.add("Success Save Data");
		return result;
	}

	@Override
	public List<SelectItem<String>> getCustnoByFilter(String statusValidasi, String slsOpt, List<String> salesmans,
			String slsFrom, String slsTo, String custFrom, String custTo, String valDateFrom, String valDateTo,
			String kplDateFrom, String kplDateTo) {
		// TODO Auto-generated method stub
		return validasiKTPRepository.getCustnoByFilter(statusValidasi, slsOpt, salesmans, slsFrom, slsTo, custFrom, custTo, valDateFrom, valDateTo, kplDateFrom, kplDateTo);
	}

	@Override
	public int getCountCustnoByFilter(String statusValidasi, String slsOpt, List<String> salesmans, String slsFrom,
			String slsTo, String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom,
			String kplDateTo) {
		// TODO Auto-generated method stub
		return validasiKTPRepository.getCountCustnoByFilter(statusValidasi, slsOpt, salesmans, slsFrom, slsTo, custFrom, custTo, valDateFrom, valDateTo, kplDateFrom, kplDateTo);
	}

	@Override
	public boolean cekPathPhotoKTP() {
		// TODO Auto-generated method stub
		return validasiKTPRepository.cekPathPhotoKTP();
	}

	@Override
	public List<ValidasiKTPDto> getValidasiKTP(String custno) {
		List<ValidasiKTPDto> result = validasiKTPRepository.getValidasiKTP(custno);
		for(ValidasiKTPDto data: result) {
			data.setDetailOutlet(validasiKTPRepository.getDetilGroupPayer(custno));
		}
		return result;
	}

	@Override
	public Resource loadImageKTP(String custno) {
		List<ValidasiKTPDto> result = validasiKTPRepository.getValidasiKTP(custno);
		String path = result.get(0).getPath();
		String fileNameKtp = result.get(0).getPhotoKtp();
		return imageStorageService.loadImage(path, fileNameKtp);
	}

	@Override
	public Resource loadImageNPWP(String custno) {
		List<ValidasiKTPDto> result = validasiKTPRepository.getValidasiKTP(custno);
		String path = result.get(0).getPath();
		String fileNameNpwp = result.get(0).getPhotoNpwp();
		return imageStorageService.loadImage(path, fileNameNpwp);
	}

	@Override
	public boolean cekIsExistOutletApproveByGroupPayer(String custno) {
		// TODO Auto-generated method stub
		return validasiKTPRepository.cekIsExistOutletApproveByGroupPayer(custno);
	}

	@Override
	public void rejectValidasiKTP(String userLogin, String custno) {
		validasiKTPRepository.rejectValidasiKTP(userLogin, custno);
	}

	@Override
	public boolean cekIsExistPassword() {
		// TODO Auto-generated method stub
		return validasiKTPRepository.cekIsExistPassword();
	}

	@Override
	public boolean cekIsPassword(String pass) {
		// TODO Auto-generated method stub
		return validasiKTPRepository.cekIsPassword(pass);
	}

	@Override
	public void revalidate(String userLogin, String custno) {
		validasiKTPRepository.revalidate(userLogin, custno);
	}

	@Override
	public List<String> infoApproveMoreThanOneOutletInGroupPayer(List<String> outlet) {
		// TODO Auto-generated method stub
		return validasiKTPRepository.infoApproveMoreThanOneOutletInGroupPayer(outlet);
	}

	@Override
	public List<String> approve(String user, List<String> custno) {
		List<String> result = new ArrayList<>();
//		double docno = Math.round(validasiKTPRepository.getDocno());
		String docnoStr = validasiKTPRepository.getDocno();
//		double docno = Math.round(Double.valueOf(docnoStr));
		if(docnoStr.equalsIgnoreCase("0")) {
			result.add("Master nomor upload perubahan attribute belum di set");
			return result;
		}else if(Integer.parseInt(docnoStr)>= 99999999) {
			result.add("Master nomor upload perubahan atribut sudah melebihi range");
			return result;
		}
//		else {
//			docno = docno+1;
//		}
		double toleransiAtt = validasiKTPRepository.getToleransiAtt();
		if(toleransiAtt == 0) {
			result.add("Toleransi Attribute 3 Belum Ada, Lakukan Inisialisasi");
			return result;
		}
		if(validasiKTPRepository.cekIsExistDocno(docnoStr)==true) {
			result.add("Nomor dokumen sudah ada");
			return result;
		}
		validasiKTPRepository.approveHeader(docnoStr, util.getTglGudang(), util.getJamSystem(), util.getTglSystem(), user);
		
		for(String c:custno) {
			ValidasiKTPOutletApproveDto dataDetail = validasiKTPRepository.getDetailOutletApprove(c);
			validasiKTPRepository.approveDetail(dataDetail, docnoStr, user, util.getTglGudang(), util.getJamSystem(), util.getTglSystem());
		}
		result.add("Success Approve");
		return result;
	}

	@Override
	public List<SelectItem<String>> getSelectItemApprove(String slsOpt, List<String> salesmans, String slsFrom,
			String slsTo, String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom,
			String kplDateTo) {
		// TODO Auto-generated method stub
		return validasiKTPRepository.getSelectItemApprove(slsOpt, salesmans, slsFrom, slsTo, custFrom, custTo, valDateFrom, valDateTo, kplDateFrom, kplDateTo);
	}

	@Override
	public String getReportOutlet(String flagReport, String statusValidasi, String slsOpt, List<String> salesmans,
			String slsFrom, String slsTo, String custFrom, String custTo, String valDateFrom, String valDateTo,
			String custId, HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String hasil ="";
		
		List<ReportValidasiKTPDto> result = new ArrayList<>();
		result = validasiKTPRepository.viewReport(flagReport, statusValidasi, slsOpt, salesmans, slsFrom, slsTo, custFrom, custTo, valDateFrom, valDateTo, custId);
		if (result.size() == 0) {
			return "X";
		} else {
			Resource resource = new ClassPathResource("");
			resource = new ClassPathResource("validasi-ktp/report_validasi_ktp.jrxml");
			InputStream inStream = resource.getInputStream();
			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);

			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);
			jasperPrint.setProperty("net.sf.jasperreports.export.pdf.permissions.denied",
					"ASSEMBLY|COPY|FILL_IN|MODIFY_ANNOTATIONS|MODIFY_CONTENTS|SCREENREADERS|DEGRADED_PRINTING|PRINTING");
			try {
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				hasil = Base64.getEncoder().encodeToString(pdf);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return hasil;
		}
	}

	@Override
	public List<String> validasiGroupPayerHasBeenApproved(List<String> outlet) {
		// TODO Auto-generated method stub
		return validasiKTPRepository.validasiGroupPayerHasBeenApproved(outlet);
	}

}
