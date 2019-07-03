$(function(){
    $(".loading_area").fadeIn();
	$("#topics").addClass("dh_dl_open");
	$("#sel_topics").addClass("active");
	$('#userName').bind('input propertychange', function(){
		clearTimeout(sel_flag);
		sel_flag = setTimeout(function(){
			start = 1;
			init();
		}, 1000);
	});
	 $('#tid').change(function(){
         init();
	 });
    $('#online').change(function(){
        init();
    });
    $('#check').change(function(){
        init();
    });
    /*$('#del').change(function(){
        init();
    });*/
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
var start=1, limit=10;
var list, fileUrl;
var beginTime,endTime,articleType,sortType,topicStatus,topicName;
function init(beginTime,endTime,articleType,sortType,topicStatus,topicName){
	$(".loading_area").fadeIn();

	 beginTime = $('#startTime').val();
	 endTime = $('#endTime').val();
	 articleType =$('#articleTypeId').val();
	 sortType =$('#sortType').val();
	 topicStatus =$('#topicStatus').val();
	 topicName = $('#topicName').val();

	ajax("cmsTopics/list",
			{
				articleType: articleType,
				sortType: sortType,
				topicStatus: topicStatus,
				topicName: topicName,
				beginTime:beginTime,
				endTime:endTime,
				page: start,
				size: limit
			},
			sel_callback);
}
function sel_callback(data){
	console.log("data",data)
	$(".loading_area").fadeOut(300);
    if( data.code==0){
        var content='<table border="1" class="table"><tr>'
            +'<td width="50px" align=center>序号</td>'
            +'<td width="8%" align=center>栏 目</td>'
            +'<td width="18%" align=center>话题名</td>'
            +'<td width="18%" align=center>话题阅读数</td>'
			+'<td width="8%" align=center>排序</td>'
            +'<td width="8%" align=center>创建人</td>'
            +'<td width="8%" align=center>发布时间</td>'
            +'<td width="8%" align=center>话题状态</td>'
            +'<td align=center>详情</td></tr>';
        if(data.data.totalElements==0){

            content+='<tr><td align=center colspan="11"><font color="red">未 查 到 数 据</font></td></tr>';
            content+='</table>';
            $('#content').html(content);
        }else {

        	console.log("data is ===",data.data.data)
            list = data.data.data;
          //  fileUrl = data.result.fileUrl;
            for (var i = 0; i < data.data.data.length; i++) {

                var obj = list[i];
                console.log('obj ====',obj);
				content += '<tr><td width="50px" align=center>' + ((start - 1) * limit + i + 1) + '</td>'
					+ '<td align=center>' + obj.typeId + '</td>'
					+ '<td align=center><img style="width: 80px; height: 80px;" src="' + data.result.fileUrl + obj.articleLogo + '"></td>'
					+ '<td align=center>' + obj.articleTitle + '</td>'
					//+ '<td align=center>' + obj.articleCreateUserId + '</td>'
					+ '<td align=center>' + obj.articleCreateUserId + '</td>'
					+ '<td align=center>' + obj.articleCreateTime + '</td>'
					+ '<td align=center>阅读数：' + obj.articleTotalView + '<br><br>  <font color="#ffc0cb">点赞数：' + obj.articleTotalLike + '</font><br><br>  <font color="#00ced1">分享数：' + obj.articleTotalShare + '</font></td>'
					+ '<td align=center id="td_'+obj.id+'">' + (obj.articleOnline == 1 ? "<font color='green'>已上架</font>" : "<font color='red'>未上架</font>") + '</td>'
					+ '<td align=center>' + (obj.articleCheckYn == 0 ? "<font color='orange'>审核中</font>" : obj.articleCheckYn == 1 ? "<font color='green'>通过</font>" : "<font color='red'>驳回</font>") + '</td>'
					+ '<td align=center>'
					+ '<button class="info_fun" index="' + i + '" style="width:80px;height:30px;margin-right: 20px;">详 情</button>'
					+ '<button class="online_fun" id="btn_'+obj.id+'" aid="'+obj.id+'" index="' + i + '" style="width:80px;height:30px;">' + (obj.articleOnline == 1 ? "下架" : "上架") + '</button>'
					+ '</td>'
					+ '</tr>';

                content += '<tr><td width="50px" align=center>' + ((start - 1) * limit + i + 1) + '</td>'
                    + '<td align=center>' + obj.articleTypeId + '</td>'
                    + '<td align=center><img style="width: 80px; height: 80px;" src="' + data.topicName + '"></td>'
                    + '<td align=center>' + obj.topicSum + '</td>'
                    + '<td align=center>' + obj.topicSort + '</td>'
                    + '<td align=center>' + obj.topicCreateUser + '</td>'
					+ '<td align=center>' + obj.topicCreateTime + '</td>'
					+ '<td align=center>' + obj.topicStatus ==0?"<font color='orange'>未启用</font>":"<font color='green'>启用</font>" + '</td>'
              //      + '<td align=center>' + (obj.articleCheckYn == 0 ? "<font color='orange'>审核中</font>" : obj.articleCheckYn == 1 ? "<font color='green'>通过</font>" : "<font color='red'>驳回</font>") + '</td>'
                    + '<td align=center>'
                    + '<button class="info_fun" index="' + i + '" style="width:80px;height:30px;margin-right: 20px;">详 情</button>'
                    + '<button class="check1_fun" id="btn_'+obj.id+'" aid="'+obj.id+'" index="' + i + '" style="width:80px;height:30px;margin-right: 20px;">启用话题</button>'
                    + '<button class="check2_fun" id="btn_'+obj.id+'" aid="'+obj.id+'" index="' + i + '" style="width:80px;height:30px;">禁用话题</button>'
                    + '</td>'
                    + '</tr>';
            }
            content += '</table>';
            content += createPage(data.data.totalElements);
            $('#content').html(content);
            $('.fy').click(function () {
                if (start != $(this).html()) {
                    start = $(this).html() == "第一页" ? 1 : $(this).html() == "最后一页" ? sum : $(this).html();
                    init();
                }
            });
            $('.info_fun').click(function(){
                var obj = list[$(this).attr("index")];
                window.sessionStorage.setItem("article_info_obj", JSON.stringify(obj));
                window.sessionStorage.setItem("fileUrl", fileUrl);
                window.location.href="article_info.html";
            });
            $('.check1_fun').click(function(){
                if(confirm("确认启用？")){
                    var aid = $(this).attr('aid');
                    info(aid, 1);
                }
            });
            $('.check2_fun').click(function(){
                if(confirm("确认禁用？")){
                    var aid = $(this).attr('aid');
                    info(aid, 2);
                }
            });
        }
	}else{
		$.jBox.tip(data.msg);
	}
}
function info(aid, check){
    $(".loading_area").fadeIn();
    ajax("cmsTopics/changeStatus",
        { id: aid, topicCreateUserId: sessionStorage.getItem("login_id"), topicCreateUser:sessionStorage.getItem("login_name")},
        function(data){
            $(".loading_area").fadeOut(300);
			if(data.state == "success" && data.recode == 0){
				console.log("beginTime == " ,beginTime);
				//$('#tr_'+aid).remove();
				init(beginTime,endTime,articleType,sortType,topicStatus,topicName);
			}else{
                $.jBox.tip(data.msg);
			}
		});
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
