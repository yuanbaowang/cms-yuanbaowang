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
<!-- <script type="text/javascript" src = "/js/jquery.validate.js"></script> -->
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
<%-- <jsp:include page="common/hander.jsp"></jsp:include> --%>
<!-- <div class="container-fluid"> -->

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
  <form class="form-inline my-2 my-lg-0" style="margin-right: 50%"  action="channel?channelId=${channelId }&categoryId=${categoryId}" method="post">
      <input class="form-control mr-sm-2" type="search" name = "title" value = "${title }" placeholder="Search" aria-label="Search">
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
						<input type="button" name = "but" class = "btn btn-danger" value = "收藏" onclick ="sc(${hot.id })">
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
		    
		      <a class="page-link" href="/channel?channelId=${channelId }&categoryId=${categoryId}&pageNum=${article.prePage == 0?1:article.prePage}&title=${title}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <c:forEach begin="1" end="${article.pages }" varStatus="count">
		    	 <li class="page-item ${article.pageNum == count.count?'active':''}"><a class="page-link" href="/channel?channelId=${channelId }&categoryId=${categoryId}&pageNum=${count.count}&title=${title}">${count.count }</a></li>
		    </c:forEach>
		   
		    
		    <li class="page-item">
		    
		      <a class="page-link" href="/channel?channelId=${channelId }&categoryId=${categoryId}&pageNum=${article.nextPage == 0?article.pages:article.nextPage}&title=${title}" aria-label="Next">
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
		alert("成功，耗时"+${hs});
		//alert('${hotList.list }');
	});
	
	
	function sc(id){
		if(confirm("确认将该文章添加到我的收藏中？")){
			$.ajax({
				url:'/article/addCollect',
				type:'post',
				data:{id:id},
				dataType:'json',
				success:function(msg){
					if(msg.code == 1){
						alert(msg.error);
						return;
					}
					alert(msg.error);
					}
			});
		}
	}
</script>
</html>