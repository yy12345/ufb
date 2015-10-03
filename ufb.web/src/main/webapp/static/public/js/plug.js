(function($) {	
	//下拉菜单
	$.fn.selectMenu = function(options) {
		var defaults = {
			refresh: false,
			change: $.noop
		};
		var sets = $.extend(defaults, options || {});
		$(this).each(function(i,d){
			var list = '';var listval = [];
			$(this).find("option").each(function(j,e){
				list += '<li>'+$(this).text()+'</li>';
				listval.push($(this).val());
			});
			if(!sets.refresh && !$(this).next().hasClass("title")){
				$(this).wrap('\
					<div class="selectmenu selectSm">\
					</div>\
				');
				$(this).after('\
					<div class="title">\
						<div class="text"></div>\
						<div class="ico"></div>\
					</div>\
					<ul class="list">'+list+'</ul>\
				');
			}else{
				$(this).parent().find(".list").html(list);
			}
			var thatAll = $('.selectmenu');
			var that = $(this).parent();
			that.css({"z-index":1000-i,"width":parseInt($(this).css("width"))+2,"margin-right":$(this).css("margin-right")});
			that.find(".text").text($(this).val());
			that.find("li").unbind("click").on("click",function(event){
				event.stopPropagation();
				that.find(".text").text($(this).text());
				that.find(".select").val(listval[$(this).index()]);
				sets.change(listval[$(this).index()]);
				that.find(".ico").removeClass("active");
				that.find(".list").hide();
			});
			that.unbind("click").on("click",function(event){
				event.stopPropagation();
				thatAll.find(".list").hide();		
				switch ($(this).find(".ico").hasClass("active")) {
					case true:
						thatAll.find(".ico").removeClass("active");
						$(this).find(".ico").removeClass("active");
						that.find(".list").hide();
						break;
					case false:
						thatAll.find(".ico").removeClass("active");
						$(this).find(".ico").addClass("active");
						that.find(".list").show();
						break;
				}
				if($(document).height()-that.offset().top-that.find(".list").height()<40){
					that.find(".list").css({top:-that.find(".list").height()});
				}else{
					that.find(".list").css({top:that.height()});
				}
			});
			that.find(".list").unbind("mousewheel").mousewheel(function(event, delta) {
				event.stopPropagation();
			});
			$(document).unbind("click").on("click",function(){
				thatAll.find(".ico").removeClass("active");
				thatAll.find(".list").hide();
			})
		});
	}
	$.fn.inputText = function() {
		$(this).each(function() {
			var that = $(this).find('input');
			if (that.val() == "") {
				that.next().show();
			}else{
				that.next().hide();
			}
			that.on('focus',function() {
				$(this).next().hide();
			}).on('blur',function() {
				if ($(this).val() == "") {
					$(this).next().show();
				}else{
					$(this).next().hide();
				}
			});
		});
	}
	$.fn.checkbox = function(options) {
		var defaults = {
			checked: false
		};
		var sets = $.extend(defaults, options || {});
		$(this).each(function(){
			var that = $(this);
			if(options != null){
				if(sets.checked){
					that.addClass('active');
					that.find('input').attr('checked',true);
				}else{
					that.removeClass('active');
					that.find('input').attr('checked',false);
				}
			}
			that.find('input').unbind("click").on('click',function() {
				switch(that.hasClass('active')){
					case true:
						that.toggleClass('active');
						$(this).attr('checked',false);
						break;
					case false:
						that.toggleClass('active');
						$(this).attr('checked',true);
						break;
				}
			})
			if(that.find('input').attr('checked')){
				that.addClass('active');
			}else{
				that.removeClass('active');
			}
		})
	}
	$.fn.radiobox = function() {
		var thatAll = $('.radiobox');
		$(this).each(function(){
			var that = $(this);
			if(that.find('input').attr('checked')){
				that.addClass('active');
			}else{
				that.removeClass('active');
			}
			that.find('input').unbind("click").on('click',function() {
				switch(that.hasClass('active')){
					case true:
						thatAll.find('input[name="'+$(this).attr('name')+'"]').parent().removeClass('active');
						thatAll.find('input[name="'+$(this).attr('name')+'"]').attr('checked',false);
						that.removeClass('active');
						$(this).attr('checked',false);
						break;
					case false:
						thatAll.find('input[name="'+$(this).attr('name')+'"]').parent().removeClass('active');
						thatAll.find('input[name="'+$(this).attr('name')+'"]').attr('checked',false);
						that.addClass('active');
						$(this).attr('checked',true);
						break;
				}
			})
		})
	}
	$.fn.cardFormat = function(pattern) {
		$(this).each(function(){
			var that = $(this);
			var k=0;
			var reg = /^(?=.*?\,)/g;
			if(reg.test( pattern.substr(0,that.find('input').val().length)) ){
				k = pattern.replace(/#/g,'').length;
			}
			var num = JH.NumberFormat.format(that.find('input').val(),pattern).replace(/,/g,' ');
			if(num == 0){
				that.append('<div class="num">&nbsp;</div>');
			}else{
				that.append('<div class="num">'+num+'</div>');
			}
			that.find('.num').css({top:-that.outerHeight()+1,width:that.outerWidth()-22});
			that.find('input').on('focus',function(){
				$(this).next().show();
			}).on('blur',function(){
				$(this).next().hide();
			}).on('keyup',function(e){
				var reg = /^(?=.*?\,)/g;
				if(!reg.test( pattern.substr(0,$(this).val().length)) ){
					k = 0;
				}
				switch(pattern.substr($(this).val().length-1+k,1)){
					case ',':
						if( e.keyCode == 8){
							k--;
						}else{
							k++;
						}				
						break;
					
				}
				var retpattern = pattern.substr(0,$(this).val().length+k);
				//console.log(retpattern);
				
				var num = JH.NumberFormat.format($(this).val(),retpattern).replace(/,/g,' ');
				if(num == 0){
					$(this).next().html('&nbsp;');
				}else{
					$(this).next().text(num);
				}
			})
		})
	}
	$.fn.box = function(options) {
		var defaults = {
			width: 720,
			height:540,
			title:''
		};
		var sets = $.extend(defaults, options || {});
		$(this).each(function(){
			var that = $(this);
			if(!that.parentsUntil('.boxwrap').parent().hasClass('boxwrap')){
				var wrapBox = $('<div class="boxwrap"><div class="boxbg"></div><div class="box"><b class="close closebox"></b><div class="title">'+sets.title+'</div><div class="content"></div></div></div>');
				wrapBox.find('.content').append(that);
				wrapBox.find('.box').css({width:sets.width,height:sets.height,marginLeft:-sets.width/2,marginTop:-sets.height/2});
				wrapBox.find('.content').css({width:sets.width-70,height:sets.height-110,margin:"20px 35px"});
				$('body').append(wrapBox);
				that.show();
				wrapBox.find('.content').jScrollPane();
				wrapBox.find('.closebox').on('click',function(){
					that.parentsUntil('.boxwrap').parent().fadeOut();
				});
				wrapBox.find('.boxbg').on('click',function(){
					that.parentsUntil('.boxwrap').parent().fadeOut();
				});
			}else{
				that.parentsUntil('.boxwrap').parent().fadeIn();
			}
			
		});
	}
	//tabs菜单
	$.fn.tabs = function(options) {
		var defaults = {
			active:1
		};
		var sets = $.extend(defaults, options || {});
		$(this).each(function(i,d){
			$(this).find('.tabsCon').eq(sets.active-1).show();
			$(this).find('.tabsNav a').eq(sets.active-1).addClass('active');
			$(this).find('.tabsNav a').each(function(i,id){
				$(this).on('click',function(){
					$(this).parent().parent().find('.tabsNav a').removeClass('active');
					$(this).addClass('active');
					$(this).parent().parent().find('.tabsCon').hide();
					$(this).parent().parent().find('.tabsCon').eq(i).show();
				})
			})
		});
	}
})(jQuery);
var JH = {}
JH.NumberUtilities = {
	random:function(nMinimum, nMaximum, nRoundToInterval) {//生成随机数
		nMaximum?nMaximum:nMaximum = 0;
		nRoundToInterval?nRoundToInterval:nRoundToInterval = 1;
		if(nMinimum > nMaximum) {
			var nTemp = nMinimum;
			nMinimum = nMaximum;
			nMaximum = nTemp;
		}
		var nDeltaRange = (nMaximum - nMinimum) + (1 * nRoundToInterval);
		var nRandomNumber = Math.random() * nDeltaRange;
		nRandomNumber += nMinimum;
		return Math.floor(nRandomNumber, nRoundToInterval);
	}
}
JH.ArrayUtilities = {
	randomize:function(aArray){//数组元素随机化
		var aCopy = aArray.concat();
		var aRandomized = new Array();
		var oElement;
		var nRandom;
		for(var i = 0; i < aCopy.length; i++) {
			nRandom = JH.NumberUtilities.random(0, aCopy.length - 1);
			aRandomized.push(aCopy[nRandom]);
			aCopy.splice(nRandom, 1);
			i--;
		}
		return aRandomized;
	}
}
JH.NumberFormat = {
	format:function(num,pattern){//格式化数字显示方式  JH.NumberFormat.format(12345.999,'#,##0.00')|(12345.999,'#,##0.##')|(123,'000000')
		var strarr = num?num.toString().split('.'):['0'];
		var fmtarr = pattern?pattern.split('.'):[''];
		var retstr='';	
		// 整数部分
		var str = strarr[0];
		var fmt = fmtarr[0];
		var i = str.length-1;  
		var comma = false;
		//console.log(fmt.substr(0,1));
		for(var f=fmt.length-1;f>=0;f--){
			switch(fmt.substr(f,1)){
				case '#':
					if(i>=0 ) retstr = str.substr(i--,1) + retstr;
					break;
				case '0':
					if(i>=0) retstr = str.substr(i--,1) + retstr;
					else retstr = '0' + retstr;
					break;
				case ',':
					comma = true;
					retstr=','+retstr;
					break;
			}
		}
		if(i>=0){
			if(comma){
				var l = str.length;
				for(;i>=0;i--){
					retstr = str.substr(i,1) + retstr;
					if(i>0 && ((l-i)%3)==0) retstr = ',' + retstr; 
				}
			}
			else retstr = str.substr(0,i+1) + retstr;
		}	
		retstr = retstr+'.';
		//小数部分
		str=strarr.length>1?strarr[1]:'';
		fmt=fmtarr.length>1?fmtarr[1]:'';
		i=0;
		for(var f=0;f<fmt.length;f++){
			switch(fmt.substr(f,1)){
				case '#':
					if(i<str.length) retstr+=str.substr(i++,1);
					break;
				case '0':
					if(i<str.length) retstr+= str.substr(i++,1);
					else retstr+='0';
					break;
			}
		}
		return retstr.replace(/^,+/,'').replace(/\.$/,'');
	}
}
JH.formatDate = function(date,pattern){//JH.formatDate("2015-05-22 20:21:00","YYYY-MM-DD hh:mm:ss");
	Date.prototype.format = function(format){ 
		var o = { 
			"M+" : this.getMonth()+1, //month 
			"D+" : this.getDate(), //day 
			"h+" : this.getHours(), //hour 
			"m+" : this.getMinutes(), //minute 
			"s+" : this.getSeconds(), //second 
			"Q+" : Math.floor((this.getMonth()+3)/3), //quarter 
			"S" : this.getMilliseconds() //millisecond 
		} 
	
		if(/(Y+)/.test(format)) { 
			format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		} 
	
		for(var k in o) { 
			if(new RegExp("("+ k +")").test(format)) { 
				format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
			} 
		} 
		return format; 
	}
	var now = new Date(date); 
	var nowStr = now.format(pattern);
	return nowStr;
}
JH.toDate = function(options){
	var date = new Date(options*1000);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
	s = date.getSeconds(); 
	return Y+M+D+h+m+s;
}