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
    		$.ajax({
            type:"GET",//브라우저가 인터셉터 한다 쿼리스트링에 다 나타난다 가로챈다/// POST는 포장해서 준다  뭐가있는지 몰라 무조건전달
            url:"newsList.jsp",//요청하는 페이지
            success:function(data){
               // console.log(data);
               $("#d_news").html(data);
            },
            });
    }
    </script>
</head>
<body>
		<script type="text/javascript">
		autoReload();
		$(document).ready(() =>{
	start = ()=>{
		setInterval(autoReload, 2000);
	}
	//start();//호출 
})//end of ready = DOM을 다 읽었으면 실행해
</script>
<div id = "d_news">뉴스 준비중</div><!-- DOM -->
<%
out.print("<font color = 'red' size = 14> 안녕</font>");//api 서블릿이 제공
%>
</body>
</html>

<!--  
어떻게 터지지 않고 
autoReload의 내용이 시간적인 런닝타임이 오래걸리느냐 오래걸리지 않느냐에 따라서 순차적으로진행되더라도
d_news를 찾으면 에러가 없는거고 못찾으면 에러가 나는거고
객관적으로 찾어보려면 인위적으로 타임라인이 걸리게 (지연이발생하면) 못읽을 수 있다 

-->
 