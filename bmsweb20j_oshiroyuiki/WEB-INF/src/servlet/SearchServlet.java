package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;


public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// 文字コードを設定する
			request.setCharacterEncoding("UTF-8");

			//isbn,title,price等の入力パラメータを取得
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			//BookDAOをインスタンス化
			BookDAO objDao = new BookDAO();

			//書籍を検索するメソッドを呼び出し、Bookオブジェクトのリストを取得
			ArrayList<Book> bookList = objDao.search(isbn, title, price);

			//取得したリストをリクエストスコープに格納
			request.setAttribute("book_list", bookList);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";

		} finally {
			//エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				//エラーが無い場合はlist.jspにフォワード
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);
			} else {
				//エラーが有る場合はerror.jspにフォワード
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
