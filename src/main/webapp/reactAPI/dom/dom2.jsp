<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!--리액트 구경해봐  -->
<body>
<div id = "root"></div>
<script crossorigin src="https://unpkg.com/react@18/umd/react.development.js"    ></script>
    <script crossorigin src="https://unpkg.com/react-dom@18/umd/react-dom.development.js"    ></script>
    <script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
	<script type = "text/babel">	
		const rootElement = document.querySelector("#root");
	//h1Element.textContent="DOM Make";과 같은 효과
//React는 react.development.js가져오는 객체
		const h1Element= React.createElemenet("h1",{children:"DOM Make"});
		//Client side rendering 시행시 앞: 주입할 대상, 뒤: root 위치
		//rootElement.appendChild(h1Element);
//react-dom.development.js에서 가져오	는 객체
		ReactDOM.createRoot(rootElement).render(h1Element);
	</script>
</body>
</html>
<!-- 
확장자는 jsp이지만 mime타입이 html이라 html 문서이다
바닐라 스크립트는 라이브러리 없이 순수한 자바스크립트 바로  그잡채
샌드박스 - playground이다
코드샌드박스 : React 등 다양한 환경에 대한 기본설정이 다 되어있다.



 -->