<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<!-- 여기는 HTML땅 -->

<%
//여 기는 자바영역 자바코딩 가능함 => 스크립틀릿
//localhost:9000/common/hangulTest.jsp?mem_name=김춘추
String name = request.getParameter("mem_name");
out.print(name);//null

%>

<!-- 여기는 HTML땅 -->