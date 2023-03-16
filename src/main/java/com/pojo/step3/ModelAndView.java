package com.pojo.step3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
/*
 * 스프링4에서 제공되는 ModelAndView를 흉내 내기
 * 1) 스코프가 request이다 
 * 2) 화면 이름을 정해준다
 * 3)
 */ 
public class ModelAndView {
	Logger logger = Logger.getLogger(ModelAndView.class);
			HttpServletRequest req = null;
			//캡슐화 코 드는 반드시 getter/setter가 필요하다
			private String viewName =null;
			List<Map<String,Object>>reqList =new ArrayList<>();
			public ModelAndView() {
			}
			public ModelAndView(HttpServletRequest req) {
				this.req=req;
			}
			public void addObject(String name, Object obj) {
				req.setAttribute(name, obj);
				Map<String,Object>pMap=new HashMap<>();
				pMap.put(name,obj);
				reqList.add(pMap);
				logger.info("ModelAndView pMap="+pMap);
			}
			public String getViewName() {
				return viewName;
			}
			public void setViewName(String viewName) {
				this.viewName = viewName;
			}
}
