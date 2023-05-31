package myor.matrix.master.util;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myor.matrix.master.tenant.TenantContext;

@Component
public class Util {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void deleteFtable13 (String xkey, String memostring){
		
		String sql = " 	DELETE FROM "+t.getTenantId()+".FTABLE13 "
					+"	WHERE xkey = '"+xkey+"' AND memostring = '"+memostring+"' ";		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	public boolean cekAda(String query){
		
		boolean result = true;
		
		String sql = "'"+query+"'";
		Query q = entityManager.createNativeQuery(sql);
		
		List<Object[]> resultList = q.getResultList();
		if(resultList.size() > 0){
			result = true;
		} else {
			result = false;
		}
				
		return result;
	}
	
	public String getSubdistId() {
		// TODO Auto-generated method stub
		String sql = "SELECT MEMOSTRING FROM "+t.getTenantId()+".FMEMO WHERE MEMONAMA='KODEDIST' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;		
	}
	
	public String getSubdistName() {
		// TODO Auto-generated method stub
		String sql = "SELECT MEMOSTRING FROM "+t.getTenantId()+".FMEMO WHERE MEMONAMA='COMPANYNAME' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;		
	}
	
	public String getDigitH(){
		String sql = "SELECT memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XDESIMALPOINT'";
		
		Query query = entityManager.createNativeQuery(sql);
		
		
		List<String> result = query.getResultList();
		
		String s = "";
		if (result.size() > 0) {
			s = result.get(0).toString();	
		} 
		
		return s;
	}
		
	public String getFlagCetakRetur() {
		// TODO Auto-generated method stub
		String sql = "SELECT MEMOSTRING FROM "+t.getTenantId()+".FMEMO WHERE MEMONAMA='NEGRETUR' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;		
	}
	
	public String getBarcode(String invno){
		
		String barcode = "";
		
		String a = invno.substring(0, 1);
		String b = invno.substring(1, 2);
		String c = invno.substring(2, 3);
		String d = invno.substring(3, 4);
		String e = invno.substring(4, 5);
		String f = invno.substring(5, 6);
		String g = invno.substring(6, 7);
		String h = invno.substring(7, 8);
		
		if (h.isEmpty() == true) {
			h = "0";
		}
		if (g.isEmpty() == true) {
			g = "0";
		}
		if (f.isEmpty() == true) {
			f = "0";
		}
		if (e.isEmpty() == true) {
			e = "0";
		}
		if (d.isEmpty() == true) {
			d = "0";
		}
		if (c.isEmpty() == true) {
			c = "0";
		}
		
		int c1 = Integer.parseInt(c);
		int d1 = Integer.parseInt(d);
		int e1 = Integer.parseInt(e);
		int f1 = Integer.parseInt(f);
		int g1 = Integer.parseInt(g);
		int h1 = Integer.parseInt(h);
		int j1 = ((c1+e1+f1+h1-d1-g1) * 2 )  - e1 - g1 + c1 + d1 + f1 + h1;
		
		String j = String.valueOf(j1*3);
		String k = j.substring(0, 1);
		j = String.valueOf(j1);
		j = j.substring(0, 1);
	    
		barcode = h + f + j+a + c +k+b + g + d + e ; 
	     	      
		
	    
		
		return barcode;
	}
	
	public String chek_tableOra(String xfile, String userLogin){
		
		String x1 = userLogin.replace(' ', 'M');
		String x2 = x1.replace('-', 'M');
		String x3 = x2.replace(':', 'M');
		String x4 = x3.replace('/', 'M');
		String x5 = x4.replace(':', 'M');
		String x6 = x5.replace('_', 'M');
		
		String userName = x6;
		
		String namaF = xfile;
		
		
		String sql = "SELECT   NVL(TO_CHAR(max(TO_NUMBER (nvl(table_name,0))) + 1), '0') table_name " 
					+"	 FROM (SELECT DISTINCT REGEXP_SUBSTR(table_name  , '[^_]+', 1, 2) table_name "
					+"	                  FROM all_tab_columns                      "
					+"	                WHERE table_name LIKE '"+xfile+userName.toUpperCase()+"%') "        
					+"	 ORDER BY table_name ASC";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		namaF = xfile+userName.toUpperCase()+"_";
		String hasil = namaF+s;
		
		return hasil;
	}
	
	
	
	
	public String currToText(String n1) {
		
		String[]  vlStep = {" ","ribu ","juta ","milyar ","trilyun "};
		boolean good = false;
		boolean belas = false;
		String n12 = "";
		String nlTemp,qtemp,stemp,stemp2,addStr = "";
		
		int i,j,k,m,panjang = 0;
		
		//n1 =   String.valueOf(Math.floor((Float.parseFloat(n1))));
		
		String[] arrayN1 = n1.split("(?!^)");
		
	//	String[] arrayQtemp = {};
		
		for (i = 0; i < n1.length() ; i++) {			
			if (arrayN1[i] != "0") {
				good = true;
			}
			
			if (good == true) {
				n12 = n12+arrayN1[i];
			}
		}
		
		if (n12.length() > 15) {
			nlTemp = n12.substring(n12.length()-15+1, 15);
		} else {
			nlTemp = n12;
		}
		
		stemp2 = "";
		
		for (i = 0; i <= 4; i++) {
			k = nlTemp.length();
			
			if (k == 0) {
				break;
			}
			
			if (k >= 3) {
				qtemp = nlTemp.substring(nlTemp.length() - 3, 6);
			} else {
				qtemp = nlTemp;
			}
			
			String[] arrayQtemp = qtemp.split("(?!^)");
			
			nlTemp = nlTemp.substring(0, nlTemp.length()-3);
			stemp = "";
			belas = false;
			
			if (k >= 3) {
				panjang  = 3;
			} else {
				panjang = k;
			}
			
			m = 4 - panjang;
			
			for (j = 0; j < panjang; j++) {
				addStr = "";
				if(arrayQtemp[j] == "9") {
					addStr = "sembilan ";	
				}
				if(arrayQtemp[j] == "8") {
					addStr = "delapan ";	
				}
				if(arrayQtemp[j] == "7") {
					addStr = "tujuh ";	
				}
				if(arrayQtemp[j] == "6") {
					addStr = "enam ";	
				}
				if(arrayQtemp[j] == "5") {
					addStr = "lima ";	
				}
				if(arrayQtemp[j] == "4") {
					addStr = "empat ";	
				}
				if(arrayQtemp[j] == "3") {
					addStr = "tiga ";	
				}
				if(arrayQtemp[j] == "2") {
					addStr = "dua ";	
				}
				if(arrayQtemp[j] == "1") {
					
					if (m == 1 || m == 2) {
						if (m == 1) {
							addStr = "se";
						}
						if (m == 2) {
							belas = true;
						}
					} else if (m == 3) {
						if (belas ==  false) {
							if (i == 1) {
								if (stemp == "") {
									addStr = "se";
								} else {
									addStr = "satu ";
								}
							} else {
								addStr = "satu ";
							}
						} else {
							addStr = "se";
						}
					}
										
				}
				
				if(arrayQtemp[j] == "0") {
					if (belas == true) {
						addStr = "sepuluh ";
						belas = false;
					}
				}
				
				if ((addStr != "") || (belas == true)) {
					if (m == 1) {
						addStr = addStr + "ratus ";
					}
					if (m == 2) {
						if (belas == false) {
							addStr = addStr + "puluh ";
						}
					}
					if (m == 3) {
						if (belas == true) {
							addStr = addStr + "belas ";
						}
					}
													
				}
				
				stemp = stemp + addStr;
				m = m + 1;
			}
			
			if (stemp != "") {
				stemp2 = stemp + vlStep[i] + stemp2;
			}
			        			
		}
		
		String hasil = stemp2.substring(1, 1) + stemp2.substring(2, stemp2.length())  ;
		
		hasil  = hasil.toUpperCase();
		
		return hasil;
	}
	
	public String getTerbilang(String value, boolean isJustFirstCapital) {
		int lenValue = value.length();
		int x = 0;
		int y = 0;
		int z;
		String bil1 = "";
		String bil2 = "";		
		
		String hasil = "";
		
		while(x < lenValue) {
			x = x + 1;
			int strTot = Integer.valueOf(value.substring(x-1,x));
			y = y + strTot;
			z = lenValue - x + 1;
			switch (strTot) {
			case 1:
				if(z == 1 || z == 7 || z == 10 || z == 13) {
					bil1 = "Satu ";
				} else if (z == 4) {
					if (x == 1) {
						bil1 = "Se";
					} else {
						bil1 = "Satu ";
					}
				} else if (z == 2 || z == 5 || z == 8 || z == 11 || z == 14) {
					x = x + 1;
					int newStrTot = Integer.valueOf(value.substring(x-1,x));
					z = lenValue - x + 1;
					bil2 = "";
					switch (newStrTot) {
					case 0:
						bil1 = "Sepuluh ";
						break;
					case 1:
						bil1 = "Sebelas ";
						break;
					case 2:
						bil1 = "Dua belas ";
						break;
					case 3:
						bil1 = "Tiga belas ";
						break;
					case 4:
						bil1 = "Empat belas ";
						break;
					case 5:
						bil1 = "Lima belas ";
						break;
					case 6:
						bil1 = "Enam belas ";
						break;
					case 7:
						bil1 = "Tujuh belas ";
						break;
					case 8:
						bil1 = "Delapan belas ";
						break;
					case 9:
						bil1 = "Sembilan belas ";
						break;
					default:
						break;
					}
				} else {
					bil1 = "Se";
				}
				break;
			case 2:
				bil1 = "Dua ";
				break;
			case 3:
				bil1 = "Tiga ";
				break;
			case 4:
				bil1 = "Empat ";
				break;
			case 5:
				bil1 = "Lima ";
				break;
			case 6:
				bil1 = "Enam ";
				break;
			case 7:
				bil1 = "Tujuh ";
				break;
			case 8:
				bil1 = "Delapan ";
				break;
			case 9:
				bil1 = "Sembilan ";
				break;
			default:
				bil1 = "";
				break;
			}	
			

			if (strTot > 0) {
				if (z == 2 || z == 5 || z == 8 || z == 11 || z == 14) {
				   bil2 = "Puluh ";
				} else if (z == 3 || z == 6 || z == 9 || z == 12 || z == 15) {
				   bil2 = "Ratus ";
				} else {
				   bil2 = "";
				}
			} else {
				bil2 = "";
			}
			
			if (y > 0) {
				switch (z) {
				case 4:
					bil2 = bil2 + "Ribu ";
					y = 0;
					break;
				case 7:
					bil2 = bil2 + "Juta ";
					y = 0;
					break;
				case 10:
					bil2 = bil2 + "Milyar ";
					y = 0;
					break;
				case 13:
					bil2 = bil2 + "Trilyun ";
					y = 0;
					break;
				default:
					break;
				}
			}
			
			if (bil1.equals("Se")) {
				String pre = bil2.substring(0, 1);
				hasil = hasil + bil1 + bil2.replace(pre, pre.toLowerCase());
			} else {
				hasil = hasil + bil1 + bil2;
			}
		} 
		
		if(isJustFirstCapital) {
			String tHasil = hasil.toLowerCase();
			String firstLetStr = tHasil.substring(0, 1);
			String remLetStr = tHasil.substring(1);
			firstLetStr = firstLetStr.toUpperCase();
			hasil = firstLetStr + remLetStr;
		}
		
		return hasil;
	}
	
	public void disActTable(String xkey, String user, String xappact) {
		String sql = " 	DELETE FROM "+t.getTenantId()+".FTABLE13 "
				+"	WHERE xkey = '"+xkey+"' "
				+"		AND MEMOSTRING2 = '"+xappact+"' "
				+"		AND NOURUT = ( "
				+"			SELECT MIN(NOURUT) FROM "+t.getTenantId()+".ftable13 "
				+"			WHERE xkey = '"+xkey+"' "
				+"				AND MEMOSTRING2 = '"+xappact+"' "
				+"		) ";		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	public void actTable(String xkey, String user, String xappact) {
		String sql = " 	DELETE FROM "+t.getTenantId()+".FTABLE13 "
				+"	WHERE xkey = '"+xkey+"' "
				+"		AND MEMOSTRING = '"+user+"' "
				+"		AND MEMOSTRING2 = '"+xappact+"' ";		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
		String sql2 = " INSERT INTO "+t.getTenantId()+".ftable13 (XKEY,MEMOSTRING,MEMOSTRING2,NOURUT) VALUES "
				+" ('"+xkey+"' ,'"+user+"' ,'"+xappact+"' , "
				+"		(SELECT  NVL(MAX(NOURUT),0)+1  "
				+"			FROM "+t.getTenantId()+".ftable13 "
				+"			WHERE XKEY = '"+xkey+"' "
				+"				AND MEMOSTRING2 = '"+xappact+"')"
				+" )";		
		Query query2 = entityManager.createNativeQuery(sql2);
		query2.executeUpdate();
	}
	
	public boolean cekFtable13ByXkeyAndMemostring2(String xkey, String memostring2){
		boolean result = true;
		
		String sql = "SELECT * FROM "+t.getTenantId()+".ftable13 "
				+"	WHERE xkey = '"+xkey+"' "
				+"		AND memostring2 = '"+memostring2+"' ";
		Query q = entityManager.createNativeQuery(sql);
		
		List<Object[]> resultList = q.getResultList();
		if(resultList.size() > 0){
			result = true;
		} else {
			result = false;
		}
				
		return result;
	}
	
	public boolean cekFprgactByNamaTableAndNoKeyAndUser(String namatable, String nokey, String user) {
		boolean result = true;
		
		String sql = "SELECT * FROM "+t.getTenantId()+".FPRGACT "
				+"	WHERE NAMATABEL = '"+namatable+"' "
				+"		AND NO_KEY = '"+nokey+"' "
				+"		AND NAMAUSER = '"+user+"' ";
		Query q = entityManager.createNativeQuery(sql);
		
		List<Object[]> resultList = q.getResultList();
		if(resultList.size() > 0){
			result = true;
		} else {
			result = false;
		}
				
		return result;
	}
	
	public boolean cekFprgactByNamaTableAndNoKey (String namaTable, String nokey) {
		boolean result = true;
		
		String sql = "SELECT * FROM "+t.getTenantId()+".FPRGACT "
				+"	WHERE NAMATABEL = '"+namaTable+"' "
				+"		AND NO_KEY = '"+nokey+"' ";				
		Query q = entityManager.createNativeQuery(sql);
		
		List<Object[]> resultList = q.getResultList();
		if(resultList.size() > 0){
			result = true;
		} else {
			result = false;
		}
				
		return result;
	}
	
	public void insertFprgact(String namatable, String nokey, String user) {
		String sql = " 	INSERT INTO "+t.getTenantId()+".FPRGACT (NAMATABEL, NO_KEY, NAMAUSER) "
				+"	VALUES('"+namatable+"', '"+nokey+"', '"+user+"')";		
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	public void deleteFprgactByNamatableAndNokeyAndUser(String namatable, String nokey, String user) {
		String sql = "DELETE FROM "+t.getTenantId()+".FPRGACT "
				+"	WHERE NAMATABEL = '"+namatable+"' "
				+"		AND NO_KEY = '"+nokey+"' "
				+"		AND NAMAUSER = '"+user+"' ";	
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	public void deleteFprgactByNamatableLikeAndUser(String namaTableLike, String user) {
		String sql = "DELETE FROM "+t.getTenantId()+".FPRGACT "
				+"	WHERE NAMATABEL LIKE '%"+namaTableLike+"%' "
				+"		AND NAMAUSER = '"+user+"' ";	
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	public void deleteFprgactByNamatable(String namaTable) {
		String sql = "DELETE FROM "+t.getTenantId()+".FPRGACT "
				+"	WHERE NAMATABEL = '"+namaTable+"' ";				
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	public void deleteFprgactByNamaTableKey(String namaTable, String key) {
		String sql = "DELETE FROM "+t.getTenantId()+".FPRGACT "
				+"	WHERE NAMATABEL = '"+namaTable+"' "
				+"		AND NO_KEY = '"+key+"' ";
	}
	
	public void deleteFprgactByNamaTableAndUser (String namaTable, String user) {
		String sql = "DELETE FROM "+t.getTenantId()+".FPRGACT "
				+"	WHERE NAMATABEL = '"+namaTable+"' "
				+"		AND NAMAUSER = '"+user+"' ";	
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	public void dropTableTempByNameLike(String namaTableLike) {
		String sql = "BEGIN "
				+"  FOR c IN ( SELECT owner, table_name " 
				+"	FROM all_tables  "
				+"	WHERE owner = '"+t.getTenantId()+"' " 
				+"		AND table_name LIKE '%"+namaTableLike+"%') "
				+"  LOOP "
				+"    EXECUTE IMMEDIATE 'DROP TABLE "+t.getTenantId()+".' || c.table_name; "
				+"  END LOOP; "
				+"END; ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	
	public String getTglGudang() {
		// TODO Auto-generated method stub
		String sql = "SELECT TO_CHAR(MEMODATE,'DD MON YYYY') AS memodate FROM "+t.getTenantId()+".FMEMO WHERE MEMONAMA  = 'CADATE' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;		
	}
	
	public String getRelease(){
		String sql = "SELECT to_char(xno) xno FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE'";
		
		Query query = entityManager.createNativeQuery(sql);
		
		
		List<String> result = query.getResultList();
		
		String s = "";
		if (result.size() > 0) {
			s = result.get(0);	
		} 
		return s;
	}
	
	public String getDuedate(String tglGudang, int top){
		String sql = "SELECT TO_CHAR(TO_DATE('"+tglGudang+"')+"+top+",'DD MON YYYY') AS duedate FROM DUAL";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;
	}
	
	public String getJamSystem(){
		String sql = "select TO_CHAR(sysdate, 'HH24:MI:SS') as jamsystem from dual ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;
	}
	
	public String getTglSystem(){
		String sql = "SELECT TO_CHAR(sysdate,'DD MON YYYY') as tglsystem FROM dual ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;
	}
	
	public List<Object[]> getPerideWeekTglGudang(){
		String sql = "SELECT to_char(PRDNO), to_char(WEEKNO) FROM "+t.getTenantId()+".FCYCLE3 WHERE CDATE=(select memodate from "+t.getTenantId()+".fmemo where memonama='CADATE')  ";
		Query query = entityManager.createNativeQuery(sql);
		
		return query.getResultList();
	}
	
	public String cekMemostringFtable13(String xkey, String memostring) {
		String sql = " SELECT memostring "
				+" FROM "+t.getTenantId()+".FTABLE13 "
				+"  WHERE xkey = '"+xkey+"' ";
		if (!memostring.equalsIgnoreCase("")) {
			sql += " AND memonama = '"+memostring+"' ";
		}
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;
	}
	
	public int cekMemointFtable13(String xkey) {
		String sql = "SELECT memoint FROM "+t.getTenantId()+".FTABLE13 "
				+" WHERE xkey = '"+xkey+"' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		int s = 0;
		try {
			s = Integer.parseInt(result.get(0).toString());
		} catch (Exception e) {}
		
		return s;
	}
	
	public int cekMemointFmemo(String memonama) {
		String sql = "SELECT memoint FROM "+t.getTenantId()+".FMEMO "
				+" WHERE memonama = '"+memonama+"' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		int s = 0;
		try {
			s = Integer.parseInt(result.get(0).toString());
		} catch (Exception e) {}
		
		return s;
	}
	
	public String cekMemonamaFtable13(String xkey){
		
		String sql =  " SELECT memonama FROM "+t.getTenantId()+".FTABLE13 "
				+" WHERE xkey = '"+xkey+"' AND memonama = '2' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;
	}
	
	public String cekFlagPay (String custno) {
		String sql = "SELECT flagpay FROM  "+t.getTenantId()+".FCUSTMST "
				+" WHERE custno = '"+custno+"' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;
	}
	
	public String cekGrouppayer(String custno) {
		String sql = "SELECT grouppayer FROM "+t.getTenantId()+".FTABLE48 WHERE custno = '"+custno+"' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;
	}
	
	public String getJamSystem2() {
		String sql = " select to_char(sysdate,'hh:mm:ss') as jamsys from dual ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		String s = "";
		try {
			s = result.get(0).toString();
		} catch (Exception e) {}
		
		return s;
	}
	
	
	
	
	public String getXkey (int x, int y, String xjam) {
		
		String xkey5,xkey6,xkey7,xstr2="";
		int xkey1,xkey2,xkey3 = 0;
		int xkey10,xkey13 = 0;

		xkey1 = Integer.parseInt(xjam.substring(0,2));
		xkey2 = Integer.parseInt(xjam.substring(3,5));
		xkey3 = Integer.parseInt(xjam.substring(6,8));
		xkey13 = Integer.parseInt(xjam.substring(7,8));
		
		xkey1 = xkey1 + 1;
		xkey2 = xkey2 + 3;
		xkey3 = xkey3 + 2;
		
		xkey5 = Integer.toString(xkey1);
		xkey6 = Integer.toString(xkey2);
		xkey7 = Integer.toString(xkey3);
		
		if (Integer.toString(xkey5.length()) == "2") {
			xkey5 = xkey5;
		} else {
			xkey5 = "0"+xkey5;
		}
		
		if (Integer.toString(xkey6.length()) == "2") {
			xkey6 = xkey6;
		} else {
			xkey6 = "0"+xkey6;
		}
		
		if (Integer.toString(xkey7.length()) == "2") {
			xkey7 = xkey7;
		} else {
			xkey7 = "0"+xkey7;
		}
		
		xstr2 = (xkey5+xkey6+xkey7) + (Integer.toString(xkey13));
		xkey10 = (Integer.parseInt(xstr2)*x)+y;
		
		
		return Integer.toString(xkey10);
	}
	
	
	public String cekKey (int x, int y, String kodeKey, String kodeAuth) {
		
		String xkey21,xkey22,xkey23,xkey24,xkey25,xkey26,xkey27,xkey31,xkey32,
				xkey33,xkey34,xkey35,xkey36,xkey37,xkey12,xkey28,xkey38,xkey39 = "";
		
		int xkey8,xkey9,xkey10,xkey11,xkey13 = 0;
		int xkey16,xkey17,xkey19,xkey20 = 0;
		
		double xkey18 = 0;
		
		String hasil = "";

		
		xkey21 = kodeAuth.substring(0,1);
		xkey22 = kodeAuth.substring(2,3);
		xkey23 = kodeAuth.substring(4,5);
		xkey24 = kodeAuth.substring(6,7);
		xkey25 = kodeAuth.substring(8,9);
		xkey26 = kodeAuth.substring(10,11);
		
		xkey31 = kodeAuth.substring(1,2);
		xkey32 = kodeAuth.substring(3,4);
		xkey33 = kodeAuth.substring(5,6);
		xkey34 = kodeAuth.substring(7,8);
		xkey35 = kodeAuth.substring(9,10);
		xkey36 = kodeAuth.substring(11,12);
		
		xkey27 = xkey21 + xkey22 + xkey23 + xkey24 + xkey25 + xkey26;
		xkey37 = xkey31 + xkey32 + xkey33 + xkey34 + xkey35 + xkey36;
		
		xkey10 = (Integer.parseInt(kodeKey)* x ) + y;
		xkey12 = Integer.toString(xkey10);
		xkey12 = xkey12.substring(0,5);
		
		xkey16 = Integer.parseInt(xkey27);
		
		xkey28 = kodeKey.substring(kodeKey.length() - 1);
		
		xkey17 = Integer.parseInt(xkey28);
		
		xkey39 = kodeKey.substring(kodeKey.length() - 1);
		
		if (xkey39.equalsIgnoreCase("0")) {
			xkey18 = xkey16;
		} else {
			xkey18 = (xkey16 / xkey17);
		}
		
		xkey38 = Double.toString(xkey18);
		
		
		if (xkey12 != xkey37) {
			hasil = "NOTOK";
		} else {
			hasil = xkey38;
		}
		
		return hasil;
	}
	
	public String getParamLibur(){
		String sql = "	SELECT NVL(memostring,'T') as memostring "
					+" 		FROM "+t.getTenantId()+".fmemo "
					+" 	WHERE memonama = 'PARAMLIBUR' ";
		
		Query query = entityManager.createNativeQuery(sql);
		
		
		List<String> result = query.getResultList();
		
		String s = "";
		if (result.size() > 0) {
			s = result.get(0).toString();	
		} 
		
		return s;
	}
	
	public int getMinPengiriman() {
		String sql = "SELECT NVL(memoint,'1') as memoint FROM "+t.getTenantId()+".FMEMO "
				+" WHERE memonama = 'XTOLERANSIPENG' ";
		Query query = entityManager.createNativeQuery(sql);
		
		List<String> result = query.getResultList();
		int s = 1;
		try {
			s = Integer.parseInt(result.get(0).toString());
		} catch (Exception e) {}
		
		return s;
	}
	
	
}
