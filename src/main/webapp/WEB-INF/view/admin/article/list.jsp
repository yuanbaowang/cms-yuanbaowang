<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


状态查询：	<select id = "sta">
			<option value = "">全部</option>
			<option value = "0" <c:if test="${status==0}">selected</c:if>>待审核</option>
			<option value = "1" <c:if test="${status==1}">selected</c:if>>审核通过</option>
			<option value = "2" <c:if test="${status==2}">selected</c:if>>拒绝</option>
		</select>
		<button onclick = "sub()">查询</button>

<table class="table">
<thead>
<tr>
    <th>ID</th>
    <th>标题</th>
    <th>栏目</th>
    <th>分类</th>
    <th>发布时间</th>
    <th>作者</th>
    <th>状态</th>
    <th>热门</th>
    <th>投诉数量</th>
    <th>操作</th>
  </tr>
</thead>
  <tbody>
  <c:forEach items="${list.list }" var = "article">
    <tr>
	    <td>${article.id }</td>
	    <td>${article.title }</td>
	    <td>${article.channel.name }</td>
	    <td>${article.category.name }</td>
	    <td><fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd"/></td>
	    <td>${article.user.username }</td>
	    <td>
	    	<c:choose>
	    		<c:when test="${article.status==0 }">待审核</c:when>
	    		<c:when test="${article.status==1 }">审核通过</c:when>
	    		<c:when test="${article.status==2 }">审核被拒</c:when>
	    		<c:otherwise>未知</c:otherwise>
	    	</c:choose>
	    </td>
	    <td>${article.hot == 1?"热门":"非热门"}</td>
	    <td>${article.complainCnt}</td>
	    <td>
	    	<input type="button" class = "btn btn-danger" value = "删除" onclick ="del(${article.id })">
	    	<input type="button" class = "btn btn-warning" value = "审核" onclick ="check(${article.id })">
	    	<input type="button" class = "btn btn-warning" value = "管理投诉" onclick ="admin(${article.id })" style="margin-left: 15px;">
	    	</td>
  </tr>
  </c:forEach>
  </tbody>
</table>
<!-- 分页组件 -->

<nav aria-label="Page navigation example">
 <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="#" onclick = "fristpage()">Previous</a>
    </li>
    	<c:forEach begin="1" end="${list.pages}" varStatus="i">
    		<li class="page-item ${list.pageNum == i.index?'active':'' }"><a class="page-link" onclick="gopage(${i.index })" href="#">${i.index }</a></li>
    	</c:forEach>
    
    <li class="page-item">
      <a class="page-link" href="#" onclick = "lastpage(${list.pages })">Next</a>
    </li>
  </ul>
</nav>


<!-- 模态框 审核文章 -->
<!-- Modal -->
<div class="modal fade" id="articleContent" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 1200px">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">文章审核</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        	<div class = "row"  id = "divTitle"></div>
        	<div class = "row" id = "divOption"></div>
        	<div class = "row" id = "divContent"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick = "setStatus(1)">审核通过</button>
        <button type="button" class="btn btn-primary" onclick = "setStatus(2)">审核拒绝</button>
        <button type="button" class="btn btn-primary" onclick = "setHot(1)">设为热门</button>
        <button type="button" class="btn btn-primary" onclick = "setHot(0)">取消热门</button>
      </div>
    </div>
  </div>
</div>


<!-- 模态框进行管理投诉 -->
<div class="modal fade" id="articleComplain" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 1200px">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">管理投诉</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        	<div class = "row"  id = "comTitle"></div>
        	<div class = "row" id = "comOption"></div>
        	<div class = "row" id = "comContent"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick = "setStatus(1)">查看投诉</button>
        <button type="button" class="btn btn-primary" onclick = "setStatus(2)">下架文章</button>
        <button type="button" class="btn btn-primary" onclick = "setHot(1)">清空投诉</button>
        <button type="button" class="btn btn-primary" onclick = "setHot(0)">联系作者</button>
      </div>
    </div>
  </div>
</div>



<script type="text/javascript">
	//全局变量
	var article_id;
	
	//模态框 消失的时候刷新当前的列表
	$('#articleContent').on('hidden.bs.modal', function (e) {
		refreshPage();
	})
	
	$('#articleComplain').on('hidden.bs.modal', function (e) {
		refreshPage();
	})
	
	//管理投诉
	function admin(id){
		$.ajax({
			url:'/article/getDetail',
			type:'post',
			data:{id:id},
			dataType:'json',
			success:function(msg){
			if(msg.code == 1){
				$("#comTitle").html("标题："+msg.date.title+" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  投诉量："+msg.date.complainCnt);
				$("#comOption").html("栏目："+'msg.date.channel.name'+"   分类："+'msg.date.category.name'+"   作者："+'msg.date.user.username');
				
				$("#comContent").html(msg.date.content);
				$('#articleComplain').modal('show');
				//把文章的id 保存到全局变量中
				article_id = msg.date.id;
				return;
			}
			alert(msg.error);
			}
		});
	}
	
	function del(id){
		if(confirm("确认删除该数据吗？")){
			$.ajax({
				url:'/user/delArticle',
				type:'post',
				data:{id:id},
				dataType:'json',
				success:function(blo){
					if(blo){
						alert("删除成功！");
						$("#workcontent").load("/user/articles");
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
		$("#workcontent").load("/admin/article?status=0&pageNum="+'${list.pageNum}');
	}
	
	function sub(){
		var status = $("#sta").val();
		$("#workcontent").load("/admin/article?status="+status);
	}
	
	
</script>
