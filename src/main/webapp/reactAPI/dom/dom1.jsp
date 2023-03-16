<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id = "root"></div><!--여기에 넣고싶어  -->

	<script type = "text/javascript">	
		const rootElement = document.querySelector("#root");
		const h1Element= document.createElement("h1");
		h1Element.textContent="DOM Make";
		rootElement.appendChild(h1Element);
	</script>
</body>
</html>
<!-- 
확장자는 jsp이지만 mime타입이 html이라 html 문서이다
 -->