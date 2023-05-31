package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscApHirDto;
import myor.matrix.master.entity.DiscDto;
import myor.matrix.master.entity.DiscOutletDto;
import myor.matrix.master.entity.DiscPromoDto;
import myor.matrix.master.entity.DiscRangeDto;
import myor.matrix.master.entity.DiscSyaratGProdDto;
import myor.matrix.master.entity.DiscSyaratKuota;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.entity.DiskonBertingkatDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.PromotionDiscNewRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class PromotionDiscNewRepositoryImpl implements PromotionDiscNewRepository{

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public DiscViewDto getPromoDiscNew(String ap) {
		// TODO Auto-generated method stub
		DiscViewDto result = new DiscViewDto();
		
		DiscApHirDto apHir = getApHir(ap);
		
		result.setBerlakuHierarki(apHir.getBerlakuHierarki());
		
		String sql ="";
		
		if (apHir.getFlagHierarki() == 0) {
			sql= "	SELECT kodeap,	"
					+"	       DECODE (tipe,	"
					+"	               1, '1. Ekstra Diskon 1',	"
					+"	               2, '2. Ekstra Diskon 2',	"
					+"	               3, '3. Promosi',	"
					+"	               4, '4. Regular Diskon'	"
					+"	              ) AS tipe,	"
					+"	       flagaktif, keterangan, TO_CHAR (tglawal, 'DD MON YYYY') AS tglawal,	"
					+"	       TO_CHAR (tglakhir, 'DD MON YYYY') AS tglakhir,	"
					+"	       TO_CHAR (tgltoleransiklaim, 'DD MON YYYY') AS tgltoleransiklaim,	"
					+"	       DECODE (klaim, 0, 'Tidak', 1, 'Ya') AS klaim, kodeparent, flagexclude,	"
					+"	       DECODE (flagexclude, 0, 'Tidak', 1, 'Ya') AS exclude,	"
					+"	       DECODE (cetakfaktur,	"
					+"	               1, '1. Nilai',	"
					+"	               2, '2. Persentase'	"
					+"	              ) AS cetakfaktur,	"
					+"	       DECODE (kontribusi, 1, '1. Nilai', 2, '2. Qty') AS kontribusi,	"
					+"	       DECODE (flagro, 0, 'Tidak Proses RO', 1, 'Proses RO') AS flagro,	"
					+"	       DECODE (berlakubeli,	"
					+"	               1, '1. Produk',	"
					+"	               2, '2. Sub Brand',	"
					+"	               3, '3. Divisi'	"
					+"	              ) AS berlakubeli,	"
					+"	       DECODE (satuanbeli,	"
					+"	               1, '1. Besar',	"
					+"	               2, '2. Tengah',	"
					+"	               3, '3. Kecil',	"
					+"	               4, '4. Nilai'	"
					+"	              ) AS satuanbeli,	"
					+"	       berlakukelipatan, to_char(minbeli) minbeli,	"
					+"	       DECODE (kategorikelipatan,	"
					+"	               1, '1. Proporsional',	"
					+"	               2, '2. Prorata'	"
					+"	              ) AS kategorikelipatan,	"
					+"	       DECODE (tipenilaikelipatan,	"
					+"	               1, '1. Besar',	"
					+"	               2, '2. Tengah',	"
					+"	               3, '3. Kecil',	"
					+"	               4, '4. Nilai'	"
					+"	              ) AS tipenilaikelipatan,	"
					+"	       to_char(nilaidisckelipatan) nilaidisckelipatan, flagmakdisk, to_char(nilaimakdisk) nilaimakdisk, flagoutlet,	"
					+"	       flaggrupoutlet, flaggrupharga, flagchannel, flagteam, flagsalesforce,	"
					+"	       flaggrupdisk, flagspesifikasi,	"
					+"	       DECODE (tiperange,	"
					+"	               1, '1. Persentase',	"
					+"	               2, '2. Fix Value',	"
					+"	               3, '3. Value per Qty',	"
					+"	               4, '4. Fix Qty'	"
					+"	              ) AS tiperange,	"
					+"	       DECODE (nilaidiskrange,	"
					+"	               1, '1. Besar',	"
					+"	               2, '2. Tengah',	"
					+"	               3, '3. Kecil',	"
					+"	               4, '4. Nilai'	"
					+"	              ) AS nilaidiskrange,	"
					+"	       flagdiskbertingkat, flagsyaratproduk, nvl(maksyaratproduk,0) as maksyaratproduk, flagkumulatif,	"
					+"	       NVL (flagsyaratkelprod, 0) AS flagsyaratkelprod, nvl(syaratmakkel,0) syaratmakkel,	"
					+"	       DECODE (syaratpilprod,	"
					+"	               1, '1. Produk',	"
					+"	               2, '2. Sub Brand',	"
					+"	               3, '3. Divisi',	"
					+"	               4, '4. Group Product'	"
					+"	              ) AS syaratpilprod,	"
					+"	       flagkuota,	"
					+"	       DECODE (kuotabaseon,	"
					+"	               1, '1. Produk Promosi',	"
					+"	               2, '2. Barang Jual'	"
					+"	              ) AS kuotabaseon,	"
					+"	       DECODE (satuankuota,	"
					+"	               1, '1. Besar',	"
					+"	               2, '2. Tengah',	"
					+"	               3, '3. Kecil',	"
					+"	               4, '4. Nilai'	"
					+"	              ) AS satuankuota,	"
					+"	       DECODE (berlakukuota,	"
					+"	               1, '1. SUBDIST',	"
					+"	               2, '2. Outlet'	"
					+"	              ) AS berlakukuota, flaghistkuota,	"
					+"	       TO_CHAR (tglawalhist, 'DD MON YYYY') AS tglawalhist,	"
					+"	       TO_CHAR (tglakhirhist, 'DD MON YYYY') AS tglakhirhist,	"
					+"	       DECODE (pilhitunghist,	"
					+"	               1, '1. Semua',	"
					+"	               2, '2. Rata-Rata per Pekan'	"
					+"	              ) AS pilhitunghist,	"
					+"	       nvl(jmlpengalihist,0) jmlpengalihist, flagproseshist, flagbatasanperoutlet, nvl(batasanperoutlet,0) batasanperoutlet,	"
					+"	       flagbatasanperap, nvl(batasanperap,0) batasanperap, flaggrowth, nvl(nilaigrowth,0) nilaigrowth, flageditpromo,	"
					+"	       flaghierarki,	"
					+"	       DECODE (berlakuhierarki,	"
					+"	               100, '100. Outlet',	"
					+"	               200, '200. Grup Outlet',	"
					+"	               300, '300. Channel',	"
					+"	               400, '400. Salesforce',	"
					+"	               500, '500. Outlet Spesifik',	"
					+"	               600, '600. Grup Diskon',	"
					+"	               700, '700. Grup Harga'	"
					+"	              ) AS berlakuhierarki,	"
					+"	       DECODE (hitungrange, 1, '1. Gross', 2, '2. Neto') AS hitungrange,	"
					+"	       DECODE (hitungdiskon, 1, '1. Gross', 2, '2. Neto') AS hitungdiskon,	"
					+"	       description	"
					+"	  FROM "+t.getTenantId()+".ftable155	"
					+"	 WHERE (tipe = 3) AND kodeap = '"+ap+"'	";	
		}else {
			sql= "	SELECT kodeap,	"
					+"	       DECODE (tipe,	"
					+"	               1, '1. Ekstra Diskon 1',	"
					+"	               2, '2. Ekstra Diskon 2',	"
					+"	               3, '3. Promosi',	"
					+"	               4, '4. Regular Diskon'	"
					+"	              ) AS tipe,	"
					+"	       flagaktif, keterangan, TO_CHAR (tglawal, 'DD MON YYYY') AS tglawal,	"
					+"	       TO_CHAR (tglakhir, 'DD MON YYYY') AS tglakhir,	"
					+"	       TO_CHAR (tgltoleransiklaim, 'DD MON YYYY') AS tgltoleransiklaim,	"
					+"	       DECODE (klaim, 0, 'Tidak', 1, 'Ya') AS klaim, kodeparent,	"
					+"	       '0' as flagexclude, DECODE (0, 0, 'Tidak', 1, 'Ya') AS exclude,	"
					+"	       DECODE (cetakfaktur,	"
					+"	               1, '1. Nilai',	"
					+"	               2, '2. Persentase'	"
					+"	              ) AS cetakfaktur,	"
					+"	       DECODE (kontribusi, 1, '1. Nilai', 2, '2. Qty') AS kontribusi,	"
					+"	       DECODE (flagro, 0, 'Tidak Proses RO', 1, 'Proses RO') AS flagro,	"
					+"	       DECODE (0,	"
					+"	               0, '0. Semua',	"
					+"	               1, '1. Produk',	"
					+"	               2, '2. Sub Brand',	"
					+"	               3, '3. Divisi'	"
					+"	              ) AS berlakubeli,	"
					+"	       DECODE (satuanbeli,	"
					+"	               1, '1. Besar',	"
					+"	               2, '2. Tengah',	"
					+"	               3, '3. Kecil',	"
					+"	               4, '4. Nilai'	"
					+"	              ) AS satuanbeli,	"
					+"	       berlakukelipatan, to_char(minbeli) minbeli,	"
					+"	       DECODE (kategorikelipatan,	"
					+"	               1, '1. Proporsional',	"
					+"	               2, '2. Prorata'	"
					+"	              ) AS kategorikelipatan,	"
					+"	       DECODE (tipenilaikelipatan,	"
					+"	               1, '1. Besar',	"
					+"	               2, '2. Tengah',	"
					+"	               3, '3. Kecil',	"
					+"	               4, '4. Nilai'	"
					+"	              ) AS tipenilaikelipatan,	"
					+"	       to_char(nilaidisckelipatan) nilaidisckelipatan, flagmakdisk, to_char(nilaimakdisk) nilaimakdisk,	"
					+"	       CASE	"
					+"	          WHEN berlakuhierarki = 100	"
					+"	             THEN '1'	"
					+"	          ELSE '0'	"
					+"	       END flagoutlet,	"
					+"	       CASE	"
					+"	          WHEN berlakuhierarki = 200	"
					+"	             THEN '1'	"
					+"	          ELSE '0'	"
					+"	       END flaggrupoutlet,	"
					+"	       CASE	"
					+"	          WHEN berlakuhierarki = 700	"
					+"	             THEN '1'	"
					+"	          ELSE '0'	"
					+"	       END flaggrupharga,	"
					+"	       CASE	"
					+"	          WHEN berlakuhierarki = 300	"
					+"	             THEN '1'	"
					+"	          ELSE '0'	"
					+"	       END flagchannel, '0' flagteam,	"
					+"	       CASE	"
					+"	          WHEN berlakuhierarki = 400	"
					+"	             THEN '1'	"
					+"	          ELSE '0'	"
					+"	       END flagsalesforce,	"
					+"	       CASE	"
					+"	          WHEN berlakuhierarki = 600	"
					+"	             THEN '1'	"
					+"	          ELSE '0'	"
					+"	       END flaggrupdisk,	"
					+"	       CASE	"
					+"	          WHEN berlakuhierarki = 500	"
					+"	             THEN '1'	"
					+"	          ELSE '0'	"
					+"	       END flagspesifikasi,	"
					+"	       DECODE (tiperange,	"
					+"	               1, '1. Persentase',	"
					+"	               2, '2. Fix Value',	"
					+"	               3, '3. Value per Qty',	"
					+"	               4, '4. Fix Qty'	"
					+"	              ) AS tiperange,	"
					+"	       DECODE (nilaidiskrange,	"
					+"	               1, '1. Besar',	"
					+"	               2, '2. Tengah',	"
					+"	               3, '3. Kecil',	"
					+"	               4, '4. Nilai'	"
					+"	              ) AS nilaidiskrange,	"
					+"	       '0' flagdiskbertingkat, flagsyaratproduk, nvl(maksyaratproduk,0) maksyaratproduk,	"
					+"	       flagkumulatif, NVL (flagsyaratkelprod, 0) AS flagsyaratkelprod,	"
					+"	       nvl(syaratmakkel,0) syaratmakkel,	"
					+"	       DECODE (syaratpilprod,	"
					+"	               1, '1. Produk',	"
					+"	               2, '2. Sub Brand',	"
					+"	               3, '3. Divisi',	"
					+"	               4, '4. Group Product'	"
					+"	              ) AS syaratpilprod,	"
					+"	       flagkuota,	"
					+"	       DECODE (kuotabaseon,	"
					+"	               1, '1. Bonus/Disc',	"
					+"	               2, '2. Barang Jual'	"
					+"	              ) AS kuotabaseon,	"
					+"	       DECODE (satuankuota,	"
					+"	               1, '1. Besar',	"
					+"	               2, '2. Tengah',	"
					+"	               3, '3. Kecil',	"
					+"	               4, '4. Nilai'	"
					+"	              ) AS satuankuota,	"
					+"	       DECODE (berlakukuota,	"
					+"	               1, '1. SUBDIST',	"
					+"	               2, '2. Outlet'	"
					+"	              ) AS berlakukuota, flaghistkuota,	"
					+"	       TO_CHAR (tglawalhist, 'DD MON YYYY') AS tglawalhist,	"
					+"	       TO_CHAR (tglakhirhist, 'DD MON YYYY') AS tglakhirhist,	"
					+"	       DECODE (pilhitunghist,	"
					+"	               1, '1. Semua',	"
					+"	               2, '2. Rata-Rata per Pekan'	"
					+"	              ) AS pilhitunghist,	"
					+"	       nvl(jmlpengalihist,0) jmlpengalihist, flagproseshist, flagbatasanperoutlet, nvl(batasanperoutlet,0) batasanperoutlet,	"
					+"	       flagbatasanperap, coalesce(batasanperap,0) batasanperap, flaggrowth, nvl(nilaigrowth,0) nilaigrowth, flageditpromo,	"
					+"	       '1' flaghierarki,	"
					+"	       DECODE (berlakuhierarki,	"
					+"	               100, '100. Outlet',	"
					+"	               200, '200. Grup Outlet',	"
					+"	               300, '300. Channel',	"
					+"	               400, '400. Salesforce',	"
					+"	               500, '500. Outlet Spesifik',	"
					+"	               600, '600. Grup Diskon',	"
					+"	               700, '700. Grup Harga'	"
					+"	              ) AS berlakuhierarki,	"
					+"	       DECODE (hitungrange, 1, '1. Gross', 2, '2. Neto') AS hitungrange,	"
					+"	       DECODE (hitungdiskon, 1, '1. Gross', 2, '2. Neto') AS hitungdiskon,	"
					+"	       description	"
					+"	  FROM "+t.getTenantId()+".ftable155_hir	"
					+"	 WHERE (tipe = 3)	"
					+"	   AND kodeap = '"+ap+"'	"
					+"	   AND tglawal = '"+apHir.getTglAwal()+"'	"
					+"	   AND berlakuhierarki = '"+apHir.getBerlakuHierarki()+"'	"
					+"	   AND seqno = '"+apHir.getSeqno()+"'	";
		}
		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			DiscDto data = new DiscDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3],
					(String) obj[4], (String) obj[5], 
					(String) obj[6], 
					(String) obj[7],
					(String) obj[8], 
					Objects.toString(obj[9],""), 
					(String) obj[10],
					(String) obj[11], (String) obj[12], (String) obj[13], (String) obj[14], (String) obj[15], (String) obj[16],
					(String) obj[17], (String) obj[18], (String) obj[19], (String) obj[20], (String) obj[21], 
					(String) obj[22],
					Objects.toString(obj[23],""),
					Objects.toString(obj[24],""), Objects.toString(obj[25],""), Objects.toString(obj[26],""), Objects.toString(obj[27],""), Objects.toString(obj[28],""),
					Objects.toString(obj[29],""), Objects.toString(obj[30],""), (String) obj[31], (String) obj[32], Objects.toString(obj[33],""), (String) obj[34], ((BigDecimal) obj[35]).doubleValue(),
					(String) obj[36], (String) obj[37], ((BigDecimal) obj[38]).doubleValue(), (String) obj[39], (String) obj[40], (String) obj[41], (String) obj[42], (String) obj[43], 
					(String) obj[44], (String) obj[45], (String) obj[46], (String) obj[47], ((BigDecimal) obj[48]).doubleValue(), (String) obj[49], (String) obj[50],
					((BigDecimal) obj[51]).doubleValue(), (String) obj[52], ((BigDecimal) obj[53]).doubleValue(), (String) obj[54], ((BigDecimal) obj[55]).doubleValue(),
					(String) obj[56], Objects.toString(obj[57],""), (String) obj[58], (String) obj[59], (String) obj[60], (String) obj[61]
					);
			result.setData(data);
		}
		
		//Start Range
		if (result.getData().getBerlakuKelipatan().equalsIgnoreCase("0")) {
			String sqlRange="";
			if (apHir.getFlagHierarki() == 0) {
				sqlRange = "SELECT kodeap, norange, minrange, makrange, nvl(nilai,0) nilai, grupkel FROM "+t.getTenantId()+".ftable167 WHERE kodeap='"+ap+"' ORDER BY norange";
			}else {
				sqlRange = "	SELECT DISTINCT a.kodeap, a.norange, a.minrange, a.makrange, a.nilai,	"
						+"	                a.grupkel	"
						+"	           FROM "+t.getTenantId()+".ftable167_hir a JOIN "+t.getTenantId()+".ftable155_hir b	"
						+"	                ON a.kodeap = b.kodeap	"
						+"	              AND a.tglawal = b.tglawal	"
						+"	              AND a.berlakuhierarki = b.berlakuhierarki	"
						+"	              AND a.detailhierarki = b.detailhierarki	"
						+"	          WHERE b.kodeap = '"+ap+"'	"
						+"	            AND b.tglawal = '"+apHir.getTglAwal()+"'	"
						+"	            AND b.berlakuhierarki = '"+apHir.getBerlakuHierarki()+"'	"
						+"	            AND b.seqno = "+apHir.getSeqno()+"	"
						+"	       ORDER BY norange	";
			}
			Query queryRange = entityManager.createNativeQuery(sqlRange);
			List<Object[]> range = queryRange.getResultList();
			List<DiscRangeDto> rangeRslt = new ArrayList<>();
			for (Object[] obj : range) {
				DiscRangeDto data = new DiscRangeDto((String) obj[0], ((BigDecimal) obj[1]).doubleValue(), ((BigDecimal) obj[2]).doubleValue(), 
						((BigDecimal) obj[3]).doubleValue(), ((BigDecimal) obj[4]).doubleValue(), (String) obj[5]);
				rangeRslt.add(data);
			}
			result.setRanges(rangeRslt);
				
		}
		//End Range
		
		//Start Product
				String sqlProduct = "	SELECT kode,	"
						+"	       CASE	"
						+"	          WHEN berlakubeli = 1	"
						+"	             THEN (SELECT pcodename	"
						+"	                     FROM "+t.getTenantId()+".fmaster	"
						+"	                    WHERE pcode = kode)	"
						+"	          WHEN berlakubeli = 2	"
						+"	             THEN (SELECT sbra1name	"
						+"	                     FROM "+t.getTenantId()+".ftsbrand1	"
						+"	                    WHERE prlin || brand || sbra1 = kode)	"
						+"	          WHEN berlakubeli = 3	"
						+"	             THEN (SELECT sbra2name	"
						+"	                     FROM "+t.getTenantId()+".ftsbrand2	"
						+"	                    WHERE sbra2 = kode)	"
						+"	       END AS keterangan	"
						+"	  FROM "+t.getTenantId()+".ftable157	"
						+"	 WHERE kodeap = '"+ap+"'	";
				Query queryProduct = entityManager.createNativeQuery(sqlProduct);
				
				List<Object[]> product = queryProduct.getResultList();
				
				List<SelectItem<String>> productResult = new ArrayList<>();
				
				for (Object[] obj : product) {
					SelectItem<String> data = new SelectItem<String>((String) obj[1], (String) obj[0]);
					productResult.add(data);
				}
				result.setProducts(productResult);
		//End Product
				
		//Start Promo
				String sqlPromo= "";
				if (apHir.getFlagHierarki() == 0) {
					sqlPromo =" SELECT a.kodeap, a.seqno, a.pcode, b.pcodename AS keterangan "
							+"   FROM "+t.getTenantId()+".ftable158 a LEFT JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode "
							+"   WHERE kodeap = '"+ap+"' ORDER BY a.seqno  ";
				}else {
					sqlPromo="SELECT DISTINCT a.kodeap, a.seqno, a.pcode, b.pcodename AS keterangan " 
							+" FROM "+t.getTenantId()+".ftable158_hir a LEFT JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode " 
							+" JOIN "+t.getTenantId()+".ftable155_hir c ON a.kodeap = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki "  
							+" WHERE c.kodeap = '"+ap+"' " 
							+" AND c.tglawal = '"+apHir.getTglAwal()+"' " 
							+" AND c.berlakuhierarki = '"+apHir.getBerlakuHierarki()+"' " 
							+" AND c.seqno = "+apHir.getSeqno()+" " 
							+" ORDER BY a.seqno ";
				}
				Query queryPromo = entityManager.createNativeQuery(sqlPromo);
				
				List<Object[]> promo = queryPromo.getResultList();
				List<DiscPromoDto> prmoRslt = new ArrayList<>();
				
				for (Object[] obj : promo) {
					DiscPromoDto data = new DiscPromoDto((String) obj[0], ((BigDecimal) obj[1]).doubleValue(), (String) obj[2], (String) obj[3]);
					prmoRslt.add(data);
				}
				result.setPromo(prmoRslt);				
		//End Promo
		
				
				//Start Syarat Kuota
				String sqlSyaratKuota = "";
				if (apHir.getFlagHierarki() == 0) {	
					sqlSyaratKuota = "	SELECT kodeap, subdist_id, outlet,	"
							+"	       CASE	"
							+"	          WHEN outlet = '*'	"
							+"	             THEN (SELECT memostring	"
							+"	                     FROM "+t.getTenantId()+".fmemo	"
							+"	                    WHERE memonama = 'COMPANYNAME')	"
							+"	          ELSE (SELECT TO_CHAR (custname)	"
							+"	                  FROM "+t.getTenantId()+".fcustmst	"
							+"	                 WHERE custno = outlet)	"
							+"	       END AS keterangan,	"
							+"	       nvl(nilai,0) nilai	"
							+"	  FROM "+t.getTenantId()+".ftable172	"
							+"	 WHERE kodeap = '"+ap+"'	";
				} else {
					sqlSyaratKuota = "	SELECT DISTINCT a.kodeap, a.subdist_id, a.outlet,	"
							+"	                CASE	"
							+"	                   WHEN outlet = '*'	"
							+"	                      THEN (SELECT memostring	"
							+"	                              FROM "+t.getTenantId()+".fmemo	"
							+"	                             WHERE memonama = 'COMPANYNAME')	"
							+"	                   ELSE (SELECT TO_CHAR (custname)	"
							+"	                           FROM "+t.getTenantId()+".fcustmst	"
							+"	                          WHERE custno = outlet)	"
							+"	                END AS keterangan,	"
							+"	                nvl(a.nilai,0) nilai	"
							+"	           FROM "+t.getTenantId()+".ftable172_hir a JOIN "+t.getTenantId()+".ftable155_hir b	"
							+"	                ON a.kodeap = b.kodeap	"
							+"	              AND a.tglawal = b.tglawal	"
							+"	              AND a.berlakuhierarki = b.berlakuhierarki	"
							+"	              AND a.detailhierarki = b.detailhierarki	"
							+"	          WHERE b.kodeap = '"+ap+"'	"
							+"	            AND b.tglawal = '"+apHir.getTglAwal()+"'	"
							+"	            AND b.berlakuhierarki = '"+apHir.getBerlakuHierarki()+"'	"
							+"	            AND b.seqno = "+apHir.getSeqno()+"	";
				}
				Query querySyaratKuota = entityManager.createNativeQuery(sqlSyaratKuota);
				List<Object[]> obj = querySyaratKuota.getResultList();
				
				List<DiscSyaratKuota> syaratKuotaResult = new ArrayList<>();
				
				for (Object[] o : obj) {
					DiscSyaratKuota syaratKuota = new DiscSyaratKuota((String) o[1], (String) o[2], (String) o[3], ((BigDecimal)o[4]).doubleValue());
					syaratKuotaResult.add(syaratKuota);
				}
				result.setSyaratKuota(syaratKuotaResult);
				//End Syarat Kuota		
				
				//Start Outlet
				if (result.getData().getFlagOutlet().equalsIgnoreCase("1")) {
					String sqlOutlet = "";
					if (apHir.getFlagHierarki() == 0) {
						sqlOutlet = "	SELECT kodeap, subdist_id, (SELECT memostring	"
								+"	                              FROM "+t.getTenantId()+".fmemo	"
								+"	                             WHERE memonama = 'COMPANYNAME') AS subdist_nm,	"
								+"	       outlet, b.custname AS keterangan	"
								+"	  FROM "+t.getTenantId()+".ftable159 a LEFT JOIN "+t.getTenantId()+".fcustmst b ON a.outlet = b.custno	"
								+"	 WHERE kodeap = '"+ap+"'	";
					}else {
						sqlOutlet = "select kodeap, (select memostring from "+t.getTenantId()+".fmemo where memonama = 'KODEDIST') subdist_id, "
								+"(select memostring from "+t.getTenantId()+".fmemo where memonama = 'COMPANYNAME') AS subdist_nm, a.detailhierarki outlet, b.custname as keterangan "
								+" from "+t.getTenantId()+".ftable155_hir a "  
								+" left join "+t.getTenantId()+".fcustmst b on a.detailhierarki = b.custno " 
								+" where kodeap = '"+ap+"' " 
								+" AND tglawal = '"+apHir.getTglAwal()+"' " 
								+" AND berlakuhierarki = '"+apHir.getBerlakuHierarki()+"' " 
								+" AND seqno = "+apHir.getSeqno()+"";
					}
					Query queryOutlet = entityManager.createNativeQuery(sqlOutlet);
					List<Object[]> objOutlet = queryOutlet.getResultList();
					
					List<DiscOutletDto> outletRslt = new ArrayList<>();
					
					for (Object[] o : objOutlet) {
						DiscOutletDto outlet = new DiscOutletDto((String)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4]);
						outletRslt.add(outlet);
					}
					result.setOutlets(outletRslt);
					result.setEdtTabOutlet("Beberapa");
					if (outletRslt != null) {
						result.setEdtTabSubdist(result.getOutlets().get(0).getSubdistName());
					}
				} else {
					result.setEdtTabOutlet("Semua");
					result.setEdtTabSubdist("");
				}
				//End Outlet
				

				//Start GroupOutlet
				if(result.getData().getFlagGroupOutlet().equalsIgnoreCase("1")) {
					String sqlGroupOutlet = "";
					if(apHir.getFlagHierarki() == 0) {
						sqlGroupOutlet = "	select kodeap, grupoutlet, grupname as keterangan from "+t.getTenantId()+".ftable160 a " + 
								"  left join "+t.getTenantId()+".fgrupout b on grupoutlet = grupout where kodeap = '"+ap+"'";
					}else {
						sqlGroupOutlet = "select a.kodeap, a.detailhierarki grupoutlet, b.grupname as keterangan from "+t.getTenantId()+".ftable155_hir a " + 
								"  left join "+t.getTenantId()+".fgrupout b on a.detailhierarki = b.grupout " +
								"  where a.kodeap = '"+ap+"' " + 
								"  AND a.tglawal = '"+apHir.getTglAwal()+"'" + 
								"  AND a.berlakuhierarki = '"+apHir.getBerlakuHierarki()+"' " + 
								"  AND a.seqno = '"+apHir.getSeqno()+"'";
					}
					Query queryGroupOut = entityManager.createNativeQuery(sqlGroupOutlet);
					List<Object[]> objGroupOut = queryGroupOut.getResultList();
					
					List<SelectItem<String>> groupOutletResult = new ArrayList<>();
					
					for (Object[] o : objGroupOut) {
						SelectItem<String> groupOutlet = new SelectItem<String>((String) o[1], (String)o[2]);
						groupOutletResult.add(groupOutlet);
					}
					result.setGroupOutlet(groupOutletResult);
					result.setEdtTabGroupOutlet("Beberapa");
				} else {
					result.setEdtTabGroupOutlet("Semua");
				}
				//End GroupOutlet
				

				//Start Group Harga
				if(result.getData().getFlagGroupHarga().equalsIgnoreCase("1")) {
					String sqlGroupHarga = "";
					if(apHir.getFlagHierarki() == 0) {
						sqlGroupHarga = " select kodeap, grupharga, ket as keterangan from "+t.getTenantId()+".ftable161 a " + 
								" left join "+t.getTenantId()+".fgharga b on a.grupharga = b.gharga where kodeap = '"+ap+"' ";
					}else {
						sqlGroupHarga = "select kodeap, a.detailhierarki grupharga, ket as keterangan from "+t.getTenantId()+".ftable155_hir a " + 
								"   left join "+t.getTenantId()+".fgharga b on a.detailhierarki = b.gharga where a.kodeap = '"+ap+"'" + 
								"   AND a.tglawal = '"+apHir.getTglAwal()+"'" + 
								"   AND a.berlakuhierarki = '"+apHir.getBerlakuHierarki()+"' " + 
								"   AND a.seqno = '"+apHir.getSeqno()+"'";
					}
					Query queryGroupHarga = entityManager.createNativeQuery(sqlGroupHarga);
					List<Object[]> objGroupPrice = queryGroupHarga.getResultList();
					
					List<SelectItem<String>> groupHargaResult = new ArrayList<>();
					
					for (Object[] o : objGroupPrice) {
						SelectItem<String> groupHarga = new SelectItem<String>((String) o[1], (String)o[2]);
						groupHargaResult.add(groupHarga);
					}
					result.setGroupHarga(groupHargaResult);
					result.setEdtTabGroupHarga("Beberapa");
				} else {
					result.setEdtTabGroupHarga("Semua");
				}
				//End Group Harga
				
				//Start Channel
				if(result.getData().getFlagChannel().equalsIgnoreCase("1")) {
					String sqlChannel = "";
					if(apHir.getFlagHierarki() == 0) {
						sqlChannel = " select kodeap, channel, typename as keterangan from "+t.getTenantId()+".ftable162 a " + 
								"  left join "+t.getTenantId()+".ftypeout b on a.channel = b.type where kodeap = '"+ap+"' ";
					}else {
						sqlChannel = "select kodeap, a.detailhierarki channel, typename as keterangan from "+t.getTenantId()+".ftable155_hir a " + 
								"   left join "+t.getTenantId()+".ftypeout b on a.detailhierarki = b.type where kodeap = '"+ap+"'" + 
								"   AND a.tglawal = '"+apHir.getTglAwal()+"'" + 
								"   AND a.berlakuhierarki = '"+apHir.getBerlakuHierarki()+"'" + 
								"   AND a.seqno = '"+apHir.getSeqno()+"'";
					}
					Query queryChannels = entityManager.createNativeQuery(sqlChannel);
					
					List<Object[]> objChannel = queryChannels.getResultList();
					
					List<SelectItem<String>> channelResult = new ArrayList<>();
					
					for (Object[] o : objChannel) {
						SelectItem<String> channel = new SelectItem<String>((String) o[1], (String)o[2]);
						channelResult.add(channel);
					}
					result.setChannels(channelResult);
					result.setEdtTabChannel("Beberapa");
				} else {
					result.setEdtTabChannel("Semua");
				}
				//End Channel
				

				//Start Team
				if(result.getData().getFlagTeam().equalsIgnoreCase("1")) {
					String sqlTeam = "select kodeap, a.team, teamname as keterangan from "+t.getTenantId()+".ftable163 a " + 
							" left join "+t.getTenantId()+".fteam b on a.team = b.team where kodeap = '"+ap+"'";
					Query queryTeam = entityManager.createNativeQuery(sqlTeam);
					List<Object[]> objTeam = queryTeam.getResultList();
					
					List<SelectItem<String>> teamResult = new ArrayList<>();
					
					for (Object[] o : objTeam) {
						SelectItem<String> team = new SelectItem<String>((String) o[1], (String)o[2]);
						teamResult.add(team);
					}
					result.setTeams(teamResult);
					result.setEdtTabTeam("Beberapa");
				}else {
					result.setEdtTabTeam("Semua");
				}
				//End Team
				
				
				//Start Salesforce
				if (result.getData().getFlagSalesforce().equalsIgnoreCase("1")) {
					String sqlSlsforce = "";
					if(apHir.getFlagHierarki() == 0) {
						sqlSlsforce = " select kodeap, a.slsforce as salesforce, data2 as keterangan from "+t.getTenantId()+".ftable164 a " + 
								"  left join "+t.getTenantId()+".ftabel10 b on a.slsforce = b.data1 where kodeap = '"+ap+"' ";
					}else {
						sqlSlsforce = " select kodeap, a.detailhierarki as salesforce, data2 as keterangan from "+t.getTenantId()+".ftable155_hir a " + 
								"   left join "+t.getTenantId()+".ftabel10 b on a.detailhierarki = b.data1 where kodeap = '"+ap+"' " + 
								"   AND a.tglawal = '"+apHir.getTglAwal()+"' " + 
								"   AND a.berlakuhierarki = '"+apHir.getBerlakuHierarki()+"' " + 
								"   AND a.seqno = '"+apHir.getSeqno()+"' ";
					}
					Query querySlsforce = entityManager.createNativeQuery(sqlSlsforce);
					List<Object[]> objSlsforce = querySlsforce.getResultList();
					
					List<SelectItem<String>> slsForceResult = new ArrayList<>();
					
					for (Object[] o : objSlsforce) {
						SelectItem<String> slsforce = new SelectItem<String>((String) o[1], (String)o[2]);
						slsForceResult.add(slsforce);
					}
					result.setSalesforces(slsForceResult);
					result.setEdtTabSlsForce("Beberapa");
				}else {
					result.setEdtTabSlsForce("Semua");
				}
				//End Salesforce
				

				//Start Group Disk
				if (result.getData().getFlagGrupDisk().equalsIgnoreCase("1")) {
					String sqlGroupDisk = "";
					if(apHir.getFlagHierarki() == 0) {
						sqlGroupDisk = " select kodeap, a.grupdisk, gdiscname as keterangan from "+t.getTenantId()+".ftable165 a " + 
								"  left join "+t.getTenantId()+".fgdisc b on a.grupdisk = b.gdisc where kodeap = '"+ap+"' ";
					}else {
						sqlGroupDisk = " select kodeap, a.detailhierarki grupdisk, gdiscname as keterangan from "+t.getTenantId()+".ftable155_hir a " + 
								"  left join "+t.getTenantId()+".fgdisc b on a.detailhierarki = b.gdisc where kodeap = '"+ap+"'" + 
								"  AND a.tglawal = '"+apHir.getTglAwal()+"' " + 
								"  AND a.berlakuhierarki = '"+apHir.getBerlakuHierarki()+"' " + 
								"  AND a.seqno = '"+apHir.getSeqno()+"' ";
					}
					Query queryGroupDisk = entityManager.createNativeQuery(sqlGroupDisk);
					List<Object[]> objGDisk = queryGroupDisk.getResultList();
					
					List<SelectItem<String>> groupDiskResult = new ArrayList<>();
					
					for (Object[] o : objGDisk) {
						SelectItem<String> gDisc = new SelectItem<String>((String) o[1], (String)o[2]);
						groupDiskResult.add(gDisc);
					}
					result.setGroupDisk(groupDiskResult);
					result.setEdtTabGroupDisc("Beberapa");
				}else {
					result.setEdtTabGroupDisc("Semua");
				}
				//End Group Disk
				

				//Start Spesifik Outlet
				if (result.getData().getFlagSpesifikasi().equalsIgnoreCase("1")) {
					String sqlSpesifikasi = "";
					if(apHir.getFlagHierarki() == 0) {
						sqlSpesifikasi = " select kodeap, a.spesifikasi, data2 as keterangan from "+t.getTenantId()+".ftable166 a " + 
								"  left join "+t.getTenantId()+".ftabel06 b on a.spesifikasi = b.data1 where kodeap = '"+ap+"'";
					}else {
						sqlSpesifikasi = "select kodeap, a.detailhierarki spesifikasi, data2 as keterangan from "+t.getTenantId()+".ftable155_hir a " + 
								"   left join "+t.getTenantId()+".ftabel06 b on a.detailhierarki = b.data1 where kodeap = '"+t.getTenantId()+"'" + 
								"   AND a.tglawal = '"+apHir.getTglAwal()+"'" + 
								"   AND a.berlakuhierarki = '"+apHir.getBerlakuHierarki()+"'" + 
								"   AND a.seqno = '"+apHir.getSeqno()+"'";
					}
					Query spesifikQuery = entityManager.createNativeQuery(sqlSpesifikasi);
					List<Object[]> objSpec = spesifikQuery.getResultList();
					
					List<SelectItem<String>> spesifikResult = new ArrayList<>();
					
					for (Object[] o : objSpec) {
						SelectItem<String> gDisc = new SelectItem<String>((String) o[1], (String)o[2]);
						spesifikResult.add(gDisc);
					}
					result.setOutletSpesifik(spesifikResult);
					result.setEdtTabSpecificOutlet("Beberapa");
				}else {
					result.setEdtTabSpecificOutlet("Semua");
				}
				//End Spesifik Outlet
			

				//Start Flag Syarat Product
				if (result.getData().getFlagSyaratProduk().equalsIgnoreCase("1")) {
					String sqlSyaratProduk = "";
					if(apHir.getFlagHierarki() == 0) {
						sqlSyaratProduk = "SELECT a.kodeap, a.kode, " + 
								" CASE " + 
								" WHEN b.syaratpilprod = 1 THEN (SELECT pcodename FROM "+t.getTenantId()+".fmaster WHERE pcode = a.kode) " + 
								" WHEN b.syaratpilprod = 2 THEN (SELECT sbra1name FROM "+t.getTenantId()+".ftsbrand1 WHERE prlin||brand||sbra1 = a.kode) " + 
								" WHEN b.syaratpilprod = 3 THEN (SELECT sbra2name FROM "+t.getTenantId()+".ftsbrand2 WHERE sbra2 = a.kode) " + 
								" WHEN b.syaratpilprod = 4 THEN (SELECT to_nchar(keterangan) FROM "+t.getTenantId()+".ftable171_h WHERE kodegrupprod = a.kode) " + 
								" END AS keterangan, decode(a.flag, 0, 'TIDAK', 1, 'YA') AS flag, decode(a.syaratqtyin, 1, '1. BESAR', 2, '2. TENGAH', 3, '3. KECIL', 4, '4. NILAI') AS syaratqtyin, a.nilai " + 
								" FROM "+t.getTenantId()+".ftable170 a JOIN "+t.getTenantId()+".ftable155 b ON a.kodeap = b.kodeap WHERE a.kodeap='"+ap+"'";
					}else {
						sqlSyaratProduk = " SELECT DISTINCT a.kodeap, a.kode, " + 
								"    CASE " + 
								"    WHEN b.syaratpilprod = 1 THEN (SELECT pcodename FROM "+t.getTenantId()+".fmaster WHERE pcode = a.kode) " + 
								"    WHEN b.syaratpilprod = 2 THEN (SELECT sbra1name FROM "+t.getTenantId()+".ftsbrand1 WHERE prlin||brand||sbra1 = a.kode) " + 
								"    WHEN b.syaratpilprod = 3 THEN (SELECT sbra2name FROM "+t.getTenantId()+".ftsbrand2 WHERE sbra2 = a.kode) " + 
								"    WHEN b.syaratpilprod = 4 THEN (SELECT to_nchar(keterangan) FROM "+t.getTenantId()+".ftable171_h WHERE kodegrupprod = a.kode) " + 
								"    END AS keterangan, decode(a.flag, 0, 'TIDAK', 1, 'YA') AS flag, decode(a.syaratqtyin, 1, '1. BESAR', 2, '2. TENGAH', 3, '3. KECIL', 4, '4. NILAI') AS syaratqtyin, a.nilai " + 
								"    FROM "+t.getTenantId()+".ftable170_hir a JOIN "+t.getTenantId()+".ftable155_hir b ON a.kodeap = b.kodeap AND a.tglawal = b.tglawal AND a.berlakuhierarki = b.berlakuhierarki AND a.detailhierarki = b.detailhierarki " + 
								"    WHERE a.kodeap='"+ap+"' " + 
								"    AND a.tglawal='"+apHir.getTglAwal()+"' " + 
								"    AND a.berlakuhierarki='"+apHir.getBerlakuHierarki()+"'" + 
								"    AND b.seqno = '"+apHir.getSeqno()+"'";
					}
					Query syaratProdQuery = entityManager.createNativeQuery(sqlSyaratProduk);
					List<Object[]> objSyaratProd = syaratProdQuery.getResultList();
					
					List<DiscSyaratGProdDto> syaratProdResult = new ArrayList<>();
					
					for (Object[] o : objSyaratProd) {
						DiscSyaratGProdDto syaratProd = new DiscSyaratGProdDto((String)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], ((BigDecimal)o[5]).doubleValue());
						syaratProdResult.add(syaratProd);
					}
					result.setSyaratGroupProd(syaratProdResult);
				}
				//End Flag Syarat Product
				
			//Finally Result
			return result;
	}

	@Override
	public List<DiscApBrowseDto> getPromoDiscApBrowse() {
		// TODO Auto-generated method stub
		List<DiscApBrowseDto> result = new ArrayList<>();
		
		String sql = "	SELECT kodeap, keterangan ,	"
				+"	       DECODE (tipe, 3, 'Promosi') AS tipe,	"
				+"	       TO_CHAR (tglawal, 'DD MON YYYY') AS Tgl_Mulai,	"
				+"	       TO_CHAR (tglakhir, 'DD MON YYYY') AS Tgl_Akhir,	"
				+"	       berlakuhierarki AS Hirarki, ROWNUM AS ID	"
				+"	  FROM (SELECT   kodeap, keterangan, tipe, tglawal, tglakhir,	"
				+"	                 NULL berlakuhierarki, 0 seqno	"
				+"	            FROM "+t.getTenantId()+".ftable155	"
				+"	           WHERE tipe = 3 "
				+"	        UNION ALL	"
				+"	        SELECT DISTINCT kodeap, keterangan, tipe, tglawal, tglakhir,	"
				+"	                        DECODE (berlakuhierarki,	"
				+"	                                100, '100. OUTLET',	"
				+"	                                200, '200. GROUP OUTLET',	"
				+"	                                300, '300. CHANNEL',	"
				+"	                                400, '400. SALESFORCE',	"
				+"	                                500, '500. SPESIFIKASI OUTLET',	"
				+"	                                600, '600. GROUP DISKON',	"
				+"	                                700, '700. GROUP HARGA',	"
				+"	                                800, '800. ALL'	"
				+"	                               ) berlakuhierarki,	"
				+"	                        seqno	"
				+"	                   FROM "+t.getTenantId()+".ftable155_hir	"
				+"	                  WHERE tipe = 3	"
				+"	               ORDER BY kodeap, seqno)	";
		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> rslt = query.getResultList();
		
		for (Object[] obj : rslt) {
			DiscApBrowseDto data = new DiscApBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3],
					(String) obj[4], (String) obj[5], ((BigDecimal) obj[6]).doubleValue());
			result.add(data);
		}
		
		return result;
	}

	@Override
	public DiscApHirDto getApHir(String ap) {
		// TODO Auto-generated method stub
		DiscApHirDto apHir =  new DiscApHirDto();
		String sqlHir ="	SELECT ROWNUM AS ID, kodeap, tglawal, berlakuhierarki, flaghierarki, seqno	"
				+"	  FROM (SELECT   kodeap, TO_CHAR (tglawal, 'DD MON YYYY') tglawal,	"
				+"	                 berlakuhierarki, 0 flaghierarki, 0 seqno	"
				+"	            FROM "+t.getTenantId()+".ftable155	"
				+"	           WHERE (tipe = 3) and kodeap='"+ap+"'	"
				+"	        UNION ALL	"
				+"	        SELECT DISTINCT kodeap, TO_CHAR (tglawal, 'DD MON YYYY') tglawal,	"
				+"	                        berlakuhierarki, 1 flaghierarki, seqno	"
				+"	                   FROM "+t.getTenantId()+".ftable155_hir	"
				+"	                  WHERE (tipe = 3) and kodeap='"+ap+"'	"
				+"	               ORDER BY kodeap, seqno)	";
		Query query = entityManager.createNativeQuery(sqlHir);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			apHir = new DiscApHirDto(((BigDecimal) obj[0]).intValue(), (String) obj[1], (String) obj[2], (String) obj[3],
					((BigDecimal) obj[4]).intValue(), ((BigDecimal) obj[5]).intValue());
		}
		
		return apHir;
	}

	@Override
	public List<DiskonBertingkatDto> getDiskonBertingkat(String ap) {
		// TODO Auto-generated method stub
		List<DiskonBertingkatDto> result = new ArrayList<>();
		String sql = " SELECT norange, kode, nilai FROM "+t.getTenantId()+".ftable168 WHERE kodeap = '"+ap+"' ORDER BY norange ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			DiskonBertingkatDto data = new DiskonBertingkatDto(((BigDecimal) obj[0]).doubleValue(), (String) obj[1], ((BigDecimal) obj[2]).doubleValue() );
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ApExcludeDto> getApExclude(String ap) {
		// TODO Auto-generated method stub
		List<ApExcludeDto> result = new ArrayList<>();
		String sql = " SELECT kodeexclude FROM "+t.getTenantId()+".ftable156 WHERE kodeap = '"+ap+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<String> hasil = query.getResultList();

		for ( String obj : hasil) {
			ApExcludeDto data = new ApExcludeDto(obj);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<DiscPromoDto> getDataPromo(String ap) {
		// TODO Auto-generated method stub

		DiscApHirDto apHir2 = getApHir(ap);
		
		String sqlPromo2="";
		if (apHir2.getFlagHierarki() == 0) {
		sqlPromo2 =" SELECT a.kodeap, a.seqno, a.pcode, b.pcodename AS keterangan "
					+"   FROM "+t.getTenantId()+".ftable158 a LEFT JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode "
					+"   WHERE kodeap = '"+ap+"' ORDER BY a.seqno  ";
		}else {
		sqlPromo2 ="SELECT DISTINCT a.kodeap, a.seqno, a.pcode, b.pcodename AS keterangan " 
					+" FROM "+t.getTenantId()+".ftable158_hir a LEFT JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode " 
					+" JOIN "+t.getTenantId()+".ftable155_hir c ON a.kodeap = c.kodeap AND a.tglawal = c.tglawal AND a.berlakuhierarki = c.berlakuhierarki AND a.detailhierarki = c.detailhierarki "  
					+" WHERE c.kodeap = '"+ap+"' " 
					+" AND c.tglawal = '"+apHir2.getTglAwal()+"' " 
					+" AND c.berlakuhierarki = '"+apHir2.getBerlakuHierarki()+"' " 
					+" AND c.seqno = "+apHir2.getSeqno()+" " 
					+" ORDER BY a.seqno ";
		}
		Query queryPromo = entityManager.createNativeQuery(sqlPromo2);
		
		List<Object[]> promo = queryPromo.getResultList();
		
		List<DiscPromoDto> resultt = new ArrayList<>(); 
		
		for (Object[] obj : promo) {
			DiscPromoDto data = new DiscPromoDto((String) obj[0], ((BigDecimal) obj[1]).doubleValue(), (String) obj[2], (String) obj[3]);
			resultt.add(data);
		}
		return resultt;	
	}

	@Override
	public void updateDataPromo(String kdAp, double seqNo, String pc) {
		// TODO Auto-generated method stub
		DiscApHirDto apHir2 = getApHir(kdAp);
		String sqlPrm="";
		if (apHir2.getFlagHierarki() == 0) {
			sqlPrm = " UPDATE "+t.getTenantId()+".ftable158 SET seqno = '"+seqNo+"' "
					+ " WHERE pcode = '"+pc+"' AND kodeap = '"+kdAp+"' ";
		}else {
			sqlPrm = " UPDATE "+t.getTenantId()+".ftable158 SET seqno = '"+seqNo+"' "
					+ " WHERE pcode = '"+pc+"' AND kodeap = '"+kdAp+"' ";
		}
		
		Query query = entityManager.createNativeQuery(sqlPrm);
		query.executeUpdate();
	}

}
