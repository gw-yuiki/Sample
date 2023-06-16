<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.OrderedItem"%>
<html>
	<head>
		<title>書籍一覧画面</title>
	</head>
	<body>
		<%@include file="../common/header.jsp"%>
		<table style="margin:auto; width:850px">
			<tr>
				<td style="text-align:center; width:80px">[<a style="text-decoration: none;" href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td style="text-align:center; width:508px; font-size:24px;"><strong>購入状況</strong></td>
				<td style="width:80px">&nbsp;</td>
				<td style="width:80px">&nbsp;</td>
			</tr>
		</table>

		<hr style="text-align:center; height:2px; background-color:black; width:950px">
		<div style="margin-bottom:250px">

			<div style="width:815px; display: flex; justify-content: flex-start; margin:0 auto; font-size: 30px; font-weight: bold">
			</div>

			<div style="width:864px; text-align:center; display: flex; justify-content: space-between; margin:0 auto; padding-top:40px">
				<div style="margin:auto; width: 100%">
					<div style="display: flex; justify-content: space-between;">
						<div style="background-color:#6666ff; width:283px">ユーザー</div>
						<div style="background-color:#6666ff; width:283px">TITLE</div>
						<div style="background-color:#6666ff; width:283px">注文日</div>
					</div>
					<%
					ArrayList<OrderedItem> list =(ArrayList<OrderedItem>)request.getAttribute("book_list");
					int total = 0;
					if(list != null){
						for(int i=0;i<list.size();i++){
							OrderedItem items = (OrderedItem)list.get(i);
					%>
					<div style="display: flex; justify-content: space-between;">
						<div style="text-align:center; width:283px"><%=items.getUserid()%></div>
						<div style="text-align:center; width:283px"><%=items.getTitle()%></div>
						<div style="text-align:center; width:283px"><%=items.getDate()%></div>
					</div>
					<%
						}
					}else{
					%>
					<div style="display: flex;">
						<div style="text-align:center; width:283px">&nbsp;</div>
						<div style="text-align:center; width:283px">&nbsp;</div>
						<div style="text-align:center; width:283px">&nbsp;</div>
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