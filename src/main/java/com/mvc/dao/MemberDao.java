package com.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class MemberDao {
	//log4j모듈 활용해서 로그출력-날짜와 시간 , 클래스명,라인번호,링크
	//System.out.print() 보다 다양한 정보 확인
	Logger logger = Logger.getLogger(MemberDao.class);//apache밑에 log4j.Logger import해라
	//MybatisCommonFactory는 DAO클래스와 오라클 서버사이에 
	//MyBatisLayer에 필요한 설정 내용을 갖고있는 공통코드
	//member.xml의 물리적 위치와 오라클 서버의 정보가 담긴 MybatisConfig.xml의 
	//정보를 IO로 읽어오는 코드가 포함되어 있다.
	//MyBatis는 쿼리문을 xml문서에 따로 관리한다
	//java로 관리하는 것 보다 컴파일을 하지 않아도 되고 
	//버전관리에도 효과적이다.
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();
	public Map<String, Object> login(Map<String, Object> pMap){
		 Map<String, Object> rmap=null;
		 //MybatisConfig.xml문서를 통해 스캔한 오라클 서버 정보로 연결통로 확보 
			SqlSessionFactory sqlSessionFactory = null;//읽고
			//위에서 SqlSessionFactory생성되면 쿼리문을 요청하는 selectOne메소드가 필요한데
			//그 메소드를 제공하는 클래스가 SqlSession  여기에 commit과 rollback도 지원하니까 
			//입력수정 삭제에서도 꼭 필요하다
			SqlSession sqlSession=null;
			try {
				//공통코드에서 연결통로 확보하고 
				sqlSessionFactory = mcf.getSqlSessionFactory();
				//연결통로 확보로 생성된 객체로 SqlSession 로딩하고
				sqlSession = sqlSessionFactory.openSession();
				//Map<String, Object> pMap=new HashMap<>();//로그인 매계변수로 Map 
				//pMap.put("mem_id","tomato");
				//pMap.put("mem_pw","123");//파라미터로 이것들을받아서 넘긴다
				//세션 리퀘스트를 가지고 http세션을 만들고 서블릿에 담는다
				rmap = sqlSession.selectOne("login",pMap);//파라미터 타입 hashmap
				logger.info(rmap);//3건 모두 조회
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return rmap;
	}
	public static void main(String args[]) {
		MemberDao mDao = new MemberDao();
		Map<String, Object>pMap = new HashMap<>();
		pMap.put("mem_id","tomato");
		pMap.put("mem_pw","123");
		Map<String, Object> rMap= mDao.login(pMap);
		System.out.print(rMap);
	}
}
