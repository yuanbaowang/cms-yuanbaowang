<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

   

<table class="table">
<thead>
<tr>
    <th>ID</th>
    <th>标题</th>
    <th>栏目</th>
    <th>分类</th>
    <th>发布时间</th>
    <th>状态</th>
    <th>操作</th>
  </tr>
</thead>
  <tbody>
  <c:forEach items="${articlePage.list }" var = "article">
    <tr>
	    <td>${article.id }</td>
	    <td>${article.title }</td>
	    <td>${article.channel.name }</td>
	    <td>${article.category.name }</td>
	    <td><fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd"/></td>
	    <td>
	    	<c:choose>
	    		<c:when test="${article.status==0 }">待审核</c:when>
	    		<c:when test="${article.status==1 }">审核通过</c:when>
	    		<c:when test="${article.status==2 }">审核被拒</c:when>
	    		<c:otherwise>未知</c:otherwise>
	    	</c:choose>
	    </td>
	    <td>
	    	<input type="button" class = "btn btn-danger" value = "删除" onclick ="del(${article.id })">
	    	<input type="button" class = "btn btn-warning" value = "修改" onclick ="upd(${article.id })">
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
    	<c:forEach begin="1" end="${articlePage.pages}" varStatus="i">
    		<li class="page-item"><a class="page-link" onclick="gopage(${i.index })" href="#">${i.index }</a></li>
    	</c:forEach>
    
    <li class="page-item">
      <a class="page-link" href="#" onclick = "lastpage(${articlePage.pages })">Next</a>
    </li>
  </ul>
</nav>




<script type="text/javascript">
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
		$("#workcontent").load("/user/articles?pageNum="+page);
	}
	
	function fristpage(){
		$("#workcontent").load("/user/articles?pageNum=1");
	}
	
	function lastpage(page){
		$("#workcontent").load("/user/articles?pageNum="+page);
	}
	
	function upd(id){
		$("#workcontent").load("/user/updateArticle?id="+id);
	}
	
</script>




    
    
    
    