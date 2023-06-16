<%@page contentType="text/html; charset=UTF-8" %>
<%
String auth = "";
if(user.getAuthority().equals("1")) {
	auth = "一般ユーザー";
}else {
	auth = "管理者";
}
%>
<html>
	<body>
		<div style="font-weight:bold;  margin-left: auto; margin-right: auto;width:950px; display: flex; justify-content: flex-end;">
			名前：<%= user.getUserid() %><br>

			権限：<%= auth %>
		</div>
		<hr style="text-align:center; height:2px; background-color:black; width:950px">
	</body>
</html>
