package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;

/**
 * プログラム名：書籍管理システムWeb版 Ver2.0
 * プログラムの説明：書籍管理システムWeb版 Ver1.0の書籍詳細機能を記述した処理
 * 作成者：大城百生
 * 作成日付：2023/06/12
 *
 * @author Globalway
 *
 */
public class DetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
	throws ServletException ,IOException{

		//BookDAOクラスのオブジェクトを生成
		BookDAO bookDao = new BookDAO();
		//表示する書籍情報を格納するBookオブジェクトを生成
		Book book = new Book();

		String error = "";
		String cmd = "";

		try {

			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			//パラメータを取得
			String isbn = request.getParameter("isbn");
			cmd = request.getParameter("cmd");

			//selectByIsbn（）メソッドを利用して書籍情報を取得
			book = bookDao.selectByIsbn(isbn);

			//詳細情報対象の有無のエラーチェック
			if (book.getIsbn() == null) {
				if (cmd.equals("detail")) {
					error = "表示対象の書籍が存在しない為、詳細情報は表示できませんでした。";
				}
				cmd = "list";
				return;
			}

			//取得した書籍情報を「book」という名前でリクエストスコープに登録
			request.setAttribute("book", book);

		}catch(IllegalStateException e) {
			if (cmd.equals("detail")) {
				error = "DB接続エラーの為、書籍詳細は表示できませんでした。";
			}
			cmd = "logout";
		}finally {
			//エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				//cmdの値でフォワード先を呼び分ける
				if (cmd.equals("detail")) {
					request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
				}else if(cmd.equals("update")) {
					request.getRequestDispatcher("/view/update.jsp").forward(request, response);
				}
			} else {
				//エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
