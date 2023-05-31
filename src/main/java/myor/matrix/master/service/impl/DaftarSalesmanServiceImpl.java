package myor.matrix.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myor.matrix.master.entity.FilterStandard;
import myor.matrix.master.entity.FormatExtractDaftarSalesmanDto;
import myor.matrix.master.entity.ListAttributeDaftarSalesmanDto;
import myor.matrix.master.repository.DaftarSalesmanRepository;
import myor.matrix.master.service.DaftarSalesmanService;

@Service
public class DaftarSalesmanServiceImpl implements DaftarSalesmanService {

	@Autowired
	private DaftarSalesmanRepository repository;

	@Override
	public List<ListAttributeDaftarSalesmanDto> getListAttribute() {
		// TODO Auto-generated method stub
		return repository.getListAttribute();
	}

	@Override
	public int cekDataAttribute() {
		// TODO Auto-generated method stub
		return repository.cekDataAttribute();
	}

	@Transactional
	@Override
	public void insertAttribute() {
		// TODO Auto-generated method stub
		repository.insertAttribute();
	}

	@Override
	public List<FormatExtractDaftarSalesmanDto> getDefaultFormat(List<String> tmp) {
		// TODO Auto-generated method stub
		List<FormatExtractDaftarSalesmanDto> result = new ArrayList<>();
		result.addAll(repository.getDefaultFormat(tmp));
		return result;
	}


	@Override
	public String getPreview(FilterStandard fs, List<String> list) {
		// TODO Auto-generated method stub
		String userId = fs.getUserLogin();
		List<String> listData = repository.getColumnField(list);
		List<String> datas = new ArrayList<>();
		String dt = new String();
		for (String obj : listData) {
			String data = obj;
			if(obj.equalsIgnoreCase("TEAM")) {
				data = "s.TEAM";
			}
			dt = new String(data);
			datas.add(dt);
			System.out.println(datas);
		}

//		repository.getSelectByColumn(datas);
		return null;
	}

	@Transactional
	@Override
	public void insertTempReport(FilterStandard fs) {
		// TODO Auto-generated method stub
		String checklist = fs.getChooseData();
		String userId = fs.getUserLogin();
	}

	@Override
	public List<FormatExtractDaftarSalesmanDto> getExtractFormat(FilterStandard fs, List<String> list) {
		// TODO Auto-generated method stub
		List<FormatExtractDaftarSalesmanDto> result = new ArrayList<>();
		List<String> listData = repository.getColumnField(list);
		List<String> datas = new ArrayList<>();
		List<String> datasHeader = new ArrayList<>();
		
		String dt = new String();
		String dh = new String();
		for (String obj : listData) {
			String data = obj;
			String dataHeader = obj;
			if(obj.equalsIgnoreCase("NO")) {
				dataHeader = "'NO'||' | '||";
				data = "TO_CHAR(ROW_NUMBER() OVER (ORDER BY s.SLSNO))||' | '||";
			} else if(obj.equalsIgnoreCase("SLSNO")) {
				dataHeader = "'NO SALESMAN'||' | '||";
				data = "s.SLSNO||' | '||";
			} else if(obj.equalsIgnoreCase("SLSNAME")) {
				dataHeader = "'NAMA SALESMAN'||' | '||";
				data = "s.SLSNAME||' | '||";
			} else if(obj.equalsIgnoreCase("SLSADD1")) {
				dataHeader = "'ALAMAT 1'||' | '||";
				data = "s.SLSADD1||' | '||";
			} else if(obj.equalsIgnoreCase("SLSADD2")) {
				dataHeader = "'ALAMAT 2'||' | '||";
				data = "s.SLSADD2||' | '||";
			} else if(obj.equalsIgnoreCase("SLSCITY")) {
				dataHeader = "'KOTA'||' | '||";
				data = "s.SLSCITY||' | '||";
			} else if(obj.equalsIgnoreCase("PDDK")) {
				dataHeader = "'PENDIDIKAN'||' | '||";
				data = "s.PDDK||' | '||";
			} else if(obj.equalsIgnoreCase("LAHIR")) {
				dataHeader = "'TGL LAHIR'||' | '||";
				data = "s.LAHIR||' | '||";
			}  else if(obj.equalsIgnoreCase("WORKDATE")) {
				dataHeader = "'TGL MASUK KERJA'||' | '||";
				data = "TO_CHAR(s.WORKDATE,'DD MON YYYY')||' | '||";
			} else if(obj.equalsIgnoreCase("OPRTYPE")) {
				dataHeader = "'TIPE OPERASI'||' | '||";
				data = " CASE WHEN s.OPRTYPE = 'T' THEN 'Taking Order' "
						+ "	WHEN s.OPRTYPE = 'C' THEN 'Canvas' "
						+ "	END ||' | '||";
			} else if(obj.equalsIgnoreCase("TEAM")) {
				dataHeader = "'TEAM ID'||' | '||";
				data = "s.TEAM||' | '||";
			} else if(obj.equalsIgnoreCase("TEAMNAME")) {
				dataHeader = "'TEAM NAME'||' | '||";
				data = "f.TEAMNAME||' | '||";
			} else if(obj.equalsIgnoreCase("TRANSDATE")) {
				dataHeader = "'TGL TRANSAKSI'||' | '||";
				data = "TO_CHAR(s.TRANSDATE,'DD MON YYYY')||' | '||";
			} else if(obj.equalsIgnoreCase("KG")) {
				dataHeader = "'KODE GUDANG'||' | '||";
				data = "s.KG||' | '||";
			} else if(obj.equalsIgnoreCase("KGNAME")) {
				dataHeader = "'NAMA GUDANG'||' | '||";
				data = "CASE WHEN s.KG = '00' THEN 'GUDANG UTAMA' "
						+ "	WHEN s.KG = '99' THEN 'GUDANG PROMOSI' "
						+ " END ||' | '||";
			} else if(obj.equalsIgnoreCase("KPEJ")) {
				dataHeader = "'KODE PEJABAT'||' | '||";
				data = "s.KPEJ||' | '||";
			} else if(obj.equalsIgnoreCase("PEJNAME")) {
				dataHeader = "'NAMA PEJABAT'||' | '||";
				data = "pj.PEJNAME||' | '||";
			} else if(obj.equalsIgnoreCase("DATA01")) {
				dataHeader = "'NO TLP'||' | '||";
				data = "s.DATA01||' | '||";
			} else if(obj.equalsIgnoreCase("DATA02")) {
				dataHeader = "'NO HP'||' | '||";
				data = "s.DATA02||' | '||";
			} else if(obj.equalsIgnoreCase("DATA03")) {
				dataHeader = "'EMAIL'||' | '||";
				data = "s.DATA03||' | '||";
			} else if(obj.equalsIgnoreCase("DATA04")) {
				dataHeader = "'KODE SALESFORCE'||' | '||";
				data = "s.DATA04||' | '||";
			} else if(obj.equalsIgnoreCase("sf.DATA2")) {
				dataHeader = "'NAMA SALESFORCE'||' | '||";
				data = "sf.DATA2||' | '||";
			} else if(obj.equalsIgnoreCase("DATA05")) {
				dataHeader = "'KODE RAYON'||' | '||";
				data = "s.DATA05||' | '||";
			} else if(obj.equalsIgnoreCase("ra.DATA2")) {
				dataHeader = "'NAMA RAYON'||' | '||";
				data = "ra.DATA2||' | '||";
			} else if(obj.equalsIgnoreCase("DATA06")) {
				dataHeader = "'BLOK'||' | '||";
				data = "CASE WHEN s.DATA06 = 'T' THEN 'TIDAK' "
						+ " ELSE 'YA' END ||' | '||";
			} else if(obj.equalsIgnoreCase("DATA08")) {
				dataHeader = "'TEMPAT LAHIR'||' | '||";
				data = "s.DATA08||' | '||";
			} else if(obj.equalsIgnoreCase("DATA09")) {
				dataHeader = "'JENIS KELAMIN'||' | '||";
				data = "CASE WHEN s.DATA09 = 'L' THEN 'LAKI-LAKI' "
						+ " ELSE 'PEREMPUAN' END ||' | '||";
			} else if(obj.equalsIgnoreCase("DATA10")) {
				dataHeader = "'STATUS'||' | '||";
				data = " CASE WHEN s.DATA10 = '1' THEN 'K0 (KAWIN)' "
						+ "        	WHEN s.DATA10 = '2' THEN 'K1 (KAWIN, ANAK 1)' "
						+ "        	WHEN s.DATA10 = '3' THEN 'K2 (KAWIN, ANAK 2)' "
						+ "        	WHEN s.DATA10 = '4' THEN 'K3 (KAWIN, ANAK 3)' "
						+ "        	WHEN s.DATA10 = '5' THEN 'K4 (KAWIN, ANAK 4)' "
						+ "        	WHEN s.DATA10 = '6' THEN 'TK (TIDAK/BELUM KAWIN)' "
						+ "        END ||' | '||";
			} else if(obj.equalsIgnoreCase("KDMAPPING")) {
				dataHeader = "'SALES DIVISI'||' | '||";
				data = "f2.KDMAPPING ||' | '||";
			} else if(obj.equalsIgnoreCase("SBRA2NAME")) {
				dataHeader = "'NAMA SALES DIVISI'||' | '||";
				data = "f2.SBRA2NAME ||' | '||";
			} else if(obj.equalsIgnoreCase("DATA11")) {
				dataHeader = "'AKUNTING & BANK'||' | '||";
				data = "s.DATA11 ||' | '||";
			} else if(obj.equalsIgnoreCase("DATA12")) {
				dataHeader = "'AGAMA'||' | '||";
				data = "CASE WHEN s.DATA12 = '1' THEN 'Islam' "
					+ " 	 WHEN s.DATA12 = '2' THEN 'Kristen' "
					+ "		 WHEN s.DATA12 = '3' THEN 'Katholik'"
					+ "		 WHEN s.DATA12 = '4' THEN 'Hindu' "
					+ "		 WHEN s.DATA12 = '5' THEN 'Budha' "
					+ "		 WHEN s.DATA12 = '6' THEN 'Konghucu' "
					+ " END ||' | '|| ";
			} else if(obj.equalsIgnoreCase("DATA13")) {
				dataHeader = "'NAMA SALES DIVISI'||' | '||";
				data = "s.DATA13 ||' | '||";
			} else if(obj.equalsIgnoreCase("f3.KODE")) {
				dataHeader = "'DISKON REGULER'||' | '||";
				data = "f3.KODE";
			} else if(obj.equalsIgnoreCase("KNOWNAS")) {
				dataHeader = "'KNOW AS'||' | '||";
				data = "s.KNOWNAS ||' | '||";
			} else if(obj.equalsIgnoreCase("SLSEMPLOYEE")) {
				dataHeader = "'SALES EMPLOYEE'||' | '||";
				data = "s.SLSEMPLOYEE ||' | '||";
			}
			
			dh = new String(dataHeader);
			dt = new String(data);
			
			datas.add(dt);
			datasHeader.add(dh);
			
			System.out.println(datas);
		}

		result = repository.getSelectByColumn(datas, datasHeader,fs.getSlsnoFrom(),fs.getSlsnoTo(),fs.getSalesforceFrom(),
								fs.getSalesforceTo(),fs.getTeamFrom(),fs.getTeamTo(),fs.getRayonFrom(),fs.getRayonTo(),
								fs.getChooseData());
		return result;
	}


}
