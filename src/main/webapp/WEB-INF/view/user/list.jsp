<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css"/>
<link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css"/>
<script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>

<style type="text/css">
	/* 背景颜色 */
	.mybgcolor{
		background:#F0FFFF;
	}
	
	/* 鼠标划过颜色 */
	.myselected li:hover{
		background:red;
	}

</style>

</head>


<body>
<nav class="navbar navbar-expand-lg navbar-light bg-*" style="background-color: #C0C0C0">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" style="margin-right: 50px">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    <!-- 设置头像 个人信息 等导航格 -->
    <div>
    	<ul class="navbar-nav mr-auto  myselected">
    		<li class="nav-link" style="margin-right: 20px"><img width="40" height="40" src="/bootstrap-4.3.1/css/img/6d2fb.gif"></li>
    		<li class="nav-item dropdown" >
    		 <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      	  个人中心
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another Action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
    		</li>
    	</ul>
    </div>
  </div>
</nav>

<!-- 中间布局 -->
<div class = "container row" style="height: 800px">
	<!-- 最左侧导航栏 -->
	<div class = "col-md-2" style="border-right: solid 3px; margin-top: 30px;font-size: 30px">
	<ul class="nav flex-column  myselected">
	  <li class="nav-item">
	    <a class="nav-link active" href="#" onclick = "showWork('/user/articles')">我的文章</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="#" onclick = "showWork('/user/postArticle')">发布文章</a>
	  </li>
	  <li class="nav-item">
   	 <a class="nav-link" href="#">所有文章</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="#" tabindex="-1" onclick = "showWork('/user/comment')">我的评论</a>
	  </li>
</ul>
	</div>

	<!-- 中间的显示区 -->
	<div class = "col-md-10" id = "workcontent" style="margin-top: 30px;font-size: 20px;height: 800px">
		
	</div>
		

</div>

<!-- 尾部导航栏 -->
<nav class = "nav justify-content-center fixed-bottom bg-*" style="background-color: #00FFFF;">
			<a class = "nav-link" href = "#">关于我们</a>
			<a class = "nav-link" href = "#">您的建议</a>
			<a class = "nav-link" href = "#">联系我们</a>
</nav>



 <!--navbar-expand-lg指定响应式行为， navbar-light bg-light定义导航栏样式 -->
    <!-- <nav class="navbar navbar-expand-lg navbar-light bg-light">
        执行导航栏的显示商标信息
        <a class="navbar-brand" href="#">NavBar</a>
        指定导航栏的响应式行为
        `data-toggle`指以什么事件触发，`data-target`指触发事件的目标标签。一起使用时表示`data-target`所指的元素以`data-toggle`指定的形式显示。
        `aria-*`的一系列属性都是为了应用在辅助设备上使用。
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            指定按钮的显示图标
            <span class="navbar-toggler-icon"></span>
        </button>
        导航栏内容，collapse表示隐藏内容
        <div class="navbar-collapse collapse " id="navbarNav">
            <ul class="navbar-nav">
                每一个导航项目，active表示显示当前导航链接
                <li class="nav-item active">
                    sr-only表示除屏幕阅读器外，其他设备隐藏该元素
                    <a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Features</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Pricing</a>
                </li>
                <li class="nav-item">
                    disabled类指定该导航无法操作
                    <a class="nav-link disabled" href="#">Disabled</a>
                </li>
                指定导航项目为下拉列表形式
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Dropdown link
                    </a>
                    下拉列表及其项目
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another Action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav> -->
</body>
<script type="text/javascript">
	/* 初始化副文本编辑器 */
	KindEditor.ready(function(K) {
		window.editor1 = K.create();
		prettyPrint();
	});

	function showWork(url){
		//设置样式选中样式
		$("#workcontent").addClass("mybgcolor");
		//懒加载 ，把url中的内容加载到 workcontent 中
		$("#workcontent").load(url);
	}
</script>

</html>