package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.CekAllDto;
import myor.matrix.master.entity.CekBankChoosen;
import myor.matrix.master.entity.CekChoosen;
import myor.matrix.master.entity.CekCustChoosen;
import myor.matrix.master.entity.CekCustDto;
import myor.matrix.master.entity.CekDto;
import myor.matrix.master.entity.CekNoCheck;
import myor.matrix.master.entity.EmployeeDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.CekRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class CekRepositoryImpl implements CekRepository {
	
	@Autowired
	private TenantContext tc;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CekDto> getDataCek() {
		// TODO Auto-generated method stub
		List<CekDto> result = new ArrayList<>();
		
		String sql = "SELECT UPPER(CHECKNO) CHECKNO,TO_CHAR(CHECKDATE,'DD MON YYYY')CHECKDATE ,"
				+ "TO_CHAR(CHECKDUEDT,'DD MON YYYY')CHECKDUEDATE ,  BANK , " 
				+ "ACCNO ACCOUNT,FLAG TIPE,CUSTNO,CHECKAMOUNT,PAYCHECK AMOUNTUSED "
				+ "FROM "+tc.getTenantId()+".FPCHEQ_H order by checkno ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for (Object[] o : obj) {
			CekDto a = new CekDto((String) o[0],(String) o[1],(String) o[2], (String) o[3], (String) o[4], (String) o[5], (String) o[6], (BigDecimal) o[7], (BigDecimal) o[8]);
			result.add(a);
		}
		
		return result;
	}
	
	
	@Override
	public CekChoosen getCekChoosen(String checkNo) {
		// TODO Auto-generated method stub
		CekChoosen result = new CekChoosen();
		String sql = " SELECT A.CHECKNO ,TO_CHAR(A.CHECKDATE,'DD MON YYYY')CHECKDATE ,TO_CHAR(A.CHECKDUEDT,'DD MON YYYY')CHECKDUEDT , C.BANK_NO  ,A.BANK , "
				+ " A.ACCNO , A.CHECKAMOUNT ,A.CUSTNO ,E.CUSTNAME ,A.FLAG ,A.PAYCHECK , B.OWNER,  TO_CHAR(EXTDATE,'DD MON YYYY')EXTDATE, "
				+ " TO_CHAR(TGLEXT,'DD MON YYYY')TGLEXT  ,BANKCAIR, TO_CHAR(CHECKCLEARDT,'DD MON YYYY')CHECKCLEARDT,TO_CHAR(CANCELDATE,'DD MON YYYY')CANCELDATE, " 
				+ " a.banktransfer, d.bank_name banktransfername, h.bank_name bankcairname  FROM "+tc.getTenantId()+".FPCHEQ_H A  LEFT JOIN "+tc.getTenantId()+".FTABLE130 B  ON A.BANKCAIR=B.BANK_NO " 
				+ " AND A.ACCNO=B.ACCNO AND A.CUSTNO=B.CUSTNO  LEFT JOIN "+tc.getTenantId()+".FTBANK C ON A.BANK=C.BANK_NAME  LEFT JOIN "
				+ ""+tc.getTenantId()+".ftbank_transfer d ON a.banktransfer = d.bank_no LEFT JOIN "+tc.getTenantId()+".fcustmst E ON A.CUSTNO = E.CUSTNO left join (select bank_no, bank_name from "+tc.getTenantId()+".FTBANK) h on a.bankcair = h.bank_no WHERE UPPER(CHECKNO) = UPPER('"+checkNo+"')";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resulList = query.getResultList();
		
		for (Object[] o : resulList) {
			result = new CekChoosen((String) o[0], (String) o[1], (String) o[2], (String) o[3], (String) o[4], (String) o[5],(BigDecimal) o[6], (String) o[7], (String) o[8], (String) o[9], (BigDecimal) o[10],
					 (String) o[11], (String) o[12], (String) o[13], (String) o[14], (String) o[15], (String) o[16], (String) o[17], (String) o[18], (String) o[19]);
		}
		return result;
	}

	@Override
	public List<SelectItem<String>> getDataBank() {
		// TODO Auto-generated method stub
		String sql = " SELECT BANK_NO,BANK_NAME FROM "+tc.getTenantId()+".FTBANK ORDER BY BANK_NO ASC ";
		Query query =  entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		return result.stream().map(o -> new SelectItem<>((String) o[0], (String) o[1]))
				.collect(Collectors.toList());
	}

	@Override
	public List<CekCustDto> getDataCust() {
		// TODO Auto-generated method stub
	List<CekCustDto> result = new ArrayList<>();
	String sql = " SELECT CUSTNO ,CUSTNAME ,CUSTADD1 ADDRESS1 ,CUSTADD2 ADDRESS2 ,CCONTACT CONTACT ,CPHONE1 PHONE ,"
			+ " CFAXNO FAX FROM "+tc.getTenantId()+".FCUSTMST ORDER BY CUSTNO ASC ";
	Query query = entityManager.createNativeQuery(sql);
	List<Object[]> obj = query.getResultList();
	for (Object[] o : obj) {
		CekCustDto a = new CekCustDto((String) o[0],(String) o[1],(String) o[2], (String) o[3], (String) o[4], (String) o[5], (String) o[6]);
		result.add(a);
	}
	
	return result;
	}

	@Override
	public List<SelectItem<String>> getDataBankCollection() {
		// TODO Auto-generated method stub
		String sql = " SELECT BANK_NO, BANK_NAME  FROM "+tc.getTenantId()+".FTBANK_TRANSFER ORDER BY BANK_NO ASC ";
		Query query =  entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		return result.stream().map(o -> new SelectItem<>((String) o[0], (String) o[1]))
				.collect(Collectors.toList());
	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		String sql = " select memonama from "+tc.getTenantId()+".ftable13 where xkey = 'user' ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
	

	@Override
	public void updateMemoint(String T) {
		// TODO Auto-generated method stub
		String sql = " update "+tc.getTenantId()+".fmemo set memoint=memoint+1 where memonama='BGM' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public int getMemoint(){
		String sql = " select memoint+1 AS NOBGM from "+tc.getTenantId()+".fmemo where memonama = 'BGM' ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
	
		return ((BigDecimal)obj).intValue();
	}
	
	@Override
	public String getTimeNowBySystem(){
		String sql = " select to_char(sysdate, 'HH24:MI:SS') tgl from dual ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
	
	@Override
	public String getDateNowBySystem(){
		String sql = " select to_char(sysdate, 'DD MON YYYY') tgl from dual ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
		
	
	@Override
	public String getDateNowByWarehouse(){
		String sql = " Select to_char(MEMODATE,'DD MON YYYY') AS warehouse_date from "+tc.getTenantId()+".FMEMO where MEMONAMA='CADATE' ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
	
	@Override
	public String getMemointTransfer() {
		// TODO Auto-generated method stub
		String sql = " select memoint from "+tc.getTenantId()+".ftable13 where xkey = 'NIL_TOLERANSI_TRANSFER' ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return ((BigDecimal)obj).toString();
	}
	
	@Override
	public void insertDataCek(String checkNo, String checkDate, String checkDuedT, String bank, String accNo,
			String checkAmount, String custNo, String flag, String bankCair, String userId) {
		// TODO Auto-generated method stub
		String createDateN = getDateNowByWarehouse();
		String createTimeN = getTimeNowBySystem();
		int noBgm = getMemoint();
		
		String sysDateN = getDateNowBySystem();
//		String createUserN = cekPenggunaan(tableName, noSeq)();
		
		String checkDateK = "";
		String checkDuedTK = "";
		String bankK = "";
		String accNoK = "0";
		String checkAmountK = "";
		String custNoK = "";
		String flagK = "";
		String bankCairK = "";
		
		if(!(checkDate.toString()).equals("null") || checkDate.toString().isEmpty()) {
			checkDateK = checkDate.toString();
		}
		if(!(checkDuedT.toString()).equals("null") || checkDuedT.toString().isEmpty()) {
			checkDuedTK = checkDuedT.toString();
		}
		if(!bank.equals("null") || bank.isEmpty()) {
			bankK = bank;
		}
		if(!accNo.equals("null") || accNo.isEmpty()) {
			accNoK = accNo;
		}
		if(!checkAmount.equals("null") || checkAmount.isEmpty()) {
			checkAmountK = checkAmount;
		}
		if(!custNo.equals("null") || custNo.isEmpty()) {
			custNoK = custNo;
		}
		if(!flag.equals("null") || flag.isEmpty()) {
			flagK = flag;
		}
		if(!bankCair.equals("null") || bankCair.isEmpty()) {
			bankCairK = bankCair;
		}
		
		
		String sql = "insert into "+tc.getTenantId()+".FPCHEQ_H (CHECKNO,CHECKDATE,CHECKDUEDT,BANK,ACCNO,CHECKAMOUNT,"
			+ "CUSTNO,FLAG,BANKCAIR,CREATEDATE,CREATETIME,SYS_DATE,CREATEUSER, NOBGM) values ('"+checkNo+"','"+checkDateK+"',"
					+ " '"+checkDuedTK+"','"+bankK+"','"+accNoK+"','"+checkAmountK+"','"+custNoK+"','"+flagK+"','"+bankCairK+"',"
							+ " '"+createDateN+"','"+createTimeN+"','"+sysDateN+"','"+userId+"', '"+noBgm+"')";
		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
	}


	@Override
	public void insertDataTransfer(String checkNo, String checkDate, String bank, String accNo, String checkAmount,
			String custNo, String flag, String bankTransfer) {
		// TODO Auto-generated method stub
		
		String checkDateK = "";
		String bankK = "";
		String accNoK = "0";
		String checkAmountK = "";
		String custNoK = "";
		String flagK = "";
		String bankTransferK = "";
		
		if(!(checkDate.toString()).equals("null") || checkDate.toString().isEmpty()) {
			checkDateK = checkDate.toString();
		}
		if(!bank.equals("null") || bank.isEmpty()) {
			bankK = bank;
		}
		if(!accNo.equals("null") || accNo.isEmpty()) {
			accNoK = accNo;
		}
		if(!checkAmount.equals("null") || checkAmount.isEmpty()) {
			checkAmountK = checkAmount;
		}
		if(!custNo.equals("null") || custNo.isEmpty()) {
			custNoK = custNo;
		}
		if(!flag.equals("null") || flag.isEmpty()) {
			flagK = flag;
		}
		if(!bankTransfer.equals("null") || bankTransfer.isEmpty()) {
			bankTransferK = bankTransfer;
		}
		
		String sql = "insert into "+tc.getTenantId()+".FPCHEQ_H (CHECKNO, CHECKDATE ,BANK ,ACCNO ,CHECKAMOUNT, " 
					+ "CUSTNO, FLAG, BANKTRANSFER) values ('"+checkNo+"','"+checkDateK+"', "
					+ " '"+bankK+"','"+accNoK+"','"+checkAmountK+"','"+custNoK+"','"+flagK+"','"+bankTransferK+"') ";
		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}


	@Override
	public List<CekAllDto> getDataAllCek() {
		// TODO Auto-generated method stub
	List<CekAllDto> result = new ArrayList<>();
	String sql = "SELECT UPPER(CHECKNO) CHECKNO,TO_CHAR(CHECKDATE,'DD MON YYYY')CHECKDATE, "
							+ "TO_CHAR(CHECKDUEDT,'DD MON YYYY')CHECKDUEDATE ,  BANK , "
							+ "ACCNO ACCOUNT,CHECKAMOUNT, CUSTNO,FLAG TIPE,PAYCHECK AMOUNTUSED, " 
							+ " TO_CHAR(TGLEXT,'DD MON YYYY')TGLEXT, bankcair, TO_CHAR(CHECKCLEARDT,'DD MON YYYY')CHECKCLEARDT, TO_CHAR(CANCELDATE,'DD MON YYYY')CANCELDATE,"
							+ " xgirocair, nobgm, TO_CHAR(createdate,'DD MON YYYY')createdate, createtime, TO_CHAR(sys_date,'DD MON YYYY')sys_date,createuser,"
							+ " banktransfer, TO_CHAR(wh_date,'DD MON YYYY')wh_date FROM "+tc.getTenantId()+".FPCHEQ_H ";
	
	Query query = entityManager.createNativeQuery(sql);
	List<Object[]> obj = query.getResultList();
	for (Object[] o : obj) {
		CekAllDto a = new CekAllDto((String) o[0],(String) o[1],(String) o[2], (String) o[3], (String) o[4], (BigDecimal) o[5], (String) o[6],
				(String) o[7], (BigDecimal) o[8], (String) o[9], (String) o[10], (String) o[11], (String) o[12], (String) o[13], (BigDecimal) o[14], (String) o[15], (String) o[16],
				(String) o[17], (String) o[18], (String) o[19], (String) o[20], 0);
		result.add(a);
		}
		return result;
	}


	@Override
	public List<CekBankChoosen> getCekBank(String bankNo) {
		// TODO Auto-generated method stub
		List<CekBankChoosen> result = new ArrayList<>();
		
		String sql = "select bank_no, bank_name from "+tc.getTenantId()+".ftbank where bank_no = '"+bankNo+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for (Object[] o : obj) {
			CekBankChoosen a = new CekBankChoosen((String) o[0],(String) o[1]);
			result.add(a);
		}
		
		return result;
	}


	@Override
	public List<CekCustChoosen> getCekCust(String custNo) {
		// TODO Auto-generated method stub
		List<CekCustChoosen> result = new ArrayList<>();
		
		String sql = "SELECT CUSTNO ,CUSTNAME from "+tc.getTenantId()+".FCUSTMST where CUSTNO = '"+custNo+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for (Object[] o : obj) {
			CekCustChoosen a = new CekCustChoosen((String) o[0],(String) o[1]);
			result.add(a);
		}
		
		return result;
	}


	@Override
	public List<CekBankChoosen> getCekBankCollection(String bankNo) {
		// TODO Auto-generated method stub
		List<CekBankChoosen> result = new ArrayList<>();
		
		String sql = "select bank_no, bank_name from "+tc.getTenantId()+".FTBANK_TRANSFER where bank_no = '"+bankNo+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for (Object[] o : obj) {
			CekBankChoosen a = new CekBankChoosen((String) o[0],(String) o[1]);
			result.add(a);
		}
		
		return result;
	}


	@Override
	public void deleteDataCek(String checkNo) {
		// TODO Auto-generated method stub
		String sql = "delete from "+tc.getTenantId()+".FPCHEQ_H where checkno = '"+checkNo+"' AND (PAYCHECK IS NULL or PAYCHECK = '0')  AND EXTDATE IS NULL ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}


	@Override
	public void updateDataCek (String checkNo, String checkDate, String checkDuedT, String bank,
			String accNo, String checkAmount, String custNo, String flag, String bankCair, String bankTransfer) {
		// TODO Auto-generated method stub
		String bankCairK = "";
		String bankTransferK = "";
		String checkDuedK = "";
		
		if(!(bankCair == null)) {
			bankCairK = bankCair;
		}
		if(!(bankTransfer == null)) {
			bankTransferK = bankTransfer;
		}
		if(!(checkDuedT == null)) {
			checkDuedK = checkDuedT;
		}
		
		
		String sql = "update "+tc.getTenantId()+".FPCHEQ_H set CHECKDATE = '"+checkDate+"', CHECKDUEDT = '"+checkDuedT+"', BANK = '"+bank+"' ,ACCNO = '"+accNo+"', "
				+ " CHECKAMOUNT = '"+checkAmount+"' ,CUSTNO = '"+custNo+"' ,FLAG = '"+flag+"' ,BANKCAIR = '"+bankCairK+"',BANKTRANSFER = '"+bankTransferK+"' WHERE CHECKNO = '"+checkNo+"' ";

		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
	}


	@Override
	public String getTransferNo (String checkNo) {
		// TODO Auto-generated method stub
		
		String sql = " select trsno from "+tc.getTenantId()+".ftable34 where payno is null and trsno = '"+checkNo+"' union all   select d.trsno from "+tc.getTenantId()+".fpay_h h " + 
				" inner join "+tc.getTenantId()+".fpay_d d on h.payno = d.payno where h.flagcancel is null and trsno = '"+checkNo+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}

	
	@Override
	public String getCheckNo (String checkNo) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT NVL (paycheck, 0) paycheck FROM "+tc.getTenantId()+".fpcheq_h WHERE checkno = '"+checkNo+"'"; 
		
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return ((BigDecimal)obj).toString();
	}


	@Override
	public List<SelectItem<String>> getDateCek(String checkNo) {
		// TODO Auto-generated method stub
		String sql = "SELECT TO_CHAR(CANCELDATE,'DD MON YYYY')CANCELDATE, TO_CHAR(CHECKCLEARDT,'DD MON YYYY')CHECKCLEARDT "
				+ " FROM "+tc.getTenantId()+".fpcheq_h WHERE UPPER(checkno) = '"+checkNo+"' "; 

		Query query =  entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		return result.stream().map(o -> new SelectItem<>((String) o[0], (String) o[1]))
				.collect(Collectors.toList());
	}

	
	@Override
	public int cekPenggunaan(String tableName, String noSeq) {
		// TODO Auto-generated method stub
		String sql = "SELECT NAMAUSER "
				   +" FROM "+tc.getTenantId()+".FPRGACT "
				   +" WHERE NAMATABEL = '"+tableName+"' AND NO_KEY = '"+noSeq+"' ";
		Query query = entityManager.createNativeQuery(sql);
		try {
			return Integer.parseInt(query.getSingleResult().toString());	
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public void insertPenggunaan(String tableName, String noSeq, String userId) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+tc.getTenantId()+".FPRGACT (NAMATABEL,NO_KEY,NAMAUSER) "
					+" VALUES ('"+tableName+"','"+noSeq+"','"+userId+"') ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}


	@Override
	public void deletePenggunaan(String tableName, String noSeq, String userId) {
		// TODO Auto-generated method stub
		String sql = "DELETE "+tc.getTenantId()+".FPRGACT WHERE NAMATABEL = '"+tableName+"'"
				+ "AND NO_KEY = '"+noSeq+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}


	@Override
	public String getKeyAuth() {
		// TODO Auto-generated method stub
		String sql = " select to_char(sysdate, 'HH:mm:ss') tgl from dual";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		
		return (String)obj;
	}


	@Override
	public String getKeyOk(String key, String auth) {
		// TODO Auto-generated method stub
	String xkey12,xkey21,xkey22,xkey23,xkey24,xkey25,xkey26,xkey27,xkey28;
	String xkey31,xkey32,xkey33,xkey34,xkey35,xkey36,xkey37,xkey38,xkey39;
	int xkey10,xkey16,xkey17;
	double xkey18;
	String xflag;
	
	xkey21 = auth.substring(0, 1);
	xkey22 = auth.substring(2, 3);
	xkey23 = auth.substring(4, 5);
	xkey24 = auth.substring(6, 7);
	xkey25 = auth.substring(8, 9);
	xkey26 = auth.substring(10, 11);
	
	xkey31 = auth.substring(1, 2); //8
	xkey32 = auth.substring(3, 4); //1
	xkey33 = auth.substring(5, 6);//4
	xkey34 = auth.substring(7, 8);//5
	xkey35 = auth.substring(9, 10);//1
	xkey36 = auth.substring(11, 12);//7
	
	xkey27 = xkey21+xkey22+xkey23+xkey24+xkey25+xkey26;
	xkey37 = xkey31+xkey32+xkey33+xkey34+xkey35+xkey36;
	
	xkey10 = Integer.valueOf(key)*Integer.valueOf("09") + 21;
	xkey12 = String.valueOf(xkey10);
	xkey12 = xkey12.substring(0, 6);
	
	xkey16 = Integer.valueOf(xkey27);
	
	xkey28 = key.substring(key.length() - 1);
	
	xkey17 = Integer.valueOf(xkey28);
	
	xkey39 = key.substring(key.length() - 1);
	
	if (xkey39 == "0") {
		xkey18 = xkey16;
	}else {
		xkey18 = (xkey16 / xkey17);
	}
	
	xkey38 = String.valueOf(xkey18);
	
	if(xkey12.equalsIgnoreCase(xkey37)) {
		xflag = "AiyayaCaptain";
	}else {
		xflag = "TurnAroundCaptain";
	}
	
		return xflag;
	}
	
	

}
