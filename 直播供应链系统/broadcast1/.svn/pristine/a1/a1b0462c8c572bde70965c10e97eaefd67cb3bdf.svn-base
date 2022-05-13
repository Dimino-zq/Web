/**
 * 
 */
$(document).ready(function() {
	var path = getRootPath();
	
	var selectedRow = {
			"table":null,	//属于那个datagrid
			"index":null,	//哪一行
			"level":null,	//类别等级
			"row":null		//行数据
	};	//当前已选中的行
	
	merchkind();
	
	initCombobox();

	//查询
	$("#btn_query").click(function() {
		var queryParams = $('#form_query').serializeJSON();
		$('#tbl_merchKind').datagrid('load', queryParams);
	});

	//点击增加--对话框打开
	$("#btn_addDlg").click(function() {
		if (selectedRow.table != null && selectedRow.level!="3") {
			var row = selectedRow.row;
			$("#com_level").combobox("setValue", 1+parseInt(row.categorylevel));
			if (null != row)
				$("#txt_merFchKind").combobox("setValue", row.categoryId);
			else
				$("#txt_merFchKind").combobox("setValue", "");
		}
		$("#dlg_merchKind").dialog("open");
	});

	//点击增加对话框中关闭--关闭
	$("#btn_close").click(function() {
		clearForm();
		$("#dlg_merchKind").dialog("close");
	});

	//增加
	$("#btn_save").click(function() {
		var comName = $("#txt_KindName").textbox("getValue");

		if (comName == "") {
			$.messager.alert("提示", "请输入商品类别名称!", "info");
			$("#txt_KindName").next("span").find("input[type='text']").focus();
			return false;
		}

		var level = $("#com_level").combobox("getValue");
		if (level == "") {
			$.messager.alert("提示", "请选择商品等级!", "info");
			return false;
		}

		var categoryId = $("#txt_merchKindId").val();
		if (categoryId == "") {
			var url = path + "/merchkind/saveCommCategories";
			var postData = $('#form_merchKind').serializeJSON();
			console.log(postData);
			$.post(url, postData, function(data) {
				if (data == "succ") {
					$.messager.alert("提示", "增加成功", "info")
					$("#dlg_merchKind").dialog("close");
					if(selectedRow.table!=null)
						(selectedRow.table).datagrid("reload");
					else
						$("#btn_query").click();
					//$("#btn_query").click();
					clearForm();
				} else {
					$.messager.alert("提示", "增加失败，请更改后重试", "info");
				}
			});
		} else {
			var url = path + "/merchkind/updCommCategories";
			var postData = $('#form_merchKind').serializeJSON();
			$.post(url, postData, function(data) {
				if (data == "succ") {
					$.messager.alert("提示", "修改成功", "info");
					$("#dlg_merchKind").dialog("close");
					if(selectedRow.table!=null)
						(selectedRow.table).datagrid("reload");
					else
						$("#btn_query").click();
					clearForm();
				} else {
					$.messager.alert("提示", "修改失败", "info");
				}
			});
		}
	});
	//点击修改
	$("#btn_updDlg").click(function() {
		if (selectedRow.table == null) {
			$.messager.alert("提示", "请选中要修改的行", "info");
			return false;
		} else {
			var row = selectedRow.row;
			$("#txt_merchKindId").val(row.categoryId);
			$("#txt_KindName").textbox("setValue", row.categoryName);
			$("#com_level").combobox("setValue", row.categorylevel);
			if (null != row.parent)
				$("#txt_merFchKind").combobox("setValue", row.parent.categoryId);
			else
				$("#txt_merFchKind").combobox("setValue", "");
			$('#dlg_merchKind').dialog('setTitle', '修改商品类别信息');
			$("#dlg_merchKind").dialog("open");
		}
	});
	//删除
	$("#btn_delDlg").click(function() {
		if (selectedRow.table != null) {
			var row = selectedRow.row;
			var categoryId = row.categoryId;
			var postData = {
				"categoryId": categoryId
			};
			$.post((path + "/merchkind/deleteCommCategories"), postData, function(data) {
				if ("deletesuccess" == data) {
					$.messager.alert("提示", "删除成功！", "info");
					if(selectedRow.table!=null)
						(selectedRow.table).datagrid("reload");
					else
						$("#btn_query").click();
				} else {
					$.messager.alert("提示", "删除失败!", "info");
				}

			});
		} else {
			$.messager.alert("提示", "请选中一行！", "info");
			return false;
		}
	});



	function clearForm() {
		$("#txt_merchKindId").val(null);
		$("#txt_KindName").textbox("clear");
		$("#txt_merFchKind").combobox("setValue", null);
		$("#com_level").combobox("reset");
	}

	//初始化表单
	var fatherinternalTimer;
	
	function merchkind() {
		var url = path + "/merchkind/getCommCategoriesByCon";
		$("#tbl_merchKind").datagrid({
			loadMsg: "加载数据中......",
			url: url,
			queryParams: {categorylevel:"1"},
			border: false,
			striped: true,
			fit: true,
			rownumbers: true,
			autoRowHeight: false,
			singleSelect: true,
			fitColumns: true,
			pagePosition: "bottom",
			pagination: true,
			pageSize: 15,
			pageList: [15, 25, 55],
			pageNumber: 1,
			columns: [[
				{field: "categoryName",title: "类别名称",width: 100},
				{field: "categorylevel",title: "类别等级",width: 100},
			]],
			view: detailview, 
			detailFormatter:function(index,row){//严重注意喔
				return "<div id='IMSIS'><table id='ddv-" + index + "' ></table></div>"; 
			}, 
			onExpandRow: function(index,row){//嵌套第一层，严重注意喔
				var ddv = $(this).datagrid('getRowDetail',index).find('#ddv-'+index);//严重注意喔 
				ddv.datagrid({ 
					url:path + "/merchkind/getchildcategories",
					border: false,
					autoRowHeight:true, 
					scrollbarSize: 0,//去掉滚动条
					fitColumns:true,//改变横向滚动条 
					singleSelect:true,//去掉选中效果 
					loadMsg:'', 
					height:'auto', 
					queryParams:{parentId:row.categoryId},
					columns:[[
						{field: "categoryName",title: "名称",width: 100},
						{field: "categorylevel",title: "等级",width: 100},
					]],
					onResize:function(width, height){
						setTimeout(function(){
							ddv.datagrid("resize",{
								width:width,
								height:height
							});
	                        $('#tbl_merchKind').datagrid('fixDetailRowHeight', index);
						},0);
					},
					onLoadSuccess:function(){
						setTimeout(function(){
							$('#tbl_merchKind').datagrid('fixDetailRowHeight', index);
						},0);
					},
					view: detailview, 
					detailFormatter:function(index1,row1){//严重注意喔 
						return '<div"><table id="ddvv-' + index1 + '" ></table></div>'; 
					}, 
					onExpandRow: function(index1,row1){//嵌套第一层，严重注意喔 
						var ddvv = $(this).datagrid('getRowDetail',index1).find('#ddvv-'+index1);//严重注意喔 
						ddvv.datagrid({
							url:path + "/merchkind/getchildcategories",
							border: false,
							autoRowHeight:true, 
							scrollbarSize: 0,//去掉滚动条
							fitColumns:true,//改变横向滚动条 
							singleSelect:true,//去掉选中效果 
							loadMsg:'', 
							height:'auto', 
							queryParams:{parentId:row1.categoryId},
							columns:[[
								{field: "categoryName",title: "名称",width: 100},
								{field: "categorylevel",title: "等级",width: 100},
							]],
							onResize:function(width, height){
								setTimeout(function(){
									ddvv.datagrid("resize",{
										width:width,
										height:height
									});
									ddv.datagrid('fixDetailRowHeight',index1);
								},0);
							},
							onLoadSuccess:function(){
								setTimeout(function(){
									ddv.datagrid('fixDetailRowHeight',index1);
								},0);
							},
							onSelect: function(index2, row2) {		//用于控制始终只能同时选择一行数据
								if(selectedRow.table !=null && selectedRow.row != row2) {
									(selectedRow.table).datagrid("unselectRow",selectedRow.index);
								}
								selectedRow.table = ddvv;
								selectedRow.row = row2;
								selectedRow.level = row2.categorylevel;
								selectedRow.index = index2;
							}
						});
						ddv.datagrid('fixDetailRowHeight', index1);
					},
					onCollapseRow: function(index1,row1) {
						setTimeout(function(){
							$('#tbl_merchKind').datagrid('fixDetailRowHeight',index);
						},0);
					},
					onSelect: function(index1, row1) {//用于控制始终只能同时选择一行数据
						if(selectedRow.table !=null && selectedRow.row != row1) {
							(selectedRow.table).datagrid("unselectRow",selectedRow.index);
						}
						selectedRow.table = ddv;
						selectedRow.row = row1;
						selectedRow.level = row1.categorylevel;
						selectedRow.index = index1;
					}
				});
				$('#tbl_merchKind').datagrid('fixDetailRowHeight', index);
			},
			onSelect: function(index, row) {//用于控制始终只能同时选择一行数据
				if(selectedRow.table !=null && selectedRow.row != row) {
					console.log(row);
					(selectedRow.table).datagrid("unselectRow",selectedRow.index);
				}
				selectedRow.table = $("#tbl_merchKind");
				selectedRow.row = row;
				selectedRow.level = row.categorylevel;
				selectedRow.index = index;
			}
		});
	}
	
               
		                  
	
	//初始化各个下拉列表框数据
	function initCombobox() {
		//部门等级下拉列表框数据初始化
		$("#com_level").combobox({
			value: 1,
			editable: false,
			valueField: 'value',
			textField: 'label',
			data: [{
				label: '一级类别',
				value: 1
			}, {
				label: '二级类别',
				value: 2
			}, {
				label: '三级类别',
				value: 3
			}],

			onSelect: function(record) {
				var param = record.value - 1;
				//仅当部门等级为2时上级部门才可以选择
				if (1 == record.value) {
					$("#txt_merFchKind").combobox({

						disabled: true,
					})
				} else {

					$("#txt_merFchKind").combobox({
						valueField: 'categoryId',
						textField: 'categoryName',
						url: path + '/merchkind/getparent',
						disabled: false,
						queryParams: {
							"categorylevel": param
						}
					});
				}





			}
		});
	}
	/*	
	    //(查询栏)获取上级部门下拉列表框数据
		$("#txt_merFchKind").combobox({
			value:null,
			limitToList:true,
	    	valueField:'categoryId',
	    	textField:'categoryName',
	    	url:path+'/merchkind/getSuperName',
	    	onLoadSuccess:function(){
	    		var data1 = $("#txt_merFchKind").combobox("getData");
	    		//上级部门下拉列表框数据初始化
	    	    $("#txt_merFchKind").combobox({
	    	    	valueField:'categoryId',
	    	    	textField:'categoryName',
	    	    	data:data1
	    	    });
	    	}
	    });
		
	
	}*/

})