<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키생성하기</title>
</head>
<body>
<%
	//아래코드는 서버가 바라보는 물리적인 위치에 들어있음
	//서버측에서 실행 -> 결과가 html이남는다 -> 클라이언트쪽으로 다운로드일어나게한다 SSR(ServerSideRendering)
	//동적처리 가능 = 실시간 변화
	 Cookie c1 = new Cookie("notebook","삼성갤럭시북3");
		c1.setMaxAge(60);//60초동안 기억해줘
	 Cookie c2 = new Cookie("hp","삼성갤럭시S23");
		c1.setMaxAge(60*2);//60*2초동안 기억해줘
		//아래를 생략하면 현재 페이지가 바라보는 물리적인 위치를 갖는다
		//여기서는 /cookie이다
		c1.setPath("/");
	Cookie c3 = new Cookie("buzz","버즈프로2");
		c1.setMaxAge(60);//60초동안 기억해줘
	response.addCookie(c1);
	response.addCookie(c2);
	response.addCookie(c3);
%>
</body>
</html>