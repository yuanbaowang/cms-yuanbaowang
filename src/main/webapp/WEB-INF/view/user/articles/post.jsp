<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel = "stylesheet" href = "/bootstrap-4.3.1/css/bootstrap.css">
<script type="text/javascript" src = "/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src = "/js/jquery.validate.js"></script>
<script type="text/javascript" src = "/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src= "/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src = "/js/localization/messages_zh.js" ></script>
	
    
<form id = "articleform">
  <div class="form-group">
    <label for="title">标题</label>
    <input type="email" class="form-control" name="title" id="title" placeholder="文章标题">
  </div>
  
<div class="form-group">
    <label for="channel">栏目</label>
    <select class="form-control" name="channelId" id="channel_id">
      <option value = "0">请选择</option>
      <c:forEach items="${channelList }" var="list">
      	<option value = "${list.id }">${list.name }</option>
      </c:forEach>
      
    </select>
  </div>
  
  <div class="form-group">
    <label for="category">分类</label>
    <select class="form-control" name = "categoryId" id="category_id">
    </select>
  </div> 
  
   <div class="form-group">
    <label for="file">文章图片</label>
    <input type="file" class="form-control-file" id="file" name = "file">
  </div>
  
  <div class="form-group">
    <label for="content1">文章内容</label>
    <textarea class="form-control" id="content1" name = "content" rows="20" cols="100" style="height: 330px"></textarea>
  </div>
  <div class="form-group">
 	 <input type="button" class="btn btn-primary" onclick="readTex()" value = "发布文章">
  </div>
</form>

<script type="text/javascript">
	//预加载函数 初始化副文本编辑器
	$(document).ready(function(){
		KindEditor.ready(function(K) {
			window.editor1 = K.create("#content1", {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				uploadJson:'/file/uploads.do',
				fileManagerJson:'/file/manager',
				//uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				//fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
		
	});

 	$("#channel_id").change(function(){
		 $.ajax({
			url:'/user/getCategoris',
			type:'post',
			data:{cid:$("#channel_id").val()},
			dataType:'json',
			success:function(arr){
				$("#category_id").empty();
				for ( var i in arr) {
					$("#category_id").append("<option value = '"+arr[i].id+"'>"+arr[i].name+"</option>");
				}
			}
		}); 
	}); 
	
	
	 function readTex(){
		alert(editor1.html());
		var formData = new FormData($("#articleform")[0]);
		formData.set("content",editor1.html());

		    $.ajax({
			url:'/user/postArticle',
			type:'post',
			data:formData,
			//告诉jQuery 不要处理发送的数据
			processData:false,
			//告诉jQuery 不要去设置content-type 的请求头
			contentType:false,
			dataType:'json',
			success:function(bol){
				if(bol){
					alert("发布成功！");
					$("#workcontent").load("/user/articles");
					
				}else{
					alert("发布失败！");
				}

			}
		});   
		
	} 
	
</script>
