package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.OrderedItem;
import dao.OrderedItemDAO;

public class ShowOrderedItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
		throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		ArrayList<OrderedItem> list;

		try {
			//OrderedItemDAOをインスタンス化し、テーブルを結合して購入履歴データを取得するメソッドを呼び出す
			OrderedItemDAO orderedItemDao = new OrderedItemDAO();
			list = orderedItemDao.selectAll();

			//リクエストスコープに"orderd_list"で格納
			request.setAttribute("book_list", list);

			//showOrderdItem.jspにフォワード
			request.getRequestDispatcher("/view/showOrderedItem.jsp").forward(request, response);

		}catch (IllegalStateException e) {
			//エラー処理
			error = "DB接続エラーの為、購入状況確認は出来ません。";
			cmd = "logout";
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}
}
