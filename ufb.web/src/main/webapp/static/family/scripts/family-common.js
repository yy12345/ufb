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
    if (regex4.test(value)) {
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
    if (pwd2 == "") {
        return "确认密码不能为空";
    }
    if (pwd1 != pwd2) {
        return "两次密码输入不一致";
    }
    return "";
}
// 验证交易密码（个人用户6位数字）
var check_tradePwd1 = function(value){
	if (value == "") {
        return "交易密码不能为空";
    }
    var regex = /^[0-9]{6}$/;
    if (!regex.test(value)) {
        return "交易密码为6位数字！";
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
// 检验身份证是否合法
var check_id = function(value) {
	if(value == "") {  
		return "输入身份证号码不能为空!";
	}  
	if (value.length != 15 && value.length != 18) {  
		return "输入身份证号码格式不正确!";
    }          
    var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};         
	if(area[parseInt(value.substr(0,2))]==null) {  
		return "身份证号码不正确(地区非法)!";
	}               
	if (value.length == 15) {  
		pattern= /^\d{15}$/;  
        if (pattern.exec(value)==null){  
        	return "15位身份证号码必须为数字！";
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
            		return "输入身份证号码不格式正确!";
            	}  
            	break;  
       		case '04':  
       		case '06':  
       		case '09':  
       		case '11':  
       			if(day>30) {  
       				return "输入身份证号码不格式正确!";
       			}  
       			break;  
       		case '02':  
       			if((birth % 4 == 0 && birth % 100 != 0) || birth % 400 == 0) {  
       				if(day>29) {  
                		return "输入身份证号码不格式正确!";
       				}  
       			} else {  
                 	if(day>28) {  
                 		return "输入身份证号码不格式正确!";
                 	}  
       			}  
       			break;  
       		default:  
       			return "输入身份证号码不格式正确!";
        }  
        var nowYear = new Date().getYear();  
        if(nowYear - parseInt(birth)<15 || nowYear - parseInt(birth)>100) {
        	return "输入身份证号码不格式正确!";
        }  
		return '';  
    }  
       
    var Wi = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);  
    var lSum        = 0;  
    var nNum        = 0;  
    var nCheckSum   = 0;       
    for (i = 0; i < 17; ++i) {  
    	if ( value.charAt(i) < '0' || value.charAt(i) > '9' ) {  
    		return "输入身份证号码不格式正确!";
        }  
        else {  
        	nNum = value.charAt(i) - '0';  
        }  
        lSum += nNum * Wi[i];  
    }  
    if( value.charAt(17) == 'X' || value.charAt(17) == 'x') {  
    	lSum += 10*Wi[17];  
    } else if ( value.charAt(17) < '0' || value.charAt(17) > '9' ) {  
    	return "输入身份证号码不格式正确!";
    } else {  
        lSum += ( value.charAt(17) - '0' ) * Wi[17];  
    }         
    if ((lSum % 11) == 1) {  
    	return '';
    } else {  
    	return "输入身份证号码不格式正确!";
	}  
}
