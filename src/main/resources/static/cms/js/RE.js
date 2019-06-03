
 // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var REcard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; //身份证
   var REphone = /^1[3|4|5|8|7]{1}\d{9}$/;//电话
   var REpassWord = /[a-zA-Z0-9]{5,11}/;//密码6-12位
   var REhomephone=/^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/;//区号3位座机号（01098909899）
   var ChineseName=/^[u4E00-u9FA5]+$/;
   var REcarLicense=/[A-Za-z0-9]{5}/;
   var REcarFrame=/^\d{4}$/;
/*
 *true验证失败   false验证成功
 */	  
function RE(str,type)  
{  
	if(!type.test(str)){
		return  true;  
	}
	return  false;  
  
}

function RETel(tel){
	
	 var result=tel.match(/\d{3}\d{8}|\d{4}\d{7}/);
           if(result!=null) 
				 return true;
				 
		return false;		 
	}

function toDecimal2(x) {  
            var f = parseFloat(x);  
            if (isNaN(f)) {  
                return false;  
            }  
            var f = Math.round(x*100)/100;  
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
        }
	
	
function GetRequest() {     
	 var url =decodeURI(location.search) ; //获取url中"?"符后的字
     var theRequest = new Object();   
  	if (url.indexOf("?") != -1) {  
       		var str = url.substr(1);    
	     	strs = str.split("&");   
		       for(var i = 0; i < strs.length; i ++) {  
			            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);    
				} 
	}    
	return theRequest; 
}