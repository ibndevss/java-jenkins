package myor.matrix.master.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.ValidasiKTPDto;
import net.sf.jasperreports.engine.JRException;

public interface ValidasiKTPService {
	public List<String> saveValidasiKTP(String pemilik, String telpon, String ktp, String ktpName, String alamat1,
			String alamat2, String npwp, String npwpName, String custno);
	List<SelectItem<String>> getCustnoByFilter(String statusValidasi, String slsOpt, List<String> salesmans, String slsFrom, String slsTo, 
			String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom, String kplDateTo);
	int getCountCustnoByFilter(String statusValidasi, String slsOpt, List<String> salesmans, String slsFrom, String slsTo, 
			String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom, String kplDateTo);
	
	//refresh data
	boolean cekPathPhotoKTP();
	List<ValidasiKTPDto> getValidasiKTP(String custno);
	public Resource loadImageKTP(String custno);
	public Resource loadImageNPWP(String custno);
	//refresh data
	
	//reject data
	public boolean cekIsExistOutletApproveByGroupPayer(String custno);
	public void rejectValidasiKTP(String userLogin, String custno);
	//reject data 
	
	//revalidate
	boolean cekIsExistPassword();
	boolean cekIsPassword(String pass);
	public void revalidate(String userLogin, String custno);
	//revalidate
	
	//approve
	List<String> infoApproveMoreThanOneOutletInGroupPayer(List<String> outlet);
//	double getDocno(); //0 : belum disetting
//	boolean cekIsExistDocno(String docno); 
//	double getToleransiAtt(); //0 : belum disetting
	List<String> approve(String user, List<String> custno);
	//approve
	
	//list outlet
	List<SelectItem<String>> getSelectItemApprove(String slsOpt, List<String> salesmans, String slsFrom, String slsTo, 
			String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom, String kplDateTo);
	//list outlet
	
	public String getReportOutlet(String flagReport, String statusValidasi, String slsOpt,
			List<String> salesmans, String slsFrom, String slsTo, String custFrom, String custTo, String valDateFrom,
			String valDateTo, String custId, HttpServletResponse response) throws FileNotFoundException, JRException, IOException;
	
	List<String> validasiGroupPayerHasBeenApproved(List<String> outlet);
}
