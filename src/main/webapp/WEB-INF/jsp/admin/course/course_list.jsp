<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-fileinput/js/locales/zh.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-fileinput/css/fileinput.min.css" />
<ol class="breadcrumb">
  	<li><a href="${pageContext.request.contextPath }/admin/index">主页</a></li>
  	<li class="active">课程及章节列表</li>
</ol>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title"><span class="fa fa-list-alt"></span>&nbsp;课程及章节信息</h3>
	</div>
	<table class="table table-striped table-condensed text-center">
		<tr>
			<th class="info text-center">ID</th>
			<th class="info text-center">课程名</th>
			<th class="info text-center">课程类别</th>
			<th class="info text-center">课程描述</th>
			<th class="info text-center">封面图</th>
			<th class="info text-center">发布时间</th>
			<th class="info text-center">章节数</th>
			<th class="info text-center">操作</th>
		</tr>			
		<c:forEach var="course" items="${pager.list }">
			<tr>
				<td>${course.courseId }</td>
				<td><a href="#lessonFor${course.courseId }" data-toggle="collapse">${course.courseName }</a></td>
				<td>${course.courseType.courseTypeName }</td>
				<td><textarea class="form-control" rows="4" cols="20">${course.description }</textarea></td>
				<td><img src="${course.coverImagePath }" alt="" class="img-circle" style="width:50px;height:50px;"/></td>
				<td><fmt:formatDate dateStyle="long" value="${course.releaseTime }"></fmt:formatDate></td>
				<td>${fn:length(course.lessons) }</td>
				<td>
					<a href="javascript:void(0)" title="设为轮播课程" data-toggle="modal" data-target="#configCarousel${course.courseId }">
						<span class="glyphicon glyphicon-cog"></span>
					</a>
					<%-- <a href="javascript:void(0)" title="删除该课程" role="button" id="removeCourse${course.courseId }">
						<span class="glyphicon glyphicon-trash"></span>
					</a> --%>
					<script type="text/javascript">
						$(document).ready(function(){
							bootbox.setDefaults({
								locale: 'zh_CN'
							});
							$("#removeCourse${course.courseId }").click(function(){
								bootbox.confirm({			
									title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
									message:"确定要删除选中的记录？",
									callback:function(confirm){
										if(confirm == true) {
											window.location.href = "${pageContext.request.contextPath }/admin/course/remove/${courseType.courseTypeId }";
										} else {
											
										}
									}
								}); 
							});
						});
					</script>
					<div class="modal fade" id="configCarousel${course.courseId }" tabindex="1" role="dialog" style="text-align:left;">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
									 	<span aria-hidden="true">&times;</span>
									 </button>
									 <h4 class="modal-title">设置轮播优先级</h4>
								</div>
								<div class="modal-body">
									<form action="${pageContext.request.contextPath }/admin/carousel/save" method="post" class="form-horizontal">
										<input type="hidden" name="courseId" value="${course.courseId }">
										<div class="form-group">
											<label class="col-md-2 control-label">优先级：</label>
											<div class="col-md-10">
												<select name="priority" id="" class="form-control">
													<option value="1">轮播课程1</option>
													<option value="2">轮播课程2</option>
													<option value="3">轮播课程3</option>
													<option value="4">轮播课程4</option>
												</select>
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
			<tr class="collapse" id="lessonFor${course.courseId }">
				<td colspan="8">
					<c:choose>
						<c:when test="${!empty course.lessons }">
							<table class="table table-hover text-center">
								<tr class="active">
									<th class="text-center">ID</th>
									<th class="text-center">章节名</th>
									<th class="text-center">视频地址</th>
									<th class="text-center">发布日期</th>
									<th class="text-center">操作</th>
								</tr>
								<c:forEach var="lesson" items="${course.lessons }">
									<tr>
										<td>${lesson.lessonId }</td>
										<td>${lesson.lessonName }</td>
										<td><a href="${lesson.url }">${lesson.url }</a></td>
										<td><fmt:formatDate value="${lesson.releaseTime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
										<td>
											<a href="javascript:void(0)" title="删除该章节" role="button" id="removeLesson${lesson.lessonId}">
												<span class="glyphicon glyphicon-trash"></span>
											</a>
											<script type="text/javascript">
												$(document).ready(function(){
													bootbox.setDefaults({
														locale: 'zh_CN'
													});
													$("#removeLesson${lesson.lessonId }").click(function(){
														bootbox.confirm({			
															title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
															message:"确定要删除选中的记录？",
															callback:function(confirm){
																if(confirm == true) {
																	window.location.href = "${pageContext.request.contextPath }/admin/lesson/remove/${lesson.lessonId }";
																} else {
																	
																}
															}
														}); 
													});
												});
											</script>
										</td>
									</tr>
								</c:forEach>
							</table>
						</c:when>
						<c:otherwise>
							该课程尚未有章节信息
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<nav>
	<ul class="pager">
		<c:choose>
			<c:when test="${pager.currentPage == 1 }">
				<li class="disabled"><a href="#">首页</a></li>
				<li class="disabled"><a href="#">上一页</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }/admin/course/list?page=1">首页</a></li>
				<li><a href="${pageContext.request.contextPath }/admin/course/list?page=${pager.currentPage - 1}">上一页</a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach begin="1" end="${pager.totalPages}" var="pageNo">
			<c:choose>
				<c:when test="${pager.currentPage == pageNo }">
					<li class="active"><a href="javascript:void(0)">${pageNo}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/admin/course/list?page=${pageNo}">${pageNo}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${pager.currentPage == pager.totalPages }">
				<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
				<li class="disabled"><a href="javascript:void(0)">尾页</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }/admin/course/list?page=${pager.currentPage + 1}">下一页</a></li>
				<li><a href="${pageContext.request.contextPath }/admin/course/list?page=${pager.totalPages}">尾页</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>