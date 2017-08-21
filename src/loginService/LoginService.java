package loginService;

import org.springframework.stereotype.Service;

import entity.User;
@Service
public interface LoginService {
	public String getUser();
	public User queryUser(String id);
	public boolean registerUser(User u);

}
