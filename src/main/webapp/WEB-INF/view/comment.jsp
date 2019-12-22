<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${list.list }" var="l">
	评论：
	<div class = "row">
		内容：${l.content }
		<br>
		作者：${l.username } 发表于 <fmt:formatDate value="${l.created }" pattern="yyyy-MM-dd"/>   
		<br>
	</div>
</c:forEach>
<!-- 分页 -->
	<div class="row justify-content-center" style="margin-top: 20px ">
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		    <li class="page-item">
		    
		      <a class="page-link" aria-label="Previous" href="javascript:gopage(${list.prePage == 0?1:list.prePage})">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <c:forEach begin="1" end="${list.pages }" varStatus="count">
		    	 <li class="page-item ${list.pageNum == count.count?'active':'' }"><a class="page-link" href="javascript:gopage(${count.count })">${count.count }</a></li>
		    </c:forEach>
		    <li class="page-item">
		      <a class="page-link" href="javascript:gopage(${list.nextPage == 0?list.pages:list.nextPage})"  aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		      
		    </li>
		  </ul>
		</nav>
	</div> 
