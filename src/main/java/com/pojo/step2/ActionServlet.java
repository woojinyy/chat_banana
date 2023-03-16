package com.pojo.step2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class ActionServlet extends HttpServlet {
	Logger logger = Logger.getLogger(ActionServlet.class);
	protected void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("doService호출");
		String uri = req.getRequestURI();
		logger.info(uri);
		String context= req.getContextPath();
		logger.info(context);
		String command=uri.substring(context.length()+1);
		System.out.println(command);
		int end =command.lastIndexOf(".");
		System.out.println(end);
		command=command.substring(0,end);
		System.out.println(command);
		//http://localhost:9000/asdf.st2
		String upmu[]=null;
		upmu=command.split("/");
		logger.info(upmu[0]+","+upmu[1]);
		req.setAttribute("upmu",upmu);
		Board2Controller boardController = new Board2Controller();
		boardController.execute(req, res);
		Object obj="";//객체타입으로 리턴을 받도록 설계
		obj = boardController.execute(req,res);
		//페이지이동처리 공통코드만들기
		//res.sendRedirect("/dept/getDeptList.jsp"); jsp->서블릿-> jsp페이지요청
		//res.sendRedirect("/dept/getDeptList.st1");
		if(obj!=null) {//redirect: XXX.jsp 또는 forward:XXX.jsp 배열로 처리하자
			String pageMove[]=null;
			if(obj instanceof String) {
				if(((String) obj).contains(":")) {//자바 api중 문자 포함되는지 확인해준다
					logger.info(":포함되어있다");
					pageMove= obj.toString().split(":");//포함되어있으면 잘라
				}else {
					logger.info(":포함되어있지 않다");
					pageMove=obj.toString().split("/");
				}
				logger.info(pageMove[0]+","+pageMove[1]);
			}
			if(pageMove!=null) {
				//pageMove[0]=redirect or forward
				//pageMove[1]=XXX.jsp
				String path = pageMove[1];//path꺼내기;
				if("redirect".equals(pageMove[0])) {
					//참이면 sendRedirect
					res.sendRedirect(path);
				}else if("forward".equals(pageMove[0])){
					RequestDispatcher view =req.getRequestDispatcher("/"+path+".jsp");
					view.forward(req, res);
				}else {
					path = pageMove[0]+"/"+pageMove[1];
					RequestDispatcher view =req.getRequestDispatcher("/WEB-INF/view"+path+".jsp");
					view.forward(req, res);
				}
			}
		}//end of 페이지 이동처리에 대한 공통 코드
	}//end of doService
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("doGet호출");
		doService(req,res); 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("doPost호출");
		doService(req,res);
	}
}