<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../header.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-fileinput/css/fileinput.min.css" />

<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/register.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/locales/zh.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript">
	$(function () {
	    var oImageInput = new ImageInput();
	    oImageInput.Init("image");
	}); 
</script>
<div class="container">
	<ol class="breadcrumb">
	  	<li><a href="${pageContext.request.contextPath }">首页</a></li>
	  	<li><a href="${pageContext.request.contextPath }/teacher/center/info">个人中心</a></li>
	  	<li class="active">个人资料</li>
	</ol>
	<div class="row">
		<div class="col-md-3">
			<div class="list-group">
				<a class="list-group-item active">个人资料<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/offer_course" class="list-group-item">我发布的课程<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/manage_course" class="list-group-item">课程管理<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/lesson" class="list-group-item">课程章节管理<span class="fa fa-chevron-circle-right pull-right"></span></a>
			</div>
		</div>
		<div class="col-md-9">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">个人信息</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-3">
						<div class="col-md-12">
							<c:choose>
							<c:when test="${user.imageSrc != null }">
								<img src="${user.imageSrc }" alt="" class="img-circle img-circle-plugin"/>
							</c:when>
							<c:otherwise>
								<img src="${pageContext.request.contextPath }/static/images/default_image.jpg" alt="" class="img-circle img-circle-plugin"/>
							</c:otherwise>
							</c:choose>
						</div>
						<div class="col-md-12">
							<p class="text-center">
								<span class="fa fa-pencil-square-o fa-2x" data-toggle="modal" data-target="#updateImageDialog"></span>
							</p>
						</div>
						<div class="col-md-12 text-center">
							<p class="text-center">
								修改密码<span class="fa fa-pencil-square-o" data-toggle="modal" data-target="#updatePasswordDialog"></span>
							</p>
						</div>
					</div>
					<div class="col-md-9">						
						<form action="${pageContext.request.contextPath }/teacher/update_info" method="post" class="form-horizontal" role="form">
							<input type="hidden" value="${user.teacherId }" name="teacherId"/>
							<div class="form-group">
								<label for="" class="col-md-2 control-label">昵称:</label>
								<div class="col-md-10">
									<input type="text" value="${user.nickname }" name="nickname" class="form-control" placeholder="昵称"/>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-md-2 control-label">性别:</label>
								<div class="col-md-10">
									<c:choose>
										<c:when test="${user.sex == 0 }">
											<label for="" class="radio-inline"><input type="radio" name="sex" value="0" checked/>男</label>
											<label for="" class="radio-inline"><input type="radio" name="sex" value="1"/>女</label>
										</c:when>
										<c:otherwise>
											<label for="" class="radio-inline"><input type="radio" name="sex" value="0"/>男</label>
											<label for="" class="radio-inline"><input type="radio" name="sex" value="1" checked/>女</label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-md-2 control-label">生日:</label>
								<div class="col-md-10">
									<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
										<input type="text" size="10" value='<fmt:formatDate value="${user.birthday }" pattern='yyyy-MM-dd'/>' id="datetimepicker" name="birthday" class="form-control" readonly/>
									    <span class="input-group-addon">
									    	<span class="glyphicon glyphicon-remove"></span>
									    </span>
										<span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							
							<div class="form-group"> 
								<label for="inputAddress" class="col-md-2 control-label">地址:</label>
								<div class="col-md-10">
									<input type="text" value="${user.address }" name="address" class="form-control" placeholder="地址"/>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-md-2 control-label">邮箱</label>
								<div class="col-md-10">
									<input type="text" value="${user.email }" name="email" class="form-control" placeholder="个人邮箱"  disabled/>
								</div>
							</div>
							<div class="form-group">
								<label for="inputProfile" class="col-md-2 control-label">个人简介:</label>
								<div class="col-md-10">
									<textarea name="profile" id="" cols="30" rows="5" class="form-control">${user.profile }</textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-offset-2 col-md-10">
									<button class="btn btn-primary"><span class="fa fa-save">&nbsp;保存</span></button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="updateImageDialog" tabindex="1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				 	<span aria-hidden="true">&times;</span>
				 </button>
				 <h4 class="modal-title">修改头像</h4>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath }/teacher/update_info" method="post" class="form-horizontal" enctype="multipart/form-data">
					<input type="hidden" value="${user.teacherId }" name="teacherId"/>
					<div class="form-group">
						<label class="col-md-2 control-label">头像：</label>
						<div class="col-md-10">				
							<input type="file" class="form-control" id="image" name="image" multiple/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-2 col-md-10">
							<button type="submit" class="btn btn-primary">
								<span class="fa fa-save"></span>&nbsp;保存
							</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">
								<span class="fa fa-close"></span>&nbsp;关闭
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="updatePasswordDialog" tabindex="1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				 	<span aria-hidden="true">&times;</span>
				 </button>
				 <h4 class="modal-title">修改密码</h4>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath }/teacher/updatepwd" method="post" class="form-horizontal" enctype="multipart/form-data">
					<div class="form-group" id="passwordDiv">
						<label class="col-md-3 control-label">新密码：</label>
						<div class="col-md-9">				
							<input type="password" class="form-control" id="password" name="password" placeholder="请输入新密码"/>
							<span id="passwordTip"></span>
						</div>
					</div>
					<div class="form-group" id="passwordAgainDiv">
						<label class="col-md-3 control-label">再次输入：</label>
						<div class="col-md-9">				
							<input type="password" class="form-control" id="passwordAgain" placeholder="请输入新密码"/>
							<span id="passwordAgainTip"></span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<button type="submit" class="btn btn-primary">
								<span class="fa fa-save"></span>&nbsp;保存
							</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">
								<span class="fa fa-close"></span>&nbsp;关闭
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(".form_date").datetimepicker({language:"zh-CN",autoclose:true,minView:"month",startDate:"1950-01-01"});
	$(".form_date").datetimepicker();
</script>
<jsp:include page="../footer.jsp"></jsp:include>