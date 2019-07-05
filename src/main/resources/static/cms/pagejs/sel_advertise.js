$(function(){
    $(".loading_area").fadeIn();
    $("#advertise").addClass("dh_dl_open");
    init();
    $('#name').bind('input propertychange', function(){
        clearTimeout(sel_flag);
        sel_flag = setTimeout(function(){
            name = $('#name').val();
            start = 1;
            init();
        }, 1000);
    });
    $(".loading_area").fadeOut(300);
});
var sel_flag;
var start=1, limit=10, name="";
function init(){
    $(".loading_area").fadeIn();
    ajax("cmsAdvertise/list", {start: start, limit: limit, name: name}, sel_callback);
}
function sel_callback(data){
    $(".loading_area").fadeOut(300);
    if(data.recode==0){
        var content='<table border="1" class="table"><tr>'
            +'<td width="50px" align=center>序号</td>'
            +'<td width="100px" align=center>广告图片</td>'
            +'<td align=center>广告名称</td>'
            +'<td width="15%" align=center>状态</td>'
            +'<td width="15%" align=center>最后操作时间</td>'
            +'<td width="15%" align=center>添加时间</td>'
            +'<td width="15%" align=center>操作</td></tr>';
        if(data.result.count==0){
            content+='<tr><td align=center colspan="6"><font color="red">未 查 到 数 据</font></td></tr>';
            content+='</table>';
            $('#content').html(content);
        }else{
            list = data.result.list;
            for(var i=0; i<data.result.list.length; i++){
                var obj=data.result.list[i];
                content+='<tr><td width="50px" align=center>'+((start-1)*limit+i+1)+'</td>'
                    +'<td height="100px" align=center>'+(obj.goodsImg==""?"无图":'<img style="width:100px;height:100px;" src="'+imgPath+obj.advertiseLogo+'">')+'</td>'
                    +'<td align=center>'+obj.advertiseName+'</td>'
                    +'<td align=center>'+(obj.advertiseIsOnline==0?"<font color='#a52a2a'>下架</font>":obj.advertiseIsOnline==1?"<font color='green'>上架</font>":"")+'</td>'
                    +'<td align=center>'+obj.advertiseUpdateTime+'</td>'
                    +'<td align=center>'+obj.advertiseCreateTime+'</td>'
                    +'<td align=center ><button class="online_fun" dataid="'+obj.id+'" style="width:100px;height:35px;">'+(obj.advertiseIsOnline==0?"上架":obj.advertiseIsOnline==1?"下架":"")+'</button>'
                    +'<button class="upd_fun" index="'+i+'" style="width:100px;height:35px;margin-left: 10px;">详情</button></td></tr>';
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
            $('.online_fun').click(function(){
                var obj_id = $(this).attr("dataid");
                $.jBox.confirm("确认修改广告状态吗？", "操作提示", function(v){
                    if(v){
                        updOnline(obj_id);
                    }
                }, { buttons: { '确认': true, '取消': false} });
            });
            $('.upd_fun').click(function(){
                var obj = list[$(this).attr("index")];
                window.sessionStorage.setItem("advertiseUpd_obj", JSON.stringify(obj));
                window.location.href="sel_advertise_a.html";
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

function updOnline(id){
    ajax("cmsAdvertise/online", {id: id}, updOnline_callback);
}
function updOnline_callback(data){
    if(data.recode==0){
        init();
    }else{
        $.jBox.tip(data.msg);
    }
}