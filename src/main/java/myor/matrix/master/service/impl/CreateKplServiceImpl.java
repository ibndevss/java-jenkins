package myor.matrix.master.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.CreateKplInputDto;
import myor.matrix.master.entity.CreateKplListOutletDto;
import myor.matrix.master.entity.CreateKplListPcodeDto;
import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.repository.CreateKplRepository;
import myor.matrix.master.service.CreateKplService;
import myor.matrix.master.service.UtilService;

@Service
public class CreateKplServiceImpl implements CreateKplService {
	
	@Autowired
	private CreateKplRepository repoCreateKpl;
	
	@Autowired
	private UtilService utilService;

	@Override
	public List<CreateKplListOutletDto> getDaftarOutlet(CreateKplInputDto p) {
		// TODO Auto-generated method stub
		List<CreateKplListOutletDto> result = new ArrayList<>();
		String edtTglGudang = Objects.toString(repoCreateKpl.getXkeyString(" to_char(MEMODATE, 'DD MON YYYY')", "FMEMO", "where MEMONAMA='CADATE'"), "");
		String slsNo = p.getSlsNo();
		Boolean CheckTglPlus = p.getDatePlusOne();
		
		String edthari = "";
		String edtweek = "";
		String DateTglGudang = "";

 		
 		if (CheckTglPlus) {
 	 		List<Object[]> getFcycle = repoCreateKpl.getFCycle(edtTglGudang);
 	 		for (Object[] o : getFcycle) {
 	 			DateTglGudang = Objects.toString(o[2], "");
 	 			edtweek = Objects.toString(o[1], "");
			}
 	 		edthari = Objects.toString(repoCreateKpl.getDay(DateTglGudang), "");
		} else {
			DateTglGudang = Objects.toString(repoCreateKpl.getXkeyString(" to_char(MEMODATE, 'DD MON YYYY')", "FMEMO", "where MEMONAMA='CADATE'"), "");
			edthari = Objects.toString(repoCreateKpl.getDay(DateTglGudang), "");
			edtweek = Objects.toString(repoCreateKpl.getXkeyString(" to_char(weekno)", "FCYCLE3", "where cdate = '"+edtTglGudang+"' AND workflag = 'Y' "), "0");
		}
 		List<CreateKplListOutletDto> datas = repoCreateKpl.getDaftarOutlet(slsNo, edthari);
 		if (datas.size() == 0) {
 			return null;
		} else {
			for (CreateKplListOutletDto dt : datas) {
				if (utilService.dmCheckPola(dt.getPola_k(), edtweek)) {
					CreateKplListOutletDto data = new CreateKplListOutletDto(dt.getCustNo(), dt.getCustName(), dt.getAddress(), dt.getSlsNo(), dt.gethSenin(), dt.gethSelasa(), dt.gethRabu(), dt.gethKamis(), dt.gethJumat(), dt.gethSabtu(), dt.gethMinggu(), dt.getPola_k(), DateTglGudang);
					result.add(data);
				}
			}
			return result;
		}
	}

	@Override
	public String getRequestKey() {
		// TODO Auto-generated method stub
		int x = 3;
		int y = 7;
		String xjam =  Objects.toString(repoCreateKpl.getFromDualString("TO_CHAR (SYSDATE, 'HH24:MI:SS') AS jam", "DUAL", " "), "");
		
		String rKey = utilService.getXkey(x, y, xjam);
		return rKey;
	}

	@Override
	@Transactional
	public MessageDto processCreateKpl(CreateKplInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		String slsNo = p.getSlsNo();
		String edtTglGudang = p.getTglProcess();
		String edtTglGudangplus1 = "";
		String xuser = p.getxUser();
		
		int x = 3;
		int y = 7;
		String kodeKey = p.getKeyRequest();
		String kodeAuth = p.getKeyProcess();
		String cekKey = utilService.cekKey(x, y, kodeKey, kodeAuth);
		if (cekKey.equalsIgnoreCase("NOTOK")) {
			message = new MessageDto("warn", "Warning", "Nomor ID tidak benar!");
			return message;
		}
		
		List<Object[]> cekFsls = repoCreateKpl.getXkeyListObject("slsno,slsname,data13", "FSLS",
				"where data13 >=1 and slsno ='"+slsNo+"'");
		if (cekFsls.size() == 0) {
			message = new MessageDto("warn", "Warning", "Data pembuatan KPL tidak ada di master salesman, proses KPL tidak dapat diproses!");
			return message;
		}
		cekFsls.clear();
		
		Boolean CheckTglPlus = p.getDatePlusOne();
 		if (CheckTglPlus) {
// 	 		List<Object[]> getFcycle = repoCreateKpl.getFCycle(edtTglGudang);
// 	 		for (Object[] o : getFcycle) {
 	 			edtTglGudangplus1 = Objects.toString(edtTglGudang, "");
//			}
// 	 		getFcycle.clear();
		} else {
			edtTglGudangplus1 = Objects.toString(repoCreateKpl.getXkeyString(" to_char(MEMODATE, 'DD MON YYYY')", "FMEMO",
					"where MEMONAMA='CADATE'"), "");
		}
		
		List<Object[]> cekCoverSalesman = repoCreateKpl.cekPengcoveran(edtTglGudangplus1, slsNo);
		if (cekCoverSalesman.size() > 0 ) {
			repoCreateKpl.deleteData("ftabel46", "where data1 ='"+edtTglGudangplus1+"' and data2 = '"+slsNo+"' ");
			repoCreateKpl.deleteData("ftabel47", "where data1 ='"+edtTglGudangplus1+"' and data2 = '"+slsNo+"' ");
			repoCreateKpl.deleteData("ftabel45", "where data1 ='"+edtTglGudangplus1+"' and data2 = '"+slsNo+"' ");
		}
		cekCoverSalesman.clear();
		
		List<Object[]> cekKplOtomatis = repoCreateKpl.getXkeyListObject("data3", "ftabel46",
				"where data1 ='"+edtTglGudangplus1+"' and data2 = '"+slsNo+"' ");
		if (cekKplOtomatis.size() > 0) {
			message = new MessageDto("warn", "Warning", "Data KPL sudah ada yang terproses otomatis!");
			return message;
		}
		cekKplOtomatis.clear();
		
		repoCreateKpl.insertData("ftabel45", "data1,data2,data5", " '"+edtTglGudangplus1+"', '"+slsNo+"', '"+xuser+"'  ", "");
		
		List<CreateKplListOutletDto> dataOutlet = p.getDataOutlet();
		if (dataOutlet.size() != 0) {
			for (CreateKplListOutletDto ot : dataOutlet) {
				String custNo = ot.getCustNo();
				List<Object[]> ftabel46 = repoCreateKpl.getXkeyListObject("data3", "ftabel46",
						"where data1 ='"+edtTglGudangplus1+"' and data2 = '"+slsNo+"' and data3 = '"+custNo+"' ");
				if (ftabel46.size() == 0) {
					repoCreateKpl.insertData("ftabel46", "data1,data2,data3,data4,data5,data6",
							"'"+edtTglGudangplus1+"', '"+slsNo+"', '"+custNo+"', 'O','T',0 ", "");
				}
			}
		}
		
		String note = "";
		List<Object[]> cekPengcoveranMonthly = repoCreateKpl.cekPengcoveranbyMonthly(slsNo);
		if (cekPengcoveranMonthly.size() != 0) {
			note = "*Note: Terdapat outlet pola monthly yang tidak disarankan!"; 
			repoCreateKpl.deleteFtabel46(edtTglGudangplus1, slsNo);
		}
		cekPengcoveranMonthly.clear();
		
		List<CreateKplListPcodeDto> dataPcode = p.getDataPcode();
		if (dataPcode.size() != 0) {
			for (CreateKplListPcodeDto pc : dataPcode) {
				String pcode = pc.getPcode();
				if (dataOutlet.size() != 0) {
					for (CreateKplListOutletDto ot : dataOutlet) {
						String custNo = ot.getCustNo();
						List<Object[]> ftabel47 = repoCreateKpl.getXkeyListObject("*", "ftabel47",
								"where data1 ='"+edtTglGudangplus1+"' and data2 = '"+slsNo+"' and data3 = '"+custNo+"' and data4 = '"+pcode+"' ");
						if (ftabel47.size() == 0) {
							repoCreateKpl.insertData("ftabel47", "DATA1,DATA2,DATA3,DATA4,DATA5,DATA6,DATA7,FLAG",
									"'"+edtTglGudangplus1+"', '"+slsNo+"', '"+custNo+"', '"+pcode+"', 0,0,0,'K' ", "");
						}
					}
				}
			}
		}
		
		if (dataOutlet.size() == 0) {
			
			message = new MessageDto("info", "Info", "Tidak ada outlet KPL yang diproses!");
			return message;
		} else {
			String xras = cekKey.substring(0, 5);
			String tgl =  Objects.toString(repoCreateKpl.getFromDualString("TO_CHAR (SYSDATE, 'DD MON YYYY') AS tgl", "DUAL", " "), "");
			repoCreateKpl.insertData("ftable13B",
					"XKEY,XUSER,TGLGD,TGLUPDATE,KEYREG1,NIKRAS,KET,KEYPASS",
					" 'CRKPL', '"+xuser+"', '"+edtTglGudang+"','"+tgl+"', '"+p.getKeyRequest()+"', '"+xras+"', 'U/ proses  Tgl' || '"+edtTglGudangplus1+"', '"+p.getKeyProcess()+"'   ", "");
			
			message = new MessageDto("success", "Success", "Outlet KPL berhasil diproses! " + note);
			return message;
		}
		
		
		

	}

}
