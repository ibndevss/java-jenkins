package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.OutletRuteDto;
import myor.matrix.master.entity.ReportOutletRuteDto;
import myor.matrix.master.entity.TotalOutletSalesmanDto;
import myor.matrix.master.repository.OutletRuteRepository;
import myor.matrix.master.tenant.TenantContext;
import myor.matrix.master.util.Util;

@Repository
public class OutletRuteRepositoryImpl implements OutletRuteRepository {
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	List<OutletRuteDto> result = new ArrayList<>();
	
	@Autowired
	Util util;

	@Override
	public List<OutletRuteDto> getOutletRute(String custFrom, String custTo, String teamFrom, String teamTo,
			String slsFrom, String slsTo, String pil, String hari, String urut) {
		List<OutletRuteDto> result = new ArrayList<>();
		String sql = "	SELECT   r.slsno, s.slsname, c.custno, c.custname, c.custadd1, c.climit,	"
				+"	         c.sbeat,	"
				+"	            DECODE (r.hsenin, 'Y', 'Y', '-')	"
				+"	         || DECODE (r.hselasa, 'Y', 'Y', '-')	"
				+"	         || DECODE (r.hrabu, 'Y', 'Y', '-')	"
				+"	         || DECODE (r.hkamis, 'Y', 'Y', '-')	"
				+"	         || DECODE (r.hjumat, 'Y', 'Y', '-')	"
				+"	         || DECODE (r.hsabtu, 'Y', 'Y', '-')	"
				+"	         || DECODE (r.hminggu, 'Y', 'Y', '-') hari,	"
				+"	         CASE	"
				+"	            WHEN r.visit1 || r.visit2 || r.visit3 || r.visit4 = 'YYYY'  THEN 'Weekly'	"
				+"	            WHEN r.visit1 || r.visit2 || r.visit3 || r.visit4 = 'YTYT' THEN 'BeWeekly1'	"
				+"	            WHEN r.visit1 || r.visit2 || r.visit3 || r.visit4 = 'TYTY' THEN 'BeWeekly2'	"
				+"	            WHEN r.visit1 || r.visit2 || r.visit3 || r.visit4 = 'YTTT'  THEN 'Monthly1'	"
				+"	            WHEN r.visit1 || r.visit2 || r.visit3 || r.visit4 = 'TYTT' THEN 'Monthly2'	"
				+"	            WHEN r.visit1 || r.visit2 || r.visit3 || r.visit4 = 'TTYT' THEN 'Monthly3'	"
				+"	            WHEN r.visit1 || r.visit2 || r.visit3 || r.visit4 = 'TTTY' THEN 'Monthly4'	"
				+"	         END AS pola,	"
				+"	         r.route	"
				+"	    FROM "+t.getTenantId()+".fcustmst c, "+t.getTenantId()+".fcustsls r, "+t.getTenantId()+".fsls s	"
				+"	   WHERE (r.slsno = s.slsno(+))	"
				+"	     AND (c.custno = r.custno(+))	";
		if(!custFrom.equalsIgnoreCase("")) {
			sql += "	     AND c.custno >= '"+custFrom+"'	";
		}
		if(!custTo.equalsIgnoreCase("")) {
			sql += "	     AND c.custno <= '"+custTo+"'	";
		}
		if(!slsFrom.equalsIgnoreCase("")) {
			sql += "	     AND r.slsno >= '"+slsFrom+"'	";
		}
		if(!slsTo.equalsIgnoreCase("")) {
			sql += "	     AND r.slsno <= '"+slsTo+"'	";
		}
		if(!teamFrom.equalsIgnoreCase("")) {
			sql +=" and S.TEAM  >= '"+teamFrom+"' ";
		}
		if(!teamTo.equalsIgnoreCase("")) {
			sql +=" and S.TEAM  <= '"+teamTo+"' ";
		}	
		if(pil.equalsIgnoreCase("nonaktif")) {
			sql += " and c.flagout='N'";
		}
		if(pil.equalsIgnoreCase("cover")) {
			sql += " and c.flagout='C'";
		}
		if(pil.equalsIgnoreCase("noo")) {
			sql += " and c.flagout='O'";
		}
		if(hari.equalsIgnoreCase("Senin")) {
			sql += " and r.hsenin = 'Y'";
		}
		if(hari.equalsIgnoreCase("Selasa")) {
			sql += " and r.hselasa = 'Y'";
		}
		if(hari.equalsIgnoreCase("Rabu")) {
			sql +=" and r.hrabu = 'Y'";
		}
		if(hari.equalsIgnoreCase("Kamis")) {
			sql +=" and r.hkamis = 'Y'";
		}
		if(hari.equalsIgnoreCase("Jumat")) {
			sql +=" and r.hjumat='Y'";
		}
		if(hari.equalsIgnoreCase("Sabtu")) {
			sql +=" and r.hsabtu='Y'";
		}
		if(hari.equalsIgnoreCase("Minggu")) {
			sql += " and r.hminggu='Y'";
		}
		if(urut.equalsIgnoreCase("outlet")) {
			sql += "  ORDER BY R.SLSNO, C.CUSTNO ";
		}
		if(urut.equalsIgnoreCase("rute")) {
			sql += " ORDER BY R.SLSNO, R.ROUTE, C.CUSTNO ";
		}
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hsl = query.getResultList();
		for (Object[] o : hsl) {
			OutletRuteDto data = new OutletRuteDto(Objects.toString(o[0], ""), Objects.toString(o[1], ""), Objects.toString(o[2], ""), Objects.toString(o[3], ""), Objects.toString(o[4], ""), 
					Integer.toString(new BigDecimal(Objects.toString(o[5], "0.0")).intValue()) ,
					Objects.toString(o[6], ""), Objects.toString(o[7], ""), Objects.toString(o[8], ""), Integer.toString(new BigDecimal(Objects.toString(o[9], "0.0")).intValue()));
			result.add(data);
		}
		return result;
	}

	@Override
	public List<TotalOutletSalesmanDto> getTotalOutlet(String salesman, String hari) {
		List<TotalOutletSalesmanDto> result = new ArrayList<>();
		String sql = "SELECT   DECODE (c.CLASS, '', 'NA', c.CLASS) kode, "
				+ "         DECODE (l.ctypename, '', 'Klas NA', l.ctypename) nama,"
				+ "         NVL (COUNT (DISTINCT c.custno), 0) jml "
				+ "    FROM "+t.getTenantId()+".fcustmst c, "+t.getTenantId()+".fcustsls r, "+t.getTenantId()+".fclass l "
				+ "   WHERE (c.CLASS = l.ctype(+)) AND (c.custno = r.custno(+)) AND r.slsno = '"+salesman+"' ";
		if(hari.equalsIgnoreCase("Senin")) {
			sql += " and r.hsenin = 'Y'";
		}
		if(hari.equalsIgnoreCase("Selasa")) {
			sql += " and r.hselasa = 'Y'";
		}
		if(hari.equalsIgnoreCase("Rabu")) {
			sql +=" and r.hrabu = 'Y'";
		}
		if(hari.equalsIgnoreCase("Kamis")) {
			sql +=" and r.hkamis = 'Y'";
		}
		if(hari.equalsIgnoreCase("Jumat")) {
			sql +=" and r.hjumat='Y'";
		}
		if(hari.equalsIgnoreCase("Sabtu")) {
			sql +=" and r.hsabtu='Y'";
		}
		if(hari.equalsIgnoreCase("Minggu")) {
			sql += " and r.hminggu='Y'";
		}
			sql=sql	+ "GROUP BY c.CLASS, l.ctypename ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for(Object[]o: obj) {
			TotalOutletSalesmanDto data = new TotalOutletSalesmanDto((String)o[0], (String)o[1], new BigDecimal(Objects.toString(o[2], "0.0")));
			result.add(data);
		}
		return result;
	}

	@Transactional
	@Override
	public void insertOutletRute(String kdsls, String namaSls, String data, String noUrut, String outlet,
			String namaOutlet, String alamat, String klimit, String sbeat, String hari, String pola, String rute, String userId) {
		String sql = "insert into  "+t.getTenantId()+".TEMP_RUTE_OUTLET(KDSLS, NAMASLS, DATA, NO_URUT, OUTLET, NAMAOUTLET, ALAMAT, KLIMIT, SBEAT, HARI, POLA, "
				+ " RUTE, USERID)  values (  '"+kdsls+"' ,'"+namaSls+"' ,'"+data+"' ,'"+noUrut+"' ,'"+outlet+"' ,'"+namaOutlet.replace("'", " ")+"' ,'"+alamat+"' ,'"+klimit+"' "
						+ ",'"+sbeat+"' ,'"+hari+"' ,'"+pola+"' ,'"+rute+"','"+userId+"' )";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public List<ReportOutletRuteDto> getReport(String custFrom, String custTo, String teamFrom, String teamTo,
			String slsFrom, String slsTo, String pil, String hari, String urut, String userid) {
		List<ReportOutletRuteDto> result = new ArrayList<>();
		String sql = "SELECT kdsls, namasls, data, no_urut, outlet, namaoutlet, alamat, to_number(klimit) klimit, sbeat, hari, pola, rute, userid FROM "+t.getTenantId()+".TEMP_RUTE_OUTLET where userid='"+userid+"' ORDER BY KDSLS , DATA, LPAD(NO_URUT, 10)";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> res = query.getResultList();
		String header = "Daftar Outlet per Rute " + util.getSubdistId() +" - " + util.getSubdistName();
		
		String sls = slsFrom + " s/d " + slsTo;
		if(slsFrom.equalsIgnoreCase("") && slsTo.equalsIgnoreCase("")) {
			sls = "Semua";
		}
		String cust = custFrom + " s/d " + custTo;
		if(custFrom.equalsIgnoreCase("") && custTo.equalsIgnoreCase("")) {
			cust = "Semua";
		}
		String tipeOutlet="";
		if(pil.equalsIgnoreCase("register")) {
			tipeOutlet = "Register";
		}else if(pil.equalsIgnoreCase("cover")) {
			tipeOutlet  = "Covered";
		}else if(pil.equalsIgnoreCase("nonaktif")) {
			tipeOutlet  = "Non Aktif";
		}else if(pil.equalsIgnoreCase("noo")) {
			tipeOutlet  = "NOO";
		}
		String periode = (util.getPerideWeekTglGudang().get(0)[0]).toString();
		String week = (util.getPerideWeekTglGudang().get(0)[1]).toString();
		String team = teamFrom+ " s/d " + teamTo;
		if(teamFrom.equalsIgnoreCase("") && teamTo.equalsIgnoreCase("")) {
			team = "Semua";
		}
		String hari_label = hari;
			
		for(Object[]o : res) {
			ReportOutletRuteDto data = new ReportOutletRuteDto((String)o[0], (String)o[1], (String)o[2], (String)o[3], (String) o[4], (String)o[5], (String)o[6], (BigDecimal)o[7], (String)o[8], (String)o[9], (String)o[10], (String)o[11]);
			data.setTGLCETAK(util.getTglSystem());
			data.setTGLGUDANG(util.getTglGudang());
			data.setRELEASE(util.getRelease());
			data.setHEADER(header);
			data.setSALESMAN(sls);
			data.setPIL_OUTLET(cust);
			data.setTIPEOUTLET(tipeOutlet);
			data.setPERIODE(periode);
			data.setPEKAN(week);
			data.setTEAM(team);
			data.setPILHARI(hari_label);
			result.add(data);
		}
		return result;
	}
	
	@Transactional
	@Override
	public void deleteTempReport(String userId) {
		String sql="delete from "+t.getTenantId()+".TEMP_RUTE_OUTLET where userid='"+userId+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
}
