package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.DocumentBrowseDto;
import myor.matrix.master.entity.ViewNooDto;
import myor.matrix.master.repository.ViewNooRepository;
import myor.matrix.master.tenant.TenantContext;
@Repository
public class ViewNooRepositoryImpl implements ViewNooRepository {
	
	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public String getMaxDocNo(String status) {
		// TODO Auto-generated method stub
		String sql = " SELECT max(DOCNO) maxDocno FROM "+t.getTenantId()+".ftable141 ";
				if (!status.equalsIgnoreCase("all")) {
					sql += " WHERE STATUS = '"+status+"' ";
				}
				sql += " ORDER BY docno ASC ";
		Query query = entityManager.createNativeQuery(sql);
		String result = (String) query.getSingleResult();
		return result;
	}

	@Override
	public ViewNooDto getDataViewNoo(String docNo) {
		// TODO Auto-generated method stub
		ViewNooDto result = new ViewNooDto();
		
		String sql = " SELECT a.docno, TO_CHAR (a.docdate, 'DD MON YYYY') docdate, "
				+ "	a.slsno, f.slsname, a.custname, a.custadd1 || ' ' || a.custadd2 custadd, "
				+ "	a.shipadd1 || ' ' || a.shipadd2 || ' ' || a.shipadd3 shipadd, "
				+ "	a.ccontact, a.cphone1, a.h11, b.ket ket11, a.h12, c.ket ket12, "
				+ "	a.h13, d.ket ket13, a.h14, e.ket ket14, a.nokartu1, a.nobarcode1, a.taxname, "
				+ "	a.taxadd1, a.taxadd2, a.npwp, no_ktp, tax_city, "
				+ "	tax_building, seq_no, postal_code, title_person, "
				+ "	CASE "
				+ "		WHEN a.status = '0' THEN 'UNPROCESSED' "
				+ "		WHEN a.status = '1' THEN 'APPROVED' "
				+ "		WHEN a.status = '2' THEN 'CANCELED' "
				+ "		ELSE '' "
				+ "	END status, TO_CHAR (a.approvedate, 'DD MON YYYY') approvedate, a.custno "
				+ "FROM "+t.getTenantId()+".ftable141 a "
				+ "LEFT JOIN "+t.getTenantId()+".fcshir11 b ON a.h11 = b.t11 "
				+ "LEFT JOIN "+t.getTenantId()+".fcshir12 c ON a.h11 = c.t11 AND a.h12 = c.t12 "
				+ "LEFT JOIN "+t.getTenantId()+".fcshir13 d ON a.h11 = d.t11 AND a.h12 = d.t12 AND a.h13 = d.t13 "
				+ "LEFT JOIN "+t.getTenantId()+".fcshir14 e ON a.h11 = e.t11 AND a.h12 = e.t12 AND a.h13 = e.t13 AND a.h14 = e.t14 "
				+ "LEFT JOIN "+t.getTenantId()+".fsls f ON a.slsno = f.slsno "
				+ "WHERE a.docno = '"+docNo+"' ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			ViewNooDto data = new ViewNooDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3],
					(String) obj[4], (String) obj[5], (String) obj[6], (String) obj[7], (String) obj[8], (String) obj[9], (String) obj[10], (String) obj[11],
					(String) obj[12], (String) obj[13], (String) obj[14], (String) obj[15], (String) obj[16], (String) obj[17], (String) obj[18], (String) obj[19],
					(String) obj[20], (String) obj[21], (String) obj[22], (String) obj[23], (String) obj[24], (String) obj[25], (String) obj[26], (String) obj[27],
					(String) obj[28], (String) obj[29], (String) obj[30], (String) obj[31]);
			result = data;
		}
		return result;
	}

	@Override
	public List<DocumentBrowseDto> getListDocument(String status) {
		// TODO Auto-generated method stub
		List<DocumentBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT a.docno DOCNO, TO_CHAR(a.docdate, 'DD MON YYYY') DOCDATE,  a.slsno SLSNO , b.slsname SLSNAME "
				+ "FROM "+t.getTenantId()+".ftable141 a  "
				+ "LEFT JOIN "+t.getTenantId()+".fsls b ON a.slsno=b.slsno ";
		        if (!status.equalsIgnoreCase("all")) {
					sql += " WHERE STATUS = '"+status+"' ";
				}
		        sql += "order by docno ";
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			DocumentBrowseDto data = new DocumentBrowseDto((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3]);
			result.add(data);
		}
		return result;
	}

}
