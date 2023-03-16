package com.pojo.step3;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

//@Service
//@Repowitory
public class CommonDao {
		Logger logger = Logger.getLogger(CommonDao.class);
		MyBatisCommonFactory mcf = new MyBatisCommonFactory();

		public List<Map<String, Object>> zipcodeList(Map<String, Object> pMap) {
			logger.info("zipcodeList호출");
			 List<Map<String, Object>> zList=null;
				SqlSessionFactory sqlSessionFactory = null;
				SqlSession sqlSession=null;
				try {
					sqlSessionFactory = mcf.getSqlSessionFactory();
					sqlSession = sqlSessionFactory.openSession();
					
					zList = sqlSession.selectList("zipcodeList",pMap);//파라미터 타입 hashmap
					logger.info(zList);//3건 모두 조회
				} catch (Exception e) {
					e.printStackTrace();
				}
			 return zList;		
		}

}
