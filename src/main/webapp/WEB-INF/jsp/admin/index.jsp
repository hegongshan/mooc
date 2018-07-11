<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱慕课网后台管理系统</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/hgs.ico"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/console.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mooc.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("ul.nav-sidebar>li>a").click(function(){
			$(this).children(".pull-right").toggleClass("glyphicon glyphicon-chevron-down pull-right").toggleClass("glyphicon glyphicon-chevron-up pull-right");
		});
		
		$(".sidebar").css({"height":$(window).height()});
		
		var x = $(".sidebar").offset().top;
		
		$(window).resize(function(){
			var _width = $(window).width();
			if(_width < 768) {
				$(".main").css({"margin-left":"0px"});
			} else {
				$(".main").css({"margin-left":$(".sidebar").width()});
			}
		});
		$(window).scroll(function(){
			var p = $(window).scrollTop(); 
			if($(".sidebar").css("display") == "block" && p > x) {
				$(".sidebar").css({"position":"fixed","top":"50px","left":"0"});
				$(".main").css({"margin-left":"16.7%"});
			}
		});
	});
</script>
</head>
<body>
<!-- 导航栏 -->
<nav class="navbar navbar-inverse navbar-fixed-top" id="nav">
	<div class="container-fluid">		
		<div class="navbar-header" >
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
				<span class="sr-only"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
			</button>
			<a href="javascript:void(0)" class="navbar-brand">爱慕课网后台管理系统</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse">
		<ul class="nav navbar-nav navbar-right" style="padding-right:30px;">
			<li class="dropdown">
				<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					<img src="
					<c:choose>
						<c:when test="${currentAdmin.imageSrc != null }">
							${currentAdmin.imageSrc }
						</c:when>
						<c:otherwise>
							${pageContext.request.contextPath}/static/images/default_image.jpg
						</c:otherwise>
					</c:choose>
					" alt="" class="img-circle img-circle-min"/>
					${currentAdmin.nickname == null ? currentAdmin.username : currentAdmin.nickname }
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="${pageContext.request.contextPath}/admin/info">
							<span class="fa fa-user-o">&nbsp;个人信息</span>
						</a>
					</li>
					<li role="separator" class="divider"></li>
					<li>
						<a href="${pageContext.request.contextPath}/admin/logout">
							<span class="fa fa-arrow-circle-right">&nbsp;注销</span>
						</a>
					</li>
				</ul>
			</li>
	   	</ul>
	   	</div>
	</div>   
</nav>
<!-- 主页面  -->
<div class="container-fluid">	
	<div class="row">
   		<!-- 侧边功能栏 -->
		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
	   			<li><a href="${pageContext.request.contextPath }/admin/index"><span class="glyphicon glyphicon-home"></span>&nbsp;主页</a></li>
	            <li>
	            	<a href="#sub1" data-toggle="collapse">
	            		<span class="glyphicon glyphicon-folder-open"></span>&nbsp;学生管理
	            		<span class="glyphicon glyphicon-chevron-down pull-right"></span>
	            	</a>
					<ul class="nav collapse collapse-list" id="sub1">
						<li>
							<a href="${pageContext.request.contextPath }/admin/student/list">
								<span class="glyphicon glyphicon-list-alt"></span>&nbsp;学生列表
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath }/admin/student/blacklist">
								<span class="fa fa-user-circle-o"></span>&nbsp;学生黑名单
							</a>
						</li>
					</ul>
	            </li>
	            <li>
	            	<a href="#sub2" data-toggle="collapse">
	            		<span class="glyphicon glyphicon-folder-open"></span>&nbsp;教师管理
	            		<span class="glyphicon glyphicon-chevron-down pull-right"></span>
	            	</a>
					<ul class="nav collapse collapse-list" id="sub2">
						<li><a href="${pageContext.request.contextPath }/admin/teacher/list"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;教师列表</a></li>
						<li><a href="${pageContext.request.contextPath }/admin/teacher/blacklist"><span class="fa fa-user-circle-o"></span>&nbsp;教师黑名单</a></li>
					</ul>
	            </li>
	            <li>
	            	<a href="#sub3" data-toggle="collapse">
	            		<span class="glyphicon glyphicon-folder-open"></span>&nbsp;课程类别管理
	            		<span class="glyphicon glyphicon-chevron-down pull-right"></span>
	            	</a>
					<ul class="nav collapse collapse-list" id="sub3">
						<li>
							<a href="${pageContext.request.contextPath }/admin/courseType/list">
								<span class="glyphicon glyphicon-list-alt"></span>&nbsp;课程类别列表
							</a>
						</li>
					</ul>
	            </li>
	            <li>
	            	<a href="#sub4" data-toggle="collapse">
	            		<span class="glyphicon glyphicon-folder-open"></span>&nbsp;课程及章节管理
	            		<span class="glyphicon glyphicon-chevron-down pull-right"></span>
	            	</a>
					<ul class="nav collapse collapse-list" id="sub4">
						<li>
							<a href="${pageContext.request.contextPath }/admin/course/list">
								<span class="glyphicon glyphicon-list-alt"></span>&nbsp;课程及章节列表
							</a>
						</li>
					</ul>
	            </li>
	            <li>
	            	<a href="#sub5" data-toggle="collapse">
	            		<span class="glyphicon glyphicon-folder-open"></span>&nbsp;评论管理
	            		<span class="glyphicon glyphicon-chevron-down pull-right"></span>
	            	</a>
					<ul class="nav collapse collapse-list" id="sub5">
						<li>
							<a href="${pageContext.request.contextPath }/admin/comment/list">
								<span class="glyphicon glyphicon-comment"></span>&nbsp;评论列表
							</a>
						</li>
					</ul>
	            </li>
	            <li>
	            	<a href="#sub6" data-toggle="collapse">
	            		<span class="glyphicon glyphicon-folder-open"></span>&nbsp;轮播管理
	            		<span class="glyphicon glyphicon-chevron-down pull-right"></span>
	            	</a>
					<ul class="nav collapse collapse-list" id="sub6">
						<li>
							<a href="${pageContext.request.contextPath }/admin/carousel/list">
								<span class="glyphicon glyphicon-list-alt"></span>&nbsp;轮播列表
							</a>
						</li>
					</ul>
	            </li>
	            <li>
	            	<a href="#sub7" data-toggle="collapse">
	            		<span class="glyphicon glyphicon-folder-open"></span>&nbsp;个人中心
	            		<span class="glyphicon glyphicon-chevron-down pull-right"></span>
	            	</a>
					<ul class="nav collapse collapse-list" id="sub7">
						<li>
							<a href="${pageContext.request.contextPath }/admin/info">
								<span class="fa fa-user-o"></span>&nbsp;个人信息
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath }/admin/updatepwd">
								<span class="glyphicon glyphicon-edit"></span>&nbsp;修改密码
							</a>
						</li>
					</ul>
	            </li>
	            <li>
	            	<a href="#sub8" data-toggle="collapse">
	            		<span class="glyphicon glyphicon-cog"></span>&nbsp;系统管理
	            		<span class="glyphicon glyphicon-chevron-down pull-right"></span>
	            	</a>
					<ul class="nav collapse collapse-list" id="sub8">
						<li><a href="${pageContext.request.contextPath }/admin/system/refresh"><span class="glyphicon glyphicon-refresh"></span>&nbsp;刷新系统缓存</a></li>
						<!-- <li><a href=""><span class="glyphicon glyphicon-bookmark"></span>&nbsp;操作日志</a></li> -->
					</ul>
	            </li>
	   		</ul>	
		</div>
		<div class="col-sm-9 col-md-10 main">
			<c:choose>
				<c:when test="${mainPage != null }">
					<jsp:include page="${mainPage }"></jsp:include>
				</c:when>
				<c:otherwise>
					<ol class="breadcrumb">
					  	<li><a href="${pageContext.request.contextPath }/admin/index" class="active">主页</a></li>
					</ol>
					<h3 class="alert alert-info text-center">
						欢迎登录爱慕课网后台管理系统
					</h3>
				</c:otherwise>
			</c:choose>
		</div>
	</div>	
</div>
</body>
</html>