<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>1번;</h2>
<jsp:include page="b.jsp" />
<h2>include다음에</h2>
</body>
</html>
<!-- 
a.jsp페이지를 출력하다가 jsp include를 만나면
그 자리에 b.jsp페이지의 출력내용을 포함시킨다.

a.jsp에는 자바코드도 올 수 있고 HTML코드도 올 수 있다
그런데, "자바코드가 어떻게 브라우저에서 실행이 된다는걸까?
document.wtire("<b>굵은글씨</b>");

자바스크립트 만으로 화면을 그릴 수 있다.
how?
 -->