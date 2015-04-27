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
    }else {
        showErrorInfo(_wrap, text);
    }
}

/* check mod */
var check_mobile = function (ipt) {//验证手机号
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "手机号不能为空");
    }
    var regex = /^1\d{10}$/;
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

var check_id = function (ipt) {//验证身份证号
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
	    var regex = /^1\d{10}$/;
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

var check_bankCard = function (ipt) {//验证银行卡
    var value = ipt.val();
    if (isNull(value)) {
        return showTips(ipt, "银行卡不能为空");
    }
    var regex = /^\d{12,19}$/;
    if (!regex.test($.trim(value))) {
        return showTips(ipt, "银行卡格式错误");
    }
    return showTips(ipt, 0)
}
/* bind mod */

$(".ipt-phoneNum").bind("blur", function() {
    check_mobile($(this));
});

$(".ipt-code").bind("blur", function() {
    check_code($(this));
});

$(".ipt-phoneCode").bind("blur", function() {
    check_phoneCode($(this));
});

$(".ipt-idCard").bind("blur", function() {
    check_id($(this));
});

$(".ipt-password1").on("blur", function() {
    check_pwd1($(this));
});

$(".ipt-password2").on("blur", function() {
    check_pwd2($(this));
});

$(".ipt-organization").on("blur", function() {
	check_organization($(this));
});

$(".ipt-business").on("blur", function() {
	check_business($(this));
});

$(".ipt-bankCard").on("blur", function() {
    check_bankCard($(this));
});

