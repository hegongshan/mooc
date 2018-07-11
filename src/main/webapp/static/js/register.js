$(document).ready(function(){
	
	$util_validateText("#username");
	$util_validatePassword("#password");
	$util_validateText("#nickname");
	$util_validateText("#realname");
	$util_validateText("#field");	
	
	/**
	 * 设置验证码的刷新
	 */
	$util_validateText("#uAuthCode");
	$("#refreshAuthCode").click(function(){
		$("#authCodeImg").attr("src",contextPath + "/authCode?" + Math.random()); 
	});
});