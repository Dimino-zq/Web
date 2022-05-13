$(document).ready(function(){
	var path=getRootPath();
	initTable();
	//查询
	$("#btn_query").click(function(){
	        var queryParams=$('#form_query').serializeJSON();
	        $('#tbl_anchorlist').datagrid('load',queryParams);
    });
	
	
		function initTable(){
		var url=path+"/anchorList/getAnchorApplicationByCon";
		
		$("#tbl_anchorlist").datagrid({
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
				{field:"businessCategory",title:"商品类别",width:100},
				{field:"commission",title:"佣金",width:100},
				{field:"service",title:"服务费",width:100},
				{field:"memo",title:"备注",width:100},
				{field:"status",title:"状态",width:100},
				{field:"reason",title:"拒绝理由",width:100,
				 formatter:function(value,row,index){
                        if(row.reason){
							return row.reason;
                               }
						else{
							return '无';
							
						}
	                          
                        
	                   }},
		      ]]
		});
	}
	
})