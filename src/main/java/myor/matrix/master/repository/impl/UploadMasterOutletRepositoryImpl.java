package myor.matrix.master.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.entity.UploadMasterOutletCoverDto;
import myor.matrix.master.entity.UploadMasterOutletOutletDto;
import myor.matrix.master.repository.UploadMasterOutletRepository;
import myor.matrix.master.tenant.TenantContext;

@Repository
public class UploadMasterOutletRepositoryImpl implements UploadMasterOutletRepository {

	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Object[]> cekNoOutlet(String xkey, String custNo) {
		// TODO Auto-generated method stub
		List<Object[]> resultList = new ArrayList<>();
		String sql = "SELECT *                                                        "
                +" FROM (SELECT SUM (custawal) custawal, SUM (custakhir) custakhir    "
                +"        FROM (SELECT TO_NUMBER (memostring2) custawal, 0 custakhir  "
                +"                FROM "+t.getTenantId()+".ftable13                   "
                +"              WHERE xkey = 'X_CUSTNO_"+xkey+"_AWAL'                 "
                +"              UNION ALL                                             "
                +"              SELECT 0 custawal, TO_NUMBER (memostring2) custakhir  "
                +"                FROM "+t.getTenantId()+".ftable13                   "
                +"               WHERE xkey = 'X_CUSTNO_"+xkey+"_AKHIR') a) a "
                + " where custawal <= '"+custNo+"' and CUSTAKHIR >= '"+custNo+"' ";

		Query query = entityManager.createNativeQuery(sql);
		resultList = query.getResultList();

		return resultList;
	}

	@Override
	public void insertFucstmst(UploadMasterOutletOutletDto p, String xbarcode, String Xflagsubdist) {
		// TODO Auto-generated method stub
		
		String sql =" insert into "+t.getTenantId()+".fcustmst (CUSTNO,CUSTNAME,CUSTADD1,CUSTADD2,CCITY,CKDPOS,CCONTACT,CPHONE1,CFAXNO, "
                +"  CRESD1,CRESD2,CCITY2,CPHONE2,CTERM,CLIMIT,FLAGLIMIT,FLAGPAY,FLAGOUT,FLAGKBON,FLAGHOME,GDISC,LOCATION, "
                +"  DISTRIK,TYPEOUT,GRUPOUT,GHARGA,GPLU,GKONV,BEAT,SBEAT,CLASS,KDPSR,KDIND,CWEEKNO,LWEEKNO,CLASTDATE, "
                +"  FIRSTDATE,FIRSTOPEN,CLASTUPD,REGDATE,TAXNAME,TAXADD1,TAXADD2,TAXCITY,TAXFLAG,NPWP,RATA,BANK,NOACC, "
                +"  NOREFSUP,AGENLAIN,TOTALAR,BRS,FLAGSBD,DATA01,DATA02,DATA03,DATA04,DATA05,DATA06,DATA07,DATA08, "
                +"  DATA09,DATA10,DATA11,DATA12,DATA13,DATA14,DATA15,DATA16,DATA17,DATA18,DATA19) values "
                +" ( replace('"+p.getCustno()+"','|',''),"
                +"   replace('"+p.getCustname()+"','|',''),"
                +"   replace('"+p.getCustadd1()+"','|',''),"
                +"   replace('"+p.getCustadd2()+"','|',''),"
                +"   replace('"+p.getCcity()+"','|',''),"
                +"   replace('"+p.getCkdpos()+"','|',''),"
                +"   replace('"+p.getCcontact()+"','|',''),"
                +"   replace('"+p.getCphone1()+"','|',''),"
                +"   replace('"+p.getCfaxno()+"','|',''),"
                +"   replace('"+p.getCresd1()+"','|',''),"
                +"   replace('"+p.getCresd2()+"','|',''),"
                +"   replace('"+p.getCcity2()+"','|',''),"
                +"   replace('"+p.getCphone2()+"','|',''),"
                +"   replace('"+p.getCterm()+"','|',''),"
                +"   replace('"+p.getClimit()+"','|',''),"
                +"   replace('"+p.getFlaglimit()+"','|',''),"
                +"   replace('"+p.getFlagpay()+"','|',''),"
                +"   replace('"+p.getFlagout()+"','|',''),"
                +"   replace('"+p.getFlagkbon()+"','|',''),"
                +"   replace('"+p.getFlaghome()+"','|',''),"
                +"   replace('"+p.getGdisc()+"','|',''),"
                +"   replace('"+p.getLocation()+"','|',''),"
                +"   replace('"+p.getDistrik()+"','|',''),"
                +"   replace('"+p.getTypeout()+"','|',''),"
                +"   replace('"+p.getGrupout()+"','|',''),";
					if (p.getGharga().length() == 0) {
						if (Xflagsubdist.equalsIgnoreCase("GT")) {
							sql += " replace(''100'',''|'',''''), ";
						} else {
							sql += "  replace(''200'',''|'',''''), ";
						}
					} else {
						sql += " replace('"+p.getGharga()+"','|',''), ";
					}
                sql +="  replace('"+p.getGplu()+"','|',''),"
                +"   replace('"+p.getGkonv()+"','|',''),"
                +"   replace('"+p.getBeat()+"','|',''),"
                +"   replace('"+p.getSbeat()+"','|',''),"
                +"   replace('"+p.getClasss()+"','|',''),"
                +"   replace('"+p.getKdpsr()+"','|',''),"
                +"   replace('"+p.getKdind()+"','|',''),"
                +"   replace('"+p.getCweekno()+"','|',''),"
                +"   replace('"+p.getLweekno()+"','|',''),"
                +"   replace('"+p.getClastdate()+"','|',''),"
                +"   replace('"+p.getFirstdate()+"','|',''),"
                +"   replace('"+p.getFirstopen()+"','|',''),"
                +"   replace('"+p.getClastupd()+"','|',''),"
                +"   replace('"+p.getRegdate()+"','|',''),"
                +"   replace('"+p.getTaxname()+"','|',''),"
                +"   replace('"+p.getTaxadd1()+"','|',''),"
                +"   replace('"+p.getTaxadd2()+"','|',''),"
                +"   replace('"+p.getTaxcity()+"','|',''),"
                +"   replace('"+p.getTaxflag()+"','|',''),"
                +"   replace('"+p.getNpwp()+"','|',''),"
                +"   replace('"+p.getRata()+"','|',''),"
                +"   replace('"+p.getBank()+"','|',''),"
                +"   replace('"+p.getNoacc()+"','|',''),"
                +"   replace('"+p.getNorefsup()+"','|',''),"
                +"   replace('"+p.getAgenlain()+"','|',''),"
                +"   replace('"+p.getTotalar()+"','|',''),"
                +"   replace('"+p.getBrs()+"','|',''),"
                +"   replace('"+p.getFlagsbd()+"','|',''),"
                +"   replace('"+xbarcode+"','|',''),"
                +"   replace('"+p.getData02()+"','|',''),"
                +"   replace('"+p.getData03()+"','|',''),"
                +"   replace('"+p.getData04()+"','|',''),"
                +"   replace('"+p.getData05()+"','|',''),"
                +"   replace('"+p.getData06()+"','|',''),"
                +"   replace('"+p.getData07()+"','|',''),"
                +"   replace('"+p.getData08()+"','|',''),"
                +"   replace('"+p.getData09()+"','|',''),"
                +"   replace('"+p.getData10()+"','|',''),"
                +"   replace('"+p.getData11()+"','|',''),"
                +"   replace('"+p.getData12()+"','|',''),"
                +"   replace('"+p.getData13()+"','|',''),"
                +"   replace('"+p.getData14()+"','|',''),"
                +"   replace('"+p.getData15()+"','|',''),"
                +"   replace('"+p.getData16()+"','|',''),"
                +"   replace('"+p.getData17()+"','|',''),"
                +"   replace('"+p.getData18()+"','|',''),"
                +"   replace('"+p.getData19()+"','|','') "
                +" )"  ;
                Query query = entityManager.createNativeQuery(sql);
        		query.executeUpdate();
	}

	@Override
	public void insertFcustsls(UploadMasterOutletCoverDto p) {
		// TODO Auto-generated method stub
		String sql ="insert into "+t.getTenantId()+".fcustsls "
                +" (CUSTNO,SLSNO,NOBRS,HSENIN,HSELASA,HRABU,HKAMIS,HJUMAT,HSABTU,HMINGGU,VISIT1,VISIT2,VISIT3,VISIT4,ROUTE)"
                +" values ("
                            +" '"+p.getCustno()+"' ,"
                            +" '"+p.getSlsno()+"' ,"
                            +" '"+p.getBaris()+"' ,"
                            +" '"+p.getHsenin()+"' ,"
                            +" '"+p.getHselasa()+"',"
                            +" '"+p.getHrabu()+"' ,"
                            +" '"+p.getHkamis()+"' ,"
                            +" '"+p.getHjumat()+"' ,"
                            +" '"+p.getHsabtu()+"' ,"
                            +" '"+p.getHminggu()+"',"
                            +" '"+p.getVisit1()+"' ,"
                            +" '"+p.getVisit2()+"' ,"
                            +" '"+p.getVisit3()+"' ,"
                            +" '"+p.getVisit4()+"' ,"
                            +" '"+p.getRoute()+"'"
                  +")"  ;
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
}
