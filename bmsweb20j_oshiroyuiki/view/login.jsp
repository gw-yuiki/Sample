<%@page contentType= "text/html; charset=UTF-8" %>

<%
//セッションからのデータの取得
String error = (String)request.getAttribute("error");
String userid = (String)request.getAttribute("userid");
String password = (String)request.getAttribute("password");
if(error == null){
 	error = "";
 }
if(userid == null){
 	userid = "";
 }
if(password == null){
 	password = "";
 }
%>
<html>
	<head>
		<title>ログイン画面</title>
	</head>
	<body>
		<%@include file="../common/header.jsp"%>
		<div  style="padding-top: 70px;"></div>
		<hr style="text-align:center; height:2px; background-color:black; width:950px">
		<div style="padding-top: 60px">
			<p class="error-msg" style="text-align:center"><%=error %></p>
			<form action="<%=request.getContextPath() %>/login" method="post">
				<table style="margin:0 auto">
					<tr>
						<td style="background-color:#6666ff; width:150px">ユーザー</td>
						<!-- <td ><input type=text size="30" name="userid" value="<%= userid %>"></input></td> -->
						<td ><input type=text size="30" name="userid" value="kandait"></input></td>
					</tr>
					<tr>
						<td style="background-color:#6666ff; width:150px">パスワード</td>
						<!-- <td ><input type=password size="30" name="password" value="<%= password %>"></input></td> -->
						<td ><input type=password size="30" name="password" value="kandait"></input></td>
					</tr>
					<tr>
						<td colspan=2 style="text-align:center; padding-top: 50px">
							<input class="btn_login" type="submit" value="ログイン">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@include file="../common/footer.jsp" %>
	</body>
</html>