$(function(){
    $(".loading_area").fadeIn();
	$("#articleComment").addClass("dh_dl_open");
	$("#sel_articleComment").addClass("active");
	$('#userName').bind('input propertychange', function(){
		clearTimeout(sel_flag);
		sel_flag = setTimeout(function(){
			start = 1;
			init();
		}, 1000);
	});
	$('.key_words').change(function(){
		init();
	});
	$('.article_title').change(function(){
		init();
	});
	$('.user_name').change(function(){
		init();
	});

	$('.comment_type').change(function(){
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
var key_words,article_title,user_name,comment_type,query_start_time,query_end_time;
function init(){
	$(".loading_area").fadeIn();

	key_words = $('.key_words').val();
	article_title=$('.article_title').val();
	user_name=$('.user_name').val();
	comment_type=$('.comment_type').val();
	query_start_time = $('#startTime').val();
	query_end_time = $('#endTime').val();
	console.log("comment_type === " ,comment_type);

	ajax("cmsArticleComment/list",
			{
				page: start,
				size: limit,
				key_words: key_words,
				article_title: article_title,
				user_name:user_name,
				comment_type:comment_type,
				query_start_time: query_start_time,
				query_end_time: query_end_time,
				sort_role:'',
				comment_root_id:'',
				article_id:''
			},
			sel_callback);
}
function sel_callback(data){
	$(".loading_area").fadeOut(300);
    if( data.code==0){
        var content='<table border="1" class="table"><tr>'
            +'<td width="50px" align=center>序号</td>'
			+'<td width="8%" align=center>评论类型</td>'
            +'<td width="8%" align=center>用户昵称</td>'
            +'<td width="18%" align=center>文章名称</td>'
            +'<td width="18%" align=center>评论内容</td>'
			+'<td width="8%" align=center>评论时间</td>'
            +'<td width="8%" align=center>统计信息</td>'
            +'<td align=center>操作</td></tr>';
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
					+ '<td align=center>' +(obj.commentType == 0 ? '评论':'回复') + '</td>'
					+ '<td align=center>' + obj.userName + '</td>'
					+ '<td align=center>' + obj.articleTitle+ '</td>'
					+ '<td align=center>' +  (obj.commentType == 0 ?obj.commentContentReplace:obj.commentContentReplace+'<span class="text-light-blue">&nbsp;//'+obj.commentParentUserName+'：</span>'+obj.commentParentContent)  + '</td>'
					+ '<td align=center>' +obj.createTime + '</td>'
					+ '<td align=center>' + (obj.commentType == 0 ? '<span>点赞:'+obj.like+'</span><br><span>回复:'+obj.commentSum+'</span>':'') + '</td>'
					+ '<td align=center>'

					+(obj.commentType == 0 ? '<button class =""  style="width:80px;height:30px;margin-right: 20px;"> 查看</button>':'')
					+'<button class="theme-login" id="call_'+obj.id+'" aid="'+obj.id+'" value="'+obj.userName+'"  onclick="articleComment_click(this)" style="width:80px;height:30px;">回复</button>'
					+''
					+'<button class="del" id="del_'+obj.id+'"  aid="'+obj.id+'"   style="width:80px;height:30px;margin-right: 20px;">删除</button>'
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
           // $('.check_fun').click(function(){

/*				$('.theme-login').click(function(){
					console.log("111", $('.theme-login'));

					var username = '';
					username = $('.theme-login').val();
					$('#articleComment-title').html('对'+'<span class="text-light-blue">&nbsp;'+username+'：</span>'+'的回复');

					$('.theme-popover-mask').fadeIn(100);

					$('.theme-popover').slideDown(200);


				})*/

				$('.theme-poptit .close').click(function(){

					$('.theme-popover-mask').fadeOut(100);

					$('.theme-popover').slideUp(200);

				})

          /*      if(confirm("确认启用？")){
                	console.log("value ========",$('.check_fun').val());
                    var aid = $(this).attr('aid');
                    info(aid, $('.check_fun').val());
                }*/
            //});

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

function create_callComment() {

	var topicName = $(".articleName_add").val();
	var articleType=$("#articleTypeId_add").val();
	var sort=$(".sort_add").val();
	var userId = window.sessionStorage.getItem("login_id");
	var userName = window.sessionStorage.getItem("login_name");


	if(!topicName || !articleType || !sort ){
		alert("回复信息不能为空！")
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
		$.jBox.tip(data.msg);
	}

}


function articleComment_click(index) {
	console.log("index is ",index)


	$('#articleComment-title').html('对'+'<span class="text-light-blue">&nbsp;'+index.value+'：</span>'+'的回复');

	$('.theme-popover-mask').fadeIn(100);

	$('.theme-popover').slideDown(200);


}



