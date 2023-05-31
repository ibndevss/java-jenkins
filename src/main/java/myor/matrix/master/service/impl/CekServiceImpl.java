package myor.matrix.master.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ArmadaAllDto;
import myor.matrix.master.entity.CekAllDto;
import myor.matrix.master.entity.CekBankChoosen;
import myor.matrix.master.entity.CekChoosen;
import myor.matrix.master.entity.CekCustChoosen;
import myor.matrix.master.entity.CekCustDto;
import myor.matrix.master.entity.CekDto;
import myor.matrix.master.entity.CekNoCheck;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.CekRepository;
import myor.matrix.master.service.CekService;

@Service
public class CekServiceImpl implements CekService {
	
	@Autowired
	private CekRepository cekRepository;

	@Override
	public List<CekDto> getDataCek() {
		// TODO Auto-generated method stub
		return cekRepository.getDataCek();
	}

	@Override
	public CekChoosen getCekChoosen(String checkNo) {
		// TODO Auto-generated method stub
		return cekRepository.getCekChoosen(checkNo);
	}
	
	@Override
	public List<SelectItem<String>> getDataBank() {
		// TODO Auto-generated method stub
		return cekRepository.getDataBank();
	}

	@Override
	public List<CekCustDto> getDataCust() {
		// TODO Auto-generated method stub
		return cekRepository.getDataCust();
	}

	@Override
	public List<SelectItem<String>> getDataBankCollection() {
		// TODO Auto-generated method stub
		return cekRepository.getDataBankCollection();
	}

	@Transactional
	@Override
	public List<CekAllDto> insertDataCek(String checkNo, String checkDate, String checkDuedT, String bank,
			String accNo, String checkAmount, String custNo, String flag, String bankCair, String userId) {
		// TODO Auto-generated method stub
		
		try {
			cekRepository.insertDataCek(checkNo, checkDate, checkDuedT, bank, accNo, checkAmount, custNo, flag, bankCair, userId);
			System.out.println("Data Berhasil");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Ada Error");
		}
		
		return cekRepository.getDataAllCek();
	}
	
	@Transactional
	@Override
	public List<CekAllDto> insertDataTransfer(String checkNo, String checkDate, String bank, String accNo,
			String checkAmount, String custNo, String flag, String bankTransfer) {
		// TODO Auto-generated method stub
		try {
			cekRepository.insertDataTransfer(checkNo, checkDate, bank, accNo, checkAmount, custNo, flag, bankTransfer);
			System.out.println("Data Berhasil");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Ada Error");
		}
		
		return cekRepository.getDataAllCek();
	}

	@Override
	public List<CekAllDto> getDataAllCek() {
		// TODO Auto-generated method stub
		return cekRepository.getDataAllCek();
	}

	@Override
	public List<CekBankChoosen> getCekBank(String bankNo) {
		// TODO Auto-generated method stub
		return cekRepository.getCekBank(bankNo);
	}

	@Override
	public List<CekCustChoosen> getCekCust(String custNo) {
		// TODO Auto-generated method stub
		return cekRepository.getCekCust(custNo);
	}

	@Override
	public List<CekBankChoosen> getCekBankCollection(String bankNo) {
		// TODO Auto-generated method stub
		return cekRepository.getCekBankCollection(bankNo);
	}

	@Transactional
	@Override
	public void deleteDataCek(String checkNo) {
		// TODO Auto-generated method stub
		cekRepository.deleteDataCek(checkNo);
	}

	@Transactional
	@Override
	public CekChoosen updateDataCek(CekChoosen dataCek) {
		// TODO Auto-generated method stub
		cekRepository.updateDataCek(dataCek.getCheckNo(), dataCek.getCheckDate(), dataCek.getCheckDuedT(),
				dataCek.getBank(), dataCek.getAccNo(), dataCek.getCheckAmount().toString(), dataCek.getCustNo(),
				dataCek.getFlag(), dataCek.getBankCair(), dataCek.getBankTransfer());
		return getCekChoosen(dataCek.getCheckNo());
	}


	@Override
	public String getCheckNo (String checkNo) {
		// TODO Auto-generated method stub
			return cekRepository.getCheckNo (checkNo);		
	}

	@Override
	public String getTransferNo(String checkNo) {
		
		// TODO Auto-generated method stub		

			try {
				return cekRepository.getTransferNo(checkNo);
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
			
	}

	@Override
	public List<SelectItem<String>> getDateCek(String checkNo) {
		// TODO Auto-generated method stub		
		
		return cekRepository.getDateCek(checkNo);
	}

	@Transactional
	@Override
	public void insertPenggunaan(String tableName, String noSeq, String userId) {
		// TODO Auto-generated method stub
		int cekDataPengguna = cekRepository.cekPenggunaan(tableName,noSeq);
		if(cekDataPengguna == 0) {
			cekRepository.insertPenggunaan(tableName,noSeq,userId);
		} else {
			cekRepository.deletePenggunaan(tableName,noSeq,userId);
			cekRepository.insertPenggunaan(tableName, noSeq, userId);
		}
	}

	@Override
	public String getMemointPatamTf() {
		// TODO Auto-generated method stub
		return cekRepository.getMemointTransfer();
	}

	@Override
	public int getKeyAuth() {
		// TODO Auto-generated method stub
		String xkey1,xkey2,xkey3,xkey4,xkey13;
		String xkey5,xkey6,xkey7,xkey12;
		int xkey10;
		String dateTime = cekRepository.getKeyAuth();
		
		xkey1 = dateTime.substring(0, 2);
		xkey2 = dateTime.substring(3, 5);
		xkey3 = dateTime.substring(6, 8);
		xkey13 = dateTime.substring(7, 8);
		
		xkey1 = String.valueOf(Integer.valueOf(xkey1) + 1);
		xkey2 = String.valueOf(Integer.valueOf(xkey2) + 2);
		xkey3 = String.valueOf(Integer.valueOf(xkey3) + 3);
		
		xkey5 = xkey1;
		xkey6 = xkey2;
		xkey7 = xkey3;
		
		if(xkey5.length() == 2) {
			xkey5 = xkey5;
		}else {
			xkey5 = "0"+xkey5;
		}
		
		if(xkey6.length() == 2) {
			xkey6 = xkey6;
		}else {
			xkey6 = "0"+xkey6;
		}
		
		if(xkey7.length() == 2) {
			xkey7 = xkey7;
		}else {
			xkey7 = "0"+xkey7;
		}
		
		String oke = xkey5+xkey6+xkey7+xkey13;
		xkey10 = Integer.valueOf(oke)*Integer.valueOf("09") + 21;
		
		System.out.println(xkey5 + " " + xkey6 + " " + xkey7);
//		int a = xkey10;
		
		try {
			return xkey10;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
		
	}

	@Override
	public String getKeyOk(String key, String auth) {
		// TODO Auto-generated method stub
		try {
			System.out.println("AiyayaCaptain");
			return cekRepository.getKeyOk(key, auth);			
		} catch (Exception e) {
			// TODO: handle exception
			String a = "TurnAroundCaptain";
			return a;
		}
	}

	@Override
	@Transactional
	public String getDateNowBySystem() {
		// TODO Auto-generated method stub
		return cekRepository.getDateNowBySystem();
	}



	
	
	

}
