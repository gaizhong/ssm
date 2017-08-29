package loginController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	HttpSession session;
	@Autowired
	LoginService loginService;

	@RequestMapping("/login")
	public String login() {
		System.out.println("--------");
		String ename = request.getParameter("ename");
		String password = request.getParameter("password");
		System.out.println("ename   " + ename + "  password  " + password);
		User u = new User();
		u.setName(ename);
		System.out.println(request.getContextPath());
		System.out.println(request.getSession().getServletContext().getRealPath(""));
		User user = loginService.queryUser(u);
		request.getSession().setAttribute("user", user);
		System.out.println(user);
		String photo = user.getPhoto().replace("\\", "/");
		System.out.println(photo);
		request.setAttribute("photo", photo);
		System.out.println(request.getAttribute("photo"));
		return "test";
	}

	@RequestMapping("/checkEname")
	@ResponseBody
	public String checkEname() {
		String ename = request.getParameter("ename");
		try {
			ename = URLDecoder.decode(ename, "utf-8");
			// String str=URLDecoder.decode(ename, "utf-8");
			System.out.println(ename);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 调用Service进行查询:
		// // 获得response对象,项页面输出:
		// HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		User u = new User();
		u.setEname(ename);
		User user = loginService.queryUser(u);
		System.out.println(user);
		// // 判断
		if (user != null) {
			return "1";
		}
		return "0";
		// return "["+"a"+":"+1+"]";
	}

	// 注册使用完了进入个人现实页面，并完善个人信息
	@RequestMapping("/register")
	@ResponseBody
	public void register() {
		String name = request.getParameter("name");
		String ename = request.getParameter("ename");
		String password = request.getParameter("password");
		System.out.println("register-->" + name + "  " + ename + "  " + password);
		User u = new User();
		u.setName(name);
		u.setEname(ename);
		u.setPassword(password);
		System.out.println("setter-->" + name + "  " + ename + "  " + password);
		session.setAttribute("User", u);
		boolean bool = loginService.registerUser(u);
		String path = request.getContextPath();
		try {
			response.sendRedirect(path + "/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return "completeUserInfo";
	}

	@RequestMapping("/updateUserInfo")
	public String updateUserInfo() {
		System.out.println("进入更新的controller");
		return "updateUserInfo";
	}

	@RequestMapping("/photoUpLoad")
	@ResponseBody
	public String fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {
		System.out.println("------------------------------------------");
		long startTime = System.currentTimeMillis();
		System.out.println("fileName：" + file.getOriginalFilename());
		String path = "E:/" + new Date().getTime() + file.getOriginalFilename();
		System.out.println(request.getContextPath());
		File newFile = new File(path);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		long endTime = System.currentTimeMillis();
		System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		return "success";
	}
}
