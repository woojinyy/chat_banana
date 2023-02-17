<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Map" %>
    <%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MimeHtmlServlet2응답페이지</title>
</head>
<body>
<h2>MimeHtmlServlet2응답페이지</h2>
<%
	//String myName;//여기서 선언했는데 아랫줄은 왜 오류야?
			//여기가 바로 스크립틀릿 왜냐하면 a.jsp->a_jsp.java->service메소드 안에 들어간다
			//코드가 자바로 바뀔 때  service메소드안에 들어간다 ->메소드안에 들어가면 지역변수
			//지역변수는 전역변수를 초기화 할 수 없어
	String myName=null;
			Integer age=null;
			
			myName=(String)session.getAttribute("myName");//꺼내기
			age=(Integer)session.getAttribute("age");//getAttribute는리턴타입이 object인데 받아오는 내용은 int라 바꿔서 꺼내기
			Map<String, Object> rmap=(Map<String, Object>)session.getAttribute("rmap");//인스턴스화 하면 망하는거야
			List<Map<String, Object>>mList=(List<Map<String, Object>>)session.getAttribute("mList");//("키") value가 Object
			out.print(myName);//선언먼저 안해서 오류=초기화 안해서오류
			out.print("<hr>");
			out.print(age);//선언먼저 안해서 오류=초기화 안해서오류
			out.print("<hr>");
			//out.print(rmap); 이렇게는 안된다
			if(rmap!=null){//NullpointException 방어코드
				
			Object keys[] = rmap.keySet().toArray();
			for(int i=0;i<keys.length;i++){
				out.print(rmap.get(keys[i]));
				
				out.print("<br/>");
			}
								
			if(mList !=null){
				for(int i =0;i<mList.size();i++){//리스트 가로  맵은 세로
					Map<String, Object> rMap=mList.get(i);
				out.print(rMap.get("mem_id")+","+rMap.get("mem_pw")+","+rMap.get("mem_name"));
				}
			}
				//out.print(mList);이거 안돼
			}
%>
</body>
</html>