$(document).ready(function(){
	alert("!!!asdfgh!!!")
	addoness();
	
		var path=getRootPath();
		 teacherapp();
		 
			$('#tabs_report').tabs({
				
			tools:'#tab-tools'
			
	});
		
		
		function addoness(){
			var c=$('#text_companygrate').numberbox("getValue");
			console.log(c);
		$('input', $("#text_companygrate").next('span')).blur(function() {
			var c=$('#text_companygrate').numberbox("getValue");
			console.log(c);
			var a = Number($("#teachergrate").val());
			console.log(a);
			var b = Number($("#companygrate").val());
			console.log(b);
			$("#text_finalgrate").numberbox("setValue", 0.3*a + 0.7*b);
		});
	}
	function obj(){
		$('input', $("#text_teachergrate").next('span')).blur(function() {
			addoness();
			var a =0.6* Number($("#text_teachergrate").val());
			
			var b =0.4* Number($("#text_companygrate").val());
			$("#text_finalgrate").numberbox("setValue",a+b );
		});
		
	}
	$("#btn_see").click(function(){
		$("#dialog_photo").dialog("open");
	});
		
		
		//按照学生姓名查询
		$("#btn_query").click(function(){
			
	        var queryParams=$('#form_query').serializeJSON();
	        $('#tbl_tapp').datagrid('load',queryParams);
	        console.log(queryParams);
	       
		}
    	);	  
		
		
		/*$("#tbl_tapp").datagrid({ 
                onClickRow: function (index, row) { 
				var status = row.status;
				if (status=="已评阅"){
					$('#btn_seeDlg').linkbutton("disable");
				}
				
				else{
					$('#btn_seeDlg').linkbutton("enable");
				}
				
		
		}
	});*/
		
		
		
		//得到当前日期
		formatterDate = function(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
		};
		 
		window.onload = function () { 
		$('#text_time').datebox('setValue', formatterDate(new Date()));
		}		      		    
			/*$("#dialog_interreport").dialog("setTitle",row.application.student.studentName+"的审阅意见");
    		$("#dialog_interreport").dialog("open");*/
    		//评阅确定
    				
  		
		//点击显示指导模块
		/*$("#btn_seeDlg").click(function(){
			var row = $("#tbl_tapp").datagrid("getSelected");	
			
			
		     if (row==null){
		      $.messager.alert("提示","请选中一行数据!","info");
		      return false;
		     }
		     else{
		    
			$("#dialog_interreport").dialog("setTitle",row.application.student.studentName+"的审阅意见");
    		$("#dialog_interreport").dialog("open");
    		//评阅确定
    		$("#btn_yesout").click(function () { 
				
    			var row = $("#tbl_tapp").datagrid("getSelected");	
    			var url=path+"/teacher/saveTeacherReportAppraisal";	
    			
    			console.log(row.reportId);
				var text = $("#text_write").val();
				var gt = $("#text_teachergrate").val();
    			if( text.length > 200 ){
					$.messager.alert("提示","请将您的评语字数限制为200字以内！","info");
				}
				else if( gt.length > 3){
					$.messager.alert("提示","您输入的成绩有误！","info");
				}
				
				
				
				else{
    			$("#txt_studentInterReportId").val(row.reportId);
    			var postData=$('#form_add').serializeJSON();
    			$.post(url,postData,function(data){
    				if (data=="succ"){
    					$.messager.alert("提示","审阅成功!","info");
    					$("#dialog_interreport").dialog("close");
    					$("#btn_query").click();
    					$("#form_add").form("reset");
    					clearForm();
    				}else{
    					$.messager.alert("提示","失败!","info");
    				}
    			});
    		}   
				 
		     }); 
    		}
  		});*/
		
		
		//查看详情
	  $("#btn_allInfo").click(function(){
	  var row = $("#tbl_tapp").datagrid("getSelected");
	     if (row==null){
	      $.messager.alert("提示","请选中一行数据!","info");
	      return false;
	     }
	     else
	     {

	        var reportId=row.reportId;
	        var url=path+"/teacher/report";
	        //post跳转网页
	        var temp_form = document.createElement("form");
	        temp_form .action = url;
	        temp_form .target = "_self";
	        temp_form .method = "post";
	        temp_form .style.display = "none"; 
	        var opt = document.createElement("textarea");
	        opt.name = "intreportId";
	        opt.value = reportId;
	        temp_form .appendChild(opt);
	        document.body.appendChild(temp_form);
	        temp_form .submit();
	       }    
	 	});
		//btn_allInfo1
	  //查看详情
	  $("#btn_allInfo1").click(function(){
	  var row = $("#tbl_tapp").datagrid("getSelected");
	     if (row==null){
	      $.messager.alert("提示","请选中一行数据!","info");
	      return false;
	     }
	     else
	     {
	    	 var reportId=row.reportId;
		        var url=path+"/teacher/all";
		        //post跳转网页
		        var temp_form = document.createElement("form");
		        temp_form .action = url;
		        temp_form .target = "_self";
		        temp_form .method = "post";
		        temp_form .style.display = "none"; 
		        var opt = document.createElement("textarea");
		        opt.name = "intreportId";
		        opt.value = reportId;
		        temp_form .appendChild(opt);
		        document.body.appendChild(temp_form);
		        temp_form .submit();
		       }    
	    	 
	        
	     
	     
	     
	 	});
	  
	  
	 
	  
	  if($("#txt_status").val()=="已评阅"){
          $('#tabs_report').tabs('enableTab', "实习评阅");	
			$('#text_write').textbox('readonly',true);
			$('#text_write').attr("data-options", "required:false");
			$('#text_write').textbox({disabled:true,required:false});
			
			$('#text_teachergrate').numberbox('readonly',true);
			$('#text_write').textbox('readonly',true);		
			$('#text_time').datebox({disabled:true});			
			$('#btn_yes').linkbutton("disable");
			$("#btn_yes").hide();
			var teachergrate=$('#txt_teachergrate').val();
			var companygrate=$('#txt_companygrate').val();
			var fianlgrate=$('#txt_fianlgrate').val();
			var reviewrComment=$('#txt_reviewrComment').val();
			var signDate=$('#txt_signDate').val();
			console.log(signDate);
			console.log(fianlgrate);
			$('#text_teachergrate').numberbox("setValue",teachergrate);
			$('#text_companygrate').numberbox("setValue",companygrate);
			$('#text_finalgrate').numberbox("setValue",fianlgrate);
			
			$('#text_write').textbox("setValue",reviewrComment);
			$('#text_time').datebox("setValue",signDate);
			
			$('#text_teachergrate').numberbox({disabled:true,required:false});
			$('#text_companygrate').numberbox({disabled:true,required:false});
			$('#text_fianlgrate').numberbox({disabled:true,required:false});
	
		}else{
			$('#text_teachergrate').numberbox({disabled:false,required:true});
			
			$('#text_companygrate').numberbox({disabled:false,required:true});
			$('#text_fianlgrate').numberbox({disabled:false,required:true});
			$('#text_time').datebox({disabled:false});			
			$('#btn_yes').linkbutton("enable");
			$("#btn_yes").show();
		}
	  
		//显示表格内容功能
		function teacherapp(){
		    var url=path+"/teacher/getStudentReportByCon";
		    $("#tbl_tapp").datagrid({
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
            pageList:[2,10,20,50],
            pageNumber:1,
			    columns:[[
				  {field: 'studentName',title: '学生姓名',width: 50,
                  formatter:function(value,row,index){
                     if (row.application.student)
                     {
                         return row.application.student.studentName;
                     }
                     else
                     {
                         return "";
                     }
                  }},
				  {field:'status',title:'评阅状态',width:50,
	                  formatter:function(value,row,index){
	                      if ( null != row.status )
	                      {
	                          return row.status;
	                      }
	                      else
	                      {
	                          return "未评阅";
	                      }
	                   }},
				{field: 'grate',title: '成绩',width: 50,
                  formatter:function(value,row,index){
                     if (row)
                     {
                         return row.grate;
                     }
                     else
                     {
                         return "";
                     }
                  }}
			      ]]
		    })
		}
	
		//all
		
		
		
		
		
		
		//审阅确定
		$("#btn_yes").click(function () {
		   var write=$("#text_write").textbox("getValue");
           var write1=$("#text_write").val();
		  if (write==""){
			$.messager.alert("提示","请写入评语!","info");
			$("#text_write").next("span").find("textarea").focus();
			return  false;
		  }else if( write1.length > 200){
			$.messager.alert("提示","请将您的评语字数限制为200字以内！","info");
			return  false;
		  }
		  var grate=$("#text_teachergrate").numberbox("getValue");
          var grate1=$("#text_teachergrate").val();
		  if (grate==""){
			$.messager.alert("提示","请输入成绩!","info");
			$("#text_grate").next("span").find("input[type='text']").focus();
			return  false;
		  }	
 		  else if( grate1.length > 3){
			$.messager.alert("提示","您输入的成绩有误！","info");
			return  false;
		  }		
			var url=path+"/teacher/saveTeacherReportAppraisal";	
			
		//	console.log(row.reportId);
			
			
			$.messager.confirm(' ', '是否保存评阅内容?', function(r){
				if (r){
					var postData=$('#form_add').serializeJSON();
					$.post(url,postData,function(data){
						if (data=="succ"){
							
					    
						window.location.reload();
						
						
						}else{
							$.messager.alert("提示","失败!","info");
						}
					});
					// exit action;
				}
				clearForm();
			});
		//	$("#txt_studentInterReportId").val(row.reportId);
			$('#text_write').attr("data-options", "required:false");
	     }); 
			
			
				/*if($("#txt_status").val()=="已评阅"){
		            $('#tabs_report').tabs('disableTab', "实习评阅");	
					
				}else{
					$('#tabs_report').tabs('enableTab', "实习评阅");	
				}*/
	function clearForm(){
		$("#text_write").textbox("setValue","");
		$("#text_teachergrate").numberbox("setValue","");
		$("#text_companygrate").numberbox("setValue","");
		$("#text_finalgrate").numberbox("setValue","");
		$("#text_time").datebox("setValue","");
	}
		
});





