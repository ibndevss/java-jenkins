package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.GudangRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class GudangRepositoryImpl implements GudangRepository {

	@Autowired
	private TenantContext t;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SelectItem<String>> getListGudang() {
		// TODO Auto-generated method stub
		String sql = "Select KG AS value, KG || ' - ' || KGNAME AS label from "+t.getTenantId()+".FLOKGD order by KG ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SelectItem<String>> getListAllGudang() {
		// TODO Auto-generated method stub
		String sql = "	select '00' as kg, TO_NCHAR('00'||'-'||'Gudang Utama') as gudang from dual "
					+"	UNION ALL "
					+"	select 'R1' as kg, TO_NCHAR('R1'||'-'||'Gudang BS') as gudang from dual "
					+"	UNION ALL "
					+"	select kg, kg||'-'||kgname as gudang from "+t.getTenantId()+".flokgd ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();

		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SelectItem<String>> getListMutasiGudang(String kdGudang, String flagWhTransisi) {
		// TODO Auto-generated method stub 
		List<SelectItem<String>> result = new ArrayList<>();
		result.add(new SelectItem<String>("Choose", null));
		
		String sql = "	SELECT a.kdGudang, a.kdGudang||' - '||a.nmGudang AS label "
					+"	FROM ( "
					+"			SELECT '00' AS kdGudang, TO_NCHAR('Gudang Utama') AS nmGudang FROM dual "
					+"			UNION ALL "
					+"			SELECT 'R1' AS kdGudang, TO_NCHAR('Gudang BS') AS nmGudang FROM dual "
					+"			UNION ALL ";
			if (flagWhTransisi.equalsIgnoreCase("Y")) {
				sql +="			SELECT 'R2' AS kdGudang, TO_NCHAR('Gudang Transisi') AS nmGudang FROM dual ";
			} else {
				sql +=" 		select kg as kdGudang, kgname as nmGudang from "+t.getTenantId()+".flokgd ";
			}					 
				sql +="		) a "
					+"	WHERE a.kdGudang IS NOT NULL ";
		if ((kdGudang != null) || (!kdGudang.equalsIgnoreCase(""))) {
			sql +=" AND a.kdGudang <> '"+kdGudang+"' ";
		}
				
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		for (Object[] obj : resultList) {
			SelectItem<String> gudang = new SelectItem<>((String) obj[1], (String) obj[0]);
			result.add(gudang);
		}

		return result;
	}

	
}
