<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Book"%>
<%
	//リクエストスコープからbook情報を取得
	Book book = (Book) request.getAttribute("book");
%>

<html>
<head>
<title>書籍管理システム</title>
</head>
<body>
	<!-- header.jspインクルード -->
	<%@include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td style="text-align: center; width: 90px">[<a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
		</td>
			<td style="text-align: center; width: 90px">[<a href="<%=request.getContextPath()%>/view/insert.jsp">書籍登録</a>]
		</td>
			<td style="text-align: center; width: 90px">[<a href="<%=request.getContextPath()%>/list">書籍一覧</a>]
		</td>
			<td style="text-align: center; width: 320px; font-size: 24px; color: #800000;">書籍変更</td>
			<td style="width: 90px">&nbsp;</td>
			<td style="width: 90px">&nbsp;</td>
			<td style="width: 90px">&nbsp;</td>
		</tr>
	</table>

	<hr style="text-align: center; height: 2px; background-color: #008000; width: 950px">
	<form action="<%=request.getContextPath()%>/update" style="margin: 70px auto 200px auto; display: flex; flex-direction: column; align-items: center; justify-content: space-between; height: 180px;">
		<div style="display: flex; justify-content: space-between; width: 480px;">
			<table style="text-align: center; width: 210px; height: 50px">
				<tr>
					<td style="text-align: right; color: #800000" ; colspan="3"><<変更前情報>></td>
				</tr>
				<tr>
					<td style="background-color: #C0C0C0;">ISBN</td>
					<td><%=book.getIsbn()%></td>
				</tr>
				<tr>
					<td style="background-color: #C0C0C0;">TITLE</td>
					<td><%=book.getTitle()%></td>
				</tr>
				<tr>
					<td style="background-color: #C0C0C0;">価格</td>
					<td><%=book.getPrice()%></td>
				</tr>
			</table>

			<table>
				<tr>
					<td style="text-align: center; color: #800000";><<変更後情報>></td>
				</tr>
				<tr>
					<td><%=book.getIsbn()%></td>
				</tr>
				<tr>
					<td><input type=text size="25" name="title" value="<%=book.getTitle()%>"></input></td>
				</tr>
				<tr>
					<td><input type=text size="25" name="price" value="<%=book.getPrice()%>"></input></td>
				</tr>
			</table>
		</div>
		<input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
		<input type="submit" value="変更完了">
	</form>

	<!-- footer.jspインクルード -->
	<%@include file="/common/footer.jsp"%>
</body>
</html>