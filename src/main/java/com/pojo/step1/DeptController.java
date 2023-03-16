package com.pojo.step1;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
//SELECT deptno,dname,loc FROM dept
public class DeptController implements Action {
	Logger logger = Logger.getLogger(DeptController.class);//컴파일->타입체크
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	ActionForward af = new ActionForward();
	//FrontMVC1클래스에서  request객체에 저장된 배열 꺼내기
	String upmu[]=(String[])req.getAttribute("upmu");
	DeptLogic deptLogic = new DeptLogic();
	String path = null;
	boolean isRedirect = false;
	req.setAttribute("upmu",upmu);
	
	//부서목록 조회
	//메소드 선언
	if("getDeptList".equals(upmu[1])) {
		    
		List<Map<String,Object>> deptList = deptLogic.getDeptList();
		req.setAttribute("deptList", deptList);
		//응답페이지이름결정
		path= "getDeptList.jsp";//af로 넘겨야하는데
		//af타입이 actionforward isredirect(false)와 forward(true) 
		isRedirect = false;//이러면 forward된다= 요청이 된다 =주소창은 그대로 페이지만 바뀐다
	}
	else if("jsonDeptList".equals(upmu[1])) {
		String jsonDoc = deptLogic.jsonDeptList();
		System.out.println(jsonDoc);
		req.setAttribute("jsonDoc",jsonDoc);
		path= "jsonDeptList.jsp";
		isRedirect = false;//이러면 forward된다= 요청이 된다 =주소창은 그대로 페이지만 바뀐다
	}
	//부서등록
	else if("deptInsert".equals(upmu[1])){
		//insert into dept(deptno,dname,loc) values(?,?,?)
		int result =deptLogic.deptInsert();
		
	}
	//부서정보 수정
	else if("deptUpdate".equals(upmu[1])){
		int result =deptLogic.deptUpdate();
	}
	//부서 삭제
	else if("deptDelete".equals(upmu[1])){
		int result =deptLogic.deptDelete();
	}
		af.setPath(path);
		af.setRedirect(isRedirect);
		return af;
	}
//여기서 method를 여러개 사용하면
	
	public ActionForward getDeptList() {//req res가 없어 
		//res가 없으면 res.sendRedirect()를 못해 응답을 안해준다는데 어떻게 괜찮음
		//의존적인거지 왜 너(response)없이는 페이지이동 못해
		//왜 주입을 못받아? override가 아니잖아 어디서 옵니까 얘는
		//FrontMVC1으로 입장하려면 뭘 지켜야해? URL뒤에 .st1 web.xml(배치서술자)에 등록했잖아
		return null;
	}
}
