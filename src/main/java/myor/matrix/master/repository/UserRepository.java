package myor.matrix.master.repository;

public interface UserRepository {
	
	public void deleteUserFirebase(String username);
	
	public void insertUserFirebase(String username, String token);
	
}
