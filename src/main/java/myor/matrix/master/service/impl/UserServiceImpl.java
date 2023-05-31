package myor.matrix.master.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.UserFirebase;
import myor.matrix.master.repository.UserRepository;
import myor.matrix.master.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public void insertTokenUserFirebase(UserFirebase userFire) {
		// TODO Auto-generated method stub
		userRepository.deleteUserFirebase(userFire.getUsername());
		userRepository.insertUserFirebase(userFire.getUsername(), userFire.getToken());
	}

}
