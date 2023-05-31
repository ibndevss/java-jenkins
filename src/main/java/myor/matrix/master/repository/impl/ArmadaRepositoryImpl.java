package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.BeanProperty.Bogus;

import myor.matrix.master.entity.ArmadaAllDto;
import myor.matrix.master.entity.ArmadaChoosenDto;
import myor.matrix.master.entity.EmployeeDto;
//import myor.matrix.master.entity.JenisKendaraanDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.SelectItemKendaraan;
import myor.matrix.master.repository.ArmadaRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ArmadaRepositoryImpl implements ArmadaRepository{

	@Autowired
	private TenantContext tc;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ArmadaAllDto> getDataArmada() {
		// TODO Auto-generated method stub
		List<ArmadaAllDto> result = new ArrayList<>();
		
		String sql = "select no_kend, jns_kend, "
				+ "CASE" + 
				"    WHEN jenis_bbm = '1'" + 
				"    THEN '1-PREMIUM'" + 
				"    WHEN jenis_bbm = '2'" + 
				"    THEN '2-SOLAR'" + 
				"    WHEN jenis_bbm = '3'" + 
				"    THEN '3-PERTAMAX'" +
				"    WHEN jenis_bbm = '4'" + 
				"    THEN '4-PERTALITE'" +
				"    ELSE '5-LAINYA'" + 
				"    END jenisbbm, capacity, kubikase, km_akhir, statusaktif, weightload, jenis_muat,"
				+ " drivercode, helpercode, status_kend, std_isi, std_isi2, maxrit, dedicated_data, to_char(tgl_efektif, 'DD MON YYYY') AS tgl_efektif, to_char(tgl_akhir, 'DD MON YYYY') AS tgl_akhir from "+tc.getTenantId()+".ftable03 order by no_kend";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for (Object[] o : obj) {
			ArmadaAllDto a = new ArmadaAllDto( (String) o[0], (String) o[1], (String) o[2], (BigDecimal) o[3], (BigDecimal) o[4], (BigDecimal) o[5], (String) o[6], (BigDecimal) o[7], 
					(String) o[8], (String) o[9], (String) o[10], (String) o[11], (BigDecimal) o[12], (BigDecimal) o[13], (BigDecimal) o[14],  0, (String) o[15], (String) o[16], (String) o[17]);
			result.add(a);
		}
		
		return result;
	}

	@Override
	public List<SelectItemKendaraan<String>> getListJenisKendaraan() {
		// TODO Auto-generated method stub
		List<SelectItemKendaraan<String>> result = new ArrayList<>();
		String sql = "select jenis_kendaraan, kubikasi, std_isi, tonase from "+tc.getTenantId()+".fvehicle_h order by jenis_kendaraan";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for(Object[] o : obj) {
			SelectItemKendaraan<String> a = new SelectItemKendaraan<String>((String) o[0], (BigDecimal) o[1], (BigDecimal) o[2], (BigDecimal) o[3]);
		result.add(a);
		}
		return result;
	}

	@Override
	public List<SelectItem<String>> getListDriver() {
		// TODO Auto-generated method stub
		String sql = "select empno, empname from "+tc.getTenantId()+".femployee where empopr='D' order by empno";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		return result.stream().map(o -> new SelectItem<>((String) o[0], (String) o[1]))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<EmployeeDto> getNameEmp(String empno) {
		// TODO Auto-generated method stub
		List<EmployeeDto> result = new ArrayList<>();
		
		String sql = "select empno, empname from "+tc.getTenantId()+".femployee where empno = '"+empno+"' order by empno";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for (Object[] o : obj) {
			EmployeeDto a = new EmployeeDto( (String) o[0], (String) o[1]);
			result.add(a);
		}
		
		return result;
	}

	@Override
	public List<SelectItem<String>> getListHelper() {
		// TODO Auto-generated method stub
		String sql = "select empno, empname from "+tc.getTenantId()+".femployee where empopr='H' order by empno";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		return result.stream().map(o -> new SelectItem<>((String) o[0], (String) o[1]))
				.collect(Collectors.toList());
	}

	@Override
	public void insertDataArmada(String noKend, String jnsKend, String jenisBbm, String capacity, String kubikase,
			String kmAkhir, String statusAktif, String tonase, String jenisMuat, String driverCode, String helperCode,
			String statusKend, String stdIsi, String maxRit, String dedicated, String tglEfektif, String tglAkhir) {
		// TODO Auto-generated method stub
		String sql = "insert into "+tc.getTenantId()+".ftable03 (NO_KEND, JNS_KEND, JENIS_BBM, CAPACITY, KUBIKASE, "
				+ "KM_AKHIR, STATUSAKTIF, WEIGHTLOAD, JENIS_MUAT, DRIVERCODE, HELPERCODE, STATUS_KEND, STD_ISI, " 
				+ "MAXRIT, DEDICATED_DATA, TGL_EFEKTIF, TGL_AKHIR) values ('"+noKend+"', '"+jnsKend+"', '"+jenisBbm+"', '"+capacity+"', '"+kubikase+"', '"+kmAkhir+"',"
				+ " '"+statusAktif+"', '"+tonase+"', '"+jenisMuat+"', '"+driverCode+"', '"+helperCode+"', '"+statusKend+"', "
				+ " '"+stdIsi+"', '"+maxRit+"', '"+dedicated+"', '"+tglEfektif+"', '"+tglAkhir+"') ";
		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteDataArmada(String noKend) {
		// TODO Auto-generated method stub
		String sql = "delete from "+tc.getTenantId()+".ftable03 where no_kend = '"+noKend+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public String getDateNowBySystem(){
		String sql = "select to_char(sysdate, 'DD MON YYYY') tgl from dual";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
	
	@Override
	public String getDateNowByWarehouse(){
		String sql = "Select to_char(MEMODATE,'DD MON YYYY') AS warehouse_date from "+tc.getTenantId()+".FMEMO where MEMONAMA='CADATE'";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}


	@Override
	public void updateDataArmada(String noKend, String jnsKend, String jenisBbm, BigDecimal capacity, BigDecimal kubikase,
			BigDecimal kmAkhir, String statusAktif, String driverCode, String helperCode, String statusKend, BigDecimal stdIsi,
			String jenisMuat, BigDecimal tonase, BigDecimal ritase, String dedicated, String tglEfektif, String akhirEfektif) {
		// TODO Auto-generated method stub
		
		String tglUpd = getDateNowBySystem();
		String tglUpdgd = getDateNowByWarehouse();
		String jnsKendK = "";
		String jenisBbmK = "";
		int capaCityK = 0;
		int kubikaseK = 0;
		int kmAkhirK = 0;
		int tonaseK = 0;
		int ritaseK = 0;
		String statusAktifK = "";
		String jenisMuatK ="";
		String driverCodeK = "";
		String helperCodeK ="";
		String statusKendK ="";
		String dedicatedK ="";
		String tglEfektifK ="";
		String akhirEfektifK ="";
		int stdIsiK = 0;
//		int stdIsi2K = 0;
		
		if(jnsKend != null) {
			jnsKendK = jnsKend;
		}
		if(jnsKend != null) {
			jenisBbmK = jenisBbm;
		}
		if(capacity != null) {
			capaCityK = capacity.intValue();
		}
		if(!(kubikase == null)) {
			kubikaseK = kubikase.intValue();
		}
		if(!(kmAkhir == null)) {
			kmAkhirK = kmAkhir.intValue();
		}
		if(statusAktif != null) {
			statusAktifK = statusAktif;
		}
		if(driverCode != null) {
			driverCodeK = driverCode;
		}
		if(helperCode != null) {
			helperCodeK = helperCode;	
		}
		if(statusKend != null) {
			statusKendK = statusKend;	
		}
		if(!(stdIsi == null)) {
			stdIsiK = stdIsi.intValue();	
		}
		if(!(tonase == null)) {
			tonaseK = tonase.intValue();	
		}
		if(!(ritase == null)) {
			ritaseK = ritase.intValue();	
		}
		if(jenisMuat != null) {
			jenisMuatK = jenisMuat;	
		}
		if(dedicated != null) {
			dedicatedK = dedicated;
		}
		if(tglEfektif != null) {
			tglEfektifK = tglEfektif;
		}
		if(akhirEfektif != null) {
			akhirEfektifK = akhirEfektif;
		}
		
//		if(!(jnsKend.equalsIgnoreCase("") || jnsKend.equals(null))) {
//			jnsKendK = jnsKend;
//		}
//		if(!(jenisBbm.equalsIgnoreCase("") || jenisBbm.equals(null))) {
//			jenisBbmK = jenisBbm;
//		}
//		if(!( capacity == null)) {
//			capaCityK = capacity.intValue();
//		}
//		if(!(kubikase == null)) {
//			kubikaseK = kubikase.intValue();
//		}
//		if(!(kmAkhir == null)) {
//			kmAkhirK = kmAkhir.intValue();
//		}
//		if(!(statusAktif.equalsIgnoreCase("") || statusAktif.equals(null))) {
//			statusAktifK = statusAktif;
//		}
//		if(!(driverCode.equalsIgnoreCase("")) || driverCode.equals(null)) {
//			driverCodeK = driverCode;
//		}
//		if(!(helperCode == null)) {
//			helperCodeK = helperCode;	
//		}
//		if(!(statusKend.equalsIgnoreCase("") || statusKend.equals(null))) {
//			statusKendK = statusKend;	
//		}
//		if(!(stdIsi == null)) {
//			stdIsiK = stdIsi.intValue();	
//		}
//		if(!(stdIsi2 == null)) {
//			stdIsi2K = stdIsi2.intValue();
//		}
		
		String[] codeJenisBbm = jenisBbmK.split("-");
		String code = codeJenisBbm[0];
		
		String sql = "update "+tc.getTenantId()+".ftable03 set jns_kend = '"+jnsKendK+"', jenis_bbm = '"+code+"',"
				+ " capacity = '"+capaCityK+"', kubikase = '"+kubikaseK+"', km_akhir = '"+kmAkhirK+"', statusaktif = '"+statusAktifK+"' , "
				+ " drivercode = '"+driverCodeK+"', helpercode = '"+helperCodeK+"', status_kend = '"+statusKendK+"', std_isi = '"+stdIsiK+"' , "
				+ " tglupd = '"+tglUpd+"', tglupdgd = '"+tglUpdgd+"', jenis_muat = '"+jenisMuatK+"', WEIGHTLOAD = '"+tonaseK+"', maxrit = '"+ritaseK+"', "
				+ " dedicated_data = '"+dedicatedK+"', tgl_efektif = '"+tglEfektifK+"', tgl_akhir = '"+akhirEfektifK+"' where no_kend = '"+noKend+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> cekKendRekap(String carNo) {
//		List<Object[]> result = new ArrayList<>();
		// TODO Auto-generated method stub
		String sql = "select recapno, carno from "+tc.getTenantId()+".ftable81 where carno = '"+carNo+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		return hasil;

	}

	@Override
	public List<Object[]> cekKendDelivery(String noKend) {
//		List<Object[]> result = new ArrayList<>();
		// TODO Auto-generated method stub
		String sql = "select kd_emp, no_kend from "+tc.getTenantId()+".ftable07 where no_kend = '"+noKend+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		return hasil;
	}

	@Override
	public List<Object[]> cekKendar(String noKend) {
		// TODO Auto-generated method stub
		String sql = " select no_kend, jns_kend from "+tc.getTenantId()+".ftable03 where no_kend = '"+noKend+"'  ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> rslt = query.getResultList();
		
		return rslt;
	}

	@Override
	public List<SelectItem<String>> getStatusKendaraan() {
		// TODO Auto-generated method stub
		String sql = " SELECT memostring,memostring AS mm FROM "+tc.getTenantId()+".ftable13 WHERE XKEY = 'STATUSKENDARAAN' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		return result.stream().map(o -> new SelectItem<>((String) o[0], (String) o[1]))
				.collect(Collectors.toList());
	}

	@Override
	public List<ArmadaAllDto> getDataArmadaTemp() {
		// TODO Auto-generated method stub
	List<ArmadaAllDto> result = new ArrayList<>();
		
		String sql = "select no_kend, jns_kend, "
				+ "CASE" + 
				"    WHEN jenis_bbm = '1'" + 
				"    THEN '1-PREMIUM'" + 
				"    WHEN jenis_bbm = '2'" + 
				"    THEN '2-SOLAR'" + 
				"    WHEN jenis_bbm = '3'" + 
				"    THEN '3-PERTAMAX'" +
				"    WHEN jenis_bbm = '4'" + 
				"    THEN '4-PERTALITE'" +
				"    ELSE '5-LAINYA'" + 
				"    END jenisbbm, capacity, kubikase, km_akhir, statusaktif, weightload, jenis_muat,"
				+ " drivercode, helpercode, status_kend, std_isi, std_isi2, maxrit, dedicated_data, to_char(tgl_efektif, 'DD MON YYYY') AS tgl_efektif, to_char(tgl_akhir, 'DD MON YYYY') AS tgl_akhir, xkey, keyauthentication from "+tc.getTenantId()+".FTABLE03_TEMP order by no_kend";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for (Object[] o : obj) {
			ArmadaAllDto a = new ArmadaAllDto( (String) o[0], (String) o[1], (String) o[2], (BigDecimal) o[3], (BigDecimal) o[4], (BigDecimal) o[5], (String) o[6], (BigDecimal) o[7], 
					(String) o[8], (String) o[9], (String) o[10], (String) o[11], (BigDecimal) o[12], (BigDecimal) o[13], (BigDecimal) o[14],  0, (String) o[15], (String) o[16], (String) o[17]);
			a.setXkey((String) o[18]);
			a.setKeyAuth((String) o[19]);
			result.add(a);
		}
		
		return result;
	}

	@Override
	public void deleteDataArmadaTemp(String noKend) {
		// TODO Auto-generated method stub
		String sql = "delete from "+tc.getTenantId()+".FTABLE03_TEMP where no_kend = '"+noKend+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Transactional
	@Override
	public void insertDataArmadaTemp(String noKend, String jnsKend, String jenisBbm, String capacity, String kubikase,
			String kmAkhir, String statusAktif, String tonase, String jenisMuat, String driverCode, String helperCode,
			String statusKend, String stdIsi, String maxRit, String dedicated, String tglEfektif, String tglAkhir,
			String xkey) {
		// TODO Auto-generated method stub
		String[] codeJenisBbm = jenisBbm.split("-");
		String code = codeJenisBbm[0];
		
		
		String sql = "merge into "+tc.getTenantId()+".FTABLE03_TEMP a using (select '"+noKend+"' as NO_KEND, '"+jnsKend+"' as JNS_KEND, '"+code+"' as JENIS_BBM, '"+capacity+"' as CAPACITY, '"+kubikase+"' as KUBIKASE, "
				+ " '"+kmAkhir+"' as KM_AKHIR, '"+statusAktif+"' as STATUSAKTIF, '"+tonase+"' as WEIGHTLOAD, '"+jenisMuat+"' as JENIS_MUAT, '"+driverCode+"' as DRIVERCODE, '"+helperCode+"' as HELPERCODE, '"+statusKend+"' as STATUS_KEND, '"+stdIsi+"' as STD_ISI, " 
				+ " '"+maxRit+"' as MAXRIT, '"+dedicated+"' as DEDICATED_DATA, '"+tglEfektif+"' as TGL_EFEKTIF, '"+tglAkhir+"' as TGL_AKHIR, '"+xkey+"' as XKEY FROM dual ) b on (a.NO_KEND = b.NO_KEND) when matched then update set "
				+ " a.JNS_KEND = b.JNS_KEND, a.JENIS_BBM = b.JENIS_BBM, a.CAPACITY = b.CAPACITY, a.KUBIKASE = b.KUBIKASE, a.KM_AKHIR = b.KM_AKHIR, a.STATUSAKTIF = b.STATUSAKTIF, a.WEIGHTLOAD = b.WEIGHTLOAD, a.JENIS_MUAT = b.JENIS_MUAT, a.DRIVERCODE = b.DRIVERCODE,"
				+ " a.HELPERCODE = b.HELPERCODE, a.STATUS_KEND = b.STATUS_KEND, a.STD_ISI = b.STD_ISI, a.MAXRIT = b.MAXRIT, a.DEDICATED_DATA = b.DEDICATED_DATA, a.TGL_EFEKTIF = b.TGL_EFEKTIF, a.TGL_AKHIR = b.TGL_AKHIR, a.XKEY = b.XKEY when not matched then insert "
				+ " ( a.NO_KEND, a.JNS_KEND, a.JENIS_BBM, a.CAPACITY, a.KUBIKASE, " 
				+ " a.KM_AKHIR, a.STATUSAKTIF, a.WEIGHTLOAD, a.JENIS_MUAT, a.DRIVERCODE, a.HELPERCODE, a.STATUS_KEND, a.STD_ISI, " 
				+ "	a.MAXRIT, a.DEDICATED_DATA, a.TGL_EFEKTIF, a.TGL_AKHIR, a.XKEY) "
				+ " values "
				+ " ( b.NO_KEND, b.JNS_KEND, b.JENIS_BBM, b.CAPACITY, b.KUBIKASE, " 
				+ " b.KM_AKHIR, b.STATUSAKTIF, b.WEIGHTLOAD, b.JENIS_MUAT, b.DRIVERCODE, b.HELPERCODE, b.STATUS_KEND, b.STD_ISI, " 
				+ "	b.MAXRIT, b.DEDICATED_DATA, b.TGL_EFEKTIF, b.TGL_AKHIR, b.XKEY) " ;
		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
	}

	
	
}
