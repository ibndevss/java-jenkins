package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ReportValidasiKTPDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.ValidasiKTPDto;
import myor.matrix.master.entity.ValidasiKTPOutletApproveDto;
import myor.matrix.master.entity.ValidasiKtpOutletGroupPayerDto;

public interface ValidasiKTPRepository {
	boolean cekPathPhotoKTP();
	List<ValidasiKTPDto> getValidasiKTP(String custno);
	List<ValidasiKtpOutletGroupPayerDto> getDetilGroupPayer(String custno);
	public void updateValidasiKTP(String pemilik, String telpon, String ktp, String ktpName, String alamat1, String alamat2,
			String npwp, String npwpName, String custno);
	boolean cekNpwp();
	boolean cekIsExistOutletApproveByGroupPayer(String custno);
	public void rejectValidasiKTP(String userLogin, String custno);
	public void revalidate(String userLogin, String custno);
	boolean cekIsExistPassword();
	boolean cekIsPassword(String pass);
	List<String> infoApproveMoreThanOneOutletInGroupPayer(List<String> outlet);
	String getDocno();
	double getToleransiAtt();
	boolean cekIsExistDocno(String docno);
	void approveHeader(String docno, String tglGudang, String jamSys, String tglSys, String user);
	ValidasiKTPOutletApproveDto getDetailOutletApprove(String custno);
	void approveDetail(ValidasiKTPOutletApproveDto data, String docno, String user, String tglGudang, String jamSys, String tglSys);
	List<SelectItem<String>> getCustnoByFilter(String statusValidasi, String slsOpt, List<String> salesmans, String slsFrom, String slsTo, 
			String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom, String kplDateTo);
	int getCountCustnoByFilter(String statusValidasi, String slsOpt, List<String> salesmans, String slsFrom, String slsTo, 
			String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom, String kplDateTo);
	List<SelectItem<String>> getSelectItemApprove(String slsOpt, List<String> salesmans, String slsFrom, String slsTo, 
			String custFrom, String custTo, String valDateFrom, String valDateTo, String kplDateFrom, String kplDateTo);
	
	List<ReportValidasiKTPDto> viewReport(String flagReport, String statusValidasi, String slsOpt, List<String> salesmans, String slsFrom, String slsTo,
			String custFrom, String custTo, String valDateFrom, String valDateTo, String custId);
	
	List<String> validasiGroupPayerHasBeenApproved(List<String> outlet);
}
