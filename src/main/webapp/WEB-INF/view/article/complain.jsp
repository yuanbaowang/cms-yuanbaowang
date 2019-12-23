<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投诉</title>
<link rel = "stylesheet" href = "/bootstrap-4.3.1/css/bootstrap.css">
<script type="text/javascript" src = "/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src = "/js/jquery.validate.js"></script>
<script type="text/javascript" src = "/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src= "/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src = "/js/localization/messages_zh.js" ></script>
</head>
<body>
<f:form action="/article/complain"  method="post" modelAttribute="complain" enctype="multipart/form-data">
		<div class="form-group">
		   <label >文章标题:</label><br>${article.title}
		  </div>
		<input type="hidden" name="articleId" value="${article.id}">
	
	 <div class="form-group">
		   <label >地址</label>
		    <f:input path="srcUrl"/>
		    <f:errors path="srcUrl" cssStyle="color:red"></f:errors>
		 </div>
		 
		 <div class="form-group">
		   <label >投诉类型</label>
		    <f:select path="complainType" >
		    	<option value="0">请选择</option>
		    	<option value="1">政治敏感</option>
		    	<option value="2">反社会</option>
		    	<option value="3">涉毒</option>
		    	<option value="4">涉黄 </option>
		    </f:select>
		     <f:errors path="complainType" cssStyle="color:red"></f:errors>
		 </div> 
		 
		  <div class="form-group">
		   <label >投诉类型</label>
		   		&nbsp;&nbsp;&nbsp;<input type="checkbox" name="compainOption" value="1"> 标题夸张
		   		  &nbsp;&nbsp;&nbsp;<input type="checkbox" name="compainOption" value="2">与事实不符 
		   		  &nbsp;&nbsp;&nbsp;<input type="checkbox" name="compainOption" value="3"> 疑似抄袭
		 	 <f:errors path="compainOption" cssStyle="color:red"></f:errors>
		  </div>
		  
		   <div class="form-group">
		   <label >图片</label>
		   	<input type="file" name="file">
		  </div>
		  
		    <div class="form-group">
		   <label >内容</label>
		   	<f:textarea path="content" cols="100" rows="3" />
		   	 <f:errors path="content" cssStyle="color:red"></f:errors>
		  </div> 
		  
		  <div class="form-group">
		   <label >邮箱</label>
		   <f:input path="email"/>
		   	 <f:errors path="email" cssStyle="color:red"></f:errors>
		  </div> 
		  
		    <div class="form-group">
		   <label >电话</label>
		   <f:input path="mobile"/>
		   	 <f:errors path="mobile" cssStyle="color:red"></f:errors>
		 </div>	 	  
		<button>提交</button>
		 
</f:form>
</body>
</html>