package com.pojo.step1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//클래스 설계에 인터페이스가 필요하다
//HttpServlet에서 강제하는 void를 다른 타입으로 바꾸어 보자
//파라미터 자리의 req,res는  개발자가 인스턴스화 하는것이 아니고 톰캣이 주입해주는 객체이다
public interface Action {
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;

}
