$(document).ready(function(){
	
	var path=getRootPath();
	
	//加载数据表格
	initTable();
	
	//点击提交查询信息
	$("#btn_query").click(function(){
		var queryParams=$('#form_query').serializeJSON();
	    $('#tbl_anchorDistribute').datagrid('load',queryParams);
	});
	
	/**
	 * 增加商品申请对话框
	 */
	//打开对话框
	$("#btn_appDlg").click(function(){
		openDlg();
	})
	
	//点击保存按钮
	var clickTimes = 0;  //判断点击次数寄存
	$("#btn_save").click(function(){
		//此处操作是为了防止多次点击导致多次执行函数
		clickTimes++;
        if(clickTimes == 1) {
    		var postData=$("#form_application").serializeJSON();
    		if (postData)
    			createApplication(postData);
    		else
    			alert("postData is null!");
    		clickTimes = 0;    //重置点击次数寄存
        } else {
        	return;
        }
	});
	
	//保存创建的申请表
	function createApplication(postData) {
		//检查表单数据是否符合要求
		if (checkForm(postData)) {
			var url = path + "/anchorDistribute/saveapplication";
			//post提交数据
			$.post(url, postData, function (data) {
				if(data != null && data.tip != null){
					if(data.tip == "succ") {
						$.messager.alert("提示", "已创建申请！", "info");
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
	
	
	
	//点击关闭按钮
	$("#btn_close").click(function(){
		closeDlg();
		clearForm();
	})
	
	
	//打开商品申请对话框函数
	function openDlg(){
		$("#dlg_application").dialog("open");
	}
	
	//关闭商品申请对话框函数
	function closeDlg() {
		$("#dlg_application").dialog("close");
	}
	
	//重置对话框表单函数
	function clearForm() {
		//
	}
	
	//表单字段校验
	function checkForm(postData)
	{
		if (postData.lv1 == "")
			$("#input_lv1").next("span").find("a").click();
		else if (postData.commission == "")
			$("#input_commission").next("span").find("a").click();
		else if (postData.service == "")
			$("#input_service").next("span").find("a").focus();
		else 
			return true;
		//return false;
	}
	
	function initTable(){
		var url=path+"/anchorDistribute/getGoodsDistributionByCon";
		
		$("#tbl_anchorDistribute").datagrid({
		  	loadMsg:"加载数据中......",
	        url:url,
	        border:false,
	        striped:true,
	        fit:true,
	        rownumbers:true,
	        autoRowHeight:false,
	        nowrap:false,
	        singleSelect:true,
	        fitColumns:true,
	        pagePosition:"bottom",
	        pagination:true,
	        pageSize:15,
	        pageList:[15,25,55],
	        pageNumber:1,
			columns:[[
				{field:"goodsName",title:"商品名称",width:100,
					formatter:function(value,row,index){
                        if(row && row.comgoodsinformation) {
							return row.comgoodsinformation.goodsName;
                        } else {
							return "";
						}
					}
				},
				{field:"brandName",title:"品牌",width:100,
					formatter:function(value,row,index){
                        if(row && row.comgoodsinformation) {
    						return row.comgoodsinformation.brandName;
                        } else {
							return "";
						}
	           		}
				},
				{field:"thirdCategory",title:"类别",width:100,
	                formatter:function(value,row,index){
                        if(row && row.comgoodsinformation) {
    	                	return row.comgoodsinformation.thirdCategory;
                        } else {
							return "";
						}
	                }
				},
				{field:"goodsPrice",title:"单价",width:100,
            	   formatter:function(value,row,index){
                       if(row && row.comgoodsinformation) {
                           return row.comgoodsinformation.goodsPrice;
                       } else {
                    	   return "";
                       }
            	   }
				},
				{field:"inventory",title:"库存",width:100,
            	   formatter:function(value,row,index){
                       if(row && row.comgoodsinformation) {
                		   return row.comgoodsinformation.inventory;
                       } else {
							return "";
                       }
            	   }
				},
				{field:"money",title:"佣金",width:100,},
				{field:"productlinkman",title:"产品联系人",width:100,
	            	   formatter:function(value,row,index){
	                       if(row && row.comgoodsinformation) {
	                		   return row.comgoodsinformation.productlinkman;
	                       } else {
								return "";
	                       }
	            	   }
					},
			]],
		  	view: detailview,
			detailFormatter: function(rowIndex, rowData){
				console.log();
				var view = "<table><tr><td style='padding:10px 5px;border:0;'>"+
				"<img src='data:image/jpeg;base64,"+(rowData.comgoodsinformation.goodsPhoto?rowData.comgoodsinformation.goodsPhoto:"")+"' style='width:150px;height: 150px;float:left'>"+
				"<div style='float:left;margin-left:20px;'>" +
				"<p style='margin-top: 5px;margin-bottom: 10px;'>" +
				"<span style='font-weight: bolder;'>商品名称：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.goodsName?rowData.comgoodsinformation.goodsName:"")+"</span>" +
				"<span style='font-weight: bolder;'>商品类别：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.fristCategory?(rowData.comgoodsinformation.fristCategory+(rowData.comgoodsinformation.secondCatogory?("->"+rowData.comgoodsinformation.secondCatogory+(rowData.comgoodsinformation.thirdCategory?("->"+rowData.comgoodsinformation.thirdCategory):"")):"")):"")+"</span>" +
				"<span style='font-weight: bolder;'>商家名称：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.sellerName?rowData.comgoodsinformation.sellerName:"")+"</span>" +
				"</p>" +
				"<p style='margin-top: 5px;margin-bottom: 10px;'>" +
				"<span style='font-weight: bolder;'>商品品牌：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.brandName?rowData.comgoodsinformation.brandName:"")+"</span>" +
				"<span style='font-weight: bolder;'>商品单价：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.goodsPrice?rowData.comgoodsinformation.goodsPrice:"")+"</span>" +
				"<span style='font-weight: bolder;'>商品规格：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.size?rowData.comgoodsinformation.size:"")+"</span>" +
				"</p>" +
				"<p style='margin-top: 5px;margin-bottom: 10px;'>" +
				"<span style='font-weight: bolder;'>日常零售价：</span>" + "<span style='width:241px;display: inline-block;'>"+(rowData.comgoodsinformation.nomalPrice?rowData.comgoodsinformation.nomalPrice:"")+"</span>" +
				"<span style='font-weight: bolder;'>往常主播价：</span>" + "<span style='width:241px;display: inline-block;'>"+(rowData.comgoodsinformation.pastprice?rowData.comgoodsinformation.pastprice:"")+"</span>" +
				"<span style='font-weight: bolder;'>直播到手价：</span>" + "<span style='width:241px;display: inline-block;'>"+(rowData.comgoodsinformation.roomPrice?rowData.comgoodsinformation.roomPrice:"")+"</span>" +
				"</p>" +
				"<p style='margin-top: 5px;margin-bottom: 10px;'>" +
				"<span style='font-weight: bolder;'>优惠方式：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.discountWay?rowData.comgoodsinformation.discountWay:"")+"</span>" +
				"<span style='font-weight: bolder;'>商品库存：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.inventory?rowData.comgoodsinformation.inventory:"")+"</span>" +
				"<span style='font-weight: bolder;'>商品佣金：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.money?rowData.money:"")+"</span>" +
				"</p>" +
				"<p style='margin-top: 5px;margin-bottom: 10px;'>" +
				"<span style='font-weight: bolder;'>佣金链接：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.moneyLink?rowData.moneyLink:"")+"</span>" +
				"<span style='font-weight: bolder;'>商品卖点：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.saleTrait?rowData.comgoodsinformation.saleTrait:"")+"</span>" +
				"<span style='font-weight: bolder;'>产品联系人：</span>" + "<span style='width:255px;display: inline-block;'>"+(rowData.comgoodsinformation.productlinkman?rowData.comgoodsinformation.productlinkman:"")+"</span>" +
				"</p>" +
				"</div>" +
				"</td></tr></table>";
				return view;
			}
		});
	}
	
	//院系和专业下拉列表框数据的初始化以及联动
	$("#input_lv1").combobox({
		valueField:'categoryName',
		textField:'categoryName',
		url:path+'/merchkind/getparent',
		queryParams:{categorylevel:1},
		onSelect:function(lv1){
			var param = lv1.categoryId;
			$("#input_lv2").combobox({
				value:null,
				valueField:'categoryName',
				textField:'categoryName',
				url:path+'/merchkind/getchildcategories',
				queryParams:{"parentId":param},
				onSelect:function(lv2){
					var param = lv2.categoryId;
					$("#input_businessCategory").combobox({
						value:null,
						valueField:'categoryName',
						textField:'categoryName',
						url:path+'/merchkind/getchildcategories',
						queryParams:{"parentId":param}
					});
				}
			});
			$("#input_businessCategory").combobox("setValue","");
		}
	});
	
	
})