package myor.matrix.master.repository;

import java.math.BigDecimal;
import java.util.List;

import myor.matrix.master.entity.DataSalesmanDto;
import myor.matrix.master.entity.MSalesmanBrandChoosen;
import myor.matrix.master.entity.MSalesmanChoosenDto;
import myor.matrix.master.entity.MSalesmanKolektor;
import myor.matrix.master.entity.MSalesmanProductChoosen;
import myor.matrix.master.entity.SalesforceBrowseDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface MSalesmanRepository {
	
	public String getMinSalesNo();
	public String getDateNowBySystem();

	public String getDateWarehouse();
	public String getWeek();
	public String getPeriod();
	public String getYear();
	
	
	public String getRange();
	
	public BigDecimal getSaran();
	
	public int cekPenggunaan(String tableName, String noSeq);

	public void insertPenggunaan(String tableName, String noSeq, String userId);

	public void deletePenggunaan(String tableName, String noSeq, String userId);
	
	public List<Object[]> getMasterFtable13(String param);

	public List<SalesmanBrowseDto> getListSalesmanSelectItem();
	public List<SelectItem<String>> getListTeam();
	public List<SelectItem<String>> getListRayon();
	public List<SalesforceBrowseDto> getListSalesforce();
	public List<Object[]> getListPejabat();
	public List<SelectItem<String>> getListGudang();
	public List<SelectItem<String>> getListKolektor();
	public List<SelectItem<String>> getListCustomer();
	public List<SelectItem<String>> getListSalesEmp();
	
	public MSalesmanChoosenDto getSalesmanChoosen(String slsno);
	public MSalesmanKolektor getKolektorChoosen(String empno); 
	
	public List<MSalesmanBrandChoosen> getSalesmanBrandChoosen(String slsno);
	public List<MSalesmanProductChoosen> getSalesmanProductChoosen(String team);
	
	public void addSalesmanFsls(String slsno,String slsname,String slsadd1,String slsadd2,String slscity,String team,
			String workdate,String oprtype, String transdate,String kpej, String upddate, String pddk,String lahir,String flagbonus,
			String kg,String flagsbd,String data01,String data02,String data03,String data04,String data05,String data06,String data08,
			String data09,String data10,String data11,String data12,String data13,String slsemployee,String knownas);
	
	public void addSalesmanFtabel12(String slsno, String team, String tglgudang);
	public void addSalesmanFtable35(String slsno, String kolektor, String kodekol);
	public void addSalesmanFtable26(String slsno, String typekartu, String flagkreditmobile);
	
	public void blokSalesmanFsls(String tglGudang, String slsno);
	public void blokSalesmanFcustmst(String tglGudang, String slsno);
	public void deleteFcustsls(String slsno);
	
	public void updateSalesmanFemployee(String slsname, String slsadd1, String slscity, String workdate, String upddate, String slsno);
	public void updateSalesmanFlagNonAktif(String flagNonAktif, String tglGudang, String slsno);
	public void updateSalesmanFsls(String slsno,String slsname,String slsadd1,String slsadd2,String slscity,String team,
			String workdate,String oprtype, String transdate,String kpej, String upddate, String pddk,String lahir,
			String kg, String data01,String data02,String data03,String data04,String data05,String data06,String data08,
			String data09,String data10,String data11,String data12,String data13,String slsemployee,String knownas);
	public void updateSalesmanFtable35(String slsno, String kolektor, String kodekol);
	
	public void deleteFtabel12(String slsno);
	public void deleteFtable26(String slsno);
	
	public List<Object[]> getftable207(String year, String period, String team, String salesforce);
	public void deleteftabel57 (String kodeSalesman, String year, String period);
	public void deleteftable57 (String kodeSalesman, String year, String period);
	public List<Object[]> getftabel57(String year, String period, String kodeSalesman);
	public void addftabel57(String year, String periode, String kodeSalesman, String pembagiPerHari, String pembagiPerPekan, String targetCb, String targetOa, String targetCall, 
			String targetEc, String targetIpt);
	public void addftable57(String year, String periode, String kodeSalesman, String targetSbpt, String targetOmzet, String targetSc, String targetEp, String flagTargetOa);
	
	public List<Object[]> validasiRayon(String kodeRayon);
	public List<Object[]> validasiSalesmanFsls(String kodeSalesman);
	public List<Object[]> validasiSalesmanFemp(String kodeSalesman);
	
	public void addFEmployee(DataSalesmanDto d, String tanggalGudang);
	public void addFSls(DataSalesmanDto d, String tanggalGudang);
	public void addDokumenUpload(String xdocno, String tanggalGudang, String user,String xkey, String kodeSalesman);
	
	public List<Object[]> cekTglGudang(String tglGudang);
	public List<Object[]> cekSeqNumber();
	public List<Object[]> cekAntrian(String xtbl_name, String xno_seq, String login_user, Boolean cek);
	public void deleteAntrian(String xtbl_name, String xno_seq, String login_user, Boolean cekName, Boolean cekKey);
	public void insertAntrian(String xtbl_name, String xno_seq, String login_user);
	
	public List<Object[]> getRange(String slsNo, String Xmaster);
	public List<Object[]> getCekRange(String slsNo, String Xmaster);
	public List<Object[]> getCekSlsno(String slsNo, String Xmaster);
	public String getSuggestNewNoSlsNo(String Xmaster, String range_awal, String range_akhir);
	public List<Object[]> getCekRangeAwalSlsno(String range_awal);
	
	public List<Object[]> getValRayon(String slsNo, String rayonNo);
	public List<Object[]> getKGAndTRansDateSales(String slsNo);
	public List<Object[]> getValTransSales(String slsNo, String kg,String transDate);
	public List<Object[]> getValInvPrint(String slsNo);
	public List<Object[]> getValTransGudang(String slsNo);
	public List<Object[]> getValTransVan(String slsNo, String kg, String transDate);
	public List<Object[]> getValTransVanNOtProcess(String slsNo, String kg);
	public List<Object[]> getValTRansVanNotEod(String slsNo);
	
	public void updateFemployee(String slsName, String slsAdd1, String slsCity, String slsDateIn, String tglGudang, String slsNo);
	public void updateFsls(DataSalesmanDto d, String tglGudang);
	
	public void insertftabel12(String slsNo, String slsTeam, String tglGudang);
	public void updateftable35(String slsNo, String kolektorNo, String kolektorName);
	
	public List<Object[]> getValVanEod(String slsNo);
	public List<Object[]> getValStokVan(String slsNo);
	public List<Object[]> getValVanNotProcess(String slsNo);
	public List<Object[]> getValTransToday(String slsNo, String tglTransaksi);
	public void updateBlokFsls(String slsNo, String tglGudang);
	public void updateBlokFCUSTMST(String slsNo, String tglGudang);
	public void deleteBlokFCUSTSLS(String slsNo);
	public void deleteBlokFTABEL12(String slsNo);
	public List<Object[]> getUBAHS();
	public List<Object[]> getFPASSWORD(String password);
	List<MSalesmanChoosenDto> getDataSalesmanSummary();
}
