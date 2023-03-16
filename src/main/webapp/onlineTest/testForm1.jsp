<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제1</title>
<!--  이벤트처리는 자바스크립트-->
<script type = "text/javascript">
//document 는 문제페이지.jsp를 의미한다 처리는ServerSideRendering ->html -> 클라이언트측에서 다운로드
//결국 text파일
//document의 최상위 객체는 window객체
//docnmet.f_test1 =>name 이 f_test1 에 접근 인스턴스화 없이 브라우저안에서 직접 접근 가능하다
	const test = (cb)=>{
		for(let i =0;i<document.f_test1.cb.length;i++){
			if(cb===i)
				document.f_test1.cb[i].checked=1
			else
				document.f_test1.cb[i].checked=0
		}
	}//end of test
	//next구현 submit처리
	//쿠키를 사용하지 않고 
	//답이 바뀌니가 let
	const next = () =>{
			let dap =1;
			for(let i =0;i<document.f_test1.cb.length;i++){
				/*  for문안에서 답 비교*/
				if(document.f_test1.cb[i].checked==1){
					document.f_test1.htest1.value = dap;//역할 = value
				}
				else{
					dap = dap +1
				}
			}
			document.f_test1.submit();//목적지 action으로 페이지 넘기기
	}
</script>
</head>
<body>
<form name = "f_test1" method ="get" action="testForm2.jsp">
<input type = "hidden" name ="htest1" value="">
문제1. 다음 중 DML구문이 아닌 것을 고르시오.<br>
<input type="checkbox" name="cb" onChange="test(0)">select<br>
<input type="checkbox" name="cb" onChange="test(1)">insert<br>
<input type="checkbox" name="cb" onChange="test(2)">create<br>
<input type="checkbox" name="cb" onChange="test(3)">delete<br>
<br>
<input type="button" value="다음문제" onClick="next()">
</form>
</body>
</html>
<!--  document 에서 name이 같은 것이 두개 이상이면 브라우저가 자동적으로 배열로 변경한다
		checked는 checkbox에서 선택된 상태일 때 처리하는 속성
		1이면 선택 0이면 비선택
		자바스크립트에서 0은 false이고 나머지는 모두 true이다 
		
		 답안지결정은 next 
		 실제 답결정되는 건 next버튼 눌렀을 때 
		// let dap =1;를 let dap =<%="1"%>; 클라이언트 
		 서버에서 이미 결정 누가 처리 주체냐만 다를 뿐이지 1을 갖는건 똑같다 
		 서버사이드가 먼저 
		  실행주체가 서버 <% %> 는 출력역할
		  
		  다운로드 되는 게 1이 다운로드가 된거죠
		  정적이라는거지
		  사용은 가능하지만 변하지는 않는다고 변수의 값이
		  왜냐면 <% %>는 서버사이드에서 결정이 되어있다 mime타입으로 다운받는다 jsp라쓰고 html 이라고 읽는다]
		  contentType="text/html;  여기 써있잖아
		  합법이긴하지만 조심하자 값이 안변한다니까
		  
		  
		 
-->


