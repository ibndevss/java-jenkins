package myor.matrix.master.repository;

import java.util.List;

public interface DaftarTeamDiskonRegulerRepository {
	
	public Object[] getHeader(String inputDateEfektif, String inputTeam);
	public List<Object[]> getData(String teamFrom,String teamTo,String dateEfektifFrom,String dateEfektifTo);
	
}
