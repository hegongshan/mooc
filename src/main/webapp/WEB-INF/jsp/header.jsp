<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>爱慕课网</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/static/images/hgs.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/reset.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/mooc.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">	
	var contextPath = "${pageContext.request.contextPath }";
    $(function (){
    	$("#img-tips").popover();
    });
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/search.js"></script>
</head>
<body class="bootstrap-body">
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
				<span class="sr-only"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
			</button>
			<a href="javascript:void(0)" class="navbar-brand">MOOC</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath }/index"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;主页</a></li>
				<li><a href="${pageContext.request.contextPath }/course/all"><span class="glyphicon glyphicon-book" aria-hidden="true"></span>&nbsp;课程分类</a></li>
			</ul>
			<form action="${pageContext.request.contextPath }/search/course" method="post" class="navbar-form navbar-left" role="search" id="searchForm">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-btn">
							<select id="searchCourseOrTeacher" name="search" class="form-control">
								<option value="search_courseName" id="searchCourseName" selected>课程</option>
								<option value="search_teacherName" id="searchTeacherName">教师</option>
							</select>
						</span>
						<input type="text" id="keyword" name="keyword" class="form-control" placeholder="搜索课程" size="32" required/>
						<span class="input-group-btn">
							<button type="submit" class="btn btn-default">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</div>	
			</form>	
			<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${!empty sessionScope.user}">
					<li class="dropdown">
						<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							<img src="${sessionScope.user.imageSrc != null ? sessionScope.user.imageSrc : '/mooc/static/images/default_image.jpg'}" alt="头像" class="img-circle img-circle-min"/>
	                		${sessionScope.user.nickname }<span class="caret"></span>
	                	</a>
	                	<ul class="dropdown-menu">
	                		<c:choose>
	                			<c:when test="${sessionScope.role == \"student\" }">
	                				<li><a href="${pageContext.request.contextPath }/student/center/info"><span class="fa fa-user-o">&nbsp;个人中心</span></a></li>
			                		<li role="separator" class="divider"></li>
			                		<li><a href="${pageContext.request.contextPath }/student/logout"><span class="fa fa-arrow-circle-right">&nbsp;退出</span></a></li>
	                			</c:when>
	                			<c:when test="${sessionScope.role == \"teacher\" }">
	                				<li><a href="${pageContext.request.contextPath }/teacher/center/info"><span class="fa fa-user-o">&nbsp;个人中心</span></a></li>
			                		<li role="separator" class="divider"></li>
			                		<li><a href="${pageContext.request.contextPath }/teacher/logout"><span class="fa fa-arrow-circle-right">&nbsp;退出</span></a></li>
	                			</c:when>
	                		</c:choose>
	                	</ul>
	               	</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="${pageContext.request.contextPath }/user/login" role="button" aria-haspopup="true" aria-expanded="false">登录</a>						
	               	</li>
	               	<li class="dropdown">
						<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">注册</a>
						
						<ul class="dropdown-menu">
	                		<li><a href="${pageContext.request.contextPath }/student/register">学生注册</a></li>
	                		<li role="separator" class="divider"></li>
	                		<li><a href="${pageContext.request.contextPath }/teacher/register">老师注册</a></li>
	                	</ul>						
	               	</li>
	               	<li>
	               		<a href="javascript:void(0)" id="img-tips" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="游客请登录">
	               			<img src="${pageContext.request.contextPath }/static/images/default_image.jpg" alt="头像" class="img-circle img-circle-min"/>
	               		</a>
	               	</li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
	</div>
</nav>