package myor.matrix.master.repository.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import myor.matrix.master.repository.ExtractMstOutletPengcoveranRepository;
import myor.matrix.master.tenant.TenantContext;


@Repository
public class ExtractMstOutletPengcoveranRepositoryImpl implements ExtractMstOutletPengcoveranRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private BufferedWriter fileWriter;
	
	private String nameFolder = "/EXTRACT";
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String usernameDb;
	
	@Value("${spring.datasource.password}")
	private String passwordDb;
	
	@Override
	public int getEkstrakDataPengcoveran(String userId, String fullFileName, String optSls, String optOutletStatus, List<String> listSalesman) {
		int hasil = 1;
		String sqlString = 	"	SELECT rownum as no,b.CUSTNO,b.SLSNO,B.SLSNAME,b.NOBRS, 									"
							+"					case when b.HSENIN='T' then 'N' else b.HSENIN end as HSENIN, 					"
							+"					case when b.HSELASA='T' then 'N' else b.HSELASA end as HSELASA,					"
							+"					case when b.HRABU='T' then 'N' else b.HRABU end as HRABU,					"
							+"					case when b.HKAMIS='T' then 'N' else b.HKAMIS end as HKAMIS,					"
							+"					case when b.HJUMAT='T' then 'N' else b.HJUMAT end as HJUMAT, 					"
							+"					case when b.HSABTU='T' then 'N' else b.HSABTU end as HSABTU, 					"
							+"					case when b.HMINGGU='T' then 'N' else b.HMINGGU end as HMINGGU,  					"
							+"					case 	when b.visit1||b.visit2||b.visit3||b.visit4 ='YYYY' then 'Weekly'  				"
							+"							when  b.visit1||b.visit2||b.visit3||b.visit4 ='YTYT' then 'BeWeekly 1'  			"
							+"							when  b.visit1||b.visit2||b.visit3||b.visit4 ='TYTY' then 'BeWeekly 2'  			"
							+"							when  b.visit1||b.visit2||b.visit3||b.visit4 ='YTTT' then 'Monthly 1'  			"
							+"							when  b.visit1||b.visit2||b.visit3||b.visit4 ='TYTT' then 'Monthly 2' 			"
							+"							when  b.visit1||b.visit2||b.visit3||b.visit4 ='TTYT' then 'Monthly 3'  			"
							+"							when  b.visit1||b.visit2||b.visit3||b.visit4 ='TTTY' then 'Monthly 4'  			"
							+"					end as pola ,b.ROUTE,b.SLIMIT 					"
							+"	FROM  (                                                                        									"
							+"		        select custno, custname, custadd1, custadd2, ccity, ckdpos, ccontact, cphone1,  								"
							+"						cfaxno, cresd1, cresd2, ccity2, cphone2, cterm, climit, flaglimit,     				"
							+"						flagpay, flagout, flagkbon, flaghome, gdisc, LOCATION, distrik,          				"
							+"						typeout, grupout, gharga, gplu, gkonv, beat1, sbeat, CLASS, kdpsr,				"
							+"						kdind, cweekno, to_char(clastdate,'ddMMyyyy') as clastdate, to_char(firstdate,'ddMMyyyy') as firstdate, to_char(firstopen,'ddMMyyyy') as firstopen, to_char(regdate,'ddMMyyyy') as regdate, taxname,       				"
							+"						taxadd1, taxadd2, taxcity, taxflag, npwp, rata, bank, noacc, norefsup,   				"
							+"						agenlain, data01, data02, data03, data04, data05, data06, data07,        				"
							+"						data08, to_char(data09,'ddMMyyyy') as data09, data10,beat2, data12, data13, data14, data15, data16,    				"
							+"						data17, data18,outtype,h11,provinsi,h12,kabupaten,h13,kecamatan,h14,kelurahan 				"
							+"	from (    									"
							+"		         	select a.custno, a.custname, a.custadd1, a.custadd2, a.ccity, a.ckdpos, a.ccontact, a.cphone1, 							"
							+"			                a.cfaxno, a.cresd1, a.cresd2, a.ccity2, a.cphone2, a.cterm, a.climit, a.flaglimit,      							"
							+"			                a.flagpay, a.flagout, a.flagkbon, a.flaghome, a.gdisc, a.LOCATION, a.distrik,          							"
							+"			                a.typeout, a.grupout, a.gharga, a.gplu, a.gkonv, a.beat as beat1, a.sbeat, a.CLASS, a.kdpsr, 							"
							+"			                a.kdind, a.cweekno, a.clastdate, a.firstdate, a.firstopen, a.regdate, a.taxname,             							"
							+"			                a.taxadd1, a.taxadd2, a.taxcity, a.taxflag, a.npwp, a.rata, a.bank, a.noacc, a.norefsup,     							"
							+"			                a.agenlain, a.data01, a.data02, a.data03, a.data04, a.data05, a.data06, a.data07,            							"
							+"			                a.data08, a.data09, a.data10, a.beat as beat2, a.data12, a.data13, a.data14, a.data15, a.data16, 							"
							+"			                a.data17, a.data18,b.outtype,b.h11,b.provinsi,b.h12,b.kabupaten,b.h13,b.kecamatan,b.h14,b.kelurahan 							"
							+"		           	from "+t.getTenantId()+".fcustmst a 							"
							+"		         	left join (							"
							+"			         	select a.*,b.OUTTYPE 						"
							+"			         	from 						"
							+"			         	(                                                        						"
							+"							select a.custno,a.h11,a.h12,a.h13,a.h14,a.provinsi,a.kabupaten,a.kecamatan,b.ket as kelurahan  			"
							+"							from (			"
							+"							 	select a.custno,a.h11,a.h12,a.h13,a.h14,a.provinsi,a.kabupaten,b.ket as kecamatan 		"
							+"							 	from ( 		"
							+"							 		select a.custno,a.h11,a.h12,a.h13,a.h14,a.provinsi,b.ket as kabupaten 	"
							+"							     	from (                   		"
							+"							     		select a.custno,a.h11,a.h12,a.h13,a.h14,b.ket as provinsi from "+t.getTenantId()+".fcustud a "
							+"										left join "+t.getTenantId()+".fcshir11 b on a.h11=t11	"
							+"							     	) a 		"
							+"							 		left join "+t.getTenantId()+".fcshir12 b on a.h11=b.t11 and a.h12=b.t12	"
							+"							 	) a 		"
							+"							 	left join "+t.getTenantId()+".fcshir13 b on a.h11=b.t11 and a.h12=b.t12 and a.h13=b.t13		"
							+"							)a 			"
							+"							left join "+t.getTenantId()+".fcshir14 b on a.h11=b.t11 and a.h12=b.t12 and a.h13=b.t13 and a.h14=b.t14			"
							+"			         	) a 						"
							+"			         	left join 						"
							+"			         	(						"
							+"			         		SELECT custno,short_code as outtype 					"
							+"			         		FROM "+t.getTenantId()+".ftable48 a "
							+"							left JOIN "+t.getTenantId()+".ftable49 b ON a.outtype = b.outtype					"
							+"			         	) b on a.custno=b.custno						"
							+"		         	) b on a.custno=b.custno							"
							+"		)                                             								"
							+"	) a 									"
							+"	left join 									"
							+"	(									"
							+"		SELECT a.custno, a.slsno, b.slsname, a.nobrs, a.hsenin, a.hselasa, a.hrabu, 								"
							+"						a.hkamis, a.hjumat, a.hsabtu, a.hminggu, a.visit1, a.visit2, a.visit3, 				"
							+"						a.visit4, a.route, a.slimit                                            				"
							+"		FROM "+t.getTenantId()+".fcustsls a 								"
							+"		left JOIN "+t.getTenantId()+".fsls b ON a.slsno = b.slsno								"
							+"	) b on a.custno=b.custno 									";
			if (optSls.equalsIgnoreCase("0")) {
				if (optOutletStatus.equalsIgnoreCase("1")) {
					sqlString += "	where a.flagout in('O','C')	";	
				}						
			} 
			
			else if (optSls.equalsIgnoreCase("1")) {
				sqlString += "	where b.slsno IN (:listSalesman)			";
				if (optOutletStatus.equalsIgnoreCase("1")) {
					sqlString += "	and a.flagout in('O','C')	";	
				}				
			}
		
		String home = System.getProperty("user.home");
		File extract = new File(home + nameFolder);
		try {
			extract.mkdir();
		} catch (Exception e) {
			hasil = 0;
		}
		
		try {
			Query query = entityManager.createNativeQuery(sqlString);
			if (optSls.equalsIgnoreCase("1")) {
				query.setParameter("listSalesman", listSalesman);
			}
			List<Object[]> resultList = query.getResultList();
			
			fileWriter = new BufferedWriter(new FileWriter(home + nameFolder + "/" + fullFileName));
			
			String line = "No Urut|No Outlet|Kode Sales|Nama Sales|No|Senin|Selasa|Rabu|Kamis|Jumat|Sabtu|Minggu|Pola Kunjungan|Rute";
			fileWriter.write(line);
			
			for (Object[] o : resultList) {
				String data = Objects.toString(o[0], "")+ "|" +Objects.toString(o[1], "") + "|" +
								Objects.toString(o[2], "")+ "|" +Objects.toString(o[3], "") + "|" +
								Objects.toString(o[4], "")+ "|" +Objects.toString(o[5], "") + "|" +
								Objects.toString(o[6], "")+ "|" +Objects.toString(o[7], "") + "|" +
								Objects.toString(o[8], "")+ "|" +Objects.toString(o[9], "") + "|" +
								Objects.toString(o[10], "")+ "|" +Objects.toString(o[11], "") + "|" +
								Objects.toString(o[12], "")+ "|" +Objects.toString(o[13], "")  ;
				fileWriter.newLine();
				fileWriter.write(data);		
			}		
			fileWriter.close();		
		} catch (Exception e) {
			hasil = 0;
		}
		return hasil;
	}
	
	@Override
	public int getEkstrakDataOutlet(String userId, String fullFileName, String optSls, String optOutletStatus, List<String> listSalesman) {
		int hasil = 1;
		String line = "";
		String sqlString = 	"	select a.* 									";
			if (optSls.equalsIgnoreCase("1")) {
				sqlString += "	,b.CUSTNO as CUSTNOSLS,b.SLSNO,b.NOBRS, 									"
							+"	   case when b.HSENIN='T' then 'N' else b.HSENIN end as HSENIN, 									"
							+"	   case when b.HSELASA='T' then 'N' else b.HSELASA end as HSELASA,									"
							+"	   case when b.HRABU='T' then 'N' else b.HRABU end as HRABU,									"
							+"	   case when b.HKAMIS='T' then 'N' else b.HKAMIS end as HKAMIS,									"
							+"	   case when b.HJUMAT='T' then 'N' else b.HJUMAT end as HJUMAT, 									"
							+"	   case when b.HSABTU='T' then 'N' else b.HSABTU end as HSABTU, 									"
							+"	   case when b.HMINGGU='T' then 'N' else b.HMINGGU end as HMINGGU,  									"
							+"	   case when b.visit1||b.visit2||b.visit3||b.visit4 ='YYYY' then 'Weekly'  									"
							+"				   when  b.visit1||b.visit2||b.visit3||b.visit4 ='YTYT' then 'BeWeekly 1'  						"
							+"				   when  b.visit1||b.visit2||b.visit3||b.visit4 ='TYTY' then 'BeWeekly 2'  						"
							+"				   when  b.visit1||b.visit2||b.visit3||b.visit4 ='YTTT' then 'Monthly 1'  						"
							+"				   when  b.visit1||b.visit2||b.visit3||b.visit4 ='TYTT' then 'Monthly 2'  						"
							+"				   when  b.visit1||b.visit2||b.visit3||b.visit4 ='TTYT' then 'Monthly 3'  						"
							+"				   when  b.visit1||b.visit2||b.visit3||b.visit4 ='TTTY' then 'Monthly 4' 						"
							+"				   end as pola ,b.ROUTE 						";
			}
				sqlString += "	from 									"
							+"	(									"
							+"		SELECT DISTINCT a.custno, a.custname, a.custadd1, a.custadd2, a.ccity, 								"
							+"			a.ckdpos, a.ccontact, a.cphone1, a.cfaxno, a.cresd1, a.cresd2,              							"
							+"			a.ccity2, a.cphone2, a.cterm, a.climit, case when a.flaglimit='T' then 'N' else a.flaglimit end as flaglimit,                           							"
							+"			a.flagpay, a.flagout, case when a.flagkbon='T' then 'N' else a.flagkbon end as flagkbon, a.flaghome, a.gdisc,                         							"
							+"			a.LOCATION, a.distrik, a.typeout, a.grupout, a.gharga, a.gplu,                							"
							+"			a.gkonv, a.beat1, a.sbeat, a.CLASS, a.kdpsr, a.kdind,                         							"
							+"			a.cweekno, a.clastdate, a.firstdate, a.firstopen, a.regdate,                  							"
							+"			a.taxname, a.taxadd1, a.taxadd2, a.taxcity, a.taxflag, a.npwp,                							"
							+"			a.rata, a.bank, a.noacc, a.norefsup, a.agenlain, a.data01,                    							"
							+"			a.data02, a.data03, a.data04, a.data05, a.data06, a.data07,                   							"
							+"			a.data08, a.data09, a.data10, a.beat2, a.data12, a.data13,                    							"
							+"			case when a.data14='T' then 'N' else a.data14 end as data14, a.data15, a.data16, a.data17, a.data18, b.outtype, a.h11,           							"
							+"			a.provinsi, a.h12, a.kabupaten, a.h13, a.kecamatan, a.h14,                    							"
							+"			a.kelurahan, b.groupdivisi,b.typecost,b.cost,b.TF,b.bykrt,b.grouppayer,b.chiller 							"
							+"			,b.noktp  							"
							+"		FROM 								"
							+"		(								"
							+"			SELECT custno, custname, custadd1, custadd2, ccity, ckdpos,                  							"
							+"				 ccontact, cphone1, cfaxno, cresd1, cresd2, ccity2,                    						"
							+"				 cphone2, cterm, climit, flaglimit, flagpay, flagout,                  						"
							+"				 flagkbon, flaghome, gdisc, LOCATION, distrik, typeout,                						"
							+"				 grupout, gharga, gplu, gkonv, beat1, sbeat, CLASS,                    						"
							+"				 kdpsr, kdind, cweekno,                                                						"
							+"				 TO_CHAR (clastdate, 'ddMMyyyy') AS clastdate,                         						"
							+"				 TO_CHAR (firstdate, 'ddMMyyyy') AS firstdate,                         						"
							+"				 TO_CHAR (firstopen, 'ddMMyyyy') AS firstopen,                         						"
							+"				 TO_CHAR (regdate, 'ddMMyyyy') AS regdate, taxname,                    						"
							+"				 taxadd1, taxadd2, taxcity, taxflag, npwp, rata, bank,                 						"
							+"				 noacc, norefsup, agenlain, data01, data02, data03,                    						"
							+"				 data04, data05, data06, data07, data08,                               						"
							+"				 TO_CHAR (data09, 'ddMMyyyy') AS data09, data10, beat2,                						"
							+"				 data12, data13, data14, data15, data16, data17,                       						"
							+"				 data18, h11, provinsi, h12, kabupaten, h13, kecamatan,                						"
							+"				 h14, kelurahan                                                        						"
							+"			FROM 							"
							+"			(							"
							+"				SELECT a.custno, a.custname, a.custadd1, a.custadd2,                 						"
							+"					 a.ccity, a.ckdpos, a.ccontact, a.cphone1,                     					"
							+"					 a.cfaxno, a.cresd1, a.cresd2, a.ccity2,                       					"
							+"					 a.cphone2, a.cterm, a.climit, a.flaglimit,                    					"
							+"					 a.flagpay, a.flagout, a.flagkbon, a.flaghome,                 					"
							+"					 a.gdisc, a.LOCATION, a.distrik, a.typeout,                    					"
							+"					 a.grupout, a.gharga, a.gplu, a.gkonv,                         					"
							+"					 a.beat AS beat1, a.sbeat, a.CLASS, a.kdpsr,                   					"
							+"					 a.kdind, a.cweekno, a.clastdate, a.firstdate,                 					"
							+"					 a.firstopen, a.regdate, a.taxname, a.taxadd1,                					"
							+"					 a.taxadd2, a.taxcity, a.taxflag, a.npwp,                      					"
							+"					 a.rata, a.bank, a.noacc, a.norefsup,                          					"
							+"					 a.agenlain, a.data01, a.data02, a.data03,                     					"
							+"					 a.data04, a.data05, a.data06, a.data07,                       					"
							+"					 a.data08, a.data09, a.data10, a.beat AS beat2,                					"
							+"					 a.data12, a.data13, a.data14, a.data15,                       					"
							+"					 a.data16, a.data17, a.data18, b.h11,                          					"
							+"					 b.provinsi, b.h12, b.kabupaten, b.h13,                        					"
							+"					 b.kecamatan, b.h14, b.kelurahan                               					"
							+"				FROM "+t.getTenantId()+".fcustmst a                                           						"
							+"				LEFT JOIN                                                     						"
							+"				(						"
							+"					SELECT a.*                                                   					"
							+"					FROM 					"
							+"					(					"
							+"						SELECT a.custno, a.h11, a.h12, a.h13, a.h14, a.provinsi, a.kabupaten, a.kecamatan, b.ket AS kelurahan                            				"
							+"						FROM 				"
							+"						(				"
							+"							SELECT a.custno, a.h11, a.h12, a.h13, a.h14, a.provinsi, a.kabupaten, b.ket AS kecamatan                    			"
							+"							FROM 			"
							+"							(			"
							+"								SELECT a.custno, a.h11, a.h12, a.h13, a.h14, a.provinsi, b.ket AS kabupaten 		"
							+"								FROM 		"
							+"								(		"
							+"									SELECT a.custno, a.h11, a.h12, a.h13, a.h14, b.ket AS provinsi        	"
							+"									FROM "+t.getTenantId()+".fcustud a 	"
							+"									LEFT JOIN "+t.getTenantId()+".fcshir11 b ON a.h11 = t11              	"
							+"								) a                   		"
							+"								LEFT JOIN "+t.getTenantId()+".fcshir12 b ON a.h11 =  b.t11 AND a.h12 = b.t12                		"
							+"							) a                           			"
							+"							LEFT JOIN "+t.getTenantId()+".fcshir13 b ON a.h11 = b.t11 AND a.h12 = b.t12 AND a.h13 = b.t13                       			"
							+"						) a                                   				"
							+"						LEFT JOIN "+t.getTenantId()+".fcshir14 b ON a.h11 = b.t11 AND a.h12 = b.t12 AND a.h13 = b.t13 AND a.h14 = b.t14                               				"
							+"					) a					"
							+"				) b ON a.custno = b.custno                 						"
							+"			)							"
							+"		) a                                                          								"
							+"		LEFT JOIN                                                                    								"
							+"		(                                                                              								"
							+"			SELECT a.custno, b.short_code AS outtype, a.groupdivisi,a.typecost,a.cost,a.TF,    							"
							+"				case when a.bykrt='*' then 'Ya' else 'Tidak' end as bykrt,     						"
							+"				a.grouppayer,                                                                      						"
							+"				case when a.chiller is null then 'Tidak' else to_char(c.alasan) end as chiller     						"
							+"				, a.noktp  						"
							+"			FROM "+t.getTenantId()+".ftable48 a 							"
							+"			LEFT JOIN "+t.getTenantId()+".ftable49 b ON a.outtype = b.outtype                                                          							"
							+"			LEFT JOIN 							"
							+"			(							"
							+"				SELECT kdalasan,alasan from "+t.getTenantId()+".ftable01 where tipealasan = '13'						"
							+"			) c ON a.chiller = c.kdalasan    							"
							+"		) b ON a.custno = b.custno								"
							+"	) a									";
                  					
			if (optSls.equalsIgnoreCase("0")) {
				if (optOutletStatus.equalsIgnoreCase("1")) {
					sqlString += "	where a.flagout in('O','C')	";	
				}						
			} 
			
			else if (optSls.equalsIgnoreCase("1")) {
				sqlString += "	left join "+t.getTenantId()+".fcustsls b on a.custno=b.custno where b.slsno IN  (:listSalesman)			";
				if (optOutletStatus.equalsIgnoreCase("1")) {
					sqlString += "	and a.flagout in('O','C')	";	
				}				
			}
		
		String home = System.getProperty("user.home");
		File extract = new File(home + nameFolder);
		try {
			extract.mkdir();
		} catch (Exception e) {
			hasil = 0;
		}
		
		try {
			Query query = entityManager.createNativeQuery(sqlString);
			if (optSls.equalsIgnoreCase("1")) {
				query.setParameter("listSalesman", listSalesman);
			}
			List<Object[]> resultList = query.getResultList();
			
			fileWriter = new BufferedWriter(new FileWriter(home + nameFolder + "/" + fullFileName));
			
			
			line = "No Outlet|Nama Outlet|CUSTADD1|CUSTADD2|CITY1|POSTZIP|CONTACT|PHONE1|FAX|CRESD1|CRESD2|CITY2|PHONE2|CTERM|CLIMIT|FLAGLIMIT|FLAGPAY|FLAGOUT|FLAGBON|FLAGHOME|Grup Diskon|Lokasi|Distrik|Channel|Grup Outlet|Grup Harga|GPLU|GKONV|Rute1|Sub Rute|Kelas|Pasar|INDUSTRY|CWEEKNO|CLASTDATE|FIRSTDATE|FIRSTOPEN|REGDATE|TAXNAME|TAXADD1|TAXADD2|TAXCITY|TAXFLAG|NPWP|RATA|BANK|NOACC|NOREFSUP|Meng-ageni|DATA01|DATA02|DATA03|DATA04|DATA05|DATA06|DATA07|DATA08|DATA09|DATA10|Rute2|DATA12|DATA13|DATA14|DATA15|DATA16|DATA17|DATA18|OUTTYPE|KodePropinsi|Propinsi|KodeKabupaten|Kabupaten|KodeKecamatan|Kecamatan|KodeKelurahan|Kelurahan|Grup Divisi|Biaya Kuli|Biaya|TF|Per Karton|GROUPPAYER|Chiller|NO KTP";
			
			if (optSls.equalsIgnoreCase("1")) {
				line += "|No Outlet|Salesman|No Urut|Senin|Selasa|Rabu|Kamis|Jumat|Sabtu|Minggu|Pola Kunjungan|Rute";
			}
			
			fileWriter.write(line);
			
			for (Object[] o : resultList) {
				String data = Objects.toString(o[0], "")+ "|" +Objects.toString(o[1], "") + "|" + Objects.toString(o[2], "")+ "|" +Objects.toString(o[3], "") + "|" +
								Objects.toString(o[4], "")+ "|" +Objects.toString(o[5], "") + "|" + Objects.toString(o[6], "")+ "|" +Objects.toString(o[7], "") + "|" +
								Objects.toString(o[8], "")+ "|" +Objects.toString(o[9], "") + "|" + Objects.toString(o[10], "")+ "|" +Objects.toString(o[11], "") + "|" +
								Objects.toString(o[12], "")+ "|" +Objects.toString(o[13], "") + "|" + Objects.toString(o[14], "")+ "|" +Objects.toString(o[15], "") + "|" + 
								Objects.toString(o[16], "")+ "|" +Objects.toString(o[17], "") + "|" + Objects.toString(o[18], "")+ "|" +Objects.toString(o[19], "") + "|" + 
								Objects.toString(o[20], "")+ "|" +Objects.toString(o[21], "") + "|" + Objects.toString(o[22], "")+ "|" +Objects.toString(o[23], "") + "|" +
								Objects.toString(o[24], "")+ "|" +Objects.toString(o[25], "")+ "|" +Objects.toString(o[26], "")+ "|" +Objects.toString(o[27], "")+ "|" +
								Objects.toString(o[28], "")+ "|" +Objects.toString(o[29], "")+ "|" +Objects.toString(o[30], "")+ "|" +Objects.toString(o[31], "")+ "|" +
								Objects.toString(o[32], "")+ "|" +Objects.toString(o[33], "")+ "|" +Objects.toString(o[34], "")+ "|" +Objects.toString(o[35], "")+ "|" +
								Objects.toString(o[36], "")+ "|" +Objects.toString(o[37], "")+ "|" +Objects.toString(o[38], "")+ "|" +Objects.toString(o[39], "")+ "|" +
								Objects.toString(o[40], "")+ "|" +Objects.toString(o[41], "")+ "|" +Objects.toString(o[42], "")+ "|" +Objects.toString(o[43], "")+ "|" +
								Objects.toString(o[44], "")+ "|" +Objects.toString(o[45], "")+ "|" +Objects.toString(o[46], "")+ "|" +Objects.toString(o[47], "")+ "|" +
								Objects.toString(o[48], "")+ "|" +Objects.toString(o[49], "")+ "|" +Objects.toString(o[50], "")+ "|" +Objects.toString(o[51], "")+ "|" +
								Objects.toString(o[52], "")+ "|" +Objects.toString(o[53], "")+ "|" +Objects.toString(o[54], "")+ "|" +Objects.toString(o[55], "")+ "|" +
								Objects.toString(o[56], "")+ "|" +Objects.toString(o[57], "")+ "|" +Objects.toString(o[58], "")+ "|" +Objects.toString(o[59], "")+ "|" +
								Objects.toString(o[60], "")+ "|" +Objects.toString(o[61], "")+ "|" +Objects.toString(o[62], "")+ "|" +Objects.toString(o[63], "")+ "|" +
								Objects.toString(o[64], "")+ "|" +Objects.toString(o[65], "")+ "|" +Objects.toString(o[66], "")+ "|" +Objects.toString(o[67], "")+ "|" +
								Objects.toString(o[68], "")+ "|" +Objects.toString(o[69], "")+ "|" +Objects.toString(o[70], "")+ "|" +Objects.toString(o[71], "")+ "|" +
								Objects.toString(o[72], "")+ "|" +Objects.toString(o[73], "")+ "|" +Objects.toString(o[74], "")+ "|" +Objects.toString(o[75], "")+ "|" +
								Objects.toString(o[76], "")+ "|" +Objects.toString(o[77], "")+ "|" +Objects.toString(o[78], "")+ "|" +Objects.toString(o[79], "")+ "|" +
								Objects.toString(o[80], "")+ "|" +Objects.toString(o[81], "")+ "|" +Objects.toString(o[82], "")+ "|" +Objects.toString(o[83], "");
								
				if (optSls.equalsIgnoreCase("1")) {
						data += "" + "|" +Objects.toString(o[84],"") + "|" +Objects.toString(o[85], "")+ "|" +Objects.toString(o[86], "")+ "|" +Objects.toString(o[87], "")+ "|" +Objects.toString(o[88], "")+ "|" +
								Objects.toString(o[89], "")+ "|" +Objects.toString(o[90], "")+ "|" +Objects.toString(o[91], "")+ "|" +Objects.toString(o[92], "")+ "|" +
								Objects.toString(o[93], "")+ "|" +Objects.toString(o[94], "")+ "|" +Objects.toString(o[95], "");
				}		

				fileWriter.newLine();
				fileWriter.write(data);		
			}		
			fileWriter.close();		
		} catch (Exception e) {
			e.printStackTrace();
			hasil = 0;
		}
		return hasil;
	}
	
}
