package com.day1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//서블릿은 java인데 브라우저에 출력할 수 있다= 화면역할 화면 그리는 데 서블릿을 사용 
//=mime type이 일을 한다
@WebServlet("/plain.do")//웹에서 접근 가능한 맵핑주소 설정
public class MimePlainServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException
{
	res.setContentType("text/plain;charset=UTF-8");
	//메소드를 통해서 객체 생성 
	PrintWriter out = res.getWriter();
	out.print("<h2>안녕</h2>");//mime type이 plain이라 태그가 다 보인다
}
}
