<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-md-3">
			<ul class="list-group">
				<c:forEach var="courseType" items="${courseTypeList }">
					<li class="list-group-item">
						<a href="${pageContext.request.contextPath }/courseType/${courseType.courseTypeId }" class="courseType">
							${courseType.courseTypeName }<span class="fa fa-chevron-circle-right pull-right"></span>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>		
		<div class="col-md-9">
			<div class="carousel slide" id="carousel-generic">				
				<ol class="carousel-indicators">
					<c:forEach var="i" begin="0" end="${fn:length(applicationScope.carouselList) - 1}">
						<c:choose>
							<c:when test="${i == 0 }">
								<li data-target="#carousel-generic" data-slide-to="0" class="active"></li>
							</c:when>
							<c:otherwise>
								<li data-target="#carousel-generic" data-slide-to="i"></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ol>
				<div class="carousel-inner" role="listbox">
					<c:set var="index" value="0" scope="request"></c:set>
					<c:forEach var="carousel" items="${applicationScope.carouselList }">
						<c:set var="index" value="${index+1 }" scope="request"></c:set>
						<c:choose>
							<c:when test="${index == 1 }">
								<div class="item active">
									<a href="${pageContext.request.contextPath }/course/detail/${carousel.course.courseId }">
										<img src="${carousel.course.coverImagePath }" alt=""/>
									</a>
									 <div class="carousel-caption">${carousel.course.courseName }</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="item">
									<a href="${pageContext.request.contextPath }/course/detail/${carousel.course.courseId }">
										<img src="${carousel.course.coverImagePath }" alt=""/>
									</a>
									<div class="carousel-caption">${carousel.course.courseName }</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<!-- 切换图片 -->
				<a href="#carousel-generic" class="carousel-control left" role="button" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">previouse</span>
				</a>
				<a href="#carousel-generic" class="carousel-control right" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">next</span>
				</a>				
			</div>			
		</div>
	</div>
</div>
<div class="container" style="margin-top:30px;">
<c:forEach var="courseType" items="${applicationScope.courseTypeList }">
	<div class="panel">
		<div class="panel-heading">
			<div class="panel-title">
				${courseType.courseTypeName }					
			</div>
			<div class="panel-body">
				<c:forEach var="course" items="${courseList }">
					<c:if test="${course.courseType.courseTypeId == courseType.courseTypeId }">
						<div class="col-md-3">
							<div class="thumbnail">
								<a href="${pageContext.request.contextPath }/course/detail/${course.courseId }">
									<img src="${course.coverImagePath }" class="thumbnail-plugin-img" alt="课程封面图片" id="tip${course.courseId }"/>
								</a>
								<div class="caption thumbnail-plugin-caption text-center">
									<h4><a href="${pageContext.request.contextPath }/course/detail/${course.courseId }">${course.courseName }</a></h4>
								</div>
								<span class="btn btn-block" style="background:#f5f5f5;">学习人数：${course.studentNumber }</span>
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
					</c:if>
				</c:forEach>
				<div class="pull-right">
					<a href="${pageContext.request.contextPath }/courseType/${courseType.courseTypeId}">
					<span class="glyphicon glyphicon-hand-right">&nbsp;更多</span>
				</a>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
	
</div>
<jsp:include page="../footer.jsp"></jsp:include>