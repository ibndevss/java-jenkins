package myor.matrix.master.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ConvUnitPcodeDto;
import myor.matrix.master.entity.DetailDate;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.UtilRepository;
import myor.matrix.master.service.UtilService;
import myor.matrix.master.util.Util;

@Service
public class UtilServiceImpl implements UtilService {
	
	@Autowired
	private UtilRepository utilRepository;
	
	@Autowired
	private Util util;
	
	@Override
	@Transactional
	public String getDateNowBySystem(){
		return utilRepository.getDateNowBySystem();	
	}
	
	@Override
	@Transactional
	public String getDateNowByWarehouse(){
		return utilRepository.getDateNowByWarehouse();
	}
	
	@Override
	public String getDateNowByWarehouseFormatYYYMMDD(){
		return utilRepository.getDateNowByWarehouseFormatYYYMMDD();
	}

	@Override
	public String cekParam(String xkey) {
		// TODO Auto-generated method stub
		return utilRepository.cekParam(xkey);
	}

	@Override
	public List<ConvUnitPcodeDto> getConvUnit(String pcode) {
		// TODO Auto-generated method stub
		return utilRepository.getConvUnit(pcode);
	}
	
	@Transactional
	@Override
	public String getAntrianNoSeq(String tableName, String field, String noSeq, String xkey, String userId, boolean withDelete) {
		// TODO Auto-generated method stub
		boolean antri1 = true;
		boolean antri2 = true;
		
		//Antrian 1
		while (antri1 == true) {
			int cekData = utilRepository.cekTableFrgAct(noSeq);
			if(cekData == 0) {
				utilRepository.insertTableFrgAct(noSeq,noSeq,userId);
				antri1 = false;
			}
		}
		
		//Antrian 2
		while(antri2 == true) {
			int cekMemoInt = utilRepository.cekMemoInt(xkey);
			int cekFieldByTable = utilRepository.cekFieldByTable(field,tableName,cekMemoInt);
			if(cekFieldByTable == 0) {
				utilRepository.updateFmemo(xkey);
				antri2 = false;
			} else {
				utilRepository.updateFmemo(xkey);
			}
		}
		
		if(withDelete) {
			utilRepository.delete(noSeq);
		}
			
		return utilRepository.getNoDoc(xkey);
	}
	
	@Transactional
	@Override
	public void insertPenggunaan(String tableName, String noSeq, String userId) {
		// TODO Auto-generated method stub
		int cekDataPengguna = utilRepository.cekPenggunaan(tableName,noSeq);
		if(cekDataPengguna == 0) {
			utilRepository.insertPenggunaan(tableName,noSeq,userId);
		} else {
			utilRepository.deletePenggunaan(tableName,noSeq,userId);
			utilRepository.insertPenggunaan(tableName, noSeq, userId);
		}
	}
	
	@Override
	public int cekPenggunaan(String tableName, String noSeq) {
		return utilRepository.cekPenggunaan(tableName, noSeq);
	}
	
	@Override
	@Transactional
	public List<String> cekPenggunaanDetail(String tableName, String noSeq, String user) {
		List<String> username = new ArrayList<>();
		List<String> result = new ArrayList<>();
		
		username = utilRepository.cekPenggunaanDetail(tableName, noSeq, user);
		
		if(!username.isEmpty()) {
			result.add("Nomor Document ini "+noSeq+", sedang digunakan oleh user "+ username.get(0) +" ");
		} else {
			deleteFprgactByNamaTableKey(tableName, noSeq);
			utilRepository.insertPenggunaan(tableName, noSeq, user);
		}
		return result;
	}

	@Override
	public List<SelectItem<String>> getPilihanOtorisasiTop() {
		// TODO Auto-generated method stub
		return utilRepository.getListFtable13ByXkey("XTOPONLINEOTO");
	}

	@Override
	public String getXqty(String pcode, String qty) {
		// TODO Auto-generated method stub
		return utilRepository.getXqty(pcode, qty);
	}
	
	@Override
	public DetailDate getDateDetail(String date) {
		// TODO Auto-generated method stub
		return utilRepository.getDateDetail(date);
	}
	
	@Transactional
	@Override
	public String getAntrianRO(String userId) {
		// TODO Auto-generated method stub
		return getAntrianNoSeq("FORDER_H", "ORDERNO", "SEQ10", "REALORDER", "SEQ10", false);
	}
	
	@Transactional
	@Override
	public String getAntrianStockOutlet(String userId) {
		// TODO Auto-generated method stub
		return getAntrianNoSeq("FTABEL38", "DATA1", "SEQ30", "NOSTOCKTK", userId, true);
	}

	@Override
	public String getTimeNow() {
		// TODO Auto-generated method stub
		return utilRepository.getTimeNow();
	}

	@Override
	public String getSequence(String sequenceName, String fieldName, String tableName) {
		// TODO Auto-generated method stub
		long seq = 0;
		String result = "";
		boolean validasi = false;
		
		boolean antrian = true;
		
		while (antrian == true) {
			seq = utilRepository.getNextVal(sequenceName);
			validasi = utilRepository.isDataExist(fieldName, tableName, seq);
			if (validasi == false) {
				antrian = false;
			}
		}
		
		result = ""+seq;		
		
		return result;
	}
	
	@Override
	public List<String> getXkeyAndAuth (int x, int y, String xjam) {
		List<String> hasil = new ArrayList<>();
		String xkey5,xkey6,xkey7,xstr2="";
		int xkey1,xkey2,xkey3 = 0;
		int xkey10,xkey13 = 0;

		xkey1 = Integer.parseInt(xjam.substring(0,2));
		xkey2 = Integer.parseInt(xjam.substring(3,5));
		xkey3 = Integer.parseInt(xjam.substring(6,8));
		xkey13 = Integer.parseInt(xjam.substring(7,8));
		
		xkey1 = xkey1 + 1;
		xkey2 = xkey2 + 3;
		xkey3 = xkey3 + 2;
		
		xkey5 = Integer.toString(xkey1);
		xkey6 = Integer.toString(xkey2);
		xkey7 = Integer.toString(xkey3);
						
		if (xkey5.length() == 2) {
			xkey5 = xkey5;
		} else {
			xkey5 = "0"+xkey5;
		}
		
		if (xkey6.length() == 2) {
			xkey6 = xkey6;
		} else {
			xkey6 = "0"+xkey6;
		}
		
		if (xkey7.length() == 2) {
			xkey7 = xkey7;
		} else {
			xkey7 = "0"+xkey7;
		}
		
		xstr2 = (xkey5+xkey6+xkey7) + (Integer.toString(xkey13));
		xkey10 = (Integer.parseInt(xstr2)*x)+y;
		hasil.add(xstr2);
		hasil.add(String.valueOf(xkey10));
		return hasil;
	}
	
	@Override
	public String getXkey (int x, int y, String xjam) {
		
		String xkey5,xkey6,xkey7,xstr2="";
		int xkey1,xkey2,xkey3 = 0;
		int xkey10,xkey13 = 0;

		xkey1 = Integer.parseInt(xjam.substring(0,2));
		xkey2 = Integer.parseInt(xjam.substring(3,5));
		xkey3 = Integer.parseInt(xjam.substring(6,8));
		xkey13 = Integer.parseInt(xjam.substring(7,8));
		
		xkey1 = xkey1 + 1;
		xkey2 = xkey2 + 3;
		xkey3 = xkey3 + 2;
		
		xkey5 = Integer.toString(xkey1);
		xkey6 = Integer.toString(xkey2);
		xkey7 = Integer.toString(xkey3);
						
		if (xkey5.length() == 2) {
			xkey5 = xkey5;
		} else {
			xkey5 = "0"+xkey5;
		}
		
		if (xkey6.length() == 2) {
			xkey6 = xkey6;
		} else {
			xkey6 = "0"+xkey6;
		}
		
		if (xkey7.length() == 2) {
			xkey7 = xkey7;
		} else {
			xkey7 = "0"+xkey7;
		}
		
		xstr2 = (xkey5+xkey6+xkey7) + (Integer.toString(xkey13));
		xkey10 = (Integer.parseInt(xstr2)*x)+y;
		
		return Integer.toString(xkey10);
	}
	
	@Override
	public String cekKey (int x, int y, String kodeKey, String kodeAuth) {
		
		String xkey21,xkey22,xkey23,xkey24,xkey25,xkey26,xkey27,xkey31,xkey32,
				xkey33,xkey34,xkey35,xkey36,xkey37,xkey12,xkey28,xkey38,xkey39 = "";
		
		int xkey8,xkey9,xkey10,xkey11,xkey13 = 0;
		int xkey16,xkey17,xkey19,xkey20 = 0;
		
		double xkey18 = 0;
		
		String hasil = "";

		try {
			xkey21 = kodeAuth.substring(0,1);
			xkey22 = kodeAuth.substring(2,3);
			xkey23 = kodeAuth.substring(4,5);
			xkey24 = kodeAuth.substring(6,7);
			xkey25 = kodeAuth.substring(8,9);
			xkey26 = kodeAuth.substring(10,11);
			
			xkey31 = kodeAuth.substring(1,2);
			xkey32 = kodeAuth.substring(3,4);
			xkey33 = kodeAuth.substring(5,6);
			xkey34 = kodeAuth.substring(7,8);
			xkey35 = kodeAuth.substring(9,10);
			xkey36 = kodeAuth.substring(11,12);
			
			xkey27 = xkey21 + xkey22 + xkey23 + xkey24 + xkey25 + xkey26;
			xkey37 = xkey31 + xkey32 + xkey33 + xkey34 + xkey35 + xkey36;
			
			xkey10 = (Integer.parseInt(kodeKey)* x ) + y;
			xkey12 = Integer.toString(xkey10);
			xkey12 = xkey12.substring(0,6);
			
			xkey16 = Integer.parseInt(xkey27);
			
			xkey28 = kodeKey.substring(kodeKey.length() - 1);
			
			xkey17 = Integer.parseInt(xkey28);
			
			xkey39 = kodeKey.substring(kodeKey.length() - 1);
			
			if (xkey39.equalsIgnoreCase("0")) {
				xkey18 = xkey16;
			} else {
				xkey18 = (xkey16 / xkey17);
			}
			
			xkey38 =  Double.toString(xkey18);
			
			
			
			if (!xkey12.equalsIgnoreCase(xkey37)) {
				hasil = "NOTOK";
			} else {
				hasil = xkey38;
			}
		} catch (Exception e) {
			// TODO: handle exception
			hasil = "NOTOK";
		}
		
		return hasil;
	}
	
	@Override
	public List<String> cekValAndKey38 (int x, int y, String kodeKey, String kodeAuth) {
		List<String> hasil2 = new ArrayList<>();
		String xkey21,xkey22,xkey23,xkey24,xkey25,xkey26,xkey27,xkey31,xkey32,
				xkey33,xkey34,xkey35,xkey36,xkey37,xkey12,xkey28,xkey38 ="",xkey39 = "";
		
		int xkey8,xkey9,xkey10,xkey11,xkey13 = 0;
		int xkey16,xkey17,xkey19,xkey20 = 0;
		
		double xkey18 = 0;
		
		String hasil = "";

		try {
			xkey21 = kodeAuth.substring(0,1);
			xkey22 = kodeAuth.substring(2,3);
			xkey23 = kodeAuth.substring(4,5);
			xkey24 = kodeAuth.substring(6,7);
			xkey25 = kodeAuth.substring(8,9);
			xkey26 = kodeAuth.substring(10,11);
			
			xkey31 = kodeAuth.substring(1,2);
			xkey32 = kodeAuth.substring(3,4);
			xkey33 = kodeAuth.substring(5,6);
			xkey34 = kodeAuth.substring(7,8);
			xkey35 = kodeAuth.substring(9,10);
			xkey36 = kodeAuth.substring(11,12);
			
			xkey27 = xkey21 + xkey22 + xkey23 + xkey24 + xkey25 + xkey26;
			xkey37 = xkey31 + xkey32 + xkey33 + xkey34 + xkey35 + xkey36;
			
			xkey10 = (Integer.parseInt(kodeKey)* x ) + y;
			xkey12 = Integer.toString(xkey10);
			xkey12 = xkey12.substring(0,6);
			
			xkey16 = Integer.parseInt(xkey27);
			
			xkey28 = kodeKey.substring(kodeKey.length() - 1);
			
			xkey17 = Integer.parseInt(xkey28);
			
			xkey39 = kodeKey.substring(kodeKey.length() - 1);
			
			if (xkey39.equalsIgnoreCase("0")) {
				xkey18 = xkey16;
			} else {
				xkey18 = (xkey16 / xkey17);
			}
			
			xkey38 =  Double.toString(xkey18);
			
			
			if (!xkey12.equalsIgnoreCase(xkey37)) {
				hasil = "NOTOK";
			} else {
				hasil = xkey38;
			}
		} catch (Exception e) {
			// TODO: handle exception
			hasil = "NOTOK";
		}
		hasil2.add(xkey38);
		hasil2.add(hasil);
		return hasil2;
	}
	
	@Override
	public Boolean dmCheckPola(String pola_k, String edtWeek) {
		
		Integer xx = Integer.valueOf(edtWeek);
		Integer i = 0;
		List<String> hasil = new ArrayList<>();
		
		while (i <= 53) {
			for (int x = 1; x <= 4; x++) {
				if (i <= 53) {
					String xpola = pola_k.substring(x-1, x);
					hasil.add(xpola);
				}
			}
			i++;
		}
		String pola = "";
		for (int j = 0; j <= hasil.size(); j++) {
			if (j == xx) {
				pola = hasil.get(j-1);
			}
		}
		
		Boolean result;
		if (pola.equalsIgnoreCase("Y")) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}

	@Override
	@Transactional
	public void deleteFprgactByNamaTableKey(String namaTable, String key) {
		util.deleteFprgactByNamaTableKey(namaTable, key);
	}
	
	@Override
	@Transactional
	public void deleteFprgactByNamaTableAndUser(String namaTable, String user) {
		util.deleteFprgactByNamaTableAndUser(namaTable, user);
	}

	@Override
	public String generateBarcode(String xstr) {
		// TODO Auto-generated method stub
		String barcode=""; String ssl; String sisip;
		Integer a1; Integer a2;Integer a3;Integer a4;Integer a5;Integer a6;Integer a7;Integer hasil;
		Double xhasil; Double xkali; Double xtambah;
		
		a1 = Integer.valueOf(xstr.substring(4, 5))*8;
		a2 = Integer.valueOf(xstr.substring(5, 6))*7;
		a3 = Integer.valueOf(xstr.substring(10, 11))*6;
		a4 = Integer.valueOf(xstr.substring(11, 12))*5;
		a5 = Integer.valueOf(xstr.substring(12, 13))*4;
		a6 = Integer.valueOf(xstr.substring(13, 14))*3;
		a7 = Integer.valueOf(xstr.substring(14, 15))*2;
		
		hasil = (a1+a2+a3+a4+a5+a6+a7);
		xhasil = Double.valueOf(hasil/11);
		xhasil = xhasil * 11;
		
		xhasil = (a1+a2+a3+a4+a5+a6+a7) - xhasil;
		sisip = "0";
		
		if (xhasil == 0) {
			sisip = "1";
		} else if (xhasil == 1) {
			sisip = "0";
		} else {
			sisip = String.valueOf(11-xhasil);
		}
		ssl = xstr.substring(4, 5) + xstr.substring(5, 6) + xstr.substring(10, 11) + sisip + xstr.substring(11, 12) + xstr.substring(12, 13) + xstr.substring(13, 14) + xstr.substring(14, 15);  
		barcode = xstr.substring(0, 10) + ssl.substring(2, 8);
		return barcode;
	}
	

}
