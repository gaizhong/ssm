package loginController;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		System.out.println(ename);
		// 调用Service进行查询:
//		// 获得response对象,项页面输出:
//		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(request.getCharacterEncoding());
		response.setCharacterEncoding("utf-8");
//		// 判断
		if (ename != null&&ename.equals("zhang")) {
			return "1";
		}
		return "0";
	//	return "["+"a"+":"+1+"]";
	}
}
