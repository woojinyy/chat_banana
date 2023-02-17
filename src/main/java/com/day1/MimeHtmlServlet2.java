package com.day1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
//서블릿은 java인데 브라우저에 출력할 수 있다= 화면역할 화면 그리는 데 서블릿을 사용 
//=mime type이 일을 한다

//서블릿을 경유하여 응답페이지를 jsp로 가져가는 실습
//최초 mimeHtmlResult.jsp를 직접 호출하는 것이 아니라 
// url/day1/html2.do 로 요청 했을 때 
//	res.sendRedirect("./mimeHtmlResult.jsp");를 만나서 
//mimeHtmlResult로 주소창이 변경되는 것을 관찰한 뒤
//서블릿에서 세션에 담은 정보를 mimeHtmlResult.jsp페이지에서 
//유지되는지 확인하는 실습이다. 

@WebServlet("/day1/html2.do")//웹에서 접근 가능한 맵핑주소 설정
public class MimeHtmlServlet2 extends HttpServlet {
	Logger logger= Logger.getLogger(MimeHtmlServlet2.class);
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException
{
		logger.info("doGet호출");
		//요청객체로 세션객체를 생성할 수 있다.
		//요청객체가 있어야만 세션사용이 가능하다
		//서블릿에서는 객체의 유지 정도가 다르다
		//화면에 이동이 많다 화면이 바뀌어도 나를 기억해주면 좋겠어
		//scope가 지원된다 서블릿에서는
		//page scope 해당 페이지 안에서만 기억해준다- 쓰레기
		//request scope 요청이 유지되는 동안에만 유지된다=URL주소가 같으면 유지된다
		//session scope URL 주소가 바뀌어도 유지 (톰캣 기본30분) 시간을 지배한다 서버쪽에 캐쉬메모리에 저장된다 
		//application scope=> 죽지않아-> 서버다운 사용하지않는다
		HttpSession session = req.getSession();//세션 담기
		String myName=new String("이순신");
		int  age=35;
		Map<String, Object>rmap = new HashMap<>(); 
		rmap.put("mem_id","tomato");
		rmap.put("	mem_pw","111");
		rmap.put("	mem_name","토마토");
		
		List<Map<String, Object>>mList = new ArrayList<>();
		if(rmap !=null) {
			rmap.clear();//map내용 지우기
		}
		 rmap = new HashMap<>(); //구현체클래스 hashmap
		rmap.put("mem_id","tomato");
		rmap.put("	mem_pw","111");
		rmap.put("	mem_name","토마토");
		mList.add(rmap);
		rmap = new HashMap<>(); 
		rmap.put("mem_id","kiwi");
		rmap.put("	mem_pw","111");
		rmap.put("	mem_name","키위");
		mList.add(rmap);
		rmap = new HashMap<>(); 
		rmap.put("mem_id","banana");
		rmap.put("	mem_pw","111");
		rmap.put("	mem_name","바나나");
		mList.add(rmap);
		
		session.setAttribute("myName", myName);//담기 (키값,벨류) 타입이 object
		session.setAttribute("age", age);//세션에 담기 (키값,벨류) 타입이 object
		session.setAttribute("rmap",rmap);//세션에 담기 (키값,벨류) 타입이 object
		session.setAttribute("rList",mList);//세션에 담기 (키값,벨류) 타입이 object
		
			res.sendRedirect("./mimeHtmlResult.jsp");//주소가 바뀐다 요청이 끊어지고 새로운 요청이다
		
}
}
