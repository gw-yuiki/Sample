package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDAO;

public class UpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//errorメッセージを格納するための変数宣言
		String error = "";

		//jspでcmd情報を判断して動的にリンク先を変更するための変数(/listで初期化)
		String cmd = "list";

		try {
			//画面からの入力情報を受け取るためのエンコードを設定
			request.setCharacterEncoding("UTF-8");

			//isbn、title、priceの入力パラメータを取得
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			//全データの空白チェック(データが入力されているかどうか)
			if (title.equals("")) {
				error = "タイトルが未入力の為、書籍更新処理は行えませんでした。";
			} else if (price.equals("")) {
				error = "価格が未入力の為、書籍更新処理は行えませんでした。";
			} else {

				//BookDAOクラスのオブジェクトを生成
				BookDAO bookDAO = new BookDAO();

				//変更する書籍情報を格納するBookオブジェクトを生成
				Book book = new Book();

				//更新対象の書籍情報をDBから取得
				book = bookDAO.selectByIsbn(isbn);

				//更新対象の書籍の存在チェック
				if (book.getIsbn() == null) {
					error = "更新対象の書籍が存在しない為、書籍更新処理は行えませんでした。";
					return;
				}

				//画面からの受け取った情報をBookオブジェクトに格納
				book.setPrice(Integer.parseInt(price));
				book.setIsbn(isbn);
				book.setTitle(title);

				//更新
				bookDAO.update(book);
			}
		} catch (

		IllegalStateException e) {
			//DB error
			error = "DB接続エラーの為、書籍更新処理は行えませんでした。";
			cmd = "logout";

			//文字が入力された場合のエラー設定
		} catch (NumberFormatException e) {
			error = "価格の値が不正の為、書籍更新処理は行えませんでした。";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;

		} finally {
			if (error.equals("")) {
				//検索結果を持ってlist.jsp(ListServlet)にフォワード
				request.getRequestDispatcher("/list").forward(request, response);

			} else {
				//errorメッセージをリクエストスコープに登録
				request.setAttribute("error", error);
				//cmd情報を判断してエラー画面で動的にリンク先を変更するため
				request.setAttribute("cmd", cmd);
				//error.jspにフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
