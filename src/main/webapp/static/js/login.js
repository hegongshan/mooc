$(document).ready(function(){
	$("#role_student").click(function(){
		$("#loginForm").attr("action",contextPath+"/student/login");
	});
	$("#role_teacher").click(function(){
		$("#loginForm").attr("action",contextPath+"/teacher/login");
	});
	$util_validateText("#username");
	$util_validateText("#password");
});