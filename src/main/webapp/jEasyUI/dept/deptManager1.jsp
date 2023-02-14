<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서관리</title>
<%@include file="../../common/easyUI_common.jsp" %>
<script>
const insertForm=()=>{
	console.log("입력화면 열기");
}
</script>
</head>
<body>
		<table id = "dg"></table>
		<script type = "text/javascript">
		//jquery에서 제공하는 ready함수 :  의미=돔 구성이 되었을 때 
		$(document).ready(function(){
			//dg가 누구인지 모르면 화면에 출력 안됨
		$('#dg').datagrid({
		    title:'부서관리',
			url:'datagrid_data.json',
		    columns:[[
		        {field:'code',title:'Code',width:100},
		        {field:'name',title:'Name',width:100},
		        {field:'price',title:'Price',width:100,align:'right'}
		    ]]
		});
		});
		</script>
</body>
</html>