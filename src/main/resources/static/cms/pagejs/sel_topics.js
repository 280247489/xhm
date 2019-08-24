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
	$('#topicName').change(function(){
		init();
	});
	$('#articleTypeId').change(function(){
		init();
	});
	$('#topicStatus').change(function(){
		init();
	});

	$('#sortType').change(function(){
		init();
	});

	$(".create-topics").click(function () {
		create_topics();

	})



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
	 console.log("init beginTime=",beginTime)

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
            list = data.data.data;
          //  fileUrl = data.result.fileUrl;
            for (var i = 0; i < data.data.data.length; i++) {
                var obj = list[i];
                content+= '<tr><td width="50px" align=center>' + ((start - 1) * limit + i + 1) + '</td>'
					+ '<td align=center>' + obj.articleTypeId + '</td>'
					+ '<td align=center>' + obj.topicName+ '</td>'
					+ '<td align=center>' +  obj.topicSum  + '</td>'
					+ '<td align=center>' +obj.topicSort + '</td>'
					+ '<td align=center>' + obj.topicCreateUser + '</td>'
					+ '<td align=center>' +  obj.topicCreateTime + '</td>'
					+ '<td align=center id="topicStatus_'+obj.id+'">' + (obj.topicStatus == 0 ? '<font color="orange">未启用</font>':'<font color="green">启用</font>')+'</td>'
					+ '<td align=center>'
					//+ '<button class="info_fun" index="' + i + '" style="width:80px;height:30px;margin-right: 20px;">详 情</button>'
					+'<button class="check_fun" id="btn_'+obj.id+'" aid="'+obj.id+'" value="'+obj.topicStatus+'" index="' + i + '" style="width:80px;height:30px;">' +
					(obj.topicStatus == 0 ? '启用话题':'禁用话题')+
					'</button>'
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


         //   $('.info_fun').click(function(){


            /*    var obj = list[$(this).attr("index")];
                window.sessionStorage.setItem("article_info_obj", JSON.stringify(obj));
                window.sessionStorage.setItem("fileUrl", fileUrl);
                window.location.href="article_info.html";*/
          //  });
            $('.check_fun').click(function(){
                if(confirm("确认启用？")){
                	console.log("value ========",$('.check_fun').val());
                    var aid = $(this).attr('aid');
                    info(aid, $('.check_fun').val());
                }
            });

        }
	}else{
		$.jBox.tip(data.msg);
	}
}
function info(aid, check){
	beginTime = $('#startTime').val();
	endTime = $('#endTime').val();
	articleType =$('#articleTypeId').val();
	sortType =$('#sortType').val();
	topicStatus =$('#topicStatus').val();
	topicName = $('#topicName').val();

   // $(".loading_area").fadeIn();
    ajax("cmsTopics/changeStatus",
        { id: aid, topicCreateUserId: sessionStorage.getItem("login_id"), topicCreateUser:sessionStorage.getItem("login_name")},
        function(data){
            $(".loading_area").fadeOut(300);
			if(data.code == 0){
				init();

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

function create_topics() {

	var topicName = $(".articleName_add").val();
	var articleType="推荐"/*$("#articleTypeId_add").val();*/
	var sort=$(".sort_add").val();
	var userId = window.sessionStorage.getItem("login_id");
	var userName = window.sessionStorage.getItem("login_name");


	if(!topicName || !articleType || !sort ){
		alert("请完善话题信息再提交")
		return;
	}
	console.log("topicName=",topicName)
	console.log("articleType=",articleType)
	console.log("sort1=",sort)


	ajax("cmsTopics/add",
		{
			topicName: topicName,
			articleTypeId: articleType,
			topicCreateUserId: userId,
			topicCreateUser: userName,
			topicSort:sort

		},
		create_callback);

}

function create_callback(data) {
	$(".loading_area").fadeOut(300);
	if( data.code==0){

		//关闭弹出层
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
		$(".articleName_add").val('');
		$("#articleTypeId_add").val('');
		$(".sort_add").val('');

		//初始化列表
		init()

	}else{
		alert(data.msg)
		//$.jBox.tip(data.msg);
	}

}



