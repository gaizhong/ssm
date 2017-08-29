package loginInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import entity.User;

//拦截器
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	HttpSession session;
    //不拦截的页面
    private static final String[] IGNORE_URI={"/LoginController/login","/LoginController/register","/LoginController/checkEname"};   //过滤的路径

    /**以下是api原文，中文是个人的理解
     * Callback after completion of request processing, that is, after rendering the view. Will be called on any outcome of handler execution, thus allows for proper resource cleanup.
     * Note: Will only be called if this interceptor's preHandle method has successfully completed and returned true! 
     * As with the postHandle method, the method will be invoked on each interceptor in the chain in reverse order, so the first interceptor will be the last to be invoked.
     * 在完成请求过程，也就是返回视图，可能执行一些处理资源回收
     * 备注：只有当preHandle方法执行成功，并且返回true才被调用。
     * 和postHandler方法一样，在每个拦截器的链上回调，这个方法才被调用，第一个拦截器会在最后被调用
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
//        System.out.println("请求通过后完成一些清理工作");
    }
    /**
     * Intercept the execution of a handler. Called after HandlerAdapter actually invoked the handler, but before the DispatcherServlet renders the view. Can expose additional model objects to the view via the given ModelAndView. 
     * DispatcherServlet processes a handler in an execution chain, consisting of any number of interceptors, with the handler itself at the end. With this method, each interceptor can post-process an execution, getting applied in inverse order of the execution chain.
     * 中断处理，在拦截器返回视图view后适配器调用处理器，显现额外的模型携带的对象给ModelAndView.
     * 执行链上的拦截器处理过程，每一个拦截器的组成部分(必不可少),在处理器的末端，每个拦截器执行的方法.在执行链上回调.
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
//        System.out.println("preHandler()执行返回true结果后，对ModelAndView进行处理");
    }
    /**
     * Intercept the execution of a handler. Called after HandlerMapping determined an appropriate handler object, but before HandlerAdapter invokes the handler.
     * 拦截器的处理器，适配器引用处理器之前，映射合适的处理对象后调用.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        System.out.println("进入第一个拦截方法 preHandle()，第一个执行");
        boolean flag=false;   //用于存储判断登录的结果，让路径是否通过
//        //对请求路径进行判断
        String servletPath=request.getServletPath(); 
        System.out.println(servletPath);
        //判断请求是否需要拦截
        for(String s:IGNORE_URI){
            if(servletPath.equals(s)){
                flag=true;    //如果是许可范围内的访问路径
                break;
            }
        }
        System.out.println("过滤完成---------------------"+flag);
        //拦截请求,当前是访问路径在许可范围内，以下所做的就是对非允许范围内的路径(没登录的用户)做拦截
        if(flag){   //如果是需要拦截的页面,User==null，用户信息为空
            	//是不用拦截的页面
            	System.out.println("放行请求-->controller");
        }else{
        	//不允许的访问路径，需要判断是否有用户信息，没有的话，让其重新登录，就跳转登录界面
        	//所以需要用户在登录或者注册后就把用户信息放入session中，后面使用邮件发送验证信息，注册的页面就不用拦截.
        	User u=(User) session.getAttribute("user");
        	System.out.println(u);
        	//如果用户不为空，表示已经有登录的用户信息，放行
        	flag=true;
        	if(u==null){
        		System.out.println("没有对象信息,请重新登录");
        		request.getRequestDispatcher("login.jsp").forward(request, response);
        		flag=false;
        	}
        }

        return flag;
    }

}