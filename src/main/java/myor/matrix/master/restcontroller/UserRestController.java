package myor.matrix.master.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.UserFirebase;
import myor.matrix.master.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/firebase")
	public void insertUserFirebase(@RequestBody UserFirebase userFire) {
		userService.insertTokenUserFirebase(userFire);
	}
	
}
