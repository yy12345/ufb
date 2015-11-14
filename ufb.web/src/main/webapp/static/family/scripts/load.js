
/* function - alert */
var alertBox = function (el) {//遮罩层
    var $this = $(el);
    if($this.length ==0) {
        return false;
    }
    $("body").prepend("<div class='alert-con'><div class='alert-bg'></div><a class='alert-close'></a>"+$this.html()+"</div>");
};
$("body").on("click", ".alert-close", function () {
    var $this = $(this);
    var $con = $this.parents(".alert-con");
    console.log($con);
    $con.hide();
});
var errorBox = function (errorTitle, errorText) {//错误框
    var errorBox=$("<div class='error-box'>" +
        "<div class='error-title'>"+errorTitle+"<a class='error-close'></a></div>" +
        "<div class='error-content'>"+errorText+"</div>" +
        "</div>");
    $("body").prepend(errorBox);
    var errorBoxHeight = errorBox.outerHeight();
    if(errorBoxHeight>0) {
        errorBox.css("marginTop",0-errorBoxHeight/2+"px").show();
    }else {
        errorBox.remove();
    }
};
$("body").on("click", ".error-close", function () {
    var $this = $(this);
    var $con = $this.parents(".error-box");
    $con.remove();
});
$(function () {
    $(".shadow-normal").on("mouseenter", function () {
        $(this).addClass("shadow-hover");
    }).on("mouseleave", function () {
        $(this).removeClass("shadow-hover");

    });
    $(".nav").on("click",".member",function () {
        $(this).toggleClass("on");
    });
    $('#index_kv').length>0 && $('#index_kv').camera({
        height:"400px",
        loader:"none",
        fx:"scrollLeft",
        navigation: false,
        playPause:false,
        time:2000,
        transPeriod:1000
    });
    /*
     event - tab
     */
    var tabSlide = function (tab) {
        var _tab = tab;
        var _tabs = _tab.parent(".tabs");
        var _index = _tabs.find(".tab").index(_tab);
        var _panel = _tabs.siblings(".panels").find(".panel");
        _tab.addClass("on").siblings().removeClass("on");
        _panel.addClass("slide-out").eq(_index).removeClass("slide-out");
    }
    $(".register-tabs").on("click", ".tab", function () {
        tabSlide($(this));
    });
    $(".project-item .tabs").on("mouseenter", ".tab", function () {
        tabSlide($(this));
    });
    $(".password-tabs").on("click", ".tab", function () {
        tabSlide($(this));
    });
    $(".info-account .tabs").on("click", ".tab", function () {
        tabSlide($(this));
    });
    /*
     event - form
     */
    $("form").each(function () {
        var _this = $(this);
        _this[0].reset();
    });
    $("form").on("click", ".tips-normal", function () {
        $(this).hide().siblings("input[type=text],input[type=password]").focus();
    });
    $("form").on("focus", "input[type=text],input[type=password]", function () {
        $(this).addClass("focus").siblings(".tips-normal").hide();
    });
    $("form").on("blur", "input[type=text],input[type=password]", function () {
        var _this = $(this);
        _this.removeClass("focus").siblings(".tips-normal").hide();
        if(_this.val()=="") {
            _this.siblings(".tips-normal").show();
        }
    });
    $(".input-select *:first-child").on("click", function () {
        var _this = $(this);
        _this.parent().toggleClass("open");
        if($('.mb10').css('display') == 'none'){
            $(".bgGray").css('display','none');
        }

    });
    $(".input-options .bank-list").on("click", ".bank", function () {
        var _this = $(this);
        var _select = $(".input-select");
        _select.removeClass("open").find(".bank-current").html(_this.html());
        $(".bgGray").hide().find(".bank-current").html(_this.html());
    });
    $(".input-options .card-list").on("click", ".card", function () {
        var _this = $(this);
        var _select = $(".input-select");
        _select.removeClass("open").find(".card-current").html(_this.html());
    });
    /* ex - card */
    $(".setting").on("mouseenter",".card", function () {
        $(this).addClass("card-hover");
    }).on("mouseleave", ".card", function () {
        $(this).removeClass("card-hover");
    });

    /* trigger - loginBox */
    $("body").on("click", ".trigger-loginBox", function () {
       alertBox(".box-login");
    });

    //errorBox("系统错误","报错内容<br>报错内容<br>报错内容<br>");
});


$(function(){
     //“登陆框”切换
    
    $(".tab-family").click(function(){
         $(this).addClass("on").siblings().removeClass("on");
         $(".tab-family i").addClass("loginTri").parents().siblings().find("i").removeClass("loginTri");
         $(".panel01").addClass("panelShow").siblings().removeClass("panelShow");
    });
    
     $(".tab-org").click(function(){
         $(this).addClass("on").siblings().removeClass("on");
         $(this).find("i").addClass("loginTri").parents().siblings().find("i").removeClass("loginTri");
         $(".panel02").addClass("panelShow").siblings().removeClass("panelShow");
    });
    
    //"自动充值、自动取现"切换
    $(".content-autoFund .autoFund-title span:first-child").click(function(){
         $(this).addClass("on").siblings().removeClass("on");
         $(this).find("i").addClass("loginTri").parents().siblings().find("i").removeClass("loginTri");
         $(".FundList01").addClass("FundList").siblings().removeClass("FundList");
    });
     $(".content-autoFund .autoFund-title span:last-child").click(function(){
         $(this).addClass("on").siblings().removeClass("on");
         $(this).find("i").addClass("loginTri").parents().siblings().find("i").removeClass("loginTri");
         $(".FundList02").addClass("FundList").siblings().removeClass("FundList");
    });
    
    $(".topNav .b3 a").click(function(){
        $(".alert-con").show();
    });
    
	// by Tracy 09-12 
    $(".container .u2 .v2_1").click(function(){
        $(".container .u2 .v2").hide();
    });
    
    $(".input-select .bank-list .banktip p").click(function(){
        $(".input-select .bank-list .banktip .down").toggleClass("up");
        $(".input-select.open .bank-list.bgGray").toggle();
    });
    $(".input-select .bank-list.bgGray p").click(function(){
        $(".input-select.open .bank-list.bgGray").hide();
    });
    
    $(".liBox .trip").mouseover(function(){
        $(this).find("div").show();
    });
      $(".liBox .trip").mouseout(function(){
        $(this).find("div").hide();
    });  
    $(".common-form .safeInfo li span a").mouseover(function(){
        $(this).find("div").show();
    });
    $(".common-form .safeInfo li span a").mouseout(function(){
        $(this).find("div").hide();
    });
    
    $(".common-form .safeInfo .provinces").click(function(){
        $(".common-form .safeInfo .inputL .chose").toggle();
        $(this).css("background","#ffffff url(../images/icon.png) no-repeat 94px -147px");
    });
    $(".common-form .safeInfo .inputL .chose li").click(function(){
        $(".common-form .safeInfo .provinces").html($(this).html()).css("background","#ffffff url(../images/icon.png) no-repeat 94px -111px");
        $(".common-form .safeInfo .inputL .chose").hide();
    });
    
    $(".common-form .safeInfo .city").click(function(){
        $(".common-form .safeInfo .inputR .choseCity").toggle();
        $(this).css("background","#ffffff url(../images/icon.png) no-repeat 94px -147px");
    });
    $(".common-form .safeInfo .inputR .choseCity li").click(function(){
        $(".common-form .safeInfo .city").html($(this).html()).css("background","#ffffff url(../images/icon.png) no-repeat 94px -111px");
        $(".common-form .safeInfo .inputR .choseCity").hide();

    });
   
    var ex;
    $(".a3 .b2 .tabs").find("a").click(function(){
        $(this).addClass("active")
           .siblings().removeClass("active");
            ex = $(this).index()+1;
        $('.a3 .b2 .tabs').find('#main'+ex).addClass('showNow')
                 .siblings().removeClass('showNow');
    });
    
    $(".common-form li input.ipt-focus").focus(function(){
         $(".common-form .safeInfo .fontBigger").show();
    });
     $(".common-form li input.ipt-focus").focusout(function(){
         $(".common-form .safeInfo .fontBigger").hide();
    });
    
    $(".profile .a5 #bx-pager a").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });

    $(".help .side-nav li").find("a").click(function(){
        $(this).addClass("current").parents().siblings().find("a").removeClass("current");
    });
    
    $(".nav a").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });
    
    $('#card2').cardFormat("####,####,####,####,###");
});