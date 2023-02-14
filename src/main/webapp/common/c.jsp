<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  
확장자는 jsp이지만 mime 타입은 html 이다 = 브라우저가 html문서로 생각한다는 뜻이다
1번줄에 contentType="text/html 이렇게 써있잖아
실행하면(=url주소에 해당페이지를 요청하면)c.jsp-> (jsp-api.jar:jsp엔진, jsp컨테이너, 톰캣에 있음) -> c_jsp.java로 변경됨 ->(servlet-api.jar : 서블릿 엔진) c_jsp.class로 변경됨
 ->out.print() out은 jsp가 제공하는 내장객체 dos창이 아닌 브라우저에 쓴다
 ->결과  자바스크립트와 같은 원리로 자바가 브라우저에서 동작이 가능하다
 
  
-->
<script>
document.write("<b>굵은글씨</b>")
/* 
test.html 문서 전체를 받는 객체가 document이다.

*/
</script>