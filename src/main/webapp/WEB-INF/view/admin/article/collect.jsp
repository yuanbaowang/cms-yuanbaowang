<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




    <h3>我的收藏：</h3>


  <tbody>
  <c:forEach items="${list.list }" var = "hot">

	  <div class = "col-md-9" style="margin-top: 50px;margin-left: 30px">
					<a href = "${hot.url }" target="-blank">${hot.text }</a>
					<br><br>
					时间：<fmt:formatDate value="${hot.created }" pattern="yyyy-MM-dd HH:mm:ss" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<a class="" href="#" onclick = "del(${hot.id})" style="color:red">删除 </a>
					
					<br>
					
				</div>
	   

  </c:forEach>

<!-- 分页组件 -->

<nav aria-label="Page navigation example">
 <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="#" onclick = "first()">Previous</a>
    </li>
    	<c:forEach begin="1" end="${list.pages}" varStatus="i">
    		<li class="page-item ${list.pageNum == i.index?'active':'' }"><a class="page-link" onclick="go(${i.index })" href="#">${i.index }</a></li>
    	</c:forEach>
    
    <li class="page-item">
      <a class="page-link" href="#" onclick = "last(${list.pages })">Next</a>
    </li>
  </ul>
</nav>






<script type="text/javascript">
	
function go(page){
	$("#workcontent").load("/admin/collect?pageNum="+page);
}

function first(){
	$("#workcontent").load("/admin/collect?pageNum=1");
}

function last(page){
	$("#workcontent").load("/admin/collect?pageNum="+page);
}
	
	function del(id){
		if(confirm("确认删除该数据吗？")){
			$.ajax({
				url:'/admin/delCollect',
				type:'post',
				data:{id:id},
				dataType:'json',
				success:function(blo){
					if(blo){
						alert("删除成功！");
						$("#workcontent").load("/admin/collect");
					}else{
						alert("删除失败！");
					}
				}
			});
		}
	}
	
	function gopage(page){
		$("#workcontent").load("/admin/article?status=${status}&pageNum="+page);
	}
	
	function fristpage(){
		$("#workcontent").load("/admin/article?status=${status}&pageNum=1");
	}
	
	function lastpage(page){
		$("#workcontent").load("/admin/article?status=${status}&pageNum="+page);
	}
	
	function check(id){
		$.ajax({
			url:'/article/getDetail',
			type:'post',
			data:{id:id},
			dataType:'json',
			success:function(msg){
				//alert(JSON.stringify(msg));
				if(msg.code == 1){
					
					$("#divTitle").html("标题："+msg.date.title);
					$("#divOption").html("栏目："+'msg.date.channel.name'+"   分类："+'msg.date.category.name'+"   作者："+'msg.date.user.username');
					
					$("#divContent").html(msg.date.content);
					$('#articleContent').modal('show');
					//把文章的id 保存到全局变量中
					article_id = msg.date.id;
					return;
				}
				alert(msg.error);
			}
		});
		//$("#workcontent").load("/user/updateArticle?id="+id);
	}
	
	//设置发布状态
	function setStatus(status){
		var id = article_id;
		$.ajax({
			url:'/admin/setArticleStatus',
			type:'post',
			data:{id:id,status:status},
			dataType:'json',
			success:function(msg){
				if(msg.code == 1){
					alert("操作成功！");
					$('#articleContent').modal('hide')
					//刷新当前页面
					//refreshPage();
					return;
				}
				alert(msg.error);
			}
		});
	}
	
	//设置热门状态
	function setHot(status){
		var id = article_id;
		$.ajax({
			url:'/admin/setArticleHot',
			type:'post',
			data:{id:id,hot:status},
			dataType:'json',
			success:function(msg){
				if(msg.code == 1){
					alert("操作成功！");
					$('#articleContent').modal('hide')
					//刷新当前页面
					//refreshPage();
					return;
				}
				alert(msg.error);
			}
		});
	}
	
	//刷新
	function refreshPage(){
		$("#workcontent").load("/admin/collect?pageNum="+'${list.pageNum}');
	}
	
	function sub(){
		var status = $("#sta").val();
		$("#workcontent").load("/admin/article?status="+status);
	}
	
	
</script>
