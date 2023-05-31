package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.ViewValidasiOutletDto;
import myor.matrix.master.repository.ViewValidasiOutletRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class ViewValidasiOutletRepositoryImpl implements ViewValidasiOutletRepository {
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ViewValidasiOutletDto> viewValidasiOutlet(String dateSubmitFrom, String dateSubmitTo) {
		List<ViewValidasiOutletDto> result = new ArrayList<>();
		String sql = "SELECT   outlet, status, longitude, latitude, provinsi, kabupaten, kecamatan, "
				+ "         kelurahan, tgl_submit, useradj, subdist "
				+ "    FROM (SELECT a.custno || ' - ' || f.custname AS outlet, "
				+ "                 CASE "
				+ "                    WHEN NVL (b.flag_approved, 0) = 0 "
				+ "                       THEN 'OPEN' "
				+ "                    WHEN b.flag_approved = 1 "
				+ "                       THEN 'APPROVE' "
				+ "                    WHEN b.flag_approved = 2 "
				+ "                       THEN 'REJECT' "
				+ "                    WHEN b.flag_approved = 3 "
				+ "                       THEN 'ADJUST' "
				+ "                    ELSE b.flag_approved "
				+ "                 END AS status, "
				+ "                 a.longitude, a.latitude, p.prop AS provinsi, "
				+ "                 kb.kab AS kabupaten, kc.kec AS kecamatan, "
				+ "                 kl.kel AS kelurahan, "
				+ "                 TO_CHAR (b.upddate, 'DD/MM/YYYY') AS tgl_submit, "
				+ "                 NVL (b.user_approved, '-') AS useradj, "
				+ "                 (SELECT memonama || ' - ' || memostring "
				+ "                    FROM "+t.getTenantId()+".ftable13 "
				+ "                   WHERE xkey = 'XBRANCH') AS subdist "
				+ "            FROM (SELECT a.* "
				+ "                    FROM "+t.getTenantId()+".ftable274 a "
				+ "                         INNER JOIN "
				+ "                         (SELECT   custno, MAX (registrationdate) AS maxdate "
				+ "                              FROM "+t.getTenantId()+".ftable274 "
				+ "                          GROUP BY custno) b "
				+ "                         ON (    a.custno = b.custno "
				+ "                             AND a.registrationdate = b.maxdate "
				+ "                            ) "
				+ "                         ) a "
				+ "                 LEFT JOIN "
				+ "                 "+t.getTenantId()+".ftable275 b ON a.custno = b.custno "
				+ "                 LEFT JOIN "+t.getTenantId()+".fcustmst f ON a.custno = f.custno "
				+ "                 LEFT JOIN "
				+ "                 (SELECT a.custno, a.h11 AS kdprop, b.ket AS prop "
				+ "                    FROM "+t.getTenantId()+".fcustud a LEFT JOIN "+t.getTenantId()+".fcshir11 b "
				+ "                         ON a.h11 = b.t11 "
				+ "                         ) p ON p.custno = a.custno "
				+ "                 LEFT JOIN "
				+ "                 (SELECT a.custno, a.h12 AS kdkab, c.ket AS kab "
				+ "                    FROM "+t.getTenantId()+".fcustud a LEFT JOIN "+t.getTenantId()+".fcshir11 b "
				+ "                         ON a.h11 = b.t11 "
				+ "                         LEFT JOIN "+t.getTenantId()+".fcshir12 c "
				+ "                         ON a.h12 = c.t12 AND a.h11 = c.t11 "
				+ "                         ) kb ON kb.custno = a.custno "
				+ "                 LEFT JOIN "
				+ "                 (SELECT a.custno, a.h13 AS kdkec, d.ket AS kec "
				+ "                    FROM "+t.getTenantId()+".fcustud a LEFT JOIN "+t.getTenantId()+".fcshir11 b "
				+ "                         ON a.h11 = b.t11 "
				+ "                         LEFT JOIN "+t.getTenantId()+".fcshir12 c "
				+ "                         ON a.h12 = c.t12 AND a.h11 = c.t11 "
				+ "                         LEFT JOIN "+t.getTenantId()+".fcshir13 d "
				+ "                         ON a.h11 = d.t11 AND a.h12 = d.t12 AND a.h13 = d.t13 "
				+ "                         ) kc ON kc.custno = a.custno "
				+ "                 LEFT JOIN "
				+ "                 (SELECT a.custno, a.h13 AS kdkel, e.ket AS kel "
				+ "                    FROM "+t.getTenantId()+".fcustud a LEFT JOIN "+t.getTenantId()+".fcshir11 b "
				+ "                         ON a.h11 = b.t11 "
				+ "                         LEFT JOIN "+t.getTenantId()+".fcshir12 c "
				+ "                         ON a.h12 = c.t12 AND a.h11 = c.t11 "
				+ "                         LEFT JOIN "+t.getTenantId()+".fcshir13 d "
				+ "                         ON a.h11 = d.t11 AND a.h12 = d.t12 AND a.h13 = d.t13 "
				+ "                         LEFT JOIN "+t.getTenantId()+".fcshir14 e "
				+ "                         ON a.h11 = e.t11 "
				+ "                       AND a.h12 = e.t12 "
				+ "                       AND a.h13 = e.t13 "
				+ "                       AND a.h14 = e.t14 "
				+ "                         ) kl ON kl.custno = a.custno "
				+ "            WHERE b.upddate >= '"+dateSubmitFrom+"' AND b.upddate <= '"+dateSubmitTo+"' "
				+ "           ) b "
				+ "ORDER BY b.tgl_submit";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> obj = query.getResultList();
		for(Object[]o:obj) {
			ViewValidasiOutletDto data = new ViewValidasiOutletDto((String)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5], 
					(String)o[6], (String)o[7], (String)o[8], (String)o[9], (String)o[10]);
			result.add(data);
		}
		return result;
	}

}
