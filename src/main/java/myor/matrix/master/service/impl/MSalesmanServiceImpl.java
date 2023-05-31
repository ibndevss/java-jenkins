package myor.matrix.master.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.DataSalesmanDto;
import myor.matrix.master.entity.MSalesmanAdd;
import myor.matrix.master.entity.MSalesmanBrandChoosen;
import myor.matrix.master.entity.MSalesmanChoosenDto;
import myor.matrix.master.entity.MSalesmanDto;
import myor.matrix.master.entity.MSalesmanKolektor;
import myor.matrix.master.entity.MSalesmanProductChoosen;
import myor.matrix.master.entity.SalesforceBrowseDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.MSalesmanRepository;
import myor.matrix.master.service.MSalesmanService;
import myor.matrix.master.service.UtilService;
import myor.matrix.master.util.Util;

@Service
public class MSalesmanServiceImpl implements MSalesmanService {
	
	@Autowired
	private MSalesmanRepository mSalesmanRepository;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private Util util;
	
	@Transactional
	@Override
	public void insertPenggunaan(String tableName, String noSeq, String userId) {
		// TODO Auto-generated method stub
		int cekDataPengguna = mSalesmanRepository.cekPenggunaan(tableName,noSeq);
		if(cekDataPengguna == 0) {
			mSalesmanRepository.insertPenggunaan(tableName,noSeq,userId);
		} else {
			mSalesmanRepository.deletePenggunaan(tableName,noSeq,userId);
			mSalesmanRepository.insertPenggunaan(tableName, noSeq, userId);
		}
	}

	@Override
	public List<SalesmanBrowseDto> getListSelectItemSalesman() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getListSalesmanSelectItem();
	}
	
	@Override
	public List<SelectItem<String>> getListTeam() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getListTeam();
	}
	
	@Override
	public List<SelectItem<String>> getListRayon() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getListRayon();
	}
	
	@Override
	public List<SalesforceBrowseDto> getListSalesforce() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getListSalesforce();
	}
	
	@Override
	public List<Object[]> getListPejabat() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getListPejabat();
	}

	@Override
	public List<SelectItem<String>> getListGudang() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getListGudang();
	}

	@Override
	public List<SelectItem<String>> getListKolektor() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getListKolektor();
	}
	
	@Override
	public List<SelectItem<String>> getListSalesEmp() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getListSalesEmp();
	}
	
	
	@Override
	public List<SelectItem<String>> getListCustomer() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getListCustomer();
	}

	@Override
	public MSalesmanDto getSalesmanDto(String slsno) {
		// TODO Auto-generated method stub
		String salesNo = slsno;
		if (salesNo.equalsIgnoreCase("null")) {
			salesNo = mSalesmanRepository.getMinSalesNo();
		}
		
		MSalesmanChoosenDto dataSalesman = mSalesmanRepository.getSalesmanChoosen(salesNo);
		List<MSalesmanBrandChoosen> dataBrandSalesman = mSalesmanRepository.getSalesmanBrandChoosen(salesNo);	
		List<MSalesmanProductChoosen> dataProductSalesman = mSalesmanRepository.getSalesmanProductChoosen(dataSalesman.getTeam());
		
		MSalesmanDto dataEntry = new MSalesmanDto(dataSalesman, dataBrandSalesman, dataProductSalesman);
		
		return dataEntry;
	}

	@Override
	@Transactional
	public String getDateNowBySystem(){
		return mSalesmanRepository.getDateNowBySystem();	
	}
	
	@Override
	public String getDateNowByWarehouse(){
		return mSalesmanRepository.getDateWarehouse();
	}
	
	@Override
	public String getRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getSaran() {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getSaran();
	}

	@Transactional
	@Override
	public MSalesmanDto addSalesman(MSalesmanAdd mAddDataSales) {
		// TODO Auto-generated method stub
		mSalesmanRepository.addSalesmanFsls(mAddDataSales.getSlsno(), mAddDataSales.getSlsname(), mAddDataSales.getSlsadd1(), 
				mAddDataSales.getSlsadd2(), mAddDataSales.getSlscity(), mAddDataSales.getTeam(), mAddDataSales.getWorkdate(), 
				mAddDataSales.getOprtype(), getDateNowByWarehouse(), mAddDataSales.getKpej(), getDateNowByWarehouse(), mAddDataSales.getPddk(), 
				mAddDataSales.getLahir(), "Y", mAddDataSales.getKg(), "*", mAddDataSales.getData01(), mAddDataSales.getData02(), mAddDataSales.getData03(), 
				mAddDataSales.getData04(), mAddDataSales.getData05(), "T", mAddDataSales.getData08(), mAddDataSales.getData09(),
				mAddDataSales.getData10(), mAddDataSales.getData11(), mAddDataSales.getData12(), mAddDataSales.getData13(), mAddDataSales.getSlsemployee(), mAddDataSales.getKnownas());
		mSalesmanRepository.addSalesmanFtabel12(mAddDataSales.getSlsno(), mAddDataSales.getTeam(), getDateNowByWarehouse());
		mSalesmanRepository.addSalesmanFtable26(mAddDataSales.getSlsno(), mAddDataSales.getTypekartu(), mAddDataSales.getFlagkreditmobile());
	
		mSalesmanRepository.addSalesmanFtable35(mAddDataSales.getSlsno(), mAddDataSales.getKolektor(), mAddDataSales.getKodekol());
		
		return getSalesmanDto(mAddDataSales.getSlsno());
	}
	
	@Transactional
	@Override
	public MSalesmanDto updateSalesman(MSalesmanAdd mUpdateSales) {
		// TODO Auto-generated method stub
		mSalesmanRepository.updateSalesmanFemployee(mUpdateSales.getSlsname(), mUpdateSales.getSlsadd1(), mUpdateSales.getSlscity(), 
				mUpdateSales.getWorkdate(), getDateNowByWarehouse(), mUpdateSales.getSlsno());
		String nonAktif;
		String tgl_nonaktif;
		
		if(mUpdateSales.getFlagNonAktif() == false) {
			nonAktif = "";
			tgl_nonaktif = "";
		} else {
			nonAktif = "Y";
			tgl_nonaktif = getDateNowByWarehouse();
		}
		
		mSalesmanRepository.updateSalesmanFlagNonAktif(nonAktif, tgl_nonaktif, mUpdateSales.getSlsno());
		
		mSalesmanRepository.updateSalesmanFsls(mUpdateSales.getSlsno(), mUpdateSales.getSlsname(), mUpdateSales.getSlsadd1(), mUpdateSales.getSlsadd2(), 
				mUpdateSales.getSlscity(), mUpdateSales.getTeam(), mUpdateSales.getWorkdate(), mUpdateSales.getOprtype(), getDateNowByWarehouse(), 
				mUpdateSales.getKpej(), getDateNowByWarehouse(), mUpdateSales.getPddk(), mUpdateSales.getLahir(), mUpdateSales.getKg(), 
				mUpdateSales.getData01(), mUpdateSales.getData02(), mUpdateSales.getData03(), mUpdateSales.getData04(), mUpdateSales.getData05(), 
				"T", mUpdateSales.getData08(), mUpdateSales.getData09(), mUpdateSales.getData10(), mUpdateSales.getData11(), 
				mUpdateSales.getData12(), mUpdateSales.getData13(), mUpdateSales.getSlsemployee(), mUpdateSales.getKnownas());
		mSalesmanRepository.updateSalesmanFtable35(mUpdateSales.getSlsno(), mUpdateSales.getKolektor(), mUpdateSales.getKodekol());
		
		mSalesmanRepository.deleteFtabel12(mUpdateSales.getSlsno());
		mSalesmanRepository.addSalesmanFtabel12(mUpdateSales.getSlsno(), mUpdateSales.getTeam(), getDateNowByWarehouse());
		
		mSalesmanRepository.deleteFtable26(mUpdateSales.getSlsno());
		mSalesmanRepository.addSalesmanFtable26(mUpdateSales.getSlsno(), mUpdateSales.getTypekartu(), mUpdateSales.getFlagkreditmobile());
		
		return getSalesmanDto(mUpdateSales.getSlsno());
	}

	@Override
	public MSalesmanKolektor getKolektorChoosen(String empno) {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getKolektorChoosen(empno);
	}

	@Transactional
	@Override
	public MSalesmanDto blokSalesman(String slsno) {
		// TODO Auto-generated method stub
		mSalesmanRepository.blokSalesmanFsls(getDateNowByWarehouse(), slsno);
		mSalesmanRepository.blokSalesmanFcustmst(getDateNowByWarehouse(), slsno);
		mSalesmanRepository.deleteFcustsls(slsno);
		mSalesmanRepository.deleteFtabel12(slsno);
		
		return getSalesmanDto(slsno);
	}

	@Override
	public List<Object[]> getMasterFtable13(String param) {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getMasterFtable13(param);
	}

	@Override
	public List<MSalesmanProductChoosen> getTeamProduct(String team) {
		// TODO Auto-generated method stub
		return mSalesmanRepository.getSalesmanProductChoosen(team);
	}

	@Transactional
	@Override
	public String[] addDataSalesman(DataSalesmanDto d) {
		// TODO Auto-generated method stub
		String week = mSalesmanRepository.getWeek();
		String period = mSalesmanRepository.getPeriod();
		String year = mSalesmanRepository.getYear();
		String tanggalGudang = mSalesmanRepository.getDateWarehouse();
		String xflagrayon = d.getxFlagRayon();	
		String kodeRayon = d.getNoRayon();
		String kodeSalesman = d.getNoSalesman();
		String salesforce = d.getNoSalesforce();
		String team = d.getTeam();
		String user = d.getUserLogin();
		
		if (xflagrayon.equalsIgnoreCase("Y")) {
			List<Object[]> rayon = mSalesmanRepository.validasiRayon(kodeRayon);
			if (rayon.size() != 0) {				
				String[] message = {"warn","Warning!","Rayon Sudah digunakan!"};
				return message;
			}
		}
		
		List<Object[]> slsFsls = mSalesmanRepository.validasiSalesmanFsls(kodeSalesman);
		if (slsFsls.size() != 0) {				
			String[] message = {"warn","Warning!","Kode Salesman Sudah Ada!"};
			return message;
		}
		
		List<Object[]> slsFEmp = mSalesmanRepository.validasiSalesmanFemp(kodeSalesman);
		if (slsFEmp.size() != 0) {				
			String[] message = {"warn","Warning!","Kode Employee Sudah Ada!"};
			return message;
		}
		
		String XMASTERDATA = d.getxMasterData();
		
		if (XMASTERDATA.equalsIgnoreCase("")) {
			mSalesmanRepository.addFEmployee(d, tanggalGudang);
		}
		
		mSalesmanRepository.addFSls(d, tanggalGudang);
		
//		buat insert dokumen upload
		if (XMASTERDATA.equalsIgnoreCase("1")) {			
			String xdocno = utilService.getSequence("seq_uploadsales", "docno", "FSLS_UPLOAD");
			String xjam = util.getJamSystem2();
			String xkey = util.getXkey(21, 13, xjam);
			mSalesmanRepository.addDokumenUpload(xdocno, tanggalGudang, user, xkey, kodeSalesman);
		}
		
		
		mSalesmanRepository.deleteFtabel12(kodeSalesman);
		mSalesmanRepository.addSalesmanFtabel12(kodeSalesman, team, tanggalGudang);
		
		List<Object[]> ftable207 = mSalesmanRepository.getftable207(year, period, team, salesforce);
		if (ftable207.size() != 0) {
			mSalesmanRepository.deleteftabel57(kodeSalesman, year, period);
			mSalesmanRepository.deleteftable57(kodeSalesman, year, period);
			
			for (Object[] o : ftable207) {
				List<Object[]> ftabel57 = mSalesmanRepository.getftabel57(year, period, kodeSalesman);
				if (ftabel57.size() == 0) {
						mSalesmanRepository.addftabel57(((BigDecimal)o[0]).toString(), ((BigDecimal)o[1]).toString(), kodeSalesman, 
								((BigDecimal)o[4]).toString(), ((BigDecimal)o[5]).toString(), ((BigDecimal)o[6]).toString(), ((BigDecimal)o[7]).toString(), 
								((BigDecimal)o[8]).toString(), ((BigDecimal)o[9]).toString(), ((BigDecimal)o[10]).toString());
						mSalesmanRepository.addftable57(((BigDecimal)o[0]).toString(), ((BigDecimal)o[1]).toString(), kodeSalesman,
								((BigDecimal)o[11]).toString(), ((BigDecimal)o[12]).toString(), ((BigDecimal)o[13]).toString(), ((BigDecimal)o[14]).toString(), ((BigDecimal)o[15]).toString());
				}
			}
		}
		
		mSalesmanRepository.addSalesmanFtable35(kodeSalesman, d.getKolektor() +" - "+ d.getNmKolektor(), d.getKolektor());
		mSalesmanRepository.deleteFtable26(kodeSalesman);
		mSalesmanRepository.addSalesmanFtable26(kodeSalesman, d.getTipeKartu(), d.getBolehKredit());
		
		String[] message = {"success","Success!","Salesman Berhasil ditambahkan!"};
		return message;

	}
	
	@Override
	@Transactional
	public String[] updateDataSalesman(DataSalesmanDto d) {
		// TODO Auto-generated method stub
		String week = mSalesmanRepository.getWeek();
		String period = mSalesmanRepository.getPeriod();
		String year = mSalesmanRepository.getYear();
		String tglGudang = mSalesmanRepository.getDateWarehouse();
		String xflagrayon = d.getxFlagRayon();	
		String rayonNo = d.getNoRayon();
		String salesforce = d.getNoSalesforce();
		String team = d.getTeam();
		String user = d.getUserLogin();
		String slsNo = d.getNoSalesman();
		
		if (xflagrayon.equalsIgnoreCase("Y")) {
			List<Object[]> rayon = mSalesmanRepository.getValRayon(slsNo, rayonNo);
			if (rayon.size() != 0) {				
				String[] message = {"warn","Warning!","Rayon Sudah digunakan!"};
				return message;
			}
		}
		
		String kg = "";
		String transDate = "";
		List<Object[]> sales = mSalesmanRepository.getKGAndTRansDateSales(slsNo);
		for (Object[] o : sales) {
			kg = (String)o[0];
			transDate = (String)o[1];
		}
		
		if (!kg.equalsIgnoreCase(d.getNoGudang())) {
			List<Object[]> getValTransSales = mSalesmanRepository.getValTransSales(slsNo, kg, transDate);
			if (getValTransSales.size() != 0) {
				String[] message = {"warn","Warning!","Ada transaksi salesman hari ini!"};
				return message;
			}
			
			List<Object[]> getValInvPrint = mSalesmanRepository.getValInvPrint(slsNo);
			if (getValInvPrint.size() != 0) {
				String[] message = {"warn","Warning!","Ada faktur yang belum dicetak!"};
				return message;
			}
			
			List<Object[]> getValTransGudang = mSalesmanRepository.getValTransGudang(slsNo);
			if (getValTransGudang.size() != 0) {
				String[] message = {"warn","Warning!","Ada transaksi gudang belum proses akhir hari!"};
				return message;
			}
			
			if (d.getTipeOperasi().equalsIgnoreCase("C")) {
				List<Object[]> getValTransVan = mSalesmanRepository.getValTransVan(slsNo, kg, transDate);
				if (getValTransVan.size() != 0) {
					String[] message = {"warn","Warning!","Ada transaksi van hari ini!"};
					return message;
				}
				List<Object[]> getValTransVanNOtProcess = mSalesmanRepository.getValTransVanNOtProcess(slsNo, kg);
				if (getValTransVanNOtProcess.size() != 0) {
					String[] message = {"warn","Warning!","Ada booking van yang belum diproses!"};
					return message;
				}
				List<Object[]> getValTRansVanNotEod = mSalesmanRepository.getValTRansVanNotEod(slsNo);
				if (getValTRansVanNotEod.size() != 0) {
					String[] message = {"warn","Warning!","Ada transaksi van belum proses akhir hari!"};
					return message;
				}
			}
			
		}
		
		mSalesmanRepository.updateFemployee(d.getNamaSalesman(), d.getAlamat1(), d.getKota(), d.getTglMasukKerja(), tglGudang, slsNo);
		mSalesmanRepository.updateFsls(d, tglGudang);
		
		mSalesmanRepository.deleteFtabel12(slsNo);
		mSalesmanRepository.insertftabel12(slsNo, d.getTeam(), tglGudang);
		
		List<Object[]> ftable207 = mSalesmanRepository.getftable207(year, period, team, salesforce);
		if (ftable207.size() != 0) {
			mSalesmanRepository.deleteftabel57(slsNo, year, period);
			mSalesmanRepository.deleteftable57(slsNo, year, period);
			
			for (Object[] o : ftable207) {
				List<Object[]> ftabel57 = mSalesmanRepository.getftabel57(year, period, slsNo);
				if (ftabel57.size() == 0) {
						mSalesmanRepository.addftabel57(((BigDecimal)o[0]).toString(), ((BigDecimal)o[1]).toString(), slsNo, 
								((BigDecimal)o[4]).toString(), ((BigDecimal)o[5]).toString(), ((BigDecimal)o[6]).toString(), ((BigDecimal)o[7]).toString(), 
								((BigDecimal)o[8]).toString(), ((BigDecimal)o[9]).toString(), ((BigDecimal)o[10]).toString());
						mSalesmanRepository.addftable57(((BigDecimal)o[0]).toString(), ((BigDecimal)o[1]).toString(), slsNo,
								((BigDecimal)o[11]).toString(), ((BigDecimal)o[12]).toString(), ((BigDecimal)o[13]).toString(), ((BigDecimal)o[14]).toString(), ((BigDecimal)o[15]).toString());
				}
			}
		}
		
		mSalesmanRepository.updateftable35(slsNo, d.getKolektor(), d.getKolektor() +" - "+ d.getNmKolektor());
		mSalesmanRepository.deleteFtable26(slsNo);
		mSalesmanRepository.addSalesmanFtable26(slsNo, d.getTipeKartu(), d.getBolehKredit());
		
		String[] message = {"success","Success!","Salesman Berhasil diperbarui!"};
		return message;
	};


	@Override
	@Transactional
	public String[] cekBlokSalesman(DataSalesmanDto p) {
		// TODO Auto-generated method stub
		String slsNo = p.getNoSalesman();
		String tglTransaksi = p.getTglTransaksi();
		String xtbl_name = "FSLS";
		String xno_seq = p.getNoSalesman();
		String login_user = p.getUserLogin();
		String[] pesan = cek_penggunaan(xtbl_name, xno_seq, login_user);
		if (pesan != null) {
			return pesan;
		}
		
		List<Object[]> getValInvPrint = mSalesmanRepository.getValInvPrint(slsNo);
		if (getValInvPrint.size() != 0) {
			String[] message = {"warn","Warning!","Ada faktur yang belum dicetak!"};
			return message;
		}
		
		List<Object[]> getValTransGudang = mSalesmanRepository.getValTransGudang(slsNo);
		if (getValTransGudang.size() != 0) {
			String[] message = {"warn","Warning!","Ada transaksi gudang belum proses akhir hari!"};
			return message;
		}
		
		if (p.getTipeOperasi().equalsIgnoreCase("C")) {
			List<Object[]> getValVanEod = mSalesmanRepository.getValVanEod(slsNo);
			if (getValVanEod.size() != 0) {
				String[] message = {"warn","Warning!","Ada transaksi van belum proses akhir hari!"};
				return message;
			}
			List<Object[]> getValStokVan = mSalesmanRepository.getValStokVan(slsNo);
			if (getValStokVan.size() != 0) {
				String[] message = {"warn","Warning!","Van masih mempunyai stok!"};
				return message;
			}
			List<Object[]> getValVanNotProcess = mSalesmanRepository.getValVanNotProcess(slsNo);
			if (getValVanNotProcess.size() != 0) {
				String[] message = {"warn","Warning!","Ada booking van yang belum diproses!"};
				return message;
			}
			List<Object[]> getValTransToday = mSalesmanRepository.getValTransToday(slsNo, tglTransaksi);
			if (getValTransToday.size() != 0) {
				String[] message = {"warn","Warning!","Ada transaksi hari ini!"};
				return message;
			}
		}
		
		return null;
	}
	
	@Override
	public String[] cekAddSalesman() {
		// TODO Auto-generated method stub
		String tglGudang = mSalesmanRepository.getDateWarehouse();
		List<Object[]> cekSudahEod = mSalesmanRepository.cekTglGudang(tglGudang);
		if (cekSudahEod.size() != 0) {
			String[] message = {"warn","Warning!","Sudah EOD Tidak Boleh Input Salesman!"};
			return message;
		}
		
		List<Object[]> cekNoSequence = mSalesmanRepository.cekSeqNumber();
		if (cekNoSequence.size() != 0) {
			for (Object[] o : cekNoSequence) {
				BigDecimal from = ((BigDecimal)o[0]);
				BigDecimal to = ((BigDecimal)o[1]);
				if (from == null || to == null ) {
					String[] message = {"warn","Warning!","No sequence belum disetting!"};
					return message;
				}
			}
		}
		return null;
	}

	@Override
	@Transactional
	public String[] cekEditSalesman(DataSalesmanDto p) {
		// TODO Auto-generated method stub
		String xtbl_name = "FSLS";
		String xno_seq = p.getNoSalesman();
		String login_user = p.getUserLogin();
		String[] message = cek_penggunaan(xtbl_name, xno_seq, login_user);
		if (message != null) {
			return message;
		}
		
		insert_penggunaan(xtbl_name, xno_seq, login_user);
		
		return null;
	}

	@Transactional
	private String[] cek_penggunaan(String xtbl_name,String xno_seq, String login_user) {
		
		List<Object[]> cekAntrian = mSalesmanRepository.cekAntrian(xtbl_name, xno_seq, login_user, true);
		if (cekAntrian.size() > 0 ) {
			for (Object[] o : cekAntrian) {
				String namaUser = (String)o[0];
				String[] message = {"warn","Warning!","Nomor Document ini "  + xno_seq + ",sedang digunakan oleh user " + namaUser};
				return message;
			}
		} else {
			mSalesmanRepository.deleteAntrian(xtbl_name, xno_seq, login_user, false, true);
			mSalesmanRepository.insertAntrian(xtbl_name, xno_seq, login_user);
		}
		
		return null;
	};
	
	@Transactional
	private void insert_penggunaan(String xtbl_name,String xno_seq, String login_user) {
		List<Object[]> cekAntrian = mSalesmanRepository.cekAntrian(xtbl_name, xno_seq, login_user, false);
		if (cekAntrian.size() == 0 ) {
			mSalesmanRepository.insertAntrian(xtbl_name, xno_seq, login_user);
		} else {
			mSalesmanRepository.deleteAntrian(xtbl_name, xno_seq, login_user, false, true);
			mSalesmanRepository.insertAntrian(xtbl_name, xno_seq, login_user);
		}
	}

	@Override
	@Transactional
	public void deleteAntrian(DataSalesmanDto p) {
		// TODO Auto-generated method stub
		String slsNo = p.getNoSalesman();
		String xUser = p.getUserLogin();
		mSalesmanRepository.deleteAntrian("FSLS", slsNo, xUser, true, false);
	}

	@Override
	public String[] getCekNewNoSlsNo(String slsNo) {
		// TODO Auto-generated method stub
		String Xmaster = "";
		BigDecimal range_awal = null;
		BigDecimal range_akhir = null;
		List<Object[]> getXmaster = mSalesmanRepository.getMasterFtable13("XMASTERDATA");
		for (Object[] o : getXmaster) {
			Xmaster = (String)o[7];
		}
		
		List<Object[]> getRange = mSalesmanRepository.getRange(slsNo, Xmaster);
		if (getRange.size() != 0 ) {
			for (Object[] o : getRange) {
				range_awal = (BigDecimal)o[1];
				range_akhir = (BigDecimal)o[2];
			}
		}
		
		List<Object[]> getCekRange = mSalesmanRepository.getCekRange(slsNo, Xmaster);
		if (getCekRange.size() == 0) {
			String[] message = {"warn","Warning!","Kode salesman tidak boleh lebih kecil "  + range_awal + " atau lebih dari " + range_akhir};
			return message;
		}
		
		List<Object[]> getCekSlsno = mSalesmanRepository.getCekSlsno(slsNo, Xmaster);
		if (getCekSlsno.size() != 0) {
			String[] message = {"warn","Warning!","Kode salesman sudah ada!"};
			return message;
		}
		return null;
	}

	@Override
	public String[] getSuggestNewNoSalesman() {
		// TODO Auto-generated method stub
		String Xmaster = "";
		BigDecimal range_awal = null;
		BigDecimal range_akhir = null;
		List<Object[]> getXmaster = mSalesmanRepository.getMasterFtable13("XMASTERDATA");
		for (Object[] o : getXmaster) {
			Xmaster = (String)o[7];
		}
		List<Object[]> getRange = mSalesmanRepository.getRange(null, Xmaster);
		if (getRange.size() != 0 ) {
			for (Object[] o : getRange) {
				range_awal = (BigDecimal)o[1];
				range_akhir = (BigDecimal)o[2];
			}
		}
		String newNoSls ="";
		String xsaran = mSalesmanRepository.getSuggestNewNoSlsNo(Xmaster, range_awal.toString(), range_akhir.toString());
		if (xsaran.isEmpty() || xsaran == null) {
			List<Object[]> getCekRangeAwalSlsno = mSalesmanRepository.getCekRangeAwalSlsno(range_awal.toString());
			if (getCekRangeAwalSlsno.size() == 0) {
				newNoSls = range_awal.toString();
			} else {
				String[] message = {"warn","Warning!","Nomor Urut Salesman sudah habis!"};
				return message;
			}
		} else {
			newNoSls = xsaran;
		}
		
		String[] message = {"success","Success!",newNoSls};
		return message;
	}

	@Override
	@Transactional
	public String[] processBlokSalesman(DataSalesmanDto p) {
		// TODO Auto-generated method stub
		String slsNo = p.getNoSalesman();
		String password = p.getPassword();
		String tglGudang = mSalesmanRepository.getDateWarehouse();
		List<Object[]> getUBAHS = mSalesmanRepository.getUBAHS();
		if (getUBAHS.size() != 0) {
			List<Object[]> getFPASSWORD = mSalesmanRepository.getFPASSWORD(password);
			if (getFPASSWORD.size() == 0) {
				String[] message = {"warn","Warning!","Password tidak sesuai!"};
				return message;
			} 
		} else {
			if (!password.contentEquals("SUPPORT")) {
				String[] message = {"warn","Warning!","Password tidak sesuai!"};
				return message;
			}
		}
		
		mSalesmanRepository.updateBlokFsls(slsNo, tglGudang);
		mSalesmanRepository.updateBlokFCUSTMST(slsNo, tglGudang);
		mSalesmanRepository.deleteBlokFCUSTSLS(slsNo);
		mSalesmanRepository.deleteBlokFTABEL12(slsNo);
		
		String[] message = {"success","Success!","Kode Salesman "+slsNo+ " telah berhasil diblok!"};
		return message;
	}
	
	@Override
	public List<MSalesmanChoosenDto> getDataSalesmanSummary(){
		return mSalesmanRepository.getDataSalesmanSummary();
	}
}
