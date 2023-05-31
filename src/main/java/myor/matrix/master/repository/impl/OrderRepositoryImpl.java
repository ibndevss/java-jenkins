package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.InvoiceBrowseDto;
import myor.matrix.master.entity.OrderJualBrowseDto;
import myor.matrix.master.entity.RealOrderBrowseDto;
import myor.matrix.master.entity.RecapBrowseDto;
import myor.matrix.master.repository.OrderRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<InvoiceBrowseDto> getBrowseInvoice(String transType) {
		// TODO Auto-generated method stub
		List<InvoiceBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT DISTINCT invno, TO_CHAR (invdate, 'DD MON YYYY') invdate, slsno, custno, transtype "
			        +"    		  FROM "+t.getTenantId()+".fjual_h "
			        +"   		 WHERE invno IS NOT NULL ";
		
		if (transType.equalsIgnoreCase("F") || transType.equalsIgnoreCase("R")) {
			sql += " AND transtype = '"+transType+"' ";
		} 
		
		sql += " ORDER BY invno ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			InvoiceBrowseDto data = new InvoiceBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<RealOrderBrowseDto> getBrowseRealOrder(String slsNo, String pilihan) {
		// TODO Auto-generated method stub
		
		List<RealOrderBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT h.orderno, h.slsno, h.custno, c.custname, "
					+"        TO_CHAR (h.orderdate, 'DD MON YYYY') orderdate, h.orderno2 "
					+"   FROM "+t.getTenantId()+".forder_h h INNER JOIN "+t.getTenantId()+".fcustmst c ON h.custno = c.custno "
					+"  WHERE h.orderno IS NOT NULL ";
		
		if(slsNo.isEmpty() == false) {
			sql+="	AND  h.slsno = '"+slsNo+"' ";
		}
		
		if(pilihan.equals("1")) {
			sql+=" AND orderno2 is not null ";
		}else if(pilihan.equals("2")) {
			sql+=" AND orderno2 is null ";
		}
	    
		sql+=" ORDER BY h.orderno ";
		
	    Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();
	
		for (Object[] obj : hasil) {
			RealOrderBrowseDto data = new RealOrderBrowseDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), Objects.toString((String) obj[2], ""),
					Objects.toString((String) obj[3], ""), Objects.toString((String) obj[4], ""),
					Objects.toString((String) obj[5], ""));
			result.add(data);
		}
		
		return result;
	}
	
	@Override
	public List<OrderJualBrowseDto> getBrowseOrderJual(String transType) {
		// TODO Auto-generated method stub
		List<OrderJualBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT orderno, TO_CHAR (orderdate, 'DD MON YYYY') orderdate, slsno, custno, transtype "
					+"    FROM "+t.getTenantId()+".fjual_h "
					+"   WHERE slsno NOT IN ('999999') ";
		
		if (transType.equalsIgnoreCase("F") || transType.equalsIgnoreCase("R")) {
			sql += " AND transtype = '"+transType+"' ";
		}
					
		sql +=" ORDER BY orderno ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			OrderJualBrowseDto data = new OrderJualBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4]);
			result.add(data);
		}		
		return result;
	}

	@Override
	public List<RecapBrowseDto> getBrowseRecap() {
		// TODO Auto-generated method stub
		List<RecapBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT DISTINCT b.recapno, TO_CHAR (b.recapdate, 'DD MON YYYY') recapdate, b.driver, b.helper "
					+"            FROM "+t.getTenantId()+".ftable81 a INNER JOIN "+t.getTenantId()+".ftable100 b ON a.recapno = b.recapno "
					+"        ORDER BY b.recapno ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			RecapBrowseDto data = new RecapBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3]);
			result.add(data);
		}		
		return result;
	}

	@Override
	public List<RealOrderBrowseDto> getBrowseOrderRO() {
		// TODO Auto-generated method stub
		List<RealOrderBrowseDto> result = new ArrayList<>();		
		
		String sql = " SELECT h.orderno, h.slsno, h.custno, c.custname, "
					+"        TO_CHAR (h.orderdate, 'DD MON YYYY') orderdate, h.orderno2 "
					+"   FROM "+t.getTenantId()+".forder_h h INNER JOIN "+t.getTenantId()+".fcustmst c ON h.custno = c.custno "
					+"  WHERE h.orderno IS NOT NULL ";
		
        Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			RealOrderBrowseDto data = new RealOrderBrowseDto(Objects.toString((String) obj[0], ""),
					Objects.toString((String) obj[1], ""), Objects.toString((String) obj[2], ""),
					Objects.toString((String) obj[3], ""), Objects.toString((String) obj[4], ""),
					Objects.toString((String) obj[5], ""));
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<InvoiceBrowseDto> getBrowseCancelInvoice() {
		// TODO Auto-generated method stub
	List<InvoiceBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT invno invoice, transtype TYPE, TO_CHAR (invdate,'DD MON YYYY') DATE_, custno    FROM "+t.getTenantId()+".finv  WHERE invno IS NOT "
				+ "NULL AND cancelflag = '*' ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			InvoiceBrowseDto data = new InvoiceBrowseDto((String) obj[0], (String) obj[2], null, (String) obj[3], (String) obj[1]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<RecapBrowseDto> getBrowseSummaryOrder(String tglGudang, String xHariLibur, int xMinPengiriman,
			String slsnoFrom, String slsnoTo, String invDateFrom, String invDateTo, String dueDateFrom,
			String dueDateTo, String invnoFrom, String invnoTo) {
		// TODO Auto-generated method stub
		List<RecapBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT DISTINCT a.recapno, TO_CHAR(b.recapdate,'DD MON YYYY') as tglRekap "
				    +"       FROM "+t.getTenantId()+".fjual_h a LEFT JOIN "+t.getTenantId()+".ftable100 b "
				    +"            ON a.recapno = b.recapno "
				    +"      WHERE a.recapno IS NOT NULL "
				    +"        AND a.invamountcharge > a.amountpaidcharge "
				    +"        AND a.transtype = 'F' "
				    +"        AND b.approve = '1' "
				    +"        AND b.recapdate IN ( "
				    +"               SELECT cdate "
				    +"                 FROM (SELECT   * "
				    +"                           FROM "+t.getTenantId()+".fcycle3 "
				    +"                          WHERE cdate <= '"+tglGudang+"' ";
			if (!xHariLibur.equalsIgnoreCase("Y")) {
				sql +="	AND workflag = 'Y' ";
			}
				    
				sql +="                       ORDER BY cdate DESC) "
				    +"                WHERE ROWNUM <= "+xMinPengiriman+" + 1) ";
			if (!slsnoFrom.equalsIgnoreCase("none")) {
				sql	+="        AND a.slsno >= '"+slsnoFrom+"' ";
			}
			if (!slsnoTo.equalsIgnoreCase("none")) {
				sql	+="        AND a.slsno <= '"+slsnoTo+"' ";
			}
			if (!invDateFrom.equalsIgnoreCase("none")) {
				sql	+="        AND a.invdate >= '"+invDateFrom+"' ";
			}
			if (!invDateTo.equalsIgnoreCase("none")) {
				sql	+="        AND a.invdate <= '"+invDateTo+"' ";
			}
			if (!dueDateFrom.equalsIgnoreCase("none")) {
				sql	+="        AND a.duedate >= '"+dueDateFrom+"' ";
			}
			if (!dueDateTo.equalsIgnoreCase("none")) {
				sql	+="        AND a.duedate <= '"+dueDateTo+"' ";
			}	    
			if (!invnoFrom.equalsIgnoreCase("none")) {
				sql	+="        AND a.invno >= '"+invnoFrom+"' ";
			}
			if (!invnoTo.equalsIgnoreCase("none")) {
				sql	+="        AND a.invno <= '"+invnoTo+"' ";
			}
				    
			 	sql +="   ORDER BY a.recapno ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			RecapBrowseDto data = new RecapBrowseDto((String) obj[0], (String) obj[1], null, null);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<InvoiceBrowseDto> getBrowseInvoiceByTglBayar(String tglBayar) {
		// TODO Auto-generated method stub
		List<InvoiceBrowseDto> result = new ArrayList<>();
		
		String sql = "	SELECT invno, to_char(invdate,'DD MON YYYY') as tglInv, slsno, custno, transtype "
					+"	  FROM "+t.getTenantId()+".fjual_h "
					+"	 WHERE invamountcharge > amountpaidcharge "
					+"	   AND transtype = 'F' "
					+"	   AND invno IS NOT NULL "
					+"	   AND invno NOT IN (SELECT writeoffno "
					+"	                       FROM "+t.getTenantId()+".fwriteoff_d "
					+"	                      WHERE TYPE = 'F') "
					+"	   AND invno NOT IN (SELECT DISTINCT d.invno "
					+"	                                FROM "+t.getTenantId()+".fartagih_h h LEFT JOIN "+t.getTenantId()+".fartagih_d d "
					+"	                                     ON h.docno = d.docno "
					+"	                                     LEFT JOIN "+t.getTenantId()+".ftable34 c "
					+"	                                     ON d.invno = c.invno "
					+"	                                   AND h.docdate = c.uploaddate "
					+"	                               WHERE h.docdate >= '"+tglBayar+"' "
					+"	                                 AND c.payno IS NULL) "
					+"	   AND NVL (status, 0) <> '4' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			InvoiceBrowseDto data = new InvoiceBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4]);
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<InvoiceBrowseDto> getBrowseInvoice2(String transType, String invnoFrom, String invnoTo,
			String slsnoFrom, String slsnoTo, String custnoFrom, String custnoTo) {
		// TODO Auto-generated method stub
		List<InvoiceBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT invno, TO_CHAR (invdate, 'DD MON YYYY') invdate, slsno, custno, transtype FROM "+t.getTenantId()+".fjual_h "
					+"  WHERE NVL (status, 0) <> '4' " 
					+"    AND invamountcharge > NVL (amountpaidcharge, 0) ";
		
		if (transType.equalsIgnoreCase("F") || transType.equalsIgnoreCase("R")) {
			sql += " AND transtype = '"+transType+"' ";
		}
		
		if (!invnoFrom.equalsIgnoreCase("")) {
			sql += " AND invno >= '"+invnoFrom+"' ";
		}
		
		if (!invnoTo.equalsIgnoreCase("")) {
			sql += " AND invno <= '"+invnoTo+"' ";
		}
		
		if (!slsnoFrom.equalsIgnoreCase("")) {
			sql += " AND slsno >= '"+slsnoFrom+"' ";
		}
		
		if (!slsnoTo.equalsIgnoreCase("")) {
			sql += " AND slsno <= '"+slsnoTo+"' ";
		}
		
		if (!custnoFrom.equalsIgnoreCase("")) {
			sql += " AND custno >= '"+custnoFrom+"' ";
		}
		
		if (!custnoTo.equalsIgnoreCase("")) {
			sql += " AND custno <= '"+custnoTo+"' ";
		}
		
		sql += " ORDER BY invno ASC ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			InvoiceBrowseDto data = new InvoiceBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4]);
			result.add(data);
		}
		return result;
	}

	@Override
	public List<RecapBrowseDto> getBrowseSummaryOrder2(String tglGudang) {
		// TODO Auto-generated method stub
		List<RecapBrowseDto> result = new ArrayList<>();
		String sql = "SELECT DISTINCT a.recapno, TO_CHAR(b.recapdate,'DD MON YYYY') as recapdate "
	               +"     FROM "+t.getTenantId()+".fjual_h a LEFT JOIN "+t.getTenantId()+".ftable100 b ON a.recapno = b.recapno "
	               +"    WHERE a.recapno IS NOT NULL "
	               +"      AND a.invamountcharge > a.amountpaidcharge "
	               +"      AND a.transtype = 'F' "
	               +"      AND b.recapdate  >= (SELECT MEMODATE-30 FROM "+t.getTenantId()+".FMEMO WHERE MEMONAMA = 'CADATE' ) ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			RecapBrowseDto data = new RecapBrowseDto((String) obj[0], (String) obj[1], null, null);
			result.add(data);
		}
		return result;
	}	
}
