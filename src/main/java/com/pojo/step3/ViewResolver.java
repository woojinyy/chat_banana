package com.pojo.step3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ViewResolver {
	//req ,res sendRedirect pageMove
	Logger logger = Logger.getLogger(ViewResolver.class);
	public ViewResolver() {	}
	public ViewResolver(HttpServletRequest req, HttpServletResponse res, String[] pageMove)throws ServletException,IOException
		{
		logger.info(pageMove[0]+","+pageMove[1]);
	      String path =pageMove[1];

          logger.info(path);
          if ("redirect".equals(pageMove[0])) {
              res.sendRedirect(path);
          } 
          else if ("forward".equals(pageMove[0])) {
              RequestDispatcher view = req.getRequestDispatcher("/" + path + ".jsp");
              logger.info(view);
              view.forward(req, res);
          }
          //setViewName(XXXX);
          //WEB-INF/views/XXXXX.jsp
          else {
          	logger.info("else호출");
          	if(pageMove[0]!=null&&pageMove[0].length()>0) {//빈문자열이 아니면
          		path = pageMove[0]+"/"+pageMove[1];
          	}
          	else {
          		path = pageMove[1];// member/cindex
          	}
             // path = pageMove[0] + "/" + pageMove[1];//""/member/cindex
              RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp");
              view.forward(req, res);
          }
      
	}//end of ViewResolver(req,res,pageMove)
}//end of ViewResolver
