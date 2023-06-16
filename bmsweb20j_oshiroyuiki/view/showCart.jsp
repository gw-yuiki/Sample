<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<html>
	<head>
		<title>書籍一覧画面</title>
	</head>
	<body>
		<%@include file="../common/header.jsp"%>
		<table style="margin:auto; width:850px">
			<tr>
				<td style="text-align:center; width:80px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td style="text-align:center; width:80px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/list">書籍一覧</a>]</td>
				<td style="text-align:center; width:508px; font-size:24px;"><strong>カート内容</strong></td>
				<td style="width:80px">&nbsp;</td>
				<td style="width:80px">&nbsp;</td>
			</tr>
		</table>

		<hr style="text-align:center; height:2px; background-color:black; width:950px">
		<div style="margin-bottom:250px">

			<div style="width:864px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:40px">

			</div>

			<div style="width:864px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:40px">
				<div style="margin:auto; width: 100%">
					<div style="display: flex; justify-content: space-between;">
						<div style="background-color:#6666ff; width:211px">ISBN</div>
						<div style="background-color:#6666ff; width:211px">TITLE</div>
						<div style="background-color:#6666ff; width:211px">価格</div>
						<div style="background-color:#6666ff; width:211px" colspan="2">変更/削除/カートに入れる</div>
					</div>
					<%
					ArrayList<Book> list =(ArrayList<Book>)request.getAttribute("book_list");
					int total = 0;
					if(list != null){
						for(int i=0;i<list.size();i++){
							Book books = (Book)list.get(i);
							total += books.getPrice();
					%>
					<div style="display: flex; justify-content: space-between;">
						<div style="text-align:center; width:211px"><a href="<%=request.getContextPath() %>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></div>
						<div style="text-align:center; width:211px"><%=books.getTitle()%></div>
						<div style="text-align:center; width:211px"><%=books.getPrice()%></div>
						<div style="text-align:center; width:211px"><a href="<%=request.getContextPath()%>/showCart?delno=<%=list.indexOf(list.get(i))%>">削除</a></div>
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
			<hr style="text-align:center; margin-top: 80px; height:2px; background-color:black; width:950px">
			<div style="test-align:center; display: flex; justify-content: center;">
				<div style="text-align:center; background-color:#6666ff; width:100px">合計</div>
				<div style="padding-left: 20px"><%= total %></div>
			</div>
			<div style="test-align:center">
				<div style="display: flex; justify-content: center;">
				<form action="<%=request.getContextPath()%>/buyConfirm" method="get">
					<input type="submit"  size="100" value="購入" style="padding-left: 10px"></input>
				</form>
				</div>
			</div>

		</div>
		<%@include file="../common/footer.jsp" %>
	</body>
</html>