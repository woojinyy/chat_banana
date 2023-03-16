package com.pojo.step2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class Board2Dao {
	Logger logger = Logger.getLogger(Board2Dao.class);
	//insert here-다른 종족 Mybatis연동에 필요한 공통클래스 객체주입필요
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();
	
	public List<Map<String, Object>> boardList() {
		 List<Map<String, Object>> bList=null;
		 logger.info("boardList호출");
			SqlSessionFactory sqlSessionFactory = null;
			SqlSession sqlSession=null;
			try {
				sqlSessionFactory = mcf.getSqlSessionFactory();
				sqlSession = sqlSessionFactory.openSession();
				Map<String, Object> pMap=new HashMap<>();
				bList = sqlSession.selectList("boardList",pMap);//파라미터 타입 hashmap
				logger.info(bList);//3건 모두 조회
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return bList;		
	}

}

/*
 * ActionServlet(페이지이동)- XXXController-> XXXLogic-> XXXDao-> MybatisLayer
 * 
*/