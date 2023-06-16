<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%
String error = (String)request.getAttribute("error");
String cmd = (String) request.getAttribute("cmd");
%>
<html>
	<head>
		<title>エラー画面</title>
	</head>
	<body>
		<%@include file="../common/header.jsp"%>
		<div style="text-align:center">●●エラー●●</div>
		<p class="error-msg" style="text-align:center"><%=error %></p>
		<p class="back-link" style="text-align:center">
			<%
			if (cmd.equals("logout")) {
			%>
				<a href="<%=request.getContextPath()%>/view/login.jsp">[ログイン画面へ]</a>
			<%
			} else if(cmd.equals("list")) {
			%>
				<a href="<%=request.getContextPath()%>/list">[一覧に戻る]</a>
			<%
			} else if(cmd.equals("menu")) {
			%>
				<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニューに戻る]</a>
			<%
			} else {
			%>
				<a href="<%=request.getContextPath()%>/view/menu.jsp">[メニューに戻る]</a>
			<%
			}
			%>
		</p>
		<%@include file="../common/footer.jsp" %>
	</body>
</html>