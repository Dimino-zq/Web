$(document).ready(function(){
	var path=getRootPath();	 
 
    //点击修改密码--弹出对话框
	$("#btn_updDlg").click(function(){
	   $("#dlg_password").dialog("open");
	});
	
	//点击关闭按钮--关闭
	$("#btn_close").click(function(){
	   $("#dlg_password").dialog("close");
		//clearForm();
	  });
	  
	 //点击保存按钮--保存
	$("#btn_save").click(function(){
	    var postData=$('#form_passwordinfo').serializeJSON();
		var url = path + "/SysPlatInformation/updSysPlatInformationPassword";
		var npassword= $("#input_newpassword").val();
		if(npassword!=""){ 	
	    	$.messager.confirm(' ', '是否修改密码?', function(r){
				if (r){
							var newpassword = $("#input_newpassword").val();
							var repeatpassword = $("#input_secnewpassword").val();
							if(newpassword.length>=6 && repeatpassword>=6 ){
								if(newpassword == repeatpassword ){
							    	$.post(url, postData, function(data) {
							    		if (data == "succ")
							    		{		
											
													 $.messager.alert("提示", "修改密码成功", "info", function () {
						           					 window.location.reload();
												
				       					 		});	    						    				    			
								    			
							    		}else if(data = "passworderror")
							    		{
							    			$.messager.alert("提示","旧密码错误","info");
							    		}	
						    		});
								}else{
												
											$.messager.alert("提示","两次输入密码不一致！请检查后重新输入", "info",function () {
						           					 $("#input_secnewpassword").next("span").find("input[type='text']").focus();
												
				       					 		});	    	
											
											
										}
								}else{
									$.messager.alert("提示","您输入的密码小于六位！","info");
								}
					
					}
			});
		}else{
			$.messager.alert("提示","请输入您的旧密码！", "info",function () {
					 $("#input_oldpassword").next("span").find("input[type='text']").focus();
											
			});	    
		}
    });
	
	

	//点击关闭按钮--关闭
	$("#btn_close1").click(function(){
	   $("#dlg_system111").dialog("close");
		//clearForm();
	  });
	  
	 //点击修改
	$("#btn_save1").click(function(){
		var url = path + "/SysPlatInformation/updSysPlatInformation";
    	var postData=$('#form_add').serializeJSON();
    	console.log(postData);
		$.post(url,postData,function(data){
			
			   console.log(data);
				if (data=="succ"){
					$.messager.alert("提示","修改成功","info");
					$("#dlg_system111").dialog("close");
					window.location.reload(path+"/SysPlatInformation/gotoSysPlatInformation");
					
				}else{
					$.messager.alert("提示","错误","info");
				}
				
			});
	   	$("#dlg_system111").dialog("close");

	});
	//修改信息
	$("#btn_addDlg").click(function(){
	    var platformId=$("#txt_schoolId").val();
		var platformName=$("#txt_schoolName").val();
	    var platformDirector=$("#txt_schoolMaster").val();
	    var directorPhone=$("#txt_schoolTel").val();
	    var sysAdministrators=$("#txt_schoolAdmin").val();
	    var briefIntroduction=$("#txt_schoolIntroduce").val();
	    $("#txt_platformId").textbox("setValue",platformId);
	    $("#txt_platformName").textbox("setValue",platformName);
	    $("#txt_platformDirector").textbox("setValue",platformDirector);
	    $("#txt_directorPhone").textbox("setValue",directorPhone);
	    $("#txt_sysAdministrators").textbox("setValue",sysAdministrators);
	    $("#txt_briefIntroduction").textbox("setValue",briefIntroduction);
		$("#dlg_system111").dialog("open");
	});



});
