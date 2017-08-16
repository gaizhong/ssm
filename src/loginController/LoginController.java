package loginController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import loginService.LoginService;

@Controller
@RequestMapping("/LoginController")
public class LoginController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	LoginService loginService;
	@RequestMapping("/login")
	public String login(){
		System.out.println("--------");
		String ename=request.getParameter("ename");
		String password=request.getParameter("password");
		loginService.getUser();
		return "loginsucc";
	}

}
