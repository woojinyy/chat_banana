package com.pojo.step3;

import java.io.IOException;

import javax.servlet.ServletException;
//javascript기반의   UI
 // 바닐라 스크립트
//리액트 = 바닐라스크립트 +ES6주요이슈 spread module arrow ,ES7  + html
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//게시판 동작
public interface Controller3 {
	//mav 와 string이 담긴 object  ModelAndView는 WEB-INF String은 webapp
		public Object zipcodeList(HttpServletRequest req, HttpServletResponse res);
		public Object jsonBoardList(HttpServletRequest req, HttpServletResponse res);
		public Object boardList(HttpServletRequest req, HttpServletResponse res);
		public Object boardDetail(HttpServletRequest req, HttpServletResponse res);
		public Object boardInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException;
		public Object boardUpdate(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException;
		public Object boardDelete(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException;
		public Object imageGet(HttpServletRequest req, HttpServletResponse res);
		public Object imageUpload(HttpServletRequest req, HttpServletResponse res);
		public Object imageDownload(HttpServletRequest req, HttpServletResponse res);
		public Object login(HttpServletRequest req, HttpServletResponse res);
		
}
