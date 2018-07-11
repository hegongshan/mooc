<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container">
	<div class="row" >
		<div class="col-md-8">
			<div class="col-md-4 text-center">				
				<img src="${course.coverImagePath }" style="width:200px;height:200px;" class="img-circle" alt="" />
			</div>
			<div class="col-md-8">
				<div class="courseDetail courseName">
					<h1>${course.courseName }</h1>
				</div>
				<div class="courseDetail teacher">
					<h4><span class="fa fa-user-o"></span>&nbsp;讲师：${course.teacher.realname }<span class="authTeacher">V</span></h4>
				</div>
				<c:if test="${ ! isMyCourse }">
					<div class="courseDetail join">
						<a href="${pageContext.request.contextPath }/student/join/${course.courseId}">
							<button class="btn btn-success">立即参加</button>
						</a>
					</div>
				</c:if>
			</div>
			<div class="col-md-12" style="margin-top:20px;">
				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;课程描述</h3>
					</div>
					<div class="panel-body">
						<div class="well">
							${course.description }
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-12">
				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title"><span class="glyphicon glyphicon-th-large"></span>&nbsp;章节列表</h3>
					</div>
					<div class="panel-body">
						<c:choose>
							<c:when test="${!empty lessonList}">
								<table class="table table-hover">
									<c:set var="index" value="0" scope="request"></c:set>
									<c:forEach var="lesson" items="${lessonList }">
										<c:set var="index" value="${index+1 }" scope="request"></c:set>
											<tr>
												<td>
													<a href="${pageContext.request.contextPath }/course/lesson/${lesson.lessonId}"><span class="glyphicon glyphicon-play-circle"></span>&nbsp;${index}.${lesson.lessonName }</a>
												</td>
											</tr>
									</c:forEach>
								</table>
							</c:when>
							<c:otherwise>
								<div class="well text-center">该课程尚未有章节，请等待教师上传章节视频</div>
							</c:otherwise>
						</c:choose>				
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title"><span class="glyphicon glyphicon-comment"></span>&nbsp;评论</h3>
					</div>
					<div class="panel-body">
					<c:choose>
					<c:when test="${!empty course.comments }">
						<table class="table table-hover">
							<c:forEach var="comment" items="${course.comments }">
								<tr>
									<td>
										<c:choose>
											<c:when test="${!empty comment.student.imageSrc }">
												<img src="${comment.student.imageSrc }" class="img-circle img-circle-min">
											</c:when>
											<c:otherwise>
												<img src="${pageContext.request.contextPath }/static/images/default_image.jpg" alt="" />
											</c:otherwise>
										</c:choose>
										&nbsp;${!empty comment.student.nickname ? comment.student.nickname : comment.student.username }：${comment.content } 
										<span class="pull-right">
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${comment.releaseTime }"></fmt:formatDate>
										</span>
									</td>
								</tr>
							</c:forEach>
						</table>
						</c:when>
							<c:otherwise>
								<div class="well text-center">该课程尚未有学生评论</div>
							</c:otherwise>
						</c:choose>				
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="col-md-12">
				<div class="well text-center">
					<img src="${course.teacher.imageSrc }" alt="" class="img-circle-plugin img-circle" />
					<h3>${course.teacher.realname }<span class="authTeacher">V</span></h3>
					<h5>${course.teacher.profile }</h5>
				</div>
			</div>
			<div class="col-md-12">
				<div class="well text-center">
					<h3 style="border-bottom:1px solid black;">课程资源</h3>
					<h5 style="margin-top:5px;"><a href="${course.resourceSrc }">下载课件</a></h5>
				</div>				
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>