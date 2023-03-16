package com.pojo.step1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class DeptLogic {
Logger logger = Logger.getLogger(DeptLogic.class);

public List<Map<String, Object>> getDeptList() {
	logger.info("getDeptList호출");
	List<Map<String,Object>> deptList =  new ArrayList<>();
	Map<String,Object> rmap = new HashMap<>();
	rmap.put("deptno",10);
	rmap.put("dname","개발부");
	rmap.put("loc","제주");
	rmap.put("deptno",20);
	rmap.put("dname","운영부");
	rmap.put("loc","경기");
	rmap.put("deptno",30);
	rmap.put("dname","기획부");
	rmap.put("loc","서울");
	return deptList;
}

public String jsonDeptList() {
	logger.info("getDeptList호출");

	List<Map<String,Object>> deptList =  new ArrayList<>();
	Map<String,Object> rmap = new HashMap<>();
	rmap.put("deptno",10);
	rmap.put("dname","개발부");
	rmap.put("loc","제주");
	deptList.add(rmap);
	 rmap = new HashMap<>();
	rmap.put("deptno",20);
	rmap.put("dname","운영부");
	rmap.put("loc","경기");
	deptList.add(rmap);
	 rmap = new HashMap<>();
	rmap.put("deptno",30);
	rmap.put("dname","기획부");
	rmap.put("loc","서울");
	deptList.add(rmap);
	
	Gson g = new Gson();
	String temp = g.toJson(deptList);
	
	return temp;//json포맷으로 전달 리액트에서 조회시에 사용함
}

	public int deptInsert() {
		logger.info("getInsert호출");
		return 0;
	}


	public int deptDelete() {
		logger.info("getDelete호출");
		return 0;
	}

	public int deptUpdate() {
		logger.info("getUpdate호출");
		return 0;
	}

}
