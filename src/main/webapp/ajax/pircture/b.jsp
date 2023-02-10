<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//스크립틀릿 = javaArea
	//http://localhost:9000/ajax/picture/b.jsp?id=2
	String id  = request.getParameter("id");
	out.print(id);//2
%>