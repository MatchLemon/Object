<%
	Object user = session.getAttribute("user");

	if(user == null){
		response.sendRedirect("../index.jsp");
	}
%>