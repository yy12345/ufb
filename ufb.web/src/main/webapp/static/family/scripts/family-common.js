/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式（数值形式）
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1234,567.45'
 * @type String
 */
var formatCurrency1 = function(num) {
	if (typeof(num) == "undefined"){
		return "0.00";
	}
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式（分段逗号形式）
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
var formatCurrency2 = function(num) {
	if (typeof(num) == "undefined"){
		return "0.00";
	}
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}

//验证密码
var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
var check_pwd1 = function(value){
    if (value == "") {
        return "密码不能为空";
    }
    if (value.length < 6 || value.length > 20) {
        return "密码为6-20位字符";
    }
    var regex1 = /\s+/;
    if (regex1.test(value)) {
        return "密码中不允许有空格";
    }
    var regex2 = /^[0-9]+$/;
    if (regex2.test(value)) {
        return "密码不能全为数字";
    }
    var regex3 = /^[a-zA-Z]+$/;
    if (regex3.test(value)) {
        return "密码不能全为字母，请包含至少1个数字或符号";
    }
    var regex4 = /^[^0-9A-Za-z]+$/;
    if (!regex4.test(value)) {
        return "密码不能全为符号";
    }
    if (isSameWord(value)) {
        return "密码不能全为相同字符或数字";
    }
    var regexAll = "^([0-9]+|[a-zA-Z]+|" + commonSymbol + ")+$";
    var regex5 = new RegExp(regexAll);
    if (!regex5.test(value)) {
        return "密码不符合规范，必须包含数字，字母，特殊字符中的两者或三者";
    }
    return "";
}
//验证重复密码
 var check_pwd2 = function(pwd1, pwd2){
    var _form = ipt.parents("form");

    var _valuePassword1 = pwd1;
    var _valuePassword2 = pwd2;
    if (_valuePassword2 == "") {
        return "确认密码不能为空";
    }
    if (_valuePassword1 != _valuePassword2) {
        return "两次密码输入不一致";
    }
    return "";
}
// 是否所有字符相同
var isSameWord = function(str){
    var char;
    if (str != null && str != "") {
        char = str.charCodeAt(0);
        char = "\\" + char.toString(8);
        var f = "[" + char + "]{" + (str.length) + "}";
        var h = new RegExp(f);
        return h.test(str);
    }
    return true
}
