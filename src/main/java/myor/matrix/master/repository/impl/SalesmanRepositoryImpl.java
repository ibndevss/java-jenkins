package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SalesmanChoosenDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.SalesmanRepository;
import myor.matrix.master.tenant.TenantContext;
import myor.matrix.master.util.Util;

@Repository
public class SalesmanRepositoryImpl implements SalesmanRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Util util;
	
	@Override
	public List<SelectItem<String>> getListSalesmanSelectItem(String oprType) {
		// TODO Auto-generated method stub
		String sql = " SELECT DISTINCT slsno, slsno||' - '||slsname as salesman " 
					+"	 FROM "+t.getTenantId()+".fsls "
					+"  WHERE data06 = 'T' ";
		
		if (oprType.equalsIgnoreCase("T") || oprType.equalsIgnoreCase("C")) {
			sql += " AND oprtype = '"+oprType+"' ";
		} 
		sql += " ORDER BY slsno ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public SalesmanChoosenDto getSalesmanChoosen(String slsId) {
		// TODO Auto-generated method stub
		SalesmanChoosenDto result = new SalesmanChoosenDto();
		
		String sql = "SELECT "
				+"		slsno, "
				+ " 	slsname, "
				+"		oprtype, "
				+"		TO_CHAR(transdate, 'DD MON YYYY') transdate, "
				+"		team, "
				+"		kg, "
				+"		warehouse, "
				+"		slsforce "
				+"	FROM "
				+"		( "
				+"		SELECT "
				+"			s.slsno, "
				+"			s.slsname, "
				+"			s.slsname || '\' || s.slsno || '\' || s.oprtype AS salesman, "
				+"			s.kg, "
				+"			s.team || '-' || t.teamname AS team, "
				+"			s.oprtype, "
				+"		CASE "
				+"				WHEN s.oprtype = 'T' THEN 'TakingOrder' "
				+"				ELSE 'Canvas' "
				+"		END oprtypeName, "
				+"			s.transdate, "
				+"			l.kgname, "
				+"		CASE "
				+"				WHEN s.kg = '00' THEN TO_NCHAR('GUDANG UTAMA') "
				+"				ELSE l.kgname "
				+"		END warehouse, "
				+"			s.data04 slsforce "
				+"		FROM "
				+"			"+t.getTenantId()+".fsls s, "
				+"			"+t.getTenantId()+".flokgd l, "
				+"			"+t.getTenantId()+".fteam t "
				+"		WHERE "
				+"			(s.kg = l.kg(+)) "
				+"			AND (s.team = t.team(+)) "
				+"			AND slsno IN ( "
				+"			SELECT "
				+"				slsno "
				+"			FROM "
				+"				"+t.getTenantId()+".fsls "
				+"			WHERE "
				+"				oprtype IS NOT NULL) "
				+"			AND slsno = '"+slsId+"') "
				+"	order by "
				+"		slsno asc ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			result = new SalesmanChoosenDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3],
					(String) obj[4], (String) obj[5], (String) obj[6], (String) obj[7]);
		}
		
		return result;
	}

	@Override
	public List<SelectItem<String>> getDataSalesCanvas() {
		
		// TODO Auto-generated method stub
		String sql = " SELECT DISTINCT slsno, slsno||' '||slsname as salesman " 
					+"	 FROM "+t.getTenantId()+".fsls "
					+"  WHERE data06 = 'T' AND oprtype = 'C' "
					+"	ORDER BY slsno ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SelectItem<String>> getListSalesforce() {
		// TODO Auto-generated method stub
		List<SelectItem<String>> result = new ArrayList<>();
		String sql = "select DISTINCT data1 AS salesForceId,  (data1||'-'||data2) AS salesforce FROM "+t.getTenantId()+".ftabel10 ORDER BY data1";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<SalesmanBrowseDto> getBrowseSalesman(String oprType) {
		// TODO Auto-generated method stub
		List<SalesmanBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT   slsno, slsname, oprtype, team, data04 slsforce, TO_CHAR(TRANSDATE, 'DD MON YYYY') transDate, data06 block, kg FROM "+t.getTenantId()+".fsls "
					+"    WHERE data06 = 'T' ";
		
		if (oprType.equalsIgnoreCase("T") || oprType.equalsIgnoreCase("C")) {
			sql += " AND oprtype = '"+oprType+"' ";
		} 
				
		sql	+= " ORDER BY slsno ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SalesmanBrowseDto data = new SalesmanBrowseDto((String) obj[0], (String) obj[1], (String) obj[2],
					(String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6], (String) obj[7]);
			result.add(data);
		}
		
		return result;
	}
	
	@Override
	public List<SalesmanChoosenDto> getListSalesmanChoosenBy(String salesBy) {
		// TODO Auto-generated method stub
		List<SalesmanChoosenDto> result = new ArrayList<>();
		
		String sql = "SELECT "
				+"		slsno, "
				+ " 	slsname, "
				+"		oprtype, "
				+"		oprtypeName, "
				+"		TO_CHAR(transdate, 'DD MON YYYY') transdate, "
				+"		team, "
				+"		kg, "
				+"		warehouse, "
				+"		isTovas, "
				+"		slsforce "
				+"	FROM "
				+"		( "
				+"		SELECT "
				+"			s.slsno, "
				+"			s.slsname, "
				+"			s.slsname || '\' || s.slsno || '\' || s.oprtype AS salesman, "
				+"			s.kg, "
				+"			s.team || '-' || t.teamname AS team, "
				+"			s.oprtype, "
				+"			CASE "
				+"				WHEN s.oprtype = 'T' THEN 'TakingOrder' "
				+"				ELSE 'Canvas' "
				+"			END oprtypeName, "
				+"			s.transdate, "
				+"			l.kgname, "
				+"			CASE "
				+"				WHEN s.kg = '00' THEN TO_NCHAR('GUDANG UTAMA') "
				+"				ELSE l.kgname "
				+"			END warehouse, "
				+"			s.data04 slsforce, "
				+"			CASE "
				+"				WHEN f.slsno IS NOT NULL THEN 'true' " 
				+"				ELSE 'false'  "
				+"			END AS isTovas "
				+"		FROM "+t.getTenantId()+".fsls s " 
				+"		LEFT JOIN "+t.getTenantId()+".flokgd l ON (s.kg = l.kg) "
				+"		LEFT JOIN "+t.getTenantId()+".fteam t ON (s.team = t.team) "
				+"		LEFT JOIN ( "
				+"			SELECT memostring as slsno " 
				+"			FROM "+t.getTenantId()+".ftable13  "
				+"			WHERE xkey = 'SLSFORCE_TV' "
				+"		) f ON (s.slsno = f.slsno) "
				+"		WHERE "
				+"			s.slsno IN ( "
				+"			SELECT "
				+"				slsno "
				+"			FROM "
				+"				"+t.getTenantId()+".fsls "
				+"			WHERE "
				+"				oprtype IS NOT NULL ";
		
			if(salesBy.equalsIgnoreCase("1")) {
				sql+="			AND data06 <> 'Y' ";
			}
			
				sql+="		) "
				+"		) "
				+"	order by slsno asc ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			SalesmanChoosenDto data = new SalesmanChoosenDto((String) obj[0], (String) obj[1], (String) obj[2],
					(String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6], (String) obj[7],
					Boolean.parseBoolean(Objects.toString(obj[8])));
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<SalesmanChoosenDto> getListSalesmanViewKpl() {
		// TODO Auto-generated method stub
		List<SalesmanChoosenDto> result = new ArrayList<>();
		
		String tglGudang = util.getTglGudang();
		
		String sql = "SELECT "
				+ "	a.data2 AS salesno, "
				+ "	s.slsname AS salesname, "
				+ "	TO_CHAR(a.data1, 'DD MON YYYY') AS transdate, "
				+ "	s.oprtype AS TYPE, "
				+ "	a.data3 AS Information, "
				+ "	a.data4 AS username_usulan, "
				+ "	a.data5 AS username_otomatis "
				+ "FROM "
				+ "	"+t.getTenantId()+".ftabel45 a "
				+ "LEFT OUTER JOIN  "
				+ " "+t.getTenantId()+".FSLS s ON "
				+ "	(a.DATA2 = s.slsno)" 
				+" WHERE a.data1 >= (select add_months('"+tglGudang+"',-3) from dual) "		
				+"		and	a.data1 <= '"+tglGudang+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj: resultList) { 
			SalesmanChoosenDto data = new SalesmanChoosenDto(
					(String) obj[0],
					(String) obj[1],
					(String) obj[2],
					(String) obj[3],
					(String) obj[4],
					(String) obj[5],
					(String) obj[6]);
			result.add(data);
		}
		return result;
	}

	@Override
	public String getSalesforce(String slsNo) {
		// TODO Auto-generated method stub
		String sql = "SELECT data04, '0' as temp_val FROM "+t.getTenantId()+".fsls "
			+ "WHERE slsno = '"+slsNo+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		String result = "";
		for (Object[] obj : resultList) {
			result = Objects.toString(obj[0], ""); 
		}
		return result;
	}

	@Override
	public List<SalesmanBrowseDto> getBrowseInPengajuanRetur(String WHTRANSISI) {
		// TODO Auto-generated method stub
		List<SalesmanBrowseDto> result = new ArrayList<>();
		String sql = " SELECT   slsno, slsname, oprtype, team, data04 slsforce, TO_CHAR(TRANSDATE, 'DD MON YYYY') transDate, data06 block, kg FROM "+t.getTenantId()+".fsls "
				+"    WHERE data06 = 'T' ";
		if (!WHTRANSISI.equalsIgnoreCase("Y")) {
			sql += " AND OPRTYPE = 'T' ";
		}
		sql += " ORDER BY SLSNO desc ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SalesmanBrowseDto data = new SalesmanBrowseDto((String) obj[0], (String) obj[1], (String) obj[2],
					(String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6], (String) obj[7]);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<SalesmanBrowseDto> getSalesmanCover(String custno) {
		// TODO Auto-generated method stub
		List<SalesmanBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT s.slsno, a.slsname, a.oprtype, a.team "
					+" 	from "+t.getTenantId()+".fcustsls s left join "+t.getTenantId()+".fsls a on s.slsno = a.slsno " 
					+" WHERE s.custno = '"+custno+"' ";
		
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SalesmanBrowseDto data = new SalesmanBrowseDto((String) obj[0], (String) obj[1], (String) obj[2],
					(String) obj[3], null, null, null, null);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<SelectItem<String>> getListSalesmanSalesByTeam(String team) {
		// TODO Auto-generated method stub
		String sql = " SELECT DISTINCT slsno, slsno||' - '||slsname as salesman " 
				+"	 FROM "+t.getTenantId()+".fsls "
				+"  WHERE data06 = 'T' ";
		if (!team.equalsIgnoreCase("00")) {
			sql +=" and team = '"+team+"' ";
		}			
			sql	+=" AND data04 not in (select memostring from  "+t.getTenantId()+".FTABLE13 WHERE XKEY = 'SLSFORCE_LK' )  "
				+"	ORDER BY slsno ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}


	@Override
	public List<SalesmanBrowseDto> getSalesmanLaporanPiutangDagang() {
		// TODO Auto-generated method stub
		List<SalesmanBrowseDto> result = new ArrayList<>();
		
		String sql = "select distinct SLSNO, slsname, oprtype, data06 as block from  "+t.getTenantId()+".fsls order by slsno ";
		
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SalesmanBrowseDto data = new SalesmanBrowseDto((String) obj[0], (String) obj[1], (String) obj[2],
					null, null, null, (String) obj[3], null);
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<SelectItem<String>> getListSalesman() {
		// TODO Auto-generated method stub
		String sql = " SELECT SLSNO, SLSNAME FROM "+t.getTenantId()+".FSLS WHERE SLSNO IS NOT NULL ORDER BY SLSNO ASC ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SalesmanBrowseDto> getSalesmanValidasiKTP() {
	List<SalesmanBrowseDto> result = new ArrayList<>();
		
		String sql = "select slsno, slsname, oprtype from  "+t.getTenantId()+".fsls order by slsno ";
		
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SalesmanBrowseDto data = new SalesmanBrowseDto((String) obj[0], (String) obj[1], (String) obj[2],
					null, null, null, null, null);
			result.add(data);
		}
		
		return result;
	}


}
