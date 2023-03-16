package com.pojo.step3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
 
public class HandlerMapping {
	static Logger logger = Logger.getLogger(HandlerMapping.class);

	/**************************************************************************************
	 * @param upmu[](upmu[0]) - 업무명[폴더명].upmu[1]-메소드명, 기능명, 페이지이름, 분기 *
	 * @param request         -> 1-1, 1-2와는 다르게 인터페이스를 implements하지 않는다*
	 * @param response        * 질문? 어디서 받아오지? *
	 * @return Object 오브젝트 타입으로 리턴해야야함* *
	 * 
	 **************************************************************************************/
	public static Object getController(String[] upmu, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		logger.info(upmu[0] + ", " + upmu[1]);
		Controller3 controller = null;
		String path = null;
		Object obj = null;
		ModelAndView mav = null;
		if("common".equals(upmu[0])) {
			controller= new CommonController();
			if ("zipcodeList".equals(upmu[1])) { // 이쪽은 HTML 화면으로 출력됨
				obj = controller.zipcodeList(req, res);
				// 리턴타입이 ModelAndView인 경우
				if (obj instanceof ModelAndView) {
					return (ModelAndView) obj;
				}
				// 리턴타입이 String인 경우
				else if (obj instanceof String) {
					return (String) obj;
				}
			}//end of zipcodeList
		}
		else if ("board3".equals(upmu[0])) {
			controller = new Board3Controller();
			// 게시글 전체 목록
			if ("boardList".equals(upmu[1])) { // 이쪽은 HTML 화면으로 출력됨
				obj = controller.boardList(req, res);
				// 리턴타입이 ModelAndView인 경우
				if (obj instanceof ModelAndView) {
					return (ModelAndView) obj;
				}
				// 리턴타입이 String인 경우
				else if (obj instanceof String) {
					return (String) obj;
				}
			}
			else if ("jsonBoardList".equals(upmu[1])) { // 이쪽은 json 포맷으로 감.-application/json ::: 그래서 경우의수를 쪼개줌
				obj = controller.jsonBoardList(req, res);
				// 리턴타입이 ModelAndView인 경우
				if (obj instanceof ModelAndView) {
					return (ModelAndView) obj;
				}
				// 리턴타입이 String인 경우
				else if (obj instanceof String) {
					return (String) obj;
				}
			} 
			/*
			 * ModelAndView는 WEB-INF String은 webapp
			 */
			
			else if ("boardDetail".equals(upmu[1])) { // 이쪽은 json 포맷으로 감.-application/json ::: 그래서 경우의수를 쪼개줌
				obj = controller.boardDetail(req, res);
				// 리턴타입이 ModelAndView인 경우
				if (obj instanceof ModelAndView) {
					return (ModelAndView) obj;
				}
				// 리턴타입이 String인 경우
				else if (obj instanceof String) {
					return (String) obj;
				}
			}
			else if ("boardInsert".equals(upmu[1])) { // 글입력 - 새글쓰기와 댓글쓰기
				obj = controller.boardInsert(req, res);
				logger.info("boardInsert호출 ==> boolean : " + obj instanceof String);
				// 리턴타입이 String인 경우
				if (obj instanceof String) {
					return (String) obj;
				}
			}
				else if ("imageUpload".equals(upmu[1])) { //react quill editor image add
					obj = controller.imageUpload(req, res);
					logger.info("boardInsert호출 ==> boolean : " + obj instanceof String);
					// 리턴타입이 String인 경우
					if (obj instanceof String) {
						return (String) obj;
					}
				}
				else if ("imageGet".equals(upmu[1])) { //react quill editor image get
						obj = controller.imageGet(req, res);
						logger.info("boardInsert호출 ==> boolean : " + obj instanceof String);
						// 리턴타입이 String인 경우
						if (obj instanceof String) {
							return (String) obj;
						}
			} 
				else if ("imageDownload".equals(upmu[1])) { //react quill editor image add
					obj = controller.imageDownload(req, res);
					logger.info("boardInsert호출 ==> boolean : " + obj instanceof String);
					// 리턴타입이 String인 경우
					if (obj instanceof String) {
						return (String) obj;
					}
				}
			else if ("boardUpdate".equals(upmu[1])) {// 글수정 - 첨부파일 수정 유무 고려하기
				obj = controller.boardUpdate(req, res);
				// 리턴타입이 String인 경우
				if (obj instanceof String) {
					return (String) obj;
				}
			} else if ("boardDelete".equals(upmu[1])) { // 글입력 - 첨부파일 삭제 유무 고려하기
				obj = controller.boardDelete(req, res);
				// 리턴타입이 String인 경우
				if (obj instanceof String) {
					return (String) obj;
				}
			}
		} // end of 게시판구현
			// 인증관리 - 김유신
		else if ("auth".equals(upmu[0])) {

		}
		// 회원관리 - 이순신
		else if ("member".equals(upmu[0])) {
			controller = new MemberController();
			if ("login".equals(upmu[1])) { //
			obj = controller.login(req, res);//멤버 업무
			
			if (obj instanceof ModelAndView) {
				return (ModelAndView) obj;
			}
			// 리턴타입이 String인 경우
			else if(obj instanceof String)
				return (String)obj;
			}
		}
		// 주문관리 - 강감찬
		else if ("order".equals(upmu[0])) {

		}
		return obj;
	}
}