package com.day1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
//form 전송시 클라이언트측의 요청을 서블릿이 받고
//method="get이면 doGet호출
//post이면 doPost호출됨
//자바가 서블릿이 되기 위한 조건은 반드시 HttpServlet을 상속받는것이다
//상속을 받으면 doGet과 doPost 오버라이딩 할 수 있는데
//이 함수의 파라미터를 통해서 request요청 객체와 response응답객체를 주입받는다
//톰캣 서버에서 주입 해준다
//웹서비스를 위해서는 URL등록이 필수이다
//프로젝트에 필요한 URL등록은  annotation과 web.xml문서를 통해서 가능하다
//annotation은 자동이고 편하기는 하지만 수동처리와 비교해서 추가작업이 불가능한 단점이 있다
//xml문서을 통해서 객체등록하고 주입받는 방법을 사용할 예정
//이유는 spring 사용시 메이븐 레포를 이용한 프로젝트 생성인 경우에 
//xml문서로 환경설정을 하고 있기 때문이다.
//class사이의 객체주입 관계도 xml문서로 처리가 가능하다
public class HelloServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HelloServlet.class);
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException
	{
		System.out.println("doGet호출");
		String mem_id=req.getParameter("mem_id");
		logger.info("사용자가 입력한 아이디는 "+mem_id+"입니다.");
		logger.info("doGet호출성공");
		res.setContentType("text/html;charset=UTF-8");
		//인스턴스화에서 메소드를 사용하는 경우는 뭐가 다른걸까?
		//A a = new A();
		//text메인타입/html서브타입-브라우저가 번역  태그는 없고 내용만 출력된다
		PrintWriter out =res.getWriter();
		String msg ="안녕하세요?";
		out.print("<font size =28px color=red>"+msg+"</font>");//리소스
				}
	//단위테스트가 불가하다
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out =res.getWriter();
		String msg ="안녕";
		out.print("<font size =28px color=blue>"+msg+"</font>");
	logger.info("doPost호출성공");
}
	
}

