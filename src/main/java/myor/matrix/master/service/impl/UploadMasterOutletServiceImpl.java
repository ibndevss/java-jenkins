package myor.matrix.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.UploadMasterOutletCoverDto;
import myor.matrix.master.entity.UploadMasterOutletDto;
import myor.matrix.master.entity.UploadMasterOutletExcelCoverDto;
import myor.matrix.master.entity.UploadMasterOutletExcelOutletDto;
import myor.matrix.master.entity.UploadMasterOutletOutletDto;
import myor.matrix.master.repository.UploadMasterOutletRepository;
import myor.matrix.master.repository.UtilRepository;
import myor.matrix.master.service.UploadMasterOutletService;
import myor.matrix.master.tenant.TenantContext;

@Service
public class UploadMasterOutletServiceImpl implements UploadMasterOutletService {
	
	@Autowired
	private UploadMasterOutletRepository repoMasterOutletRepository;
	
	@Autowired
	private UtilRepository repoUtilRepository;
	
	@Autowired
	private TenantContext t;

	@Override
	public UploadMasterOutletDto cekExcel(UploadMasterOutletDto p) {
		// TODO Auto-generated method stub
		List<String> logs = new ArrayList<>();
		String message = "success";
		String user = p.getUser();
		List<Object[]> cekData =  new ArrayList<>();
		
		List<UploadMasterOutletCoverDto> dataCover = new ArrayList<>();
		List<UploadMasterOutletOutletDto> dataOutlet = new ArrayList<>();
		
		String xFlag = repoUtilRepository.getTableString("FTABLE13", "memostring", "where XKEY='XBRANCH'");
		if (xFlag.length() != 0) {
			xFlag = "1";
		}
		
		try {
			 cekData = repoUtilRepository.getTableListObject("FTABLE49", "*", "");
		} catch (Exception e) {
			// TODO: handle exception
			logs.add("Download outlet tidak dapat dilakukan, lakukan proses inisialisasi data terlebih dulu!");
			message = "warn";
			return new UploadMasterOutletDto(null, null, null, null, logs, message, user);
		}
		
		 cekData = repoUtilRepository.getTableListObject("FTABLE49", "*", "");
		if (cekData.size() == 0) {
			logs.add("Master tipe outlet tidak ada!");
			message = "warn";
			return new UploadMasterOutletDto(null, null, null, null, logs, message, user);
		}
		
		cekData = repoUtilRepository.getTableListObject("ftable13", "XKEY, memonama", "where XKEY ='user' and memonama <> '"+user+"'");
		if (cekData.size() != 0) {
			logs.add("Ada user lain yang sedang login di Matrix Pro!");
			for (Object[] cU : cekData) {
				logs.add("User " +Objects.toString(cU[1], ""));
			}
			message = "warn";
			return new UploadMasterOutletDto(null, null, null, null, logs, message, user);
		}
		
		cekData = repoUtilRepository.getTableListObject("fuseract", "*", "");
		if (cekData.size() != 0) {
			logs.add("Ada user yang masih menggunakan aplikasi, Close seluruh User aplikasi terlebih dahulu !");
			message = "warn";
			return new UploadMasterOutletDto(null, null, null, null, logs, message, user);
		}
		
		String XNPWPBLANK = repoUtilRepository.getTableString("ftable13", "memostring", "WHERE xkey = 'XNPWPBLANK'");
		String XMASTERDATA = repoUtilRepository.getTableString("ftable13", "memostring", "WHERE xkey = 'XMASTERDATA'");
		String XGROUPHARGA = repoUtilRepository.getTableString("fmemo", "CASE  WHEN memostring = 'GT' THEN '100' ELSE '200' END memostring",
				"WHERE memonama = 'TIPEDIST'");
		String xtanggal = repoUtilRepository.getTableString("FMEMO", "TO_CHAR(MEMODATE,'DD MON YYYY')", "WHERE MEMONAMA='CADATE'");
		String xweek = repoUtilRepository.getTableString("fcycle3", "weekno", "where CDATE ='"+xtanggal+"'");
		if (xweek.length() == 0) {
			logs.add("Kalendar operasional belum disetting!");
			message = "warn";
			return new UploadMasterOutletDto(null, null, null, null, logs, message, user);
		} 
		
		Boolean XCEK = true;
		// download cover sales
		for (UploadMasterOutletExcelCoverDto tpSls : p.getDataCoverExcel()) {
			String xstr = tpSls.getOutlet2();
			Integer hasil = xstr.length();
			if (hasil == 7) {
				Boolean masuk = true;
				
				//1B.---CEK KODE OUTLET YANG SUDAH TERDAFTAR
				cekData = repoUtilRepository.getTableListObject("fcustmst", "*", "WHERE CUSTNO = '"+tpSls.getOutlet2()+"'");
				if (cekData.size() != 0) {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Kode outlet sudah ada "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//1.B.----------------------CEK KODE OUTLET YANG SUDAH TERDAFTAR

				//1C.---CEK Type Kode Salesman
				cekData = repoUtilRepository.getTableListObject("FSLS", "*", "WHERE SLSNO = '"+tpSls.getSalesman3()+"'");
				if (cekData.size() == 0) {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Kode Salesman tidak ada "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//1C.----------------------CEK Kode Salesman
				
				//1D.--CEK Kode Kode Outlet dan Salesman yang sudah di Download
				//1D.-------------CEK Kode Kode Outlet dan Salesman yang sudah di Download
				
				//1.--cek kode sales yang kosong
				if (tpSls.getSalesman3().length() == 0) {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Kode Salesman harus diisi "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//1.----------- cek salesman yang blank
				
				//2.--cek Hari Senin yang tidak sesuai
				if (tpSls.getSenin6().equalsIgnoreCase("Y")) {
					
				} else if (tpSls.getSenin6().equalsIgnoreCase("N")) {
					
				} else {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Field Hari Senin Tidak Sesuai "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//2.----------- cek Hari Senin yang tidak sesuai
				
				//3.--cek Hari Selasa yang tidak sesuai
				if (tpSls.getSelasa7().equalsIgnoreCase("Y")) {
					
				} else if (tpSls.getSelasa7().equalsIgnoreCase("N")) {
					
				} else {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Field Hari Selasa Tidak Sesuai "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//3.----------- cek Hari Senin yang tidak sesuai
				
				//4.--cek Hari Rabu yang tidak sesuai
				if (tpSls.getRabu8().equalsIgnoreCase("Y")) {
					
				} else if (tpSls.getRabu8().equalsIgnoreCase("N")) {
					
				} else {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Field Hari Rabu Tidak Sesuai "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//4.----------- cek Hari Rabu yang tidak sesuai
				
				//5.--cek Hari Kamis yang tidak sesuai
				if (tpSls.getKamis9().equalsIgnoreCase("Y")) {
					
				} else if (tpSls.getKamis9().equalsIgnoreCase("N")) {
					
				} else {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Field Hari Kamis Tidak Sesuai "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//5.----------- cek Hari Kamis yang tidak sesuai
				
				//6.--cek Hari Jumat yang tidak sesuai
				if (tpSls.getJumat10().equalsIgnoreCase("Y")) {
					
				} else if (tpSls.getJumat10().equalsIgnoreCase("N")) {
					
				} else {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Field Hari Jumat Tidak Sesuai "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//6.----------- cek Hari Jumat yang tidak sesuai
				
				//7.--cek Hari Sabtu yang tidak sesuai
				if (tpSls.getSabtu11().equalsIgnoreCase("Y")) {
					
				} else if (tpSls.getSabtu11().equalsIgnoreCase("N")) {
					
				} else {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Field Hari Sabtu Tidak Sesuai "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//7.----------- cek Hari Sabtu yang tidak sesuai
				
				//8.--cek Hari Minggu yang tidak sesuai
				if (tpSls.getMinggu12().equalsIgnoreCase("Y")) {
					
				} else if (tpSls.getMinggu12().equalsIgnoreCase("N")) {
					
				} else {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Field Hari Minggu Tidak Sesuai "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//8.----------- cek Hari  Minggu yang tidak sesuai
				
				//Cek Pola				
				if (tpSls.getPola13().equalsIgnoreCase("BeWeekly1") || tpSls.getPola13().equalsIgnoreCase("BeWeekly2") ||
					tpSls.getPola13().equalsIgnoreCase("Weekly") || tpSls.getPola13().equalsIgnoreCase("Monthly1") ||
					tpSls.getPola13().equalsIgnoreCase("Monthly2") || tpSls.getPola13().equalsIgnoreCase("Monthly3") ||
					tpSls.getPola13().equalsIgnoreCase("Monthly4")) {
				} else {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Tidak sesuai Harus Di Isi BeWeekly1,BeWeekly2,Weekly,Monthly1,Monthly2,Monthly3,Monthly4 "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				}
				//_________Cek Pola
				
				//T16-161_Validasi Digit Route pada saat Upload Master Outlet dan perubahan coverage
				if (tpSls.getNorute14().length() != 3) {
					XCEK = false; message = "warn";
					logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
							+ "| Panjang NO Rute <> 3 "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
				} else {
					try {
						Integer ANGKA = Integer.valueOf(tpSls.getNorute14()) * 1 ;
					} catch (Exception e) {
						// TODO: handle exception
						XCEK = false; message = "warn";
						logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"|"+tpSls.getNamasalesman4()+""
								+ "| Rute Harus Angka "+"pada daftar sales cover baris ke - "+tpSls.getNumber1());
					}
				}
				//T16-161_Validasi Digit Route pada saat Upload Master Outlet dan perubahan coverage
				if (XCEK == true) {
					UploadMasterOutletCoverDto coverInput = new UploadMasterOutletCoverDto();
					
					coverInput.setCustno(tpSls.getOutlet2());
					coverInput.setSlsno(tpSls.getSalesman3());
					coverInput.setSlsname(tpSls.getNamasalesman4());
					
					if (tpSls.getBaris5().length() == 0) {
						coverInput.setBaris("1");
					} else {
						coverInput.setBaris(tpSls.getBaris5());
					}
					
					coverInput.setHsenin(tpSls.getSenin6());
					coverInput.setHselasa(tpSls.getSelasa7());
					coverInput.setHrabu(tpSls.getRabu8());
					coverInput.setHkamis(tpSls.getKamis9());
					coverInput.setHjumat(tpSls.getJumat10());
					coverInput.setHsabtu(tpSls.getSabtu11());
					coverInput.setHminggu(tpSls.getMinggu12());
					
					if (tpSls.getPola13().equalsIgnoreCase("BeWeekly1")) {
						coverInput.setVisit1("Y");
						coverInput.setVisit2("T");
						coverInput.setVisit3("Y");
						coverInput.setVisit4("T");
						coverInput.setPola("BeWeekly1");
					} else if (tpSls.getPola13().equalsIgnoreCase("BeWeekly2")) {
						coverInput.setVisit1("T");
						coverInput.setVisit2("Y");
						coverInput.setVisit3("T");
						coverInput.setVisit4("Y");
						coverInput.setPola("BeWeekly2");
					} else if (tpSls.getPola13().equalsIgnoreCase("Weekly")) {
						coverInput.setVisit1("Y");
						coverInput.setVisit2("Y");
						coverInput.setVisit3("Y");
						coverInput.setVisit4("Y");
						coverInput.setPola("Weekly");
					} else if (tpSls.getPola13().equalsIgnoreCase("Monthly1")) {
						coverInput.setVisit1("Y");
						coverInput.setVisit2("T");
						coverInput.setVisit3("T");
						coverInput.setVisit4("T");
						coverInput.setPola("Monthly1");
					} else if (tpSls.getPola13().equalsIgnoreCase("Monthly2")) {
						coverInput.setVisit1("T");
						coverInput.setVisit2("Y");
						coverInput.setVisit3("T");
						coverInput.setVisit4("T");
						coverInput.setPola("Monthly2");
					} else if (tpSls.getPola13().equalsIgnoreCase("Monthly3")) {
						coverInput.setVisit1("T");
						coverInput.setVisit2("T");
						coverInput.setVisit3("Y");
						coverInput.setVisit4("T");
						coverInput.setPola("Monthly3");
					} else if (tpSls.getPola13().equalsIgnoreCase("Monthly4")) {
						coverInput.setVisit1("Y");
						coverInput.setVisit2("T");
						coverInput.setVisit3("T");
						coverInput.setVisit4("Y");
						coverInput.setPola("Monthly4");
					}
					
					if (tpSls.getNorute14().length() == 0) {
						coverInput.setRoute("1");
					} else {
						coverInput.setRoute(tpSls.getNorute14());
					}
					dataCover.add(coverInput);
				}
			} else {
				message = "warn";
				logs.add("|"+tpSls.getOutlet2()+"|"+tpSls.getSalesman3()+"| "
						+ "Range Outlet Sales Cover Tidak Sesuai pada daftar outlet baris ke - "+tpSls.getNumber1());
			}
		}
		// download cover sales
		XCEK = true;
		for (UploadMasterOutletExcelOutletDto dO : p.getDataOutletExcel()) {
			String xstr = dO.getOutlet1();
			Integer hasil = xstr.length();
			if (hasil == 7) {
				Boolean masuk = true;
				//1.---CEK RANGE OUTLET
				if (dO.getGroupdivisi77().equalsIgnoreCase("M1")) {
					cekData = repoMasterOutletRepository.cekNoOutlet("M1", dO.getOutlet1());
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "No outlet M1 tidak sesuai dengan range outlet yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
				} else if (dO.getGroupdivisi77().equalsIgnoreCase("M2")) {
					cekData = repoMasterOutletRepository.cekNoOutlet("M2", dO.getOutlet1());
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "No outlet M2 tidak sesuai dengan range outlet yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
				} else if (dO.getGroupdivisi77().equalsIgnoreCase("M3")) {
					cekData = repoMasterOutletRepository.cekNoOutlet("M3", dO.getOutlet1());
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "No outlet M3 tidak sesuai dengan range outlet yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
				} else {
					cekData = repoMasterOutletRepository.cekNoOutlet("ALL", dO.getOutlet1());
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "No outlet ALL tidak sesuai dengan range outlet yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
				}
				//1.----------------CEK RANGE OUTLET
				
				if (masuk == true) {
					
					//2.CEK KODE OUTLET YANG SUDAH TERDAFTAR
					cekData = repoUtilRepository.getTableListObjectJoin("FCUSTMST a",
							"a.custno,(a.custname||'-'||a.custadd1||'-'||a.custadd2||'-'||a.ccity)Customer",
							" inner join "+t.getTenantId()+".ftable48 b on a.custno=b.custno  ", 
							" where a.custno ='"+dO.getOutlet1()+"' and b.groupdivisi='"+dO.getGroupdivisi77()+"' ");
					if (cekData.size() != 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Kode Outlet Sudah terdaftar, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//2.----------------------CEK KODE OUTLET YANG SUDAH TERDAFTAR
					
					//3.-- cek nama OUTLET YANG BLANK
					if (dO.getNamaoutlet2().length() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Nama Outlet Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//3.----------- cek nama OUTLET YANG BLANK
					
					//4.-- cek Kota YANG BLANK
					if (dO.getKota5().length() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data KOTA Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//4.----------- cek Kota YANG BLANK
					
					//5.-- cek Alamat YANG BLANK
					if (dO.getAlamat13().length() == 0 || dO.getAlamat13().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data ALAMAT 1 Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//5.----------- cek Alamat YANG BLANK
					
					if (xFlag.equals("1")) {
						//-- cek BARCODE YANG BLANK
						if (dO.getBarcode50().length() == 0 || dO.getBarcode50().equals("0") ) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data BARCODE Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//----------- cek FLAG PAJAK YANG BLANK
						
						if (XMASTERDATA.length() != 0) {
							//-----cek KOTA YANG BLANK
							if (dO.getAlamat13().length() == 0 || dO.getAlamat13().equals("0")) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data KOTA Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
							//-----cek KOTA YANG BLANK
						}
						
						//-- cek KODEPOS YANG BLANK
						if (dO.getPos6().length() == 0 || dO.getPos6().equals("0")) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data KODEPOS Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//-----------KODEPOS  YANG BLANK
						
						//-- cek Telp Toko YANG BLANK
						if (dO.getTelptoko8().length() == 0 || dO.getTelptoko8().equals("0")) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data TELP TOKO Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//----------- cek Telp Toko YANG BLANK
						
						//-- cek No. FAX YANG BLANK
						if (XMASTERDATA.length() == 0) {
							if (dO.getNofax9().length() == 0 || dO.getNofax9().equals("0")) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data NO FAX Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
						}
						//----------- cek No. FAX YANG BLANK
						
						//--CEk Nama Propinsi
						if (XMASTERDATA.length() != 0) {
							if (dO.getNamapropinsi70().length() == 0 || dO.getNamapropinsi70().equals("0")) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data NAMA PROPINSI Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
						}
						//--CEk Nama Propinsi
						
						//-- cek Industrial /outlet chain YANG BLANK
						if (dO.getIndustrial33().length() == 0 || dO.getIndustrial33().equals("0")) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Industrial /outlet chain Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//----------- cek Industrial /outlet chain YANG BLANK
						
						//-- cek Nama Pajak YANG BLANK
						if (dO.getNamapajak39().length() == 0 || dO.getNamapajak39().equals("0")) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data NAMA PAJAK Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//-- cek Alamat Pajak 1 YANG BLANK
						if (dO.getAlamatpajak140().length() == 0 || dO.getAlamatpajak140().equals("0")) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data ALAMAT PAJAK 1 Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//-- cek Alamat Pajak 2 YANG BLANK
						if (dO.getAlamatpajak241().length() == 0 || dO.getAlamatpajak241().equals("0")) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data ALAMAT PAJAK 2 Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//-- cek Kota Pajak YANG BLANK
						if (dO.getKotapajak42().length() == 0 || dO.getKotapajak42().equals("0")) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data KOTA PAJAK Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//-- cek FLAG PAJAK YANG BLANK
						if (dO.getFlagpajak43().length() == 0 || dO.getFlagpajak43().equals("0")) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data FLAG PAJAK Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						
						//Validasi NPWP
						if (dO.getNpwp44().length() < 15) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data NPWP Kurang dari 15 Digit, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						} else if (dO.getNpwp44().length() > 15) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data NPWP Lebih dari 15 Digit, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						} else {
							if (!dO.getNpwp44().matches("[0-9]+")) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data NPWP Harus Angka, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
						}
						//Validasi NPWP
						
						//Flag NPWP Blank
						if (XNPWPBLANK.length() == 0) {
							//-------------- NPWP ----------------//
							if (dO.getNpwp44().length() == 0 || dO.getNpwp44().equals("0")) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data NPWP Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
							//-------------- KTP ----------------//
							if (dO.getNpwp44().length() == 0 || dO.getNpwp44().length() < 15) {
								if (dO.getKtp84().length() == 0 || dO.getKtp84().length() < 16) {
									message = "warn"; XCEK = false;
									logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
											+ "Data NO KTP tidak sesuai, yang ada pada daftar outlet baris ke - "+dO.getNumber());
								}
							}
						} else {
							//-------------- KTP ----------------//
							if (dO.getKtp84().length() == 0 || dO.getKtp84().length() < 16) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data NO KTP tidak sesuai, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
						}
						//Flag NPWP Blank
						
						//-- cek Kontak Person YANG BLANK
						if (XMASTERDATA.length() != 0) {
							if (dO.getTittle59().length() == 0) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data Title/Contact Person Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
						}
						//----------- cek Kontak Person YANG BLANK
						
						//Cek Lokasi Blank
						if (dO.getLokasi22().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data LOKASI Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//Cek Lokasi Blank
						
						//Cek Sub Lokasi Blank
						if (dO.getSublokasi65().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data SUB LOKASI Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//Cek Sub Lokasi Blank
						
						//Cek Kode Pasar Blank
						if (dO.getKdpasar32().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data KODE PASAR Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//Cek Kode Pasar Blank
						
						//Cek Distrik Blank
						if (dO.getDistrik23().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data DISTRIK RAYON Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//Cek Distrik Blank
						
						//Cek Rute Blank
						if (dO.getRute29().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data RUTE Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//Cek Rute Blank
						
						//Cek Sub Rute Blank
						if (dO.getSubrute30().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data SUB RUTE Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//Cek Sub Rute Blank
						
						//Cek Lokasi Tidak Ada Di Master
						cekData = repoUtilRepository.getTableListObject("FLOCATION", "*", "WHERE LOCATION = '"+dO.getLokasi22()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Kode Lokasi tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						
						}
						//Cek Lokasi Tidak Ada Di Master
						
						//Cek Sub Lokasi Tidak Ada Di Master
						cekData = repoUtilRepository.getTableListObject("ftabel07", "*", "WHERE DATA2 = '"+dO.getSublokasi65()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Kode Sub Lokasi tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						
						}
						//Cek Sub Lokasi Tidak Ada Di Master
						
						//Cek Pasar Tidak Ada Di Master
						cekData = repoUtilRepository.getTableListObject("FKDPASAR", "*", "WHERE KDPSR = '"+dO.getKdpasar32()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Kode Pasar tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						
						}
						//Cek Pasar Tidak Ada Di Master
						
						//Cek Distrik Tidak Ada Di Master
						cekData = repoUtilRepository.getTableListObject("fdistrik", "*", "WHERE distrik = '"+dO.getDistrik23()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Kode Distrik tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						
						}
						//Cek Distrik Tidak Ada Di Master
						
						//Cek Rute Tidak Ada Di Master
						cekData = repoUtilRepository.getTableListObject("FBEATNEW", "*", "WHERE BEAT = '"+dO.getRute29()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Rute tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						
						}
						//Cek Rute Tidak Ada Di Master
						
						//Cek Sub Rute Tidak Ada Di Master
						cekData = repoUtilRepository.getTableListObject("FSBEATNEW", "*", "WHERE SBEAT = '"+dO.getSubrute30()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data SubRute tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						
						}
						//Cek Sub Rute Tidak Ada Di Master
					} else {
						if (dO.getAlamatkirim151().length() == 0 || dO.getAlamatkirim151().equals("0") ) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Alamat Kirim 1 Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//18A.--Flag Batas Limit kredit
						if (!dO.getFlagbataslimitkredit16().equalsIgnoreCase("Y")) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Flag bata limit kredit harus diisi 'Y', yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//18A.--Flag Batas Limit kredit
						//19.--Kontra BON
						if (dO.getKontrabon19().equalsIgnoreCase("T")) {
							
						} else if (dO.getKontrabon19().equalsIgnoreCase("Y")) {
							
						} else {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Kontra bon harus diisi, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//19.--Kontra BON
						
						//21.---CEK Kode Group Diskon Terdaftar
						if (dO.getGroupdiskon21().length() != 0) {
							cekData = repoUtilRepository.getTableListObject("FGDISC", "*", "where GDISC ='"+dO.getGroupdiskon21()+"'");
							if (cekData.size() == 0) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Grup diskon tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
						}
						//21.----------------------CEK Kode Group Diskon Terdaftar
						
						//6.-- cek LOKASI  YANG BLANK
						if (dO.getLokasi22().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data LOKASI Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//6.-- cek LOKASI  YANG BLANK
						
						//10.-- cek Kode Distrik
						if (dO.getDistrik23().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data DISTRIK RAYON Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//10.-- cek Kode Distrik
						
						//13.-- cek Kode Group Harga
						if (dO.getGroupharga26().length() != 0) {
							cekData = repoUtilRepository.getTableListObject("FGHARGA", "*", "where GHARGA ='"+dO.getGroupharga26()+"'");
							if (cekData.size() == 0) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Grup harga tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
						}
						//13.-- cek Kode Group Harga
						
						//11.-- cek Kode Rute
						if (dO.getRute29().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data RUTE Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//11.-- cek Kode Rute
						
						//12.-- cek Sub Rute
						if (dO.getSubrute30().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data SUB RUTE Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//12.-- cek Sub Rute
						
						//9.-- cek Kode Pasar
						if (dO.getKdpasar32().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data KODE PASAR Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//9.-- cek Kode Pasar
						
						//26.----CEK KODE PASAR yang ada di database
						cekData = repoUtilRepository.getTableListObject("FKDPASAR", "*", "WHERE KDPSR = '"+dO.getKdpasar32()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Kode Pasar tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						
						}
						//26.----CEK KODE PASAR yang ada di database
						
						//12.-- cek Industrial Key /Outlet Chain
						if (dO.getIndustrial33().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Industrial /outlet chain Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//12.-- cek Industrial Key /Outlet Chain
						
						//18.--Flag Limit kredit group
						if (dO.getFlaglimitkreditgroup63().equalsIgnoreCase("T")) {
							
						} else if (dO.getFlaglimitkreditgroup63().equalsIgnoreCase("T")) {
							
						} else if (dO.getFlaglimitkreditgroup63().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Flag limit kredit Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						} else {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Flag Limit Kredit Group <>T/Y, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//18.--Flag Limit kredit group
						
						//8.-- cek Sub Lokasi YANG BLANK
						if (dO.getSublokasi65().length() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data SUB LOKASI Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						//8.-- cek Sub Lokasi YANG BLANK
						
						//27.----CEK LOKASI  yang ada di database
						cekData = repoUtilRepository.getTableListObject("FLOCATION", "*", "WHERE LOCATION = '"+dO.getLokasi22()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Kode Lokasi tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						
						}
						//27.----CEK LOKASI  yang ada di database
						
						//28.---CEK Sub LOKASI  yang ada di database
						cekData = repoUtilRepository.getTableListObject("ftabel07", "*", "WHERE data1 ='"+dO.getLokasi22()+"' AND DATA2 = '"+dO.getSublokasi65()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Kode Sub Lokasi tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						
						}
						//28.---CEK Sub LOKASI  yang ada di database
					}
					
					//------- cek panjang kode pos
					if (dO.getPos6().length() != 0) {
						if (dO.getPos6().length() > 5) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Kode Pos lebih dari 5 Digit, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						
						try {
							Integer ANGKA = Integer.valueOf(dO.getPos6()) * 1 ;
						} catch (Exception e) {
							// TODO: handle exception
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Kode Pos Tidak boleh huruf, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
					}
					
					//21.--CEk Kontak Person
					if (dO.getContakperson7().length() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data Kontak Person Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//21.--CEk Kontak Person
					
					//15.--cek TOP
					if (dO.getTop14().length() == 0 || dO.getTop14().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data TOP Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//15.--cek TOP
					
					//14.--cek kredit limit
					if (dO.getKriditlimit15().length() == 0 || dO.getKriditlimit15().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data Kredit Limit Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//14.--cek kredit limit
					
					//20.--Jenis Pembayaran Faktur
					if (dO.getJenispembayaranfaktur17().equalsIgnoreCase("K")) {
						
					} else if (dO.getJenispembayaranfaktur17().equalsIgnoreCase("T")) {
						
					} else if (dO.getJenispembayaranfaktur17().equalsIgnoreCase("G")) {
						
					} else {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Tipe pembayaran faktur harus diisi atau tidak sesuai, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//20.--Jenis Pembayaran Faktur
					
					//18.--CEk Flag Outlet aktif
					if (dO.getStatusaktifoutlet18().equalsIgnoreCase("O")) {
						
					} else if (dO.getStatusaktifoutlet18().equalsIgnoreCase("N")) {
						
					} else if (dO.getStatusaktifoutlet18().equalsIgnoreCase("C")) {
						
					} else {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Status Aktif Outlet/O/C/N Salah, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//18.--CEk Flag Outlet aktif
					
					//16.--cek Bangungan
					if (dO.getBangunan20().equals("1")) {
						
					} else if (dO.getBangunan20().equals("2")) {
						
					} else if (dO.getBangunan20().equals("3")) {
						
					} else {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data BANGUNAN Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//16.--cek Bangungan
					
					//21A.--CEk Kode Channel 
					if (dO.getChanel24().length() == 0 || dO.getChanel24().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data CHANNEL Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//21A.--CEk Kode Chanel
					
					//25.--CEk Kode Group Outlet
					if (dO.getGroupoutlet25().length() == 0 || dO.getGroupoutlet25().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data GRUP OUTLET Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//25.--CEk Kode Group Outlet
					
					//25a--CEK Kode Group Outlet Terdaftar 
					cekData = repoUtilRepository.getTableListObject("FGRUPOUT", "*", "where grupout = '"+dO.getGroupoutlet25()+"' ");
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "GRUP OUTLET Tidak terdaftar di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//25a--CEK Kode Group Outlet Terdaftar 
					
					//21B.--CEk Kode Class Klasifikasi
					if (dO.getKlasifikasi31().length() == 0 || dO.getKlasifikasi31().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data KLASIFIKASI Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//21B.--CEk Kode Class Klasifikasi
					
					//17.--cek Outlet Type
					if (dO.getTipeoutlet68().length() == 0 || dO.getTipeoutlet68().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data TIPE OUTLET Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//17.--cek Outlet Type
					
					//22.--CEk Kode propinsi
					if (dO.getPemerintahan69().length() == 0 || dO.getPemerintahan69().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data PROPINSI Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//22.--CEk Kode propinsi
					
					//23.--CEk Kode Kabupaten
					if (dO.getKabupaten71().length() == 0 || dO.getKabupaten71().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data KABUPATEN Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//23.--CEk Kode Kabupaten
					
					//24.--CEk Nama Kabupaten
					if (dO.getNamakabupaten72().length() == 0 || dO.getNamakabupaten72().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data NAMA KABUPATEN Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//24.--CEk Nama Kabupaten
					
					//25.--CEk Kode Kecamatan
					if (dO.getKecamatan73().length() == 0 || dO.getKecamatan73().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data KECAMATAN Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//25.--CEk Kode Kecamatan
					
					//25.--CEk Nama Kecamatan
					if (dO.getNamakecamatan74().length() == 0 || dO.getNamakecamatan74().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data NAMA KECAMATAN Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//25.--CEk Nama Kecamatan
					
					//26.--CEk Kode Kelurahan
					if (dO.getKelurahan75().length() == 0 || dO.getKelurahan75().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data KELURAHAN Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//26.--CEk Kode Kelurahan
					
					//27.--CEk Nama Kelurahan
					if (dO.getNamakelurahan76().length() == 0 || dO.getNamakelurahan76().equals("0")) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Data NAMA KELURAHAN Blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//27.--CEk Nama Kelurahan
					
					if (dO.getGroupdivisi77().equalsIgnoreCase("M1")) {
						
					} else if (dO.getGroupdivisi77().equalsIgnoreCase("M2")) {
						
					} else if (dO.getGroupdivisi77().equalsIgnoreCase("M3")) {
						
					} else if (dO.getGroupdivisi77().equalsIgnoreCase("ALL")) {
						
					} else {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Grup Divisi <> M1/M2/M3/ALL, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					
					//34.---CEK Type Outlet
					cekData = repoUtilRepository.getTableListObject("FTYPEOUT", "*", "where type = '"+dO.getChanel24()+"' ");
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "KODE CHANNEL Tidak terdaftar di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//34.---CEK Type Outlet
					
					//35.---CEK Type Class
					cekData = repoUtilRepository.getTableListObject("FCLASSNEW", "*", "where type = '"+dO.getChanel24()+"' AND ctype = '"+dO.getKlasifikasi31()+"' ");
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "KODE KLASIFIKASI Tidak terdaftar di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//35.---CEK Type Class
					
					//36.---CEK data Industri
					cekData = repoUtilRepository.getTableListObject("FINDUSTRI", "*", "where KDIND = '"+dO.getIndustrial33()+"' ");
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "KODE INDUSTRI Tidak terdaftar di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//36.---CEK data Industri
					if (dO.getTipeoutlet68().length() != 0) {
						cekData = repoUtilRepository.getTableListObject("ftable49", "*", "where short_code = '"+dO.getTipeoutlet68()+"' ");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Type Outlet tidak sesuai atau blank, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
					}
					
					//29.---CEK Sub kode PROPINSI  yang ada di database
					cekData = repoUtilRepository.getTableListObject("fcshir11", "*", "where t11 = '"+dO.getPemerintahan69()+"' ");
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "KODE PROPINSI Tidak terdaftar di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//29.---CEK Sub kode PROPINSI  yang ada di database
					
					//30.---CEK Sub kode Kabupaten  yang ada di database
					cekData = repoUtilRepository.getTableListObject("fcshir12", "*", "where t11 = '"+dO.getPemerintahan69()+"' AND t12 = '"+dO.getKabupaten71()+"' ");
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "KODE KABUPATEN Tidak terdaftar di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//30.---CEK Sub kode Kabupaten  yang ada di database
					
					//32.---CEK Sub kode Kecamatan  yang ada di database
					cekData = repoUtilRepository.getTableListObject("fcshir13", "*", "where t11 = '"+dO.getPemerintahan69()+"' AND t12 = '"+dO.getKabupaten71()+"' "
							+ " AND t13='"+dO.getKecamatan73()+"' ");
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "KODE KECAMATAN Tidak terdaftar di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//32.---CEK Sub kode Kecamatan  yang ada di database
					
					//33.---CEK Sub kode Kelurahan  yang ada di database
					cekData = repoUtilRepository.getTableListObject("fcshir14", "*", "where t11 = '"+dO.getPemerintahan69()+"' AND t12 = '"+dO.getKabupaten71()+"' "
							+ " AND t13='"+dO.getKecamatan73()+"' AND t14 = '"+dO.getKelurahan75()+"' ");
					if (cekData.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "KODE KELURAHAN Tidak terdaftar di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					}
					//33.---CEK Sub kode Kelurahan  yang ada di database
					
					//36.----------------------Cek Data Group Harga
					if (xFlag.equals("1")) {
						if (dO.getDistrik23().length() != 0) {
							cekData = repoUtilRepository.getTableListObject("fdistrik", "*", "WHERE distrik = '"+dO.getDistrik23()+"'");
							if (cekData.size() == 0) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data Kode Distrik tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
							cekData = repoUtilRepository.getTableListObject("FBEATNEW", "*", "WHERE distrik ='"+dO.getDistrik23()+"' AND BEAT = '"+dO.getRute29()+"'");
							if (cekData.size() == 0) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data Rute tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
							cekData = repoUtilRepository.getTableListObject("FSBEATNEW", "*", "WHERE distrik ='"+dO.getDistrik23()+"' AND BEAT = '"+dO.getRute29()+"' AND SBEAT = '"+dO.getSubrute30()+"'");
							if (cekData.size() == 0) {
								message = "warn"; XCEK = false;
								logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
										+ "Data SubRute tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
							}
						}
					} else {
						cekData = repoUtilRepository.getTableListObject("fdistrik", "*", "WHERE distrik = '"+dO.getDistrik23()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Kode Distrik tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						cekData = repoUtilRepository.getTableListObject("FBEATNEW", "*", "WHERE distrik ='"+dO.getDistrik23()+"' AND BEAT = '"+dO.getRute29()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data Rute tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
						cekData = repoUtilRepository.getTableListObject("FSBEATNEW", "*", "WHERE distrik ='"+dO.getDistrik23()+"' AND BEAT = '"+dO.getRute29()+"' AND SBEAT = '"+dO.getSubrute30()+"'");
						if (cekData.size() == 0) {
							message = "warn"; XCEK = false;
							logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
									+ "Data SubRute tidak ada di master, yang ada pada daftar outlet baris ke - "+dO.getNumber());
						}
					}
					//36.----------------------Cek Data Group Harga
					List<UploadMasterOutletCoverDto> listTampungSales = dataCover.stream()
			                .filter(dCover -> dCover.getCustno().equals(dO.getOutlet1()))
			                .collect(Collectors.toList());
					System.out.println("TAMPUNGAN :"+listTampungSales.size());
					if (listTampungSales.size() == 0) {
						message = "warn"; XCEK = false;
						logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
								+ "Sales cover tidak ada, yang ada pada daftar outlet baris ke - "+dO.getNumber());
					} else {
						for (UploadMasterOutletCoverDto lTS : listTampungSales) {
							if (dO.getTf80().length() != 0) {
								if (dO.getTf80().equalsIgnoreCase("*")) {
									cekData = repoUtilRepository.getTableListObject("fsls", "*",
											"WHERE slsno ='"+lTS.getSlsno()+"' and data04 in (select memostring from "+t.getTenantId()+".ftable13 where xkey = 'SLSFORCE_TF'  ");
									if (cekData.size() == 0) {
										message = "warn"; XCEK = false;
										logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
												+ "Outlet Taskforce Tetapi bukan Salesman Taskforce :" + lTS.getSlsno());
									}
								} else {

								}
							} else {

							}
						}
					}
					
					if (XCEK == true) {
						UploadMasterOutletOutletDto outletInput = new UploadMasterOutletOutletDto();
						outletInput.setCustno(dO.getOutlet1().toUpperCase());
						outletInput.setCustname(dO.getNamaoutlet2().toUpperCase());
						
						if (xFlag.equals("1")) {
							if (dO.getAlamat24().length() == 0) {
								outletInput.setCustadd2("");
							} else {
								outletInput.setCustadd2(dO.getAlamat24().toUpperCase());
							}
						} else {
							outletInput.setCustadd2(dO.getAlamat24().toUpperCase());
						}
						
						outletInput.setCustadd1(dO.getAlamat13().toUpperCase());
						outletInput.setCcity(dO.getKota5().toUpperCase());
						outletInput.setCkdpos(dO.getPos6().toUpperCase());
						outletInput.setCcontact(dO.getContakperson7().toUpperCase());
						outletInput.setCphone1(dO.getTelptoko8().toUpperCase());
						outletInput.setCfaxno(dO.getNofax9().toUpperCase());
						
						if (xFlag.equals("1")) {
							if (dO.getAlamatrumah110().length() == 0) {
								outletInput.setCresd1("");
							} else {
								outletInput.setCresd1(dO.getAlamatrumah110().toUpperCase());
							}
						} else {
							outletInput.setCresd1(dO.getAlamatrumah110().toUpperCase());
						}
						
						if (xFlag.equals("1")) {
							if (dO.getAlamatrumah211().length() == 0) {
								outletInput.setCresd2("");
							} else {
								outletInput.setCresd2(dO.getAlamatrumah211().toUpperCase());
							}
						} else {
							outletInput.setCresd2(dO.getAlamatrumah211().toUpperCase());
						}
						
						if (xFlag.equals("1")) {
							if (dO.getTelprumah13().length() == 0) {
								outletInput.setCphone2("");
							} else {
								outletInput.setCphone2(dO.getTelprumah13().toUpperCase());
							}
						} else {
							outletInput.setCphone2(dO.getTelprumah13().toUpperCase());
						}
						
						outletInput.setCterm(dO.getTop14().toUpperCase());
						outletInput.setClimit(dO.getKriditlimit15().toUpperCase());
						
						if (xFlag.equals("1")) {
							if (dO.getFlagbataslimitkredit16().length() == 0) {
								outletInput.setFlaglimit("Y");
							} else {
								outletInput.setFlaglimit(dO.getFlagbataslimitkredit16().toUpperCase());
							}
						} else {
							outletInput.setFlaglimit(dO.getFlagbataslimitkredit16().toUpperCase());
						}
						
						outletInput.setFlagpay(dO.getJenispembayaranfaktur17().toUpperCase());
						
						if (xFlag.equals("1")) {
							if (dO.getKontrabon19().length() == 0) {
								outletInput.setFlagkbon("T");
							} else {
								outletInput.setFlagkbon(dO.getKontrabon19().toUpperCase());
							}
						} else {
							outletInput.setFlagkbon(dO.getKontrabon19().toUpperCase());
						}
						
						outletInput.setFlaghome(dO.getBangunan20().toUpperCase());
						
						if (xFlag.equals("1")) {
							if (dO.getGroupdiskon21().length() == 0) {
								outletInput.setGdisc("99");
							} else {
								outletInput.setGdisc(dO.getGroupdiskon21().toUpperCase());
							}
						} else {
							outletInput.setGdisc(dO.getGroupdiskon21().toUpperCase());
						}
						
						if (xFlag.equals("1")) {
							if (dO.getLokasi22().length() == 0) {
								outletInput.setLocation("10");
							} else {
								outletInput.setLocation(dO.getLokasi22().toUpperCase());
							}
							if (dO.getDistrik23().length() == 0) {
								outletInput.setDistrik("03");
							} else {
								outletInput.setDistrik(dO.getDistrik23().toUpperCase());
							}
						} else {
							outletInput.setLocation(dO.getLokasi22().toUpperCase());
							outletInput.setDistrik(dO.getDistrik23().toUpperCase());
						}
						
						outletInput.setTypeout(dO.getChanel24().toUpperCase());
						outletInput.setGrupout(dO.getGroupoutlet25().toUpperCase());
						
						if (XMASTERDATA.equals("1")) {
							if (dO.getGroupharga26().length() == 0) {
								outletInput.setGharga(XGROUPHARGA);
							} else {
								outletInput.setGharga(dO.getGroupharga26().toUpperCase());
							}
						} else {
							outletInput.setGharga(dO.getGroupharga26().toUpperCase());
						}
						
						if (dO.getGroupplu27().length() == 0) {
							outletInput.setGplu("");
						} else {
							outletInput.setGplu(dO.getGroupplu27().toUpperCase());
						}
						
						if (dO.getGroupkonversi28().length() == 0) {
							outletInput.setGkonv("");
						} else {
							outletInput.setGkonv(dO.getGroupkonversi28().toUpperCase());
						}
						
						if (xFlag.equals("1")) {
							if (dO.getRute29().length() == 0) {
								outletInput.setBeat("01");
							} else {
								outletInput.setBeat(dO.getRute29().toUpperCase());
							}
							
							if (dO.getSubrute30().length() == 0) {
								outletInput.setSbeat("01");
							} else {
								outletInput.setSbeat(dO.getSubrute30().toUpperCase());
							}

							if (dO.getKlasifikasi31().length() == 0) {
								outletInput.setClasss("ZZ");
							} else {
								outletInput.setClasss(dO.getKlasifikasi31().toUpperCase());
							}
							
							if (dO.getKdpasar32().length() == 0) {
								outletInput.setKdpsr("9999");
							} else {
								outletInput.setKdpsr(dO.getKdpasar32().toUpperCase());
							}
							
						} else {
							outletInput.setBeat(dO.getRute29().toUpperCase());
							outletInput.setSbeat(dO.getSubrute30().toUpperCase());
							outletInput.setClasss(dO.getKlasifikasi31().toUpperCase());
							outletInput.setKdpsr(dO.getKdpasar32().toUpperCase());
						}
						
						outletInput.setKdind(dO.getIndustrial33().toUpperCase());
						
						if (xFlag.equals("1")) {
							if (dO.getKdpasar32().length() == 0) {
								outletInput.setCweekno("1");
							} else {
								if (dO.getAwalpekanpenjualan34().length() == 0 || dO.getAwalpekanpenjualan34().equals("0")) {
									outletInput.setCweekno(xweek);
								} else {
									outletInput.setCweekno(dO.getAwalpekanpenjualan34().toUpperCase());
								}
							}
						} else {
							if (dO.getAwalpekanpenjualan34().length() == 0 || dO.getAwalpekanpenjualan34().equals("0")) {
								outletInput.setCweekno(xweek);
							} else {
								outletInput.setCweekno(dO.getAwalpekanpenjualan34().toUpperCase());
							}
						}
						
						outletInput.setCweekno(xweek);
						outletInput.setClastdate(dO.getTgltransaksiterakhir35().toUpperCase());
						outletInput.setFirstdate(dO.getTgltransaksipertama36().toUpperCase());
						outletInput.setFirstopen(dO.getTglbuka37().toUpperCase());
						outletInput.setClastupd(xtanggal);
						outletInput.setRegdate(xtanggal);
						
						outletInput.setTaxname(dO.getNamapajak39().toUpperCase());
						outletInput.setTaxadd1(dO.getAlamatpajak140().toUpperCase());
						outletInput.setTaxadd2(dO.getAlamatpajak241().toUpperCase());
						outletInput.setTaxcity(dO.getKotapajak42().toUpperCase());
						outletInput.setTaxflag(dO.getFlagpajak43().toUpperCase());
						
						outletInput.setNpwp(dO.getNpwp44().toUpperCase());
						
						outletInput.setRata(dO.getRatapkn45().toUpperCase());
						outletInput.setBank(dO.getBank46().toUpperCase());
						outletInput.setNoacc(dO.getNoaccount47().toUpperCase());
						outletInput.setNorefsup(dO.getReffsub48().toUpperCase());
						outletInput.setAgenlain(dO.getAgen49().toUpperCase());
						outletInput.setTotalar("0");
						outletInput.setBrs("0");
						outletInput.setFlagsbd("0");
						outletInput.setData01(dO.getBarcode50().toUpperCase());
						outletInput.setData02(dO.getAlamatkirim151().toUpperCase());
						outletInput.setData03(dO.getAlamatkirim252().toUpperCase());
						outletInput.setData04(dO.getAlamatkirim353().toUpperCase());
						outletInput.setData05(dO.getEmail54().toUpperCase());
						outletInput.setData06(dO.getAlias55().toUpperCase());
						outletInput.setData07(dO.getBankgaransi56().toUpperCase());
						outletInput.setData08(dO.getValgransi57().toUpperCase());
						outletInput.setData09(dO.getTglbg58().toUpperCase());
						outletInput.setData10(dO.getAlamatrumah110().toUpperCase());
						
						if (xFlag.equals("1")) {
							if (dO.getNorute60().length() == 0) {
								outletInput.setData11("10");
							} else {
								outletInput.setData11(dO.getNorute60().toUpperCase());
							}
						} else {
							outletInput.setData11(dO.getNorute60().toUpperCase());
						}
						
						if (xFlag.equals("1")) {
							if (dO.getLastchanel61().length() == 0) {
								outletInput.setData12("101");
							} else {
								outletInput.setData12(dO.getLastchanel61().toUpperCase());
							}
						} else {
							outletInput.setData12(dO.getLastchanel61().toUpperCase());
						}
						
						if (xFlag.equals("1")) {
							if (dO.getLastklasifikasichanel62().length() == 0) {
								outletInput.setData13("ZZ");
							} else {
								outletInput.setData13(dO.getLastklasifikasichanel62().toUpperCase());
							}
							
							if (dO.getFlaglimitkreditgroup63().length() == 0) {
								outletInput.setData14("T");
							} else {
								outletInput.setData14(dO.getFlaglimitkreditgroup63().toUpperCase());
							}

							if (dO.getSpesifikasioutlet64().length() == 0) {
								outletInput.setData15("T");
							} else {
								outletInput.setData15(dO.getSpesifikasioutlet64().toUpperCase());
							}

							if (dO.getSublokasi65().length() == 0) {
								outletInput.setData16("T");
							} else {
								outletInput.setData16(dO.getSublokasi65().toUpperCase());
							}
							
						} else {
							outletInput.setData13(dO.getLastklasifikasichanel62().toUpperCase());
							outletInput.setData14(dO.getFlaglimitkreditgroup63().toUpperCase());
							outletInput.setData15(dO.getSpesifikasioutlet64().toUpperCase());
							outletInput.setData16(dO.getSublokasi65().toUpperCase());
						}
						
						outletInput.setData17(dO.getGroupoutlet166().toUpperCase());
						outletInput.setData18(dO.getGroupoutlet267().toUpperCase());
						
						if (xFlag.equals("1")) {
							if (dO.getTipeoutlet68().length() == 0) {
								outletInput.setData20("CF+HF");
							} else {
								outletInput.setData20(dO.getTipeoutlet68().toUpperCase());
							}
						} else {
							outletInput.setData20(dO.getTipeoutlet68().toUpperCase());
						}
						
						outletInput.setProv(dO.getPemerintahan69().toUpperCase());
						outletInput.setNamaprov(dO.getNamapropinsi70().toUpperCase());
						outletInput.setKab(dO.getKabupaten71().toUpperCase());
						outletInput.setNamakab(dO.getNamakabupaten72().toUpperCase());
						outletInput.setKec(dO.getKecamatan73().toUpperCase());
						outletInput.setNamakec(dO.getNamakecamatan74().toUpperCase());
						outletInput.setKel(dO.getKelurahan75().toUpperCase());
						outletInput.setNamakel(dO.getNamakelurahan76().toUpperCase());
						outletInput.setFlagout(dO.getStatusaktifoutlet18().toUpperCase());
						outletInput.setGroupdivisi(dO.getGroupdivisi77().toUpperCase());
						
						if (xFlag.equals("1")) {
							if (dO.getTypecost78().length() == 0) {
								outletInput.setTypecost("1");
							} else {
								outletInput.setTypecost(dO.getTypecost78().toUpperCase());
							}
							if (dO.getCost79().length() == 0) {
								outletInput.setTypecost("0");
							} else {
								outletInput.setTypecost(dO.getCost79().toUpperCase());
							}
							
						} else {
							outletInput.setTypecost(dO.getTypecost78().toUpperCase());
							if (dO.getTypecost78().equals("1")) {
								outletInput.setTypecost("0");
							} else {
								outletInput.setTypecost(dO.getCost79().toUpperCase());
							}
							outletInput.setTypecost(dO.getTf80().toUpperCase());
						}
						
						if (xFlag.equals("1")) {
							if (dO.getQtykarton81().length() == 0) {
								outletInput.setBykrt("");
							} else {
								if (dO.getQtykarton81().toUpperCase().equalsIgnoreCase("Y")) {
									outletInput.setBykrt("*");
								} else {
									outletInput.setBykrt("");
								}
							}
						} else {
							if (dO.getQtykarton81().toUpperCase().equalsIgnoreCase("Y")) {
								outletInput.setBykrt("*");
							} else {
								outletInput.setBykrt("");
							}
						}
						
						if (dO.getPayer82().length() != 0) {
							cekData = repoUtilRepository.getTableListObject("fcustmst", "*", "WHERE CUSTNO = '"+dO.getPayer82()+"'");
							if (cekData.size() == 0) {
								outletInput.setGrouppayer(dO.getPayer82().toUpperCase());
								outletInput.setGrouppayertemp("");
							} else {
								outletInput.setGrouppayer(dO.getPayer82().toUpperCase());
								outletInput.setGrouppayertemp(dO.getPayer82().toUpperCase());
							}
						} else {
							cekData = repoUtilRepository.getTableListObject("fcustmst", "*", "WHERE CUSTNO = '"+dO.getOutlet1()+"'");
							if (cekData.size() == 0) {
								outletInput.setGrouppayer(dO.getOutlet1().toUpperCase());
								outletInput.setGrouppayertemp("");
							} else {
								outletInput.setGrouppayer(dO.getOutlet1().toUpperCase());
								outletInput.setGrouppayertemp(dO.getOutlet1().toUpperCase());
							}
						}
						
						if (dO.getChiller83().length() == 0) {
							outletInput.setChiller("0");
						} else {
							outletInput.setChiller(dO.getChiller83().toUpperCase());
						}
						
						outletInput.setNoktp(dO.getKtp84().toUpperCase());
						
						dataOutlet.add(outletInput);
					}
				}
				
			} else {
				message = "warn";
				logs.add("|"+dO.getOutlet1()+"|"+dO.getNamaoutlet2()+"|"+dO.getAlamat13()+"| "
						+ " Range Outlet Tidak Sesuai pada daftar outlet baris ke - "+dO.getNumber());
			
			}
		}
		
		return new UploadMasterOutletDto(dataOutlet, dataCover, p.getDataOutletExcel(), p.getDataCoverExcel(), logs, message, user);
	}

	@Override
	@Transactional
	public MessageDto process(UploadMasterOutletDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		List<Object[]> getData = new ArrayList<>();
		String Xflagsubdist = repoUtilRepository.getTableString("FMEMO", "MEMOSTRING", "WHERE MEMONAMA='TIPEDIST'");
		if (p.getDataOutlet().size() != 0) {
			for (UploadMasterOutletOutletDto dO : p.getDataOutlet()) {
				String xbarcode = generateBarcode(dO.getCustno());
				repoMasterOutletRepository.insertFucstmst(dO, xbarcode, Xflagsubdist);
				
				getData = repoUtilRepository.getTableListObject("fcustud", "*", "where custno = '"+dO.getCustno()+"'");
				if (getData.size() == 0) {
					repoUtilRepository.insertData("fcustud", "CUSTNO,H11,H12,H13,H14",
							" '"+dO.getCustno()+"', '"+dO.getProv()+"', '"+dO.getKab()+"', '"+dO.getKec()+"', '"+dO.getKel()+"' ");
				}
				
				List<Object[]> data1 = repoUtilRepository.getTableListObject("ftable49", "OUTTYPE, OUTTYPE as b", "where short_code = '"+dO.getData20()+"'");
				if (data1.size() != 0) {
					for (Object[] dt1 : data1) {
						List<Object[]> data2 = repoUtilRepository.getTableListObject("ftable48", "groupdivisi,bykrt,grouppayer,chiller", "where custno = '"+dO.getCustno()+"'");
						if (data2.size() != 0) {
							for (Object[] dt2 : data2) {
								repoUtilRepository.updateData("ftable48", "set OUTTYPE ='"+Objects.toString(dt1[0], "")+"',"
										+ " groupdivisi ='"+Objects.toString(dt2[0], "")+"' , "
										+ " bykrt ='"+Objects.toString(dt2[1], "")+"' , "
										+ " grouppayer ='"+Objects.toString(dt2[2], "")+"' , "
										+ " chiller ='"+Objects.toString(dt2[3], "")+"' , "
										+ " CUSTNO_OLD= '"+dO.getCustnoOld()+"', "
										+ " NOKTP = '"+dO.getNoktp()+"' ",
										"where custno = '"+dO.getCustno()+"'");
							}
						} else {
							repoUtilRepository.insertData("ftable48",
									"CUSTNO,OUTTYPE,GROUPDIVISI,TYPECOST,COST,TF,BYKRT,GROUPPAYER,CHILLER,CUSTNO_OLD,NOKTP",
									" '"+dO.getCustno()+"', '"+Objects.toString(dt1[0], "")+"', '"+dO.getGroupdivisi()+"',"
									+ " '"+dO.getTypecost()+"', '"+dO.getCost()+"', '"+dO.getTf()+"', '"+dO.getBykrt()+"', "
									+ " '"+dO.getGrouppayer()+"', '"+dO.getChiller()+"', '"+dO.getCustnoOld()+"', '"+dO.getNoktp()+"' ");
						}
					}
				}
				
				getData = repoUtilRepository.getTableListObject("Ftabel64", "*", "where data2 = '"+dO.getCustno()+"'");
				if (getData.size() == 0) {
					repoUtilRepository.insertData("Ftabel64", "data1,data2,data3,data4,data5",
							" '100', '"+dO.getCustno()+"', '"+dO.getClimit()+"', '0', '0'  ");
				}
				
			}
			
			for (UploadMasterOutletCoverDto dC : p.getDataCover()) {
				repoMasterOutletRepository.insertFcustsls(dC);
			}
			
			message = new MessageDto("success", "Success Message", "Proses Selesai, proses input berhasil "+p.getDataOutlet().size()+" Outlet!");
		} else {
			message = new MessageDto("warn", "Warning Message", "Tidak ada data yang diproses!");
		}
		
		return message;
	}
	
	private String generateBarcode(String custNo) {
		String xbarcode = "";
		String xoutlet = custNo;
		
		Double xkali = 0.0;
		Double xtambah = 0.0;
		xkali = Double.valueOf(xoutlet.substring(0, 1))*8 + Double.valueOf(xoutlet.substring(1, 2))*7 + Double.valueOf(xoutlet.substring(2, 3))*6 
				+ Double.valueOf(xoutlet.substring(3, 4))*5 + Double.valueOf(xoutlet.substring(4, 5))*4 + Double.valueOf(xoutlet.substring(5, 6))*3
				+ Double.valueOf(xoutlet.substring(6, 7))*2;
		xkali = xkali - (Math.floor(xkali/11)*11);
		if (xkali == 0) {
			xtambah = 1.0;
		} else if (xkali == 1) {
			xtambah = 0.0;
		} else {
			xtambah = 11 - (xkali-(Math.floor(xkali/11)*11));
		}
		xbarcode = xoutlet.substring(0, 3) + xtambah.intValue() + xoutlet.substring(3, 6);
		return xbarcode;
	};

}
