package myor.matrix.master.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.CopyFileDto;
import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.ProsesNooBrowseDto;
import myor.matrix.master.entity.ProsesNooDataProsesDto;
import myor.matrix.master.entity.ProsesNooDetailDto;
import myor.matrix.master.entity.ProsesNooDto;
import myor.matrix.master.entity.ProsesNooEditDto;
import myor.matrix.master.entity.ProsesNooFppDto;
import myor.matrix.master.entity.ProsesNooInputDto;
import myor.matrix.master.entity.ProsesNooMessageDto;
import myor.matrix.master.entity.ProsesNooRekapDto;
import myor.matrix.master.repository.ProsesNooRepository;
import myor.matrix.master.service.FileApiService;
import myor.matrix.master.service.MasterOutletService;
import myor.matrix.master.service.ProsesNooService;
import myor.matrix.master.service.UtilService;
import myor.matrix.master.tenant.TenantContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ProsesNooServiceImpl implements ProsesNooService {

	@Autowired
	private ProsesNooRepository repoProsesNoo;
	
	@Autowired
	private UtilService util;
	
	@Autowired
	private TenantContext t;
	
	@Autowired
	private MasterOutletService masterOutletService;
	
	@Autowired
	private FileApiService fileApiService;

	@Override
	public List<String> loadForm() {
		// TODO Auto-generated method stub
		List<String> datas = new ArrayList<>();
		String tglGudang = repoProsesNoo.getXkeyString("FMEMO", "TO_CHAR(MEMODATE,'DD MON YYYY')", "WHERE MEMONAMA='CADATE'");
		datas.add(tglGudang);
		String sysDate =  repoProsesNoo.getFromDualString("DUAL","TO_CHAR (SYSDATE, 'DD MON YYYY') AS tanggal", " ");
		datas.add(sysDate);
		return datas;
	}

	@Override
	public List<ProsesNooBrowseDto> getBrowseDocument() {
		// TODO Auto-generated method stub
		return repoProsesNoo.getBrowseDocument();
	}
	
	@Override
	public List<ProsesNooBrowseDto> getBrowseDataDocument() {
		// TODO Auto-generated method stub
		return repoProsesNoo.getBrowseDataDocument();
	}

	@Override
	public ProsesNooDto getLoadData(String docNo) {
		// TODO Auto-generated method stub
		String param = "";
		Boolean paramDelete;
		ProsesNooDto result = new ProsesNooDto();

		if (docNo.length() != 0) {
			List<Object[]> belumprintfppdanblmproses = repoProsesNoo.getXkeyListObject("ftable142_h", "*",
					" where docno ='"+docNo+"' and xkey is null and fpp is null ");
			if (belumprintfppdanblmproses.size() != 0) {
				param = "belumprintfppdanblmproses";
			}
			
			List<Object[]> sudahprintfppdanblmgeneratekey = repoProsesNoo.getXkeyListObject("ftable142_h", "*",
					" where docno ='"+docNo+"' and xkey is null and fpp='Y' ");
			if (sudahprintfppdanblmgeneratekey.size() != 0) {
				param = "sudahprintfppdanblmgeneratekey";
			}
			
			List<Object[]> sudahprintfppdansudahdiberistatus = repoProsesNoo.getXkeyListObjectJoin("ftable142_h a ", "docno, seq_no",
					" join "+t.getTenantId()+".ftable142_d b on a.docno=b.docno join "+t.getTenantId()+".ftable141 c on b.DOCNO_NOO=c.SEQ_NO",
					" where fpp='Y' and approve is not null and a.docno='"+docNo+"' and xkey is null ");
			if (sudahprintfppdansudahdiberistatus.size() != 0) {
				param = "sudahprintfppdansudahdiberistatus";
			}
			
			List<Object[]> sudahprintrekap = repoProsesNoo.getXkeyListObject("ftable142_h", "*",
					"where docno ='"+docNo+"' and xkey is not null");
			if (sudahprintrekap.size() != 0) {
				param = "sudahprintrekap";
			}
			
			List<Object[]> cekDelete = repoProsesNoo.getXkeyListObject("ftable142_h", "*",
					"where docno ='"+docNo+"' and fpp='Y' ");
			if (cekDelete.size() != 0) {
				paramDelete = true;
			} else {
				paramDelete = false;
			}
			
			List<ProsesNooDetailDto> dataDetail = repoProsesNoo.getDetailNoo(docNo);
			result = new ProsesNooDto(param, paramDelete, dataDetail);
		}
		
		return result;
	}

	@Override
	@Transactional
	public MessageDto addDocument(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		String cekPROSESNOO = repoProsesNoo.getXkeyString("ftable13", "memostring", "WHERE xkey = 'PROSESNOO'");
		if (cekPROSESNOO.length() == 0) {
			message = new MessageDto("warn", "Warning Message", "No. Dokumen belum disetting!");
		} else {
			String docno = util.getSequence("seq_prosesnoo", "docno", "ftable142_h");
			repoProsesNoo.updateData("ftable13", "SET memostring='"+docno+"'", "WHERE xkey='PROSESNOO'");
			repoProsesNoo.insertData("ftable142_h", "docno,date_created,user_created",
					" '"+docno+"', '"+p.getDocDate()+"', '"+p.getXuser()+"' ");
			
			message = new MessageDto("success", "Success Message", docno);
		}
		
		return message;
	}
	
	@Override
	@Transactional
	public MessageDto addData(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		
		List<Object[]> cekData =  repoProsesNoo.getXkeyListObject("ftable142_d", "*", "WHERE docno= '"+p.getDocNo()+"' AND docno_noo='"+p.getDocNoData()+"' ");
		if (cekData.size() != 0) {
			message = new MessageDto("warn", "Warning Message", "Data "+p.getDocNoData()+" sudah ada!");
		} else {
			repoProsesNoo.insertData("ftable142_d", "docno,docno_noo", "'"+p.getDocNo()+"', '"+p.getDocNoData()+"'");
			repoProsesNoo.updateData("ftable142_h", "set xkey='',keyauthentication=''", "WHERE docno ='"+p.getDocNo()+"' ");
			message = new MessageDto("success", "Success Message", "Data "+p.getDocNoData()+" berhasil ditambahkan!");
		}
		return message;
	}

	@Override
	@Transactional
	public MessageDto cancelDocument(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		List<Object[]> cekFPP = repoProsesNoo.getXkeyListObject("ftable142_h", "*", "where docno='"+p.getDocNo()+"' and fpp='Y'");
		if (cekFPP.size() != 0) {
			message = new MessageDto("warn", "Warning Message", "Dokumen "+p.getDocNo()+" sudah print FPP, tidak bisa dihapus!");
		} else {
			repoProsesNoo.deleteData("ftable142_h", "WHERE docno='"+p.getDocNo()+"'");
			repoProsesNoo.deleteData("ftable142_d", "WHERE docno='"+p.getDocNo()+"'");
			message = new MessageDto("success", "Success Message", "Berhasil Cancel Dokumen "+p.getDocNo()+"!");
		}
		
		return message;
	}

	@Override
	@Transactional
	public MessageDto saveDocument(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		List<Object[]> cekDetail = repoProsesNoo.getXkeyListObject("ftable142_d", "*", "where docno='"+p.getDocNo()+"'");
		if (cekDetail.size() == 0) {
			message = new MessageDto("warn", "Warning Message", "Dokumen "+p.getDocNo()+" Detail tidak ada, Silahkan input detail atau batalkan dokumen!");
		} else {
			String groupDivisi = "ALL";
			repoProsesNoo.updateData("ftable142_h", "SET groupdivisi='"+groupDivisi+"' ",  "where docno='"+p.getDocNo()+"'");
			message = new MessageDto("success", "Success Message", "Berhasil Save Dokumen "+p.getDocNo()+"!");
		}
		return message;
	}
	
	@Override
	@Transactional
	public MessageDto bersihDocument(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
//		List<Object[]> cekDetail = repoProsesNoo.getXkeyListObject("ftable142_d", "*", "where docno='"+p.getDocNo()+"'");
//		if (cekDetail.size() == 0) {
//			message = new MessageDto("warn", "Warning Message", "Dokumen "+p.getDocNo()+" Detail tidak ada, Silahkan input detail atau batalkan dokumen!");
//		} else {
//			String groupDivisi = "ALL";
		repoProsesNoo.deleteData("ftable142_d", "WHERE docno='"+p.getDocNo()+"'");
			message = new MessageDto("success", "Success Message", "Berhasil membersihkan detail Dokumen "+p.getDocNo()+"!");
//		}
		return message;
	}

	@Override
	@Transactional
	public MessageDto deleteDocument(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
//		List<Object[]> cekFPP = repoProsesNoo.getXkeyListObject("ftable142_h", "*", "where docno='"+p.getDocNo()+"' and fpp='Y'");
//		if (cekFPP.size() != 0) {
//			message = new MessageDto("warn", "Warning Message", "Dokumen "+p.getDocNo()+" sudah print FPP, tidak bisa dihapus!");
//		} else {
			repoProsesNoo.deleteData("ftable142_h", "WHERE docno='"+p.getDocNo()+"'");
			repoProsesNoo.deleteData("ftable142_d", "WHERE docno='"+p.getDocNo()+"'");
			message = new MessageDto("success", "Success Message", "Berhasil Delete Dokumen "+p.getDocNo()+"!");
//		}
		
		return message;
	}

	@Override
	@Transactional
	public MessageDto loadDataDocument(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		repoProsesNoo.deleteData("ftable142_d", "WHERE docno='"+p.getDocNo()+"'");
		String query = repoProsesNoo.getQueryCekData(p);
		List<Object[]> cekData = repoProsesNoo.getListObjectQuery(query);
		if (cekData.size() == 0) {
			message = new MessageDto("warn", "Warning Message", "Data tidak ada!");
		} else {
			repoProsesNoo.insertDataSelect("ftable142_d",
					" SELECT '"+p.getDocNo()+"' docno, a.docno docno_noo FROM ("+query+") a ", "");
			repoProsesNoo.updateData("ftable142_h", "set xkey='',keyauthentication=''", "WHERE docno ='"+p.getDocNo()+"' ");
			message = new MessageDto("success", "Success Message", "Success Load Data!");
		}
		
		return message;
	}

	@Override
	@Transactional
	public MessageDto deleteDataDocument(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		
		repoProsesNoo.deleteData("ftable142_d", "WHERE docno='"+p.getDocNo()+"' AND docno_noo='"+p.getDocNoData()+"' ");
		repoProsesNoo.updateData("ftable142_h", "set xkey='',keyauthentication=''", "WHERE docno ='"+p.getDocNo()+"' ");
		message = new MessageDto("success", "Success Message", "Success delete Data "+p.getDocNoData()+"!");
		
		return message;
	}

	@Override
	@Transactional
	public MessageDto onPrintFPP(ProsesNooInputDto p, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		String hasil = "";
		List<Object[]> cekDetail = repoProsesNoo.getXkeyListObject("ftable142_d", "*", "WHERE docno='"+p.getDocNo()+"'");
		if (cekDetail.size() == 0) {
			message = new MessageDto("warn", "Warning Message", "Detail tidak ada, Silahkan input detail atau batalkan dokumen!");
			return message;
		}
		String path = repoProsesNoo.getXkeyString("ftable13", "memostring", "WHERE xkey='XPICTURENOO'");
		if (path.length() == 0) {
			message = new MessageDto("warn", "Warning Message", "Folder Foto Belum Di Mapping!");
			return message;
		}
		
		List<ProsesNooFppDto> result = new ArrayList<>();
		
		result = repoProsesNoo.getDataPrintFpp(p.getDocNo(), path);
		
		if (result.size() == 0) {
			message = new MessageDto("warn", "Warning Message", "Data Tidak ada");
			return message;
		} else {
			Resource resource = new ClassPathResource("prosesNoo/fpp.jrxml");
			InputStream inStream = resource.getInputStream();
			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);
			jasperPrint.setProperty("net.sf.jasperreports.export.pdf.permissions.denied",
					"ASSEMBLY|COPY|FILL_IN|MODIFY_ANNOTATIONS|MODIFY_CONTENTS|SCREENREADERS|DEGRADED_PRINTING|PRINTING");
			try {
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
				byte[] pdf = baos.toByteArray();
				hasil = Base64.getEncoder().encodeToString(pdf);
				
//				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
//				hasil = Base64.getEncoder().encodeToString(pdf);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		repoProsesNoo.updateData("ftable142_h", "set fpp='Y', groupdivisi='ALL'", "where docno='"+p.getDocNo()+"'");
		message = new MessageDto("success", "Success Message", hasil);
		return message;
	}

	@Override
	@Transactional
	public MessageDto keyRequest(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		String groupdivisi = "ALL";
		String keyRequest = "";
		String keyAuth = "";
		
		List<Object[]> cekEditDocno = repoProsesNoo.getXkeyListObjectJoin("ftable142_h a", "docno, seq_no",
				" join "+t.getTenantId()+".ftable142_d b on a.docno=b.docno join "+t.getTenantId()+".ftable141 c on b.DOCNO_NOO=c.SEQ_NO ",
				" where (fpp is null or approve is null) and a.docno='"+p.getDocNo()+"' ");
		if (cekEditDocno.size() != 0) {
			message = new MessageDto("warn", "Warning Message", "Ada docno yang belum diedit!");
			return message;
		}

		String xjam =  repoProsesNoo.getFromDualString("DUAL","TO_CHAR (SYSDATE, 'HH24:MI:SS') AS jam", "");
		List<String> dataKey = util.getXkeyAndAuth(8, 56, xjam);
		keyRequest = dataKey.get(0);
		keyAuth = dataKey.get(1);
		
		repoProsesNoo.updateData("ftable142_h", "SET XKEY ='"+keyRequest+"', keyauthentication = '"+keyAuth+"', groupdivisi='"+groupdivisi+"' ",
				"WHERE docno = '"+p.getDocNo()+"' ");
		
		message = new MessageDto("success", "Success Message", keyRequest);
		return message;
	}

	@Override
	@Transactional
	public MessageDto onPrintRekap(ProsesNooInputDto p, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		String hasil = "";
		List<ProsesNooRekapDto> result = new ArrayList<>();
		
		result = repoProsesNoo.getDataPrintRekap(p.getDocNo(), p.getDocDate(), p.getReqKey());
		
		if (result.size() == 0) {
			message = new MessageDto("warn", "Warning Message", "Data Tidak ada");
			return message;
		} else {
			Resource resource = new ClassPathResource("prosesNoo/rekap.jrxml");
			InputStream inStream = resource.getInputStream();
			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);
			jasperPrint.setProperty("net.sf.jasperreports.export.pdf.permissions.denied",
					"ASSEMBLY|COPY|FILL_IN|MODIFY_ANNOTATIONS|MODIFY_CONTENTS|SCREENREADERS|DEGRADED_PRINTING|PRINTING");
			try {
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				hasil = Base64.getEncoder().encodeToString(pdf);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		repoProsesNoo.updateData("ftable142_h", "set rekap='Y', groupdivisi='ALL'", "where docno='"+p.getDocNo()+"'");
		message = new MessageDto("success", "Success Message", hasil);
		return message;
	}

	@Override
	@Transactional
	public ProsesNooMessageDto onProcessDocument(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		ProsesNooMessageDto hasil = new ProsesNooMessageDto(); 
		MessageDto message = new MessageDto();
		List<String> messageList = new ArrayList<>();
		List<Object[]> cekRekap = repoProsesNoo.getXkeyListObject("ftable142_h", "*",
				"where docno='"+p.getDocNo()+"' and rekap='Y'");
		if (cekRekap.size() == 0) {
			message = new MessageDto("warn", "Warn Message", "Dokumen Belum print rekap!");
			hasil = new ProsesNooMessageDto(message, messageList, "1", null);
			return hasil;
		}
		String tglGudang = repoProsesNoo.getXkeyString("FMEMO", "TO_CHAR(MEMODATE,'DD MON YYYY')", "WHERE MEMONAMA='CADATE'");
		List<String> valKey = util.cekValAndKey38(8, 56, p.getReqKey(), p.getAuthKey());
		String edtRas = valKey.get(0);
		if (valKey.get(1).equalsIgnoreCase("NOTOK")) {
			message = new MessageDto("warn", "Warn Message", "Nomor Key Auth tidak benar!");
			hasil = new ProsesNooMessageDto(message, messageList, "1", null);
			return hasil;
		} else {
			String Xflagsubdist = repoProsesNoo.getXkeyString("fmemo", "MEMOSTRING", "WHERE MEMONAMA='TIPEDIST'");
			String pekan = repoProsesNoo.getXkeyString("fmemo", "memoint", "WHERE MEMONAMA='PEKAN'");
			String groupDivisi = "ALL";
			List<ProsesNooDataProsesDto> datas = repoProsesNoo.getDataProcess(p.getDocNo());
			for (ProsesNooDataProsesDto dt : datas) {
				
				if (!dt.getApprove().equalsIgnoreCase("Y")) {
					repoProsesNoo.updateData("ftable141", "SET STATUS = '2'", "WHERE DOCNO = '"+dt.getDocno()+"'");
					messageList.add("No Dokumen : " + dt.getSeqNo() + ", DITOLAK");
				} else {
					String[] cekCustNo = masterOutletService.getSuggestNoOutlet(groupDivisi);
					if (!cekCustNo[0].equalsIgnoreCase("info")) {
						message = new MessageDto(cekCustNo[0], cekCustNo[1], cekCustNo[2]);
						hasil = new ProsesNooMessageDto(message, messageList, "1", null);
						return hasil;
					} 
					
					String custno = cekCustNo[3];
					String slsno = dt.getSlsno();
					String docno = dt.getDocno();
					String tipepajak = "S";
					
					String NPWP = repoProsesNoo.getFromDualString("DUAL", "REPLACE ('"+dt.getNpwp()+"', '|', '') npwp", "");;
					System.out.println("Panjang "+NPWP.length());
					if (NPWP.length() == 20) {
						NPWP = NPWP;
					} else if (NPWP.length() == 15){
						NPWP = NPWP.substring(0, 2)+"."+NPWP.substring(2, 5)+"."+NPWP.substring(5, 8)+"."+NPWP.substring(8, 9)+"-"+NPWP.substring(9, 12)+"."+NPWP.substring(12, 15);
					} else {
						NPWP = "00.000.000.0-000.000";
					}
					System.out.println(NPWP);
					String param  = "";
					if (Xflagsubdist.equalsIgnoreCase("GT")) {
						param = "100";
					} else {
						param = "200";
					}
					repoProsesNoo.insertData("fcustmst",
							" custno, custname, custadd1, custadd2, cphone1, data02, data03, data04, " //column
							+ " flagout, ccontact, cterm, cweekno, flaglimit, data14, climit, flagpay, "
							+ " flagkbon, regdate, data19, location, data16, kdpsr, typeout, class, grupout, "
							+ " distrik, beat, sbeat, kdind, data15, data17, data18, gdisc, gharga, "
							+ " taxname, taxadd1, taxadd2, npwp, taxflag, ckdpos, taxcity, ccity, data10, flaghome",
							" '"+custno+"', replace('"+dt.getCustname()+"','|',''), replace('"+dt.getCustadd1()+"','|',''), " //value
							+ " replace('"+dt.getCustadd2()+"','|',''), replace('"+dt.getCphone1()+"','|',''), replace('"+dt.getShipadd1()+"','|',''), "
							+ " replace('"+dt.getShipadd2()+"','|',''), replace('"+dt.getShipadd3()+"','|',''), 'O', "
							+ " replace('"+dt.getCcontact()+"','|',''), replace('"+dt.getCterm()+"','|',''), '"+pekan+"', "
							+ " 'N', 'T', '"+dt.getClimit()+"', '"+dt.getFlagpay()+"', 'Y', '"+tglGudang+"', '"+p.getXuser()+"', "
							+ " '20', '04', '355231', '"+dt.getTypeout()+"', '"+dt.getClassS()+"', '"+dt.getGrupout()+"', "
							+ " '03', '03', '03', '"+dt.getKdind()+"', '99', '99', '99', '99', '"+param+"', "
							+ " replace('"+dt.getTaxname()+"','|',''), replace('"+dt.getTaxadd1()+"','|',''), replace('"+dt.getTaxadd2()+"','|',''), "
							+ " '"+NPWP+"', '"+tipepajak+"', '"+dt.getPostalCode()+"', '"+dt.getTaxCity()+"', '"+dt.getKabupaten()+"', "
							+ " '"+dt.getTitlePerson()+"', '"+dt.getFlaghome()+"' ");
					
					repoProsesNoo.insertData("fcustud",
							"custno, h11, h12, h13, h14",
							" '"+custno+"', '"+dt.getHh11()+"', '"+dt.getHh12()+"', '"+dt.getHh13()+"', '"+dt.getHh14()+"' ");
					
					repoProsesNoo.insertData("fcustsls",
							"custno, slsno, nobrs, hsenin, hselasa, hrabu, hkamis, hjumat, hsabtu, hminggu, visit1, visit2, visit3, visit4, route",
							" '"+custno+"', '"+dt.getSlsno()+"', '1', '"+dt.getHsenin()+"', '"+dt.getHselasa()+"', '"+dt.getHrabu()+"', "
							+ " '"+dt.getHkamis()+"', '"+dt.getHjumat()+"', '"+dt.getHsabtu()+"', '"+dt.getHminggu()+"', "
							+ " '"+dt.getVisit1()+"', '"+dt.getVisit2()+"', '"+dt.getVisit3()+"', '"+dt.getVisit4()+"', "
							+ " '"+dt.getRoute()+"' ");
					
					List<Object[]> cekSales =  repoProsesNoo.getXkeyListObject("fsls", "*",
							" where slsno = '"+slsno+"' and data04 in (select memostring from "+t.getTenantId()+".ftable13 where xkey = 'SLSFORCE_TF') ");
					if (cekSales.size() > 0) {
						repoProsesNoo.insertData("ftable48",
								"custno, outtype, groupdivisi, typecost, grouppayer,custname, latitude, longitude, chiller,tf, noktp ,flag_ktp,nama_ktp, alamat_ktp, alamat_ktp2, nama_pemilik",
								" '"+custno+"', '99', '"+groupDivisi+"', '1', '"+custno+"', replace('"+dt.getCustname()+"','|',''), "
								+ " '"+dt.getLatitude()+"', '"+dt.getLongitude()+"', '0', '*', '"+dt.getNoKtp()+"', '0', "
								+ " '"+dt.getNamaKtp()+"', '"+dt.getAlamatKtp1()+"', '"+dt.getAlamatKtp2()+"', "
								+ " '"+dt.getNamaPemilik()+"' ");
					} else {
						repoProsesNoo.insertData("ftable48",
								"custno, outtype, groupdivisi, typecost, grouppayer, custname, latitude, longitude, chiller, noktp, flag_ktp,nama_ktp, alamat_ktp, alamat_ktp2, nama_pemilik",
								" '"+custno+"', '99', '"+groupDivisi+"', '1', '"+custno+"', replace('"+dt.getCustname()+"','|',''), "
								+ " '"+dt.getLatitude()+"', '"+dt.getLongitude()+"', '0', '"+dt.getNoKtp()+"', '0', "
								+ " '"+dt.getNamaKtp()+"', '"+dt.getAlamatKtp1()+"', '"+dt.getAlamatKtp2()+"', "
								+ " '"+dt.getNamaPemilik()+"' ");
					}
					
					repoProsesNoo.insertData("ftabel64", "data1, data2, data3, data4, data5",
							"'100', '"+custno+"', '"+dt.getClimit()+"', '0', '0' ");
					String fotoktp = repoProsesNoo.getXkeyString("ftable141_PHOTO", "img_name", " WHERE docno='"+docno+"' and img_name like '%_"+slsno+"_KTP%' ");
					String fotonpwp = repoProsesNoo.getXkeyString("ftable141_PHOTO", "img_name", " WHERE docno='"+docno+"' and img_name like '%_"+slsno+"_NPWP%' ");
					String XPATH = repoProsesNoo.getXkeyString("ftable13", "memostring", " where xkey='XPHOTOKTP' ");
					String XPATHNOO = repoProsesNoo.getXkeyString("ftable13", "memostring", " where xkey='XPICTURENOO' "); 
					
					repoProsesNoo.insertData("FKONFIRMASI_KTP",
							"CUSTNO, GROUPPAYER, DOCDATE, SLSNO, FLAG_KTP, NAMA_PEMILIK, TLP, NIK, NAMA_KTP, ALAMAT_KTP, ALAMAT_KTP2, NPWP, NAMA_NPWP, ALAMAT_NPWP, ALAMAT_NPWP2, FOTO_KTP, FOTO_NPWP  ",
							" '"+custno+"', '"+custno+"', '"+tglGudang+"', '"+slsno+"', '0', '"+dt.getNamaPemilik()+"', replace('"+dt.getCphone1()+"','|',''), "
							+ " '"+dt.getNoKtp()+"', '"+dt.getNamaKtp()+"', '"+dt.getAlamatKtp1()+"', '"+dt.getAlamatKtp2()+"', "
							+ " '"+NPWP+"', '"+dt.getTaxname()+"', '"+dt.getTaxadd1()+"', '"+dt.getTaxadd2()+"', '"+fotoktp+"', '"+fotonpwp+"' ");
					
//					OPEN FEIGN
					List<CopyFileDto> filesCopy = new ArrayList<>();
					filesCopy.add(new CopyFileDto(XPATHNOO+"\\"+fotoktp, XPATH+"\\"+fotoktp));
					filesCopy.add(new CopyFileDto(XPATHNOO+"\\"+fotonpwp, XPATH+"\\"+fotonpwp));
					if (filesCopy.size() != 0) {
						MessageDto hasilApi = fileApiService.copyFile(t.getTokenAuth(), t.getTenantId(), filesCopy);
						System.err.println(hasilApi.getDetail());
					}
					
					String barcode1 = "";
					String barcode2 = "";
					String barcode3 = "";
					
					if (!dt.getNokartu1().equalsIgnoreCase("") && dt.getNokartu1().length() == 15) {
						String nokartu1 = dt.getNokartu1();
						String nokartu2 = dt.getNokartu2();
						String nokartu3 = dt.getNokartu3();
						barcode1 = util.generateBarcode(nokartu1);
						barcode2 = util.generateBarcode(nokartu2);
						barcode3 = util.generateBarcode(nokartu3);
						repoProsesNoo.insertData("ftable21", "custno, nokartu1, nobarcode1, nokartu2, nobarcode2, nokartu3, nobarcode3",
								" '"+custno+"', '"+nokartu1+"', '"+barcode1+"', '"+nokartu2+"', '"+barcode2+"', '"+nokartu3+"', '"+barcode3+"' ");
					} else {
						barcode1 = "";
					}
					
					repoProsesNoo.updateData("ftable141", "SET status='1',approvedate='"+tglGudang+"',nobarcode1='"+barcode1+"',custno='"+custno+"' ",
							" WHERE docno='"+docno+"' ");
					
					messageList.add("No Dokumen : " + dt.getSeqNo() +", No Outlet : "+custno+ ", DITERIMA");
				}
			}
			repoProsesNoo.updateData("ftable142_h", "SET date_process='"+tglGudang+"', user_process='"+p.getXuser()+"', userauthentication='"+edtRas+"' ", "WHERE docno='"+p.getDocNo()+"'");
		}
		
		message = new MessageDto("success", "Success Message", "Success Process "+p.getDocNo()+", Silahkan cek di table LOGS!");
		hasil = new ProsesNooMessageDto(message, messageList, "0", null);
		return hasil;
	}
	
	@Override
	public ProsesNooMessageDto onCekEdit(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		ProsesNooMessageDto hasil = new ProsesNooMessageDto(); 
		MessageDto message = new MessageDto();
		
		List<Object[]> cekData =  repoProsesNoo.getXkeyListObject("ftable142_d", "*", "WHERE docno = '"+p.getDocNo()+"' ");
		if (cekData.size() == 0) {
			message = new MessageDto("warn", "Warning Message", "Detail tidak ada, Silahkan input detail atau batalkan dokumen!");
			hasil = new ProsesNooMessageDto(message, null, "", null);
			return hasil;
		} 
		
		List<Object[]> diproses =  repoProsesNoo.getXkeyListObject("ftable142_h", "*", "WHERE user_process is not null and docno = '"+p.getDocNo()+"' ");
		if (diproses.size() != 0) {
			message = new MessageDto("warn", "Warning Message", "Dokumen sudah diproses!");
			hasil = new ProsesNooMessageDto(message, null, "", null);
			return hasil;
		}
		
		List<Object[]> fpp =  repoProsesNoo.getXkeyListObject("ftable142_h", "*", "WHERE fpp='Y' and docno = '"+p.getDocNo()+"' ");
		if (fpp.size() == 0) {
			message = new MessageDto("warn", "Warning Message", "Silakan print FPP terlebih dahulu!");
			hasil = new ProsesNooMessageDto(message, null, "", null);
			return hasil;
		}
		
		message = new MessageDto("success", "Success Message", "Success!");
		ProsesNooEditDto datas = repoProsesNoo.getDataFtable141(p.getDocNoData());
		hasil = new ProsesNooMessageDto(message, null, "", datas);
		return hasil;
	}

	@Override
	@Transactional
	public MessageDto onActionEdit(ProsesNooInputDto p) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		
		repoProsesNoo.updateData("ftable141",
				" set nokartu1='"+p.getNokartu1()+"', nokartu2='"+p.getNokartu2()+"', nokartu3='"+p.getNokartu3()+"', "
				+ " typeout='"+p.getChannel()+"',class='"+p.getClassS()+"',grupout='"+p.getGroupOutlet()+"',kdind='"+p.getOutletChain()+"', "
				+ " cterm='"+p.getTop()+"',climit='"+p.getLimitKredit()+"', flagpay='"+p.getFlagPay()+"', hsenin='"+p.gethSenin()+"', hselasa='"+p.gethSelasa()+"', "
				+ " hrabu='"+p.gethRabu()+"', hkamis='"+p.gethKamis()+"', hjumat='"+p.gethJumat()+"', hsabtu='"+p.gethSabtu()+"', hminggu='N', "
				+ " visit1='"+p.getVisit1()+"', visit2='"+p.getVisit2()+"', visit3='"+p.getVisit3()+"', visit4='"+p.getVisit4()+"', "
				+ " route='"+p.getRoute()+"', approve='"+p.getAprrove()+"', slsno='"+p.getSlsNo()+"' ",
				" WHERE seq_no='"+p.getDocNoOutlet()+"'");
		
		message = new MessageDto("success", "Success Message", "Success Edit Document "+p.getDocNoOutlet());
		return message;
	}
	
}
