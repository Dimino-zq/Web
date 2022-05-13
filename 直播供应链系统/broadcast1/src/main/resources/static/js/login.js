/**
 * 登录
 */
$(document).ready(function(){
	var path=getRootPath();
	$("#login_click").click(function(){
		if ($("#txt_userCode").val()==""){
			alert("请输入用户名！");
			return false;
		}
		if ($("#txt_userPass").val()==""){
			alert("请输入密码！");
			return false;
		}
		
		if ($("#txt_userVerCode").val()==""){
			alert("请输入验证码！");
			return false;
		}
		$("#loginForm").submit();
	});
    //输入完密码捕获回车事件
    $("#txt_userVerCode").keydown(function(e){
        var curKey = e.which;
        if (curKey == 13) {
            $("#loginForm").submit();
        }
    });

});