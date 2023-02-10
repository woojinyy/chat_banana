package com.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionMgr {
    //드라이버클래스가 필요해 물리적으로 떨어진 오라클서버에 접속
    //ojdbc6.jar ojbdc8.jar

    //오라클제품에서는 물리적으로 떨어져있는 서버에 접속하는 방식으로
    //thin방식과 oci방식을 지원하고 있다.
    //그 중 멀티티어(multitier)환경에서는 thin 방식
    //서버의 DNS ,포트번호,SID이름(물리적인 저장소 참조)
    //드라이버 이름:@ip주소
    	//이 클래스를 읽어야 오라클 제품인것을 확인가능함.
	public static final String _DRIVER 
    = "oracle.jdbc.driver.OracleDriver";
public static final String _URL 
    = "jdbc:oracle:thin:@192.168.10.78:1521:orcl11";
public static String _USER = "kiwi";
public static String _PW = "tiger";
public Connection getConnection(){
    Connection con = null;

    try {
        Class.forName(_DRIVER);
        con = DriverManager.getConnection(_URL, _USER, _PW);
    } catch (ClassNotFoundException e) {
        System.out.println("드라이버클래스를 찾을 수 없습니다.");
    }catch(Exception e){//멀티 블럭 작성시 범위가 가장 넓은 클래스를 뒤에 
        System.out.println("오라클 서버와 커넥션 실패");
    }//======검색 정보를 가져올 수 있다.

    return con;
}
//사용한 지원을 반납하는 코드는 명시적으로 하는 것을 원칙으로
//반납하는 순서는 생성된 역순으로 진행
//사용한 자원 반납하기 -INSERT, UPDATE, DELETE물리적인 테이블에 직접적인 변화
public void freeConnection (Connection con, Statement stmt){
    if (stmt != null) {
        try {
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    if (con != null) {
        try {
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
public void freeConnection (Connection con, PreparedStatement pstmt){
    if (pstmt != null) {
        try {
            pstmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    if (con != null) {
        try {
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
//사용한 지원 반납하기 -SERECT 테이블에 있는 정보를 꺼내서 조회 -> 커서 조작이 필요
public void freeConnection (Connection con, Statement stmt, ResultSet rs){
    if (rs != null) {
        try {
            rs.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    if (stmt != null) {
        try {
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    if (con != null) {
        try {
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
public void freeConnection (Connection con, PreparedStatement pstmt, ResultSet rs){
    if (rs != null) {
        try {
            rs.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    if (pstmt != null) {
        try {
            pstmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    if (con != null) {
        try {
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}



public static void main(String[] args) {
    DBConnectionMgr dbcnn= new DBConnectionMgr();
    Connection con = dbcnn.getConnection();
System.out.println("con====>"+con);
    }
}
/*
 * JDBC API를 이용하여 DB연동하기
 * 1.각 제조사가 제공하는 드라이버 클래스를 로딩(ojdbc6.jar)
 * Class,forName(드라이버클라스(패키지까지 포함해서));
 * 
 * 2.오라클서버와 연결 통로를 확보
 * Connection 은 인터페이스이다.-오른쪽에 구현체 클래스가 와야 한다
 *메소드인데 인스턴스화를 해준다??? ㄱㄴ???ㅁㅇㅁㅇ???? new가 없는데 객체는 왜 만들어짐?????????????
 *
 * 
 * Connection con = DriverManager.getConnection(URL,"scott","tiger");
 * 3.DML문을 자바에서 오라클서버로 전달해 줄 인터페이스를 생성한다.(Statement:정적쿼리문, PreparedStatement:동적쿼리문지원)
 * 4,SELECT문의 경우 오라클 서버에서 제공하는 커서를지원하는 ResultSet인터페이스를 활용하여 
 * 테이블에 제공되는 커서를 조작하여  해당 로우에 데이터가 존재하면 Cursor....open....fetch과정을 통해서
 * 오라클 서버로부터 데이터를 꺼내서 List<DeptVO>에 담을 수 있다.ㅣ
 * 주의 : INSERT,UPDATE, DELETE에서는 커서가 필요없다
 * 리턴값은 int이다
 * 이것들을 요청할 때는 executeUpdate(INSERT문 orUPDATE or DELETE문)
 * 
 * SELECT일때는
 * ResultSet rs = executeQuery("SELECT * FROM dept");
 * 오라클에서 제공되는 커서를 조작하는 인터페이스를 리턴타입으로 제공받아서
 * 조회결과를 Collection 에 담을 수 있다.
 * List<DeptVO> deptList =new ArrayList<>();
 * DeptVO dVO= null;
 * while(rs.next()){
 * dVO= new DeptVO();
 * 
 * deptList.add(dVO);
 * }
 * 
 * 사용한 자원을 반납할 때는 사용한 역순으로 닫아주면 됨
 * SELECT인 경우 
 * ResultSet먼저 닫고 Statement 혹은 PreparedStatement 두번쨰로 닫고
 * Connection 을 맨 나중에 닫아주면 된다.
 * 닫지 않으면 자동으로 언젠가 닫아주기는 하지만 명시적으로 닫아주면
 * 닫는 시간을 앞당길 수 있어 프로젝트에서는 명시적으로
 * 닫는 코드 작성을 가이드로 정한다.
 *
 *  INSERT, UPDATE,DELETE는 두번째와 세번째만 닫으면 된다.
 * 왜냐하면 리턴타입이 커서가 필요 없다
 */