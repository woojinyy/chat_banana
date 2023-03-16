package com.pojo.step3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.oreilly.servlet.MultipartRequest;

 
public class Board3Logic {
	Logger logger = Logger.getLogger(Board3Logic.class);
	//역제어가 아니라 내가 생성하고 내가 컨트롤하고
	Board3Dao boardDao=new Board3Dao();
	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		logger.info("boardList호출"+pMap);
		List<Map<String,Object>>bList=null;
		bList =boardDao.boardList(pMap);
		return bList;
	}
	public List<Map<String, Object>> boardDetail(Map<String, Object> pMap) {
		logger.info("boardDetil호출"+pMap);
		List<Map<String,Object>>bList=null;
		bList =boardDao.boardList(pMap);
		int bm_no=Integer.parseInt(pMap.get("bm_no").toString());
		boardDao.hitCount(bm_no);
		return bList;
	}
	public int boardUpdate(Map<String, Object> pMap) {
		logger.info("boardUpdate호출"+pMap);
		int result =0;
		result = boardDao.boardMUpdate(pMap);
		return result;
	}
	public int boardDelete(Map<String, Object> pMap) {
		logger.info("boardDelete호출"+pMap);
		int result =0;
		result = boardDao.boardMDelete(pMap);
		return result;
	}
	public int boardInsert(Map<String, Object> pMap) {
		logger.info("boardInsert호출"+pMap);
		int result =  0;
		int bm_no=0;
		int bm_group=0;
		bm_no = boardDao.getBNo();
		pMap.put("bm_no",bm_no);
		//Map안에서 꺼내온다 = 화면에서 넘어온 값
		if(pMap.get("bm_group")!=null) {//상세보기에서 가져온 그룹번호
			bm_group =Integer.parseInt(pMap.get("bm_group").toString());
		}
		//댓글쓰기
		/*
		 *    UPDATE board_master_t
         SET bm_step = bm_step+1
         WHERE bm_group=:g
         AND bm_step>:5
		 */
		if(bm_group>0) {
			boardDao.bStepUpdate(pMap);//bm_group=8,bm_step>1
			pMap.put("bm_pos", Integer.parseInt(pMap.get("bm_pos").toString())+1);
			pMap.put("bm_step", Integer.parseInt(pMap.get("bm_step").toString())+1);
			
		}
		//새글쓰기
		else {
			bm_group=boardDao.getBGroup();
			logger.info("새글쓰기로직호출"+bm_group);
			pMap.put("bm_group",bm_group);
			pMap.put("bm_pos",0);
			pMap.put("bm_step",0);
		}
		result = boardDao.boardInsert(pMap);
		//첨부파일 존재유무 확인
		if(pMap.get("bs_file")!=null &&pMap.get("bs_file").toString().length()>1) {
			pMap.put("bm_no", bm_no);
			pMap.put("bs_seq",1);
			int result2 =0;
			boardDao.boardSInsert(pMap);
			logger.info(result2);//1이면 첨부파일등록 성공 
		}
		return result;
	}
	//컨트롤계층에서 multi주소번지를 넘겨준 이유
	//실제 파일명과 파일 크기를 Map에 담기 
	//반환값으로는 파일명, 파일크기
	//ViewResolver터짐 pageMove[1]에는 null상태
	
	public Map<String, Object> imageUpload(MultipartRequest multi, String realFolder) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		logger.info("image:"+multi);
		String filename =  null;
		String fullPath = null;
		//첨부파일에 대한 정보를 받아오는 코드 추가
		Enumeration<String> files = multi.getFileNames();
		logger.info("files : "+files);		
		//첨부파일이 존재하나?
		if(files!=null) {
			File file = null;
			while(files.hasMoreElements()) {
				String fname = files.nextElement();
				logger.info("fname:"+fname);//bs_file
				filename = multi.getFilesystemName(fname);
				logger.info("filename:"+filename);//첨부파일이름
				pMap.put("bs_file", filename);
				//file객체 생성하기 -> 왜냐하면 첨부파일의 크기를 알고 싶어요....
				//file = multi.getFile(filename);
				if(filename !=null && filename.length()>1) {
					file = new File(realFolder+"\\"+filename);
				}
				logger.info("file:"+file);
			}//end of while
			//첨부파일의 크기를 담을 수 있는 변수
			double size = 0;
			if(file != null) {
				size = file.length();//파일 크기가 저장 5.2MB
				logger.info("size:"+size);
				size = size/(1024);
				logger.info("size/1024:"+size);
				pMap.put("bs_size", size);
			}
			//int result = boardSDao.boardSInsert(pMap);
		}//////end of if		
		//return filename;
		return pMap;
	}
	public byte[] imageDownload(String imageName) {
		String fname = null;
		try {
			fname = URLDecoder.decode(imageName, "UTF-8");
			logger.info(fname);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//out.print("b_file: 8->euc"+b_file);		
		//out.print("<br>");		
		String filePath = "D:\\workspace_java\\chat_banana\\src\\main\\webapp\\pds"; // 절대경로.	
		//가져온 파일이름을 객체화 시켜줌. - 파일이 있는 물리적인 경로가 필요함.
		File file = new File(filePath, fname.trim());
	   	
	 	//해당 파일을 읽어오는 객체 생성해 줌. - 이 때 파일명을 객체화 한 주소번지가 필요함. 
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            fis = new FileInputStream(file);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }
        return fileArray;
	}

}
