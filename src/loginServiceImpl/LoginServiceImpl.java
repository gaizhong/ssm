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

	public User queryUser(User u){
		User user=loginDaoImpl.queryUser(u);
		return user;
		
	}

	@Override//注册时填写name，ename，password
	public boolean registerUser(User u) {
		UUID uuid=UUID.randomUUID();
		u.setId(uuid.randomUUID().toString().replace("-", ""));
		loginDaoImpl.insertUser(u);
		return true;
	}
}
