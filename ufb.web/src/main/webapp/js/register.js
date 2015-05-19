function showErrorInfo(b, a) {
	//a is a message
    jQuery("#" + b + "").html("<u></u>" + a + "").show();
    jQuery("#" + b + "").parents("li").addClass("cur_error");
    var c = b.split("_");
    jQuery("#" + c[0] + "_desc").hide()
}
function showPassError(a, b) {
    jQuery("#" + a + "_tip").hide();
    jQuery("#" + a + "_Level").hide();
    showErrorInfo(a + "_error", b)
}
function showPass2Error(a, b) {
    jQuery("#" + a + "2_tip").hide();
    showErrorInfo(a + "2_error", b)
}
var jsRegistFed = {
	ieLower: $.browser.msie && $.browser.version == 6 || false,
	helpCenterHover: function() {
        $(".help_wrap", ".regist_header_right ").hover(function() {
            $(this).addClass("help_wrap_hover")
        }, function() {
            $(this).removeClass("help_wrap_hover")
        })
    },registForm: function(e) {
        if ($(e).length <= 0) {
            return
        }
        var f = $(e).val();
        var d = $(".regist_account_info");
        $(e, ".regist_form").focus(function() {
            if ($(this).val() == f && $("#lockEmail").val() != 1) {
                $(this).val("").removeClass("gay_text")
            }
            $(this).parents("li").removeClass("cur_error").addClass("cur")
        });
        $(e, ".regist_form").blur(function() {
            var a = $(this).val();
            if (!a) {
                $(this).val(f).addClass("gay_text")
            }
            $(this).parents("li").removeClass("cur")
        });
        $(".form_item").delegate(".ipt_username", "keyup", function() {
            $(this).next(".associat_input").end().parents("li").css("z-index", "103");
            d.css({position: "relative","z-index": "203"})
        });
        $(document).bind("click", function(a) {
            var b = a.target;
            if (b.className != "ipt_username" || b.className != "associat_input") {
                $(".associat_input").hide();
                d.removeAttr("style")
            }
        })
    },serviceAgreement: function() {
        $(".check_agreement", ".service_agreement").click(function() {
            if ($(this).hasClass("uncheck_agreement")) {
                $(this).attr("class", "check_agreement");
                $(this).parents("li").next("li").find("button").removeClass("disable_btn").attr("disabled", false)
            } else {
                $(this).attr("class", "uncheck_agreement");
                $(this).parents("li").next("li").find("button").addClass("disable_btn").attr("disabled", true)
            }
            return false
        })
    },changeNickName: function() {
        var d = $("#nickname").val();
        var c;
        $("a", ".nickname_default").click(function() {
            $(".nickname_default").hide();
            $(".change_nickname_detail").show();
            $(".change_nickname_detail").delegate("input", "focus", function() {
                if (d == $(this).val()) {
                    $(this).val("")
                }
                $(this).removeClass("gay_text")
            });
            $(".change_nickname_detail").delegate("input", "blur", function() {
                c = $(this).val();
                if (!c) {
                    $(this).val(d);
                    $(this).addClass("gay_text")
                }
            });
            $(".change_nickname_detail").delegate(".save_btn", "click", function() {
                var a = /[\"<>$+]/;
                var f = $("#nickname").val();
                if (f == "") {
                    $("#nickNameDiv").addClass("nichname_wrong");
                    return false
                }
                if (a.test(f)) {
                    $("#nickNameDiv").addClass("nichname_wrong");
                    return false
                }
                if (f.length > 500) {
                    $("#nickNameDiv").addClass("nichname_wrong");
                    return false
                }
                var b = false;
                $.ajax({type: "POST",url: "/passport/updateNickName.do",async: false,data: {nickName: f},success: function(e) {
                        if (e.errorCode == 0) {
                            b = true
                        } else {
                            $("#nickNameDiv").addClass("nichname_wrong")
                        }
                    }});
                if (b) {
                    $(this).parents(".change_nickname_detail").hide().next(".your_nickname").show().find(".nickname").text(c);
                    $(this).parents(".change_nickname_detail").prev(".change_nickname").hide()
                }
                return false
            })
        })
    },emailReceive: function() {
        $(".no_email_detail").delegate(".no_email", "click", function() {
            $(this).next("ul").show();
            return false
        })
    },rate: function(z, r) {
        var v = document.getElementById(r);
        if (null == v) {
            return
        }
        var u = v.style;
        var q = !-[1, ];
        if (q) {
            var p = z * Math.PI / 180, s = Math.cos(p), t = -Math.sin(p), n = Math.sin(p), o = Math.cos(p);
            v.fw = v.fw || v.offsetWidth / 2;
            v.fh = v.fh || v.offsetHeight / 2;
            var y = (90 - z % 90) * Math.PI / 180, w = Math.sin(y) + Math.cos(y);
            u.filter = "progid:DXImageTransform.Microsoft.Matrix(M11=" + s + ",M12=" + t + ",M21=" + n + ",M22=" + o + ",SizingMethod='auto expand');";
            u.top = v.fh * (1 - w) + "px";
            u.left = v.fw * (1 - w) + "px"
        } else {
            var x = "rotate(" + z + "deg)";
            u.MozTransform = x;
            u.WebkitTransform = x;
            u.OTransform = x;
            u.msTransform = x;
            u.Transform = x
        }
        return false
    },paswdStrength: function(b) {
        if ($(b).length <= 0) {
            return
        }
        if ($(b + "2").length <= 0) {
            return
        }
        $(b + "2").attr("readonly", "readonly").css("background-color", "#C0C1C4");
        $(b).focus(function() {
            $(b).parents("li").removeClass("cur_error").addClass("cur")
        });
        $(b + "2").focus(function() {
            $(b + "2").parents("li").removeClass("cur_error").addClass("cur")
        });
        $(".form_item").delegate("input[name='pwd']", "click", function() {
            $(this).hide().next("input[type='password']").show().focus();
            $(this).parents("li").removeClass("cur_error").addClass("cur")
        });
        $("input[name='pwd']").focus(function() {
            $(this).hide().next("input[type='password']").show().focus();
            $(this).parents("li").removeClass("cur_error").addClass("cur")
        });
        $(".form_item").delegate("input[type='password']", "blur", function() {
            var a = $(this).val();
            if (!a) {
                $(this).hide().prev("input[type='text']").show()
            }
            $(this).parents("li").removeClass("cur")
        });
        $(".form_item").delegate(b, "keyup", function() {
            liItem = $(this).parents("li");
            arrowId = liItem.find("i").attr("id");
            liItem.find(".paswd_strength").show();
            var d = $(this).val().length;
            var a = getPassPoint(b);
            console.log("getPassPoint(b): b = " + jQuery(b).val());
            console.log("getPassPoint(b): " + a);
            if (d == 1) {
                jsRegistFed.rate(0, arrowId)
            } else {
                if (d > 1 && d < 4) {
                    jsRegistFed.rate(30, arrowId)
                } else {
                    if (a >= 80) {
                        jsRegistFed.rate(150, arrowId)
                    } else {
                        if (a >= 50) {
                            jsRegistFed.rate(90, arrowId)
                        }
                    }
                }
            }
        })
    },receiveCode: function() {
        $(".phone_verifica_form").delegate(".receive_code", "click", function() {
            if ($(".receive_code").hasClass("reacquire_code")) {
                return false
            }
            $.ajax({type: "POST",url: "/passport/sendMobileCheckCode.do",async: false,success: function(a) {
                    if (a) {
                        if (1 == a.sendRs) {
                            alert("验证码已发送至您的手机！");
                            $("#text_change").html("手机验证码已发送至手机：" + subMobile + ",请查看短信并填写验证码");
                            return
                        } else {
                            if (2 == a.sendRs) {
                                alert("每天只有5次获取验证码的机会");
                                return
                            }
                        }
                    }
                }});
            $(this).addClass("reacquire_code").html("重新获取验证码(<i>59</i>)");
            var d = $("i", ".reacquire_code").text();
            var e = setInterval(function() {
                if (d > 0) {
                    d--;
                    $("i", ".reacquire_code").text(d)
                }
            }, 1000);
            var f = setTimeout(function() {
                $(".receive_code", ".phone_verifica_form").removeClass("reacquire_code").html("重新获取验证码")
            }, d * 1000);
            return false
        })
    },registTab: function() {
        $(".regist_tab").delegate("li", "click", function() {
            var c = $("li", ".regist_tab"), d = c.index(this);
            if (d == 1) {
                $(".regist_tab .cur_tab").animate({left: "258px"}, 300, function() {
                    $(this).addClass("cur").siblings("li").removeClass("cur");
                    $(".regist_form", ".mod_regist_wrap").eq(d).show().siblings(".regist_form").hide()
                })
            } else {
                $(".regist_tab .cur_tab").animate({left: "0"}, 300, function() {
                    $(this).addClass("cur").siblings("li").removeClass("cur");
                    $(".regist_form", ".mod_regist_wrap").eq(d).show().siblings(".regist_form").hide()
                })
            }
        })
    },successRotate: function() {
        var b = setTimeout(function() {
            $(".success_rotate").addClass("rating")
        }, 1000)
    },areaSelect: function() {
        $(".company_area").parents("li").css("z-index", "200")
    },mobileRecvCodeLeft: -350,mobileValidCodeLeft: 0,reSetValidCodeFlage: false,getMobileRecvCode: function(c) {
        var d = false;
        $.ajax({type: "POST",url: "/passport/sendCheckCodeForRegister.do",async: true,data: {"user.cellphone": $("#phone").val(),validCode: $("#validCodeMobile").val()},success: function(a) {
                if (a.errorCode == 1) {
                    showPhoneError("不能为空")
                } else {
                    if (a.errorCode == 20) {
                        $(".regist_form .recv_mobile_code").addClass("cur_error");
                        $("#mobile_validcode_error").addClass("regist_tips_error");
                        $("#mobile_validcode_error").find("p").text("验证码错误");
                        refresh_valid_code();
                        if (showValidCodeWhenRegistByMobile == 0) {
                            showValidCodeWhenRegistByMobile = 1;
                            jsRegistFed.showMobileValidCode()
                        }
                    } else {
                        if (a.errorCode == 15) {
                            showPhoneError("格式错误，请输入正确的手机号码")
                        } else {
                            if (a.errorCode == 16) {
                                showPhoneError("该手机号已存在，<a href='/passport/login_input.do'>登录</a>")
                            } else {
                                if (a.errorCode == 17) {
                                    alert("每天只有3次获取验证码的机会")
                                } else {
                                    d = true
                                }
                            }
                        }
                    }
                }
                if (d && undefined != c && null != c) {
                    c.call();
                    jsRegistFed.reSetValidCodeFlage = true
                }
            }})
    },mobileRegist: function() {
        $(".recv_mobile_code").delegate(".receive_code", "click", function(b) {
            if (!$(".receive_code").hasClass("reacquire_code")) {
                if (showValidCodeWhenRegistByMobile == 1) {
                    jsRegistFed.showMobileValidCode()
                } else {
                    jsRegistFed.showMobileRecvCode();
                    jsRegistFed.getMobileRecvCode()
                }
            }
        });
        //TODO by goodrich
        $("#password_mobile").hide();
    },showMobileValidCode: function() {
        $("#mobile_validcode_error").removeClass("regist_tips_error");
        $("#mobile_validcode_error").find("p").text("");
        $(".mb_code_box").animate({left: jsRegistFed.mobileValidCodeLeft}, 300, function() {
            refresh_valid_code()
        })
    },showMobileRecvCode: function() {
        $("#mobile_validcode_error").removeClass("regist_tips_error");
        $("#mobile_validcode_error").find("p").text("");
        if (showValidCodeWhenRegistByMobile == 1) {
            $(".mb_code_box").animate({left: jsRegistFed.mobileRecvCodeLeft}, 300, function() {
                jsRegistFed.mobileRecvCodeCountdown()
            })
        } else {
            jsRegistFed.mobileRecvCodeCountdown()
        }
    },mobileRecvCodeCountingdown: false,mobileRecvCodeCountingdownAutotime: null,mobileRecvCodeCountingdownTimeout: null,mobileRecvCodeCountdown: function() {
        jsRegistFed.mobileRecvCodeCountingdown = true;
        $(".regist_form .recv_mobile_code a.receive_code").addClass("reacquire_code").html("重新获取验证码(<i>59</i>)");
        var b = $("i", ".reacquire_code").text();
        jsRegistFed.mobileRecvCodeCountingdownAutotime = setInterval(function() {
            if (b > 0) {
                b--;
                $("i", ".reacquire_code").text(b)
            } else {
                clearInterval(jsRegistFed.mobileRecvCodeCountingdownAutotime);
                jsRegistFed.mobileRecvCodeCountingdownAutotime = null
            }
        }, 1000);
        jsRegistFed.mobileRecvCodeCountingdownTimeout = setTimeout(function() {
            $(".receive_code", ".recv_mobile_code").removeClass("reacquire_code").html("重新获取验证码");
            jsRegistFed.mobileRecvCodeCountingdown = false
        }, b * 1000);
        return false
    },initMobileRegist: function() {
        $(".regist_form .recv_mobile_code .check_code").hover(function() {
            $(this).find("i").show()
        }, function() {
            $(this).find("i").hide()
        });
        $("#phone").change(function() {
            jQuery("#m_code_right").hide();
            jQuery("#m_code_wrong").hide();
            if (jsRegistFed.reSetValidCodeFlage) {
                $("#validPhoneCode").val("6位验证码");
                jsRegistFed.showMobileValidCode();
                jsRegistFed.reSetValidCodeFlage = false
            }
            if (null != jsRegistFed.mobileRecvCodeCountingdownAutotime) {
                clearInterval(jsRegistFed.mobileRecvCodeCountingdownAutotime);
                jsRegistFed.mobileRecvCodeCountingdownAutotime = null;
                jsRegistFed.mobileRecvCodeCountingdown = false
            }
            if (null != jsRegistFed.mobileRecvCodeCountingdownTimeout) {
                clearTimeout(jsRegistFed.mobileRecvCodeCountingdownTimeout);
                jsRegistFed.mobileRecvCodeCountingdownTimeout = null
            }
            $("#phone_desc").css("display", "none");
            $(".receive_code", ".recv_mobile_code").html("获取验证码");
            var c = $(this).val();
            if (c == "" || c == "请输入手机号码") {
                showPhoneError("手机号码不能为空");
                return false
            }
            var d = /^(13|15|18|14|17)[0-9]{9}$/;
            if (!d.test(c)) {
                showPhoneError("格式错误，请输入正确的手机号码");
                return false
            }
            $.ajax({type: "POST",url: "/check/check_phone.do",data: {validPhone: $("#phone").val()},success: function(b) {
                    if (b.checkResult == 0) {
                        jQuery("#phone_tip").hide();
                        $("#phone_desc").css("display", "block");
                        jQuery("#phone").parents("li").removeClass("cur_error");
                        var a = jQuery("#validCodeMobile");
                        a.removeAttr("readonly");
                        a.css("background", "")
                    } else {
                        if (b.checkResult == 1) {
                            showPhoneError("该手机号已存在，<a href='/passport/login_input.do'>登录</a>")
                        }
                    }
                }})
        })
    },loadFunRegist: function() {
        //jsRegistFed.helpCenterHover();
        //jsRegistFed.registTab();
        //jsRegistFed.registForm(".ipt_username");
        //jsRegistFed.registForm(".ipt_phone");
        //jsRegistFed.registForm(".ipt_code");
        //jsRegistFed.registForm(".ipt_phonecode");
        //jsRegistFed.serviceAgreement();
        //jsRegistFed.rate(0, "arrow_email");
        //jsRegistFed.rate(0, "arrow_mobile");
        //jsRegistFed.paswdStrength("#password_email");
        jsRegistFed.paswdStrength("#password_mobile");
        jsRegistFed.mobileRegist();
        jsRegistFed.initMobileRegist()
    },loadFunEnglishRegist: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_code");
        jsRegistFed.serviceAgreement();
        jsRegistFed.rate(0, "arrow_email");
        jsRegistFed.paswdStrength("#password_email")
    },loadFunRegistSuccess: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.changeNickName();
        jsRegistFed.emailReceive();
        jsRegistFed.paswdStrength("#password");
        jsRegistFed.successRotate()
    },loadFunFindPassword: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_code");
        jsRegistFed.receiveCode()
    },loadFunFindPassword2: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_code");
        jsRegistFed.receiveCode();
        jsRegistFed.rate(0, "arrow");
        jsRegistFed.paswdStrength("#password")
    },loadFunJointLanding: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.rate(0, "arrow");
        jsRegistFed.paswdStrength("#password")
    },loadFunBtbRegist: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_companyName");
        jsRegistFed.registForm(".ipt_linkmanMobile");
        jsRegistFed.registForm(".ipt_landLine");
        jsRegistFed.registForm(".ipt_address1");
        jsRegistFed.registForm(".ipt_linkmanName");
        jsRegistFed.registForm(".ipt_validCode");
        jsRegistFed.rate(0, "arrow");
        jsRegistFed.paswdStrength("#password");
        jsRegistFed.areaSelect();
        jsRegistFed.serviceAgreement()
    }};

function refresh_valid_code() {
    var b = $("img[name='valid_code_pic']");
    var a = "/passport/valid_code.do";
    if (valid_code_service_flag == 1) {
        a = URLPrefix.validCodeShowUrl
    }
    if (b) {
        b.attr("src", a + "?t=" + Math.random())
    }
}

function check_pwd1(g) {
    var h = $("#" + g).val();
    if (h == "") {
    	//密码不能为空
        return 1
    }
    if (h.length > 20) {
    	//密码为6-20位字符
        return 2
    }
    if (h.length < 6) {
    	//密码为6-20位字符
        return 3
    }
    var f = /\s+/;
    if (f.test(h)) {
    	//密码中不允许有空格
        return 4
    }
    var a = /^[0-9]+$/;
    if (a.test(h)) {
    	//密码不能全为数字
        return 5
    }
    var b = /^[a-zA-Z]+$/;
    if (b.test(h)) {
    	//密码不能全为字母，请包含至少1个数字或符号
        return 6
    }
    var i = /^[^0-9A-Za-z]+$/;
    if (i.test(h)) {
    	//密码不能全为符号
        return 7
    }
    if (isSameWord(h)) {
    	//密码不能全为相同字符或数字
        return 8
    }
    var e = "d*" + commonSymbol + "";
    var d = "\\\d+[A-Za-z]|[A-Za-z]+[0-9]+|[A-Za-z]+" + commonSymbol + "[0-9]+|[A-Za-z]+[0-9]+" + commonSymbol + "|" + e + "";
    var c = new RegExp(d);
    if (!c.test(h)) {
        return 10
    }
    return 0
}
function check_pwd2(a) {
    var c = $("#" + a).val();
    var b = $("#" + a + "2").val();
    if (b == "") {
    	//确认密码不能为空
        return 1
    }
    if (c != b) {
    	//两次密码输入不一致
        return 2
    }
    return 0
}
var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
var spliter = ",";
function showoff(b) {
    var a = b.split("_");
    if (a[0] != "password") {
        jQuery("#" + a[0] + "_error").hide();
        jQuery("#" + a[0] + "_tip").show()
    }
    jQuery("#" + b + "").hide()
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
function isSameWord(d) {
	//密码不能全为相同字符或数字
    var b;
    if (d != null && d != "") {
        b = d.charCodeAt(0);
        b = "\\" + b.toString(8);
        var a = "[" + b + "]{" + (d.length) + "}";
        var c = new RegExp(a);
        return c.test(d)
    }
    return true
}
function checkPassWordContent(b) {
    jQuery("#" + b).parents("li").removeClass("cur_error");
    var a = jQuery("#" + b).val();
    if (a.length > 0) {
        changePassStrong(b)
    } else {
        hideOtherTips(b)
    }
}
function registerByPhoneSubmit() {
    //window.location.href = "register_1.htm";
    //return;
    if (!doPhoneSubmit("password_mobile")) {
        return
    }
    var k = jQuery("#validPhoneCode").val();
    console.log("line 547 #validPhoneCode: " + k);
    var o = "";
    if ("" == k || "6位验证码" == k) {
        o = "请输入6位手机验证码"
    } else {
        if (k.length != 6) {
            o = "手机验证码错误"
        }
    }
    if (o != "") {
        $("#validPhoneCode_wrong").show();
        jQuery("#validPhoneCode_wrong").parents("li").addClass("cur_error");
        $(".regist_form .recv_mobile_code").addClass("cur_error");
        $("#mobile_validcode_error").addClass("regist_tips_error");
        $("#mobile_validcode_error").find("p").text(o);
        return
    }
    var l = $("#phone").val();
    var j = $("#password_mobile").val();
    var p = $("#password_mobile2").val();
    var m = new JSEncrypt();
    var n = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXQG8rnxhslm+2f7Epu3bB0inrnCaTHhUQCYE+2X+qWQgcpn+Hvwyks3A67mvkIcyvV0ED3HFDf+ANoMWV1Ex56dKqOmSUmjrk7s5cjQeiIsxX7Q3hSzO61/kLpKNH+NE6iAPpm96Fg15rCjbm+5rR96DhLNG7zt2JgOd2o1wXkQIDAQAB";
    m.setPublicKey(n);
    l = m.encrypt(l);
    j = m.encrypt(j);
    p = m.encrypt(p);
    m.setPublicKey("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    var q = {
    		validPhone: l,
    		"user.password": j,
    		password2: p,
    		validCode: $("#validPhoneCode").val(),
    		returnUrl: $("#returnUrl").val(),
    		activities: $("#activities").val(),
    		lockEmail: $("#lockEmail").val()};
    var r = URLPrefix.passport + "/passport/registerByPhone.do";
    jQuery.post(r, q, function(a) {
        if (a.errorCode == 0 || a.errorCode == 14) {
            window.location = a.returnUrl
        } else {
            if (a.errorCode == 1) {
                showPhoneError("不能为空")
            } else {
                if (a.errorCode == 2) {
                    $("#validPhoneCode_wrong").show();
                    jQuery("#validPhoneCode_wrong").parents("li").addClass("cur_error");
                    $(".regist_form .recv_mobile_code").addClass("cur_error");
                    $("#mobile_validcode_error").addClass("regist_tips_error");
                    $("#mobile_validcode_error").find("p").text("验证码错误")
                } else {
                    if (a.errorCode == 15) {
                        showPhoneError("格式错误，请输入正确的手机号")
                    } else {
                        if (a.errorCode == 16) {
                            showPhoneError("该手机号已存在，<a href='/passport/login_input.do'>登录</a>")
                        } else {
                            if (a.errorCode == 4) {
                                showPass2Error("password_mobile", "两次密码输入不一致")
                            } else {
                                if (a.errorCode == 5) {
                                    showPassError("password_mobile", "您的密码存在安全隐患,请更改 ")
                                } else {
                                    if (a.errorCode == 13) {
                                        alert("系统异常")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    })
}
function doSubmitPwd(f) {
    var e = check_pwd1(f);
    console.log("check_pwd1: " + e);
    if (e == 1) {
        showPassError(f, "密码不能为空");
        return false
    }
    if (e == 2) {
        showPassError(f, "密码为6-20位字符");
        return false
    }
    if (e == 3) {
        showPassError(f, "密码为6-20位字符");
        return false
    }
    if (e == 4) {
        showPassError(f, "密码中不允许有空格");
        return false
    }
    if (e == 5) {
        showPassError(f, "密码不能全为数字");
        return false
    }
    if (e == 6) {
        showPassError(f, "密码不能全为字母，请包含至少1个数字或符号 ");
        return false
    }
    if (e == 7) {
        showPassError(f, "密码不能全为符号");
        return false
    }
    if (e == 8) {
        showPassError(f, "密码不能全为相同字符或数字");
        return false
    }
    var d = check_pwd2(f);
    if (d == 1) {
        showPass2Error(f, "确认密码不能为空");
        return false
    } else {
        if (d == 2) {
            showPass2Error(f, "两次密码输入不一致");
            return false
        }
    }
}
function check_phone() {
    var a = jQuery("#phone").val();
    if (a == "" || a == "请输入手机号码") {
    	//不能为空
        return 1
    }
    var b = /^1\d{10}$/;
    if (!b.test(a)) {
    	//格式错误，请输入正确的手机号码
        return 2
    }
    return 0
}

function checkPasswordOnBlur(c) {
    hideOtherTips(c);
    var a = check_pwd1(c);
    console.log("check_pwd1 line663: " + a);
    if (a != 0) {
        jQuery("#" + c + "2").attr("readonly", "readonly")
    }
    if (a == 1) {
        showPassError(c, "密码不能为空")
    } else {
        if (a == 2) {
            showPassError(c, "密码为6-20位字符")
        } else {
            if (a == 3) {
                showPassError(c, "密码为6-20位字符")
            } else {
                if (a == 4) {
                    showPassError(c, "密码中不允许有空格")
                } else {
                    if (a == 5) {
                        showPassError(c, "密码不能全为数字")
                    } else {
                        if (a == 6) {
                            showPassError(c, "密码不能全为字母，请包含至少1个数字或符号 ")
                        } else {
                            if (a == 7) {
                                showPassError(c, "密码不能全为符号")
                            } else {
                                if (a == 8) {
                                    showPassError(c, "密码不能全为相同字符或数字")
                                } else {
                                    var b;
                                    //by goodrich
                                    //if (c.indexOf("phone") > -1) {
                                    if (c.indexOf("mobile") > -1) {
                                    	console.log("if_1: phone:" + $("#phone").val());
                                        b = {userName: $("#phone").val(),password: $("#" + c).val()}
                                    } else {
                                    	console.log("if_1: email:" + $("#email").val());
                                        b = {userEmail: $("#email").val(),password: $("#" + c).val()}
                                    }
                                    jQuery.ajax({
                                        type: "POST",
                                        url: "/check/check_unsafeInfo.do",
                                        data: b,
                                        success: function(d) {
                                            switch (d.checkResult) {
                                                case 1:
                                                    showPassError(c, "您的密码存在安全隐患,请更改 ");
                                                    break;
                                                case 0:
                                                    jQuery("#" + c + "2").removeAttr("readonly");
                                                    break;
                                                default:
                                                    jQuery("#" + c + "2").removeAttr("readonly");
                                                    break
                                            }
                                        }
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
function getPassPoint(b) {
    var a = jQuery(b).val();
    var c = checkPassLength(a);
    c = c + checkPassSymbol(a);
    c = c + checkPassNumAndWord(a);
    c = c + (checkPassAll(a));
    c = c + checkPassAlpha(a);
    c = c + checkPassNum(a);
    return c
}

function checkPassLength(a) {
    if (a.length > 6 && a.length < 8) {
        return 10
    }
    if (a.length >= 8) {
        return 25
    }
    return 0
}
function checkPassSymbol(a) {
    if (getSymbolPattern(2).test(a)) {
        return 25
    } else {
        if (getSymbolPattern(1).test(a)) {
            return 10
        }
    }
    return 0
}
function getSymbolPattern(b) {
    var c = "" 
    	+ commonSymbol.substr(0, commonSymbol.length - 1) 
    	+ "{" + b + ",}";
    var a = new RegExp(c);
    return a
}
var patternNumAlpha = /^(?=.*\d.*)(?=.*[a-zA-Z].*)./;
function checkPassNumAndWord(a) {
    if (patternNumAlpha.test(a)) {
        return 5
    }
    return 0
}
function checkPassAll(a) {
    if (isDigit(a) && isBigWord(a) && isSmallWord(a) && isSymbol(a)) {
        return 5
    }
    if (patternNumAlpha.test(a)) {
        if (isSymbol(a)) {
            return 3
        } else {
            return 2
        }
    }
    return 0
}

function isDigit(b) {
    var a = /(?=.*[0-9])/;
    return getCompareResult(a, b)
}
function isBigWord(b) {
    var a = /(?=.*[A-Z])/;
    return getCompareResult(a, b)
}
function isSmallWord(b) {
    var a = /(?=.*[a-z])/;
    return getCompareResult(a, b)
}
function isSymbol(b) {
    var a = "(?=.*" + commonSymbol.substr(0, commonSymbol.length - 1) + ")";
    var c = new RegExp(a);
    return getCompareResult(c, b)
}
function getCompareResult(a, b) {
    if (a.test(b)) {
        return true
    }
    return false
}
function checkPassAlpha(a) {
    var b = /^[a-z]+$|^[A-Z]+$/;
    if (b.test(a)) {
        return 10
    }
    var c = /.*?[A-Z]+?.*?[a-z]+?.*?|.*?[a-z]+?.*[A-Z]+?.*?/;
    if (c.test(a)) {
        return 25
    }
    return 0
}
function checkPassNum(a) {
    if (getNumPattern(3).test(a)) {
        return 20
    }
    if (getNumPattern(1).test(a)) {
        return 10
    }
    return 0
}
function getNumPattern(b) {
    var c = "[0-9]{" + b + ",}";
    var a = new RegExp(c);
    return a
}
function passwordOnFocus(b) {
    var a = jQuery("#" + b);
    if (a.val() == "") {
        hideOtherTips(b);
        return
    }
    checkPassWordContent(b);
    hideOtherTips(b + "2");
    showoff(b + "2_desc")
}
function hideOtherTips(a) {
    if (jQuery("#" + a + "").val() == "") {
        jQuery("#" + a + "_error").hide();
        jQuery("#" + a + "_tip").show()
    }
    jQuery("#" + a + "").parents("li").removeClass("cur_error")
}

function changePassStrong(b) {
    var a = jQuery("#" + b);
    if (check_pwd1(b) == 0) {
        jQuery("#" + b + "2").removeAttr("readonly");
        jQuery("#" + b + "2").css("background-color", a.css("background-color"))
    } else {
        jQuery("#" + b + "2").attr("readonly", "readonly");
        jQuery("#" + b + "2").css("background-color", "#D2D2D5")
    }
    if (a.val().length == 0) {
        jQuery("#" + b + "_Level").hide();
        hideOtherTips(b);
        return
    } else {
        jQuery("#" + b + "_tip").hide();
        jQuery("#" + b + "_error").hide()
    }
}
function doPhoneSubmit(c) {
    if (doSubmitPwd(c) == false) {
        return false
    }
    var d = check_phone();
    if (d == 1) {
        showPhoneError("不能为空");
        return false
    } else {
        if (d == 2) {
            showPhoneError("格式错误，请输入正确的手机号码");
            return false
        }
    }
    return true
}

