<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/locales/zh.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-fileinput/css/fileinput.min.css" />
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
	  	<li class="active">我发布的课程</li>
	</ol>
	<div class="row">
		<div class="col-md-3">
			<div class="list-group">
				<a href="${pageContext.request.contextPath }/teacher/center/info" class="list-group-item">个人资料<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/offer_course" class="list-group-item active">我发布的课程<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/manage_course" class="list-group-item">课程管理<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/lesson" class="list-group-item">课程章节管理<span class="fa fa-chevron-circle-right pull-right"></span></a>
			</div>
		</div>
		<div class="col-md-9">
			<div class="panel panel-default" style="border:1px white solid">
				<div class="panel-heading">
					<div class="panel-title">
						<h3>我发布的课程
							<a href="javascript:void(0)" class="btn btn-primary pull-right" data-toggle="modal" data-target="#addCourseDialog">
								<span class="fa fa-plus"></span>&nbsp;添加课程
							</a>
						</h3>					
					</div>
				</div>				
				<div class="panel-body">
					<c:forEach var="course" items="${courseList }">
					<div class="col-md-3">
						<div class="thumbnail">
							<a href="${pageContext.request.contextPath }/course/detail/${course.courseId }">
								<img src="${course.coverImagePath }" class="thumbnail-plugin-img" alt="课程封面" />
							</a>
							<div class="caption thumbnail-plugin-caption">
								<h4><a href="${pageContext.request.contextPath }/course/detail/${course.courseId }">${course.courseName }</a></h4>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="addCourseDialog" tabindex="1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				 	<span aria-hidden="true">&times;</span>
				 </button>
				 <h4 class="modal-title">添加课程</h4>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath }/teacher/add_course" method="post" class="form-horizontal" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-md-2 control-label">课程名：</label>
						<div class="col-md-10">
							<input type="text" maxlength="32" class="form-control" name="courseName" placeholder="请输入课程名" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">类别：</label>
						<div class="col-md-10">
							<select name="courseTypeId" id="" class="form-control">
								<c:forEach var="courseType" items="${courseTypeList }">
									<option value="${courseType.courseTypeId }">${courseType.courseTypeName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">封面图：</label>
						<div class="col-md-10">				
							<input type="file" class="form-control" id="image" name="image" multiple/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">简介：</label>
						<div class="col-md-10">
							<textarea name="description" id="" rows="4" class="form-control"></textarea>
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
<jsp:include page="../footer.jsp"></jsp:include>