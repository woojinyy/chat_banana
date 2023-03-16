package com.pojo.step2;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class Board2Controller implements Controller {
//Logic,Dao,Mybatis로 연결해야해
	Logger logger = Logger.getLogger(Board2Controller.class);
	Board2Logic boardLogic = new Board2Logic();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("execute호출");
		//ActionSerlvet클래스에서  request객체에 저장된 배열 꺼내기
		String upmu[]=(String[])req.getAttribute("upmu");
		String page = null;
		if("boardList".equals(upmu[1])) {//select경우
			logger.info("게시글목록보기");
			List<Map<String, Object>>bList=null;
			bList = boardLogic.boardList();
			//가져온 정보를 담을 떄는 키와 벨류
			req.setAttribute("bList", bList);//디비연동을위해 로직에서 다오까지 연결
			page ="forward:board2/boardList";// /board2/boardList.jsp가 되겠지 else if문에서 정의햇던 두번째 컨셉
		}
		return page;
	}

}
