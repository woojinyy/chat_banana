package com.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class TestDao {
	Logger logger = Logger.getLogger(TestDao.class);//apache밑에 log4j.Logger import해라
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();
	public List<Map<String, Object>> getBookMember(){
		 List<Map<String, Object>> mList=null;
			SqlSessionFactory sqlSessionFactory = null;//읽고
			SqlSession sqlSession=null;
			try {
				sqlSessionFactory = mcf.getSqlSessionFactory();
				sqlSession = sqlSessionFactory.openSession();
				Map<String, Object> pMap=new HashMap<>();
				pMap.put("mem_id","tomato");
				pMap.put("mem_pw","123");
				mList = sqlSession.selectList("getBookMember",pMap);//파라미터 타입 hashmap
				logger.info(mList);//3건 모두 조회
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return mList;
	}
	
	public String testDate() {
		String cTime=null;
		SqlSessionFactory sqlSessionFactory = null;//읽고
		//SQLsession으로 commit과 rollback지원받음
		SqlSession sqlSession=null;//쓰기 프리페어스테이트먼트preparedstatment 대신에 얘가 일한다
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			Map<String, Object> pMap=new HashMap<>();
			pMap.put("mem_id","tomato");//쿼리문 요청할 때 셀렉트문where절 두번쨰 파라미터로 넘겨주면 된다
			pMap.put("mem_pw","123");
			//tesx.xml에서 id가 testDate 를 찾아서 실행요청함
			cTime = sqlSession.selectOne("testDate");//파라미터로 넘길게 없다

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTime;//변수 맞추기
	}
}
