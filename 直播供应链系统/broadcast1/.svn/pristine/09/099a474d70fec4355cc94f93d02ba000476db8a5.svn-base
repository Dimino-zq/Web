$(document).ready(function(){
	var path=getRootPath();
	
	var oldGoodsIds = null;	//原已选商品id集合
	
	//初始化表单
	clearForm();

	//加载数据表格
	initTable();
	
	//点击提交查询信息
	$("#btn_query").click(function(){
		var queryParams=$('#form_query').serializeJSON();
	    $('#tbl_anchor').datagrid('load',queryParams);
	});
	
	//点击保存按钮
	var clickTimes = 0;  //判断点击次数寄存
	$("#btn_save").click(function(){
		//此处操作是为了防止多次点击导致多次执行函数
		clickTimes++;
        if(clickTimes == 1) {
        	console.log("aaa");
    		var postData=$("#form_anchor").serializeJSON();
    		if (postData)
    		{
    			if(postData.anchorId!=null && postData.anchorId!="")
    				updateAnchor(postData);
    			else
    				createAnchor(postData);
    		}
    		else
    			alert("postData is null!");
    		clickTimes =0;    //重置点击次数寄存
        } else {
        	return;
        }
	});
	
	//点击关闭按钮
	$("#btn_close").click(function(){
		closeDlg();
		clearForm();
	})
	
	/**
	 * 增加主播信息对话框
	 */
	//打开增加对话框
	$("#btn_addDlg").click(function(){
		openDlg();
	})
	
	//保存创建的申请表
	function createAnchor(postData) {
		//检查表单数据是否符合要求
		if (checkForm(postData)) {
			var url = path + "/anchor/saveAnchor";
			//post提交数据
			$.post(url, postData, function (data) {
				if(data != null && data.tip != null){
					if(data.tip == "succ") {
						$.messager.alert("提示", "增加成功！", "info");
						closeDlg();
						clearForm();
						initTable();
					} else {
						$.messager.alert("错误", data.tip ,"error");
					}
				} else {
					$.messager.alert("错误", "未知错误！", "error");
				}
			});
		} else {
			return;
		}
	}
	
	/**
	 * 修改一条主播数据
	 */
	//打开修改对话框
	$("#btn_updDlg").click(function(){
		var row = $("#tbl_anchor").datagrid("getSelected");
		if (row==null){
			$.messager.alert("提示","请选中一行数据!","info");
			return false;
		}
		else
		{
			$("#input_anchorId").val(row.anchorId);
			$("#input_anchorName").textbox("setValue",row.anchorName);
			var anchorSex=row.anchorSex;
			if ("男"==anchorSex)
			{
				$("#rdo_anchorSexboy").radiobutton("check",0);
			}
			else
			{
				$("#rdo_anchorSexgirl").radiobutton("check",1);
			}
			$("#input_birthDay").datebox("setValue",row.birthDay);
			$("#input_phone").textbox("setValue",row.phone);
			if(null!=row.user)
				$("#input_password").textbox("setValue",row.user.password);
			$("#input_roomId").textbox("setValue",row.roomId);
			$("#input_anchorPlatform").textbox("setValue",row.anchorPlatform);
			$("#input_fans").textbox("setValue",row.fans);
			$("#input_pitFee").textbox("setValue",row.pitFee);
			$("#input_ninetySale").textbox("setValue",row.ninetySale);
		    $("#input_nativeplace").textbox("setValue",row.nativeplace);
		    $("#input_workTime").textbox("setValue",row.workTime);
			$("#input_businessAmount").textbox("setValue",row.businessAmount);
			$("#input_thirtyWorkTimes").textbox("setValue",row.thirtyWorkTimes);
			$("#input_perCustomerPrice").textbox("setValue",row.perCustomerPrice);
			$("#input_saleCategory").textbox("setValue",row.saleCategory);
			$("#input_investedInformation").textbox("setValue",row.investedInformation);
//			$("#input_onlinePhoto").filebox("setValue",row.onlinePhoto);
		    $("#dialog_anchor").dialog("setTitle","修改主播信息");
			openDlg();
		}
	});

	//保存修改后的数据
	function updateAnchor(postData) {
		if (checkForm(postData)) {
			var url = path + "/anchor/updateAnchor";
			$.post(url, postData, function (data) {
				if(data != null && data.tip != null){
					if(data.tip == "succ") {
						$.messager.alert("提示", "修改成功！", "info");
						closeDlg();
						clearForm();
						initTable();
					} else {
						$.messager.alert("错误", data.tip, "error");
					}
				} else {
					$.messager.alert("错误", "未知错误！", "error");
				}
			});
		}
	}
	
	/**
	 * 删除一条主播数据
	 */
	//点击删除按钮
	$("#btn_delDlg").click(function () {
		var row = $("#tbl_anchor").datagrid("getSelected");
		//检查是否选中数据
		if (row == null) {
			$.messager.alert("提示", "请选中一行数据!", "info");
		} else {
			$.messager.confirm("确认", "您确认想要删除记录吗？", function (r) {
				if (r) {
					var anchorId = row.anchorId;
					var postData = {"anchorId": anchorId};
					var url = path + "/anchor/delAnchor";
					$.post(url, postData, function (data) {
						if(data != null && data.tip != null){
							if(data.tip == "succ") {
								$.messager.alert("提示", "删除成功！", "info");
								initTable();
							}
							else {
								$.messager.alert("错误", data.tip, "error");
							}
						} else {
							$.messager.alert("错误", "未知错误！", "error");
						}
					});
				}
			});
		}
	});

	//重置对话框表单函数
	function clearForm(){
		$("#input_anchorId").val("");
		$("#input_anchorName").textbox("setValue","");
		$("#rdo_anchorSexboy").radiobutton("check",0);
		$("#input_birthDay").datebox("setValue","");
		$("#input_phone").textbox("setValue","");
		$("#input_password").textbox("setValue","");
		$("#input_roomId").textbox("setValue","");
		$("#input_anchorPlatform").textbox("setValue","");	
		$("#input_fans").textbox("setValue","");
		$("#input_pitFee").textbox("setValue","");
		$("#input_ninetySale").textbox("setValue","");
		$("#input_workTime").textbox("setValue","");
		$("#input_businessAmount").textbox("setValue","");
		$("#input_thirtyWorkTimes").textbox("setValue","");
		$("#input_perCustomerPrice").textbox("setValue","");
		$("#input_saleCategory").textbox("setValue","");
		$("#input_investedInformation").textbox("setValue","");
//		$("#input_onlinePhoto").filebox("setValue","");
	}
	
	
    $('#tab_mag').datagrid({
        url:path+"/anchor/getComgoodsByCon",
        //fit:true,
        border:false,
        striped:true,
        rownumbers:true,
        autoRowHeight:false,
        fitColumns:true,
        idField:'goodsId',
        columns:[[
            {field: 'goodsId',checkbox:true},
            {field: 'goodsName',title: '商品名称',width:150},
            {field: 'thirdCategory',title: '所属类别',width:150},
            {field: 'sellerName',title: '所属企业',width:150},
            {field: 'goodsPrice',title: '商品单价',fixed:true,width:68},
            {field: 'commission',title: '商品佣金',fixed:true,width:68},
            {field: 'tureCommission',title: '实际佣金',width:150,
                formatter: function(value,row,index){
                	return "<div style='text-align: center;'><input id='goodsId_"+row.goodsId+"' name='' value='' style='width: 70px;height: 18px'></div>";
                }
            },
            {field: 'commissionLink',title: '佣金链接',width:150,
                formatter: function(value,row,index){
                	return "<div style='text-align: center;'><input id='moneyLink_"+row.goodsId+"' name='' value='' style='width: 110px;height: 18px'></div>";
                }
            },
        ]],
        onBeforeLoad:function(data){
        	$('#tab_mag').datagrid("uncheckAll");
        },
        onLoadSuccess:function(data){
        	//$(".datagrid-header-check").html("");
        	oldGoodsIds = "";
        	if(null!=data && null!=data.anchorGoods){
        		var anchorGoods = data.anchorGoods;
        		for(var i=0;i<anchorGoods.length;i++){
        			$("#goodsId_"+anchorGoods[i].comgoodsinformation.goodsId).val(anchorGoods[i].money);
        			$("#moneyLink_"+anchorGoods[i].comgoodsinformation.goodsId).val(anchorGoods[i].moneyLink);
        			$('#tab_mag').datagrid("checkRow", $('#tab_mag').datagrid("getRowIndex", anchorGoods[i].comgoodsinformation.goodsId));
        			if(i == 0)
        				oldGoodsIds+=anchorGoods[i].comgoodsinformation.goodsId;
        			else
        				oldGoodsIds+=(","+anchorGoods[i].comgoodsinformation.goodsId);
        		}
        	}
        },
        onCheck:function(index, row){
        	var money = $("#goodsId_"+row.goodsId).val();
        	var moneyLink = $("#moneyLink_"+row.goodsId).val();
        	if(null == money || "" == money){
        		$.messager.alert("提示", "请先填写佣金！", "info");
        		$('#tab_mag').datagrid("uncheckRow", index);
        	}else if(null == moneyLink || "" == moneyLink){
        		$.messager.alert("提示", "请先填写佣金链接！", "info");
        		$('#tab_mag').datagrid("uncheckRow", index);
        	}
        },
        onCheckAll:function(rows){
        	var countMoney = 0;
        	var countMoneyLink = 0;
        	for(var i=0; i<rows.length; i++) {
        		var money = $("#goodsId_"+rows[i].goodsId).val();
        		var moneyLink = $("#moneyLink_"+rows[i].goodsId).val();
            	if(null == money || "" == money){
            		$('#tab_mag').datagrid("uncheckRow", i);
            		countMoney++;
            	} else if(null == moneyLink || "" == moneyLink){
            		$('#tab_mag').datagrid("uncheckRow", i);
            		countMoneyLink++;
	        	}
        	}
        	if(countMoney>0)
        		$.messager.alert("提示", "请先填写佣金！", "info");
        	else if(countMoneyLink>0)
        		$.messager.alert("提示", "请先填写佣金链接！", "info");
        },
        onBeforeSelect:function(){
        	return false;
        },
        onSelectAll:function(){
        	$('#tab_mag').datagrid("clearSelections");
        },
    });
	
	//点击提交查询信息
	$("#btn_queryGoods").click(function(){
		var queryParams=$('#form_queryGoods').serializeJSON();
	    $('#tab_mag').datagrid('load',queryParams);
	});
    
	//点击分配商品对话框打开
    $("#btn_disDlg").click(function () {
		var row = $("#tbl_anchor").datagrid("getSelected");
		if (row==null){
			$.messager.alert("提示","请选中一行数据!","info");
			return false;
		} else {
			$("#input_anchorId2").val(row.anchorId);
			$("#btn_queryGoods").click();
			$("#dialog_mag").dialog("open");
		}
    });
    
	//分配商品关闭
    $("#btn_close1").click(function () {
		$("#input_anchorId2").val("");
        $("#dialog_mag").dialog("close");
    });
    
    //分配商品保存
    $("#btn_save1").click(function () {
    	var anchorId = $("#input_anchorId2").val();
    	var rows = $('#tab_mag').datagrid('getChecked');
    	var goodsIds = new Array();
    	for(var i=0; i<rows.length; i++) {
    		goodsIds[i] = {
//    				'anchorInformation' : {'anchorId':anchorId},
    				'money' : $("#goodsId_"+rows[i].goodsId).val(),
    				'moneyLink' : $("#moneyLink_"+rows[i].goodsId).val(),
    				'comgoodsinformation' : {'goodsId':rows[i].goodsId}
    		};
    	}

    	$.ajax({
			url:path+"/anchor/savaAnchorGoods?anchorId="+anchorId+"&oldGoodsIds="+oldGoodsIds,
			data:JSON.stringify(goodsIds),
	        dataType: "json",
	        contentType:"application/json",
            type : "post",
            async : false,
            cache:true,
            success: function (data){
				if(data != null && data.tip != null){
					if(data.tip == "succ") {
						$.messager.alert("提示", "保存成功！", "info");
						//$("#btn_queryGoods").click();
					} else {
						$.messager.alert("错误", data.tip ,"error");
					}
				} else {
					$.messager.alert("错误", "未知错误！", "error");
				}
            }
    	})
    });
	
	//商品类别下拉列表框数据的初始化以及联动
	$("#input_fristCategory").combobox({
		valueField:'categoryName',
		textField:'categoryName',
		url:path+'/merchkind/getparent',
		queryParams:{categorylevel:1},
		onSelect:function(lv1){
			var param = lv1.categoryId;
			$("#input_secondCatogory").combobox({
				value:null,
				valueField:'categoryName',
				textField:'categoryName',
				url:path+'/merchkind/getchildcategories',
				queryParams:{"parentId":param},
				onSelect:function(lv2){
					var param = lv2.categoryId;
					$("#input_thirdCategory").combobox({
						value:null,
						valueField:'categoryName',
						textField:'categoryName',
						url:path+'/merchkind/getchildcategories',
						queryParams:{"parentId":param}
					});
					$("#input_thirdCategory").combobox("enable");
				}
			});
			$("#input_thirdCategory").combobox("setValue","");
			$("#input_thirdCategory").combobox("disable");
		}
	});
	
	//初始化数据表格
	function initTable() {
		var url=path+"/anchor/getAnchorByCon";
		$('#tbl_anchor').datagrid({
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
	        pageSize:15,
	        pageList:[15,25,55],
	        pageNumber:1,
	        columns:[[
	            {field: 'anchorName',title: '姓名',width:150},
	            {field: 'anchorSex',title: '性别',width:150},
	            {field: 'anchorPlatform',title: '直播平台',width:150},
	            {field: 'fans',title: '粉丝',width:150},
	            {field: 'pitFee',title: '坑位',width:150},
	            {field: 'saleCategory',title: '销售类别',width:150},
	            {field: 'businessAmount',title: '招商计划数',width:150},
//	            {field: 'nativeplace',title: '籍贯',width:150},
//				{field: 'birthDay',title: '生日',width:150},
				{field:'phone',title:'联系电话',width:100},
//				{field: "onlinePhoto",title: "照片",width:150,
//				formatter:function (value,row,index) {
//					if(null==row.onlinePhoto || row.onlinePhoto == "")
//						return "<div style='color:red'>无照片</div>";
//					else
//						return  "<a href='#' file='"+row.filePath+"'>查看</a>"; 
//				}},
	         ]]
		})
	}

	//打开主播信息对话框函数
	function openDlg(){
		$("#dialog_anchor").dialog("open");
	}
	
	//关闭主播信息对话框函数
	function closeDlg(){
		$("#dialog_anchor").dialog("close");
	}
	
	//表单字段校验
	function checkForm(postData)
	{
//		if (postData.insurance == "")
//			$("#input_insurance").next("span").find("a").click();
//		else if (postData.surcomjob == "")
//			$("#input_surcomjob").next("span").find("input[type='text']").focus();
//		else if (postData.endDate == "")
//			$("#input_endDate").next("span").find("a").click();
//		else
			return true;
//		return false;
	}
	
/*	//直播平台下拉框
	$("#input_insurance").combobox({
		value:"抖音",
		url:path+"",
    	editable:false,
    	textField: "dictValue",
    	valueField: "dictKey"
	});*/
	
	/**
	 * 上传附件
	 */
/*	$("#btn_photo").click(function () {
		$("#dialog_importphoto").dialog("open");
    });

	$("#btn_mod").click(function () {
		var form = $("#form_importphoto")[0];
		var file = $(form).find('input[type=file]')[0].files[0];
		if(file == null) {
			$.messager.alert("错误", "请先选择一个文件！", "error");
		}
		var fileName = file.name;
		console.log(fileName);
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
        var anchorId = $("#tbl_stuAnchor").datagrid("getSelected").applyId;
        formData.append("anchorId",anchorId);
        $.ajax({
            url : path+ "/studentapp/uploadfile",
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
            success : function(e) {
				$.messager.progress("close");
                if (null != e.error) {
					$.messager.alert("错误", e.error, "error");
                    alert(e.error);
                } else {
					$.messager.alert("提示", e.tip, "info", function () {
						$("#dialog_importphoto").dialog("close");
						//initTable();
						location.reload();
					});
                }
            },
			error : function(e) {
				$.messager.progress("close");
				alert("未知错误！");
			}
        });
    }*/

	//验证框的验证规则
    $.extend($.fn.validatebox.defaults.rules, {
        minLength: {
            validator: function(value, param){
                return value.length >= param[0];
            },
            message: 'Please enter at least {0} characters.'
        }
    });

})
