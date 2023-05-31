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

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.TeamRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class TeamRepositoryImpl implements TeamRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SelectItem<String>> getAllListTeam() {
		// TODO Auto-generated method stub
		String sql = "SELECT team, team||' '||teamname as teamname "
			+"	FROM "+t.getTenantId()+".fteam "
			+"	ORDER BY team ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SelectItem<String>> getAllListTeamActiveSalesman() {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT t.team, t.team||' '||t.teamname as teamname "
			+"	FROM "+t.getTenantId()+".fteam t "
			+"	INNER JOIN "+t.getTenantId()+".fsls s ON (t.team = s.team) "
			+"	ORDER BY t.team ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultList = query.getResultList();
		
		return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
				.collect(Collectors.toList());
	}

	@Override
	public List<SearchBrowseDto> getBrowseTeam() {
		// TODO Auto-generated method stub
		List<SearchBrowseDto> result = new ArrayList<>();
		
		String sql = " SELECT team, teamname FROM "+t.getTenantId()+".fteam ORDER BY team ASC ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> hasil = query.getResultList();

		for (Object[] obj : hasil) {
			SearchBrowseDto data = new SearchBrowseDto(Objects.toString(obj[0], ""), Objects.toString(obj[1], ""));
			result.add(data);
		}
		
		return result;
	}

	@Override
	public List<SelectItem<String>> getListTeam() {
		// TODO Auto-generated method stub
		String sql = "	SELECT   team, team||'-'||teamname as teamName "
					+"	    FROM (SELECT DISTINCT s.team, t.teamname "
					+"	                     FROM "+t.getTenantId()+".fsls s LEFT JOIN "+t.getTenantId()+".fteam t "
					+"	                          ON s.team = t.team "
					+"	          UNION ALL "
					+"	          SELECT CAST ('00' AS VARCHAR2 (4)), CAST ('All' AS NVARCHAR2 (30)) "
					+"	            FROM DUAL) "
					+"	ORDER BY team ";
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> resultList = query.getResultList();
			
			return resultList.stream().map(obj -> new SelectItem<>((String) obj[1], (String) obj[0]))
					.collect(Collectors.toList());
	}

}
