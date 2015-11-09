<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.boxy.news.bean.*" %>
<%@ page import="com.boxy.news.service.*" %>
<%@ page import="com.boxy.news.service.impl.*" %>
<%
	request.setCharacterEncoding("UTF-8");

	NewsService newsService = new NewsServiceImpl();
	
	List<News> inner = newsService.findPage(1, 3, "国内");
	List<News> outer = newsService.findPage(1, 3, "国际");
	List<News> happy = newsService.findPage(1, 3, "娱乐");
%>
<%	
	String nid = request.getParameter("nid");

	int newsId = nid == null ? 1 : Integer.parseInt(nid);
	News n = newsService.find(newsId);
%>
<%
	CommentService commentService = new CommentServiceImpl();
	List<Comment> comments = commentService.findComments(newsId);
	
	String authorIp = request.getRemoteAddr();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻中国</title>
<link href="css/read.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function check() {
		var author = document.frmComment.author;
		var content = document.frmComment.content;
		
		if (author.value == "") {
			alert("用户名不能为空！！");
			return false;
		} else if (content.value == "") {
			alert("评论内容不能为空！！");
			return false;
		}
		
		return true;
	}
</script>
</head>
<body>
	<div id="header">
		<div id="top_login">
			<label> 登录名 </label> <input type="text" id="uname" value=""
				class="login_input" /> <label> 密&#160;&#160;码 </label> <input
				type="password" id="upwd" value="" class="login_input" /> <input
				type="button" class="login_sub" value="登录" onclick="login()" /> <label
				id="error"> </label> <a href="../index.html" class="login_link">返回首页</a>
			<img src="images/friend_logo.gif" alt="Google" id="friend_logo" />
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
				<ul class="classlist">
					<table width="80%" align="center">
						<tr width="80%">
							<td colspan="2" align="center"><%=n.getTitle() %></td>
						</tr>
						<tr>
							<td colspan="2"><hr /></td>
						</tr>
						<tr>
							<td align="center"><%=n.getCreateDate() %></td>
							<td align="left"><%=n.getAuthor() %></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><%=n.getSummary() %></td>
						</tr>
						<tr>
							<td colspan="2">
								<%=n.getContent() %>
							</td>
						</tr>
						<tr>
							<td colspan="2"><hr /></td>
						</tr>
					</table>
				</ul>
				<ul class="classlist">
					<table width="80%" align="center">
						<%
							for(Comment comment : comments){
						%>
						<tr>
							<td colspan="6">
								<%=comment.getContent() %>
							</td>
						</tr>
						<tr>
							<td>
								评论:<%=comment.getAuthor() %>
							</td>
							<td>&nbsp;</td>
							<td>
								IP:<%=comment.getAuthorIp() %>
							</td>
							<td>&nbsp;</td>
							<td colspan="2">
								<%=comment.getCreateDate() %>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<hr />
							</td>
						</tr>
						<%
							}
						%>
					</table>
				</ul>
				<ul class="classlist">
					<form name="frmComment" action="control/do-add-comment.jsp" method="post" onsubmit="return check()">
						<table width="80%" align="center">
							<tr>
								<td>评 论</td>
								<td>
									<input type="hidden" name="newsId" value="<%=newsId%>" />
								</td>
							</tr>
							<tr>
								<td>用户名：<input name="author" value="这家伙很懒什么也没留下" /></td>
								<td>IP： <input name="authorIp" value="<%=authorIp%>" readonly="readonly" /></td>
							</tr>
							<tr>
								<td colspan="2">
									<textarea name="content" cols="70" rows="10"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2"><input name="submit" value="发  表" type="submit" /></td>
							</tr>
						</table>
					</form>
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
