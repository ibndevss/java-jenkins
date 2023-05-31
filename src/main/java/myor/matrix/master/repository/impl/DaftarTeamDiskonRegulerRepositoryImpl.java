package myor.matrix.master.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.repository.DaftarTeamDiskonRegulerRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class DaftarTeamDiskonRegulerRepositoryImpl implements DaftarTeamDiskonRegulerRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Object[] getHeader(String inputDateEfektif, String inputTeam) {
		// TODO Auto-generated method stub
		String sql = "  SELECT "
				+ "	'DAFTAR TEAM & DISKON REGULER' || ' - ' || (SELECT memostring FROM "+t.getTenantId()+".fmemo WHERE memonama = 'KODEDIST') || ' ' ||  "
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
				+ "            '"+inputTeam+"' AS inputTeam, "
				+ "            '"+inputDateEfektif+"' AS inputDateEfektif "
				+ "            FROM DUAL ";
		Query query = entityManager.createNativeQuery(sql);
		Object[] result = (Object[]) query.getSingleResult();
		return result;
	}

	@Override
	public List<Object[]> getData(String teamFrom,String teamTo,String dateEfektifFrom,String dateEfektifTo) {
		// TODO Auto-generated method stub
		String sql = " SELECT TO_CHAR (t.tgl, 'DD-MON-YYYY') tgl, t.team, t.sbra2, t.kode, m.sbra2name, a.teamname  FROM "+t.getTenantId()+".ftabel141 t, "
				+ ""+t.getTenantId()+".ftsbrand2 m, "+t.getTenantId()+".fteam a WHERE t.sbra2 = m.sbra2(+) AND t.team = a.team(+) " ;
		   if (dateEfektifFrom.length() != 0 ) {
		       sql += " AND (t.Tgl >= '"+dateEfektifFrom+"') ";
		   	}
		   if (dateEfektifTo.length() != 0 ) {
		       sql += " AND (t.Tgl <= '"+dateEfektifTo+"') ";
		   	}
		   if (teamFrom.length() != 0 ) {
		       sql += " AND (t.team >= '"+teamFrom+"') ";
		   	}
		   if (teamTo.length() != 0 ) {
		       sql += " AND (t.team <= '"+teamTo+"') ";
		   	}
		   sql += "  order by t.tgl,t.team,t.sbra2 asc  ";
		
		Query query = entityManager.createNativeQuery(sql);
 		List<Object[]> resultList = query.getResultList();
		return resultList;
	}
	
}
