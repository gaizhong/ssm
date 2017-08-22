package loginService;

import org.springframework.stereotype.Service;

import entity.User;
@Service
public interface LoginService {
	public User queryUser(User u);
	public boolean registerUser(User u);

}
