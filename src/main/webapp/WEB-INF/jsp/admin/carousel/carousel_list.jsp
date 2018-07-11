<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<ol class="breadcrumb">
  	<li><a href="${pageContext.request.contextPath }/admin/index">主页</a></li>
  	<li class="active">轮播管理</li>
</ol>
<c:choose>
	<c:when test="${!empty requestScope.carouselList}">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;轮播列表&nbsp;<font color="red">${errorMsg }</font></h3>
		</div>
		<table class="table table-hover table-bordered table-condensed text-center">
			<tr>
				<th class="info text-center">轮播顺序</th>
				<th class="info text-center">课程名</th>
				<th class="info text-center">封面图</th>
				<th class="info text-center">描述</th>
				<th class="info text-center">操作</th>
			</tr>			
			<c:set var="index" value="0" scope="request"></c:set>
			<c:forEach var="carousel" items="${requestScope.carouselList }">
				<tr>
					<c:set var="index" value="${index+1 }" scope="request"></c:set>
					<td>
						<c:out value="${index }"></c:out>
					</td>
					<td>${carousel.course.courseName }</td>
					<td><img src="${carousel.course.coverImagePath }" alt="封面图片" class="img-circle img-circle-default"/></td>
					<td>
						<textarea rows="4" cols="" class="form-control">${carousel.course.description }</textarea>
					</td>
					<td>
						<a href="javascript:void(0)" id="remove${carousel.priority}">
							<span class="glyphicon glyphicon-trash"></span>
						</a>
						<script type="text/javascript">
							$(document).ready(function(){
								bootbox.setDefaults({
									locale: 'zh_CN'
								});
								$("#remove${carousel.priority}").click(function(){
									bootbox.confirm({			
										title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
										message:"确定要删除选中的记录？",
										callback:function(confirm){
											if(confirm == true) {
												window.location.href = "${pageContext.request.contextPath }/admin/carousel/remove/${carousel.priority }";
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
	</div>
	<div class="panel panel-default">
		<div class="panel-heading"><h3 class="panel-title"><span class="glyphicon glyphicon-eye-open"></span>&nbsp;轮播预览</h3></div>
		<div class="panel-body">
			<div class="col-md-offset-1 col-md-9">
			<div class="carousel slide" id="carousel-generic">				
				<ol class="carousel-indicators">
					<c:forEach var="i" begin="0" end="${fn:length(requestScope.carouselList) - 1}">
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
					<c:forEach var="carousel" items="${requestScope.carouselList }">
						<c:set var="index" value="${index+1 }" scope="request"></c:set>
						<c:choose>
							<c:when test="${index == 1 }">
								<div class="item active">
									<a href="${pageContext.request.contextPath }/course/detail/${carousel.course.courseId }" target="_blank">
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
					<span class="sr-only">previous</span>
				</a>
				<a href="#carousel-generic" class="carousel-control right" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">next</span>
				</a>				
			</div>	
			</div>		
		</div>
	</div>
</c:when>
<c:otherwise>
	<div class="alert alert-info text-center">尚未添加轮播课程,请去课程列表中添加轮播课程</div>
</c:otherwise>
</c:choose>