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

import myor.matrix.master.entity.GetChannelChoosen;
import myor.matrix.master.entity.GetOutletChoosen;
import myor.matrix.master.entity.GetPassword;
import myor.matrix.master.entity.GetProduct;
import myor.matrix.master.entity.HargaDto;
import myor.matrix.master.entity.ProductBrowseInPengajuanSODto;
import myor.matrix.master.entity.ProductBrowseDto;
import myor.matrix.master.entity.ProductBrowserHargaSpesifikDto;
import myor.matrix.master.entity.ProductChoosenDto;
import myor.matrix.master.entity.ProductDetailVanDto;
import myor.matrix.master.entity.ProductVanBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.ProductRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void deleteData(String tableName, String where) {
		// TODO Auto-generated method stub
		String sql = "DELETE "+t.getTenantId()+"."+tableName+" "+where+" ";
	Query query = entityManager.createNativeQuery(sql);
	query.executeUpdate();
	}	

	@Override
	public List<SelectItem<String>> getListProductSelectItem() {
		String sql =  " SELECT DISTINCT pcode, pcode||' '||pcodename as product FROM " + t.getTenantId()+ ".fmaster " 
					+ "	 WHERE data10 = 'T' ORDER BY pcode";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}
	
	@Override
	public GetPassword getPassword(String id) {
		// TODO Auto-generated method stub
		GetPassword result = new GetPassword();
		
		String sql = "SELECT ID,NAMA FROM "+t.getTenantId()+".FPASSWORD where ID='"+id+"' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] obj : resultList) {
			result = new GetPassword((String) obj[0], (String) obj[1]);
		}
		return result;
	}

	@Override
	public ProductChoosenDto getProductChoosen(String pcode) {

		ProductChoosenDto result = new ProductChoosenDto();

		String sql = "SELECT a.*, tb2.data2 class1name, tb3.data2 class2name "
				+ " FROM (SELECT m.pcode, m.pcodename, m.matgr, m.cpro, m.prlin, m.brand,"
				+ "              m.sbra1, m.prkat, m.sbra2, m.flavr, m.packs, m.packt, m.princ,"
				+ "               m.weight1, m.weight2, m.weight3 berat, NVL (st.unitcost, 0) hpp, m.unit1, "
				+ "               m.unit2, m.unit3, m.convunit3, m.convunit2, m.sortno,  "
				+ "               m.xsftstock, m.buyprice1, m.buyprice2, m.buyprice3,  "
				+ "               m.sellprice1, m.sellprice2, m.sellprice3, m.mweekno, m.pbm, "
				+ "               m.plines lines, m.pbmjual, m.suppno, s.suppname, m.xminstock,"
				+ "               u.ud1, u.ud2, u.td1, u.td2, u.td3, u.td4, u.td5, m.rptname1,"
				+ "               m.rptname2, m.rptname3, pc.pcodename parentnm,"
				+ "               DECODE (m.flagmain, 'Y', 'Main', 'No') ketmain,"
				+ "               DECODE (m.flagbatch, 'Y', 'Ya', 'No') ketbatch,"
				+ "               DECODE (m.flagaktif, 'A', 'Active', 'Non-Active') ketak,"
				+ "               DECODE (m.jatah, 'Y', 'Yes', 'No') ketjt," + "               DECODE (m.tipeob, "
				+ "                       '1', '1.x Pkn RPP', " + "                       '2', '2.Rep.SafetyStock', "
				+ "                       '3.Rep x RPP' " + "                      ) tipeob, "
				+ "               m.pcparent pcparent, tcp.cproname, tpl.prliname, tbr.brandname, "
				+ "               ts1.sbra1name, ts2.sbra2name, tpk.prkatname, tfl.flavrname, "
				+ "               tps.packsname, tpt.packtname, tpc.princname, "
				+ "               DECODE (m.flagnew, 'Y', 'Yes', 'No') flagnew, m.data01, "
				+ "               m.data02, m.data03, m.data04, m.data05, m.data06, m.data07, "
				+ "               TO_CHAR(m.data08, 'DD MON YYYY') data08, m.data09, m.data10, m.data11, "
				+ "               DECODE (m.data12, 'Y', 'All Channel', 'Some Channel') blhjual, "
				+ "               DECODE (m.data14, 'Y', 'All Outlets', 'Some Outlets' ) blhjual2, "
				+ " (select memostring from "+t.getTenantId()+".fmemo where memonama = 'KODEDIST') memo, ut1.unitname unitname1, ut2.unitname unitname2,ut3.unitname unitname3,tb134.pcodev, tb134.pcodenameksv ,tb4.data1 prodclass1, tb5.data1 prodclass2, ROUND((m.data04 * m.data05 * m.data06)/1000000, 3) as volume " + "          FROM "
				+ t.getTenantId() + ".fmaster m, " + "               " + t.getTenantId() + ".fmasterst st, "
				+ "               " + t.getTenantId() + ".fsupplier s, " + "               " + t.getTenantId()
				+ ".fmasterud u, " + "               " + t.getTenantId() + ".ftconpro tcp, " + "               "
				+ t.getTenantId() + ".ftproline tpl, " + "               " + t.getTenantId() + ".ftbrand tbr, "
				+ "               " + t.getTenantId() + ".ftsbrand1 ts1, " + "               " + t.getTenantId()
				+ ".ftsbrand2 ts2, " + "               " + t.getTenantId() + ".ftprokateg tpk, " + "               "
				+ t.getTenantId() + ".ftflavor tfl, " + "               " + t.getTenantId() + ".ftpacksize tps, "
				+ "               " + t.getTenantId() + ".ftpacktype tpt, " + "               " + t.getTenantId()
				+ ".ftprincipel tpc, " + "               " + t.getTenantId() + ".fmaster pc, " + "               "
				+ t.getTenantId() + ".ftabel1 t1, " + "               " + t.getTenantId() + ".ftabel2 t2, "
				+ "               " + t.getTenantId() + ".ftabel3 t3, " + "               " + t.getTenantId()
				+ ".ftabel4 t4, " + "               " + t.getTenantId() + ".ftabel5 t5, " + "               "
				+ t.getTenantId() + ".fhirar11 h11, " + "               " + t.getTenantId() + ".fhirar12 h12, "
				+ "               " + t.getTenantId() + ".fhirar13 h13, " + "               " + t.getTenantId()
				+ ".fhirar14 h14, " + "               " + t.getTenantId() + ".fhirar15 h15, " + "               "
				+ t.getTenantId() + ".fhirar21 h21, " + "               " + t.getTenantId() + ".fhirar22 h22, "
				+ "               " + t.getTenantId() + ".fhirar23 h23, " + "               " + t.getTenantId()
				+ ".fhirar24 h24, " + "               " + t.getTenantId() + ".fhirar25 h25, " + "               "
				+ t.getTenantId() + ".fhirar31 h31, " + "               " + t.getTenantId() + ".fhirar32 h32, "
				+ "               " + t.getTenantId() + ".fhirar33 h33, " + "               " + t.getTenantId()
				+ ".fhirar34 h34, " + "               " + t.getTenantId() + ".fhirar35 h35, " + "               "
				+ t.getTenantId() + ".fhirar41 h41, " + "               " + t.getTenantId() + ".fhirar42 h42, "
				+ "               " + t.getTenantId() + ".fhirar43 h43, " + "               " + t.getTenantId()
				+ ".fhirar44 h44, " + "               " + t.getTenantId() + ".fhirar45 h45, " + "               "
				+ t.getTenantId() + ".ftabel04 tb4, " + "               " + t.getTenantId() + ".ftabel05 tb5,  "
						+ " 			  " + t.getTenantId() +".funit ut3,"
						+ "               " + t.getTenantId() +".funit ut2,"
						+ "               " + t.getTenantId() +".funit ut1,"
						+ "               " + t.getTenantId() +".ftable134 tb134 "
				+ "         WHERE (m.pcode = st.pcode(+)) " + "           AND (m.pcode = u.pcode(+)) "
				+ "           AND (m.suppno = s.suppno(+)) " + "           AND (m.cpro = tcp.cpro(+)) "
				+ "           AND (m.unit3 = ut3.unit(+)) " + "           AND (m.unit2 = ut2.unit(+)) "
				+ "           AND (m.unit1 = ut1.unit(+)) " + "           AND (m.pcode = tb134.pcodev(+)) "
				+ "           AND (m.prlin = tpl.prlin(+)) " + "           AND (m.pcparent = pc.pcode(+)) "
				+ "           AND (m.brand = tbr.brand(+)) " + "           AND (m.sbra1 = ts1.sbra1(+)) "
				+ "           AND (m.prlin = tbr.prlin(+)) " + "           AND (m.prlin = ts1.prlin(+)) "
				+ "           AND (m.brand = ts1.brand(+)) " + "           AND (m.prkat = tpk.prkat(+)) "
				+ "           AND (m.sbra2 = ts2.sbra2(+)) " + "           AND (m.flavr = tfl.flavr(+)) "
				+ "           AND (m.packs = tps.packs(+)) " + "           AND (m.packt = tpt.packt(+)) "
				+ "           AND (m.princ = tpc.princ(+)) " + "           AND (u.td1 = t1.ID(+)) "
				+ "           AND (u.td2 = t2.ID(+)) " + "           AND (u.td3 = t3.ID(+)) "
				+ "           AND (u.td4 = t4.ID(+)) " + "           AND (u.td5 = t5.ID(+)) "
				+ "           AND (u.h11 = h11.t11(+)) " + "           AND (u.h11 = h12.t11(+)) "
				+ "           AND (u.h11 = h13.t11(+)) " + "           AND (u.h11 = h14.t11(+)) "
				+ "           AND (u.h11 = h15.t11(+)) " + "           AND (u.h12 = h12.t12(+)) "
				+ "           AND (u.h12 = h13.t12(+)) " + "           AND (u.h12 = h14.t12(+)) "
				+ "           AND (u.h12 = h15.t12(+)) " + "           AND (u.h13 = h13.t13(+)) "
				+ "           AND (u.h13 = h14.t13(+)) " + "           AND (u.h13 = h15.t13(+)) "
				+ "           AND (u.h14 = h14.t14(+)) " + "           AND (u.h14 = h15.t14(+)) "
				+ "           AND (u.h15 = h15.t15(+)) " + "           AND (u.h21 = h21.t11(+)) "
				+ "           AND (u.h21 = h22.t11(+)) " + "           AND (u.h21 = h23.t11(+)) "
				+ "           AND (u.h21 = h24.t11(+)) " + "           AND (u.h21 = h25.t11(+)) "
				+ "           AND (u.h22 = h22.t12(+)) " + "           AND (u.h22 = h23.t12(+)) "
				+ "           AND (u.h22 = h24.t12(+)) " + "           AND (u.h22 = h25.t12(+)) "
				+ "           AND (u.h23 = h23.t13(+)) " + "           AND (u.h23 = h24.t13(+)) "
				+ "           AND (u.h23 = h25.t13(+)) " + "           AND (u.h24 = h24.t14(+)) "
				+ "           AND (u.h24 = h25.t14(+)) " + "           AND (u.h25 = h25.t15(+)) "
				+ "           AND (u.h31 = h31.t11(+)) " + "           AND (u.h31 = h32.t11(+)) "
				+ "           AND (u.h31 = h33.t11(+)) " + "           AND (u.h31 = h34.t11(+)) "
				+ "           AND (u.h31 = h35.t11(+)) " + "           AND (u.h32 = h32.t12(+)) "
				+ "           AND (u.h32 = h33.t12(+)) " + "           AND (u.h32 = h34.t12(+)) "
				+ "           AND (u.h32 = h35.t12(+)) " + "           AND (u.h33 = h33.t13(+)) "
				+ "           AND (u.h33 = h34.t13(+)) " + "           AND (u.h33 = h35.t13(+)) "
				+ "           AND (u.h34 = h34.t14(+)) " + "           AND (u.h34 = h35.t14(+)) "
				+ "           AND (u.h35 = h35.t15(+)) " + "           AND (u.h41 = h41.t11(+)) "
				+ "           AND (u.h41 = h42.t11(+)) " + "           AND (u.h41 = h43.t11(+)) "
				+ "           AND (u.h41 = h44.t11(+)) " + "           AND (u.h41 = h45.t11(+)) "
				+ "           AND (u.h42 = h42.t12(+)) " + "           AND (u.h42 = h43.t12(+)) "
				+ "           AND (u.h42 = h44.t12(+)) " + "           AND (u.h42 = h45.t12(+)) "
				+ "           AND (u.h43 = h43.t13(+)) " + "           AND (u.h43 = h44.t13(+)) "
				+ "           AND (u.h43 = h45.t13(+)) " + "           AND (u.h44 = h44.t14(+)) "
				+ "           AND (u.h44 = h45.t14(+)) " + "           AND (u.h45 = h45.t15(+)) "
				+ "           AND (m.pcode = tb4.pcode(+)) " + "           AND (m.pcode = tb5.pcode(+)) "
				+ "           AND m.pcode = " + pcode + ") a " + "       LEFT JOIN " + "       " + t.getTenantId()
				+ ".ftabel02 tb2 ON a.prodclass1 = tb2.data1 " + "       LEFT JOIN " + t.getTenantId()
				+ ".ftabel03 tb3 ON a.prodclass2 = tb3.data1 ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		for (Object[] obj : resultList) {
			result = new ProductChoosenDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""), Objects.toString(obj[2], ""), Objects.toString(obj[3], ""),
					Objects.toString(obj[4], ""), Objects.toString(obj[5], ""), Objects.toString(obj[6], ""), Objects.toString(obj[7], ""), Objects.toString(obj[8], ""),
					Objects.toString(obj[9], ""), Objects.toString(obj[10], ""), Objects.toString(obj[11], ""), Objects.toString(obj[12], ""),
					new BigDecimal(Objects.toString(obj[13], "0")),
					new BigDecimal(Objects.toString(obj[14], "0")),
					new BigDecimal(Objects.toString(obj[15], "0")),
					new BigDecimal(Objects.toString(obj[16], "0")),
					Objects.toString(obj[17], ""),
					Objects.toString(obj[18], ""),
					Objects.toString(obj[19], ""), 
					new BigDecimal(Objects.toString(obj[20], "0")), 
					new BigDecimal(Objects.toString(obj[21], "0")),
					new BigDecimal(Objects.toString(obj[22], "0")),
					Objects.toString(obj[23], ""), 
					new BigDecimal(Objects.toString(obj[24], "0")), 
					new BigDecimal(Objects.toString(obj[25], "0")),
					new BigDecimal(Objects.toString(obj[26], "0")), 
					new BigDecimal(Objects.toString(obj[27], "0")), 
					new BigDecimal(Objects.toString(obj[28], "0")), 
					new BigDecimal(Objects.toString(obj[29], "0")),
					new BigDecimal(Objects.toString(obj[30], "0")), 
					new BigDecimal(Objects.toString(obj[31], "0")), 
					new BigDecimal(Objects.toString(obj[32], "0")),
					new BigDecimal(Objects.toString(obj[33], "0")),
					Objects.toString(obj[34], ""), Objects.toString(obj[35], ""), Objects.toString(obj[36], ""), Objects.toString(obj[37], ""), Objects.toString(obj[38], ""),
					Objects.toString(obj[39], ""), Objects.toString(obj[40], ""), Objects.toString(obj[41], ""), Objects.toString(obj[42], ""), Objects.toString(obj[43], ""),
					Objects.toString(obj[44], ""), Objects.toString(obj[45], ""), Objects.toString(obj[46], ""), Objects.toString(obj[47], ""), Objects.toString(obj[48], ""),
					Objects.toString(obj[49], ""), Objects.toString(obj[50], ""), Objects.toString(obj[51], ""), Objects.toString(obj[52], ""), Objects.toString(obj[53], ""),
					Objects.toString(obj[54], ""), Objects.toString(obj[55], ""), Objects.toString(obj[56], ""), Objects.toString(obj[57], ""), Objects.toString(obj[58], ""),
					Objects.toString(obj[59], ""), Objects.toString(obj[60], ""), Objects.toString(obj[61], ""), Objects.toString(obj[62], ""), Objects.toString(obj[63], ""),
					Objects.toString(obj[64], ""), 
					new BigDecimal(Objects.toString(obj[65], "0")), 
					new BigDecimal(Objects.toString(obj[66], "0")), 
					new BigDecimal(Objects.toString(obj[67], "0")),
					new BigDecimal(Objects.toString(obj[68], "0")),
					new BigDecimal(Objects.toString(obj[69], "0")),
					new BigDecimal(Objects.toString(obj[70], "0")),
					Objects.toString(obj[71], ""),
					Objects.toString(obj[72], ""), Objects.toString(obj[73], ""), Objects.toString(obj[74], ""), Objects.toString(obj[75], ""), Objects.toString(obj[76], ""),
					Objects.toString(obj[77], ""), Objects.toString(obj[84], ""), Objects.toString(obj[85], ""), Objects.toString(obj[87], ""), Objects.toString(obj[88], ""), 
					Objects.toString(obj[78], ""), Objects.toString(obj[79], ""), Objects.toString(obj[80], ""), 
					Objects.toString(obj[81], ""), new Double(Objects.toString(obj[86], "0")), Objects.toString(obj[82], ""), Objects.toString(obj[83], ""));
		}
		resultList.clear();
		return result;
	}

	@Override
	public List<GetChannelChoosen> getChannelChoosen(String pcode) {
		// TODO Auto-generated method stub
		List<GetChannelChoosen> result = new ArrayList<>();
		String sql = "SELECT DATA1,DATA2,TYPENAME FROM "+t.getTenantId()+".FTABEL13, "+t.getTenantId()+".FTYPEOUT WHERE DATA2=TYPE(+) AND DATA1='"+pcode+"' ORDER BY DATA2";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) {
			GetChannelChoosen data = new GetChannelChoosen((String) obj[0], (String) obj[1], (String) obj[2]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<GetOutletChoosen> getOutletChoosen(String pcode) {
		// TODO Auto-generated method stub
		List<GetOutletChoosen> result = new ArrayList<>();
		String sql = "select subdist_id, a.custno, custname from "+t.getTenantId()+".fproduct_outlet a left join "+t.getTenantId()+".fcustmst b on a.custno= b.custno "
				+ "and a.subdist_id=(select memostring from "+t.getTenantId()+".fmemo where memonama = 'KODEDIST')  where "
				+ " pcode = '"+pcode+"' and subdist_id=(select memostring from "+t.getTenantId()+".fmemo where memonama = 'KODEDIST') order by subdist_id, custno";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) {
			GetOutletChoosen data = new GetOutletChoosen((String) obj[0], (String) obj[1], (String) obj[2]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<GetProduct> getAllProduct() {
		// TODO Auto-generated method stub
		List<GetProduct> result = new ArrayList<>();
		String sql = "select m.jatah, m.pcode, m.pcodename, m.sbra2||' - '||ts2.sbra2name as divisi , m.prlin||' - '||tpl.prliname as umbrella, "
				+ "m.brand||' - '||tbr.brandname as brand, m.sbra1||' - '||ts1.sbra1name as subbrand, "
				+ " CASE "
				+ "    WHEN m.jatah = 'Y' "
				+ "         THEN 'true' "
				+ "            ELSE 'false'"
				+ "END penjatahan from "+t.getTenantId()+".fmaster m "
				+ "left join "+t.getTenantId()+".ftsbrand2 ts2 on m.sbra2 = ts2.sbra2 "
				+ "left join "+t.getTenantId()+".ftproline tpl on m.prlin = tpl.prlin "
				+ "left join "+t.getTenantId()+".ftbrand tbr on m.brand = tbr.brand "
				+ "left join "+t.getTenantId()+".ftsbrand1 ts1 on m.sbra1 = ts1.sbra1 "
				+ "order by m.pcode ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		for(Object[] obj : hasil) {
			GetProduct data = new GetProduct((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6], Boolean.parseBoolean((String) obj[7]), "0"); 
			result.add(data);
		}
		return result;
	}

	@Override
	public void updateJatah(String pcode, Boolean check) {
		// TODO Auto-generated method stub
		char setJatah;
		if (check == true) {
			setJatah = 'Y';
		} else {
			setJatah = 'T';
		}
		String sql = " update "+t.getTenantId()+".fmaster set jatah = '"+setJatah+"' where pcode = '"+pcode+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public ProductDetailVanDto getDetail(String slsno, String pcode) {
		ProductDetailVanDto result = new ProductDetailVanDto();
		
		String sql = " SELECT a.slsno, a.pcode, b.pcodename, TO_CHAR(b.buyprice1) buyprice1 , TO_CHAR(b.buyprice2) buyprice2, TO_CHAR(b.buyprice3) buyprice3 "
					+"   FROM "+t.getTenantId()+".fvan a INNER JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode "
					+"	WHERE a.slsno = '"+slsno+"' AND a.pcode = '"+pcode+"' ";		
		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			ProductDetailVanDto data = new ProductDetailVanDto((String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5]);			
			result = data;
		}		
		
		return result;
	}

	@Override
	public List<ProductVanBrowseDto> getBrowsePcodeVan(String slsno) {
		// TODO Auto-generated method stub
		List<ProductVanBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT   a.pcode, b.pcodename "
					+"     FROM "+t.getTenantId()+".fvan a INNER JOIN "+t.getTenantId()+".fmaster b ON a.pcode = b.pcode "
					+"    WHERE a.slsno = '"+slsno+"' "
					+" ORDER BY b.sbra2 ASC, b.prlin || b.brand || b.sbra1 ASC, a.pcode ASC ";
		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			ProductVanBrowseDto data = new ProductVanBrowseDto((String) obj[0], (String) obj[1]);			
			result.add(data);
		}
		return result;
	}

	@Override
	public List<ProductBrowseDto> getBrowsePcodeRO(String slsNo, String tipeOpr, String team) {
		// TODO Auto-generated method stub
		List<ProductBrowseDto> result = new ArrayList<>();
		
		String sql = "SELECT DISTINCT pcode, pcodename, buyprice1, buyprice2, buyprice3, data09, data10, convunit3, convunit2 "
             +"    FROM (SELECT pcode, pcodename, buyprice1, buyprice2, buyprice3, data09, data10, convunit3, convunit2 "
             +"            FROM (SELECT m.pcode, m.pcodename, "
             +"                         m.buyprice1 AS buyprice1, "
             +"                         m.buyprice2 AS buyprice2, "
             +"                         m.buyprice3 AS buyprice3, m.sbra2 divisi, "
             +"                         m.prlin || m.brand || m.sbra1 AS subbrand, "
             +" 						m.data09, m.data10, m.convunit3, m.convunit2 ";
		
		if(tipeOpr.equalsIgnoreCase("T")) {
			sql+="				  FROM "+t.getTenantId()+".fmasterst v LEFT JOIN "+t.getTenantId()+".fmaster m "
				+"						   ON m.pcode = v.pcode "
				+"					WHERE ";
		}else {
			sql+="				  FROM "+t.getTenantId()+".fvan v LEFT JOIN "+t.getTenantId()+".fmaster m "
				+"                         ON m.pcode = v.pcode "
				+"                  WHERE v.slsno = '"+slsNo+"' "
				+"						AND ";
		}
		
        sql+="                    m.sbra2 IN ( "
             +"                            SELECT data2 "
             +"                              FROM "+t.getTenantId()+".ftabel12 "
             +"                             WHERE slsno = '"+slsNo+"' "
             +"                               AND data2 NOT IN ( "
             +"                                      SELECT kdmapping "
             +"                                        FROM "+t.getTenantId()+".ftable279 "
             +"                                       WHERE team = '"+team+"' "
             +"                                         AND tipe = '3' "
             +"                                         AND flag = '2')) "
             +"                     AND m.prlin || m.brand || m.sbra1 NOT IN ( "
             +"                            SELECT kdmapping "
             +"                              FROM "+t.getTenantId()+".ftable279 "
             +"                             WHERE team = '"+team+"' "
             +"                               AND tipe = '2' "
             +"                               AND flag = '2') "
             +"                     AND m.pcode NOT IN ( "
             +"                            SELECT kdmapping "
             +"                              FROM "+t.getTenantId()+".ftable279 "
             +"                             WHERE team = '"+team+"' "
             +"                               AND tipe = '1' "
             +"                               AND flag = '2')) "
             +"          UNION ALL "
             +"          SELECT DISTINCT b.pcode, b.pcodename, b.buyprice1, "
             +"                          b.buyprice2, b.buyprice3, m.data09, m.data10, m.convunit3, m.convunit2 ";
        
        if(tipeOpr.equalsIgnoreCase("T")) {
        	sql+="                 FROM "+t.getTenantId()+".fmasterst a ";
        }else {
        	sql+="                 FROM "+t.getTenantId()+".fvan a ";
        }
        
        sql+="                      LEFT JOIN "+t.getTenantId()+".fmaster m ON (a.pcode = m.pcode) "
        	 +"						INNER JOIN "
             +"                          (SELECT a.kdmapping pcode, b.pcodename, "
             +"                                  b.buyprice1 AS buyprice1, "
             +"                                  b.buyprice2 AS buyprice2, "
             +"                                  b.buyprice3 AS buyprice3 "
             +"                             FROM "+t.getTenantId()+".ftable279 a LEFT JOIN "+t.getTenantId()+".fmaster b "
             +"                                  ON a.kdmapping = b.pcode "
             +"                            WHERE a.team = '"+team+"' "
             +"                              AND a.tipe = '1' "
             +"                              AND a.flag = '1' "
             +"                           UNION ALL "
             +"                           SELECT b.pcode, b.pcodename, "
             +"                                  b.buyprice1 AS buyprice1, "
             +"                                  b.buyprice2 AS buyprice2, "
             +"                                  b.buyprice3 AS buyprice3 "
             +"                             FROM "+t.getTenantId()+".ftable279 a LEFT JOIN "+t.getTenantId()+".fmaster b "
             +"                                  ON a.kdmapping = b.prlin || b.brand || b.sbra1 "
             +"                            WHERE a.team = '"+team+"' "
             +"                              AND a.tipe = '2' "
             +"                              AND a.flag = '1' "
             +"                           UNION ALL "
             +"                           SELECT b.pcode, b.pcodename, "
             +"                                  b.buyprice1 AS buyprice1, "
             +"                                  b.buyprice2 AS buyprice2, "
             +"                                  b.buyprice3 AS buyprice3 "
             +"                             FROM "+t.getTenantId()+".ftable279 a LEFT JOIN "+t.getTenantId()+".fmaster b "
             +"                                  ON a.kdmapping = b.sbra2 "
             +"                            WHERE a.team = '"+team+"' "
             +"                              AND a.tipe = '3' "
             +"                              AND a.flag = '1') b ON a.pcode = b.pcode ";
        
        if(tipeOpr.equalsIgnoreCase("T") == false) {
        	sql+="                 WHERE a.slsno = '"+slsNo+"' ";
        }
        
        sql+="   ) "
        	+"	WHERE pcode NOT LIKE '7%' "
        	+"	ORDER BY pcode ";
		
        Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			ProductBrowseDto data = new ProductBrowseDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), ((BigDecimal) obj[2]).doubleValue(),
					((BigDecimal) obj[3]).doubleValue(), ((BigDecimal) obj[4]).doubleValue(),
					Objects.toString((String) obj[5], ""), Objects.toString((String) obj[6], ""),
					((BigDecimal) obj[7]).doubleValue(), ((BigDecimal) obj[8]).doubleValue());
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<ProductBrowseDto> getBrowsePcodeStockOutlet(String slsNo) {
		// TODO Auto-generated method stub
		List<ProductBrowseDto> result = new ArrayList<>();
		
		String sql = "SELECT pcode, pcodename, buyprice1, buyprice2, buyprice3, data09, data10, convunit3, convunit2 "
				+" 	FROM ( "
				+"		SELECT pcode, pcodename, buyprice1, buyprice2, buyprice3, data09, data10, convunit3, convunit2 "
				+" 		FROM "+t.getTenantId()+".fmaster "
				+" 		WHERE sbra2 IN ( "
				+"			SELECT data2 "
				+" 			FROM "+t.getTenantId()+".ftabel12 "
				+" 			WHERE slsno = '"+slsNo+"' "
				+"		)"
				+"	) ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			ProductBrowseDto data = new ProductBrowseDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), ((BigDecimal) obj[2]).doubleValue(),
					((BigDecimal) obj[3]).doubleValue(), ((BigDecimal) obj[4]).doubleValue(),
					Objects.toString((String) obj[5], ""), Objects.toString((String) obj[6], ""),
					((BigDecimal) obj[7]).doubleValue(), ((BigDecimal) obj[8]).doubleValue());
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<ProductBrowseDto> getBrowseAllPcode() {
		// TODO Auto-generated method stub
		List<ProductBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT pcode, pcodename, buyprice1, buyprice2, buyprice3, data09, data10, convunit3, convunit2 "
					+"   FROM "+t.getTenantId()+".fmaster ORDER BY pcode ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			ProductBrowseDto data = new ProductBrowseDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), ((BigDecimal) obj[2]).doubleValue(),
					((BigDecimal) obj[3]).doubleValue(), ((BigDecimal) obj[4]).doubleValue(),
					Objects.toString((String) obj[5], ""), Objects.toString((String) obj[6], ""),
					((BigDecimal) obj[7]).doubleValue(), ((BigDecimal) obj[8]).doubleValue());
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<ProductBrowserHargaSpesifikDto> getViewBrowserHargaSpefisik() {
		List<ProductBrowserHargaSpesifikDto> result = new ArrayList<>();
		String sql = "SELECT "
				+ "	t14.data1 pcode, "
				+ "	m.pcodename AS pcodename, "
				+ "	t14.data3 code, "
				+ "	DECODE (t14.data2,'100', "
				+ "		c.custname,'200', "
				+ "		g.grupname,'300', "
				+ "		t10.data2,'400', "
				+ "		t.typename,'500', "
				+ "		h.ket,'ALL' ) kodename, "
				+ "	to_char(t14.data4, 'dd MON yyyy') start_date, "
				+ "	COALESCE(t14.data5,0) toleransi, "
				+ "	t14.data2 TYPE, "
				+ "	t14.data6 sell1, "
				+ "	t14.data7 sell2, "
				+ "	t14.data8 sell3, "
				+ "	to_char(t14.data9, 'dd MON yyyy') end_date, "
				+ "	g.grupname, "
				+ "	t10.data2 sforname, "
				+ "	t.typename , "
				+ "	CASE WHEN h.ket IS NULL THEN '-'  "
				+ "	ELSE h.ket END AS gruphrgname, "
				+ "	CASE WHEN t14.data2 IS NULL THEN '-' "
				+ "	ELSE  "
				+ "	DECODE (t14.data2, "
				+ "	'100', "
				+ "	'100.OUTLET', "
				+ "	'200', "
				+ "	'200.GRUPOUTLET', "
				+ "	'300', "
				+ "	'300.SFORCE', "
				+ "	'400', "
				+ "	'400.CHANNEL', "
				+ "	'500', "
				+ "	'500.GRUPHARGA', "
				+ "	'ALL' ) END AS kettipe, "
				+ "	 c.custname "
				+ "FROM "
				+ "	"+t.getTenantId()+".ftabel14 t14 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fmaster m ON "
				+ "	t14.data1 = m.pcode "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fcustmst c ON "
				+ "	t14.data3 = c.custno "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftypeout t ON "
				+ "	t14.data3 = t.TYPE "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fgrupout g ON "
				+ "	t14.data3 = g.grupout "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".ftabel10 t10 ON "
				+ "	t14.data3 = t10.data1 "
				+ "LEFT OUTER JOIN "+t.getTenantId()+".fgharga h ON "
				+ "	t14.data3 = h.gharga "
				+ "ORDER BY "
				+ "	T14.DATA4, "
				+ "	T14.DATA2";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList) { 
			ProductBrowserHargaSpesifikDto data = new ProductBrowserHargaSpesifikDto(
					(String)obj[0],
					(String)obj[1],
					(String)obj[2],
					(String)obj[3],
					(String)obj[4],
					((BigDecimal)obj[5]).intValue(),
					(String)obj[6],
					((BigDecimal) obj[7]).doubleValue(),
					((BigDecimal) obj[8]).doubleValue(),
					((BigDecimal) obj[9]).doubleValue(),
					(String)obj[10],
					(String)obj[11],
					(String)obj[12],
					(String)obj[13],
					(String)obj[14],
					(String)obj[15],
					(String)obj[16]);
			result.add(data);
		}
		
		return result;
	}

	public List<ProductBrowseDto> getBrowsePcodeGudangUtama() {
		// TODO Auto-generated method stub
		List<ProductBrowseDto> result = new ArrayList<>();
		String sql = " select gd.pcode, mt.pcodename, mt.buyprice1, mt.buyprice2, mt.buyprice3, mt.data09, mt.data10, mt.convunit3, mt.convunit2 " 
					+"			from "+t.getTenantId()+".fmasterst gd  "
					+"			inner join "+t.getTenantId()+".fmaster mt on gd.pcode = mt.pcode "  
					+"	order BY gd.pcode ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			ProductBrowseDto data = new ProductBrowseDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), ((BigDecimal) obj[2]).doubleValue(),
					((BigDecimal) obj[3]).doubleValue(), ((BigDecimal) obj[4]).doubleValue(),
					Objects.toString((String) obj[5], ""), Objects.toString((String) obj[6], ""),
					((BigDecimal) obj[7]).doubleValue(), ((BigDecimal) obj[8]).doubleValue());
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<ProductBrowseDto> getBrowsePcodeGudangTambahan(String kdGudang) {
		// TODO Auto-generated method stub
		List<ProductBrowseDto> result = new ArrayList<>();
		String sql = "select gd.pcode, mt.pcodename, mt.buyprice1, mt.buyprice2, mt.buyprice3, mt.data09, mt.data10, mt.convunit3, mt.convunit2 "
					+"			from "+t.getTenantId()+".fwhstock gd " 
					+"			inner join "+t.getTenantId()+".fmaster mt on gd.pcode = mt.pcode "  
					+"	WHERE gd.kg = '"+kdGudang+"'	"
					+"	order BY gd.pcode ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			ProductBrowseDto data = new ProductBrowseDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), ((BigDecimal) obj[2]).doubleValue(),
					((BigDecimal) obj[3]).doubleValue(), ((BigDecimal) obj[4]).doubleValue(),
					Objects.toString((String) obj[5], ""), Objects.toString((String) obj[6], ""),
					((BigDecimal) obj[7]).doubleValue(), ((BigDecimal) obj[8]).doubleValue());
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<ProductBrowseDto> getBrowsePcodeOpname(String kdGudang) {
		// TODO Auto-generated method stub
		List<ProductBrowseDto> result = new ArrayList<>();		
		
		String tableName = "";
		
		if (kdGudang.equalsIgnoreCase("00") || kdGudang.equalsIgnoreCase("R1")) {
			tableName = "fmasterst";
		} else {
			tableName = "fwhstock";
		}
		
		
		String sql =" select gd.pcode, mt.pcodename, mt.buyprice1, mt.buyprice2, mt.buyprice3, mt.data09, mt.data10, mt.convunit3, mt.convunit2 " 
					+"			from "+t.getTenantId()+"."+tableName+" gd  "
					+"			inner join "+t.getTenantId()+".fmaster mt on gd.pcode = mt.pcode ";
				if (kdGudang.equalsIgnoreCase("00") || kdGudang.equalsIgnoreCase("R1")) {
					sql +="  WHERE gd.pcode NOT LIKE '7%'	";
				} else {
					sql +=" WHERE kg = '"+kdGudang+"' ";
				}					
				sql	+="	order BY gd.pcode ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			ProductBrowseDto data = new ProductBrowseDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), ((BigDecimal) obj[2]).doubleValue(),
					((BigDecimal) obj[3]).doubleValue(), ((BigDecimal) obj[4]).doubleValue(),
					Objects.toString((String) obj[5], ""), Objects.toString((String) obj[6], ""),
					((BigDecimal) obj[7]).doubleValue(), ((BigDecimal) obj[8]).doubleValue());
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<SelectItem<String>> getListPcodeOpnameSelectItem(String kdGudang) {
		// TODO Auto-generated method stub
		String sql =  " SELECT DISTINCT pcode, pcode||' '||pcodename as product FROM " + t.getTenantId()+ ".fmaster " 
				+ "	 WHERE data10 = 'T' ";
			if (kdGudang.equalsIgnoreCase("00") || kdGudang.equalsIgnoreCase("R1")) {
				sql +=" AND pcode in (select distinct pcode from "+t.getTenantId()+".fmasterst WHERE pcode NOT LIKE '7%') ";
			} else {
				sql +=" AND pcode in (select distinct pcode from "+t.getTenantId()+".fwhstock WHERE kg = '"+kdGudang+"') ";
			}
			sql	+= " ORDER BY pcode";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
	
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public HargaDto getHargaProductHirarki(String pcode, String tgl, String custNo, String groupOut, String salesForce,
			String channel, String groupHarga) {
		// TODO Auto-generated method stub
		String sql = " SELECT pcode, hirarki, atribut, tglawal, data9 as hrgakhir, data6 AS hargakecil, "
			+"      data7 AS hargatengah, data8 AS hargabesar  "
			+" FROM (SELECT *  "
			+"         FROM (SELECT ROWNUM nomer, pcode, hirarki, atribut, tglawal  "
			+"                 FROM (SELECT   pcode, hirarki, atribut  "
			+"                                , MAX (tglawal) tglawal  "
			+"                           FROM (  "
			+"                            select * from (  "
			+"                           SELECT data1 pcode, data2 hirarki,  "
			+"                                        data4 tglawal, "
			+"                                        data6 hrgkcl, data7 hrgtgh,  "
			+"                                        data8 hrgbsr, data3,  "
			+"                                        CASE  "
			+"                                               WHEN data2 = '100'  "
			+"                                                  THEN '"+custNo+"'   "
			+"                                               WHEN data2 = '200'    "
			+"                                                  THEN '"+groupOut+"'  "
			+"                                               WHEN data2 = '300'  "
			+"                                                  THEN '"+salesForce+"'  "
			+"                                               WHEN data2 = '400'  "
			+"                                                  THEN '"+channel+"'  "
			+"                                               WHEN data2 = '500'  "
			+"                                                  THEN '"+groupHarga+"'  "
			+"                                        END atribut  "
			+"                                   FROM " + t.getTenantId()+ ".ftabel14  "
			+"                                  WHERE data1 = '"+pcode+"'  "
			+"                                  AND data4 <= '"+tgl+"'  "
			+"                                  AND data9 >= '"+tgl+"'  "
			+"                                  ) a  "
			+"                                  where atribut = data3  "
			+"                                  ) a  "
			+"                       GROUP BY pcode, hirarki, atribut  "
			+"                       ORDER BY hirarki ASC) a) a  "
			+"        WHERE ROWNUM = '1') a  "
			+"      INNER JOIN  "
			+"      " + t.getTenantId()+ ".ftabel14 b  "
			+"      ON a.pcode = b.data1  "
			+"    AND a.hirarki = b.data2  "
			+"    AND a.atribut = b.data3  "
			+"    AND a.tglawal = data4 ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		HargaDto result = new HargaDto();
		for (Object[] obj : resultList) {
			result = new HargaDto(Double.parseDouble(Objects.toString(obj[7], "0")),
					Double.parseDouble(Objects.toString(obj[6], "0")),
					Double.parseDouble(Objects.toString(obj[5], "0")));
		}
		
		return result;
	}

	@Override
	public HargaDto getSellPriceProduct(String pcode) {
		// TODO Auto-generated method stub
		String sql = "select sellprice1 as hargakecil, sellprice2 hargatengah, sellprice3 hargabesar "
			+ "From " + t.getTenantId()+ ".fmaster "
			+ "where pcode ='"+pcode+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		HargaDto result = new HargaDto();
		for (Object[] obj : resultList) {
			result = new HargaDto(Double.parseDouble(Objects.toString(obj[2], "0")),
					Double.parseDouble(Objects.toString(obj[1], "0")),
					Double.parseDouble(Objects.toString(obj[0], "0")));
		}
		
		return result;
	}

	@Override
	public String getHargaProductHirarkiPerc(String pcode, String divisi, String tgl, String custNo, String groupOut,
			String salesForce, String channel, String groupHarga) {
		// TODO Auto-generated method stub
		String sql = " SELECT pcode, hirarki, atribut, tglawal ,data9 AS tglakhir,data6 AS perc "
			+"   FROM (   "
			+"       SELECT *  "
			+"       FROM (  "
			+"           SELECT ROWNUM nomer, pcode, hirarki, atribut, tglawal   "
			+"           FROM (    "
			+"               SELECT pcode  "
			+"                   ,hirarki   "
			+"                   ,atribut "
			+"                   ,MAX(tglawal) tglawal "
			+"                   ,type                      "
			+"               FROM (                          "
			+"                   SELECT *                                        "
			+"                   FROM (                                          "
			+"                       SELECT data1 pcode                          "
			+"                           ,data2 hirarki                          "
			+"                           ,data4 tglawal                          "
			+"                           ,data6 perc                             "
			+"                           ,data3                                  "
			+"                           ,type                                   "
			+"                           ,CASE                                   "
			+"                               WHEN data2 = '300'        "
			+"                                   THEN '"+salesForce+"'                      "
			+"                               WHEN data2 = '400'     "
			+"                                   THEN '"+channel+"'                      "
			+"                               WHEN data2 = '500'     "
			+"                                   THEN '"+groupHarga+"'                      "
			+"                               END AS atribut                      "
			+"                       FROM "+t.getTenantId()+".fharga_spesifik_2                      "
			+"                       WHERE (data1 = '"+pcode+"'                      "
			+"                           OR data1 = '"+divisi+"')                         "
			+"                           AND data4 <= '"+tgl+"'              "
			+"                           AND data9 >= '"+tgl+"'              "
			+"                       ) a                                         "
			+"                   WHERE atribut = data3                           "
			+"                   ) a                                             "
			+"               GROUP BY pcode                                      "
			+"                   ,hirarki                                        "
			+"                   ,atribut                                        "
			+"                   ,type                                           "
			+"               ORDER BY hirarki ASC                                "
			+"                   ,type DESC                                      "
			+"               ) a                                                 "
			+"           ) a                                                     "
			+"       WHERE ROWNUM = '1'                                        "
			+"       ) a                                                         "
			+"   INNER JOIN "+t.getTenantId()+".fharga_spesifik_2 b ON a.pcode = b.data1    "
			+"       AND a.hirarki = b.data2                                     "
			+"       AND a.atribut = b.data3                                     "
			+"       AND a.tglawal = data4                                     ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		String result = "";
		for (Object[] obj : resultList) {
			result = Objects.toString(obj[5], "0");
		}
		return result;
	}

	@Override
	public String getDivisiProduct(String pcode) {
		// TODO Auto-generated method stub
		String sql = "Select sbra2, 0 as temp_val FROM "+t.getTenantId()+".fmaster "
			+ "WHERE pcode='"+pcode+"' ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		String result = "";
		for (Object[] obj : resultList) {
			result = Objects.toString(obj[0], "");
		}
		return result;
	}

	@Override
	public Integer getHargaData6Spesifik2(String pcode, String xDivisi) {
		// TODO Auto-generated method stub
		String sql = "select a.data1, a.data2, a.tgl_awal, a.type, data6, data9 "
			+" from (   "
			+" select rownum, a.*   "
			+"  from (  "
			+"  select data1, data2, max(data4) tgl_awal,type from "+t.getTenantId()+".fharga_spesifik_2   "
			+"  where (data1='"+pcode+"' or data1='"+xDivisi+"') and data2='600'        "
			+"  group by data1, data2, type "
			+"  order by type desc)a where rownum=1)a  "
			+" join "+t.getTenantId()+".fharga_spesifik_2 b on a.data1=b.data1 and a.data2=b.data2 and a.tgl_awal = b.data4 and a.type = b.type  ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		Integer result = 0;
		for (Object[] obj : resultList) {
			result = Integer.parseInt(Objects.toString(obj[4], "0"));
		}
		return result;
			
	}

	public List<ProductBrowseDto> getBrowsePcodeDN(String nomorDN) {
		// TODO Auto-generated method stub
		List<ProductBrowseDto> result = new ArrayList<>();		
		
		String sql = "SELECT a.pcode, mt.pcodename, a.price1, a.price2, a.price3,  mt.data09, mt.data10, mt.convunit3, mt.convunit2 " 
					+"	FROM "+t.getTenantId()+".fmutasigd_d1 a INNER JOIN "+t.getTenantId()+".fmaster mt ON a.pcode = mt.pcode " 
					+"	 WHERE docno in "
					+"		(SELECT docno FROM "+t.getTenantId()+".FMUTASIGD_H fh  WHERE delino = '"+nomorDN+"') ";
		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
		
		for (Object[] obj : hasil) {
			ProductBrowseDto data = new ProductBrowseDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), ((BigDecimal) obj[2]).doubleValue(),
					((BigDecimal) obj[3]).doubleValue(), ((BigDecimal) obj[4]).doubleValue(),
					Objects.toString((String) obj[5], ""), Objects.toString((String) obj[6], ""),
					((BigDecimal) obj[7]).doubleValue(), ((BigDecimal) obj[8]).doubleValue());
					result.add(data);
				}
				
				return result;	
	}

	@Override
	public List<ProductBrowseDto> getBrowseInPengajuanRetur(String slsNo, String slsType, String slsTeam) {
		// TODO Auto-generated method stub
		List<ProductBrowseDto> result = new ArrayList<>();
		String sql = "  ";
		if (slsType.equalsIgnoreCase("T")) {
			sql += " SELECT " + 
					"    DISTINCT pcode, " + 
					"    pcodename, " + 
					"    buyprice1, " + 
					"    buyprice2, " + 
					"    buyprice3,  " + 
					"    convunit3, " + 
					"    convunit2 " + 
					"FROM " + 
					"    ( " + 
					"        SELECT " + 
					"            pcode, " + 
					"            pcodename, " + 
					"            buyprice1, " + 
					"            buyprice2, " + 
					"            buyprice3, " + 
					"            convunit3, " + 
					"            convunit2 " + 
					"        FROM " + 
					"            ( " + 
					"                SELECT " + 
					"                    m.pcode, " + 
					"                    m.pcodename, " + 
					"                    m.buyprice1 AS buyprice1, " + 
					"                    m.buyprice2 AS buyprice2, " + 
					"                    m.buyprice3 AS buyprice3, " + 
					"                    m.sbra2 divisi, " + 
					"                    m.prlin | | m.brand | | m.sbra1 AS subbrand, " + 
					"                    m.convunit3, " + 
					"                    m.convunit2 " + 
					"                FROM " + 
					"                    "+t.getTenantId()+".fmasterst v " + 
					"                    LEFT JOIN "+t.getTenantId()+".fmaster m ON m.pcode = v.pcode " + 
					"                WHERE " + 
					"                    m.sbra2 IN ( " + 
					"                        SELECT " + 
					"                            data2 " + 
					"                        FROM " + 
					"                            "+t.getTenantId()+".ftabel12 " + 
					"                        WHERE " + 
					"                            slsno = '"+slsNo+"' " + 
					"                            AND data2 NOT IN ( " + 
					"                                SELECT " + 
					"                                    kdmapping " + 
					"                                FROM " + 
					"                                    "+t.getTenantId()+".ftable279 " + 
					"                                WHERE " + 
					"                                    team = '"+slsTeam+"' " + 
					"                                    AND tipe = '3' " + 
					"                                    AND flag = '2' " + 
					"                            ) " + 
					"                    ) " + 
					"                    AND m.prlin | | m.brand | | m.sbra1 NOT IN ( " + 
					"                        SELECT " + 
					"                            kdmapping " + 
					"                        FROM " + 
					"                            "+t.getTenantId()+".ftable279 " + 
					"                        WHERE " + 
					"                            team = '"+slsTeam+"' " + 
					"                            AND tipe = '2' " + 
					"                            AND flag = '2' " + 
					"                    ) " + 
					"                    AND m.pcode NOT IN ( " + 
					"                        SELECT " + 
					"                            kdmapping " + 
					"                        FROM " + 
					"                            "+t.getTenantId()+".ftable279 " + 
					"                        WHERE " + 
					"                            team = '"+slsTeam+"' " + 
					"                            AND tipe = '1' " + 
					"                            AND flag = '2' " + 
					"                    ) " + 
					"            ) " + 
					"        UNION ALL " + 
					"        SELECT " + 
					"            DISTINCT b.pcode, " + 
					"            b.pcodename, " + 
					"            b.buyprice1, " + 
					"            b.buyprice2, " + 
					"            b.buyprice3, " + 
					"            b.convunit3, " + 
					"            b.convunit2 " + 
					"        FROM " + 
					"            "+t.getTenantId()+".fmasterst a " + 
					"            INNER JOIN ( " + 
					"                SELECT " + 
					"                    a.kdmapping pcode, " + 
					"                    b.pcodename, " + 
					"                    b.buyprice1 AS buyprice1, " + 
					"                    b.buyprice2 AS buyprice2, " + 
					"                    b.buyprice3 AS buyprice3, " + 
					"                    b.convunit3, " + 
					"                    b.convunit2 " + 
					"                FROM " + 
					"                    "+t.getTenantId()+".ftable279 a " + 
					"                    LEFT JOIN "+t.getTenantId()+".fmaster b ON a.kdmapping = b.pcode " + 
					"                WHERE " + 
					"                    a.team = '"+slsTeam+"' " + 
					"                    AND a.tipe = '1' " + 
					"                    AND a.flag = '1' " + 
					"                UNION ALL " + 
					"                SELECT " + 
					"                    b.pcode, " + 
					"                    b.pcodename, " + 
					"                    b.buyprice1 AS buyprice1, " + 
					"                    b.buyprice2 AS buyprice2, " + 
					"                    b.buyprice3 AS buyprice3, " + 
					"                    b.convunit3, " + 
					"                    b.convunit2 " + 
					"                FROM " + 
					"                    "+t.getTenantId()+".ftable279 a " + 
					"                    LEFT JOIN "+t.getTenantId()+".fmaster b ON a.kdmapping = b.prlin | | b.brand | | b.sbra1 " + 
					"                WHERE " + 
					"                    a.team = '"+slsTeam+"' " + 
					"                    AND a.tipe = '2' " + 
					"                    AND a.flag = '1' " + 
					"                UNION ALL " + 
					"                SELECT " + 
					"                    b.pcode, " + 
					"                    b.pcodename, " + 
					"                    b.buyprice1 AS buyprice1, " + 
					"                    b.buyprice2 AS buyprice2, " + 
					"                    b.buyprice3 AS buyprice3, " + 
					"                    b.convunit3, " + 
					"                    b.convunit2 " + 
					"                FROM " + 
					"                    "+t.getTenantId()+".ftable279 a " + 
					"                    LEFT JOIN "+t.getTenantId()+".fmaster b ON a.kdmapping = b.sbra2 " + 
					"                WHERE " + 
					"                    a.team = '"+slsTeam+"' " + 
					"                    AND a.tipe = '3' " + 
					"                    AND a.flag = '1' " + 
					"            ) b ON a.pcode = b.pcode " + 
					"    ) " + 
					"ORDER BY " + 
					"    pcode ";			
		} else {
			sql += " SELECT " + 
					"    DISTINCT pcode, " + 
					"    pcodename, " + 
					"    buyprice1 as buyprice1, " + 
					"    buyprice2 as buyprice2, " + 
					"    buyprice3 as buyprice3, " + 
					"    convunit3 as convunit3,  " + 
					"    convunit2 as convunit2 " + 
					"FROM " + 
					"    ( " + 
					"        SELECT " + 
					"            pcode, " + 
					"            pcodename, " + 
					"            buyprice1, " + 
					"            buyprice2, " + 
					"            buyprice3, " + 
					"            convunit3, " + 
					"            convunit2 " + 
					"        FROM " + 
					"            ( " + 
					"                SELECT " + 
					"                    m.pcode, " + 
					"                    m.pcodename, " + 
					"                    m.buyprice1 AS buyprice1, " + 
					"                    m.buyprice2 AS buyprice2, " + 
					"                    m.buyprice3 AS buyprice3, " + 
					"                    m.sbra2 divisi, " + 
					"                    m.prlin | | m.brand | | m.sbra1 AS subbrand, " + 
					"                    m.convunit3,  " + 
					"                    m.convunit2 " + 
					"                FROM " + 
					"                    "+t.getTenantId()+".fvan v " + 
					"                    LEFT JOIN "+t.getTenantId()+".fmaster m ON m.pcode = v.pcode " + 
					"                WHERE " + 
					"                    v.slsno = '"+slsNo+"' " + 
					"                    AND m.sbra2 IN ( " + 
					"                        SELECT " + 
					"                            data2 " + 
					"                        FROM " + 
					"                            "+t.getTenantId()+".ftabel12 " + 
					"                        WHERE " + 
					"                            slsno = '"+slsNo+"' " + 
					"                            AND data2 NOT IN ( " + 
					"                                SELECT " + 
					"                                    kdmapping " + 
					"                                FROM " + 
					"                                    "+t.getTenantId()+".ftable279 " + 
					"                                WHERE " + 
					"                                    team = '"+slsTeam+"' " + 
					"                                    AND tipe = '3' " + 
					"                                    AND flag = '2' " + 
					"                            ) " + 
					"                    ) " + 
					"                    AND m.prlin | | m.brand | | m.sbra1 NOT IN ( " + 
					"                        SELECT " + 
					"                            kdmapping " + 
					"                        FROM " + 
					"                            "+t.getTenantId()+".ftable279 " + 
					"                        WHERE " + 
					"                            team = '"+slsTeam+"' " + 
					"                            AND tipe = '2' " + 
					"                            AND flag = '2' " + 
					"                    ) " + 
					"                    AND m.pcode NOT IN ( " + 
					"                        SELECT " + 
					"                            kdmapping " + 
					"                        FROM " + 
					"                            "+t.getTenantId()+".ftable279 " + 
					"                        WHERE " + 
					"                            team = '"+slsTeam+"' " + 
					"                            AND tipe = '1' " + 
					"                            AND flag = '2' " + 
					"                    ) " + 
					"            ) " + 
					"        UNION ALL " + 
					"        SELECT " + 
					"            DISTINCT b.pcode, " + 
					"            b.pcodename, " + 
					"            b.buyprice1, " + 
					"            b.buyprice2, " + 
					"            b.buyprice3,  " + 
					"            convunit3, " + 
					"            convunit2 " + 
					"        FROM " + 
					"            "+t.getTenantId()+".fvan a " + 
					"            INNER JOIN ( " + 
					"                SELECT " + 
					"                    a.kdmapping pcode, " + 
					"                    b.pcodename, " + 
					"                    b.buyprice1 AS buyprice1, " + 
					"                    b.buyprice2 AS buyprice2, " + 
					"                    b.buyprice3 AS buyprice3,  " + 
					"                    b.convunit3,  " + 
					"                    b.convunit2 " + 
					"                FROM " + 
					"                    "+t.getTenantId()+".ftable279 a " + 
					"                    LEFT JOIN "+t.getTenantId()+".fmaster b ON a.kdmapping = b.pcode " + 
					"                WHERE " + 
					"                    a.team = '"+slsTeam+"' " + 
					"                    AND a.tipe = '1' " + 
					"                    AND a.flag = '1' " + 
					"                UNION ALL " + 
					"                SELECT " + 
					"                    b.pcode, " + 
					"                    b.pcodename, " + 
					"                    b.buyprice1 AS buyprice1, " + 
					"                    b.buyprice2 AS buyprice2, " + 
					"                    b.buyprice3 AS buyprice3,  " + 
					"                    b.convunit3, " + 
					"                    b.convunit2 " + 
					"                FROM " + 
					"                    "+t.getTenantId()+".ftable279 a " + 
					"                    LEFT JOIN "+t.getTenantId()+".fmaster b ON a.kdmapping = b.prlin | | b.brand | | b.sbra1 " + 
					"                WHERE " + 
					"                    a.team = '"+slsTeam+"' " + 
					"                    AND a.tipe = '2' " + 
					"                    AND a.flag = '1' " + 
					"                UNION ALL " + 
					"                SELECT " + 
					"                    b.pcode, " + 
					"                    b.pcodename, " + 
					"                    b.buyprice1 AS buyprice1, " + 
					"                    b.buyprice2 AS buyprice2, " + 
					"                    b.buyprice3 AS buyprice3,  " + 
					"                    b.convunit3, " + 
					"                    b.convunit2 " + 
					"                FROM " + 
					"                    "+t.getTenantId()+".ftable279 a " + 
					"                    LEFT JOIN "+t.getTenantId()+".fmaster b ON a.kdmapping = b.sbra2 " + 
					"                WHERE " + 
					"                    a.team = '"+slsTeam+"' " + 
					"                    AND a.tipe = '3' " + 
					"                    AND a.flag = '1' " + 
					"            ) b ON a.pcode = b.pcode " + 
					"        WHERE " + 
					"            a.slsno = '"+slsNo+"' " + 
					"    ) " + 
					"ORDER BY " + 
					"    pcode ";
		}
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProductBrowseDto data = new ProductBrowseDto(Objects.toString((String)obj[0], ""), Objects.toString((String)obj[1], ""),
			((BigDecimal)obj[2]).doubleValue(), ((BigDecimal)obj[3]).doubleValue(), ((BigDecimal)obj[4]).doubleValue(),
			"", "", ((BigDecimal)obj[5]).doubleValue(), ((BigDecimal)obj[6]).doubleValue());
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<ProductBrowseInPengajuanSODto> getBrowseInPengajuanSO(String slsNo, String slsType) {
		// TODO Auto-generated method stub
		List<ProductBrowseInPengajuanSODto> result = new ArrayList<>();
		String sql = "  ";
		if (slsType.equalsIgnoreCase("T")) {
			sql += " SELECT "
					+ "    m.PCODE, "
					+ "    m.PCODENAME, "
					+ "    m.BUYPRICE1 AS buyprice1, "
					+ "    m.BUYPRICE2 AS buyprice2, "
					+ "    m.BUYPRICE3 AS buyprice3,  "
					+ "    m.DATA03 AS Data03, "
					+ "    m.DATA04 AS Data04, "
					+ "    m.DATA05 AS Data05, "
					+ "    m.DATA06 AS Data06, "
					+ "    m.CONVUNIT3 AS conv3, "
					+ "    m.CONVUNIT2 AS conv2 "
					+ "FROM "
					+ "    "+t.getTenantId()+".fmasterst v "
					+ "    LEFT JOIN "+t.getTenantId()+".fmaster m ON m.pcode = v.pcode "
					+ "WHERE "
					+ "    m.sbra2 IN ( "
					+ "        SELECT "
					+ "            data2 "
					+ "        FROM "
					+ "            "+t.getTenantId()+".ftabel12 "
					+ "        WHERE "
					+ "            slsno = '"+slsNo+"' "
					+ "    ) ORDER BY m.sbra2 ASC, m.prlin || m.brand || m.sbra1 ASC, m.pcode ASC ";			
		} else {
			sql += " SELECT "
					+ "    m.PCODE, "
					+ "    m.PCODENAME, "
					+ "    m.BUYPRICE1 AS buyprice1, "
					+ "    m.BUYPRICE2 AS buyprice2, "
					+ "    m.BUYPRICE3 AS buyprice3,  "
					+ "    m.DATA03 AS Data03, "
					+ "    m.DATA04 AS Data04, "
					+ "    m.DATA05 AS Data05, "
					+ "    m.DATA06 AS Data06, "
					+ "    m.CONVUNIT3 AS conv3, "
					+ "    m.CONVUNIT2 AS conv2 "
					+ "FROM "
					+ "    "+t.getTenantId()+".fvan v "
					+ "    LEFT JOIN "+t.getTenantId()+".fmaster m ON m.pcode = v.pcode "
					+ "WHERE "
					+ "    v.slsno = '"+slsNo+"' "
					+ "    AND m.sbra2 IN ( "
					+ "        SELECT "
					+ "            data2 "
					+ "        FROM "
					+ "            "+t.getTenantId()+".ftabel12 "
					+ "        WHERE "
					+ "            slsno = '"+slsNo+"' "
					+ "    ) ORDER BY m.sbra2 ASC, m.prlin || m.brand || m.sbra1 ASC, m.pcode ASC   ";
		}
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ProductBrowseInPengajuanSODto data = new ProductBrowseInPengajuanSODto(
					Objects.toString((String)obj[0], ""), Objects.toString((String)obj[1], ""), 
					new BigDecimal(Objects.toString(obj[2], "0")), new BigDecimal(Objects.toString(obj[3], "0")),
					new BigDecimal(Objects.toString(obj[4], "0")), new BigDecimal(Objects.toString(obj[5], "0")), 
					new BigDecimal(Objects.toString(obj[6], "0")), new BigDecimal(Objects.toString(obj[7], "0")), 
					new BigDecimal(Objects.toString(obj[8], "0")), new BigDecimal(Objects.toString(obj[9], "0")), 
					new BigDecimal(Objects.toString(obj[10], "0")));
			result.add(data);
		}
		
		return result;
	}

}
