$(function(){
	$("#bh").addClass("dh_dl_open");
	$("#bhMySel").addClass("active");
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
var start=1, limit=15, startTime='', endTime='', userName='', adminName='';
var list;
function init(){
	$(".loading_area").fadeIn();
	userName = $('#userName').val();
	/*adminName = $('#adminName').val();*/
	startTime = $('#startTime').val();
	endTime = $('#endTime').val();
	ajax("cmsArticle/sel",
			{start: start, 
			limit: limit, 
			startTime: startTime, 
			endTime: endTime, 
			userName: userName,
			adminName: "",
			adminId:window.sessionStorage.getItem("login_id") 
			},
			sel_callback);
}
function sel_callback(data){
	$(".loading_area").fadeOut(300);
	if(data.recode==1){
		var content='<table border="1" class="table"><tr>'
			+'<td width="50px" align=center>序号</td>'
			+'<td width="8%" align=center>&nbsp;&nbsp;&nbsp;&nbsp;代理名称</td>'
			+'<td width="8%" align=center>收货人</td>'
			+'<td width="8%" align=center>收货电话</td>'
			+'<td width="8%" align=center>收货地址</td>'
			+'<td width="8%" align=center>备货金额</td>'
			+'<td align=center>备货备注</td>'
			+'<td width="8%" align=center>结算时间</td>'
			+'<td width="8%" align=center>操作时间</td>'
			+'<td width="8%" align=center>操作人</td>'
			+'<td width="20%" align=center>详情</td></tr>';
		if(data.result.count==0){
			content+='<tr><td align=center colspan="11"><font color="red">未 查 到 数 据</font></td></tr>';
			content+='</table>';
			$('#content').html(content);
		}else{
			list = data.result.list;
			for(var i=0; i<data.result.list.length; i++){
				var obj=data.result.list[i];
				content+='<tr><td width="50px" align=center>'+((start-1)*limit+i+1)+'</td>'
				+'<td align=center>'+(obj.cyn!="0"?"<img src='images/c.png' style='width: 20px;'/>":obj.cyn=="0"?"":"")+'&nbsp;&nbsp;'+(obj.xyn!="0"?"<img src='images/x.png' style='width: 20px;'/>":obj.xyn=="0"?"":"")+'&nbsp;&nbsp;'+obj.userName+'</td>'
				+'<td align=center>'+obj.shrName+'</td>'
				+'<td align=center>'+obj.shrPhone+'</td>'
				+'<td align=center>'+obj.shrAddr+'</td>'
				+'<td align=center><font color="orage">'+utils.toDouble(obj.bhMoney)+'</font></td>'
				+'<td align=center>'+obj.remark+'</td>'
				+'<td align=center>'+obj.time+'</td>'
				+'<td align=center>'+obj.createTime+'</td>'
				+'<td align=center>'+obj.adminName+'</td>'
				+'<td align=center><button class="details_fun" index="'+i+'" style="width:80px;height:30px;margin-right: 20px;">详 情</button>'
				+(obj.userAddrId!=1?'<button class="bhupd_fun" index="'+i+'" style="width:80px;height:30px;margin-right: 20px;">修 改</button>':"")
				/*+'<button class="bhupd_fun" index="'+i+'" style="width:80px;height:30px;margin-right: 20px;">修 改</button>'*/
				+'<button class="bhcf_fun" index="'+i+'" style="width:80px;height:30px;">拆 分</button></td>'
				+'</tr>';
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
			$('.details_fun').click(function(){
				var obj = list[$(this).attr("index")];
				window.sessionStorage.setItem("bhMaster_obj", JSON.stringify(obj));
				window.location.href="bhMySelDetails.html";
			});
			$('.bhupd_fun').click(function(){
				var obj = list[$(this).attr("index")];
				window.sessionStorage.setItem("bhMaster_obj_upd", JSON.stringify(obj));
				window.location.href="bhMySelUpd.html";
			});
			$('.bhcf_fun').click(function(){
				var obj = list[$(this).attr("index")];
				window.sessionStorage.setItem("bhMaster_obj_cf", JSON.stringify(obj));
				window.location.href="bhMySelCf.html";
			});
		}
		$('#sum').html(utils.toDouble(data.result.sum));
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