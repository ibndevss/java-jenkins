package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.UploadOutletAktifNonAktifCoverDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifDetailDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifHeaderDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifPrintDto;
import myor.matrix.master.repository.UploadOutletAktifNonAktifRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class UploadOutletAktifNonAktifRepositoryImpl implements UploadOutletAktifNonAktifRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<UploadOutletAktifNonAktifHeaderDto> getBrowseDocument() {
		// TODO Auto-generated method stub
		List<UploadOutletAktifNonAktifHeaderDto> result = new ArrayList<>();
		
		String sql =" select distinct DOCNO, TO_CHAR(DOCDATE, 'DD MON YYYY') DOCDATE, TIPE, USER_CREATED, XKEY, KEYAUTHENTICATION, USERAUTHENTICATION, TO_CHAR(DATEPROSES, 'DD MON YYYY') DATEPROSES "
				+ "  from "+t.getTenantId()+".FTABLE51_H where "
				+ "USERAUTHENTICATION is null ORDER BY DOCNO  ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			UploadOutletAktifNonAktifHeaderDto data = new UploadOutletAktifNonAktifHeaderDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""));
			result.add(data);
		}
		return result;
	}

	@Override
	public UploadOutletAktifNonAktifHeaderDto getHeader(String docNo) {
		// TODO Auto-generated method stub
		UploadOutletAktifNonAktifHeaderDto result = new UploadOutletAktifNonAktifHeaderDto();
		String sql = " select "
				+ "    DOCNO, TO_CHAR(DOCDATE, 'DD MON YYYY') DOCDATE, TIPE, USER_CREATED, XKEY, KEYAUTHENTICATION, USERAUTHENTICATION, TO_CHAR(DATEPROSES, 'DD MON YYYY') DATEPROSES "
				+ "from "
				+ "    "+t.getTenantId()+".FTABLE51_h "
				+ "where "
				+ "    docno = '"+docNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] o : resultList) {
			result = new UploadOutletAktifNonAktifHeaderDto(Objects.toString(o[0], ""), Objects.toString(o[1], ""), 
					Objects.toString(o[2], ""), Objects.toString(o[3], ""), Objects.toString(o[4], ""), 
					Objects.toString(o[5], ""), Objects.toString(o[6], ""), Objects.toString(o[7], ""));
		}
		return result;
	}

	@Override
	public List<UploadOutletAktifNonAktifDetailDto> getDetail(String docNo) {
		// TODO Auto-generated method stub
		List<UploadOutletAktifNonAktifDetailDto> result = new ArrayList<>();
		
		String sql =" select "
				+ "    DOCNO, CUSTNO, CUSTNAME, CUSTADDR, CHANNEL, ALASAN, b.typename "
				+ "from "
				+ "    "+t.getTenantId()+".FTABLE51_D a "
				+ " left join "+t.getTenantId()+".FTYPEOUT b on a.channel = b.type "
				+ "where "
				+ "    DOCNO = '"+docNo+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			UploadOutletAktifNonAktifDetailDto data = new UploadOutletAktifNonAktifDetailDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""));
			result.add(data);
		}
		return result;
	}

	@Override
	public List<UploadOutletAktifNonAktifCoverDto> getCover(String docNo) {
		// TODO Auto-generated method stub
		List<UploadOutletAktifNonAktifCoverDto> result = new ArrayList<>();
		
		String sql =" select "
				+ "    a.DOCNO, a.CUSTNO, a.SLSNO, b.slsname as slsname, a.NOBRS, "
				+ " a.HSENIN, a.HSELASA, a.HRABU, a.HKAMIS, a.HJUMAT, a.HSABTU, a.HMINGGU, "
				+ " a.VISIT1, a.VISIT2, a.VISIT3, a.VISIT4, a.ROUTE, "
				+"    case when a.visit1||a.visit2||a.visit3||a.visit4='YYYY' then 'Weekly'  "
			      +"   when a.visit1||a.visit2||a.visit3||a.visit4='YTYT' then 'Be Weekly 1'  "
			      +"   when a.visit1||a.visit2||a.visit3||a.visit4='TYTY' then 'Be Weekly 2' "
			      +"   when a.visit1||a.visit2||a.visit3||a.visit4='YTTT' then 'Monthly 1' "
			      +"   when a.visit1||a.visit2||a.visit3||a.visit4='TYTT' then 'Monthly 2' "
			      +"   when a.visit1||a.visit2||a.visit3||a.visit4='TTYT' then 'Monthly 3' "
			      +"   when a.visit1||a.visit2||a.visit3||a.visit4='TTTY' then 'Monthly 4' end as pola, "
			      + " c.custname "
				+ "from "
				+ "    "+t.getTenantId()+".FTABLE51_R a "
				+ "    left join "+t.getTenantId()+".FSLS b on a.slsno = b.slsno "
				+ "    LEFT JOIN "+t.getTenantId()+".FCUSTMST c ON a.CUSTNO = c.CUSTNO  "
				+ "where "
				+ "    a.DOCNO = '"+docNo+"'  ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			UploadOutletAktifNonAktifCoverDto data = new UploadOutletAktifNonAktifCoverDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""), Objects.toString(obj[9], ""), 
					Objects.toString(obj[10], ""), Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					Objects.toString(obj[13], ""), Objects.toString(obj[14], ""), Objects.toString(obj[15], ""),
					Objects.toString(obj[16], ""), Objects.toString(obj[17], ""), Objects.toString(obj[18], ""));
			result.add(data);
		}
		return result;
	}

	@Override
	public List<Object[]> valOutletOutstanding(String custNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = "SELECT * FROM ( SELECT   h.custno, SUM (invamountinp) totfaktur, SUM (NVL (amountpaid, 0)) totbayar "
                +" FROM "+t.getTenantId()+".fjual_h h WHERE h.invno IS NOT NULL AND h.invamountinp IS NOT NULL AND h.invamountinp <> 0 "
                +" AND h.transtype = 'F' AND h.paidflag IS NULL GROUP BY h.custno) "
                +" WHERE TOTBAYAR < TOTFAKTUR and custno = '"+custNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<Object[]> valOutletTransaksi(String custNo, String docDate, String periode, String tahun) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select * from "+t.getTenantId()+".fjual_h where custno = '"+custNo+"' "
        +" and invdate <= '"+docDate+"' "
        +" and invdate >= (select min(startweek) from "+t.getTenantId()+".fcycle2 where prdno = "+periode+" "
        +" and tahun = "+tahun+") ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<UploadOutletAktifNonAktifPrintDto> dataCetak(String docNo) {
		// TODO Auto-generated method stub
		List<UploadOutletAktifNonAktifPrintDto> result = new ArrayList<>();
		
		String sql =" SELECT "
				+ "    a.DOCNO, "
				+ "    TO_CHAR(c.DOCDATE, 'DD/MM/YYYY') docdate, "
				+ "    c.xkey, "
				+ "    c.TIPE, "
				+ "    CASE "
				+ "        WHEN c.TIPE = 'A' THEN 'ACTIVE' "
				+ "        ELSE 'NON ACTIVE' "
				+ "    END AS STATUS, "
				+ "    CUSTNO, "
				+ "    CUSTNAME, "
				+ "    CUSTADDR, "
				+ "    CHANNEL | | ' - ' | | b.TYPENAME CHANNEL, "
				+ "    CASE "
				+ "        WHEN c.TIPE = 'N' THEN a.ALASAN "
				+ "        ELSE '' "
				+ "    END AS ALASAN, "
				+ "    'KONFIRMASI AKTIVASI OUTLET' | | ' - ' | | ( "
				+ "        SELECT "
				+ "            memostring "
				+ "        FROM "
				+ "            "+t.getTenantId()+".fmemo "
				+ "        WHERE "
				+ "            memonama = 'KODEDIST' "
				+ "    ) | | ' ' | | CASE "
				+ "        WHEN ( "
				+ "            SELECT "
				+ "                memonama "
				+ "            FROM "
				+ "                "+t.getTenantId()+".ftable13 "
				+ "            WHERE "
				+ "                xkey = 'XBRANCH' "
				+ "        ) IS NULL THEN ( "
				+ "            SELECT "
				+ "                memostring "
				+ "            FROM "
				+ "                "+t.getTenantId()+".fmemo "
				+ "            WHERE "
				+ "                memonama = 'COMPANYNAME' "
				+ "        ) "
				+ "        ELSE ( "
				+ "            SELECT "
				+ "                memostring "
				+ "            FROM "
				+ "                "+t.getTenantId()+".ftable13 "
				+ "            WHERE "
				+ "                xkey = 'XBRANCH' "
				+ "        ) "
				+ "    END header, "
				+ "    TO_CHAR (SYSDATE, 'DD MON YYYY') AS tglcetak, "
				+ "    ( "
				+ "        Select "
				+ "            TO_CHAR(MEMODATE, 'DD MON YYYY') datememo "
				+ "        from "
				+ "            "+t.getTenantId()+".FMEMO "
				+ "        where "
				+ "            MEMONAMA = 'CADATE' "
				+ "    ) AS tglgudang, "
				+ "    ( "
				+ "        select "
				+ "            TO_CHAR(XNO) "
				+ "        from "
				+ "            "+t.getTenantId()+".ftable13 "
				+ "        where "
				+ "            xkey = 'ARWANA_UPDATE' "
				+ "    ) AS RELEASE "
				+ "FROM "
				+ "    "+t.getTenantId()+".FTABLE51_D a "
				+ "    LEFT JOIN "+t.getTenantId()+".FTYPEOUT b ON a.CHANNEL = b.TYPE "
				+ "    LEFT JOIN "+t.getTenantId()+".FTABLE51_H c ON a.DOCNO = c.DOCNO "
				+ "WHERE "
				+ "    DOCNO = '"+docNo+"'   ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			UploadOutletAktifNonAktifPrintDto data = new UploadOutletAktifNonAktifPrintDto(Objects.toString(obj[0], ""),
					Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
					Objects.toString(obj[7], ""), Objects.toString(obj[8], ""), Objects.toString(obj[9], ""), 
					Objects.toString(obj[10], ""), Objects.toString(obj[11], ""), Objects.toString(obj[12], ""), Objects.toString(obj[13], ""));
			result.add(data);
		}
		return result;
	}

}
