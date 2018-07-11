<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	window.onload = function(){
		var selectAll = document.getElementById("selectAll");
		var checkbox = document.getElementsByName("commentId");
		var removeBatch = document.getElementById("removeBatch");
		var ids = "";
		selectAll.onclick = function(){
			if(this.checked) {
				for(var i = 0 ; i < checkbox.length ; i++) {
					checkbox[i].checked = true;
				}
			} else {
				for(var i = 0 ; i < checkbox.length ; i++) {
					checkbox[i].checked = false;
				}
			}	
		};
		bootbox.setDefaults({
			locale: 'zh_CN'
		});
		
		removeBatch.onclick = function(){
			for(var i = 0 ; i < checkbox.length ; i++) {
				if(checkbox[i].checked){  
					ids += checkbox[i].value + ",";                
	            }  
			}
			if(ids != "") {
				bootbox.confirm({			
					title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
					message:"确定要删除选中的记录？",
					callback:function(confirm){
						if(confirm == true) {
							window.location.href = "${pageContext.request.contextPath}/admin/comment/remove/batch?commentids=" + ids;
						} else {
							
						}
					}
				});  
			} else {
				bootbox.alert({				
					title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
					message:"请选择要删除的记录",
					buttons:{
						ok:{
							label:"确定",
							className:"btn btn-primary"
						}
					}
				});				
			}
		}
	};
</script>
<ol class="breadcrumb">
  	<li><a href="${pageContext.request.contextPath }/admin/index">主页</a></li>
  	<li class="active">评论管理</li>
</ol>
<div class="alert alert-success">
	<a href="javascript:void(0)" class="btn btn-danger" id="removeBatch">
		<span class="glyphicon glyphicon-trash"></span>&nbsp;批量删除
	</a>
	<font color="red">${errorMsg }</font>
</div>
<c:choose>
	<c:when test="${!empty requestScope.pager.list}">
		<table class="table table-striped table-bordered table-condensed text-center">
			<tr>
				<th class="info text-center"><input type="checkbox" id="selectAll"/></th>
				<th class="info text-center">ID</th>
				<th class="info text-center">评论学生</th>
				<th class="info text-center">评论内容</th>
				<th class="info text-center">被评论课程</th>
				<th class="info text-center">发表时间</th>
				<th class="info text-center">操作</th>
			</tr>			
			<c:forEach var="comment" items="${requestScope.pager.list }">
				<tr>
					<td><input type="checkbox" name="commentId" value="${comment.commentId }"></td>
					<td>
						<c:out value="${comment.commentId }"></c:out>
					</td>
					<td>${!empty comment.student.nickname ? comment.student.nickname : comment.student.username }</td>
					<td>${comment.content }</td>
					<td>${comment.course.courseName }</td>
					<td><fmt:formatDate type="both" dateStyle="long" timeStyle="short" value="${comment.releaseTime }"></fmt:formatDate></td>
					<td>
						<a href="javascript:void(0)" role="button" id="remove${comment.commentId }">
							<span class="glyphicon glyphicon-trash"></span>
						</a>
						<script type="text/javascript">
							$(document).ready(function(){
								bootbox.setDefaults({
									locale: 'zh_CN'
								});
								$("#remove${comment.commentId }").click(function(){
									bootbox.confirm({			
										title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
										message:"确定要删除选中的记录？",
										callback:function(confirm){
											if(confirm == true) {
												window.location.href = "${pageContext.request.contextPath }/admin/comment/remove/${comment.commentId }";
											} else {
												
											}
										}
									}); 
								});
							});
						</script>
					</td>
				</tr>
			</c:forEach>
		</table>
		<nav>
		<ul class="pager">
			<c:choose>
				<c:when test="${pager.currentPage == 1 }">
					<li class="disabled"><a href="#">首页</a></li>
					<li class="disabled"><a href="#">上一页</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/admin/comment/list?page=1">首页</a></li>
					<li><a href="${pageContext.request.contextPath }/admin/comment/list?page=${pager.currentPage - 1}">上一页</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="1" end="${pager.totalPages}" var="pageNo">
				<c:choose>
					<c:when test="${pager.currentPage == pageNo }">
						<li class="active"><a href="javascript:void(0)">${pageNo}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath }/admin/comment/list?page=${pageNo}">${pageNo}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pager.currentPage == pager.totalPages }">
					<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
					<li class="disabled"><a href="javascript:void(0)">尾页</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/admin/comment/list?page=${pager.currentPage + 1}">下一页</a></li>
					<li><a href="${pageContext.request.contextPath }/admin/comment/list?page=${pager.totalPages}">尾页</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info text-center">尚未添加课程类别</div>
	</c:otherwise>
</c:choose>