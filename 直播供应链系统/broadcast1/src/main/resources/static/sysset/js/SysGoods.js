$(document).ready(function(){
	var path=getRootPath();
	//alert("sss");
	company();
    initCombobox() ;
	
	$(".panel-tool-close").click(function(){
		window.location.reload();

	});
	
	
	$("#btn_addimg").click(function(){
    	$("#saveimg").dialog("open");
  	});
    $("#btn_closeimg").click(function(){
    	$("#saveimg").dialog("close");
  	});
	$("#btn_saveimg").click(function(){
			    	/*$.ajaxFileUpload({
						    url: '/comgoods/saveimga',
						    type: 'post',
						    //data: otherObj,
						    secureuri: false,
						    fileElementId: 'imgGifFile',
						    dataType: 'json',
						    success: function (data) {
						       // ("#courseBank").datagrid("reload");              
								 if (data != "") {
						            $.messager.alert("提示", "头像修改成功！");
						        } else {
						            $.messager.alert("提示", "头像添加成功！");
						        }
						    }
						   /* error: function () {
						        window.OnLoadFunc.isSaved();
						        $.messager.alert("提示", "服务器无响应，请稍后重试！");
						    }*/
					/*});*/
				
							
				        var file = $("#imgGifFile").next().find('input[type=file]')[0].files[0];
				        console.log(file);
				        if (file == null)
				        {
				        	$.messager.alert("提示",'错误，请选择文件',"info");
				        	return;
				        }
				        var fileName = file.name;
				        var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
				        if (file_typename == '.jpg')
				        {
							console.log(file_typename);
				        	ajaxFileUpload(file);
							

				        	//$("#saveimg").dialog("close");
				        	
				        }
				        else
				        {
				        	$.messager.alert("提示","文件类型错误","info");
				        	$("#saveimg").dialog("close");
				        } 
				
					
  	});

    //上传文件函数,预览图片
    function ajaxFileUpload(file) {
        var formData = new FormData();
        formData.append("file", file);
        $.ajax({
            url : path+ "/comgoods/saveimga",
            type : "post",
            async : false,
			cache:false,
            data : formData,
            processData : false,
            contentType : false,
            beforeSend : function() {
                console.log("正在进行，请稍候");
            },
            
			success : function(data) {
				
            	
            	 var thisData=eval(data);   
                 console.log(thisData);
					for(var key in thisData){
									if(key=="code"){        			 
			           			  
								 $("#pictureimg").attr("src","data:image/jpeg;base64,"+thisData[key]);         			 
			           		 }else{
			           			
			           		 }   
						
					}
				                 
					
                 
                
            },
            dataType : "json"
        });
    }

	
	$("#btn_addDlg").click(function(){
    	$("#addgoods").dialog("open");
  	});
	//关闭
	$("#btn_close").click(function(){
		clearForm();
   	$("#addgoods").dialog("close");
  });

	$("#btn_query").click(function(){
	        var queryParams=$('#form_query').serializeJSON();
	        $('#tbl_goods').datagrid('load',queryParams);
    });

	//增加
	$("#btn_save").click(function(){
		var postData=$('#form_add').serializeJSON();
		if (postData.goodsName == "") {
 			$.messager.alert("提示","请输入产品名称！","info",function (){
 				$("#txt_goodsName").next("span").find("input[type='text']").focus();
 			});
 		} else if (postData.brandName == "") {
 			$.messager.alert("提示","请输入品牌名字！","info",function (){
 				$("#txt_brandName").next("span").find("input[type='text']").focus();
 			});
 		} else if (postData.goodsPrice == "") {
 			$.messager.alert("提示","请输入产品单价！","info",function (){
 				$("#txt_goodsPrice").next("span").find("input[type='text']").focus();
 			});
 		} else if (postData.commission == "") {
 			$.messager.alert("提示","请输入佣金！","info",function (){
 				$("#txt_commission").next("span").find("input[type='text']").focus();
 			});
 		} else if (postData.transportation == "") {
 			$.messager.alert("提示","请输入物流+包材！","info",function (){
 				$("#txt_transportation").next('span').find("input[type='text']").focus();
 			});
 		} else if (postData.productlinkman == "") {
 			$.messager.alert("提示","请输入产品联系人！","info",function (){
 				$("#txt_productlinkman").next('span').find("input[type='text']").focus();
 			});
 		} else if (postData.phonenumber == "") {
 			$.messager.alert("提示","请输入联系电话！","info",function (){
 				$("#txt_phonenumber").next("span").find("input[type='text']").focus();
 			});
 		} else if (postData.fristCategory == "") {
 			$.messager.alert("提示","请选择一级类别！","info",function (){
 				$("#txt_fristCategory").next("span").find("a").click();
 			});
 		} else if (postData.secondCatogory =="") {
 			$.messager.alert("提示","请选择二级类别！","info",function (){
 				$("#txt_secondCatogory").next("span").find("a").click();
 			});
 		} else if (postData.saleTrait =="") {
 			$.messager.alert("提示","请输入卖点！","info",function (){
 				$("#txt_saleTrait").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.inventory =="") {
 			$.messager.alert("提示","请输入库存！","info",function (){
 				$("#txt_inventory").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.nomalPrice =="") {
 			$.messager.alert("提示","请输入日常零售价！","info",function (){
 				$("#txt_nomalPrice").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.discountWay =="") {
 			$.messager.alert("提示","请输入优惠方式！","info",function (){
 				$("#txt_discountWay").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.roomPrice =="") {
 			$.messager.alert("提示","请输入直播到手价！","info",function (){
 				$("#txt_roomPrice").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.size =="") {
 			$.messager.alert("提示","请输入规格！","info",function (){
 				$("#txt_size").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.comment =="") {
 			$.messager.alert("提示","请输入备注！","info",function (){
 				$("#txt_comment").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.douyinGoodsLink =="") {
 			$.messager.alert("提示","请输入抖音链接！","info",function (){
 				$("#txt_douyinGoodsLink").next("span").find("input[type='text']").focus();
 			});
 			
 		} else if (postData.kuaishougoodslink =="") {
 			$.messager.alert("提示","请输入快手链接！","info",function (){
 				$("#txt_kuaishougoodslink").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.otherlink =="") {
 			$.messager.alert("提示","请输入其他链接！","info",function (){
 				$("#txt_otherlink").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.taobaoCommissionLink =="") {
 			$.messager.alert("提示","请输入淘宝佣金链接！","info",function (){
 				$("#txt_taobaoCommissionLink").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.taobaoGoodsLink =="") {
 			$.messager.alert("提示","请输入淘宝/天猫产品链接！","info",function (){
 				$("#txt_taobaoGoodsLink").next("span").find("input[type='text']").focus();
 			});
		
 		} else if (postData.pastprice =="") {
 			$.messager.alert("提示","请输入往常主播价(主播名-直播价)！","info",function (){
 				$("#txt_pastprice").next("span").find("input[type='text']").focus();
 			});
		
 		} else {
 			
			//校验图片
			var file = $("#uploadimg").next().find('input[type=file]')[0].files[0];
			//强制上传图片
			if (file == null)
			{
				$.messager.alert("提示",'错误，请选择文件',"info");
				return;
			}
			var fileName = file.name;
			var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
			if(file.size>1*1024*2024) {
				$.messager.alert("提示","文件大小超过1MB！","info");
				return;
			} else if(file_typename.toLowerCase() != '.jpg') {
				$.messager.alert("提示","文件类型错误,请选择.jpg格式的图片","info");
				return;
			}
 			//上传数据
 			var txt_goodsId = postData.goodsId;
 			//goodsId为空时进行增加操作
			if (txt_goodsId=="") {
				var url=path+"/comgoods/savecomgoods";
				var formData = new FormData($('#form_add')[0]);
				$.ajax({
					url : path+ "/sysgoods/saveimga",
					type : "post",
					async : false,
					cache:false,
					data : formData,
					processData : false,
					contentType : false,
					beforeSend : function() {
						console.log("正在进行，请稍候");
					},
					success : function(data) {
						var thisData=eval(data);   
						for(var key in thisData) {
							if(key=="backmess") {
								if (thisData[key]=="succ") {
									$.messager.alert("提示","增加成功","info")
									$("#addgoods").dialog("close");
									$("#btn_query").click();
									clearForm();
								} else {
									$.messager.alert("提示","增加失败请更改后重试","info");
									$("#txt_brandName").next("span").find("input[type='text']").focus();	
								}
							}
						}
					},
					dataType : "json"
				});

			} else {
				//修改操作
				var url=path+"/comgoods/updcomgoods";
				var formData = new FormData($('#form_add')[0]);
		        $.ajax({
		            url : path+ "/sysgoods/updcomgoods",
		            type : "post",
		            async : false,
					cache:false,
		            data : formData,
		            processData : false,
		            contentType : false,
		            beforeSend : function() {
		                console.log("正在进行，请稍候");
		            },						            
					success : function(data) {					            	
		            	 var thisData=eval(data);   
							for(var key in thisData){
									if(key=="code"){        			 
					           			  
										// $("#pictureimg").attr("src","data:image/jpeg;base64,"+thisData[key]);         			 
					           		 }else{
					           			
					           		 } 
									if(key=="backmess"){
										if (thisData[key]=="succ"){
									        	$.messager.alert("提示","修改成功","info")
									        	$("#addgoods").dialog("close");
									        	$("#btn_query").click();
									        	clearForm();
									        }else{
									          $.messager.alert("提示","修改失败请更改后重试","info");
											  $("#txt_brandName").next("span").find("input[type='text']").focus();	
									        } 														
									} 				
							}				                
		             },
		             dataType : "json"
		        });
			
			
			
		}
	
		}
	
		
		
		
		
		
		
		
	});
	
	
	//点击修改
	$("#btn_updDlg").click(function(){
		var  row=$('#tbl_goods').datagrid("getSelected");
		console.log(row);
		if (row==null){
			$.messager.alert("提示","请选中要修改的行","info");
			return false;
		}else{
			$("#addgoods").dialog("open");
			$("#pictureimg").attr("src","data:image/jpeg;base64,"+row.goodsPhoto);  
			$("#txt_goodsId").val(row.goodsId);
			$("#imgString").val(row.goodsPhoto);
			$("#txt_goodsName").textbox("setValue",row.goodsName);
			$("#txt_brandName").textbox("setValue",row.brandName);
			$("#txt_size").textbox("setValue",row.size);
			$("#txt_goodsPrice").textbox("setValue",row.goodsPrice);
			$("#txt_inventory").textbox("setValue",row.inventory);
			$("#txt_commission").textbox("setValue",row.commission);
			$("#txt_comment").textbox("setValue",row.comment);
			$("#txt_transportation").textbox("setValue",row.transportation);
			$("#txt_nomalPrice").textbox("setValue",row.nomalPrice);
			$("#txt_roomPrice").textbox("setValue",row.roomPrice);
			$("#txt_discountWay").textbox("setValue",row.discountWay);
			$("#txt_pastprice").textbox("setValue",row.pastprice);
			$("#txt_saleTrait").textbox("setValue",row.saleTrait);
			$("#txt_productlinkman").textbox("setValue",row.productlinkman);
			$("#txt_phonenumber").textbox("setValue",row.phonenumber);
			
			//$("#txt_fristCategory").combobox("setValue",row.fristCategory);			
			//$("#txt_secondCatogory").combobox("setValue",row.secondCatogory);
			//setTimeout(function(){$("#txt_ThirdCatogory").combobox("setValue",row.thirdCategory);},50);
			$("#txt_fristCategory").combobox({
					onLoadSuccess: function(param){
						
						
						$("#txt_fristCategory").combobox("setValue",row.fristCategory);
					}
				});
			$("#txt_secondCatogory").combobox({
					onLoadSuccess: function(param){
						
						
						$("#txt_secondCatogory").combobox("setValue",row.secondCatogory);
					}
				});	
			$("#txt_ThirdCatogory").combobox({
					onLoadSuccess: function(param){
						
						
						$("#txt_ThirdCatogory").combobox("setValue",row.thirdCategory);
					}
				});
			
			
			$("#txt_taobaoCommissionLink").textbox("setValue",row.taobaoCommissionLink);
			$("#txt_taobaoGoodsLink").textbox("setValue",row.taobaoGoodsLink);
			$("#txt_douyinGoodsLink").textbox("setValue",row.douyinGoodsLink);
			$("#txt_kuaishougoodslink").textbox("setValue",row.kuaishougoodslink);
			$("#txt_otherlink").textbox("setValue",row.otherlink);
			
			
			$('#addgoods').dialog('setTitle', '修改商品');
			
		}
		});
	
	
	//清空表单功能
	function clearForm(){
		$("#txt_goodsId").val("");
		$("#imgString").val("");
		$("#txt_goodsName").textbox("setValue","");
			$("#txt_brandName").textbox("setValue","");
			$('#uploadimg').filebox('clear');
			$("#txt_size").textbox("setValue","");
			$("#txt_goodsPrice").textbox("setValue","");
			$("#txt_inventory").textbox("setValue","");
			$("#txt_commission").textbox("setValue","");
			$("#txt_comment").textbox("setValue","");
			$("#txt_transportation").textbox("setValue","");
			$("#txt_nomalPrice").textbox("setValue","");
			$("#txt_roomPrice").textbox("setValue","");
			$("#txt_discountWay").textbox("setValue","");
			$("#txt_pastprice").textbox("setValue","");
			$("#txt_saleTrait").textbox("setValue","");
			$("#txt_productlinkman").textbox("setValue","");
			$("#txt_phonenumber").textbox("setValue","");
			$("#txt_fristCategory").combobox("setValue","");
			$("#txt_secondCatogory").combobox("setValue","");
			$("#txt_ThirdCatogory").combobox("setValue","");
			
			$("#txt_taobaoCommissionLink").textbox("setValue","");
			$("#txt_taobaoGoodsLink").textbox("setValue","");
			$("#txt_douyinGoodsLink").textbox("setValue","");
			$("#txt_kuaishougoodslink").textbox("setValue","");
			$("#txt_otherlink").textbox("setValue","");
			$('#pictureimg').attr('src', "");
			$('#addgoods').dialog('setTitle', '新增商品');
	}
	
	//删除
	$("#btn_delDlg").click(function () {
    var row=$("#tbl_goods").datagrid("getSelected");
        if (null!=row)
        {
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
				if (r)
				{
          		var goodsId=row.goodsId;
          		var postData={"goodsId":goodsId};
          		$.post((path+"/comgoods/deletecomgoods"),postData,function (data) {
              		if ("deletesuccess"==data)
             	 	{
                  		$.messager.alert("提示","删除成功！","info");
                  		company()
              		}else{
						$.messager.alert("提示","该商品已被分配给主播，不允许删除！","info");
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
	
	
	
	
	
	//查看
	$("#btn_seeDlg").click(function(){
		var  row=$('#tbl_goods').datagrid("getSelected");
		if (row==null){
			$.messager.alert("提示","请选中需要查看的商品","info");
			return false;
		}else{
			$("#photo").attr("src","data:image/jpeg;base64,"+row.goodsPhoto);
			$('#header').text('产品名称：'+row.goodsName)
			$(".zz li>p").eq(0).text(row.brandName)
					$(".zz li>p").eq(1).text(row.goodsPrice)
					$(".zz li>p").eq(2).text(row.nomalPrice)
					$(".zz li>p").eq(3).text(row.discountWay)
					$(".zz li>p").eq(4).text(row.commission)
					$(".zz li>p").eq(5).text(row.transportation)
					$(".zz li>p").eq(6).text(row.productlinkman)
					$(".zz li>p").eq(7).text(row.phonenumber)
					$(".zz li>p").eq(8).text(row.size)
					$(".zz li>p").eq(9).text(row.roomPrice)
					$(".zz li>p").eq(10).text(row.saleTrait)
					$(".zz li>p").eq(11).text(row.inventory)
					$(".zz li>p").eq(12).text(row.fristCategory)
					$(".zz li>p").eq(13).text(row.secondCatogory)
					$(".zz li>p").eq(14).text(row.thirdCategory)
					$(".zz li>p").eq(15).text(row.taobaoCommissionLink)
					$(".zz li>p").eq(16).text(row.taobaoGoodsLink)
					$(".zz li>p").eq(17).text(row.pastprice)
					$(".zz li>p").eq(18).text(row.douyinGoodsLink)
					$(".zz li>p").eq(19).text(row.kuaishougoodslink)
					$(".zz li>p").eq(20).text(row.otherlink)
					$(".zz li>p").eq(21).text(row.comment)
			$("#txt_savegoods").dialog("open");
			
			/*
			$("#txt_goodsId1").val(row.goodsId);
			$("#pictureimg1").attr("src","data:image/jpeg;base64,"+row.goodsPhoto);
			$("#txt_goodsName1").textbox("setValue",row.goodsName);
			$("#txt_brandName1").textbox("setValue",row.brandName);
			$("#txt_size1").textbox("setValue",row.size);
			$("#txt_goodsPrice1").textbox("setValue",row.goodsPrice);
			$("#txt_inventory1").textbox("setValue",row.inventory);
			$("#txt_commission1").textbox("setValue",row.commission);
			$("#txt_comment1").textbox("setValue",row.comment);
			$("#txt_transportation1").textbox("setValue",row.transportation);
			$("#txt_nomalPrice1").textbox("setValue",row.nomalPrice);
			$("#txt_roomPrice1").textbox("setValue",row.roomPrice);
			$("#txt_discountWay1").textbox("setValue",row.discountWay);
			$("#txt_pastprice1").textbox("setValue",row.pastprice);
			$("#txt_saleTrait1").textbox("setValue",row.saleTrait);
			$("#txt_productlinkman1").textbox("setValue",row.productlinkman);
			$("#txt_phonenumber1").textbox("setValue",row.phonenumber);
			$("#txt_fristCategory1").combobox("setValue",row.fristCategory);
			$("#txt_secondCatogory1").combobox("setValue",row.secondCatogory);
			$("#txt_thiCategoryright1").combobox("setValue",row.thirdCategory);
			$("#txt_taobaoCommissionLink1").textbox("setValue",row.taobaoCommissionLink);
			$("#txt_taobaoGoodsLink1").textbox("setValue",row.taobaoGoodsLink);
			$("#txt_douyinGoodsLink1").textbox("setValue",row.douyinGoodsLink);
			$("#txt_kuaishougoodslink1").textbox("setValue",row.kuaishougoodslink);
			$("#txt_otherlink1").textbox("setValue",row.otherlink);*/
			$('#addgoods').dialog('setTitle', '查看全部字段');
			
		}
	});
	
	function company(){
		var url=path+"/sysgoods/getComgoodsByCon";
		$("#tbl_goods").datagrid({
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
       
		onClickCell:function(rowIndex,field,value){
		              if(field=='commission'){
		                      var rows = $('#tbl_goods').datagrid('getRows');
		                        var row = rows[rowIndex];                   
		            console.log(row);
					comanchor(row);
					$("#seecommission").dialog("open");
		            
		          }else{
		               return false;
		          }
		    },
		columns:[[
			{field:"goodsName",title:"产品名称",width:100},
			{field:"brandName",title:"品牌名字",width:100},
			{field:"size",title:"规格",width:100},
			{field:"goodsPrice",title:"产品单价",width:100},
			{field:"inventory",title:"库存",width:100},
			{field:"productlinkman",title:"产品联系人",width:100},
			{field:"fristCategory",title:"一级类别",width:100},
			{field:"secondCatogory",title:"二级类别",width:100},
			{field:"thirdCategory",title:"三级类别",width:100},
			{field:"phonenumber",title:"联系电话",width:100},
			{field:"commission",title:"佣金",width:100},
	      ]]
	});
	}
	
	
	function comanchor(row){
		console.log(row);
		var url=path+"/sysgoods/getAnchorComm";
		$("#seeanchorcom").datagrid({
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
        pageSize:20,
        pageList:[20,40,60],
        pageNumber:1,
        queryParams:{"goodsId": row.goodsId},
		columns:[[
			{field:"anchorName",title:"主播姓名",width:100},
			{field:"money",title:"主播佣金",width:100},
			{field:"comsession",title:"商品佣金",width:100,
				formatter(value, row, index){
					return row.commission;
				}
			}
	      ]]
	});
	}
	

     //初始化各个下拉列表框数据
	function initCombobox() {
		
				
				//院系和专业下拉列表框数据的初始化以及联动
    	$("#txt_fristCategory").combobox({
	        
    		valueField: 'categoryName',
			textField: 'categoryName',
			value: "",             //默认初始值
    		url:path+'/comgoods/getComgoodsLevelFri',
    		onSelect:function(record){
	            console.log(record);
    			var param = record.categoryId;
                console.log(param);
    			$("#txt_secondCatogory").combobox({
    				
    				valueField: 'categoryName',
					textField: 'categoryName',
					
    				url: path + '/comgoods/getComgoodsParents',
    				queryParams:{"categoryId": param},
					onSelect:function(record){
						if(record != ""){
							var paramw = record.categoryId;
					    			$("#txt_ThirdCatogory").combobox({
					    				
					    				valueField: 'categoryName',
										textField: 'categoryName',
										
					    				url: path + '/comgoods/getComgoodsParents',
					    				queryParams:{"categoryId": paramw},
					    			});
							
								}
					    			
					    		}

    			});
    		}
    	});
		
		
		$("#queryFristCategory").combobox({
	        
    		valueField: 'categoryName',
			textField: 'categoryName',
    		url:path+'/comgoods/getComgoodsLevelFri',
    		onSelect:function(record){
				
	            console.log(record);
    			var param = record.categoryId;
                console.log(param);
    			$("#querySecondCatogory").combobox({
    				
    				valueField: 'categoryName',
					textField: 'categoryName',
    				url: path + '/comgoods/getComgoodsParents',
    				queryParams:{"categoryId": param},
					onSelect:function(record){
					    			var param = record.categoryId;
					    			$("#queryThiCategory").combobox({
					    				
					    				valueField: 'categoryName',
										textField: 'categoryName',
					    				url: path + '/comgoods/getComgoodsParents',
					    				queryParams:{"categoryId": param}
					
					    			});
					    		}

    			});
    		}
    	});
	

    }





});