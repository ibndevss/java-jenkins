package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.CreateKplListOutletDto;
import myor.matrix.master.repository.CreateKplRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class CreateKplRepositoryImpl implements CreateKplRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String getXkeyString(String column, String table, String where) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+where+"  ";
			Query query = entityManager.createNativeQuery(sql);
			result = (String) query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
	}
	
	@Override
	public String getFromDualString(String column, String table, String where) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " select "+column+" from "+table+" "+where+"  ";
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
	public List<Object[]> getFCycle(String edtTglGudang) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql =" SELECT * FROM( "
		        +" SELECT rownum as nourut,to_char(weekno),TO_CHAR(cdate, 'DD MON YYYY') cdate,workflag  FROM ( "
		        +" select weekno,cdate,workflag from "+t.getTenantId()+".fcycle3 "
		        + " where cdate >=  '"+edtTglGudang+"'  "
		        + " and workflag = 'Y'  ORDER BY CDATE ASC "
		        +" ) WHERE rownum IN(1,2) ) A WHERE  NOURUT =2 ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<CreateKplListOutletDto> getDaftarOutlet(String edtSlsNo, String edtHari) {
		// TODO Auto-generated method stub
		System.out.println(edtHari + " " + edtHari.length());
		List<CreateKplListOutletDto> result = new ArrayList<>();
		String sql =" select * from (SELECT c.custno, c.custname,c.custadd1 as alamat, s.slsno, s.hsenin, s.hselasa, s.hrabu, s.hkamis, "
		      +" s.hjumat, s.hsabtu, s.hminggu, (s.visit1 || s.visit2 || s.visit3 || s.visit4) AS pola_k "
		      +" FROM "+t.getTenantId()+".fcustmst c INNER JOIN "+t.getTenantId()+".fcustsls s ON c.custno = s.custno "
		      +" WHERE s.slsno ='"+edtSlsNo+"' ";
			  if (edtHari.equalsIgnoreCase("Mon") || edtHari.equalsIgnoreCase("Senin") || edtHari.equalsIgnoreCase("Sen") || edtHari.equalsIgnoreCase("Monday")) {
				  sql += " AND s.hsenin = 'Y' ";
			  } 
			  if (edtHari.equalsIgnoreCase("Tue") || edtHari.equalsIgnoreCase("Selasa") || edtHari.equalsIgnoreCase("Sel") || edtHari.equalsIgnoreCase("Tuesday")) {
				  sql += " AND s.hselasa = 'Y' ";
			  }  
			  if (edtHari.equalsIgnoreCase("Wed") || edtHari.equalsIgnoreCase("Rabu") || edtHari.equalsIgnoreCase("Rab") || edtHari.equalsIgnoreCase("Wednesday")) {
				  sql += " AND s.hrabu = 'Y' ";
			  }  
			  if (edtHari.equalsIgnoreCase("Thu") || edtHari.equalsIgnoreCase("Kamis") || edtHari.equalsIgnoreCase("Kam") || edtHari.equalsIgnoreCase("Thursday")) {
				  sql += " AND s.hkamis = 'Y' ";
			  } 
			  if (edtHari.equalsIgnoreCase("Fri") || edtHari.equalsIgnoreCase("Jumat") || edtHari.equalsIgnoreCase("Jum") || edtHari.equalsIgnoreCase("Friday")) {
				  sql += " AND s.hjumat = 'Y' ";
			  } 
			  if (edtHari.equalsIgnoreCase("Sat") || edtHari.equalsIgnoreCase("Sabtu") || edtHari.equalsIgnoreCase("Sab") || edtHari.equalsIgnoreCase("Saturday")) {
				  sql += " AND s.hsabtu = 'Y' ";
			  } 
			  if (edtHari.equalsIgnoreCase("Sun") || edtHari.equalsIgnoreCase("Minggu") || edtHari.equalsIgnoreCase("Ming") || edtHari.equalsIgnoreCase("Sunday")) {
				  sql += " AND s.hminggu = 'Y' ";
			  }
		          sql += " )a order by a.custno";
				  
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			CreateKplListOutletDto data = new CreateKplListOutletDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""),
			Objects.toString(obj[2], ""), Objects.toString(obj[3], ""), Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""),
			Objects.toString(obj[7], ""), Objects.toString(obj[8], ""), Objects.toString(obj[9], ""), Objects.toString(obj[10], ""), Objects.toString(obj[11], ""), null);
			result.add(data);
		}
		hasil.clear();
		
		return result;
	}

	@Override
	public String getDay(String DateTglGudang) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			String sql = " SELECT REGEXP_REPLACE(hari, ' ') day FROM ( SELECT to_char(cdate, 'Day') as hari from "+t.getTenantId()+".FCYCLE3 WHERE CDATE = '"+DateTglGudang+"')  ";
			Query query = entityManager.createNativeQuery(sql);
			result = (String) query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
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
	public void insertData(String table, String column, String value, String where) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+"."+table+" ("+column+") values ("+value+") "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> cekPengcoveran(String edtTglGudangplus1, String edtSlsNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select A.DATA1,A.DATA2,B.DATA32 from "+t.getTenantId()+".FTABEL62 A "
				+ " INNER JOIN "+t.getTenantId()+".FTABEL63 B ON A.DATA1 =  B.DATA1 "
				+ " WHERE A.DATA2  ='"+edtTglGudangplus1+"' and b.data32 ='"+edtSlsNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<Object[]> cekPengcoveranbyMonthly(String edtSlsNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = "select m.* from "
                +" (select a.*, b.weekno, c.urut from "+t.getTenantId()+".ftabel46 a  "
                +" left join "+t.getTenantId()+".fcycle3 b on a.DATA1=b.CDATE          "
                +" left join  "
                +" (select row_number() over (partition by tahun,prdno order by tahun, prdno, weekno) as urut, tahun, weekno, prdno from "+t.getTenantId()+".fcycle2 order by tahun, prdno, weekno) c "
                +" on b.PRDNO=c.prdno and b.tahun=c.tahun and b.WEEKNO=c.weekno) m  "
                +" where urut=5 and data2='"+edtSlsNo+"' "
                +" and data3 in ((select custno from "
                +"  (select custno, slsno, visit1||visit2||visit3||visit4 as pola from "+t.getTenantId()+".fcustsls) a   "
                +" where slsno='"+edtSlsNo+"' and pola='YTTT')) ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public void deleteFtabel46(String edtTglGudangplus1, String edtSlsNo) {
		// TODO Auto-generated method stub
		String sql = "delete from "+t.getTenantId()+".ftabel46 where data1='"+edtTglGudangplus1+"' and data2='"+edtSlsNo+"'  "
                +" and data3 in ((select custno from "
                +" (select custno, slsno, visit1||visit2||visit3||visit4 as pola from "+t.getTenantId()+".fcustsls) a   "
                +" where slsno='"+edtSlsNo+"' and pola='YTTT')) ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

}
