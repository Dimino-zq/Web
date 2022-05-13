$(document).ready(function(){
	var path=getRootPath();
	
	var oldGoodsIds = null;	//原已选商品id集合
	
	clearForm();
	
	initTable();
	
	initGoods();
	
	//点击提交查询信息
	$("#btn_query").click(function(){
		var queryParams=$('#form_query').serializeJSON();
		console.log(queryParams);
	    $('#tbl_anchorApplication').datagrid('load',queryParams);
	});
	
	/**
	 * 处理申请对话框
	 */
	//打开对话框
	$("#btn_updDlg").click(function(){
		var row = $("#tbl_anchorApplication").datagrid("getSelected");
		if (row==null){
			$.messager.alert("提示","请选中一行数据!","info");
			return false;
		}
		else
		{
			if(row.anchorInformation) {
				$("#dialog_anchorApplication").dialog("setTitle","主播"+row.anchorInformation.anchorName+"的申请");
				$("#input_anchorId").val(row.anchorInformation.anchorId);
			}
			$("#txt_thirdCategory").textbox("setValue",row.businessCategory);
			$("#txt_commission").textbox("setValue",row.commission);
			$("#txt_service").textbox("setValue",row.service);
			$("#txt_memo").textbox("setValue",row.memo);
			var queryParams=$('#form_anchorApplication').serializeJSON();
		    $('#tab_mag').datagrid('load',queryParams);
			openDlg();
		}
	})
	
	//点击保存按钮
	var clickTimes = 0;  //判断点击次数寄存
	$("#btn_agree").click(function(){
		//此处操作是为了防止多次点击导致多次执行函数
		clickTimes++;
        if(clickTimes == 1) {
        	var anchorId = $("#input_anchorId").val();
    		var applicationId = $("#tbl_anchorApplication").datagrid("getSelected");
    		if(applicationId) {
    			applicationId = applicationId.applicationId;
    		}
        	var rows = $('#tab_mag').datagrid('getChecked');
        	var goodsIds = new Array();
        	for(var i=0; i<rows.length; i++) {
        		goodsIds[i] = {
//        				'anchorInformation' : {'anchorId':anchorId},
        				'money' : $("#goodsId_"+rows[i].goodsId).val(),
        				'moneyLink' : $("#moneyLink_"+rows[i].goodsId).val(),
        				'comgoodsinformation' : {'goodsId':rows[i].goodsId}
        		};
        	}

        	$.ajax({
    			url:path+"/application/savaAnchorGoodsForApplication?anchorId="+anchorId+"&oldGoodsIds="+oldGoodsIds+"&applicationId="+applicationId,
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
    						closeDlg();
    						clearForm();
    						$("#btn_query").click();
    					} else {
    						$.messager.alert("错误", data.tip ,"error");
    					}
    				} else {
    					$.messager.alert("错误", "未知错误！", "error");
    				}
                }
        	})
        	
    		clickTimes = 0;    //重置点击次数寄存
        } else {
        	return;
        }
	});
	
	/**
	 * 拒绝申请
	 */
	//点击拒绝按钮
	var clickTimes = 0;  //判断点击次数寄存
	$("#btn_disagree").click(function(){
		//此处操作是为了防止多次点击导致多次执行函数
		clickTimes++;
        if(clickTimes == 1) {
        	$("#dialog_refuse").dialog("open");
    		clickTimes = 0;    //重置点击次数寄存
        } else {
        	return;
        }
	});
	
	//拒绝窗口关闭
	$("#btn_no").click(function(){
		$("#txt_reason").textbox("setValue","");
		$("#dialog_refuse").dialog("close");
	});
	
	//拒绝窗口确认
	$("#btn_yes").click(function(){
		var applicationId = $("#tbl_anchorApplication").datagrid("getSelected");
		if(applicationId) {
			applicationId = applicationId.applicationId;
		}
		var reason = $("#txt_reason").textbox("getValue");
		var postData = {'applicationId':applicationId, 'reason':reason};
		var url = path+"/application/refuseApplication";
		$.post(url, postData, function (data) {
			if(data != null && data.tip != null){
				if(data.tip == "succ") {
					$.messager.alert("提示", "已拒绝！", "info");
					initTable();
					$("#btn_no").click();
					closeDlg();
				}
				else {
					$.messager.alert("错误", data.tip, "error");
				}
			} else {
				$.messager.alert("错误", "未知错误！", "error");
			}
		});

	});
	
	
	//点击关闭按钮
	$("#btn_close").click(function(){
		closeDlg();
		clearForm();
	})
	
	//打开商品申请对话框函数
	function openDlg(){
		$("#dialog_anchorApplication").dialog("open");
	}
	
	//关闭商品申请对话框函数
	function closeDlg() {
		$("#dialog_anchorApplication").dialog("close");
	}
	
	//重置对话框表单函数
	function clearForm() {
		$("#input_anchorId").val("");
		$("#txt_thirdCategory").textbox("setValue","");
		$("#txt_commission").textbox("setValue","");
		$("#txt_service").textbox("setValue","");
		$("#txt_memo").textbox("setValue","");
	}
	
	function initGoods(){
	    $('#tab_mag').datagrid({
	        url:path+"/anchor/getComgoodsByCon",
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
	                	return "<div style='text-align: center;'><input id='goodsId_"+row.goodsId+"' name='' value='' style='width: 45px;height: 18px'></div>";
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
	}
	
	//初始化数据表格
	function initTable() {
		var url=path+"/application/getAnchorApplicationByCon1";
		$('#tbl_anchorApplication').datagrid({
	        loadMsg:"加载数据中......",
	    	url:url,
	    	queryParams:{status:"未处理"},
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
	            {field: 'anchorName',title: '姓名',width:150,
		        	formatter: function(value,row,index){
		                if (row.anchorInformation){
		                    return row.anchorInformation.anchorName;
		                } else {
		                    return value;
		                }
		            }
	            },
	            {field: 'anchorSex',title: '性别',width:150,
		        	formatter: function(value,row,index){
		                if (row.anchorInformation){
		                    return row.anchorInformation.anchorSex;
		                } else {
		                    return value;
		                }
		            }
	            },
	            {field: 'phone',title: '手机号',width:150,
		        	formatter: function(value,row,index){
		                if (row.anchorInformation){
		                    return row.anchorInformation.phone;
		                } else {
		                    return value;
		                }
		            }
	            },
	            {field: 'fans',title: '粉丝数',width:150,
		        	formatter: function(value,row,index){
		                if (row.anchorInformation){
		                    return row.anchorInformation.fans;
		                } else {
		                    return value;
		                }
		            }
	            },
	            {field: 'businessCategory',title: '商品类目',width:150,},
	            {field: 'commission',title: '佣金',width:150,},
	            {field: 'service',title: '服务费',width:150,},
	            {field: 'status',title: '状态',width:150},
	         ]],
	         onSelect:function(index, row){
	        	 if(row.status == "已拒绝")
	        		 $("#btn_updDlg").linkbutton("disable");
	        	 else
	        		 $("#btn_updDlg").linkbutton("enable");
	         }
		})
	}


})
