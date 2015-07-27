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
		
var uploader1 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader1', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '3mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			// showLoader();
			up.start();
			//for(var i = 0, len = files.length; i<len; i++){
    		//	var file_name = files[i].name; //文件名
    		//}
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				// TODO 抽象包装
				$("#upp1").hide();
				$('#upimg1').show().attr('src','../images/' + response);	
				// TODO 结束
				// 更9新图片信息
				updatePicInfo("uploader01", response);
				// hideLoader();
			}else{
				// TODO 抽象包装
				$("#upp1").show();
				$('#upimg1').hidden();
				// TODO 结束
			}
		},
		Error: function(up, err) {
			// TODO 抽象包装
			$("#upp1").show();
			$('#upimg1').hidden();
			// TODO 结束
			//alert(err.code);
			//alert(err.file);
			//alert(err.message);
		}
	}
});
	
var uploader2 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader2', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '3mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			// showLoader();
			up.start();
			//for(var i = 0, len = files.length; i<len; i++){
    		//	var file_name = files[i].name; //文件名
    		//}
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				// TODO 抽象包装
				$("#upp2").hide();
				$('#upimg2').show().attr('src','../images/' + response);	
				// TODO 结束
				// 更9新图片信息
				updatePicInfo("uploader02", response);
				// hideLoader();
			}else{
				// TODO 抽象包装
				$("#upp2").show();
				$('#upimg2').hidden();
				// TODO 结束
			}
		},
		Error: function(up, err) {
			// TODO 抽象包装
			$("#upp2").show();
			$('#upimg2').hidden();
			// TODO 结束
			//alert(err.code);
			//alert(err.file);
			//alert(err.message);
		}
	}
});

var uploader3 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader3', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '3mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			// showLoader();
			up.start();
			//for(var i = 0, len = files.length; i<len; i++){
    		//	var file_name = files[i].name; //文件名
    		//}
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				// TODO 抽象包装
				$("#upp3").hide();
				$('#upimg3').show().attr('src','../images/' + response);	
				// TODO 结束
				// 更9新图片信息
				updatePicInfo("uploader03", response);
				// hideLoader();
			}else{
				// TODO 抽象包装
				$("#upp3").show();
				$('#upimg3').hidden();
				// TODO 结束
			}
		},
		Error: function(up, err) {
			// TODO 抽象包装
			$("#upp3").show();
			$('#upimg3').hidden();
			// TODO 结束
			//alert(err.code);
			//alert(err.file);
			//alert(err.message);
		}
	}
});

var uploader4 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader4', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '3mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			// showLoader();
			up.start();
			//for(var i = 0, len = files.length; i<len; i++){
    		//	var file_name = files[i].name; //文件名
    		//}
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				// TODO 抽象包装
				$("#upp4").hide();
				$('#upimg4').show().attr('src','../images/' + response);	
				// TODO 结束
				// 更9新图片信息
				updatePicInfo("uploader04", response);
				// hideLoader();
			}else{
				// TODO 抽象包装
				$("#upp4").show();
				$('#upimg4').hidden();
				// TODO 结束
			}
		},
		Error: function(up, err) {
			// TODO 抽象包装
			$("#upp4").show();
			$('#upimg4').hidden();
			// TODO 结束
			//alert(err.code);
			//alert(err.file);
			//alert(err.message);
		}
	}
});

var uploader5 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader5', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '3mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			// showLoader();
			up.start();
			//for(var i = 0, len = files.length; i<len; i++){
    		//	var file_name = files[i].name; //文件名
    		//}
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				// TODO 抽象包装
				$("#upp5").hide();
				$('#upimg5').show().attr('src','../images/' + response);	
				// TODO 结束
				// 更9新图片信息
				updatePicInfo("uploader05", response);
				// hideLoader();
			}else{
				// TODO 抽象包装
				$("#upp5").show();
				$('#upimg5').hidden();
				// TODO 结束
			}
		},
		Error: function(up, err) {
			// TODO 抽象包装
			$("#upp5").show();
			$('#upimg5').hidden();
			// TODO 结束
			//alert(err.code);
			//alert(err.file);
			//alert(err.message);
		}
	}
});

var uploader6 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader6', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '3mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			// showLoader();
			up.start();
			//for(var i = 0, len = files.length; i<len; i++){
    		//	var file_name = files[i].name; //文件名
    		//}
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				// TODO 抽象包装
				$("#upp6").hide();
				$('#upimg6').show().attr('src','../images/' + response);	
				// TODO 结束
				// 更9新图片信息
				updatePicInfo("uploader06", response);
				// hideLoader();
			}else{
				// TODO 抽象包装
				$("#upp6").show();
				$('#upimg6').hidden();
				// TODO 结束
			}
		},
		Error: function(up, err) {
			// TODO 抽象包装
			$("#upp6").show();
			$('#upimg6').hidden();
			// TODO 结束
			//alert(err.code);
			//alert(err.file);
			//alert(err.message);
		}
	}
});

var uploader7 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader7', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '3mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			// showLoader();
			up.start();
			//for(var i = 0, len = files.length; i<len; i++){
    		//	var file_name = files[i].name; //文件名
    		//}
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				// TODO 抽象包装
				$("#upp7").hide();
				$('#upimg7').show().attr('src','../images/' + response);	
				// TODO 结束
				// 更9新图片信息
				updatePicInfo("uploader07", response);
				// hideLoader();
			}else{
				// TODO 抽象包装
				$("#upp7").show();
				$('#upimg7').hidden();
				// TODO 结束
			}
		},
		Error: function(up, err) {
			// TODO 抽象包装
			$("#upp7").show();
			$('#upimg7').hidden();
			// TODO 结束
			//alert(err.code);
			//alert(err.file);
			//alert(err.message);
		}
	}
});

var uploader8 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader8', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '3mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			// showLoader();
			up.start();
			//for(var i = 0, len = files.length; i<len; i++){
    		//	var file_name = files[i].name; //文件名
    		//}
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				// TODO 抽象包装
				$("#upp8").hide();
				$('#upimg8').show().attr('src','../images/' + response);	
				// TODO 结束
				// 更9新图片信息
				updatePicInfo("uploader08", response);
				// hideLoader();
			}else{
				// TODO 抽象包装
				$("#upp8").show();
				$('#upimg8').hidden();
				// TODO 结束
			}
		},
		Error: function(up, err) {
			// TODO 抽象包装
			$("#upp8").show();
			$('#upimg8').hidden();
			// TODO 结束
			//alert(err.code);
			//alert(err.file);
			//alert(err.message);
		}
	}
});

var uploader9 = new plupload.Uploader({
	runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
	browse_button : 'uploader9', 
	url : '../upload/upload.htm',
	flash_swf_url : '../static/js/Moxie.swf',
	silverlight_xap_url : '../static/js/Moxie.xap',
	multi_selection: false,
	filters : {
		max_file_size : '3mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,jpeg,bmp,gif,png"}
		]
	},
	init: {
		PostInit: function() {
		},
		FilesAdded: function(up, files) {
			// showLoader();
			up.start();
			//for(var i = 0, len = files.length; i<len; i++){
    		//	var file_name = files[i].name; //文件名
    		//}
		},
		UploadProgress: function(up, file) {
		},
		FileUploaded: function(up,file,responseObject){
			var status = responseObject.status;
			var response = jQuery.parseJSON(responseObject.response);
			if(status == '200'){
				// TODO 抽象包装
				$("#upp9").hide();
				$('#upimg9').show().attr('src','../images/' + response);	
				// TODO 结束
				// 更9新图片信息
				updatePicInfo("uploader09", response);
				// hideLoader();
			}else{
				// TODO 抽象包装
				$("#upp9").show();
				$('#upimg9').hidden();
				// TODO 结束
			}
		},
		Error: function(up, err) {
			// TODO 抽象包装
			$("#upp9").show();
			$('#upimg9').hidden();
			// TODO 结束
			//alert(err.code);
			//alert(err.file);
			//alert(err.message);
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