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

import myor.matrix.master.entity.DataSalesmanDto;
import myor.matrix.master.entity.MSalesmanBrandChoosen;
import myor.matrix.master.entity.MSalesmanChoosenDto;
import myor.matrix.master.entity.MSalesmanKolektor;
import myor.matrix.master.entity.MSalesmanProductChoosen;
import myor.matrix.master.entity.SalesforceBrowseDto;
import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.MSalesmanRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class MSalesmanRepositoryImpl implements MSalesmanRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
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
	public String getDateNowBySystem(){
		String sql = "select to_char(sysdate, 'DD MON YYYY') tgl from "+t.getTenantId()+".dual";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
	
	@Override
	public String getDateWarehouse(){
		String sql = "Select to_char(MEMODATE,'DD MON YYYY') AS warehouse_date from "+t.getTenantId()+".FMEMO where MEMONAMA='CADATE'";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
	
	@Override
	public String getWeek() {
		// TODO Auto-generated method stub
		String sql = "Select memoint from "+t.getTenantId()+".FMEMO where MEMONAMA='PEKAN'";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return ((BigDecimal)obj).toString();
	}

	@Override
	public String getPeriod() {
		// TODO Auto-generated method stub
		String sql = "select MEMOINT from "+t.getTenantId()+".FMEMO WHERE MEMONAMA='PERIODE'";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return ((BigDecimal)obj).toString();
	}

	@Override
	public String getYear() {
		// TODO Auto-generated method stub
		String sql = "select MEMOINT from "+t.getTenantId()+".FMEMO WHERE MEMONAMA='TAHUN'";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return ((BigDecimal)obj).toString();
	}
	
	@Override
	public BigDecimal getSaran() {
		// TODO Auto-generated method stub
		String sql = "SELECT xsaran "
				+ "  FROM (SELECT MAX (xnomor) + 1 AS xsaran "
				+ "          FROM (SELECT 'fsls' AS xtable, MAX (slsno) AS xnomor "
				+ "                  FROM "+t.getTenantId()+".fsls "
				+ "                 WHERE slsno > '100000' AND slsno < '999999' "
				+ "                UNION ALL "
				+ "                SELECT 'femployee' AS xtable, MAX (empno) AS xnomor "
				+ "                  FROM "+t.getTenantId()+".femployee "
				+ "                 WHERE empno > '100000' AND empno < '999999' "
				+ "                UNION ALL "
				+ "                SELECT 'fsls_upload' AS xtable, MAX (slsno) AS xnomor "
				+ "                  FROM "+t.getTenantId()+".fsls_upload "
				+ "                 WHERE slsno > '100000' AND slsno < '999999')) "
				+ " WHERE xsaran < '999999'";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (BigDecimal)obj;
	}
	

	@Override
	public List<SelectItem<String>> getListTeam() {
		// TODO Auto-generated method stub
		
		List<SelectItem<String>> result = new ArrayList<>();
		String sql = "select team, teamname from "+t.getTenantId()+".FTEAM ORDER BY TEAM";
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> resultList = query.getResultList();

			return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
		.collect(Collectors.toList());
	}
	
	@Override
	public String getRange() {
		// TODO Auto-generated method stub
		String sql = "SELECT * "
				+ "  FROM (SELECT kdsls, rangeawal, (SELECT memoint AS rangeakhir "
				+ "                                    FROM "+t.getTenantId()+".fmemo "
				+ "                                   WHERE memonama = 'XSLSNOTO') AS rangeakhir "
				+ "          FROM (SELECT '' AS kdsls, memoint AS rangeawal "
				+ "                  FROM "+t.getTenantId()+".fmemo "
				+ "                 WHERE memonama = 'XSLSNOFROM')) a";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}
	
	
	
	@Override
	public List<SalesmanBrowseDto> getListSalesmanSelectItem() {
		// TODO Auto-generated method stub
		List<SalesmanBrowseDto> result = new ArrayList<>();
		String sql = " SELECT a.slsno, a.slsname, "
				+ "	a.team || ' - ' || ( "
				+ "	SELECT teamname "
				+ "	FROM "+t.getTenantId()+".fteam "
				+ "	WHERE team = a.team) team, "
				+ "	CASE "
				+ "		WHEN a.kg = '00' THEN TO_NCHAR('00 - Gudang Utama ') "
				+ "		ELSE a.kg || ' - ' ||( "
				+ "		SELECT kgname "
				+ "		FROM "+t.getTenantId()+".flokgd "
				+ "		WHERE kg = a.kg) "
				+ "	END gudang, "
				+ "	CASE "
				+ "		WHEN a.oprtype = 'C' THEN 'C - CANVAS' "
				+ "		WHEN a.oprtype = 'S' THEN 'S - SHOP SALES' "
				+ "		WHEN a.oprtype = 'T' THEN 'T - ORDER TAKING' "
				+ "	END oprtype, "
				+ "	to_char(a.transdate, 'dd MON yyyy') AS tgl_transaksi "
				+ "FROM "+t.getTenantId()+".fsls  a "
				+ "ORDER BY slsno ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SalesmanBrowseDto data = new SalesmanBrowseDto((String) obj[0], (String) obj[1], (String) obj[4],
					(String) obj[2], null, (String) obj[5], null, (String) obj[3]);
			result.add(data);
		}
		
		return result;
	}
	

	@Override
	public List<SelectItem<String>> getListRayon() {
		// TODO Auto-generated method stub
		String sql = "select data1, data2 from "+t.getTenantId()+".FTabel11";
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> resultList = query.getResultList();

			return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
		.collect(Collectors.toList());
	}
	
	@Override
	public List<SalesforceBrowseDto> getListSalesforce() {
		// TODO Auto-generated method stub
		List<SalesforceBrowseDto> result = new ArrayList<>();
		String sql = " select data1 AS value, data2 AS label, data3 as flag, to_char(data4) AS seq from "+t.getTenantId()+".FTabel10 ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) {
			SalesforceBrowseDto data = new SalesforceBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3]);
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<Object[]> getListPejabat() {
		// TODO Auto-generated method stub
		String sql = "Select * from (  Select A.PEJKODE K4,A.PEJNAME N4,B.PEJKODE K3, B.PEJNAME N3  , C.PEJKODE K2, C.PEJNAME N2, D.PEJKODE K1,  "
				+ "D.PEJNAME N1  From "+t.getTenantId()+".FPEJ A, "+t.getTenantId()+".FPEJ B, "+t.getTenantId()+".FPEJ C,"+t.getTenantId()+".FPEJ D  Where A.PEJATAS=B.PEJKODE(+) AND  "
				+ "B.PEJATAS=C.PEJKODE(+) AND C.PEJATAS=D.PEJKODE(+)  AND A.PEJTIPE='4' ORDER BY K4)";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<SelectItem<String>> getListGudang() {
		// TODO Auto-generated method stub
		String sql = "Select * from "+t.getTenantId()+".FLOKGD order by KG";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SelectItem<String>> getListKolektor() {
		// TODO Auto-generated method stub
		String sql = "select EMPNO,EMPNAME as empname from "+t.getTenantId()+".femployee where empopr='K' order by empno";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<SelectItem<String>> getListCustomer() {
		// TODO Auto-generated method stub
		String sql = "SELECT  custno, custname FROM "+t.getTenantId()+".fcustmst where custno not in ( select knownas from "+t.getTenantId()+".fsls where knownas is not "
				+ "null) ORDER BY custno ASC";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<SelectItem<String>> getListSalesEmp() {
		// TODO Auto-generated method stub
		String sql = "SELECT memostring,memostring|| ' - ' ||memonama FROM "+t.getTenantId()+".ftable13 WHERE xkey = 'XSALESEMP' ORDER BY memostring ASC";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}
	
	@Override
	public MSalesmanKolektor getKolektorChoosen(String empno) {
		// TODO Auto-generated method stub
		MSalesmanKolektor result = new MSalesmanKolektor();
		String sql = "select EMPNO,EMPNO|| ' - ' ||EMPNAME as empname from "+t.getTenantId()+".femployee where empno = '"+empno+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			result = new MSalesmanKolektor((String) obj[0], (String) obj[1]);	
		}
		return result;
	}

	@Override
	public MSalesmanChoosenDto getSalesmanChoosen(String slsno) {
		// TODO Auto-generated method stub
		MSalesmanChoosenDto result = new MSalesmanChoosenDto();
		
		String sql = "SELECT   a.*, b.typekartu, b.flagkreditmobile, c.data2 nmsalesforce, c.data3 flagtransaksi, c.data4 seq, d.data2 nmrayon, k.k4, k.n4, k.k3, k.n3, k.k2, k.n2, k.k1, k.n1, l.kodekol, l.empname "
				+ "    FROM ((SELECT   a.slsno, a.slsname, a.team, (SELECT teamname "
				+ "                                                  FROM "+t.getTenantId()+".fteam "
				+ "                                                 WHERE team = a.team) nmteam, "
				+ "                   a.kg, "
				+ "                   CASE "
				+ "                      WHEN a.kg = '00' "
				+ "                         THEN TO_NCHAR ('Gudang Utama') "
				+ "                      ELSE (SELECT kgname "
				+ "                              FROM "+t.getTenantId()+".flokgd "
				+ "                             WHERE kg = a.kg) "
				+ "                   END nmkg, "
				+ "                   to_char(a.transdate,'dd MON yyyy') as transdate, a.oprtype, "
				+ "                   CASE "
				+ "                      WHEN a.oprtype = 'C' "
				+ "                         THEN 'Kanvas' "
				+ "                      WHEN a.oprtype = 'S' "
				+ "                         THEN 'SHOP SALES' "
				+ "                      WHEN a.oprtype = 'T' "
				+ "                         THEN 'Taking Order' "
				+ "                   END nmoprtype, "
				+ "                   a.slsadd1, a.slsadd2, a.slscity, a.area, to_char(a.workdate,'dd MON yyyy') as workdate, "
				+ "                   a.kpej, to_char(a.upddate,'dd MON yyyy') as upddate, a.pddk,to_char(a.lahir,'dd MON yyyy') as lahir, a.flagbonus, a.kdkms, "
				+ "                   a.jatah, a.flagsbd, a.data01, a.data02, a.data03, a.data04, "
				+ "                   a.data05, a.data06, a.data07, a.data08, a.data09, a.data10, "
				+ "                   a.data11, a.data12, a.data13, a.flag_nonaktif, "
				+ "                   to_char(a.tgl_nonaktif,'dd MON yyyy') as tgl_nonaktif, a.slsemployee, a.knownas "
				+ "              FROM "+t.getTenantId()+".fsls a "
				+ "             WHERE a.slsno = '"+slsno+"' "
				+ "          ORDER BY slsno ASC) a "
				+ "         LEFT JOIN "
				+ "         "+t.getTenantId()+".ftable26 b ON a.slsno = b.slsno "
				+ " 		LEFT JOIN "+t.getTenantId()+".Ftabel10 c ON a.data04 = c.data1 "
				+ "			LEFT JOIN "+t.getTenantId()+".FTabel11 d ON a.data05 = d.data1) "
						+ " LEFT JOIN "
						+ "(SELECT * FROM (SELECT   e.pejkode k4, e.pejname n4, f.pejkode k3, f.pejname n3, "
						+ "                 g.pejkode k2, g.pejname n2, h.pejkode k1, h.pejname n1 "
						+ "            FROM "+t.getTenantId()+".fpej e, "
						+ "                 "+t.getTenantId()+".fpej f, "
						+ "                 "+t.getTenantId()+".fpej g, "
						+ "                 "+t.getTenantId()+".fpej h "
						+ "           WHERE e.pejatas = f.pejkode(+) "
						+ "             AND f.pejatas = g.pejkode(+) "
						+ "             AND g.pejatas = h.pejkode(+) "
						+ "             AND e.pejtipe = '4' "
						+ "        ORDER BY k4 ) j) k on a.kpej = k.k4"
						+ " LEFT JOIN "
						+ " (select  distinct kodekol,empname, a.slsno from "+t.getTenantId()+".ftable35 a left join "+t.getTenantId()+".femployee b on a.kodekol=b.empno) l on a.slsno = l.slsno "
				+ "ORDER BY a.slsno ASC ";
		Query query = entityManager.createNativeQuery(sql);
		String blockPcs = "";
		List<Object[]> getBlokPcs = getBlokPcs(slsno);
		if (getBlokPcs.size() != 0) {
			blockPcs = "Y";
		} else {
			blockPcs = "N";
		}
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			result = new MSalesmanChoosenDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], 
					(String) obj[6], (String) obj[7], (String) obj[8], (String) obj[9], (String) obj[10], (String) obj[11], (String) obj[12], 
					(String) obj[13], (String) obj[14], (String) obj[15], (String) obj[16], (String) obj[17], (String) obj[18], (String) obj[19], 
					(String) obj[20], (String) obj[21], (String) obj[22], (String) obj[23], (String) obj[24], (String) obj[25], (String) obj[26],
					(String) obj[27], (String) obj[28], (String) obj[29], (String) obj[30], (String) obj[31], (String) obj[32], (String) obj[33],
					(BigDecimal) obj[34], (String) obj[35], (String) obj[36], (String) obj[37], (String) obj[38], (String) obj[39], (String) obj[40],
					(String) obj[41], (String) obj[42], (BigDecimal) obj[43], (String) obj[44], (String) obj[45], (String) obj[46], (String) obj[47], (String) obj[48],
					(String) obj[49], (String) obj[50], (String) obj[51], (String) obj[52], (String) obj[53], (String) obj[54], blockPcs);	
		}
		return result;
	}
	
	private List<Object[]> getBlokPcs(String slsNo) {
		List<Object[]> result = new ArrayList<>();
		String sql = " select  * from "+t.getTenantId()+".ftable13 where xkey =  'BLOK_PCS' and MEMOSTRING='"+slsNo+"'  ";
		Query query = entityManager.createNativeQuery(sql);
		 result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	public List<MSalesmanBrandChoosen> getSalesmanBrandChoosen(String slsno) {
		// TODO Auto-generated method stub
		List<MSalesmanBrandChoosen> result = new ArrayList<>();
		String sql = "SELECT   a.slsno, a.nobrs, a.data2, "
				+ "         CASE "
				+ "            WHEN kategori = 1 "
				+ "               THEN (SELECT pcodename "
				+ "                       FROM "+t.getTenantId()+".fmaster "
				+ "                      WHERE pcode = a.data2) "
				+ "            WHEN kategori = 2 "
				+ "               THEN (SELECT sbra1name "
				+ "                       FROM "+t.getTenantId()+".ftsbrand1 "
				+ "                      WHERE prlin || brand || sbra1 = a.data2) "
				+ "            WHEN kategori = 3 "
				+ "               THEN (SELECT sbra2name "
				+ "                       FROM "+t.getTenantId()+".ftsbrand2 "
				+ "                      WHERE sbra2 = a.data2) "
				+ "         END AS sbra2name, "
				+ "         a.kode, a.kategori "
				+ "    FROM "+t.getTenantId()+".ftabel12 a "
				+ "   WHERE a.slsno = '"+slsno+"' "
				+ "ORDER BY a.kategori ASC, a.data2 ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) {
			MSalesmanBrandChoosen data = new MSalesmanBrandChoosen((String) obj[0], (BigDecimal) obj[1], (String) obj[2], (String) obj[3], 
					(String) obj[4], (BigDecimal) obj[5]);
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<MSalesmanProductChoosen> getSalesmanProductChoosen(String team) {
		// TODO Auto-generated method stub
		List<MSalesmanProductChoosen> result = new ArrayList<>();
		
		String sql = "SELECT DISTINCT a.team, b.sbra2 AS divisi, c.sbra2name AS divisiname, "
				+ "                a.kdmapping || ' - ' || b.pcodename keterangan, "
				+ "                CASE "
				+ "                   WHEN a.flag = '1' "
				+ "                      THEN 'INCLUDE' "
				+ "                   WHEN a.flag = '2' "
				+ "                      THEN 'EXCLUDE' "
				+ "                END flag "
				+ "           FROM "+t.getTenantId()+".ftable279 a LEFT JOIN "+t.getTenantId()+".fmaster b "
				+ "                ON a.kdmapping = b.pcode "
				+ "                LEFT JOIN "+t.getTenantId()+".ftsbrand2 c ON b.sbra2 = c.sbra2 "
				+ "          WHERE a.team = '"+team+"' AND a.tipe = '1' "
				+ "UNION ALL "
				+ "SELECT DISTINCT a.team, b.sbra2 AS divisi, c.sbra1name AS divisiname, "
				+ "                a.kdmapping || ' - ' || c.sbra1name keterangan, "
				+ "                CASE "
				+ "                   WHEN a.flag = '1' "
				+ "                      THEN 'INCLUDE' "
				+ "                   WHEN a.flag = '2' "
				+ "                      THEN 'EXCLUDE' "
				+ "                END flag "
				+ "           FROM "+t.getTenantId()+".ftable279 a LEFT JOIN "+t.getTenantId()+".fmaster b "
				+ "                ON a.kdmapping = b.prlin || b.brand || b.sbra1 "
				+ "                LEFT JOIN "+t.getTenantId()+".ftsbrand1 c "
				+ "                ON b.prlin || b.brand || b.sbra1 = "
				+ "                                                 c.prlin || c.brand || c.sbra1 "
				+ "          WHERE a.team = '"+team+"' AND a.tipe = '2' "
				+ "UNION ALL "
				+ "SELECT DISTINCT a.team, a.kdmapping AS divisi, b.sbra2name AS divisiname, "
				+ "                a.kdmapping || ' - ' || b.sbra2name keterangan, "
				+ "                CASE "
				+ "                   WHEN a.flag = '1' "
				+ "                      THEN 'INCLUDE' "
				+ "                   WHEN a.flag = '2' "
				+ "                      THEN 'EXCLUDE' "
				+ "                END flag "
				+ "           FROM "+t.getTenantId()+".ftable279 a LEFT JOIN "+t.getTenantId()+".ftsbrand2 b "
				+ "                ON a.kdmapping = b.sbra2 "
				+ "          WHERE a.team = '"+team+"' AND a.tipe = '3'";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) {
			MSalesmanProductChoosen data = new MSalesmanProductChoosen((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4]);
			result.add(data);
		}
		return result;
	}

	@Override
	public void addSalesmanFsls(String slsno, String slsname, String slsadd1, String slsadd2, String slscity,
			String team, String workdate, String oprtype, String transdate, String kpej, String upddate, String pddk,
			String lahir, String flagbonus, String kg, String flagsbd, String data01, String data02, String data03,
			String data04, String data05, String data06, String data08, String data09, String data10, String data11,
			String data12, String data13, String slsemployee, String knownas) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+".fsls "
				+ "            (slsno, slsname, slsadd1, slsadd2, slscity, team, "
				+ "             workdate, oprtype, transdate, kpej, upddate, "
				+ "             pddk, lahir, flagbonus, kg, flagsbd, data01, "
				+ "             data02, data03, data04, data05, data06, data08, data09, "
				+ "             data10, data11, data12, data13, slsemployee, knownas "
				+ "            ) "
				+ "     VALUES ('"+slsno+"', '"+slsname+"', '"+slsadd1+"', '"+slsadd2+"', '"+slscity+"', '"+team+"', "
				+ "             '"+workdate+"', '"+oprtype+"', '"+transdate+"', '"+kpej+"', '"+upddate+"', "
				+ "             '"+pddk+"', '"+lahir+"', '"+flagbonus+"', '"+kg+"', '"+flagsbd+"', '"+data01+"', "
				+ "             '"+data02+"', '"+data03+"', '"+data04+"', '"+data05+"', '"+data06+"', '"+data08+"', '"+data09+"', "
				+ "             '"+data10+"', '"+data11+"', '"+data12+"', '"+data13+"', '"+slsemployee+"', '"+knownas+"' "
				+ "            ) ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void addSalesmanFtabel12(String slsno, String team, String tglgudang) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+".ftabel12 "
				+ "            (slsno, data2, nobrs, kode, tipe, kategori) "
				+ "   SELECT '"+slsno+"' AS slskd, sbra2, ROWNUM AS no_urut, kode, tipe, kategori "
				+ "     FROM "+t.getTenantId()+".ftabel141 "
				+ "    WHERE team = '"+team+"' "
				+ "      AND tipe = 1 "
				+ "      AND tgl = (SELECT MAX (tgl) "
				+ "                   FROM "+t.getTenantId()+".ftabel141 "
				+ "                  WHERE team = '"+team+"' AND tipe = 1 AND tgl <= '"+tglgudang+"') "
				+ "   UNION ALL "
				+ "   SELECT '"+slsno+"' AS slskd, sbra2, ROWNUM AS no_urut, kode, tipe, kategori "
				+ "     FROM "+t.getTenantId()+".ftabel141 "
				+ "    WHERE team = '"+team+"' "
				+ "      AND tipe = 2 "
				+ "      AND tgl = (SELECT MAX (tgl) "
				+ "                   FROM "+t.getTenantId()+".ftabel141 "
				+ "                  WHERE team = '"+team+"' AND tipe = 2 AND tgl <= '"+tglgudang+"') "
				+ "   UNION ALL "
				+ "   SELECT '"+slsno+"' AS slskd, sbra2, ROWNUM AS no_urut, kode, tipe, kategori "
				+ "     FROM "+t.getTenantId()+".ftabel141 "
				+ "    WHERE team = '"+team+"' "
				+ "      AND tipe = 3 "
				+ "      AND tgl = (SELECT MAX (tgl) "
				+ "                   FROM "+t.getTenantId()+".ftabel141 "
				+ "                  WHERE team = '"+team+"' AND tipe = 3 AND tgl <= '"+tglgudang+"') "
				+ "   UNION ALL "
				+ "   SELECT '"+slsno+"' AS slskd, sbra2, ROWNUM AS no_urut, kode, tipe, kategori "
				+ "     FROM "+t.getTenantId()+".ftabel141 "
				+ "    WHERE team = '"+team+"' "
				+ "      AND (tipe = 4 OR tipe IS NULL) "
				+ "      AND tgl = "
				+ "             (SELECT MAX (tgl) "
				+ "                FROM "+t.getTenantId()+".ftabel141 "
				+ "               WHERE team = '"+team+"' "
				+ "                 AND (tipe = 4 OR tipe IS NULL) "
				+ "                 AND tgl <= '"+tglgudang+"')";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void addSalesmanFtable35(String slsno, String kolektor, String kodekol) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+".FTABLE35 (SLSNO,KOLEKTOR,KODEKOL)  VALUES ('"+slsno+"','"+kolektor+"',  '"+kodekol+"')";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void addSalesmanFtable26(String slsno, String typekartu, String flagkreditmobile) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+".FTABLE26 (slsno, typekartu, flagkreditmobile) VALUES ( '"+slsno+"' ,'"+typekartu+"' ,'"+flagkreditmobile+"')";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void blokSalesmanFsls(String tglGudang, String slsno) {
		// TODO Auto-generated method stub
		String sql = "   UPDATE "
				+ "  "+t.getTenantId()+".FSLS "
				+ "SET "
				+ "  DATA06 = 'Y', "
				+ "  UPDDATE = '"+tglGudang+"', "
				+ "  flag_nonaktif = 'Y', "
				+ "  tgl_nonaktif = '"+tglGudang+"' "
				+ "WHERE "
				+ "  SLSNO = '"+slsno+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public void blokSalesmanFcustmst(String tglGudang, String slsno) {
		// TODO Auto-generated method stub
		String sql = "  UPDATE "
				+ "  "+t.getTenantId()+".FCUSTMST C "
				+ "SET "
				+ "  CLASTUPD = '"+tglGudang+"' "
				+ "WHERE "
				+ "  CUSTNO =( "
				+ "    SELECT "
				+ "      CUSTNO "
				+ "    FROM "
				+ "      "+t.getTenantId()+".FCUSTSLS S "
				+ "    WHERE "
				+ "      C.CUSTNO = S.CUSTNO "
				+ "      AND S.SLSNO = '"+slsno+"' "
				+ "  )";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public void updateSalesmanFemployee(String slsname,String slsadd1, String slscity, String workdate, String upddate, String slsno) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+".femployee "
				+ "   SET empname = '"+slsname+"', "
				+ "       empadd = '"+slsadd1+"', "
				+ "       empcity = '"+slscity+"', "
				+ "       workdate = '"+workdate+"', "
				+ "       upddate = '"+upddate+"' "
				+ " WHERE empno = '"+slsno+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateSalesmanFlagNonAktif(String flagNonAktif, String tglGudang, String slsno) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+".fsls "
				+ "   SET flag_nonaktif = '"+flagNonAktif+"', "
				+ "       tgl_nonaktif = '"+tglGudang+"' "
				+ " WHERE slsno = '"+slsno+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateSalesmanFsls(String slsno, String slsname, String slsadd1, String slsadd2, String slscity,
			String team, String workdate, String oprtype, String transdate, String kpej, String upddate, String pddk,
			String lahir, String kg, String data01, String data02, String data03, String data04, String data05,
			String data06, String data08, String data09, String data10, String data11, String data12, String data13,
			String slsemployee, String knownas) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+".fsls "
				+ "   SET kg = '"+kg+"', "
				+ "       slsname = '"+slsname+"', "
				+ "       slsadd1 = '"+slsadd1+"', "
				+ "       slsadd2 = '"+slsadd2+"', "
				+ "       slscity = '"+slscity+"', "
				+ "       team = '"+team+"', "
				+ "       workdate = '"+workdate+"', "
				+ "       oprtype = '"+oprtype+"', "
				+ "       transdate = '"+transdate+"', "
				+ "       kpej = '"+kpej+"', "
				+ "       upddate = '"+upddate+"', "
				+ "       pddk = '"+pddk+"', "
				+ "       lahir = '"+lahir+"', "
				+ "       data01 = '"+data01+"', "
				+ "       data02 = '"+data02+"', "
				+ "       data03 = '"+data03+"', "
				+ "       data04 = '"+data04+"', "
				+ "       data05 = '"+data05+"', "
				+ "       data06 = '"+data06+"', "
				+ "       data08 = '"+data08+"', "
				+ "       data09 = '"+data09+"', "
				+ "       data10 = '"+data10+"', "
				+ "       data11 = '"+data11+"', "
				+ "       data12 = '"+data12+"', "
				+ "       data13 = '"+data13+"', "
				+ "       slsemployee = '"+slsemployee+"', "
				+ "       knownas = '"+knownas+"' "
				+ " WHERE slsno = '"+slsno+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public void updateSalesmanFtable35(String slsno, String kolektor, String kodekol) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+".ftable35 "
				+ "   SET kolektor = '"+kolektor+"', "
				+ "       kodekol = '"+kodekol+"' "
				+ " WHERE slsno = '"+slsno+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteFtabel12(String slsno) {
		// TODO Auto-generated method stub
		String sql = "DELETE "+t.getTenantId()+".FTABEL12 WHERE SLSNO='"+slsno+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public void deleteFtable26(String slsno) {
		// TODO Auto-generated method stub
		String sql = "DELETE "+t.getTenantId()+".FTABLE26 WHERE SLSNO='"+slsno+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteFcustsls(String slsno) {
		// TODO Auto-generated method stub
		String sql = "DELETE "+t.getTenantId()+".FCUSTSLS WHERE SLSNO='"+slsno+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public String getMinSalesNo() {
		// TODO Auto-generated method stub
		String sql = "SELECT MIN(SLSNO) minSls  FROM "+t.getTenantId()+".FSLS F order by SLSNO";
		Query query = entityManager.createNativeQuery(sql);
		String result = (String) query.getSingleResult();
		return result;
	}

	@Override
	public List<Object[]> getMasterFtable13(String param) {
		// TODO Auto-generated method stub
		List<Object[]> result = new ArrayList<>();
		String sql = "SELECT *  FROM "+t.getTenantId()+".FTABLE13 WHERE XKEY = '"+param+"'";
		Query query = entityManager.createNativeQuery(sql);
		 result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> validasiRayon(String kodeRayon) {
		// TODO Auto-generated method stub
		List<Object[]> result = new ArrayList<>();
		String sql = "SELECT slsno, data05 FROM "+t.getTenantId()+".fsls WHERE data06 <> 'Y' AND data05 = '"+kodeRayon+"'";
		Query query = entityManager.createNativeQuery(sql);
		 result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> validasiSalesmanFsls(String kodeSalesman) {
		// TODO Auto-generated method stub
		List<Object[]> result = new ArrayList<>();
		String sql = "SELECT SLSNO FROM "+t.getTenantId()+".FSLS WHERE SLSNO='"+kodeSalesman+"'";
		Query query = entityManager.createNativeQuery(sql);
		 result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> validasiSalesmanFemp(String kodeSalesman) {
		// TODO Auto-generated method stub
		List<Object[]> result = new ArrayList<>();
		String sql = "SELECT EMPNO FROM "+t.getTenantId()+".FEMPLOYEE WHERE EMPNO='"+kodeSalesman+"'";
		Query query = entityManager.createNativeQuery(sql);
		 result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	public void addFEmployee(DataSalesmanDto d, String tanggalGudang) {
		// TODO Auto-generated method stub
		String sql = "Insert into "+t.getTenantId()+".FEMPLOYEE (EMPNO,EMPNAME,EMPADD,EMPCITY,EMPOPR,WORKDATE,UPDDATE)   "
				+ "Values ('"+d.getNoSalesman()+"', "
				+ " '"+d.getNamaSalesman()+"'     , "
				+ " '"+d.getAlamat1()+"'          , "
				+ " '"+d.getKota()+"'             , "
				+ " 'S'                           , "
				+ " '"+d.getTglMasukKerja()+"'    , "
				+ " '"+tanggalGudang+"            ') ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void addFSls(DataSalesmanDto d, String tanggalGudang) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".FSLS (SLSNO ,SLSNAME ,SLSADD1 ,SLSADD2 ,SLSCITY ,TEAM ,WORKDATE ,OPRTYPE ,TRANSDATE ,KPEJ , "
	               +" UPDDATE ,PDDK ,LAHIR ,FLAGBONUS ,KG ,FLAGSBD ,DATA01 ,DATA02 ,DATA03 , DATA04 ,DATA05 ,DATA06 ,DATA08 , "
	               +" DATA09 ,DATA10 ,DATA11 ,DATA12 ,DATA13, SLSEMPLOYEE, KNOWNAS) "
	               +" VALUES ( '"+d.getNoSalesman()+"' "
	               +" , '"+d.getNamaSalesman()+"'	"
	               +" , '"+d.getAlamat1()+"'	"
	               +" , '"+d.getAlamat2()+"'	"
	               +" , '"+d.getKota()+"'	"
	               +" , '"+d.getTeam()+"'	"
	               +" , '"+d.getTglMasukKerja()+"'	"
	               +" , '"+d.getTipeOperasi()+"'	"
	               +" , '"+d.getTglTransaksi()+"'	"
	               +" , '"+d.getNoPejabatLv4()+"'	"
	               +" , '"+tanggalGudang+"' "
	               +" , '"+d.getPendidikan()+"'	"
	               +" , '"+d.getTanggalLahir()+"'	"
	               +" , 'Y' "
	               +" , '"+d.getNoGudang()+"'	"
	               +" , '*' "
	               +" , '"+d.getTelepon()+"'	"
	               +" , '"+d.getHp()+"'	"
	               +" , '"+d.getEmail()+"'	"
	               +" , '"+d.getNoSalesforce()+"'	"
	               +" , '"+d.getNoRayon()+"'	"
	               +" , '"+d.getBlokSalesman()+"'	"
	               +" , '"+d.getTempatLahir()+"'	"
	               +" , '"+d.getJenisKelamin()+"'	"
	               +" , '"+d.getStatus()+"'	"
	               +" , '"+d.getBank()+"'	"
	               +" , '"+d.getAgama()+"'	"
	               +" , '"+d.getKpl()+"'	"
	               +" , '"+d.getSalesEmp()+"'	"
	               +" , '"+d.getKnownAs()+"'	"
	               +" ) ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> getftable207(String year, String period, String team, String salesforce) {
		// TODO Auto-generated method stub
		List<Object[]> result = new ArrayList<>();
		String sql = "select * from "+t.getTenantId()+".ftable207 where tahun='"+year+"'  and periode='"+period+"' and team='"+team+"'  and salesforce='"+salesforce+"'";
		Query query = entityManager.createNativeQuery(sql);
		 result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	public void deleteftabel57(String kodeSalesman, String year, String period) {
		// TODO Auto-generated method stub
		String sql = " DELETE "+t.getTenantId()+".ftabel57 WHERE SLSNO='"+kodeSalesman+"'  and  tahun='"+year+"'  and periode='"+period+"'  ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteftable57(String kodeSalesman, String year, String period) {
		// TODO Auto-generated method stub
		String sql = " DELETE "+t.getTenantId()+".ftable57 WHERE SLSNO='"+kodeSalesman+"'  and  year='"+year+"'  and period='"+period+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> getftabel57(String year, String period, String kodeSalesman) {
		// TODO Auto-generated method stub
		List<Object[]> result = new ArrayList<>();
		String sql = "Select * from "+t.getTenantId()+".ftabel57  where tahun='"+year+"'  and periode='"+period+"'  and slsno='"+kodeSalesman+"'";
		Query query = entityManager.createNativeQuery(sql);
		 result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	public void addftabel57(String year, String periode, String kodeSalesman, String pembagiPerHari,
			String pembagiPerPekan, String targetCb, String targetOa, String targetCall, String targetEc,
			String targetIpt) {
		// TODO Auto-generated method stub
		String sql = " Insert Into "+t.getTenantId()+".ftabel57  (Tahun,Periode,slsno,Bagiday,bagiwk,trgcb,trgoa,trgcall,trgec,trgipt)  "
				+ " values ('"+year+"','"+periode+"','"+kodeSalesman+"' ,'"+pembagiPerHari+"','"+pembagiPerPekan+"' ,'"+targetCb+"','"+targetOa+"' ,'"+targetCall+"','"+targetEc+"' ,'"+targetIpt+"')  ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void addftable57(String year, String periode, String kodeSalesman, String targetSbpt, String targetOmzet,
			String targetSc, String targetEp, String flagTargetOa) {
		// TODO Auto-generated method stub
		String sql = " Insert Into "+t.getTenantId()+".ftable57 (Year,Period,slsno,trgsbpt,Omzet,SC,EP,Flagtargetoa)  "
				+ " values ('"+year+"','"+periode+"' ,'"+kodeSalesman+"' ,'"+targetSbpt+"','"+targetOmzet+"' ,'"+targetSc+"', '"+targetEp+"' ,'"+flagTargetOa+"') ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void addDokumenUpload(String xdocno, String tanggalGudang, String user, String xkey, String kodeSalesman) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".fsls_upload (docno, docdate, usercreated, "
				+ "	xkey, flagprocess, slsno, slsname, oprtype, tipekartu, flagkredit, team, "
				+ "	kpej, slsemp, knownas, salesforce, rayon, "
				+ "	kg, kpl, kolektor, slsadd1, slsadd2, "
				+ "	kota, jnskelamin, tempatlahir, status, "
				+ "	agama, telephone, tgllahir) "
				+ " SELECT '"+xdocno+"' AS docno, '"+tanggalGudang+"' docdate,  "
				+ " '"+user+"' usercreated, '"+xkey+"' xkey, '0' flag, "
				+ "	a.slsno, a.slsname, a.oprtype, "
				+ "	CASE "
				+ "		WHEN b.typekartu = 'M1' THEN '1' "
				+ "		WHEN b.typekartu = 'M2' THEN '2' "
				+ "		WHEN b.typekartu = 'M3' THEN '3' "
				+ "		ELSE '1' "
				+ "	END tipekartu, b.flagkreditmobile, a.team, a.kpej, "
				+ "	a.slsemployee, a.knownas, a.data04 salesforce, "
				+ "	a.data05 rayon, a.kg, a.data13 kpl, "
				+ "	c.kodekol, a.slsadd1, a.slsadd2, a.slscity, a.data09 jnskel, "
				+ "	a.data08 tempalahir, a.data10 status, a.data12 agama, a.data01 tlp, a.lahir "
				+ "FROM "+t.getTenantId()+".fsls a "
				+ "LEFT JOIN "+t.getTenantId()+".ftable26 b ON a.slsno = b.slsno "
				+ "LEFT JOIN "+t.getTenantId()+".ftable35 c ON a.slsno = c.slsno "
				+ "WHERE a.slsno = '"+kodeSalesman+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> cekTglGudang(String tglGudang) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " SELECT 'tanggal' AS tanggal, tglgudang    FROM (SELECT MAX (transdate) tglgudang FROM "+t.getTenantId()+".fsls)   WHERE tglgudang > '"+tglGudang+"' ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> cekSeqNumber() {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " SELECT (SELECT memoint FROM "+t.getTenantId()+".fmemo WHERE memonama = 'XSLSNOFROM' AND memoint > 0) seqfrom, "
				+ "  (SELECT memoint FROM "+t.getTenantId()+".fmemo  WHERE memonama = 'XSLSNOTO' AND memoint > 0) seqto    FROM DUAL ";
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> cekAntrian(String xtbl_name, String xno_seq, String login_user, Boolean cek) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " SELECT NAMAUSER, NAMATABEL FROM "+t.getTenantId()+".FPRGACT  WHERE NAMATABEL = '"+xtbl_name+"' AND NO_KEY='"+xno_seq+"' ";
				if (cek == true) {					
					sql += " AND NAMAUSER <> '"+login_user+"' ";
				}
		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteAntrian(String xtbl_name, String xno_seq, String login_user, Boolean cekName, Boolean cekKey) {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM "+t.getTenantId()+".FPRGACT WHERE NAMATABEL = '"+xtbl_name+"' ";
		if (cekKey == true) {					
			sql += " AND NO_KEY='"+xno_seq+"' ";
		}
		if (cekName == true) {					
			sql += " AND NAMAUSER = '"+login_user+"' ";
		}
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void insertAntrian(String xtbl_name, String xno_seq, String login_user) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+t.getTenantId()+".FPRGACT (NAMATABEL,NO_KEY,NAMAUSER)  values ('"+xtbl_name+"','"+xno_seq+"','"+login_user+"') ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> getRange(String slsNo, String Xmaster) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " ";
		
		if (Xmaster.equalsIgnoreCase("1")) {
			sql =" SELECT * FROM (SELECT kdsls, rangeawal, (SELECT memoint AS rangeakhir FROM "+t.getTenantId()+".fmemo "
					+ "                                   WHERE memonama = 'XSLSNOTO') AS rangeakhir "
					+ "         FROM (SELECT '"+slsNo+"' AS kdsls, memoint AS rangeawal FROM "+t.getTenantId()+".fmemo "
					+ "                 WHERE memonama = 'XSLSNOFROM')) a ";
		} else {
			sql = " Select * from (SELECT kdsls,rangeawal, (SELECT memoint AS rangeakhir "
					+ " FROM "+t.getTenantId()+".fmemo "
					+ " WHERE memonama = 'SLSRANGE2') AS rangeakhir "
					+ " FROM (SELECT '"+slsNo+"' as kdsls, memoint AS rangeawal "
					+ " FROM "+t.getTenantId()+".fmemo "
					+ " WHERE memonama = 'SLSRANGE1'))a";
		}

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getCekRange(String slsNo, String Xmaster) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " ";
		
		if (Xmaster.equalsIgnoreCase("1")) {
			sql =" SELECT * FROM (SELECT kdsls, rangeawal, (SELECT memoint AS rangeakhir FROM "+t.getTenantId()+".fmemo "
					+ "                                    WHERE memonama = 'XSLSNOTO') AS rangeakhir "
					+ "           FROM (SELECT '"+slsNo+"' AS kdsls, memoint AS rangeawal FROM "+t.getTenantId()+".fmemo "
					+ "                 WHERE memonama = 'XSLSNOFROM')) a "
					+ "  WHERE a.kdsls >= a.rangeawal AND a.kdsls <= a.rangeakhir ";
		} else {
			sql = "Select * from (SELECT kdsls,rangeawal, (SELECT memoint AS rangeakhir "
					+ "  FROM "+t.getTenantId()+".fmemo "
					+ "  WHERE memonama = 'SLSRANGE2') AS rangeakhir "
					+ "  FROM (SELECT '"+slsNo+"' as kdsls, memoint AS rangeawal "
					+ "  FROM "+t.getTenantId()+".fmemo "
					+ "  WHERE memonama = 'SLSRANGE1'))a "
					+ "  where a.kdsls>= a.rangeawal and a.kdsls <= a.rangeakhir";
		}

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getCekSlsno(String slsNo, String Xmaster) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " ";
		
		if (Xmaster.equalsIgnoreCase("1")) {
			sql =" SELECT DISTINCT slsno "
					+ "   FROM (SELECT slsno FROM "+t.getTenantId()+".fsls WHERE slsno = '"+slsNo+"' "
					+ "         UNION ALL "
					+ "         SELECT slsno FROM "+t.getTenantId()+".fsls_upload WHERE slsno = '"+slsNo+"'  "
					+ "       UNION ALL "
					+ "         SELECT empno AS slsno FROM "+t.getTenantId()+".femployee WHERE empno = '"+slsNo+"') ";
		} else {
			sql =" SELECT DISTINCT slsno "
					+ "   FROM (SELECT slsno FROM "+t.getTenantId()+".fsls WHERE slsno = '"+slsNo+"'  "
					+ "      UNION ALL "
					+ "        SELECT empno AS slsno FROM "+t.getTenantId()+".femployee WHERE empno = '"+slsNo+"') ";
		}

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public String getSuggestNewNoSlsNo(String Xmaster, String range_awal, String range_akhir) {
		// TODO Auto-generated method stub
		String sql =" SELECT TO_CHAR(xsaran) FROM ( "
				+ "   SELECT MAX (xnomor) + 1 AS xsaran "
				+ "     FROM (SELECT 'fsls' AS xtable, MAX (slsno) AS xnomor FROM "+t.getTenantId()+".fsls "
				+ "           WHERE slsno > '"+range_awal+"' AND slsno < '"+range_akhir+"' "
				+ "         UNION ALL "
				+ "          SELECT 'femployee' AS xtable, MAX (empno) AS xnomor FROM "+t.getTenantId()+".femployee "
				+ "           WHERE empno > '"+range_awal+"' AND empno < '"+range_akhir+"' ";
		if (Xmaster.equalsIgnoreCase("1")) {
			sql += " UNION ALL "
					+ " SELECT 'fsls_upload' AS xtable, MAX (slsno) AS xnomor FROM "+t.getTenantId()+".fsls_upload "
					+ " WHERE slsno > '"+range_awal+"' AND slsno < '"+range_akhir+"' ";
		}
		      sql += " )) WHERE xsaran < '"+range_akhir+"' ";
		Query query = entityManager.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		return (String)obj;
	}

	@Override
	public List<Object[]> getCekRangeAwalSlsno(String range_awal) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " SELECT * FROM "+t.getTenantId()+".fsls WHERE slsno = '"+range_awal+"' ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValRayon(String slsNo, String rayonNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = "SELECT slsno, data05 "
				+ " FROM "+t.getTenantId()+".fsls "
				+ " WHERE slsno <> '"+slsNo+"' "
				+ " AND data06 <> 'Y' "
				+ " AND data05 = '"+rayonNo+"' ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getKGAndTRansDateSales(String slsNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql =" select kg, TO_CHAR(transdate, 'DD MON YYYY') dateTrans from "+t.getTenantId()+".fsls where slsno = '"+slsNo+"'  ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValTransSales(String slsNo, String kg, String transDate) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FJUAL_H where SLSNO='"+slsNo+"' "
				+ " and KG='"+kg+"' "
				+ " and INVNO is not null "
				+ " and INVDATE= '"+transDate+"' ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValInvPrint(String slsNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FJUAL_H where SLSNO='"+slsNo+"' and INVNO is null  ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValTransGudang(String slsNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FMUTASIGD_H where SLSNO='"+slsNo+"' and FLAGEOD is null  ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValTransVan(String slsNo, String kg, String transDate) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FMUTASIVAN_H where SLSNO='"+slsNo+"' "
				+ " and KG='"+kg+"' "
				+ " and DOCDATE='"+transDate+"' ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValTransVanNOtProcess(String slsNo, String kg) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FBOOKV_H where "
				+ " SLSNO='"+slsNo+"' "
				+ " and KG='"+kg+"' and FLAG is null";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValTRansVanNotEod(String slsNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FMUTASIVAN_H where SLSNO='"+slsNo+"' and FLAGPROC is null  ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void updateFemployee(String slsName, String slsAdd1, String slsCity, String slsDateIn, String tglGudang,
			String slsNo) {
		// TODO Auto-generated method stub
		String  sql = "Update "+t.getTenantId()+".FEMPLOYEE "
				+ " Set EMPNAME='"+slsName+"' "
				+ " , EMPADD='"+slsAdd1+"' "
				+ " , EMPCITY='"+slsCity+"' "
				+ " ,WORKDATE='"+slsDateIn+"' "
				+ " ,UPDDATE='"+tglGudang+"' "
				+ " Where EMPNO='"+slsNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateFsls(DataSalesmanDto d, String tglGudang) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+".FSLS "
				+ " SET KG='"+Objects.toString(d.getNoGudang(),"")+"' "
				+ " , SLSNAME='"+Objects.toString(d.getNamaSalesman(),"")+"' "
                + " , SLSADD1='"+Objects.toString(d.getAlamat1(),"")+"' "
                + " , SLSADD2='"+Objects.toString(d.getAlamat2(),"")+"' "
                + " , SLSCITY='"+Objects.toString(d.getKota(),"")+"' "
                + " , TEAM='"+Objects.toString(d.getTeam(),"")+"' "
                + " , WORKDATE='"+Objects.toString(d.getTglMasukKerja(),"")+"' "
                + " , OPRTYPE='"+Objects.toString(d.getTipeOperasi(),"")+"' "
                + " , TRANSDATE='"+Objects.toString(d.getTglTransaksi(),"")+"' "
                + " , KPEJ='"+Objects.toString(d.getNoPejabatLv4(),"")+"' "
                + " , UPDDATE='"+Objects.toString(tglGudang,"")+"' "
                + " , PDDK='"+Objects.toString(d.getPendidikan(),"")+"' "
                + " , LAHIR='"+Objects.toString(d.getTanggalLahir(),"")+"' "
                + " , DATA01='"+Objects.toString(d.getTelepon(),"")+"' "
                + " , DATA02='"+Objects.toString(d.getHp(),"")+"' "
                + " , DATA03='"+Objects.toString(d.getEmail(),"")+"' "
                + " , DATA04='"+Objects.toString(d.getNoSalesforce(),"")+"' "
                + " , DATA05='"+Objects.toString(d.getNoRayon(),"")+"' "
                + " , DATA06='"+Objects.toString(d.getBlokSalesman(),"")+"' "
                + " , DATA08='"+Objects.toString(d.getTempatLahir(),"")+"' "
                + " , DATA09='"+Objects.toString(d.getJenisKelamin(),"")+"' "
                + " , DATA10='"+Objects.toString(d.getStatus(),"")+"' "
                + " , DATA11='"+Objects.toString(d.getBank(),"")+"' "
                + " , DATA12='"+Objects.toString(d.getAgama(),"")+"' "
                + " , DATA13='"+Objects.toString(d.getKpl(),"")+"' "
                + " , SLSEMPLOYEE = '"+Objects.toString(d.getSalesEmp(),"")+"' "
                + " , KNOWNAS = '"+Objects.toString(d.getKnownAs(),"")+"' "
                + " , flag_nonaktif = '"+Objects.toString(d.getNonAktif(),"")+"' "
                + " , tgl_nonaktif = '"+Objects.toString(d.getTanggalNonAktif(),"")+"' "
                + " WHERE SLSNO='"+Objects.toString(d.getNoSalesman(),"")+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void insertftabel12(String slsNo, String slsTeam, String tglGudang) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+".ftabel12(slsno, data2, nobrs, kode, tipe, kategori) "
				+ " SELECT '"+slsNo+"' as slskd, sbra2, rownum as no_urut, kode, tipe, kategori FROM "+t.getTenantId()+".ftabel141 WHERE team = '"+slsTeam+"' "
				+ " AND tipe = 1 AND tgl = (SELECT MAX(tgl) FROM "+t.getTenantId()+".ftabel141 WHERE team = '"+slsTeam+"' AND tipe = 1 AND tgl <= '"+tglGudang+"') "
				+ " UNION ALL "
				+ " SELECT '"+slsNo+"' as slskd, sbra2, rownum as no_urut, kode, tipe, kategori FROM "+t.getTenantId()+".ftabel141 WHERE team = '"+slsTeam+"' "
				+ " AND tipe = 2 AND tgl = (SELECT MAX(tgl) FROM "+t.getTenantId()+".ftabel141 WHERE team = '"+slsTeam+"' AND tipe = 2 AND tgl <= '"+tglGudang+"') "
				+ " UNION ALL "
				+ " SELECT '"+slsNo+"' as slskd, sbra2, rownum as no_urut, kode, tipe, kategori FROM "+t.getTenantId()+".ftabel141 WHERE team = '"+slsTeam+"' "
				+ " AND tipe = 3 AND tgl = (SELECT MAX(tgl) FROM "+t.getTenantId()+".ftabel141 WHERE team = '"+slsTeam+"' AND tipe = 3 AND tgl <= '"+tglGudang+"') "
				+ " UNION ALL "
				+ " SELECT '"+slsNo+"' as slskd, sbra2, rownum as no_urut, kode, tipe, kategori FROM "+t.getTenantId()+".ftabel141 WHERE team = '"+slsTeam+"' "
				+ " AND (tipe = 4 or tipe is null) AND tgl = (SELECT MAX(tgl) FROM "+t.getTenantId()+".ftabel141 WHERE team = '"+slsTeam+"' AND (tipe = 4 or tipe is null) AND tgl <= '"+tglGudang+"')";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateftable35(String slsNo, String kolektorNo, String kolektorName) {
		// TODO Auto-generated method stub
		String sql = " update "+t.getTenantId()+".ftable35 set kolektor='"+kolektorName+"',kodekol='"+kolektorNo+"' where slsno='"+slsNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> getValVanEod(String slsNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FMUTASIVAN_H where SLSNO='"+slsNo+"' and FLAGPROC is null  ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValStokVan(String slsNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FVAN where PCODE is not null and SLSNO='"+slsNo+"' and (DAYBEG+DAYIN-DAYOUT)<>0 ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValVanNotProcess(String slsNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FBOOKV_H where SLSNO='"+slsNo+"' and REFNO is null ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getValTransToday(String slsNo, String tglTransaksi) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " Select * from "+t.getTenantId()+".FJUAL_H where SLSNO='"+slsNo+"' and INVDATE='"+tglTransaksi+"' ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void updateBlokFsls(String slsNo, String tglGudang) {
		// TODO Auto-generated method stub
		String sql = " UPDATE "+t.getTenantId()+".FSLS  "
				+ "    SET DATA06='Y' "
				+ "       ,UPDDATE='"+tglGudang+"'  "
				+ "     , flag_nonaktif = 'Y' "
				+ "       ,tgl_nonaktif= '"+tglGudang+"' "
				+ " WHERE SLSNO='"+slsNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateBlokFCUSTMST(String slsNo, String tglGudang) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+t.getTenantId()+".FCUSTMST C "
				+ "  SET CLASTUPD='"+tglGudang+"' "
				+ " WHERE CUSTNO=( SELECT CUSTNO FROM "+t.getTenantId()+".FCUSTSLS S "
				+ " WHERE C.CUSTNO=S.CUSTNO AND S.SLSNO='"+slsNo+"' )";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteBlokFCUSTSLS(String slsNo) {
		// TODO Auto-generated method stub
		String sql = "DELETE "+t.getTenantId()+".FCUSTSLS WHERE SLSNO='"+slsNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteBlokFTABEL12(String slsNo) {
		// TODO Auto-generated method stub
		String sql = "DELETE "+t.getTenantId()+".FTABEL12 WHERE SLSNO='"+slsNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> getUBAHS() {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = " SELECT ID,NAMA FROM "+t.getTenantId()+".FPASSWORD where ID='UBAHS'  ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getFPASSWORD(String password) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<Object[]>();
		String sql = "";
		if (password.length() != 0) {
			sql = "SELECT ID,NAMA FROM "+t.getTenantId()+".FPASSWORD where ID='UBAHS' and nama ='"+password+"' ";
		} else {
			sql = "SELECT ID,NAMA FROM "+t.getTenantId()+".FPASSWORD where ID='UBAHS' and nama is null ";
		}

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();
		return resultList;
	}
	
	@Override
	public List<MSalesmanChoosenDto> getDataSalesmanSummary(){
		List<MSalesmanChoosenDto> result = new ArrayList<>();
		String sql = " 	SELECT SLSNO, SLSNO||' - '||SLSNAME as SLSNAME from "+t.getTenantId()+".fsls "
					+"	where DATA06<> 'Y' order by slsno ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] o : resultList) {
			MSalesmanChoosenDto data = new MSalesmanChoosenDto();
			data.setSlsno((String)o[0]);
			data.setSlsname((String)o[1]);
			data.setChecklist(true);
			result.add(data);
		}
		
		return result;
	}

}
