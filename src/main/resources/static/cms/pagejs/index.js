$(function(){
	 init();
});
function init(){
	
	$(".loading_area").fadeIn();
	$(".loading_area").fadeOut(300);
	
	clockon();
}
function clockon() {
    var now = new Date();
    var year = now.getFullYear(); //getFullYear getYear
    var month = now.getMonth();
    var date = now.getDate();
    var day = now.getDay();
    var hour = now.getHours();
    var minu = now.getMinutes();
    var sec = now.getSeconds();
    var week;
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;
    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    week = arr_week[day];
    var time = "";
    time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;
    var content_html='<h1 style="color:#5B5B5B;font-size:20px;font-weight:bold;text-align:center;">欢迎     '+(window.sessionStorage.getItem("login_name")==null?"您":window.sessionStorage.getItem("login_name"))+'</h1>'
	+'<p style="color:#2894FF;font-size:16px;font-weight:bold;text-align:center;">今天是: <span id="bgclock"></span>'
	+(hour<12?' 上午好. 又是崭新的一天^_^. ':hour==12?' 中午好. ':hour>12&&hour<=18?' 下午好. ':' 晚上好. ')
	+'<br/>© 2018-2019 DangJia 版权所有——当家科技</p>';
    $('#content').html(content_html);
    $("#bgclock").html(time);

    setTimeout(clockon, 1000);
}