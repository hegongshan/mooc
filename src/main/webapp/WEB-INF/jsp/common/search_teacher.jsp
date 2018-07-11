<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container">
	<ol class="breadcrumb">
	  	<li><a href="${pageContext.request.contextPath }/index">主页</a></li>
	  	<li class="active">教师搜索</li>
	</ol>
	<c:choose>
		<c:when test="${!empty teacherList}">
			<div class="row">
				<div class="col-md-12">
					<div class="alert alert-info">
						<span class="fa fa-hand-o-down"></span>共有${fn:length(teacherList)}个包含<font color="red">${keyword }</font>的教师
					</div>
				</div>
			</div>
			<c:forEach var="teacher" items="${teacherList }">
				<div class="row" >
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="col-md-3">
								<div class="text-center">
									<c:choose>
										<c:when test="${!empty teacher.imageSrc }">
											<img src="${teacher.imageSrc }"  class="img-circle-plugin img-circle" alt="头像" />
										</c:when>
										<c:otherwise>
											<img src="${pageContext.request.contextPath }/static/images/default_image.jpg"  class="img-circle-plugin img-circle" alt="头像" />
										</c:otherwise>
									</c:choose>
									<h3>${teacher.realname }<span class="authTeacher">V</span></h3>
								</div>
								<h4>擅长领域：${teacher.field }</h4>
								<h5>${teacher.profile }</h5>
							</div>
							<div class="col-md-9">
								<c:forEach var="course" begin="0" end="3" items="${teacher.courses }">
									<div class="col-md-3">
										<div class="thumbnail">
											<a href="${pageContext.request.contextPath }/course/detail/${course.courseId}">
												<img src="${course.coverImagePath }" class="thumbnail-plugin-img" alt="课程封面" id="tip${course.courseId }"/>
											</a>
											<div class="caption thumbnail-plugin-caption text-center">
												<h5><a href="${pageContext.request.contextPath }/course/detail/${course.courseId}">${course.courseName }</a></h5>
											</div>
											<span class="btn btn-block" style="background:#f5f5f5;">学习人数：${course.studentNumber }</span>
										</div>
										<script type="text/javascript">
											$(document).ready(function(){
												var popoverWidth = $(".thumbnail").width();
												$("#tip${course.courseId }").popover({
													container:"body",								
													title:"课程描述",
													content:"<div style='width:" + popoverWidth + "px;'>${course.description}</div>",
													trigger:"hover",
													placement:"bottom",
													html:true
												});
											});
										</script>
									</div>
								</c:forEach>
								<div class="pull-right">
									<a href="${pageContext.request.contextPath }/search/teacher/course/list?teacherId=${teacher.teacherId }">
										<span class="glyphicon glyphicon-hand-right"></span>&nbsp;更多
									</a>
								</div>
							</div>
						</div>
					</div>					
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="alert alert-warning">没有找到包含<font color="red">${keyword }</font>的教师=_=</div>
		</c:otherwise>
	</c:choose>
</div>
<jsp:include page="../footer.jsp"></jsp:include>