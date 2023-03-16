package com.pojo.step1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class FrontMVC1 extends HttpServlet {

	Logger logger = Logger.getLogger(FrontMVC1.class);
	/*
	doService메소드는 톰캣으로부터 주입받은것이 아니다
	따라서 doGet메소드와 doPost메소드 안에서 doService메소드 호출할 때
	파라미터로 넘겨받는다
	*/
	
	protected void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("doService호출");
		String uri = req.getRequestURI();
		logger.info(uri);// /dept/getDeptList.st1
		String context= req.getContextPath();// "/" -> server.xml
		logger.info(context);// /dept/getDeptList.st1
		//http://localhost:9000/업무명폴더명/getDeptList.st1
		// ex.) http://localhost:9000/board/getBoardList.st1
		//http://localhost:9000/member/getmemberList.st1

		String command=uri.substring(context.length()+1);
		System.out.println(command);
		int end =command.lastIndexOf(".");//확장자이름 띄어내기
		System.out.println(end);
		command=command.substring(0,end);
		System.out.println(command);
		
		String upmu[]=null;//업무명|폴더명 ,upmu[1] =요청기능이름
		
		upmu= command.split("/");//dept 0번방, getDeptList 1번방
		for(String imsi:upmu) {
			System.out.println(imsi);			
		} 
		//아직 결정되지 않았다 뭐가 > 업무명이  업무명이 0번방에 들어온다
		//업무명이 controller class에 접두어이다.XXXController
		
		ActionForward af = null;
		DeptController deptController = null;//게으른인스턴스화 선언먼
		EmpController empCotroller = null;//게으른인스턴스화
		//request객체는 저장소이다 setAttribute, getAttribute
		//upmu배열의 주소번지 원본을 저장
		req.setAttribute("upmu",upmu);
		if("dept".equals(upmu[0])) {
			//인스턴스화 이유 execute method 호출 안하면 NullPointException ->500번 클라이언트x  백엔드쪽 봐야지
			deptController = new DeptController();
			//원본하나 얕은복사
			//파라미터로 doGet메소드가 주입받은 req와 res주소번지 원본을 넘겨준
			//넘기지 않는다면 DeptController에서는 req와 res누릴 수 없다.
			//서블릿이 아니기 때문에 (=req와 res를 톰캣서버로부터 주입받을수 없기 때문에)
			//메소드를 하나만 가질 수 있다.
			//다른 메소드를 정의하는 것은 가능하지만 req와 res는 주입받을 수 없다.
			//입력,수정, 삭제,조회 4가지메소드가 필요
			//DeptLogic메소드 4가지를 호출해야 하는데 upmu[1]에 정보가 필요함
			af=deptController.execute(req, res);
			//deptcontroller는 서블릿이 아니어서 req res를 WAS(톰캣)로부터 주입받을 수 없다	
		}else if("emp".equals(upmu[0])) {
			//req.setAttribute("upmu",upmu);//여기에 쓸까 말까?? 필요없어
			empCotroller= new EmpController();
			af=empCotroller.execute(req,res);
		}
		//페이지이동처리 공통코드만들기
		//res.sendRedirect("/dept/getDeptList.jsp"); jsp->서블릿-> jsp페이지요청
		//res.sendRedirect("/dept/getDeptList.st1");
		if(af!=null) {
			if(af.isRedirect()) {
				//참이면 sendRedirect
				res.sendRedirect(af.getPath());
			}else {
				RequestDispatcher view =req.getRequestDispatcher(af.getPath());
				view.forward(req, res);
			}
		}//end of 페이지 이동처리에 대한 공통 코드
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet호출");//브라우저의 주소창을 통해 요청하는 건 모두 GET방식이다
		//doGet() 메소드가 호출 된다
		doService(req,resp);// 이 메소드를 호출해야 doService메소드 실행가능
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost호출");
		doService(req,resp);
	}

}
