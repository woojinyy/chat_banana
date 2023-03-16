package com.pojo.step3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.util.HashMapBinder;

public class MemberController implements Controller3 {
	Logger logger = Logger.getLogger(MemberController.class);
	MemberLogic memberLogic = new MemberLogic();
	@Override
	public String login(HttpServletRequest req, HttpServletResponse res) {
		logger.info("login호출");
		//Map?? VO??
		Map<String, Object>rMap = new HashMap<>();
		Map<String, Object>pMap = new HashMap<>();
		HashMapBinder hmb =new HashMapBinder(req);
		hmb.bind(pMap);
		rMap = memberLogic.login(pMap);//서버에서 로직으로 로직에서 Dao
		logger.info(rMap);//조회눌렀을 때 출력 안되서 확인해보는 로그
		
		Cookie cmem_id =  new Cookie("cmem_id",rMap.get("MEM_ID").toString());
		cmem_id.setPath("/");
		cmem_id.setMaxAge(60*60);
		res.addCookie(cmem_id);//사용자에게 응답할 쿠키
		Cookie cmem_name =  new Cookie("cmem_name",rMap.get("MEM_NAME").toString());
		cmem_name.setPath("/");
		cmem_name.setMaxAge(60*60);
		res.addCookie(cmem_name);
		
	
		return"redirect:./cindex.jsp";//사용자가 볼 페이지
	}

	@Override
	public Object zipcodeList(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object jsonBoardList(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardList(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardDetail(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object imageGet(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object imageUpload(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object imageDownload(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}


}
