<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<ol class="breadcrumb">
  	<li><a href="${pageContext.request.contextPath }/admin/index">主页</a></li>
  	<li class="active">学生黑名单</li>
</ol>
<c:choose>
<c:when test="${fn:length(students) > 0}">
	<table class="table table-striped table-bordered table-condensed text-center" style="padding-right:15px;">
		<tr>
			<th class="info text-center">ID</th>
			<th class="info text-center">用户名</th>
			<th class="info text-center">昵称</th>
			<th class="info text-center">注册时间</th>
			<th class="info text-center">操作</th>
		</tr>
		
		<c:set var="index" value="0" scope="request"></c:set>
		<c:forEach var="student" items="${students}">
			<tr>
				<c:set var="index" value="${index+1 }" scope="request"></c:set>
				<td><c:out value="${index }"></c:out></td>
				<td>${student.username }</td>
				<td>${student.nickname }</td>
				<td><fmt:formatDate type="both" dateStyle="long" timeStyle="short" value="${student.registerTime }"></fmt:formatDate></td>
				<td>
					<a href="${pageContext.request.contextPath }/admin/student/remove_from_blacklist?studentId=${student.studentId}" class="btn btn-danger btn-xs">
						<span class="glyphicon glyphicon-trash"></span>&nbsp;解禁
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:when>
<c:otherwise>
	<div class="alert alert-info text-center">
		尚未有黑名单成员
	</div>
</c:otherwise>
</c:choose>