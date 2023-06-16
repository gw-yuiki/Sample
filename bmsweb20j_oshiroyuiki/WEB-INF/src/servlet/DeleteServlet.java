package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import dao.BookDAO;

/**
 * プログラム名：書籍管理システムWeb版 Ver2.0
 * プログラムの説明：書籍管理システムWeb版 Ver1.0の書籍削除機能を記述した処理
 * 作成者：大城百生
 * 作成日付：2023/06/12
 *
 * @author Globalway
 *
 */
public class DeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
	throws ServletException ,IOException{

		BookDAO bookDao = new BookDAO();

		String error = "";
		String cmd = "";

		try {
			//画面からの入力情報を受け取るためのエンコード
			request.setCharacterEncoding("UTF-8");

			//パラメータを取得
			String isbn = request.getParameter("isbn");

			//削除対象の有無のエラーチェック
			if (bookDao.selectByIsbn(isbn).getIsbn() == null) {
				error = "削除対象の書籍が存在しない為、書籍削除処理は行えませんでした。";
				cmd = "list";
				return;
			}

			//delete()メソッドを利用して書籍情報を削除
			bookDao.delete(isbn);

		}catch(IllegalStateException e) {
			error = "DB接続エラーの為、書籍削除処理は行えませんでした。";
			cmd = "logout";
		} finally {
			if (error.equals("")) {
				//エラーが無い場合はListServletにフォワード
				request.getRequestDispatcher("/list").forward(request, response);
			} else {
				//エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
