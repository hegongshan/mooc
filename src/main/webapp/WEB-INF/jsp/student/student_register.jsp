<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/register.js"></script>
<div class="container">
	<form action="${pageContext.request.contextPath }/student/register" method="post" class="form-horizontal" id="studentRegisterForm">
		<fieldset>
			<legend style="text-align:center;">注册</legend>
			<div class="form-group" id="usernameDiv">
				<label for="inputEmail" class="col-md-4 control-label">用户名：</label>
				<div class="col-md-5"> 
					<input type="email" class="form-control" placeholder="使用邮箱，作为登录账户" id="username" name="username" value="${requestScope.student.username }" required="required"/>
					<span id="usernameTip"></span>
				</div>
			</div>
			<div class="form-group" id="passwordDiv">
				<label for="inputPassword" class="col-md-4 control-label">密码：</label>
				<div class="col-md-5">					
					<input type="password" class="form-control" maxlength="32"  placeholder="6-32位密码，区分大小写" id="password" name="password" value="${requestScope.student.password }" required="required"/>
					<span id="passwordTip"></span>
				</div>
			</div>
			<div class="form-group" id="passwordAgainDiv">
				<label for="inputPasswordAgain" class="col-md-4 control-label">确认密码：</label>
				<div class="col-md-5">					
					<input type="password" class="form-control" placeholder="再次输入密码" id="passwordAgain" value="${requestScope.student.password }" required="required"/>
					<span id="passwordAgainTip"></span>
				</div>
			</div>
			<div class="form-group" id="nicknameDiv">
				<label for="inputNickname" class="col-md-4 control-label">昵称：</label>
				<div class="col-md-5">
					<input type="text" class="form-control" placeholder="个人昵称" id="nickname" name="nickname" value="${requestScope.student.nickname }" required="required"/>
					<span id="nicknameTip"></span>
				</div>
			</div>
			<div class="form-group" id="uAuthCodeDiv">
				<label for="inputAuthCode" class="col-md-4 control-label">验证码：</label>
				<div class="col-md-5">
					<input type="text" class="form-control" placeholder="请输入验证码" id="uAuthCode" name="uAuthCode" value="${requestScope.uAuthCode }" required="required"/>
					<span id="uAuthCodeTip"></span>
					<img src="${pageContext.request.contextPath }/authCode" alt="验证码" id="authCodeImg" />
					<a href="javascript:void(o)" id="refreshAuthCode">看不清？</a>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-4 col-md-5">
					<button type="submit" class="btn btn-primary btn-block">注册</button>
				</div>
				<div class="col-md-3">
					<font color="red">${requestScope.errorMsg }</font>
				</div>
			</div>
		</fieldset>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#username").blur(function(){
			$.ajax({
				type:"GET",
				async:true,
				url:"${pageContext.request.contextPath}/student/checkUsername",
				data:"username=" + $(this).val(),
				datatype:"text",
				success:function(data){
					if(data == "isExist") {
						$("#usernameDiv").attr("class","form-group has-error has-feedback");
						$("#usernameTip").html("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span></font>");
						$("#usernameTip").append("<span class='text-danger'>用户名已存在！</span>");
					}
				}
			});
		});
	});
</script>
<jsp:include page="../footer.jsp"></jsp:include>