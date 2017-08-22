<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("UTF-8");%>
    <%response.setHeader("Charset","UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

</head>
<body>
登录<br>
<form action="LoginController/login" method="post">
Ename:<input type="text" name="ename"><br>
password:<input type="password" name="password"><br>
<input type="submit" value="登录"><br>
</form>
<hr>
</form>
</body>
</html>