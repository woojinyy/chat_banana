<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 /*  
 현재 내가 바라보는 페이지 tsetForm4.jsp이지만
 3번문제에 대한 답은 다음 문제 버 튼이 눌렸을 때 마음에 결정이 된것
 3번문제에 대한 답을 담는 hidden 은 남겨야한다
 그 값을 다음 문제인 testForm4.jsp에서 reuqest.getParametere()로 읽어서
 쿠키를 생성하고 그 값을 저장한다
 */
		String dap3=request.getParameter("htest3");//쿼리스트링을 가져올 땐 getParameter
			out.print("3번문제 체크한 답"+dap3+"</br>");
		Cookie c3 = new Cookie("testForm3",dap3);
	   	c3.setPath("/onlineTest");
	    c3.setMaxAge(60*60); //평가시간이 60분일 때시간 설정 값
		response.addCookie( c3);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제4</title>
<script type = "text/javascript">
	const test = (cb)=>{
		for(let i =0;i<document.f_test4.cb.length;i++){
			if(cb===i)
				document.f_test4.cb[i].checked=1
			else
				document.f_test4.cb[i].checked=0
		}
	}//end of test
	//이전페이지 구성
	const prev = ()=>{
	
		 window.location.href="testForm3.jsp";
	}
	//다음페이지구성
	const next = () =>{
			let dap =1;
			for(let i =0;i<document.f_test4.cb.length;i++){
				/*  for문안에서 답 비교*/
				if(document.f_test4.cb[i].checked==1){
					document.f_test4.htest4.value = dap;
				}
				else{
					dap = dap +1
				}
			}
			document.f_test4.submit();
	}
	</script>
</head>
<body>
<form name = "f_test4" method ="get" action="testForm5.jsp">

<input type = "hidden" name ="htest4"value="">
문제4. 다음 중 테이블에 대한 설명으로 틀린 것을 고르시오.<br>
<input type="checkbox" name="cb" onChange="test(0)">
row와 column으로 구성되어있다.<br>
<input type="checkbox" name="cb" onChange="test(1)">
테이블에는 반드시 index가 있어야 한다.<br>
<input type="checkbox" name="cb" onChange="test(2)">
컬럼에는 적당한 타입을 선택하고 담을 수 있는 크기도 설정할 수 있다.<br>
<input type="checkbox" name="cb" onChange="test(3)">
테이블에는 PK도 올 수 있고 FK도 올 수 있다.
<br>
<br>
<input type="button" value="이전문제" onClick="prev()">
<input type="button" value="다음문제" onClick="next()">
</form>
</body>
</html>