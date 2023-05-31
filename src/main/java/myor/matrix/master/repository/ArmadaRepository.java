package myor.matrix.master.repository;

import java.math.BigDecimal;
import java.util.List;

import myor.matrix.master.entity.ArmadaAllDto;
import myor.matrix.master.entity.ArmadaChoosenDto;
import myor.matrix.master.entity.EmployeeDto;
//import myor.matrix.master.entity.JenisKendaraanDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SelectItemKendaraan;

public interface ArmadaRepository {

	public List<ArmadaAllDto> getDataArmada();
	
	public List<ArmadaAllDto> getDataArmadaTemp();
	
	public List<SelectItemKendaraan<String>> getListJenisKendaraan();
	
	public List<SelectItem<String>> getStatusKendaraan();
	
	public List<SelectItem<String>> getListDriver();
	
	public List<EmployeeDto> getNameEmp(String empno);
	
	public List<SelectItem<String>> getListHelper();
	
	public void insertDataArmada(String noKend, String jnsKend, String jenisBbm, String capacity, String kubikase,
			String kmAkhir, String statusAktif, String tonase, String jenisMuat, String driverCode, String helperCode,
			String statusKend, String stdIsi, String maxRit, String dedicated, String tglEfektif, String tglAkhir);
	
	public void deleteDataArmada(String noKend);
	
	public void deleteDataArmadaTemp(String noKend);
	
	public void updateDataArmada(String noKend, String jnsKend, String jenisBbm, BigDecimal capacity, BigDecimal kubikase,
			BigDecimal kmAkhir, String statusAktif, String driverCode, String helperCode,
			String statusKend, BigDecimal stdIsi, String jenisMuat, BigDecimal tonase, BigDecimal ritase, String dedicated,
			String tglEfektif, String akhirEfektif);

	public String getDateNowBySystem();
	
	public String getDateNowByWarehouse();
	
	public List<Object[]> cekKendRekap (String carNo);
	
	public List<Object[]> cekKendDelivery (String noKend);
	
	public List<Object[]> cekKendar (String noKend);
	
	public void insertDataArmadaTemp(String noKend, String jnsKend, String jenisBbm, String capacity, String kubikase,
			String kmAkhir, String statusAktif, String tonase, String jenisMuat, String driverCode, String helperCode,
			String statusKend, String stdIsi, String maxRit, String dedicated, String tglEfektif, String tglAkhir, String xkey);
	
	
}
