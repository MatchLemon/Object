<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.boxy.service.*" %>
<%@ page import="com.boxy.news.bean.*" %>
<%@ page import="com.boxy.news.service.*" %>
<%@ page import="com.boxy.news.service.impl.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻中国</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function checkLogin(){
	var userName = document.login.userName;
	var password = document.login.password;
	var error = document.getElementById("error");
	
	error.innerHTML = "";
	if(userName.value == ""){
		error.innerHTML = "请输入用户名";
		userName.focus();
		return;
	}
	if(password.value == ""){
		error.innerHTML = "请输入密码";
		password.focus();
		return;
	}
	
	document.login.submit();
}
</script>
<%
	//
	Cookie[] cookies = request.getCookies();
	String userName = "";
	if(cookies != null){
		for(Cookie cookie : cookies){
			if("userName".equals(cookie.getName())){
				userName = java.net.URLDecoder.decode(cookie.getValue());
			}
		}
	}
%>

<%
	NewsService newsService = new NewsServiceImpl();
	TopicService topicService = new TopicServiceImpl();
	
	List<News> inner = newsService.findPage(1, 3, "国内").getPages();
	List<News> outer = newsService.findPage(1, 3, "国际").getPages();
	List<News> happy = newsService.findPage(1, 3, "娱乐").getPages();
	
	List<Topic> topics = topicService.findAll();
	
	String pageIndexStr = request.getParameter("pageIndex");
	String topicIdStr = request.getParameter("tid");
	
	int pageIndex = pageIndexStr == null ? 1 : Integer.parseInt(pageIndexStr);
	int topicId = topicIdStr == null ? 0 : Integer.parseInt(topicIdStr);
	int pageSize = 30;
	
	PagedList<News> pages = newsService.findPage(pageIndex, pageSize, topicId);
%>
</head>
<body>
	<div id="header">
		<div id="top_login">
			<form name="login" action="control/do-login.jsp" method="post">
				<label> 登录名 </label><input type="text" name="userName" value="<%=userName%>"	class="login_input" />
				<label> 密&#160;&#160;码 </label> <input type="password" name="password" value="" class="login_input" />
				<input type="button" class="login_sub" value="登录" onclick="checkLogin()" /> 
				<label id="error"></label><img src="images/friend_logo.gif" alt="Google" id="friend_logo" />
			</form>
		</div>
		<div id="nav">
			<div id="logo">
				<img src="images/logo.jpg" alt="新闻中国" />
			</div>
			<div id="a_b01">
				<img src="images/a_b01.gif" alt="" />
			</div>
			<!--mainnav end-->
		</div>
	</div>
	<div id="container">
		<div class="sidebar">
			<h1>
				<img src="images/title_1.gif" alt="国内新闻" />
			</h1>
			<div class="side_list">
				<ul>
					<%
						for(News news : inner){
					%>
					<li><a href='news-read.jsp?nid=<%=news.getId()%>'><b><%=news.getTitle()%></b></a></li>
					<%
						}
					%>
				</ul>
			</div>
			<h1>
				<img src="images/title_2.gif" alt="国际新闻" />
			</h1>
			<div class="side_list">
				<ul>
					<%
						for(News news : outer){
					%>
					<li><a href='news-read.jsp?nid=<%=news.getId()%>'><b><%=news.getTitle()%></b></a></li>
					<%
						}
					%>
				</ul>
			</div>
			<h1>
				<img src="images/title_3.gif" alt="娱乐新闻" />
			</h1>
			<div class="side_list">
				<ul>
					<%
						for(News news : happy){
					%>
					<li><a href='news-read.jsp?nid=<%=news.getId()%>'><b><%=news.getTitle()%></b></a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
		<div class="main">
			<div class="class_type">
				<img src="images/class_type.gif" alt="新闻中心" />
			</div>
			<div class="content">
				<ul class="class_date">
					<li id='class_month'>
						<%
							for(Topic topic : topics){
						%>
							<a href="index.jsp?tid=<%=topic.getId() %>"><b><%=topic.getTopicName()%></b></a> 
						<%
							}
						%>	
					</li>
					
				</ul>
				<ul class="classlist">
				<% for(News news : pages.getPages()){ %>
					<li>
						<a href='news-read.jsp?nid=<%=news.getId()%>'><%=news.getTitle() %> </a>
						<span><%=news.getCreateDate()%> </span>
					</li>
				<%
					}
				%>
					
					<p align="right">
						<a href="index.jsp?tid=<%=topicId%>&pageIndex=<%=pages.getFirstIndex()%>">首页</a>&nbsp;
						<a href="index.jsp?tid=<%=topicId%>&pageIndex=<%=pages.getPrevIndex()%>">上一页</a>&nbsp;
						&nbsp;[<%=pages.getPageIndex()%>/<%=pages.getPageCount()%> ]&nbsp;
						<a href="index.jsp?tid=<%=topicId%>&pageIndex=<%=pages.getNextIndex()%>">下一页</a>&nbsp;
						<a href="index.jsp?tid=<%=topicId%>&pageIndex=<%=pages.getLastIndex()%>">尾页</a>
					</p>
				</ul>
			</div>
			<div class="picnews">
				<ul>
					<li><a href="#"><img src="images/Picture1.jpg" width="249"
							alt="" /> </a><a href="#">幻想中穿越时空</a></li>
					<li><a href="#"><img src="images/Picture2.jpg" width="249"
							alt="" /> </a><a href="#">国庆多变的发型</a></li>
					<li><a href="#"><img src="images/Picture3.jpg" width="249"
							alt="" /> </a><a href="#">新技术照亮都市</a></li>
					<li><a href="#"><img src="images/Picture4.jpg" width="249"
							alt="" /> </a><a href="#">群星闪耀红地毯</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="friend">
		<h1 class="friend_t">
			<img src="images/friend_ico.gif" alt="合作伙伴" />
		</h1>
		<div class="friend_list">
			<ul>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
			</ul>
		</div>
	</div>
	<div id="footer">
		<p class="">
			24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a>
			&#160;&#160;&#160;&#160; 新闻热线：010-627488888 <br />
			文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱： <a href="#">jubao@jb-aptech.com.cn</a>
		</p>
		<p class="copyright">
			Copyright &copy; 1999-2009 News China gov, All Right Reserver <br />
			新闻中国 版权所有
		</p>
	</div>
</body>
</html>
