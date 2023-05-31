package myor.matrix.master.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myor.matrix.master.tenant.TenantContext;
import myor.matrix.master.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private TenantContext t;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void deleteUserFirebase(String username) {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM "+t.getTenantId()+".fuser_firebase WHERE username = '"+username+"' ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void insertUserFirebase(String username, String token) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "+t.getTenantId()+".fuser_firebase(username, token) "
				+" VALUES( "
				+"		'"+username+"',	"
				+"		'"+token+"'	"
				+" ) ";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	
	
}
