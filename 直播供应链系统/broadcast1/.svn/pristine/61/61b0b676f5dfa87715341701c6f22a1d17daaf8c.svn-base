$(document).ready(function(){
	
	var path=getRootPath();
	
	/**
	 * 修改个人信息
	 * @author tomset
	 */
	//打开修改对话框
	$("#btn_updInfo").click(function(){
		var anchorId = $("#input_anchorId").val();
		if(null == anchorId || "" == anchorId) {
			$.messager.alert("提示","无法获取数据，请刷新页面后再试!","info");
			return;
		}
		$("#input_anchorName").textbox("setValue",$("#anchorName").html());
		$("#input_phone").textbox("setValue",$("#phone").html());
		console.log($("#phone").html())
		if ("男"==$("#anchorSex").html())
		{
			$("#rdo_anchorSexboy").radiobutton("check",0);
		}
		else
		{
			$("#rdo_anchorSexgirl").radiobutton("check",1);
		}
		$("#input_birthDay").datebox("setValue",$("#birthDay").html());
		$("#input_roomId").textbox("setValue",$("#roomId").html());
		$("#input_anchorPlatform").textbox("setValue",$("#anchorPlatform").html());
		$("#input_fans").textbox("setValue",$("#fans").html());
		$("#input_pitFee").textbox("setValue",$("#pitFee").html());
		$("#input_service").textbox("setValue",$("#service").html());
		$("#input_ninetySale").textbox("setValue",$("#ninetySale").html());
	    $("#input_nativeplace").textbox("setValue",$("#nativeplace").html());
	    $("#input_workTime").textbox("setValue",$("#workTime").html());
		$("#input_businessAmount").textbox("setValue",$("#businessAmount").html());
		$("#input_thirtyWorkTimes").textbox("setValue",$("#thirtyWorkTimes").html());
		$("#input_perCustomerPrice").textbox("setValue",$("#perCustomerPrice").html());
		$("#input_saleCategory").textbox("setValue",$("#saleCategory").html());
		$("#input_investedInformation").textbox("setValue",$("#investedInformation").attr("investedInformation"));
		console.log($("#investedInformation").val("investedInformation"));
		$("#dialog_anchor").dialog("open");
	});
	
	//保存修改后的信息
    $("#btn_save").click(function(){
    	var postData=$('#form_anchor').serializeJSON();
    	if(postData == null) {
    		$.messager.alert("提示","postData is null!","info");
    		return;
    	} else if (postData.anchorName == "") {
 			$.messager.alert("提示","请输入姓名！","info",function () {
 				$("#input_anchorName").next("span").find("input[type='text']").focus();
 			});
 		} else if (postData.birthDay == "") {
 			$.messager.alert("提示","请选择出生日期！","info",function () {
 				$("#input_birthDay").next("span").find("a").click();
 			});
 		} else if (postData.anchorPlatform == "") {
 			$.messager.alert("提示","请输入直播平台！","info",function () {
 				$("#input_anchorPlatform").next("span").find("input[type='text']").focus();
 			});
 		} else if (postData.roomId == "") {
 			$.messager.alert("提示","请输入房间号！","info",function () {
 				$("#input_roomId").next("span").find("input[type='text']").focus();
 			});
 		} else if (postData.fans == "") {
 			$.messager.alert("提示","请输入粉丝数！","info",function () {
 	 			$("#input_fans").next("span").find("input[type='text']").focus();
 			});
 		} else if (postData.commission == "") {
 			$.messager.alert("提示","请输入佣金！","info",function () {
 	 			$("#input_commission").next("span").find("input[type='text']").focus();
 			});
		} else if (postData.service == "") {
 			$.messager.alert("提示","请输入服务费！","info",function () {
 	 			$("#input_service").next("span").find("input[type='text']").focus();
 			});
		} else if (postData.thirtyWorkTimes == "") {
 			$.messager.alert("提示","请输入30日直播次数！","info",function () {
 	 			$("#input_thirtyWorkTimes").next("span").find("input[type='text']").focus();
 			});
		} else if (postData.workTime == "") {
 			$.messager.alert("提示","请输入主播时间段！","info",function () {
 	 			$("#input_workTime").next("span").find("input[type='text']").focus();
 			});
		} else if (postData.ninetySale == "") {
 			$.messager.alert("提示","请输入近90日销售额！","info",function () {
 	 			$("#input_ninetySale").next("span").find("input[type='text']").focus();
 			});
		} else if (postData.businessAmount == "") {
 			$.messager.alert("提示","请输入招商计划数！","info",function () {
 	 			$("#input_businessAmount").next("span").find("input[type='text']").focus();
 			});
		} else if (postData.perCustomerPrice == "") {
 			$.messager.alert("提示","请输入客单价！","info",function () {
 	 			$("#input_perCustomerPrice").next("span").find("input[type='text']").focus();
 			});
		
		} else if (postData.saleCategory == "") {
 			$.messager.alert("提示","请输入产品销售类别！","info",function () {
 	 			$("#input_saleCategory").next("span").find("input[type='text']").focus();
 			});
		
		} else if (postData.investedInformation == "") {
 			$.messager.alert("提示","请输入招商信息！","info",function () {
 	 			$("#input_investedInformation").next("span").find("input[type='text']").focus();
 			});
 		} else {
 			var url = path + "/anchorInform/updAnchorInformation";
			//post提交数据
			$.post(url, postData, function (data) {
				if(data != null && data.tip != null){
					if(data.tip == "succ") {
						$.messager.alert("提示", "修改成功！", "info");
						window.location.reload();
					} else {
						$.messager.alert("错误", data.tip ,"error");
					}
				} else {
					$.messager.alert("错误", "未知错误！", "error");
				}
			});
		}
	});
	
	//关闭修改信息框
    $("#btn_close").click(function(){
		$("#dialog_anchor").dialog("close");
	});
    
	/**
	 * 修改密码
	 * @author tomset
	 */
	//打开对话框
	$("#btn_updPassword").click(function(){
		var anchorId = $("#input_anchorId").val();
		if(null == anchorId || "" == anchorId) {
			$.messager.alert("提示","无法获取数据，请刷新页面后再试!","info");
			return;
		}
		$("#dialog_password").dialog("open");
	});
	
	//保存密码框
    $("#btn_savepassword").click(function(){
    	var postData=$('#form_passwordinfo').serializeJSON();
    	var secnewpassword = $("#input_secnewpassword").textbox("getValue");
    	if(null == postData) {
    		$.messager.alert("提示","postData is null!","info");
    		return;
    	} else if(postData.oldpassword == "") {
 			$.messager.alert("提示","请输入原密码！","info",function () {
 	 			$("#input_oldpassword").next("span").find("input[type='text']").focus();
 			});
    	} else if(postData.newpassword == "") {
 			$.messager.alert("提示","请输入新密码！","info",function () {
 	 			$("#input_newpassword").next("span").find("input[type='text']").focus();
 			});
    	} else if(postData.newpassword.length <6 && postData.newpassword.length >12) {
 			$.messager.alert("提示","请输入不少于六位数不大于十二位数的新密码！","info",function () {
 				$("#input_newpassword").textbox("setValue","");
 	 			$("#input_newpassword").next("span").find("input[type='text']").focus();
 			});
    	} else if(secnewpassword == "") {
 			$.messager.alert("提示","请再次输入新密码！","info",function () {
 	 			$("#input_secnewpassword").next("span").find("input[type='text']").focus();
 			});
    	} else if(postData.newpassword != secnewpassword) {
    		console.log(postData.newpassword);
    		console.log(secnewpassword);
 			$.messager.alert("提示","两次输入的密码不一致！","info",function () {
 				$("#input_secnewpassword").textbox("setValue","");
 	 			$("#input_secnewpassword").next("span").find("input[type='text']").focus();
 			});
    	} else{
    		var url = path + "/anchorInform/updAnchorInformationPassword";
    		$.post(url, postData, function(data) {
				if(data != null && data.tip != null){
					if(data.tip == "succ") {
						$.messager.alert("提示", "修改成功！", "info");
						$("#btn_closepassword").click();
						window.location.reload();
					} else {
						$.messager.alert("错误", data.tip ,"error");
					}
				} else {
					$.messager.alert("错误", "未知错误！", "error");
				}
    		});
    	}
    });
    		
	//关闭修改密码框
    $("#btn_closepassword").click(function(){
		$("#input_oldpassword").textbox("setValue","");
		$("#input_newpassword").textbox("setValue","");
		$("#input_secnewpassword").textbox("setValue","");
		$("#dialog_password").dialog("close");
	});
	
	$('#input_oldpassword').passwordbox({
		inputEvents: $.extend({}, $.fn.passwordbox.defaults.inputEvents, {
			keypress: function(e){
				var char = String.fromCharCode(e.which);
				$('#viewer').html(char).fadeIn(200, function(){
					$(this).fadeOut();
				});
			}
		})
	});

	/**
	 * 修改头像
	 * @author tomset
	 */
	//点击上传头像
	$("#btn_updPhoto").click(function(){
		var anchorId = $("#input_anchorId").val();
		if(null == anchorId || "" == anchorId) {
			$.messager.alert("提示","无法获取数据，请刷新页面后再试!","info");
			return;
		}
		$("#dialog_photo").dialog("open");
	});

	$("#btn_saveimg").click(function () {
		var form = $("#form_photo")[0];
		var file = $(form).find('input[type=file]')[0].files[0];
		if(file == null) {
			$.messager.alert("错误", "请先选择一个文件！", "error");
		}
		var fileName = file.name;
		var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
		if (file_typename == '.jpg') {
			phoFileUpload(form);
		} else {
			$.messager.alert("错误", "请选择一个.jpg格式的文件！", "error");
		}

	})
	//上传
	function phoFileUpload(form) {
		var formData = new FormData(form);
		$.ajax({
			url : path+ "/anchorInform/saveimg",
            type : "post",
            async : false,
            cache:false,
            data : formData,
            processData : false,
            contentType : false,
			beforeSend : function() {
				$.messager.progress({
				 	text:"上传中..."
				});
			},
            success : function(data) {
				$.messager.progress("close");
				if(data != null && data.tip != null){
					if(data.tip == "succ") {
						$.messager.alert("提示", "修改成功！", "info");
						window.location.reload();
					} else {
						$.messager.alert("错误", data.tip ,"error");
					}
				} else {
					$.messager.alert("错误", "未知错误！", "error");
				}
            },
			error : function(e) {
				$.messager.progress("close");
				alert("未知错误！");
			}
        });
    }
	
	$("#btn_closeimg").click(function(){
		$("#imgGifFile").filebox("setValue","");
		$("#dialog_photo").dialog("close");
	});
	
});