package myor.matrix.master.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifCoverDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifDetailDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifExcelDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifHeaderDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifPrintDto;
import myor.matrix.master.repository.UploadOutletAktifNonAktifRepository;
import myor.matrix.master.repository.UtilRepository;
import myor.matrix.master.service.UploadOutletAktifNonAktifService;
import myor.matrix.master.tenant.TenantContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class UploadOutletAktifNonAktifServiceImpl implements UploadOutletAktifNonAktifService {

	@Autowired
	private UploadOutletAktifNonAktifRepository repoOutlet;
	
	@Autowired
	private UtilRepository repoUtilRepository;
	
	@Autowired
	private TenantContext t;
	
	@Override
	public List<String> loadForm() {
		// TODO Auto-generated method stub
		List<String> datas = new ArrayList<>();
		String tglGudang = repoUtilRepository.getTableString("FMEMO", "TO_CHAR(MEMODATE,'DD MON YYYY')", "WHERE MEMONAMA='CADATE'");
		datas.add(tglGudang);
		String sysDate =  repoUtilRepository.getDualString("DUAL","TO_CHAR (SYSDATE, 'DD MON YYYY') AS tanggal", " ");
		datas.add(sysDate);
		
		return datas;
	}

	@Override
	public List<UploadOutletAktifNonAktifHeaderDto> getBrowseDocument() {
		// TODO Auto-generated method stub
		return repoOutlet.getBrowseDocument();
	}

	@Override
	public UploadOutletAktifNonAktifDto getDatas(String docNo) {
		// TODO Auto-generated method stub
		UploadOutletAktifNonAktifDto result = new UploadOutletAktifNonAktifDto();
		
		UploadOutletAktifNonAktifHeaderDto header = repoOutlet.getHeader(docNo);
		List<UploadOutletAktifNonAktifDetailDto> detail = repoOutlet.getDetail(docNo);
		List<UploadOutletAktifNonAktifCoverDto> cover = repoOutlet.getCover(docNo);
		
		result = new UploadOutletAktifNonAktifDto(header, detail, cover);
		
		return result;
	}

	@Override
	@Transactional
	public MessageDto deleteDocument(String docNo) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		
		repoUtilRepository.deleteData("FTABLE51_H", "where docno='"+docNo+"'");
		repoUtilRepository.deleteData("FTABLE51_D", "where docno='"+docNo+"'");
		repoUtilRepository.deleteData("FTABLE51_R", "where docno='"+docNo+"'");
		
		message =  new MessageDto("success", "Success Message", "Delete Dokumen " + docNo +" berhasil!");
		return message;
	}

	@Override
	public MessageDto validasiOutlet(String custNo, String docDate, String action) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		List<Object[]> cekData = new ArrayList<>();
		
		cekData = repoUtilRepository.getTableListObjectJoin("FTABLE51_H a", "a.docno, a.docno as a", 
				"LEFT JOIN "+t.getTenantId()+".FTABLE51_D b ON a.docno = b.docno", "WHERE CUSTNO = '"+custNo+"' AND USERAUTHENTICATION is null");
		if (cekData.size() != 0) {
			for (Object[] cD : cekData) {
				message =  new MessageDto("warn", "Warning Message", "Outlet "+custNo+" sudah ada di no dokumen "+Objects.toString(cD[0], "")+"!");
			}
			return message;
		}
		if (action.equalsIgnoreCase("N")) {
			cekData = repoOutlet.valOutletOutstanding(custNo);
			if (cekData.size() != 0) {
				message =  new MessageDto("warn", "Warning Message", "Outlet "+custNo+" tidak dapat di daftarkan karena ada piutang outstanding!");
				return message;
			}
			
			String periode = repoUtilRepository.getTableString("FMEMO", "MEMOINT", "WHERE MEMONAMA='PERIODE'");
			String tahun = repoUtilRepository.getTableString("FMEMO", "MEMOINT", "WHERE MEMONAMA='TAHUN'");
			
			cekData = repoOutlet.valOutletTransaksi(custNo, docDate, periode, tahun);
			if (cekData.size() != 0) {
				message =  new MessageDto("warn", "Warning Message", "Outlet "+custNo+" tidak dapat di daftarkan karena ada transaksi di periode perjalan!");
				return message;
			}
		}
		
		message =  new MessageDto("success", "Success Message", "Cek Validasi Outlet " + custNo +" berhasil!");
		return message;
	}

	@Override
	@Transactional
	public MessageDto processDocument(String docNo, String act, String pass, String ras, String xuser) {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		String xact;
		
		if (act.equalsIgnoreCase("A")) {
			xact = "C";
		} else {
			xact = "N";
		}
		
		String tglGudang = repoUtilRepository.getTableString("FMEMO", "TO_CHAR(MEMODATE,'DD MON YYYY')", "WHERE MEMONAMA='CADATE'");

		repoUtilRepository.updateData("FTABLE51_H",
				" set KEYAUTHENTICATION = '"+pass+"', USERAUTHENTICATION = '"+ras+"', DATEPROSES= '"+tglGudang+"' ",
				" WHERE docno = '"+docNo+"' ");
		if (act.equalsIgnoreCase("C")) {
			List<UploadOutletAktifNonAktifDetailDto> dataDetail = repoOutlet.getDetail(docNo);
			if (dataDetail.size() > 0) {
				for (UploadOutletAktifNonAktifDetailDto dT : dataDetail) {
					repoUtilRepository.updateData("FCUSTMST",
							" SET FLAGOUT= '"+xact+"', CLASTUPD='"+tglGudang+"', DATA19='"+xuser+"', DATA20= '"+dT.getAlasan()+"' ",
							" WHERE CUSTNO = '"+dT.getCustno()+"'");
					repoUtilRepository.updateData("FCUSTDIV",
							" SET FLAGOUT= '"+xact+"' ",
							" WHERE CUSTNO = '"+dT.getCustno()+"'");
					repoUtilRepository.deleteData("fcustsls", " WHERE CUSTNO = '"+dT.getCustno()+"'");
				}
			}
		} else {
			List<UploadOutletAktifNonAktifDetailDto> dataDetail = repoOutlet.getDetail(docNo);
			if (dataDetail.size() > 0) {
				for (UploadOutletAktifNonAktifDetailDto dT : dataDetail) {
					repoUtilRepository.updateData("FCUSTMST",
							" SET FLAGOUT= '"+xact+"', CLASTUPD='"+tglGudang+"', DATA19='"+xuser+"', DATA20= '"+dT.getAlasan()+"' ",
							" WHERE CUSTNO = '"+dT.getCustno()+"'");
					repoUtilRepository.updateData("FCUSTDIV",
							" SET FLAGOUT= '"+xact+"' ",
							" WHERE CUSTNO = '"+dT.getCustno()+"'");
					repoUtilRepository.deleteData("fcustsls", " WHERE CUSTNO = '"+dT.getCustno()+"'");
				}
			}
			List<UploadOutletAktifNonAktifCoverDto> dataCover = repoOutlet.getCover(docNo);
			if (dataCover.size() > 0) {
				for (UploadOutletAktifNonAktifCoverDto dC : dataCover) {
					repoUtilRepository.insertData("FCUSTSLS",
							"CUSTNO, SLSNO, NOBRS, HSENIN, HSELASA, HRABU, HKAMIS, HJUMAT, HSABTU, HMINGGU, VISIT1, VISIT2, VISIT3, VISIT4, ROUTE",
							" '"+dC.getCustno()+"', '"+dC.getSlsno()+"', '"+dC.getNobrs()+"', "
						  + " '"+dC.getHsenin()+"', '"+dC.getHselasa()+"', '"+dC.getHrabu()+"', '"+dC.getHkamis()+"', "
						  + " '"+dC.getHjumat()+"', '"+dC.getHsabtu()+"', '"+dC.getHminggu()+"', "
						  + " '"+dC.getVisit1()+"', '"+dC.getVisit2()+"', '"+dC.getVisit3()+"', '"+dC.getVisit4()+"', "
						  + " '"+dC.getRoute()+"' ");
				}
			}
		}
		
		
		message =  new MessageDto("success", "Success Message", "Process Dokumen " + docNo +" berhasil!");
		return message;
	}

	@Override
	@Transactional
	public MessageDto print(UploadOutletAktifNonAktifDto p, String action, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		MessageDto message = new MessageDto();
		String hasil = "";
		List<Object[]> cekData = new ArrayList<>();
		UploadOutletAktifNonAktifHeaderDto header = p.getDataHeader();
		List<UploadOutletAktifNonAktifDetailDto> detail = p.getDataDetail();
		List<UploadOutletAktifNonAktifCoverDto> cover = p.getDataCover();
		String docNo = header.getDocno();
		
		cekData = repoUtilRepository.getTableListObject("FTABLE51_H", "*", "WHERE docno = '"+docNo+"' ");
		if (action.equalsIgnoreCase("edit") && cekData.size() != 0) {
			repoUtilRepository.deleteData("FTABLE51_H", "WHERE docno = '"+docNo+"' ");
		} else {
			cekData = repoUtilRepository.getTableListObject("ftable13", "*", "where xkey = 'NOTIF'");
			if (cekData.size() != 0) {
				Integer XNO = Integer.valueOf(repoUtilRepository.getTableString("ftable13", "nvl(MEMOSTRING,0) MEMOSTRING", "where xkey = 'NOTIF'"));
				Integer urut = Integer.valueOf(repoUtilRepository.getTableString("FTABLE51_H", "nvl(max(docno),0) urut", ""));
				if (XNO < urut) {
					XNO = urut;
				}
				if (XNO < 99999999) {
					docNo = String.valueOf((XNO + 1));
				} else {
					message = new MessageDto("warn", "Warning Message", "Master nomor upload outlet aktif sudah melebihi range!");
					return message;
				}
			} else {
				message = new MessageDto("warn", "Warning Message", "Master nomor upload outlet aktif belum di set!");
				return message;
			}
		}
		
		repoUtilRepository.insertData("FTABLE51_H", "DOCNO, DOCDATE, TIPE,USER_CREATED, XKEY, KEYAUTHENTICATION",
				" '"+docNo+"', '"+header.getDocdate()+"', '"+header.getTipe()+"', '"+header.getUsercreated()+"', '"+header.getXkey()+"', '"+header.getKeyauthentication()+"' ");
		
		if (detail.size() > 0) {
			if (action.equalsIgnoreCase("edit")) {
				repoUtilRepository.deleteData("FTABLE51_D", "WHERE docno = '"+docNo+"' ");
			}
			for (UploadOutletAktifNonAktifDetailDto dD : detail) {
				cekData = repoUtilRepository.getTableListObject("FTABLE51_D", "*", "WHERE docno = '"+dD.getDocno()+"' AND custno = '"+dD.getCustno()+"' ");
				if (cekData.size() == 0) {
					repoUtilRepository.insertData("FTABLE51_D", "DOCNO, CUSTNO, CUSTNAME,CUSTADDR, CHANNEL, ALASAN",
							" '"+docNo+"', '"+dD.getCustno()+"', '"+dD.getCustname()+"', '"+dD.getCustaddr()+"', '"+dD.getChannel()+"', '"+dD.getAlasan()+"' ");
				}
			}
			if (header.getTipe().equalsIgnoreCase("A")) {
				if (action.equalsIgnoreCase("edit")) {
					repoUtilRepository.deleteData("FTABLE51_R", "WHERE docno = '"+docNo+"' ");
				}
				for (UploadOutletAktifNonAktifCoverDto dC : cover) {
					cekData = repoUtilRepository.getTableListObject("FTABLE51_R", "*", "WHERE docno = '"+dC.getDocno()+"' and custno = '"+dC.getCustno()+"' and slsno = '"+dC.getSlsno()+"' ");
					if (cekData.size() == 0) {
						repoUtilRepository.insertData("FTABLE51_R",
								"DOCNO, CUSTNO, SLSNO, NOBRS, HSENIN, HSELASA, HRABU, HKAMIS, HJUMAT, HSABTU, HMINGGU, VISIT1, VISIT2, VISIT3, VISIT4, ROUTE, SLIMIT",
								" '"+docNo+"', '"+dC.getCustno()+"', '"+dC.getSlsno()+"', '"+dC.getNobrs()+"', "
								+ " '"+dC.getHsenin()+"', '"+dC.getHselasa()+"', '"+dC.getHrabu()+"', '"+dC.getHkamis()+"', '"+dC.getHjumat()+"', "
								+ " '"+dC.getHsabtu()+"', '"+dC.getHminggu()+"', '"+dC.getVisit1()+"', '"+dC.getVisit2()+"', '"+dC.getVisit3()+"', "
								+ " '"+dC.getVisit4()+"', '"+dC.getRoute()+"', '' ");
					}
				}
			}
		}
		
		if (action.equalsIgnoreCase("add")) {
			repoUtilRepository.updateData("ftable13", "set memostring = '"+docNo+"' ", "where xkey = 'NOTIF'");
		}
		
		List<UploadOutletAktifNonAktifPrintDto> result = new ArrayList<>();
		
		result = repoOutlet.dataCetak(docNo);
		
		if (result.size() == 0) {
			message = new MessageDto("warn", "Warning Message", "Data Tidak ada");
			return message;
		} else {
			Resource resource = new ClassPathResource("uploadOutlet/aktifNonAktif/formOutletAktifNonAktif.jrxml");
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
		
		message = new MessageDto("success", "Success Message", hasil);
		return message;
	}

	@Override
	public UploadOutletAktifNonAktifDto cekExcel(List<UploadOutletAktifNonAktifExcelDto> p, String action, String docdate) {
		// TODO Auto-generated method stub
		UploadOutletAktifNonAktifDto result = new UploadOutletAktifNonAktifDto();
		List<UploadOutletAktifNonAktifDetailDto> dataDetail = new ArrayList<>();
		List<UploadOutletAktifNonAktifCoverDto> dataCover = new ArrayList<>();
		List<String> logs = new ArrayList<>();
		for (UploadOutletAktifNonAktifExcelDto dt : p) {
			if (dt.getCustno().length() == 7) {
				List<Object[]> dataOutlet = repoUtilRepository.getTableListObject("FCUSTMST", "custno, custname, custadd1, typeout, flagout", "WHERE CUSTNO = '"+dt.getCustno()+"'");
				if (dataOutlet.size() == 0) {
					logs.add("Outlet "+dt.getCustno()+" Tidak dikenal! | Baris : "+dt.getRownum()+" ");
				} else {
					for (Object[] dO : dataOutlet) {
						Boolean validasi = true;
						List<Object[]> cekData = new ArrayList<>();
						
						cekData = repoUtilRepository.getTableListObjectJoin("FTABLE51_H a", "a.docno, a.docno as a", 
								"LEFT JOIN "+t.getTenantId()+".FTABLE51_D b ON a.docno = b.docno", "WHERE CUSTNO = '"+dt.getCustno()+"' AND USERAUTHENTICATION is null");
						if (cekData.size() != 0) {
							for (Object[] cD : cekData) {
								logs.add("Outlet "+dt.getCustno()+" sudah ada di no dokumen "+Objects.toString(cD[0], "")+"! | Baris : "+dt.getRownum()+" ");
							}
							validasi = false;
						}
						
						if (validasi == true) {
							cekData = repoUtilRepository.getTableListObject("FSLS", "*", "WHERE SLSNO = '"+dt.getSlsno()+"'");
							if (cekData.size() == 0) {
								logs.add("Outlet "+dt.getCustno()+" sales "+dt.getSlsno()+" tidak terdaftar! | Baris : "+dt.getRownum()+" ");
								validasi = false;
							}
						}
						
						if (validasi == true) {
							if (action.equalsIgnoreCase("N")) {
								cekData = repoOutlet.valOutletOutstanding(dt.getCustno());
								if (cekData.size() != 0) {
									logs.add("Outlet "+dt.getCustno()+" ada piutang outstanding! | Baris : "+dt.getRownum()+" ");
									validasi = false;
								}
								
								String periode = repoUtilRepository.getTableString("FMEMO", "MEMOINT", "WHERE MEMONAMA='PERIODE'");
								String tahun = repoUtilRepository.getTableString("FMEMO", "MEMOINT", "WHERE MEMONAMA='TAHUN'");
								
								cekData = repoOutlet.valOutletTransaksi(dt.getCustno(), docdate, periode, tahun);
								if (cekData.size() != 0) {
									logs.add("Outlet "+dt.getCustno()+" ada transaksi di periode perjalan! | Baris : "+dt.getRownum()+" ");
									validasi = false;
								}
								if (dt.getReason().length() == 0) {
									logs.add("Outlet "+dt.getCustno()+" kolom alasan kosong! | Baris : "+dt.getRownum()+" ");
									validasi = false;
								} else {
									if (!dt.getReason().toUpperCase().equalsIgnoreCase("BANGKRUT") && !dt.getReason().toUpperCase().equalsIgnoreCase("DOUBLE OUTLET")
											&& !dt.getReason().toUpperCase().equalsIgnoreCase("ALIH PROFESI") && !dt.getReason().toUpperCase().equalsIgnoreCase("GANTI PEMILIK")
											&& !dt.getReason().toUpperCase().equalsIgnoreCase("SPLIT AREA")) {
										logs.add("Outlet "+dt.getCustno()+" alasan tidak sesuai! | Baris : "+dt.getRownum()+" ");
										validasi = false;
									}
								}
								if (Objects.toString(dO[4]).equalsIgnoreCase("N")) {
									logs.add("Outlet "+dt.getCustno()+" sudah TIDAK AKTIF! | Baris : "+dt.getRownum()+" ");
									validasi = false;
								}
							} else {
								if (!Objects.toString(dO[4]).equalsIgnoreCase("N")) {
									logs.add("Outlet "+dt.getCustno()+" sudah AKTIF! | Baris : "+dt.getRownum()+" ");
									validasi = false;
								}
							}
						}
						
						if (validasi == true) {
							UploadOutletAktifNonAktifDetailDto detail = new UploadOutletAktifNonAktifDetailDto();
							UploadOutletAktifNonAktifCoverDto cover = new UploadOutletAktifNonAktifCoverDto();
							
							String channel = Objects.toString(dO[3],"");
							String channelName = repoUtilRepository.getTableString("FTYPEOUT", "TYPENAME", "WHERE TYPE = '"+channel+"'");
							detail.setCustno(Objects.toString(dO[0],""));
							detail.setCustname(Objects.toString(dO[1],""));
							detail.setCustaddr(Objects.toString(dO[2],""));
							detail.setChannel(channel);
							detail.setAlasan(dt.getReason().toUpperCase());
							detail.setNamatipe(channelName);
							
							String slsName = repoUtilRepository.getTableString("FSLS", "SLSNAME", "WHERE SLSNO = '"+dt.getSlsno()+"'");
							cover.setCustno(Objects.toString(dO[0],""));
							cover.setCustname(Objects.toString(dO[1],""));
							cover.setSlsno(dt.getSlsno());
							cover.setSlsname(slsName);
							cover.setNobrs("1");
							cover.setHsenin(dt.getHsenin());
							cover.setHselasa(dt.getHselasa());
							cover.setHrabu(dt.getHrabu());
							cover.setHkamis(dt.getHkamis());
							cover.setHjumat(dt.getHjumat());
							cover.setHsabtu(dt.getHsabtu());
							cover.setHminggu(dt.getHminggu());
							cover.setVisit1(dt.getVisit1());
							cover.setVisit2(dt.getVisit2());
							cover.setVisit3(dt.getVisit3());
							cover.setVisit4(dt.getVisit4());
							cover.setRoute(dt.getRoute());
							
							if ((dt.getVisit1()+dt.getVisit2()+dt.getVisit3()+dt.getVisit4()).equalsIgnoreCase("YYYY")) {
								cover.setPola("Weekly");
							} else if ((dt.getVisit1()+dt.getVisit2()+dt.getVisit3()+dt.getVisit4()).equalsIgnoreCase("YTYT")) {
								cover.setPola("Be Weekly 1");
							} else if ((dt.getVisit1()+dt.getVisit2()+dt.getVisit3()+dt.getVisit4()).equalsIgnoreCase("TYTY")) {
								cover.setPola("Be Weekly 2");
							} else if ((dt.getVisit1()+dt.getVisit2()+dt.getVisit3()+dt.getVisit4()).equalsIgnoreCase("YTTT")) {
								cover.setPola("Monthly 1");
							} else if ((dt.getVisit1()+dt.getVisit2()+dt.getVisit3()+dt.getVisit4()).equalsIgnoreCase("TYTT")) {
								cover.setPola("Monthly 2");
							} else if ((dt.getVisit1()+dt.getVisit2()+dt.getVisit3()+dt.getVisit4()).equalsIgnoreCase("TTYT")) {
								cover.setPola("Monthly 3");
							} else if ((dt.getVisit1()+dt.getVisit2()+dt.getVisit3()+dt.getVisit4()).equalsIgnoreCase("TTTY")) {
								cover.setPola("Monthly 4");
							} else {
								cover.setPola(dt.getVisit1()+dt.getVisit2()+dt.getVisit3()+dt.getVisit4());
							}
							
							dataDetail.add(detail);
							dataCover.add(cover);
						}
						
					}
				}
			} else {
				logs.add("Outlet "+dt.getCustno()+" harus 7 digit! | Baris : "+dt.getRownum()+" ");
			}
		}
		result = new UploadOutletAktifNonAktifDto(null, dataDetail, dataCover, logs);
		return result;
	}
	
}
