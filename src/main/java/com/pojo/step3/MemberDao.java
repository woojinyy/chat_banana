package com.pojo.step3;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class MemberDao {
	Logger logger = Logger.getLogger(MemberDao.class);
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();

	public Map<String, Object> login(Map<String, Object> pMap) {

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
				rmap = sqlSession.selectOne("login",pMap);//
				logger.info("서버에서 받아온"+rmap);//3건 모두 조회
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return rmap;
	
	}
}
