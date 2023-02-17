package com.mvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mvc.dao.TestDao;

public class TestServlet extends HttpServlet {
	/**
	 * 패킷단위로 쪼개보낸다 구분을 해야하니까 추가해주는 시리얼 넘버
	 */
	private static final long serialVersionUID = 1L;
	//사용자 정의 메소드를 만들꺼야
			//override붙이면 안돼 사용자 정의 메소드라 
		public void doService(HttpServletRequest req, HttpServletResponse res)//파라미터에 객ㅊ레 주입
		throws ServletException, IOException//에러처리 난 못하겠다 톰캣한테 던진다 니가 해줘
		{
			logger.info("doService호출");
			logger.fatal("doService 호출");
			logger.debug("doService 호출");
			logger.warn("doService 호출");
			logger.error("doService 호출");
			TestDao tDao= new TestDao();//인스턴스화
			List<Map<String,Object>> mList= tDao.getBookMember();
			//페이지 이동
			//getMemberList.jsp
			//web.xml->servlet-mapping->/test/test.do->url-pattern
			//경로명 /요청을 처리하는 이름.do (.do 확장자는 의미없다)
			//의미없는데 왜 써? 해당요청을 받아서 인터셉트 해당업무를 담당하는
			//클래스에 연결을 해야함(FrontController.java설계 각 업무별 XXXController필요함)
			//페이지 처리는 JSP에게 맡긴다 
			//서블릿은 요청을 받아서 업무담당자에게 연결하는 (맵핑,매칭)역할
			//서블릿에서 맵핑한 이름
			//이것을 어떻게 나눌 것인가?
			//요청은 업무담당자나 사용자 선택에 따라 결정
			//결정에 대응하는 클래스를FrontController연결
			//FrontController에서 실제 업무를 담당하는 XXController에 전달할 때
			//요청객체와 응답객체 또한 전달이 되어야 한다.
			//요청객체 무엇을 누릴 수 있나
			//사용자가 입력한 값을 듣기위해 필요하다
			//request.getParameter("") :String
			//session.getAttribute(""): Object
			//request;getParameter("XXX"); mem_id, mem_name, mem_no...
			//HttpSession 생성하는 데도 request.getSeesion();역할을 한다 세션객체를 생성
			//http 프로토콜이 비상태프로토콜이므로 상태값을 관리하는 별도의 메카니즘
			//쿠키와 세션 <- List,Map<- 객체배열[{}] <- 배열 <- 원시형타입변수
			//response
			// response.setContentType();마임타입을 결정한다
			//서블릿인데 json 서블릿인데 html 서블릿인데 xml이 될 수도 있다는 말
			// response.setContentType("application/json")
			// response.setContentType("text/html")html 취급
			// response.setContentType("text/xml")xml 취급
			// response.setContentType("text/css") 확장자는 java인데 브라우저에서는 css취급
			//이 역할을 하는 게 response이다
			// response.sendRedirect("페이지 이름")-> 페이지 이동 발생 -> 주소창이 바뀜
			// =기존의 요청이 끊어지고 새로운 요청이 발생했다를 의미
			//마치 계속 유지하고 있는 것 처럼 보여져야 한다
			//그러니까 쿠키나 세션에 담아둔다 왜냐하면 비상태 프로토콜이기 때문
			//쿠키 - String  로컬 pc에저장된다 세션 -서버의 캐시메모리에 저장 Object 
			//배열 : 중간에 끼워넣기 불가능 타입다르면 불가능
			//객체배열[{ }]:  다른타입 넣기 가능 
			//세션과 쿠키 시간을 지배하는 자 로그인을 3분연장하시겠습니까? ->세션 최근에 본 상품 -> 쿠키
			//doGet doPost 를 사용하려면httpServlet 상속받아야해 = 서블릿이 되어야 한다 
			//아래코드만나기전까지 http://localhost:9000/test/test.do
			//를 보여주다가 아래 문자를 만나면 그때http://localhost:9000/test/getMemberList.jsp를 요청
			res.sendRedirect("./getMemberList.jsp");
			tDao.getBookMember();
			//String cTime = tDao.testDate();
			//logger.info("today : "+cTime);
		}//배치서술자로 가보자
	Logger logger=Logger.getLogger(TestServlet.class);
	@Override//restful api 전송방식 클라이언트에서 서버로 WAS(톰캣)
	public void doGet(HttpServletRequest req, HttpServletResponse res)//파라미터에 객ㅊ레 주입
	throws ServletException, IOException//에러처리 난 못하겠다 톰캣한테 던진다 니가 해줘
	{
		doService(req,res);
	}
	@Override//restful api 전송방식 클라이언트에서 서버로 WAS(톰캣)
	public void doPost(HttpServletRequest req, HttpServletResponse res)//파라미터에 객ㅊ레 주입
	throws ServletException, IOException//에러처리 난 못하겠다 톰캣한테 던진다 니가 해줘
	{
		doService(req,res);
	}
	
}
