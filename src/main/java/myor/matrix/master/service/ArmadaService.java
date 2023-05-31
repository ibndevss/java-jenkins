package myor.matrix.master.service;

import java.math.BigDecimal;
import java.util.List;

import myor.matrix.master.entity.ArmadaAllDto;
import myor.matrix.master.entity.ArmadaChoosenDto;
import myor.matrix.master.entity.EmployeeDto;
//import myor.matrix.master.entity.JenisKendaraanDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SelectItemKendaraan;

public interface ArmadaService {

	public List<ArmadaAllDto> getDataArmada();
	
	public List<ArmadaAllDto> getDataArmadaTemp();
	
	public List<SelectItemKendaraan<String>> getListJenisKendaraan();
	
	public List<SelectItem<String>> getListDriver();
	
	public List<SelectItem<String>> getStatusKendaraan();
	
	public List<EmployeeDto> getNameEmp(String empno);
	
	public List<SelectItem<String>> getListHelper();
	
	public List<ArmadaAllDto> insertDataArmada(String noKend, String jnsKend, String jenisBbm, String capacity, String kubikase,
			String kmAkhir, String statusAktif, String tonase, String jenisMuat, String driverCode, String helperCode,
			String statusKend, String stdIsi, String maxRit, String dedicated, String tglEfektif, String tglAkhir);
	
	public void deleteDataArmada(String noKend);
	
	public String deleteDataArmadaTemp(String noKend);
	
	public List<ArmadaAllDto> updateDataArmada(List<ArmadaAllDto> dataArmada);
	
	public List<Object[]> cekKendRekap (String carNo);
	
	public List<Object[]> cekKendDelivery (String noKend);
	
	public String cekKendar (String noKend);
	
	public String getXkey();
	
	public String cekKeyAuth(String kodeKey, String kodeAuth);
	
	public List<ArmadaAllDto> insertDataArmadaTemp(List<ArmadaAllDto> dataArmadaTemp);
	
	public List<ArmadaAllDto> deleteDataArmadaTempFinal(List<ArmadaAllDto> dataArmadaTemp);
	
	public boolean cekAntian(String nmTbl, String xUser);
	
	public void deleteAntrian(String nmTbl,String xUser);
	
	public void insertAntiran(String nmTbl, String xUser);
	
	public String getXkey (int x, int y, String xjam);
	
	public String cekKey (int x, int y, String kodeKey, String kodeAuth);
	
	
	
	
}
