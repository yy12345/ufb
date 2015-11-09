/*
 * JQuery UI Datepicker 本地化配置文件
 */
jQuery(function($){
    $.datepicker.regional['zh-CN'] = {
		//currentText: '今天', //按钮的文字，暂时没用到
        //closeText: '关闭', //按钮的文字，暂时没用到
        prevText: '上一月',
        nextText: '下一月',
        //monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'], //暂时没用到
        monthNamesShort: ['一','二','三','四','五','六','七','八','九','十','十一','十二'],
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        //dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'], //暂时没用到
        dayNamesMin: ['日','一','二','三','四','五','六'],
        //weekHeader: '周', //暂时没用到
        dateFormat: 'yy-mm-dd', //格式
        firstDay: 0, //每周的第一天是星期几 0:星期天
        isRTL: false,
        showMonthAfterYear: true, //月显示在年后
        yearSuffix: ' 年 '};
    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);  
});