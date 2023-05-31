package myor.matrix.master.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.repository.DaftarHargaGeneralRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class DaftarHargaGeneralRepositoryImpl implements DaftarHargaGeneralRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Object[] getHeader(String inputDateBerlaku, String inputPcode) {
		// TODO Auto-generated method stub
		String sql = "  SELECT "
				+ "	'DAFTAR HARGA GENERAL BARU' || ' - ' || (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') || ' ' ||  "
				+ "   CASE WHEN (SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') IS NULL "
				+ "   THEN (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'COMPANYNAME') "
				+ "   ELSE (SELECT memostring FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XBRANCH') END header , "
				+ "           TO_CHAR (SYSDATE, 'DD/MM/YYYY') AS tglcetak, "
				+ "         (Select "
				+ "            TO_CHAR(MEMODATE, "
				+ "            'DD MON YYYY') datememo  "
				+ "        from "
				+ "            "+t.getTenantId()+".FMEMO  "
				+ "        where "
				+ "            MEMONAMA='CADATE') AS tglgudang, "
				+ "        (select "
				+ "            TO_CHAR(XNO)  "
				+ "        from "
				+ "            "+t.getTenantId()+".ftable13  "
				+ "        where "
				+ "            xkey = 'ARWANA_UPDATE') AS RELEASE, "
				+ "            '"+inputPcode+"' AS inputPcode, "
				+ "            '"+inputDateBerlaku+"' AS inputDateBerlaku "
				+ "            FROM DUAL ";
		Query query = entityManager.createNativeQuery(sql);
		Object[] result = (Object[]) query.getSingleResult();
		return result;
	}

	@Override
	public List<Object[]> getData(String pcodeFrom, String pcodeTo, String dateBerlakuFrom, String dateBerlakuTo) {
		// TODO Auto-generated method stub
		String sql = " SELECT   a.pcode, b.pcodename, a.tipe kodetipe, "
                +"  DECODE (a.tipe, 'B', 'Beli', 'J', 'Jual', NULL) tipe, a.tprice3,  "
                +"  a.tprice2, a.tprice1, TO_CHAR (a.tglganti, 'DD-MM-YYYY') tglganti "
                +"  FROM "+t.getTenantId()+".ftharga a, "+t.getTenantId()+".fmaster b   "
                +" WHERE a.pcode = b.pcode(+) " ;
		   if (dateBerlakuFrom.length() != 0 ) {
		       sql += " AND (a.TGLGANTI >= '"+dateBerlakuFrom+"') ";
		   	}
		   if (dateBerlakuTo.length() != 0 ) {
		       sql += " AND (a.TGLGANTI <= '"+dateBerlakuTo+"') ";
		   	}
		   if (pcodeFrom.length() != 0 ) {
		       sql += " AND (a.PCODE >= '"+pcodeFrom+"') ";
		   	}
		   if (pcodeTo.length() != 0 ) {
		       sql += " AND (a.PCODE <= '"+pcodeTo+"') ";
		   	}
		   sql += "  ORDER BY a.pcode, kodetipe asc  ";
		
		Query query = entityManager.createNativeQuery(sql);
 		List<Object[]> resultList = query.getResultList();
		return resultList;
	}

}
