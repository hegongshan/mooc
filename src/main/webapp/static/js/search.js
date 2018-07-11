$(document).ready(function(){
	$("#searchForm").bind("change",function(){
		if($("#searchCourseOrTeacher option:selected").val() == "search_courseName") {
			$(this).attr("action",contextPath+"/search/course");
			$("#keyword").attr("placeholder","搜索课程");
		}
		if($("#searchCourseOrTeacher option:selected").val() == "search_teacherName") {
			$(this).attr("action",contextPath+"/search/teacher");
			$("#keyword").attr("placeholder","搜索教师");
		}
	});
});