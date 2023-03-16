<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String cmem_id=null;
    	String cmem_name=null;
		//쿠키 read가 먼저
		//서버측에서 클라이언트(사용자)에게 쿠키값좀 보내주세요 요청
		Cookie cs[ ]= request.getCookies();
	 	int size = 0;
	 	//NullPointException 방어코드
	 	if(cs!=null){
	 	size = cs.length;
	 	}
	 		for (int i = 0; i < size; i++) {
	 			// 쿠키이름 가져오는 코드 생성자의 첫번째 파라미터자리에 값
    			String c_name = cs[i].getName();
	 			//서버측에서 클라이언트로부터 cs[i].getName() 넘겨받은 문자열을 비교
    			if ("cmem_id".equals(c_name)) {
    				//쿠키이름이 cmem_id인 이름이 가진 값을 가져와서 담아줘
    				cmem_id = cs[i].getValue();
    				
    			} 
    			//서버측에서 클라이언트로부터 cs[i].getName() 넘겨받은 문자열을 비교
	 			if ("cmem_name".equals(c_name)) {
	 				//쿠키이름이 cmem_name인 이름이 가진 값을 가져와서 담아줘
    				cmem_name = cs[i].getValue();
       			}
	 	}
	 	out.print("쿠키에서 꺼내온 값"+cmem_id+","+cmem_name);//값이 null이면 로그인 안함
	 	if(cmem_id==null&&cmem_name==null){
	 		%>
	 		<script>
	 		alert("로그인하세요");
	 		//return; 안먹어
	 		</script>
	 <%
	//return;if문을 그냥 나가서 들어가면 안 돼
	 	}
    %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Web Application[쿠키인증실습]</title>
  <%@include file="../common/easyUI_common.jsp" %>
<style type="text/css">
a{
tetx-decoration:none;
}
</style>
<script type="text/javascript">
		const login =()=>{
		/* 
		테스트 :com.mvc.dao.MemberDao.java
		SELECT mem_name FROM book_member
		WHERE mem_id=:id
		ANd   mem_pw=:pw
		*/
		//사용자가 입력한 아이디 가져오기
		 const user_id =$("#_easyui_textbox_input1").val(); //easyui에서 랩핑을 하기때문에 개발자센터가서 확인 
		//사용자가 입력한 비밀번호 가져오기
		const user_pw=$("#_easyui_textbox_input2").val();
		 alert(user_id+user_pw);
		 window.location.href="./login.st3?mem_id="+user_id+"&mem_pw="+user_pw;//get방식
		}
	</script>
</head>
<body>
    <h2>웹 어플리케이션 실습[회원정보처리]</h2>
    <div style="margin:20px 0;"></div>
    <div class="easyui-layout" style="width:1000px;height:550px;">
        <!-- 메뉴구성 [로그인화면과 트리메뉴]-->
        <div id="p" data-options="region:'west'" title="West" style="width:200px;padding:10px">
            <!--==============[(로그인화면)]  ==============-->
            <!--  로그인 전에는 id와 비번 입력받는 화면-->
            	<div id="d_login" align="center">
            	<div style ="margin:5px 0px;"></div>
            	<input id="tb_id" type="text" style="width:180px">
				<script>
					$('#tb_id').textbox({
		   			 iconCls:'icon-man',
		   			 iconAlign:'right',
		   			 prompt:'아이디'
					})
				</script>
            	<div style ="margin:5px 0px;"></div>
				   <input id="pb_pw" type="text" style="width:180px">
			    <script>
				$('#pb_pw').textbox({
					iconCls:'icon-lock',
					iconAlign:'right',
					prompt:'비밀번호',
    				showEye:true
				});
				</script>
				<div style ="margin:5px 0px;"></div>
            	<a id="btn_join" href="javascript:membership()">회원가입</a>
            	<script>
            	$('#btn_join').linkbutton({
            	    iconCls: 'icon-add'
            	});
            	</script>
            	<a id="btn_login" href="javascript:login()">로그인</a>
            	<script type="text/javascript">
            	$('#btn_login').linkbutton({
            	    iconCls: 'icon-man'
            	});
            	</script>
            	</div>            
            	
            <!--==============[(로그아웃화면)] ============= -->
            	<div id="d_logout" align="center">            	</div>
        <!-- 메뉴구성 [로그인화면과 트리메뉴]-->
        <!-- 메인화면 [게시판, 온라인시험,쪽지관리(받은,보낸),회원관리(회원목록 회원등록 회원삭제),우편번호검색기]-->
            					<div style ="margin:5px 0px;"></div>
        <ul id="tree_gym" class="easyui-tree">
    <li data-options="state:closed">
        <span>회원관리</span>
        <ul>
             <li><a href="#">회원목록</a></li>
             <li><a href="#">회원등록</a></li>
             <li><a href="#">회원삭제</a></li>
                </ul>
            </li>
          <li data-options="state:'closed'">
<span>쪽지관리</span>
<ul>
<li><a href="#">받은쪽지함</a></li>
<li><a href="#">보낸쪽지함</a></li>
</ul>
</li>
<li data-options="state:'closed'">
<span>기타</span>
<ul>
<li><a href="#">우편번호검색기</a></li>
<li><a href="#">게시판</a></li>
</ul>
</li>
</ul>
        </div>
        <div data-options="region:'center'" title="Center">
        </div>
        <!-- 메인화면 [게시판, 온라인시험,회원관리,우편번호검색기]-->
    </div>
</body>
</html>
<!-- 부트스트랩과 jEasyUI비교  
부트스트랩 -리액트에서 사용할겁니다-> spring 과 리액트 연계 -> 프로젝트 적용
반응형지원, CSS라이브러리사용 
css쪽이라 JS역할은 거의 없어

jEasyUI	이벤트처리(jQuery-레거시시스템)

자바스크립트 공부 하세요  표준은 아니지만(=jQuery기반이기때문에)
자바스크립트 기반의 UI솔루션 사용 하는 데는 큰 도움이 될것이다
얘 자체도 결국은 자바스크립트 문법으로 UI솔루션을 지원하기 때문에
개발자 도구 활용할 수 밖에 없다 ->디버깅을 할 수밖에 없다 
왜냐하면 표준 html을 래핑하기 때문에
vue.js, react.js를 사용하는 데 도움이 된다

JSTL 사용하는 경우는 거의 없어 요즘
 
-->
