function updatePicInfo(imgtype, imgpath) {
	$.ajax({
		type : "GET",
		url : '../upload/update.htm',
		data: {
			imgtype: imgtype,
			imgpath: imgpath
		},
		dataType: "json",
		contentType : "application/json",
        	         
		success : function(result) {
			//var result = JSON.parse(''+data+'');
			//if('0000' == result.code){
			//location.reload();
			//}
        }
	});
}

function uploaderErr(index){
	$("#uptext" + index).show();
	$("#upimg" + index).hide().attr("load","0");
}	
function uploaderSuccess(index,response){
	$("#uptext" + index).hide();
	$("#upimg" + index).show().attr("src","../images/" + response).attr("load","1");
	updatePicInfo("uploader" + index, response);
}				
function uploaderErrHandler(err,index){
	uploaderErr(index)
	var code = err.code;
	if(code == '-600'){
		alert("文件尺寸过大（大小不超过1M）。");
	}else{
		alert("上传文件发生错误。");
	}
	//alert(err.code);
	//alert(err.file);
	//alert(err.message);
}
var uploader1 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader01', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '1mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			up.start();
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				uploaderSuccess("01",response);
			}else{
				uploaderErr("01");
			}
		},
		Error: function(up, err) {
			uploaderErrHandler(err,"01");
		}
	}
});
	
var uploader2 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader02', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '1mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			up.start();
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				uploaderSuccess("02",response);
			}else{
				uploaderErr("02");
			}
		},
		Error: function(up, err) {
			uploaderErrHandler(err,"02");
		}
	}
});

var uploader3 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader03', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '1mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			up.start();
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				uploaderSuccess("03",response);
			}else{
				uploaderErr("03");
			}
		},
		Error: function(up, err) {
			uploaderErrHandler(err,"03");
		}
	}
});

var uploader4 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader04', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '1mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			up.start();
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				uploaderSuccess("04",response);
			}else{
				uploaderErr("04");
			}
		},
		Error: function(up, err) {
			uploaderErrHandler(err,"04");
		}
	}
});

var uploader5 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader05', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '1mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			up.start();
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				uploaderSuccess("05",response);
			}else{
				uploaderErr("05");
			}
		},
		Error: function(up, err) {
			uploaderErrHandler(err,"05");
		}
	}
});

var uploader6 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader06', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '1mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			up.start();
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				uploaderSuccess("06",response);
			}else{
				uploaderErr("06");
			}
		},
		Error: function(up, err) {
			uploaderErrHandler(err,"06");
		}
	}
});

var uploader7 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader07', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '1mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			up.start();
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				uploaderSuccess("07",response);
			}else{
				uploaderErr("07");
			}
		},
		Error: function(up, err) {
			uploaderErrHandler(err,"07");
		}
	}
});

var uploader8 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader08', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '1mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			up.start();
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				uploaderSuccess("08",response);
			}else{
				uploaderErr("08");
			}
		},
		Error: function(up, err) {
			uploaderErrHandler(err,"08");
		}
	}
});

var uploader9 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader09', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '1mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			up.start();
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				uploaderSuccess("09",response);
			}else{
				uploaderErr("09");
			}
		},
		Error: function(up, err) {
			uploaderErrHandler(err,"09");
		}
	}
});

uploader1.init();
uploader2.init();
uploader3.init();
uploader4.init();
uploader5.init();
uploader6.init();
uploader7.init();
uploader8.init();
uploader9.init();