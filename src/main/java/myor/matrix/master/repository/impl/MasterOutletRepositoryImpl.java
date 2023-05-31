package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.GroupPayerBrowseDto;
import myor.matrix.master.entity.MasterOutletBrowseDto;
import myor.matrix.master.entity.MasterOutletDataAttributeDto;
import myor.matrix.master.entity.MasterOutletDataCoverDto;
import myor.matrix.master.entity.MasterOutletDataDivisiDto;
import myor.matrix.master.entity.MasterOutletDataPajakDto;
import myor.matrix.master.entity.MasterOutletDataPemerintahanDto;
import myor.matrix.master.entity.MasterOutletDataProfileDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.MasterOutletRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MasterOutletRepositoryImpl implements MasterOutletRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Object[]> getFtable13AwalAkhir(String groupDivisi) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql ="SELECT memostring2 m1awal, "
	            +" (SELECT memostring2 "
	            +" FROM "+t.getTenantId()+".ftable13 "
	            +" WHERE memonama = '"+groupDivisi+"' AND xkey = 'X_CUSTNO_"+groupDivisi+"_AKHIR') m1akhir "
	            +" FROM "+t.getTenantId()+".ftable13 "
	            +" WHERE memonama = '"+groupDivisi+"' AND xkey = 'X_CUSTNO_"+groupDivisi+"_AWAL' ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public String getFtable13XCustNo(String groupDivisi) {
		// TODO Auto-generated method stub
		String sql = " select memostring from "+t.getTenantId()+".ftable13 where xkey = 'X_CUSTNO_"+groupDivisi+"_AWAL' and memonama = '"+groupDivisi+"'  ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}

	@Override
	public List<Object[]> getFtable48(String groupDivisi) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select * from (select * from "+t.getTenantId()+".ftable48 where groupdivisi = '"+groupDivisi+"') WHERE ROWNUM = 1 ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public String getXkeyString(String column,String table, String where) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " select "+column+" from "+t.getTenantId()+"."+table+" where "+where+"  ";
			Query query = entityManager.createNativeQuery(sql);
			result = (String) query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}

	@Override
	public List<Object[]> getXkeyListObject(String column, String table, String where) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<MasterOutletDataProfileDto> getDataProfile(String custNo) {
		// TODO Auto-generated method stub
		List<MasterOutletDataProfileDto> result = new ArrayList<>();
		String sql = " SELECT "
				+ "    DECODE (B.TF, '*', 'TAKSFORCE', '') FLAG, "
				+ "    b.groupdivisi, "
				+ "    b.bykrt, "
				+ "    b.grouppayer, "
				+ "    b.chiller, "
				+ "    m.custno, "
				+ "    m.custname, "
				+ "    m.custadd1, "
				+ "    m.custadd2, "
				+ "    m.ccity, "
				+ "    to_char(m.ckdpos) ckdpos, "
				+ "    m.cphone1, "
				+ "    m.cfaxno, "
				+ "    m.ccontact, "
				+ "    m.data02, "
				+ "    m.data03, "
				+ "    m.data04, "
				+ "    DECODE ( "
				+ "        m.flagout, "
				+ "        'O', "
				+ "        'OpenNewOutlet', "
				+ "        'N', "
				+ "        'NonAktif', "
				+ "        'Covered' "
				+ "    ) flagout, "
				+ "    m.data05, "
				+ "    m.data06, "
				+ "    to_char(m.cterm) cterm, "
				+ "    to_char(m.cweekno) cweekno, "
				+ "    m.data14, "
				+ "    to_char(m.climit) climit, "
				+ "    m.flagpay, "
				+ "    m.flagkbon, "
				+ "    m.data20, "
				+ "    m.data10, "
				+ "    to_char(m.data11) data11, "
				+ "    to_char(m.rata) rata, "
				+ "    to_char(m.clastdate, 'DD MON YYYY') transakhir, "
				+ "    to_char(m.firstdate, 'DD MON YYYY') transawal, "
				+ "    to_char(m.clastupd, 'DD MON YYYY') transupd, "
				+ "    to_char(m.firstopen, 'DD MON YYYY') mulaiusaha, "
				+ "    to_char(m.regdate, 'DD MON YYYY') regdate, "
				+ "    m.data19, "
				+ "    a.nokartu1, "
				+ "    a.nokartu2, "
				+ "    a.nokartu3, "
				+ "    b.empno, "
				+ "    e.empname, "
				+ "    b.latitude, "
				+ "    b.longitude, "
				+ "    b.custno_old custnoold, "
				+ "    f.alasan, "
				+ "    g.nokartu nokarturack "
				+ "FROM "
				+ "    "+t.getTenantId()+".fcustmst m "
				+ "    LEFT JOIN "+t.getTenantId()+".ftable21 a ON m.custno = a.custno "
				+ "    left JOIN "+t.getTenantId()+".ftable48 b ON m.custno = b.custno "
				+ "    LEFT JOIN "+t.getTenantId()+".femployee e ON b.empno = e.empno "
				+ "    LEFT JOIN ( "
				+ "        SELECT "
				+ "            kdalasan, "
				+ "            alasan "
				+ "        FROM "
				+ "            "+t.getTenantId()+".ftable01 "
				+ "        where "
				+ "            tipealasan = '13' "
				+ "    ) f ON b.chiller = f.kdalasan "
				+ "    LEFT JOIN "+t.getTenantId()+".ftable269 g ON m.custno = g.custno "
				+ "where m.custno = '"+custNo+"'  ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			MasterOutletDataProfileDto data = new MasterOutletDataProfileDto(Objects.toString((String)obj[0], ""), Objects.toString((String)obj[1], ""),
			Objects.toString((String)obj[2], ""), Objects.toString((String)obj[3], ""), Objects.toString((String)obj[4], ""),
			Objects.toString((String)obj[5], ""), Objects.toString((String)obj[6], ""), Objects.toString((String)obj[7], ""),
			Objects.toString((String)obj[8], ""), Objects.toString((String)obj[9], ""), Objects.toString((String)obj[10], ""),
			Objects.toString((String)obj[11], ""), Objects.toString((String)obj[12], ""), Objects.toString((String)obj[13], ""),
			Objects.toString((String)obj[14], ""), Objects.toString((String)obj[15], ""), Objects.toString((String)obj[16], ""),
			Objects.toString((String)obj[17], ""), Objects.toString((String)obj[18], ""), Objects.toString((String)obj[19], ""),
			Objects.toString((String)obj[20], ""), Objects.toString((String)obj[21], ""), Objects.toString((String)obj[22], ""),
			Objects.toString((String)obj[23], ""), Objects.toString((String)obj[24], ""), Objects.toString((String)obj[25], ""),
			Objects.toString((String)obj[26], ""), Objects.toString((String)obj[27], ""), Objects.toString((String)obj[28], ""),
			Objects.toString((String)obj[29], ""), Objects.toString((String)obj[30], ""), Objects.toString((String)obj[31], ""),
			Objects.toString((String)obj[32], ""), Objects.toString((String)obj[33], ""), Objects.toString((String)obj[34], ""),
			Objects.toString((String)obj[35], ""), Objects.toString((String)obj[36], ""), Objects.toString((String)obj[37], ""),
			Objects.toString((String)obj[38], ""), Objects.toString((String)obj[39], ""), Objects.toString((String)obj[40], ""),
			Objects.toString((String)obj[41], ""), Objects.toString((String)obj[42], ""), Objects.toString((String)obj[43], ""),
			Objects.toString((String)obj[44], ""), Objects.toString((String)obj[45], ""));
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<MasterOutletDataAttributeDto> getDataAttribute(String custNo) {
		// TODO Auto-generated method stub
		List<MasterOutletDataAttributeDto> result = new ArrayList<>();
		String sql = " SELECT "
				+ "    s.GROUPDIVISI, "
				+ "    m.custno, "
				+ "    m.LOCATION kdlokasi, "
				+ "    b.locname, "
				+ "    m.data16 kdsublokasi, "
				+ "    c.data3, "
				+ "    m.kdpsr, "
				+ "    a.kdpsrname, "
				+ "    m.typeout kdchannel, "
				+ "    d.typename nmchannel, "
				+ "    m.CLASS kdclass, "
				+ "    e.ctypename, "
				+ "    m.data12 kdchannellast, "
				+ "    u.typename nmchannellast, "
				+ "    m.data13 kdclasslast, "
				+ "    v.ctypename nmclasslast, "
				+ "    m.grupout kdgroupout, "
				+ "    f.grupname, "
				+ "    m.distrik kddistrik, "
				+ "    g.disname, "
				+ "    m.beat rute, "
				+ "    h.beatname, "
				+ "    m.sbeat subrute, "
				+ "    i.sbeatname, "
				+ "    m.kdind, "
				+ "    j.indname, "
				+ "    m.data15 kdspecout, "
				+ "    k.data2 nmspecout, "
				+ "    m.data17 kdgrup1, "
				+ "    l.data2 nmgrup1, "
				+ "    m.data18 kdgrup2, "
				+ "    n.data2 nmgrup2, "
				+ "    m.GDISC, "
				+ "    o.GDISCNAME, "
				+ "    m.GPLU, "
				+ "    p.KET NMGPLU, "
				+ "    m.GKONV, "
				+ "    q.KET NMGKONV, "
				+ "    m.GHARGA, "
				+ "    r.KET NMGHARGA, "
				+ "    s.OUTTYPE, "
				+ "    t.DESCRIPTION, "
				+ "    to_char(s.TYPECOST) typecost, "
				+ "    to_char(s.COST) cost "
				+ "FROM "
				+ "    "+t.getTenantId()+".fcustmst m "
				+ "    LEFT JOIN "+t.getTenantId()+".fkdpasar a ON m.kdpsr = a.kdpsr "
				+ "    LEFT JOIN "+t.getTenantId()+".flocation b ON m.LOCATION = b.LOCATION "
				+ "    LEFT JOIN "+t.getTenantId()+".ftabel07 c ON m.LOCATION = c.data1 "
				+ "    AND m.data16 = c.data2 "
				+ "    LEFT JOIN "+t.getTenantId()+".ftypeout d ON m.typeout = d.TYPE "
				+ "    LEFT JOIN "+t.getTenantId()+".fclassnew e ON m.typeout = e.TYPE "
				+ "    AND m.CLASS = e.ctype "
				+ "    LEFT JOIN "+t.getTenantId()+".fgrupout f ON m.grupout = f.grupout "
				+ "    LEFT JOIN "+t.getTenantId()+".fdistrik g ON m.distrik = g.distrik "
				+ "    LEFT JOIN "+t.getTenantId()+".fbeatnew h ON m.distrik = h.distrik "
				+ "    AND m.beat = h.beat "
				+ "    LEFT JOIN "+t.getTenantId()+".fsbeatnew i ON m.distrik = i.distrik "
				+ "    AND m.beat = i.beat "
				+ "    AND m.sbeat = i.sbeat "
				+ "    LEFT JOIN "+t.getTenantId()+".findustri j ON m.kdind = j.kdind "
				+ "    LEFT JOIN "+t.getTenantId()+".ftabel06 k ON m.data15 = k.data1 "
				+ "    LEFT JOIN "+t.getTenantId()+".ftabel08 l ON m.data17 = l.data1 "
				+ "    LEFT JOIN "+t.getTenantId()+".ftabel09 n ON m.data18 = n.data1 "
				+ "    LEFT JOIN "+t.getTenantId()+".fgdisc o ON m.GDISC = o.GDISC "
				+ "    LEFT JOIN "+t.getTenantId()+".fgplu p ON m.GPLU = p.GPLU "
				+ "    LEFT JOIN "+t.getTenantId()+".fgkonv q ON m.GKONV = q.GKONV "
				+ "    LEFT JOIN "+t.getTenantId()+".fgharga r ON m.GHARGA = r.GHARGA "
				+ "    LEFT JOIN "+t.getTenantId()+".ftable48 s ON m.CUSTNO = s.CUSTNO "
				+ "    LEFT JOIN "+t.getTenantId()+".ftable49 t ON s.OUTTYPE = t.OUTTYPE "
				+ "    LEFT JOIN "+t.getTenantId()+".ftypeout u ON m.data12 = u.TYPE "
				+ "    LEFT JOIN "+t.getTenantId()+".fclassnew v ON m.data13 = v.ctype "
				+ "WHERE "
				+ "    m.CUSTNO = '"+custNo+"'  ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			MasterOutletDataAttributeDto data = new MasterOutletDataAttributeDto(Objects.toString((String)obj[0], ""), Objects.toString((String)obj[1], ""),
			Objects.toString((String)obj[2], ""), Objects.toString((String)obj[3], ""), Objects.toString((String)obj[4], ""),
			Objects.toString((String)obj[5], ""), Objects.toString((String)obj[6], ""), Objects.toString((String)obj[7], ""),
			Objects.toString((String)obj[8], ""), Objects.toString((String)obj[9], ""), Objects.toString((String)obj[10], ""),
			Objects.toString((String)obj[11], ""), Objects.toString((String)obj[12], ""), Objects.toString((String)obj[13], ""),
			Objects.toString((String)obj[14], ""), Objects.toString((String)obj[15], ""), Objects.toString((String)obj[16], ""),
			Objects.toString((String)obj[17], ""), Objects.toString((String)obj[18], ""), Objects.toString((String)obj[19], ""),
			Objects.toString((String)obj[20], ""), Objects.toString((String)obj[21], ""), Objects.toString((String)obj[22], ""),
			Objects.toString((String)obj[23], ""), Objects.toString((String)obj[24], ""), Objects.toString((String)obj[25], ""),
			Objects.toString((String)obj[26], ""), Objects.toString((String)obj[27], ""), Objects.toString((String)obj[28], ""),
			Objects.toString((String)obj[29], ""), Objects.toString((String)obj[30], ""), Objects.toString((String)obj[31], ""),
			Objects.toString((String)obj[32], ""), Objects.toString((String)obj[33], ""), Objects.toString((String)obj[34], ""),
			Objects.toString((String)obj[35], ""), Objects.toString((String)obj[36], ""), Objects.toString((String)obj[37], ""),
			Objects.toString((String)obj[38], ""), Objects.toString((String)obj[39], ""), Objects.toString((String)obj[40], ""),
			Objects.toString((String)obj[41], ""), Objects.toString((String)obj[42], ""), Objects.toString((String)obj[43], ""));
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<MasterOutletDataPajakDto> getDataPajak(String custNo) {
		// TODO Auto-generated method stub
		List<MasterOutletDataPajakDto> result = new ArrayList<>();
		String sql = " SELECT "
				+ "    b.groupdivisi, "
				+ "    m.custno, "
				+ "    m.taxname, "
				+ "    m.taxadd1, "
				+ "    m.taxadd2, "
				+ "    m.taxcity, "
				+ "    m.npwp, "
				+ "    m.taxflag, "
				+ "    m.agenlain, "
				+ "    m.norefsup, "
				+ "    m.data07, "
				+ "    to_char(m.data08) data08, "
				+ "    to_char(m.data09, 'DD MON YYYY') data09, "
				+ "    m.bank, "
				+ "    m.noacc, "
				+ "    m.cresd1, "
				+ "    m.cresd2, "
				+ "    m.ccity2, "
				+ "    m.cphone2, "
				+ "    m.flaghome, "
				+ "    b.noktp, "
				+ "    b.groupdeliv "
				+ "FROM "
				+ "    "+t.getTenantId()+".fcustmst m "
				+ "    LEFT JOIN "+t.getTenantId()+".ftable48 b ON m.custno = b.custno "
				+ "where "
				+ "    m.custno = '"+custNo+"'  ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			MasterOutletDataPajakDto data = new MasterOutletDataPajakDto(Objects.toString((String)obj[0], ""), Objects.toString((String)obj[1], ""),
					Objects.toString((String)obj[2], ""), Objects.toString((String)obj[3], ""), Objects.toString((String)obj[4], ""),
					Objects.toString((String)obj[5], ""), Objects.toString((String)obj[6], ""), Objects.toString((String)obj[7], ""),
					Objects.toString((String)obj[8], ""), Objects.toString((String)obj[9], ""), Objects.toString((String)obj[10], ""),
					Objects.toString((String)obj[11], ""), Objects.toString((String)obj[12], ""), Objects.toString((String)obj[13], ""),
					Objects.toString((String)obj[14], ""), Objects.toString((String)obj[15], ""), Objects.toString((String)obj[16], ""),
					Objects.toString((String)obj[17], ""), Objects.toString((String)obj[18], ""), Objects.toString((String)obj[19], ""),
					Objects.toString((String)obj[20], ""), Objects.toString((String)obj[21], ""));
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<MasterOutletDataPemerintahanDto> getDataPemerintahan(String custNo) {
		// TODO Auto-generated method stub
		List<MasterOutletDataPemerintahanDto> result = new ArrayList<>();
		String sql = " SELECT "
				+ "    e.GROUPDIVISI, "
				+ "    a.custno, "
				+ "    a.kdprop, "
				+ "    a.prop, "
				+ "    b.kdkab, "
				+ "    b.kab, "
				+ "    c.kdkec, "
				+ "    c.kec, "
				+ "    d.kdkel, "
				+ "    d.kel "
				+ "FROM "
				+ "    ( "
				+ "        SELECT "
				+ "            a.custno, "
				+ "            a.h11 kdprop, "
				+ "            b.ket prop "
				+ "        FROM "
				+ "            "+t.getTenantId()+".fcustud a "
				+ "            LEFT JOIN "+t.getTenantId()+".fcshir11 b ON a.h11 = b.t11 "
				+ "    ) a "
				+ "    LEFT JOIN ( "
				+ "        SELECT "
				+ "            a.custno, "
				+ "            a.h12 kdkab, "
				+ "            c.ket kab "
				+ "        FROM "
				+ "            "+t.getTenantId()+".fcustud a "
				+ "            LEFT JOIN "+t.getTenantId()+".fcshir12 c ON a.h11 = c.t11 "
				+ "            AND a.h12 = c.t12 "
				+ "    ) b ON a.custno = b.custno "
				+ "    LEFT JOIN ( "
				+ "        SELECT "
				+ "            a.custno, "
				+ "            a.h13 kdkec, "
				+ "            d.ket kec "
				+ "        FROM "
				+ "            "+t.getTenantId()+".fcustud a "
				+ "            LEFT JOIN "+t.getTenantId()+".fcshir13 d ON a.h11 = d.t11 "
				+ "            AND a.h12 = d.t12 "
				+ "            AND a.h13 = d.t13 "
				+ "    ) c ON a.custno = c.custno "
				+ "    LEFT JOIN ( "
				+ "        SELECT "
				+ "            a.custno, "
				+ "            a.h14 kdkel, "
				+ "            e.ket kel "
				+ "        FROM "
				+ "            "+t.getTenantId()+".fcustud a "
				+ "            LEFT JOIN "+t.getTenantId()+".fcshir14 e ON a.h11 = e.t11 "
				+ "            AND a.h12 = e.t12 "
				+ "            AND a.h13 = e.t13 "
				+ "            AND a.h14 = e.t14 "
				+ "    ) d ON a.custno = d.custno "
				+ "    left join "+t.getTenantId()+".ftable48 e on a.CUSTNO = e.custno "
				+ "where "
				+ "    a.custno = '"+custNo+"'  ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			MasterOutletDataPemerintahanDto data = new MasterOutletDataPemerintahanDto(Objects.toString((String)obj[0], ""), Objects.toString((String)obj[1], ""),
			Objects.toString((String)obj[2], ""), Objects.toString((String)obj[3], ""), Objects.toString((String)obj[4], ""),
			Objects.toString((String)obj[5], ""), Objects.toString((String)obj[6], ""), Objects.toString((String)obj[7], ""),
			Objects.toString((String)obj[8], ""), Objects.toString((String)obj[9], ""));
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<MasterOutletDataCoverDto> getDataCover(String custNo) {
		// TODO Auto-generated method stub
		List<MasterOutletDataCoverDto> result = new ArrayList<>();
		String sql = " SELECT "
				+ "    s.custno, "
				+ "    s.slsno, "
				+ "    ss.slsname, "
				+ "    case "
				+ "        ss.oprtype "
				+ "        when 'T' then 'Order Taking' "
				+ "        when 'C' then 'Canvas' "
				+ "    end tipe, "
				+ "    s.hsenin, "
				+ "    s.hselasa, "
				+ "    s.hrabu, "
				+ "    s.hkamis, "
				+ "    s.hjumat, "
				+ "    s.hsabtu, "
				+ "    s.hminggu, "
				+ "    s.visit1, "
				+ "    s.visit2, "
				+ "    s.visit3, "
				+ "    s.visit4, "
				+ "    to_char(s.route) route, "
				+ "    case "
				+ "        when s.visit1 || s.visit2 || s.visit3 || s.visit4 = 'YYYY' then 'Weekly' "
				+ "        when s.visit1 || s.visit2 || s.visit3 || s.visit4 = 'YTYT' then 'Be Weekly 1' "
				+ "        when s.visit1 || s.visit2 || s.visit3 || s.visit4 = 'TYTY' then 'Be Weekly 2' "
				+ "        when s.visit1 || s.visit2 || s.visit3 || s.visit4 = 'YTTT' then 'Monthly 1' "
				+ "        when s.visit1 || s.visit2 || s.visit3 || s.visit4 = 'TYTT' then 'Monthly 2' "
				+ "        when s.visit1 || s.visit2 || s.visit3 || s.visit4 = 'TTYT' then 'Monthly 3' "
				+ "        when s.visit1 || s.visit2 || s.visit3 || s.visit4 = 'TTTY' then 'Monthly 4' "
				+ "    end as pola "
				+ "FROM "
				+ "    "+t.getTenantId()+".fcustsls s "
				+ "    LEFT JOIN "+t.getTenantId()+".fsls ss ON s.slsno = ss.slsno "
				+ "where "
				+ "    s.custno = '"+custNo+"'  ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			MasterOutletDataCoverDto data = new MasterOutletDataCoverDto(Objects.toString((String)obj[0], ""), Objects.toString((String)obj[1], ""), 
					Objects.toString((String)obj[2], ""), Objects.toString((String)obj[3], ""), Objects.toString((String)obj[4], ""), 
					Objects.toString((String)obj[5], ""), Objects.toString((String)obj[6], ""), Objects.toString((String)obj[7], ""), 
					Objects.toString((String)obj[8], ""), Objects.toString((String)obj[9], ""), Objects.toString((String)obj[10], ""), 
					Objects.toString((String)obj[11], ""), Objects.toString((String)obj[12], ""), Objects.toString((String)obj[13], ""), 
					Objects.toString((String)obj[14], ""), Objects.toString((String)obj[15], ""), Objects.toString((String)obj[16], ""));
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<MasterOutletDataDivisiDto> getDataDivisi(String custNo) {
		// TODO Auto-generated method stub
		List<MasterOutletDataDivisiDto> result = new ArrayList<>();
		String sql = " SELECT "
				+ "    a.custno, "
				+ "    a.sbra2, "
				+ "    b.sbra2name, "
				+ "    a.flagout, "
				+ "    TO_CHAR(a.firstdate, 'DD MON YYYY') firstdate "
				+ "FROM "
				+ "    "+t.getTenantId()+".fcustdiv a "
				+ "    LEFT JOIN "+t.getTenantId()+".ftsbrand2 b ON a.sbra2 = b.sbra2 "
				+ "where "
				+ "    A.custno = '"+custNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			MasterOutletDataDivisiDto data = new MasterOutletDataDivisiDto(Objects.toString((String)obj[0], ""), Objects.toString((String)obj[1], ""),
					Objects.toString((String)obj[2], ""), Objects.toString((String)obj[3], ""), Objects.toString((String)obj[4], ""));
			result.add(data);
		}
		
		return result;
	}

	@Override
	public String getMaxCustNo(String group, String value) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " SELECT TO_CHAR(MAX(m.CUSTNO)) noCust from "+t.getTenantId()+".fcustmst m "
					+ " left join "+t.getTenantId()+".ftable48 b on m.CUSTno = b.CUSTNO "
					+ " where b.groupdivisi = '"+group+"' and b.tf is "+value+" ORDER BY M.CUSTNO asc  ";
			Query query = entityManager.createNativeQuery(sql);
			result = (String) query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}

	@Override
	public List<MasterOutletBrowseDto> getBrowseOutlet(String groupDivisi, String valTaskforce) {
		// TODO Auto-generated method stub
		List<MasterOutletBrowseDto> result = new ArrayList<>();
		String sql = " select m.custno CUSTOMER_NO,m.custname CUSTOMER_NAME,m.custadd1 ADDRESS  "
				+ "from "+t.getTenantId()+".fcustmst m  "
				+ "left join "+t.getTenantId()+".ftable48 b on m.CUSTNO= b.CUSTNO "
				+ "where b.tf is "+valTaskforce+" ";
				if (groupDivisi.length() != 0) {			
					sql+= " AND b.GROUPDIVISI ='"+groupDivisi+"'  ";
				}
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			MasterOutletBrowseDto data = new MasterOutletBrowseDto(Objects.toString((String)obj[0], ""), Objects.toString((String)obj[1], ""),
					Objects.toString((String)obj[2], ""));
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<Object[]> getCekSuggestCustNo(String divisi) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " SELECT "
				+ "    CASE "
				+ "        WHEN LENGTH (new_custno) = 1 THEN '000000' || new_custno "
				+ "        WHEN LENGTH (new_custno) = 2 THEN '00000' || new_custno "
				+ "        WHEN LENGTH (new_custno) = 3 THEN '0000' || new_custno "
				+ "        WHEN LENGTH (new_custno) = 4 THEN '000' || new_custno "
				+ "        WHEN LENGTH (new_custno) = 5 THEN '00' || new_custno "
				+ "        WHEN LENGTH (new_custno) = 6 THEN '0' || new_custno "
				+ "        ELSE new_custno "
				+ "    END new_custno, new_custno as custno1 "
				+ "FROM "
				+ "    ( "
				+ "        SELECT "
				+ "            TO_CHAR (MAX (custno) + 1) new_custno "
				+ "        FROM "
				+ "            "+t.getTenantId()+".fcustmst "
				+ "        WHERE "
				+ "            custno >= ( "
				+ "                SELECT "
				+ "                    memostring2 "
				+ "                FROM "
				+ "                    "+t.getTenantId()+".ftable13 "
				+ "                WHERE "
				+ "                    xkey = 'X_CUSTNO_"+divisi+"_AWAL' "
				+ "            ) "
				+ "            AND custno <= ( "
				+ "                SELECT "
				+ "                    memostring2 "
				+ "                FROM "
				+ "                    "+t.getTenantId()+".ftable13 "
				+ "                WHERE "
				+ "                    xkey = 'X_CUSTNO_"+divisi+"_AKHIR' "
				+ "            ) "
				+ "    ) "
				+ "WHERE "
				+ "    TO_NUMBER(new_custno) < ( "
				+ "        SELECT "
				+ "            TO_NUMBER(memostring2) "
				+ "        FROM "
				+ "            "+t.getTenantId()+".ftable13 "
				+ "        WHERE "
				+ "            xkey = 'X_CUSTNO_"+divisi+"_AKHIR' "
				+ "    )  ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<Object[]> getCekSuggestCustNoFcustmst(String divisi) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " SELECT "
				+ "    * "
				+ "FROM "
				+ "    "+t.getTenantId()+".fcustmst "
				+ "WHERE "
				+ "    custno IN ( "
				+ "        SELECT "
				+ "            memostring2 "
				+ "        FROM "
				+ "            "+t.getTenantId()+".ftable13 "
				+ "        WHERE "
				+ "            xkey = 'X_CUSTNO_"+divisi+"_AWAL' "
				+ "    )  ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public List<Object[]> getCekDate(String custNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " SELECT "
				+ "    custno, "
				+ "    regdate AS TGL_REG, "
				+ "    memodate AS tgl_gudang, "
				+ "    (memodate - regdate) jml "
				+ "FROM "
				+ "    "+t.getTenantId()+".fcustmst, "
				+ "    "+t.getTenantId()+".fmemo "
				+ "WHERE "
				+ "    memonama = 'CADATE' "
				+ "    AND (memodate - regdate) < ( "
				+ "        SELECT "
				+ "            memostring "
				+ "        FROM "
				+ "            "+t.getTenantId()+".ftable13 "
				+ "        WHERE "
				+ "            xkey = 'XCUST' "
				+ "    ) "
				+ "    AND CUSTNO = '"+custNo+"'  ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<Object[]> getCekBlok(String custNo, String oprtype) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql =" SELECT s.custno, s.slsno, ss.oprtype FROM "+t.getTenantId()+".fcustsls s "
				+ " LEFT JOIN "+t.getTenantId()+".fsls ss ON s.slsno = ss.slsno "
				+ " where s.custno = '"+custNo+"' and oprtype='"+oprtype+"' ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public void insertData(String table, String column, String value) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+"."+table+" ("+column+") VALUES ("+value+") ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateData(String table, String columnValue, String where) {
		// TODO Auto-generated method stub
		String sql = " UPDATE "+t.getTenantId()+"."+table+" SET "+columnValue+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteData(String table, String where) {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM "+t.getTenantId()+"."+table+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void insertFtable48(String custNo, String outletType, String groupDivisi, String typeCost, String cost,
			String kdKolektor, String tf, String byKrt, String groupPayer, String flagOutlet, String custNoOld,
			String nik, String groupDelivery) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".ftable48 (custno,outtype,groupdivisi,TYPECOST,COST,EMPNO,TF,BYKRT,grouppayer,CHILLER,custno_old,noktp,groupdeliv) VALUES "
				+ " ('"+custNo+"','"+outletType+"','"+groupDivisi+"', "
				+ " '"+typeCost+"','"+cost+"','"+kdKolektor+"', "
				+ " '"+tf+"','"+byKrt+"','"+groupPayer+"', "
				+ " '"+flagOutlet+"',  '"+custNoOld+"', "
				+ " '"+nik+"', "
				+ " '"+groupDelivery+"')  ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	@Override
	public void updateFtable48Add(String outletType, String groupDivisi, String custNoOld, String nik,
			String groupDelivery, String custNo) {
		// TODO Auto-generated method stub
		String sql = " update "+t.getTenantId()+".ftable48 set outtype = '"+outletType+"', groupdivisi = '"+groupDivisi+"', "
				+ " custno_old = '"+custNoOld+"' , "
				+ " noktp = '"+nik+"', "
				+ " groupdeliv = '"+groupDelivery+"' "
				+ " where custno =  '"+custNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	@Override
	public void insertFtable64(String data1, String custNo, String limit, String groupOutlet) {
		// TODO Auto-generated method stub
		String sql = " insert into "+t.getTenantId()+".ftabel64 (data1,data2,data3,data4,data5) "
				+ " values ('"+data1+"','"+custNo+"','"+limit+"','0','0') ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	@Override
	public void updateFtable64(String limit, String custNo, String groupOutlet) {
		// TODO Auto-generated method stub
		String sql = " update "+t.getTenantId()+".ftabel64 set data3 = '"+limit+"' where data2 = '"+groupOutlet+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void insertFcustmst(String custno, String custname, String custadd1, String custadd2, String ccity,
			String cphone1, String ckdpos, String data02, String data03, String data04, String flagout, String ccontact,
			String cfaxno, String data05, String data06, String cterm, String cweekno, String flaglimit, String data14,
			String climit, String flagpay, String flagkbon, String data10, String data11, String firstopen,
			String regdate, String data19, String location, String data16, String kdpsr, String typeout, String klass,
			String grupout, String distrik, String beat, String sbeat, String kdind, String data15, String data17,
			String data18, String gdisc, String gplu, String gkonv, String gharga, String taxname, String taxadd1,
			String taxadd2, String taxcity, String npwp, String taxflag, String agenlain, String norefsup,
			String data07, String data08, String data09, String bank, String noacc, String cresd1, String cresd2,
			String ccity2, String cphone2, String flaghome) {
		// TODO Auto-generated method stub
		String sql =" INSERT INTO "+t.getTenantId()+".fcustmst  "
				+ " (custno, custname, custadd1, custadd2, ccity, cphone1, ckdpos, "
				+ " data02,data03,data04,flagout, "
				+ " ccontact, cfaxno, data05, data06, cterm, cweekno, flaglimit,data14,  "
				+ " climit, flagpay, flagkbon, data10, data11, firstopen, regdate, "
				+ " data19, location, data16, kdpsr, typeout, class, grupout,  "
				+ " distrik, beat, sbeat, kdind, data15, data17, data18, gdisc, "
				+ " gplu, gkonv, gharga, taxname, taxadd1, taxadd2, taxcity, npwp,   "
				+ " taxflag, agenlain, norefsup, data07, data08, data09, bank, "
				+ " noacc, cresd1, cresd2, ccity2, cphone2, flaghome  "
				+ "  )  "
				+ " VALUES ('"+custno+"',  "
				+ " '"+custname+"',  "
				+ " '"+custadd1+"',  "
				+ " '"+custadd2+"',  "
				+ " '"+ccity+"',  "
				+ " '"+cphone1+"',  "
				+ " '"+ckdpos+"',  "
				+ " '"+data02+"',  "
				+ " '"+data03+"',  "
				+ " '"+data04+"', "
				+ " 'O',  "
				+ " '"+ccontact+"',  "
				+ " '"+cfaxno+"',  "
				+ " '"+data05+"',  "
				+ " '"+data06+"',  "
				+ " '"+cterm+"',  "
				+ " '"+cweekno+"', "
				+ " 'Y',  "
				+ " '"+data14+"',  "
				+ " '"+climit+"',  "
				+ " '"+flagpay+"',  "
				+ " '"+flagkbon+"',  "
				+ " '"+data10+"',  "
				+ " '"+data11+"',  "
				+ " '"+firstopen+"',  "
				+ " '"+regdate+"',  "
				+ " '"+data19+"',  "
				+ " '"+location+"',  "
				+ " '"+data16+"',  "
				+ " '"+kdpsr+"',  "
				+ " '"+typeout+"',  "
				+ " '"+klass+"',  "
				+ " '"+grupout+"',  "
				+ " '"+distrik+"',  "
				+ " '"+beat+"',  "
				+ " '"+sbeat+"',  "
				+ " '"+kdind+"',  "
				+ " '"+data15+"',  "
				+ " '"+data17+"',  "
				+ " '"+data18+"',  "
				+ " '"+gdisc+"',  "
				+ " '"+gplu+"',  "
				+ " '"+gkonv+"',  "
				+ " '"+gharga+"',  "
				+ " '"+taxname+"',  "
				+ " '"+taxadd1+"',  "
				+ " '"+taxadd2+"',  "
				+ " '"+taxcity+"',  "
				+ " '"+npwp+"',  "
				+ " '"+taxflag+"',  "
				+ " '"+agenlain+"',  "
				+ " '"+norefsup+"',  "
				+ " '"+data07+"',  "
				+ " '"+data08+"',  "
				+ " '"+data09+"',  "
				+ " '"+bank+"',  "
				+ " '"+noacc+"',  "
				+ " '"+cresd1+"',  "
				+ " '"+cresd2+"',  "
				+ " '"+ccity2+"',  "
				+ " '"+cphone2+"',  "
				+ " '"+flaghome+"'  "
				+ " ) ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public void updateFcustmst(String custno, String custname, String custadd1, String custadd2, String ccity,
			String cphone1, String ckdpos, String data02, String data03, String data04, String ccontact, String cfaxno,
			String data05, String data06, String cterm, String cweekno, String data14, String climit, String flagpay,
			String flagkbon, String data10, String data11, String clastupd, String location, String data16,
			String kdpsr, String typeout, String klass, String grupout, String distrik, String beat, String sbeat,
			String kdind, String data15, String data17, String data18, String gdisc, String gplu, String gkonv,
			String gharga, String taxname, String taxadd1, String taxadd2, String taxcity, String npwp, String taxflag,
			String agenlain, String norefsup, String data07, String data08, String data09, String bank, String noacc,
			String cresd1, String cresd2, String ccity2, String cphone2, String flaghome) {
		// TODO Auto-generated method stub
		String sql = " UPDATE "+t.getTenantId()+".fcustmst  "
				+ "SET  "
				+ "custname = '"+custname+"',  "
				+ "custadd1 = '"+custadd1+"',  "
				+ "custadd2 = '"+custadd2+"',  "
				+ "ccity = '"+ccity+"',  "
				+ "cphone1 = '"+cphone1+"',  "
				+ "ckdpos = '"+ckdpos+"',  "
				+ "data02 = '"+data02+"',  "
				+ "data03 = '"+data03+"',  "
				+ "data04 = '"+data04+"',  "
				+ "ccontact = '"+ccontact+"',  "
				+ "cfaxno = '"+cfaxno+"',  "
				+ "data05 = '"+data05+"',  "
				+ "data06 = '"+data06+"',  "
				+ "cterm = '"+cterm+"',  "
				+ "cweekno = '"+cweekno+"',  "
				+ "data14 = '"+data14+"',  "
				+ "climit = '"+climit+"',  "
				+ "flagpay = '"+flagpay+"',  "
				+ "flagkbon = '"+flagkbon+"',  "
				+ "data10 = '"+data10+"',  "
				+ "data11 = '"+data11+"',  "
				+ "clastupd = '"+clastupd+"',  "
				+ "location = '"+location+"',  "
				+ "data16 =  '"+data16+"',  "
				+ "kdpsr = '"+kdpsr+"',  "
				+ "typeout = '"+typeout+"',  "
				+ "class = '"+klass+"',  "
				+ "grupout = '"+grupout+"',  "
				+ "distrik = '"+distrik+"',  "
				+ "beat = '"+beat+"',  "
				+ "sbeat = '"+sbeat+"',  "
				+ "kdind = '"+kdind+"',  "
				+ "data15 = '"+data15+"',  "
				+ "data17 = '"+data17+"',  "
				+ "data18 = '"+data18+"',  "
				+ "gdisc = '"+gdisc+"',  "
				+ "gplu = '"+gplu+"',  "
				+ "gkonv = '"+gkonv+"',  "
				+ "gharga = '"+gharga+"',  "
				+ "taxname = '"+taxname+"',  "
				+ "taxadd1 = '"+taxadd1+"',  "
				+ "taxadd2 = '"+taxadd2+"',  "
				+ "taxcity = '"+taxcity+"',  "
				+ "npwp = '"+npwp+"',  "
				+ "taxflag = '"+taxflag+"',  "
				+ "agenlain = '"+agenlain+"',  "
				+ "norefsup = '"+norefsup+"',  "
				+ "data07 = '"+data07+"',  "
				+ "data08 = '"+data08+"',  "
				+ "data09 = '"+data09+"',  "
				+ "bank = '"+bank+"',  "
				+ "noacc = '"+noacc+"',  "
				+ "cresd1 = '"+cresd1+"',  "
				+ "cresd2 = '"+cresd2+"',  "
				+ "ccity2 = '"+ccity2+"',  "
				+ "cphone2 = '"+cphone2+"',  "
				+ "flaghome = '"+flaghome+"'  "
				+ "WHERE custno = '"+custno+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateFtable48Update(String custNo, String outletType, String groupDivisi, String typeCost, String cost,
			String kdKolektor, String byKrt, String flagOutlet, String custNoOld, String nik, String groupDelivery,
			String groupPayer) {
		// TODO Auto-generated method stub
		String sql = " UPDATE "+t.getTenantId()+".ftable48  "
				+ "SET  "
				+ "outtype = '"+outletType+"',  "
				+ "groupdivisi = '"+groupDivisi+"',  "
				+ "typecost = '"+typeCost+"',  "
				+ "cost = '"+cost+"',  "
				+ "EMPNO = '"+kdKolektor+"',  "
				+ "BYKRT = '"+byKrt+"',  "
				+ "chiller = '"+flagOutlet+"',  "
				+ "CUSTNO_OLD = '"+custNoOld+"',  "
				+ "noktp = '"+nik+"',  "
				+ "groupdeliv = '"+groupDelivery+"',  "
				+ "grouppayer = '"+groupPayer+"'  "
				+ "WHERE custno = '"+custNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateFcustudUpdate(String custNo, String kdProp, String kdKab, String kdKec, String kdKel) {
		// TODO Auto-generated method stub
		String sql = " UPDATE "+t.getTenantId()+".fcustud  "
				+ "SET  "
				+ "H11 = '"+kdProp+"',  "
				+ "H12 = '"+kdKab+"',  "
				+ "H13 = '"+kdKec+"',  "
				+ "H14 = '"+kdKel+"'  "
				+ "WHERE custno = '"+custNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> getCekEditCover(String custNo, String slsNo, String groupDivisi) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql ="SELECT custno, slsname FROM (SELECT DISTINCT (slsno), slsname, oprtype "
			    +" FROM (SELECT s.slsno, s.slsname, s.team, s.oprtype, a.data2 "
			    +" FROM "+t.getTenantId()+".fsls s LEFT JOIN "+t.getTenantId()+".ftabel12 a "
			    +" ON s.slsno = a.slsno "
			    +" ) a "
			    +" INNER JOIN "
			    +" (SELECT * "
			    +" FROM "+t.getTenantId()+".ftable84 "
			    +" WHERE groupdivisi = '"+groupDivisi+"') b ON a.data2 = b.divisi "
			    +" where slsno = '"+slsNo+"' "
			    +" ) A LEFT JOIN "+t.getTenantId()+".FCUSTSLS C ON A.SLSNO = C.SLSNO WHERE CUSTNO = '"+custNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public void updateDataCover(MasterOutletDataCoverDto p) {
		// TODO Auto-generated method stub
		String sql = " update "+t.getTenantId()+".fcustsls set "
				+ " custno = '"+p.getCustno()+"' , "
				+ " slsno = '"+p.getSlsno()+"' , "
				+ " hsenin = '"+p.getHsenin()+"' , "
				+ " hselasa = '"+p.getHselasa()+"' , "
				+ " hrabu = '"+p.getHrabu()+"' , "
				+ " hkamis = '"+p.getHkamis()+"' , "
				+ " hjumat = '"+p.getHjumat()+"' , "
				+ " hsabtu = '"+p.getHsabtu()+"' , "
				+ " hminggu = '"+p.getHminggu()+"' , "
				+ " visit1 = '"+p.getVisit1()+"' , "
				+ " visit2 = '"+p.getVisit2()+"' , "
				+ " visit3 = '"+p.getVisit3()+"' , "
				+ " visit4 = '"+p.getVisit4()+"' , "
				+ " route = '"+p.getRoute()+"' "
				+ " where custno = '"+p.getCustno()+"' "
				+ " and slsno = '"+p.getSlsno()+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	@Override
	public void addDataCover(MasterOutletDataCoverDto p) {
		// TODO Auto-generated method stub
		String sql = " insert into "+t.getTenantId()+".fcustsls (custno, slsno, hsenin, hselasa, hrabu, hkamis, hjumat, hsabtu, hminggu, visit1, visit2, visit3, visit4, route) "
				+ " values ('"+p.getCustno()+"', '"+p.getSlsno()+"', '"+p.getHsenin()+"', '"+p.getHselasa()+"', '"+p.getHrabu()+"', '"+p.getHkamis()+"', '"+p.getHjumat()+"', '"+p.getHsabtu()+"', '"+p.getHminggu()+"', '"+p.getVisit1()+"', '"+p.getVisit2()+"', '"+p.getVisit3()+"', '"+p.getVisit4()+"', '"+p.getRoute()+"') ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<SalesmanBrowseDto> getBrowseSalesman(String groupDivisi, String taskforce) {
		// TODO Auto-generated method stub
		List<SalesmanBrowseDto> result = new ArrayList<>();
		String sql = "  ";
		if (taskforce.equalsIgnoreCase("null")) {
			sql ="SELECT DISTINCT (slsno), slsname, oprtype"
              +" FROM (SELECT s.slsno, s.slsname, s.team, s.oprtype, a.data2 "
              +" FROM "+t.getTenantId()+".fsls s LEFT JOIN "+t.getTenantId()+".ftabel12 a ON s.slsno = a.slsno "
              +" ) a "
              +" INNER JOIN "
              +" (SELECT * "
              +" FROM "+t.getTenantId()+".ftable84 "
              +" WHERE groupdivisi = '"+groupDivisi+"') b ON a.data2 = b.divisi";
		} else {
			sql ="SELECT DISTINCT (slsno), slsname, oprtype"
              +" FROM (SELECT s.slsno, s.slsname, s.team, s.oprtype, a.data2 "
              +" FROM (select * from "+t.getTenantId()+".fsls where data04 in (select memostring from "+t.getTenantId()+".ftable13 where xkey = 'SLSFORCE_TF')) s LEFT JOIN "+t.getTenantId()+".ftabel12 a ON s.slsno = a.slsno "
              +" ) a "
              +" INNER JOIN "
              +" (SELECT * "
              +" FROM "+t.getTenantId()+".ftable84 "
              +" WHERE groupdivisi = '"+groupDivisi+"') b ON a.data2 = b.divisi";
		}
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SalesmanBrowseDto data = new SalesmanBrowseDto((String) obj[0], (String) obj[1], (String) obj[2],
			null, null, null, null, null);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<GroupPayerBrowseDto> getBrowseGroupPayer(String taskforce) {
		// TODO Auto-generated method stub
		List<GroupPayerBrowseDto> result = new ArrayList<>();
		String sql = " select m.custno CUSTOMER_NO,m.custname CUSTOMER_NAME,m.custadd1 ADDRESS, b.GROUPDIVISI DIVISION "
				+ " from "+t.getTenantId()+".fcustmst m left join "+t.getTenantId()+".ftable48 b on m.CUSTNO= b.CUSTNO "
				+ " where b.tf is "+taskforce+" order by b.groupdivisi, m.custno asc ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			GroupPayerBrowseDto data = new GroupPayerBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3]);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<SearchBrowseDto> getBrowseChiller() {
		// TODO Auto-generated method stub
		List<SearchBrowseDto> result = new ArrayList<>();
		String sql = " SELECT KDALASAN KODE, ALASAN FLAG_NAME FROM "+t.getTenantId()+".FTABLE01 WHERE tipealasan = '13' ORDER BY TO_NUMBER(kode) ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SearchBrowseDto data = new SearchBrowseDto((String) obj[0], (String) obj[1]);
			result.add(data);
		}
		
		return result;
	}

}
