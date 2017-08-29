<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		alert(111);
		var ename=document.getElementById("ename").value;
		
		//验证登录用户名是否存在，类似的可以验证手机号什么的
		// 1.创建异步对象  
	    var xhr = createXmlHttp();  
	    xhr.onreadystatechange = function () {  
	       if (xhr.readyState === 4 && xhr.status === 200) {  
	  
	        //    var data = new Function("return" + xhr.responseText)()//反序列化  
	  		var val=xhr.responseText;
	        alert(vals);
		        if(val==1){
		        	document.getElementById("ch").innerHTML="重新设置名字";
		        	document.getElementById("ename").focus();
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
	
	
function submitInfo(){
	var name=document.getElementById("name").value;
	var ename=document.getElementById("ename").value;
	var password=document.getElementById("password").value;
	var pwdConfirm=document.getElementById("pwdConfirm").value;
	name=name.replace(/(^\s*)|(\s*$)/g, "");
	ename=ename.replace(/(^\s*)|(\s*$)/g, "");
	password=password.replace(/(^\s*)|(\s*$)/g, "");
	pwdConfirm=pwdConfirm.replace(/(^\s*)|(\s*$)/g, "");
	if(name.length==0||name=="	"||name.langth=="undefined") {
	//alert(name.langth);
	//alert(111);
	alert("姓名为必填项");
	return ;
	}
	if(ename.length==0||ename=="	"||ename.langth=="undefined") {
	alert("登录名为必填项");
	return ;
	}
	if(password.length==0||password=="	"||password.langth=="undefined") {
	alert("密码为必填项");
	return;
	}
	if(password!=pwdConfirm){
		alert("两次密码不统一");
		return ;
	}
	 document.getElementById("form").submit();
}
	

</script>
<title>个人注册</title>
</head>
<body>
注册<br>
<form action="LoginController/register" method="post" id="form">
姓名:<input type="text" name="name" id="name"><br>
登录名:<input type="text" name="ename" id="ename" onblur="infoCheck()"> <font id="ch" name="ch"></font><br>
密码:<input type="password" name="password" id="password"><br>
密码确定:<input type="password" name="pwdConfirm" id="pwdConfirm"><br>
<input type="button" value="注册" onclick="submitInfo()" >
</form>
</body>
</html>