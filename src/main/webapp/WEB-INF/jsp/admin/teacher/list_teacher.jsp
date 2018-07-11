<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<ol class="breadcrumb">
  	<li><a href="${pageContext.request.contextPath }/admin/index">主页</a></li>
  	<li class="active">教师列表</li>
</ol>
<table class="table table-striped table-bordered table-condensed table-responsive text-center">
	<tr>
		<th class="info text-center">ID</th>
		<th class="info text-center">用户名</th>
		<th class="info text-center">姓名</th>
		<th class="info text-center">性别</th>
		<th class="info text-center">擅长领域</th>
		<th class="info text-center">邮箱</th>
		<th class="info text-center">生日</th>
		<th class="info text-center">地址</th>
		<th class="info text-center">个人简介</th>
		<th class="info text-center">注册时间</th>
		<th class="info text-center">注册状态</th>
		<th class="info text-center">操作</th>
	</tr>
	
	<c:set var="index" value="0" scope="request"></c:set>
	<c:forEach var="teacher" items="${teachers }">
		<tr>
			<c:set var="index" value="${index+1 }" scope="request"></c:set>
			<td>
				<c:out value="${index }"></c:out>
			</td>
			<td>${teacher.username }</td>
			<td>${teacher.realname }</td>
			<td>${teacher.sex == 0 ? '男'  : '女' }</td>
			<td>${teacher.field }</td>
			<td>${teacher.email }</td>
			<td><fmt:formatDate dateStyle="long" value="${teacher.birthday }"></fmt:formatDate></td>
			<td>${teacher.address }</td>
			<td><textarea rows="3" cols="25">${teacher.profile }</textarea></td>
			<td><fmt:formatDate type="both" dateStyle="long" timeStyle="short" value="${teacher.registerTime }"></fmt:formatDate></td>
			<td>${teacher.status == 1 ? "<span class='btn btn-success btn-xs'>已激活</span>":"<span class='btn btn-warning btn-xs'>待激活</span>"}</td>
			<td>
				<c:if test="${teacher.status == 1 }">
					<a href="${pageContext.request.contextPath }/admin/teacher/add_to_blacklist?teacherId=${teacher.teacherId }" class="btn btn-danger btn-xs" role="button">
						<span class="glyphicon glyphicon-trash"></span>&nbsp;封禁
					</a>
				</c:if>				
			</td>
		</tr>
	</c:forEach>
</table>