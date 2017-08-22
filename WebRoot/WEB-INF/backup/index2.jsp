<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("UTF-8");%>
    <%response.setHeader("Charset","UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>22222222222222222</title>
<script type="text/javascript">

	function createXmlHttp() {  
	    var xmlHttp;  
	    try { // Firefox, Opera 8.0+, Safari  
	        xmlHttp = new XMLHttpRequest();  
	    }  
	    catch (e) {  
	        try {// Internet Explorer  
	            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");  
	        }  
	        catch (e) {  
	            try {  
	                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");  
	            }  
	            catch (e) { }  
	        }  
	    }  
	
	    return xmlHttp;  
	
	}  


	function infoCheck(){
		var ename=document.getElementById("ename").value;
		var password=document.getElementById("password").value;
		var pwdConfirm=document.getElementById("pwdConfirm").value;
		if(password!=pwdConfirm){
			alert("两次密码不统一");
			return ;
		}
		//验证登录用户名是否存在，类似的可以验证手机号什么的
		// 1.创建异步对象  
	    var xhr = createXmlHttp();  
	    xhr.onreadystatechange = function () {  
	       if (xhr.readyState === 4 && xhr.status === 200) {  
	  
	        //    var data = new Function("return" + xhr.responseText)()//反序列化  
	  		var val=xhr.responseText;
		        if(val==1){
		        	document.getElementById("ch").innerHTML="重新设置名字";
		        	return;
		        }else{
		        	document.getElementById("ch").innerHTML="";
		        }
	        }  
	    }  
	  
	    xhr.open("post", 'LoginController/checkEname?ename='+escape(encodeURIComponent(ename)), true);  
// 	    //返回数据需要是json格式，这个需要调整
// 	    function processRequest(data) {  
// 	        alert("111:"+data);  
// 	   }  
	    // 4.发送  
	    xhr.send(null);  
	} 

	

</script>
</head>
<body>
登录<br>
<form action="LoginController/login" method="post">
Ename:<input type="text" name="ename"><br>
password:<input type="password" name="password"><br>
<input type="submit" value="登录"><br>
</form>
<hr>
注册<br>
<form action="LoginController/register" method="post">
姓名:<input type="text" name="name"><br>
登录名:<input type="text" name="ename" id="ename" onblur="infoCheck()"> <font id="ch" name="ch"></font><br>
密码:<input type="password" name="password" id="password"><br>
密码确定:<input type="password" name="pwdConfirm" id="pwdConfirm"><br>
<input type="submit" value="注册"  >
</form>
<hr>
更新<br>
<form action="LoginController/updateUserInfo" method="post">
<input type="submit" value="更新"  >
</form>
</body>
</html>