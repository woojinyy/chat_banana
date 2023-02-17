<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="f_test" method="post" action="/day1/hello.kh"><!--이렇게해야 post호출 가능  -->
<!--Q. 전송버튼에 대한 이벤트처리가 없는데 요청이 서버에 전달되는 이유는??
	   A. 버튼은 타입이 submit이기 때문에 전송이 된다.
 -->
<button>전송</button>
<input type="button" id ="btnSend" value="인풋전송">
<script type = "text/javascript">
const btnSend = document.querySelector("#btnSend");
btnSend.addEventListener('click',()=>{
	alert("전송버튼클릭")
	document.querySelector("#f_test").submit()
})
</script>
</form>
</body>
</html>