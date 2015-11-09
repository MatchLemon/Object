<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.boxy.news.bean.*" %>
<%@ page import="com.boxy.news.service.*" %>
<%@ page import="com.boxy.news.service.impl.*" %>

<%
	request.setCharacterEncoding("UTF-8");

	String content = request.getParameter("content");	
	String author = request.getParameter("author");	
	String authorIp = request.getParameter("authorIp");
	Date createDate = new Date();
	String newsIdStr = request.getParameter("newsId");
	int newsId = newsIdStr == null ? 1 : Integer.parseInt(newsIdStr);
	
	Comment comment = new Comment(0, newsId, content, author, authorIp, createDate);
	
	CommentService commentService = new CommentServiceImpl();
	
	commentService.add(comment);
	
	response.sendRedirect("../news-read.jsp?nid="+newsIdStr);
%>