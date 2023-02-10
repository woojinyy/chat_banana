<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import= "java.util.*,com.google.gson.Gson" %>
<%
//out.print("[{"mem_id":"tomato"}]");//문자를 나타내는 건 더블쿼테이션 이러면 터지네
//out.print("[{"+"mem_id:"+"tomato"+"}],{"+"mem_id:"+"apple"+"}],{"+"mem_id:"+"kiwi"+"}]");//문자를 나타내는 건 더블쿼테이션 +로 합쳐보자
List<Map<String, Object>> memberList = new ArrayList<>();
Map<String, Object> map = new HashMap<>();
map.put("mem_id","tomato");
memberList.add(map);
map = new HashMap<>();
map.put("mem_id","apple");
memberList.add(map);
map = new HashMap<>();
map.put("mem_id","banana");
memberList.add(map);
//구글에서는 Gson.jar 라이브러리 지원 
Gson g =new Gson();
//mime type이 json으로 되어있다 하더라도
String temp = g.toJson(memberList);
//out.print(temp);
out.print(memberList);//json이 아니다 그냥 자료구조찍으면 json 구조가 아니다=자바스크립트에서 읽을 수 없다
//조회 결과 확인이 불가능
%>