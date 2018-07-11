<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.hegongshan.com/jsp/jstl/util" prefix="util" %>
<jsp:include page="../header.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/login.js"></script>
<div class="container-fluid">
	<form action="javascript:void(0)" method="post" class="form-signin" id="loginForm" style="background:white;box-shadow:0 0;">
		<h2 class="form-signin-heading text-center">用户登录</h2>
		<div class="form-group" id="usernameDiv">
			<div class="col-md-12">
				<div class="input-group">
					<span class="input-group-addon form-addon">
						<span class="fa fa-user"></span>
					</span>
					<c:choose>
						<c:when test="${!empty requestScope.errorUser}">
							<input type="text" class="form-control" placeholder="请输入用户名" id="username" name="username" value="${errorUser.username}"/>
						</c:when>
						<c:when test="${!empty cookie.COM_HEGONGSHAN_IMOOC_USERNAME.value }">
							<input type="text" class="form-control" placeholder="请输入用户名" id="username" name="username" value="${cookie.COM_HEGONGSHAN_IMOOC_USERNAME.value }">
						</c:when>
						<c:otherwise>
							<input type="text" class="form-control" placeholder="请输入用户名" id="username" name="username" >
						</c:otherwise>
					</c:choose>
					<span id="usernameTip"></span>
				</div>
			</div>
		</div>	
		<div class="form-group" id="passwordDiv">
			<div class="col-md-12">
				<div class="input-group">
					<span class="form-addon input-group-addon">
						<span class="fa fa-lock"></span>
					</span>
					<c:choose>
						<c:when test="${!empty requestScope.errorUser}">
							<input type="password" class="form-control" placeholder="请输入密码" id="password" name="password" value="${errorUser.password}"/>
						</c:when>
						<c:when test="${!empty cookie.COM_HEGONGSHAN_IMOOC_USERNAME.value }">
							<input type="password" class="form-control" placeholder="请输入密码" id="password" name="password" value="${util:decode(cookie.COM_HEGONGSHAN_IMOOC_PASSWORD.value) }">
						</c:when>
						<c:otherwise>
							<input type="password" class="form-control" placeholder="请输入密码" id="password" name="password">
						</c:otherwise>
					</c:choose>
					<span id="passwordTip"></span>
				</div>
			</div>
		</div>		
		<div class="form-group">
			<div class="col-md-12">
				<div class="radio-inline">
					<input type="radio" class="radio" id="role_student" name="role" value="1"/>学生
				</div>
				<div class="radio-inline">
					<input type="radio" class="radio" id="role_teacher" name="role" value="2"/>教师
				</div>
			</div>
		</div>	
		<div class="form-group">
			<div class="col-md-12">
				<div class="checkbox">
					<label>
					   <input type="checkbox" name="rememberMe" value="remember-me">&nbsp;一周内免登陆
					</label>
					<a href="${pageContext.request.contextPath }/user/resetpwd" class="pull-right">忘记密码？</a>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" class="btn btn-primary btn-block">登录</button>
				<font color="red">${errorMsg }</font>
			</div>
		</div>	
	</form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>