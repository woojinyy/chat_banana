package com.pojo.step3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ActionSupport extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ActionSupport.class);
	protected void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("doService호출");
		String uri = req.getRequestURI();
		logger.info(uri);// 
		String context = req.getContextPath();//
		logger.info(context);// 
		String command = uri.substring(context.length()+1);
		int end = command.lastIndexOf(".");//16 -st1잘라내기위해 사용
		command = command.substring( 0,end);//dept/getDeptList
		String upmu[] = null;
		upmu = command.split("/");
		//http://localhost:9000/board/getBoardList.st3
		logger.info(upmu[0]+" , "+upmu[1]);
		req.setAttribute("upmu", upmu);
		Object obj = "";
		try {
			//여기를 진행하는 것은 서버측으로 요청이 가는 길 controller-> logic -> dao
			obj = HandlerMapping.getController(upmu, req, res);
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
			logger.error("Exception occurred: ", e);
		}
		//여기는 응답으로 나가는 길  ModelANdView이거나 String
		if(obj !=null) {//redirect:XXX.jsp or forward:XXX.jsp
			//pagemove 응답페이지의 위치,이름 결정
			String pageMove[] = null;
			ModelAndView mav = null;
			//String일 때
			if(obj instanceof String) {
				logger.info("obj가 String일때");
				if(((String) obj).contains(":")) {
					logger.info(":포함되어 있어요.");
					pageMove = obj.toString().split(":");
				}else if(((String) obj).contains("/")) {
					logger.info("/포함되어 있어요.");
					pageMove = obj.toString().split("/");
				}
				else {
					//spring boot @RestController spring4(@RestController가 미지원)버전은 ResponseBody사용
					logger.info(":콜론도 /도 포함되어 있지 않아요");//text/plain-> text형식-> String
							pageMove=new String[1];
							pageMove[0]=obj.toString();
							logger.info(obj.toString());				
							logger.info(pageMove[0]);
				}		
			}else if(obj instanceof ModelAndView) {
				logger.info("obj가 ModelAndView일때");
				mav = (ModelAndView)obj;
				pageMove = new String[2];
				pageMove[0] = "";//forward-> ViewResolver else if()타게됨-> webapp
				pageMove[1] = mav.getViewName();
				logger.info(pageMove[0]);
				logger.info(pageMove[1]);
				
			}
			logger.info("Object가 String일 때와 ModelAndview일 때가 끝나는 지점...");
			logger.info("pageMove ===> " + pageMove);
			logger.info("pageMove.length ===> " + pageMove.length);
			if (pageMove != null && pageMove.length == 2) {
				// pageMove[0] = redirect or forward
				// pageMove[1] = XXX.jsp
				new ViewResolver(req, res, pageMove);
			} // end of 페이지 이동처리에 대한 공통 코드 부분
			//pageMove배열이 한개인 경우는 return값이 String일경우
			//@RestController
			//ResponseBody
			else if (pageMove != null && pageMove.length == 1) {//QuillEditor img처리할 때
				res.setContentType("text/plain;charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.print(pageMove[0]);
			}
		}//end of 페이지  이동처리에 대한 공통 코드 부분		
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet호출");//브라우저의 주소창을 통해 요청하는 건 모두 get방식이다 - doGet()호출
		doService(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost호출");
		doService(req,resp);
	}	
}
