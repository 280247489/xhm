$(function(){
    $(".loading_area").fadeIn();
	$("#article").addClass("dh_dl_open");
	$("#sel_article").addClass("active");
	$('#userName').bind('input propertychange', function(){
		clearTimeout(sel_flag);
		sel_flag = setTimeout(function(){
			start = 1;
			init();
		}, 1000);
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
});
var sel_flag, time_flag;
var start=1, limit=15, startTime='', endTime='', userName='', tid='', online='', check='', del='';
var list;
function init(){
	$(".loading_area").fadeIn();
	userName = $('#userName').val();
    tid = $('#tid').val();
    online = $('#online').val();
    check = $('#check').val();
    del = $('#del').val();
	startTime = $('#startTime').val();
	endTime = $('#endTime').val();
	//adminId:window.sessionStorage.getItem("login_id")
	ajax("cmsArticle/sel",
			{userName: userName,
				tid: tid,
                online: online,
                check: check,
    			del: del,
                startTime: startTime,
                endTime: endTime,
                start: start,
                limit: limit
			},
			sel_callback);
}
function sel_callback(data){
	$(".loading_area").fadeOut(300);
    if(data.state=="success"){
        if(data.recode==0){
            var content='<table border="1" class="table"><tr>'
                +'<td width="50px" align=center>序号</td>'
                +'<td width="8%" align=center>栏 目</td>'
                +'<td width="8%" align=center>logo</td>'
                +'<td width="8%" align=center>标 题</td>'
                +'<td width="8%" align=center>发布时间</td>'
                +'<td width="8%" align=center>上下架状态</td>'
                +'<td width="8%" align=center>点赞数</td>'
                +'<td width="8%" align=center>阅读数</td>'
                +'<td width="8%" align=center>分享数</td>'
                +'<td width="8%" align=center>审核状态</td>'
                +'<td width="8%" align=center>审核时间</td>'
                +'<td align=center>详情</td></tr>';
            if(data.data.count==0){
                content+='<tr><td align=center colspan="11"><font color="red">未 查 到 数 据</font></td></tr>';
                content+='</table>';
                $('#content').html(content);
            }else {
                list = data.data.data.list;
                for (var i = 0; i < list.length; i++) {
                    var obj = list[i];
                    content += '<tr><td width="50px" align=center>' + ((start - 1) * limit + i + 1) + '</td>'
                        + '<td align=center>' + obj.typeId + '</td>'
                        + '<td align=center>' + obj.articleLogo + '</td>'
                        + '<td align=center>' + obj.articleTitle + '</td>'
                        + '<td align=center>' + obj.articleCreateTime + '</td>'
                        + '<td align=center>' + (obj.articleOnline == 1 ? "<font color='green'>上架</font>" : "<font color='red'>下架</font>") + '</td>'
                        + '<td align=center>' + obj.articleTotalLike + '</td>'
                        + '<td align=center>' + obj.articleTotalView + '</td>'
                        + '<td align=center>' + obj.articleTotalShare + '</td>'
                        + '<td align=center>' + (obj.articleCheckYn == 0 ? "<font color='orange'>审核中</font>" : obj.articleCheckYn == 1 ? "<font color='green'>通过</font>" : "<font color='red'>驳回</font>") + '</td>'
                        + '<td align=center>' + obj.articleCheckTime + '</td>'
                        + '<td align=center>'
                        + '<button class="details_fun" index="' + i + '" style="width:80px;height:30px;margin-right: 20px;">详 情</button>'
                        + '<button class="bhcf_fun" index="' + i + '" style="width:80px;height:30px;">' + (obj.articleOnline == 1 ? "下架" : "上架") + '</button>'
                        + '</td>'
                        + '</tr>';
                }
                content += '</table>';
                content += createPage(data.data.data.count);
                $('#content').html(content);
                $('.fy').click(function () {
                    if (start != $(this).html()) {
                        start = $(this).html() == "第一页" ? 1 : $(this).html() == "最后一页" ? sum : $(this).html();
                        init();
                    }
                });
            }
		}else{
            $.jBox.tip(data.msg);
        }
	}else{
		$.jBox.tip(data.msg);
	}
}
//分页
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