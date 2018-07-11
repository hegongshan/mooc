<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/locales/zh.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-fileinput/css/fileinput.min.css" />
<script type="text/javascript">
	$(function () {
	    var oImageInput = new ImageInput();
	    oImageInput.Init("image", "${pageContext.request.contextPath }/admin/manage_course/add");
	}); 
</script>
<ol class="breadcrumb">
  	<li><a href="${pageContext.request.contextPath }/admin/index">主页</a></li>
  	<li class="active">个人信息</li>
</ol>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title"><span class="fa fa-user-o"></span>&nbsp;个人信息</h3>
	</div>
	<div class="panel-body">
		<div class="col-md-1">
			<div>
				<img src="${currentAdmin.imageSrc }" alt="头像"  class="img-circle img-circle-plugin"/>
			</div>
		</div>
		<div class="col-md-11">
			<form action="${pageContext.request.contextPath }/admin/info" method="post" enctype="multipart/form-data" class="form-horizontal">
				<div class="form-group">
					<label for="" class="col-md-3 control-label">头像：</label>
					<div class="col-md-9">
						<input type="file" class="input form-control" id="image" name="image" placeholder="昵称，不超过32个字符"/>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-md-3 control-label">个人昵称：</label>
					<div class="col-md-9">
						<input type="text" class="form-control" name="nickname" value="${currentAdmin.nickname }" placeholder="昵称，不超过32个字符"/>
					</div>
				</div>		
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<button type="submit" class="btn btn-primary"><span class="fa fa-save"></span>保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>