package loginServiceImpl;

import java.util.UUID;

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
		User u=loginDaoImpl.queryUser(id);
		return u;
		
	}

	@Override//注册时填写name，ename，password
	public boolean registerUser(User u) {
		System.out.println(u);
		UUID uuid=UUID.randomUUID();
		u.setId(uuid.randomUUID().toString().replace("-", ""));
		System.out.println(u);
		loginDaoImpl.insertUser(u);
		return true;
	}
}
