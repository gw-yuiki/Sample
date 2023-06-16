package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;

/**
 * プログラム名：書籍管理システムWeb版 Ver2.0
 * プログラムの説明：書籍管理システムWeb版 Ver1.0の書籍一覧機能を記述した処理
 * 作成者：大城百生
 * 作成日付：2023/05/23
 *
 * @author Globalway
 *
 */
public class ListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
	throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		//BookDAOクラスをオブジェクト化
		BookDAO bookDao = new BookDAO();

		try {
			//作成したオブジェクトでselectAllメソッドを呼び出し
			ArrayList<Book> list = bookDao.selectAll();

			//リクエストスコープへデータを登録
			request.setAttribute("book_list", list);
		}catch (IllegalStateException e) {
				error = "DB接続エラーの為、一覧表示は行えませんでした。";
				cmd = "logout";
		} finally {
			//エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				// エラーが無い場合はlist.jspにフォワード
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
