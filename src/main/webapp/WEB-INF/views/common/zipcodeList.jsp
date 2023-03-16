<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%
  
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색기</title>
<%@ include file="../common/easyUI_common.jsp" %>
</head>
<body>
<script>
$(document).ready(function(){
		$("#btn_zipcode").linkbutton({
			oncli
		})
})
</script>
<!--======================= 우편번호 검색기 =======================-->
<div id="dlg_zipcode" style="width:100%;max-width:600px;padding:30px 30px;">
	<input class="easyui-textbox" id="dong" name="dong" labelPosition="top" data-options="prompt:'동이름 이나 주소정보 입력...'" style="width:210px;">
	<a id="btn_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">찾기</a>
	<div  style="margin-bottom:10px;"></div>
	<table id="dg_zipcode">
	</table>
</div>
<!--======================= 우편번호 검색기 =======================-->
</body>
</html>