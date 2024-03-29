<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.util.PageBar" %>    
<%
// jsp에서 자바코드(스크립틀릿)와 html코드의 작성 위치는 문제가 되지 않는다.
// 왜냐하면 어차피 jsp는 서버에서 실행되고 그 결과가 text로 출력되는 것이므로 
// html과 처리 시점이 완전 다르니까...

	List<Map<String,Object>> boardList = 
			(List<Map<String,Object>>)request.getAttribute("bList");
	int size = 0;
	if(boardList!=null){
		size = boardList.size();
	}		
	int numPerPage =3;/*한페이지에 출력될 row의 수  */
	//현재 내가 바라보는 페이지를 담을 변수
	int nowPage=0;
	if(request.getParameter("nowPage")!=null){
		nowPage=Integer.parseInt(request.getParameter("nowPage"));
	}
			out.print(size);
%>    
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="UTF-8"> 이것때문에 한글깨짐.-->
<title>MVC기반의 계층형 게시판 구현하기[webapp]</title>
<%@ include file="../common/easyUI_common.jsp" %>
<script type="text/javascript">
	let g_no=0;//그리드에서 선택이 바뀔때 마다 변경된 값이 저장됨.
	let tb_value;//사용자가 입력한 문자열 담기
	let isOk = false;
	function dlgIns_save(){
		//폼 전송 처리함.
		$("#f_boardIns").submit();
	}
	function dlgIns_close(){
		$("#dlg_boardIns").dialog('close');
	}
	function getBoardList(){
		//alert("getBoardList호출");
		//사용자가 선택한 콤보박스에 value가 담김 - b_title, or b_content or b_writer
		cb_value = user_combo;
		tb_value = $("#tb_search").val();//사용자가 입력한 조건 검색 문자열
		console.log("콤보박스 값: "+ cb_value+", 사용자가 입력한 키워드: "+tb_value);
		location.href = "./boardList.st3?cb_search="+cb_value+"&tb_search="+tb_value+"&bm_reg="+v_date;
	}	
	function boardDetail(bm_no){
		location.href="./boardDetail.st3?bm_no="+bm_no;
	}
    function fileDown(fname){
		location.href="downLoad.jsp?bs_file="+fname;
    }	
</script>
</head>
<body>
<script type="text/javascript">
	let user_combo="bm_title";//제목|내용|작성자
	//전변 - javascript에서는 선언만 하고 선택을 하지 않았거나 값이 할당되지 않으면 
	//그냥 null비교만 해서는 안된다.
	let v_date;//사용자가 선택한 날짜 정보 담기
//기본 날짜포맷을 재정의
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10? "0"+m:m)+'-'+(d<10? "0"+d:d);
	}
//날짜 포맷을 적용	
	$.fn.datebox.defaults.parser = function(s){
		var t = Date.parse(s);
		if (!isNaN(t)){
			return new Date(t);
		} else {
			return new Date();
		}
	}	
	$(document).ready(function(){//DOM구성이 완료된 시점-자바스크립트로 태그접근,설정변경,이미지
		$("#dg_board").datagrid({
			onSelect:function(index,row){
				g_no = row.B_NO;
				console.log("g_no:"+g_no);
			},
			onDblClickCell: function(index, field, value){
				if("B_TITLE" == field){
					location.href="./boardDetail.pj?b_no="+g_no;
					g_no = 0;
					$("#dg_board").datagrid('clearSelections')
				}
			}
		});
	
		//등록 날짜 정보를 선택했을 때
		$('#db_date').datebox({
			//왜? undefinded이었나?
			onSelect: function(date){
				//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				const y = date.getFullYear();
				const m = date.getMonth()+1;
				const d = date.getDate();
				v_date = y+"-"+(m<10? "0"+m:m)+"-"+(d<10? "0"+d:d);
				//console.log("사용자가 선택한 날짜 ==> "+v_date);
			}
		});
		
		//검색 조건 콤보에 변경이 일어났을 때
		$('#cb_search').combobox({
			onChange:function(){
				user_combo = $("#cb_search").combobox('getValue');//b_title or b_content or b_writer
				console.log(user_combo)
			}
		});

		$('#tb_search').textbox({
			icons: [{
				iconCls:'icon-search',
				handler: function(e){
					console.log("검색");
					//$(e.data.target).textbox('setValue', 'Something added!');
					$("#dg_board").datagrid({

					});
				}
			}]
		});

	    /*===================== CRUD버튼 시작 ====================*/	    
		//조회버튼 클릭했을 때
	    $('#crudBtnSel').bind('click', function(){
	    	getBoardList();
	    });
		$('#crudBtnIns').bind('click', function(){
	        //alert('입력 버튼');
	        $("#dlg_boardIns").dialog('open');
	    });	
		$('#crudBtnUpd').bind('click', function(){
	        alert('수정 버튼');
	    });	
		$('#crudBtnDel').bind('click', function(){
	        alert('삭제 버튼');
	    });			
	    /*===================== CRUD버튼 끝 ====================*/	    

	});///////////////// end of ready
</script>
<center>
    <table id="dg_board" class="easyui-datagrid" title="계층형 게시판 목록" style="width:800px;height:550px"
            data-options="rownumbers:true,singleSelect:true,toolbar:'#tb',footer:'#pn_board'">
        <thead>
            <tr>
                <th data-options="field:'BM_NO',width:60, align:'center', hidden:'true'">글번호</th>
                <th data-options="field:'BM_TITLE',width:350">제목</th>
                <th data-options="field:'BM_WRITER',width:80,align:'center'">작성자</th>
                <th data-options="field:'BM_DATE',width:100,align:'center'">작성일</th>
                <th data-options="field:'BS_FILE',width:170">첨부파일</th>
                <th data-options="field:'BM_HIT',width:60,align:'center'">조회수</th>
            </tr>
        </thead>
        <tbody>
<%
	if(size==0){	
%> 	
<script>
	$.messager.alert('Info','조회결과가 없습니다.');
</script>
<%
		}
	else if(size>0){
		for(int i=nowPage*numPerPage;i<(nowPage*numPerPage)+numPerPage;i++){
			if(size == i) break;
			Map<String,Object> rMap = boardList.get(i);
%>	      
        	<tr>
        		<td><%=1%></td>
        		<td>
<!-- 너 댓글이니? -->     	
<a href="javascript:boardDetail('<%=rMap.get("BM_NO") %>')" style="text-decoration:none;color:#000000">        		
        		<%=rMap.get("BM_TITLE")%>
</a>        		
        		</td>
        		<td><%=rMap.get("BM_WRITER")%></td>
        		<td><%=rMap.get("BM_REG")%></td>
        		<td><%="2023-03-06"%></td>
        		<td>
        		<%="첨부파일 없음"%>	
        		</td>
        		<td><%=rMap.get("BM_HIT")%></td>
        	</tr>
<%
		}// end of for
	}// end of else if
%>        	
        </tbody>
    </table>
<!-- 툴바 추가 중 조건검색 화면 시작 -->    
    <div id="tb" style="padding:2px 5px;">
                                    <!-- 
                                    req.getParameter("cb_search"):String
                                    SELECT * FROM board_master_t
                                    WHERE ?(컬럼) LIKE %||?||%
                                     -->
        <select class="easyui-combobox" id="cb_search" name="cb_search" panelHeight="auto" style="width:100px">
            <option selected>선택</option>
            <option value="bm_title">제목</option>
            <option value="bm_content">내용</option>
            <option value="bm_writer">작성자</option>
        </select>
        <input id="tb_search" name="tb_search" class="easyui-textbox" style="width:320px">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                작성일: <input id="db_date" class="easyui-datebox" name="bm_date" style="width:110px">
	<!-- 버튼 추가 화면 시작 --> 
	    <div id="ft" style="padding:2px 5px;">
	        <a id="crudBtnSel" href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true">조회</a>
	        <a id="crudBtnIns" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">입력</a>
	        <a id="crudBtnUpd" href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">수정</a>
	        <a id="crudBtnDel" href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true">삭제</a>
	    </div>
	<!-- 버튼 추가 화면 끝 -->     
    </div>
<!-- 툴바 추가 중 조건검색 화면 끝 -->
   
<!-- 페이지 네이션 추가 시작 -->
	<div style="display:table-cell;vertical-align:middle; width:800px; background:#efefef; height:30; border:1px solid #ccc;">
	<%
	String pagePath="boardList.st3";
	PageBar pb = new PageBar(numPerPage,size,nowPage,pagePath);
	out.print(pb.getPageBar());
	%>
	</div>
<!-- 페이지 네이션 추가   끝  -->
<%
	String gubun = request.getParameter("gubun");
	if("list".equals(gubun)){
%>	
<script type="text/javascript">
		getBoardList();
</script>	
<%		
	}
%>
<!-- 글입력 화면 추가 시작 -->
    <div id="dlg_boardIns" footer="#tb_boardIns" class="easyui-dialog" title="글쓰기" data-options="modal:true,closed:true" style="width:600px;height:400px;padding:10px">
       <form id="f_boardIns" method="post" enctype="multipart/form-data" action="./boardInsert.st3"> 
       <!-- <form id="f_boardIns" method="get" action="./boardInsert.st3"> -->
       <!--히든속성은 화면에는 보이지 않지만 개발자가 필요로 하느는값 등록부분과 수정부분이 동시에 발생할 수도 있다.  -->
	    <input type="hidden" id="b_no" name="bm_no" value="0">
	    <input type="hidden" id="b_group" name="bm_group" value="0">
	    <input type="hidden" id="b_pos" name="bm_pos" value="0">
	    <input type="hidden" id="b_step" name="bm_step" value="0">
        	<table>
        		<tr>
        			<td width="100px">제&nbsp;&nbsp;&nbsp;목</td>
        			<td width="500px"><input id="bm_title" name="bm_title" class="easyui-textbox" data-options="width:'250px'" required></td>
        		</tr>
        		<tr>
        			<td width="100px">작&nbsp;성&nbsp;자</td>
        			<td width="500px"><input id="bm_writer" name="bm_writer" class="easyui-textbox" data-options="width:'150px'" required></td>
        		</tr>
        		<tr>
        			<td width="100px">내&nbsp;&nbsp;&nbsp;용</td>
        			<td width="500px"><input id="bm_content" name="bm_content" class="easyui-textbox" data-options="multiline:'true',width:'350px', height:'90px'" required></td>
        		</tr>
        		<tr>
        			<td width="100px">비&nbsp;&nbsp;&nbsp;번</td>
        			<td width="500px"><input id="bm_pw" name="bm_pw" class="easyui-textbox" data-options="width:'100px'" required></td>
        		</tr>
        		<tr>
        			<td width="100px">첨부파일</td>
        			<td width="500px"><input id="bs_file" name="bs_file" class="easyui-filebox" data-options="width:'350px'"></td>
        		</tr>
        	</table>
        </form>
    </div>
    <!-- 다이얼로그 화면 버튼 추가 시작 -->
	<div id="tb_boardIns">
	<a href="javascript:dlgIns_save()" class="easyui-linkbutton">저장</a>
	<a href="javascript:dlgIns_close()" class="easyui-linkbutton">닫기</a>
	</div>    
    <!-- 다이얼로그 화면 버튼 추가  끝   -->
<!-- 글입력 화면 추가  끝   -->
</center>
</body>
</html>