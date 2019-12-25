<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "/bootstrap-4.3.1/css/bootstrap.css">
<script type="text/javascript" src = "/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src = "/js/jquery.validate.js"></script>
<script type="text/javascript" src = "/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src= "/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src = "/js/localization/messages_zh.js" ></script>


</head>
<body class ="text-center" style="background:url(../bootstrap-4.3.1/css/img/0.jpg">


<div class ="container">
			
            <h2 class ="text-center" style="color:red">登录管理</h2>
			<h2 class ="text-center" style="color:red">${error }</h2>
            <div class ="logindiv" >
                <form action ="/user/login" id = "form" method ="post">
                    <div class ="input-group input-group-lg">
     		        <span class ="input-group-addon"> <span class ="glyphicon glyphicon-user"> </span> </span>
		           <%-- 	<f:input class ="form-control" maxlength = "8" minlength = "2" cssStyle="margin-left: 100px"   path="username" remote="/user/checkName" placeholder ="用户名"/>  --%>
 					<input type ="text" class ="form-control" name ="username" placeholder ="用户名">
                    </div>
                    <br>
                    <div class ="input-group input-group-lg" style="">
                        <span class ="input-group-addon"> <span class ="glyphicon glyphicon-lock"> </span> </span>
                       <%--  <f:password class ="form-control" path="password" placeholder ="请输入密码"   cssStyle="margin-right: 63px"/> --%>
						 <input type ="password" class ="form-control" style="margin-left: -85px" name ="password" placeholder ="请输入密码">
                    </div>
					<!-- 复选框 -->
					 <div class="form-group form-check">
					    <input type="checkbox" class="form-check-input" value = "1" name = "mdl">
					    <label class="form-check-label" for="exampleCheck1">免登录</label>
					 </div>

                    <br>
                    <div class ="input-group input-group-lg" style ="margin:0 auto; width:100%;">
                         <button type ="submit" class ="buttonlarg btn btn-primary btn-lg" style ="width:100%;">登录</button>
                    </div>
					<div class ="input-group input-group-lg" style ="margin:0 auto; width:100%;">
                         <a href = "register"type ="submit" class ="buttonlarg btn btn-primary btn-lg" style ="width:100%;">跳转注册页面</a>
                    </div>
                </form>
            </div>
        </div>

</body>
<script type="text/javascript">
	$("#form").validate();

</script>
</html>