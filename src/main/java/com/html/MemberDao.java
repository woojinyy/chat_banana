package com.html;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.gson.Gson;
import com.util.DBConnectionMgr;
import com.util.MyBatisCommonFactory;
import com.vo.MemberVO;

public class MemberDao {
DBConnectionMgr dbMgr = new DBConnectionMgr();
Connection con    	              = null;
PreparedStatement pstmt=null;
ResultSet rs = null;
MyBatisCommonFactory mcf = new MyBatisCommonFactory();
	// 회원목록 가져오기
	public List<Map<String,Object>>getMemberList(){
		System.out.println("getMemberList호출");
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
		return memList;
	}
	public String jsonMemberList(){
		System.out.println("getMemberList호출");
		List<Map<String,Object>> memList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT mem_no,mem_id,mem_name\r\nFROM member");
		try {
			con = dbMgr.getConnection();//물리적으로 떨어져 있는 오라클 서버에 연결통로
			pstmt = con.prepareStatement(sql.toString());//연결통로를 통해서 select문을 가져가고 오라클 서버에게 요청하는객체를 로딩
			rs= pstmt.executeQuery();
			memList = new ArrayList<>();//인스턴스화 내가 해야해
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
		return temp;
	}

public List<Map<String, Object>> myBatisMemberList() {
	//System.out.println("getMemberList 호출");
	//물리적으로 떨어져 있는 서버와 연결통로 확보 -MyBatisConfig.xml문서에서 정보(드라이버,오라클서버 URL주소,계정정보+쿼리문을 담은 XML등록)를 참조
	SqlSessionFactory sqlSessionFactory = null;//읽고
	//SQLsession으로 commit과 rollback지원받음
	SqlSession sqlSession=null;//쓰기 프리페어스테이트먼트preparedstatment 대신에 얘가 일한다
	List<Map<String, Object>> memList = null;//63~68 알아서 해준다 인스턴스화도 알아서 해준다
	Map<String,Object> pmap = new HashMap<>();
	pmap.put("mem_no",3);
	try {
		sqlSessionFactory = mcf.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		//member.xml에서 id가 getMemberList 를 찾아서 실행요청함
		memList = sqlSession.selectList("getMemberList",pmap);
		//insert,update,delete시에 커밋호출할 때 사용
		//sqlSession.commit(); 
		System.out.println(memList);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return memList;
}

public List<MemberVO> myBatisMemberList2() {
	SqlSessionFactory sqlSessionFactory = null;
	SqlSession sqlSession=null;
	List<MemberVO> memList = null;//63~68 알아서 해준다 인스턴스화도 알아서 해준다
	Map<String,Object> pmap = new HashMap<>();
	pmap.put("mem_no",0);
	try {
		sqlSessionFactory = mcf.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		memList = sqlSession.selectList("myBatisMemberList2", pmap);
		System.out.println(memList);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return memList;
}
public static void main(String args[]) {
	MemberDao memDao = new MemberDao();
//	memDao.getMemberList();
//	String temp = memDao.jsonMemberList();
	List<MemberVO> memList = null;
	memList = memDao.myBatisMemberList2();
	Gson g  = new Gson();
	String temp = g.toJson(memList);
	
	
	System.out.println(temp);
}
}
