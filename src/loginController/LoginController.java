package loginController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v3.decode.Decoder;

import entity.User;
import loginService.LoginService;

@Controller
@RequestMapping("/LoginController")
public class LoginController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	LoginService loginService;
	@RequestMapping("/login")
	public String login(){
		System.out.println("--------");
		String ename=request.getParameter("ename");
		String password=request.getParameter("password");
		System.out.println("ename   "+ename+"  password  "+password);
		loginService.getUser();
		return "test";
	}
	
	@RequestMapping("checkEname")
	@ResponseBody
	public String checkEname(){
		String ename=request.getParameter("ename");
		try {
			String str=URLDecoder.decode(ename, "utf-8");
			 ename=URLDecoder.decode(ename, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 调用Service进行查询:
//		// 获得response对象,项页面输出:
//		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		User u=loginService.queryUser(ename);
//		// 判断
		if (u!=null) {
			return "1";
		}
		return "0";
	//	return "["+"a"+":"+1+"]";
	}
	//注册使用完了进入个人现实页面，并完善个人信息
	@RequestMapping("/register")
	public String register(){
		String name=request.getParameter("name");
		String ename=request.getParameter("ename");
		String password=request.getParameter("password");
		System.out.println("register-->"+name+"  "+ename +"  "+password);
		User u=new User();
		u.setName(name);
		u.setEname(ename);
		u.setPassword(password);
		System.out.println("setter-->"+name+"  "+ename +"  "+password);
		boolean bool=loginService.registerUser(u);
		System.out.println(bool);
		return "completeUserInfo";
	}
}
