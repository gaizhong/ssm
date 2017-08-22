package loginDaoImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import entity.User;
import loginDao.LoginDao;

/**
 * repository 说明就是一个dao层的注解
 */
@Repository
public class LoginDaoImpl implements LoginDao{
	/**
	 * 自动注入这里spring管理了mybatis的sqlsessionfactory
	 */
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	
	/**
	 * 查询用户信息
	 * 
	 * @param u
	 * @return
	 */
	public User queryUser(User u) {
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("进入daoimpl的-->"+u);
		return session.selectOne("mybatis.queryUser", u);
	}


	@Override
	public boolean insertUser(User u) {
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("插入操作:");
		int insert = session.insert("insertUser", u);
		if(insert==1){
			return true;
		}else
		return false;
	}
}
