package com.pojo.step3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.HashMapBinder;
//스프링부트
//@Controller

//spring-maven방식
//pom

public class Board3Controller implements Controller3 {
		Logger logger = Logger.getLogger(Board3Controller.class);
		//@Autowired
		Board3Logic boardLogic= new Board3Logic();
	@Override
	public ModelAndView boardList(HttpServletRequest req, HttpServletResponse res) {
		logger.info("boardList호출");
		List<Map<String, Object>>bList = null;
		//사용자가 조건 검색을 원하는 경우 조건 값을 전달할 객체 생성
		//Mybaits 에서는 동적쿼리를 지원하므로 하나로 2가지 경우 사용능
		Map<String, Object>pMap = new HashMap<>();
		HashMapBinder hmb =new HashMapBinder(req);
		hmb.bind(pMap);
		bList = boardLogic.boardList(pMap);
		logger.info(bList);//조회눌렀을 때 출력 안되서 확인해보는 로그
		/* req.setAttribute("bList", bList); */
		ModelAndView mav = new ModelAndView(req);
		mav.setViewName("board3/boardList");
		mav.addObject("bList", bList);
		return mav;
	}
	@Override
	public Object jsonBoardList(HttpServletRequest req, HttpServletResponse res)  {
		logger.info("jsonBoardList호출");
		List<Map<String,Object>> bList = null;
		Map<String, Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);//get방식이라 Binder/multiBinder 아니야
		bList = boardLogic.boardList(pMap);
		//오라클 연동 후에 조회결과가  bList에 담겨 있음
		//forwrad할 때 그 주소번지를 저장 해두어야 화면에서 접근 가능
		//키값이 중요
		req.setAttribute("bList", bList);
		return "forward:board3/jsonBoardList"; 
		
	}

	@Override
	public Object boardDetail(HttpServletRequest req, HttpServletResponse res) {
		logger.info("boardDetail호출");
		List<Map<String, Object>> bList =null;
		Map<String, Object>pMap = new HashMap<>();
		HashMapBinder hmb =new HashMapBinder(req);
		hmb.bind(pMap);
		bList = boardLogic.boardDetail(pMap);
		logger.info(bList);
		req.setAttribute("bList", bList);
		return "forward:board3/boardDetail";
	
	}
/*
 * INSERT INTO board_master_t(bm_no, bm_title, bm_writer, bm_reg, bm_hit
		                                              , bm_group, bm_pos, bm_step)
	    VALUES(seq_board_no.nextval, #{bm_title}, #{bm_writer}, to_char(sysdate, 'YYYY-MM-DD')
	               ,  0, #{bm_group}, #{bm_pos}, #{bm_step})
 * 화면에서 받아올 값: bm_title, bm_writer,bm_content
 * 
 */
	@Override
	public Object boardInsert(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		logger.info("boardInsert호출");
		int result = 0;
		//폼태그에 사용자가 작성한 정보 가져오는 코드
		//req.getParameter("bm_writer");
		//req.getParameter("bm_title");
		//req.getParameter("bm_content");
		//req.getParameter("bm_xxxx");
		Map<String, Object>pMap = new HashMap<>();
		logger.info("담기전"+pMap);
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.multiBind(pMap);
		logger.info("담긴후"+pMap);
		//
		result = boardLogic.boardInsert(pMap);
		String path="";
		if(result==1) {
			path = "redirect:/board3/boardList.st3";
		}else {
			path="boardInsertFail.jsp";
			res.sendRedirect(path);
		}
		return path;
	}

	@Override
	public Object boardUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		logger.info("boardUpdate호출");
		int result = 0;
		//insert here
		Map<String, Object>pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);//multipartrequest 이용
		logger.info(pMap);
		//result 값(현재0)에 변화(1로)를 주는 코 드 추가
		result = boardLogic.boardUpdate(pMap);
		String path="";
		if(result==1) {
			path = "redirect:/board3/boardList.st3";
		}else {
			path = "boardInsertFail.jsp";
			res.sendRedirect(path);
		} 
		return "path";
	}

	@Override
	public Object boardDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		logger.info("boardDelete호출");
		int result = 0;
		Map<String, Object>pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		result = boardLogic.boardDelete(pMap);
		String path="";
		if(result==1) {
			path = "redirect:/board3/boardList.st3";
		}else {//result=0경우 else타게되므로
			path = "redirect:/board3/boardInsertFail.jsp";
			res.sendRedirect(path);
		}
		return "path";
		}
	
	//Quill Editor에서 이미지를 선택하면 업로드처리하는 부분
	//물리적 위치= 톰캣서버가 바라보는 chat_banana /webapp/pds
	//바이너리 타입 코드 첨부
	//이미지 파일 첨부(application[main]/*[sub:im.png,img.gif]
	//파일크기 제한 5메가 유효성 체크 네가 만들어라
	//UI에서 체크해서 5Mb초과 에러화면 출력
	@Override
	public Object imageUpload(HttpServletRequest req, HttpServletResponse res) {
		logger.info("imageUpload 호출 성공");
		//첨부파일 처리에 필요한 변수 선언
		//첨부파일 업로드 API는 cos.jar maven repository
		//get방식 post방식
		// post encType속성 기존 요청객체request.getParameter("")로 사용자가 입력한 값을 읽을 수 없다
		//따라서 cos.jar 가 필요
		//post이면서 첨부파일이 있는 형태인 경우 이 클래스 반드시 필요
		MultipartRequest multi = null;
		String realFolder = "D:\\workspace_java\\chat_banana\\src\\main\\webapp\\pds";
		//첨부파일의 한글처리
		String encType = "utf-8";
		//첨부파일의 크기
		int maxSize = 50*1024*1024;//5MB
		try {
			//인스화 성공하면 pds폴더에 파일 추가(=업로드된다)
			//인스턴스화 하기
			//@param1 - req요청객체 
			//@param2 실제파일이 있는 물리적 위치
			//@param3 최대 크기
			//@param4 한글인코딩설정값
			//@param5 옵저버 같은이름이 있을 경우 관찰 거기에 대한 대응값 반환
			//거의 즉시 업로드 됨 파일크기가 크면 지연상태에 빠짐 dead lock상태 
			multi = new MultipartRequest(req, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		}
		//String filename = boardLogic.imageUpload(multi, realFolder);
		Map<String,Object> rMap = boardLogic.imageUpload(multi, realFolder);
		logger.info(rMap);
		//Gson g = new Gson();
		//String temp = g.toJson(rMap);
		//logger.info(temp);
		//logger.info(g);
		String temp = "";
		temp = rMap.get("bs_file").toString()+","+rMap.get("bs_size").toString();
		logger.info(temp);
		return temp;
	}
//process.env.REACT_APP_PUBLIC_URL+`board3/imageGet.st3?imageName=${res.data}`
	//QuillEditor에서 필요해서 추가
	//위지웤기능첨가<p></p> <img..../>PNG,JPG,JPEG
	//일단 이미지를 선택하면 pds에 먼저업로드 되고 그 이미지 경로를 참조해서 editor에 출력해줌
	//return  null  인 이유는 이미지 정보를 얻어오는 부분이다보니 화면적으로 처리 할 부분이 없다.
	//에디터에서 이미지를 선택하면 bm_content컬럼에 img태그와 함께 이미지 정보에 대한 url (소스)가 
	//텍스트 형태로 저장 ㅣ이미지태그정보를 읽어서 화면에 보여주는 컨셉
	//오라클 서버에 저장된 bm_content정보를 읽어서 브라우저에 출력해주는 컨셉
	@Override
	public Object imageGet(HttpServletRequest req, HttpServletResponse res) {
		//imageName 정보는 공통코드로 제공하는 QuillEditor.jsx에서 파라미터로 넘어오는 값
		//imageUpload메소드에서는 업로드 된 파일정보(파일명,파일크기)가 리턴됨
		String b_file = req.getParameter("imageName");//get방식으로 넘어오는
		logger.info("imageGet 호출 성공===>"+b_file);//XXXX.pmg 
		//톰캣서버의 물리적인 위치
		String filePath = "D:\\workspace_java\\chat_banana\\src\\main\\webapp\\pds"; // 절대경로.	
		String fname = b_file;
		logger.info("b_file: 8->euc"+b_file);	
		//File은 내용까지 복제되는 것은 아니고 파일 이름만 객체화 해주는 클래스이다.
		File file = new File(filePath,b_file.trim());
		//실제 업로드 된 파일에 대한 mimeType을 출력해주는 코드
	 	String mimeType = req.getServletContext().getMimeType(file.toString());
	 	logger.info(mimeType);
		if(mimeType == null){//mimetype이 null이면 아래 속성값으로 변경
			//Q. 왜?
			//A. 브라우저는 해석이 가능한 mimeType은 브라우저에서 열려벌임
			//해석이 불가능한 mimeType은 다운로드
			//브라우저에서 해석가능한 마임타입의 경우 화면에 그대로 출력이 되는것을 방지하기 위해서추가
			res.setContentType("application/octet-stream");
		}
		//다운로드 되는 파일명
		String downName = null;
		//위File객체에서 생성된 객체에 내용을 읽기위한 클래스 선언
		FileInputStream fis = null;
		//응답으로 나갈 정보가 웹서비스에 처리되어야 하기에 사용한 객체
		ServletOutputStream sos = null;
		try{
			if(req.getHeader("user-agent").indexOf("MSIE")==-1){
				downName = new String(b_file.getBytes("UTF-8"),"8859_1");
			}else{
				downName = new String(b_file.getBytes("EUC-KR"),"8859_1");
			}
			//응답헤더에 다운로드 될 파일명을 매핑하기
		   	res.setHeader("Content-Disposition", "attachment;filename="+downName);
		 	//위에서 생성된 파일 문자열 객체를 가지고 파일 생성에 필요한 객체의 파라미터 넘김
		   	fis = new FileInputStream(file);
			sos = res.getOutputStream();
			//파일 내용을 담을 byte배열 생성
			byte b[] = new byte[1024*10];
			int data = 0;
			while((data=(fis.read(b,0,b.length)))!=-1){
				//파일에서 읽은 내용을 가지고 실제 파일에 쓰기 처리
				//여기서 처리된 값은 브라우저를 통해서 내보내지는 정보
				sos.write(b,0,data);
			}
			//버퍼에 있는 처리한 내용을 모두 처리 요청하기
			//그후 버퍼 비우기, 버퍼는 크기가 작다
			sos.flush();		
		}catch(Exception e){
			logger.info(e.toString());
		}finally{
			try {
				if(sos != null) sos.close();
				if(fis != null) fis.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		//byte[] fileArray = boardLogic.imageDownload(imageName);
		//logger.info(fileArray.length);
		return null;
	}// end of imageGet
	//download.jsp페이지의 내용과 같다
	public Object  imageDownload(HttpServletRequest req, HttpServletResponse res) {
		logger.info("imageDownload 호출 성공");
		String b_file = req.getParameter("imageName");
		String filePath = "D:\\workspace_java\\chat_banana\\src\\main\\webapp\\pds"; // 절대경로.	
		String fname = b_file;
		logger.info("b_file: 8->euc"+b_file);		
		File file = new File(filePath,b_file.trim());
	 	String mimeType = req.getServletContext().getMimeType(file.toString());
		if(mimeType == null){
			res.setContentType("application/octet-stream");
		}
		String downName = null;
		FileInputStream fis = null;
		ServletOutputStream sos = null;
		try{
			if(req.getHeader("user-agent").indexOf("MSIE")==-1){
				downName = new String(b_file.getBytes("UTF-8"),"8859_1");
			}else{
				downName = new String(b_file.getBytes("EUC-KR"),"8859_1");
			}
		   	res.setHeader("Content-Disposition", "attachment;filename="+downName);
		 	fis = new FileInputStream(file);
			sos = res.getOutputStream();
			byte b[] = new byte[1024*10];
			int data = 0;
			while((data=(fis.read(b,0,b.length)))!=-1){
				sos.write(b,0,data);
			}
			sos.flush();		
		}catch(Exception e){
			logger.info(e.toString());
		}finally{
			try {
				if(sos != null) sos.close();
				if(fis != null) fis.close();				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;

	}// end of imageDownload
	@Override
	public Object zipcodeList(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object login(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}
	
	}

