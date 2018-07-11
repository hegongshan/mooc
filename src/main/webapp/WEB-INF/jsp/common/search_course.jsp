<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container">
	<ol class="breadcrumb">
	  	<li><a href="${pageContext.request.contextPath }/index">主页</a></li>
	  	<li class="active">课程搜索</li>
	</ol>
	<div class="row">
		<c:choose>
			<c:when test="${!empty courseList}">
				<div class="col-md-12">
					<div class="alert alert-info">
						<span class="fa fa-hand-o-down"></span>共有${fn:length(courseList)}个包含<font color="red">${keyword }</font>的课程
					</div>
				</div>
				<c:forEach var="course" items="${courseList }">
					<div class="col-md-3">
						<div class="thumbnail">
							<a href="${pageContext.request.contextPath }/course/detail/${course.courseId}">
								<img src="${course.coverImagePath }" class="thumbnail-plugin-img" alt="课程封面" id="tip${course.courseId }"/>
							</a>
							<div class="caption thumbnail-plugin-caption text-center">
								<h4><a href="${pageContext.request.contextPath }/course/detail/${course.courseId}">${course.courseName }</a></h4>
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
			</c:when>
			<c:otherwise>
				<div class="alert alert-warning">没有找到包含<font color="red">${keyword }</font>的课程=_=</div>
			</c:otherwise>
		</c:choose>
	</div>
	
</div>
<jsp:include page="../footer.jsp"></jsp:include>