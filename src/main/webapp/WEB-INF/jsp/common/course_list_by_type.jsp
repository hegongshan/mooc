<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container">
	<ol class="breadcrumb">
	  	<li><a href="${pageContext.request.contextPath }/index">主页</a></li>
	  	<li><a href="${pageContext.request.contextPath }/course/all">所有课程</a></li>
	  	<li class="active">${activeCourseType.courseTypeName }</li>
	</ol>
	<ul class="list-inline">
		<li>课程类别：</li>
		<c:forEach var="courseType" items="${courseTypeList}">
			<c:choose>
				<c:when test="${activeCourseType.courseTypeId == courseType.courseTypeId }">
					<li class="bg-info"><a href="${pageContext.request.contextPath }/courseType/${courseType.courseTypeId}">${courseType.courseTypeName }</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/courseType/${courseType.courseTypeId}">${courseType.courseTypeName }</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	
	<div class="row">
		<div class="col-md-12">
			<c:forEach var="course" items="${pager.list }">
				<div class="col-md-3">
					<div class="thumbnail">
						<a href="${pageContext.request.contextPath }/course/detail/${course.courseId }" id="tip${course.courseId }">
							<img src="${course.coverImagePath }" class="thumbnail-plugin-img" alt="课程封面图" title="${course.description }"/>
						</a>
						<div class="caption thumbnail-plugin-caption text-center">
							<h4>
								<a href="${pageContext.request.contextPath }/course/detail/${course.courseId }">
									${course.courseName }
								</a>
							</h4>
						</div>
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
	</div>
	<nav>
		<ul class="pager">
			<c:choose>
				<c:when test="${pager.currentPage == 1 }">
					<li class="disabled"><a href="#">首页</a></li>
					<li class="disabled"><a href="#">上一页</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/courseType/${activeCourseType.courseTypeId}?page=1">首页</a></li>
					<li><a href="${pageContext.request.contextPath }/courseType/${activeCourseType.courseTypeId}?page=${pager.currentPage - 1}">上一页</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="1" end="${pager.totalPages}" var="pageNo">
				<c:choose>
					<c:when test="${pager.currentPage == pageNo }">
						<li class="active"><a href="javascript:void(0)">${pageNo}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath }/courseType/${activeCourseType.courseTypeId}?page=${pageNo}">${pageNo}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pager.currentPage == pager.totalPages }">
					<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
					<li class="disabled"><a href="javascript:void(0)">尾页</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/courseType/${activeCourseType.courseTypeId}?page=${pager.currentPage + 1}">下一页</a></li>
					<li><a href="${pageContext.request.contextPath }/courseType/${activeCourseType.courseTypeId}?page=${pager.totalPages}">尾页</a></li>
				</c:otherwise>
			</c:choose>
			
		</ul>
	</nav>
</div>
<jsp:include page="../footer.jsp"></jsp:include>