<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title }</title>
<link rel = "stylesheet" href = "/bootstrap-4.3.1/css/bootstrap.css">
<script type="text/javascript" src = "/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src = "/js/jquery.validate.js"></script>
<script type="text/javascript" src = "/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src= "/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src = "/js/localization/messages_zh.js" ></script>
</head>
<body>
<!-- 导航条 -->
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
</nav>
	<div class = "container">
		<div class = "row justify-content-center">
			<h3>${article.title }</h3>
		</div>
		<div class = "row justify-content-center">
			<h5>
			作者：${article.user.username }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			栏目：${article.channel.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			分类：${article.category.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			发布时间：
			<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd" />
			</h5>
			<a style = "color:red" href = "/article/complain?articleId=${article.id }">投诉</a>
		</div>
		<div style="margin-top: 30px">
			<h3 style="color: red">文章内容：</h3>${article.content }	
		</div>

		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		    <li class="page-item">
		      <a class="page-link" href="/article/detail?id=${id-1}&pageNum=${hotList.prePage==0?1:hotList.prePage }" target="blank_" aria-label="Previous">
		        <span aria-hidden="true">上一篇</span>
		      </a>
		    </li>
		    <li class="page-item">
		      <a class="page-link" href="/article/detail?id=${id+1}&pageNum=${hotList.nextPage==0?hotList.pages:hotList.nextPage }" target="blank_" aria-label="Next">
		        <span aria-hidden="true">下一篇</span>
		      </a>
		    </li>
		  </ul>
		</nav>
	</div>
	<!-- 发布评论 -->
	<div class = "row justify-content-center">
		<textarea rows="5" cols="300" id = "commentText">
		
		</textarea>
		<input type="button" class="btn btn-primary" value = "发表评论" onclick = "addComment()">
	</div>
	
	<div style="margin-top: 30px">
		<h3 style="color: red;margin-left: 35px" id = "comment">评论：</h3>
		
		<!-- <div class = "row" id = "comment">
			显示评论
		</div> -->
	</div>
	
</body>
<script type="text/javascript">
	function gopage(page){
		showComment(page);
	}

	function showComment(page){
		$("#comment").load("/article/comments?id=${id}&pageNum="+page);
	}
 	$(document).ready(function(){
		//显示第一页的评论
		showComment(1);
	}); 


	function addComment(){
		alert($("#commentText").val());
		$.ajax({
			url:"/article/addComment",
			type:'post',
			data:{articleId:'${id}',content:$("#commentText").val()},
			dataType:'json',
			success:function(bol){
				if(bol.code == 1){
					alert("发布成功！");
					$("#commentText").val("");
				}else{
					alert(bol.error);
				}
				
				
			}
		});
	}
</script>
</html>