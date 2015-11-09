<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.boxy.news.bean.*" %>
<%@ page import="com.boxy.news.service.*" %>
<%@ page import="com.boxy.news.service.impl.*" %>

<%
	request.setCharacterEncoding("UTF-8");

	String title = request.getParameter("title");	
	String summary = request.getParameter("summary");	
	String content = request.getParameter("content");	
	String imageUrl = "";	
	String author = request.getParameter("author");	
	String topicId = request.getParameter("topicId");
	
	News news = new News(0, title, summary, content, imageUrl, author, new Date(), new Date(), Integer.parseInt(topicId));
	
	NewsService newsService = new NewsServiceImpl();
	
	newsService.add(news);
	
	response.sendRedirect("../admin/news-index.jsp");
%>