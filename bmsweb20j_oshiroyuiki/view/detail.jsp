<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%
Book book = (Book)request.getAttribute("book");
%>
<html>
	<head>
		<title>書籍詳細情報画面</title>
	</head>
	<body>
		<%@include file="../common/header.jsp"%>

		<table style="margin:auto; width:900px">
			<tr>
				<td style="text-align:center; width:60px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td style="text-align:center; width:60px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</td>
				<td style="text-align:center; width:60px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/list">書籍一覧</a>]</td>
				<td style="text-align:left; width:200px; font-size:24px; padding-left:65;"><strong>書籍詳細情報</strong></td>
				<td style="width:80px">&nbsp;</td>
				<td style="width:80px">&nbsp;</td>
			</tr>
		</table>

		<hr style="text-align:center; height:2px; background-color:black; width:950px">
		<div style="margin-bottom:250px; text-align:center;">

			<div style="width:150px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:40px">
				<form action="<%=request.getContextPath() %>/detail">
					<input type="hidden" name="isbn" value="<%= book.getIsbn() %>">
					<input type="hidden" name="cmd" value="update">
					<input type="submit" value="変更">
				</form>
				<form action="<%=request.getContextPath() %>/delete">
					<input type="hidden" name="isbn" value="<%= book.getIsbn() %>">
					<input type="hidden" name="cmd" value="delete">
					<input type="submit" value="削除">
				</form>
			</div>
			<div style="width:215px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:40px">
				<div style="background-color:#6666ff; width:100px">isbn</div>
				<div style="background-color:#00ffff; text-align:center; width: 110px"><%=book.getIsbn()%></div>
			</div>
			<div style="width:215px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:5px">
				<div style="background-color:#6666ff; width:100px">title</div>
				<div style="background-color:#00ffff; text-align:center; width:110px"><%=book.getTitle()%></div>
			</div>
			<div style="width:215px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:5px">
				<div style="background-color:#6666ff; width:100px">価格</div>
				<div style="background-color:#00ffff; text-align:center; width:110px"><%=book.getPrice()%></div>
			</div>
			<div style="width:215px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:5px">
				<div style="text-align:center; width:200px">&nbsp;</div>
				<div style="text-align:center; width:200px">&nbsp;</div>
				<div style="text-align:center; width:200px">&nbsp;</div>
				<div style="text-align:center; width:250px" colspan="2">&nbsp;</div>
			</div>

		</div>
		<%@include file="../common/footer.jsp" %>
	</body>
</html>