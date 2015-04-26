/**
 * Created by FingerLink008A on 2015/4/20.
 */
var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
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

/* error mod */
function showErrorInfo(wrap, errText) {
    wrap.find(".tips-check").html("<i class='icon'></i>" + errText);
    wrap.removeClass("ok");
    wrap.addClass("error");
}
function showOkInfo(wrap) {
    wrap.find(".tips-check").html("<i class='icon'></i>");
    wrap.removeClass("error");
    wrap.addClass("ok");
}
function showTips(ele, text) {
    var _wrap = ele.parents("li");
    var _tips = _wrap.find(".tips-check");
    if(!text) {
        showOkInfo(_wrap);
    }else {
        showErrorInfo(_wrap, text);
    }
}

/* check mod */
var check_mobile = function (ipt) {
    var value = ipt.val();
    if (value == "" || value == ipt.context.defaultValue) {
        return 1
    }
    var regex = /^1\d{10}$/;
    if (!regex.test(value)) {
        return 2
    }
    return 0
}
var check_code = function (ipt) {
    var value = ipt.val();
    if (value == "" || value == ipt.context.defaultValue) {
        return 1
    }
    if (value.length != 4) {
        return 2
    }
    return 0
}
var check_pwd1 = function (ipt) {
    var value = ipt.val();
    if (value == "") {
        return 1
    }
    if (value.length > 20) {
        return 2
    }
    if (value.length < 6) {
        return 3
    }
    var regex1 = /\s+/;
    if (regex1.test(value)) {
        return 4
    }
    var regex2 = /^[0-9]+$/;
    if (regex2.test(value)) {
        return 5
    }
    var regex3 = /^[a-zA-Z]+$/;
    if (regex3.test(value)) {
        return 6
    }
    var regex4 = /^[^0-9A-Za-z]+$/;
    if (regex4.test(value)) {
        return 7
    }
    if (isSameWord(value)) {
        return 8
    }
    var n = "d*" + commonSymbol + "";
    var regexAll = "\\\d+[A-Za-z]|[A-Za-z]+[0-9]+|[A-Za-z]+" + commonSymbol + "[0-9]+|[A-Za-z]+[0-9]+" + commonSymbol + "|" + n + "";
    var regex5 = new RegExp(regexAll);
    if (!regex5.test(value)) {
        return 10
    }
    return 0
}
function check_pwd2(ipt) {
    var _form = ipt.parents("form");

    var _valuePassword1 = _form.find(".ipt-password1").val();
    var _valuePassword2 = ipt.val();
    if (_valuePassword2 == "") {
        return 1
    }
    if (_valuePassword1 != _valuePassword2) {
        return 2
    }
    return 0
}
var check_organization = function (ipt) {
    var value = ipt.val();
    if (value == "" || value == ipt.context.defaultValue) {
        return 1
    }
    return 0
}
var check_business = function (ipt) {
    var value = ipt.val();
    if (value == "" || value == ipt.context.defaultValue) {
        return 1
    }
    return 0
}
/* bind mod */

$(".ipt-phoneNum").bind("blur", function() {
    var _this = $(this);
    var checkCode = check_mobile(_this);
    switch (checkCode) {
        case 1:
            showTips(_this, "手机号不能为空");
            break;
        case 2:
            showTips(_this, "格式错误，请输入正确的手机号码");
            break;
        case 0:
            showTips(_this, 0);
    }
});

$(".ipt-code").bind("blur", function() {
    var _this = $(this);
    var checkCode = check_code(_this);
    switch (checkCode) {
        case 1:
            showTips(_this, "验证码不能为空");
            break;
        case 2:
            showTips(_this, "验证码为4位数字");
            break;
        case 0:
            showTips(_this, 0);
    }
});

$(".ipt-phoneCode").bind("blur", function() {
    var _this = $(this);
    var checkCode = check_code(_this);
    switch (checkCode) {
        case 1:
            showTips(_this, "手机验证码不能为空");
            break;
        case 2:
            showTips(_this, "验证码为6位数字");
            break;
        case 0:
            showTips(_this, 0);
    }
});

$(".ipt-password1").on("blur", function() {
    var _this = $(this);
    var checkCode = check_pwd1(_this);
    switch (checkCode) {
        case 1:
            showTips(_this, "密码不能为空");
            break;
        case 2:
            showTips(_this, "密码为6-20位字符");
            break;
        case 3:
            showTips(_this, "密码为6-20位字符");
            break;
        case 4:
            showTips(_this, "密码中不允许有空格");
            break;
        case 5:
            showTips(_this, "密码不能全为数字");
            break;
        case 6:
            showTips(_this, "密码不能全为字母，请包含至少1个数字或符号 ");
            break;
        case 7:
            showTips(_this, "密码不能全为符号");
            break;
        case 8:
            showTips(_this, "密码不能全为相同字符或数字");
            break;
        case 0:
            showTips(_this, 0);
    }
});


$(".ipt-password2").on("blur", function() {
    var _this = $(this);
    var checkCode = check_pwd2(_this);
    switch (checkCode) {
        case 1:
            showTips(_this, "确认密码不能为空");
            break;
        case 2:
            showTips(_this, "两次密码输入不一致");
            break;
        case 0:
            showTips(_this, 0);
    }
});

$(".ipt-organization").bind("blur", function() {
    var _this = $(this);
    var checkCode = check_organization(_this);
    switch (checkCode) {
        case 1:
            showTips(_this, "幼教机构名称不能为空");
            break;
        case 0:
            showTips(_this, 0);
    }
});

$(".ipt-business").bind("blur", function() {
    var _this = $(this);
    var checkCode = check_business(_this);
    switch (checkCode) {
        case 1:
            showTips(_this, "营业执照注册号不能为空");
            break;
        case 0:
            showTips(_this, 0);
    }
});

