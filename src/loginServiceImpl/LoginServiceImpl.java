package loginServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.User;
import loginDaoImpl.LoginDaoImpl;
import loginService.*;
@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDaoImpl loginDaoImpl;

	@Override
	public String getUser() {
		System.out.println("进入serviceImpl");
		User u=loginDaoImpl.queryUser("1");
		System.out.println(u);
		return null;
	}
	
	public User queryUser(String id){
			
		return null;
		
	}
}
