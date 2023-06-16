<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<title>書籍登録画面</title>
	</head>
	<body>
		<div style="text-align:center">
			<%@include file="../common/header.jsp"%>
			<table style="margin:auto; width:850px">
				<tr>
					<td style="text-align:center; width:80px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
					<td style="text-align:center; width:80px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/list">書籍一覧</a>]</td>
					<td style="text-align:center; font-size:24px"><strong>書籍登録</strong></td>
					<td style="width:80px">&nbsp;</td>
					<td style="width:80px">&nbsp;</td>
				</tr>
			</table>
			<hr style="text-align:center; height:2px; background-color:black; width:950px">
			<br><br>
			<form action="<%=request.getContextPath() %>/insert" method="get">
				<table style="margin:0 auto">
					<tr style=" heigth:20px">
						<td style="background-color:#6666ff; width:100px;">ISBN</td>
						<td ><input type=text size="30" name="isbn"></input></td>
					</tr>
					<tr>
						<td style="background-color:#6666ff; width:100px">TITLE</td>
						<td ><input type=text size="30" name="title"></input></td>
					</tr>
					<tr>
						<td style="background-color:#6666ff; width:100px">価格</td>
						<td ><input type=text size="30" name="price"></input></td>
					</tr>
					<tr>
						<td colspan=2 style="text-align:center">
							<input type="submit" value="登録">
						</td>
					</tr>
				</table>
			</form>
			<br>
	 		</div>
			<%@include file="../common/footer.jsp" %>
 	</body>
 </html>