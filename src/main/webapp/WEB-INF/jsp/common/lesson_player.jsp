<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-9">
			<div class="col-md-12">
				<video width="100%" height="500px" controls loop>
					<source src="${currentLesson.url }" type="video/mp4">
				</video>
			</div>
			<c:if test="${role == \"student\" }">
				<div class="col-md-12">
				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title"><span class="glyphicon glyphicon-comment"></span>&nbsp;评论</h3>
					</div>
					<div class="panel-body">
						<form action="${pageContext.request.contextPath }/student/comment/${currentLesson.course.courseId}" method="post" class="form-horizontal">
							<input type="hidden" name="lessonId" value="${currentLesson.lessonId }"/>
							<div class="col-md-12">
								<textarea name="content" id="" cols="30" rows="5" class="form-control"></textarea>
							</div>
							<div class="col-md-12">
								<button class="btn btn-primary"><span class="fa fa-save"></span>&nbsp;发表</button>
								<font color="red">${errorMsg }</font>
							</div>
						</form>
						
					</div>
				</div>	
			</div>	
			</c:if>			
			<div class="col-md-12">
				<c:choose>
					<c:when test="${!empty currentLesson.course.comments }">
						<div class="panel-heading">
							<h3 class="panel-title"><span class="glyphicon glyphicon-comment"></span>&nbsp;最近评论</h3>
						</div>
						<table class="table table-hover">
							<c:forEach var="comment" items="${currentLesson.course.comments }">
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
		<div class="col-md-3">
			<ul class="list-group">
				<c:forEach var="lesson" items="${lessonList }">
					<c:choose>
						<c:when test="${lesson.lessonId == currentLesson.lessonId }">
							<li class="list-group-item disabled">
								<span class="glyphicon glyphicon-play-circle"></span>&nbsp;${lesson.lessonName }
							</li>
						</c:when>
						<c:otherwise>
							<li class="list-group-item">
								<a href="${pageContext.request.contextPath }/course/lesson/${lesson.lessonId}">
									<span class="glyphicon glyphicon-play-circle"></span>&nbsp;${lesson.lessonName }
								</a>
							</li>
						</c:otherwise>
					</c:choose>	
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>