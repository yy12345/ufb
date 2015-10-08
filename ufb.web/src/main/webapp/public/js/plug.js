(function($) {
	$.fn.limit=function(num){//截取文字
		$(this).each(function(i,d){
			var objString = $(this).text();
			var objLength = $(this).text().length;
			if(objLength > num){
				$(this).attr("title",objString);
				$(this).css({textIndent:0});
				objString = $(this).text(objString.substring(0,JH.StringUtilities.limitLength(objString,num))+'…');
			}
		})
	}
	//下拉菜单
	$.fn.selectMenu = function(options) {
		var defaults = {
			refresh: false,
			change: $.noop,
			blur: $.noop
		};
		var sets = $.extend(defaults, options || {});
		$(this).each(function(i,d){
			var list = '';var listval = [];
			$(this).find("option").each(function(j,e){
				if(!$(this).attr('disabled')){
					list += '<li>'+$(this).text()+'</li>';
					listval.push($(this).val());
				}else{
					list += '<li class="disabled">'+$(this).text()+'</li>';
					listval.push($(this).val());
				}
			});
			if(!sets.refresh && !$(this).next().hasClass("title")){
				$(this).wrap('\
					<div class="selectmenu" tabindex="-1">\
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
			var that = $(this).parent();
			that.css({"z-index":999,"width":parseInt($(this).css("width"))+2,"margin-right":$(this).css("margin-right"),"margin-left":$(this).css("margin-left"),"outline":"none"});
			that.find('.list').css({"width":parseInt($(this).css("width"))});
			that.find(".text").text($(this).find('option:selected').text());
			that.find("li").unbind("click").on("click",function(event){
				event.stopPropagation();
				if(!$(this).hasClass('disabled')){
					that.find(".text").text($(this).text());
					that.find(".select").val(listval[$(this).index()]);
					sets.change(listval[$(this).index()]);
					that.find(".ico").removeClass("active");
					that.find(".list").hide();
				}
			});
			that.find("li").unbind("mouseover").on("mouseover",function(){
				$(this).addClass('over');
			});
			that.find("li").unbind("mouseout").on("mouseout",function(){
				$(this).removeClass('over');
			});
			that.unbind("click").on("click",function(event){
				event.stopPropagation();
				$('.selectmenu').css('z-index',1);
				$(this).css('z-index',2);
				$('.selectmenu').find(".list").hide();		
				switch ($(this).find(".ico").hasClass("active")) {
					case true:
						$('.selectmenu').find(".ico").removeClass("active");
						$(this).find(".ico").removeClass("active");
						that.find(".list").hide();
						break;
					case false:
						$('.selectmenu').find(".ico").removeClass("active");
						$(this).find(".ico").addClass("active");
						that.find(".list").show();
						break;
				}
				if($(document).height()-that.offset().top-that.find(".list").height()<40){
					that.find(".list").css({top:-that.find(".list").height()-1});
				}else{
					that.find(".list").css({top:that.height()-1});
				}
			});
			that.unbind("blur").on("blur",function(event){
				sets.blur($(this).find("select"));
			});
			$(document).unbind("click").on("click",function(){
				$('.selectmenu').find(".ico").removeClass("active");
				$('.selectmenu').find(".list").hide();
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
				thatAll.find('input[name="'+$(this).attr('name')+'"]').parent().removeClass('active');
				thatAll.find('input[name="'+$(this).attr('name')+'"]').attr('checked',false);
				that.addClass('active');
				$(this).attr('checked',true);
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
				$(this).parent().find('.num').show();
			}).on('blur',function(){
				$(this).parent().find('.num').hide();
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
				if($(this).val().length>pattern.replace(/,/g,'').length){
					var num = JH.NumberFormat.format($(this).val().substr(0,pattern.replace(/,/g,'').length),retpattern).replace(/,/g,' ')+"…";
				}else{
					var num = JH.NumberFormat.format($(this).val(),retpattern).replace(/,/g,' ');
				}
				if(num == 0){
					$(this).parent().find('.num').html('&nbsp;');
				}else{
					$(this).parent().find('.num').text(num);
				}
			})
		})
	}
	$.alert = function(options) {
		var defaults = {
			width:200,
			time:1,
			title:''
		};
		var sets = $.extend(defaults, options || {});
		$('.alert').remove();
		if(sets.time != null){
			$.doTimeout( 'timeid' );
			$.doTimeout('timeid',1000,function(){
				sets.time--;
				if(sets.time == 0){
					$('.alert').remove();
					return false;
				}
				return true;
			})		
		}
		$('body').append('<div class="alert">'+sets.title+'</div>');
		$('.alert').css({marginLeft:-$('.alert').outerWidth()/2,marginTop:-$('.alert').outerHeight()/2});
	}
	$.confirm = function(options) {
		var defaults = {
			width:450,
			title:'',
			complete:$.noop
		};
		var sets = $.extend(defaults, options || {});
		$('body').append('<div class="confirm"><div class="close closeAlert"></div><div class="content">'+sets.title+'</div><div class="ok">确 认</div><div class="cancel closeAlert">取 消</div></div>');
		
		$('.confirm').css({width:sets.width,marginLeft:-sets.width/2,marginTop:-$('.confirm').outerHeight()/2});
		$('.confirm .closeAlert').on('click',function(){
			$('.confirm').remove();
		})
		$('.confirm .ok').on('click',function(){
			sets.complete();
			$('.confirm').remove();
		})
	}
	$.delay = function(options) {
		var defaults = {
			delay:200,
			complete:$.noop,
			id:'delayid'
		};
		var sets = $.extend(defaults, options || {});
		$.doTimeout(sets.id);
		$.doTimeout(sets.id,sets.delay,function(){
			sets.complete();
		});
	}
	$.loadPage = function(url,mode) {
		var target,reg,url;
		url = url;
		switch(mode){
			case 'main':
				target = '.page .main';
				break;
			case 'body':
				target = '.page .body';
				break;
			case 'target':
				target = '.page .target';
				break;
			case 'more':
				target = '.page .loadmore';
				break;
			case 'page':
				target = 'body';
				break;
		}
		$(target).prepend('<div class="loading"><span></span></div>');
		var loading = $('.loading span');
		loading.animate({width:'90%'},1000,'easeOutQuad');
		$.ajax({
			type: "GET",
			url: url,
			success: function(data){
				loading.stop().animate({width:'100%'},100,'easeOutQuad',function(){
					$('.loading').remove();
					if(mode == 'main'){
						reg = /<section [^>]*class="loadmain"[^>]*>(<section[^>]*>[\s\S]*?<\/section>|[\s\S])*?<\/section>/;
					}else if(mode == 'body'){
						reg = /<section [^>]*class="loadbody"[^>]*>(<section[^>]*>[\s\S]*?<\/section>|[\s\S])*?<\/section>/;
					}else if(mode == 'target'){
						reg = /<section [^>]*class="loadtarget"[^>]*>(<section[^>]*>[\s\S]*?<\/section>|[\s\S])*?<\/section>/;
					}else if(mode == 'page'){
						reg = /<section [^>]*class="loadpage"[^>]*>(<section[^>]*>[\s\S]*?<\/section>|[\s\S])*?<\/section>/;
					}
					if(mode == 'more'){
						$(target).append($(data).find('.loadmore').html());
					}if(mode == 'page'){
						$(target).append(data.match(reg)[0]);
						$(data).find('.loadpage').fadeIn();
						$(data).find('.loadpageclose,.loadpagebg').on('click',function(){
							$(this).closest('.loadpage').fadeOut(400,'easeOutQuad',function(){
								$(this).remove();
							});
						});
					}else{
						$(target).html(data.match(reg)[0]);
					}
					//$(target).css('opacity',0).animate({'opacity':1},400,'easeOutQuad');
				});
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({title:'网络不给力，请再试试！'});
				$('.loading').remove();
			}
		});
	}
})(jQuery);
var JH = {}
JH.load = {
	add:function(url,target,callback){
		$(target).prepend('<div class="loading"><span></span></div>');
		var loading = $('.loading span');
		loading.animate({width:'90%'},1000,'easeOutQuad');
		$.ajax({
			type: "GET",
			url: url,
			success: function(data){
				loading.stop().animate({width:'100%'},100,'easeOutQuad',function(){
					$('.loading').remove();
					$(target).append(data);
					callback(target);
				});
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({title:'网络不给力，请再试试！'});
			}
		})
	},
	syncAdd:function(url,target){
		$.ajax({
			type: "GET",
			url: url,
			async:false,
			success: function(data){
				$(target).append(data);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({title:'网络不给力，请再试试！'});
			}
		})
	}
}
JH.ArrayUtilities = {
	toString:function(oArray, nLevel) {//快速输出数组内容
		nLevel?nLevel:nLevel = 0;
		var sIndent = "";
		for(var i = 0; i < nLevel; i++) {
			sIndent += "\t";
		}
		var sOutput = "";
		for(var sItem in oArray) {
			if(typeof oArray[sItem] == "object") {
				sOutput = sIndent + "** " + sItem + " **\n" + toString(oArray[sItem], nLevel + 1) + sOutput;
			}
			else {
				sOutput += sIndent + sItem + ":" + oArray[sItem] + "\n";
			}
		}
		return sOutput;
	}
}
JH.StringUtilities = {
	isWhitespace:function( ch ) {
		return ch == '\r' || 
				ch == '\n' ||
				ch == '\f' || 
				ch == '\t' ||
				ch == ' '; 
	},
	trim:function( original ) {//剪去开始结尾处空白
		var characters = original.split( "" );
		for ( var i = 0; i < characters.length; i++ ) {
			if ( this.isWhitespace( characters[i] ) ) {
				characters.splice( i, 1 );
				i--;
			} else {
				break;
			}
		}
		for ( i = characters.length - 1; i >= 0; i-- ) {
			if ( this.isWhitespace( characters[i] ) ) {
				characters.splice( i, 1 );
			} else {
				break;
			}
		}
		return characters.join("");
	},
	limitLength:function(str,num){
		var l = 0;
		var a = str.split("");
		for (var i=0;i<num;i++) {
			if (a[i].charCodeAt(0)<299) {
				l+=2;
			} else {
				l++;
			}
		}
		return l;
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