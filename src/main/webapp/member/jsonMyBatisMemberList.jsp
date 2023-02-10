<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="com.util.*,java.util.*" %>
    <%@ page import ="com.google.gson.Gson" %>
    <%@ page import= " org.apache.ibatis.session.SqlSession"%>
<%@ page import = "org.apache.ibatis.session.SqlSessionFactory"%>
    
<%
//스크립플릿
//out.print("jsonMemberList페이지");
MyBatisCommonFactory mcf = new MyBatisCommonFactory();

		List<Map<String,Object>> memList = null;
		//물리적으로 떨어져 있는 서버와 연결통로 확보 -MyBatisConfig.xml문서에서 정보(드라이버,오라클서버 URL주소,계정정보+쿼리문을 담은 XML등록)를 참조
		SqlSessionFactory sqlSessionFactory = null;//읽고
		//SQLsession으로 commit과 rollback지원받음
		SqlSession sqlSession=null;//쓰기 프리페어스테이트먼트preparedstatment 대신에 얘가 일한다
		//StringBuilder sql = new StringBuilder(); 필요 없어
		
		//sql.append("SELECT mem_no,mem_id,mem_name\r\nFROM member"); member.xml에서 가져다 쓸거기 때문에 필요 없어
		Map<String,Object> pmap = new HashMap<>();
		pmap.put("mem_no",0);
		try {
			/* con = dbMgr.getConnection();//물리적으로 떨어져 있는 오라클 서버에 연결통로
			pstmt = con.prepareStatement(sql.toString());//연결통로를 통해서 select문을 가져가고 오라클 서버에게 요청하는객체를 로딩
			rs= pstmt.executeQuery();    얘네도 다 필요 없어*/ 
			
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			//member.xml에서 id가 getMemberList 를 찾아서 실행요청함
			
			memList = sqlSession.selectList("getMemberList",pmap);
		
			//memList = new ArrayList<>();//인스턴스화 필요 없어
			//Map<String,Object> rmap = null;//결괏값 받아오는  너도 필요 없어
			/* while(rs.next()) {
				rmap = new HashMap<>();
				rmap.put("mem_no",rs.getInt("mem_no"));
				rmap.put("mem_id",rs.getString("mem_id"));
				rmap.put("mem_name",rs.getString("mem_name"));
				memList.add(rmap);
			} 얘네도 다 필요 없어 */
			//System.out.println(memList);
		} catch (Exception e) {
			e.printStackTrace();
		}/* finally {
			//사용한 지원을 반납하기 명시적으로 반납 실행시점을 당기기위해 
			dbMgr.freeConnection(con, pstmt, rs);
		}  얘네도 다 필요 없어*/
		Gson g= new Gson();
		String temp = null;
		temp = g.toJson(memList);
		out.print(temp);
%>