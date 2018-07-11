<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$("#studentOrTeacher").bind("change",function(){
		if($("#studentOrTeacher option:selected").val() == "1") {
			$("#resetPwdForm").attr("action",contextPath+"/student/resetpwd");
		}
		if($("#studentOrTeacher option:selected").val() == "2") {
			$("#resetPwdForm").attr("action",contextPath+"/teacher/resetpwd");
		}
	});
});
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/login.css" />
<div class="container-fluid">
	<form action="${pageContext.request.contextPath }/student/resetpwd" method="post" class="form-signin" id="resetPwdForm" style="background:white;box-shadow:0 0;">
		<h2 class="form-signin-heading text-center">忘记密码</h2>
		<div class="form-group">
			<div class="col-md-12">
				<div class="input-group">
					<span class="input-group-addon form-addon">
						<span class="fa fa-user"></span>
					</span>						
					<input type="text" class="form-control" placeholder="请输入用户名" name="username" value="${user.username }"/>
				</div>
			</div>
		</div>	
		<div class="form-group">
			<div class="col-md-12">
				<div class="input-group">
					<span class="input-group-addon form-addon">
						<span class="fa fa-id-card-o" style="width:10px;height:14px;"></span>
					</span>
					<select name="role" id="studentOrTeacher" class="form-control">
						<option value="1">学生</option>
						<option value="2">教师</option>
					</select>
				</div>
			</div>
		</div>	
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" class="btn btn-primary btn-block">提交</button>
				<font class="text-success">${tip }</font>
			</div>
		</div>	
	</form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>