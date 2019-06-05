$(document).ready(function() {
	//粒子背景特效
	$('body').particleground({
    	dotColor: '#5cbdaa',
    	lineColor: '#5cbdaa'
	});
	$(".submit_btn").click(function(){
		var loginname=$('#loginname').val();
		var password=$('#password').val();
		if(loginname==""){
			$.jBox.tip("请填写账号");
			$('#loginname').focus();
		}else if(password==""){
			$.jBox.tip("请填写密码");
			$('#password').focus();
		}else{
			login(loginname, password);
		}
	});
	$(document).keydown(function(event){ 
		if(event.keyCode==13){
			var loginname=$('#loginname').val();
			var password=$('#password').val();
			if(loginname!=""&&password!=""){
				login(loginname, password);
			}
		} 
	});
	if(window.localStorage.getItem("remeberLoginname")){
		$('#remeberLoginname').attr("checked", "checked");
		$('#loginname').val(window.localStorage.getItem("remeberLoginname"));
		$('#password').focus();
	}else{
		$('#loginname').focus();
	}
});
function login(loginname, password){
	ajax("cmsSysAdmin/login", {loginname: loginname, password: password}, login_callback);
}
function login_callback(data){
	if(data.state=="success" && data.recode==0){
        if($('#remeberLoginname').is(":checked")){
            window.localStorage.setItem("remeberLoginname", $('#loginname').val());
        }else{
            window.localStorage.removeItem("remeberLoginname");
        }
        window.sessionStorage.setItem("login_id", data.result.id);
        window.sessionStorage.setItem("login_name", data.result.name);
        window.location.href='index.html';
	}else{
		$.jBox.tip(data.msg);
	}
}