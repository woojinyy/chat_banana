<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서관리2</title>
<%@include file="../../common/easyUI_common.jsp" %>
    <script>
    
      getDeptList = () => {
        console.log("getDeptList 호출");
        $("#dg_dept").datagrid({
          //XXX.jsp , XXX.do, XXX.spring
          url: "./dept.json",
          method: "get",
        });
      };
      
    </script>
</head>
<body>
		<table id = "dg"></table>
		<script type = "text/javascript">
		//jquery에서 제공하는 ready함수 :  의미=돔 구성이 되었을 때 
	window.addEventListener("load",function(event){//jquery의ready나 window의 load나 똑같다
});
		$('#dg').datagrid({
		    title:'부서관리',
			url:'datagrid_data.json',
		    columns:[[
		        {field:'code',title:'Code',width:100},
		        {field:'code',title:'입력',width:100},
		        {field:'name',title:'Name',width:100},
		        {field:'price',title:'Price',width:100,align:'right'}
		    ]]
		});

		</script>
<div id="toolbar">
      <a href="javascript:void(0)"   class="easyui-linkbutton"  iconCls="icon-search"   plain="true"  onclick="getDeptList()">조회</a>
   
    </div>
</body>
</html>