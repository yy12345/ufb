/**
 * Created by FingerLink008A on 2015/4/20.
 */
var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
var spliter = ",";

function isSameWord(str) {
    var char;
    if (str != null && str != "") {
        char = str.charCodeAt(0);
        char = "\\" + char.toString(8);
        var f = "[" + char + "]{" + (str.length) + "}";
        var h = new RegExp(f);
        return h.test(str)
    }
    return true
}
function trim(a) {
    return a.replace(/(^\s*)|(\s*$)/g, "")
}

function ltrim(a) {
    return a.replace(/(^\s*)/g, "")
}

function rtrim(a) {
    return a.replace(/(\s*$)/g, "")
}

function isNull(a) {
    return a == null || a == undefined || $.trim(a).length == 0
}

function isNotNull(a) {
    return !isNull(a)
}
/* error mod */
var showErrorInfo = function (wrap, errText) {
    wrap.find(".tips-check").html("<i class='icon'></i>" + errText);
    wrap.removeClass("ok");
    wrap.addClass("error");
}
var showOkInfo = function (wrap) {
    wrap.find(".tips-check").html("<i class='icon'></i>");
    wrap.removeClass("error");
    wrap.addClass("ok");
}
var showTips = function (ele, text) {
    var _wrap = ele.parents("li");
    if(!text) {
        showOkInfo(_wrap);
        // ADD BY GH
        return true;
    }else {
        showErrorInfo(_wrap, text);
        // ADD BY GH
        //if(ele.attr("type")!="password"){
        //	ele.focus();
        //}
        return false;
    }
}

/* check mod */
var check_null = function (ipt, text) {
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, text);
    }
    return showTips(ipt, 0);
}

var check_mobile = function (ipt) {//验证手机号
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "手机号不能为空");
    }
    var regex = /^0?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[0-9])[0-9]{8}$/;
    if (!regex.test(value)) {
        return showTips(ipt, "格式错误，请输入正确的手机号码");
    }

    return showTips(ipt, 0);
}

var check_code = function (ipt) {//验证验证码
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "验证码不能为空");
    }
    if (value.length != 4) {
        return showTips(ipt, "验证码为4位数字");
    }
    return showTips(ipt, 0);
}
var check_phoneCode = function (ipt) {//验证验证码
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "手机验证码不能为空");
    }
    if (value.length != 6) {
        return showTips(ipt, "验证码为6位数字");
    }
    return showTips(ipt, 0);
}

var check_autoDat = function (ipt) {//充值时间
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "充值时间不能为空");
    }
    if (value.length > 2) {
        return showTips(ipt, "充值时间为2位数字");
    }
    var regex2 = /^[0-9]+$/;
    if (!regex2.test(value)) {
        return showTips(ipt, "充值时间范围为1至28日");
    }
    if (value>29 || value<1) {
        return showTips(ipt, "充值时间范围为1至28日");
    }
    return showTips(ipt, 0);
}



var check_id_back = function (ipt) {//验证身份证号
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "身份证不能为空");
    }
    var regex = /^((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\d{3}(x|X))|(\d{4}))$/;
    if (!regex.test(value)) {
        return showTips(ipt, "格式错误，请输入正确的身份证号");
    }
    return showTips(ipt, 0);
}



var check_id = function (ipt) {
	var value = ipt.val();
	if(value == "") {  
		return showTips(ipt, "输入身份证号码不能为空!");
	}  
 
	if (value.length != 15 && value.length != 18) {  
		return showTips(ipt, "输入身份证号码格式不正确!");
    }  
         
    var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};   
        
	if(area[parseInt(value.substr(0,2))]==null) {  
		return showTips(ipt, "身份证号码不正确(地区非法)!");
	}   
             
	if (value.length == 15) {  
		pattern= /^\d{15}$/;  
        if (pattern.exec(value)==null){  
        	return showTips(ipt, "15位身份证号码必须为数字！");
        }  
        var birth = parseInt("19" + value.substr(6,2));  
        var month = value.substr(8,2);  
        var day = parseInt(value.substr(10,2));  
        switch(month) {  
       		case '01':  
       		case '03':  
       		case '05':  
       		case '07':  
       		case '08':  
       		case '10':  
       		case '12':  
            	if(day>31) {  
            		return showTips(ipt, "输入身份证号码不格式正确!");
            	}  
            	break;  
       		case '04':  
       		case '06':  
       		case '09':  
       		case '11':  
       			if(day>30) {  
       				return showTips(ipt, "输入身份证号码不格式正确!");
       			}  
       			break;  
       		case '02':  
       			if((birth % 4 == 0 && birth % 100 != 0) || birth % 400 == 0) {  
       				if(day>29) {  
                		return showTips(ipt, "输入身份证号码不格式正确!");
       				}  
       			} else {  
                 	if(day>28) {  
                 		return showTips(ipt, "输入身份证号码不格式正确!");
                 	}  
       			}  
       			break;  
       		default:  
       			return showTips(ipt, "输入身份证号码不格式正确!");
        }  
        var nowYear = new Date().getYear();  
        if(nowYear - parseInt(birth)<15 || nowYear - parseInt(birth)>100) {
        	return showTips(ipt, "输入身份证号码不格式正确!");
        }  
        return (true);  
    }  
       
    var Wi = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);  
    var lSum        = 0;  
    var nNum        = 0;  
    var nCheckSum   = 0;  
       
    for (i = 0; i < 17; ++i) {  
    	if ( value.charAt(i) < '0' || value.charAt(i) > '9' ) {  
    		return showTips(ipt, "输入身份证号码不格式正确!");
        }  
        else {  
        	nNum = value.charAt(i) - '0';  
        }  
        lSum += nNum * Wi[i];  
    }  
    if( value.charAt(17) == 'X' || value.charAt(17) == 'x') {  
    	lSum += 10*Wi[17];  
    } else if ( value.charAt(17) < '0' || value.charAt(17) > '9' ) {  
    	return showTips(ipt, "输入身份证号码不格式正确!");
    } else {  
        lSum += ( value.charAt(17) - '0' ) * Wi[17];  
    } 
         
    if ((lSum % 11) == 1) {  
    	showTips(ipt, 0);
    } else {  
    	return showTips(ipt, "输入身份证号码不格式正确!");
	}  
}

var check_pwd1 = function (ipt) {//验证密码
    var value = ipt.val();
    if (value == "") {
        return showTips(ipt, "密码不能为空");
    }
    if (value.length < 6 || value.length > 20) {
        return showTips(ipt, "密码为6-20位字符");
    }
    var regex1 = /\s+/;
    if (regex1.test(value)) {
        return showTips(ipt, "密码中不允许有空格");
    }
    var regex2 = /^[0-9]+$/;
    if (regex2.test(value)) {
        return showTips(ipt, "密码不能全为数字");
    }
    var regex3 = /^[a-zA-Z]+$/;
    if (regex3.test(value)) {
        return showTips(ipt, "密码不能全为字母，请包含至少1个数字或符号");
    }
    var regex4 = /^[^0-9A-Za-z]+$/;
    if (regex4.test(value)) {
        return showTips(ipt, "密码不能全为符号");
    }
    if (isSameWord(value)) {
        return showTips(ipt, "密码不能全为相同字符或数字");
    }
    var regexAll = "^([0-9]+|[a-zA-Z]+|" + commonSymbol + ")+$";
    var regex5 = new RegExp(regexAll);
    if (!regex5.test(value)) {
        return showTips(ipt, "密码不符合规范，必须包含数字，字母，特殊字符中的两者或三者");
    }
    return showTips(ipt, 0);
}
 var check_pwd2 = function (ipt) {//验证重复密码
    var _form = ipt.parents("form");

    var _valuePassword1 = _form.find(".ipt-password1").val();
    var _valuePassword2 = ipt.val();
    if (_valuePassword2 == "") {
        return showTips(ipt, "确认密码不能为空");
    }
    if (_valuePassword1 != _valuePassword2) {
        return showTips(ipt, "两次密码输入不一致");
    }
    return showTips(ipt, 0);
}
 
var check_mobile = function (ipt) {//验证手机号
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "手机号不能为空");
    }
    
    /* 手机号码验证*/
    var regex = /^0?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[0-9])[0-9]{8}$/;
    if (!regex.test(value)) {
        return showTips(ipt, "格式错误，请输入正确的手机号码");
    }
    return showTips(ipt, 0);
}
 
var check_organization = function (ipt) {//幼教机构名称
	var value = ipt.val();
	if (isNull(value)) {
		return showTips(ipt, "幼教机构名称不能为空");
	}
	return showTips(ipt, 0)
}
 
var check_business = function (ipt) {//营业执照注册号
	var value = ipt.val();
	if (isNull(value)) {
		return showTips(ipt, "营业执照注册号不能为空");
	}
	return showTips(ipt, 0)
}

var check_bankAcnm = function (ipt) {//经办人姓名
	var value = ipt.val();
	if (isNull(value)) {
		return showTips(ipt, "经办人姓名不能为空");
	}
	return showTips(ipt, 0)
}


var check_bankCard = function (ipt) {//验证银行卡
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "银行卡不能为空");
    }
    var regex = /^\d{5,21}\d*$/;
    if (!regex.test($.trim(value))) {
        return showTips(ipt, "银行卡格式错误");
    }
    
    return showTips(ipt, 0)
}

var check_cash = function (ipt) {
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "金额不能为空");
    }
    
    var regex = /^([1-9]{1}[0-9]{0,10}(\.[0-9]{1,2})?|0\.[1-9][0-9]?|0\.0[1-9])$/;
    if (!regex.test($.trim(value))) {
        return showTips(ipt, "请填写正确的金额，例如：10000元");
    }
    return showTips(ipt, 0);
}
/* bind mod */

$("body").on("blur", ".ipt-phoneNum", function() {
    check_mobile($(this));
});

$("body").on("blur", ".ipt-code", function() {
    check_code($(this));
});

$("body").on("blur", ".ipt-phoneCode", function() {
    check_phoneCode($(this));
});

$("body").on("blur", ".ipt-idCard", function() {
    check_id($(this));
});

$("body").on("blur", ".ipt-password0",  function() {
	check_null($(this), "密码不能为空");
});

$("body").on("blur", ".ipt-password1",  function() {
    check_pwd1($(this));
});

$("body").on("blur", ".ipt-password2",  function() {
    check_pwd2($(this));
});

$("body").on("blur", ".ipt-organization",  function() {
	check_organization($(this));
});

$("body").on("blur", ".ipt-business",  function() {
	check_business($(this));
});

$("body").on("blur", ".ipt-bankCard",  function() {
    check_bankCard($(this));
});

$("body").on("blur", ".ipt-cash",  function() {
    check_cash($(this));
});

$("body").on("blur", ".ipt-bankAcnm",  function() {
	check_bankAcnm($(this));
});

$("body").on("blur", ".ipt-loginUsername", function () {
    check_null($(this), "登录名不能为空");
});
$("body").on("blur", ".ipt-loginPassword", function () {
    check_null($(this), "登录密码不能为空");
});
