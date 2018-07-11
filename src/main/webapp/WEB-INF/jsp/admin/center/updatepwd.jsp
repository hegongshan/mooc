<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/register.js"></script>
<ol class="breadcrumb">
	<li><a href="">主页</a></li>
	<li class="active"><a href="">修改密码</a></li>
</ol>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改密码</h3>
	</div>
	<div class="panel-body">
		<form action="${pageContext.request.contextPath }/admin/updatepwd" method="post" class="form-horizontal">
			<div class="form-group" id="passwordDiv">
				<label for="" class="col-md-2 control-label">新密码：</label>
				<div class="col-md-10">
					<input type="password" id="password" name="password" class="form-control" required/>
					<span id="passwordTip"></span>
				</div>
			</div>
			<div class="form-group" id="passwordAgainDiv">
				<label for="" class="col-md-2 control-label">再次确认：</label>
				<div class="col-md-10">
					<input type="password" id="passwordAgain" name="passwordAgain" class="form-control" required/>
					<span  id="passwordAgainTip"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-10">
					<button type="submit" class="btn btn-primary"><span class="fa fa-save"></span>&nbsp;确认</button>
				</div>
			</div>
		</form>
	</div>
</div>