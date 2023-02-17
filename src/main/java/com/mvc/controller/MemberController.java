package com.mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.MemberDao;


@WebServlet("/intro/login")
public class MemberController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//오라클 서버에서 조회한 결과를 받아서 세션에 담기위해 세션객체 생성
		HttpSession session = req.getSession();
		//인스턴스화 오라클서버에 연동하는 일만 전담하는 XXDao클래스
		MemberDao mDao = new MemberDao();
		//사용자가 입력한 아이디 요청하기
		String mem_id= req.getParameter("mem_id");
		//사용자가 입력한 비밀번호 요청하기
		String mem_pw= req.getParameter("mem_pw");
		//mybatis에 파라미터로 아이디와 비밀번호를 Map에 담아서 넘기기위한 Map 생성
		Map<String, Object>pmap = new HashMap<>();
		//키값에 대응하는 사용자가 입력한 아이디와 비밀번호 Map에 담기
		pmap.put("mem_id",mem_id);
		pmap.put("mem_pw",mem_pw);
		//오라클 서버에 연동하는 login 메소드 호출 호출시 파라미터로 pmap넘겨줌
		//pmap안에 사용자가 입력한 아이디와 비밀번호
		Map<String,Object> rmap = mDao.login(pmap);// 처리결과 받아옴 type= Map
		//위에서 받아온 rmap에 들어있는(=오라클서버에서 select문으로 조회한 결과)
		//결과를 세션객체에 저장
		session.setAttribute("smem_id", rmap.get("MEM_ID"));
		session.setAttribute("smem_name", rmap.get("MEM_NAME"));
		//로그인화면을 보여주는 index.jsp페이지로 다시 출력
		//이때 세션이 갖고있는 세션값을 꺼내서 xxx님 환영합니다 라고 말 할 수 있다.
		resp.sendRedirect("./index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
		//오라클 서버에서 조회한 결과를 받아서 세션에 담기위해 세션객체 생성
		HttpSession session = req.getSession();
		//인스턴스화 오라클서버에 연동하는 일만 전담하는 XXDao클래스
		MemberDao mDao = new MemberDao();
		//사용자가 입력한 아이디 요청하기
		String mem_id= req.getParameter("mem_id");
		//사용자가 입력한 비밀번호 요청하기
		String mem_pw= req.getParameter("mem_pw");
		//mybatis에 파라미터로 아이디와 비밀번호를 Map에 담아서 넘기기위한 Map 생성
		Map<String, Object>pmap = new HashMap<>();
		//키값에 대응하는 사용자가 입력한 아이디와 비밀번호 Map에 담기
		pmap.put("mem_id",mem_id);
		pmap.put("mem_pw",mem_pw);
		//오라클 서버에 연동하는 login 메소드 호출 호출시 파라미터로 pmap넘겨줌
		//pmap안에 사용자가 입력한 아이디와 비밀번호
		Map<String,Object> rmap = mDao.login(pmap);// 처리결과 받아옴 type= Map
		//위에서 받아온 rmap에 들어있는(=오라클서버에서 select문으로 조회한 결과)
		//결과를 세션객체에 저장
		session.setAttribute("smem_id", rmap.get("MEM_ID"));
		session.setAttribute("smem_name", rmap.get("MEM_NAME"));
		//로그인화면을 보여주는 index.jsp페이지로 다시 출력
		//이때 세션이 갖고있는 세션값을 꺼내서 xxx님 환영합니다 라고 말 할 수 있다.
		resp.sendRedirect("./index.jsp");
	
	}
}
