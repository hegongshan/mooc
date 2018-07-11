function $util_validateSuccess(selector) {
	if($(selector+"Tip").html()) {
		$(selector+"Tip").empty();
	}
	$(selector+"Div").attr("class","form-group has-success has-feedback");
	$(selector+"Tip").html("<span class='glyphicon glyphicon-ok form-control-feedback' aria-hidden='true'></span></font>");
}
function $util_validateError(selector) {
	if($(selector+"Tip").html()) {
		$(selector+"Tip").empty();
	}
	$(selector+"Div").attr("class","form-group has-error has-feedback");
	$(selector+"Tip").append("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span></font>");
}
function $util_validateText(selector) {
	$(selector).blur(function(){
		if($.trim($(selector).val()) == "") {
			$util_validateError(selector);
			return true;
		} else {
			$util_validateSuccess(selector);
			return false;
		}
	});	
}
function $util_validatePassword(selector) {
	$util_validateText(selector);
	$(selector).blur(function(){
		if($(selector+"Again").val() == $(selector).val()) {
			$util_validateSuccess(selector+"Again");
			$(selector+"AgainTip").append("<font color='#3c763d'>两次输入一致</font>");
			return true;
		} else {
			$util_validateError(selector+"Again");
			$(selector+"AgainTip").append("<font color='#a94442'>两次输入不一致</font>");
			return false;
		}
	});
	$(selector+"Again").blur(function(){
		if($(selector+"Again").val() == $(selector).val()) {
			$util_validateSuccess(selector+"Again");
			$(selector+"AgainTip").append("<font color='#3c763d'>两次输入一致</font>");
			return true;
		} else {
			$util_validateError(selector+"Again");
			$(selector+"AgainTip").append("<font color='#a94442'>两次输入不一致</font>");
			return false;
		}
	});
}
//初始化fileinput
var VideoInput = function () {
	var oFile = new Object();
	//初始化fileinput控件（第一次初始化）
	oFile.Init = function(ctrlName) {
		var control = $('.' + ctrlName);
		//初始化上传控件的样式
		control.fileinput({
			language: 'zh', //设置语言
			/*uploadUrl: uploadUrl, //上传的地址
*/			uploadAsync: true,
			allowedFileExtensions: ['mp4','wmv'],//接收的文件后缀
			showUpload: false, //是否显示上传按钮
			showCaption: true,//是否显示标题
			showPreview: true,/*
			previewClass: "input",
			mainClass: "input",*/
			browseClass: "btn btn-primary", //按钮样式	 
			dropZoneEnabled: false,//是否显示拖拽区域
			//minImageWidth: 50, //图片的最小宽度
			//minImageHeight: 50,//图片的最小高度
			//maxImageWidth: 1000,//图片的最大宽度
			//maxImageHeight: 1000,//图片的最大高度
			maxFileSize: 204800,//单位为kb，如果为0表示不限制文件大小
			//minFileCount: 0,
			maxFileCount: 1, //表示允许同时上传的最大文件个数
			enctype: "multipart/form-data",
			validateInitialCount:true,
			msgFilesTooMany: "选择上传的视频数量({n}) 超过允许的最大数值{m}！",
		}).on("fileerror",function(event,data,msg) {
			
		});
	}
	return oFile;
};
var ImageInput = function () {
	var oFile = new Object();
	//初始化fileinput控件（第一次初始化）
	oFile.Init = function(ctrlName) {
		var control = $('#' + ctrlName);
		//初始化上传控件的样式
		control.fileinput({
			language: 'zh', //设置语言
			/*uploadUrl: uploadUrl, //上传的地址*/
			uploadAsync: true,
			allowedPreviewTypes: ['image'],
			allowedFileExtensions: ['jpg','png','gif','ico'],//接收的文件后缀
			showUpload: false, //是否显示上传按钮
			showCaption: true,//是否显示标题
			showPreview: true,
			/*mainClass: "input",
			previewClass: "input",*/
			browseClass: "btn btn-primary", //按钮样式	 
			dropZoneEnabled: true,//是否显示拖拽区域
			//minImageWidth: 50, //图片的最小宽度
			//minImageHeight: 50,//图片的最小高度
			//maxImageWidth: 1000,//图片的最大宽度
			//maxImageHeight: 1000,//图片的最大高度
			maxFileSize: 10240,//单位为kb，如果为0表示不限制文件大小
			//minFileCount: 0,
			maxFileCount: 10, //表示允许同时上传的最大文件个数
			enctype: 'multipart/form-data',
			validateInitialCount:true,
			previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
			msgFilesTooMany: "选择上传的图片数量({n}) 超过允许的最大数值{m}！",
		});
	}
	return oFile;
};
var ImageInputByClass = function () {
	var oFile = new Object();
	//初始化fileinput控件（第一次初始化）
	oFile.Init = function(ctrlName) {
		var control = $("." + ctrlName);
		//初始化上传控件的样式
		control.fileinput({
			language: 'zh', //设置语言
			/*uploadUrl: uploadUrl, //上传的地址*/
			uploadAsync: true,
			allowedPreviewTypes: ['image'],
			allowedFileExtensions: ['jpg','png','gif','ico'],//接收的文件后缀
			showUpload: false, //是否显示上传按钮
			showCaption: true,//是否显示标题
			showPreview: true,
			/*mainClass: "input",
			previewClass: "input",*/
			browseClass: "btn btn-primary", //按钮样式	 
			dropZoneEnabled: true,//是否显示拖拽区域
			//minImageWidth: 50, //图片的最小宽度
			//minImageHeight: 50,//图片的最小高度
			//maxImageWidth: 1000,//图片的最大宽度
			//maxImageHeight: 1000,//图片的最大高度
			maxFileSize: 10240,//单位为kb，如果为0表示不限制文件大小
			//minFileCount: 0,
			maxFileCount: 10, //表示允许同时上传的最大文件个数
			enctype: 'multipart/form-data',
			validateInitialCount:true,
			previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
			msgFilesTooMany: "选择上传的图片数量({n}) 超过允许的最大数值{m}！",
		});
	}
	return oFile;
};
var ResourceInput = function () {
	var oFile = new Object();
	//初始化fileinput控件（第一次初始化）
	oFile.Init = function(ctrlName) {
		var control = $('#' + ctrlName);
		//初始化上传控件的样式
		control.fileinput({
			language: 'zh', //设置语言
			/*uploadUrl: uploadUrl, //上传的地址*/
			uploadAsync: true,
			//allowedPreviewTypes: ['image'],
			allowedFileExtensions: ['rar','zip'],//接收的文件后缀
			showUpload: false, //是否显示上传按钮
			showCaption: true,//是否显示标题
			showPreview: false,
			/*mainClass: "input",
			previewClass: "input",*/
			browseClass: "btn btn-primary", //按钮样式	 
			dropZoneEnabled: true,//是否显示拖拽区域
			//minImageWidth: 50, //图片的最小宽度
			//minImageHeight: 50,//图片的最小高度
			//maxImageWidth: 1000,//图片的最大宽度
			//maxImageHeight: 1000,//图片的最大高度
			maxFileSize: 10240,//单位为kb，如果为0表示不限制文件大小
			//minFileCount: 0,
			maxFileCount: 1, //表示允许同时上传的最大文件个数
			enctype: 'multipart/form-data',
			validateInitialCount:true,
			previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
			msgFilesTooMany: "选择上传的课件数量({n}) 超过允许的最大数值{m}！",
		});
	}
	return oFile;
};