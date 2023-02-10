<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.sql.*, java.util.*, com.util.DBConnectionMgr" %>
    <%@ page import ="com.google.gson.Gson" %>
    
<%
//스크립플릿
//out.print("jsonMemberList페이지");
DBConnectionMgr dbMgr = new DBConnectionMgr();
Connection con    	              = null;
PreparedStatement pstmt=null;
ResultSet rs = null;
		List<Map<String,Object>> memList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT mem_no,mem_id,mem_name\r\nFROM member");
		try {
			con = dbMgr.getConnection();//물리적으로 떨어져 있는 오라클 서버에 연결통로
			pstmt = con.prepareStatement(sql.toString());//연결통로를 통해서 select문을 가져가고 오라클 서버에게 요청하는객체를 로딩
			rs= pstmt.executeQuery();
			memList = new ArrayList<>();//인스턴스화
			Map<String,Object> rmap = null;//결괏값 받아오는 
			while(rs.next()) {
				rmap = new HashMap<>();
				rmap.put("mem_no",rs.getInt("mem_no"));
				rmap.put("mem_id",rs.getString("mem_id"));
				rmap.put("mem_name",rs.getString("mem_name"));
				memList.add(rmap);
			}
			//System.out.println(memList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//사용한 지원을 반납하기 명시적으로 반납 실행시점을 당기기위해 
			dbMgr.freeConnection(con, pstmt, rs);
		}
		Gson g= new Gson();
		String temp = null;
		temp = g.toJson(memList);
		out.print(temp);
%>