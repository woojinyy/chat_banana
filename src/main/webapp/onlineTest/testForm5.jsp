<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		String dap4=request.getParameter("htest4");//쿼리스트링을 가져올 땐 getParameter
			out.print("4번문제 체크한 답"+dap4+"</br>");
		Cookie c4 = new Cookie("testForm4",dap4);
		c4.setPath("/onlineTest");
		c4.setMaxAge(60*60); //평가시간이 60분일 때시간 설정 값
			response.addCookie( c4);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제5</title>
<script type = "text/javascript">
	const test = (cb)=>{
		for(let i =0;i<document.f_test5.cb.length;i++){
			if(cb===i)
				document.f_test5.cb[i].checked=1
			else
				document.f_test5.cb[i].checked=0
		}
	}//end of test
	//이전페이지 구성
	const prev = ()=>{
	
		 window.location.href="testForm4.jsp";
	}
	//다음페이지구성
	const next = () =>{
			let dap =1;
			for(let i =0;i<document.f_test5.cb.length;i++){
				/*  for문안에서 답 비교*/
				if(document.f_test5.cb[i].checked==1){
					document.f_test5.htest5.value = dap;
				}
				else{
					dap = dap +1
				}
			}
			document.f_test5.submit();
	}
	</script>
</head>
<body>
<form name = "f_test5" method ="get" action="testForm6.jsp">
<!-- 최초에 쿼리스트링으로 넘기고 있어서 처음 입력한 답안을 계속 기억이 어려워
페이지 이동할 때마다 계속 달고 다녀야 한다
반면 쿠키는 현재 페이지에서 이전문제에대한 답안 한개만 request.getParameter()로 가져와 
쿠키에 저장한다
현재 문제에 대한 답안은 hidden 한줄로  다음페이지에 전달하면 된다.
 -->
<input type = "hidden" name ="htest5"value="">
문제5. 다음 중 프로시저에 대한 설명으로 틀린 것을 고르시오.<br>
<input type="checkbox" name="cb" onChange="test(0)">
프로시저를 생성할 때 파라미터를 선언할 수 있다.<br>
<input type="checkbox" name="cb" onChange="test(1)">
파라미터에 여러 변수를 선언할 수 있다.<br>
<input type="checkbox" name="cb" onChange="test(2)">
프로시저안에서 SELECT,INSERT,UPDATE, DELETE 모두 사용 할 수 있다.<br>
<input type="checkbox" name="cb" onChange="test(3)">
프로시저 안에서 commit할 수 없다.
<br>
<br>
<input type="button" value="이전문제" onClick="prev()">
<input type="button" value="다음문제" onClick="next()">
</form>
</body>
</html>

<!--
정답을 체크하는 페이지 이전에 마지막 페이지의 정답을 받아 줄 또다른 페이지가 필요

-->