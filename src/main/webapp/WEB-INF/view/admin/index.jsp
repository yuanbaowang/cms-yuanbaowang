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
.menu:hover {
    animation-name: hvr-back-pulse;
    animation-duration: .2s;
    animation-timing-function: linear;
    animation-iteration-count: 1;
    background-color: 
	#ed4040;
	color:
	    #fff;
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
<div class="container-fluid">

<div class="row">
<!-- 左侧栏目 -->
<div class="col-md-2">
	<div style="font-size: 30px;margin-top:20px; margin-left:20px; color: red"><strong>栏目：</strong></div>
	<div>
		<ul class="nav flex-column">
			<c:forEach items="${channelList }" var="channel">
				<li class="nav-item  menu">
					
					<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">${channel.name }</a>
			</c:forEach>
		</ul>
	</div>

</div>
<!-- 中间栏目 -->
<div class="col-md-6">
	<!-- 轮播图 -->
	<div>
		<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
		  <ol class="carousel-indicators">
		   	<c:forEach items="${slideList }" var="slide" varStatus="index"> 
		   		<li data-target="#carouselExampleCaptions" data-slide-to="${index.index }" class="${index.index == 0?'active':'' }"></li>
		   	</c:forEach>
		  </ol>
		  <div class="carousel-inner" style="margin-top: 10px">
		   <c:forEach items="${slideList }" var="slide" varStatus="index"> 
		   		<div class="carousel-item ${index.index == 0?'active':'' }">
			       <img src="/pic/${slide.picture }" width="600px" height="500px" class="d-block w-100" alt="${slide.title }">
			       <div class="carousel-caption d-none d-md-block">
			       <h5>${slide.title }</h5>
			       <p>${slide.title }</p>
		      </div>
		      </div>
		   </c:forEach>
		    
		    </div>
		    
		    
		  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
	</div>
	<!-- 文章列表 -->
	<div style="margin-top: 10px">
		<c:forEach items="${hotList.list }" var="hot">
			<div class = "row" style="margin-top: 15px">
				<div class = "col-md-3" >
					<img src="/pic/${hot.picture }"
					 onerror="this.src='/resource/css/img/6011698.jpg'";
					 width="120px" height="120px" class="rounded"
					>
					
				</div>
				<div class = "col-md-9" style="margin-top: 20px">
					<a href = "/index/detail?id=${hot.id }" target="-blank">${hot.title }</a>
					<br>
					作者：${hot.user.username }
					<br>
					栏目：${hot.channel.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分类：${hot.category.name }
					<br>
					
				</div>
			</div>
		</c:forEach>
	</div>
	
</div>
<!-- 右侧栏目 -->
<div class="col-md-4">
	<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>
<div class="panel panel-warning">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>
<div class="panel panel-danger">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>

</div>

</div>


</div>


</body>
<script type="text/javascript">
	$(document).ready(function(){
		//alert('${hotList.list }');
	});
</script>
</html>