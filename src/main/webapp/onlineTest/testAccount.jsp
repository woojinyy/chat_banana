<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //쿠키정보는 사용자 컴퓨터에 저장
    //서버는 사용자 컴퓨터에 요청을 해야한다
    //서버는 사용자를 어떻게 구분?
    		//JsessionID발급 쿠키에 저장 쿠키값을 기준으로 알아본다
    String users []=new String[5];
            Cookie cs[ ]= request.getCookies();
      			int size =0;
        	 	size = cs.length;        	    
       				for (int i = 0; i < size; i++) {
    			String c_name = cs[i].getName();
    			if ("testForm1".equals(c_name)) {
    				users[0] = cs[0].getValue();
    				
    			} else if ("testForm2".equals(c_name)) {
    				users[1] = cs[1].getValue();
    	
    			} else if ("testForm3".equals(c_name)) {
    				users[2] = cs[2].getValue();
    			
    			} else if ("testForm4".equals(c_name)) {
    				users[3] = cs[3].getValue();
    			
    			} else if ("testForm5".equals(c_name)) {
    				users[4] = cs[4].getValue();
    			}		
       			}
       				
             	//문제당 배점
             	    int point = 20;
             	    //합격 기준 점수는
             	    int pass = 60;
             	    //정답개수
             	    int cnt =0;
             	    //총점
             	    int total =point*cnt;
             	    //합격여부
             	    boolean isPass=false;
             	    //정답표
             	         String daps[]= {"3","4","1","2","4"};
             	    for(int i=0;i<size;i++){
             	    	if(users[i]==daps[i]){
             	    		cnt++;
             	    	}
             	    }
			
    		//end of for
           	 	 //채점하는 로직구현
           	 	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
const cookieDelete=()=>{
	location.href="cookieDelete.jsp";
}
</script>
</head>
<body>
<h3>정답 개수 : <%=cnt %>개 입니다.</h3>
<h3>점수 : <%=total%>점 입니다.</h3>
<!-- 
리액트 : onClick / className 
함수로 화면 그린다
함수안에서 UI담당하는 예약어 
return (
	)
	리렌더링 useState
					 props <- 주소번지 넘긴다 이벤트 핸들러도 넘긴다 statehook도 넘긴다
					 부모component가 바뀔 때
-->
<button onClick="cookieDelete()">쿠키삭제</button>
<div>
<%
	if(total>=pass){
		out.print("합격입니다.");
	}
	else{
		out.print("불합격입니다.");
	}

%>
</div>
</body>
</html>