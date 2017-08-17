package loginDao;

import entity.User;

public interface LoginDao {
	public User queryUser(String id);

}
