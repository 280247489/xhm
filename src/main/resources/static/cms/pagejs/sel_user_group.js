if(window.sessionStorage.getItem("user_obj")==null)
    window.location.href="index.html";
var obj = JSON.parse(window.sessionStorage.getItem("user_obj"));
$(function(){
    $(".loading_area").fadeIn();
    $("#user").addClass("dh_dl_open");
    $('#type').change(function(){
        init();
    });
    laydate.render({
        elem: '#startTime',
        /*value: new Date(),*/
        max: 0,
        showBottom: false,
        done: function(value, date){
            var fff=$('#endTime').val();
            if(value>fff)
                fff=time_flag;
            $('#endTime_span').html("").html('<input type="text" id="endTime" style="width:200px;" placeholder="结束时间.." class="textbox"/>');
            $('#endTime').val(fff);
            init();
            laydate.render({
                elem: '#endTime',
                min: value,
                /* max: 0,*/
                showBottom: false,
                done: function(value, date){
                    init();
                }
            });
        }
    });
    laydate.render({
        elem: '#endTime',
        value: new Date(),
        /*	  max: 0,
              min: 0,*/
        showBottom: false
    });
    setTimeout(function(){
        time_flag=$('#endTime').val();
        start = 1;
        init();
    }, 500);
    $(".loading_area").fadeOut(300);
});
var userId = obj.id;
$("#tName").html(obj.userName);
$("#tSum").html(obj.userGroup+"人");
var sel_flag;
var start=1, limit=10, type="",startTime='', endTime='';
function init(){
    $(".loading_area").fadeIn();
    startTime = $('#startTime').val();
    endTime = $('#endTime').val();
    type = $('#type').val();
    ajax("cmsUserGroup/userGroupList", {start: start, limit: limit, userId: userId,startTime:startTime,endTime:endTime,type:type}, sel_callback);
}
function sel_callback(data){
    $(".loading_area").fadeOut(300);
    if(data.recode==0){
        var content='<table border="1" class="table"><tr>'
            +'<td width="50px" align=center>序号</td>'
            +'<td align=center>用户名称</td>'
            +'<td align=center>用户头像</td>'
            +'<td width="15%" align=center>注册时间</td></tr>';
        if(data.result.count==0){
            content+='<tr><td align=center colspan="4"><font color="red">未 查 到 数 据</font></td></tr>';
            content+='</table>';
            $('#content').html(content);
        }else{
            list = data.result.list;
            for(var i=0; i<data.result.list.length; i++){
                var obj=data.result.list[i];
                var userLogo = "";
                if (obj.userLogo.indexOf("http") == -1 ){
                    userLogo = data.result.fileUrl+obj.userLogo;
                }else{
                    userLogo =obj.userLogo;
                }
                content+='<tr><td width="50px" align=center>'+((start-1)*limit+i+1)+'</td>'
                    +'<td align=center>'+obj.userName+'</td>'
                    +'<td align=center><img style="width:100px;height:100px;" src="'+userLogo+'"></td>'
                    +'<td align=center>'+obj.userCreateTime+'</td></tr>';
            }
            content+='</table>';
            content+=createPage(data.result.count);
            $('#content').html(content);
            $('.fy').click(function(){
                if(start!=$(this).html()){
                    start=$(this).html()=="第一页"?1:$(this).html()=="最后一页"?sum:$(this).html();
                    init();
                }
            });

        }
    }else{
        $.jBox.tip(data.msg);
    }
}
function createPage(count){
    sum=Math.ceil(count/limit);
    var c='';
    if(sum<=10){
        c='<section><aside class="paging">';
        for(var i=1; i<=sum; i++){
            if(i==start){
                c+='<a class="fy" style="color:red;">'+i+'</a> ';
            }else {
                c+='<a class="fy">'+i+'</a> ';
            }
        }
        c+='</aside></section>';
    }else{
        c='<section><aside class="paging"><a class="fy">第一页</a> ';
        if(start!=1&&start!=2&&(start==sum||Math.ceil(sum/2)>=start)){
            c+='<a>...</a> ';
        }else if(start!=1&&start!=2&&(Math.ceil(sum/2)<start)){
            c+='<a>..</a> ';
        }
        for(var i=1; i<=sum; i++){
            if(i+1==start||i-1==start){
                c+='<a class="fy">'+i+'</a> ';
            }else if(i==start){
                c+='<a class="fy" style="color:red;">'+i+'</a> ';
            }
        }
        if(start!=sum&&start!=sum-1&&(start==1||Math.ceil(sum/2)<=start)){
            c+='<a>...</a> ';
        }else if(start!=sum&&start!=sum-1&&(Math.ceil(sum/2)>start)){
            c+='<a>..</a> ';
        }
        c+='<a class="fy">最后一页</a></aside></section>';
    }
    return c;
}

$('.top_rt_btn').click(function(){
    window.history.back();
});