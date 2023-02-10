<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>   
    <script>
    const autoReload = ()=>{
    		console.log('autoReload 호출')
            //ajax함수는 jquert.min.js가 제공하는 api이다
            //ajax함수는 결국 XMLHttpRequest를 대신 한다
            //const ajax = new XMLHttpRequest()
    		//ajax.open("GET",ulr,flase)
    		//ajax.send()
    		$.ajax({
            type:"GET",//브라우저가 인터셉터 한다 쿼리스트링에 다 나타난다 가로챈다/// POST는 포장해서 준다  뭐가있는지 몰라 무조건전달
            url:"newsList.jsp",//요청하는 페이지
            success:function(data){ //성공 했을 때 실패했을 때는?
                console.log(data);
            },
            error:function(request, status, error){
            	//console.log('404error')
            	console.log('404error'+request.status)
            	console.log('404error'+request.responseText)
            }
            });
    }
    </script>
</head>
<body>
		<script type="text/javascript">
		//호출하지 않아도 자동 실행
		//window-> document -ready(function(){실행문}) 함수  
		$(document).ready(() =>{
			//실행문이 오는 자리 변수선언,if문 switch문 ...반복문
	start = ()=>{
		setInterval(autoReload, 2000);
	}
	// =function start(){}
	start();//호출 
})//end of ready = DOM을 다 읽었으면 실행해
</script>
<div id = "d_news">뉴스 준비중</div><!-- DOM -->
<%
out.print("<font color = 'red' size = 14> 안녕</font>");//api 서블릿이 제공
%>
</body>
</html>
<!--
html은 순차적으로 실행 (단방향, 변수선언 제어문 지원X 이벤트 처리 X)
자바스크립트 코드의 위치에 따라서document.querySelector(id or class or elementName)
항상 선언이 먼저
자바스크립트 위치
1) head안에 올 수 있다 - 전역변수 선언, 함수선언 할 때(=호출을 해야 실행이 된다)
만일 이것을 지연하고 싶을 땐 defer를 사용한다 html,DOM Tree를 그릴 때까지
2)body안에 올 수 있다 - 호출하지 않아도 실행 됨(단, 함수로 선언된 부분은 제외)

화면 = 듣기 사용자가 입력한 값을( Request) 
http 프로토콜 서블릿에 존재한다 서블릿은 화면 그리는 게 쓰레기야

자바스크립트로 화면을 그릴 수 있다.
document.write(<font color = 'red' size = 14)</font>)
write는 어디다 쓰는 건가요? 브라우저
java는 브라우저에 쓸 수 없다 
System.out.println("<b>굵은글씨</b>") 안돼
out.print()  가능 
jsp라고 쓰고 html 이라고 읽기도 하고
jsp라고 쓰고 json으로 읽어야 할 때가 있다
 -> mime 타입이 다르기 때문에
 메인타입/서브타입
 text/html
 text/css
 text/module
 text/javascript
 text/babel
  -->
