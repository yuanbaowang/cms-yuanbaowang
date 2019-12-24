<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<link rel = "stylesheet" href = "/bootstrap-4.3.1/css/bootstrap.css">
<script type="text/javascript" src = "/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src = "/js/jquery.validate.js"></script>
<script type="text/javascript" src = "/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src= "/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src = "/js/localization/messages_zh.js" ></script>
    
<nav class="navbar navbar-expand-lg navbar-light bg-*" style="background-color: pink">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="#">Features</a>
      <a class="nav-item nav-link" href="#">Pricing</a>
      <a class="nav-item nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
    </div>
  </div>
  <form class="form-inline my-2 my-lg-0" style="margin-right: 50%">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    <div>
    	<ul class="navbar-nav mr-auto  myselected">
    		<li class="nav-item dropdown" >
    		  <c:if test="${sessionScope.USER_KEY != null}">
    			 <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      	  ${sessionScope.USER_KEY.username}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/user/loginOut">注销</a>
                         <c:if test="${sessionScope.USER_KEY.role == 1}">
                       	 	<a class="dropdown-item" href="/admin/index">进入个人中心</a>
                        </c:if>
                        <c:if test="${sessionScope.USER_KEY.role == 0}">
                       	 	<a class="dropdown-item" href="/user/list">进入个人中心</a>
                        </c:if>
                        
                        
                        <a class="dropdown-item" href="#">个人设置</a>
                    </div>
                    </c:if>
                    
                    <c:if test="${sessionScope.USER_KEY == null}">
                     <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      	 登录方式
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/user/login">登录</a>
                        <a class="dropdown-item" href="/user/register">注册</a>
                    </div>
                    </c:if>
    		</li>
    	</ul>
    </div>
</nav>