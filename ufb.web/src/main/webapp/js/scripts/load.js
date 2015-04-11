$(function () {
    /* index */
    $(".main .section").on("mouseenter", function () {
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
    /*
     event - form
     */
    $("form").each(function () {
        var _this = $(this);
        _this[0].reset();
    });
    $("form").on("click", ".tips-normal", function () {
        $(this).hide().siblings("input[type=text]").focus();
    });
    $("form").on("focus", "input[type=text]", function () {
        $(this).addClass("focus").siblings(".tips-normal").hide();
    });
    $("form").on("blur", "input[type=text]", function () {
        var _this = $(this);
        _this.removeClass("focus").siblings(".tips-normal").hide();
        if(_this.val()=="") {
            _this.siblings(".tips-normal").show();
        }
    });
	$("form").on("click", ".input-select", function () {
		$(this).toggleClass("open");
    });
	$(".input-options img").on("click", function () {
		var _this = $(this);
		_this.parents(".input-options").siblings(".bank").attr("src",_this.attr("src"));
    });

});