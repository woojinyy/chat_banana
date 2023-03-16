<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>b.jsp[WEB-INF]</title>
</head>
<body>
b.jsp페이지 입니다.
<!-- WEB-INF경로는 URL로 접근 불가능
disbpatcherservlet이 그 역할을 한다
반드시 서블릿을 경유하여 RequestDispatcher를 사용하여 요청하자
그러면 WEB-INF아래 있는 경로더라도 페이지 호출이 가능하다
다만 scope는 request scope를 갖게 되어야 한다. 반 드 시 -->

</body>
</html>