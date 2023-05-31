package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.GroupPayerBrowseDto;
import myor.matrix.master.entity.MasterOutletBrowseDto;
import myor.matrix.master.entity.MasterOutletDataAttributeDto;
import myor.matrix.master.entity.MasterOutletDataCoverDto;
import myor.matrix.master.entity.MasterOutletDataDivisiDto;
import myor.matrix.master.entity.MasterOutletDataPajakDto;
import myor.matrix.master.entity.MasterOutletDataPemerintahanDto;
import myor.matrix.master.entity.MasterOutletDataProfileDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SearchBrowseDto;

public interface MasterOutletRepository {

	public List<Object[]> getFtable13AwalAkhir(String groupDivisi);
	public String getFtable13XCustNo(String groupDivisi);
	public List<Object[]> getFtable48(String groupDivisi);
	public String getMaxCustNo(String group, String value);
	public String getXkeyString(String column,String table, String where);
	public List<Object[]> getXkeyListObject(String column,String table, String where);
	public List<MasterOutletDataProfileDto> getDataProfile(String custNo);
	public List<MasterOutletDataAttributeDto> getDataAttribute(String custNo);
	public List<MasterOutletDataPajakDto> getDataPajak(String custNo);
	public List<MasterOutletDataPemerintahanDto> getDataPemerintahan(String custNo);
	public List<MasterOutletDataCoverDto> getDataCover(String custNo);
	public List<MasterOutletDataDivisiDto> getDataDivisi(String custNo);
	
	public List<MasterOutletBrowseDto> getBrowseOutlet(String groupDivisi, String valTaskforce);
	
	public List<Object[]> getCekSuggestCustNo(String divisi);
	public List<Object[]> getCekSuggestCustNoFcustmst(String divisi);
	public List<Object[]> getCekDate(String custNo);
	public List<Object[]> getCekBlok(String custNo, String oprtype);

	public void insertData(String table, String column, String value);
	public void updateData(String table, String columnValue, String where);
	public void deleteData(String table, String where);

	public void insertFtable48(String custNo, String outletType, String groupDivisi, String typeCost, String cost, String kdKolektor, String tf, String byKrt, String groupPayer, String flagOutlet, String custNoOld, String nik, String groupDelivery);
	public void updateFtable48Add(String outletType, String groupDivisi, String custNoOld, String nik, String groupDelivery, String custNo);
	
	public void insertFtable64(String data1, String custNo, String limit, String groupOutlet);
	public void updateFtable64(String limit, String custNo, String groupOutlet);
	
	public void insertFcustmst(String custno, String custname, String custadd1, String custadd2, String ccity, String cphone1, String ckdpos, String data02, String data03,String data04, String flagout, String ccontact, String cfaxno, String data05, String data06, String cterm, String cweekno, String flaglimit,String data14, String  climit, String flagpay, String flagkbon, String data10, String data11, String firstopen, String regdate, String data19, String location, String data16, String kdpsr, String typeout, String klass, String grupout, String  distrik, String beat, String sbeat, String kdind, String data15, String data17, String data18, String gdisc, String gplu, String gkonv, String gharga, String taxname, String taxadd1, String taxadd2, String taxcity, String npwp, String   taxflag, String agenlain, String norefsup, String data07, String data08, String data09, String bank, String noacc, String cresd1, String cresd2, String ccity2, String cphone2, String flaghome);

	public void updateFcustmst(String custno, String custname, String custadd1, String custadd2, String ccity, String cphone1, String ckdpos, String data02, String data03, String data04, String ccontact, String cfaxno, String data05, String data06, String cterm, String cweekno, String data14, String climit, String flagpay, String flagkbon, String data10, String data11, String clastupd, String location, String data16, String kdpsr, String typeout, String klass, String grupout, String distrik, String beat, String sbeat, String kdind, String data15, String data17, String data18, String gdisc, String gplu, String gkonv, String gharga, String taxname, String taxadd1, String taxadd2, String taxcity, String npwp, String taxflag, String agenlain, String norefsup, String data07, String data08, String data09, String bank, String noacc, String cresd1, String cresd2, String ccity2, String cphone2, String flaghome);

	public void updateFtable48Update(String custNo, String outletType, String groupDivisi, String typeCost, String cost, String kdKolektor, String byKrt, String flagOutlet, String custNoOld, String nik, String groupDelivery, String groupPayer);

	public void updateFcustudUpdate(String custNo, String kdProp, String kdKab, String kdKec, String kdKel);

	public List<Object[]> getCekEditCover(String custNo, String slsNo, String groupDivisi);

	public void updateDataCover(MasterOutletDataCoverDto p);

	public void addDataCover(MasterOutletDataCoverDto p);

	public List<SalesmanBrowseDto> getBrowseSalesman(String groupDivisi, String taskforce);

	public List<GroupPayerBrowseDto> getBrowseGroupPayer(String taskforce);

	public List<SearchBrowseDto> getBrowseChiller();

}
