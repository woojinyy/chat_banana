<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 삭제하기</title>
</head>
<body>
<%
	Cookie cs[] = request.getCookies();
	int size = 0;
	size = cs.length;
	for (int i = 0; i < size; i++) {
		String c_name = cs[i].getName();
		if ("testForm1".equals(c_name)) {
			Cookie cookie = new Cookie(c_name, "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		if ("testForm2".equals(c_name)) {
			Cookie cookie = new Cookie(c_name, "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		if ("testForm3".equals(c_name)) {
			Cookie cookie = new Cookie(c_name, "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		if ("testForm4".equals(c_name)) {
			Cookie cookie = new Cookie(c_name, "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		if ("testForm5".equals(c_name)) {
			Cookie cookie = new Cookie(c_name, "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

	}
%>

</body>
</html>