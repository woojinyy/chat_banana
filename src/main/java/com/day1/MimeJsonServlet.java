package com.day1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
//서블릿은 java인데 브라우저에 출력할 수 있다= 화면역할 화면 그리는 데 서블릿을 사용 
//=mime type이 일을 한다
@WebServlet("/json.do")//웹에서 접근 가능한 맵핑주소 설정
public class MimeJsonServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException
{
	res.setContentType("application/json;charset=UTF-8");
	//메소드를 통해서 객체 생성 
	PrintWriter out = res.getWriter();
	List<Map<String, Object>>mList = new ArrayList<>();
	Map<String,Object> rmap = new HashMap<>(); //구현체클래스 hashmap
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
	out.print(mList);//관전 포인트 Gson 안쓰면 어떻게 되냐?
	Gson g = new Gson();
	String temp = g.toJson(mList);
	out.print(temp);
}
}

