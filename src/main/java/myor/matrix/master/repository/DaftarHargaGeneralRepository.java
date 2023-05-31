package myor.matrix.master.repository;

import java.util.List;

public interface DaftarHargaGeneralRepository {
	
	public Object[] getHeader(String inputDateBerlaku, String inputPcode);
	public List<Object[]> getData(String pcodeFrom,String pcodeTo,String dateBerlakuFrom,String dateBerlakuTo);

}
