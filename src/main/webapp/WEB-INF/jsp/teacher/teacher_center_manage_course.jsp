<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootbox/bootbox.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-fileinput/css/fileinput.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/locales/zh.js"></script>
<script type="text/javascript">
	$(function () {
	    var oVideoInput = new VideoInput();
	    oVideoInput.Init("video");
	});
	$(function () {
	    var oResourceInput = new ResourceInput();
	    oResourceInput.Init("courseResource");
	});
	$(function () {
	    var oImageInput = new ImageInputByClass();
	    oImageInput.Init("image");
	});
</script>
<script type="text/javascript">
/* $(document).ready(function(){
	$("#video_upload_form").submit(function(){
		var formData = new FormData($("#video_upload_form")[0]);
		formData.append("video",$("#video").prop("files")[0]);
		json = $.ajax({
			url:"${pageContext.request.contextPath }/teacher/upload",
			data:formData,
			type:"POST",
			dataType:"text",
			contentType:false,
			processData:false,
			beforeSend:function(){
				$("#upload_message").html("<img src='${pageContext.request.contextPath }/static/images/loading.jpg'>");
			},
			success:function(data){
				$("#upload_message").html(eval("("+data")"));
			}})
		return false;
	});
}); */
$(document).ready(function(){
	$("#resouce_upload_form").submit(function(){
		$("#resouce_upload_message").html("<img src='${pageContext.request.contextPath }/static/images/loading.gif'>");
	});
});
</script>
<div class="container">
	<ol class="breadcrumb">
	  	<li><a href="${pageContext.request.contextPath }">首页</a></li>
	  	<li><a href="${pageContext.request.contextPath }/teacher/center/info">个人中心</a></li>
	  	<li class="active">课程管理</li>
	</ol>
	<div class="row">
		<div class="col-md-3">
			<div class="list-group">
				<a href="${pageContext.request.contextPath }/teacher/center/info" class="list-group-item">个人资料<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/offer_course" class="list-group-item">我发布的课程<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/manage_course" class="list-group-item active">课程管理<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/lesson " class="list-group-item">课程章节管理<span class="fa fa-chevron-circle-right pull-right"></span></a>
			</div>
		</div>
		<div class="col-md-9">
		<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<a href="#courseList">课程列表<span class="fa fa-chevron-down pull-right"></span></a>
						<font color="red">${errorMsg }</font>
					</h3>
				</div>
				<div class="panel-body" id="courseList">
					<table class="table table-hover">
						<tr>
							<th>ID</th>
							<th>课程名</th>
							<th>课程描述</th>
							<th>发布时间</th>
							<th>章节数</th>
							<th>课程类型</th>
							<th>操作</th>
						</tr>
						<c:forEach var="course" items="${courseList }">
						<tr>
							<td>${course.courseId }</td>
							<td><a href="${pageContext.request.contextPath }/course/detail/${course.courseId}">${course.courseName }</a></td>
							<td>${course.description }</td>
							<td><fmt:formatDate value="${course.releaseTime }" dateStyle="long" timeStyle="short" type="both"></fmt:formatDate></td>
							<td>${fn:length(course.lessons) }</td>
							<td>${course.courseType.courseTypeName }</td>
							<td>
								<a href="javascript:void(0)" title="修改课程信息" data-toggle="modal" data-target="#editCourseDialog${course.courseId }"><span class="fa fa-pencil-square-o"></span></a>
								<script type="text/javascript">
									$(document).ready(function(){
										bootbox.setDefaults({
											locale: 'zh_CN'
										});
										$("#remove${course.courseId}").click(function(){
											bootbox.confirm({			
												title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
												message:"确定要删除选中的记录？",
												callback:function(confirm){
													if(confirm == true) {
														window.location.href = "${pageContext.request.contextPath }/teacher/remove/${course.courseId }";
													} else {
														
													}
												}
											}); 
										});
									});
								</script>
								<a href="javascript:void(0)" title="删除课程" id="remove${course.courseId }"><span class="fa fa-trash-o"></span></a>
								<div class="modal fade" id="editCourseDialog${course.courseId }" tabindex="1" role="dialog">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
												 	<span aria-hidden="true">&times;</span>
												 </button>
												 <h4 class="modal-title">编辑课程信息</h4>
											</div>
											<div class="modal-body">
												<form action="${pageContext.request.contextPath }/teacher/course/edit" method="post" class="form-horizontal" enctype="multipart/form-data" id="updateCourseInfo">
													<input type="hidden" name="courseId" value="${course.courseId }">
													<div class="form-group">
														<label class="col-md-2 control-label">课程名：</label>
														<div class="col-md-10">
															<input type="text" maxlength="32" class="form-control" name="courseName" value="${course.courseName}" placeholder="请输入课程名" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-2 control-label">类型：</label>
														<div class="col-md-10">
															<select name="courseTypeId" id="" class="form-control">
																<c:forEach var="courseType" items="${courseTypeList }">
																	<c:choose>
																		<c:when test="${courseType.courseTypeId != course.courseType.courseTypeId }">
																			<option value="${courseType.courseTypeId }" selected>${courseType.courseTypeName }</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${courseType.courseTypeId }">${courseType.courseTypeName }</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-2 control-label">封面图：</label>
														<div class="col-md-10">				
															<input type="file" class="form-control" name="image" class="image" multiple/>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-2 control-label">简介：</label>
														<div class="col-md-10">
															<textarea name="description" id="" rows="4" class="form-control">${course.description }</textarea>
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
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;课程资源
					</h3>
				</div>
				<div class="panel-body">
					<form action="${pageContext.request.contextPath }/teacher/upload/course/resource" method="post" class="form-horizontal" enctype="multipart/form-data" id="resouce_upload_form">
						<div class="form-group">
							<label class="col-md-2 control-label">所属课程：</label>
							<div class="col-md-10">
								<select id="courseId" name="courseId" id="" class="form-control">
									<c:forEach var="course" items="${courseList }">
										<option value="${course.courseId }">${course.courseName }</option>
									</c:forEach>
								</select>
							</div>
						</div>						
						<div class="form-group">
							<label class="col-md-2 control-label">课程文件：</label>
							<div class="col-md-10">				
								<input type="file" class="form-control" id="courseResource" name="courseResource"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2 col-md-10">
								<button type="submit" class="btn btn-primary" id="video_upload_btn">
									<span class="fa fa-save"></span>&nbsp;保存
								</button>
								<span id="resouce_upload_message"></span>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>