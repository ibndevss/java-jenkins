package myor.matrix.master.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ArmadaAllDto;
import myor.matrix.master.entity.ArmadaChoosenDto;
import myor.matrix.master.entity.EmployeeDto;
//import myor.matrix.master.entity.JenisKendaraanDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SelectItemKendaraan;
import myor.matrix.master.repository.ArmadaRepository;
import myor.matrix.master.repository.CekRepository;
import myor.matrix.master.repository.impl.ArmadaRepositoryImpl;
import myor.matrix.master.service.ArmadaService;
import myor.matrix.master.util.Util;

@Service
public class ArmadaServiceImpl implements ArmadaService{

	@Autowired
	private ArmadaRepository armadaRepository;
	
	@Autowired
	private Util util;
	
	@Override
	public List<ArmadaAllDto> getDataArmada() {
		// TODO Auto-generated method stub
		return armadaRepository.getDataArmada();
	}

	@Override
	public List<SelectItemKendaraan<String>> getListJenisKendaraan() {
		// TODO Auto-generated method stub
		return armadaRepository.getListJenisKendaraan();
	}

	@Override
	public List<SelectItem<String>> getListDriver() {
		// TODO Auto-generated method stub
		return armadaRepository.getListDriver();
	}

	@Override
	public List<SelectItem<String>> getListHelper() {
		// TODO Auto-generated method stub
		return armadaRepository.getListHelper();
	}

	@Override
	public List<EmployeeDto> getNameEmp(String empno) {
		// TODO Auto-generated method stub
		return armadaRepository.getNameEmp(empno);
	}

	@Transactional
	@Override
	public List<ArmadaAllDto> insertDataArmada(String noKend, String jnsKend, String jenisBbm, String capacity,
			String kubikase, String kmAkhir, String statusAktif, String tonase, String jenisMuat, String driverCode,
			String helperCode, String statusKend, String stdIsi, String maxRit, String dedicated, String tglEfektif, String tglAkhir) {
		// TODO Auto-generated method stub
		
		try {
			armadaRepository.insertDataArmada(noKend, jnsKend, jenisBbm, capacity, kubikase, kmAkhir,
					statusAktif, tonase, jenisMuat, driverCode, helperCode, statusKend, stdIsi, maxRit,
					dedicated, tglEfektif, tglAkhir);
			System.out.println("Data Berhasil");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ada Error");
		}
		
		return armadaRepository.getDataArmada();
	}

	@Transactional
	@Override
	public void deleteDataArmada(String noKend) {
		// TODO Auto-generated method stub
		armadaRepository.deleteDataArmada(noKend);
		System.out.println("Data Berhasil Terhapus");
	}

	@Transactional
	@Override
	public List<ArmadaAllDto> updateDataArmada(List<ArmadaAllDto> dataArmada) {
		// TODO Auto-generated method stub
		for (ArmadaAllDto da : dataArmada) {
			
			armadaRepository.updateDataArmada(da.getNoKend(), da.getJnsKend(), da.getJenisBbm(), da.getCapacity(),
					da.getKubikase(), da.getKmAkhir(), da.getStatusAktif(), da.getDriverCode(), da.getHelperCode(),
					da.getStatusKend(), da.getStdIsi(), da.getJenisMuat(), da.getTonase(), da.getMaxRit(), da.getDedicatedData(),
					da.getTglEfektif(), da.getAkhirEfektif());
		}
		return armadaRepository.getDataArmada();
	}

	@Override
	public List<Object[]> cekKendRekap(String carNo) {

		// TODO Auto-generated method stub
		return armadaRepository.cekKendRekap(carNo);
		
	}

	@Override
	public List<Object[]> cekKendDelivery(String noKend) {
		// TODO Auto-generated method stub
		return armadaRepository.cekKendDelivery(noKend);
	}

	@Override
	public String cekKendar(String noKend) {
		// TODO Auto-generated method stub
		List<Object[]> rslt = armadaRepository.cekKendar(noKend);
		String cekin;
		if(rslt.size() > 0) {
			cekin = "Ada";
		}else {
			cekin = "Blm";
		}	
		
		return cekin;
	}

	@Override
	public List<SelectItem<String>> getStatusKendaraan() {
		// TODO Auto-generated method stub
		return armadaRepository.getStatusKendaraan();
	}

	@Override
	public List<ArmadaAllDto> getDataArmadaTemp() {
		// TODO Auto-generated method stub
		return armadaRepository.getDataArmadaTemp();
	}
	
	@Transactional
	@Override
	public String deleteDataArmadaTemp(String noKend) {
		// TODO Auto-generated method stub
		String cek="Failed Del Temp";
		try {
			armadaRepository.deleteDataArmadaTemp(noKend);
			return cek="Success Del Temp";
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			return cek="Failed Del Temp";
		}
	
	}

	@Override
	public String getXkey() {
		// TODO Auto-generated method stub
		String xTime = util.getJamSystem();
		String xkey = getXkey(23, 25, xTime);
		return xkey;
	}

	@Transactional
	@Override
	public List<ArmadaAllDto> insertDataArmadaTemp(List<ArmadaAllDto> dataArmadaTemp) {
		// TODO Auto-generated method stub
		try {
			
			for (ArmadaAllDto da : dataArmadaTemp) {
				armadaRepository.deleteDataArmadaTemp(da.getNoKend());
				
				armadaRepository.insertDataArmadaTemp(da.getNoKend(), da.getJnsKend(), da.getJenisBbm(), da.getCapacity().toString(),
						da.getKubikase().toString(), da.getKmAkhir().toString(), da.getStatusAktif(), da.getTonase().toString(), da.getJenisMuat(),
						da.getDriverCode(), da.getHelperCode(), da.getStatusKend(), da.getStdIsi().toString(), da.getMaxRit().toString(),
						da.getDedicatedData(), da.getTglEfektif(), da.getAkhirEfektif(), da.getXkey());
			}
			
			return armadaRepository.getDataArmadaTemp();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			return armadaRepository.getDataArmadaTemp();
		}
		
		
	}

	@Override
	public String cekKeyAuth(String kodeKey, String kodeAuth) {
		// TODO Auto-generated method stub
		String result = cekKey(23, 25, kodeKey, kodeAuth);
		return result;
	}

	@Override
	public boolean cekAntian(String nmTbl, String xUser) {
		// TODO Auto-generated method stub
		boolean cek;
		cek = util.cekFprgactByNamaTableAndNoKey(nmTbl, xUser);	
		return cek;
	}

	@Transactional
	@Override
	public void deleteAntrian(String nmTbl,String xUser) {
		// TODO Auto-generated method stub
		util.deleteFprgactByNamaTableAndUser(nmTbl, xUser);
	}

	@Transactional
	@Override
	public void insertAntiran(String nmTbl, String xUser) {
		// TODO Auto-generated method stub
		String nokey = "queArmada";
		util.insertFprgact(nmTbl, nokey, xUser);
	}

	@Transactional
	@Override
	public List<ArmadaAllDto> deleteDataArmadaTempFinal(List<ArmadaAllDto> dataArmadaTemp) {
		// TODO Auto-generated method stub
		try {
			
			for (ArmadaAllDto da : dataArmadaTemp) {
				armadaRepository.deleteDataArmadaTemp(da.getNoKend());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
		return armadaRepository.getDataArmadaTemp();
	}

	@Override
	public String getXkey(int x, int y, String xjam) {
		// TODO Auto-generated method stub
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
	public String cekKey(int x, int y, String kodeKey, String kodeAuth) {
		// TODO Auto-generated method stub
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


//	@Override
//	public List<SelectItem<String>> cek(String carNo) {
//		// TODO Auto-generated method stub
//		List<SelectItem<String>> result = new ArrayList<>();
//		
//		List<Object[]> hasil = armadaRepository.cek(carNo);
//		
//		for(Object[] o : hasil){
//			SelectItem<String> s = new SelectItem<String>((String)o[0],(String) o[1]);
//			result.add(s);
//		}
//		return result;
//	}

}
