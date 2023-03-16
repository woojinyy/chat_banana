<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
		String dap5=request.getParameter("htest5");//쿼리스트링을 가져올 땐 getParameter
		//out.print("수험생이 작성한 답안지는"+dap1+","+dap2+","+dap3+","+dap4+","+dap5);
		//단위테스트 URL
		//http://localhost:9000/onlineTest/testForm6.jsp?htest1=1&htest2=2&htest3=3&htest4=4&htest5=5
		/*
		1번부터 5번까지 작성한 답안지의 값을 쿼리스트링으로 받아야 하므로 문제지는  testForm5.jsp에서끝났다 하더라도
		1번부터 5번까지의 답안질르 가지고있는 testForm5.jsp에서 값을 모두 읽어들인 후
		채점을 하는 testAccount.jsp페이지로 이동시켜야 한다
		반복되는 작업이고 문제수가 많아지면 그만큼 많은 값을 쿼리스트링으로 끝없이 넘겨야 하므로 비효율적
		그래서 유지의 문제는 중요하고 여기서는 쿠키를 사용하여 개선하도록한다
		*/
		Cookie c5= new Cookie("testForm5",dap5);
	   	c5.setPath("/onlineTest");
	    c5.setMaxAge(60*60); //평가시간이 60분일 때시간 설정 값
		response.addCookie( c5);
		response.sendRedirect("testAccount.jsp");
		
    %>

<!--
실습개요
온라인 시험이지만 한 번 푼 문제라도 다시 돌려볼 수있어야 한다
그래서 form 태그로 문제를 감싼뒤
form 태그에 action속성을 이용 다음문제로 이동은 가능하지만
이전문제로 이동하기위해서는 window 객체 location 객체의 href속성으로 처리하여야 한다
이럴경우 URL이 변경되므로 화면전환시 새로운 요청을 서버로 전송하게 된다
이때 전송방식은 get으로하였다
한 화면에 한 문제만 볼 수 있으므로 선택한 답안지 정보는 2번문제를 풀 때 가져가야한다
가져갈 떄 화면에 나타낼 필요가 없어서 input태그의 hidden 속성을 사용
문제는 푼 문제가 늘어날 수록 hidden 의 개수도 계속 늘어난다
1번에는 한개 2번에는 2개 3번에는 3개 4번에는 4개 n번에는 n개 뒤진다
이 문제를 개선하는 방법은 쿠키를 이용하여 한 문제를 풀 때 마다 쿠키에 값을 저장하는 것

Cookie c1 = new Cookie("testForm1",2);
//아래코드를 생략하면 디폴트가 현재 바라보는 물리적인 폴더명이므로 
//현재 바라보는 경로에 onlineTest가 들어있는 경우에는 생략할 수 있다.
//직관적으로 알 수 있도록 써주는 것이 현명하다
//만일 setPath("/")라고 할 경우 루트 도메인에서 읽고 수정 삭제가 가능하므로 주의
c1.setPath("/onlineTest")
c1.setMaxAge(60*60); //평가시간이 60분일 때시간 설정 값
//위에 작성된 답안 번호를 저장한 값을 클라이언트측에 반드시 내려보내야 한다
response.addCookie(c1)
-->