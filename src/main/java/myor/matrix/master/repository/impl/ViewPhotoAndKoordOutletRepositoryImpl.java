package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ViewPhotoAndKoordOutletDto;
import myor.matrix.master.repository.ViewPhotoAndKoordOutletRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ViewPhotoAndKoordOutletRepositoryImpl implements ViewPhotoAndKoordOutletRepository {
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ViewPhotoAndKoordOutletDto> getViewPhotoAndKoordOutlet(String custno) {
		List<ViewPhotoAndKoordOutletDto> result = new ArrayList<>();
		String sql ="SELECT a.custno, a.upddate, a.user_approved, "
				+ "       CASE "
				+ "          WHEN a.flag_approved IS NULL OR a.flag_approved = '' "
				+ "             THEN 'Open' "
				+ "          ELSE 'Validated' "
				+ "       END flag_approved, "
				+ "       b.custname, b.custadd1, "
				+ "       TO_CHAR (b.registrationdate, 'DD Mon YYYY') dater, "
				+ "       TO_CHAR (b.registrationdate, 'YYYYMMDD') registrationdate, b.address, "
				+ "       b.latitude, b.longitude, b.slsno, b.slsname,"
				+ "       (select memostring from "+t.getTenantId()+".ftable13 where xkey='XPICTUREOUTLET')||'\\'||TO_CHAR (b.registrationdate, 'YYYYMMDD')path, b.picturename picturename "
				+ "  FROM "+t.getTenantId()+".ftable275 a "
				+ "       LEFT JOIN "
				+ "       (SELECT DISTINCT a.custno, b.custname, b.custadd1, a.registrationdate, "
				+ "                        a.address, a.latitude, a.longitude, a.slsno, "
				+ "                        c.slsname, a.picturename "
				+ "                   FROM "+t.getTenantId()+".ftable274 a INNER JOIN "+t.getTenantId()+".fcustmst b "
				+ "                        ON a.custno = b.custno "
				+ "                        INNER JOIN "+t.getTenantId()+".fsls c ON a.slsno = c.slsno "
				+ "                        ) b ON a.custno = b.custno "
				+ " WHERE a.custno IN ('"+custno+"') "
				+ "   AND b.registrationdate IN (SELECT MAX (registrationdate) tgl "
				+ "                                FROM "+t.getTenantId()+".ftable274 "
				+ "                               WHERE custno = '"+custno+"')";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for(Object[]o: obj) {
			ViewPhotoAndKoordOutletDto data = new ViewPhotoAndKoordOutletDto(Objects.toString(o[0], ""), Objects.toString(o[1], ""), Objects.toString(o[2], ""), 
					Objects.toString(o[3], ""), Objects.toString(o[4], ""), Objects.toString(o[5], ""), Objects.toString(o[6], ""), Objects.toString(o[7], ""),
					Objects.toString(o[8], ""), Objects.toString(o[9], ""), Objects.toString(o[10], ""), Objects.toString(o[11], ""), Objects.toString(o[12], ""), Objects.toString(o[14], ""), Objects.toString(o[13], ""));
			result.add(data);
		}
		return result;
	}

	@Transactional
	@Override
	public void insertCustnoToFtable275() {
		String sql = "insert into "+t.getTenantId()+".ftable275 (custno)  select custno FROM "+t.getTenantId()+".fcustmst where custno not in (select Custno from "
				+ " "+t.getTenantId()+".ftable275)";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public boolean cekPathPhoto() {
		String sql = "select * from "+t.getTenantId()+".ftable13 where xkey='XPICTUREOUTLET'";
		Query query = entityManager.createNativeQuery(sql);
		if(query.getResultList().size()>0) {
			return true;
		}else {
			return false;
		}
	}

}
