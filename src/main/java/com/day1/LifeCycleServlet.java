package com.day1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
//GenericServlet(service())<- HttpServlet(doGet,doPost) 상속받는것이 상위계층 상속받는것보다 더 누릴 게 많다 (하위클래스 가까운 클래스 상속받는것이 눌리것이 더 많다)
//WebServlet이 있는 annotation 이 있는 서블릿은 컨트롤 계층을 담당한다
//서블릿은 구조상 뷰계층을 담당하지 않는다 out.print(" ")열거를 해야하는데 노가다야 
@WebServlet("/lifecycle")//spring에서 @controller컨트롤계층에대한의미+@RequestMapping[URL패턴을 정의하는의미] 합쳐진 의미를 갖는 annotation 이다
public class LifeCycleServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger("LifeCycleServlet.class");

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		//입력,처리,출력을 담당
		//사용자의 요청을 받는 메소드
		logger.info("service호출");
	}

	@Override
	public void destroy() {
		//서블릿이 메모리에서 제거될 때 서블릿 컨테이너에 의해 자동 호출
		logger.info("destroy호출");
	}

	@Override
	public void init() throws ServletException {
		//서블릿 초기화 작업 담당
		//서블릿 초기화 될 때 자동호출되는 메소드
		//개발자가 호출하는 메소드아님
		logger.info("init호출");
	}

}
