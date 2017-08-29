<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传图片</title>
</head>
<body>
	-------------------
	<%=request.getParameter("ename")%><br>
	
	<img alt="<%=request.getAttribute("photo")%>"
		src="G:/a.JPG" width="200">
	
		<br>
		<hr>

	<form action="photoUpLoad" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file" width="16"> 
		
		<input type="submit" value="pin">

	</form>

</body>
</html>