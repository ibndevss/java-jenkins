package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ConvUnitPcodeDto;
import myor.matrix.master.entity.DetailDate;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.UtilRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class UtilRepositoryImpl implements UtilRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String getDateNowBySystem(){
		String sql = "select to_char(sysdate, 'DD MON YYYY') tgl from dual";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
	
	@Override
	public String getDateNowByWarehouse(){
		String sql = "Select to_char(MEMODATE,'DD MON YYYY') AS warehouse_date from "+t.getTenantId()+".FMEMO where MEMONAMA='CADATE'";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
	
	@Override
	public String getDateNowByWarehouseFormatYYYMMDD(){
		String sql = "Select to_char(MEMODATE,'yyyy-mm-dd') AS warehouse_date from "+t.getTenantId()+".FMEMO where MEMONAMA='CADATE'";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}


	@Override
	public String cekParam(String xkey) {
		// TODO Auto-generated method stub
		String sql = "select memonama " + 
					 "from "+t.getTenantId()+".ftable13 " + 
					"where xkey='"+xkey+"' ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
		
	}

	@Override
	public List<ConvUnitPcodeDto> getConvUnit(String pcode) {
		// TODO Auto-generated method stub
		List<ConvUnitPcodeDto> result = new ArrayList<>();
		String sql = "SELECT convunit3, convunit2 " + 
				"FROM "+t.getTenantId()+".FMASTER " + 
				"WHERE pcode = '"+pcode+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			ConvUnitPcodeDto data = new ConvUnitPcodeDto(
					((BigDecimal) obj[0]).doubleValue(),
					((BigDecimal) obj[1]).doubleValue()
					);
			result.add(data);
		}
		return result;
	}

	@Override
	public int cekTableFrgAct(String noSeq) {
		// TODO Auto-generated method stub
		String sql = "SELECT NO_KEY " + 
					" FROM "+t.getTenantId()+".FPRGACT " +
					" WHERE NAMATABEL = '"+noSeq+"' ";
		
		Query query = entityManager.createNativeQuery(sql);	
		List<Object> hasil = query.getResultList();
		int result = 0;
		if (hasil.size() > 0) {
			result = 1;
		}
		return result;
		
	}

	@Override
	public void insertTableFrgAct(String noSeq, String noSeq2, String userId) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".FPRGACT " + 
					 " VALUES ('"+noSeq+"','"+noSeq2+"','"+userId+"') ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public int cekMemoInt(String xkey) {
		// TODO Auto-generated method stub
		String sql = " SELECT (MEMOINT+1)MEMOINT " + 
				" FROM "+t.getTenantId()+".FMEMO " + 
				" WHERE MEMONAMA = '"+xkey+"' ";
		Query query = entityManager.createNativeQuery(sql);
		try {
			return Integer.parseInt(query.getSingleResult().toString());	
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int cekFieldByTable(String getfield, String tableName, int cekMemoInt) {
		// TODO Auto-generated method stub
		String sql = "SELECT "+getfield+" "
				   +" FROM "+t.getTenantId()+"."+tableName+" "
				   +" WHERE "+getfield+" = "+cekMemoInt+" ";
		Query query = entityManager.createNativeQuery(sql);
		try {
			return Integer.parseInt(query.getSingleResult().toString());	
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public void updateFmemo(String xkey) {
		// TODO Auto-generated method stub
		String sql = " UPDATE "+t.getTenantId()+".FMEMO "
				+ " set MEMOINT = (MEMOINT+1) WHERE MEMONAMA='"+xkey+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void delete(String noSeq) {
		// TODO Auto-generated method stub
		String sql = " DELETE "+t.getTenantId()+".FPRGACT WHERE NAMATABEL='"+noSeq+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public String getNoDoc(String xkey) {
		// TODO Auto-generated method stub
		String sql = "Select MEMOINT From "+t.getTenantId()+".FMEMO where MEMONAMA='"+xkey+"' ";
		Query query = entityManager.createNativeQuery(sql);
		return String.valueOf(query.getSingleResult().toString());
	}

	@Override
	public int cekPenggunaan(String tableName, String noSeq) {
		// TODO Auto-generated method stub
		String sql = "SELECT NAMAUSER "
				   +" FROM "+t.getTenantId()+".FPRGACT "
				   +" WHERE NAMATABEL = '"+tableName+"' AND NO_KEY = '"+noSeq+"' ";
		Query query = entityManager.createNativeQuery(sql);
		try {
			return Integer.parseInt(query.getSingleResult().toString());	
		}catch(Exception e) {
			return 0;
		}
	}
	
	@Override
	public List<String> cekPenggunaanDetail(String tableName, String noSeq, String user) {
		// TODO Auto-generated method stub
		String sql = "SELECT NAMAUSER "
				   +" FROM "+t.getTenantId()+".FPRGACT "
				   +" WHERE NAMATABEL = '"+tableName+"' AND NO_KEY = '"+noSeq+"'"
				   +" AND NAMAUSER <> '"+user+"' ";
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}

	@Override
	public void insertPenggunaan(String tableName, String noSeq, String userId) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+".FPRGACT (NAMATABEL,NO_KEY,NAMAUSER) "
					+" VALUES ('"+tableName+"','"+noSeq+"','"+userId+"') ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deletePenggunaan(String tableName, String noSeq, String userId) {
		// TODO Auto-generated method stub
		String sql = "DELETE "+t.getTenantId()+".FPRGACT WHERE NAMATABEL = '"+tableName+"'"
				+ "AND NO_KEY = '"+noSeq+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<SelectItem<String>> getListFtable13ByXkey(String xkey) {
		// TODO Auto-generated method stub
		List<SelectItem<String>> result = new ArrayList<SelectItem<String>>();
		
		String sql = "SELECT remark, memoint FROM "+t.getTenantId()+".ftable13 "
				+" 	WHERE xkey = '"+xkey+"' "
				+"	ORDER BY xkey, memoint ";
			
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			SelectItem<String> data = new SelectItem<String>((String) obj[0], ((BigDecimal) obj[1]).toString());
			result.add(data);
		}
		
		return result;
	}
	
	@Override
	public String getXqty(String pcode, String qty) {
		// TODO Auto-generated method stub
		String sql = "SELECT "
					+"		CASE "
					+"         WHEN length(floor(qty/convunit3))=1 THEN '0000'||to_char(floor(qty/convunit3)) " 
					+"          WHEN length(floor(qty/convunit3))=2 THEN '000'||to_char(floor(qty/convunit3)) " 
					+"          WHEN length(floor(qty/convunit3))=3 THEN '00'||to_char(floor(qty/convunit3)) " 
					+"          WHEN length(floor(qty/convunit3))=4 THEN '0'||to_char(floor(qty/convunit3))  "
					+"          WHEN length(floor(qty/convunit3))=5 THEN to_char(floor(qty/convunit3))  "
					+"        END||'.'||  "
					+"        CASE " 
					+"          WHEN length(floor(mod(qty,convunit3)/convunit2))=1 THEN '00'||to_char(floor(mod(qty,convunit3)/convunit2)) " 
					+"          WHEN length(floor(mod(qty,convunit3)/convunit2))=2 THEN '0'||to_char(floor(mod(qty,convunit3)/convunit2))  "
					+"          WHEN length(floor(mod(qty,convunit3)/convunit2))=3 THEN to_char(floor(mod(qty,convunit3)/convunit2))  "
					+"        END||'.'||  "
					+"        CASE  "
					+"          WHEN length(mod(mod(qty,convunit3),convunit2))=1 THEN '00'||to_char(mod(mod(qty,convunit3),convunit2)) " 
					+"          WHEN length(mod(mod(qty,convunit3),convunit2))=2 THEN '0'||to_char(mod(mod(qty,convunit3),convunit2))  "
					+"          WHEN length(mod(mod(qty,convunit3),convunit2))=3 THEN to_char(mod(mod(qty,convunit3),convunit2))  "
					+"        END AS xqty "
					+" FROM ( "
					+"		SELECT pcode, pcodename, CONVUNIT2 , CONVUNIT3 , '"+qty+"' AS qty "
					+"		FROM "+t.getTenantId()+".fmaster WHERE pcode  = '"+pcode+"' "
					+"	) a ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}

	@Override
	public DetailDate getDateDetail(String date) {
		// TODO Auto-generated method stub
		
		DetailDate result = new DetailDate();
		String sql = "SELECT TO_CHAR(CDATE, 'DD MON YYYY') dates, TO_CHAR(TAHUN) years, TO_CHAR(PRDNO) prdsno,"
				+ "TO_CHAR(WEEKNO) weeksno, TO_CHAR(WORKFLAG) workflag FROM "+t.getTenantId()+".FCYCLE3 F WHERE CDATE = '"+date+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			result = new DetailDate((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4]);
		}
		
		return result;
	}

	@Override
	public boolean cekPenggunaanOtherUser(String tableName, String noSeq, String userName) {
		// TODO Auto-generated method stub
		boolean result = false;		
		
		String sql = " SELECT namauser FROM "+t.getTenantId()+".fprgact "
					+"  WHERE namatabel = '"+tableName+"' "
					+"    AND no_key = '"+noSeq+"' "
					+"    AND namauser <> '"+userName+"' ";
		Query query = entityManager.createNativeQuery(sql);	
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			result = true;
		}
		
		return result;
	}

	@Override
	public String getMemostringFtable13(String xkey) {
		// TODO Auto-generated method stub
		String sql = "select memostring " + 
				 "from "+t.getTenantId()+".ftable13 " + 
				"where xkey='"+xkey+"' ";
		Query query = entityManager.createNativeQuery(sql);	
		List<Object> resultList = query.getResultList();
		
		String result = "";
		for (Object obj : resultList) {
			result = Objects.toString(obj, "");
		}
		
		return result;
	}

	@Override
	public String getTimeNow() {
		// TODO Auto-generated method stub
		String sql = "SELECT TO_CHAR(sysdate, 'HH24:MI:SS') time FROM dual";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return Objects.toString(obj, "");
	}
	
	@Override
	public boolean isDataExist(String fieldName, String tableName, long sequence) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		String sql = " SELECT "+fieldName+" FROM "+t.getTenantId()+"."+tableName+" WHERE "+fieldName+" = '"+sequence+"' ";
		Query query = entityManager.createNativeQuery(sql);	
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			result = true;
		}
		
		return result;
	}

	@Override
	public long getNextVal(String sequenceName) {
		// TODO Auto-generated method stub
		int seq = 0;
		
		String sql = " SELECT "+t.getTenantId()+"."+sequenceName+".NEXTVAL FROM DUAL ";		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			seq = Integer.parseInt(Objects.toString(hasil.get(0), "0"));
		} 
		
		return seq;
	}

	@Override
	public long getCurrtVal(String sequenceName) {
		// TODO Auto-generated method stub
		int seq = 0;
		
		String sql = " SELECT last_number - increment_by SEQUENCE FROM all_sequences "
					+"  WHERE UPPER (sequence_owner) = UPPER ('"+t.getTenantId()+"') "
					+"    AND UPPER (sequence_name) = UPPER ('"+sequenceName+"') ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object> hasil = query.getResultList();
		
		if (hasil.size() > 0) {
			seq = ((BigDecimal) hasil.get(0)).intValue();
		} 
		
		return seq;
	}

	@Override
	public boolean cekNpwpOutlet(String custNo) {
		// TODO Auto-generated method stub
		String sql = "select * from "+t.getTenantId()+".fcustmst "
				+ " where ((REGEXP_LIKE(npwp, '^[0-9.-]+$') "
				+ "		and length(npwp)=20 and npwp <> '00.000.000.0-000.000' "
				+ "		and npwp <> '00.00.00.00.00.00.-0') "
				+ "  	or (REGEXP_LIKE(npwp, '^[[:digit:]]+$') "
				+ "			and length(npwp)=15 and npwp <>'000000000000000')"
				+ "		) "
				+ "		and custno='"+custNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		boolean result = false;
		if(resultList.size() > 0) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}

	@Override
	public boolean cekKtpOutlet(String custNo) {
		// TODO Auto-generated method stub
		String sql = "select * from "+t.getTenantId()+".ftable48 "
				+ " where ( flag_ktp is null or flag_ktp <> '1' or noktp is null "
				+ " 		or noktp ='' or noktp='0' or noktp='0000000000000000' "
				+ "			or length(noktp)<16"
				+ "		) "
				+ "		and custno='"+custNo+"'";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		boolean result = false;
		if(resultList.size() > 0) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}

	@Override
	public String getMemonamaFtable13(String xkey) {
		// TODO Auto-generated method stub
		String sql = "select memonama " + 
				 "from "+t.getTenantId()+".ftable13 " + 
				"where xkey='"+xkey+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object> resultList = query.getResultList();
		
		String result = "";
		for (Object obj : resultList) {
			result = Objects.toString(obj, ""); 
		}
		return result;
	}

	@Override
	public Double roundSql(Double value, String decPoint) {
		// TODO Auto-generated method stub
		String sql = "select round("+value+", "+decPoint+") as val, 0 temp_val from dual";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		Double result = 0.0;
		for (Object[] obj : resultList) {
			result = Double.parseDouble(Objects.toString(obj[0], "0")); 
		}
		return result;
	}
	
	@Override
	public String getTableString(String table, String column, String where) {
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
	public String getTableStringJoin(String table, String column, String join, String where) {
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
	public String getDualString( String table, String column, String where) {
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
	public List<Object[]> getTableListObject(String table, String column, String where) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public List<Object[]> getTableListObjectJoin(String table, String column, String join, String where) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = " select "+column+" from "+t.getTenantId()+"."+table+" "+join+" "+where+" ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public List<Object[]> getTableListObjectQuery(String query1) {
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

}
