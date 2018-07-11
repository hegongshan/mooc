<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	window.onload = function(){
		var selectAll = document.getElementById("selectAll");
		var checkbox = document.getElementsByName("typeId");
		var removeBatch = document.getElementById("removeBatch");
		var ids = "";
		selectAll.onclick = function(){
			if(this.checked) {
				for(var i = 0 ; i < checkbox.length ; i++) {
					checkbox[i].checked = true;
				}
			} else {
				for(var i = 0 ; i < checkbox.length ; i++) {
					checkbox[i].checked = false;
				}
			}	
		};
		bootbox.setDefaults({
			locale: 'zh_CN'
		});
		
		removeBatch.onclick = function(){
			for(var i = 0 ; i < checkbox.length ; i++) {
				if(checkbox[i].checked){  
					ids += checkbox[i].value + ",";                
	            }  
			}
			if(ids != "") {
				bootbox.confirm({			
					title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
					message:"确定要删除选中的记录？",
					callback:function(confirm){
						if(confirm == true) {
							window.location.href = "${pageContext.request.contextPath}/admin/courseType/remove/batch?typeIds=" + ids;
						} else {
							
						}
					}
				});  
			} else {
				bootbox.alert({				
					title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
					message:"请选择要删除的记录",
					buttons:{
						ok:{
							label:"确定",
							className:"btn btn-primary"
						}
					}
				});				
			}
		}
	};
</script>
<ol class="breadcrumb">
  	<li><a href="${pageContext.request.contextPath }/admin/index">主页</a></li>
  	<li class="active">课程类别管理</li>
</ol>
<div class="alert alert-success">
	<a href="javascript:void(0)" class="btn btn-danger" id="removeBatch">
		<span class="glyphicon glyphicon-trash"></span>&nbsp;批量删除
	</a>
	<a href="javascript:void(0)" data-toggle="modal" data-target="#addCourseTypeDialog" class="btn btn-primary">
		<span class="glyphicon glyphicon-plus"></span>&nbsp;添加类别
	</a>
	<span class="text-danger">${errorMsg }</span>
</div>
<c:choose>
	<c:when test="${!empty requestScope.courseTypeList}">
		<table class="table table-striped table-bordered table-condensed text-center">
			<tr>
				<th class="info text-center"><input type="checkbox" id="selectAll"/></th>
				<th class="info text-center">ID</th>
				<th class="info text-center">类别名</th>
				<th class="info text-center">创建时间</th>
				<th class="info text-center">操作</th>
			</tr>			
			<c:set var="index" value="0" scope="request"></c:set>
			<c:forEach var="courseType" items="${requestScope.courseTypeList }">
				<tr>
					<td><input type="checkbox" name="typeId" value="${courseType.courseTypeId }"></td>
					<c:set var="index" value="${index+1 }" scope="request"></c:set>
					<td>
						<c:out value="${index }"></c:out>
					</td>
					<td>${courseType.courseTypeName }</td>
					<td><fmt:formatDate type="both" dateStyle="long" timeStyle="short" value="${courseType.releaseTime }"></fmt:formatDate></td>
					<td>
						<a href="javascript:void(0)" class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateCourseType${index }">
							<span class="fa fa-pencil-square-o"></span>&nbsp;编辑
						</a>
						<a href="javascript:void(0)" class="btn btn-danger btn-xs" role="button" id="remove${courseType.courseTypeId }">
							<span class="fa fa-trash-o"></span>&nbsp;删除
						</a>
						<script type="text/javascript">
							$(document).ready(function(){
								bootbox.setDefaults({
									locale: 'zh_CN'
								});
								$("#remove${courseType.courseTypeId }").click(function(){
									bootbox.confirm({			
										title:"<span class='glyphicon glyphicon-info-sign'></span>操作提示",
										message:"确定要删除选中的记录？",
										callback:function(confirm){
											if(confirm == true) {
												window.location.href = "${pageContext.request.contextPath }/admin/courseType/remove/${courseType.courseTypeId }";
											} else {
												
											}
										}
									}); 
								});
							});
						</script>
						<div class="modal fade" id="updateCourseType${index }" tabindex="1" role="dialog" style="text-align:left;">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
										 	<span aria-hidden="true">&times;</span>
										 </button>
										 <h4 class="modal-title">更改课程分类</h4>
									</div>
									<div class="modal-body">
										<form action="${pageContext.request.contextPath }/admin/courseType/update" method="post" class="form-horizontal">
											<input type="hidden" name="courseTypeId" value="${courseType.courseTypeId }">
											<div class="form-group">
												<label class="col-md-2 control-label">类别名：</label>
												<div class="col-md-10">
													<input type="text" maxlength="32" class="form-control" value="${courseType.courseTypeName }" name="courseTypeName" placeholder="请输入课程类型" />
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
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info text-center">尚未添加课程类别</div>
	</c:otherwise>
</c:choose>
<div class="modal fade" id="addCourseTypeDialog" tabindex="1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				 	<span aria-hidden="true">&times;</span>
				 </button>
				 <h4 class="modal-title">添加课程分类</h4>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath }/admin/courseType/save" method="post" class="form-horizontal" id="loginForm">
					<div class="form-group">
						<label class="col-md-2 control-label">类别名：</label>
						<div class="col-md-10">
							<input type="text" maxlength="32" class="form-control" name="courseTypeName" placeholder="请输入课程类型" />
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