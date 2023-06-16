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
				<td style="text-align:left; width:127px; padding-left: 60px; font-size:24px;"><strong>書籍一覧</strong></td>
			</tr>
		</table>

		<hr style="text-align:center; height:2px; background-color:black; width:950px">
		<div style="margin-bottom:250px">

			<div style="width:815px; display: flex; justify-content: flex-start; margin:0 auto; font-size: 24px; font-weight: bold; padding-top: 50px">
				初期データとして以下のデータを登録しました。
			</div>

			<div style="width:864px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:40px">
				<div style="margin:auto; width: 100%">
					<div style="display: flex; justify-content: space-between;">
						<div style="background-color:#6666ff; width:283px">ISBN</div>
						<div style="background-color:#6666ff; width:283px">TITLE</div>
						<div style="background-color:#6666ff; width:283px">価格</div>
					</div>
					<%
					ArrayList<Book> list =(ArrayList<Book>)request.getAttribute("book_list");
					if(list != null){
						for(int i=0;i<list.size();i++){
							Book books = (Book)list.get(i);
					%>
					<div style="display: flex; justify-content: space-between;">
						<div style="text-align:center; width:283px"><a href="<%=request.getContextPath() %>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></div>
						<div style="text-align:center; width:283px"><%=books.getTitle()%></div>
						<div style="text-align:center; width:283px"><%=books.getPrice()%></div>
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