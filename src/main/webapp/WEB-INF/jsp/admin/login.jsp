<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱慕课网后台管理系统</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/static/images/hgs.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/login.js"></script>
<script>
	window.onload = function(){
		document.body.style.backgroundImage = "url('${pageContext.request.contextPath}/static/images/bg_image.jpg')";
		document.body.style.backgroundSize = "cover";
		document.body.onresize = function() {
			var _width = document.body.clientWidth;			
			if(_width >= 768) {
				document.body.style.backgroundSize = "cover";
			} else {
				document.body.style.backgroundSize = "768px 768px";
			}
		};
	};
</script>
</head>
<body>
<div class="container-fluid">
	<form action="${pageContext.request.contextPath }/admin/login" method="post" class="form-horizontal form-signin">
		<h2 class="form-signin-heading text-center">爱慕课网后台管理</h2>
		<div class="form-group">
			<div class="col-md-12">
				<div class="input-group">
					<span class="input-group-addon form-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>						
					<input type="text" class="form-control" placeholder="请输入用户名" name="username" value="${requestScope.resultAdmin.username }"/>
				</div>
			</div>
		</div>				
		<div class="form-group">
			<div class="col-md-12">
				<div class="input-group">
					<span class="form-addon input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="password" class="form-control" placeholder="请输入密码" name="password" value="${requestScope.resultAdmin.password }"/>
				</div>
			</div>
		</div>		
		<div class="form-group">
			<div class="col-md-12">
				<span class="text-danger">${requestScope.errorMsg }</span>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" class="btn btn-primary btn-block">登录</button>
			</div>
		</div>	
	</form>
</div>
</body>
</html>