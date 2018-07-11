<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../header.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-fileinput/css/fileinput.min.css" />
<div class="container">
	<ol class="breadcrumb">
	  	<li><a href="${pageContext.request.contextPath }">首页</a></li>
	  	<li><a href="${pageContext.request.contextPath }/teacher/center/info">个人中心</a></li>
	  	<li class="active">课程章节管理</li>
	</ol>
	<div class="row">
		<div class="col-md-3">
			<div class="list-group">
				<a href="${pageContext.request.contextPath }/teacher/center/info" class="list-group-item">个人资料<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/offer_course" class="list-group-item">我发布的课程<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/manage_course" class="list-group-item">课程管理<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/teacher/center/lesson" class="list-group-item active">课程章节管理<span class="fa fa-chevron-circle-right pull-right"></span></a>
			</div>
		</div>
		<div class="col-md-9">
			<div class="col-md-10">
				<form action="${pageContext.request.contextPath }/teacher/lesson/list" method="post" class="form-horizontal" id="video_upload_form" enctype="multipart/form-data">
					<div class="form-group">
						<div class="col-md-8">
							<select id="courseId" name="courseId" class="form-control">
								<option value="0" >--所属课程--</option>
								<c:forEach var="course" items="${user.courses }">
									<option value="${course.courseId }">${course.courseName }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-4">
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-search"></span>&nbsp;查询
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-default" data-toggle="modal" data-target="#saveLessonDialog">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;添加章节<font color="red">${tip }</font>
				</button>
			</div>
			<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-list-alt"></span>&nbsp;课程章节
					</h3>
				</div>		
			<table class="table table-hover">
				<tr>
					<th>ID</th>
					<th>章节名</th>
					<th>发布时间</th>
					<th>课程名</th>
					<th>操作</th>
				</tr>
				<c:forEach var="lesson" items="${requestScope.currentCourse.lessons }">
				<tr>
					<td>${lesson.lessonId }</td>
					<td><a href="${pageContext.request.contextPath }/course/lesson/${lesson.lessonId}" target="_blank">${lesson.lessonName }</a></td>
					<td><fmt:formatDate value="${lesson.releaseTime }" dateStyle="long" timeStyle="short" type="both"></fmt:formatDate></td>
					<td>${lesson.course.courseName }</td>
					<td>
						<a href="javascript:void(0)" title="修改课程信息" data-toggle="modal" data-target="#editLessonDialog${lesson.lessonId }"><span class="fa fa-pencil-square-o"></span></a>
						<a href="javascript:void(0)" title="删除课程" id="remove${lesson.lessonId }"><span class="fa fa-trash-o"></span></a>
						<script type="text/javascript">
							$(document).ready(function(){
								bootbox.setDefaults({
									locale: 'zh_CN'
								});
								$("#remove${lesson.lessonId}").click(function(){
									bootbox.confirm({			
										title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
										message:"确定要删除选中的记录？",
										callback:function(confirm){
											if(confirm == true) {
												window.location.href = "${pageContext.request.contextPath }/teacher/lesson/remove/${lesson.lessonId }";
											} else {
												
											}
										}
									}); 
								});
							});
						</script>
						<div class="modal fade" id="editLessonDialog${lesson.lessonId }" tabindex="1" role="dialog">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
										 	<span aria-hidden="true">&times;</span>
										 </button>
										 <h4 class="modal-title">编辑章节信息</h4>
									</div>
									<div class="modal-body">
										<form action="${pageContext.request.contextPath }/teacher/lesson/update" method="post" class="form-horizontal" enctype="multipart/form-data" id="updateLessonInfo${lesson.lessonId }">
											<input type="hidden" name="lessonId" value="${lesson.lessonId }">
											<div class="form-group">
												<label class="col-md-2 control-label">章节名：</label>
												<div class="col-md-10">
													<input type="text" maxlength="32" class="form-control" name="lessonName" value="${lesson.lessonName}" placeholder="请输入章节名" id="Lesson${lesson.lessonId }"/>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">视频：</label>
												<div class="col-md-10">				
													<input type="file" name="video" class="video" multiple/>
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
													<span id="updateLoading${lesson.lessonId }"></span>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<script type="text/javascript">
							$(document).ready(function(){
								$("#updateLessonInfo${lesson.lessonId }").submit(function(){
									$("#updateLoading${lesson.lessonId }").html("<img src='${pageContext.request.contextPath }/static/images/loading.gif'>");
								});
							});
						</script>
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
</div>
</div>
<div class="modal fade" id="saveLessonDialog" tabindex="1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				 	<span aria-hidden="true">&times;</span>
				 </button>
				 <h4 class="modal-title">添加章节</h4>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath }/teacher/lesson/save" method="post" class="form-horizontal" enctype="multipart/form-data" id="saveLessonInfo">
					<input type="hidden" name="courseId" value="${currentCourse.courseId }">
					<div class="form-group">
						<label class="col-md-2 control-label">章节名：</label>
						<div class="col-md-10">
							<input type="text" maxlength="32" class="form-control" name="lessonName" value="${lesson.lessonName}" placeholder="请输入章节名" id="Lesson${lesson.lessonId }"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">视频：</label>
						<div class="col-md-10">				
							<input type="file" name="video" class="video" multiple/>
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
							<span id="saveLoading"></span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/locales/zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootbox/bootbox.min.js"></script>
<script type="text/javascript">
	$(function () {
	    var oVideoInput = new VideoInput();
	    oVideoInput.Init("video");
	});
	$(document).ready(function(){
		$("#saveLessonInfo").submit(function(){
			$("#saveLoading").html("<img src='${pageContext.request.contextPath }/static/images/loading.gif'>");
		});
	});
</script>
<jsp:include page="../footer.jsp"></jsp:include>