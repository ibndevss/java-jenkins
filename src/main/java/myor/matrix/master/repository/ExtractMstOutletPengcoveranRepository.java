package myor.matrix.master.repository;

import java.util.List;

public interface ExtractMstOutletPengcoveranRepository {

	int getEkstrakDataPengcoveran(String userId, String fullFileName, String optSls, String optOutletStatus,
			List<String> listSalesman);

	int getEkstrakDataOutlet(String userId, String fullFileName, String optSls, String optOutletStatus,
			List<String> listSalesman);

}
