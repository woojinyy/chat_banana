<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import="java.util.Map" %>
<%
//테스트 시나리오
//서블릿을 경유하여 forward메소드 호출의 결과 페이지로 b.jsp가 출력될 때
//rmap이 null이 아니므로 주의할것
//즉 b.jsp페이지를 직접 호출하면 출력값은 null이 될것이다.
Map<String, Object> rmap=(Map<String, Object>)request.getAttribute("rmap");
if(rmap!=null){//NullpointException 방어코드
	//주소창의 주소는 a이지만 출력되는 페이지는 b
	//여기서 run on server 하면 안된다 NullPointException발생
	out.print(rmap.get("mem_id"));		
		out.print("<br/>");
	}

%>
<hr>
<!--expresion 으로 출력하기  -->
<%= rmap.get("mem_id") %>
<!--
200번 성공 
300번 	재요청으로인한 err
400번 클라이언트 실수err
500번  서버err

 -->