$(function () {
    $(".shadow-normal").on("mouseenter", function () {
        $(this).addClass("shadow-hover");
    }).on("mouseleave", function () {
        $(this).removeClass("shadow-hover");

    })
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
    $(".register-tabs .tab").each(function (index) {
        var tab = $(this);
        var panels = $(".register-panels .panel");
        tab.bind("click", function () {
            tab.addClass("on").siblings().removeClass("on");
            panels.addClass("slide-out").eq(index).removeClass("slide-out");
        });
    });
    $(".project-item .tabs").on("mouseenter", ".tab", function () {
        var _tab = $(this);
        var _tabs = _tab.parent(".tabs");
        var _index = _tabs.find(".tab").index(_tab);
        var _panel = _tabs.siblings(".panels").find(".panel");
        _tab.addClass("on").siblings().removeClass("on");
        _panel.addClass("slide-out").eq(_index).removeClass("slide-out");
    });
    $(".password-tabs").on("click", ".tab", function () {
        var _tab = $(this);
        var _tabs = _tab.parent(".tabs");
        var _index = _tabs.find(".tab").index(_tab);
        var _panel = _tabs.siblings(".panels").find(".panel");
        _tab.addClass("on").siblings().removeClass("on");
        _panel.addClass("slide-out").eq(_index).removeClass("slide-out");
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
    });
    $(".input-options .bank-list").on("click", ".bank", function () {
        var _this = $(this);
        var _select = $(".input-select");
        _select.removeClass("open").find(".bank-current").html(_this.html());
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
});