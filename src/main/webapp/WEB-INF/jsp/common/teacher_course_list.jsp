<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container">
	<ol class="breadcrumb">
	  	<li><a href="${pageContext.request.contextPath }/index">主页</a></li>
	  	<li>教师搜索</li>
	  	<li class="active"><font color="red">${teacher.realname }</font>教师发布的所有课程</li>
	</ol>
	<div class="row">
		<div class="col-md-9">
			<c:forEach var="course" items="${teacher.courses}">
				<div class="col-md-3">
					<div class="thumbnail">
						<a href="${pageContext.request.contextPath }/course/detail/${course.courseId }">
							<img src="${course.coverImagePath }" class="thumbnail-plugin-img" alt="课程封面" id="tip${course.courseId }"/>
						</a>
						<div class="caption thumbnail-plugin-caption text-center">
							<h4>
								<a href="${pageContext.request.contextPath }/course/detail/${course.courseId }">
									${course.courseName }
								</a>
							</h4>
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
		</div>
		<div class="col-md-3">
			<div class="well text-center">
				<c:choose>
					<c:when test="${!empty teacher.imageSrc }">
						<img src="${teacher.imageSrc }"  class="img-circle-plugin img-circle" alt="头像" />
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath }/static/images/default_image.jpg"  class="img-circle-plugin img-circle" alt="头像" />
					</c:otherwise>
				</c:choose>
				<h3>${teacher.realname }<span class="authTeacher">V</span></h3>
				<h5>${teacher.profile }</h5>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>