<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.boxy.news.bean.*" %>
<%@ page import="com.boxy.news.service.*" %>
<%@ page import="com.boxy.news.service.impl.*" %>

<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	String userName = request.getParameter("userName");
	String password = request.getParameter("password");
	
	UserService userService = new UserServiceImpl();
	
	if(userService.login(userName, password)){
		session.setAttribute("user", userName);

		userName = java.net.URLEncoder.encode(userName);
		
		Cookie cookie = new Cookie("userName", userName);
		
		cookie.setPath("/");
		
		cookie.setMaxAge(5 * 60);
		
		response.addCookie(cookie);
		
		response.sendRedirect("../admin/news-index.jsp");
	} else {
		response.sendRedirect("../index.jsp");
	}
%>