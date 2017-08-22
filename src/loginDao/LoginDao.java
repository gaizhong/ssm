package loginDao;

import entity.User;

public interface LoginDao {
	public User queryUser(User u);
	public boolean insertUser(User u);

}
