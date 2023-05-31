package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ProsesNooBrowseDto;
import myor.matrix.master.entity.ProsesNooDataProsesDto;
import myor.matrix.master.entity.ProsesNooDetailDto;
import myor.matrix.master.entity.ProsesNooEditDto;
import myor.matrix.master.entity.ProsesNooFppDto;
import myor.matrix.master.entity.ProsesNooInputDto;
import myor.matrix.master.entity.ProsesNooRekapDto;
import myor.matrix.master.repository.ProsesNooRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ProsesNooRepositoryImpl implements ProsesNooRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String getXkeyString(String table, String column, String where) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+where+"  ";
			Query query = entityManager.createNativeQuery(sql);
			result = Objects.toString(query.getSingleResult(), "");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}
	
	
	@Override
	public String getXkeyStringJoin(String table, String column, String join, String where) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+join+" "+where+"  ";
			Query query = entityManager.createNativeQuery(sql);
			result = Objects.toString(query.getSingleResult(), "");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}
	
	@Override
	public String getFromDualString( String table, String column, String where) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " select "+column+" from "+table+" "+where+"  ";
			Query query = entityManager.createNativeQuery(sql);
			result = Objects.toString(query.getSingleResult(), "");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}

	@Override
	public List<Object[]> getXkeyListObject(String table, String column, String where) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public List<Object[]> getXkeyListObjectJoin(String table, String column, String join, String where) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+join+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public List<Object[]> getListObjectQuery(String query1) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = query1;
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public void deleteData(String table, String where) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM "+t.getTenantId()+"."+table+" "+where+" ";	
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateData(String table, String values, String where) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+"."+table+" "+values+" "+where+" ";	
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void insertData(String table, String column, String value) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+"."+table+" ("+column+") values ("+value+") ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public void insertDataSelect(String table, String select, String where) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+"."+table+" "+select+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}


	@Override
	public List<ProsesNooBrowseDto> getBrowseDocument() {
		// TODO Auto-generated method stub
		List<ProsesNooBrowseDto> result = new ArrayList<>();
		
		String sql = "	SELECT a.docno DOCNO, TO_CHAR(a.DATE_CREATED, 'DD MON YYYY') DOCDATE,"
				+ "  XKEY REQUESTKEY, GROUPDIVISI  FROM "+t.getTenantId()+".FTABLE142_H "
				+ "a  WHERE DATE_PROCESS IS NULL  ORDER BY TO_NUMBER(a.docno) desc 	";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProsesNooBrowseDto data = new ProsesNooBrowseDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""),
					Objects.toString(obj[2], ""), Objects.toString(obj[3], ""), "", "");
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<ProsesNooBrowseDto> getBrowseDataDocument() {
		// TODO Auto-generated method stub
		List<ProsesNooBrowseDto> result = new ArrayList<>();
		
		String sql = "	SELECT a.seq_no DOCNO, TO_CHAR(a.docdate, 'DD MON YYYY') DOCDATE, "
				+ "  a.slsno SLSNO , b.slsname SLSNAME  FROM "+t.getTenantId()+".ftable141 a "
				+ " LEFT JOIN "+t.getTenantId()+".fsls b ON a.slsno=b.slsno "
				+ " LEFT JOIN "+t.getTenantId()+".ftable142_d c ON a.seq_no=c.docno_noo  WHERE a.status='0' AND "
				+ " c.docno_noo IS NULL  ORDER BY a.docno asc 	";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProsesNooBrowseDto data = new ProsesNooBrowseDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""), "", "",
					Objects.toString(obj[2], ""), Objects.toString(obj[3], ""));
			result.add(data);
		}
		return result;
	}


	@Override
	public List<ProsesNooDetailDto> getDetailNoo(String docNo) {
		// TODO Auto-generated method stub
		List<ProsesNooDetailDto> result = new ArrayList<>();
		
		String sql = "	SELECT c.docno_noo docno, TO_CHAR(a.docdate,'DD MON YYYY') docdate, a.slsno, b.slsname, "
				+ "  a.custname, a.custadd1||''||a.custadd2 custadd, b.oprtype "
				+ " FROM "+t.getTenantId()+".ftable142_d c  INNER JOIN "+t.getTenantId()+".ftable141 a ON c.docno_noo=a.seq_no "
				+ " LEFT JOIN "+t.getTenantId()+".fsls b ON a.slsno = b.slsno  WHERE c.docno='"+docNo+"' 	";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProsesNooDetailDto data = new ProsesNooDetailDto(
					Objects.toString(obj[0], ""), Objects.toString(obj[1], ""),
					Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""));
			result.add(data);
		}
		return result;
	}


	@Override
	public String getQueryCekData(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		String sql =" SELECT a.seq_no docno, a.docdate, a.slsno, b.slsname, " 
			     +" a.custname, a.custadd1||''||a.custadd2 custadd "
			     +" FROM "+t.getTenantId()+".ftable141 a LEFT JOIN "+t.getTenantId()+".fsls b ON a.slsno = b.slsno "
			     +" LEFT JOIN "+t.getTenantId()+".ftable142_d c ON a.seq_no=c.docno_noo "
			     +" WHERE a.docno IS NOT NULL AND a.status='0' AND c.docno_noo IS NULL ";
		if (p.getTanggalFrom().length() != 0) {
			sql += " AND a.docdate >= '"+p.getTanggalFrom()+"' ";
		}
		if (p.getTanggalTo().length() != 0) {
			sql += " AND a.docdate <= '"+p.getTanggalTo()+"' ";
		}
		if (p.getNoSalesFrom().length() != 0) {
			sql += " AND a.slsno >= '"+p.getNoSalesFrom()+"' ";
		}
		if (p.getNoSalesTo().length() != 0) {
			sql += " AND a.slsno <= '"+p.getNoSalesTo()+"' ";
		}
		sql += " ORDER BY a.docno ";
		
		return sql;
	}


	@Override
	public List<ProsesNooFppDto> getDataPrintFpp(String docNo, String path) {
		// TODO Auto-generated method stub
		List<ProsesNooFppDto> result = new ArrayList<>();
		
		String sql =" SELECT TO_CHAR(ID_) ID, DOCNO, DOCDATE, SLSNO, SLSNAME, CUSTNAME, CUSTADD, SHIPADD, CCONTACT, CPHONE1, NO_KTP, H11, KET11, H12, KET12, H13, KET13, H14, KET14, NOKARTU1, NOBARCODE1, TAXNAME, TAXADD, NPWP, POSTAL_CODE, TAX_BUILDING, STATUS, APPROVEDATE, CUSTNO, NAMAFILE_KTP, NAMAFILE_NPWP, NAMAFILE_OWNER, NAMAFILE_OUTLET, HEADER, TGLCETAK, TGLGUDANG, RELEASE, KODEDIST, COMPANYNAME, KOTA, XDOC FROM "
				+ " (SELECT ROWNUM id_, a.* FROM ( "
			     +"     SELECT a.seq_no docno, TO_CHAR (a.docdate, 'DD MON YYYY') docdate, a.slsno, h.slsname, a.custname, "
			     +"     a.custadd1||''||a.custadd2 custadd, a.shipadd1||a.shipadd2||a.shipadd3 shipadd, a.ccontact, a.cphone1, no_ktp, "
			     +"     a.h11, b.ket ket11, a.h12, c.ket ket12, a.h13, d.ket ket13, a.h14, e.ket ket14, a.nokartu1, a.nobarcode1, "
			     +"     a.taxname, a.taxadd1||''||a.taxadd2 taxadd, a.npwp, postal_code, tax_building, "
			     +"     CASE WHEN a.status = '0' THEN 'UNPROCESSED' "
			     +"          WHEN a.status = '1' THEN 'APPROVED' "
			     +"          WHEN a.status = '2' THEN 'CANCELED' "
			     +"          ELSE '' "
			     +"     END status, "
			      +"    TO_CHAR (a.approvedate, 'DD MON YYYY') approvedate, a.custno, "
			      +"    case when k.img_name is null then '' else  "
			      +"    '"+path+"' || k.img_name end namafile_ktp,  "
			      +"    case when n.img_name is null then '' else  "
			      +"    '"+path+"' || n.img_name end namafile_npwp, "
			      +"     case when o.img_name is null then '' else  "
			      +"    '"+path+"' || o.img_name end namafile_owner, "
			      +"    case when o2.img_name is null then '' else  "
			      +"   '"+path+"' || o2.img_name end namafile_outlet, "
			      + " 'FORM PENDATAAN PELANGGAN (FPP)' as HEADER, "
			      + " TO_CHAR (SYSDATE, 'DD MON YYYY HH24:MI') AS tglcetak, "
			      + " ( SELECT TO_CHAR(MEMODATE, 'DD MON YYYY') datememo FROM "+t.getTenantId()+".FMEMO WHERE MEMONAMA = 'CADATE') AS tglgudang, "
			      + " ( SELECT TO_CHAR(XNO) FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') AS RELEASE, "
			      + " (select memostring from "+t.getTenantId()+".fmemo where memonama='KODEDIST') AS KODEDIST, "
			      + " (select memostring from "+t.getTenantId()+".fmemo where memonama='COMPANYNAME') AS COMPANYNAME, "
			      + " (select MEMOSTRING from "+t.getTenantId()+".fmemo where memonama ='KOTA') KOTA, "
			      + " '"+docNo+"' AS XDOC "
			     +"     FROM "+t.getTenantId()+".ftable142_h f "
			     +"     INNER JOIN "+t.getTenantId()+".ftable142_d g ON f.docno=g.docno "
			     +"     INNER JOIN "+t.getTenantId()+".ftable141 a ON g.docno_noo = a.seq_no                                     "
			     +"     left join (select * from "+t.getTenantId()+".ftable141_photo where type='1') k on k.SEQ_NO = a.seq_no  "
			     +"     left join (select * from "+t.getTenantId()+".ftable141_photo where type='2') n on n.SEQ_NO = a.seq_no  "
			     +"     left join (select * from "+t.getTenantId()+".ftable141_photo where type='3') o on o.SEQ_NO = a.seq_no  "
			     +"     left join (select * from "+t.getTenantId()+".ftable141_photo where type='4') o2 on o2.SEQ_NO = a.seq_no "
			     +"     LEFT JOIN "+t.getTenantId()+".fcshir11 b ON a.h11 = b.t11 "
			     +"     LEFT JOIN "+t.getTenantId()+".fcshir12 c ON a.h11 = c.t11 AND a.h12 = c.t12 "
			     +"     LEFT JOIN "+t.getTenantId()+".fcshir13 d ON a.h11 = d.t11 AND a.h12 = d.t12 AND a.h13 = d.t13 "
			     +"     LEFT JOIN "+t.getTenantId()+".fcshir14 e ON a.h11 = e.t11 AND a.h12 = e.t12 AND a.h13 = e.t13 AND a.h14 = e.t14 "
			     +"     LEFT JOIN "+t.getTenantId()+".fsls h ON a.slsno = h.slsno "
			     +"     WHERE f.docno='"+docNo+"' "
			     +"     ORDER BY a.docno "
			     +" ) a order by slsno, docno) b ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProsesNooFppDto data = new ProsesNooFppDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""),
					Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""),
					Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					Objects.toString(obj[13], ""), Objects.toString(obj[14], ""),
					Objects.toString(obj[15], ""), Objects.toString(obj[16], ""),
					Objects.toString(obj[17], ""), Objects.toString(obj[18], ""),
					Objects.toString(obj[19], ""), Objects.toString(obj[20], ""),
					Objects.toString(obj[21], ""), Objects.toString(obj[22], ""),
					Objects.toString(obj[23], ""), Objects.toString(obj[24], ""),
					Objects.toString(obj[25], ""), Objects.toString(obj[26], ""),
					Objects.toString(obj[27], ""), Objects.toString(obj[28], ""),
					Objects.toString(obj[29], ""), Objects.toString(obj[30], ""),
					Objects.toString(obj[31], ""), Objects.toString(obj[32], ""),
					Objects.toString(obj[33], ""), Objects.toString(obj[34], ""),
					Objects.toString(obj[35], ""), Objects.toString(obj[36], ""),
					Objects.toString(obj[37], ""), Objects.toString(obj[38], ""),
					Objects.toString(obj[39], ""),Objects.toString(obj[40], ""));
			result.add(data);
		}
		return result;
	}


	@Override
	public List<ProsesNooRekapDto> getDataPrintRekap(String docNo, String docDate, String reqKey) {
		// TODO Auto-generated method stub
		List<ProsesNooRekapDto> result = new ArrayList<>();
		
		String sql =" SELECT   a.seq_no docno, TO_CHAR (a.docdate, 'DD MON YYYY') docdate, "
			      +"   case when approve='N' then null else a.slsno||'-'||h.slsname end as salesman, a.custname, "
			      +"   a.custadd1 || a.custadd2 custadd, "
			      +"   a.shipadd1 || a.shipadd2 || a.shipadd3 shipadd, "
			      +"   a.ccontact, a.cphone1, no_ktp, a.h11, b.ket ket11, a.h12, "
			      +"   c.ket ket12, a.h13, d.ket ket13, a.h14, e.ket ket14, "
			      +"   case when approve='N' then '' else nokartu1 end as nokartu1, a.nobarcode1, a.taxname, "
			      +"   a.taxadd1 || a.taxadd2 taxadd,case when approve='N' then null else typeout||'-'||typename end as channel, "
			      +"   case when approve='N' then null else grupname end as grupname, "
			      +"   case when approve='N' then null else cterm end as cterm, "
			      +"   case when approve='N' then null else climit end as climit,  "
			      +"   case when approve='N' then '' else case when flagpay='K' then 'Kredit' when flagpay='T' then 'Tunai' when flagpay='G' then 'Giro' end end as paytype, "
			      +"   case when approve='N' then '' else case when hsenin='Y' then 'Senin' when hselasa='Y' then 'Selasa' when hrabu='Y' then 'Rabu'  "
			       +"   when hkamis='Y' then 'Kamis' when hjumat='Y' then 'Jumat' when hsabtu='Y' then 'Sabtu' else 'Minggu' end end as kunj,  "
			      +"   case when approve='N' then '' else case when visit1||visit2||visit3||visit4='YYYY' then 'Weekly'  "
			      +"   when visit1||visit2||visit3||visit4='YTYT' then 'Be Weekly1'  "
			      +"   when visit1||visit2||visit3||visit4='TYTY' then 'Be Weekly2' "
			      +"   when visit1||visit2||visit3||visit4='YTTT' then 'Monthly 1' "
			      +"   when visit1||visit2||visit3||visit4='TYTT' then 'Monthly 2' "
			      +"   when visit1||visit2||visit3||visit4='TTYT' then 'Monthly 3' "
			      +"   when visit1||visit2||visit3||visit4='TTTY' then 'Monthly 4' end end as pola, "
			      +"   case when approve='Y' then 'DITERIMA' when approve='N' then 'DITOLAK' end as decision, "
			      + " 'REKAP PENGAJUAN NEW OPEN OUTLET' as HEADER, "
			      + " TO_CHAR (SYSDATE, 'DD MON YYYY HH24:MI') AS tglcetak, "
			      + " ( SELECT TO_CHAR(MEMODATE, 'DD MON YYYY') datememo FROM "+t.getTenantId()+".FMEMO WHERE MEMONAMA = 'CADATE') AS tglgudang, "
			      + " ( SELECT TO_CHAR(XNO) FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') AS RELEASE, "
			      + " (select memostring from "+t.getTenantId()+".fmemo where memonama='KODEDIST') AS KODEDIST, "
			      + " (select memostring from "+t.getTenantId()+".fmemo where memonama='COMPANYNAME') AS COMPANYNAME, "
			      + " (select MEMOSTRING from "+t.getTenantId()+".fmemo where memonama ='KOTA') KOTA, "
			      + " '"+docNo+"' AS XDOC, '"+docDate+"' AS XDOCDATE, '"+reqKey+"' AS REQKEY  "
			      +"             FROM "+t.getTenantId()+".ftable142_h f INNER JOIN "+t.getTenantId()+".ftable142_d g "
			      +"   ON f.docno = g.docno "
			      +"   INNER JOIN "+t.getTenantId()+".ftable141 a ON g.docno_noo = a.seq_no "
			      +"   LEFT JOIN "+t.getTenantId()+".fcshir11 b ON a.h11 = b.t11 "
			      +"   LEFT JOIN "+t.getTenantId()+".fcshir12 c ON a.h11 = c.t11 AND a.h12 = c.t12 "
			      +"   LEFT JOIN "+t.getTenantId()+".fcshir13 d ON a.h11 = d.t11 AND a.h12 = d.t12 AND a.h13 = d.t13 "
			      +"   LEFT JOIN "+t.getTenantId()+".fcshir14 e "
			      +"   ON a.h11 = e.t11 "
			      +"                AND a.h12 = e.t12 "
			      +"                AND a.h13 = e.t13 "
			      +"                AND a.h14 = e.t14 "
			      +"   LEFT JOIN "+t.getTenantId()+".fsls h ON a.slsno = h.slsno "
			      +"   left join "+t.getTenantId()+".ftypeout t on t.type = a.typeout "
			      +"   left join "+t.getTenantId()+".fgrupout o on o.GRUPOUT = a.grupout "
			      +"            WHERE f.docno = '"+docNo+"' "
			      +"         ORDER BY a.docno ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProsesNooRekapDto data = new ProsesNooRekapDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""),
					Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""),
					Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					Objects.toString(obj[13], ""), Objects.toString(obj[14], ""),
					Objects.toString(obj[15], ""), Objects.toString(obj[16], ""),
					Objects.toString(obj[17], ""), Objects.toString(obj[18], ""),
					Objects.toString(obj[19], ""), Objects.toString(obj[20], ""),
					Objects.toString(obj[21], ""), Objects.toString(obj[22], ""),
					new BigDecimal(Objects.toString(obj[23], "0")),
					new BigDecimal(Objects.toString(obj[24], "0")),
					Objects.toString(obj[25], ""), Objects.toString(obj[26], ""),
					Objects.toString(obj[27], ""), Objects.toString(obj[28], ""),
					Objects.toString(obj[29], ""), Objects.toString(obj[30], ""),
					Objects.toString(obj[31], ""), Objects.toString(obj[32], ""),
					Objects.toString(obj[33], ""), Objects.toString(obj[34], ""),
					Objects.toString(obj[35], ""), Objects.toString(obj[36], ""),
					Objects.toString(obj[37], ""), Objects.toString(obj[38], ""));
			result.add(data);
		}
		return result;
	}


	@Override
	public List<ProsesNooDataProsesDto> getDataProcess(String docNo) {
		// TODO Auto-generated method stub
		List<ProsesNooDataProsesDto> result = new ArrayList<>();
		
		String sql =" SELECT DOCNO, TO_CHAR(DOCDATE, 'DD MON YYYY') DOCDATE, SLSNO, "
				+ "        CUSTNAME, CUSTADD1, CUSTADD2, SHIPADD1, SHIPADD2, SHIPADD3, CCONTACT, CPHONE1, "
				+ "        H11 AS HH11, H12 AS HH12, H13 AS HH13, H14 AS HH14, H15 AS HH15, NOKARTU1, "
				+ "        NOBARCODE1, TAXNAME, TAXADD1, TAXADD2, NPWP, LATITUDE, LONGITUDE, STATUS, "
				+ "        APPROVEDATE, CUSTNO, DOCID, NAMAFILE, NAMA_KTP, ALAMAT_KTP_1, ALAMAT_KTP_2, NAMA_PEMILIK, "
				+ "        SEQ_NO, POSTAL_CODE, TITLE_PERSON, NO_KTP, TAX_CITY, TAX_BUILDING, NOKARTU2, NOKARTU3, TYPEOUT,         CLASS, GRUPOUT, KDIND, CTERM, CLIMIT, FLAGPAY, "
				+ "        HSENIN, HSELASA, HRABU, HKAMIS, HJUMAT, HSABTU, HMINGGU, "
				+ "        VISIT1, VISIT2, VISIT3, VISIT4, ROUTE, "
				+ "        APPROVE , ket kabupaten, case when tax_building='Milik Sendiri' then '1' when tax_building='Sewa' then '3' end as flaghome "
				  +"  FROM "+t.getTenantId()+".ftable142_h a "
		          +" INNER JOIN "+t.getTenantId()+".ftable142_d b ON a.docno = b.docno "
		          +" INNER JOIN "+t.getTenantId()+".ftable141 c ON b.docno_noo = c.seq_no "
		          +" left join "+t.getTenantId()+".fcshir12 d on c.h11 = d.t11 AND c.h12 = d.t12 "
		          +" WHERE a.docno = '"+docNo+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			ProsesNooDataProsesDto data = new ProsesNooDataProsesDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""),
					Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""),
					Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					Objects.toString(obj[13], ""), Objects.toString(obj[14], ""),
					Objects.toString(obj[15], ""), Objects.toString(obj[16], ""),
					Objects.toString(obj[17], ""), Objects.toString(obj[18], ""),
					Objects.toString(obj[19], ""), Objects.toString(obj[20], ""),
					Objects.toString(obj[21], ""), Objects.toString(obj[22], ""),
					Objects.toString(obj[23], ""), Objects.toString(obj[24], ""),
					Objects.toString(obj[25], ""), Objects.toString(obj[26], ""),
					Objects.toString(obj[27], ""), Objects.toString(obj[28], ""),
					Objects.toString(obj[29], ""), Objects.toString(obj[30], ""),
					Objects.toString(obj[31], ""), Objects.toString(obj[32], ""),
					Objects.toString(obj[33], ""), Objects.toString(obj[34], ""),
					Objects.toString(obj[35], ""), Objects.toString(obj[36], ""),
					Objects.toString(obj[37], ""), Objects.toString(obj[38], ""),
					Objects.toString(obj[39], ""),Objects.toString(obj[40], ""),
					Objects.toString(obj[41], ""),Objects.toString(obj[42], ""),
					Objects.toString(obj[43], ""),Objects.toString(obj[44], ""),
					Objects.toString(obj[45], ""),Objects.toString(obj[46], ""),
					Objects.toString(obj[47], ""),Objects.toString(obj[48], ""),
					Objects.toString(obj[49], ""),Objects.toString(obj[50], ""),
					Objects.toString(obj[51], ""),Objects.toString(obj[52], ""),
					Objects.toString(obj[53], ""),Objects.toString(obj[54], ""),
					Objects.toString(obj[55], ""),Objects.toString(obj[56], ""),
					Objects.toString(obj[57], ""),Objects.toString(obj[58], ""),
					Objects.toString(obj[59], ""),Objects.toString(obj[60], ""),
					Objects.toString(obj[61], ""),Objects.toString(obj[62], ""));
			result.add(data);
		}
		return result;
	}


	@Override
	public String cekCustNo(String groupDivisi) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql =" SELECT NVL (MAX (c.custno), 0) custno "
				       +" FROM "+t.getTenantId()+".fcustmst c "
				       +" LEFT JOIN "+t.getTenantId()+".ftable48 b ON c.custno = b.custno "
				       +" WHERE b.groupdivisi = '"+groupDivisi+"' "
				       +"   AND c.custno >= (SELECT memostring2 FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'X_CUSTNO_"+groupDivisi+"_AWAL') "
				       +"   AND c.custno <= (SELECT memostring2 FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'X_CUSTNO_"+groupDivisi+"_AKHIR') ";
			Query query = entityManager.createNativeQuery(sql);
			result = Objects.toString(query.getSingleResult(), "");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}


	@Override
	public ProsesNooEditDto getDataFtable141(String docNo) {
		// TODO Auto-generated method stub
		ProsesNooEditDto result = new ProsesNooEditDto();
		String sql = " Select DOCNO, TO_CHAR(DOCDATE, 'DD MON YYYY') docDate, SLSNO, CUSTNAME, "
				+ "CUSTADD1, CUSTADD2, SHIPADD1, SHIPADD2, SHIPADD3, CCONTACT, CPHONE1, "
				+ "H11, H12, H13, H14, H15, NOKARTU1, NOBARCODE1, TAXNAME, TAXADD1, "
				+ "TAXADD2, NPWP, LATITUDE, LONGITUDE, STATUS, TO_CHAR(APPROVEDATE, 'DD MON YYYY') approveDate, "
				+ "CUSTNO, DOCID, NAMAFILE, NAMA_KTP, ALAMAT_KTP_1, ALAMAT_KTP_2, NAMA_PEMILIK, "
				+ "SEQ_NO, POSTAL_CODE, TITLE_PERSON, NO_KTP, TAX_CITY, TAX_BUILDING, "
				+ "NOKARTU2, NOKARTU3, TYPEOUT, CLASS, a.GRUPOUT, KDIND, CTERM, CLIMIT, "
				+ "FLAGPAY, HSENIN, HSELASA, HRABU, HKAMIS, HJUMAT, HSABTU, HMINGGU, "
				+ "VISIT1, VISIT2, VISIT3, VISIT4, ROUTE, APPROVE,  "
				+ "    case "
				+ "        when visit1 || visit2 || visit3 || visit4 = 'YYYY' then 'Weekly' "
				+ "        when visit1 || visit2 || visit3 || visit4 = 'YTYT' then 'Be Weekly 1' "
				+ "        when visit1 || visit2 || visit3 || visit4 = 'TYTY' then 'Be Weekly 2' "
				+ "        when visit1 || visit2 || visit3 || visit4 = 'YTTT' then 'Monthly 1' "
				+ "        when visit1 || visit2 || visit3 || visit4 = 'TYTT' then 'Monthly 2' "
				+ "        when visit1 || visit2 || visit3 || visit4 = 'TTYT' then 'Monthly 3' "
				+ "        when visit1 || visit2 || visit3 || visit4 = 'TTTY' then 'Monthly 4' "
				+ "    end as pola, "
				+ " b.GRUPNAME, c.TYPENAME, d.ctypename, e.indname  "
				+ " from "+t.getTenantId()+".ftable141 a "
				+ " LEFT JOIN "+t.getTenantId()+".fgrupout b ON a.GRUPOUT = b.grupout "
				+ " LEFT JOIN "+t.getTenantId()+".ftypeout c ON a.typeout = c.type "
				+ " LEFT JOIN "+t.getTenantId()+".fclassnew d ON a.typeout = d.type "
				+ " LEFT JOIN "+t.getTenantId()+".findustri e ON a.kdind = e.kdind "
				+ " where seq_no = '"+docNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		for (Object[] obj : hasil) {
			result = new ProsesNooEditDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""),
					Objects.toString(obj[3], ""), Objects.toString(obj[4], ""),
					Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""),
					Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					Objects.toString(obj[13], ""), Objects.toString(obj[14], ""),
					Objects.toString(obj[15], ""), Objects.toString(obj[16], ""),
					Objects.toString(obj[17], ""), Objects.toString(obj[18], ""),
					Objects.toString(obj[19], ""), Objects.toString(obj[20], ""),
					Objects.toString(obj[21], ""), Objects.toString(obj[22], ""),
					Objects.toString(obj[23], ""), Objects.toString(obj[24], ""),
					Objects.toString(obj[25], ""), Objects.toString(obj[26], ""),
					Objects.toString(obj[27], ""), Objects.toString(obj[28], ""),
					Objects.toString(obj[29], ""), Objects.toString(obj[30], ""),
					Objects.toString(obj[31], ""), Objects.toString(obj[32], ""),
					Objects.toString(obj[33], ""), Objects.toString(obj[34], ""),
					Objects.toString(obj[35], ""), Objects.toString(obj[36], ""),
					Objects.toString(obj[37], ""), Objects.toString(obj[38], ""),
					Objects.toString(obj[39], ""),Objects.toString(obj[40], ""),
					Objects.toString(obj[41], ""),Objects.toString(obj[42], ""),
					Objects.toString(obj[43], ""),Objects.toString(obj[44], ""),
					Objects.toString(obj[45], ""),Objects.toString(obj[46], ""),
					Objects.toString(obj[47], ""),Objects.toString(obj[48], ""),
					Objects.toString(obj[49], ""),Objects.toString(obj[50], ""),
					Objects.toString(obj[51], ""),Objects.toString(obj[52], ""),
					Objects.toString(obj[53], ""),Objects.toString(obj[54], ""),
					Objects.toString(obj[55], ""),Objects.toString(obj[56], ""),
					Objects.toString(obj[57], ""),Objects.toString(obj[58], ""),
					Objects.toString(obj[59], ""),Objects.toString(obj[60], ""),
					Objects.toString(obj[61], ""), Objects.toString(obj[62], ""),
					Objects.toString(obj[63], ""), Objects.toString(obj[64], ""),
					Objects.toString(obj[65], ""));
		}
		return result;
	}
	
}
