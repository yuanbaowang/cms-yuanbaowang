<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章管理系统</title>
<link rel = "stylesheet" href = "/bootstrap-4.3.1/css/bootstrap.css">
<script type="text/javascript" src = "/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src = "/js/jquery.validate.js"></script>
<script type="text/javascript" src = "/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src= "/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src = "/js/localization/messages_zh.js" ></script>
<style type="text/css">
	
	/* 菜单样式 */
	.menu {
	display: block;
	width: 110px;
	height: 40px;
	line-height: 40px;
	text-align: center;
	color: #444;
	margin-top:20px;
	border-radius: 4px;
	margin-bottom: 2px;
	transition-property: color,background-color;
	font-size: 25px;
}
.m{
	font-size: 15px;
	margin-top:20px;
	border-radius: 4px;
	margin-bottom: 2px;
	transition-property: color,background-color;
	color: #444;

}
.m:hover{
	animation-name: hvr-back-pulse;
    animation-duration: .2s;
    animation-timing-function: linear;
    animation-iteration-count: 1;
    background-color: 
	#ed4040;
	color:#fff;
}
.menu:hover {
    animation-name: hvr-back-pulse;
    animation-duration: .2s;
    animation-timing-function: linear;
    animation-iteration-count: 1;
    background-color: 
	#ed4040;
	color:#fff;
}


a.active{
	  background-color:pink;
}
.ex {
		overflow: hidden;
		text-overflow:ellipsis;
		white-space: nowrap;
	}

</style>
</head>
<body>
<!-- 导航条 -->
<jsp:include page="common/hander.jsp"></jsp:include>
<!-- <div class="container-fluid"> -->

<div class="row">
<!-- 左侧栏目 -->
<div class="col-md-2">
	<div style="font-size: 30px;margin-top:20px; margin-left:10px; color: red"><img src="/resource/images/logo.png"></div>
	<div>
		<ul class="nav flex-column">
			<c:forEach items="${channelList }" var="channel">
				<li class="nav-item  menu">
					<a class="nav-link ${channelId==channel.id?'active':'' }" href="channel?channelId=${channel.id }" tabindex="-1">${channel.name }</a>
			</c:forEach>
		</ul>
	</div>

</div>
<!-- 中间栏目 -->
<div class="col-md-6" style="margin-top: 20px; background-color: pink">
	<div>
		<ul class="nav nav-pills">
				<li class="nav-item m" style="margin-left: 20px;margin-top: 20px">
		   			 <a class="nav-link ${categoryId==0?'active':''}" href="channel?channelId=${channelId }">全部</a>
				</li>
			<c:forEach items="${category }" var="category">
				<li class="nav-item m" style="margin-left: 20px;margin-top: 20px">
		   		 <a class="nav-link ${categoryId==category.id?'active':''}" href="channel?channelId=${channelId }&categoryId=${category.id}">${category.name}</a>
				</li>
			</c:forEach>
		</ul>
	
	</div>
	
	<!-- 文章列表 -->
	<div style="margin-top: 10px">
		<c:forEach items="${article.list }" var="hot">
			<div class = "row" style="margin-top: 15px">
				<div class = "col-md-3" >
					<img src="/pic/${hot.picture }"
					 onerror="this.src='/resource/css/img/6011698.jpg'"
					 width="120px" height="120px" class="rounded"
					 style="border-radius:24px!important"
					>
					
				</div>
				<div class = "col-md-9" style="margin-top: 20px">
					<a href = "/article/detail?id=${hot.id }" target="-blank">${hot.title }</a>
					<br>
					作者：${hot.user.username }
					<br>
					栏目：${hot.channel.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分类：${hot.category.name }
					<br>
					
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="row justify-content-center" style="margin-top: 20px ">
		<!-- 分页 -->
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		    <li class="page-item">
		    
		      <a class="page-link" href="/channel?channelId=${channelId }&categoryId=${categoryId}&pageNum=${article.prePage == 0?1:article.prePage}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <c:forEach begin="1" end="${article.pages }" varStatus="count">
		    	 <li class="page-item ${article.pageNum == count.count?'active':'' }"><a class="page-link" href="/channel?channelId=${channelId }&categoryId=${categoryId}&pageNum=${count.count}">${count.count }</a></li>
		    </c:forEach>
		   
		    
		    <li class="page-item">
		    
		      <a class="page-link" href="/channel?channelId=${channelId }&categoryId=${categoryId}&pageNum=${article.nextPage == 0?article.pages:article.nextPage}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		      
		    </li>
		  </ul>
		</nav>
	</div>
	
</div>
<!-- 右侧栏目 -->
<div class="col-md-4" style="margin-top: 20px;">
		<div class="card">
	 	 <div class="card-header">
	  		  最新文章
	  	</div>
	  	<div class="card-body">
	   		<ol>
	   		<c:forEach items="${lastList }" var="last" varStatus="count">
	   			<li class = "ex">${count.count }.  <a href = "/article/detail?id=${last.id }" target="_blank">${last.title }</a></li>
	   		</c:forEach>
	   		</ol>	
		</div>
	</div>
	
	<div class="card" style="margin-top: 100px">
	 	 <div class="card-header">
	  		 公告 
	  	</div>
	  	<div class="card-body">
	   		<ul>
	   			<li>1</li>
	   			<li>2</li>
	   			<li>3</li>
	   		</ul>	
		</div>
	</div>
</div>
</div>
<jsp:include page="common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		//alert('${hotList.list }');
	});
</script>
</html>