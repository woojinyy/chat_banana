<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//스크립틀릿 = javaArea
	//http://localhost:9000/ajax/picture/b.jsp?id=2
	String id  = request.getParameter("id");
	//out.print(id);//2
	String pics[] = {"회의.jpg", "회의-1.jpg", "회의-2.jpg", "회의-3.jpg"};
	String newIMG=null;
	 for (int i = 0; i < pics.length; i++) {
	      //out.print(id + "," + i);
	      if (Integer.parseInt(id) == i) {
	        	newIMG = pics[i];
	      }//end of if
	    }//end of for
%>
<!-- html땅 -->
<img
  id="imgDetail"
  src="../../image/sample/<%out.print(newIMG); %>"
  width="400px"
  height="400px"
  alt="그림"
/>