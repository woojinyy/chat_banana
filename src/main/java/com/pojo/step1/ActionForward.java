package com.pojo.step1;

public class ActionForward {
//캡슐레이션을 위해 private
	//
	private String path  =null;//응답페이지 이름 or 서블릿의 이름
	//sendRedirect로 페이지를 이동할것인지
	//forward로 페이지를 이동할 것인지여부를 결정해주는 코드
	private boolean isRedirect = false;//true :redirect(insert|update|delete), false : forward(select)조회결과를 유지해야하니까
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
}
/*
 * 
 */
