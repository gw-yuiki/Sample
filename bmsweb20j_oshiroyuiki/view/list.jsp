<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book,bean.Order"%>
<html>
	<head>
		<title>書籍一覧画面</title>
	</head>
	<body>
		<%@include file="../common/header.jsp"%>
		<table style="margin:auto; width:950px">
			<tr>
				<td style="text-align:center; width:30px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td style="text-align:center; width:25px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</td>
				<td style="text-align:center; width:45px">[<a style="text-decoration: none;" href="<%=request.getContextPath()%>/showCart">カート状況確認</a>]</td>
				<td style="text-align:left; width:127px; padding-left: 60px; font-size:24px;"><strong>書籍一覧</strong></td>
			</tr>
		</table>

		<hr style="text-align:center; height:2px; background-color:black; width:950px">
		<div style="margin-bottom:250px">

			<div style="width:864px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:40px">
				<form action="<%=request.getContextPath()%>/search" method="get">
					ISBN<input type=text size="25" name="isbn"></input>
					TITLE<input type=text size="25" name="title"></input>
					価格<input type=text size="25" name="price"></input>
					<input type="submit" name="search" value="検索" style="padding-left: 10px"></input>
				</form>
				<form action="<%=request.getContextPath()%>/list">
					<input type="submit" size="100" name="searchall" value="全件表示"></input>
				</form>
			</div>

			<div style="width:864px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:40px">
				<div style="margin:auto; width: 100%">
					<div style="display: flex; justify-content: space-between;">
						<div style="background-color:#6666ff; width:190px">ISBN</div>
						<div style="background-color:#6666ff; width:190px">TITLE</div>
						<div style="background-color:#6666ff; width:190px">価格</div>
						<div style="background-color:#6666ff; width:274px" colspan="2">変更/削除/カートに入れる</div>
					</div>
					<%
					ArrayList<Book> list =(ArrayList<Book>)request.getAttribute("book_list");
					if(list != null){
						for(int i=0;i<list.size();i++){
							Book books = (Book)list.get(i);
					%>
					<div style="display: flex; justify-content: space-between;">
						<div style="text-align:center; width:190px"><a href="<%=request.getContextPath() %>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></div>
						<div style="text-align:center; width:190px"><%=books.getTitle()%></div>
						<div style="text-align:center; width:190px"><%=books.getPrice()%></div>
						<div style="display: flex;">
							<div style="text-align:center; width:68px">
								<a href="<%=request.getContextPath() %>/detail?isbn=<%=books.getIsbn()%>&cmd=update">更新</a>
							</div>
							<div style="text-align:left; width:68px">
								<a href="<%=request.getContextPath()%>/delete?isbn=<%=books.getIsbn()%>">削除</a>
							</div>
							<div style="text-align:left; width:137px">
								<a href="<%=request.getContextPath()%>/insertIntoCart?isbn=<%=books.getIsbn()%>">カートに入れる</a>
							</div>
						</div>
					</div>
					<%
						}
					}else{
					%>
					<div style="display: flex;">
						<div style="text-align:center; width:200px">&nbsp;</div>
						<div style="text-align:center; width:200px">&nbsp;</div>
						<div style="text-align:center; width:200px">&nbsp;</div>
						<div style="text-align:center; width:250px" colspan="2">&nbsp;</div>
					</div>
					<%
					}
					%>
				</div>
			</div>

		</div>
		<%@include file="../common/footer.jsp" %>
	</body>
</html>