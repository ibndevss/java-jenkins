package myor.matrix.master.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.GroupPayerBrowseDto;
import myor.matrix.master.entity.MasterOutletBrowseDto;
import myor.matrix.master.entity.MasterOutletDataAttributeDto;
import myor.matrix.master.entity.MasterOutletDataCoverDto;
import myor.matrix.master.entity.MasterOutletDataDivisiDto;
import myor.matrix.master.entity.MasterOutletDataPajakDto;
import myor.matrix.master.entity.MasterOutletDataPemerintahanDto;
import myor.matrix.master.entity.MasterOutletDataProfileDto;
import myor.matrix.master.entity.MasterOutletDto;
import myor.matrix.master.entity.MasterOutletFormShowDto;
import myor.matrix.master.entity.MasterOutletSaveDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.MasterOutletRepository;
import myor.matrix.master.service.MasterOutletService;
import myor.matrix.master.tenant.TenantContext;

@Service
public class MasterOutletServiceImpl implements MasterOutletService {

	@Autowired
	private MasterOutletRepository repoMOutlet;

	@Autowired
	private TenantContext t;

	@Override
	public String[] getSelectDivisi(String groupDivisi) {
		// TODO Auto-generated method stub
		
		List<Object[]> Ftable13AwalAkhir = repoMOutlet.getFtable13AwalAkhir(groupDivisi);
		String mlawal = "";
		String mlakhir = "";
		for (Object[] o : Ftable13AwalAkhir) {
			mlawal = Objects.toString((String)o[0], "");
			mlakhir = Objects.toString((String)o[1], "");
		}
		System.out.println(mlawal + " - " + mlakhir + " - " + groupDivisi);
		if (mlawal.equalsIgnoreCase("") || mlakhir.equalsIgnoreCase("")) {
			if (groupDivisi.equalsIgnoreCase("M1")) {
				String[] message = {"warn","Range nomor outlet M1 bermasalah, cek kembali.",""};
				return message;
			} else if (groupDivisi.equalsIgnoreCase("M2")) {
				String[] message = {"warn","Range nomor outlet M2 bermasalah, cek kembali.",""};
				return message;
			} else if (groupDivisi.equalsIgnoreCase("M3")) {
				String[] message = {"warn","Range nomor outlet M3 bermasalah, cek kembali.",""};
				return message;
			} else if (groupDivisi.equalsIgnoreCase("ALL")) {
				String[] message = {"warn","Range nomor outlet ALL bermasalah, cek kembali.",""};
				return message;
			} 
		} else {
			String XCUSTNO = Objects.toString(repoMOutlet.getFtable13XCustNo(groupDivisi), "");
			if (XCUSTNO.equalsIgnoreCase("Y")) {
				List<Object[]> Ftable48 = repoMOutlet.getFtable48(groupDivisi);
				if (Ftable48.size() > 0) {
					String[] message = {"success","",""};
					return message;
				} else {
					String[] message = {"success","","kosong"};
					return message;
				}
			} else {
				if (groupDivisi.equalsIgnoreCase("M1")) {
					String[] message = {"warn","Tidak diijinkan untuk group divisi M1,cek setingan aplikasi.",""};
					return message;
				} else if (groupDivisi.equalsIgnoreCase("M2")) {
					String[] message = {"warn","Tidak diijinkan untuk group divisi M2,cek setingan aplikasi.",""};
					return message;
				} else if (groupDivisi.equalsIgnoreCase("M3")) {
					String[] message = {"warn","Tidak diijinkan untuk group divisi M3,cek setingan aplikasi.",""};
					return message;
				} else if (groupDivisi.equalsIgnoreCase("ALL")) {
					String[] message = {"warn","Tidak diijinkan untuk group divisi Semua,cek setingan aplikasi.",""};
					return message;
				} 
			}
		}
		String[] message = {"success","",""};
		return message;
	}

	@Override
	public MasterOutletFormShowDto getFormShow() {
		// TODO Auto-generated method stub
		String XMASTERDATA = Objects.toString(repoMOutlet.getXkeyString("memostring", "ftable13", "xkey = 'XMASTERDATA'"), "");
		String XNPWPBLANK = Objects.toString(repoMOutlet.getXkeyString("memostring", "ftable13", "xkey = 'XNPWPBLANK'"), "");
		String XEXTEOD = Objects.toString(repoMOutlet.getXkeyString("memonama", "ftable13", "xkey = 'XEXTEOD'"), "");
		String OVERDUEGP = Objects.toString(repoMOutlet.getXkeyString("memostring", "ftable13", "xkey = 'OVERDUEGP'"), "");
		String LIMITKREDITGP = Objects.toString(repoMOutlet.getXkeyString("memostring", "ftable13", "xkey = 'LIMITKREDITGP'"), "");
		String XEDITGROUPDISC = Objects.toString(repoMOutlet.getXkeyString("memostring", "ftable13", "xkey = 'XEDITGROUPDISC'"), "");
		String XEDITSPESIFIC = Objects.toString(repoMOutlet.getXkeyString("memostring", "ftable13", "xkey = 'XEDITSPESIFIC'"), "");
		String TGLGUDANG = Objects.toString(repoMOutlet.getXkeyString("to_char(memodate,'DD MON YYYY')", "fmemo", "memonama = 'CADATE'"), "");
		String PEKAN = Objects.toString(repoMOutlet.getXkeyString("to_char(memoint)", "fmemo", "memonama = 'PEKAN'"), "");
		MasterOutletFormShowDto result = new MasterOutletFormShowDto(XMASTERDATA, XNPWPBLANK, XEXTEOD, OVERDUEGP, LIMITKREDITGP, XEDITGROUPDISC, XEDITSPESIFIC, TGLGUDANG, PEKAN);
		return result;
	}

	@Override
	public MasterOutletDto getMasterOutlet(String custNo, String group, String value) {
		// TODO Auto-generated method stub
		String noOutlet = custNo;
		if (custNo.length() == 0) {
			noOutlet = Objects.toString(repoMOutlet.getMaxCustNo(group, value), "");
		}
		MasterOutletDto result = new MasterOutletDto();
		List<MasterOutletDataProfileDto> dataProfile = repoMOutlet.getDataProfile(noOutlet);
		List<MasterOutletDataAttributeDto> dataAttribute = repoMOutlet.getDataAttribute(noOutlet);
		List<MasterOutletDataPajakDto> dataPajak = repoMOutlet.getDataPajak(noOutlet);
		List<MasterOutletDataPemerintahanDto> dataPemerintahan = repoMOutlet.getDataPemerintahan(noOutlet);
		List<MasterOutletDataCoverDto> dataCover = repoMOutlet.getDataCover(noOutlet);
		List<MasterOutletDataDivisiDto> dataDivisi = repoMOutlet.getDataDivisi(noOutlet);

		result =  new MasterOutletDto(dataProfile, dataAttribute, dataPajak, dataPemerintahan, dataCover, dataDivisi);

		return result;
	}

	@Override
	public List<MasterOutletBrowseDto> getBrowserMasterOutlet(String groupDivisi, String valTaskforce) {
		// TODO Auto-generated method stub
		return repoMOutlet.getBrowseOutlet(groupDivisi, valTaskforce);
	}

	@Override
	public String[] getSuggestNoOutlet(String divisi) {
		// TODO Auto-generated method stub
		List<Object[]> Ftable48 = repoMOutlet.getFtable48(divisi);
		if (Ftable48.size() == 0) {
			String NOOUTLETNEW = Objects.toString(repoMOutlet.getXkeyString("memostring2", "ftable13", "xkey = 'X_CUSTNO_"+divisi+"_AWAL'"), "");
			
			String[] message = {"info","Success!","Usulan Nomor Outlet " + NOOUTLETNEW, NOOUTLETNEW};
			return message;
		} else {
			List<Object[]> cekNoOutlet = repoMOutlet.getCekSuggestCustNo(divisi);
			if (cekNoOutlet.size() == 0) {
				List<Object[]> cekNoOutletFcustmst = repoMOutlet.getCekSuggestCustNoFcustmst(divisi);
				if (cekNoOutletFcustmst.size() == 0) {
					String NOOUTLETNEW = Objects.toString(repoMOutlet.getXkeyString("memostring2 as new_custno", "ftable13", "xkey = 'X_CUSTNO_"+divisi+"_AWAL'"), "");
					
					String[] message = {"info","Success!","Usulan Nomor Outlet " + NOOUTLETNEW, NOOUTLETNEW};
					return message;
				} else {
					String[] message = {"warn","Warning!","Nomor Outlet Sudah Habis!"};
					return message;
				}
			} else {
				String NOOUTLETNEW = "";
				for (Object[] o : cekNoOutlet) {
					NOOUTLETNEW = Objects.toString((String)o[0], "");
				}
				
				String[] message = {"info","Success!","Usulan Nomor Outlet " + NOOUTLETNEW, NOOUTLETNEW};
				return message;
			}
		}
	}

	@Override
	public String[] cekEditCustomer(String custNo) {
		// TODO Auto-generated method stub
		if (custNo.length() == 0) {
			String[] message = {"warn","Warning!","Tidak ada outlet yang dipilih!"};
			return message;
		}
		String XMASTERDATA = Objects.toString(repoMOutlet.getXkeyString("memostring", "ftable13", "xkey = 'XMASTERDATA'"), "");
		if (XMASTERDATA.equalsIgnoreCase("1")) {
			List<Object[]> cekFtable48 = repoMOutlet.getXkeyListObject("*", "ftable48", "WHERE processdate IS NOT NULL AND custno = '"+custNo+"'");
			if (cekFtable48.size() != 0) {
				String[] message = {"warn","Warning!","Outlet Sudah Diproses!"};
				return message;
			}
		}

		List<Object[]> cekFjualH = repoMOutlet.getXkeyListObject("*", "fjual_h", "WHERE custno = '"+custNo+"' and ROWNUM = 1");
		if (cekFjualH.size() != 0) {
			String[] message = {"warn","Warning!","Sudah ada Order atas outlet " + custNo + " edit tidak bisa dilakukan, silahkan menggunakan menu Perubahan Atribut Outlet!"};
			return message;
		}

		List<Object[]> cekForderH = repoMOutlet.getXkeyListObject("*", "forder_h", "WHERE custno = '"+custNo+"' and ROWNUM = 1");
		if (cekForderH.size() != 0) {
			String[] message = {"warn","Warning!","Sudah ada Order atas outlet " + custNo + " edit tidak bisa dilakukan, silahkan menggunakan menu Perubahan Atribut Outlet!"};
			return message;
		}

		String blokTO = Objects.toString(repoMOutlet.getXkeyString("memonama", "ftable13", "xkey = 'XBLOKUBAHOUTLETTO'"), "");
		String blokKVS = Objects.toString(repoMOutlet.getXkeyString("memonama", "ftable13", "xkey = 'XBLOKUBAHOUTLETKVS'"), "");
		String xflag = Objects.toString(repoMOutlet.getXkeyString("memonama", "ftable13", "xkey = 'XEXTEOD'"), "");
		String hari = Objects.toString(repoMOutlet.getXkeyString("MEMOSTRING", "ftable13", "xkey = 'XCUST'"), "");

		if (cekFjualH.size() == 0) {
			List<Object[]> getCekDate = repoMOutlet.getCekDate(custNo);
			if (getCekDate.size() != 0 ) {
				if (XMASTERDATA.length() == 0) {
					if (blokTO.equalsIgnoreCase("2") && xflag.equalsIgnoreCase("2")) {
						List<Object[]> cekBlokTO = repoMOutlet.getCekBlok(custNo, "T");
						if (cekBlokTO.size() != 0) {
							String[] message = {"warn","Warning!","Outlet yang dicover oleh salesman TO tidak dapat diubah!"};
							return message;
						}
					}
					if (blokKVS.equalsIgnoreCase("2") && xflag.equalsIgnoreCase("2")) {
						List<Object[]> cekBlokKVS = repoMOutlet.getCekBlok(custNo, "C");
						if (cekBlokKVS.size() != 0) {
							String[] message = {"warn","Warning!","Outlet yang dicover oleh salesman Kanvas tidak dapat diubah!"};
							return message;
						}
					}
				} 
			} else {
				String[] message = {"warn","Warning!","Sudah Lewat " + hari + " Hari Dari Tanggal Register, Perubahan Atribute Outlet dilakukan Lewat Otorisasi!"};
				return message;
			}
		} else {
			String[] message = {"warn","Warning!","Outlet Ini Sudah ada Transaksi, Setiap Perubahan Atribut Outlet Lewat Otorisasi!"};
			return message;
		}
		
		String[] message = {"info","Info","Customer dapat diEdit!"};
		return message;
	}

	@Override
	@Transactional
	public String[] saveOutlet(MasterOutletSaveDto p, String divisi, String valTaskforce) {
		// TODO Auto-generated method stub
		String nullDefault = "";
		MasterOutletDataProfileDto pfl = p.getDataProfile();
		MasterOutletDataPemerintahanDto pmr = p.getDataPemerintahan();
		MasterOutletDataPajakDto pjk = p.getDataPajak();
		MasterOutletDataAttributeDto att = p.getDataAttribute();
		String custNo = Objects.toString(pfl.getCustno(), nullDefault);
		System.out.println("custNo : " + custNo);
		String custName = Objects.toString(pfl.getCustname(), nullDefault);
		System.out.println("custName : " + custName);
		String alamat1 = Objects.toString(pfl.getCustadd1(), nullDefault);
		System.out.println("alamat1 : " + alamat1);
		String custadd1 = Objects.toString(pfl.getCustadd1(), nullDefault);
		System.out.println("custadd1 : " + custadd1);
		String custadd2 = Objects.toString(pfl.getCustadd2(), nullDefault);
		System.out.println("custadd2 : " + custadd2);
		String ccity = Objects.toString(pfl.getCcity(), nullDefault);
		System.out.println("ccity : " + ccity);
		String cphone1 = Objects.toString(pfl.getCphone1(), nullDefault);
		System.out.println("cphone1 : " + cphone1);
		String ckdpos = Objects.toString(pfl.getCkdpos(), nullDefault);
		System.out.println("ckdpos : " + ckdpos);
		String data02 = Objects.toString(pfl.getData02(), nullDefault);
		System.out.println("data02 : " + data02);
		String data03 = Objects.toString(pfl.getData03(), nullDefault);
		System.out.println("data03 : " + data03);
		String data04 = Objects.toString(pfl.getData04(), nullDefault);
		System.out.println("data04 : " + data04);
		String flagout = Objects.toString(pfl.getFlagout(), nullDefault);
		System.out.println("flagout : " + flagout);
		String ccontact = Objects.toString(pfl.getCcontact(), nullDefault);
		System.out.println("ccontact : " + ccontact);
		String cfaxno = Objects.toString(pfl.getCfaxno(), nullDefault);
		System.out.println("cfaxno : " + cfaxno);
		String data05 = Objects.toString(pfl.getData05(), nullDefault);
		System.out.println("data05 : " + data05);
		String data06 = Objects.toString(pfl.getData06(), nullDefault);
		System.out.println("data06 : " + data06);
		String cterm = Objects.toString(pfl.getCterm(), nullDefault);
		System.out.println("cterm : " + cterm);
		String cweekno = Objects.toString(pfl.getCweekno(), nullDefault);
		System.out.println("cweekno : " + cweekno);
		String flaglimit = "";
		String data14 = Objects.toString(pfl.getData14(), nullDefault);
		System.out.println("data14 : " + data14);
		String climit = Objects.toString(pfl.getClimit(), nullDefault);
		System.out.println("climit : " + climit);
		String flagpay = Objects.toString(pfl.getFlagpay(), nullDefault);
		System.out.println("flagpay : " + flagpay);
		String flagkbon = Objects.toString(pfl.getFlagkbon(), nullDefault);
		System.out.println("flagkbon : " + flagkbon);
		String data10 = Objects.toString(pfl.getData10(), nullDefault);
		System.out.println("data10 : " + data10);
		String data11 = Objects.toString(pfl.getData11(), nullDefault);
		System.out.println("data11 : " + data11);
		String firstopen = Objects.toString(pfl.getMulaiusaha(), nullDefault);
		System.out.println("firstopen : " + firstopen);
		String regdate = Objects.toString(pfl.getRegdate(), nullDefault);
		System.out.println("regdate : " + regdate);
		String data19 = Objects.toString(pfl.getData19(), nullDefault);
		System.out.println("data19 : " + data19);
		String clastupd = Objects.toString(pfl.getTransupd(), nullDefault);
		System.out.println("clastupd : " + clastupd);
		String tglGudang = Objects.toString(pfl.getTransupd(), nullDefault);
		String location = Objects.toString(att.getKdlokasi(), nullDefault);
		System.out.println("location : " + location);
		String data16 = Objects.toString(att.getKdsublokasi(), nullDefault);
		System.out.println("data16 : " + data16);
		String kdpsr = Objects.toString(att.getKdpsr(), nullDefault);
		System.out.println("kdpsr : " + kdpsr);
		String typeout = Objects.toString(att.getKdchannel(), nullDefault);
		System.out.println("typeout : " + typeout);
		String klass = Objects.toString(att.getKdclass(), nullDefault);
		System.out.println("klass : " + klass);
		String grupout = Objects.toString(att.getKdgroupout(), nullDefault);
		System.out.println("grupout : " + grupout);
		String distrik = Objects.toString(att.getKddistrik(), nullDefault);
		System.out.println("distrik : " + distrik);
		String beat = Objects.toString(att.getRute(), nullDefault);
		System.out.println("beat : " + beat);
		String sbeat = Objects.toString(att.getSubrute(), nullDefault);
		System.out.println("sbeat : " + sbeat);
		String kdind = Objects.toString(att.getKdind(), nullDefault);
		System.out.println("kdind : " + kdind);
		String data15 = Objects.toString(att.getKdspecout(), nullDefault);
		System.out.println("data15 : " + data15);
		String data17 = Objects.toString(att.getKdgrup1(), nullDefault);
		System.out.println("data17 : " + data17);
		String data18 = Objects.toString(att.getKdgrup2(), nullDefault);
		System.out.println("data18 : " + data18);
		String gdisc = Objects.toString(att.getGdisc(), nullDefault);
		System.out.println("gdisc : " + gdisc);
		String gplu = Objects.toString(att.getGplu(), nullDefault);
		System.out.println("gplu : " + gplu);
		String gkonv = Objects.toString(att.getGkonv(), nullDefault);
		System.out.println("gkonv : " + gkonv);
		String gharga = Objects.toString(att.getGharga(), nullDefault);
		System.out.println("gharga : " + gharga);
		String taxname = Objects.toString(pjk.getTaxname(), nullDefault);
		System.out.println("taxname : " + taxname);
		String taxadd1 = Objects.toString(pjk.getTaxadd1(), nullDefault);
		System.out.println("taxadd1 : " + taxadd1);
		String taxadd2 = Objects.toString(pjk.getTaxadd2(), nullDefault);
		System.out.println("taxadd2 : " + taxadd2);
		String taxcity = Objects.toString(pjk.getTaxcity(), nullDefault);
		System.out.println("taxcity : " + taxcity);
		String npwp = Objects.toString(pjk.getNpwp(), nullDefault);
		System.out.println("npwp : " + npwp);
		String taxflag = Objects.toString(pjk.getTaxflag(), nullDefault);
		System.out.println("taxflag : " + taxflag);
		String agenlain = Objects.toString(pjk.getAgenlain(), nullDefault);
		System.out.println("agenlain : " + agenlain);
		String norefsup = Objects.toString(pjk.getNorefsup(), nullDefault);
		System.out.println("norefsup : " + norefsup);
		String data07 = Objects.toString(pjk.getData07(), nullDefault);
		System.out.println("data07 : " + data07);
		String data08 = Objects.toString(pjk.getData08(), nullDefault);
		System.out.println("data08 : " + data08);
		String data09 = Objects.toString(pjk.getData09(), nullDefault);
		System.out.println("data09 : " + data09);
		String bank = Objects.toString(pjk.getBank(), nullDefault);
		System.out.println("bank : " + bank);
		String noacc = Objects.toString(pjk.getNoacc(), nullDefault);
		System.out.println("noacc : " + noacc);
		String cresd1 = Objects.toString(pjk.getCresd1(), nullDefault);
		System.out.println("cresd1 : " + cresd1);
		String cresd2 = Objects.toString(pjk.getCresd2(), nullDefault);
		System.out.println("cresd2 : " + cresd2);
		String ccity2 = Objects.toString(pjk.getCcity2(), nullDefault);
		System.out.println("ccity2 : " + ccity2);
		String cphone2 = Objects.toString(pjk.getCphone2(), nullDefault);
		System.out.println("cphone2 : " + cphone2);
		String flaghome = Objects.toString(pjk.getFlaghome(), nullDefault);
		System.out.println("flaghome : " + flaghome);
		String kdProp = Objects.toString(pmr.getKdprop(), nullDefault);
		System.out.println("kdProp : " + kdProp);
		String kdKab  = Objects.toString(pmr.getKdkab(), nullDefault);
		System.out.println("kdKab : " + kdKab);
		String kdKec  = Objects.toString(pmr.getKdkec(), nullDefault);
		System.out.println("kdKec : " + kdKec);
		String kdKel  = Objects.toString(pmr.getKdkel(), nullDefault);
		System.out.println("kdKel : " + kdKel);

		String outletType = Objects.toString(att.getOuttype(), nullDefault);
		System.out.println("outletType : " + outletType);
		String groupDivisi = Objects.toString(pfl.getGroupdivisi(), nullDefault);
		System.out.println("groupDivisi : " + groupDivisi);
		String custNoOld = Objects.toString(pfl.getCustnoold(), nullDefault);
		System.out.println("custNoOld : " + custNoOld);
		String noKtp = Objects.toString(pjk.getNoktp(), nullDefault);
		System.out.println("noKtp : " + noKtp);
		String nik = Objects.toString(pjk.getNoktp(), nullDefault);
		System.out.println("nik : " + nik);
		String groupDelivery = Objects.toString(pjk.getGroupdeliv(), nullDefault);
		System.out.println("groupDelivery : " + groupDelivery);
		String typeCost = Objects.toString(att.getTypecost(), nullDefault);
		System.out.println("typeCost : " + typeCost);
		String cost = Objects.toString(att.getCost(), nullDefault);
		System.out.println("cost : " + cost);
		String kdKolektor = Objects.toString(pfl.getEmpno(), nullDefault);
		System.out.println("kdKolektor : " + kdKolektor);
		String byKrt = Objects.toString(pfl.getBykrt(), nullDefault);
		System.out.println("byKrt : " + byKrt);
		String groupPayer = Objects.toString(pfl.getGrouppayer(), nullDefault);
		System.out.println("groupPayer : " + groupPayer);
		String flagOutlet = Objects.toString(pfl.getChiller(), nullDefault);
		System.out.println("flagOutlet : " + flagOutlet);
		String limit = Objects.toString(pfl.getClimit(), nullDefault);
		System.out.println("limit : " + limit);
		String groupOutlet = Objects.toString(att.getKdgroupout(), nullDefault);
		System.out.println("groupOutlet : " + groupOutlet);
		
		String noKartu1 = Objects.toString(pfl.getNokartu1(), nullDefault);
		System.out.println("noKartu1 : " + noKartu1);
		String noKartu2 = Objects.toString(pfl.getNokartu2(), nullDefault);
		System.out.println("noKartu2 : " + noKartu2);
		String noKartu3 = Objects.toString(pfl.getNokartu3(), nullDefault);
		System.out.println("noKartu3 : " + noKartu3);
		

		String tf = "";
		if (valTaskforce.equalsIgnoreCase("not null")) {
			tf = "*";
		}


		List<Object[]> cekFcustmst = repoMOutlet.getXkeyListObject
		("*", "fcustmst", "WHERE custno = '"+custNo+"'");
		if (cekFcustmst.size() == 0) {
			List<Object[]> cekValidasiOutlet = repoMOutlet.getXkeyListObject
			("m.custno, m.custname", "fcustmst", "m left join "+t.getTenantId()+".ftable48 b on m.custno = b.custno  where m.custname = '"+custName+"' and b.groupdivisi = '"+divisi+"' ");
			if (cekValidasiOutlet.size() != 0) {
				String noCust = "";
				for (Object[] o : cekValidasiOutlet) {
					noCust = Objects.toString((String)o[0], "");
				}
				String[] message = {"warn","Warning!","Nama " + custName + " telah dipakai nomor customer " + noCust + ", silahkan ganti nama outlet!"};
				return message;
			} else {
				List<Object[]> cekValidasiAlamat = repoMOutlet.getXkeyListObject
				("m.custno, m.custname", "fcustmst", "m left join "+t.getTenantId()+".ftable48 b on m.custno = b.custno  where m.custadd1 = '"+alamat1+"' and b.groupdivisi = '"+divisi+"' ");
				if (cekValidasiAlamat.size() != 0) {
					String noCust = "";
					for (Object[] o : cekValidasiAlamat) {
						noCust = Objects.toString((String)o[0], "");
					}
					String[] message = {"warn","Warning!","Alamat " + alamat1 + " telah dipakai nomor customer " + noCust + ", silahkan ganti alamat outlet!"};
					return message;
				} else {
					String out_awalStr = Objects.toString(repoMOutlet.getXkeyString
					("memostring2", "ftable13", "memonama = '"+divisi+"' and xkey = 'X_CUSTNO_"+divisi+"_AWAL'"), "");
					
					String out_akhirStr = Objects.toString(repoMOutlet.getXkeyString
					("memostring2", "ftable13", "memonama = '"+divisi+"' and xkey = 'X_CUSTNO_"+divisi+"_AKHIR'"), "");
					
					Double out_awalDouble = Double.parseDouble(out_awalStr);
					Double out_akhirDouble = Double.parseDouble(out_akhirStr);
					Double custnoDouble = Double.parseDouble(custNo);
					
					if (custnoDouble < out_awalDouble) {
						String[] message = {"warn","Warning!","Kode outlet tidak boleh kurang dari " + out_awalStr + " atau lebih dari " + out_akhirStr + " !"};
						return message;
					}
					if (custnoDouble > out_akhirDouble) {
						String[] message = {"warn","Warning!","Kode outlet tidak boleh kurang dari " + out_awalStr + " atau lebih dari " + out_akhirStr + " !"};
						return message;
					}
					// Insert Pemerintahan
					List<Object[]> cekPemerintahan = repoMOutlet.getXkeyListObject
					("*", "fcustud", "where custno = '"+custNo+"'");
					if (cekPemerintahan.size() == 0) {
						repoMOutlet.insertData("fcustud", "custno,H11,H12,H13,H14", "'"+custNo+"', '"+kdProp+"', '"+kdKab+"', '"+kdKec+"', '"+kdKel+"'");
					}

					//insert outlet type
					List<Object[]> cekOutletType = repoMOutlet.getXkeyListObject
					("*", "ftable48", "where custno = '"+custNo+"'");
					if (cekOutletType.size() == 0) {
						repoMOutlet.insertFtable48(custNo, outletType, groupDivisi, typeCost, cost, kdKolektor, tf, byKrt, groupPayer, flagOutlet, custNoOld, nik, groupDelivery);
					} else {
						repoMOutlet.updateFtable48Add(outletType, groupDivisi, custNoOld, nik, groupDelivery, custNo);
					}


					if (pfl.getData14().equalsIgnoreCase("Y")) {
						repoMOutlet.insertFtable64("100", custNo, limit, groupOutlet);
						List<Object[]> cekFtabel64 = repoMOutlet.getXkeyListObject
					("*", "ftabel64", "where data2 = '"+groupOutlet+"'");

						if (cekFtabel64.size() != 0) {
							repoMOutlet.updateFtable64(limit, custNo, groupOutlet);
						} else if (pfl.getData14().equalsIgnoreCase("Y")) {
							repoMOutlet.insertFtable64("200", custNo, limit, groupOutlet);
						}
					} else if  (pfl.getData14().equalsIgnoreCase("T")){
						repoMOutlet.insertFtable64("100", custNo, limit, groupOutlet);
					}

					repoMOutlet.insertFcustmst(custNo, custName, custadd1, custadd2, ccity, cphone1, ckdpos, data02, data03, data04, flagout, ccontact, cfaxno, data05, data06, cterm, cweekno, flaglimit, data14, climit, flagpay, flagkbon, data10, data11, firstopen, regdate, data19, location, data16, kdpsr, typeout, klass, grupout, distrik, beat, sbeat, kdind, data15, data17, data18, gdisc, gplu, gkonv, gharga, taxname, taxadd1, taxadd2, taxcity, npwp, taxflag, agenlain, norefsup, data07, data08, data09, bank, noacc, cresd1, cresd2, ccity2, cphone2, flaghome);

					CREATEBARCODE(custNo);
					// BARCODE
					kartuM1(custNo, noKartu1);
					kartuM2(custNo, noKartu2);
					kartuM3(custNo, noKartu3);
					repoMOutlet.updateData("ftable21", "upddate = '"+tglGudang+"' ", "where custno = '"+custNo+"'");
				}
			}
		} else {

			repoMOutlet.updateFcustmst(custNo, custName, custadd1, custadd2, ccity, cphone1, ckdpos, data02, data03, data04, ccontact, cfaxno, data05, data06, cterm, cweekno, data14, climit, flagpay, flagkbon, data10, data11, clastupd, location, data16, kdpsr, typeout, klass, grupout, distrik, beat, sbeat, kdind, data15, data17, data18, gdisc, gplu, gkonv, gharga, taxname, taxadd1, taxadd2, taxcity, npwp, taxflag, agenlain, norefsup, data07, data08, data09, bank, noacc, cresd1, cresd2, ccity2, cphone2, flaghome);

			List<Object[]> cekftable48 = repoMOutlet.getXkeyListObject
			("*", "ftable48", "where custno = '"+custNo+"'");
			if (cekftable48.size() != 0) {
				repoMOutlet.updateFtable48Update(custNo, outletType, groupDivisi, typeCost, cost, kdKolektor, byKrt, flagOutlet, custNoOld, nik, groupDelivery, groupPayer);
			} 
			List<Object[]> cekfcustud = repoMOutlet.getXkeyListObject
			("*", "fcustud", "where custno = '"+custNo+"'");
			if (cekfcustud.size() != 0) {
				repoMOutlet.updateFcustudUpdate(custNo, kdProp, kdKab, kdKec, kdKel);
			} 

			CREATEBARCODE(custNo);
			// BARCODE
			kartuM1(custNo, noKartu1);
			kartuM2(custNo, noKartu2);
			kartuM3(custNo, noKartu3);
			repoMOutlet.updateData("ftable21", "upddate = '"+tglGudang+"' ", "where custno = '"+custNo+"'");
		}
		
		String[] message = {"success","Success!","Save Outlet berhasil!"};
		return message;
	}

	private void CREATEBARCODE(String custno){

	}

	private void kartuM1(String custNo, String noKartu1){

		if (noKartu1.equalsIgnoreCase("000000000000000")) {
			
			repoMOutlet.updateData("fcustmst", "data01 = '"+noKartu1+"'", "where custno = '"+custNo+"'");

			List<Object[]> FTABLE21 = repoMOutlet.getXkeyListObject
			("*", "FTABLE21", "WHERE custno = '"+custNo+"'");

			if (FTABLE21.size() == 0) {
				repoMOutlet.insertData("FTABLE21", "CUSTNO, NOKARTU1, NOBARCODE1", "'"+custNo+"', '"+noKartu1+"', '"+noKartu1+"'");
			} else {
				repoMOutlet.updateData("FTABLE21", "NOKARTU1 = '"+noKartu1+"', NOBARCODE1 = '"+noKartu1+"' ", "where custno = '"+custNo+"'");
			}

		} else {
			String xstr = noKartu1;
			Integer hasil = xstr.length();
			if (hasil == 15) {
				Integer a1 = Integer.valueOf(xstr.substring(4, 5))*8;
				Integer a2 = Integer.valueOf(xstr.substring(5, 6))*7;
				Integer a3 = Integer.valueOf(xstr.substring(10, 11))*6;
				Integer a4 = Integer.valueOf(xstr.substring(11, 12))*5;
				Integer a5 = Integer.valueOf(xstr.substring(12, 13))*4;
				Integer a6 = Integer.valueOf(xstr.substring(13, 14))*3;
				Integer a7 = Integer.valueOf(xstr.substring(14, 15))*2;
				System.out.println("a1 = " + a1);
				System.out.println("a2 = " + a2);
				System.out.println("a3 = " + a3);
				System.out.println("a4 = " + a4);
				System.out.println("a5 = " + a5);
				System.out.println("a6 = " + a6);
				System.out.println("a7 = " + a7);

				hasil = a1+a2+a3+a4+a5+a6+a7;
				System.out.println("hasil = " + hasil);
				Integer xhasil = hasil/11;
				xhasil = xhasil*11;
				System.out.println("xhasil = " + xhasil);
				xhasil = (a1+a2+a3+a4+a5+a6+a7)-xhasil;
				System.out.println("xhasil = " + xhasil);
				String sisip = "0";
				if (xhasil == 0){
					sisip = "1";
				} else if (xhasil == 1){
					sisip = "0";
				} else {
					sisip = String.valueOf((11-xhasil));
				}
				System.out.println("sisip = " + sisip);

				String ssl = xstr.substring(4, 5) 
				+ xstr.substring(5, 6) 
				+ xstr.substring(10, 11)
				+ sisip 
				+ xstr.substring(11, 12)
				+ xstr.substring(12, 13)
				+ xstr.substring(13, 14)
				+ xstr.substring(14, 15);
				System.out.println("ssl = " + ssl);
				String BARCODE = xstr.substring(0,10) + ssl.substring(2, 8);
				System.out.println("BARCODE " + BARCODE);

				repoMOutlet.updateData("fcustmst", "data01 = '"+BARCODE+"'", "where custno = '"+custNo+"'");
				List<Object[]> FTABLE21 = repoMOutlet.getXkeyListObject
				("*", "FTABLE21", "WHERE custno = '"+custNo+"'");
				if (FTABLE21.size() == 0) {
					repoMOutlet.insertData("FTABLE21", "CUSTNO, NOKARTU1, NOBARCODE1", "'"+custNo+"', '"+noKartu1+"', '"+BARCODE+"'");
				} else {
					repoMOutlet.updateData("FTABLE21", "NOKARTU1 = '"+noKartu1+"', NOBARCODE1 = '"+BARCODE+"' ", "where custno = '"+custNo+"'");
				}
			}
		}
	}

	private void kartuM2(String custNo, String noKartu2){
		if (noKartu2.equalsIgnoreCase("000000000000000")) {
			List<Object[]> FTABLE21 = repoMOutlet.getXkeyListObject
			("*", "FTABLE21", "WHERE custno = '"+custNo+"'");

			if (FTABLE21.size() == 0) {
				repoMOutlet.insertData("FTABLE21", "CUSTNO, NOKARTU2, NOBARCODE2", "'"+custNo+"', '"+noKartu2+"', '"+noKartu2+"'");
			} else {
				repoMOutlet.updateData("FTABLE21", "NOKARTU2 = '"+noKartu2+"', NOBARCODE2 = '"+noKartu2+"' ", "where custno = '"+custNo+"'");
			}
		} else {
			String xstr = noKartu2;
			Integer hasil = xstr.length();
			if (hasil == 15) {
				Integer a1 = Integer.valueOf(xstr.substring(4, 5))*8;
				Integer a2 = Integer.valueOf(xstr.substring(5, 6))*7;
				Integer a3 = Integer.valueOf(xstr.substring(10, 11))*6;
				Integer a4 = Integer.valueOf(xstr.substring(11, 12))*5;
				Integer a5 = Integer.valueOf(xstr.substring(12, 13))*4;
				Integer a6 = Integer.valueOf(xstr.substring(13, 14))*3;
				Integer a7 = Integer.valueOf(xstr.substring(14, 15))*2;
				System.out.println("a1 = " + a1);
				System.out.println("a2 = " + a2);
				System.out.println("a3 = " + a3);
				System.out.println("a4 = " + a4);
				System.out.println("a5 = " + a5);
				System.out.println("a6 = " + a6);
				System.out.println("a7 = " + a7);

				hasil = a1+a2+a3+a4+a5+a6+a7;
				System.out.println("hasil = " + hasil);
				Integer xhasil = hasil/11;
				xhasil = xhasil*11;
				System.out.println("xhasil = " + xhasil);
				xhasil = (a1+a2+a3+a4+a5+a6+a7)-xhasil;
				System.out.println("xhasil = " + xhasil);
				String sisip = "0";
				if (xhasil == 0){
					sisip = "1";
				} else if (xhasil == 1){
					sisip = "0";
				} else {
					sisip = String.valueOf((11-xhasil));
				}
				System.out.println("sisip = " + sisip);

				String ssl = xstr.substring(4, 5) 
				+ xstr.substring(5, 6) 
				+ xstr.substring(10, 11)
				+ sisip 
				+ xstr.substring(11, 12)
				+ xstr.substring(12, 13)
				+ xstr.substring(13, 14)
				+ xstr.substring(14, 15);
				System.out.println("ssl = " + ssl);
				String BARCODE = xstr.substring(0,10) + ssl.substring(2, 8);
				System.out.println("BARCODE " + BARCODE);

				repoMOutlet.updateData("fcustmst", "data01 = '"+BARCODE+"'", "where custno = '"+custNo+"'");
				List<Object[]> FTABLE21 = repoMOutlet.getXkeyListObject
				("*", "FTABLE21", "WHERE custno = '"+custNo+"'");
				if (FTABLE21.size() == 0) {
					repoMOutlet.insertData("FTABLE21", "CUSTNO, NOKARTU2, NOBARCODE2", "'"+custNo+"', '"+noKartu2+"', '"+BARCODE+"'");
				} else {
					repoMOutlet.updateData("FTABLE21", "NOKARTU2 = '"+noKartu2+"', NOBARCODE2 = '"+BARCODE+"' ", "where custno = '"+custNo+"'");
				}
			}
		}
	}

	private void kartuM3(String custNo, String noKartu3){
		if (noKartu3.equalsIgnoreCase("000000000000000")) {
			List<Object[]> FTABLE21 = repoMOutlet.getXkeyListObject
			("*", "FTABLE21", "WHERE custno = '"+custNo+"'");

			if (FTABLE21.size() == 0) {
				repoMOutlet.insertData("FTABLE21", "CUSTNO, NOKARTU3, NOBARCODE3", "'"+custNo+"', '"+noKartu3+"', '"+noKartu3+"'");
			} else {
				repoMOutlet.updateData("FTABLE21", "NOKARTU3 = '"+noKartu3+"', NOBARCODE3 = '"+noKartu3+"' ", "where custno = '"+custNo+"'");
			}
		} else {
			String xstr = noKartu3;
			Integer hasil = xstr.length();
			if (hasil == 15) {
				Integer a1 = Integer.valueOf(xstr.substring(4, 5))*8;
				Integer a2 = Integer.valueOf(xstr.substring(5, 6))*7;
				Integer a3 = Integer.valueOf(xstr.substring(10, 11))*6;
				Integer a4 = Integer.valueOf(xstr.substring(11, 12))*5;
				Integer a5 = Integer.valueOf(xstr.substring(12, 13))*4;
				Integer a6 = Integer.valueOf(xstr.substring(13, 14))*3;
				Integer a7 = Integer.valueOf(xstr.substring(14, 15))*2;
				System.out.println("a1 = " + a1);
				System.out.println("a2 = " + a2);
				System.out.println("a3 = " + a3);
				System.out.println("a4 = " + a4);
				System.out.println("a5 = " + a5);
				System.out.println("a6 = " + a6);
				System.out.println("a7 = " + a7);

				hasil = a1+a2+a3+a4+a5+a6+a7;
				System.out.println("hasil = " + hasil);
				Integer xhasil = hasil/11;
				xhasil = xhasil*11;
				System.out.println("xhasil = " + xhasil);
				xhasil = (a1+a2+a3+a4+a5+a6+a7)-xhasil;
				System.out.println("xhasil = " + xhasil);
				String sisip = "0";
				if (xhasil == 0){
					sisip = "1";
				} else if (xhasil == 1){
					sisip = "0";
				} else {
					sisip = String.valueOf((11-xhasil));
				}
				System.out.println("sisip = " + sisip);

				String ssl = xstr.substring(4, 5) 
				+ xstr.substring(5, 6) 
				+ xstr.substring(10, 11)
				+ sisip 
				+ xstr.substring(11, 12)
				+ xstr.substring(12, 13)
				+ xstr.substring(13, 14)
				+ xstr.substring(14, 15);
				System.out.println("ssl = " + ssl);
				String BARCODE = xstr.substring(0,10) + ssl.substring(2, 8);
				System.out.println("BARCODE " + BARCODE);

				repoMOutlet.updateData("fcustmst", "data01 = '"+BARCODE+"'", "where custno = '"+custNo+"'");
				List<Object[]> FTABLE21 = repoMOutlet.getXkeyListObject
				("*", "FTABLE21", "WHERE custno = '"+custNo+"'");
				if (FTABLE21.size() == 0) {
					repoMOutlet.insertData("FTABLE21", "CUSTNO, NOKARTU3, NOBARCODE3", "'"+custNo+"', '"+noKartu3+"', '"+BARCODE+"'");
				} else {
					repoMOutlet.updateData("FTABLE21", "NOKARTU3 = '"+noKartu3+"', NOBARCODE3 = '"+BARCODE+"' ", "where custno = '"+custNo+"'");
				}
			}
		}
	}
	
	@Override
	@Transactional
	public void deleteCoverOutlet(String custNo, String slsNo, String tglGudang) {
		// TODO Auto-generated method stub
		repoMOutlet.deleteData("fcustsls", "WHERE custno = '"+custNo+"' AND slsno = '"+slsNo+"'");
		repoMOutlet.updateData("fcustmst", "clastupd = '"+tglGudang+"' ", "WHERE custno = '"+custNo+"'");
	}

	@Override
	public String[] cekEditCover(String custNo, String slsNo, String groupDivisi) {
		// TODO Auto-generated method stub
		List<Object[]> fcustmst = repoMOutlet.getXkeyListObject
		("*", "fcustmst", "WHERE custno = '"+custNo+"' and flagout = 'N'");
		if (fcustmst.size() != 0) {
			String[] message = {"warn","Warning!","Outlet ini tidak aktif!"};
			return message;
		}
		List<Object[]> cekEditCover = repoMOutlet.getCekEditCover(custNo, slsNo, groupDivisi);
		if (cekEditCover.size() != 0) {
			String[] message = {"success","Success!","Save Outlet berhasil!"};
			return message;
		} else {
			String[] message = {"warn","Warning!","Salesman ini tidak termasuk dalam grup divisi " + groupDivisi + "Tidak dapat diedit pengcoverannya!"};
			return message;
		}
	}

	@Override
	@Transactional
	public void updateCover(MasterOutletDataCoverDto p, String tglGudang) {
		// TODO Auto-generated method stub
		repoMOutlet.updateDataCover(p);
		repoMOutlet.updateData("fcustmst", "clastupd = '"+tglGudang+"' ", "where custno = '"+p.getCustno()+"'");
	}

	@Override
	@Transactional
	public String[] addCover(MasterOutletDataCoverDto p, String tglGudang) {
		// TODO Auto-generated method stub
		List<Object[]> fsls = repoMOutlet.getXkeyListObject
		("*", "fsls", "WHERE slsno = '"+p.getSlsno()+"' and data04 in (select memostring from "+t.getTenantId()+".ftable13 where xkey = 'SLSFORCE_TF')");
		if (fsls.size() != 0) {
			List<Object[]> ftable13 = repoMOutlet.getXkeyListObject
			("*", "ftable13", "WHERE where xkey = 'TF_CHANNEL_EXCLUDE' and memostring in (select typeout from "+t.getTenantId()+".fcustmst where custno = '"+p.getCustno()+"')");
			if (ftable13.size() != 0) {
				String[] message = {"warn","Warning!","SALESAN SERANG TIDAK BOLEH OUTLET GROSIR!"};
				return message;
			}
		}
		repoMOutlet.addDataCover(p);
		repoMOutlet.updateData("fcustmst", "clastupd = '"+tglGudang+"' ", "where custno = '"+p.getCustno()+"'");
		String[] message = {"success","Success!","Add Cover Outlet salesman "+p.getSlsno()+" berhasil ditambahkan!"};
		return message;
	}

	@Override
	public List<SalesmanBrowseDto> getBrowseSalesman(String groupDivisi, String taskforce) {
		// TODO Auto-generated method stub
		return repoMOutlet.getBrowseSalesman(groupDivisi, taskforce);
	}

	@Override
	public List<GroupPayerBrowseDto> getBrowseGroupPayer(String taskforce) {
		// TODO Auto-generated method stub
		return repoMOutlet.getBrowseGroupPayer(taskforce);
	}

	@Override
	public List<SearchBrowseDto> getBrowseChiller() {
		// TODO Auto-generated method stub
		return repoMOutlet.getBrowseChiller();
	}
}
