<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String menu = request.getParameter("menu");//info or login or board or google
	out.print(menu);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>패턴1</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<style>
a {
	text-decoration: none; /*  일괄처리 */
}
</style>
</head>
<body>
<script defer type="text/javascript">
const autoReload = ()=>{
    		console.log('autoReload 호출')
    		$.ajax({
            type:"GET",//브라우저가 인터셉터 한다 쿼리스트링에 다 나타난다 가로챈다/// POST는 포장해서 준다  뭐가있는지 몰라 무조건전달
            url:"newsList.jsp",//요청하는 페이지
            success:function(data){
                console.log(data);
            },
            });
    }
    </script>
<script>
$(document).ready(() =>{
	//실행문이 오는 자리 
start = ()=>{
setInterval(autoReload, 2000);
}
// =function start(){}
start();//호출 
})
</script>
	<table border="1" align="center" width="1000px">
		<tr>
			<td width="100%" heigth="50px">
				<!------------------- top 시작-------------------> 
				<%@ include	file="top.jsp"%> 
				<!--------------------- top 끝-------------------->
			</td>
		</tr>
		<tr>
			<td>
				<!------------------- body시작 -------------------->
				<table>
					<tr>
						<!------------------- 메뉴시작 ------------------>
						<td width="200px" heigth="400px">
						<%@ include file="menu.jsp"%>
						</td>
						<!-- ------------------메뉴 끝 --------------------->
						<td width="800" height="400">
							<%
        if(menu ==null){
  %> 
  <%@ include file="main.jsp"%> 
  <%
        }else if("info".equals(menu)){
        %> 
        <%@ include file="info.jsp"%>
         <%
        }else if("login".equals(menu)){
            %>
             <%@ include file="login.jsp"%>
              <%
        }else if("board".equals(menu)){
            %> 
            <%@ include file="board.jsp"%>
             <%
        }else if("google".equals(menu)){
           %> 
           <%@ include file="google.jsp"%> 
           <%
        }
%>
				</td>
			</tr>
		</table> 
<!--------------------------- body 끝 ---------------------------->
			</td>
		</tr>
		<tr>
			<td width="1000px" height="30">
<!-------------------------- bottom시작 -------------------------> 
				<%@ include	file="bottom.jsp"%>
<!--------------------------- bottom 끝  -------------------------->
			</td>
		</tr>
	</table>
</body>
</html>
