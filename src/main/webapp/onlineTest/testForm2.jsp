<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String dap1=request.getParameter("htest1");//쿼리스트링을 가져올 땐 getParameter
    		out.print("1번문제 체크한 답"+dap1);
    	Cookie c1 = new Cookie("testForm1",dap1);
	  	c1.setPath("/onlineTest");
 		c1.setMaxAge(60*60); //평가시간이 60분일 때시간 설정 값

  response.addCookie(c1);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제2</title>
<script type = "text/javascript">
	const test = (cb)=>{	
		for(let i =0;i<document.f_test2.cb.length;i++){
			if(cb===i)
				document.f_test2.cb[i].checked=1
			else
				document.f_test2.cb[i].checked=0
		}
	}//end of test
	//이전페이지 구성
	const prev = ()=>{
		//location.href속성으로 페이지를 전환하는 것은 SPA(singlepageapplication)에는 맞지 않는다
		//주소변경없이 구성 돔이 바뀌는것이기 때문에 리액트에서 페이지전환은 const navigate= useNavigate();
		//navigate("./testForm1.jsp")
		 window.location.href="testForm1.jsp";
	}
	//다음페이지 구성
	const next = () =>{
			let dap =1;
			for(let i =0;i<document.f_test2.cb.length;i++){
				/*  for문안에서 답 비교*/
				if(document.f_test2.cb[i].checked==1){
					document.f_test2.htest2.value = dap;//하는 일은 value
				}
				else{
					dap = dap +1
				}
			}
			document.f_test2.submit();
	}
</script>
</head>
<body>
<form name = "f_test2" method ="get" action="testForm3.jsp">
<input type = "hidden" name ="htest2"value="">
문제2. 다음 중 DDL구문이 아닌 것을 고르시오.<br>
<input type="checkbox" name="cb" onChange="test(0)">create<br>
<input type="checkbox" name="cb" onChange="test(1)">drop<br>
<input type="checkbox" name="cb" onChange="test(2)">alter<br>
<input type="checkbox" name="cb" onChange="test(3)">delete<br>
<br>
<input type="button" value="이전문제" onClick="prev()">
<input type="button" value="다음문제" onClick="next()">
</form>
</body>
</html>