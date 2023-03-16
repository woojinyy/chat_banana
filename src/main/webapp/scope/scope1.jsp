<%@page import="com.book.scope.Sonata"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="myCar" scope="request" class="com.book.scope.Sonata" />
<jsp:useBean id="yourCar" scope="session" class="com.book.scope.Sonata" />
<jsp:useBean id="herCar" scope="page" class="com.book.scope.Sonata" />

<!-- 요청을 받는페이지 -->
<%
	com.book.scope.Sonata hisCar= new com.book.scope.Sonata();//나는 바보다 scope를 가질 수 없다.
	//유지의 문제 ->request session +cookie
	out.print("<h3>"+myCar.carName+"</h3>");//유지
	out.print("<h3>"+herCar.carName+"</h3>");//null
	out.print("<h3>"+yourCar.carName+"</h3>");//유지
	out.print("<h3>"+hisCar.toString()+"</h3>");
	request.setAttribute("myCar", myCar);
	pageContext.setAttribute("herCar", myCar);//scope1에서만 유지
	session.setAttribute("yourCar", myCar);
%>
<!--응답이 나가는 페이지  -->
<jsp:forward page="./move1.jsp">
	<jsp:param name="oMyCar" value="<%=myCar.toString() %>"/>
	<jsp:param name="oHerCar" value="<%=herCar.toString() %>"/>
	<jsp:param name="oYourCar" value="<%=yourCar.toString() %>"/>
	</jsp:forward>
</body>
</html>