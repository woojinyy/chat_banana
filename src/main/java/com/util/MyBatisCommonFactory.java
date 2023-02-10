package com.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MyBatisCommonFactory {
	Logger logger = Logger.getLogger(MyBatisCommonFactory.class);
	//MyBatis는 자바와 오라클 서버 사이에 위치하면서 DB연계를 지원하는 매핑서비스이다
	//물리적으로 떨어져 있는 서버와 통신을 위해 드라이버 클래스 로딩
	//연결통로 확보 코드가 반복적으로 작성
	//이것을 전담하는 SqlSessionFactroy지원 (By MyBatis)
	//SqlSessionFactroyBean -spring적응시
	public SqlSessionFactory sqlSessionFactory = null;
	//SqlSessionFactory객체를 생성해 주는 메소드 입니다.
	public void init() {
		try {
			//xml문서에 드라이버 클래스명,url주소,계정정보 담음
			//또 커리문을 xml문서에 따로 등록해서 관리
			//쿼리문을 담고있는 xml문서의 물리적인 위치를
			String resource = "com\\util\\MyBatisConfig.xml";
			System.out.println("resource");
			Reader reader = Resources.getResourceAsReader(resource);
			logger.info("before sqlSessionFactory : "+sqlSessionFactory);
			//싱글톤패턴에서 객체 생성하기
			if(sqlSessionFactory == null) {//널일 때만 인스턴스화 해라
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader,"development");
			}
			logger.info("after sqlSessionFactory : "+sqlSessionFactory);
			System.out.println("after sqlSessionFactory : "+sqlSessionFactory);//주소번지 출력
		} catch (Exception e) {
			logger.info("[[ Exception ]] "+e.toString());
			System.out.println("[[ Exception ]] "+e.toString());
		}
	}// end of init
	public SqlSessionFactory getSqlSessionFactory() {
		System.out.println("11");
		init();
		return sqlSessionFactory;
	}
	public static void main(String[] args) {
		System.out.println("test");
		MyBatisCommonFactory mcf = new MyBatisCommonFactory();
	}

}
