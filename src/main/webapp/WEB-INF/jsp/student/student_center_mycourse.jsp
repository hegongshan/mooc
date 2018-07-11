<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container">
	<ol class="breadcrumb">
	  	<li><a href="${pageContext.request.contextPath }/index">首页</a></li>
	  	<li><a href="${pageContext.request.contextPath }/student/center/info">个人中心</a></li>
	  	<li class="active">我的课程</li>
	</ol>
	<div class="row">
		<div class="col-md-3">
			<div class="list-group">
				<a href="${pageContext.request.contextPath }/student/center/info" class="list-group-item">个人资料<span class="fa fa-chevron-circle-right pull-right"></span></a>
				<a href="${pageContext.request.contextPath }/student/center/mycourse" class="list-group-item active">我的课程<span class="fa Example of chevron-circle-right fa-chevron-circle-right pull-right"></span></a>
			</div>
		</div>
		<div class="col-md-9">
			<div class="panel">
				<div class="panel-heading">
					<div class="panel-title">
						<h3>我的课程</h3>					
					</div>
				</div>
				
				<div class="panel-body">
					<c:choose>
						<c:when test="${!empty myCourseList }">
							<c:forEach var="course" items="${myCourseList }">
								<div class="col-md-3">
									<div class="thumbnail">
										<a href="${pageContext.request.contextPath }/course/detail/${course.courseId}">
											<img src="${course.coverImagePath }" class="thumbnail-plugin-img" alt="242*200"/>
										</a>
										<div class="caption thumbnail-plugin-caption text-center" style="height:50px;">
											<p>
												<a href="${pageContext.request.contextPath }/course/detail/${course.courseId}">
													${course.description }
												</a>
											</p>		
											
										</div>
										<a href="${pageContext.request.contextPath }/student/remove/${course.courseId}" class="btn btn-block" style="background:#f5f5f5;">放弃学习&nbsp;<span class="fa fa-arrow-circle-right"></span></a>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="well text-center">您暂未学习课程</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>