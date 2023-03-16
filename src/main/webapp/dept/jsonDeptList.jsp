<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
//jsp페이지 이지만 page directive에 (1번줄) mime type = application/json
//브라우저는 json 포맷으로 인지한다
String temp=(String)request.getAttribute("jsonDoc");
out.print(temp);

%>