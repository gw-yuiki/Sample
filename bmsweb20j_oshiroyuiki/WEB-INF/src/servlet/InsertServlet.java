package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;

/**
 * プログラム名：書籍管理システムWeb版 Ver2.0
 * プログラムの説明：書籍管理システムWeb版 Ver1.0の書籍登録機能を記述した処理
 * 作成者：大城百生
 * 作成日付：2023/06/12
 *
 * @author Globalway
 *
 */
public class InsertServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
	throws ServletException ,IOException{

		//BookDAOクラスのオブジェクト
		BookDAO bookDao = new BookDAO();

		//登録する書籍情報を格納するBookオブジェクト
		Book book = new Book();

		String error = "";
		String cmd = "";

		try {
			//画面からの入力情報を受け取るためのエンコード
			request.setCharacterEncoding("UTF-8");

			//パラメータを取得
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String strPrice = request.getParameter("price");

			//データ未入力チェック
			if (isbn.equals("")) {
				error = "ISBNが未入力の為、書籍登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			if (title.equals("")) {
				error = "タイトルが未入力の為、書籍登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			if (strPrice.equals("")) {
				error = "価格が未入力の為、書籍登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			int price;
			try {
				//数値チェック
				price = Integer.parseInt(strPrice);
			} catch (NumberFormatException e) {
				error = "価格の値が不正の為、書籍登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			// ISBNの重複チェック
			if (bookDao.selectByIsbn(isbn).getIsbn() != null) {
				error = "入力ISBNは既に登録済みの為、書籍登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			//画面からの入力情報を受け取り、Bookオブジェクトに格納
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(price);

			//insert（）メソッドを利用して、Bookオブジェクトに格納された書籍データをデータベースに登録
			bookDao.insert(book);
		}catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
			cmd = "logout";
		}finally {
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