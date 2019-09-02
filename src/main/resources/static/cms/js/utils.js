//checkRole
if(window.sessionStorage.getItem("login_id")==null&&window.location.pathname.indexOf("login.html")==-1){
	window.location.href="login.html";
}else if(window.location.pathname.indexOf("login.html")>-1){
	window.sessionStorage.clear();
}else{

}
// var serPath="http://server.momjia.com/xhm/";
var imgPath="http://server.momjia.com/xhm/";
var serPath="http://192.168.1.185:8082/xhm/";
//var imgPath="http://hasystem.houaihome.com/";
var url_editer = serPath + '/jodit/uploadImg';
var deBug=false;
$(function(){
	//非登录页加载
	if(window.location.pathname.indexOf("login.html")==-1){
		$('#loginUserName').html(window.sessionStorage.getItem("login_name"));
		if($('#sys').size()>0){
			var sys_html="";

			//文章管理
            sys_html+='<li><dl id="article">';
            sys_html+='<dt><a href="javascript:;">文章管理</a></dt>';
            sys_html+='<dd><a id="wsh_article" href="wsh_article.html">未审核文章</a></dd>';
            sys_html+='<dd><a id="sel_article" href="sel_article.html">文章查询</a></dd>';
            sys_html+='</dl></li>';

            //用户管理
            sys_html+='<li><dl id="user">';
            sys_html+='<dt><a href="javascript:;" >用户管理</a></dt>';
            sys_html+='<dd><a id="" href="sel_user.html">用户查询</a></dd>';
            sys_html+='</dl></li>';

            //系统管理
            sys_html+='<li><dl id="">';
            sys_html+='<dt><a href="javascript:;">系统管理</a></dt>';
            sys_html+='<dd><a id="" href="system.html">系统查询</a></dd>';
            sys_html+='</dl></li>';

            //话题管理
			sys_html+='<li><dl id="topics">';
			sys_html+='<dt><a href="javascript:;">话题管理</a></dt>';
			sys_html+='<dd><a id="sel_topics" href="sel_topics.html">话题查询</a></dd>';
			sys_html+='</dl></li>';

			//评论管理
			sys_html+='<li><dl id="articleComment">';
			sys_html+='<dt><a href="javascript:;">评论管理</a></dt>';
			sys_html+='<dd><a id="sel_articleComment" href="sel_articleComment.html">评论查询</a></dd>';
			sys_html+='</dl></li>';

			//商品管理
			sys_html+='<li><dl id="goods">';
			sys_html+='<dt><a href="javascript:;">商品管理</a></dt>';
			sys_html+='<dd><a id="" href="add_goods.html">商品添加</a></dd>';
			sys_html+='<dd><a id="" href="sel_goods.html">商品查询</a></dd>';
			sys_html+='</dl></li>';


			/*var permissionList = JSON.parse(window.sessionStorage.getItem("login_permission"));
			for ( var i = 0; i < permissionList.length; i++) {
				var obj = permissionList[i];
				sys_html+='<li><dl id="'+obj.type+'">';
				if(obj.type=="system"){
					sys_html+='<dt><a href="javascript:;">系统管理</a></dt>';
				}else if(obj.type=="goods"){
					sys_html+='<dt><a href="javascript:;">产品管理</a></dt>';
				}else if(obj.type=="goodsHd"){
					sys_html+='<dt><a href="javascript:;">活动管理</a></dt>';
				}else if(obj.type=="user"){
					sys_html+='<dt><a href="javascript:;">用户管理</a></dt>';
				}else if(obj.type=="bh"){
					sys_html+='<dt><a href="javascript:;">备货管理</a></dt>';
				}else if(obj.type=="fh"){
					sys_html+='<dt><a href="javascript:;">发货管理</a></dt>';
				}else if(obj.type=="th"){
					sys_html+='<dt><a href="javascript:;">退货管理</a></dt>';
				}else if(obj.type=="tj"){
					sys_html+='<dt><a href="javascript:;">统计管理</a></dt>';
				}
				var list = obj.list;
				for ( var int = 0; int < list.length; int++) {
					sys_html+='<dd><a id="'+list[int].url+'" href="'+list[int].url+'.html">'+list[int].name+'</a></dd>';
				}
				sys_html+='</dl></li>';
			}*/
			$('#sys').html(sys_html);
			$('#sys dt').on('click', function(e){
				if($(this).parent().find('dd').is(":hidden")){
					$('#sys dd').slideUp('fast');
					$(this).parent().find('dd').slideToggle('fast');
				}
			});
		}
		$("#esc").click(function(){
			window.sessionStorage.clear();
			window.location.href="login.html";
		});
	}
});
//jBox.messager(window.sessionStorage.getItem("userName")+"，您好！您有新的抄送消息，请注意查看。", "抄送我的", 10000, { width: 330, showType: 'slide', icon: 'info' });
/**
 * Memory-迪
 * JS工具包
 * 1. 正则验证
 * 2. 获取URL参数 getParam(param)
 * 3. 回跳页面 getBackUrlStr()
 * 4. 图片比例修改
 */
var utils = {
	/**
	 * 为空验证
	 * @param string
	 * @returns {Boolean}
	 */
	isEmpty : function(object){
		if(object==null){
			return object;
		}else if(typeof(object)=="string"){
			return object=="";
		}else if(typeof(object)=="object"){
			var isEmpty_bool=false;
			for(var i=0; i<object.length; i++){
				if(object[i]==""){
					isEmpty_bool=true;
				}

			}
			return isEmpty_bool;
		}
	},
	/**
	 * 6到12位密码验证
	 * @param string
	 * @returns
	 */
	isPassWord : function(string){
		var passWord=/^[a-zA-Z0-9]{6,12}$/;
		return passWord.test(string);
	},
	/**
	 * 长度验证
	 * @param string 验证的字符串
	 * @param len 验证的长度
	 * @returns {Boolean}
	 */
	isLength : function(string, maxLen, minLen){
		minLen=(minLen==undefined?0:minLen);
		return (minLen<=string.length&&string.length<=maxLen);
	},
	/**
	 * 手机号验证
	 * @param string
	 * @returns
	 */
	isMobile : function(string){
		var mobile=/^1\d{10}$/;
		return mobile.test(string);
	},
	/**
	 * 座机号验证
	 * @param string
	 * @returns
	 */
	isPhone : function(string){
		var phone=/^\d[3,4]-?\d[8]$/;
		return phone.test(string);
	},
	/**
	 * 邮箱验证
	 * @param string
	 * @returns
	 */
	isEmail : function(string){
		var email=/^[a-zA-Z0-9]+@\w+[.]\w+$/;
		return email.test(string);
	},
	/**
	 * 价钱验证
	 * @param string
	 * @returns
	 */
	isPrice : function(string){
		var price=/^\d+.?\d{0,2}$/;
		return price.test(string);
	},
	isIDCard : function(string){
		var idCard=/^\d{15,17}[a-z0-9]{1}$/;
		return idCard.test(string);
	},
	/**
	 * URL获取参数
	 * @param paramName
	 * @returns
	 */
	getParam : function(paramName){
		var url = location.href;
        var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
        var paraObj = {};
        for (var i = 0; j = paraString[i]; i++) {
            paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
        }
        var returnValue = paraObj[paramName.toLowerCase()];
        if (typeof (returnValue) == "undefined") {
            return "";
        } else {
            return returnValue;
        }
	},
	/**
	 * 回跳页面
	 * @param paramName
	 * @returns
	 */
	getBackUrlStr : function(paramName){
		var paramSize=paramName.length;
	   	var url = location.href;
	   	var index = url.indexOf("?");
	   	var paraString ;
	   	if (index>0) {
	   		paraString = url.substring(index + paramSize+2, url.length);
		}else{
			paraString = "index.jsp";
		}
		 return paraString;
	},
	/**
	 * 强制保留数字,小数点后两位
	 * @param x
	 * @returns
	 */
	toDouble : function(param) {
		var f = parseFloat(param);
		if (isNaN(f)) {
		    return false;
		}
		var f = Math.round(param*100)/100;
		var s = f.toString();
		var rs = s.indexOf('.');
		if (rs < 0) {
			rs = s.length;
			s += '.';
		}
		while (s.length <= rs + 2) {
			s += '0';
		}
		return s;
	},
	/**
	 * 图片比例修改
	 * @param url
	 * @param w
	 * @param h
	 * @returns {String}
	 */
	picUrl : function(url,w,h){
		if(h==""){

			var array=url.split(".");
			var path0=array[0]+"_"+w;
			var picUrl=path0+"."+array[1];
			return picUrl;
		}

		 var array=url.split(".");

		var path0=array[0]+"_"+w+"x"+h;
		var picUrl=path0+"."+array[1];
		return picUrl;
	}
};
function ajax(url, args, callback_fn, type){
	if(deBug){
		console.log("***-----ajax-----***");
		console.log("ajax---url:"+url);
		console.log("ajax---args:"+args);
		console.log("ajax---callback:"+callback_fn);
	}
	$.ajax({
		url : serPath+url,
		data : args,
		type: (type==undefined?"post":type),
		success : function(data){
			if(deBug){
				console.log("ajax-resultData:↓↓↓");
				console.log(data);
			}
			callback_fn(data);
		},
		error : function(e){
			console.log("Ajax---error"+"   status:"+e.status+"   statusText:"+e.statusText);
			if(error_fn!=undefined){
				error_fn(data);
			}
		}
	});
}
//金额change事件，验证是否非数字,非负数,保留两位小数
function bind_price(_id){
	$('#'+_id).bind('input propertychange', function(e) {
		if(isNaN(e.target.value)){
			$('#'+_id).val($('#'+_id).attr("valuea"));
		}else if(e.target.value<0){
			$('#'+_id).val($('#'+_id).attr("valuea"));
		}else{
			$('#'+_id).attr("valuea", $.trim(e.target.value)).val($('#'+_id).attr("valuea"));
		}
	});
	$('#'+_id).change(function(e) {
		if(utils.toDouble($('#'+_id).val())&&utils.toDouble($('#'+_id).val())>=0){
			$('#'+_id).val(utils.toDouble($('#'+_id).val())).attr("valuea", $.trim(e.target.value));
		}
	});
}
function bind_price_fu(_id){
	$('#'+_id).bind('input propertychange', function(e) {
		if(isNaN(e.target.value)){
			if(e.target.value.lastIndexOf("-")!=0){
				$('#'+_id).val($('#'+_id).attr("valuea"));
			}
		}else{
			$('#'+_id).attr("valuea", $.trim(e.target.value)).val($('#'+_id).attr("valuea"));
		}
	});
	$('#'+_id).change(function(e) {
		if(utils.toDouble($('#'+_id).val())){
			$('#'+_id).val(utils.toDouble($('#'+_id).val())).attr("valuea", $.trim(e.target.value));
		}
	});
}
function bind_number(_id){
	$('#'+_id).bind('input propertychange', function(e) {
		if(isNaN(e.target.value)){
			$('#'+_id).val($('#'+_id).attr("valuea"));
		}else if(e.target.value<0){
			$('#'+_id).val($('#'+_id).attr("valuea"));
		}else if(e.target.value.indexOf(".")>=0){
			$('#'+_id).val($('#'+_id).attr("valuea"));
		}else{
			$('#'+_id).attr("valuea", $.trim(e.target.value)).val($('#'+_id).attr("valuea"));
		}
	});
}
function bind_number_class(_class){
	$('.'+_class).bind('input propertychange', function(e) {
		if(isNaN(e.target.value)){
			$(this).val($(this).attr("valuea"));
		}else if(e.target.value<0){
			$(this).val($(this).attr("valuea"));
		}else if(e.target.value.indexOf(".")>=0){
			$(this).val($(this).attr("valuea"));
		}else{
			$(this).attr("valuea", $.trim(e.target.value)).val($(this).attr("valuea"));
		}
	});
	$('.'+_class).change(function(e) {
		if(e.target.value==0){
			$(this).val("0");
		}
	});
}
