<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.User,bean.Order"%>
<%
//セッションからユーザー情報を取得
User user = (User)session.getAttribute("user");
//セッション切れか確認
if(user == null){
//セッション切れならerror.jspへフォワード
request.setAttribute("error","セッション切れの為、メニュー画面が表示できませんでした。");
request.setAttribute("cmd","logout");
request.getRequestDispatcher("/view/error.jsp").forward(request, response);
return;
}
%>
<html>
	<head>
		<title>書籍管理メニュー画面</title>
	</head>
	<body>
		<%@include file="../common/header.jsp"%>
		<%@include file="../common/userInfo.jsp" %>
		<div  style="text-align:center; font-size:24px; font-weight:bold;">MENU</div>
		<hr style="text-align:center; height:2px; background-color:black; width:950px">
		<div style="margin-bottom:350px">
			<table  style="margin:auto; border:0;">
				<tr><td style="padding-top: 15px"><a href="<%=request.getContextPath()%>/view/insert.jsp">【書籍登録】</a></td></tr>
				<tr><td style="padding-top: 15px"><a href="<%=request.getContextPath()%>/list">【書籍一覧】</a></td></tr>
				<tr><td style="padding-top: 15px"><a href="<%=request.getContextPath()%>/showCart">【カート状況確認】</a></td></tr>
				<tr><td style="padding-top: 15px"><a href="<%=request.getContextPath()%>/insertIniData">【初期データ登録(データが名場合のみ)】</a></td></tr>
				<tr><td style="padding-top: 15px"><a href="<%=request.getContextPath()%>/showOrderedItem">【購入状況確認】</a></td></tr>
				<tr><td style="padding-top: 30px"><a href="<%=request.getContextPath()%>/logout">【ログアウト】</a></td></tr>
			</table>
		</div>
		<%@include file="../common/footer.jsp" %>
	</body>
</html>