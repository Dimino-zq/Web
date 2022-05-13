$(document).ready(function(){
	var path=getRootPath();
	//alert(111);
	company();
	
	
	$("#btn_addDlg").click(function(){
    	$("#dlg_company").dialog("open");
  	});
	//关闭
	$("#btn_close").click(function(){
		clearForm();
   	$("#dlg_company").dialog("close");
  });
	$("#btn_close1").click(function(){
   	$("#dlg_company1").dialog("close");
  });
	$("#btn_query").click(function(){
        var queryParams=$('#form_query').serializeJSON();
        $('#tbl_company').datagrid('load',queryParams);
    });


$("#btn_query").click(function(){
        var queryParams=$('#form_query').serializeJSON();
        $('#tbl_company').datagrid('load',queryParams);
    });

	//增加
	$("#btn_save").click(function(){
		$("#txt_signTime").attr("disabled","true");
			var comName=$("#txt_comName").textbox("getValue");
			if (comName==""){
				$.messager.alert("提示","请输入企业名称!","info");
				$("#txt_comName").next("span").find("input[type='text']").focus();
			return  false;
			}
			var mobile = $("#txt_comPhone").val();
                        if (mobile && /[1][3,4,5,7,8][0-9]{9}$/.test(mobile)) {
                            if (mobile.length > 11 || mobile.length < 11) {
                                $.messager.alert("提示","请输入11位的手机号码","info");
                                return;
                            }
                        } else {
                            $.messager.alert("提示","请输入正确企业联系方式!","info");
							$("#txt_comPhone").next("span").find("input[type='text']").focus();
                            return;
                        }
			var comStartTime=$("#txt_comStartTime").textbox("getValue");
			if (comStartTime==""){
				$.messager.alert("提示","请输入注册时间!","info");
				$("#txt_comStartTime").next("span").find("input[type='text']").focus();
			return  false;
			}
			var comeail=$("#txt_comeail").textbox("getValue");
			if (comeail==""){
				$.messager.alert("提示","请输入正确的邮箱!","info");
				$("#txt_comeail").next("span").find("input[type='text']").focus();
			return  false;
			}
			var comContacts=$("#txt_comContacts").textbox("getValue");
			if (comContacts==""){
				$.messager.alert("提示","请输入联系人姓名!","info");
				$("#txt_comContacts").next("span").find("input[type='text']").focus();
			return  false;
			}
			var comBusiness=$("#txt_comBusiness").textbox("getValue");
			if (comBusiness==""){
				$.messager.alert("提示","请填写营业执照!","info");
				$("#txt_comBusiness").next("span").find("input[type='text']").focus();
			return  false;
			}
			var businessRange=$("#txt_businessRange").textbox("getValue");
			if (businessRange==""){
				$.messager.alert("提示","请输入经营范围!","info");
				$("#txt_businessRange").next("span").find("input[type='text']").focus();
			return  false;
			}
			var comProfile=$("#txt_comProfile").textbox("getValue");
			if (comProfile==""){
				$.messager.alert("提示","请输入企业简介!","info");
				$("#txt_comProfile").next("span").find("input[type='text']").focus();
			return  false;
			}
			var comAddress=$("#txt_comAddress").textbox("getValue");
			if (comAddress==""){
				$.messager.alert("提示","请输入企业地址!","info");
				$("#txt_comAddress").next("span").find("input[type='text']").focus();
			return  false;
			}
			var txt_companyId=$("#txt_companyId").val();
			if (txt_companyId==""){
		    	var url=path+"/syscompany/saveSysCompany";
		    	var postData=$('#form_company').serializeJSON();
		    	$.post(url,postData,function(data){
		        if (data=="succ"){
		        	$.messager.alert("提示","增加成功","info")
		        	$("#dlg_company").dialog("close");
		        	$("#btn_query").click();
		        	clearForm();
		        }else{
		          $.messager.alert("提示","企业名称重复，请更改后重试","info");
				  $("#txt_comName").next("span").find("input[type='text']").focus();
				  console.log(data);	
		        }
		      });
			}
		else {
			var url=path+"/syscompany/updSysCompany";
			var postData=$('#form_company').serializeJSON();
			$.post(url,postData,function(data){
				if (data=="succ"){
					$.messager.alert("提示","修改成功","info");
					$("#dlg_company").dialog("close");
					$("#btn_query").click();
					$("#form_company").form("reset");
					clearForm();
				}else{
					$.messager.alert("提示","企业名称重复","info");
				}
			});
		}
	});
	
	//点击修改
	$("#btn_updDlg").click(function(){
		var  row=$('#tbl_company').datagrid("getSelected");
		
		if (row==null){
			$.messager.alert("提示","请选中要修改的行","info");
			return false;
		}else{
			$("#txt_companyId").val(row.companyId);
			$("#txt_comName").textbox("setValue",row.comName);
			$("#txt_comPhone").textbox("setValue",row.comPhone);
			$("#txt_comStartTime").datebox("setValue",row.comStartTime);
			$("#txt_comeail").textbox("setValue",row.comeail);	
			$("#txt_comContacts").textbox("setValue",row.comContacts);
			$("#txt_comBusiness").textbox("setValue",row.comBusiness);
			$("#txt_businessRange").textbox("setValue",row.businessRange);
			$("#txt_comAddress").textbox("setValue",row.comAddress);
			$("#txt_comProfile").textbox("setValue",row.comProfile);
			$('#dlg_company').dialog('setTitle', '修改企业信息');
			$("#txt_updDate").datebox("setValue",row.updDate);
			$("#dlg_company").dialog("open");
		}
		});
	
	
	
	//删除
	$("#btn_delDlg").click(function () {
    var row=$("#tbl_company").datagrid("getSelected");
        if (null!=row)
        {
	 		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
				if (r)
				{
		          var companyId=row.companyId;
					var userId=row.user.userId;
		          var postData={"companyId":companyId,"userId":userId};
		          $.post((path+"/syscompany/deleteSysCompany"),postData,function (data) {
		              if ("deletesuccess"==data)
		              {
		                  $.messager.alert("提示","删除成功！","info");
		                  company()
		              }
              	else 
					{
					$.messager.alert("提示","该企业正在使用!","info");
			 		}
                  
          		});
				}
			});
        }
        else
        {
            $.messager.alert("提示","请选中一行！","info");
			return false;
        }
    });

//清空表单功能
	function clearForm(){
		$("#txt_companyId").val("");
		$("#txt_comName").textbox("setValue","");
		$("#txt_comPhone").textbox("setValue","");
		$("#txt_comStartTime").datebox("setValue","");
		$("#txt_comeail").textbox("setValue","");	
		$("#txt_comContacts").textbox("setValue","");
		$("#txt_comBusiness").textbox("setValue","");
		$("#txt_businessRange").textbox("setValue","");
		$("#txt_comAddress").textbox("setValue","");
		$("#txt_comProfile").textbox("setValue","");
		$('#dlg_company').dialog('setTitle', '增加企业');
	}

	//查看字段

	$("#btn_seeDlg").click(function(){
		var  row=$('#tbl_company').datagrid("getSelected");
		if (row==null){
			$.messager.alert("提示","请选中需要查看的企业","info");
			return false;
		}else{
			$("#txt_companyId").val(row.companyId);
			$("#txt_comName1").textbox("setValue",row.comName);
			$("#txt_comPhone1").textbox("setValue",row.comPhone);
			$("#txt_comStartTime1").datebox("setValue",row.comStartTime);
			$("#txt_comeail1").textbox("setValue",row.comeail);	
			$("#txt_comContacts1").textbox("setValue",row.comContacts);
			$("#txt_comBusiness1").textbox("setValue",row.comBusiness);
			$("#txt_businessRange1").textbox("setValue",row.businessRange);
			$("#txt_comAddress1").textbox("setValue",row.comAddress);
			$("#txt_comProfile1").textbox("setValue",row.comProfile);
			$("#txt_updDate1").datebox("setValue",row.updDate);
			$("#dlg_company1").dialog("open");
		}
		
	});
	
	
	function company(){
		var url=path+"/syscompany/getSysCompanyByCon";
	    $("#tbl_company").datagrid({
			loadMsg:"加载数据中......",
            url:url,
            border:false,
            striped:true,
            fit:true,
            rownumbers:true,
            autoRowHeight:false,
            singleSelect:true,
            fitColumns:true,
            pagePosition:"bottom",
            pagination:true,
            pageSize:10,
            pageList:[10,20,50],
            pageNumber:1,
	    	columns:[[
			{field:"comName",title:"企业名称",width:100},
			{field:"comContacts",title:"企业联系人",width:100},
			{field:"comPhone",title:"企业联系方式",width:100},
			{field:"comeail",title:"企业邮箱",width:100},
			{field:"comBusiness",title:"营业执照号",width:100},
			{field:"businessRange",title:"企业经营范围",width:100},
	      ]]
	    })
	}
})