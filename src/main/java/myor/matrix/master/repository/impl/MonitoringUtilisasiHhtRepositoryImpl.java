package myor.matrix.master.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.MonitoringUtilisasiHhtDetail;
import myor.matrix.master.entity.MonitoringUtilisasiHhtFristLoadDto;
import myor.matrix.master.entity.MonitoringUtilisasiHhtRekap;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.MonitoringUtilisasiHhtRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MonitoringUtilisasiHhtRepositoryImpl implements MonitoringUtilisasiHhtRepository{

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<MonitoringUtilisasiHhtFristLoadDto> getDates(String tglGudang) {
		// TODO Auto-generated method stub
		String sql = " SELECT DISTINCT a.prdno, a.tahun, MAX (a.weekno) MAX, MIN (a.weekno) MIN,  " + 
				"             to_char(MIN (a.startweek),'DD MON YYYY') awal, to_char(MAX (a.endweek),'DD MON YYYY') akhir  " + 
				"             FROM "+t.getTenantId()+".fcycle2 a " + 
				"             INNER JOIN  " + 
				"             (SELECT prdno, tahun " + 
				"             FROM "+t.getTenantId()+".fcycle3 " + 
				"             WHERE cdate = '"+tglGudang+"') b  " + 
				"             ON a.tahun = b.tahun AND a.prdno = b.prdno " + 
				"             GROUP BY a.tahun, a.prdno ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object []> result = query.getResultList();
		return result.stream().map(o -> new MonitoringUtilisasiHhtFristLoadDto((BigDecimal) o[0], 
				(BigDecimal) o[1], 
				(BigDecimal) o[2],
				(BigDecimal) o[3],
				(String) o[4],
				(String) o[5]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SelectItem<String>> getDataSalesman() {
		// TODO Auto-generated method stub
		List<SelectItem<String>> resultList = new ArrayList<>();
		String sql = " select distinct slsno, (slsno||'-'||slsname) as slsman from (  " + 
				" SELECT s.slsno, s.slsname, t.data2  " + 
				" FROM  "+t.getTenantId()+".fsls s INNER JOIN "+t.getTenantId()+".ftabel12 t ON s.slsno = t.slsno  " + 
				" ) A order by slsno asc ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object []> result = query.getResultList();
		for (Object[] o : result) {
			SelectItem<String> data = new SelectItem<String>((String) o[1], (String) o[0]);
			resultList.add(data);
		}
		return resultList;
	}

	@Override
	public List<SelectItem<String>> getDataAlasan() {
		// TODO Auto-generated method stub
		List<SelectItem<String>> resultList = new ArrayList<>();
		String sql = " select KDALASAN, ALASAN from "+t.getTenantId()+".ftable01 where TIPEALASAN = '2' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object []> result = query.getResultList();
		for (Object[] o : result) {
			SelectItem<String> data = new SelectItem<String>((String) o[1], (String) o[0]);
			resultList.add(data);
		}
		return resultList;
	}

	@Override
	public List<MonitoringUtilisasiHhtRekap> getDataRekap(String tglAwal, String tglAkhir, String salesman,
			String tglGd, String periode, String pekan, String plhData, String sls, String altis, String plhAllKpl) {
		// TODO Auto-generated method stub
		List<MonitoringUtilisasiHhtRekap> result = new ArrayList<>();
		String sql = " SELECT " + 
				"	('Monitoring Utilisasi HHT (SUMMARY) ' || (select MEMOSTRING from "+t.getTenantId()+".FMEMO WHERE MEMONAMA='KODEDIST')||' - '|| " + 
				"	(select MEMOSTRING from "+t.getTenantId()+".FMEMO WHERE MEMONAMA='COMPANYNAME')) AS header, (SELECT to_char(sysdate,'DD MON YYYY') FROM dual) AS tglcetak, " + 
				"	(select to_char(MEMODATE,'DD MON YYYY') from "+t.getTenantId()+".FMEMO WHERE MEMONAMA='CADATE') AS tglgd,  " + 
				"	(SELECT to_char(xno) FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') AS rls, ('"+periode+"') AS periode, ('"+pekan+"') AS pekan, ('"+plhData+"') AS plhdata, " + 
				"	('"+sls+"') AS sls, ('"+altis+"') altis, " + 
				"	a.slsno || '-' || c.slsname AS salesman, " + 
				"	c.data04 || '-' || SUBSTR (d.data2,1,3) slsforce, " + 
				"	a.weekno, " + 
				"	a.targetcall, " + 
				"	a.SCAN, " + 
				"	(NVL (a.SCAN, " + 
				"	0) / NVL (a.targetcall, " + 
				"	0)) * 100 persenscan, " + 
				"	a.nonscan, " + 
				"	( NVL ( TO_NUMBER (NVL (a.targetcall, " + 
				"	0)) -  " + 
				"TO_NUMBER (NVL (a.SCAN, " + 
				"	0)), " + 
				"	0 ) / NVL (a.targetcall, " + 
				"	0) ) * 100 persennonscan, " + 
				"	nvl(b.b1,0) b1, " + 
				"	nvl(b.b2,0) b2, " + 
				"	nvl(b.b3,0) b3, " + 
				"	nvl(b.b4,0) b4, " + 
				"	nvl(b.b5,0) b5, " + 
				"	nvl(b.b6,0) b6, " + 
				"	nvl(b.b7,0) b7, " + 
				"	a.t, " + 
				"	a.noncall " + 
				"FROM " + 
				"	( " + 
				"	SELECT " + 
				"		slsno, " + 
				"		weekno, " + 
				"		COUNT (DISTINCT invdate || custno) targetcall, " + 
				"		SUM (SCAN) SCAN, " + 
				"		COUNT (DISTINCT  " + 
				"invdate || custno) - SUM (SCAN) nonscan, " + 
				"		SUM (flagnoncall) noncall, " + 
				"		SUM (flagmanual) t " + 
				"	FROM " + 
				"		( " + 
				"		SELECT " + 
				"			ab.*, " + 
				"			CASE " + 
				"				WHEN (((flagscan = 'Y' " + 
				"				AND st = 'S') ))                  " + 
				"                              THEN '1' " + 
				"				ELSE '0' " + 
				"			END AS SCAN, " + 
				"			CASE " + 
				"				WHEN ( ( jamin = jamout " + 
				"				OR (jamin IS NULL " + 
				"				AND jamout IS NULL) ) " + 
				"				AND invno  " + 
				" IS NULL " + 
				"				AND status = 'kpl' )                                        " + 
				"         THEN '1' " + 
				"				ELSE '0' " + 
				"			END AS flagnoncall, " + 
				"			CASE " + 
				"				WHEN ( (jamin = jamout " + 
				"				AND invno IS NOT NULL) " + 
				"				OR ( SUBSTR (jamin, " + 
				"				7, " + 
				"				2) = '00' " + 
				"				AND SUBSTR (jamout, " + 
				"				7, " + 
				"				2) = '00' " + 
				"				AND invno IS NOT NULL          " + 
				"                   ) " + 
				"				OR ( jamin IS NULL " + 
				"				AND jamout  " + 
				"IS NULL " + 
				"				AND invno IS NOT NULL )                                         " + 
				"        ) THEN '1' " + 
				"				ELSE '0' " + 
				"			END AS flagmanual " + 
				"		FROM " + 
				"			( " + 
				"			SELECT " + 
				"				a.*, " + 
				"				CASE " + 
				"					WHEN b.slsno IS NOT NULL THEN 'kpl' " + 
				"					ELSE 'nonkpl' " + 
				"				END AS status, " + 
				"				c.jamin, " + 
				"				c.jamout, " + 
				"				( NVL (( (SUBSTR (c.jamout, " + 
				"				1, " + 
				"				2) * 3600) + (SUBSTR (c.jamout, " + 
				"				4, " + 
				"				2) * 60)        " + 
				"          + (SUBSTR (c.jamout, " + 
				"				7, " + 
				"				2)) ), " + 
				"				0                    " + 
				"                             ) - NVL (( (SUBSTR (c.jamin, " + 
				"				1, " + 
				"				2) * 3600)         " + 
				" + (SUBSTR (c.jamin, " + 
				"				4, " + 
				"				2) * 60) + (SUBSTR (c.jamin, " + 
				"				7, " + 
				"				2)) ), " + 
				"				0 ) ) salling, " + 
				"				CASE " + 
				"					WHEN ( c.jamin = c.jamout " + 
				"					OR ( c.jamin IS NULL " + 
				"					AND c.jamout IS NULL ) " + 
				"					OR ( SUBSTR (c.jamin, " + 
				"					7, " + 
				"					2) = '00' " + 
				"					AND SUBSTR (c.jamout, " + 
				"					7, " + 
				"					2) = '00' )                              " + 
				"                   ) THEN 'T' " + 
				"					ELSE 'Y' " + 
				"				END AS flagscan, " + 
				"				d.st, " + 
				"				d.altis, " + 
				"				e.invno, " + 
				"				f.weekno " + 
				"			FROM " + 
				"				( " + 
				"				SELECT " + 
				"					DISTINCT slsno, " + 
				"					invdate, " + 
				"					custno " + 
				"				FROM " + 
				"					( " + 
				"					SELECT " + 
				"						* " + 
				"					FROM " + 
				"						( " + 
				"						SELECT " + 
				"							data2 slsno, " + 
				"							data1  " + 
				"invdate, " + 
				"							data3 custno " + 
				"						FROM " + 
				"							"+t.getTenantId()+".ftabel46 " + 
				"						WHERE " + 
				"							data1 >= '"+tglAwal+"' " + 
				"							AND data1 <=       " + 
				"                               '"+tglAkhir+"' " + 
				"							AND data2 IN ("+salesman+")) " + 
				"					WHERE " + 
				"						invdate <= '"+tglGd+"'					 ";
				if(plhAllKpl.equalsIgnoreCase("all")) {
				sql+="				UNION ALL " + 
					"					SELECT " + 
					"						h.slsno, " + 
					"						h.invdate, " + 
					"						h.custno " + 
					"					FROM " + 
					"						"+t.getTenantId()+".fjual_h h " + 
					"					INNER JOIN  " + 
					""+t.getTenantId()+".fjual_d1 d ON " + 
					"						h.orderno = d.orderno " + 
					"						AND  " + 
					"h.slsno = d.slsno " + 
					"						AND h.transtype = d.transtype " + 
					"					WHERE " + 
					"						h.invdate >=    " + 
					"                '"+tglAwal+"' " + 
					"						AND h.invdate <= '"+tglAkhir+"' " + 
					"						AND h.invno IS NOT NULL " + 
					"						AND h.transtype = 'F' " + 
					"						AND h.slsno IN ("+salesman+") ";
				}
				sql+=" )) a " + 
				"			LEFT JOIN ( " + 
				"				SELECT " + 
				"					* " + 
				"				FROM " + 
				"					( " + 
				"					SELECT " + 
				"						data2 slsno, " + 
				"						data1 invdate, " + 
				"						data3 custno " + 
				"					FROM " + 
				"						"+t.getTenantId()+".ftabel46 " + 
				"					WHERE " + 
				"						data1 >= '"+tglAwal+"' " + 
				"						AND data1 <= '"+tglAkhir+"' " + 
				"						AND  " + 
				"data2 IN ("+salesman+")) " + 
				"				WHERE " + 
				"					invdate <= '"+tglGd+"' ) b ON " + 
				"				a.slsno = b.slsno " + 
				"				AND  " + 
				"a.invdate = b.invdate " + 
				"				AND a.custno = b.custno " + 
				"			LEFT JOIN ( " + 
				"				SELECT " + 
				"					DISTINCT  " + 
				"(data4) custno, " + 
				"					data2 slsno, " + 
				"					data3 tgl, " + 
				"					MAX (data5) jamin, " + 
				"					MAX (data6) jamout " + 
				"				FROM " + 
				"					"+t.getTenantId()+".ftabel38 " + 
				"				WHERE " + 
				"					data3 >= '"+tglAwal+"' " + 
				"					AND data3 <= '"+tglAkhir+"' " + 
				"					AND data2 IN ("+salesman+") " + 
				"				GROUP BY " + 
				"					data4, " + 
				"					data2, " + 
				"					data3) c ON " + 
				"				a.slsno = c.slsno " + 
				"				AND a.invdate = c.tgl " + 
				"				AND a.custno = c.custno " + 
				"			LEFT JOIN ( " + 
				"				SELECT " + 
				"					DISTINCT  " + 
				"a.slsno, " + 
				"					a.outlet, " + 
				"					a.tgl, " + 
				"					MAX (a.jamin) jamin, " + 
				"					MAX (a.jamout) jamout, " + 
				"					b.st, " + 
				"					b.altis " + 
				"				FROM " + 
				"					( " + 
				"					SELECT " + 
				"						DISTINCT a.filenm, " + 
				"						a.slsno, " + 
				"						a.tgl, " + 
				"						a.outlet, " + 
				"						a.jamin, " + 
				"						a.jamout, " + 
				"						a.transno " + 
				"					FROM " + 
				"						( " + 
				"						SELECT " + 
				"							a.* " + 
				"						FROM " + 
				"							( " + 
				"							SELECT " + 
				"								DISTINCT a.filenm, " + 
				"								a.slsno, " + 
				"								a.uploaddate tgl, " + 
				"								c.outlet, " + 
				"								MAX (c.transno) transno, " + 
				"								MAX (c.jamin) jamin, " + 
				"								MAX (c.jamout) jamout " + 
				"							FROM " + 
				"								( " + 
				"								SELECT " + 
				"									filenm, " + 
				"									slsno, " + 
				"									uploaddate " + 
				"								FROM " + 
				"									"+t.getTenantId()+".ftable210_h " + 
				"								WHERE " + 
				"									uploaddate >= '"+tglAwal+"' " + 
				"									AND uploaddate <= '"+tglAkhir+"' " + 
				"									AND slsno IN ("+salesman+")) a " + 
				"							LEFT JOIN ( " + 
				"								SELECT " + 
				"									d.filenm, " + 
				"									d.trntype, " + 
				"									d.fullcon, " + 
				"									SUBSTR (d.fullcon, " + 
				"									15, " + 
				"									7) AS outlet, " + 
				"									SUBSTR (d.fullcon, " + 
				"									10, " + 
				"									4) AS transno, " + 
				"									SUBSTR (d.fullcon, " + 
				"									34, " + 
				"									8) AS jamin, " + 
				"									SUBSTR (d.fullcon, " + 
				"									43, " + 
				"									8) AS jamout " + 
				"								FROM " + 
				"									"+t.getTenantId()+".ftable210_h h " + 
				"								INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"									h.filenm = d.filenm " + 
				"								WHERE " + 
				"									h.uploaddate >= '"+tglAwal+"' " + 
				"									AND h.uploaddate <= '"+tglAkhir+"' " + 
				"									AND h.slsno IN ("+salesman+") " + 
				"										AND d.trntype = 'FTABEL38') c ON " + 
				"								a.filenm = c.filenm " + 
				"							LEFT JOIN ( " + 
				"								SELECT " + 
				"									d.* " + 
				"								FROM " + 
				"									"+t.getTenantId()+".ftable210_h h " + 
				"								INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"									h.filenm = d.filenm " + 
				"								WHERE " + 
				"									h.uploaddate >= '"+tglAwal+"' " + 
				"									AND h.uploaddate <= '"+tglAkhir+"' " + 
				"									AND h.slsno IN ("+salesman+") " + 
				"										AND (d.trntype = 'FTABEL38a' " + 
				"											OR d.trntype = 'FTABEL38A')) aa ON " + 
				"								c.filenm = aa.filenm " + 
				"								AND c.transno = aa.docid " + 
				"							WHERE " + 
				"								c.fullcon IS NOT NULL " + 
				"								AND a.uploaddate >= '"+tglAwal+"' " + 
				"								AND a.uploaddate <= '"+tglAkhir+"' " + 
				"								AND a.slsno IN ("+salesman+") " + 
				"									AND SUBSTR (aa.fullcon,16,1) = 'S' " + 
				"								GROUP BY " + 
				"									a.filenm, " + 
				"									a.slsno, " + 
				"									a.uploaddate, " + 
				"									c.outlet) a " + 
				"						LEFT JOIN                 " + 
				"                           ( " + 
				"							SELECT " + 
				"								d.filenm, " + 
				"								d.trntype, " + 
				"								d.fullcon, " + 
				"								SUBSTR (d.fullcon, " + 
				"								11, " + 
				"								4) AS transno, " + 
				"								SUBSTR (d.fullcon, " + 
				"								16, " + 
				"								2) AS alur " + 
				"							FROM " + 
				"								"+t.getTenantId()+".ftable210_h h " + 
				"							INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"								h.filenm = d.filenm " + 
				"							WHERE " + 
				"								h.uploaddate >= '"+tglAwal+"' " + 
				"								AND h.uploaddate <= '"+tglAkhir+"' " + 
				"								AND h.slsno IN ("+salesman+") " + 
				"									AND (d.trntype = 'FTABEL38b' " + 
				"										OR d.trntype = 'FTABEL38B')) c ON " + 
				"							a.filenm = c.filenm " + 
				"							AND a.transno = c.transno) a " + 
				"					LEFT JOIN  ( " + 
				"						SELECT " + 
				"							d.filenm, " + 
				"							d.trntype, " + 
				"							d.fullcon, " + 
				"							SUBSTR (d.fullcon, " + 
				"							11, " + 
				"							4) AS transno, " + 
				"							SUBSTR (d.fullcon, " + 
				"							16, " + 
				"							1) AS st, " + 
				"							CASE " + 
				"								WHEN SUBSTR (d.fullcon, " + 
				"								16, " + 
				"								1) = 'S' THEN ' ' " + 
				"								ELSE SUBSTR (d.fullcon, " + 
				"								18, " + 
				"								2) " + 
				"							END AS altis " + 
				"						FROM " + 
				"							"+t.getTenantId()+".ftable210_h h " + 
				"						INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"							h.filenm = d.filenm " + 
				"						WHERE " + 
				"							h.uploaddate >= '"+tglAwal+"' " + 
				"							AND h.uploaddate <= '"+tglAkhir+"' " + 
				"							AND h.slsno IN ("+salesman+") " + 
				"								AND (d.trntype = 'FTABEL38a' " + 
				"									OR d.trntype = 'FTABEL38A')) b ON " + 
				"						a.filenm = b.filenm " + 
				"					GROUP BY " + 
				"						a.filenm, " + 
				"						a.slsno, " + 
				"						a.tgl, " + 
				"						a.outlet, " + 
				"						a.jamin, " + 
				"						a.jamout, " + 
				"						a.transno) a " + 
				"				LEFT JOIN ( " + 
				"					SELECT " + 
				"						d.filenm, " + 
				"						d.trntype, " + 
				"						d.fullcon, " + 
				"						SUBSTR (d.fullcon, " + 
				"						11, " + 
				"						4) AS transno, " + 
				"						SUBSTR (d.fullcon, " + 
				"						16, " + 
				"						1) AS st, " + 
				"						CASE " + 
				"							WHEN SUBSTR (d.fullcon, " + 
				"							16, " + 
				"							1) = 'S'                    " + 
				"             THEN ' ' " + 
				"							ELSE SUBSTR (d.fullcon, " + 
				"							18, " + 
				"							2) " + 
				"						END AS altis " + 
				"					FROM " + 
				"						"+t.getTenantId()+".ftable210_h h " + 
				"					INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"						h.filenm = d.filenm " + 
				"					WHERE " + 
				"						h.uploaddate >= '"+tglAwal+"' " + 
				"						AND h.uploaddate <= '"+tglAkhir+"' " + 
				"						AND h.slsno IN ("+salesman+") " + 
				"							AND (d.trntype = 'FTABEL38a' " + 
				"								OR d.trntype = 'FTABEL38A')) b ON " + 
				"					a.filenm = b.filenm " + 
				"					AND a.transno = b.transno " + 
				"				GROUP BY " + 
				"					a.slsno, " + 
				"					a.outlet, " + 
				"					a.tgl, " + 
				"					b.st, " + 
				"					b.altis) d ON " + 
				"				a.slsno = d.slsno " + 
				"				AND a.invdate = d.tgl " + 
				"				AND a.custno = d.outlet " + 
				"			LEFT JOIN ( " + 
				"				SELECT " + 
				"					DISTINCT h.slsno, " + 
				"					h.invdate, " + 
				"					h.custno, " + 
				"					MAX (h.invno) invno " + 
				"				FROM " + 
				"					"+t.getTenantId()+".fjual_h h " + 
				"				INNER JOIN "+t.getTenantId()+".fjual_d1 d ON " + 
				"					h.orderno = d.orderno " + 
				"					AND h.slsno = d.slsno " + 
				"					AND h.transtype = d.transtype " + 
				"				WHERE " + 
				"					h.invdate >= '"+tglAwal+"' " + 
				"					AND h.invdate <= '"+tglAkhir+"' " + 
				"					AND h.transtype = 'F' " + 
				"					AND h.slsno IN ("+salesman+") " + 
				"				GROUP BY " + 
				"					h.slsno, " + 
				"					h.invdate, " + 
				"					h.custno) e ON " + 
				"				a.slsno = e.slsno " + 
				"				AND a.invdate = e.invdate " + 
				"				AND a.custno = e.custno " + 
				"			LEFT JOIN "+t.getTenantId()+".fcycle3 f ON " + 
				"				a.invdate = f.cdate ) ab) " + 
				"	GROUP BY " + 
				"		slsno, " + 
				"		weekno) a " + 
				"LEFT JOIN                                           " + 
				"        ( " + 
				"	SELECT " + 
				"		a.slsno, " + 
				"		a.weekno, " + 
				"		SUM (a.b1) b1, " + 
				"		SUM (a.b2) b2, " + 
				"		SUM (a.b3) b3, " + 
				"		SUM (a.b4) b4, " + 
				"		SUM (a.b5) b5, " + 
				"		SUM (a.b6) b6, " + 
				"		SUM (a.b7) b7 " + 
				"	FROM " + 
				"		( " + 
				"		SELECT " + 
				"			a.slsno, " + 
				"			a.weekno, " + 
				"			CASE " + 
				"				WHEN flag = 'B1' THEN flag_count " + 
				"				ELSE NULL " + 
				"			END AS b1, " + 
				"			CASE " + 
				"				WHEN flag = 'B2' THEN flag_count " + 
				"				ELSE NULL " + 
				"			END AS b2, " + 
				"			CASE " + 
				"				WHEN flag =  " + 
				" 'B3' THEN flag_count " + 
				"				ELSE NULL " + 
				"			END AS b3, " + 
				"			CASE " + 
				"				WHEN flag = 'B4' THEN flag_count " + 
				"				ELSE NULL " + 
				"			END AS b4, " + 
				"			CASE " + 
				"				WHEN flag = 'B5' THEN flag_count " + 
				"				ELSE NULL " + 
				"			END AS b5, " + 
				"			CASE " + 
				"				WHEN flag = 'B6' THEN flag_count " + 
				"				ELSE NULL " + 
				"			END AS b6, " + 
				"			CASE " + 
				"				WHEN flag = 'B7' THEN flag_count " + 
				"				ELSE NULL " + 
				"			END AS b7 " + 
				"		FROM " + 
				"			( " + 
				"			SELECT " + 
				"				DISTINCT a.slsno, " + 
				"				a.weekno, " + 
				"				a.flag, " + 
				"				SUM (hit) OVER (PARTITION BY a.slsno, " + 
				"				a.weekno, " + 
				"				a.flag) flag_count " + 
				"			FROM " + 
				"				( " + 
				"				SELECT " + 
				"					DISTINCT slsno, " + 
				"					custno custno, " + 
				"					invdate, " + 
				"					weekno, " + 
				"					altis flag, " + 
				"					'1' AS hit " + 
				"				FROM " + 
				"					( " + 
				"					SELECT " + 
				"						a.*, " + 
				"						CASE " + 
				"							WHEN b.slsno IS NOT NULL THEN 'kpl' " + 
				"							ELSE 'nonkpl' " + 
				"						END                      " + 
				"       AS status, " + 
				"						c.jamin, " + 
				"						c.jamout, " + 
				"						CASE " + 
				"							WHEN ( c.jamin = c.jamout " + 
				"								OR ( c.jamin IS NULL " + 
				"									AND c.jamout IS NULL            " + 
				") ) THEN 'T' " + 
				"							ELSE 'Y' " + 
				"						END  " + 
				"                           AS flagscan, " + 
				"						d.st, " + 
				"						d.altis, " + 
				"						'1'      " + 
				"                       AS hit, " + 
				"						e.invno, " + 
				"						f.weekno " + 
				"					FROM " + 
				"						( " + 
				"						SELECT " + 
				"							DISTINCT slsno, " + 
				"							invdate, " + 
				"							custno " + 
				"						FROM " + 
				"							( " + 
				"							SELECT " + 
				"								* " + 
				"							FROM " + 
				"								( " + 
				"								SELECT " + 
				"									data2 slsno, " + 
				"									data1 invdate, " + 
				"									data3 custno " + 
				"								FROM " + 
				"									"+t.getTenantId()+".ftabel46 " + 
				"								WHERE " + 
				"									data1 >= '"+tglAwal+"' " + 
				"									AND data1 <= '"+tglAkhir+"' " + 
				"									AND data2 IN ("+salesman+")) " + 
				"							WHERE " + 
				"								invdate <= '"+tglGd+"'						 ";
				if(plhAllKpl.equalsIgnoreCase("all")) {
					sql+="						UNION ALL " + 
					"							SELECT " + 
					"								h.slsno, " + 
					"								h.invdate, " + 
					"								h.custno " + 
					"							FROM " + 
					"								"+t.getTenantId()+".fjual_h h " + 
					"							INNER JOIN "+t.getTenantId()+".fjual_d1 d ON " + 
					"								h.orderno = d.orderno " + 
					"								AND h.slsno = d.slsno " + 
					"								AND h.transtype = d.transtype " + 
					"							WHERE " + 
					"								h.invdate >= '"+tglAwal+"' " + 
					"								AND h.invdate <= '"+tglAkhir+"' " + 
					"								AND h.invno IS NOT NULL " + 
					"								AND h.transtype = 'F' " + 
					"								AND h.slsno IN ("+salesman+")							 ";
				}
				sql+="								)) a " + 
				"					LEFT JOIN ( " + 
				"						SELECT " + 
				"							* " + 
				"						FROM " + 
				"							( " + 
				"							SELECT " + 
				"								data2 slsno, " + 
				"								data1 invdate, " + 
				"								data3 custno " + 
				"							FROM " + 
				"								"+t.getTenantId()+".ftabel46 " + 
				"							WHERE " + 
				"								data1 >= '"+tglAwal+"' " + 
				"								AND data1 <= '"+tglAkhir+"' " + 
				"								AND data2 IN ("+salesman+")) " + 
				"						WHERE " + 
				"							invdate <= '"+tglGd+"' ) b ON " + 
				"						a.slsno = b.slsno " + 
				"						AND a.invdate = b.invdate " + 
				"						AND a.custno = b.custno " + 
				"					LEFT JOIN ( " + 
				"						SELECT " + 
				"							DISTINCT (data4) custno, " + 
				"							data2 slsno, " + 
				"							data3 tgl, " + 
				"							MAX (data5) jamin, " + 
				"							MAX (data6) jamout " + 
				"						FROM " + 
				"							"+t.getTenantId()+".ftabel38 " + 
				"						WHERE " + 
				"							data3 >= '"+tglAwal+"' " + 
				"							AND data3 <= '"+tglAkhir+"' " + 
				"							AND data2 IN ("+salesman+") " + 
				"						GROUP BY " + 
				"							data4, " + 
				"							data2, " + 
				"							data3) c ON " + 
				"						a.slsno = c.slsno " + 
				"						AND a.invdate = c.tgl " + 
				"						AND a.custno = c.custno " + 
				"					LEFT JOIN ( " + 
				"						SELECT " + 
				"							a.*, " + 
				"							b.st, " + 
				"							b.altis " + 
				"						FROM " + 
				"							( " + 
				"							SELECT " + 
				"								DISTINCT filenm, " + 
				"								slsno, " + 
				"								tgl, " + 
				"								outlet, " + 
				"								MAX (transno) transno, " + 
				"								MAX (jamin) jamin, " + 
				"								MAX (jamout) jamout " + 
				"							FROM " + 
				"								( " + 
				"								SELECT " + 
				"									a.* " + 
				"								FROM " + 
				"									( " + 
				"									SELECT " + 
				"										DISTINCT a.filenm, " + 
				"										a.slsno, " + 
				"										a.uploaddate tgl, " + 
				"										c.outlet, " + 
				"										MAX (c.transno) transno, " + 
				"										MAX (c.jamin) jamin, " + 
				"										MAX (c.jamout) jamout " + 
				"									FROM " + 
				"										( " + 
				"										SELECT " + 
				"											filenm, " + 
				"											slsno, " + 
				"											uploaddate " + 
				"										FROM " + 
				"											"+t.getTenantId()+".ftable210_h " + 
				"										WHERE " + 
				"											uploaddate >= '"+tglAwal+"' " + 
				"											AND uploaddate <= '"+tglAkhir+"' " + 
				"											AND slsno IN ("+salesman+")) a " + 
				"									LEFT JOIN     " + 
				"                                               ( " + 
				"										SELECT " + 
				"											d.filenm, " + 
				"											d.trntype, " + 
				"											d.fullcon, " + 
				"											SUBSTR (d.fullcon, " + 
				"											15, " + 
				"											7) AS outlet, " + 
				"											SUBSTR (d.fullcon, " + 
				"											10, " + 
				"											4) AS transno, " + 
				"											SUBSTR (d.fullcon, " + 
				"											34, " + 
				"											8) AS jamin, " + 
				"											SUBSTR (d.fullcon, " + 
				"											43, " + 
				"											8) AS jamout " + 
				"										FROM " + 
				"											"+t.getTenantId()+".ftable210_h h " + 
				"										INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"											h.filenm = d.filenm " + 
				"										WHERE " + 
				"											h.uploaddate >= '"+tglAwal+"' " + 
				"											AND h.uploaddate <= '"+tglAkhir+"' " + 
				"											AND h.slsno IN ("+salesman+") " + 
				"												AND d.trntype = 'FTABEL38') c ON " + 
				"										a.filenm = c.filenm " + 
				"									LEFT JOIN ( " + 
				"										SELECT " + 
				"											d.* " + 
				"										FROM " + 
				"											"+t.getTenantId()+".ftable210_h h " + 
				"										INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"											h.filenm = d.filenm " + 
				"										WHERE " + 
				"											h.uploaddate >= '"+tglAwal+"' " + 
				"											AND h.uploaddate <= '"+tglAkhir+"' " + 
				"											AND h.slsno IN ("+salesman+") " + 
				"												AND (trntype = 'FTABEL38a' " + 
				"													OR trntype = 'FTABEL38A')) aa ON " + 
				"										c.filenm = aa.filenm " + 
				"										AND c.transno = aa.docid " + 
				"									WHERE " + 
				"										c.fullcon IS NOT NULL " + 
				"										AND a.uploaddate >= '"+tglAwal+"' " + 
				"										AND a.uploaddate <= '"+tglAkhir+"' " + 
				"										AND a.slsno IN ("+salesman+") " + 
				"											AND SUBSTR (aa.fullcon, " + 
				"											16, " + 
				"											1) = 'M' " + 
				"										GROUP BY " + 
				"											a.filenm, " + 
				"											a.slsno, " + 
				"											a.uploaddate, " + 
				"											c.outlet, " + 
				"											aa.fullcon) a " + 
				"								LEFT JOIN                " + 
				"                   ( " + 
				"									SELECT " + 
				"										DISTINCT a.filenm, " + 
				"										a.slsno, " + 
				"										a.uploaddate tgl, " + 
				"										c.outlet, " + 
				"										MAX (c.transno) transno, " + 
				"										MAX (c.jamin) jamin, " + 
				"										MAX (c.jamout) jamout " + 
				"									FROM " + 
				"										( " + 
				"										SELECT " + 
				"											filenm, " + 
				"											slsno, " + 
				"											uploaddate " + 
				"										FROM " + 
				"											"+t.getTenantId()+".ftable210_h " + 
				"										WHERE " + 
				"											uploaddate >= '"+tglAwal+"' " + 
				"											AND uploaddate <= '"+tglAkhir+"' " + 
				"											AND slsno IN ("+salesman+")) a " + 
				"									LEFT JOIN ( " + 
				"										SELECT " + 
				"											d.filenm, " + 
				"											d.trntype, " + 
				"											d.fullcon, " + 
				"											SUBSTR (d.fullcon, " + 
				"											15, " + 
				"											7) AS outlet, " + 
				"											SUBSTR (d.fullcon, " + 
				"											10, " + 
				"											4) AS transno, " + 
				"											SUBSTR (d.fullcon, " + 
				"											34, " + 
				"											8) AS jamin, " + 
				"											SUBSTR (d.fullcon, " + 
				"											43, " + 
				"											8) AS jamout " + 
				"										FROM " + 
				"											"+t.getTenantId()+".ftable210_h h " + 
				"										INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"											h.filenm = d.filenm " + 
				"										WHERE " + 
				"											h.uploaddate >= '"+tglAwal+"' " + 
				"											AND h.uploaddate <= '"+tglAkhir+"' " + 
				"											AND h.slsno IN ("+salesman+") " + 
				"											AND d.trntype = 'FTABEL38') c ON " + 
				"										a.filenm = c.filenm " + 
				"									LEFT JOIN ( " + 
				"										SELECT " + 
				"											d.* " + 
				"										FROM " + 
				"											"+t.getTenantId()+".ftable210_h h " + 
				"										INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"											h.filenm = d.filenm " + 
				"										WHERE " + 
				"											h.uploaddate >= '"+tglAwal+"' " + 
				"											AND h.uploaddate <= '"+tglAkhir+"' " + 
				"											AND h.slsno IN ("+salesman+") " + 
				"												AND (trntype = 'FTABEL38a' " + 
				"													OR trntype = 'FTABEL38A')) aa                        " + 
				"                            		ON c.filenm = aa.filenm " + 
				"										AND c.transno = aa.docid " + 
				"									WHERE " + 
				"										c.fullcon IS NOT NULL " + 
				"										AND a.uploaddate >= '"+tglAwal+"' " + 
				"										AND a.uploaddate <= '"+tglAkhir+"' " + 
				"										AND a.slsno IN ("+salesman+") " + 
				"											AND SUBSTR (aa.fullcon,16,1) = 'S' " + 
				"										GROUP BY " + 
				"											a.filenm, " + 
				"											a.slsno, " + 
				"											a.uploaddate, " + 
				"											c.outlet, " + 
				"											aa.fullcon) b ON " + 
				"									a.filenm = b.filenm " + 
				"									AND a.slsno = b.slsno " + 
				"									AND a.tgl = b.tgl " + 
				"									AND a.outlet = b.outlet " + 
				"								WHERE " + 
				"									b.filenm IS NULL) " + 
				"							GROUP BY " + 
				"								filenm, " + 
				"								slsno, " + 
				"								tgl, " + 
				"								outlet) a " + 
				"						LEFT JOIN ( " + 
				"							SELECT " + 
				"								d.filenm, " + 
				"								d.trntype, " + 
				"								d.fullcon, " + 
				"								SUBSTR (d.fullcon, " + 
				"								11, " + 
				"								4) AS transno, " + 
				"								SUBSTR (d.fullcon, " + 
				"								16, " + 
				"								1) AS st, " + 
				"								CASE " + 
				"									WHEN SUBSTR (d.fullcon, " + 
				"									16, " + 
				"									1) = 'S' THEN ' ' " + 
				"									ELSE SUBSTR (d.fullcon, " + 
				"									18, " + 
				"									2) " + 
				"								END AS altis " + 
				"							FROM " + 
				"								"+t.getTenantId()+".ftable210_h h " + 
				"							INNER JOIN "+t.getTenantId()+".ftable210_d d ON " + 
				"								h.filenm = d.filenm " + 
				"							WHERE " + 
				"								h.uploaddate >= '"+tglAwal+"' " + 
				"								AND h.uploaddate <= '"+tglAkhir+"' " + 
				"								AND h.slsno IN ("+salesman+") " + 
				"									AND (d.trntype = 'FTABEL38a' " + 
				"										OR d.trntype = 'FTABEL38A')) b ON " + 
				"							a.filenm = b.filenm " + 
				"							AND a.transno = b.transno " + 
				"						LEFT OUTER JOIN ( " + 
				"							SELECT " + 
				"								a.slsno, " + 
				"								a.outlet, " + 
				"								a.uploaddate, " + 
				"								MAX (a.transno) transno " + 
				"							FROM " + 
				"								( " + 
				"								SELECT " + 
				"									b.slsno, " + 
				"									a.docid AS transno, " + 
				"									b.uploaddate, " + 
				"									SUBSTR (a.fullcon,15,7) AS outlet " + 
				"								FROM " + 
				"									"+t.getTenantId()+".ftable210_d a " + 
				"								LEFT OUTER JOIN "+t.getTenantId()+".ftable210_h b             " + 
				"               					ON a.filenm = b.filenm " + 
				"								WHERE " + 
				"									(trntype = 'FTABEL38') " + 
				"										AND b.slsno IN ("+salesman+") " + 
				"											AND b.uploaddate >= '"+tglAwal+"' " + 
				"											AND b.uploaddate <= '"+tglAkhir+"') a " + 
				"							GROUP BY " + 
				"								a.slsno, " + 
				"								a.outlet, " + 
				"								a.uploaddate) t ON " + 
				"							a.slsno = t.slsno " + 
				"							AND a.tgl = t.uploaddate " + 
				"							AND a.outlet = t.outlet " + 
				"							AND a.transno = t.transno " + 
				"						WHERE " + 
				"							t.transno IS NOT NULL) d ON " + 
				"						a.slsno = d.slsno " + 
				"						AND a.invdate = d.tgl " + 
				"						AND a.custno = d.outlet " + 
				"					LEFT JOIN ( " + 
				"						SELECT " + 
				"							DISTINCT h.slsno, " + 
				"							h.invdate, " + 
				"							h.custno, " + 
				"							h.invno " + 
				"						FROM " + 
				"							"+t.getTenantId()+".fjual_h h " + 
				"						INNER JOIN "+t.getTenantId()+".fjual_d1 d ON " + 
				"							h.orderno = d.orderno " + 
				"							AND h.slsno = d.slsno " + 
				"							AND h.transtype = d.transtype " + 
				"						WHERE " + 
				"							h.invdate >= '"+tglAwal+"' " + 
				"							AND h.invdate <= '"+tglAkhir+"' " + 
				"							AND h.transtype = 'F' " + 
				"							AND h.slsno IN ("+salesman+")) e ON " + 
				"						a.slsno = e.slsno " + 
				"							AND a.invdate = e.invdate " + 
				"							AND a.custno = e.custno " + 
				"						LEFT JOIN "+t.getTenantId()+".fcycle3 f ON " + 
				"							a.invdate = f.cdate " + 
				"						WHERE " + 
				"							c.jamin <> c.jamout) " + 
				"				WHERE " + 
				"					slsno IN ("+salesman+") " + 
				"						AND invdate >= '"+tglAwal+"' " + 
				"						AND invdate <= '"+tglAkhir+"' " + 
				"						AND st IS NOT NULL) a " + 
				"			WHERE " + 
				"				a.flag IN      " + 
				"           ('B1', 'B2', 'B3', 'B4', 'B5', 'B6', 'B7') ) a ) a " + 
				"	GROUP BY " + 
				"		a.slsno, " + 
				"		a.weekno ) b ON " + 
				"	a.slsno = b.slsno " + 
				"	AND a.weekno = b.weekno " + 
				"LEFT JOIN  " + 
				""+t.getTenantId()+".fsls c ON " + 
				"	a.slsno = c.slsno " + 
				"LEFT JOIN "+t.getTenantId()+".ftabel10 d ON " + 
				"	c.data04 = d.data1 " + 
				"ORDER BY " + 
				"	a.slsno, " + 
				"	a.weekno ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] o : resultList) {
			MonitoringUtilisasiHhtRekap data = new MonitoringUtilisasiHhtRekap((String) o[0], 
					(String) o[1], 
					(String) o[2], 
					(String) o[3], 
				    String.valueOf((char) o[4]), 
					(String) o[5], (String) o[6], (String) o[7], (String) o[8], (String) o[9], (String) o[10], (BigDecimal) o[11], (BigDecimal) o[12], (BigDecimal) o[13], (BigDecimal) o[14], 
					(BigDecimal) o[15], (BigDecimal) o[16], (BigDecimal) o[17], (BigDecimal) o[18], (BigDecimal) o[19], (BigDecimal) o[20], (BigDecimal) o[21], (BigDecimal) o[22], (BigDecimal) o[23],
					(BigDecimal) o[24], (BigDecimal) o[25]);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<MonitoringUtilisasiHhtDetail> getDataDetail(String tglAwal, String tglAkhir, String salesman,
			String tglGd, String periode, String pekan, String plhData, String sls, String flag, String calll,
			String tpScan, String plhAllKpl) {
		// TODO Auto-generated method stub
		List<MonitoringUtilisasiHhtDetail> result = new ArrayList<>();
		String sql = " SELECT ('Monitoring Utilisasi HHT (DETAIL) ' || (select MEMOSTRING from "+t.getTenantId()+".FMEMO WHERE MEMONAMA='KODEDIST')||' - '|| " + 
				"    (select MEMOSTRING from "+t.getTenantId()+".FMEMO WHERE MEMONAMA='COMPANYNAME')) AS header, (SELECT to_char(sysdate,'DD MON YYYY') FROM dual) AS tglcetak, " + 
				"    (select to_char(MEMODATE,'DD MON YYYY') from "+t.getTenantId()+".FMEMO WHERE MEMONAMA='CADATE') AS tglgd,  " + 
				"    (SELECT to_char(xno) FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'ARWANA_UPDATE') AS rls, ('"+periode+"') AS periode, ('"+pekan+"') AS pekan, ('"+plhData+"') AS plhdata, " + 
				"    ('"+sls+"') AS sls, cast('"+flag+"' as varchar2(10)) flagg, cast('"+calll+"' as varchar2(10)) calll, cast('"+tpScan+"' as varchar2(10)) tpscan,  " + 
				"       ROW_NUMBER() OVER (PARTITION BY a.salesman, a.slsforce, b.tanggal ORDER BY a.salesman, b.tanggal, a.custno) no_urut,  " + 
				"       a.salesman, a.slsforce, b.tanggal, b.hari, a.custno, a.custname, a.flag, a.invoice,  " + 
				"       a.jamin, a.jamout, a.CALL, a.hht, a.typeofscan, " + 
				"       CASE WHEN (to_char(a.alasan) IS NULL OR to_char(a.alasan) = '') THEN '-' ELSE to_char(a.alasan) END AS alasan " + 
				"  FROM (SELECT   a.slsno, a.slsno || '-' || g.slsname salesman, " + 
				"                 g.data04 || '-' || SUBSTR (h.data2, 1, 3) slsforce, " + 
				"                 a.invdate, a.custno, i.custname, " + 
				"                 CASE " + 
				"                    WHEN b.slsno IS NOT NULL " + 
				"                       THEN 'OK' " + 
				"                    ELSE '?' " + 
				"                 END AS flag, e.invoice, SUBSTR (c.jamin, 1, 5) jamin, " + 
				"                 SUBSTR (c.jamout, 1, 5) jamout, " + 
				"                 CASE " + 
				"                    WHEN (  NVL ((  (SUBSTR (c.jamout, 1, 2) * 3600) " + 
				"                                  + (SUBSTR (c.jamout, 4, 2) * 60) " + 
				"                                  + (SUBSTR (c.jamout, 7, 2)) " + 
				"                                 ), " + 
				"                                 0 " + 
				"                                ) " + 
				"                          - NVL ((  (SUBSTR (c.jamin, 1, 2) * 3600) " + 
				"                                  + (SUBSTR (c.jamin, 4, 2) * 60) " + 
				"                                  + (SUBSTR (c.jamin, 7, 2)) " + 
				"                                 ), " + 
				"                                 0 " + 
				"                                ) <> '0' " + 
				"                         ) " + 
				"                     OR (e.invoice IS NOT NULL) " + 
				"                       THEN 'CALL' " + 
				"                    ELSE 'NONCALL' " + 
				"                 END AS CALL, " + 
				"                 CASE " + 
				"                    WHEN (   c.jamin = c.jamout " + 
				"                          OR (c.jamin IS NULL AND c.jamout IS NULL) " + 
				"                          OR (    SUBSTR (c.jamin, 7, 2) = '00' " + 
				"                              AND SUBSTR (c.jamout, 7, 2) = '00' " + 
				"                             ) " + 
				"                         ) " + 
				"                       THEN 'T' " + 
				"                    ELSE 'Y' " + 
				"                 END AS hht, " + 
				"                 d.st typeofscan, d.altis, d.altis || '-' || f.alasan alasan " + 
				"            FROM (SELECT DISTINCT slsno, invdate, custno " + 
				"                             FROM (SELECT * " + 
				"                                     FROM (SELECT data2 slsno, data1 invdate, " + 
				"                                                  data3 custno " + 
				"                                             FROM "+t.getTenantId()+".ftabel46 " + 
				"                                            WHERE data1 >= '"+tglAwal+"' " + 
				"                                              AND data1 <= '"+tglAkhir+"' " + 
				"                                              AND data2 IN ("+salesman+")) " + 
				"                                    WHERE invdate <= '"+tglGd+"' ";
		if(plhAllKpl.equalsIgnoreCase("all")) {
					sql+="                                   UNION ALL " + 
					"                                   SELECT h.slsno, h.invdate, h.custno " + 
					"                                     FROM "+t.getTenantId()+".fjual_h h INNER JOIN "+t.getTenantId()+".fjual_d1 d " + 
					"                                          ON h.orderno = d.orderno " + 
					"                                        AND h.slsno = d.slsno " + 
					"                                        AND h.transtype = d.transtype " + 
					"                                    WHERE h.invdate >= '"+tglAwal+"' " + 
					"                                      AND h.invdate <= '"+tglAkhir+"' " + 
					"                                      AND h.invno IS NOT NULL " + 
					"                                      AND h.transtype = 'F' " + 
					"                                      AND h.slsno IN ("+salesman+") ";
		}
				sql+=" )) a " + 
				"                 LEFT JOIN " + 
				"                 (SELECT * " + 
				"                    FROM (SELECT data2 slsno, data1 invdate, data3 custno " + 
				"                            FROM "+t.getTenantId()+".ftabel46 " + 
				"                           WHERE data1 >= '"+tglAwal+"' " + 
				"                             AND data1 <= '"+tglAkhir+"' " + 
				"                             AND data2 IN ("+salesman+")) " + 
				"                   WHERE invdate <= '"+tglGd+"') b " + 
				"                 ON a.slsno = b.slsno " + 
				"               AND a.invdate = b.invdate " + 
				"               AND a.custno = b.custno " + 
				"                 LEFT JOIN " + 
				"                 (SELECT DISTINCT (data4) custno, data2 slsno, data3 tgl, " + 
				"                                  MAX (data5) jamin, MAX (data6) jamout " + 
				"                             FROM "+t.getTenantId()+".ftabel38 " + 
				"                            WHERE data3 >= '"+tglAwal+"' " + 
				"                              AND data3 <= '"+tglAkhir+"' " + 
				"                              AND data2 IN ("+salesman+") " + 
				"                         GROUP BY data4, data2, data3) c " + 
				"                 ON a.slsno = c.slsno " + 
				"               AND a.invdate = c.tgl " + 
				"               AND a.custno = c.custno " + 
				"                 LEFT JOIN " + 
				"                 (SELECT DISTINCT a.slsno, a.outlet, a.tgl, " + 
				"                                  MAX (a.jamin) jamin, MAX (a.jamout) jamout, " + 
				"                                  b.st, b.altis " + 
				"                             FROM (SELECT DISTINCT a.filenm, a.slsno, " + 
				"                                                   a.uploaddate tgl, c.outlet, " + 
				"                                                   MAX (c.transno) transno, " + 
				"                                                   MAX (c.jamin) jamin, " + 
				"                                                   MAX (c.jamout) jamout " + 
				"                                              FROM (SELECT filenm, slsno, " + 
				"                                                           uploaddate " + 
				"                                                      FROM "+t.getTenantId()+".ftable210_h " + 
				"                                                     WHERE uploaddate >= " + 
				"                                                                 '"+tglAwal+"' " + 
				"                                                       AND uploaddate <= " + 
				"                                                                 '"+tglAkhir+"' " + 
				"                                                       AND slsno IN " + 
				"                                                              ("+salesman+")) a " + 
				"                                                   LEFT JOIN " + 
				"                                                   (SELECT d.filenm, " + 
				"                                                           d.trntype, " + 
				"                                                           d.fullcon, " + 
				"                                                           SUBSTR " + 
				"                                                              (d.fullcon, " + 
				"                                                               15, " + 
				"                                                               7 " + 
				"                                                              ) AS outlet, " + 
				"                                                           SUBSTR " + 
				"                                                              (d.fullcon, " + 
				"                                                               10, " + 
				"                                                               4 " + 
				"                                                              ) AS transno, " + 
				"                                                           SUBSTR " + 
				"                                                              (d.fullcon, " + 
				"                                                               34, " + 
				"                                                               8 " + 
				"                                                              ) AS jamin, " + 
				"                                                           SUBSTR " + 
				"                                                              (d.fullcon, " + 
				"                                                               43, " + 
				"                                                               8 " + 
				"                                                              ) AS jamout " + 
				"                                                      FROM "+t.getTenantId()+".ftable210_h h INNER JOIN "+t.getTenantId()+".ftable210_d d " + 
				"                                                           ON h.filenm = d.filenm " + 
				"                                                     WHERE h.uploaddate >= '"+tglAwal+"' " + 
				"                                                       AND h.uploaddate <= '"+tglAkhir+"' " + 
				"                                                       AND h.slsno IN ("+salesman+") " + 
				"                                                       AND d.trntype = 'FTABEL38') c " + 
				"                                                   ON a.filenm = c.filenm " + 
				"                                                   LEFT JOIN " + 
				"                                                   (SELECT d.* " + 
				"                                                      FROM "+t.getTenantId()+".ftable210_h h INNER JOIN "+t.getTenantId()+".ftable210_d d " + 
				"                                                           ON h.filenm = d.filenm " + 
				"                                                     WHERE h.uploaddate >= '"+tglAwal+"' " + 
				"                                                       AND h.uploaddate <= '"+tglAkhir+"' " + 
				"                                                       AND h.slsno IN ("+salesman+") " + 
				"                                                       AND ( d.trntype = 'FTABEL38a' " + 
				"                                                            OR d.trntype = 'FTABEL38A' " + 
				"                                                           )) aa " + 
				"                                                   ON c.filenm = aa.filenm " + 
				"                                                 AND c.transno = aa.docid " + 
				"                                             WHERE c.fullcon IS NOT NULL " + 
				"                                               AND a.uploaddate >= '"+tglAwal+"' " + 
				"                                               AND a.uploaddate <= '"+tglAkhir+"' " + 
				"                                               AND a.slsno IN ("+salesman+") " + 
				"                                               AND SUBSTR (aa.fullcon, 16, 1) = 'S' " + 
				"                                          GROUP BY a.filenm, " + 
				"                                                   a.slsno, " + 
				"                                                   a.uploaddate, " + 
				"                                                   c.outlet, " + 
				"                                                   aa.fullcon " + 
				"                                   UNION ALL " + 
				"                                   SELECT DISTINCT filenm, slsno, tgl, outlet, " + 
				"                                                   MAX (transno) transno, " + 
				"                                                   MAX (jamin) jamin, " + 
				"                                                   MAX (jamout) jamout " + 
				"                                              FROM (SELECT a.* " + 
				"                                                      FROM (SELECT DISTINCT a.filenm, " + 
				"                                                                            a.slsno, " + 
				"                                                                            a.uploaddate " + 
				"                                                                               tgl, " + 
				"                                                                            c.outlet, " + 
				"                                                                            MAX " + 
				"                                                                               (c.transno " + 
				"                                                                               ) " + 
				"                                                                               transno, " + 
				"                                                                            MAX " + 
				"                                                                               (c.jamin " + 
				"                                                                               ) " + 
				"                                                                               jamin, " + 
				"                                                                            MAX " + 
				"                                                                               (c.jamout " + 
				"                                                                               ) " + 
				"                                                                               jamout " + 
				"                                                                       FROM (SELECT filenm, " + 
				"                                                                                    slsno, " + 
				"                                                                                    uploaddate " + 
				"                                                                               FROM "+t.getTenantId()+".ftable210_h " + 
				"                                                                              WHERE uploaddate >= '"+tglAwal+"' " + 
				"                                                                                AND uploaddate <= '"+tglAkhir+"' " + 
				"                                                                                AND slsno IN ("+salesman+")) a " + 
				"                                                                            LEFT JOIN " + 
				"                                                                            (SELECT d.filenm, " + 
				"                                                                                    d.trntype, " + 
				"                                                                                    d.fullcon, " + 
				"                                                                                    SUBSTR " + 
				"                                                                                       (d.fullcon, " + 
				"                                                                                        15, " + 
				"                                                                                        7 " + 
				"                                                                                       ) " + 
				"                                                                                       AS outlet, " + 
				"                                                                                    SUBSTR " + 
				"                                                                                       (d.fullcon, " + 
				"                                                                                        10, " + 
				"                                                                                        4 " + 
				"                                                                                       ) " + 
				"                                                                                       AS transno, " + 
				"                                                                                    SUBSTR " + 
				"                                                                                       (d.fullcon, " + 
				"                                                                                        34, " + 
				"                                                                                        8 " + 
				"                                                                                       ) " + 
				"                                                                                       AS jamin, " + 
				"                                                                                    SUBSTR " + 
				"                                                                                       (d.fullcon, " + 
				"                                                                                        43, " + 
				"                                                                                        8 " + 
				"                                                                                       ) " + 
				"                                                                                       AS jamout " + 
				"                                                                               FROM "+t.getTenantId()+".ftable210_h h INNER JOIN "+t.getTenantId()+".ftable210_d d " + 
				"                                                                                    ON h.filenm = d.filenm " + 
				"                                                                              WHERE h.uploaddate >= '"+tglAwal+"' " + 
				"                                                                                AND h.uploaddate <= '"+tglAkhir+"' " + 
				"                                                                                AND h.slsno IN ("+salesman+") " + 
				"                                                                                AND d.trntype = 'FTABEL38') c " + 
				"                                                                            ON a.filenm = c.filenm " + 
				"                                                                            LEFT JOIN " + 
				"                                                                            (SELECT d.* " + 
				"                                                                               FROM "+t.getTenantId()+".ftable210_h h INNER JOIN "+t.getTenantId()+".ftable210_d d " + 
				"                                                                                    ON h.filenm = d.filenm " + 
				"                                                                              WHERE h.uploaddate >= '"+tglAwal+"' " + 
				"                                                                                AND h.uploaddate <= '"+tglAkhir+"' " + 
				"                                                                                AND h.slsno IN ("+salesman+") " + 
				"                                                                                AND (   d.trntype = 'FTABEL38a' " + 
				"                                                                                     OR d.trntype = 'FTABEL38A' " + 
				"                                                                                    )) aa " + 
				"                                                                            ON c.filenm = " + 
				"                                                                                 aa.filenm " + 
				"                                                                          AND c.transno = " + 
				"                                                                                 aa.docid " + 
				"                                                                      WHERE c.fullcon IS NOT NULL " + 
				"                                                                        AND a.uploaddate >= '"+tglAwal+"' " + 
				"                                                                        AND a.uploaddate <= '"+tglAkhir+"' " + 
				"                                                                        AND a.slsno IN ("+salesman+") " + 
				"                                                                        AND SUBSTR " + 
				"                                                                               (aa.fullcon, " + 
				"                                                                                16, " + 
				"                                                                                1 " + 
				"                                                                               ) = " + 
				"                                                                               'M' " + 
				"                                                                   GROUP BY a.filenm, " + 
				"                                                                            a.slsno, " + 
				"                                                                            a.uploaddate, " + 
				"                                                                            c.outlet, " + 
				"                                                                            aa.fullcon) a " + 
				"                                                           LEFT JOIN " + 
				"                                                           (SELECT DISTINCT a.filenm, " + 
				"                                                                            a.slsno, " + 
				"                                                                            a.uploaddate " + 
				"                                                                               tgl, " + 
				"                                                                            c.outlet, " + 
				"                                                                            MAX " + 
				"                                                                               (c.transno " + 
				"                                                                               ) " + 
				"                                                                               transno, " + 
				"                                                                            MAX " + 
				"                                                                               (c.jamin " + 
				"                                                                               ) " + 
				"                                                                               jamin, " + 
				"                                                                            MAX " + 
				"                                                                               (c.jamout " + 
				"                                                                               ) " + 
				"                                                                               jamout " + 
				"                                                                       FROM (SELECT filenm, " + 
				"                                                                                    slsno, " + 
				"                                                                                    uploaddate " + 
				"                                                                               FROM "+t.getTenantId()+".ftable210_h " + 
				"                                                                              WHERE uploaddate >= '"+tglAwal+"' " + 
				"                                                                                AND uploaddate <= '"+tglAkhir+"' " + 
				"                                                                                AND slsno IN ("+salesman+")) a " + 
				"                                                                            LEFT JOIN " + 
				"                                                                            (SELECT d.filenm, " + 
				"                                                                                    d.trntype, " + 
				"                                                                                    d.fullcon, " + 
				"                                                                                    SUBSTR " + 
				"                                                                                       (d.fullcon, " + 
				"                                                                                        15, " + 
				"                                                                                        7 " + 
				"                                                                                       ) " + 
				"                                                                                       AS outlet, " + 
				"                                                                                    SUBSTR " + 
				"                                                                                       (d.fullcon, " + 
				"                                                                                        10, " + 
				"                                                                                        4 " + 
				"                                                                                       ) " + 
				"                                                                                       AS transno, " + 
				"                                                                                    SUBSTR " + 
				"                                                                                       (d.fullcon, " + 
				"                                                                                        34, " + 
				"                                                                                        8 " + 
				"                                                                                       ) " + 
				"                                                                                       AS jamin, " + 
				"                                                                                    SUBSTR " + 
				"                                                                                       (d.fullcon, " + 
				"                                                                                        43, " + 
				"                                                                                        8 " + 
				"                                                                                       ) " + 
				"                                                                                       AS jamout " + 
				"                                                                               FROM "+t.getTenantId()+".ftable210_h h INNER JOIN "+t.getTenantId()+".ftable210_d d " + 
				"                                                                                    ON h.filenm = d.filenm " + 
				"                                                                              WHERE h.uploaddate >= '"+tglAwal+"' " + 
				"                                                                                AND h.uploaddate <= '"+tglAkhir+"' " + 
				"                                                                                AND h.slsno IN ("+salesman+") " + 
				"                                                                                AND d.trntype = 'FTABEL38') c " + 
				"                                                                            ON a.filenm = c.filenm " + 
				"                                                                            LEFT JOIN " + 
				"                                                                            (SELECT d.* " + 
				"                                                                               FROM "+t.getTenantId()+".ftable210_h h INNER JOIN "+t.getTenantId()+".ftable210_d d " + 
				"                                                                                    ON h.filenm = d.filenm " + 
				"                                                                              WHERE h.uploaddate >= '"+tglAwal+"' " + 
				"                                                                                AND h.uploaddate <= '"+tglAkhir+"' " + 
				"                                                                                AND h.slsno IN " + 
				"                                                                                       ("+salesman+") " + 
				"                                                                                AND (   d.trntype = 'FTABEL38a' " + 
				"                                                                                     OR d.trntype = 'FTABEL38A' " + 
				"                                                                                    )) aa " + 
				"                                                                            ON c.filenm = aa.filenm " + 
				"                                                                          AND c.transno = aa.docid " + 
				"                                                                      WHERE c.fullcon IS NOT NULL " + 
				"                                                                        AND a.uploaddate >= '"+tglAwal+"' " + 
				"                                                                        AND a.uploaddate <= '"+tglAkhir+"' " + 
				"                                                                        AND a.slsno IN ("+salesman+") " + 
				"                                                                        AND SUBSTR " + 
				"                                                                               (aa.fullcon, " + 
				"                                                                                16, " + 
				"                                                                                1 " + 
				"                                                                               ) = " + 
				"                                                                               'S' " + 
				"                                                                   GROUP BY a.filenm, " + 
				"                                                                            a.slsno, " + 
				"                                                                            a.uploaddate, " + 
				"                                                                            c.outlet, " + 
				"                                                                            aa.fullcon) b " + 
				"                                                           ON a.filenm = b.filenm " + 
				"                                                         AND a.slsno = b.slsno " + 
				"                                                         AND a.tgl = b.tgl " + 
				"                                                         AND a.outlet = b.outlet " + 
				"                                                     WHERE b.filenm IS NULL) " + 
				"                                          GROUP BY filenm, slsno, tgl, outlet) a " + 
				"                                  LEFT JOIN " + 
				"                                  (SELECT d.filenm, d.trntype, d.fullcon, " + 
				"                                          SUBSTR (d.fullcon, 11, " + 
				"                                                  4) AS transno, " + 
				"                                          SUBSTR (d.fullcon, 16, 1) AS st, " + 
				"                                          CASE " + 
				"                                             WHEN SUBSTR (d.fullcon, " + 
				"                                                          16, " + 
				"                                                          1 " + 
				"                                                         ) = 'S' " + 
				"                                                THEN ' ' " + 
				"                                             ELSE SUBSTR (d.fullcon, 18, 2) " + 
				"                                          END AS altis " + 
				"                                     FROM "+t.getTenantId()+".ftable210_h h INNER JOIN "+t.getTenantId()+".ftable210_d d " + 
				"                                          ON h.filenm = d.filenm " + 
				"                                    WHERE h.uploaddate >= '"+tglAwal+"' " + 
				"                                      AND h.uploaddate <= '"+tglAkhir+"' " + 
				"                                      AND h.slsno IN ("+salesman+") " + 
				"                                      AND (   trntype = 'FTABEL38a' " + 
				"                                           OR trntype = 'FTABEL38A' " + 
				"                                          )) b " + 
				"                                  ON a.filenm = b.filenm " + 
				"                                AND a.transno = b.transno " + 
				"                         GROUP BY a.slsno, a.outlet, a.tgl, b.st, b.altis) d " + 
				"                 ON a.slsno = d.slsno " + 
				"               AND a.invdate = d.tgl " + 
				"               AND a.custno = d.outlet " + 
				"                 LEFT JOIN " + 
				"                 (SELECT DISTINCT h.slsno, h.invdate, h.custno, " + 
				"                                  MAX (h.invno) invno, SUM (d.amount) invoice " + 
				"                             FROM "+t.getTenantId()+".fjual_h h INNER JOIN "+t.getTenantId()+".fjual_d1 d " + 
				"                                  ON h.orderno = d.orderno " + 
				"                                AND h.slsno = d.slsno " + 
				"                                AND h.transtype = d.transtype " + 
				"                            WHERE h.invdate >= '"+tglAwal+"' " + 
				"                              AND h.invdate <= '"+tglAkhir+"' " + 
				"                              AND h.transtype = 'F' " + 
				"                              AND h.slsno IN ("+salesman+") " + 
				"                         GROUP BY h.slsno, h.invdate, h.custno) e " + 
				"                 ON a.slsno = e.slsno " + 
				"               AND a.invdate = e.invdate " + 
				"               AND a.custno = e.custno " + 
				"                 LEFT JOIN " + 
				"                 (SELECT * " + 
				"                    FROM "+t.getTenantId()+".ftable01 " + 
				"                   WHERE tipealasan = '2') f ON d.altis = f.kdalasan " + 
				"                 LEFT JOIN "+t.getTenantId()+".fsls g ON a.slsno = g.slsno " + 
				"                 LEFT JOIN "+t.getTenantId()+".ftabel10 h ON g.data04 = h.data1 " + 
				"                 LEFT JOIN "+t.getTenantId()+".fcustmst i ON a.custno = i.custno " + 
				"        ORDER BY a.slsno, a.invdate) a " + 
				"       LEFT JOIN " + 
				"       (SELECT ROWNUM AS nomer, tahun, SUBSTR (hari, 1, 3) hari, cdate, " + 
				"               SUBSTR (tanggal, 1, 6) tanggal, prdno, weekno, " + 
				"               CASE " + 
				"                  WHEN workflag = 'Y' " + 
				"                     THEN 'KERJA' " + 
				"                  WHEN workflag = 'T' " + 
				"                     THEN 'TIDAK' " + 
				"                  ELSE workflag " + 
				"               END AS status " + 
				"          FROM (SELECT   tahun, " + 
				"                         TO_CHAR (cdate, " + 
				"                                  'DAY', " + 
				"                                  'nls_date_language=English' " + 
				"                                 ) hari, " + 
				"                         cdate, TO_CHAR (cdate, 'DD MON YYYY') tanggal, prdno, " + 
				"                         weekno, workflag " + 
				"                    FROM "+t.getTenantId()+".fcycle3 " + 
				"                   WHERE cdate >= '"+tglAwal+"' AND cdate <= '"+tglAkhir+"' " + 
				"                ORDER BY cdate)) b ON a.invdate = b.cdate ";
		if((!flag.equalsIgnoreCase("ALL")) && (calll.equalsIgnoreCase("ALL")) && (tpScan.equalsIgnoreCase("ALL"))) {
			sql+= " WHERE flag = '"+flag+"' ";
		}
		if((!flag.equalsIgnoreCase("ALL")) && (!calll.equalsIgnoreCase("ALL")) && (!tpScan.equalsIgnoreCase("ALL"))) {
			sql+= " WHERE flag = '"+flag+"' AND CALL = '"+calll+"' AND typeofscan = '"+tpScan+"' ";
		}
		if((!flag.equalsIgnoreCase("ALL")) && (calll.equalsIgnoreCase("ALL")) && (!tpScan.equalsIgnoreCase("ALL"))) {
			sql+= " WHERE flag = '"+flag+"' AND typeofscan = '"+tpScan+"' ";
		}
		if((!flag.equalsIgnoreCase("ALL")) && (!calll.equalsIgnoreCase("ALL")) && (tpScan.equalsIgnoreCase("ALL"))) {
			sql+= " WHERE flag = '"+flag+"' AND CALL = '"+calll+"' ";
		}
		if((flag.equalsIgnoreCase("ALL")) && (!calll.equalsIgnoreCase("ALL")) && (tpScan.equalsIgnoreCase("ALL"))) {
			sql+= " WHERE CALL = '"+calll+"' ";
		}
		if((flag.equalsIgnoreCase("ALL")) && (!calll.equalsIgnoreCase("ALL")) && (!tpScan.equalsIgnoreCase("ALL"))) {
			sql+= " WHERE CALL = '"+calll+"' AND typeofscan = '"+tpScan+"' ";
		}
		if((flag.equalsIgnoreCase("ALL")) && (calll.equalsIgnoreCase("ALL")) && (!tpScan.equalsIgnoreCase("ALL"))) {
			sql+= " WHERE typeofscan = '"+tpScan+"' ";
		}
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object []> resultList = query.getResultList();
		for (Object[] o : resultList) {
			MonitoringUtilisasiHhtDetail data = new MonitoringUtilisasiHhtDetail((String) o[0], (String) o[1], (String) o[2], (String) o[3], String.valueOf((char) o[4]), (String) o[5],
					(String) o[6], 
					(String) o[7], 
					(String) o[8], 
					(String) o[9], 
					(String) o[10], 
					(BigDecimal) o[11], 
					(String) o[12], 
					(String) o[13], 
					(String) o[14], 
					(String) o[15],
					(String) o[16], (String) o[17], (String) o[18], (BigDecimal) o[19],
					(String) o[20], 
					(String) o[21], 
					(String) o[22], 
					String.valueOf((char) o[23]), 
					(String) o[24], 
					(String) o[25]);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public String cekFcycle2(String year, String period) {
		// TODO Auto-generated method stub
		String rslt = "";
		
		String sql = " select tahun from "+t.getTenantId()+".fcycle2 where tahun = '"+year+"' and prdno = '"+period+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> cek = query.getResultList();
		if (cek.size() > 0) {
			rslt = "Aman";
		}else {
			rslt = "Gagal";
		}
		
		return rslt;
	}
	
	
}
